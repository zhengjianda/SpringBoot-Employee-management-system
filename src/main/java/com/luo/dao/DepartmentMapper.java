package com.luo.dao;


import com.luo.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {

    // 获取所有部门的信息
    public List<Department> getDepartments();

    //通过id获取部门
    public Department getDepartmentById(Integer id);
}
