package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.FtpCobrador;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGenerarCarteraExternaService;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.ImagenPDFUtil;


/**
 * Service que controla la Generacion del Cronograma
 *  
 * <p>
 * <a href="ProcesoCOBGenerarCarteraExternaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCOBGenerarCarteraExternaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBGenerarCarteraExternaServiceImpl extends BaseService implements ProcesoCOBGenerarCarteraExternaService {

    
	private String generarIDCarpeta(Map params) {
		
		String codigoEtapaDeuda = (String)params.get("codigoEtapaDeuda");
				
		//Aqui das el formato que necesitas en la fecha. con la letra "S" que esta al final, ves los minisegundos
		SimpleDateFormat formatEntrada = new SimpleDateFormat("ddMMyyyy_kkmmss_S");

		//Aqui estas obtienendo la fecha actual de tu PC
		Date fechaEntrada = new Date();

		//Aqui das el formato de la fecha que tiene tu PC.
		String fechaFormateada = formatEntrada.format(fechaEntrada);
		
		return fechaFormateada;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBGenerarCarteraExternaService#executeSC(java.util.Map)
	 */
	public void executeGenerarSCCartera(Map params, FtpCobrador ftpCobrador ) throws Exception {
		
		List listaDatos = (List)params.get("listaDatos");
		
		
		//Obtener los datos del Servidor FTP de las Solicitudes de Credito
		
		String servidorFtpSC = (String)params.get("servidorFtpSC");
		String puertoFtpSC = (String)params.get("puertoFtpSC");
		String directorioFtpSC = (String)params.get("directorioFtpSC");
		String usuarioFtpSC = (String)params.get("usuarioFtpSC");
		String passwordFtpSC = (String)params.get("passwordFtpSC");
		String directorioTempSC = (String)params.get("directorioTempSC");		
		String scaleFitSC = (String)params.get("scaleFitSC");
	
		String carpetaMasiva = generarIDCarpeta(params);
		
		//creamos el directorio
		File directorio = new File(directorioTempSC + carpetaMasiva);
		directorio.mkdir();
				
		ImagenPDFUtil imagenUtil = new ImagenPDFUtil();
		for(int i=0; i<listaDatos.size(); i++) {
			Map mapFila = (Map)listaDatos.get(i);
			
			String codigoCliente = (String)mapFila.get("codigoCliente");
		
			//Generamos el pdf en base a las imagenes recuperas del servidor FTP
			imagenUtil.generarPdfFtpToLocal(servidorFtpSC, puertoFtpSC, directorioFtpSC + codigoCliente, 
						usuarioFtpSC, passwordFtpSC, "jpg",	directorioTempSC + carpetaMasiva + "/", 
						codigoCliente, Long.parseLong(scaleFitSC));
		}	
		
				
		//Obteniendo los datos del Servidor FTP del Cobrador 
		Interfaz inter = new Interfaz();
		inter.setServidorFtp(ftpCobrador.getServidorFTP());
		inter.setPuertoFtp(ftpCobrador.getPuertoFTP());
		inter.setUsuarioFtp(ftpCobrador.getUsuarioFTP());
		inter.setPasswordFtp(ftpCobrador.getClaveFTP());
		
		
		//nos conectamos al servidor FTP
		FTPUtil ftpUtil = new FTPUtil();
		ftpUtil.loguearFTP(inter);
		
		//copiamos el directorio al servidor FTP del Cobrador
		ftpUtil.copiarDirectorioRedaFTP(directorioTempSC, carpetaMasiva, ftpCobrador.getDirectorioFTP());
		ftpUtil.cerrarFTP();
		
		//borramos el directorio del servidor local
		try {
			//borramos primeros los ficheros del directorio
			FilenameFilter filenameFilter = new FilenameFilter() {
				public boolean accept(File dir, String name){
			        return true;
			    }
			};

			File dir = new File(directorioTempSC + carpetaMasiva);
			String[] fileNames = dir.list(filenameFilter);
			//lo vamos aadiendo al PDF
			for(int i=0; i < fileNames.length; i++) {	
				File fileArchivo = new File(directorioTempSC + carpetaMasiva, fileNames[i]);
				
				try {
					boolean borrarFichero= fileArchivo.delete();
				
					if(borrarFichero)
						log.debug("No se pudo eliminar el archivo : " + fileNames[i]);
				} catch (Exception e) {
					log.error("No se pudo eliminar el archivo : " + fileNames[i]);
				}		
			}	
			
			File fileDirectorio = new File(directorioTempSC + carpetaMasiva);
			boolean borrarDirectorio = fileDirectorio.delete();
			if(!borrarDirectorio)
				log.debug("No se pudo eliminar el directorio : " + directorioTempSC + carpetaMasiva);
			
		} catch (Exception e) {
			log.error("No se pudo eliminar el directorio : " + directorioTempSC + carpetaMasiva);
		}
		
		params.put("carpetaMasiva", carpetaMasiva);
	}
	

	

}
