package com.schening.phoenix.security.web.impl;

import com.schening.phoenix.security.web.LoginResource;
import com.schening.phoenix.security.web.ReportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author schening
 * @date 2020/7/13
 */
@RestController
public class ReportResourceImpl implements ReportResource {

    @Resource
    private LoginResource loginResource;

    private final static String PREFIX = "/report";

    @Override
    @GetMapping(PREFIX)
    public String listReports() {
        return loginResource.getUserName() + "查询报表信息";
    }

}
