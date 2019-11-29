package com.pi.systemmetrics.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileSystemVolume {
  
  private String name;
  private String usedValue;
  private String totalValue;
  private double used;
  private double total;
  
}
