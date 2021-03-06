package com.example.employee_management.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.employee_management.entity.EmAttachment;
import com.example.employee_management.entity.EmEmployee;
import com.example.employee_management.mapper.EmEmployeeMapper;
import com.example.employee_management.service.EmAttachmentService;
import com.example.employee_management.service.EmEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 */
@Service
public class EmEmployeeServiceImpl implements EmEmployeeService {
    @Autowired
    private EmEmployeeMapper employeeMapper;
    @Autowired
    private EmAttachmentService attachmentService;

    /**
     * 添加员工
     *
     * @param employee 员工信息
     * @return
     */
    @Override
    public int addEmployee(EmEmployee employee) {
        return employeeMapper.insert(employee);
    }

    /**
     * 修改员工信息
     *
     * @param employee 员工信息
     * @return
     */
    @Override
    public int editEmployee(EmEmployee employee) {
        return employeeMapper.updateById(employee);
    }

    /**
     * 查找一个员工的信息
     *
     * @param id 员工id
     * @return
     */
    @Override
    public EmEmployee findOne(int id) {
        EmEmployee employee = employeeMapper.selectById(id);
        if (employee.getAvatar() != null) {
            EmAttachment avatarAttach = attachmentService.getAttachInfo(employee.getAvatar());
            employee.setAvatarAttachment(avatarAttach);
        }
        return employee;
    }

    /**
     * 根据id删除某个员工
     *
     * @param id 员工id
     * @return 影响行数
     */
    @Override
    public int deleteById(int id) {
        return employeeMapper.deleteById(id);
    }

    @Override
    public IPage<EmEmployee> selectByParam(int currentPage, int pageSize, HashMap<String, String> where) {
        String keyword = "", entryTimeStart = "", entryTimeEnd = "", status = "", type = "";
        if (where.containsKey("keyword")) {
            keyword = where.get("keyword");
        }
        if (where.containsKey("entryTimeStart")) {
            entryTimeStart = where.get("entryTimeStart");
        }
        if (where.containsKey("entryTimeEnd")) {
            entryTimeEnd = where.get("entryTimeEnd");
        }
        if (where.containsKey("status")) {
            status = where.get("status");
        }
        if (where.containsKey("type")) {
            type = where.get("type");
        }
        Page<?> page = new Page<>(currentPage, pageSize);
        return employeeMapper.selectPage(page, keyword, entryTimeStart, entryTimeEnd, status, type);
    }


}
