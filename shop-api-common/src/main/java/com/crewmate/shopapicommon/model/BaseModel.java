package com.crewmate.shopapicommon.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseModel {
    private String regDt;
    private String modDt;
    private int regSeq;
    private int modSeq;
}
