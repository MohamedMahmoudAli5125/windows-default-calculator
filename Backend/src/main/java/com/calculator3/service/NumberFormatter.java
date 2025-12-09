package com.calculator3.service;

import java.text.DecimalFormat;

public class NumberFormatter {

    public static String format(double value) {
        DecimalFormat df;
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            df = new DecimalFormat("#,###");
        } else {
            df = new DecimalFormat("#,###.##########");
        }
        return df.format(value);
    }
}