package com.pi.systemmetrics.metrics;

import com.pi.systemmetrics.api.SystemMetricsAdapter;
import com.pi.systemmetrics.model.CpuUsage;
import com.pi.systemmetrics.model.MemoryUsage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor.TickType;
import oshi.util.Util;

@Slf4j
@Component
class SystemMetricsOshiAdapter implements SystemMetricsAdapter {
  
  @Override
  public CpuUsage measureCpuUsage () {
    SystemInfo systemInfo = new SystemInfo();
    val hardware = systemInfo.getHardware();
    val processor = hardware.getProcessor();
    
    long[] prevTicks = processor.getSystemCpuLoadTicks();
    Util.sleep(1000);
    long[] ticks = processor.getSystemCpuLoadTicks();
    long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
    long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
    long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
    long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
    long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
    long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
    long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
    long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
    long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
    
    return CpuUsage.builder()
                   .idle(round(100d * idle / totalCpu))
                   .total(100.00)
                   .build();
  }
  
  @Override
  public MemoryUsage measureMemoryUsage () {
    SystemInfo systemInfo = new SystemInfo();
    val hardware = systemInfo.getHardware();
    val memory = hardware.getMemory();
    
    return MemoryUsage.builder()
                      .total(100)
                      .used(round(100d * memory.getAvailable() / memory.getTotal()))
                      .build();
  }
  
  private double round ( double value ) {
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
  
  
}
