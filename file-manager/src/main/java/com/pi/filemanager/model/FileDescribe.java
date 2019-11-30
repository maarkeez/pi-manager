package com.pi.filemanager.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDescribe {
  
  private String directory;
  private List<FileModel> files;
}
