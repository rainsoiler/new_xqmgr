package com.github.rainsoil.fastapi.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序
 *
 * @author luyanan
 * @since 2023/04/13
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class MiniProperties {
	/**
	 * 设置微信小程序的appid
	 */
	private String appid;

	/**
	 * 设置微信小程序的Secret
	 */
	private String secret;
}
