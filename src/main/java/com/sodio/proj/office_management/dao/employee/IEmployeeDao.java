package com.sodio.proj.office_management.dao.employee;

import java.util.Set;

import com.sodio.proj.office_management.domain.employee.Employee;
import com.sodio.proj.office_management.exception.DaoException;

public interface IEmployeeDao {

	public void saveEmployee(Employee employee) throws DaoException;

	public Employee getEmpById(int id) throws DaoException;

	public Set<Employee> getAllEmployees(int orgId) throws DaoException;

	public void delete(Employee employee) throws DaoException;
}
