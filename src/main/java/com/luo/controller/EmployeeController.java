package com.luo.controller;

import com.luo.dao.DepartmentMapper;
import com.luo.dao.EmployeeMapper;
import com.luo.pojo.Department;
import com.luo.pojo.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    //shiro 新增
    @RequestMapping("/toLogin")
    public String toLogin(){
        System.out.println("返回首页");
        return "index";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获得当前用户
        System.out.println(username);
        System.out.println(password);
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        try{
            subject.login(token); //登录令牌 如果没有异常就说明ok
            return "dashboard";
        }catch (UnknownAccountException e){
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "index";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "index";
        }
    }
    @RequestMapping("/logout")
    public String logout(){
        System.out.println("退出登录");
        return "redirect:/toLogin";

    }

    // shrio新增

    @RequestMapping("/emps")
    public String list(Model model){
        List<Employee> employees=employeeMapper.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")  //这里是Get方法请求
    public String toAddpage(Model model){
        //首先查出所有部门的信息，传递给前端,让用户可以看到所有的可选择
        System.out.println("成功地调用");
        List<Department> departments=departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "/emp/add";
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
    @RequestMapping("getdash")
    public String getdash(){
        return "dashboard";
    }
}
