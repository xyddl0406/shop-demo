package com.crewmate.shopsystemapi.service.code;

import com.crewmate.shopcommonapi.model.ListResult;
import com.crewmate.shopsystemapi.model.code.Code;
import com.crewmate.shopsystemapi.model.code.CommonCodeSearch;

public interface CommonCodeService {
    
    /**
     * 공통코드검색
     * @param search
     * @return
     */
    ListResult<Code> getCommonCodeList(CommonCodeSearch search);

}
