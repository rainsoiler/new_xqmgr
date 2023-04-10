package com.github.rainsoil.fastapi.web.security;

import cn.hutool.core.util.StrUtil;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户端
 *
 * @author luyanan
 * @since 2023/04/10
 */
@UtilityClass
public class ClientUtils {


	/**
	 * 获取来源
	 *
	 * @return com.github.rainsoil.fastapi.web.security.ClientUtils.Source
	 * @since 2023/04/10
	 */
	public Source getSource() {
		HttpServletRequest request =
				SpringContextHolder.getRequest();
		String source = request.getHeader("source");
		return StrUtil.isBlank(source) ? Source.WEB : Source.valueOf(source);
	}

	public static enum Source {

		/**
		 * WEB
		 *
		 * @author luyanan
		 * @since 2023/04/10
		 */
		WEB,
		/**
		 * 小程序
		 *
		 * @author luyanan
		 * @since 2023/04/10
		 */
		MINI
	}
}
