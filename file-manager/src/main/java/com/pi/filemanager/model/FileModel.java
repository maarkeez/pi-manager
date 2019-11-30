package com.pi.filemanager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileModel {
  
  private String name;
  private String size;
  private FileType type;
}
