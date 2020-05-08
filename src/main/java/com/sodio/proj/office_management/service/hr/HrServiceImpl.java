package com.sodio.proj.office_management.service.hr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sodio.proj.office_management.controller.hr.dto.HrDataDTO;
import com.sodio.proj.office_management.controller.hr.dto.HrRequestDTO;
import com.sodio.proj.office_management.controller.hr.dto.HrResponseDTO;
import com.sodio.proj.office_management.dao.hr.IHrDao;
import com.sodio.proj.office_management.dao.user.IUserDao;
import com.sodio.proj.office_management.domain.UserRole;
import com.sodio.proj.office_management.domain.hr.HR;
import com.sodio.proj.office_management.domain.user.User;
import com.sodio.proj.office_management.exception.DaoException;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.DateUtility;
import com.sodio.proj.office_management.util.ResponseDTO;

@Service
public class HrServiceImpl implements IHrService {

	@Autowired
	private IHrDao hrDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void createHr(HrRequestDTO requestDTO) throws ServiceException {

		try {
			HR dbHr = hrDao.getHrByUserName(requestDTO.getUserName());
			if (dbHr != null) {
				throw new ServiceException("UserName already exist.");
			}
			HR hr = new HR();
			BeanUtils.copyProperties(requestDTO, hr);
			hr.setCreatedAt(DateUtility.getDate(new Date()));
			hr.setPassword(encoder.encode(requestDTO.getPassword()));
			hr.setRole(UserRole.HR.toString());
			hrDao.saveHr(hr);
			saveUser(requestDTO);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void update(HrRequestDTO requestDTO) throws ServiceException {
		try {
			HR dbHr = hrDao.getHrByUserName(requestDTO.getUserName());
			if (dbHr != null && dbHr.getId() != requestDTO.getId()) {
				throw new ServiceException("UserName already exist.");
			}

			dbHr.setName(requestDTO.getName());
			dbHr.setUserName(requestDTO.getUserName());
			dbHr.setMobileNumber(requestDTO.getMobileNumber());
			dbHr.setOrganizationName(requestDTO.getOrganizationName());
			dbHr.setUpdatedAt(DateUtility.getDate(new Date()));
			dbHr.setPassword(encoder.encode(requestDTO.getPassword()));
			hrDao.saveHr(dbHr);
			saveUser(requestDTO);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public ResponseDTO getHrById(int id) throws ServiceException {
		try {
			HR hr = hrDao.getHrById(id);
			if (hr == null) {
				throw new ServiceException("Invalid Id.");
			}
			HrDataDTO dataDTO = new HrDataDTO();
			BeanUtils.copyProperties(hr, dataDTO);
			List<HrDataDTO> dataList = new ArrayList<HrDataDTO>();
			dataList.add(dataDTO);
			HrResponseDTO responseDTO = new HrResponseDTO();
			responseDTO.setData(dataList);
			return responseDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ResponseDTO getAllHr() throws ServiceException {
		try {
			List<HR> hrList = hrDao.getAll();
			List<HrDataDTO> dataList = new ArrayList<HrDataDTO>();
			for (HR hr : hrList) {
				HrDataDTO dataDTO = new HrDataDTO();
				BeanUtils.copyProperties(hr, dataDTO);
				dataList.add(dataDTO);
			}

			HrResponseDTO responseDTO = new HrResponseDTO();
			responseDTO.setData(dataList);
			return responseDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteHr(int id) throws ServiceException {

		try {
			HR hr = hrDao.getHrById(id);
			if (hr == null) {
				throw new ServiceException("Invalid Id.");
			}
			hrDao.delete(hr);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private void saveUser(HrRequestDTO requestDTO) throws ServiceException {
		try {
			User user = new User();
			user.setUserName(requestDTO.getUserName());
			user.setEmail(requestDTO.getEmail());
			user.setPassword(encoder.encode(requestDTO.getPassword()));
			user.setUserRole(UserRole.HR.toString());
			userDao.saveUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
