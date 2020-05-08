package com.sodio.proj.office_management.dao.employee;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sodio.proj.office_management.domain.employee.Employee;
import com.sodio.proj.office_management.domain.organization.Organization;
import com.sodio.proj.office_management.exception.DaoException;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveEmployee(Employee employee) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(employee);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public Employee getEmpById(int id) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			Employee emp = session.get(Employee.class, id);
			return emp;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public Set<Employee> getAllEmployees(int orgId) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			Organization org = session.get(Organization.class, orgId);
			if (org == null) {
				throw new DaoException("Invalid Organization id.");
			}
			Set<Employee> emps = org.getEmployees();
			return emps;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void delete(Employee employee) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(employee);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

}
