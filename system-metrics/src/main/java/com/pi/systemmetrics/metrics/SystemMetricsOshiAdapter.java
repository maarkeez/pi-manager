package com.pi.systemmetrics.metrics;

import com.pi.systemmetrics.api.SystemMetricsAdapter;
import com.pi.systemmetrics.model.CpuUsage;
import com.pi.systemmetrics.model.FileSystemVolume;
import com.pi.systemmetrics.model.MemoryUsage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor( onConstructor = @__( @Autowired ) )
class SystemMetricsOshiAdapter implements SystemMetricsAdapter {
  
  private final CpuOshiAdapter cpuOshiAdapter;
  private final MemoryOshiAdapter memoryOshiAdapter;
  private final FileSystemOshiAdapter fileSystemOshiAdapter;
  
  @Override
  public CpuUsage measureCpuUsage () {
    return cpuOshiAdapter.measureCpuUsage();
  }
  
  @Override
  public MemoryUsage measureMemoryUsage () {
    return memoryOshiAdapter.measureMemoryUsage();
  }
  
  @Override
  public List<FileSystemVolume> measureFileSystem () {
    return fileSystemOshiAdapter.measureFileSystem();
  }
  
}
