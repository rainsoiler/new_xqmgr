package com.github.rainsoil.fastapi.web.system.service;

import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;

/**
 * <p>
 * 微信用户 服务类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
public interface IWxUserService extends IBaseService<WxUser> {

	/**
	 * 获取unionid
	 *
	 * @param code code
	 * @return java.lang.String
	 * @since 2023/04/13
	 */
	String getUnionid(String code);
}
