package com.sodio.proj.office_management.controller.organization.dto;

import java.util.List;

import com.sodio.proj.office_management.util.ResponseDTO;

public class OrganisationResponseDTO extends ResponseDTO {

	List<OrganizationDataDTO> data;

	public List<OrganizationDataDTO> getData() {
		return data;
	}

	public void setData(List<OrganizationDataDTO> data) {
		this.data = data;
	}

}
