package com.schening.phoenix.security.domain;

import java.util.List;

/**
 * @author schening
 * @date 2020/7/14
 */
public class PermissionDO {

    private String id;

    private String code;

    private String description;

    private String url;

    private String roleList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "PermissionDO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", roleList='" + roleList + '\'' +
                '}';
    }
}
