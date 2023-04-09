package com.github.rainsoil.fastapi.web.security.handler;

import com.alibaba.fastjson.JSON;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.exception.GlobalMsgCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

		log.debug("{}访问权限不足:{}", request.getRequestURI(), e.getMessage());
		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(JSON.toJSONString(R.failed(GlobalMsgCode.FORBIDDEN, e.getMessage())));
		printWriter.flush();
	}
}