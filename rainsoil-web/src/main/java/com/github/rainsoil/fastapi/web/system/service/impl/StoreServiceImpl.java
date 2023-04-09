package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.Store;
import com.github.rainsoil.fastapi.web.system.mapper.StoreMapper;
import com.github.rainsoil.fastapi.web.system.service.IStoreService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class StoreServiceImpl extends BaseServiceImpl<StoreMapper, Store> implements IStoreService {

}
