package com.yet.spring.loggers;

import java.util.List;
import com.yet.spring.core.Event;

public class CombinedEventLogger implements EventLogger {

	private List<EventLogger> loggers;

	public CombinedEventLogger(List<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (EventLogger loggerItem : loggers) {
			loggerItem.logEvent(event);
		}
	}
}
