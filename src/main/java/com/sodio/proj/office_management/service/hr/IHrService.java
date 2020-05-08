package com.sodio.proj.office_management.service.hr;

import com.sodio.proj.office_management.controller.hr.dto.HrRequestDTO;
import com.sodio.proj.office_management.exception.ServiceException;
import com.sodio.proj.office_management.util.ResponseDTO;

public interface IHrService {

	public void createHr(HrRequestDTO requestDTO) throws ServiceException;

	public void update(HrRequestDTO requestDTO) throws ServiceException;

	public void deleteHr(int id) throws ServiceException;

	public ResponseDTO getHrById(int id) throws ServiceException;

	public ResponseDTO getAllHr() throws ServiceException;

}
