package com.pi.systemmetrics.api;

import com.pi.systemmetrics.model.CpuUsage;
import com.pi.systemmetrics.model.MemoryUsage;

public interface SystemMetricsAdapter {
  
  CpuUsage measureCpuUsage ();
  
  MemoryUsage measureMemoryUsage ();
}
