package com.xu.yan.employee_management.model;


import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.NStringTypeHandler;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;



//@Table(name = "emp")
@Data//自动生成所有 Getter、Setter、toString、equals 和 hashCode 方法
//@Entity
@Getter
@Setter
public class Emp {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private int gender; // 1 for male, 0 for female (or other representations based on your business logic)
    private String image;
    private int job;
    private LocalDate entryDate;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //@ManyToOne  // 关系映射，多个员工对应一个部门
    //@JoinColumn(name = "dept_id")
    private Dept dept;  // dept 字段类型为 Dept

}