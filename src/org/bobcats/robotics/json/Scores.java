package org.bobcats.robotics.json;

public class Scores {
	private int adjustPoints;
	private int autoPoints;
	private String bay1; // "None", "Panel" "PanelAndCargo",
	private String bay2;
	private String bay3;
	private String bay4;
	private String bay5;
	private String bay6;
	private String bay7;
	private String bay8;
	private int cargoPoints;
	private String completeRocketRankingPoint; // true or false
	private String completedRocketFar; // true or false
	private String completedRocketNear; // true or false
	private String endgameRobot1; // "HabLevel1, 2, or 3"
	private String endgameRobot2;
	private String endgameRobot3;
	private int foulCount;
	private int foulPoints;
	private int habClimbPoints;
	private String habDockingRankingPoint; // true or false
	private String habLineRobot1;  // "CrossedHabLineInSandstorm",
	private String habLineRobot2;
	private String habLineRobot3;
	private int hatchPanelPoints;
	private String lowLeftRocketFar; // "None", "Panel" "PanelAndCargo",
	private String lowLeftRocketNear;
	private String lowRightRocketFar;
	private String lowRightRocketNear;
	private String midLeftRocketFar; // "None", "Panel" "PanelAndCargo",
	private String midLeftRocketNear;
	private String midRightRocketFar;
	private String midRightRocketNear;
	private String preMatchBay1; // "None", "Panel" "PanelAndCargo",
	private String preMatchBay2;
	private String preMatchBay3;
	private String preMatchBay6;
	private String preMatchBay7;
	private String preMatchBay8;
	private String preMatchLevelRobot1; // "HabLevel1, 2, or 3"
	private String preMatchLevelRobot2;
	private String preMatchLevelRobot3;
	private int rp;
	private int sandStormBonusPoints;
	private int techFoulCount;
	private int teleopPoints;
	private String topLeftRocketFar; // "None", "Panel" "PanelAndCargo",
	private String topLeftRocketNear;
	private String topRightRocketFar;
	private String topRightRocketNear;
	private int totalPoints;


    public int getAutoPoints() {
        return this.autoPoints;
    }

    public int getCargoPoints() {
        return this.cargoPoints;
    }

    public String getCompleteRocketRankingPoint() {
        return this.completeRocketRankingPoint;
    }

    public int getHabClimbPoints() {
        return this.habClimbPoints;
    }

    public String getHabDockingRankingPoint() {
        return this.habDockingRankingPoint;
    }

    public int getRp() {
        return this.rp;
    }

    public int getSandStormBonusPoints() {
        return this.sandStormBonusPoints;
    }

    public int getTeleopPoints() {
        return this.teleopPoints;
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

	
	@Override
	public String toString() {
		return "{" +
			" adjustPoints='" + adjustPoints + "'" +
			", autoPoints='" + autoPoints + "'" +
			", bay1='" + bay1 + "'" +
			", bay2='" + bay2 + "'" +
			", bay3='" + bay3 + "'" +
			", bay4='" + bay4 + "'" +
			", bay5='" + bay5 + "'" +
			", bay6='" + bay6 + "'" +
			", bay7='" + bay7 + "'" +
			", bay8='" + bay8 + "'" +
			", cargoPoints='" + cargoPoints + "'" +
			", completeRocketRankingPoint='" + completeRocketRankingPoint + "'" +
			", completedRocketFar='" + completedRocketFar + "'" +
			", completedRocketNear='" + completedRocketNear + "'" +
			", endgameRobot1='" + endgameRobot1 + "'" +
			", endgameRobot2='" + endgameRobot2 + "'" +
			", endgameRobot3='" + endgameRobot3 + "'" +
			", foulCount='" + foulCount + "'" +
			", foulPoints='" + foulPoints + "'" +
			", habClimbPoints='" + habClimbPoints + "'" +
			", habDockingRankingPoint='" + habDockingRankingPoint + "'" +
			", habLineRobot1='" + habLineRobot1 + "'" +
			", habLineRobot2='" + habLineRobot2 + "'" +
			", habLineRobot3='" + habLineRobot3 + "'" +
			", hatchPanelPoints='" + hatchPanelPoints + "'" +
			", lowLeftRocketFar='" + lowLeftRocketFar + "'" +
			", lowLeftRocketNear='" + lowLeftRocketNear + "'" +
			", lowRightRocketFar='" + lowRightRocketFar + "'" +
			", lowRightRocketNear='" + lowRightRocketNear + "'" +
			", midLeftRocketFar='" + midLeftRocketFar + "'" +
			", midLeftRocketNear='" + midLeftRocketNear + "'" +
			", midRightRocketFar='" + midRightRocketFar + "'" +
			", midRightRocketNear='" + midRightRocketNear + "'" +
			", preMatchBay1='" + preMatchBay1 + "'" +
			", preMatchBay2='" + preMatchBay2 + "'" +
			", preMatchBay3='" + preMatchBay3 + "'" +
			", preMatchBay6='" + preMatchBay6 + "'" +
			", preMatchBay7='" + preMatchBay7 + "'" +
			", preMatchBay8='" + preMatchBay8 + "'" +
			", preMatchLevelRobot1='" + preMatchLevelRobot1 + "'" +
			", preMatchLevelRobot2='" + preMatchLevelRobot2 + "'" +
			", preMatchLevelRobot3='" + preMatchLevelRobot3 + "'" +
			", rp='" + rp + "'" +
			", sandStormBonusPoints='" + sandStormBonusPoints + "'" +
			", techFoulCount='" + techFoulCount + "'" +
			", teleopPoints='" + teleopPoints + "'" +
			", topLeftRocketFar='" + topLeftRocketFar + "'" +
			", topLeftRocketNear='" + topLeftRocketNear + "'" +
			", topRightRocketFar='" + topRightRocketFar + "'" +
			", topRightRocketNear='" + topRightRocketNear + "'" +
			", totalPoints='" + totalPoints + "'" +
			"}";
	}
}
