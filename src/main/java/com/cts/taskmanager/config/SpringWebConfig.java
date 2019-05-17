package com.cts.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.cts.taskmanager.dao.ProjectDAO;
import com.cts.taskmanager.dao.TaskDAO;
import com.cts.taskmanager.dao.UserDAO;
import com.cts.taskmanager.service.TaskManagerService;
import com.cts.taskmanager.service.TaskManagerServiceImpl;


 
@EnableWebMvc //mvc:annotation-driven
@Configuration
@ComponentScan({ "com.cts.taskmanager" })
public class SpringWebConfig implements WebMvcConfigurer {
 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	@Bean(name="taskManagerService")
	public TaskManagerService getTaskManagerService() {
		return new TaskManagerServiceImpl();
	}
	
	@Bean(name="userDAO")
	public UserDAO getUserDAO() {
		return new UserDAO();
	}
	
	@Bean(name="taskDAO")
	public TaskDAO getTaskDAO() {
		return new TaskDAO();
	}
	
	@Bean(name="projectDAO")
	public ProjectDAO getProjectDAO() {
		return new ProjectDAO();
	}
	
	

	
 
}
