package com.crewmate.shopapisystem.model.code;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import com.crewmate.shopapicommon.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Code extends BaseModel {
    
    @NotEmpty(message = "코드그룹은 필수값 입니다.")
    private String codeGrp;
    
    @NotEmpty(message = "코드는 필수값 입니다.")
    private String code;
    
    @NotEmpty(message = "코드명은 필수값 입니다.")
    private String codeNm;
    
    
    private String codeVal1;
    private String codeVal2;
    private String codeVal3;
    private String codeVal4;
    private String codeVal5;
    
    @NotEmpty(message = "사용여부는 필수값 입니다.")
    private String useYn;
    
    @NotNull
    private Integer dispNo;
    private String regDt;
    private String modDt;
    private int regSeq;
    private int modSeq;
}