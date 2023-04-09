package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.web.system.mapper.WxUserMapper;
import com.github.rainsoil.fastapi.web.system.service.IWxUserService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
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

}
