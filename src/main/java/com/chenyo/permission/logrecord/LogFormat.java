package com.chenyo.permission.logrecord;

import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormat extends Formatter {
    public String format(LogRecord record) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return record.getLoggerName() + " " + record.getLevel() + " : " + format.format(record.getMillis())
                + " " + record.getMessage() + "\n";
    }
}
