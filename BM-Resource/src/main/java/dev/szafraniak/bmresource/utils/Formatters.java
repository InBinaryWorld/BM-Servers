package dev.szafraniak.bmresource.utils;

import dev.szafraniak.bmresource.config.BaseEnvironment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Formatters {

    private final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(BaseEnvironment.DATE_PATTERN);
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(BaseEnvironment.DATE_TIME_PATTERN);


    public static String formatTaxRate(BigDecimal number) {
        return number.toString() + "%";
    }

    public static String formatDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(dateFormat);
    }

    public static String formatDate(LocalDate date) {
        return date.format(dateFormat);
    }

    public static String formatDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(dateTimeFormat);
    }

    public static String formatPrice(BigDecimal number, String suffix) {
        return formatPrice(number) + " " + suffix;
    }

    public static String formatPrice(BigDecimal number) {
        DecimalFormat decimalFormat = new DecimalFormat(BaseEnvironment.BASE_DECIMAL_PATTERN);
        DecimalFormatSymbols sym = decimalFormat.getDecimalFormatSymbols();
        sym.setGroupingSeparator(' ');
        decimalFormat.setDecimalFormatSymbols(sym);
        return decimalFormat.format(number);
    }
}
