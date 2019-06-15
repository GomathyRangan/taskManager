package com.cts.taskmanager;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.cts.taskmanager.util.TaskManagerUtil;
import com.cts.taskmanager.vo.ParentTask;
import com.cts.taskmanager.vo.Project;
import com.cts.taskmanager.vo.Task;
import com.cts.taskmanager.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TaskManagerController.class})
@WebAppConfiguration
@EnableWebMvc
@ComponentScan({ "com.cts.taskmanager" })
@SpringBootTest
public class TaskManagerApplicationTests {

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


	@Test
	public void testAddUser() throws Exception {

		User user = new User();

		user.setEmployeeId(1);
		user.setFirstName("Gomathy");
		user.setLastName("R");
		user.setProjectId(5);
		user.setTaskId(5);

		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(user);
		System.out.println(jsonTxt);

		mockMVC.perform(post("/addOrUpdateUser")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))
		.andExpect(status().isOk());
	}




	@Test
	public void testGetAllUsers() throws Exception {


		mockMVC.perform(get("/getAllUsers")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	@Test
	public void testGetUserById() throws Exception {

		System.out.println(" user by id :::: ");
		mockMVC.perform(get("/getUserById/3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}


	@Test
	public void testDeleteUser() throws Exception {

		mockMVC.perform(delete("/deleteUser/3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}



	@Test
	public void testAddProject() throws Exception {

		Project project = new Project();

		project.setProjectName("test");
		project.setPriority(1);
		project.setStartDate(TaskManagerUtil.convertToDate("05/13/2019"));
		project.setEndDate(TaskManagerUtil.convertToDate("05/13/2019"));
		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(project);
		System.out.println(jsonTxt);

		mockMVC.perform(post("/addProject")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testUpdateProject() throws Exception {

		Project project = new Project();

		project.setProjectName("test");
		project.setPriority(1);
		project.setProjectId(3);
		project.setStartDate(TaskManagerUtil.convertToDate("05/13/2019"));
		project.setEndDate(TaskManagerUtil.convertToDate("05/13/2019"));
		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(project);
		

		mockMVC.perform(put("/updateProject")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testDeleteProject() throws Exception {

		mockMVC.perform(delete("/deleteProject/100")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testGetProjectDetails() throws Exception {

		System.out.println(" user by id :::: ");
		mockMVC.perform(get("/getProjectDetails")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	 
	
	@Test
	public void testGetAllProject() throws Exception {

		
		mockMVC.perform(get("/getAllProjects")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testGetProjectById() throws Exception {

		System.out.println(" user by id :::: ");
		mockMVC.perform(get("/getProjectById/3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testaddTask() throws Exception {
		
		Task task = new Task();
		task.setTask("test");
		task.setParentId(1);
		task.setStartDate(TaskManagerUtil.convertToDate("05/18/2019"));
		task.setEndDate(TaskManagerUtil.convertToDate("05/18/2019"));
		task.setPriority(1);
		task.setProjectId(3);

		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(task);

		System.out.println(" user by id :::: ");
		mockMVC.perform(post("/addTask")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))	
		
		.andExpect(status().isOk());

	}
	
	@Test
	public void testaddParentTask() throws Exception {
		
		ParentTask task = new ParentTask();
		task.setParentTask("test");
		

		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(task);

		System.out.println(" user by id :::: ");
		mockMVC.perform(post("/addParentTask")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))	
		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testAddTaskForException() throws Exception {
		
		Task task = new Task();
		task.setTask("test");		
		task.setStartDate(TaskManagerUtil.convertToDate("05/18/2019"));
		task.setEndDate(TaskManagerUtil.convertToDate("05/18/2019"));
		task.setPriority(1);
		

		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(task);

		System.out.println(" user by id :::: ");
		mockMVC.perform(post("/addTask")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))	
		
		.andExpect(status().isBadRequest());

	}
	
	
	@Test
	public void testGetAllParentTask() throws Exception {

		System.out.println(" user by id :::: ");
		mockMVC.perform(get("/getAllParentTask")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testUpdateTaskStatus() throws Exception {

		System.out.println(" user by id :::: ");
		mockMVC.perform(put("/updateTaskStatus/5")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testGetTaskByProjectId() throws Exception {

		System.out.println(" user by id :::: ");
		mockMVC.perform(get("/getTaskByProjectId/5")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}

	
	@Test
	public void testGetAllTask() throws Exception {

		
		mockMVC.perform(get("/getAllTask")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	@Test
	public void testGetTaskById() throws Exception {

		
		mockMVC.perform(get("/getTaskById/3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))		
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void testUpdateTask() throws Exception {
		
		Task task = new Task();
		task.setTask("test");
		task.setTaskId(5);
		task.setParentId(1);
		task.setStartDate(TaskManagerUtil.convertToDate("05/18/2019"));
		task.setEndDate(TaskManagerUtil.convertToDate("05/18/2019"));
		task.setPriority(1);
		task.setProjectId(3);

		ObjectMapper obj = new ObjectMapper();

		String jsonTxt = obj.writeValueAsString(task);

		
		mockMVC.perform(put("/updateTask")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTxt))			
		.andExpect(status().isOk());

		
	}
	
	@Test
	public void testDateUtil() {
		
		Date date =   TaskManagerUtil.convertToDate("ewrwer");
		assertNull("Invalid Date", date);
	}




	@Test
	public void contextLoads() {
	}

}
