package biz.belcorp.ssicc.web.listener;

import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaPremiosExcelService;
import biz.belcorp.ssicc.service.util.FileUtil;

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
    	
		ReporteService reporteService = (ReporteService)ctx.getBean("scsicc.reporteService");
		ProcesoINCCargaPremiosExcelService serviceCP = 	(ProcesoINCCargaPremiosExcelService) ctx.getBean("spusicc.procesoINCCargaPremiosExcelService");
		LookupService service = (LookupService) ctx.getBean("lookupService");
		UsuarioService usuarioService = (UsuarioService)ctx.getBean("usuarioService"); 
		
		String rutaPath = context.getRealPath("/");    
		try{
			 
			usuarioService.setRutaPath(FileUtil.formatDirectory(rutaPath));
			boolean esPrincipal = verificarServidorPrincipal(service);
			
			esPrincipal = false;
			if(esPrincipal) {
				service.updateProcesosNoTerminados();
				
				// Se procede a actualizar Auditoria de Reportes
				reporteService.actualizarRegistroAuditoriaReinicioServidor();
				
				Map criteria = new HashMap();
				// Se procede a eliminar los registros de la tabla temporal (Rep. Mapa Anaqueles con Balanceo Diario)
				reporteService.eliminarRegistrosTablaTempReporteMapaAnaq(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Rep. Balanceo de Línea y Productos)
				reporteService.eliminarRegistrosTablaTemporalReporteBalanceoLinea(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Programa Let. Reporte de Proyección)
				reporteService.eliminarRegistrosTablaTemporalReporProyeccion(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Rep. STO Rechazadas)
				reporteService.eliminarRegistrosTablaTempSTORechazadas(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Rep. Cierre Costos - Ventas)
				reporteService.eliminarRegistrosTablaTempReporteCierreCostoVentas(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Rep. Cierre Costos - Devoluciones)
				reporteService.eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(criteria);
	
				// Se procede a eliminar los registros de la tabla temporal (Inc. Carga Premios Excel)
				serviceCP.eliminarRegistrosTablaTempINCCargaPremios(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Rep. SAP Descuento Comercial)
				reporteService.eliminarRegistrosTablaTempReporteDescuentoComercial(criteria);
	
				// Se procede a eliminar los registros de la tabla temporal (Rep. SAP Descuento Volumen)			
				reporteService.eliminarRegistrosTablaTempReporteDescuentoVolumen(criteria);
				
				// Se procede a eliminar los registros de la tabla temporal (Rep. SAP Venta Linea)
				reporteService.eliminarRegistrosTablaTempReporteVentaLinea(criteria);
	
				// Se procede a eliminar los registros de la tabla temporal (Rep. REC Operaciones Reclamos por Pedidos)
				reporteService.eliminarRegistrosTablaTemporalReporOperaReclaPedidos(criteria);
				
				
			}	
			
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
	 
    
    private static boolean  verificarServidorPrincipal(LookupService service) throws Exception {
		 boolean esPrincipal = true;
		 
		 InetAddress localHost = InetAddress.getLocalHost();
		 String hostname = localHost.getHostName();
		 log.info("verificarServidorPrincipal, nombre Servidor: " + hostname);
		 
		 List paises = (List)service.getAllPaises();
		 Iterator i = paises.iterator();
        while (i.hasNext()) {
       	 Pais pais = (Pais)i.next();
       	 if(StringUtils.isNotEmpty(pais.getUrlServidorReportes())) {
       		 log.info("verificarServidorPrincipal, getUrlServidorReportes: " + pais.getUrlServidorReportes());
       		 if(pais.getUrlServidorReportes().indexOf(hostname)>=0) {
       			 esPrincipal = false;
       			 break;
       		 }
       	 }
        }
        
        return esPrincipal;
	 }
    
    private static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".")).toLowerCase();

        } catch (Exception e) {
            return "";
        }

    }
}