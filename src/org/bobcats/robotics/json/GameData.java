package org.bobcats.robotics.json;

import java.util.Arrays;
import java.util.Map;

public class GameData {
	private String actual_time;
	private Map<String, Alliance> alliances;
	private String comp_level;
	private String event_key;  // Event
	private String key;		   // Match
	private String match_number;
	private String post_result_time;
	private String predicted_time;
	private Map<String,Scores> score_breakdown;
	private String set_number;
	private String time;
	private Video [] videos;
	private String winning_alliance;
	
	private String teamColor ="";
	
	public String getAllianceColor(String team) {
		String color = "";
		int blue = alliances.get("blue").findTeam(team);
		int red = alliances.get("red").findTeam(team);
		if (blue > 0) {
			color = "blue";
		}
		if (red > 0 ) {
			color = "red";
		}
		teamColor = color;
		return color;
	}
	
	public int getAllianceTeamPos(String team) {
		int pos = -1;
		int blue = alliances.get("blue").findTeam(team);
		int red = alliances.get("red").findTeam(team);
		if (blue > 0) {
			pos = blue;
		}
		if (red > 0 ) {
			pos = red;
		}
		return pos;
	}
	
//	public boolean hasFinished() {
//		return dis
//	}
//	
	public String getKey() {
		return key;
	}

	public Scores getTeamScores( ) {
		return score_breakdown.get(teamColor);
	}
	
	public Scores getOpponentsTeamScores() {
		Scores sc = new Scores();
		if ("blue".equals(teamColor)) {
			sc = score_breakdown.get("red");
		}
		if ("red".equals(teamColor)) {
			sc= score_breakdown.get("blue");
		}
		return sc;
	}
	
	public String getShortName() {
		return key.substring(key.indexOf('_')+1);
	}
	
	public String getMatchStatus() {
		StringBuilder mstat = new StringBuilder();
		if (teamColor.equals(winning_alliance)) 
			mstat.append("W ");
		else 
			mstat.append("L ");
		int us = getTeamScores().getTotalPoints();
		int them = getOpponentsTeamScores().getTotalPoints();
		mstat.append(us);
		mstat.append(" - ");
		mstat.append(them);
		return mstat.toString();
	}
	
	public boolean matchCompleted() {
		boolean complete = true;
		if ((actual_time == null) || (post_result_time == null)) {
			complete = false;
		}
		return complete;
	}	
	@Override
	public String toString() {
		return "GameData [actual_time=" + actual_time + ", alliances=" + alliances + ", comp_level=" + comp_level
				+ ", event_key=" + event_key + ", key=" + key + ", match_number=" + match_number + ", post_result_time="
				+ post_result_time + ", predicted_time=" + predicted_time + ", score_breakdown=" + score_breakdown
				+ ", set_number=" + set_number + ", time=" + time + ", videos=" + Arrays.toString(videos)
				+ ", winning_alliance=" + winning_alliance + "]";
	}
}
