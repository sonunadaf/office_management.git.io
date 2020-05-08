package com.sodio.proj.office_management.controller.organization.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrganizationDataDTO {

	private int id;
	private String organizationName;
	private String website;
	private String phoneNumber;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
