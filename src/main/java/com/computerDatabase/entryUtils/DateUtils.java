package com.computerDatabase.entryUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.computerDatabase.Controller.Controller;

public class DateUtils {

  public static LocalDate convertDate(String dateIn) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate dateOut;
    try {
      dateOut = LocalDate.parse(dateIn, formatter);
    } catch (DateTimeParseException e) {
      System.out.println("Le format de la date n'est pas respecté ! (yyyy-MM-dd)");
      Controller.menu();
      dateOut = null;
    }

    return dateOut;

  }

  public static boolean compareDate(LocalDate introduced, LocalDate discontinued) {

   return introduced.isBefore(discontinued);

  }

}
