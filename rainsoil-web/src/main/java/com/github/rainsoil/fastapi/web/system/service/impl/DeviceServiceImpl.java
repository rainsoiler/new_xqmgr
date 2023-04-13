package com.github.rainsoil.fastapi.web.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import com.github.rainsoil.fastapi.web.system.entity.Device;
import com.github.rainsoil.fastapi.web.system.entity.Region;
import com.github.rainsoil.fastapi.web.system.entity.Store;
import com.github.rainsoil.fastapi.web.system.mapper.DeviceMapper;
import com.github.rainsoil.fastapi.web.system.service.IDeviceService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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

	private final LoginService loginService;

	@Override
	public PageInfo<Device> page2(PageRequest<Device> pageRequest) {
		LoginUser user = loginService.getUser();

		Device param = pageRequest.getParam();

		MPJLambdaWrapper<Device> wrapper = new MPJLambdaWrapper();
		wrapper.selectAll(Device.class).selectAs(Store::getName, "storeName").selectAs(Region::getName,"regionName");
		wrapper.leftJoin(Store.class, Store::getEquipmentSn, Device::getSn)
				.leftJoin(Region.class, Region::getId, Store::getRegionId);
//				.eq(Region::getCompanyId, user.getId());
		if (null != param) {
			wrapper.like(StrUtil.isNotBlank(param.getSn()), Device::getSn, param.getSn());
			wrapper.like(StrUtil.isNotBlank(param.getBrand()), Device::getBrand, param.getBrand());
			wrapper.like(StrUtil.isNotBlank(param.getStoreName()), Store::getName, param.getStoreName());
		}

		Page<Device> page = getPage(pageRequest);
		page = this.pageDeep(page, wrapper);
		return getPageInfo(page);
	}
}
