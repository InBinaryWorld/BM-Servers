package dev.szafraniak.bmresource.utils;

public class Regexps {
    private final static String UPPERCASE_LETTER = "[A-ZŻŹĆĄŚĘŁÓŃ]";
    private final static String LOWERCASE_LETTER = "[a-zżźćńółęąś]";
    private final static String LETTER = "[" + UPPERCASE_LETTER + LOWERCASE_LETTER + "]";
    private final static String LETTER_OR_NUMBER = "[0-9" + LETTER + "]";
    private final static String WITHOUT_BLANK_WITH_SPACE = "[^\\t\\n\\r\\f\\x0B]";

    public static final String BARCODE_5_20 = "\\S{5,20}";
    public final static String NUMBERS_10 = "\\d{10}";
    public final static String WORD_1_20 = LETTER + "{1,20}";
    public final static String WORDS = LETTER + "+( +" + LETTER + "+)*";
    public final static String WORDS_WITH_NUMBERS = LETTER_OR_NUMBER + "+( *" + LETTER_OR_NUMBER + "+)*";
    public final static String CURRENCY = UPPERCASE_LETTER + "{2,4}";
    public final static String POSTAL_CODE = "\\d{2}-\\d{3}";
    public final static String HOUSE_NUMBER = "\\d{1,3}[A-Za-z]?";
    public final static String INVOICE_PREFIX_2_14 = "\\S{2,14}";
    public final static String ALMOST_ALL_CHARACTERS = WITHOUT_BLANK_WITH_SPACE + "+";

}