package com.github.rainsoil.fastapi.common.core.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 基础Mapper
 *
 * @param <T> 泛型
 * @author luyanan
 * @since 2023/3/28
 **/
public interface BaseBaseMapper<T> extends BaseMapper<T> {

	/**
	 * 批量插入 仅适用于 mysql
	 *
	 * @param entityList 实体列表
	 * @return 影响行数
	 */
	Integer insertBatchSomeColumn(List<T> entityList);
}
