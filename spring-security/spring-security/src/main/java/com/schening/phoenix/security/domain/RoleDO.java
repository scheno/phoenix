package com.schening.phoenix.security.domain;

import java.util.List;

/**
 * @author schening
 * @date 2020/7/15
 */
public class RoleDO {

    private String id;

    private String roleName;

    private String description;

    private Integer status;

    private List<PermissionDO> permissionDOList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PermissionDO> getPermissionDOList() {
        return permissionDOList;
    }

    public void setPermissionDOList(List<PermissionDO> permissionDOList) {
        this.permissionDOList = permissionDOList;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", permissionDOList=" + permissionDOList +
                '}';
    }
}
