package com.relyy.shop.backend.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2022/1/6
 */
@Configuration
public class MybatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(){
		MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
		plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		return plusInterceptor;
	}
}
