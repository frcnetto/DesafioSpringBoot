package br.com.frcnetto.desafiospringboot.desafiospringboot.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

  private static final String path = "src/main/resources/files/file_";

  @Autowired
  private PDFServiceImpl pdfService;

  @Override
	public int save( MultipartFile file, Object ... hashParams ) {
    
    try {

      int hash = this.generateHashCode( hashParams );
      String finalPath = path + hash + "." + file.getOriginalFilename().split("\\.")[1];
      
      pdfService.addFooter( file.getBytes(), finalPath, String.valueOf( hash ) );

      return hash;
    
    } 
    
    catch ( Exception e ) {
      e.printStackTrace();
      return 0;
	  }

  }

  @Override
  public int generateHashCode( Object... params ) {

    int hash = 0;

    for (Object obj : params) 
      hash += obj.hashCode();

    return hash;
  }

  @Override
  public void persist(String relativePath, MultipartFile file) throws IOException {
    
    byte[] bytes = file.getBytes();
    Path path    = Paths.get( relativePath );
    Files.write(path, bytes);

  }
  
}