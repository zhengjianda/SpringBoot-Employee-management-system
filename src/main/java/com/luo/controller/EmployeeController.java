package com.luo.controller;

import com.luo.dao.DepartmentDao;
import com.luo.dao.DepartmentMapper;
import com.luo.dao.EmployeeDao;
import com.luo.dao.EmployeeMapper;
import com.luo.pojo.Department;
import com.luo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Struct;
import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;


    @RequestMapping("/emps")
    public String list(Model model){
        List<Employee> employees=employeeMapper.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")  //这里是Get方法请求
    public String toAddpage(Model model){
        //首先查出所有部门的信息，传递给前端,让用户可以看到所有的可选择
        List<Department> departments=departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("add"+employee);
       // employeeDao.add(employee);
        employeeMapper.addEmployee(employee);
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        //查出原来的数据
       // Employee employee=employeeDao.getEmployeeById(id);
        Employee employee=employeeMapper.getEmployeeById(id);
        model.addAttribute("emp",employee);
       // Collection<Department> departments=departmentDao.getDepartments();
        List<Department> departments=departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
       // employeeDao.add(employee);
        employeeMapper.updateEmployee(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        //employeeDao.delete(id);
        employeeMapper.delete(id);
        return "redirect:/emps";
    }
}
