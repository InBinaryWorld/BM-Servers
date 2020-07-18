package dev.szafraniak.bmresource.utils;

public class Regexps {
    private final static String UPPERCASE_LETTER = "[A-ZŻŹĆĄŚĘŁÓŃ]";
    private final static String LOWERCASE_LETTER = "[a-zżźćńółęąś]";
    private final static String LETTER = "[" + UPPERCASE_LETTER + LOWERCASE_LETTER + "]";
    private final static String ALLOWED_SIGNS = "[^\\t\\n\\r\\f\\x0B]";

    public final static String WORD_1_20 = LETTER + "{1,20}";
    public final static String WORDS = "( *" + LETTER + "+ *)+";
    public final static String BASE_2_40 = ALLOWED_SIGNS + "{2,40}";
    public final static String BASE_2_60 = ALLOWED_SIGNS + "{2,60}";

    public static final String PHONE_4_12 = "\\+?\\d{4,12}";
    public static final String BARCODE_5_20 = "\\S{5,20}";
    public final static String INVOICE_PREFIX_2_14 = "\\S{2,14}";
    public final static String HOUSE_NUMBER = "\\d{1,3}[A-Za-z]?";
    public final static String POSTAL_CODE = "\\d{2}-\\d{3}";
    public final static String CURRENCY = UPPERCASE_LETTER + "{2,4}";
    public final static String TAX_IDENTITY_NUMBER = "\\d{10}";
}