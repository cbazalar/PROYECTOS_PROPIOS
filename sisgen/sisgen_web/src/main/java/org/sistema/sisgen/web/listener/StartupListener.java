package org.sistema.sisgen.web.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * StartupListener class used to initialize and database settings and populate
 * any application-wide drop-downs.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a>
 * 
 */

public class StartupListener extends ContextLoaderListener implements
        ServletContextListener {

    private static final Log log = LogFactory.getLog(StartupListener.class);
    private static final String PDF_EXTENSION  = ".pdf";
    private static final String XLS_EXTENSION  = ".xls";
    private static final String XLSX_EXTENSION = ".xlsx";
	private static final String CSV_EXTENSION  = ".csv";	
	private static final String TXT_EXTENSION  = ".txt";
	private static final String ZIP_EXTENSION  = ".zip";
   
    public static void setupContext(ServletContext context) {
    	ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(context);
    	
	    String rutaPath = context.getRealPath("/");    
		try{
			 
	
			
		}catch (Exception ex) {	
			log.error("ERROR (updateProcesosNoTerminados):" + ex.getMessage());
		}
		
		/* Eliminando Archivos Temporales de la ruta Resources */
		try{
			String rutaArchivos = rutaPath + "/resources/";
			File ruta= new File(rutaArchivos); 
			File[] files = ruta.listFiles();
			File f = null; 
			if(ruta.exists()) 	{ 
				for (int x=0; x < files.length;x++) { 
				   f= new File(files[x].toString());
				   String extensionArchivo = getFileExtension(f);
				   if (extensionArchivo.equals(PDF_EXTENSION) ||
					   extensionArchivo.equals(XLS_EXTENSION) ||
					   extensionArchivo.equals(XLSX_EXTENSION) ||   
					   extensionArchivo.equals(TXT_EXTENSION) || 
					   extensionArchivo.equals(ZIP_EXTENSION) ) {
				       f.delete(); 
				       log.error("Archivo Eliminado:" + f.getName());
				   }
				} 
			}
		}catch (Exception ex) {	
			log.error("ERROR Archivos Temporales de la ruta Resources:" + ex.getMessage());
		}
    }
    
    
    /* (non-Javadoc)
     * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        if (log.isInfoEnabled()) {
            log.info("initializing context...");
        }

        // invoca al ContextLoaderListener del contexto de Spring para para
        // inicializar todos los archivos de contexto especificador en el
        // web.xml
       try{
          super.contextInitialized(event);
          ServletContext context = event.getServletContext();
          setupContext(context);
       }
       catch(Exception e)
       {    
    	   log.error("Error:", e);    	  
       }

    }
	 
    
    
    
    /**
     * @param file
     * @return
     */
    private static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".")).toLowerCase();

        } catch (Exception e) {
            return "";
        }

    }
}