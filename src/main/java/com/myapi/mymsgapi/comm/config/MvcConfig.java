package com.myapi.mymsgapi.comm.config;

import com.myapi.mymsgapi.comm.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;

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
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginCheckInterceptor)
      .excludePathPatterns("/css/**/")
      .excludePathPatterns("/js/**/")
      .excludePathPatterns("/images/**/")
      .excludePathPatterns("/profile/**/")
      .excludePathPatterns("/favicon.ico")
    ;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {

  }
}
