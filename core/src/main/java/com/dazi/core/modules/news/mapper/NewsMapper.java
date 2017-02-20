package com.dazi.core.modules.news.mapper;

import com.dazi.core.common.datatable.Order;
import com.dazi.core.modules.news.model.News;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    int selectTotal(@Param("record") News record);

    List<News> selectList(@Param("record") News record, @Param("order") Order order, @Param("offset") int offset, @Param("count") int count);
}