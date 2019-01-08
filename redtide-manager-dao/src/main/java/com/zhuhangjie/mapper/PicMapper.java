package com.zhuhangjie.mapper;

import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.pojo.PicExample;
import com.zhuhangjie.pojo.QueryPoJo;
import com.zhuhangjie.pojo.QueryPojoDuring;

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
    
    //把年份信息封装到一个List
    List<Integer> selectYears();
    
    //找到数据库中距离今天日期最近的一天
    Pic selectLastDate();
    
    //通过日期找图片对象
    Pic selectByDate(String date);
    
    //找到指定年的指定月份的图像列表
    List<Pic> selectAreaByMonth(QueryPoJo query);
    
    //查询所有日期影像，按照日期排序
    List<Pic> selectAllPicOrderByDate();
    
    //通过日期范围查找影像名称
    List<String> selectNamesByTime(QueryPojoDuring query);
}