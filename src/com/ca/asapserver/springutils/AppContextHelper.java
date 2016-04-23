package com.ca.asapserver.springutils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * AppContextHelper
 * 
 * Singleton Spring Bean. Implements ApplicationcontextAware and receives a context reference during initialization.
 * Used by any non spring beans class for getting context and access other beans calling the static method getApplicationContext().
 * 
 * @author Rodrigo Carvalho.
 *
 */
@Scope("singleton")
@Component
public class AppContextHelper implements ApplicationContextAware {
 
   private static ApplicationContext context;
 
   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      initializeApplicationContext(applicationContext);
   }
 
   private static void initializeApplicationContext(ApplicationContext applicationContext) {
      context = applicationContext;
   }
 
   public static ApplicationContext getApplicationContext() {
      return context;
   }
 
}
