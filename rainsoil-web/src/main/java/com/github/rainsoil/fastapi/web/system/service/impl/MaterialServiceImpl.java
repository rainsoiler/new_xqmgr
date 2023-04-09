package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.Material;
import com.github.rainsoil.fastapi.web.system.mapper.MaterialMapper;
import com.github.rainsoil.fastapi.web.system.service.IMaterialService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 物料表 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class MaterialServiceImpl extends BaseServiceImpl<MaterialMapper, Material> implements IMaterialService {

}
