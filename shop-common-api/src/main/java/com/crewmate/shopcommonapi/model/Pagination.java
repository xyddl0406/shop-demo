package com.crewmate.shopcommonapi.model;

import lombok.Data;

@Data
public class Pagination {
    private int limit = 10;
    private int offset;
}
