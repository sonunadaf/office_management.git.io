package com.sodio.proj.office_management.controller.employee;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodio.proj.office_management.controller.employee.dto.EmployeeRequestDTO;
import com.sodio.proj.office_management.controller.login.dto.UserDTO;
import com.sodio.proj.office_management.domain.UserRole;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.service.employee.IEmployeeService;
import com.sodio.proj.office_management.util.ResponseDTO;
import com.sodio.proj.office_management.util.SessionMng;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO dto, HttpServletRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			employeeService.createEmployee(dto);
			responseDTO.setStatus("200");
			responseDTO.setMessage("Employee created successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (ServiceException e) {
			responseDTO.setStatus("500");
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO dto, HttpServletRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			employeeService.updateEmployee(dto);
			responseDTO.setStatus("200");
			responseDTO.setMessage("Employee updated successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (ServiceException e) {
			responseDTO.setStatus("500");
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getEmpById(@PathVariable int id, HttpServletRequest request) {

		try {
			ResponseDTO responseDTO = employeeService.getEmpById(id);
			responseDTO.setStatus("200");
			responseDTO.setMessage("Employee fetched successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (ServiceException e) {
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus("500");
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllEmp/{orgId}")
	public ResponseEntity<ResponseDTO> getAllEmp(@PathVariable int orgId, HttpServletRequest request) {
		try {
			ResponseDTO responseDTO = employeeService.getAllEmp(orgId);
			responseDTO.setStatus("200");
			responseDTO.setMessage("Employee fetched successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (ServiceException e) {
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus("500");
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> updateEmployee(@PathVariable int id, HttpServletRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			employeeService.deleteEmployee(id);
			responseDTO.setStatus("200");
			responseDTO.setMessage("Employee deleted successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (ServiceException e) {
			responseDTO.setStatus("500");
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
