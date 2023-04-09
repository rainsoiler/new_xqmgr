package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.Device;
import com.github.rainsoil.fastapi.web.system.mapper.DeviceMapper;
import com.github.rainsoil.fastapi.web.system.service.IDeviceService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class DeviceServiceImpl extends BaseServiceImpl<DeviceMapper, Device> implements IDeviceService {

}
