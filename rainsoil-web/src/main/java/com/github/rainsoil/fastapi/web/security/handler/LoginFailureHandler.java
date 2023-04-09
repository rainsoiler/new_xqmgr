package com.github.rainsoil.fastapi.web.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.github.rainsoil.fastapi.common.core.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	/**
	 * Called when an authentication attempt fails.
	 *
	 * @param request   the request during which the authentication attempt occurred.
	 * @param response  the response.
	 * @param exception the exception which was thrown to reject the authentication
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		log.debug("登录失败了");
		ServletUtil.write(response, JSON.toJSONString(R.failed("用户名或密码错误")), "application/json;charset=utf-8");

	}
}
