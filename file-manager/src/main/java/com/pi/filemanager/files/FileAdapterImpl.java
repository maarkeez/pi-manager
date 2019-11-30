package com.pi.filemanager.files;

import static com.pi.filemanager.model.FileType.FILE;
import static com.pi.filemanager.model.FileType.FOLDER;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.list;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import com.pi.filemanager.api.FileAdapter;
import com.pi.filemanager.model.FileModel;
import java.nio.file.Path;
import java.util.List;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class FileAdapterImpl implements FileAdapter {
  
  @Override
  @SneakyThrows
  public List<FileModel> listDirectory ( Path directory ) {
    
    return list(directory).map(this::toFileModel)
                          .collect(toList());
  }
  
  private FileModel toFileModel ( Path path ) {
    
    val type = isDirectory(path) ? FOLDER : FILE;
    
    val name = path.getFileName()
                   .toString();
    
    val size = byteCountToDisplaySize(path.toFile()
                                          .length());
    
    return FileModel.builder()
                    .name(name)
                    .size(size)
                    .type(type)
                    .build();
  }
}
