package com.github.rainsoil.fastapi.web.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自定义字段填充
 *
 * @author luyanan
 * @since 2023/04/03
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	private final LoginService loginService;

	@Override
	public void insertFill(MetaObject metaObject) {
		LoginUser user = loginService.getUser();

		if (metaObject.hasSetter("createTime")) {
			this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
		}
		if (metaObject.hasSetter("createUser") && null != user) {
			this.strictInsertFill(metaObject, "createUser", () -> user.getId(), String.class);
		}


		if (metaObject.hasSetter("updateTime")) {
			this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
		}
		if (metaObject.hasSetter("updateUser") && null != user) {
			this.strictInsertFill(metaObject, "updateUser", () -> user.getId() + "", String.class);
		}
		if (metaObject.hasSetter("delFlag")) {
			this.strictInsertFill(metaObject, "delFlag", () -> "0", String.class);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		LoginUser user = loginService.getUser();


		this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
		if (null != user) {
			this.strictUpdateFill(metaObject, "updateUser", () -> user.getId(), String.class); // 起始版本 3.3.3(推荐)
		}
	}
}
