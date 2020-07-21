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
    public final static String BASE_2_240 = ALLOWED_SIGNS + "{2,60}";

    public static final String BANK_ACCOUNT = "^(?:(?:IT|SM)\\d{2}[A-Z]\\d{22}|CY\\d{2}[A-Z]\\d{23}|NL\\d{2}[A-Z]{4}\\d{10}|LV\\d{2}[A-Z]{4}\\d{13}|(?:BG|BH|GB|IE)\\d{2}[A-Z]{4}\\d{14}|GI\\d{2}[A-Z]{4}\\d{15}|RO\\d{2}[A-Z]{4}\\d{16}|KW\\d{2}[A-Z]{4}\\d{22}|MT\\d{2}[A-Z]{4}\\d{23}|NO\\d{13}|(?:DK|FI|GL|FO)\\d{16}|MK\\d{17}|(?:AT|EE|KZ|LU|XK)\\d{18}|(?:BA|HR|LI|CH|CR)\\d{19}|(?:GE|DE|LT|ME|RS)\\d{20}|IL\\d{21}|(?:AD|CZ|ES|MD|SA)\\d{22}|PT\\d{23}|(?:BE|IS)\\d{24}|(?:FR|MR|MC)\\d{25}|(?:AL|DO|LB|PL)\\d{26}|(?:AZ|HU)\\d{27}|(?:GR|MU)\\d{28})$";
    public static final String PHONE_4_12 = "\\+?\\d{4,12}";
    public static final String BARCODE_5_20 = "\\S{5,20}";
    public final static String INVOICE_PREFIX_2_14 = "\\S{2,14}";
    public final static String HOUSE_NUMBER = "\\d{1,3}[A-Za-z]?";
    public final static String POSTAL_CODE = "\\d{2}-\\d{3}";
    public final static String CURRENCY = UPPERCASE_LETTER + "{2,4}";
    public final static String TAX_IDENTITY_NUMBER = "\\d{10}";
}