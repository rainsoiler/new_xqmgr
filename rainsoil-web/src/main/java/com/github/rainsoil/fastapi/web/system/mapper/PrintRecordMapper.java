package com.github.rainsoil.fastapi.web.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.web.system.entity.PrintRecord;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 打印记录 Mapper 接口
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Mapper
public interface PrintRecordMapper extends BaseBaseMapper<PrintRecord> {

	/**
	 * 打印量日报统计
	 *
	 * @param storeId 店铺id
	 * @param page    下表
	 * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo.PrintRecordVoReport>
	 * @since 2023/04/12
	 */
	Page<PrintRecordVo.PrintRecordVoReport> printRecordVoReport(@Param("storeId") String storeId, Page page);


	/**
	 * 小程序-报表详情
	 *
	 * @param time   时间
	 * @param status 状态
	 * @return java.util.List<com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo.PrintRecordInfoGroupmaterialIdVo>
	 * @since 2023/04/12
	 */
	List<PrintRecordVo.PrintRecordInfoGroupmaterialIdVo> printRecordVoReportInfo(@Param("time") String time,
																				 @Param("status") String status,

																				 @Param("storeId") String storeId
	);
}
