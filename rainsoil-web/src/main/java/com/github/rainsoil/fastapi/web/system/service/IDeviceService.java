package com.github.rainsoil.fastapi.web.system.service;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.web.system.entity.Device;
import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
public interface IDeviceService extends IBaseService<Device> {

	/**
	 * 分页
	 *
	 * @param pageRequest
	 * @return com.github.rainsoil.fastapi.common.core.PageInfo<com.github.rainsoil.fastapi.web.system.entity.Device>
	 * @since 2023/04/13
	 */
	PageInfo<Device> page2(PageRequest<Device> pageRequest);
}
