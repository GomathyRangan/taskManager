package com.cts.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.taskmanager.dao.ProjectDAO;
import com.cts.taskmanager.dao.TaskDAO;
import com.cts.taskmanager.dao.UserDAO;
import com.cts.taskmanager.exception.TaskManagerException;
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
	public void addOrUpdateUser(User user) throws TaskManagerException {
		userDAO.addUser(user);	
	}

	@Override
	public void addOrUpdateProject(Project project) throws TaskManagerException {
		projectDAO.addOrUpdateProject(project);

	}

	public List<ProjectDetails> getProjectDetails() throws TaskManagerException {
		return projectDAO.getProjectDetails();
	}
	
	public List<Project> getAllProject() throws TaskManagerException {
		return projectDAO.getAllProjects();
	}

	
	public void addParentTask(ParentTask parentTask) throws TaskManagerException {
		taskDAO.addParentTask(parentTask);

	}
	
	public List<ParentTask> getAllParentTask(ParentTask parentTask) throws TaskManagerException {
		return taskDAO.getAllParentTask();

	}

	@Override
	public void addTask(Task task) throws TaskManagerException {
		taskDAO.addTask(task);

	}

	@Override
	public List<User> getAllUsers() throws TaskManagerException {
		// TODO Auto-generated method stub
		return userDAO.getAllUserDetails();
	}

	@Override
	public void deleteUser(int userId) throws TaskManagerException {
		userDAO.deleteUser(userId);

	}




	@Override
	public void deleteProject(int id) throws TaskManagerException {
		projectDAO.deleteProject(id);

	}

	

	@Override
	public User getUserById(int id) throws TaskManagerException {
		return userDAO.getUserById(id);
	}

	@Override
	public Project getProjectById(int id) throws TaskManagerException {
		return projectDAO.getProjectById(id);
	}

	@Override
	public List<Project> getAllProjects() throws TaskManagerException {
		return projectDAO.getAllProjects();
	}

	@Override
	public void updateTaskStatus(int id, String status) throws TaskManagerException {
		taskDAO.updateTaskStatus(id, status);
		
	}

	@Override
	public List<Task> getTaskByProjId(int projectId) throws TaskManagerException {		
		return taskDAO.getTaskByProjectId(projectId);
	}

	@Override
	public void updateTask(Task task) throws TaskManagerException {
		taskDAO.updateTask(task);
		
	}

	@Override
	public Task getTaskById(int id) throws TaskManagerException {
		
		return taskDAO.getTaskById(id);
	}

	@Override
	public List<ParentTask> getAllParentTask() throws TaskManagerException {
		// TODO Auto-generated method stub
		return taskDAO.getAllParentTask();
	}

	@Override
	public List<Task> getAllTask() throws TaskManagerException {		
		return taskDAO.getAllTask();
	}


	

}
