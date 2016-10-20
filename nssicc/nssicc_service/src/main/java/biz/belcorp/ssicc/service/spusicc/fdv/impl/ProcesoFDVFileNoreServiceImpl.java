package biz.belcorp.ssicc.service.spusicc.fdv.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileNoreDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileNoreService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessFileException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.ValidationGenericUtil;

/**
 * <p>
 * <a href="ProcesoFDVFileNoreServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

@Service("spusicc.procesoFDVFileNoreService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFDVFileNoreServiceImpl extends BaseService implements ProcesoFDVFileNoreService
{
	@Resource(name = "spusicc.procesoFDVFileNoreDAO")
	private ProcesoFDVFileNoreDAO procesoFDVFileNoreDAO;

	/**
	 * @return Returns the procesoFDVFileNoreDAO.
	 */
	public ProcesoFDVFileNoreDAO getProcesoFDVFileNoreDAO() {
		return procesoFDVFileNoreDAO;
	}

	/**
     * Setea el DAO para la comunicación con la capa de persistencia.
     * 
     * @param procesoFDVFileNoreDAO
     */
	public void setProcesoFDVFileNoreDAO(ProcesoFDVFileNoreDAO procesoFDVFileNoreDAO) {
		this.procesoFDVFileNoreDAO = procesoFDVFileNoreDAO;
	}

	/**
     * Adjunta el archivo de BD No Reconstruida, en una carpeta temporal definida por el pais
     * y una vez hecho esto lee el archivo e inserta los datos en su respectiva tabla
     * 
     * @param 
     * 		procesoFDVClusterizacion
     * 		usuario
     */
	public void saveFileNoReconstruida(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {

		String codProc = procesoFDVClusterizacion.getCodProc();
		String directorioTemp = procesoFDVClusterizacion.getDirectorioTemporal();
		
		// Subiendo los archivos excel
		String fileNameNore = null;
		boolean processNore = false;
		
		try{
			
			File noReconstruidaFile = procesoFDVClusterizacion.getNoReconstruidaFile();
			if(!"".equals(noReconstruidaFile.getName())){
				
				// EXCEL DE BD NO RECONSTRUIDA
				fileNameNore = uploadArchivoNoReconstruida(procesoFDVClusterizacion, directorioTemp);
				loadfileNoreExcel(directorioTemp, fileNameNore, codProc);
				
				processNore = true;
	        	procesoFDVClusterizacion.setArcNore(fileNameNore);
			}
			
		}catch(Exception e){
			
			if(!processNore){
				fileNameNore = null;
			}
			
        	procesoFDVClusterizacion.setArcNore(fileNameNore);			
			throw e;			
		}
	}
	
	/**
     * 
	 * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVFileNoreService#loadfileNoreExcel(
     * 		java.lang.String, java.lang.String, java.lang.String)
     */
	private void loadfileNoreExcel(String directorioTemp, String fileNameNore,
			String codProc) throws Exception {
		
		if(StringUtils.isNotBlank(fileNameNore)){
			
			List noreList = null;
			ExcelUtil excelUtil = null;
			int fila = 1;
			
			try {
				
				// Abrimos el archivo Excel para su procesamiento
				excelUtil = new ExcelUtil(directorioTemp, fileNameNore);
				for (int i = 0; i < Constants.NUMERO_MAX_HOJAS_EXCEL; i++) {
					
					try{
						// Nos colocamos en la primera hoja del documento Excel
						excelUtil.initSheet(i);
						
						// Nos pasamos a la segunda fila, ya que en el primero se encuentra la cabecera
						excelUtil.next();
					}catch(Exception e){
						// Si no encuentra la hoja iterada o no encuentra datos en la hoja, terminar la iteracion
						break;
					}
					
					noreList = new ArrayList();
					Map mapNore = null;				
					
					while(excelUtil.hasNext()){
						
						Map mapRow = excelUtil.next();
						mapNore = new HashMap();				
						fila = fila + 1;
						
						if (log.isDebugEnabled()) {
							log.debug("Carga BD No Reconstruida mapRow: "+fila+" - "+mapRow.toString());
						}
						
						BigDecimal nVenr = BigDecimal.ZERO;
						BigDecimal nVfdv = BigDecimal.ZERO;
						
						String bnrAnyo = (String.valueOf(mapRow.get("0"))).trim();
						String bnrZona = (String.valueOf(mapRow.get("1"))).trim();
						String bnrCamp = (String.valueOf(mapRow.get("2"))).trim();						
						String bnrVenr = (String.valueOf(mapRow.get("3"))).trim();
						String bnrVfdv = (String.valueOf(mapRow.get("4"))).trim();
						
						bnrAnyo = ValidationGenericUtil.validateNullString(bnrAnyo);
						bnrZona = ValidationGenericUtil.validateNullString(bnrZona);
						bnrCamp  = ValidationGenericUtil.validateNullString(bnrCamp);
						bnrVenr = ValidationGenericUtil.validateNullString(bnrVenr);
						bnrVfdv = ValidationGenericUtil.validateNullString(bnrVfdv);
						
						if(bnrZona != null){
							if(bnrZona.endsWith(".0")){
								bnrZona = bnrZona.substring(0, bnrZona.length()-2);
							}
						}
						
						if(bnrAnyo != null){
							if(bnrAnyo.endsWith(".0")){
								bnrAnyo = bnrAnyo.substring(0, bnrAnyo.length()-2);
							}
						}
						
						if(bnrCamp != null){
							bnrCamp = bnrCamp.substring(0, 2);
						}
						
						if(bnrVenr != null){
							try{							
								nVenr = new BigDecimal(bnrVenr);
							}catch(Exception pe){
								nVenr = BigDecimal.ZERO;
							}
						}
						
						if(bnrVfdv != null){
							try{							
								nVfdv = new BigDecimal(bnrVfdv);
							}catch(Exception pe){
								nVfdv = BigDecimal.ZERO;
							}
						}
						
						mapNore.put("procCodProc", codProc);
						mapNore.put("bnrZona", 	bnrZona);
						mapNore.put("bnrAnyo", 	bnrAnyo);
						mapNore.put("bnrCamp", 	bnrCamp);
						mapNore.put("bnrVenr", 	new BigDecimal(nVenr.toPlainString()));								
						mapNore.put("bnrVfdv",  new BigDecimal(nVfdv.toPlainString()));
						
						// Validando que si tiene valor en la zona, entonces toda la fila es valida
						if(StringUtils.isNotBlank(bnrZona)){
							noreList.add(mapNore);
						}
					}
					
					if(!noreList.isEmpty()){
						if(i == 0){
							procesoFDVFileNoreDAO.insertNoReconstruidaFDV(noreList, codProc, true);
						}else{
							procesoFDVFileNoreDAO.insertNoReconstruidaFDV(noreList, codProc, false);
						}
					}
				}
							
				excelUtil.cerrar();			
				
			} catch (Exception e) {
				
				if(excelUtil != null){
					excelUtil.cerrar();
				}
				
				// Si existe algun error debemos de borrar el archivo de la carpeta temporal
				borrarFichero(directorioTemp, fileNameNore);
				
				if(e.getCause() instanceof DataIntegrityViolationException){
					// Se lanzara una excepcion si existe algun fallo en la integridad de los datos
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameNore);
				}else if(e.getCause() instanceof UncategorizedSQLException){
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameNore);
				}else{
					throw e;
				}
			}
		}
	}
	
	/**
	 * Realiza la subida del archivo bd no reconstruida a un directorio temporal
	 * @param noReconstruidaFile
	 * @param directorioTemp
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private String uploadArchivoNoReconstruida(ProcesoFDVClusterizacion procesoFDVClusterizacion, 
			String directorioTemp) throws Exception{

		String fileName = null;
		File noReconstruidaFile = procesoFDVClusterizacion.getNoReconstruidaFile();
		FileOutputStream os = null;
		
		try{
			
			if(!"".equals(noReconstruidaFile.getName())){
		
				// verificamos que el archivo adjuntado sea del formato indicado
				if(!noReconstruidaFile.getName().endsWith(".xls")){
					throw new InvalidUploadException(noReconstruidaFile.getName(),"extension");
				}else{
					// modificamos el nombre del archivo y lo concatenamos con la fecha y hora actual para que sea unico
					String[] arrayName = noReconstruidaFile.getName().split(".xls");
					fileName = arrayName[0] + " " + DateUtil.getDateTimeNow("yyyyMMdd_HHmmss",
					new Date(System.currentTimeMillis())) + ".xls";
				}
				
				procesoFDVClusterizacion.setArcNore(fileName);
				
				// Leemos el stream de entrada
				InputStream is = new FileInputStream(noReconstruidaFile);   
				
				// Abrimos el stream de escritura, ubicacion al cual se grabara el archivo
				os = new FileOutputStream(new File(
				directorioTemp, procesoFDVClusterizacion.getArcNore()));
				
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
				throw new InvalidUploadException(noReconstruidaFile.getName(),"error");
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