package org.bobcats.robotics.sort;

import java.util.Comparator;

import org.bobcats.robotics.json.AvgGameData;

public class AvgCompare implements Comparator<AvgGameData> {

	@Override
	public int compare(AvgGameData o1, AvgGameData o2) {
		if (o1 == null || o2 == null) {
			return 0;
		}
		if (o1.equals(o2))
			return 0;
		int result = (int)(o2.getRp() - o1.getRp());
		if (result == 0) {
			result = (int)(o2.getTotalPoints() - o1.getTotalPoints());
		}
		return result;
	}
}
