package com.pi.systemmetrics.metrics;

import static com.pi.systemmetrics.utils.DoubleUtils.roundWithTwoDecimals;
import static oshi.util.Util.sleep;

import com.pi.systemmetrics.model.CpuUsage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor.TickType;

@Slf4j
@Component
class CpuOshiAdapter {
  
  CpuUsage measureCpuUsage () {
    val systemInfo = new SystemInfo();
    val hardware = systemInfo.getHardware();
    val processor = hardware.getProcessor();
    
    long[] prevTicks = processor.getSystemCpuLoadTicks();
    sleep(1000);
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
    
    val idlePercentage = 100d * idle / totalCpu;
    val usedPercentage = roundWithTwoDecimals(100.00 - idlePercentage);
    
    return CpuUsage.builder()
                   .used(usedPercentage)
                   .total(100.00)
                   .build();
  }
  
}
