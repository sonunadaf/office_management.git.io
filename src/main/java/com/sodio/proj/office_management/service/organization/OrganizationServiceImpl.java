package com.sodio.proj.office_management.service.organization;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodio.proj.office_management.controller.organization.dto.OrganizationRequestDTO;
import com.sodio.proj.office_management.controller.organization.dto.OrganisationResponseDTO;
import com.sodio.proj.office_management.controller.organization.dto.OrganizationDataDTO;
import com.sodio.proj.office_management.dao.organization.IOrganizationDao;
import com.sodio.proj.office_management.domain.organization.Organization;
import com.sodio.proj.office_management.exception.DaoException;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.DateUtility;
import com.sodio.proj.office_management.util.ResponseDTO;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

	@Autowired
	private IOrganizationDao organizationDao;
	private static Logger logger = Logger.getLogger(OrganizationServiceImpl.class);

	public void createOrg(OrganizationRequestDTO requestDTO) throws ServiceException {
		try {
			Organization organization = new Organization();
			BeanUtils.copyProperties(requestDTO, organization);
			organization.setCreatedDate(DateUtility.getDate(new Date()));
			organizationDao.saveOrg(organization);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateOrg(OrganizationRequestDTO requestDTO) throws ServiceException {
		try {
			Organization organization = organizationDao.getOrgById(requestDTO.getId());
			if (organization == null) {
				throw new ServiceException("Invalid id.");
			}
			BeanUtils.copyProperties(requestDTO, organization);
			organization.setUpdatedDate(DateUtility.getDate(new Date()));
			organizationDao.saveOrg(organization);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ResponseDTO getOrgById(int id) throws ServiceException {
		try {
			Organization org = organizationDao.getOrgById(id);
			if (org == null)
				throw new ServiceException("Invallid id.");
			OrganizationDataDTO dataDTO = new OrganizationDataDTO();
			BeanUtils.copyProperties(org, dataDTO);
			OrganisationResponseDTO dto = new OrganisationResponseDTO();
			List<OrganizationDataDTO> list = new ArrayList<OrganizationDataDTO>();
			list.add(dataDTO);
			dto.setData(list);
			return dto;
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	public ResponseDTO getAllOrg() throws ServiceException {
		try {
			List<Organization> orgList = organizationDao.getAllOrganization();
			List<OrganizationDataDTO> dataList = new ArrayList<OrganizationDataDTO>();
			for (Organization org : orgList) {
				OrganizationDataDTO dataDTO = new OrganizationDataDTO();
				BeanUtils.copyProperties(org, dataDTO);
				dataList.add(dataDTO);
			}
			OrganisationResponseDTO responseDTO = new OrganisationResponseDTO();
			responseDTO.setData(dataList);
			return responseDTO;

		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteOrg(int id) throws ServiceException {
		try {
			Organization org = organizationDao.getOrgById(id);
			if (org == null) {
				throw new ServiceException("Invalid Id.");
			}
			organizationDao.deleteOrg(org);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}
}
