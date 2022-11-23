package com.crewmate.shopproductapi.service.impl.comp;

import org.springframework.stereotype.Service;

import com.crewmate.shopcommonapi.model.ListResult;
import com.crewmate.shopproductapi.mapper.comp.CompMapper;
import com.crewmate.shopproductapi.model.comp.Comp;
import com.crewmate.shopproductapi.service.comp.CompService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompServiceImpl implements CompService {
    
    private final CompMapper compMapper;

    @Override
    public ListResult<Comp> getCompList() {
        ListResult<Comp> result = new ListResult<Comp>();
        
        result.setTotal(this.compMapper.selectCompListCount());
        result.setList(this.compMapper.selectCompList());
        
        return result;
    }
}
