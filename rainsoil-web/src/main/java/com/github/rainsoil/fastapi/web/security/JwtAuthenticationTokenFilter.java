package com.github.rainsoil.fastapi.web.security;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		try {


			String token = request.getHeader("Authorization");
			if (StrUtil.isNotBlank(token)) {
				LoginUser loginUser = JwtUtils.getLoginUser(token);
				if (null != loginUser && SecurityContextHolder.getContext().getAuthentication() == null) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					logger.debug(String.format("Authenticated userDetail %s, setting security context", loginUser.getUsername()));
					SecurityContextHolder.getContext().setAuthentication(authentication);
//				TenantContextHolder.setTenantId(loginUser.getTenantId());
				}
			}

		} catch (Exception e) {
			throw e;
		}
		filterChain.doFilter(request, response);
	}
}
