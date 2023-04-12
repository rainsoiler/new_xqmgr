package com.github.rainsoil.fastapi.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import com.github.rainsoil.fastapi.web.system.entity.PrintRecord;
import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.web.system.mapper.PrintRecordMapper;
import com.github.rainsoil.fastapi.web.system.service.IPrintRecordService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.web.system.service.IWxUserService;
import com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 打印记录 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class PrintRecordServiceImpl extends BaseServiceImpl<PrintRecordMapper, PrintRecord> implements IPrintRecordService {

	private final LoginService loginService;

	private final IWxUserService wxUserService;

	@Override
	public PageInfo<PrintRecordVo.PrintRecordVoReport> printRecordVoReport(PageRequest<PrintRecord> pageRequest) {
		LoginUser user = loginService.getUser();
		WxUser wxUser = wxUserService.getById(user.getId());
		Page<PrintRecordVo.PrintRecordVoReport> page = getPage(pageRequest);
		page = this.baseMapper.printRecordVoReport(wxUser.getStoreId(), page);
		return getPageInfo(page);
	}

	@Override
	public PrintRecordVo.PrintRecordInfoVo printRecordVoReportInfo(String time) {
		LoginUser user = loginService.getUser();
		WxUser wxUser = wxUserService.getById(user.getId());
		return new PrintRecordVo.PrintRecordInfoVo()
				.setTreatedCount(this.baseMapper.printRecordVoReportInfo(time, null, wxUser.getStoreId()))
				.setUntreatedCount(this.baseMapper.printRecordVoReportInfo(time, "1", wxUser.getStoreId()))
				.setTreatedList(this.baseMapper.selectList(new LambdaQueryWrapper<PrintRecord>().eq(PrintRecord::getStoreId, wxUser.getStoreId())
						.like(PrintRecord::getPrintTime, time)))
				.setUnTreatedList(this.baseMapper.selectList(new LambdaQueryWrapper<PrintRecord>()
						.eq(PrintRecord::getStatus, "1")
						.eq(PrintRecord::getStoreId, wxUser.getStoreId())
						.like(PrintRecord::getPrintTime, time)))
				;
	}
}
