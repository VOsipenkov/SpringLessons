package com.yet.spring.loggers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.yet.spring.core.Event;

public class CacheFileEventLogger extends FileEventLogger {

	private int cacheSize;
	private List<Event> cache = new ArrayList<Event>();
	private String fileName;

	public CacheFileEventLogger(int cacheSize, String fileName) {
		super(fileName);
		this.fileName = fileName;
		this.cacheSize = cacheSize;
	}

	public void logEvent(Event event) {
		cache.add(event);

		if (cache.size() > cacheSize) {
			if (new File(fileName).canWrite()) {
				writeEventsFormCache();
				cache.clear();
			} else {
				// TODO error message
			}
		}
	}

	private void writeEventsFormCache() {
		for (Event event : cache) {
			super.logEvent(event);
		}
	}

	private void destroy() {
		if (cache != null && !cache.isEmpty() && new File(fileName).canWrite()) {
			writeEventsFormCache();
		}
	}
}
