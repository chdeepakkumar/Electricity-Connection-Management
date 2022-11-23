package com.project.electricityConns.utils.enums;

public enum Category {
	Commercial("Commercial"), Residential("Residential");

	private String value;

	private Category(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static Category getCategory(String value) {
		for(Category val: Category.values())
			if(val.value.equals(value))
				return val;
		return null;
	}
}
