package com.spring.main.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGenerator {

    public static String generateId() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
