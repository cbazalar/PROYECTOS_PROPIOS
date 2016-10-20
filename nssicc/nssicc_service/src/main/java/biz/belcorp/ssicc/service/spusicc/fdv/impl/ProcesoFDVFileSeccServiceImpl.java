package biz.belcorp.ssicc.service.spusicc.fdv.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileSeccDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileSeccService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessFileException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.ValidationGenericUtil;

/**
 * <p>
 * <a href="ProcesoFDVFileSeccServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

@Service("spusicc.procesoFDVFileSeccService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFDVFileSeccServiceImpl extends BaseService implements ProcesoFDVFileSeccService{

	@Resource(name = "spusicc.procesoFDVFileSeccDAO")
	private ProcesoFDVFileSeccDAO procesoFDVFileSeccDAO;

	/**
	 * @return Returns the procesoFDVFileSeccDAO.
	 */
	public ProcesoFDVFileSeccDAO getProcesoFDVFileSeccDAO() {
		return procesoFDVFileSeccDAO;
	}

	/**
     * Setea el DAO para la comunicación con la capa de persistencia.
     * 
     * @param procesoFDVFileSeccDAO
     */
	public void setProcesoFDVFileSeccDAO(ProcesoFDVFileSeccDAO procesoFDVFileSeccDAO) {
		this.procesoFDVFileSeccDAO = procesoFDVFileSeccDAO;
	}

	/**
     * Adjunta el archivo de BD Secciones, en una carpeta temporal definida por el pais
     * y una vez hecho esto lee el archivo e inserta los datos en su respectiva tabla
     * 
     * @param 
     * 		procesoFDVClusterizacion
     * 		usuario
     */
	public void saveFileSecciones(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {

		String codProc = procesoFDVClusterizacion.getCodProc();
		String directorioTemp = procesoFDVClusterizacion.getDirectorioTemporal();
		
		// Subiendo los archivos excel
		String fileNameSecc = null;
		boolean processSecc = false;
		
		try{			
			
			File seccionFile = procesoFDVClusterizacion.getSeccionFile();
			if(!"".equals(seccionFile.getName())){
				
				// EXCEL DE SECCIONES
				fileNameSecc = uploadArchivoSecciones(procesoFDVClusterizacion, directorioTemp);
				loadfileSeccExcel(directorioTemp, fileNameSecc, codProc);
				
				processSecc = true;			
	        	procesoFDVClusterizacion.setArcSecc(fileNameSecc);
			}
			
		}catch(Exception e){
			
			if(!processSecc){
				fileNameSecc = null;
			}
			
        	procesoFDVClusterizacion.setArcSecc(fileNameSecc);
			throw e;			
		}
	}
	
	/**
     * 
	 * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#loadfileSeccExcel(
     * 		java.lang.String, java.lang.String, java.lang.String)
     */
	private void loadfileSeccExcel(String directorioTemp, String fileNameSecc,
			String codProc) throws Exception {

		if(StringUtils.isNotBlank(fileNameSecc)){
			
			List seccionList = null;
			ExcelUtil excelUtil = null;
			int fila = 1;
			
			try {
				
				// Abrimos el archivo Excel para su procesamiento
				excelUtil = new ExcelUtil(directorioTemp, fileNameSecc);				
				for (int i = 0; i < Constants.NUMERO_MAX_HOJAS_EXCEL; i++) {
					
					try{
						// Nos colocamos en la hoja iterada del documento Excel
						excelUtil.initSheet(i);
						
						// Nos pasamos a la segunda fila, ya que en el primero se encuentra la cabecera
						excelUtil.next();
					}catch(Exception e){
						// Si no encuentra la hoja iterada o no encuentra datos en la hoja, terminar la iteracion
						break;
					}
					
					seccionList = new ArrayList();
					Map mapSecciones = null;
					
					while(excelUtil.hasNext()){
						
						Map mapRow = excelUtil.next();
						mapSecciones = new HashMap();				
						fila = fila + 1;
						
						if (log.isDebugEnabled()) {
							log.debug("Carga Secciones mapRow: "+fila+" - "+mapRow.toString());
						}
		
						Long nCapi = new Long("0");
						Long nIngr = new Long("0");
						Long nRein = new Long("0");
						Long nEgre = new Long("0");
						
						String bseAnyo = (String.valueOf(mapRow.get("0"))).trim();
						String bseCamp = (String.valueOf(mapRow.get("1"))).trim();
						String bseZona = (String.valueOf(mapRow.get("2"))).trim();
						String bseRegi = (String.valueOf(mapRow.get("3"))).trim();
						String bseCapi = (String.valueOf(mapRow.get("4"))).trim();
						String bseIngr = (String.valueOf(mapRow.get("5"))).trim();				
						String bseRein = (String.valueOf(mapRow.get("6"))).trim();
						String bseEgre = (String.valueOf(mapRow.get("7"))).trim();
						String bseSecc = (String.valueOf(mapRow.get("8"))).trim();
						
						bseAnyo = ValidationGenericUtil.validateNullString(bseAnyo);
						bseCamp = ValidationGenericUtil.validateNullString(bseCamp);
						bseZona = ValidationGenericUtil.validateNullString(bseZona);
						bseRegi = ValidationGenericUtil.validateNullString(bseRegi);
						bseCapi = ValidationGenericUtil.validateNullString(bseCapi);
						bseIngr = ValidationGenericUtil.validateNullString(bseIngr);				
						bseRein = ValidationGenericUtil.validateNullString(bseRein);
						bseEgre = ValidationGenericUtil.validateNullString(bseEgre);
						bseSecc = ValidationGenericUtil.validateNullString(bseSecc);
						
						if(bseCapi != null){
							
							if(bseCapi.endsWith(".0")){
								bseCapi = bseCapi.substring(0, bseCapi.length()-2);
							}
							
							try{
								nCapi = new Long(bseCapi);
							}catch(Exception pe){
								nCapi = new Long("0");
							}
						}
						
						if(bseIngr != null){
							
							if(bseIngr.endsWith(".0")){
								bseIngr = bseIngr.substring(0, bseIngr.length()-2);
							}
							
							try{
								nIngr = new Long(bseIngr);
							}catch(Exception pe){
								nIngr = new Long("0");
							}
						}
						
						if(bseRein != null){
							
							if(bseRein.endsWith(".0")){
								bseRein = bseRein.substring(0, bseRein.length()-2);
							}
							
							try{
								nRein = new Long(bseRein);
							}catch(Exception pe){
								nRein = new Long("0");
							}
						}
						
						if(bseEgre != null){
							
							if(bseEgre.endsWith(".0")){
								bseEgre = bseEgre.substring(0, bseEgre.length()-2);
							}
							
							try{
								nEgre = new Long(bseEgre);
							}catch(Exception pe){
								nEgre = new Long("0");
							}
						}
						
						if(bseZona != null){
							if(bseZona.endsWith(".0")){
								bseZona = bseZona.substring(0, bseZona.length()-2);
							}
						}
						
						if(bseAnyo != null){
							if(bseAnyo.endsWith(".0")){
								bseAnyo = bseAnyo.substring(0, bseAnyo.length()-2);
							}
						}
						
						if(bseCamp != null){
							bseCamp = bseCamp.substring(0, 2);
						}
						
						mapSecciones.put("procCodProc", codProc);
						mapSecciones.put("bseAnyo", bseAnyo);
						mapSecciones.put("bseCamp", bseCamp);
						mapSecciones.put("bseZona", bseZona);
						mapSecciones.put("bseRegi", bseRegi);				
						mapSecciones.put("bseCapi", nCapi);
						mapSecciones.put("bseIngr", nIngr);
						mapSecciones.put("bseRein", nRein);
						mapSecciones.put("bseEgre", nEgre);
						mapSecciones.put("bseSecc", bseSecc);				
						
						// Validando que si tiene valor en la zona, entonces toda la fila es valida
						if(StringUtils.isNotBlank(bseZona)){
							seccionList.add(mapSecciones);
						}
					}
					
					if(!seccionList.isEmpty()){						
						if(i == 0){
							procesoFDVFileSeccDAO.insertSeccionesFDV(seccionList, codProc, true);
						}else{
							procesoFDVFileSeccDAO.insertSeccionesFDV(seccionList, codProc, false);
						}					
					}
				}				
				
				excelUtil.cerrar();			
				
			} catch (Exception e) {
				
				if(excelUtil != null){
					excelUtil.cerrar();
				}
				
				// Si existe algun error debemos de borrar el archivo de la carpeta temporal
				borrarFichero(directorioTemp, fileNameSecc);
				
				if(e.getCause() instanceof DataIntegrityViolationException){
					// Se lanzara una excepcion si existe algun fallo en la integridad de los datos
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameSecc);
				}else if(e.getCause() instanceof UncategorizedSQLException){
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameSecc);
				}else{
					throw e;
				}
			}
		}
	}
	
	/**
	 * Realiza la subida del archivo secciones a un directorio temporal
	 * @param seccionFile
	 * @param directorioTemp
	 * @throws Exception 
	 */
	private String uploadArchivoSecciones(ProcesoFDVClusterizacion procesoFDVClusterizacion, 
			String directorioTemp) throws Exception{

		String fileName = null;
		File seccionFile = procesoFDVClusterizacion.getSeccionFile();
		FileOutputStream os = null;
		
		try{
		
			if(!"".equals(seccionFile.getName())){
			
				// verificamos que el archivo adjuntado sea del formato indicado
				if(!seccionFile.getName().endsWith(".xls")){
					throw new InvalidUploadException(seccionFile.getName(),"extension");
				}else{
					// modificamos el nombre del archivo y lo concatenamos con la fecha y hora actual para que sea unico
					String[] arrayName = seccionFile.getName().split(".xls");
					fileName = arrayName[0] + " " + DateUtil.getDateTimeNow("yyyyMMdd_HHmmss",
					new Date(System.currentTimeMillis())) + ".xls";
				}
				
				procesoFDVClusterizacion.setArcSecc(fileName);
				
				// Leemos el stream de entrada
				InputStream is = new FileInputStream(seccionFile);
				
				// Abrimos el stream de escritura, ubicacion al cual se grabara el archivo
				os = new FileOutputStream(new File(
				directorioTemp, procesoFDVClusterizacion.getArcSecc()));
				
				// Grabamos cada 1024 bytes
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				
				os.close();
			}
			
			return fileName;
		
		}catch(Exception e){
			
			if(os != null){
				try {
					os.close();
				} catch (IOException e1) {}
			}
			
			if(e instanceof InvalidUploadException){
				throw e;
			}else{
				throw new InvalidUploadException(seccionFile.getName(),"error");
			}			
		}
	}
	
	/**
	 * elimina el fichero del temporal
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			
			if (log.isDebugEnabled()) {
				log.debug("Se elimino el archivo");
			}
		}	
		catch(Exception ex) {
			
			if (log.isDebugEnabled()) {
				log.debug("No se pudo eliminar el archivo "+ex.getMessage());
			}
		}
	}
}