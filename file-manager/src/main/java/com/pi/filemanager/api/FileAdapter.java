package com.pi.filemanager.api;

import com.pi.filemanager.model.FileModel;
import java.nio.file.Path;
import java.util.List;

public interface FileAdapter {
  
  List<FileModel> listDirectory ( Path directory );
  
}
