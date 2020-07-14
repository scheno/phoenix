package com.schening.phoenix.security.web.impl;

import com.schening.phoenix.security.web.LoginResource;
import com.schening.phoenix.security.web.SalaryResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author schening
 * @date 2020/7/13
 */
@RestController
public class SalaryResourceImpl implements SalaryResource {

    @Resource
    private LoginResource loginResource;

    private final static String PREFIX = "/salary";

    @Override
    @GetMapping(PREFIX)
    public String listSalary() {
        return loginResource.getUserName() + "查询薪资信息";
    }

}
