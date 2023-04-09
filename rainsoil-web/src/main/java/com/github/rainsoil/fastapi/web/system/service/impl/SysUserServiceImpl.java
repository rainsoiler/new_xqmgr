package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.SysUser;
import com.github.rainsoil.fastapi.web.system.mapper.SysUserMapper;
import com.github.rainsoil.fastapi.web.system.service.ISysUserService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
