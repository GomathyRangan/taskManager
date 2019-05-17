package com.cts.taskmanager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.taskmanager.exception.DAOException;
import com.cts.taskmanager.vo.Project;
import com.cts.taskmanager.vo.ProjectDetails;
import com.cts.taskmanager.vo.User;



@Transactional
public class ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addOrUpdateProject(Project project) throws DAOException {
		Session session = sessionFactory.getCurrentSession();			
		session.saveOrUpdate(project);			
		System.out.println("Project Details inserted into Database");
	}

	public List<ProjectDetails> getProjectDetails() throws DAOException {
		/*
		 * Session session = sessionFactory.getCurrentSession(); CriteriaBuilder
		 * criteriaBuilder = session.getCriteriaBuilder(); CriteriaQuery<Project>
		 * criteriaQuery = criteriaBuilder.createQuery(Project.class); Root<Project>
		 * root = criteriaQuery.from(Project.class); criteriaQuery.select(root); return
		 * session.createQuery(criteriaQuery).getResultList();
		 */
		List<ProjectDetails> projectDetails = new ArrayList<ProjectDetails>();

		String sql = "select p.project,t.start_date,t.end_date,p.priority,t.status,count(t.project_id),p.project_id  "
				+ "from fsd_assg.task t,fsd_assg.project p where t.project_id=p.project_id \r\n" + 
				"group by t.project_id";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> result = query.getResultList();
		for(Object[] row : result){
			ProjectDetails projDetails = new ProjectDetails();
			projDetails.setProjectName(row[0].toString());
			projDetails.setStartDate(row[1].toString());
			projDetails.setEndDate(row[2].toString());			
			projDetails.setPriority(row[3] != null ? Integer.parseInt(row[3].toString()) : 0);
			projDetails.setStatus(row[4] != null ? row[4].toString() : "");
			projDetails.setNoOfTask(row[5] != null ? Integer.parseInt(row[5].toString()) : 0);
			projDetails.setProjectId(row[6] != null ? Integer.parseInt(row[6].toString()) : 0);
			projectDetails.add(projDetails);

		}
		
		return projectDetails;


	}
	
	public boolean deleteProject(int id) throws DAOException {
		boolean isDeleted = false;
		Session session = sessionFactory.getCurrentSession();			
		Query query = session.createQuery("delete Project where id= :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();

		if (result > 0) {
			isDeleted = true;
		}

		return isDeleted;
	}
	
	
	public List<Project> getAllProjects() throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
		Root<Project> root = criteriaQuery.from(Project.class);
		criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).getResultList();

	}
	
	public Project getProjectById(int id) throws DAOException {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
		Root<Project> root = criteriaQuery.from(Project.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("projectId"),id));
	//	criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).uniqueResult();

	}
}
