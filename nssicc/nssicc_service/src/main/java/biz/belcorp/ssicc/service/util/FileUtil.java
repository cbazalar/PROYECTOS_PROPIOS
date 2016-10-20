package biz.belcorp.ssicc.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Metodos utilitarios para el manejo de archivos.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class FileUtil {
	protected static final Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * Elimina el archivo pasado como parametro.
	 * 
	 * @param file
	 *            Nombre de Archivo a eliminar
	 */
	public static void deleteFile(File file) {
		try {
			if (file != null && file.isFile()) {
				file.delete();
				log.info("Se elimino el archivo: " + file.getAbsolutePath());
			}
		} catch (Exception e) {
			log.error("No se pudo eliminar el archivo");
		}
	}

	/**
	 * Agrega el separador de archivo al final de la ruta.
	 * 
	 * @param ruta
	 * @return
	 */
	public static String formatDirectory(String ruta) {
		if (!ruta.trim().endsWith(System.getProperty("file.separator")))
			ruta = ruta.trim() + System.getProperty("file.separator");
		return ruta.trim();
	}
	
	public static boolean validarUltimaLineaArchivo(String ruta){
        boolean estado = true;
		
	    try {
	        File file = new File(ruta);
	        RandomAccessFile fileHandler = new RandomAccessFile( file, "r" );
	        long fileLength = file.length() - 1;
	 
	        boolean saltoLinea = false;
	        boolean lineaVacia = false;
	        
	        boolean archivoCorrecto = false;
	        
	        for( long filePointer = fileLength; filePointer != -1; filePointer-- ) {
	            fileHandler.seek( filePointer );
	            int readByte = fileHandler.readByte();                
	 
	            if( readByte == 0xA ) { //
	                if( filePointer == fileLength ) {
	                    saltoLinea = true;
	                    continue;
	                } else {
	                    break;
	                }
	            } else if( readByte == 0xD ) {
	                if( filePointer == fileLength - 1 ) {
	                	lineaVacia = true;
	                    continue;		                    
	                } else {
	                    break;
	                }                    
	            }
	 
	            if(saltoLinea && lineaVacia)
	            {
	            	archivoCorrecto = true;
	            	break;
	            }
	        }
	 
	        fileHandler.close();
	        
	        if(!archivoCorrecto)
	        {
	        	//Agregar la linea al final
	        	RandomAccessFile fileH = new RandomAccessFile( file, "rw" );
	        	fileH.seek(fileLength+1);
	        	fileH.write(0xD);
	        	fileH.write(0xA);
	        	
	        	fileH.close();
	        }
	        
	    } catch(FileNotFoundException e ) {
	        e.printStackTrace();
        	estado = false;
	    } catch(IOException e ) {
	        e.printStackTrace();
	        estado = false;
	    }
		
	    return estado;
	}
}
