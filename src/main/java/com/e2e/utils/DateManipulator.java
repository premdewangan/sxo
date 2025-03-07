package com.e2e.utils;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateManipulator {

	public static String formatDate(String inputDate, String inputDateFormat, String outputDateFormat)
			throws IOException, ParseException, InterruptedException {
		SimpleDateFormat DateFormat = new SimpleDateFormat(outputDateFormat);

		// Initializing the date Object
		Date newDateObject = convertToDateObject(inputDate, inputDateFormat);

		// Using format() method for conversion
		String formattedDate = DateFormat.format(newDateObject);
		System.out.println("Formatted Date: " + formattedDate);

		return formattedDate;
	}

	public static Date convertToDateObject(String inputDateString, String inputDateFormat)
			throws IOException, ParseException, InterruptedException {
		Date dateObject = new SimpleDateFormat(inputDateFormat).parse(inputDateString);
		return dateObject;
	}

	public static String convertDateToString(Date inputDateObject, String inputFormat) {
		SimpleDateFormat DateFormat = new SimpleDateFormat(inputFormat);
		String newDateString = DateFormat.format(inputDateObject);
		return newDateString;
	}

	public static String addDaystoDate(String inputDate, String inputDateFormat, int daysToAdd) {

		// create instance of the SimpleDateFormat that matches the given date
		SimpleDateFormat dateFormat = new SimpleDateFormat(inputDateFormat);

		// create instance of the Calendar class and set the date to the given date
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(dateFormat.parse(inputDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// use add() method to add the days to the input date
		cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
		String dateAfter = dateFormat.format(cal.getTime());

		// date after adding days to the given date
		return dateAfter;
	}

	public static Date getCurrentDate(String outputFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
		Date currentDate = new Date();
		return currentDate;
	}
}
