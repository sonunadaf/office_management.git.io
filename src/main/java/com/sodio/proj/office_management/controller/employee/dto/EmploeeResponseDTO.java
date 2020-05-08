package com.sodio.proj.office_management.controller.employee.dto;

import java.util.List;

import com.sodio.proj.office_management.util.ResponseDTO;

public class EmploeeResponseDTO extends ResponseDTO {

	List<EmployeeDataDTO> data;

	public List<EmployeeDataDTO> getData() {
		return data;
	}

	public void setData(List<EmployeeDataDTO> data) {
		this.data = data;
	}
	
	
}
