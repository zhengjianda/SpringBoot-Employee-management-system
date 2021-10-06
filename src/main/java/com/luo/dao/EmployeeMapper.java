package com.luo.dao;

import com.luo.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {


    @Autowired
    DepartmentMapper departmentMapper = null;

    //员工增删改查
    //增加一个员工
    public int addEmployee(Employee employee);

    //更新一个员工
    public int updateEmployee(Employee employee);


    //查询所有的员工
    public List<Employee> getAll();

    //通过id查询员工
    public Employee getEmployeeById(Integer id);

    //通过id删除员工
    public void delete(Integer id);





}
