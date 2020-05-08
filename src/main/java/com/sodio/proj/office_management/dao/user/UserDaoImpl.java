package com.sodio.proj.office_management.dao.user;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sodio.proj.office_management.domain.user.User;
import com.sodio.proj.office_management.exception.DaoException;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveUser(User user) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public User getUser(String userName) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from User where userName=:un";
			Query query = session.createQuery(sql);
			query.setParameter("un", userName);
			User user = (User) query.uniqueResult();
			return user;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

}
