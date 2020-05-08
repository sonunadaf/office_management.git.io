package com.sodio.proj.office_management.dao.hr;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sodio.proj.office_management.domain.hr.HR;
import com.sodio.proj.office_management.exception.DaoException;

@Repository
public class HrDaoImpl implements IHrDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveHr(HR hr) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(hr);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public HR getHrById(int id) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			HR hr = session.get(HR.class, id);
			return hr;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public List<HR> getAll() throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from HR";
			Query query = session.createQuery(sql);
			List<HR> hrList = query.list();
			return hrList;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public void delete(HR hr) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(hr);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public HR getHrByUserName(String userName) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from HR where userName=:un";
			Query query = session.createQuery(sql);
			query.setParameter("un", userName);
			HR hr = (HR) query.uniqueResult();
			return hr;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

}
