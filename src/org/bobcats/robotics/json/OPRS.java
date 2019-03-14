package org.bobcats.robotics.json;

import java.util.Map;

public class OPRS {
	private Map<String,Double> ccwms;
	private Map<String,Double> dprs;
	private Map<String,Double> oprs;
	
	public double[] getStrength(String team) {
		double [] stg = { 0.0, 0.0, 0.0 };
		if (ccwms.get(team) != null) 
			stg[0] = ccwms.get(team);
		if (dprs.get(team) != null)
			stg[1] = dprs.get(team);
		if (oprs.get(team) != null)
			stg[2] = oprs.get(team);
		return stg;
	}
	
	
	@Override
	public String toString() {
		return "OPRS [ccwms=" + ccwms + ", dprs=" + dprs + ", oprs=" + oprs + "]";
	}
	
	

}
