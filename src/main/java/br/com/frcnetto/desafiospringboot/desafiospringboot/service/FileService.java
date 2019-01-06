package br.com.frcnetto.desafiospringboot.desafiospringboot.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  int save( MultipartFile file, Object... params );
  void persist( String relativePath, MultipartFile file ) throws IOException;
  int generateHashCode( Object... params );
  
}