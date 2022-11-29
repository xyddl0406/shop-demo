package com.crewmate.shopapiproduct.model.comp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comp {
    /**
     * 업체 고유번호
     */
    private int compSeq;
    
    /**
     * 업체명
     */
    private String compNm;
    
    /**
     * 업체상태코드
     */
    private String compStatCd;
    
    /**
     * 업체유형코드
     */
    private String compTypeCd;
    
    /**
     * 등록일시
     */
    private String regDt;
    
    /**
     * 수정일시
     */
    private String modDt;
    
    /**
     * 등록자
     */
    private int regSeq;
    
    /**
     * 수정자
     */
    private int modSeq;
}
