package com.cts.taskmanager.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table (name = "fsd_assg.task")
@JsonInclude
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="task_id")
	private int taskId;
	
	@Column(name="parent_id", nullable=true)
	private int parentId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="task")
	private String task;
	
	@Column(name="start_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@Column(name="end_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="status")
	private String status;
	
	@Transient
	private int userId;
	@Transient
	private String parentTask;
	
	
	
	
	/**
	 * @return the parentTask
	 */
	public String getParentTask() {
		return parentTask;
	}
	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the partentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param partentId the partentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	
	

}
