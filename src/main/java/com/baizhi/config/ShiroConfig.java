package com.baizhi.config;

import com.baizhi.authorizingRealm.ShiroAuthorizingRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    // 权限管理
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        System.out.println("getShiroFilterFactoryBean");
        // 创建ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //
        Map<String, String> map = new HashMap<>();
        //设置SecurityManager 需要自动注入securityManager
        // AnonymousFilter               匿名过滤器   anon
        // FormAuthenticationFilter      认证过滤器   authc

        map.put("/admin/login", "anon");
        map.put("/login/**", "anon");
        map.put("/getCode", "anon");
        //map.put("/main.jsp", "anon");
        //有问题，拦截后无法获取到验证码
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login/login.jsp");

        return shiroFilterFactoryBean;
    }

    // 把shiro的SecurityManager权限交给spring工厂
    @Bean
    public SecurityManager getSecurityManager(ShiroAuthorizingRealm shiroAuthorizingRealm, CacheManager cacheManager) {
        System.out.println("getSecurityManager");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(shiroAuthorizingRealm);
        defaultWebSecurityManager.setRealm(shiroAuthorizingRealm);
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
    }

    // 把shiro的缓存权限交给spring工厂
    @Bean
    public CacheManager getCacheManager() {
        CacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }

    @Bean
    public ShiroAuthorizingRealm getShiroAuthorizingRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        ShiroAuthorizingRealm shiroAuthorizingRealm = new ShiroAuthorizingRealm();
        shiroAuthorizingRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroAuthorizingRealm;
    }

    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

}
