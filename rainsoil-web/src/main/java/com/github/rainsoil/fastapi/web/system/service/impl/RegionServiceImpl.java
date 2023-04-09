package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.Region;
import com.github.rainsoil.fastapi.web.system.mapper.RegionMapper;
import com.github.rainsoil.fastapi.web.system.service.IRegionService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class RegionServiceImpl extends BaseServiceImpl<RegionMapper, Region> implements IRegionService {

}
