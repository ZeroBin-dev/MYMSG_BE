package com.myapi.mymsgapi.comm.config;

import com.myapi.mymsgapi.comm.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final LoginCheckInterceptor loginCheckInterceptor;

  @Value("${spring.file.profile-path}")
  private String profilePath;

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**", "/resources/**")
      .addResourceLocations("classpath:/templates/", "classpath:/static/");

    registry.addResourceHandler("/profile/**")
      .addResourceLocations(profilePath);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginCheckInterceptor)
      .excludePathPatterns("/css/**/")
      .excludePathPatterns("/js/**/")
      .excludePathPatterns("/images/**/")
      .excludePathPatterns("/profile/**/")
      .excludePathPatterns("/favicon.ico")
      .excludePathPatterns("/dudqls3kr/**/")
    ;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {

  }
}
