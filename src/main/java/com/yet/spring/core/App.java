package com.yet.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	static Client client;
	static EventLogger logger;

	public static void main(String[] args) {
		ApplicationContext contex = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) contex.getBean("app");
		app.logEvent("log event from 1");
		app.logEvent("log event from 2");
	}

	public App(Client client, EventLogger logger) {
		this.client = client;
		this.logger = logger;
	}

	private void logEvent(String message) {
		String fixedMessage = message.replaceAll(client.getId(), client.getName());
		logger.logEvent(fixedMessage);
	}
}
