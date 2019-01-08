package br.com.frcnetto.desafiospringboot.desafiospringboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
public class FileServiceImplIntegrationTest {

  @TestConfiguration
  static class FileServiceImplIntegrationTestConfiguration {

    @Bean
    public FileServiceImpl fileServiceImpl(){
      return new FileServiceImpl();
    }

    @Bean
    public PDFServiceImpl pdfServiceImpl(){
      return new PDFServiceImpl();
    }

  }

  @Autowired
  private FileServiceImpl fileServiceImpl;

  @MockBean
  private PDFServiceImpl pdfServiceImpl;

  @Before
  public void setUp(){

    //Mockito.when( fileServiceImpl.generateHashCode( "teste" ) )
    //       .thenReturn( 123456 );
  
  }

  @Test
  public void whenValidFile_thenSave(){

    String fileName    = "text.txt";
    String contentType = "text/plain";
    byte[] content     = null;
    String hashParams  = "ABCDEF";

    MultipartFile file = new MockMultipartFile( fileName, fileName, contentType, content );

    int result = fileServiceImpl.save( file, hashParams );

    assertEquals( hashParams.hashCode(), result );

  }
  
  @Test
  public void whenValidObjects_thenGenerateHashCode(){
    
    String string1 = "AAAAA";
    String string2 = "BBBBB";
    String string3 = "CCCCC";
    String string4 = "DDDDD";
    String string5 = "EEEEE";
    String string6 = "FFFFF";

    int controllHash = string1.hashCode() + string2.hashCode() + string3.hashCode() +
                       string4.hashCode() + string5.hashCode() + string6.hashCode();

    int resultHash = fileServiceImpl.generateHashCode( string1, string2, string3, string4, string5, string6 );

    assertEquals( controllHash, resultHash );
  
  }

}