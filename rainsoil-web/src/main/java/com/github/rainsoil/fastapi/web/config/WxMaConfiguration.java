package com.github.rainsoil.fastapi.web.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 小程序的配置
 *
 * @author luyanan
 * @since 2023/04/13
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MiniProperties.class)
public class WxMaConfiguration {
	private final MiniProperties properties;

	@Autowired
	public WxMaConfiguration(MiniProperties properties) {
		this.properties = properties;
	}

	@Bean
	public WxMaService wxMaService() {
		WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
		config.setAppid(properties.getAppid());
		config.setSecret(properties.getSecret());
		WxMaService maService = new WxMaServiceImpl();
		maService.setWxMaConfig(config);
		return maService;
	}


}