package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.ConsultaHIPDatosClienteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRRecuperarImagenesSCService;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.ImagenPDFUtil;

/**
 * @author Sergio Apaza
 */

@Service("spusicc.pedido.procesoOCRRecuperarImagenesSCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoOCRRecuperarImagenesSCServiceImpl extends BaseService implements ProcesoOCRRecuperarImagenesSCService {

	@Resource(name="scsicc.consultaHIPDatosClienteDAO")
	private ConsultaHIPDatosClienteDAO consultaHIPDatosClienteDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRRecuperarImagenesSCService#getConsultaMasivaSC(java.util.Map)
	 */
	public List getConsultaMasivaSC(Map params) {
		String tipoArchivo = (String)params.get("tipoArchivo");
		List listaDatos = (List)params.get("listaDatos");
		String indRetiradas = (String)params.get("indRetiradas");
		
		List listaConsulta = new ArrayList();
		Map mapDatos = new HashMap();
		
		for(int i=0; i<listaDatos.size(); i++) {
			String dato = (String)listaDatos.get(i);
			
			if(tipoArchivo.equals(Constants.OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC)) 
				mapDatos.put("codigoCliente", dato);
			else	
				mapDatos.put("documentoIdentidad", dato);
			
			if("1".equals(indRetiradas))
				mapDatos.put("indRetiradas", "1");

			//verificamos si tiene archivos escaneados SC y traes los datos de dicho cliente	
			Map mapConsulta = consultaHIPDatosClienteDAO.getDatosClienteEscaneoSC(mapDatos);
			if(mapConsulta != null)
			{
				listaConsulta.add(mapConsulta);
			}				
			else
			{
				mapDatos.put("imagen", "NO");
				listaConsulta.add(mapDatos);
			}
		}
		
		return listaConsulta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRRecuperarImagenesSCService#getConsultaTodasMasivaSC(java.util.Map)
	 */
	public List getConsultaTodasMasivaSC(Map params) {
		List listaConsulta  = consultaHIPDatosClienteDAO.getDatosTodosClienteEscaneoSC(params);
		return listaConsulta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRRecuperarImagenesSCService#executeGenerarConsultaMasivaSC(java.util.Map)
	 */
	public void executeGenerarConsultaMasivaSC(Map params) throws Exception {
		List listaDatos = (List)params.get("listaDatos");
		
		String servidorFtpSC = (String)params.get("servidorFtpSC");
		String puertoFtpSC = (String)params.get("puertoFtpSC");
		String directorioFtpSC = (String)params.get("directorioFtpSC");
		String usuarioFtpSC = (String)params.get("usuarioFtpSC");
		String passwordFtpSC = (String)params.get("passwordFtpSC");
		String directorioTempSC = (String)params.get("directorioTempSC");
		String directorioFtpMasivSC = (String)params.get("directorioFtpMasivSC");
		String scaleFitSC = (String)params.get("scaleFitSC");
	
		String carpetaMasiva = (String)params.get("carpetaMasiva");
		
		//creamos el directorio
		File directorio = new File(directorioTempSC + carpetaMasiva);
		directorio.mkdir();
				
		ImagenPDFUtil imagenUtil = new ImagenPDFUtil();
		for(int i=0; i<listaDatos.size(); i++) {
			Map mapFila = (Map)listaDatos.get(i);
			
			String codigoCliente = (String)mapFila.get("codigoCliente");
			String imagen = (String)mapFila.get("imagen");
		
			if(imagen.equals("NO")) continue;
			
			log.debug("Empieza la Generacion PDF para la consultora: " + codigoCliente);
			/*INI JC PER-SiCC-2012-0340*/
			//Generamos el pdf en base a las imagenes recuperas del servidor FTP
			try {
				imagenUtil.generarPdfFtpToLocal(servidorFtpSC, puertoFtpSC, directorioFtpSC + codigoCliente, 
						usuarioFtpSC, passwordFtpSC, "jpg",	directorioTempSC + carpetaMasiva + "/", 
						codigoCliente, Long.parseLong(scaleFitSC));
			} catch (Exception e) {
				throw new Exception("Consultora: "+codigoCliente + " - "+ e);
			}
			/*FIN JC PER-SiCC-2012-0340*/
			
			log.debug("Finaliza la Generacion PDF para la consultora: " + codigoCliente);
		}	
		
		//Luego dejamos el directorio creado en el servidor FTP central de Imagenes
		Interfaz inter = new Interfaz();
		inter.setServidorFtp(servidorFtpSC);
		inter.setPuertoFtp(puertoFtpSC);
		inter.setUsuarioFtp(usuarioFtpSC);
		inter.setPasswordFtp(passwordFtpSC);
		
		//nos conectamos al servidor FTP
		FTPUtil ftpUtil = new FTPUtil();
		ftpUtil.loguearFTP(inter);
		
		//copiamos el directorio al servidor FTP central de imagenes
		ftpUtil.copiarDirectorioRedaFTP(directorioTempSC, carpetaMasiva, directorioFtpMasivSC);
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
				
					if(!borrarFichero)
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
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRRecuperarImagenesSCService#executeDeleteMasivaSC(java.util.Map)
	 */
	public void executeDeleteMasivaSC(Map params) throws Exception {
		List listaDatos = (List)params.get("listaDatos");
		
		String servidorFtpSC = (String)params.get("servidorFtpSC");
		String puertoFtpSC = (String)params.get("puertoFtpSC");
		String directorioFtpSC = (String)params.get("directorioFtpSC");
		String usuarioFtpSC = (String)params.get("usuarioFtpSC");
		String passwordFtpSC = (String)params.get("passwordFtpSC");
	
		Interfaz inter = new Interfaz();
		inter.setServidorFtp(servidorFtpSC);
		inter.setPuertoFtp(puertoFtpSC);
		inter.setUsuarioFtp(usuarioFtpSC);
		inter.setPasswordFtp(passwordFtpSC);
		
		//nos conectamos al servidor FTP
		FTPUtil ftpUtil = new FTPUtil();
		ftpUtil.loguearFTP(inter);
		
		//borramos las carpetas de las consultoras en el servidor FTP
		for(int i=0; i<listaDatos.size(); i++) {
			Map mapFila = (Map)listaDatos.get(i);
			
			String codigoCliente = (String)mapFila.get("codigoCliente");
		
			//eliminamos el directorio de la consultora con todos sus archivos almacenados en esa carpeta
			try {
				ftpUtil.eliminarDirectorio(directorioFtpSC + codigoCliente);
				mapFila.put("borrado", "ok");
			} catch(Exception ex) {
				log.warn("executeDeleteMasivaSC - No se pudo borrar el directorio para la consultora :" + codigoCliente);
				String mensajeEjecucion = "No se pudo borrar el directorio para la consultora :" + codigoCliente+", sin embargo se continua con la ejecución";
				params.put("mensajeEjecucion", mensajeEjecucion);
				//throw new Exception("No se pudo borrar el directorio para la consultora :" + codigoCliente);
			}	
		}	
		ftpUtil.cerrarFTP();
		
		//borramos los registros en la tabla de Transferencia de la BD
		for(int i=0; i<listaDatos.size(); i++) {
			Map mapFila = (Map)listaDatos.get(i);
			String codigoCliente = (String)mapFila.get("codigoCliente");
			
			//if(mapFila.get("borrado")!=null)
				consultaHIPDatosClienteDAO.deleteImagenesEscaneoSC(codigoCliente);	
		}	
		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRRecuperarImagenesSCService#getIDCarpeta()
	 */
	public String getIDCarpeta() {
		//Aqui das el formato que necesitas en la fecha. con la letra "S" que esta al final, ves los minisegundos
		SimpleDateFormat formatEntrada = new SimpleDateFormat("yyMMdd_kkmmss_S");

		//Aqui estas obtienendo la fecha actual de tu PC
		Date fechaEntrada = new Date();

		//Aqui das el formato de la fecha que tiene tu PC.
		String fechaFormateada = formatEntrada.format(fechaEntrada);
		
		return fechaFormateada;
	}
}
