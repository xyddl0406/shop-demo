package com.crewmate.shopapisystem.model.admin;

import java.io.Serializable;

import lombok.Data;

@Data
public class Admin implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 527914478962979182L;
    private int adminSeq;
    private String adminId;
    private String adminPw;
}
