package com.crewmate.shopapicommon.config;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.crewmate.shopapicommon.model.BaseModel;
import com.crewmate.shopapicommon.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * -@RequestBody 로 들어오는 DTO에대하여 insert, update에 필요한 데이터인 reg_seq, mod_seq값을 세션에서 취득하여 주입해준다.
 * BaseModel.java를 상속받은 자식model에만 해당된다.
 * @author CREWMATE
 *
 */
@Slf4j
@ControllerAdvice
public class BaseModelRequestBodyAdvice implements RequestBodyAdvice {

    /**
     * supports의 return 값이 true이면 아래 afterBodyRead 메소드가 동작한다.
     * 컨트롤러에서 DTO파라미터로 선언한 모델중 BaseModel.java를 상속받은 모델이있는지 탐색한 후 있으면
     * true를 리턴한다 -> afterBodyRead 에서 데이터를세팅한다
     * 만약 false를 리턴한다면 값을 주입하지 않는다.
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //controller에서 BaseModel을 바로 사용하는 경우가 없겠지만 방어코딩으로 넣는다.
        if (methodParameter.getParameterType() == BaseModel.class) {
            return true;
        }
        
        //while문으로 여러개의 중첩상속일때의 경우도 처리한다.
        Class<?> tmp = methodParameter.getParameterType().getSuperclass(); //최초 부모클래스 취득.
        while(tmp != null) {
            if (tmp == BaseModel.class) {
                //부모클래스가 BaseModel이면 true리턴
                return true;
            } else {
                //whild문의 동작을 위하여 부모클래스의 부모클래스를 꺼내온다.
                tmp = tmp.getSuperclass();
            }
        }
        
        //부모클래스 중 BaseModel.java를 상속받지않는다면 값을 주입하지않으므로 false리턴
        return false;
    }

    /**
     * 위 supports() 에서 true로 리턴했을때 동작하는 메소드다.
     * Object body 에는 컨트롤러에서 받기로 명시한DTO 객체가 넘어온다.
     * 이 body를 BaseModel로 형 변환 한뒤 세션에서 값을 꺼내서 req_seq, mod_seq에 주입해준다.
     * 
     * ??? body는 분명 BaseModel을 상속받은 자식 Model일텐데, 이걸 BaseModel로 형변환하면 자식모델에있는 데이터가 사라질것같았지만,
     * ??? 사라지지않고 내가 추가한 값만 자식모델에서 받을 수 있었다.
     * ??? 아마 afterBodyRead() 에서 리턴한값이 그대로 컨트롤러로 전달되지않고, 중간에 값을 merge해주거나 형변환해도 사라지지않는것인가?..
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("BaseModel FIND !! - REG_SEQ, MOD_SEQ setting with curruntSessionAdmin");
        BaseModel bm = (BaseModel) body;
        
        int adminSeq = CommonUtils.getCurrentAdmin().getAdminSeq();
        
        bm.setRegSeq(adminSeq);
        bm.setModSeq(adminSeq);
        
        //BaseModel을 리턴해도 Controller에서 자식모델에 담겨있던 데이터가 사라지지 않는다?!
        return bm;
    }

    /**
     * body 가 비었을때 실행하는 메소드. 사용하지않는다.
     */
    @Override
    @Nullable
    public Object handleEmptyBody(@Nullable Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // TODO Auto-generated method stub
        return body;
    }

    /**
     * requestBody가 생성되기 전에는 할일이 없다.
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }
}
