package com.github.rainsoil.fastapi.web.security;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

/**
 * jwt
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Slf4j
@UtilityClass
public class JwtUtils {

	public static final Long expireTime = 30 * 24 * 60 * 60 * 1000L;

	public static final String signKey = "sdfsd12334";

	/**
	 * 生成token
	 *
	 * @param loginUser 登录用户
	 * @return java.lang.String
	 * @since 2023/04/09
	 */
	public String createToken(LoginUser loginUser) {
		String token = Jwts.builder().setSubject(loginUser.getUsername())
				.claim("user", JSON.toJSONString(loginUser))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(SignatureAlgorithm.HS512, signKey)
				.compressWith(CompressionCodecs.GZIP).compact();
		return token;
	}

	/**
	 * 获取登录用户
	 *
	 * @param token
	 * @return com.github.rainsoil.fastapi.web.security.LoginUser
	 * @since 2023/04/09
	 */
	public LoginUser getLoginUser(String token) {
		if (StrUtil.isBlank(token)) {
			return null;
		}
		token = token.replace("Bearer ", "");
		try {
			Jws<Claims> jws = Jwts.parser().setSigningKey(signKey).parseClaimsJws(token);
			Object user = jws.getBody().get("user");
			return JSON.parseObject(user.toString(), LoginUser.class);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("admin123");
		System.out.println(encode);
		System.out.println(passwordEncoder.matches("admin123", "$2a$10$ALwH7XbiW6YTj0TgXqlw2uimwziPooOgArIlryOC9AOlY.O325aiu"));
	}
}
