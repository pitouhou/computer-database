package com.computerDatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.computerDatabase.formatter.CompanyDTOFormatter;


@Configuration
@EnableWebMvc
@ComponentScan({ "com.computerDatabase" })
@EnableTransactionManagement

public class AppConfiguration extends WebMvcConfigurerAdapter {
  
  @Bean
  public ViewResolver viewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setViewClass(JstlView.class);
      viewResolver.setPrefix("/WEB-INF/views/");
      viewResolver.setSuffix(".jsp");

      return viewResolver;
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
  
  @Bean 
  public FormattingConversionService formattingConversionService () {
      FormattingConversionService service = new FormattingConversionService();
      service.addFormatter(new CompanyDTOFormatter());
      return service;
  }
}
