package dev.szafraniak.bmresource.config;

import java.util.ArrayList;
import java.util.List;

public final class BaseEnvironment {

    public static List<String> QUANTITY_UNITS_PRODUCT_IDS = new ArrayList<>() {{
        add("piece");
        add("hour");
        add("meter");
    }};

    public static List<String> QUANTITY_UNITS_SERVICE_IDS = new ArrayList<>() {{
        add("piece");
        add("hour");
        add("meter");
    }};

}
