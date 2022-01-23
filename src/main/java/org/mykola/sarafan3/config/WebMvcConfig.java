package org.mykola.sarafan3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerCustomizer(){
		return container -> {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
		};
	}
	
	private String picturePath = "/D:/docs/pics/sarafan";
	
//	@Value("${upload.path}")
//	private String uploadPath;
//	@Value("${upload.path2}")
//	private String uploadPath2;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:///"+picturePath+"/");
		
	}
}
