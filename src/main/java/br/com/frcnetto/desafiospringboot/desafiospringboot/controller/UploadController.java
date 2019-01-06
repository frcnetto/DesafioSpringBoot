package br.com.frcnetto.desafiospringboot.desafiospringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.frcnetto.desafiospringboot.desafiospringboot.service.FileServiceImpl;

@RestController
@RequestMapping( "/upload" )
public class UploadController {

  @Autowired
  private FileServiceImpl service;

  @PostMapping( "/send" )
  public ResponseEntity<Object> sendDocument( HttpServletRequest request, @RequestParam( "cpf" ) String cpf, @RequestParam("file") MultipartFile file ){

    try {
      
      int generatedHash = service
                            .save( file, 
                                   cpf, 
                                   request.getRemoteAddr(), 
                                   System.currentTimeMillis() );
      
      String result = new JSONObject()
                            .put("hash", generatedHash)
                            .toString();

      return ResponseEntity.ok( result );
    } 
    
    catch ( Exception e ) {
      e.printStackTrace();
      return new ResponseEntity<Object>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }
  
}