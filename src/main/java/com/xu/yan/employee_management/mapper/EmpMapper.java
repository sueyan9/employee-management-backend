package com.xu.yan.employee_management.mapper;

import com.xu.yan.employee_management.model.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("SELECT * FROM emp")
    @Results({
            @Result(property = "dept", column = "dept_id",
                    one = @One(select = "com.xu.yan.employee_management.mapper.DeptMapper.findById"))
    })
    List<Emp> findAll();

    @Select("SELECT * FROM emp WHERE id = #{id}")
    @Results({
            @Result(property = "dept", column = "dept_id",
                    one = @One(select = "com.xu.yan.employee_management.mapper.DeptMapper.findById"))
    })
    Emp findById(int id);

    @Insert("INSERT INTO emp(username, password, name, gender, image, job, entry_date, dept_id, create_time, update_time) " +
            "VALUES(#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entryDate}, #{dept.id}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Emp emp);

    @Update("UPDATE emp SET username=#{username}, password=#{password}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, " +
            "entry_date=#{entryDate}, dept_id=#{dept.id}, create_time=#{createTime}, update_time=#{updateTime} WHERE id=#{id}")
    int update(Emp emp);

    @Delete("DELETE FROM emp WHERE id=#{id}")
    int delete(int id);
}