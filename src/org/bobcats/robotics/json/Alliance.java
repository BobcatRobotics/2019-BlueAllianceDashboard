package org.bobcats.robotics.json;

import java.util.Arrays;

public class Alliance {
	private String [] dq_team_keys;
	private int score;
	private String [] surrogate_team_keys;
	private String [] team_keys;
	
	public int findTeam(String team) {
		int pos = -1;
		if (team.equals(team_keys[0])) {
			pos = 1;
		} else if (team.equals(team_keys[1])) {
			pos = 2;
		} else if (team.equals(team_keys[2])) {
			pos = 3;
		}
		return pos;
	}
	
	@Override
	public String toString() {
		return "Alliance [dq_team_keys=" + dq_team_keys + ", score=" + score + ", surrogate_team_keys="
				+ surrogate_team_keys + ", team_keys=" + Arrays.toString(team_keys) + "]";
	}
	
	
}
