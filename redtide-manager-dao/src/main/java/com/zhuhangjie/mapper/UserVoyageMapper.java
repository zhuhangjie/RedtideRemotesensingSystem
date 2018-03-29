package com.zhuhangjie.mapper;

import com.zhuhangjie.pojo.UserVoyage;
import com.zhuhangjie.pojo.UserVoyageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVoyageMapper {
    int countByExample(UserVoyageExample example);

    int deleteByExample(UserVoyageExample example);

    int insert(UserVoyage record);

    int insertSelective(UserVoyage record);

    List<UserVoyage> selectByExample(UserVoyageExample example);

    int updateByExampleSelective(@Param("record") UserVoyage record, @Param("example") UserVoyageExample example);

    int updateByExample(@Param("record") UserVoyage record, @Param("example") UserVoyageExample example);
}