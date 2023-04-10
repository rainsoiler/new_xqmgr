package com.github.rainsoil.fastapi.web.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 打印记录vo类
 *
 * @author luyanan
 * @since 2023/04/10
 */
public class PrintRecordVo {


	/**
	 * 小程序统计
	 *
	 * @author luyanan
	 * @since 2023/04/10
	 */
	@Accessors(chain = true)
	@Data
	public static class MiniStatistics {

		/**
		 * 今日打印量
		 *
		 * @since 2023/04/10
		 */
		@ApiModelProperty(value = "今日打印量")
		private Long printCount;


		/**
		 * 累计打印量
		 *
		 * @since 2023/04/10
		 */
		@ApiModelProperty(value = "累计打印量")
		private Long totalPrintCount;

		/**
		 * 今日未处理量
		 *
		 * @since 2023/04/10
		 */
		@ApiModelProperty(value = "今日未处理量")
		private Long untreatedCount;
	}
}
