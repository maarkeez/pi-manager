package com.pi.systemmetrics.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtils {
  
  public static double roundWithTwoDecimals ( double value ) {
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
