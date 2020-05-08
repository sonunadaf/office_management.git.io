package com.sodio.proj.office_management.dao.organization;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sodio.proj.office_management.domain.organization.Organization;
import com.sodio.proj.office_management.exception.DaoException;

@Repository
public class OrganizationDaoImpl implements IOrganizationDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(OrganizationDaoImpl.class);

	public void saveOrg(Organization organization) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(organization);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public Organization getOrgById(int id) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			Organization org = session.get(Organization.class, id);
			return org;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public List<Organization> getAllOrganization() throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from Organization";
			Query query = session.createQuery(sql);
			List<Organization> orgList = query.list();
			return orgList;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public void deleteOrg(Organization organization) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(organization);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

}
