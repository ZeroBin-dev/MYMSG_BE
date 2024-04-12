package com.myapi.mymsgapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MymsgApiApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

  public static void main(String[] args) {
    try {
      SpringApplication.run(MymsgApiApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected WebApplicationContext run(SpringApplication application) {

    return super.run(application);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(MymsgApiApplication.class);
  }

}
