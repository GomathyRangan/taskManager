package com.cts.taskmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.taskmanager.exception.TaskManagerException;
import com.cts.taskmanager.service.TaskManagerService;
import com.cts.taskmanager.vo.ParentTask;
import com.cts.taskmanager.vo.Project;
import com.cts.taskmanager.vo.ProjectDetails;
import com.cts.taskmanager.vo.Task;
import com.cts.taskmanager.vo.User; 

@RestController
public class TaskManagerController {

	private Logger log = LogManager.getLogger();

	@Autowired
	public TaskManagerService  taskManagerService;

	@PostMapping("/addOrUpdateUser")
	public ResponseEntity<User> addOrUpdateUser(@RequestBody User user)
	{
		ResponseEntity<User> responseEntity = null;
		log.info(" <<< Add or update User controller >>>" , user);
		try {
			taskManagerService.addOrUpdateUser(user);
			responseEntity = new ResponseEntity<User> (user,HttpStatus.OK);
			log.info(" >>> User information is sucessfully inserted <<<");
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<User> (user,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers () {
		log.info(" >>> Get all user details controller >>>");
		List<User> users = new ArrayList<>();
		ResponseEntity<List<User>> responseEntity = null;
		try {			
			users = taskManagerService.getAllUsers();
			responseEntity = new ResponseEntity<List<User>> (users,HttpStatus.OK);
			log.info("<<< Fetched users details from DB. >>> ");
		}catch (TaskManagerException e) {			
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<List<User>> (users,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User>  getUserById(@PathVariable("id") int id){
		ResponseEntity<User> responseEntity = null;
		User user = null;
		try {
			log.info(" >>> Get user details using user id >>>");
			user = taskManagerService.getUserById(Integer.valueOf(id));
			responseEntity = new ResponseEntity<User> (user,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<User> (user,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String>  deleteUser(@PathVariable("id") int id) {
		ResponseEntity<String> responseEntity = null;
		String msg = "User Got deleted";
		try {
			log.info(" >>> Delete user details using user id >>>");
			taskManagerService.deleteUser(Integer.valueOf(id));		
			responseEntity = new ResponseEntity<String> (msg,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			msg = "unable to delete the user";
			responseEntity = new ResponseEntity<String> (msg,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}

	@PostMapping("/addProject")
	public ResponseEntity<Project> addOrUpdateProject(@RequestBody Project project)
	{	 
		ResponseEntity<Project> responseEntity = null;
		log.info(" <<< Add or update project >>>");
		try {

			taskManagerService.addOrUpdateProject(project);
			responseEntity = new ResponseEntity<Project> (project,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<Project> (project,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@PutMapping("/updateProject")
	public ResponseEntity<Project> updateProject(@RequestBody Project project)
	{	 
		ResponseEntity<Project> responseEntity = null;
		log.info("<<< Update Project >>> " );
		try {
			taskManagerService.addOrUpdateProject(project);
			responseEntity = new ResponseEntity<Project> (project,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<Project> (project,HttpStatus.BAD_REQUEST);		}
		return responseEntity;
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteProject/{id}")
	public ResponseEntity<String>  deleteProject(@PathVariable("id") int id){
		ResponseEntity<String> responseEntity = null;
		String msg = "project Got deleted";
		try {
			log.info("<<< Delete Project >>> " );
			taskManagerService.deleteUser(Integer.valueOf(id));			
			responseEntity = new ResponseEntity<String> (msg,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			msg = "Project Detail could not deleted";
			responseEntity = new ResponseEntity<String> (msg,HttpStatus.BAD_REQUEST);	
		}
		return responseEntity;


	}


	@GetMapping("/getProjectDetails")
	public ResponseEntity<List<ProjectDetails>> getProjectDetails() {
		ResponseEntity<List<ProjectDetails>> responseEntity = null;
		List<ProjectDetails> projDetails = new ArrayList<>();
		try {		
			log.info("<<< Get Project Details >>> " );
			projDetails = taskManagerService.getProjectDetails();
			responseEntity = new ResponseEntity<List<ProjectDetails>> (projDetails,HttpStatus.OK);
		}catch (TaskManagerException e) {
			log.error(e.getMessage(),e);			
			responseEntity = new ResponseEntity<List<ProjectDetails>> (projDetails,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	@GetMapping("/getAllProjects")
	public ResponseEntity<List<Project>> getAllProject() {

		ResponseEntity<List<Project>> responseEntity = null;		
		List<Project> projDetails = new ArrayList<>();
		try {		
			log.info("<<< Get all Project Details >>> " );
			projDetails = taskManagerService.getAllProjects();
			responseEntity = new ResponseEntity<List<Project>> (projDetails,HttpStatus.OK);
		}catch (TaskManagerException e) {
			log.error(e.getMessage(),e);			
			responseEntity = new ResponseEntity<List<Project>> (projDetails,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	@GetMapping("/getProjectById/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {

		ResponseEntity<Project> responseEntity = null;		
		Project projDetails = null;
		log.info("<<< Get Project Details using project id >>> " );
		try {			
			projDetails = taskManagerService.getProjectById(id);
			responseEntity = new ResponseEntity<Project> (projDetails,HttpStatus.OK);
		}catch (TaskManagerException e) {
			log.error(e.getMessage(),e);			
			responseEntity = new ResponseEntity<Project> (projDetails,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	@PostMapping("/addTask")
	public ResponseEntity<Task> addTask(@RequestBody Task task)
	{	 

		ResponseEntity<Task> responseEntity = null;
		log.info("<<< add task details >>> " );

		try {
			taskManagerService.addTask(task);
			responseEntity = new ResponseEntity<Task> (task,HttpStatus.OK);			
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);			
			responseEntity = new ResponseEntity<Task> (task,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	@GetMapping("/getAllParentTask")
	public ResponseEntity<List<ParentTask>> getAllParentTask() {

		ResponseEntity<List<ParentTask>> responseEntity = null;		
		List<ParentTask> parentTasks = new ArrayList<>();
		try {		
			log.info("<<< Get all Project Details >>> " );
			parentTasks = taskManagerService.getAllParentTask();
			responseEntity = new ResponseEntity<List<ParentTask>> (parentTasks,HttpStatus.OK);
		}catch (TaskManagerException e) {
			log.error(e.getMessage(),e);			
			responseEntity = new ResponseEntity<List<ParentTask>> (parentTasks,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	@PutMapping("/updateTaskStatus/{id}")
	public ResponseEntity<String> updateTaskStatus(@PathVariable("id") int id)
	{	 
		ResponseEntity<String> responseEntity = null;
		log.info("<<< Update Project >>> " );
		try {
			taskManagerService.updateTaskStatus(id, "Completed");
			responseEntity = new ResponseEntity<String> ("Status Updated",HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<String> ("Status not updated",HttpStatus.BAD_REQUEST);		}
		return responseEntity;
	}


	@GetMapping("/getTaskByProjectId/{id}")
	public ResponseEntity<List<Task>> getTaskByProjectId(@PathVariable("id") int id)
	{	 
		ResponseEntity<List<Task>> responseEntity = null;
		List<Task> tasks = new ArrayList<>();
		log.info("<<< Update Project >>> " );
		try {
			tasks = taskManagerService.getTaskByProjId(id);
			responseEntity = new ResponseEntity<List<Task>> (tasks,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<List<Task>> (tasks,HttpStatus.BAD_REQUEST);		}
		return responseEntity;
	}


	@GetMapping("/getAllTask")
	public ResponseEntity<List<Task>> getAllTask()
	{	 
		ResponseEntity<List<Task>> responseEntity = null;
		List<Task> tasks = new ArrayList<>();
		log.info("<<< Update Project >>> " );
		try {
			tasks = taskManagerService.getAllTask();
			responseEntity = new ResponseEntity<List<Task>> (tasks,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<List<Task>> (tasks,HttpStatus.BAD_REQUEST);		}
		return responseEntity;
	}


	@GetMapping("/getTaskById/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") int id)
	{	 
		ResponseEntity<Task> responseEntity = null;
		Task task = null;;
		log.info("<<< Update Project >>> " );
		try {
			task = taskManagerService.getTaskById(id);
			responseEntity = new ResponseEntity<Task> (task,HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<Task> (task,HttpStatus.BAD_REQUEST);		}
		return responseEntity;
	}



	@PutMapping("/updateTask")
	public ResponseEntity<String> updateTask(@RequestBody Task task)
	{	 
		ResponseEntity<String> responseEntity = null;

		log.info("<<< Update task >>> " );
		try {
			taskManagerService.updateTask(task);
			responseEntity = new ResponseEntity<String> ("Task details updated",HttpStatus.OK);
		} catch (TaskManagerException e) {
			log.error(e.getMessage(),e);
			responseEntity = new ResponseEntity<String> ("Task Details not updated",HttpStatus.BAD_REQUEST);		
		}
		return responseEntity;
	}




}
