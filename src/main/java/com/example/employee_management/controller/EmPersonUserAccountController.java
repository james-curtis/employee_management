package com.example.employee_management.controller;


import com.example.employee_management.common.utils.Result;
import com.example.employee_management.entity.EmPersonUserAccount;
import com.example.employee_management.service.impl.EmPersonUserAccountServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 个人用户账号 前端控制器
 * </p>
 */
@Api(tags = "用户信息的各种操作，以及暂停，注销，启动状态的改变")
@RestController
@RequestMapping("/em-person-user-account")
public class EmPersonUserAccountController{


    @Autowired
    EmPersonUserAccountServiceImpl Service;

    @ApiOperation(value = "郑前====》页面默认展示所有用户信息")
    @PostMapping("/userInformation")
    public Result personUserAccount(){
        return Result.success(Service.select());
    }


    @PutMapping("/Change")
    @ApiOperation(value = "郑前====》通过id和新的状态改变用户状态信息")
    public Result Change(@RequestParam("id") int id,@RequestParam("status") String status) {
        boolean enable = Service.updateStatus(id, status);
        if (enable) {
            return Result.success("成功");
        } else {
            return Result.fail("失败");
        }
    }

    @DeleteMapping("/sing_out")
    @ApiOperation(value = "郑前====》通过id注销用户")
    public Result sing_out(@RequestParam("id") int id){
        Service.deleteById(id);
        return Result.success("成功");
    }

    @PostMapping("/search")
    @ApiOperation(value = "郑前====》查询用户,keywords代表用户的姓名或者该用户编号尾部有效部分的数字")
    public Result search(@RequestParam("keywords") String keywords){
        EmPersonUserAccount search = Service.Search(keywords);
        return Result.success(search);
    }

}
