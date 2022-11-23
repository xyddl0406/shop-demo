package com.crewmate.shopauth;

import java.io.Serializable;

import lombok.Data;

@Data
public class Admin implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -4391999147791248404L;
    
    private int adminSeq;
    private String adminId;
    private String adminPw;
}
