package com.pi.filemanager.api;

import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;

import com.pi.filemanager.model.FileDescribe;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api/filemanager/" )
@RequiredArgsConstructor( onConstructor = @__( @Autowired ) )
public class ApiFileManager {
  
  private final FileAdapter fileAdapter;
  
  @GetMapping( "describe/directory" )
  public FileDescribe describeDirectory ( @RequestParam( "directory" ) String directory ) {
    return FileDescribe.builder()
                       .files(fileAdapter.listDirectory(get(directory)))
                       .directory(directory)
                       .build();
  }
  
  @GetMapping( "describe/home" )
  public FileDescribe describeDirectory () {
    val directory = getProperty("user.home");
    return FileDescribe.builder()
                       .files(fileAdapter.listDirectory(get(directory)))
                       .directory(directory)
                       .build();
  }
  
}
