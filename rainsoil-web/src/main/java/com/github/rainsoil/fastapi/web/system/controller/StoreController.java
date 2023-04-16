package com.github.rainsoil.fastapi.web.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import com.github.rainsoil.fastapi.web.system.entity.Store;
import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.web.system.service.IStoreService;
import com.github.rainsoil.fastapi.web.system.service.IWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 店铺表 前端控制器
 *
 * @author luyanan
 * @since 2023-04-09
 */
@Api(tags = "店铺表")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/store")
public class StoreController {

	private final IStoreService iStoreService;

	private final LoginService loginService;

	private final IWxUserService wxUserService;

	/**
	 * 分页
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.Store>>
	 * @since 2023/04/08
	 */
	@ApiOperation(value = "分页")
	@PostMapping("page")
	public R<PageInfo<Store>> page(@RequestBody PageRequest<Store> pageRequest) {
		PageInfo<Store> pageInfo = iStoreService.page(pageRequest, new PageHandler<Store>() {


		});
		return R.ok(pageInfo);
	}

	/**
	 * 保存
	 *
	 * @param store 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@PostMapping("")
	@ApiOperation(value = "保存")
	public R save(@RequestBody Store store) {

		this.iStoreService.save(store);
		return R.ok();
	}

	/**
	 * 修改
	 *
	 * @param store 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@ApiOperation(value = "修改")
	@PutMapping("")
	public R update(@RequestBody Store store) {
		this.iStoreService.updateById(store);
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
		this.iStoreService.removeById(id);
		return R.ok();
	}


	/**
	 * 小程序-绑定云打印机
	 *
	 * @param sn 打印机设备编码
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/04/11
	 */
	@ApiOperation(value = "小程序-绑定云打印机")
	@PostMapping("miniBindSn")
	public R miniBindSn(String sn) {

		LoginUser user = loginService.getUser();
		WxUser wxUser = wxUserService.getById(user.getId());

		this.iStoreService.updateById(new Store()
				.setId(wxUser.getStoreId())
				.setEquipmentSn(sn));
		return R.ok();
	}


	/**
	 * 解绑云打印机
	 *
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/04/16
	 */
	@PostMapping("miniUnBindSn")
	@ApiOperation(value = "解绑云打印机")
	public R miniUnBindSn() {
		LoginUser user = loginService.getUser();
		WxUser wxUser = wxUserService.getById(user.getId());
		this.iStoreService
				.update(
						null,
						Wrappers.<Store>lambdaUpdate()
								.set(Store::getEquipmentSn, null) //把email设置成null
								.eq(Store::getId, wxUser.getStoreId())
				);
		return R.ok();
	}

}
