package com.pi.systemmetrics.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CpuUsage {
  
  private double idle;
  private double total;
}
