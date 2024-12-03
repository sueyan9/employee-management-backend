package com.itheima.tlias_web_management.mapper;

import com.itheima.tlias_web_management.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**员工管理*/
@Mapper
public interface EmpMapper {

    @Select("Select * from emp")
    List<Emp> findAll();

    @Select("SELECT * FROM emp WHERE id = #{id}")
    Emp findById(Integer id);

    @Insert("INSERT INTO emp (username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, NOW(), NOW())")
    void insert(Emp emp);

    @Update("UPDATE emp SET username = #{username}, password = #{password}, name = #{name}, gender = #{gender}, " +
            "image = #{image}, job = #{job}, entrydate = #{entrydate}, dept_id = #{deptId}, update_time = NOW() " +
            "WHERE id = #{id}")
    void update(Emp emp);

    @Delete("DELETE FROM emp WHERE id = #{id}")
    void delete(Integer id);
}
