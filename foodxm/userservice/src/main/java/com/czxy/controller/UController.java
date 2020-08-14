package com.czxy.controller;

import com.czxy.domain.Orders;
import com.czxy.domain.User;
import com.czxy.service.OrdersService;
import com.czxy.service.UserService;
import com.czxy.utils.JwtUtils;
import com.czxy.utils.RasUtils;
import com.czxy.vo.BaseResult;
import com.czxy.vo.OrderVo;
import com.czxy.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/1/16 0016
 **/
@RestController
@RequestMapping("/user")
public class UController {
    @Resource
    private UserService userService;
    @Resource
    private OrdersService ordersService;

    private static final String priKeyPath = "E:\\ras\\ras.pri";

    /**
     * 登录功能
     * @param user
     * @return
     */
    @PostMapping("/login")
    public BaseResult login(@RequestBody User user) {
        try {
            User loginer = userService.login(user);
            if (loginer != null) {
                String token = JwtUtils.generateToken(loginer, 30000, RasUtils.getPrivateKey(priKeyPath));
                return BaseResult.ok("登录成功").append("token", token).append("user", loginer);
            } else {
                return BaseResult.error("用户名或密码错误,请重试!");
            }
        } catch (Exception e) {
            return BaseResult.error("登录失败,请重试!");
        }

    }

    /**
     * 后台管理员登录功能
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/hlogin")
    public BaseResult hlogin(@RequestBody User user, HttpSession session) {
        User loginer = userService.login(user);
        System.out.println(loginer);
        try {
            if (loginer.getUtype().equals("管理员")) {
                if (loginer != null) {
                    String token = JwtUtils.generateToken(loginer, 30000, RasUtils.getPrivateKey(priKeyPath));
                    return BaseResult.ok("登录成功").append("token", token).append("user", loginer);
                } else {
                    return null;
                }
            } else {
                return BaseResult.error("您无此权限,请联系管理员!");
            }
        } catch (Exception e) {
            return BaseResult.error("用户名或密码错误,请重试!!");
        }


    }

    /**
     * 注册功能
     * @param user
     * @return
     */
    @PostMapping("/register")
    public BaseResult register(@RequestBody User user) {
        System.out.println(user);
        userService.insert(user);
        return BaseResult.ok("注册成功");
    }

    /**
     * 检测用户名
     * @param user
     * @return
     */
    @PostMapping("/checkname")
    public BaseResult CheckUsername(@RequestBody User user) {
        System.out.println(user.getUsername());
        String flag = userService.CheckUsername(user.getUsername());
        System.out.println(flag);
        return BaseResult.ok(flag + "");
    }

    /**
     * 分页查询所有用户
     * @param userVo
     * @return
     */
    @PostMapping("/search")
    public BaseResult searchuser(@RequestBody UserVo userVo) {
        PageInfo<User> pageInfo = userService.selectall(userVo);
        System.out.println(pageInfo.getList());
        return BaseResult.ok("查询成功", pageInfo);
    }

    /**
     * 分页查询所有管理员
     * @param userVo
     * @return
     */
    @PostMapping("/searchadmin")
    public BaseResult searchadmin(@RequestBody UserVo userVo) {
        PageInfo<User> pageInfo = userService.searchadmin(userVo);
        System.out.println(pageInfo.getList());
        return BaseResult.ok("查询成功", pageInfo);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/adds")
    public BaseResult adds(@RequestBody User user) {
        System.out.println(user+"Uuuuuuuuuuuuuuuuuuuuuuuuuu");
        userService.insert(user);
        return BaseResult.ok("添加成功");
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public BaseResult dels(@PathVariable("id") Long id) {
        userService.del(id);
        return BaseResult.ok("删除成功");
    }


    /**
     * 根据id查询某个用户详情
     * @param id
     * @return
     */
    @GetMapping("/searchbyid/{id}")
    public BaseResult searchbyid(@PathVariable("id") Long id) {
        User user = userService.searchbyid(id);
        return BaseResult.ok("查询成功", user);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping
    public BaseResult upde(@RequestBody User user) {
        System.out.println(user);
        userService.upde(user);
        return BaseResult.ok("修改成功");
    }


    /**
     * 查询所有订单并分页
     * @param orderVo
     * @return
     */
    @PostMapping("/opage")
    public BaseResult opage(@RequestBody OrderVo orderVo) {
        PageInfo<Orders> pageInfo = ordersService.page(orderVo);
        System.out.println(pageInfo.getList());
        return BaseResult.ok("查询成功", pageInfo);
    }

    /**
     * 我的订单并分页
     * @param orderVo
     * @param id
     * @return
     */
    @PostMapping("/oneopage/{id}")
    public BaseResult oneopage(@RequestBody OrderVo orderVo,@PathVariable(name = "id")Long id) {
        PageInfo<Orders> pageInfo = ordersService.oneopage(orderVo,id);
        System.out.println(pageInfo.getList());
        return BaseResult.ok("查询成功", pageInfo);
    }

    /**
     * 用户下单
     * @return
     */
    @PostMapping("/oneadd/{id}")
    public BaseResult oneadd(@RequestBody Orders orders,@PathVariable(name = "id")Long id){
        ordersService.oneadd(orders,id);
        return BaseResult.ok("提交成功");
    }


    /**
     * 后台添加订单
     * @param orders
     * @return
     */
    @PostMapping("/addo")
    public BaseResult addo(@RequestBody Orders orders){
        ordersService.addc(orders);
        return BaseResult.ok("提交成功");
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/selectuserall")
    public BaseResult selectuserall(){
        List<User>ulist=userService.selectuserall();
        return BaseResult.ok("查询所有",ulist);
    }


    /**
     * 根据id删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public BaseResult delo(@PathVariable("id") Long id) {
        ordersService.del(id);
        return BaseResult.ok("删除成功");
    }


    /**
     * 根据id查询某个订单详情
     * @param id
     * @return
     */
    @GetMapping("/searchbyoid/{id}")
    public BaseResult searchbyoid(@PathVariable("id") Long id) {
        Orders product = ordersService.searchbyid(id);
        return BaseResult.ok("查询成功", product);
    }

    /**
     * 修改订单
     * @return
     */
    @PutMapping("/up")
    public BaseResult updeo(@RequestBody Orders orders) {
        System.out.println(orders);
        ordersService.upde(orders);
        return BaseResult.ok("修改成功");
    }

    /**
     * 处理订单状态
     * @param id
     * @return
     */
    @PutMapping("/upstatue/{id}")
    public BaseResult upstatue(@PathVariable("id") Long id) {
        System.out.println(id);
        ordersService.upstatue(id);
        return BaseResult.ok("订单处理完成!");
    }









}
