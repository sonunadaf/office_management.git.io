package com.sodio.proj.office_management.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sodio.proj.office_management.controller.login.dto.UserDTO;
import com.sodio.proj.office_management.exception.ServiceException;

public class SessionMng {

	public static void createSession(HttpServletRequest request, UserDTO user) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}

	public static UserDTO getSession(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			throw new ServiceException("Session Expired.");
		}
		return user;
	}

}
