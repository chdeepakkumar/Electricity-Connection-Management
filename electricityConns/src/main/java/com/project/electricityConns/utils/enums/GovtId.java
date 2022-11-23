package com.project.electricityConns.utils.enums;

public enum GovtId {
	AADHAR("AADHAR"), VOTER_ID("VOTER_ID"), PAN("PAN"), PASSPORT("PASSPORT");

	private String value;

	private GovtId(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static GovtId getGovtId(String value) {
		for(GovtId val: GovtId.values())
			if(val.value.equals(value))
				return val;
		return null;
	}
}
