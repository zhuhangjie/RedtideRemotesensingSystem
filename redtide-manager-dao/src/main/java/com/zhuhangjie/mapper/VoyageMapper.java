package com.zhuhangjie.mapper;

import com.zhuhangjie.pojo.Voyage;
import com.zhuhangjie.pojo.VoyageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoyageMapper {
    int countByExample(VoyageExample example);

    int deleteByExample(VoyageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Voyage record);

    int insertSelective(Voyage record);

    List<Voyage> selectByExample(VoyageExample example);

    Voyage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Voyage record, @Param("example") VoyageExample example);

    int updateByExample(@Param("record") Voyage record, @Param("example") VoyageExample example);

    int updateByPrimaryKeySelective(Voyage record);

    int updateByPrimaryKey(Voyage record);
}