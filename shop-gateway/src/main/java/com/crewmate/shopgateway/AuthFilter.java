package com.crewmate.shopgateway;

import java.net.URI;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{
    
    private static final String[] EXCEPT_EXTENSIONS = {"js", "css", "ico"};
    private static final String[] EXCEPT_URIS = {"/login/form", "/auth/login", "/system-api/admin/info/session"};

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //log.info("GatewayFilter start");
        return ((exchange, chain) -> {
            
            if (this.checkExceptUri(exchange.getRequest())) {
                //log.info("{}", exchange.getRequest().getCookies());
                //log.info("{}", exchange.getRequest().getURI());
                MultiValueMap<String, String> c = new LinkedMultiValueMap<String, String>();
                exchange.getRequest().getCookies().toSingleValueMap().forEach((key, httpCookie) -> { c.add(key, httpCookie.getValue()); });
                
                //헤더는 바이패스
                WebClient wc = WebClient.builder()
                        .baseUrl("http://shop.auth:9001")
                        .defaultCookies(cookie -> cookie.putAll(c))
                        .build();
                
                return wc.get()
                        .uri("/auth/check")
                        .retrieve()
                        .bodyToMono(ResponseMessage.class)
                        .map(responseMessage -> {
                            boolean isAuth = (boolean) responseMessage.getData();
                            if (isAuth) {
                                //log.info("AUTH!");
                            } else {
                                //log.info("UNAUTH!");
                                throw new AdminAuthException();
                            }
                            
                            return exchange;
                        })
                        .flatMap(chain::filter);
            } else {
                return chain.filter(exchange);
            }
        });
    }
    
    /**
     * 요청 온 URI가 로그인인증이 필요한 URI인지 체크 
     * @param request
     * @return true - 인증검사 필요 / false - 인증검사 불필요
     */
    private boolean checkExceptUri(ServerHttpRequest request) {
        boolean result = true;
        URI requestedURI = request.getURI();
        
        if (requestedURI.getPath().startsWith("/_nuxt")) { //이걸하는게 맞나?
            //log.info("[ /_nuxt ] request : {}", request.getURI());
            return false;
        }
        
        for (String extension : EXCEPT_EXTENSIONS) {
            if (requestedURI.getPath().toLowerCase().endsWith(extension)) {
                //log.info("AuthFilter checkExceptUri IGNORE!>>>>>> {}", extension);
                return false;
            };
        }
        
        for (String URI : EXCEPT_URIS) {
            if (requestedURI.getPath().toLowerCase().endsWith(URI)) {
                //log.info("AuthFilter checkExceptUri IGNORE!>>>>>> {}", URI);
                return false;
            };
        }
        
        return result; 
    }

    public static class Config {

    }
}
