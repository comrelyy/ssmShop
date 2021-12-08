package com.relyy.shop.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Created by cairuirui
 * @Date 2021/12/3
 */
@Slf4j
@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		//log.info("开始启动");
		SpringApplication.run(BackendApplication.class,args);
	}
}
