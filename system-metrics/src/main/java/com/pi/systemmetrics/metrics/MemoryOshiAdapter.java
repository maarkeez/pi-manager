package com.pi.systemmetrics.metrics;

import static com.pi.systemmetrics.utils.DoubleUtils.roundWithTwoDecimals;

import com.pi.systemmetrics.model.MemoryUsage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;

@Slf4j
@Component
class MemoryOshiAdapter {
  
  MemoryUsage measureMemoryUsage () {
    SystemInfo systemInfo = new SystemInfo();
    val hardware = systemInfo.getHardware();
    val memory = hardware.getMemory();
    
    return MemoryUsage.builder()
                      .total(100)
                      .used(roundWithTwoDecimals(100d * memory.getAvailable() / memory.getTotal()))
                      .build();
  }
  
}
