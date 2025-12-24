package com.folio3.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDataUtil {

    public static String generateUniqueSOPNumber() {
        return "SOP-" + System.currentTimeMillis();
    }

    public static String generateUniqueTitle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "Automation SOP " + LocalDateTime.now().format(formatter);
    }

    public static String generateUsername() {
        return "auto_user_" + System.currentTimeMillis();
    }
}
