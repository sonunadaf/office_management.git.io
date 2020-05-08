package com.sodio.proj.office_management.service.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodio.proj.office_management.controller.employee.dto.EmploeeResponseDTO;
import com.sodio.proj.office_management.controller.employee.dto.EmployeeDataDTO;
import com.sodio.proj.office_management.controller.employee.dto.EmployeeRequestDTO;
import com.sodio.proj.office_management.dao.employee.IEmployeeDao;
import com.sodio.proj.office_management.dao.organization.IOrganizationDao;
import com.sodio.proj.office_management.domain.UserRole;
import com.sodio.proj.office_management.domain.employee.Employee;
import com.sodio.proj.office_management.domain.organization.Organization;
import com.sodio.proj.office_management.exception.DaoException;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.DateUtility;
import com.sodio.proj.office_management.util.ResponseDTO;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;
	@Autowired
	private IOrganizationDao organizationDao;

	public void createEmployee(EmployeeRequestDTO dto) throws ServiceException {
		try {
			Organization org = organizationDao.getOrgById(dto.getOrganizationId());
			if (org == null) {
				throw new ServiceException("Invalid Organization Id.");
			}
			Employee employee = new Employee();
			BeanUtils.copyProperties(dto, employee);
			employee.setCreatedAt(DateUtility.getDate(new Date()));
			employee.setRole(UserRole.EMPLOYEE.toString());
			employee.setOrganizationName(org.getOrganizationName());
			employee.setOrganization(org);
			UUID uuid = UUID.randomUUID();
			employee.setEmployeeId(uuid.toString());
			employeeDao.saveEmployee(employee);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateEmployee(EmployeeRequestDTO dto) throws ServiceException {
		try {
			Employee emp = employeeDao.getEmpById(dto.getId());
			if (emp == null) {
				throw new ServiceException("Invalid Employee Id.");
			}
			emp.setName(dto.getName());
			emp.setEmail(dto.getEmail());
			emp.setUpdatedAt(DateUtility.getDate(new Date()));
			emp.setMobileNumber(dto.getMobileNumber());
			employeeDao.saveEmployee(emp);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public ResponseDTO getEmpById(int id) throws ServiceException {
		try {
			Employee emp = employeeDao.getEmpById(id);
			EmployeeDataDTO dataDTO = new EmployeeDataDTO();
			BeanUtils.copyProperties(emp, dataDTO);
			List<EmployeeDataDTO> list = new ArrayList<EmployeeDataDTO>();
			list.add(dataDTO);
			EmploeeResponseDTO emploeeResponseDTO = new EmploeeResponseDTO();
			emploeeResponseDTO.setData(list);
			return emploeeResponseDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public ResponseDTO getAllEmp(int orgId) throws ServiceException {
		try {
			Set<Employee> emp = employeeDao.getAllEmployees(orgId);
			List<EmployeeDataDTO> emList = new ArrayList<EmployeeDataDTO>();
			for (Employee employee : emp) {
				EmployeeDataDTO dataDTO = new EmployeeDataDTO();
				BeanUtils.copyProperties(employee, dataDTO);
				emList.add(dataDTO);
			}
			EmploeeResponseDTO responseDTO = new EmploeeResponseDTO();
			responseDTO.setData(emList);
			return responseDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteEmployee(int id) throws ServiceException {
		try {
			Employee emp = employeeDao.getEmpById(id);
			if (emp == null) {
				throw new ServiceException("Invalid Employee Id.");
			}
			employeeDao.delete(emp);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}
