package com.sodio.proj.office_management.service.organization;

import com.sodio.proj.office_management.controller.organization.dto.OrganizationRequestDTO;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.ResponseDTO;

public interface IOrganizationService {

	public void createOrg(OrganizationRequestDTO requestDTO) throws ServiceException;

	public void updateOrg(OrganizationRequestDTO requestDTO) throws ServiceException;

	public ResponseDTO getOrgById(int id) throws ServiceException;

	public ResponseDTO getAllOrg() throws ServiceException;

	public void deleteOrg(int id) throws ServiceException;

}
