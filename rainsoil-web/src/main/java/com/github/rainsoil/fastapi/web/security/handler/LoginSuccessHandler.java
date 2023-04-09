package com.github.rainsoil.fastapi.web.security.handler;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.github.rainsoil.fastapi.web.security.JwtUtils;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Object principal = authentication.getPrincipal();

		LoginUser loginUser = (LoginUser) principal;
		String token = JwtUtils.createToken(loginUser);

		ServletUtil.write(response, JSON.toJSONString(MapBuilder.create().put("access_token", token).put("expiresIn", 600).put("token_type", "Bearer").build()), "application/json;charset=utf-8");
//		ServletUtil.write(response, JsonUtils.toJson(R.ok(token)), "application/json;charset=utf-8");
	}
}
