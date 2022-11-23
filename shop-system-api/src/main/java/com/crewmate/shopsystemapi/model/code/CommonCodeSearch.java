package com.crewmate.shopsystemapi.model.code;

import com.crewmate.shopcommonapi.model.Pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCodeSearch extends Pagination {
    
    private String searchType;
    private String searchWord;
    private boolean paging = true;
}
