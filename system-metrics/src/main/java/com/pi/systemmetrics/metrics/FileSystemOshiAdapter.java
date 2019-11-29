package com.pi.systemmetrics.metrics;

import static com.pi.systemmetrics.utils.DoubleUtils.roundWithTwoDecimals;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import com.pi.systemmetrics.model.FileSystemVolume;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.software.os.OSFileStore;
import oshi.util.FormatUtil;

@Slf4j
@Component
class FileSystemOshiAdapter {
  
  List<FileSystemVolume> measureFileSystem () {
    val systemInfo = new SystemInfo();
    val operatingSystem = systemInfo.getOperatingSystem();
    val fileSystem = operatingSystem.getFileSystem();
    
    return stream(fileSystem.getFileStores()).map(this::toFileSystemVolume)
                                             .collect(toList());
  }
  
  
  private FileSystemVolume toFileSystemVolume ( OSFileStore fs ) {
    long usable = fs.getUsableSpace();
    long total = fs.getTotalSpace();
    
    val usedStr = FormatUtil.formatBytes(usable);
    val totalStr = FormatUtil.formatBytes(total);
    val used = roundWithTwoDecimals(100d * usable / total);
    
    return FileSystemVolume.builder()
                           .name(fs.getName())
                           .usedValue(usedStr)
                           .totalValue(totalStr)
                           .used(used)
                           .total(100.00)
                           .build();
  }
  
  
}
