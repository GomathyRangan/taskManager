package com.cts.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.taskmanager.dao.ProjectDAO;
import com.cts.taskmanager.dao.TaskDAO;
import com.cts.taskmanager.dao.UserDAO;
import com.cts.taskmanager.vo.ParentTask;
import com.cts.taskmanager.vo.Project;
import com.cts.taskmanager.vo.ProjectDetails;
import com.cts.taskmanager.vo.Task;
import com.cts.taskmanager.vo.User;

public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private TaskDAO taskDAO;

	@Override
	public void addOrUpdateUser(User user) throws Exception {
		userDAO.addUser(user);	
	}

	@Override
	public void addOrUpdateProject(Project project) throws Exception {
		projectDAO.addOrUpdateProject(project);

	}

	public List<ProjectDetails> getProjectDetails() throws Exception {
		return projectDAO.getProjectDetails();
	}
	
	

	
	public void addParentTask(ParentTask parentTask) throws Exception {
		taskDAO.addParentTask(parentTask);

	}
	


	@Override
	public void addTask(Task task) throws Exception {
		taskDAO.addTask(task);

	}

	@Override
	public List<User> getAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getAllUserDetails();
	}

	@Override
	public void deleteUser(int userId) throws Exception {
		userDAO.deleteUser(userId);

	}




	@Override
	public void deleteProject(int id) throws Exception {
		projectDAO.deleteProject(id);

	}

	

	@Override
	public User getUserById(int id) throws Exception {
		return userDAO.getUserById(id);
	}

	@Override
	public Project getProjectById(int id) throws Exception {
		return projectDAO.getProjectById(id);
	}

	@Override
	public List<Project> getAllProjects() throws Exception {
		return projectDAO.getAllProjects();
	}

	@Override
	public void updateTaskStatus(int id, String status) throws Exception {
		taskDAO.updateTaskStatus(id, status);
		
	}

	@Override
	public List<Task> getTaskByProjId(int projectId) throws Exception {		
		return taskDAO.getTaskByProjectId(projectId);
	}

	@Override
	public void updateTask(Task task) throws Exception {
		taskDAO.updateTask(task);
		
	}

	@Override
	public Task getTaskById(int id) throws Exception {
		
		return taskDAO.getTaskById(id);
	}

	@Override
	public List<ParentTask> getAllParentTask() throws Exception {
		// TODO Auto-generated method stub
		return taskDAO.getAllParentTask();
	}

	@Override
	public List<Task> getAllTask() throws Exception {		
		return taskDAO.getAllTask();
	}


	

}
