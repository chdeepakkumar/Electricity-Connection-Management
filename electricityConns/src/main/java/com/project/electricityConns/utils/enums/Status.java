package com.project.electricityConns.utils.enums;

public enum Status {
	Approved("Approved"), Pending("Pending"), Rejected("Rejected"), Released("Released");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static Status getStatus(String value) {
		for(Status val: Status.values())
			if(val.value.equals(value))
				return val;
		return null;
	}
}
