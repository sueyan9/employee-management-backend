package com.itheima.tlias_web_management.controller;
import com.itheima.tlias_web_management.pojo.Dept;
import com.itheima.tlias_web_management.pojo.Result;
import com.itheima.tlias_web_management.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

/**
 * 部门管理Controller*/
@Slf4j
@RestController
public class DeptController {


    //private static Logger log= LoggerFactory.getLogger(DeptController.class）；
    @Autowired
    private DeptService deptService;
   //@RequestMapping(value ="/depts",method = RequestMethod.GET)//指定请求方式位GET
    @GetMapping("/depts")
    public Result list() {
        log.info("查询全部部门数据");

          //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
}
