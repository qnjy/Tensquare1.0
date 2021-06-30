package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    //查询所有
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(id));
    }

    //分页+多条件查询
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap,
                             @PathVariable int page,
                             @PathVariable int size){
        Page<Recruit> pageList = recruitService.findSearch(searchMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Recruit>(pageList.getTotalElements(),pageList.getContent()));
    }

    //根据条件查询
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findSearch(searchMap));
    }

    //新增数据
    @PutMapping
    public Result save(@RequestBody Recruit recruit){
        recruitService.save(recruit);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    //修改数据
    @PutMapping("/{id}")
    public Result update(@PathVariable String id,@RequestBody Recruit recruit){
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //推荐职位
    @GetMapping("/search/recommend")
    public Result recommend(){
        return new Result(true, StatusCode.OK, "查询成功", recruitService.recommend());
    }

    //最新职位
    @GetMapping("/search/newlist")
    public Result newlist() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.newlist());
    }
}
