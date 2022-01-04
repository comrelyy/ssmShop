package com.relyy.shop.backend.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.relyy.shop.backend.services.shiro.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Description 认证，鉴权框架shiro 配置
 * @Created by Reeve Cai
 * @Date 2021/12/10
 */
@Configuration
public class ShiroConfig {

	/**
	 * 配置Shiro生命周期处理器
	 * @return
	 */
	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 为了能在thymeleaf里使用shiro的标签bean
	 * @return
	 */
	@Bean
	public static ShiroDialect shiroDialect(){
		return  new ShiroDialect();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//shiro 核心安全接口
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		/**自定义拦截器限制并发人数**/
		//LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
		//限制同一帐号同时在线的个数
		//filtersMap.put("kickout", kickoutSessionControlFilter());
		//shiroFilterFactoryBean.setFilters(filtersMap);
		/** 配置访问权限 必须是LinkedHashMap，因为它必须保证有序
		过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 一定要注意顺序,否则就不好使了*/

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// anon 标识标识可以不用登录就可以访问的资源
		filterChainDefinitionMap.put("/login","anon");
		filterChainDefinitionMap.put("/getVerify","anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/layuimini/**", "anon");
		filterChainDefinitionMap.put("/upload/**", "anon");
		filterChainDefinitionMap.put("/files/**", "anon");
		filterChainDefinitionMap.put("/blog", "anon");
		filterChainDefinitionMap.put("/blog/open/**", "anon");
		// logout 是shiro提供的过滤器
		filterChainDefinitionMap.put("/logout", "logout");
		// authc 标识需要登录才能访问的资源
		filterChainDefinitionMap.put("/**", "authc");

		//此时访问/userInfo/del需要del权限,在自定义Realm中为用户授权。
		//filterChainDefinitionMap.put("/userInfo/del", "perms[\"userInfo:del\"]");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(){
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(userRealm());
		//todo 缓存管理
		// todo 会话管理
		//securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean
	public Realm userRealm() {
		UserRealm userRealm = new UserRealm();
		return userRealm;
	}

//	@Bean
//	public SessionManager sessionManager() {
//		SessionManager sessionManager = new DefaultSessionManager();
//
//
//	}
}
