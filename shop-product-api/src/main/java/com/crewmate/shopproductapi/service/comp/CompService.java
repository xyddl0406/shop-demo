package com.crewmate.shopproductapi.service.comp;

import com.crewmate.shopcommonapi.model.ListResult;
import com.crewmate.shopproductapi.model.comp.Comp;

public interface CompService {
    
    ListResult<Comp> getCompList();

}
