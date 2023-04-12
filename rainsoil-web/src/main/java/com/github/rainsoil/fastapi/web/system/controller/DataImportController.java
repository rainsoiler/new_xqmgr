package com.github.rainsoil.fastapi.web.system.controller;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import com.github.rainsoil.fastapi.web.system.entity.AppInfo;
import com.github.rainsoil.fastapi.web.system.entity.Device;
import com.github.rainsoil.fastapi.web.system.entity.Material;
import com.github.rainsoil.fastapi.web.system.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 数据导入
 *
 * @author luyanan
 * @since 2023/04/10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("data/import")
public class DataImportController {

	/**
	 * 数据导入
	 *
	 * @param table 表
	 * @param token token
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/04/10
	 */
	@ApiOperation(value = "数据导入")
	@PostMapping("importData")
	public R importData(String table, String token, @RequestParam(value = "cleanAll", defaultValue = "false") Boolean cleanAll) {

		Map<String, Class<? extends IBaseService>> map = new HashMap<>();


		map.put("app", IAppInfoService.class);
		map.put("device", IDeviceService.class);
		map.put("material", IMaterialService.class);
		map.put("materialType", IMaterialTypeService.class);
		map.put("printRecord", IPrintRecordService.class);
		map.put("region", IRegionService.class);
		map.put("storageType", IStorageTypeService.class);
		map.put("store", IStoreService.class);
		map.put("sys_role", ISysRoleService.class);
		map.put("sys_user", ISysUserService.class);
		map.put("sys_menu", ISysMenuService.class);
		map.put("wxuser", IWxUserService.class);

		Class<? extends IBaseService> aClass = map.get(table);
		IBaseService baseService = SpringContextHolder.getBean(aClass);

		if (cleanAll) {
			baseService.remove(new LambdaQueryWrapper());
		}

		dataSpider(table, token, baseService.getEntityClass(), (entity) -> {
			baseService.save(entity);
		});

		return R.ok();
	}


	public <T> void dataSpider(String table, String token, Class<T> clazz, Consumer<T> function) {
		Integer size = 10;
		int page = 1;

		while (true) {
			String url = "https://unicloud-api.dcloud.net.cn/unicloud/api/database/find?provider=aliyun&" +
					"spaceId=mp-7298d449-2ac5-4fe9-8667-349ef21edd11&appid=&collection=" + table + "&filter=&pageSize=" + size + "&page=" + page;
			String body = HttpUtil.createPost(url).addHeaders(MapBuilder.create(new HashMap<String, String>()).put("token", token).build())
					.execute().body();

			log.debug("开始抓取第" + page + "页的数据");
			if (StrUtil.isNotBlank(body)) {
				Integer ret = JSON.parseObject(body).getInteger("ret");
				if (null != ret && 0 == ret) {

					Integer last_page = JSON.parseObject(body).getJSONObject("data").getInteger("last_page");

					JSONArray jsonArray = JSON.parseObject(body).getJSONObject("data").getJSONArray("data");
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						T t = jsonObject.toJavaObject(clazz);
						function.accept(t);
					}
					if (last_page == page) {
						break;
					}
				}
			}
			page = page + 1;
		}

	}


}
