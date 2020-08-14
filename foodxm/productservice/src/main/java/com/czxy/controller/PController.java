package com.czxy.controller;

import com.czxy.domain.Orderitem;
import com.czxy.domain.Product;
import com.czxy.service.OrderitemService;
import com.czxy.service.ProductService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.ProductVo;
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
@RequestMapping("/product")
public class PController {
    @Resource
    private ProductService productService;
    @Resource
    private OrderitemService orderitemService;

    /**
     * 分页查询所有商品
     * @param productVo
     * @return
     */
    @PostMapping("/page")
    public BaseResult page(@RequestBody ProductVo productVo) {
        PageInfo<Product> pageInfo = productService.page(productVo);
        System.out.println(pageInfo.getList());
        return BaseResult.ok("查询成功", pageInfo);
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    @PostMapping("/addp")
    public BaseResult addp(@RequestBody Product product) {
        productService.addp(product);
        return BaseResult.ok("添加成功");
    }

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public BaseResult dels(@PathVariable("id") Long id) {
        productService.del(id);
        return BaseResult.ok("删除成功");
    }


    /**
     * 根据id查询某个商品详情
     * @param id
     * @return
     */
    @GetMapping("/searchbyid/{id}")
    public BaseResult searchbyid(@PathVariable("id") Long id) {
        Product product = productService.searchbyid(id);
        return BaseResult.ok("查询成功", product);
    }

    /**
     * 修改商品
     * @param product
     * @return
     */
    @PutMapping
    public BaseResult upde(@RequestBody Product product) {
        System.out.println(product);
        productService.upde(product);
        return BaseResult.ok("修改成功");
    }


    /**
     * 远程调用Feign查询Orderitem
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResult findorid(@PathVariable("id") Long id){
        List<Orderitem> orderitem= orderitemService.findorid(id);
        return  BaseResult.ok("查询成功",orderitem);
    }

    /**
     * 查询所有商品信息
     * @return
     */
    @PostMapping("/spall")
    public BaseResult spall(){
        List<Product>plist=productService.spall();
        return BaseResult.ok("查询所有",plist);
    }


    /**
     * 远程调用Feign添加订单信息
     * @param orderitem
     * @return
     */
    @PostMapping("/addorderitem")
    public BaseResult addorderitem(@RequestBody Orderitem orderitem) {
        orderitemService.addorderitem(orderitem);
        return BaseResult.ok("添加成功");
    }

    /**
     * 根据id删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/delos/{id}")
    public BaseResult delos(@PathVariable("id") Long id) {
     orderitemService.delos(id);
        return BaseResult.ok("删除成功");
    }


    /**
     * 查看订单包含商品信息
     * @param id
     * @return
     */
    @GetMapping("/selectLookorder/{id}")
    public BaseResult selectLookorder(@PathVariable("id") Long id){
        System.out.println(id);
        List<Orderitem>ulist=orderitemService.selectLookorder(id);
        return BaseResult.ok("查询所有",ulist);
    }
}
