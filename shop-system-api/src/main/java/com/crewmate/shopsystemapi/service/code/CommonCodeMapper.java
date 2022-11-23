package com.crewmate.shopsystemapi.service.code;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.crewmate.shopsystemapi.model.code.Code;
import com.crewmate.shopsystemapi.model.code.CommonCodeSearch;

@Mapper
public interface CommonCodeMapper {
    
    /**
     * 공통코드 검색
     * @param search
     * @return
     */
    List<Code> selectCommonCodeList(CommonCodeSearch search);
    int selectCommonCodeListCount(CommonCodeSearch search);

}
