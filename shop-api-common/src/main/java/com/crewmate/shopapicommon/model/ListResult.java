package com.crewmate.shopapicommon.model;

import java.util.List;

import lombok.Data;

@Data
public class ListResult<T> {
    int total;
    List<T> list;
}
