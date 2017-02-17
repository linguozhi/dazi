package com.dazi.dashboard.controller;

import com.dazi.core.common.bootstraptable.BootstrapTable;
import com.dazi.core.common.datatable.Order;
import com.dazi.core.common.protocol.ResponseHelper;
import com.dazi.core.common.utils.IntegerUtil;
import com.dazi.core.modules.column.model.Column;
import com.dazi.core.modules.column.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author : linguozhi@52tt.com
 * @desc : 栏目
 * @date :  2017/2/9
 */
@Controller
@RequestMapping("column")
public class ColumnController extends BaseController{

    @Autowired
    private ColumnService columnService;

    /**
     * index
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "column/index";
    }


    @RequestMapping("/getList")
    @ResponseBody
    public BootstrapTable getList(Column column, Order order, int offset, int limit) {
        int total = columnService.selectTotal(column);
        List<Column> list = null;

        if (total > 0) {
            list = columnService.selectList(column, order, offset, limit);
        }

        return ResponseHelper.buildBootstrapTable(total, list);
    }

    /**
     * 编辑
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {
        Column column = new Column();
        if (IntegerUtil.gtZero(id)) {
            column = columnService.selectByPrimaryKey(id);
        }

        model.addAttribute("column", column);
        return "column/edit";
    }

    /**
     * 更新
     * @param column
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map update(Column column) {
        Assert.isTrue(IntegerUtil.gtZero(column.getId()), "id不能小于1");

        if (columnService.updateByPrimaryKeySelective(column) < 1) {
            return ResponseHelper.buildErrorResult("更新失败");
        }

        return ResponseHelper.buildSuccessResult();
    }


    /**
     * 保存
     * @param column
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map save(Column column) {
        if (columnService.insertSelective(column) < 1) {
            return ResponseHelper.buildErrorResult("保存失败");
        }

        return ResponseHelper.buildSuccessResult();

    }

}
