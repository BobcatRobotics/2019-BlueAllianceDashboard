package org.bobcats.robotics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.bobcats.robotics.json.SimpleEvent;

public class XLSTeamEventList extends XLSReportGenerator {
	private String year = "2019";
	private String teamNbr = "frc177";
	private String reportName = "events.xlsx";
	private boolean generateXLS = true;

	private XLSTeamEventList() {
		super();
	}

	public XLSTeamEventList(String team,boolean generateXLS) {
		this();
		teamNbr = team;
		String teamnbr = team.substring(3);
		reportName = "team" + teamnbr + "_events.xlsx";
		this.generateXLS = generateXLS;
	}

	public String getReportName() {
		return reportName;
	}

	public List<String> getFullData() throws IOException {
		List<String> eventList = new ArrayList<String>();
		List<SimpleEvent> evtList = http.callListURL(SimpleEvent.class, teamEventListURL, teamNbr, year);
		int rowNbr = 0;
		for (SimpleEvent evt : evtList) {
	
			eventList.add(evt.getKey());

			if (generateXLS) {
				Row row = sheet.createRow(rowNbr);
				Cell cell = row.createCell(0);
				cell.setCellValue((String) evt.getKey());
				Cell cell2 = row.createCell(1);
				cell2.setCellValue((String) evt.getName());
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(
						(String) String.format("Start: %s - End: %s", evt.getStart_date(), evt.getEnd_date()));
				rowNbr++;
			}
		}
		return eventList;
	}

	public List<String> getCurrentData() throws IOException {
		List<String> eventList = new ArrayList<String>();
		List<SimpleEvent> evtList = http.callListURL(SimpleEvent.class, teamEventListURL, teamNbr, year);
		int rowNbr = 0;
		for (SimpleEvent evt : evtList) {
			if (!ReportGenerator.checkStringDate(evt.getStart_date())) {
				continue;
			}
	
			eventList.add(evt.getKey());

			if (generateXLS) {
				Row row = sheet.createRow(rowNbr);
				Cell cell = row.createCell(0);
				cell.setCellValue((String) evt.getKey());
				Cell cell2 = row.createCell(1);
				cell2.setCellValue((String) evt.getName());
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(
						(String) String.format("Start: %s - End: %s", evt.getStart_date(), evt.getEnd_date()));
				rowNbr++;
			}
		}
		return eventList;
	}

}
