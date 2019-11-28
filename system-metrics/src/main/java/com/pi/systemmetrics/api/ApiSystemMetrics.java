package com.pi.systemmetrics.api;

import com.pi.systemmetrics.model.CpuUsage;
import com.pi.systemmetrics.model.MemoryUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api/system/metrics/" )
@RequiredArgsConstructor( onConstructor = @__( @Autowired ) )
public class ApiSystemMetrics {
  
  private final SystemMetricsAdapter systemMetricsAdapter;
  
  @GetMapping( "cpu" )
  public CpuUsage measureCpuUsage () {
    return systemMetricsAdapter.measureCpuUsage();
  }
  
  @GetMapping( "memory" )
  public MemoryUsage measureMemoryUsage () {
    return systemMetricsAdapter.measureMemoryUsage();
  }
}
