package com.hibernatedirty.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@Configuration
public class CORSFilter {

	@Bean
	public WebMvcConfigurer corsMappingConfigurer() {
	   return new WebMvcConfigurer() {
	       @Override
	       public void addCorsMappings(CorsRegistry registry) {
	           registry.addMapping("/**")
	             .allowedOrigins("*")
	             .allowedMethods("GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD")
	             .allowCredentials(true)
	             .maxAge(3600)
	             .allowedHeaders("Authorization", "Requestor-Type")
	             .exposedHeaders("X-Get-Header");
	       }
	   };

}
}

