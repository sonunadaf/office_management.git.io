package com.sodio.proj.office_management.dao.user;

import com.sodio.proj.office_management.domain.user.User;
import com.sodio.proj.office_management.exception.DaoException;

public interface IUserDao {

	public void saveUser(User user) throws DaoException;

	public User getUser(String userName) throws DaoException;

}
