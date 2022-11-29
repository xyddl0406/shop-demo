package com.crewmate.shopapisystem.model.code;

import com.crewmate.shopapicommon.model.Pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCodeSearch extends Pagination {
    
    private String searchType;
    private String searchWord;
    private boolean paging = true;
}
