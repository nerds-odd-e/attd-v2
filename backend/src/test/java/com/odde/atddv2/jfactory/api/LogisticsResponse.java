package com.odde.atddv2.jfactory.api;

import java.util.ArrayList;
import java.util.List;

public class LogisticsResponse {
    public int status;
    public String msg;
    public LogisticsResult result;

    public static class LogisticsResult {
        public String number, type, typename, logo;
        public int deliverystatus, issign;
        public List<LogisticsItem> list = new ArrayList<>();
    }

    public static class LogisticsItem {
        public String time, status;
    }
}
