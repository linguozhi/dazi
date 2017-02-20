package com.dazi.core.modules.news.service;

import com.dazi.core.common.datatable.Order;
import com.dazi.core.modules.news.mapper.NewsMapper;
import com.dazi.core.modules.news.model.News;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public int deleteByPrimaryKey(Integer id) {
        Assert.notNull(id,"id不能为空");
        return newsMapper.deleteByPrimaryKey(id);
    }

    public int insert(News record) {
        Assert.notNull(record, "查询对象不能为空");
        return newsMapper.insert(record);
    }

    public int insertSelective(News record) {
        Assert.notNull(record, "查询对象不能为空");
        return newsMapper.insertSelective(record);
    }

    public News selectByPrimaryKey(Integer id) {
        Assert.notNull(id, "查询对象不能为空");
        return newsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(News record) {
        Assert.notNull(record, "查询对象不能为空");
        return newsMapper.updateByPrimaryKeySelective(record);
    }

    public int selectTotal(News record) {
        Assert.notNull(record, "查询对象不能为空");
        return newsMapper.selectTotal(record);
    }

    public List<News> selectList(News record, Order order, int offset, int count) {
        Assert.notNull(record, "查询对象不能为空");
        return newsMapper.selectList(record, order, offset, count);
    }
}