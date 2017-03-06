package com.computerDatabase.entryUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.computerDatabase.controller.Controller;

public class DateUtils {

  /**
   * Method to convert a string to LocalDate .
   * @param dateIn : dateIn
   * @return LocalDate
   */
  public static LocalDate convertDate(String dateIn) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateOut;
    try {
      dateOut = LocalDate.parse(dateIn, formatter);
    } catch (DateTimeParseException e) {
      System.out.println("Le format de la date n'est pas respecté ! (yyyy-MM-dd)");
      dateOut = null;
    }
    return dateOut;
  }

  public static String convertToString(LocalDate dateIn) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
    String dateOut = dateIn.format(formatter);
    return dateOut;
  }

  public static Date convertToSQLDate(String dateIn) {
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date parsed = new java.util.Date();
    try {
      parsed = date.parse(dateIn);

    } catch (ParseException e) {
      e.getStackTrace();
    }
    Date dateOut = new Date(parsed.getTime());
    return dateOut;
  }

}
