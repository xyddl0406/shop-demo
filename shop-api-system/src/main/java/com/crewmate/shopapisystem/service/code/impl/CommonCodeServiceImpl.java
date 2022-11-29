package com.crewmate.shopapisystem.service.code.impl;

import org.springframework.stereotype.Service;

import com.crewmate.shopapicommon.model.ListResult;
import com.crewmate.shopapisystem.model.code.Code;
import com.crewmate.shopapisystem.model.code.CommonCodeSearch;
import com.crewmate.shopapisystem.service.code.CommonCodeMapper;
import com.crewmate.shopapisystem.service.code.CommonCodeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {
    
    private CommonCodeMapper commonCodeMapper;

    @Override
    public ListResult<Code> getCommonCodeList(CommonCodeSearch search) {
        
        //이걸 공통으로 뽑을수있나?
        ListResult<Code> listResult = new ListResult<Code>();
        
        listResult.setTotal(this.commonCodeMapper.selectCommonCodeListCount(search));
        listResult.setList(this.commonCodeMapper.selectCommonCodeList(search));
        
        return listResult;
    }

}
