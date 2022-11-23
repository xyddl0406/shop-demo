package com.crewmate.shopcommonapi.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class ListResult<T> {
    int total;
    List<T> list;
}
