package com.itheima.tlias_web_management.service.impl;

import com.itheima.tlias_web_management.mapper.DeptMapper;
import com.itheima.tlias_web_management.pojo.Dept;
import com.itheima.tlias_web_management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


}
