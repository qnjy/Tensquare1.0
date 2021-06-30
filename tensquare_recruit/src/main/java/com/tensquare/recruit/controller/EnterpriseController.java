package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ConditionalOnClass
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    //查看全部数据
    @GetMapping
    public Result findAll() {
        List list = enterpriseService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    //根据id查询数据
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findById(id));
    }

    //增加数据
    @PutMapping
    public Result save(@RequestBody Enterprise enterprise) {
        enterpriseService.save(enterprise);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    //修改数据
    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody Enterprise enterprise) {
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        enterpriseService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //得到热门列表
    @GetMapping("/search/hotlist")
    public Result hotlist() {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.hotlist());
    }

    //分页+条件查询
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap,
                             @PathVariable int page,
                             @PathVariable int size) {
        Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);

        PageResult<Enterprise> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());

        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    //条件查询
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findSearch(searchMap));
    }
}
