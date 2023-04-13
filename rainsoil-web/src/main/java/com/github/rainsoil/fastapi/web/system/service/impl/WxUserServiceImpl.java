package com.github.rainsoil.fastapi.web.system.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.github.rainsoil.fastapi.common.core.exception.WarningException;
import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.web.system.mapper.WxUserMapper;
import com.github.rainsoil.fastapi.web.system.service.IWxUserService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 微信用户 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class WxUserServiceImpl extends BaseServiceImpl<WxUserMapper, WxUser> implements IWxUserService {
	private final WxMaService wxMaService;

	@Override
	public String getUnionid(String code) {

		try {
			WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
			return session.getUnionid();
		} catch (WxErrorException e) {
			throw new WarningException(20001);
		}
	}
}
