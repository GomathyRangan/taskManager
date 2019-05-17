package com.cts.taskmanager.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.cts.taskmanager.util.TaskManagerUtil;
import com.cts.taskmanager.vo.ParentTask;
import com.cts.taskmanager.vo.ProjectDetails;
import com.cts.taskmanager.vo.Task;

@Transactional
public class TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addTask(Task task) throws Exception {
		Session session = sessionFactory.getCurrentSession();			
		session.persist(task);			
		System.out.println("Book Details inserted into Database");
	}

	public void addParentTask(ParentTask parentTask) throws Exception {
		Session session = sessionFactory.getCurrentSession();			
		session.persist(parentTask);			
		System.out.println("Book Details inserted into Database");
	}


	public List<ParentTask> getAllParentTask() throws Exception {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<ParentTask> criteriaQuery = criteriaBuilder.createQuery(ParentTask.class);
		Root<ParentTask> root = criteriaQuery.from(ParentTask.class);
		criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).getResultList();

	}
	
	
	public List<Task> getAllTask() throws Exception {

		List<Task> taskDetails = new ArrayList<Task>();

		String sql = "select t.task,p.parent_task,t.priority,t.start_date,t.end_date from fsd_assg.task t, fsd_assg.parent_task p where t.parent_id = p.parent_id";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> result = query.getResultList();
		for(Object[] row : result){
			Task task = new Task();
			task.setTask(row[0].toString());
			task.setParentTask(row[1].toString());
			task.setPriority(row[2] != null ? Integer.parseInt(row[2].toString()) : 0);		
			task.setStartDate(row[3] != null ? TaskManagerUtil.convertToDate(row[3].toString()) : null);
			task.setEndDate(row[3] != null ? TaskManagerUtil.convertToDate(row[3].toString()) : null);
			
			taskDetails.add(task);

		}
		return taskDetails;

	}

	public Task getTaskById(int id) throws Exception {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
		Root<Task> root = criteriaQuery.from(Task.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("taskId"),id));
		//	criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).uniqueResult();

	}


	public List<Task> getTaskByProjectId(int projectId) throws Exception {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
		Root<Task> root = criteriaQuery.from(Task.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("projectId"),projectId));
		//	criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).getResultList();

	}

	public void updateTaskStatus (int taskId,String status) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaUpdate<Task> updateCriteria = criteriaBuilder.createCriteriaUpdate(Task.class);
		Root<Task> e = updateCriteria.from(Task.class);
		updateCriteria.set("status", status);
		updateCriteria.where(criteriaBuilder.equal(e.get("taskId"), taskId));
		session.createQuery(updateCriteria).executeUpdate();
	}
	
	
	public void updateTask (Task task) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaUpdate<Task> updateCriteria = criteriaBuilder.createCriteriaUpdate(Task.class);
		Root<Task> e = updateCriteria.from(Task.class);
		updateCriteria.set("task", task.getTask());
		updateCriteria.set("priority", task.getPriority());
		updateCriteria.set("startDate", task.getStartDate());
		updateCriteria.set("endDate", task.getEndDate());
		updateCriteria.where(criteriaBuilder.equal(e.get("taskId"), task.getTaskId()));
		session.createQuery(updateCriteria).executeUpdate();
	}

}
