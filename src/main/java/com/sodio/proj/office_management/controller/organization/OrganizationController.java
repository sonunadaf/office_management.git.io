package com.sodio.proj.office_management.controller.organization;

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

import com.sodio.proj.office_management.controller.login.dto.UserDTO;
import com.sodio.proj.office_management.controller.organization.dto.OrganizationRequestDTO;
import com.sodio.proj.office_management.domain.UserRole;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.service.organization.IOrganizationService;
import com.sodio.proj.office_management.util.ResponseDTO;
import com.sodio.proj.office_management.util.SessionMng;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private IOrganizationService organizationService;
	private static Logger logger = Logger.getLogger(OrganizationController.class);

	public OrganizationController() {
		logger.info("Created  : " + this.getClass().getSimpleName());
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createOrg(@RequestBody OrganizationRequestDTO requestDto,
			HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			organizationService.createOrg(requestDto);
			dto.setStatus("200");
			dto.setMessage("Organization created successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dto.setMessage(e.getMessage());
			dto.setStatus("500");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateOrg(@RequestBody OrganizationRequestDTO requestDto,
			HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			organizationService.updateOrg(requestDto);
			dto.setStatus("200");
			dto.setMessage("Organization updated successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dto.setMessage(e.getMessage());
			dto.setStatus("500");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseDTO> getOrg(@PathVariable int id, HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			ResponseDTO responseDTO = organizationService.getOrgById(id);
			responseDTO.setStatus("200");
			responseDTO.setMessage("Organization fetched successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dto.setMessage(e.getMessage());
			dto.setStatus("500");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getOrg(HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			ResponseDTO responseDTO = organizationService.getAllOrg();
			responseDTO.setStatus("200");
			responseDTO.setMessage("Organization fetched successfully.");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dto.setMessage(e.getMessage());
			dto.setStatus("500");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteOrg(@PathVariable int id, HttpServletRequest request) {
		ResponseDTO dto = new ResponseDTO();
		try {
			verifyUser(request);
			organizationService.deleteOrg(id);
			dto.setStatus("200");
			dto.setMessage("Organization deleted successfully.");
			return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dto.setMessage(e.getMessage());
			dto.setStatus("500");
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
