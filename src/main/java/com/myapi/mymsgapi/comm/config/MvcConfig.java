package com.myapi.mymsgapi.comm.config;

import com.myapi.mymsgapi.comm.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

  private final LoginCheckInterceptor loginCheckInterceptor;

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**", "/resources/**")
      .addResourceLocations("classpath:/templates/", "classpath:/static/")
      .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));

    registry.addResourceHandler("/img/**")
      .addResourceLocations("file:///Y:/zeroBin/");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginCheckInterceptor)
      .excludePathPatterns("/css/**/")
      .excludePathPatterns("/js/**/")
      .excludePathPatterns("/img/**/")
      .excludePathPatterns("/favicon.ico")
    ;
  }
}
