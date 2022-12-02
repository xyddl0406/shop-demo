package com.crewmate.shopapicommon.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
    private String regDt;
    private String modDt;
    private int regSeq;
    private int modSeq;
}
