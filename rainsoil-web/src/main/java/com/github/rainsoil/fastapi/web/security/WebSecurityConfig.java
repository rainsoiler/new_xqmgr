package com.github.rainsoil.fastapi.web.security;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.web.security.handler.JwtAuthenticationEntryPoint;
import com.github.rainsoil.fastapi.web.security.handler.LoginFailureHandler;
import com.github.rainsoil.fastapi.web.security.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 安全配置
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService customUserDetailsService;
	private final LoginSuccessHandler loginSuccessHandler;

	private final LoginFailureHandler loginFailureHandler;

	private final JwtAuthenticationEntryPoint unauthorizedHandler;

	private final AccessDeniedHandler accessDeniedHandler;
	private final JwtAuthenticationTokenFilter authenticationTokenFilter;
	/**
	 * configureAuthentication
	 *
	 * @param authenticationManagerBuilder authenticationManagerBuilder
	 * @since 2022/12/30/030
	 */
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				// 设置UserDetailsService
				.userDetailsService(this.customUserDetailsService)
				// 使用BCrypt进行密码的hash
				.passwordEncoder(passwordEncoder());
	}

	/**
	 * 装载BCrypt密码编码器
	 *
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity

				// 禁用 CSRF
				.csrf().disable()
				.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
				// 授权异常
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.accessDeniedHandler(accessDeniedHandler)
				// 防止iframe 造成跨域
				.and()
				.headers()
				.frameOptions()
				.disable()
				// 不创建会话
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				// 静态资源等等
				.antMatchers(
						HttpMethod.GET,
						"/*.html",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/webSocket/**"
				).permitAll()
				// swagger 文档
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/*/api-docs").permitAll()
				// 文件
				.antMatchers("/avatar/**").permitAll()
				.antMatchers("/file/**").permitAll()
				// 阿里巴巴 druid
				.antMatchers("/druid/**").permitAll()
				.antMatchers("/login").permitAll()
				// 放行OPTIONS请求
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// 所有请求都需要认证
				.anyRequest().authenticated()
				.and()

				.formLogin().successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.and().logout()
				.addLogoutHandler(new LogoutHandler() {
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
						ServletUtil.write(response, JSON.toJSONString(R.ok("退出成功")), "application/json;charset=utf-8");

					}
				}).and().addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.cors();
		;

	}

	@Override
	public void configure(WebSecurity web) {


	}

	/**
	 * AuthenticationManager
	 *
	 * @return org.springframework.security.authentication.AuthenticationManager
	 * @since 2022/12/30/030
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	/**
	 * corsFilter
	 *
	 * @return org.springframework.web.filter.CorsFilter
	 * @since 2022/12/30/030
	 */
	@Bean
	public CorsFilter corsFilter() {
		//1. 添加 CORS配置信息
		CorsConfiguration config = new CorsConfiguration();
		//放行哪些原始域
//		config.addAllowedOrigin("*");
		config.addAllowedOriginPattern("*");
		//是否发送 Cookie
		config.setAllowCredentials(true);
		//放行哪些请求方式
		config.addAllowedMethod("*");
		//放行哪些原始请求头部信息
		config.addAllowedHeader("*");
		//暴露哪些头部信息
//		config.addExposedHeader("*");
		config.setMaxAge(18000L);
		//2. 添加映射路径
		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", config);
		//3. 返回新的CorsFilter
		log.error("开启cors");
		return new CorsFilter(corsConfigurationSource);
	}
}