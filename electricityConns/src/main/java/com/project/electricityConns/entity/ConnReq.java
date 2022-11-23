package com.project.electricityConns.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.electricityConns.utils.enums.Category;
import com.project.electricityConns.utils.enums.Gender;
import com.project.electricityConns.utils.enums.GovtId;
import com.project.electricityConns.utils.enums.Ownership;
import com.project.electricityConns.utils.enums.Status;

import lombok.Data;

@Entity
@Table(name = "connreq")
@Data
public class ConnReq {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String district;
	private String state;
	private String pinCode;
	@Enumerated(EnumType.STRING)
	private Ownership ownership;
	@Enumerated(EnumType.STRING)
	private GovtId govtId;
	@Column(unique = true)
	private String idNumber;
	@Enumerated(EnumType.STRING)
	private Category category;
	private Integer loadAppl;
	private LocalDate dateOfApplication;
	private LocalDate dateOfApproval;
	private LocalDate modifiedDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	private Integer reviewerId;
	private String reviewerName;
	private String comments;
}
