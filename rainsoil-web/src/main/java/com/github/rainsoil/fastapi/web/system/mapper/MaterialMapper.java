package com.github.rainsoil.fastapi.web.system.mapper;

import com.github.rainsoil.fastapi.web.system.entity.Material;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 物料表 Mapper 接口
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Mapper
public interface MaterialMapper extends BaseBaseMapper<Material> {

}
