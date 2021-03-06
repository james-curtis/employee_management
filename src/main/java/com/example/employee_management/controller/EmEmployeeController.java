package com.example.employee_management.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.employee_management.common.utils.Result;
import com.example.employee_management.entity.EmEmployee;
import com.example.employee_management.service.EmEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//import jdk.nashorn.internal.ir.SetSplitState;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/em-employee")
@Api(value = "EmEmployeeController", tags = {"陈柯雨===>员工管理"})
public class EmEmployeeController {
    @Autowired
    private EmEmployeeService employeeService;

    /**
     * 查询一个员工信息
     *
     * @param id 员工id
     * @return
     */
    @GetMapping("/findOne")
    public Result findOne(int id) {
        return Result.success(employeeService.findOne(id));
    }

    /**
     * 分页搜索查询员工列表
     * @param json 查询参数
     * @return
     */
    @ApiOperation("分页搜索查询员工列表")
    @PostMapping("/selectList")
    public Result selectList(@RequestBody HashMap<String, String> json) {
        int currentPage = 1;
        int pageSize = 10;
        if (json.containsKey("currentPage")) {
            currentPage = Integer.parseInt((String) json.get("currentPage"));
        }
        if (json.containsKey("pageSize")) {
            pageSize = Integer.parseInt((String) json.get("pageSize"));
        }

        IPage<EmEmployee> result = employeeService.selectByParam(currentPage, pageSize, json);
//        HashMap<String, Object> resultMap = new HashMap<>();
//        resultMap.put("data", result);
//        resultMap.put("total",)
        return Result.success(result);
    }

    /**
     * 编辑员工
     *
     * @param employee 员工信息
     * @return
     */
    @ApiOperation("编辑员工")
    @PutMapping("/edit")
    public Result edit(EmEmployee employee) {
        return employeeService.editEmployee(employee) > 0 ? Result.success("修改成功") : Result.fail("修改失败");
    }

    /**
     * 删除员工
     *
     * @param id 员工id
     * @return
     */
    @ApiOperation("删除员工")
    @DeleteMapping("/delete")
    public Result delete(int id) throws MissingRequestValueException {
        EmEmployee employee = employeeService.findOne(id);
        if (employee == null) {
            throw new MissingRequestValueException("没有该员工");
        }
        return employeeService.deleteById(id) > 0 ? Result.success("删除成功") : Result.fail("删除失败");
    }

    /**
     * 添加一个员工
     *
     * @param employee 员工信息
     * @return
     */
    @ApiOperation("添加一个员工")
    @PutMapping("/add")
    public Result add(EmEmployee employee) {
        return employeeService.addEmployee(employee) > 0 ? Result.success("修改成功") : Result.fail("修改失败");
    }

    /**
     * 获取员工类型
     *
     * @return
     */
    @ApiOperation("获取员工类型")
    @GetMapping("/getEmployeeTypes")
    public Result getEmployeeTypes() {
        HashMap<String, String> types = new HashMap<>();
        types.put("full_time", "全职");
        types.put("internship", "实习");
        types.put("part_time_job", "兼职");
        types.put("outsource", "外包");
        types.put("dispatch", "派遣");
        return Result.success(types);
    }

    /**
     * 获取员工岗位
     *
     * @return
     */
    @ApiOperation("获取员工岗位")
    @GetMapping("/getEmployeePosts")
    public Result getEmployeePosts() {
        HashMap<String, String> types = new HashMap<>();
        types.put("ordinary_staff", "普通员工");
        types.put("manager", "经理");
        types.put("executive_director", "主管");
        types.put("minister", "部长");
        types.put("group_leader", "组长");
        types.put("chief_inspector", "总监");
        types.put("management_layer", "管理层");
        types.put("senior_management", "高级管理层");
        types.put("general_manager", "总经理");
        return Result.success(types);
    }

    /**
     * 获取员工职位
     *
     * @return
     */
    @ApiOperation("获取员工职位")
    @GetMapping("/getEmployeeJobs")
    public Result getEmployeeJobs() {
        HashMap<String, String> types = new HashMap<>();
        types.put("administration", "行政");
        types.put("customer_service", "客服");
        types.put("design", "设计");
        types.put("research_and_development", "研发");
        types.put("finance", "财务");
        types.put("personnel_matters", "人事");
        types.put("it", "IT");
        types.put("market", "市场");
        types.put("operate", "运营");
        types.put("product", "产品");
        types.put("purchase", "采购");
        types.put("sale", "销售");
        types.put("other", "其他");
        return Result.success(types);
    }

    /**
     * 获取员工状态
     *
     * @return
     */
    @ApiOperation("获取员工类型")
    @GetMapping("/getEmployeeStatuses")
    public Result getEmployeeStatuses() {
        HashMap<String, String> types = new HashMap<>();
        types.put("formal", "正式");
        types.put("on_trial", "试用");
        types.put("quit", "离职");
        types.put("pending_resignation", "待离职");
        return Result.success(types);
    }

}
