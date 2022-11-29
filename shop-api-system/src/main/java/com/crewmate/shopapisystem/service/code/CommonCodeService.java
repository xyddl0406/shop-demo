package com.crewmate.shopapisystem.service.code;

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

}
