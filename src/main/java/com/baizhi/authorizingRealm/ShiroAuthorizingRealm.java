package com.baizhi.authorizingRealm;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.AdminRoleDao;
import com.baizhi.dao.RoleDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminRole;
import com.baizhi.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShiroAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private RoleDao roleDao;


    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 拿到主身份
        String username = (String) principalCollection.getPrimaryPrincipal();
        //根据username查询相应的角色

        Admin admin = new Admin();
        admin.setUsername(username);
        Admin loginAdmin = adminDao.selectOne(admin);
        System.out.println("loginAdmin = " + loginAdmin);

        // AdminRole
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(loginAdmin.getId());
        System.out.println("adminRole = " + adminRole);
        List<AdminRole> adminRoles = adminRoleDao.select(adminRole);
        // roleDao
        SimpleAuthorizationInfo simpleAuthorizationInfo = null;
        List<String> strings = new ArrayList<>();
        for (AdminRole adminRole1 : adminRoles) {
            Role role = new Role();
            role.setId(adminRole1.getRoleId());
            Role loginRole = roleDao.selectOne(role);
            strings.add(loginRole.getName());
            System.out.println("adminRole1 = " + adminRole1);
            System.out.println("adminRoles = " + adminRoles);
        }
        if (strings.size() != 0) {
            // 授权
            simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRoles(strings);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //   根据username查user对象
        Admin admin = new Admin();
        admin.setUsername((String) token.getPrincipal());
//        从数据库中查询得到的admin
        Admin loginAdmin = adminDao.selectOne(admin);
        System.out.println("loginAdmin = " + loginAdmin);
        if (loginAdmin != null) {
//            将loginAdmin转换为SimpleAuthenticationInfo对象返回
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginAdmin.getUsername(), loginAdmin.getPassword(), ByteSource.Util.bytes(loginAdmin.getSalt()), this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
