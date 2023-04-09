package com.github.rainsoil.fastapi.web;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 *
 * @author luyanan
 * @since 2023/4/4
 **/
@EnableEncryptableProperties
@SpringBootApplication
public class AppApplication {


	/**
	 * 著启动类
	 *
	 * @param args 参数
	 * @since 2023/4/4
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
