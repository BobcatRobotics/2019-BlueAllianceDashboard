package org.bobcats.robotics;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.bobcats.robotics.json.AvgGameData;
import org.bobcats.robotics.json.GameData;
import org.bobcats.robotics.json.OPRS;
import org.bobcats.robotics.json.Scores;
import org.bobcats.robotics.json.SimpleEvent;
import org.bobcats.robotics.json.Team;

public class Dashboard {

	private JFrame frame;
	private static HTTPSHandler http = new HTTPSHandler();

	// Specific Codes for the event and list
	// "event_code": "mabos",
	// "key": "2018mabos",
	//

	// API Calls
	private static String teamURL = "https://www.thebluealliance.com/api/v3/team/{1}";
	private static String eventsURL = "https://www.thebluealliance.com/api/v3/events/{1}/simple";
	private static String eventTeamURL = "https://www.thebluealliance.com/api/v3/event/{1}/teams";
	private static String eventTeamDataURL = "https://www.thebluealliance.com/api/v3/team/{1}/event/{2}/matches";
	private static String teamDistricsURL = "https://www.thebluealliance.com/api/v3/team/{1}/districts";
	private static String eventOprsURL = "https://www.thebluealliance.com/api/v3/event/{1}/oprs";
	private static String eventURL = "https://www.thebluealliance.com/api/v3/event/{1}/simple";
	// TODO: Use?
	private static String eventResultsURL = "https://www.thebluealliance.com/api/v3/team/{1}/event/{2}/status";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// generateXLSFilesOneTeam();
		generateXLSFiles();
		// generateTextFiles();

		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		// Dashboard window = new Dashboard();
		// window.frame.setVisible(true);
		// Team team177 = http.callURL(Team.class, teamURL);
		// System.out.println("Team 177 " + team177);
		//
		// List<SimpleEvent> eventList = http.callListURL(SimpleEvent.class, eventsURL,
		// "2019");
		// System.out.println("Events are " + eventList);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static void generateXLSFiles() {
		int TEST_TEAMS = 100;
		try {
			String event = "2019ctwat";

			XLSEventTeamList xlsReport = new XLSEventTeamList(event);
			xlsReport.createReport("teamlist.xlsx");
			List<String> teams = xlsReport.getData();
			xlsReport.closeReport();

			// Loop over each team, accumulate scores for avg score
			List<AvgGameData> avgReport = new ArrayList<AvgGameData>();
			int testCtr = 0;
			for (String teamKey : teams) {
				XLSTeamEventList xlsReport2 = new XLSTeamEventList(teamKey,false);
				//xlsReport2.createReport(xlsReport2.getReportName());
				List<String> eventKeys = xlsReport2.getCurrentData();
				//xlsReport2.closeReport();
				testCtr++;
				if (testCtr > TEST_TEAMS)
					break;
				// Loop over each event that a team has participated in
				AvgGameData avg = null;
				String teamShortKey = teamKey.substring(3);
				avg = new AvgGameData(teamShortKey);

				System.out.println("Processing team " + teamKey);
				for (String eventKey : eventKeys) {
					System.out.println("Processing event " + eventKey);
					List<GameData> matchResults = http.callListURL(GameData.class, eventTeamDataURL, teamKey, eventKey);
					for (GameData match : matchResults) {
						// Check if the match has occurred.
						if (!match.matchCompleted()) {
							continue;
						}
						match.getAllianceColor(teamKey);
						Scores sc = match.getTeamScores();
						avg.addScores(sc);
						// alliance = match.getAllianceColor(teamKey);
						// teamPosition = match.getAllianceTeamPos(teamKey);
						// System.out.println(match);
						// System.out.println(sc);
						// Scores scOpp = match.getOpponentsTeamScores();
						// avg.addScores(sc, scOpp);
					}
					// For all matches for an event, avg has all scores
				}
				// Now add all events for one team
				if (avg != null) {
					//System.out.println(String.format("Team - %s. Adding Avg %s", teamKey, avg));
					avgReport.add(avg);
				}
			}
			XLSMasterReport mst = new XLSMasterReport();
			mst.createReport("strengths.xlsx");
			mst.generateReport(avgReport);
			mst.closeReport();
			System.out.println("Completed processing championship event - " + event);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateXLSFilesOneTeam() {
		int TEST_TEAMS = 100;
		try {
			String event = "2019ctwat";
			String teamKey = "frc177";

			// Loop over each team, accumulate scores for avg score
			List<AvgGameData> avgReport = new ArrayList<AvgGameData>();

			XLSTeamEventList xlsReport2 = new XLSTeamEventList(teamKey,true);
			xlsReport2.createReport(xlsReport2.getReportName());
			List<String> eventKeys = xlsReport2.getFullData();
			xlsReport2.closeReport();

			// Loop over each event that a team has participated in
			AvgGameData avg = null;
			String teamShortKey = teamKey.substring(3);
			avg = new AvgGameData(teamShortKey);

			System.out.println("Processing team " + teamKey);
			for (String eventKey : eventKeys) {
				System.out.println("Processing event " + eventKey);
				List<GameData> matchResults = http.callListURL(GameData.class, eventTeamDataURL, teamKey, eventKey);
				for (GameData match : matchResults) {
					// Check if match was completed
					if (!match.matchCompleted()) {
						continue;
					}
					match.getAllianceColor(teamKey);
					Scores sc = match.getTeamScores();
					avg.addScores(sc);
					// alliance = match.getAllianceColor(teamKey);
					// teamPosition = match.getAllianceTeamPos(teamKey);
					// System.out.println(match);
					// System.out.println(sc);
					// Scores scOpp = match.getOpponentsTeamScores();
					// avg.addScores(sc, scOpp);
				}
				// For all matches for an event, avg has all scores
			}
			// Now add all events for one team
			if (avg != null) {
				//System.out.println(String.format("Team - %s. Adding Avg %s", teamKey, avg));
				avgReport.add(avg);
			}
			XLSMasterReport mst = new XLSMasterReport();
			mst.createReport("strengths.xlsx");
			mst.generateReport(avgReport);
			mst.closeReport();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateTextFiles() {
		try {
			String teamKey = "frc177";
			Team team177 = http.callURL(Team.class, teamURL, teamKey);

			ReportGenerator rpt = new ReportGenerator();
			ReportGenerator.setTeamNbr(teamKey);
			ReportGenerator.resetLog();
			ReportGenerator.log(String.format("Team - %s %s", team177.getNickname(), team177.getName()));

			String eventKey = "2018necmp";
			SimpleEvent event = http.callURL(SimpleEvent.class, eventURL, eventKey);
			ReportGenerator.log(String.format("Event - %s   Date - %s", event.getName(), event.getStart_date()));

			OPRS oprs = http.callURL(OPRS.class, eventOprsURL, eventKey);
			double[] ranks = oprs.getStrength(teamKey);
			ReportGenerator.log(" ");
			ReportGenerator.log("Event OPRS");
			ReportGenerator.log("----------");
			ReportGenerator.log(String.format("OPRS - %5.2f", ranks[2]));
			ReportGenerator.log(String.format("DPRS - %5.2f", ranks[1]));
			ReportGenerator.log(String.format("CCWMS- %5.2f", ranks[0]));

			ReportGenerator.log(" ");
			ReportGenerator.log("Event Matches");
			// ReportGenerator.log("-------------");
			// ReportGenerator.log("");
			ReportGenerator.log("      --Auto--   -Teleop-  -Opp Tele-                    --- RP ---");
			ReportGenerator.log("Event  SCA SWT    SCA SWT    SCA SWT   Vault   Endgame   Auto  Boss    ---Status---");

			String matchStr = "%-6s  %2d  %2d    %3d %3d   %3d  %3d     %2d    %8s  %4s %4s    %12s";
			List<GameData> matchResults = http.callListURL(GameData.class, eventTeamDataURL, teamKey, eventKey);
			AvgGameData avg = new AvgGameData(teamKey);
			String alliance = "";
			int teamPosition = -1;
			for (GameData match : matchResults) {
				// System.out.println(match);
				alliance = match.getAllianceColor(teamKey);
				teamPosition = match.getAllianceTeamPos(teamKey);
				Scores sc = match.getTeamScores();
				Scores scOpp = match.getOpponentsTeamScores();
				avg.addScores(sc);
				// ReportGenerator.log(String.format(matchStr, match.getShortName(),
				// sc.getAutoScaleOwnershipSec(),
				// sc.getAutoSwitchOwnershipSec(), sc.getTeleopScaleOwnershipSec(),
				// sc.getTeleopSwitchOwnershipSec(), scOpp.getTeleopScaleOwnershipSec(),
				// scOpp.getTeleopSwitchOwnershipSec(), sc.getVaultPoints(),
				// sc.endGameStatus(teamPosition),
				// sc.getAutoQuestRankingPoint(), sc.getFaceTheBossRankingPoint(),
				// match.getMatchStatus()));
			}
			ReportGenerator.log(" ");
			ReportGenerator.log("Match Average Scores");
			ReportGenerator.log("        --Auto--      -Teleop-      -Opp Tele- ");
			ReportGenerator.log("         SCA  SWT     SCA   SWT      SCA   SWT      Vault");
			// ReportGenerator.log(avg.averageString());

			// String year = "2018";
			// List<SimpleEvent> eventList = httphand.callListURL(SimpleEvent.class,
			// eventsURL, year);
			// for (SimpleEvent evt : eventList) {
			// if ("USA".equals(evt.getCountry()) && year.equals(evt.getYear())) {
			// System.out.println("USA Event is " + evt.toShortString());
			// } else {
			// System.out.println(evt.toShortString());
			// }
			//
			// }
			//
			// String eventKey = "2018necmp";
			// List<Team> eventTeams = httphand.callListURL(Team.class, eventTeamURL,
			// eventKey);
			// for (Team team: eventTeams) {
			// System.out.println("Team - " + team);
			// }
			//
			//
			// List<GameData> matchResults = httphand.callListURL(GameData.class,
			// eventTeamDataURL, teamKey, eventKey);
			// for (GameData match : matchResults) {
			// System.out.println("match - " + match);
			// }
			//
			// String currentDistrict = "";
			// List<District> districts = httphand.callListURL(District.class,
			// teamDistricsURL, teamKey);
			// for (District dist : districts) {
			// System.out.println("dist - " + dist);
			// if (dist.getYear() == 2018) {
			// currentDistrict = dist.getKey();
			// }
			// }
			// System.out.println(String.format("Current District for team %s is
			// %s",teamKey,currentDistrict));
			//
			// OPRS oprs = httphand.callURL(OPRS.class, eventOprsURL,eventKey);
			// System.out.println("oprs - " + oprs.toString());
			// double [] ranks = oprs.getStrength(teamKey);
			// System.out.println("ccwms " + ranks[0]);
			// System.out.println("dprs " + ranks[1]);
			// System.out.println("oprs " + ranks[2]);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
