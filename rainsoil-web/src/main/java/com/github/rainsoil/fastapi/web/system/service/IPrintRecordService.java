package com.github.rainsoil.fastapi.web.system.service;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.web.system.entity.PrintRecord;
import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;
import com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo;

/**
 * <p>
 * 打印记录 服务类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
public interface IPrintRecordService extends IBaseService<PrintRecord> {


	/**
	 * 打印量报表
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.PageInfo<com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo.PrintRecordVoReport>
	 * @since 2023/04/12
	 */
	PageInfo<PrintRecordVo.PrintRecordVoReport> printRecordVoReport(PageRequest<PrintRecord> pageRequest);


	/**
	 * 打印量报表详情
	 *
	 * @param time 时间
	 * @return com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo.PrintRecordInfoVo
	 * @since 2023/04/12
	 */
	PrintRecordVo.PrintRecordInfoVo printRecordVoReportInfo(String time);
}
