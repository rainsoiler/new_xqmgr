package com.github.rainsoil.fastapi.web.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.bean.BeanConvertUtils;
import com.github.rainsoil.fastapi.common.core.exception.WarningException;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.system.entity.Store;
import com.github.rainsoil.fastapi.web.system.entity.WxUser;
import com.github.rainsoil.fastapi.web.system.service.IStoreService;
import com.github.rainsoil.fastapi.web.system.service.IWxUserService;
import com.github.rainsoil.fastapi.web.system.vo.WxUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 微信用户 前端控制器
 *
 * @author luyanan
 * @since 2023-04-09
 */
@Api(tags = "微信用户")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/wxUser")
public class WxUserController {

	private final IWxUserService iWxUserService;

	private final IStoreService storeService;

	/**
	 * 分页
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.WxUser>>
	 * @since 2023/04/08
	 */
	@ApiOperation(value = "分页")
	@PostMapping("page")
	public R<PageInfo<WxUser>> page(@RequestBody PageRequest<WxUser> pageRequest) {
		PageInfo<WxUser> pageInfo = iWxUserService.page(pageRequest, new PageHandler<WxUser>() {


		});
		return R.ok(pageInfo);
	}

	/**
	 * 保存
	 *
	 * @param wxUser 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@PostMapping("")
	@ApiOperation(value = "保存")
	public R save(@RequestBody WxUser wxUser) {

		this.iWxUserService.save(wxUser);
		return R.ok();
	}

	/**
	 * 修改
	 *
	 * @param wxUser 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@ApiOperation(value = "修改")
	@PutMapping("")
	public R update(@RequestBody WxUser wxUser) {
		this.iWxUserService.updateById(wxUser);
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
		this.iWxUserService.removeById(id);
		return R.ok();
	}


	/**
	 * 根据openid查询用户信息
	 *
	 * @param openid
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.web.system.vo.WxUserVo.WxUserMinioVo>
	 * @since 2023/04/10
	 */
	@PostMapping("miniInfo")
	@ApiOperation(value = "根据openid查询用户信息")
	public R<WxUserVo.WxUserMiniVo> minioInfo(String openid) {

		WxUser wxUser = this.iWxUserService.getById(openid);
		if (null == wxUser) {
			throw new WarningException("用户不存在");
		}
		Store store = storeService.getById(wxUser.getStoreId());

		WxUserVo.WxUserMiniVo wxUserMinioVo = BeanConvertUtils.convertTo(wxUser, WxUserVo.WxUserMiniVo::new);
		wxUserMinioVo.setStoreName(store.getName());
		wxUserMinioVo.setDeviceSN(store.getEquipmentSn());
		return R.ok(wxUserMinioVo);
	}

}