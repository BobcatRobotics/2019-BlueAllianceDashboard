package org.bobcats.robotics.json;

public class SimpleEvent {
	String city;
    String country;
    District district;
    String end_date;
    String event_code;
    String event_type;
    String key;
    String name;
    String start_date;
    String state_prov;
    String year;
    
	public String getCountry() {
		return country;
	}

	public String toShortString() {
		return "city=" + city + ", end_date="	+ end_date + ", event_code=" + event_code + ", event_type=" + event_type + ", key=" + key;	
	}
	
	public String getYear() {
		return year;
	}

	
	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	
	public District getDistrict() {
		return district;
	}

	@Override
	public String toString() {
		return "SimpleEvent [city=" + city + ", country=" + country + ", district=" + district + ", end_date="
				+ end_date + ", event_code=" + event_code + ", event_type=" + event_type + ", key=" + key + ", name="
				+ name + ", start_date=" + start_date + ", state_prov=" + state_prov + ", year=" + year + "]";
	}
}
