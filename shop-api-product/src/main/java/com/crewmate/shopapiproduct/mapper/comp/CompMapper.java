package com.crewmate.shopapiproduct.mapper.comp;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.crewmate.shopapiproduct.model.comp.Comp;

@Mapper
public interface CompMapper {
    
    List<Comp> selectCompList();
    int selectCompListCount();

}
