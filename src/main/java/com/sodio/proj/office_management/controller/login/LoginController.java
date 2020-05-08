package com.sodio.proj.office_management.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sodio.proj.office_management.controller.login.dto.UserDTO;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.service.user.IUserService;
import com.sodio.proj.office_management.util.ResponseDTO;
import com.sodio.proj.office_management.util.SessionMng;

@RestController
@RequestMapping("/")
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;

	public LoginController() {
		logger.info("Created : " + this.getClass().getSimpleName());
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestParam String userName, @RequestParam String password,
			HttpServletRequest request) {
		try {
			ResponseDTO responseDto = userService.login(userName, password);
			HttpSession session = request.getSession();
			UserDTO user = (UserDTO) responseDto;
			session.setAttribute("user", user);
			responseDto.setStatus("200");
			responseDto.setMessage("Login Successfully.");
			return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
		} catch (ServiceException e) {
			ResponseDTO responseDto = new ResponseDTO();
			responseDto.setStatus("500");
			responseDto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<ResponseDTO> logOut(HttpServletRequest request) {
		try {
			ResponseDTO responseDto = new ResponseDTO();
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			responseDto.setStatus("200");
			responseDto.setMessage("Logout Successfully.");
			return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
		} catch (Exception e) {
			ResponseDTO responseDto = new ResponseDTO();
			responseDto.setStatus("500");
			responseDto.setMessage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
