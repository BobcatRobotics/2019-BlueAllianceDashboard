package org.bobcats.robotics.json;
import java.util.Map;

public class Team {
	private String address;
	private String city;
	private String country;
	private String gmaps_place_id;
	private String gmaps_url;
	private Map<Integer, String> home_championship;	
	private String key;
	private String lat;
	private String lng;
	private String location_name;
	private String motto;
	private String name;
	private String nickname;
	private String postal_code;
	private String rookie_year;
	private String state_prov;
	private int team_number;
	private String website;
	
	public Team() {
		super();
	}


	public String getKey() {
		return key;
	}


	public String getName() {
		return name;
	}


	public String getNickname() {
		return nickname;
	}

	public String getAddress() {
		return address;
	}


	public String getCity() {
		return city;
	}


	public String getCountry() {
		return country;
	}


	public String getState_prov() {
		return state_prov;
	}


	public int getTeam_number() {
		return team_number;
	}

	
	public String getFullAddress() {
		return String.format("%s, %s %s", city,state_prov,country);
	}

	@Override
	public String toString() {
		return "Team [address=" + address + ", city=" + city + ", country=" + country + ", gmaps_place_id="
				+ gmaps_place_id + ", gmaps_url=" + gmaps_url + ", home_championship=" + home_championship + ", key="
				+ key + ", lat=" + lat + ", lng=" + lng + ", location_name=" + location_name + ", motto=" + motto
				+ ", name=" + name + ", nickname=" + nickname + ", postal_code=" + postal_code + ", rookie_year="
				+ rookie_year + ", state_prov=" + state_prov + ", team_number=" + team_number + ", website=" + website
				+ "]";
	}
	
	
}
