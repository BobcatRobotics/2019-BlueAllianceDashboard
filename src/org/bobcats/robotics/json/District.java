package org.bobcats.robotics.json;

public class District {
	private String abbreviation;
	private String display_name;
	private String key;
	private int year;

	public String getKey() {
		return key;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "District [abbreviation=" + abbreviation + ", display_name=" + display_name + ", key=" + key + ", year="
				+ year + "]";
	}

}
