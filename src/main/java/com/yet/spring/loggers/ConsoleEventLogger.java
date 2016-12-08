package com.yet.spring.loggers;

import com.yet.spring.core.Event;

public class ConsoleEventLogger implements EventLogger {

	public void logEvent(Event event) {
		System.out.println(event);
	}
}
