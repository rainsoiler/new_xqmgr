package com.github.rainsoil.fastapi.core.mybatisplus;

import cn.hutool.db.ds.simple.SimpleDataSource;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;
import com.github.rainsoil.fastapi.core.utils.YmlUtils;
import com.mysql.cj.jdbc.DatabaseMetaDataUsingInfoSchema;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Collections;
import java.util.Map;

/**
 * 代码生成
 *
 * @author luyanan
 * @since 2023/04/08
 */
public class CodeGenerator {
	/**
	 * 代码生成启动类
	 *
	 * @param args 参数
	 * @since 2022/10/12
	 */
	public static void main(String[] args) {

		// 读取yml中的配置


		String configPath = System.getProperty("user.dir") + "/rainsoil-web/src/main/resources/application-dev.yml";
		Map<String, String> configs = YmlUtils.getYmlByFileName(configPath);

		String url = configs.get("spring.datasource.url");
		String username = configs.get("spring.datasource.username");
		String password = configs.get("spring.datasource.password");


		FastAutoGenerator.create(url,
						username,
						password)
				.strategyConfig(builder -> {
					builder
							.entityBuilder()
//                            .disableSerialVersionUID()
							.enableChainModel()
							.enableLombok()
							.enableTableFieldAnnotation()
							.enableTableFieldAnnotation()
//                            .enableRemoveIsPrefix()
							.enableTableFieldAnnotation()
//                            .enableActiveRecord()
							.versionColumnName("version")
							.versionPropertyName("version")
							.logicDeleteColumnName("del_flag")
							.logicDeletePropertyName("delFlag")
							.fileOverride()
//                            .naming(NamingStrategy.no_change)
							.columnNaming(NamingStrategy.underline_to_camel)
							.addTableFills(new Column("create_user", FieldFill.INSERT))
							.addTableFills(new Column("update_user", FieldFill.INSERT_UPDATE))
							.addTableFills(new Column("create_time", FieldFill.INSERT))
							.addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
							.idType(IdType.ASSIGN_ID)
							.controllerBuilder().enableRestStyle()
							.mapperBuilder().superClass(BaseBaseMapper.class).enableMapperAnnotation().mapperAnnotation(Mapper.class)
							.serviceBuilder().superServiceClass(IBaseService.class)
							.serviceBuilder().superServiceImplClass(BaseServiceImpl.class)
							.build();
				})
				.globalConfig(builder -> {
					builder.author("luyanan") // 设置作者
							.enableSwagger() // 开启 swagger 模式
							.fileOverride() // 覆盖已生成文件

							.outputDir("E://com"); // 指定输出目录
				})

				.packageConfig(builder -> {
					builder.parent("com.github.rainsoil.fastapi.web") // 设置父包名
//							.moduleName("system") // 设置父包模块名
							.moduleName("system") // 设置父包模块名
							.pathInfo(Collections.singletonMap(OutputFile.xml, "D://generator")); // 设置mapperXml生成路径
				})
				.strategyConfig(builder -> {


//					builder.likeTable(new LikeTable("sys_combo_quan"))
					builder.addInclude(".*") // 设置需要生成的表名
							.addTablePrefix("syssss_"); // 设置过滤表前缀
				})
				.templateConfig(builder -> {
							builder
									.disable(TemplateType.ENTITY)
									.entity("/templates/entity.java")
									.service("/templates/service.java")
									.serviceImpl("/templates/serviceImpl.java")
									.mapper("/templates/mapper.java")
//                            .mapperXml("/templates/mapper.xml")
//									.controller("/templates/controller.java")
									.controller("/templates/controller2.java")
									.build();
						}

				)
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();

	}


}
