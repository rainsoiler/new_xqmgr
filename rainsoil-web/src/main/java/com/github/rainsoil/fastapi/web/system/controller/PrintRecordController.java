package com.github.rainsoil.fastapi.web.system.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import com.github.rainsoil.fastapi.web.system.entity.PrintRecord;
import com.github.rainsoil.fastapi.web.system.entity.SysUser;
import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.web.system.service.IPrintRecordService;
import com.github.rainsoil.fastapi.web.system.service.ISysUserService;
import com.github.rainsoil.fastapi.web.system.service.IWxUserService;
import com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 打印记录 前端控制器
 *
 * @author luyanan
 * @since 2023-04-09
 */
@Api(tags = "打印记录")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/printRecord")
public class PrintRecordController {

	private final IPrintRecordService iPrintRecordService;

	private final LoginService loginService;

	private final ISysUserService sysUserService;

	private final IWxUserService wxUserService;


	/**
	 * 分页
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.PrintRecord>>
	 * @since 2023/04/08
	 */
	@ApiOperation(value = "分页")
	@PostMapping("page")
	public R<PageInfo<PrintRecord>> page(@RequestBody PageRequest<PrintRecord> pageRequest) {
		PageInfo<PrintRecord> pageInfo = iPrintRecordService.page(pageRequest, new PageHandler<PrintRecord>() {


		});
		return R.ok(pageInfo);
	}

	/**
	 * 保存
	 *
	 * @param printRecord 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@PostMapping("")
	@ApiOperation(value = "保存")
	public R save(@RequestBody PrintRecord printRecord) {

		this.iPrintRecordService.save(printRecord);
		return R.ok();
	}

	/**
	 * 修改
	 *
	 * @param printRecord 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@ApiOperation(value = "修改")
	@PutMapping("")
	public R update(@RequestBody PrintRecord printRecord) {
		this.iPrintRecordService.updateById(printRecord);
		return R.ok();
	}

	/**
	 * 根据id删除
	 *
	 * @param id id
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@DeleteMapping()
	@ApiOperation(value = "根据id删除")
	public R remove(@RequestParam(value = "id", required = true) Long id) {
		this.iPrintRecordService.removeById(id);
		return R.ok();
	}


	/**
	 * 小程序-首页统计
	 *
	 * @return com.github.rainsoil.fastapi.common.core.R<MiniStatistics>
	 * @since 2023/04/10
	 */
	@ApiOperation(value = "小程序-首页统计")
	@GetMapping("miniStatistics")
	public R<PrintRecordVo.MiniStatistics> miniStatistics() {

		LoginUser user = loginService.getUser();
		String id = user.getId();
		WxUser wxUser = wxUserService.getById(id);
		String storeId = wxUser.getStoreId();
		long printCount = this.iPrintRecordService.count(new
				LambdaQueryWrapper<PrintRecord>().like(PrintRecord::getPrintTime, DateUtil.date().toString(DatePattern.NORM_DATE_PATTERN))
				.eq(PrintRecord::getStoreId, storeId).eq(PrintRecord::getStatus, 2));

		long totalPrintCount = this.iPrintRecordService.count(new
				LambdaQueryWrapper<PrintRecord>()
				.eq(PrintRecord::getStoreId, storeId).eq(PrintRecord::getStatus, 2));

		long untreatedCount = this.iPrintRecordService.count(new
				LambdaQueryWrapper<PrintRecord>()
				.eq(PrintRecord::getStoreId, storeId).eq(PrintRecord::getStatus, 1));

		return R.ok(new PrintRecordVo.MiniStatistics()
				.setPrintCount(printCount)
				.setTotalPrintCount(totalPrintCount)
				.setUntreatedCount(untreatedCount));
	}


	/**
	 * 小程序-标签管理
	 *
	 * @param pageRequest 分页请求参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.PrintRecord>>
	 * @since 2023/04/11
	 */
	@ApiOperation(value = "小程序-标签管理")
	@PostMapping("miniManage")
	public R<PageInfo<PrintRecord>> miniManage(@RequestBody PageRequest<PrintRecord> pageRequest) {
		PageInfo<PrintRecord> pageInfo = this.iPrintRecordService.page(pageRequest, new PageHandler<PrintRecord>() {
			@Override
			public void queryWrapperHandler(PrintRecord param, LambdaQueryWrapper<PrintRecord> queryWrapper) {
				if (null != param) {
					LoginUser user = loginService.getUser();
					WxUser wxUser = wxUserService.getById(user.getId());
					queryWrapper.eq(PrintRecord::getStoreId, wxUser.getStoreId());
					String miniStatus = param.getMiniStatus();
					if (miniStatus.equals("1")) {
						// 标准(过期时间大于今天的)

						queryWrapper.eq(PrintRecord::getStatus, 1)
								.ge(PrintRecord::getExpirationMs, System.currentTimeMillis());
					} else if (miniStatus.equals("2")) {
						// 临期(过期时间大于今天,小于临期时间之内的)
						queryWrapper.eq(PrintRecord::getStatus, 1)
								.ge(PrintRecord::getExpirationMs, System.currentTimeMillis())
								.lt(PrintRecord::getExpirationMs, DateUtil.offsetDay(new Date(), 2).getTime());
					} else if (miniStatus.equals("3")) {
						// 超时(过期时间小于今天的)
						queryWrapper.eq(PrintRecord::getStatus, "1")
								.lt(PrintRecord::getExpirationMs, System.currentTimeMillis());
					} else if (miniStatus.equals("4")) {
						// 已完结
						queryWrapper.eq(PrintRecord::getStatus, "2");
					}
				}

			}
		});
		return R.ok(pageInfo);
	}

	/**
	 * 小程序-报表列表
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo.PrintRecordVoReport>>
	 * @since 2023/04/12
	 */
	@ApiOperation(value = "小程序-报表")
	@PostMapping(value = "mini/printRecordVoReport")
	public R<PageInfo<PrintRecordVo.PrintRecordVoReport>> printRecordVoReport(@RequestBody PageRequest<PrintRecord> pageRequest) {
		PageInfo<PrintRecordVo.PrintRecordVoReport> pageInfo = this.iPrintRecordService.printRecordVoReport(pageRequest);
		return R.ok(pageInfo);
	}

	/**
	 * 小程序-报表详情
	 *
	 * @param time 时间
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.web.system.vo.PrintRecordVo.PrintRecordInfoVo>
	 * @since 2023/04/12
	 */
	@ApiOperation(value = "小程序-报表详情")
	@PostMapping("mini/printRecordVoReportInfo")
	public R<PrintRecordVo.PrintRecordInfoVo> printRecordVoReportInfo(String time) {

		PrintRecordVo.PrintRecordInfoVo info = this.iPrintRecordService.printRecordVoReportInfo(time);
		return R.ok(info);
	}
}
