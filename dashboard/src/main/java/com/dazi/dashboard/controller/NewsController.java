package com.dazi.dashboard.controller;

import com.dazi.core.common.bootstraptable.BootstrapTable;
import com.dazi.core.common.datatable.DataTable;
import com.dazi.core.common.datatable.Order;
import com.dazi.core.common.protocol.ResponseHelper;
import com.dazi.core.common.utils.IntegerUtil;
import com.dazi.core.modules.column.model.Column;
import com.dazi.core.modules.column.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import com.dazi.core.modules.news.model.News;
import com.dazi.core.modules.news.service.NewsService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private ColumnService columnService;

    @RequestMapping("/index")
    public String index() {
        return "news/index";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public BootstrapTable getList(News news, Order order, int offset, int limit) {
        int total = newsService.selectTotal(news);
        List<News> list = null;
        if(total > 0) {
            list = newsService.selectList(news, order, offset, limit);
        }

        return ResponseHelper.buildBootstrapTable(total, list);
    }

    /**
     * 变价
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {
        News news = new News();
        if (IntegerUtil.gtZero(id)) {
            news = newsService.selectByPrimaryKey(id);
        }

        List<Column> columnList = columnService.selectAll();

        model.addAttribute("columnList", columnList);
        model.addAttribute("news", news);
        return "news/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map update(News news) {
        Assert.notNull(news.getId() , "id not null");

        if (newsService.updateByPrimaryKeySelective(news) < 1 ) {
            return ResponseHelper.buildErrorResult("更新失败");
        }

        return ResponseHelper.buildSuccessResult();
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map save(News news) {
        if (newsService.insertSelective(news) < 1 ) {
            return ResponseHelper.buildErrorResult(" 添加失败 ");
        }

        return ResponseHelper.buildSuccessResult();
    }

    @RequestMapping("/detail")
    public String detail(Integer id, Model model) {
        News news = newsService.selectByPrimaryKey(id);

        model.addAttribute("news", news);
        return "news/detail";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(Integer id) {
        if (newsService.deleteByPrimaryKey(id) < 1) {
            return ResponseHelper.buildErrorResult("删除失败");
        }

        return ResponseHelper.buildSuccessResult("删除成功");
    }
}