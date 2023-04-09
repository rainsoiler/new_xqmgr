package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.MaterialType;
import com.github.rainsoil.fastapi.web.system.mapper.MaterialTypeMapper;
import com.github.rainsoil.fastapi.web.system.service.IMaterialTypeService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 物料分类 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class MaterialTypeServiceImpl extends BaseServiceImpl<MaterialTypeMapper, MaterialType> implements IMaterialTypeService {

}
