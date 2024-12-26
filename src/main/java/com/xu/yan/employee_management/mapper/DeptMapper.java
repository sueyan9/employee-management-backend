package com.xu.yan.employee_management.mapper;

import com.xu.yan.employee_management.model.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 查询所有部门
    @Select("SELECT * FROM dept")
    List<Dept> getAllDepts();

    // 根据 ID 查询部门
    @Select("SELECT * FROM dept WHERE id = #{id}")
    Dept getDeptById(@Param("id") int id);

    // 新增部门
    @Insert("INSERT INTO dept (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createDept(Dept dept);

    // 更新部门信息
    @Update("UPDATE dept SET name = #{name} WHERE id = #{id}")
    int updateDept(Dept dept);

    // 删除部门
    @Delete("DELETE FROM dept WHERE id = #{id}")
    int deleteDept(@Param("id") int id);
}