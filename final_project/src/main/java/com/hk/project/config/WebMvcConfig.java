package com.hk.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 구현된 interceptor 객체를 등록한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).order(1) // 우선순위 설정
				.addPathPatterns("/**") // 전체 요청에 대해 적용
				.excludePathPatterns("/error",  "/feign/**", "/board/**", "/", "/user/**", "/css/**", "/js/**");

//		registry.addInterceptor(new LoginInterceptor())
//		.order(2)
//		.addPathPatterns("/**")   //전체 요청에 대해 적용
//		.excludePathPatterns("/board","/","/user/**","/css/**","/js/**");
		// 추가할 인터셉터를 정의
//		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/banking/*");
//						.excludePathPatterns("/banking/test","","")
	}

}
