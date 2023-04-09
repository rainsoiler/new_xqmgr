package com.github.rainsoil.fastapi.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 登录用户
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Service
public class LoginService {

	/**
	 * 获取登录的用户
	 *
	 * @return com.github.rainsoil.fastapi.web.security.LoginUser
	 * @since 2023/04/09
	 */
	public LoginUser getUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (null == context) {
			return null;
		}
		Authentication authentication = context.getAuthentication();
		if (null == authentication) {
			return null;
		}
		Object details = authentication.getPrincipal();
		if (null == details) {
			return null;
		}
		if (details instanceof LoginUser) {
			return (LoginUser) details;
		} else {
			return null;
		}

	}
}
