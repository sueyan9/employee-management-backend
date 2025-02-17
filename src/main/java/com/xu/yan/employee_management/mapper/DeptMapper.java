package com.xu.yan.employee_management.mapper;

import com.xu.yan.employee_management.model.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("SELECT * FROM dept")
    List<Dept> getAllDepts();

    @Select("SELECT * FROM dept WHERE id = #{id}")
    Dept getDeptById(@Param("id") int id);

    @Insert("INSERT INTO dept (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createDept(Dept dept);

    @Update("UPDATE dept SET name = #{name} WHERE id = #{id}")
    int updateDept(Dept dept);

    @Delete("DELETE FROM dept WHERE id = #{id}")
    int deleteDept(@Param("id") int id);
}