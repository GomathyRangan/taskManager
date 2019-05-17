package com.cts.taskmanager.dao;



import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cts.taskmanager.exception.DAOException;
import com.cts.taskmanager.vo.User;


@Transactional
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) throws DAOException {
		Session session = sessionFactory.getCurrentSession();			
		session.saveOrUpdate(user);			
		System.out.println("Book Details inserted into Database");
	}

	public List<User> getAllUserDetails() throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).getResultList();

	}


	public void deleteUser(int id) throws DAOException {

		Session session = sessionFactory.getCurrentSession();			
		Query query = session.createQuery("delete User where id= :id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	public User getUserById(int id) throws DAOException {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder =  session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("userId"),id));
	//	criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).uniqueResult();

	}







}
