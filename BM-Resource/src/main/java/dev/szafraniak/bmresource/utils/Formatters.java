package dev.szafraniak.bmresource.utils;

import dev.szafraniak.bmresource.config.BaseEnvironment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Formatters {

    private final static DecimalFormat dfPrice = getDecimalFormatPrice();
    private final static DecimalFormat dfFraction = getDecimalFormatWithFraction();
    private final static DecimalFormat dfNoFraction = getDecimalFormatNoFraction();
    private final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(BaseEnvironment.DATE_PATTERN);

    public static String formatTaxRate(BigDecimal number) {
        return number.toString() + "%";
    }

    public static String formatDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(dateFormat);
    }

    public static String formatDate(LocalDate date) {
        return date.format(dateFormat);
    }

    public static String formatPrice(BigDecimal number, String suffix) {
        return String.format("%s %s", formatPrice(number), suffix);
    }

    public static String formatPrice(BigDecimal number) {
        return dfPrice.format(number);
    }

    public static String formatDecimal(BigDecimal number) {
        return dfFraction.format(number);
    }

    public static String formatInteger(BigDecimal number) {
        return dfNoFraction.format(number);
    }

    private static DecimalFormat getBaseDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(BaseEnvironment.DECIMAL_SEPARATOR);
        symbols.setGroupingSeparator(BaseEnvironment.GROUPING_SEPARATOR);

        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(symbols);
        return format;
    }

    private static DecimalFormat getDecimalFormatWithFraction() {
        DecimalFormat format = getBaseDecimalFormat();
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(Integer.MAX_VALUE);
        return format;
    }

    private static DecimalFormat getDecimalFormatPrice() {
        DecimalFormat format = getBaseDecimalFormat();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        return format;
    }

    private static DecimalFormat getDecimalFormatNoFraction() {
        DecimalFormat format = getBaseDecimalFormat();
        format.setMaximumFractionDigits(0);
        return format;
    }
}
