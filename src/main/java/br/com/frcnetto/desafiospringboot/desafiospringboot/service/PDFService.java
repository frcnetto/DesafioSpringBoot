package br.com.frcnetto.desafiospringboot.desafiospringboot.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface PDFService {

  void addFooter( byte[] src, String dest, String... params ) throws DocumentException, IOException;
  
}