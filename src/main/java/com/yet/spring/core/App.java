package com.yet.spring.core;

import java.sql.Date;
import java.text.DateFormat;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yet.spring.loggers.EventLogger;

public class App {
	static Client client;
	static EventLogger logger;

	public static void main(String[] args) {
		ConfigurableApplicationContext contex = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) contex.getBean("app");
		app.logEvent("log event from 1 \n");
		app.logEvent("log event from 2 \n");

		contex.close();
	}

	public App(Client client, EventLogger logger) {
		this.client = client;
		this.logger = logger;
	}

	private void logEvent(String message) {
		String fixedMessage = message.replaceAll(client.getId(), client.getName());
		Date currentDate = new Date(System.currentTimeMillis());
		Event event = new Event(currentDate);
		DateFormat df = DateFormat.getDateInstance();
		event.setMessage(fixedMessage, df);

		logger.logEvent(event);
	}
}
