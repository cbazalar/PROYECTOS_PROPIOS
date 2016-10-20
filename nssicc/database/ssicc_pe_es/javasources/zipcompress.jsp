create or replace and compile java source named zipcompress as
import java.io.*;
import java.util.zip.*;

public class ZIPCompress {

  static final int BUFFER = 2048;
  
  public static void comprimir(String nombreDirectorio, String nombreArchivo) throws Exception {
      try {
    File archivoZip = new File(nombreDirectorio, nombreArchivo + ".ZIP");
          BufferedInputStream origin = null;
          FileOutputStream dest = new 
          FileOutputStream(archivoZip);
          ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
          
          byte data[] = new byte[BUFFER];
          // get a list of files from current directory
          File f = new File(nombreDirectorio, nombreArchivo + ".TMP");
  
          FileInputStream fi = new FileInputStream(f);
    origin = new BufferedInputStream(fi, BUFFER);
          ZipEntry entry = new ZipEntry(nombreArchivo + ".TMP");
          out.putNextEntry(entry);
          int count;
          while((count = origin.read(data, 0, BUFFER)) != -1) {
              out.write(data, 0, count);
          }
          origin.close();
          out.close();
      } 
      catch(Exception e) {
          e.printStackTrace();
          throw e;
      }
   } 
}
/

