package com.sodio.proj.office_management.service.employee;

import com.sodio.proj.office_management.controller.employee.dto.EmployeeRequestDTO;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.ResponseDTO;

public interface IEmployeeService {

	public void createEmployee(EmployeeRequestDTO dto) throws ServiceException;

	public void updateEmployee(EmployeeRequestDTO dto) throws ServiceException;

	public ResponseDTO getEmpById(int id) throws ServiceException;

	public ResponseDTO getAllEmp(int orgId) throws ServiceException;

	public void deleteEmployee(int id) throws ServiceException;

}
