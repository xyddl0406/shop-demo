package com.crewmate.shopapiproduct.service.comp;

import com.crewmate.shopapicommon.model.ListResult;
import com.crewmate.shopapiproduct.model.comp.Comp;

public interface CompService {
    
    ListResult<Comp> getCompList();

}
