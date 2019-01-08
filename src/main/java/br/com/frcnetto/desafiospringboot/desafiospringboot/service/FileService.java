package br.com.frcnetto.desafiospringboot.desafiospringboot.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  int save( MultipartFile file, Object... hashParams );
  int generateHashCode( Object... params );
  
}