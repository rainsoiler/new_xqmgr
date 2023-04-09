package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.AppInfo;
import com.github.rainsoil.fastapi.web.system.mapper.AppInfoMapper;
import com.github.rainsoil.fastapi.web.system.service.IAppInfoService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * app信息表 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class AppInfoServiceImpl extends BaseServiceImpl<AppInfoMapper, AppInfo> implements IAppInfoService {

}
