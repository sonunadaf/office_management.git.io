package com.sodio.proj.office_management.controller.hr;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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

import com.sodio.proj.office_management.controller.hr.dto.HrRequestDTO;
import com.sodio.proj.office_management.controller.login.dto.UserDTO;
import com.sodio.proj.office_management.domain.UserRole;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.service.hr.IHrService;
import com.sodio.proj.office_management.util.ResponseDTO;
import com.sodio.proj.office_management.util.SessionMng;

@RestController
@RequestMapping("/hr")
public class HrController {

	@Autowired
	private IHrService hrService;
	private static Logger logger = Logger.getLogger(HrController.class);

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createHr(@RequestBody HrRequestDTO requestDTO, HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			hrService.createHr(requestDTO);
			dto.setStatus("200");
			dto.setMessage("Hr created successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			dto.setStatus("500");
			dto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody HrRequestDTO requestDTO, HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			hrService.update(requestDTO);
			dto.setStatus("200");
			dto.setMessage("Hr updated successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			dto.setStatus("500");
			dto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable int id, HttpServletRequest request) {
		try {
			verifyUser(request);
			ResponseDTO dto = hrService.getHrById(id);
			dto.setStatus("200");
			dto.setMessage("Hr fetched successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			ResponseDTO dto = new ResponseDTO();
			dto.setStatus("500");
			dto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getAll(HttpServletRequest request) {
		try {
			verifyUser(request);
			ResponseDTO dto = hrService.getAllHr();
			dto.setStatus("200");
			dto.setMessage("Hr fetched successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			ResponseDTO dto = new ResponseDTO();
			dto.setStatus("500");
			dto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteHr(@PathVariable int id, HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			hrService.deleteHr(id);
			dto.setStatus("200");
			dto.setMessage("Hr deleted successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			dto.setStatus("500");
			dto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void verifyUser(HttpServletRequest request) throws ServiceException {
		try {
			UserDTO user = SessionMng.getSession(request);
			if (!user.getUserRole().equals(UserRole.ADMIN.toString())) {
				throw new ServiceException("Invalid User Role.");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
