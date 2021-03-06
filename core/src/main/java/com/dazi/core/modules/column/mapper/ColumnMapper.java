package com.dazi.core.modules.column.mapper;

import com.dazi.core.common.datatable.Order;
import com.dazi.core.modules.column.model.Column;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Column record);

    int insertSelective(Column record);

    Column selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Column record);

    int updateByPrimaryKey(Column record);

    int selectTotal(@Param("record") Column record);

    List<com.dazi.core.modules.column.model.Column> selectList(@Param("record") Column record, @Param("order") Order order, @Param("offset") int offset, @Param("count") int count);
}