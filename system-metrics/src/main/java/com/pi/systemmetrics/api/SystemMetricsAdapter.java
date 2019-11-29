package com.pi.systemmetrics.api;

import com.pi.systemmetrics.model.CpuUsage;
import com.pi.systemmetrics.model.FileSystemVolume;
import com.pi.systemmetrics.model.MemoryUsage;
import java.util.List;

public interface SystemMetricsAdapter {
  
  CpuUsage measureCpuUsage ();
  
  MemoryUsage measureMemoryUsage ();
  
  List<FileSystemVolume> measureFileSystem ();
}
