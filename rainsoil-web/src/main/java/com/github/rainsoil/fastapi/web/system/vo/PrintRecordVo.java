package com.github.rainsoil.fastapi.web.system.vo;

import com.github.rainsoil.fastapi.web.system.entity.PrintRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

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


	/**
	 * 打印报表
	 *
	 * @author luyanan
	 * @since 2023/04/12
	 */
	@Data
	public static class PrintRecordVoReport {

		/**
		 * 时间
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "时间")
		private String dailyDate;


		/**
		 * 今日打印量
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "今日打印量")
		private Long printCount;

		/**
		 * 今日未处理量
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "今日未处理两")
		private Long untreatedCount;
	}


	/**
	 * 日报详情
	 *
	 * @author luyanan
	 * @since 2023/04/12
	 */
	@Data
	@Accessors(chain = true)
	public static class PrintRecordInfoVo {

		/**
		 * 打印记录排名
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "打印记录排名")
		private List<PrintRecordInfoGroupmaterialIdVo> treatedCount;


		/**
		 * 未处理排行
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "未处理排行")
		private List<PrintRecordInfoGroupmaterialIdVo> untreatedCount;


		/**
		 * 打印记录详情
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "打印记录详情")
		private List<PrintRecord> treatedList;

		/**
		 * 未处理详情
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "未处理详情")
		private List<PrintRecord> unTreatedList;
	}

	/**
	 * 打印机日报根据物料分组
	 *
	 * @author luyanan
	 * @since 2023/04/12
	 */
	@Data
	public static class PrintRecordInfoGroupmaterialIdVo {

		/**
		 * 物料名称
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "物料名称")
		private String name;
		/**
		 * 打印数量
		 *
		 * @since 2023/04/12
		 */
		@ApiModelProperty(value = "打印数量")
		private Long num;
	}
}
