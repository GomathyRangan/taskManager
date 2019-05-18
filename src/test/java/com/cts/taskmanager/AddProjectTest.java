package com.cts.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.taskmanager.controller.TaskManagerController;
import com.cts.taskmanager.vo.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class,Parameterized.class)
@ContextConfiguration(classes= {TaskManagerController.class})
@WebAppConfiguration
@EnableWebMvc
@ComponentScan({ "com.cts.taskmanager" })
@SpringBootTest

public class AddProjectTest {
	private MockMvc mockMVC;

	@Autowired
	private WebApplicationContext webAppCxt;

	@Before
	public void setUp() {
		mockMVC = MockMvcBuilders.webAppContextSetup(this.webAppCxt).build();
	}

	@Test
	public void testAddProjects() throws Exception {

		Project project = new Project();
		project.setProjectName("test");
		project.setStartDate(new Date());
		project.setEndDate(new Date());

		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(project);


		mockMVC.perform(post("/addProject")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)				
				.content(jsonTxt))
		.andExpect(status().isOk());
		
		
	}
	

}
