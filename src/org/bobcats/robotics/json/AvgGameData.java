package org.bobcats.robotics.json;

public class AvgGameData {
	private int nbrGames = 0;
	private String teamnbr;
	private double autoPoints = 0.0;
	private double cargoPoints = 0.0;
	private double rocketRP = 0.0;
	private double habClimbPoints = 0.0;
	private double habitatRP = 0.0;
	private double rp = 0.0;
	private double sandstormBonusPoints = 0.0;
	private double teleopPoints = 0.0;
	private double totalPoints = 0.0;

	private AvgGameData() {
		super();
	}

	public AvgGameData(String team) {
		this();
		teamnbr = team;
	}
	
	public void addScores(Scores us/*,Scores them */) {
		nbrGames++;
		autoPoints += us.getAutoPoints();
		cargoPoints += us.getCargoPoints();
		rocketRP += ("true".equals(us.getCompleteRocketRankingPoint()) ? 1.0 : 0.0);
		habClimbPoints += us.getHabClimbPoints();
		habitatRP += ("true".equals(us.getHabDockingRankingPoint()) ? 1.0 : 0.0);
		rp += us.getRp();
		sandstormBonusPoints += us.getSandStormBonusPoints();
		teleopPoints += us.getTeleopPoints();
		totalPoints += us.getTotalPoints();
	}

	public int getNbrGames() {
		return nbrGames;
	}

	public String getTeamnbr() {
		return teamnbr;
	}


	public double getTotalPoints() {
		return this.totalPoints;
	}
	

	public double getAutoPoints() {
		return this.autoPoints;
	}

	public double getCargoPoints() {
		return this.cargoPoints;
	}

	public double getRocketRP() {
		return this.rocketRP;
	}

	public double getHabClimbPoints() {
		return this.habClimbPoints;
	}

	public double getHabitatRP() {
		return this.habitatRP;
	}

	public double getRp() {
		return this.rp;
	}

	public double getSandstormBonusPoints() {
		return this.sandstormBonusPoints;
	}

	public double getTeleopPoints() {
		return this.teleopPoints;
	}

}
