package com.project.electricityConns.utils.enums;

public enum Ownership {
	JOINT("JOINT"),INDIVIDUAL("INDIVIDUAL");
	
	private String value;
	
	private Ownership(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	public static Ownership getOwnership(String value) {
		for(Ownership val: Ownership.values())
			if(val.value.equals(value))
				return val;
		return null;
	}
}
