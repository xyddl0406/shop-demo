package com.crewmate.shopapisystem.service.code;

import java.util.List;

import com.crewmate.shopapicommon.model.ListResult;
import com.crewmate.shopapisystem.model.code.Code;
import com.crewmate.shopapisystem.model.code.CommonCodeSearch;

public interface CommonCodeService {
    
    /**
     * 공통코드검색
     * @param search
     * @return
     */
    ListResult<Code> getCommonCodeList(CommonCodeSearch search);
    
    /**
     * 공통코드 단일 검색
     * @param codeGrp
     * @return
     */
    List<Code> getCommonCodeForm(String codeGrp);
    
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
