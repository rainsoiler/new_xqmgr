package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.StorageType;
import com.github.rainsoil.fastapi.web.system.mapper.StorageTypeMapper;
import com.github.rainsoil.fastapi.web.system.service.IStorageTypeService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 存储方式 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class StorageTypeServiceImpl extends BaseServiceImpl<StorageTypeMapper, StorageType> implements IStorageTypeService {

}
