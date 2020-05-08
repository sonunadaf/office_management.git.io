package com.sodio.proj.office_management.service.user;

import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.ResponseDTO;

public interface IUserService {

	public ResponseDTO login(String userName, String password) throws ServiceException;

}
