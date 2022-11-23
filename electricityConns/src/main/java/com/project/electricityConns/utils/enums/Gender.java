package com.project.electricityConns.utils.enums;

public enum Gender {
	Male("Male"), Female("Female");
	private String value;
	
	private Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static Gender getGender(String value) {
		for(Gender val: Gender.values())
			if(val.value.equals(value))
				return val;
		return null;
	}
}
