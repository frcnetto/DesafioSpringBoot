package br.com.frcnetto.desafiospringboot.desafiospringboot.service;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.springframework.stereotype.Service;

@Service
public class PDFServiceImpl implements PDFService {

  @Override
  public void addFooter( byte[] src, String dest, String... params) throws DocumentException, IOException {

    PdfReader  reader  = new PdfReader( src );
    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream( dest ));
    Phrase     footer  = new Phrase();

    float x, y;

    for (String s : params)
      footer.add(s);

    for ( int pageNum = 1; pageNum <= reader.getNumberOfPages(); pageNum++ ) {
      
      x = reader.getPageSize( pageNum ).getWidth() - 10;
      y = reader.getPageSize( pageNum ).getBottom( 10 );

      ColumnText.showTextAligned( stamper.getUnderContent( pageNum ), 
                                  Element.ALIGN_RIGHT, 
                                  footer, 
                                  x, 
                                  y, 
                                  0 );

    }

    stamper.close();
    reader.close();

  }
  
}