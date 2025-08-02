package com.smartlibrary.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static long calculateFine(LocalDate issueDate, LocalDate returnDate, int allowedDays) {
        long daysLate = ChronoUnit.DAYS.between(issueDate.plusDays(allowedDays), returnDate);
        return daysLate > 0 ? daysLate * 5 : 0; // ₹5 fine per day
    }
}
