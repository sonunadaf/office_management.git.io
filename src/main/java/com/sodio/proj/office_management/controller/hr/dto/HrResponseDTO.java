package com.sodio.proj.office_management.controller.hr.dto;

import java.util.List;

import com.sodio.proj.office_management.util.ResponseDTO;

public class HrResponseDTO extends ResponseDTO {

	List<HrDataDTO> data;

	public List<HrDataDTO> getData() {
		return data;
	}

	public void setData(List<HrDataDTO> data) {
		this.data = data;
	}

}
