package com.czxy.controller;

import com.czxy.domain.Category;
import com.czxy.service.CategoryService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.CategoryVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/2/5 0005
 **/
@RestController
@RequestMapping("/category")
public class CController {
    @Resource
    private CategoryService categoryService;

    @GetMapping
    public BaseResult caselectall(){
        List<Category>clist=categoryService.caselectall();
        return BaseResult.ok("查询成功",clist);
    }


    @PostMapping("/page")
    public BaseResult page(@RequestBody CategoryVo categoryVo) {
        PageInfo<Category> pageInfo = categoryService.page(categoryVo);
        System.out.println(pageInfo.getList());
        return BaseResult.ok("查询成功", pageInfo);
    }


    @PostMapping("/addc")
    public BaseResult addp(@RequestBody Category category) {
        System.out.println(category);
        categoryService.addp(category);
        return BaseResult.ok("添加成功");
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public BaseResult dels(@PathVariable("id") Long id) {
        categoryService.del(id);
        return BaseResult.ok("删除成功");
    }


    /**
     * 根据id查询某个分类
     * @param id
     * @return
     */
    @GetMapping("/searchbyid/{id}")
    public BaseResult searchbyid(@PathVariable("id") Long id) {
        Category category = categoryService.searchbyid(id);
        return BaseResult.ok("查询成功", category);
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @PutMapping
    public BaseResult upde(@RequestBody Category category) {
        System.out.println(category);
        categoryService.upde(category);
        return BaseResult.ok("修改成功");
    }



}
