package org.bobcats.robotics;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.bobcats.robotics.json.AvgGameData;
import org.bobcats.robotics.sort.AvgCompare;

public class XLSMasterReport extends XLSReportGenerator {
	private static final String reportName = "teamlist.xlsx";
	//private String eventName = "2018dar";

	public XLSMasterReport() {
		createReport(reportName, "strength");
	}

	// public XLSMasterReport(String eventName) {
	// super();
	// this.eventName = eventName;
	// }

	public int generateReport(List<AvgGameData> averages) throws IOException {
		int nbrTeams = 0;
		generateHeaderRow();

		int row = 1;
		Collections.sort(averages, new AvgCompare());
		for (AvgGameData avg : averages) {
			//System.out.println("Avg - " + avg.getTeamnbr() + " " + avg.toString());
			Row detailRow = sheet.createRow(row);
			int col = 0;
			Cell c1 = detailRow.createCell(col++);
			c1.setCellValue((String) avg.getTeamnbr());

			Cell c2 = detailRow.createCell(col++);
			//c2.setCellValue((Double) avg.getTotalPts());
			c2.setCellValue((Double) fmt(avg.getTotalPoints()));

			Cell c6 = detailRow.createCell(col++);
			c6.setCellValue((Double) fmt(avg.getRp()));

			Cell c3 = detailRow.createCell(col++);
			c3.setCellValue((Double) fmt(avg.getAutoPoints()));

			Cell c4 = detailRow.createCell(col++);
			c4.setCellValue((Double) fmt(avg.getCargoPoints()));

			Cell c5 = detailRow.createCell(col++);
			c5.setCellValue((Double) fmt(avg.getHabClimbPoints()));

			Cell c7 = detailRow.createCell(col++);
			c7.setCellValue((Double) fmt(avg.getHabitatRP()));

			Cell c8 = detailRow.createCell(col++);
			c8.setCellValue((Double) fmt(avg.getRocketRP()));

			Cell c10 = detailRow.createCell(col++);
			c10.setCellValue((Double) fmt(avg.getTeleopPoints()));

			Cell c11 = detailRow.createCell(col++);
			c11.setCellValue((Double) fmt(avg.getTotalPoints()));

			// Cell c13 = detailRow.createCell(col++);
			// c13.setCellValue((Double) fmt(avg.getFouls()));

			// Cell c14 = detailRow.createCell(col++);
			// c14.setCellValue((Double) fmt(avg.getRp()));

			// Cell c15 = detailRow.createCell(col++);
			// c15.setCellValue((Double) fmt(avg.getTechfoul()));

			// Cell c16 = detailRow.createCell(col++);
			// c16.setCellValue((Double) fmt(avg.getTeleopownpts()));

			// Cell c17 = detailRow.createCell(col++);
			// c17.setCellValue((Double) fmt(avg.getTeleoppts()));

			// Cell c18 = detailRow.createCell(col++);
			// c18.setCellValue((Double) fmt(avg.getTeleopsc()));

			// Cell c19 = detailRow.createCell(col++);
			// c19.setCellValue((Double) fmt(avg.getTeleopsw()));

			// Cell c20 = detailRow.createCell(col++);
			// c20.setCellValue((Double) fmt(avg.getTotalPts()));

			// Cell c21 = detailRow.createCell(col++);
			// c21.setCellValue((Double) fmt(avg.getVault()));
			row++;
			nbrTeams++;
		}
		return nbrTeams;
	}

	private void generateHeaderRow() throws IOException {
		String[] headers = { "Team", "Final Score",  "Ranking Points", "Auto Points", "Cargo Points",
				"Habitat Points ",  "Habitat RP", "Rocket RP",
				"Teleop Points", "Total Points" };
		Row headerRow = sheet.createRow(0);
		int colNbr = 0;
		Cell hdrCell;
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		font.setFontHeightInPoints((short) 10);
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setRotation((short) 90);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		for (String hdr : headers) {
			hdrCell = headerRow.createCell(colNbr);
			hdrCell.setCellStyle(cellStyle);
			hdrCell.setCellValue((String) hdr);
			colNbr++;
		}
	}

	private double fmt(double value) {
		int newVal = (int) (value * 100.0);
		return (double) (newVal / 100.0);
	}
}
