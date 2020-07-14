package com.schening.phoenix.security.authorization;

import com.schening.phoenix.security.domain.PermissionDO;
import com.schening.phoenix.security.po.PermissionPO;
import com.schening.phoenix.security.po.RolePO;
import com.schening.phoenix.security.repository.PermissionMapper;
import com.schening.phoenix.security.repository.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author schening
 * @date 2020/7/13
 */
@Service
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 每个资源（url）所需要的权限（角色）集合
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 获取每个资源（url）所需要的权限（角色）集合
     * 这里应该从数据库中动态查询，这里为了方便而直接创建
     */
    private void getResourcePermission() {
        map = new HashMap<>(16);

        /**
         * 创建权限Map，存储所有权限信息
         */
        Map<String, ConfigAttribute> configAttributeMap = new HashMap<>(16);
        List<RolePO> rolePOList = roleMapper.listRoles();
        rolePOList.forEach(rolePO -> {
            ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + rolePO.getRoleName().toUpperCase());
            configAttributeMap.put(rolePO.getId(), configAttribute);
        });

        List<PermissionDO> permissionDOList = permissionMapper.listPermissions();
        permissionDOList.forEach(permissionDO -> {
            String[] roleArray = permissionDO.getRoleList().split(",");
            Collection<ConfigAttribute> configAttributeCollection = new ArrayList<>();
            for (String role: roleArray) {
                configAttributeCollection.add(new SecurityConfig("ROLE_" + role.toUpperCase()));
            }
            map.put(permissionDO.getUrl(), configAttributeCollection);
        });

//        /**
//         * 创建两个权限集合
//         */
//        List<ConfigAttribute> adminUrlRoles = new ArrayList<>();
//        adminUrlRoles.add(adminRole);
//        List<ConfigAttribute> employeeUrlRoles = new ArrayList<>();
//        employeeUrlRoles.add(employeeRole);
//
//        /**
//         * 设置资源（url）所需要的权限（角色）集合
//         */
//        map.put("/r/r1", adminUrlRoles);
//        map.put("/r/r2", employeeUrlRoles);
//        map.put("/toUser", null);
//        map.put("/toAbout", null);
//        map.put("/toHome", null);
//        map.put("/getPrincipal", null);
//        map.put("/getUserDetails", null);
    }

    /**
     * 获取用户请求的某个具体的资源（url）所需要的权限（角色）集合
     *
     * @param object 包含了用户请求的request信息
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            getResourcePermission();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        /**
         * 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合，
         * 如果全都不匹配，则表示用户请求的资源（url)不需要权限（角色）即可访问
         */
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String url = iter.next();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
