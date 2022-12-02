package com.crewmate.shopapisystem.service.code;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.crewmate.shopapisystem.model.code.Code;
import com.crewmate.shopapisystem.model.code.CommonCodeSearch;

@Mapper
public interface CommonCodeMapper {
    
    /**
     * 공통코드 검색
     * @param search
     * @return
     */
    List<Code> selectCommonCodeList(CommonCodeSearch search);
    int selectCommonCodeListCount(CommonCodeSearch search);
    
    
    /**
     * 공통코드 단일 검색
     * @param codeGrp
     * @return
     */
    List<Code> selectCommonCodeForm(String codeGrp);

    /**
     * 공통코드 그룹 조회
     * @return
     */
    List<Code> selectCommonCodeGrpList();
    
    /**
     * 공통코드 insert
     * @param code
     * @return
     */
    int insertCode(Code code);
}
