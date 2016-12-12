package com.yet.spring.loggers;

import java.util.ArrayList;
import java.util.List;
import com.yet.spring.core.Event;

public class CacheFileEventLogger extends FileEventLogger {

	private int cacheSize;
	private List<Event> cache = new ArrayList<Event>();

	public CacheFileEventLogger(int cacheSize, String fileName) {
		super(fileName);
		this.cacheSize = cacheSize;
	}

	public void logEvent(Event event) {
		cache.add(event);

		if (cache.size() > cacheSize) {
			writeEventsFormCache();
			cache.clear();
		}
	}

	private void writeEventsFormCache() {
		for (Event event : cache) {
			super.logEvent(event);
		}
	}

	private void destroy() {
		if (cache != null && !cache.isEmpty()) {
			writeEventsFormCache();
		}
	}
}
