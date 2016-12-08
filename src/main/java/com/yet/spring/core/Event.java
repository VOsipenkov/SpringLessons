package com.yet.spring.core;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

	public static Random random = new Random(47);
	private int id;
	private String message;
	private Date date;
	private DateFormat df;

	public void setMessage(String message, DateFormat df) {
		this.message = message;
		id = random.nextInt(1000);
		this.df = df;
	}

	public Event(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return message;
	}
}
