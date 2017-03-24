package com.computerDatabase.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class MainInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
      return new Class[] { AppConfiguration.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
      return null;
  }

  @Override
  protected String[] getServletMappings() {
      return new String[] { "/" };
  } 
}