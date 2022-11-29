package com.crewmate.shopapicommon.model;

import lombok.Data;

@Data
public class Pagination {
    private int limit = 10;
    private int offset;
}
