package br.com.frcnetto.desafiospringboot.desafiospringboot.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping( "/upload" )
public class UploadController {

  @Autowired
  private ServletContext context;

  @PostMapping( "/send" )
  public ResponseEntity<Object> sendDocument( HttpServletRequest request, @RequestParam( "cpf" ) String cpf, @RequestParam("file") MultipartFile file ){

    try {
      //TODO: Melhorar implementação
      byte[] bytes = file.getBytes();
      Path path    = Paths.get( context.getRealPath( "files" ) + file.getOriginalFilename() );
      
      Files.write( path, bytes );
      
      return ResponseEntity.ok( "File uploaded with success!" );
    } 
    
    catch (Exception e) {
      return ResponseEntity.ok( e.getMessage() );
    }
  }
  
}