package com.itheima.tlias_web_management.mapper;

import com.itheima.tlias_web_management.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门管理*/
@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门
     * @return*/
    @Select("select * from dept")
    List<Dept> list();

}
