package com.pi.systemmetrics.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoryUsage {
  
  private double total;
  private double used;
}
