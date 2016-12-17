package com.yet.spring.core;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Map;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yet.spring.loggers.EventLogger;

public class App {
	private static Client client;
	private static Map<EventType, EventLogger> mapLoggers;
	private static EventLogger defaultLogger;

	public static void main(String[] args) {
		ConfigurableApplicationContext contex = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) contex.getBean("app");
		app.logEvent("log event from 1 \n", EventType.ERROR);
		app.logEvent("log event from 2 \n", EventType.INFO);
		app.logEvent("log event from 3 \n", EventType.ERROR);

		contex.close();
	}

	public App(Client client, EventLogger defauleLogger, Map<EventType, EventLogger> mapLoggers) {
		this.client = client;
		this.mapLoggers = mapLoggers;
		this.defaultLogger = defauleLogger;
	}

	private void logEvent(String message, EventType eventType) {
		String fixedMessage = message.replaceAll(client.getId(), client.getName());
		Date currentDate = new Date(System.currentTimeMillis());
		Event event = new Event(currentDate);
		DateFormat df = DateFormat.getDateInstance();
		event.setMessage(fixedMessage, df);

		EventLogger logger = mapLoggers.get(eventType);
		if (logger == null) {
			logger = defaultLogger;
		}

		logger.logEvent(event);
	}
}
