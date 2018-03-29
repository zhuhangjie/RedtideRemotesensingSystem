package com.zhuhangjie.mapper;

import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.pojo.PicExample;
import com.zhuhangjie.pojo.QueryPoJo;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PicMapper {
    int countByExample(PicExample example);

    int deleteByExample(PicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pic record);

    int insertSelective(Pic record);

    List<Pic> selectByExample(PicExample example);

    Pic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByExample(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);

    List<Pic> selectYears();
    
    List<Pic> selectAreaByMonth(QueryPoJo query);
    
    Pic selectLastDate();
    
    Pic selectByDate(String date);
}