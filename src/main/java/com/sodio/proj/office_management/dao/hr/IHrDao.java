package com.sodio.proj.office_management.dao.hr;

import java.util.List;

import com.sodio.proj.office_management.domain.hr.HR;
import com.sodio.proj.office_management.exception.DaoException;

public interface IHrDao {

	public void saveHr(HR hr) throws DaoException;

	public HR getHrById(int id) throws DaoException;

	public List<HR> getAll() throws DaoException;

	public void delete(HR hr) throws DaoException;

	public HR getHrByUserName(String userName) throws DaoException;

}
