package com.pegasus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SsmConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/home").setViewName("/home");
		registry.addViewController("/success").setViewName("temp/success");
	}

	@Override
	//org.springframework.boot.autoconfigure.web.ResourceProperties类下的：CLASSPATH_RESOURCE_LOCATIONS
	//2静态资源配置，默认为：/src/main/resources/下的:/META-INF/resources/, /resoureces/, /static/, /public/
	//
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//String[] resourceHandlers = {"/**"};
		//String[] resourceLocations = {"classpath:/","file:D:/personnal/pic/"};
		registry.addResourceHandler(new String[] {"/**"})
		.addResourceLocations(new String[] {"classpath:/","classpath:/static/","file:D:/personnal/pic/"});
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
}
