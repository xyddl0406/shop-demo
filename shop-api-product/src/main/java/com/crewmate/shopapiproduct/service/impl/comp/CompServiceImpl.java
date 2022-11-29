package com.crewmate.shopapiproduct.service.impl.comp;

import org.springframework.stereotype.Service;

import com.crewmate.shopapicommon.model.ListResult;
import com.crewmate.shopapiproduct.mapper.comp.CompMapper;
import com.crewmate.shopapiproduct.model.comp.Comp;
import com.crewmate.shopapiproduct.service.comp.CompService;

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
