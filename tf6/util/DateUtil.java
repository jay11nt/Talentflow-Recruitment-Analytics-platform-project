package com.tr.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static boolean isWeekday(LocalDate date) {
        return date.getDayOfWeek().getValue() <= 5;
    }
    
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}
