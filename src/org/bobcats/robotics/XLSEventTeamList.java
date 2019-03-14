package org.bobcats.robotics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.bobcats.robotics.json.SimpleEvent;
import org.bobcats.robotics.json.Team;
import org.bobcats.robotics.sort.TeamCompare;

public class XLSEventTeamList extends XLSReportGenerator {
	private static final String reportName = "teamlist.xlsx";
	private String eventName = "2018dar";
	
	public XLSEventTeamList() {
		createReport(reportName,"teams");
	}
	
	public XLSEventTeamList(String eventName) {
		super();
		this.eventName = eventName;
	}


	public List<String> getData() throws IOException {
		List<String> teams = new ArrayList<String>();	
		
		SimpleEvent event = http.callURL(SimpleEvent.class, eventURL, eventName);
		Row headerRow = sheet.createRow(0);
		Cell cell = headerRow.createCell(0);
		cell.setCellValue((String)String.format("%s - %s", event.getYear(),event.getName()));
		Row headerRow2 = sheet.createRow(1);
		Cell cell2 = headerRow2.createCell(0);
		cell2.setCellValue((String)String.format("Start: %s - End: %s", event.getStart_date(),event.getEnd_date()));
//		System.out.println(event.toShortString());
//		System.out.println(event);
		
		int row = 3;
		List<Team> eventTeams = http.callListURL(Team.class, eventTeamURL, eventName);
		Collections.sort(eventTeams,new TeamCompare());
		for (Team team: eventTeams) {
//			System.out.println("Team - " + team);
			teams.add(team.getKey());
			Row detailRow = sheet.createRow(row);
			Cell c1 = detailRow.createCell(0);
			c1.setCellValue((Integer)team.getTeam_number());
			Cell c2 = detailRow.createCell(1);
			c2.setCellValue((String)team.getNickname());
			Cell c3 = detailRow.createCell(2);
			c3.setCellValue((String)team.getFullAddress());

			row++;
		}
		return teams;
	}
}
