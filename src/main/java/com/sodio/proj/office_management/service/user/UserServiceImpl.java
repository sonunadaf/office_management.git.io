package com.sodio.proj.office_management.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sodio.proj.office_management.controller.login.dto.UserDTO;
import com.sodio.proj.office_management.dao.user.IUserDao;
import com.sodio.proj.office_management.domain.UserRole;
import com.sodio.proj.office_management.domain.user.User;
import com.sodio.proj.office_management.exception.DaoException;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.ResponseDTO;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public ResponseDTO login(String userName, String password) throws ServiceException {

		try {
			User user = userDao.getUser(userName);

			if (user != null && user.getUserRole().contentEquals(UserRole.ADMIN.toString())
					&& user.getPassword().equals(password)) {
				return getUserDto(user);
			} else if (user != null && encoder.matches(password, user.getPassword())) {
				return getUserDto(user);
			} else {
				throw new ServiceException("Invalid user name/ password.");
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	private UserDTO getUserDto(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(user.getUserName());
		userDTO.setEmail(user.getEmail());
		userDTO.setUserRole(user.getUserRole());
		return userDTO;
	}

}
