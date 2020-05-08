package com.sodio.proj.office_management.dao.organization;

import java.util.List;

import com.sodio.proj.office_management.domain.organization.Organization;
import com.sodio.proj.office_management.exception.DaoException;

public interface IOrganizationDao {

	public void saveOrg(Organization organization) throws DaoException;

	public Organization getOrgById(int id) throws DaoException;

	public List<Organization> getAllOrganization() throws DaoException;

	public void deleteOrg(Organization organization) throws DaoException;

}
