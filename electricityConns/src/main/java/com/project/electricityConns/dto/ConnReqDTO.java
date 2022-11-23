package com.project.electricityConns.dto;
import java.time.LocalDate;

import com.project.electricityConns.utils.enums.Category;
import com.project.electricityConns.utils.enums.Gender;
import com.project.electricityConns.utils.enums.GovtId;
import com.project.electricityConns.utils.enums.Ownership;
import com.project.electricityConns.utils.enums.Status;

import lombok.Data;

@Data
public class ConnReqDTO {
	private Integer id;
	private String name;
	private Gender gender;
	private String district;
	private String state;
	private String pinCode;
	private Ownership ownership;
	private GovtId govtId;
	private String idNumber;
	private Category category;
	private Integer loadAppl;
	private LocalDate dateOfApplication;
	private LocalDate dateOfApproval;
	private LocalDate modifiedDate;
	private Status status;
	private Integer reviewerId;
	private String reviewerName;
	private String comments;
}
