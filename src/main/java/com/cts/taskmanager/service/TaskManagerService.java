package com.cts.taskmanager.service;



import java.util.List;


import com.cts.taskmanager.vo.ParentTask;
import com.cts.taskmanager.vo.Project;
import com.cts.taskmanager.vo.ProjectDetails;
import com.cts.taskmanager.vo.Task;
import com.cts.taskmanager.vo.User;


public interface TaskManagerService {

	 void addOrUpdateUser(User user)  throws Exception;
	 List<User> getAllUsers() throws Exception;
	 void deleteUser(int userId) throws Exception;
	// void updateUser(User user) throws MyException;
	 User getUserById(int id) throws Exception;
	 
	 void addOrUpdateProject(Project project) throws Exception;	 
	 void deleteProject(int id) throws Exception;
	 Project getProjectById(int id) throws Exception;	 
	 List<ProjectDetails> getProjectDetails() throws Exception;
	 List<Project> getAllProjects() throws Exception;
	 
	 void addParentTask(ParentTask parentTask) throws Exception;
	 void addTask(Task task) throws Exception;
	 void updateTaskStatus(int id,String status) throws Exception;
	 List<Task> getTaskByProjId(int projectId) throws Exception;
	 List<Task> getAllTask() throws Exception;
	 Task getTaskById(int id) throws Exception;	 
	 void updateTask(Task task) throws Exception;
	 
	 
	 
	 List<ParentTask> getAllParentTask() throws Exception;
	
	

}
