package com.github.rainsoil.fastapi.web.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.bean.BeanConvertUtils;
import com.github.rainsoil.fastapi.common.core.exception.WarningException;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
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

	private final LoginService loginService;

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
	 * 小程序-根据openid查询用户信息
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


	/**
	 * 小程序-查询店员
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.web.system.entity.WxUser>
	 * @since 2023/04/11
	 */
	@ApiOperation(value = "小程序-查询店员")
	@PostMapping("mini/clerk")
	public R<PageInfo<WxUser>> clerk(@RequestBody PageRequest<WxUser> pageRequest) {
		LoginUser user = loginService.getUser();
		WxUser param = pageRequest.getParam();

		WxUser wxUser = this.iWxUserService.getById(user.getId());
		if (null == param) {
			param = new WxUser();
		}
		param.setStoreId(wxUser.getStoreId())
				.setAdmin("0");
		return R.ok(this.iWxUserService.page(pageRequest));
	}


	/**
	 * 小程序-绑定店员
	 *
	 * @param bindClerkVo 绑定店员vo类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/04/11
	 */
	@ApiOperation(value = "小程序-绑定店员")
	@PostMapping("mini/bindClerk")
	public R miniBindClerk(@RequestBody WxUserVo.BindClerkVo bindClerkVo) {

		LoginUser user = loginService.getUser();
		WxUser wxUser = this.iWxUserService.getById(user.getId());
		// 判断店员是不是已经绑定
		WxUser wxUser2 = this.iWxUserService.getById(bindClerkVo.getId());
		if (null != wxUser2) {
			throw new WarningException(20000);
		}
		WxUser param = BeanConvertUtils.convertTo(bindClerkVo, WxUser::new);
		param.setAdmin("0");
		param.setStoreId(wxUser.getStoreId());
		this.save(param);
		return R.ok();
	}


	/**
	 * 小程序-解除员工绑定
	 *
	 * @param id 员工的id
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/04/11
	 */
	@PostMapping("miniRelieveBindClerk")
	@ApiOperation(value = "小程序-解除员工绑定")
	public R miniRelieveBindClerk(String id) {

		this.iWxUserService.removeById(id);
		return R.ok();
	}


	/**
	 * 根据code获取unionid
	 *
	 * @param code code
	 * @return com.github.rainsoil.fastapi.common.core.R<java.lang.String>
	 * @since 2023/04/13
	 */
	@PostMapping("getUnionid")
	@ApiOperation(value = "根据code获取unionid")
	public R<String> getUnionid(String code) {

		String  unionid = this.iWxUserService.getUnionid(code);
		return R.ok(unionid);
	}
}
