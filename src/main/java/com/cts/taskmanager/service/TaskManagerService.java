package com.cts.taskmanager.service;



import java.util.List;

import com.cts.taskmanager.exception.TaskManagerException;
import com.cts.taskmanager.vo.ParentTask;
import com.cts.taskmanager.vo.Project;
import com.cts.taskmanager.vo.ProjectDetails;
import com.cts.taskmanager.vo.Task;
import com.cts.taskmanager.vo.User;


public interface TaskManagerService {

	 void addOrUpdateUser(User user)  throws TaskManagerException;
	 List<User> getAllUsers() throws TaskManagerException;
	 void deleteUser(int userId) throws TaskManagerException;
	// void updateUser(User user) throws MyException;
	 User getUserById(int id) throws TaskManagerException;
	 
	 void addOrUpdateProject(Project project) throws TaskManagerException;	 
	 void deleteProject(int id) throws TaskManagerException;
	 Project getProjectById(int id) throws TaskManagerException;	 
	 List<ProjectDetails> getProjectDetails() throws TaskManagerException;
	 List<Project> getAllProjects() throws TaskManagerException;
	 
	// void addParentTask(ParentTask parentTask) throws MyException;
	 void addTask(Task task) throws TaskManagerException;
	 void updateTaskStatus(int id,String status) throws TaskManagerException;
	 List<Task> getTaskByProjId(int projectId) throws TaskManagerException;
	 List<Task> getAllTask() throws TaskManagerException;
	 Task getTaskById(int id) throws TaskManagerException;	 
	 void updateTask(Task task) throws TaskManagerException;
	 
	 
	 
	 List<ParentTask> getAllParentTask() throws TaskManagerException;
	
	

}
