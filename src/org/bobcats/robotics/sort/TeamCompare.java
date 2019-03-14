package org.bobcats.robotics.sort;

import java.util.Comparator;

import org.bobcats.robotics.json.Team;

public class TeamCompare implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		return o1.getTeam_number() - o2.getTeam_number();
	}
}
