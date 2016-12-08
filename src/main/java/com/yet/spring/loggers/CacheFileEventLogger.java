package com.yet.spring.loggers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.yet.spring.core.Event;

public class CacheFileEventLogger extends FileEventLogger {

	private int cacheSize;
	private List<Event> cache;
	private String fileName;

	public CacheFileEventLogger(int cacheSize, String fileName) {
		super(fileName);
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
			try {
				FileUtils.writeStringToFile(new File(fileName), event.toString(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void destroy() {
		if (cache != null && !cache.isEmpty() && new File(fileName).canWrite()) {
			writeEventsFormCache();
		}
	}
}
