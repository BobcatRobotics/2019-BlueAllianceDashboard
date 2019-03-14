package org.bobcats.robotics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSReportGenerator {
	// Not a good design but quick - HTTP URL's
	protected static String eventTeamURL = "https://www.thebluealliance.com/api/v3/event/{1}/teams";
	protected static String eventURL = "https://www.thebluealliance.com/api/v3/event/{1}/simple";
	protected static String teamEventListURL = "https://www.thebluealliance.com/api/v3/team/{1}/events/{2}/simple";

	// private static String teamNbr = "";
	private static String path = File.separator + "FIRST" + File.separator + "xls" + File.separator;
	private String filename = path + "default.txt";
	protected XSSFWorkbook workbook = null;
	protected XSSFSheet sheet = null;
	protected static HTTPSHandler http = new HTTPSHandler();

	static {
		File newDir = new File(path);
		if (!newDir.exists()) {
			try {
				newDir.mkdir();
			} catch (SecurityException e) {
				String err = "XLSReportGenerator Security exception " + e;
				System.out.println(err);
			}
		}
	}

	public void createReport(String xlsName) {
		createReport(xlsName,"Report");
	}

	public void createReport(String xlsName,String sheetName) {
		filename = path + xlsName;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet(sheetName);
	}
	
	public void closeReport() {
		try {
			FileOutputStream outputStream = new FileOutputStream(filename);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			String err = "XLSReportGenerator FileNotFoundException " + e;
			System.out.println(err);
		} catch (IOException e) {
			String err = "XLSReportGenerator IOException " + e;
			System.out.println(err);
		}

	}

}
