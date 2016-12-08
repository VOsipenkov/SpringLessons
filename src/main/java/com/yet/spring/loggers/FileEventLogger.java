package com.yet.spring.loggers;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.yet.spring.core.Event;

public class FileEventLogger implements EventLogger {
	private String fileName;

	public FileEventLogger(String fileName) {
		this.fileName = fileName;
	}

	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile(new File(fileName), event.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws IOException {
		File file = new File(fileName);

		if (!file.exists()) {
			file.mkdir();
		}

		if (!file.canWrite()) {
			throw new IOException();
		}
	}
}
