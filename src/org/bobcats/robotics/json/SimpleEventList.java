package org.bobcats.robotics.json;

import java.util.ArrayList;
import java.util.List;

public class SimpleEventList {
	private List<SimpleEvent> events = new ArrayList<SimpleEvent>();

	@Override
	public String toString() {
		return "SimpleEventList [events=" + events + "]";
	}
}
