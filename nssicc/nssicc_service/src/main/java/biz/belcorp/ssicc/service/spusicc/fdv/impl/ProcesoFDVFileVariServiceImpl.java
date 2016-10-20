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
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileVariDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileVariService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessFileException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.ValidationGenericUtil;

/**
 * <p>
 * <a href="ProcesoFDVFileVariServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

@Service("spusicc.procesoFDVFileVariService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFDVFileVariServiceImpl extends BaseService implements ProcesoFDVFileVariService{

	@Resource(name = "spusicc.procesoFDVFileVariDAO")
	private ProcesoFDVFileVariDAO procesoFDVFileVariDAO;

	/**
	 * elimina el fichero del temporal
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			
			if (this.log.isDebugEnabled()) {
				this.log.debug("Se elimino el archivo");
			}
		}	
		catch(Exception ex) {
			
			if (this.log.isDebugEnabled()) {
				this.log.debug("No se pudo eliminar el archivo "+ex.getMessage());
			}
		}
	}

	/**
	 * @return Returns the procesoFDVFileVariDAO.
	 */
	public ProcesoFDVFileVariDAO getProcesoFDVFileVariDAO() {
		return this.procesoFDVFileVariDAO;
	}

	/**
     * 
	 * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVFileVariService#loadfileVariExcel(
     * 		java.lang.String, java.lang.String, java.lang.String)
     */
	private void loadfileVariExcel(String directorioTemp, String fileNameVari,
			String codProc) throws Exception {
		
		if(StringUtils.isNotBlank(fileNameVari)){
			
			List varList = null;
			ExcelUtil excelUtil = null;
			int fila = 1;
			
			try {
				
				// Abrimos el archivo Excel para su procesamiento
				excelUtil = new ExcelUtil(directorioTemp, fileNameVari);
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
					
					varList = new ArrayList();
					Map mapVari = null;				
					
					while(excelUtil.hasNext()){
						
						Map mapRow = excelUtil.next();
						mapVari = new HashMap();				
						fila = fila + 1;
						
						if (this.log.isDebugEnabled()) {
							this.log.debug("Carga Variables Exog. mapRow: "+fila+" - "+mapRow.toString());
						}
						
						Long nPobl = new Long("0");
						BigDecimal nPoblacion = BigDecimal.ZERO;
						
						String bvxZona = (String.valueOf(mapRow.get("0"))).trim();
						String bvxPobl = (String.valueOf(mapRow.get("1"))).trim();
						String bvxNse  = (String.valueOf(mapRow.get("2"))).trim();
						String bvxRlur = (String.valueOf(mapRow.get("3"))).trim();
						String grpPoblUsua = (String.valueOf(mapRow.get("4"))).trim();
						String cluUsua = (String.valueOf(mapRow.get("5"))).trim();
						
						bvxZona = ValidationGenericUtil.validateNullString(bvxZona);
						bvxPobl = ValidationGenericUtil.validateNullString(bvxPobl);
						bvxNse  = ValidationGenericUtil.validateNullString(bvxNse);
						bvxRlur = ValidationGenericUtil.validateNullString(bvxRlur);
						grpPoblUsua = ValidationGenericUtil.validateNullString(grpPoblUsua);
						cluUsua = ValidationGenericUtil.validateNullString(cluUsua);

						if(bvxPobl != null){
														
							try{							
								nPoblacion = new BigDecimal(bvxPobl);								
							}catch(Exception pe){
								nPoblacion = BigDecimal.ZERO;
							}
							
							nPobl = nPoblacion.setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue();							
						}
						
						if(bvxZona != null){
							if(bvxZona.endsWith(".0")){
								bvxZona = bvxZona.substring(0, bvxZona.length()-2);
							}
						}
						
						if(Constants.MUY_PEQUEÑO.equalsIgnoreCase(grpPoblUsua)){
							grpPoblUsua = Constants.UNO;
						}
						if(Constants.PEQUEÑO.equalsIgnoreCase(grpPoblUsua)){
							grpPoblUsua = Constants.DOS;
						}
						if(Constants.MEDIANO.equalsIgnoreCase(grpPoblUsua)){
							grpPoblUsua = Constants.TRES;
						}
						if(Constants.GRANDE.equalsIgnoreCase(grpPoblUsua)){
								grpPoblUsua = Constants.CUATRO;
						}
						if(Constants.MUY_GRANDE.equalsIgnoreCase(grpPoblUsua)){
								grpPoblUsua = Constants.CINCO;
						}

						mapVari.put("procCodProc", codProc);
						mapVari.put("bvxZona", 	bvxZona);
						mapVari.put("bvxPobl", 	nPobl);
						mapVari.put("bvxNse", 	bvxNse);
						mapVari.put("bvxRlur", 	bvxRlur);								
						mapVari.put("grpPoblUsua",  grpPoblUsua);
						mapVari.put("cluUsua",  cluUsua);
						
						// Validando que si tiene valor en la zona, entonces toda la fila es valida
						if(StringUtils.isNotBlank(bvxZona)){
							varList.add(mapVari);
						}
					}
					
					if(!varList.isEmpty()){
						if(i == 0){
							this.procesoFDVFileVariDAO.insertVariablesExogFDV(varList, codProc, true);
						}else{
							this.procesoFDVFileVariDAO.insertVariablesExogFDV(varList, codProc, false);
						}
					}
				}
							
				excelUtil.cerrar();			
				
			} catch (Exception e) {
				
				if(excelUtil != null){
					excelUtil.cerrar();
				}
				
				// Si existe algun error debemos de borrar el archivo de la carpeta temporal
				this.borrarFichero(directorioTemp, fileNameVari);
				
				if(e.getCause() instanceof DataIntegrityViolationException){
					// Se lanzara una excepcion si existe algun fallo en la integridad de los datos
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameVari);
				}else if(e.getCause() instanceof UncategorizedSQLException){
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameVari);
				}else{
					throw e;
				}
			}
		}
	}
	
	/**
     * Adjunta el archivo de BD Variables Exog., en una carpeta temporal definida por el pais
     * y una vez hecho esto lee el archivo e inserta los datos en su respectiva tabla
     * 
     * @param 
     * 		procesoFDVClusterizacion
     * 		usuario
     */
	public void saveFileVariablesExog(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {

		String codProc = procesoFDVClusterizacion.getCodProc();
		String directorioTemp = procesoFDVClusterizacion.getDirectorioTemporal();
		
		// Subiendo los archivos excel
		String fileNameVari = null;
		boolean processVari = false;
		
		try{
			
			File variablesExogFile = procesoFDVClusterizacion.getVariablesExogFile();
			if(!"".equals(variablesExogFile.getName())){
				
				// EXCEL DE VARIABLES EXOGENAS
				fileNameVari = this.uploadArchivoVariablesExog(procesoFDVClusterizacion, directorioTemp);
				this.loadfileVariExcel(directorioTemp, fileNameVari, codProc);
				
				processVari = true;
	        	procesoFDVClusterizacion.setArcVari(fileNameVari);
			}
			
		}catch(Exception e){
			
			if(!processVari){
				fileNameVari = null;
			}
			
        	procesoFDVClusterizacion.setArcVari(fileNameVari);			
			throw e;			
		}
	}
	
	/**
     * Setea el DAO para la comunicación con la capa de persistencia.
     * 
     * @param procesoFDVFileVariDAO
     */
	public void setProcesoFDVFileVariDAO(ProcesoFDVFileVariDAO procesoFDVFileVariDAO) {
		this.procesoFDVFileVariDAO = procesoFDVFileVariDAO;
	}
	
	/**
	 * Realiza la subida del archivo variables exogenas a un directorio temporal
	 * @param variablesExogFile
	 * @param directorioTemp
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private String uploadArchivoVariablesExog(ProcesoFDVClusterizacion procesoFDVClusterizacion, 
			String directorioTemp) throws Exception{

		String fileName = null;
		File variablesExogFile = procesoFDVClusterizacion.getVariablesExogFile();
		FileOutputStream os = null;
		
		try{
			
			if(!"".equals(variablesExogFile.getName())){
		
				// verificamos que el archivo adjuntado sea del formato indicado
				if(!variablesExogFile.getName().endsWith(".xls")){
					throw new InvalidUploadException(variablesExogFile.getName(),"extension");
				}else{
					// modificamos el nombre del archivo y lo concatenamos con la fecha y hora actual para que sea unico
					String[] arrayName = variablesExogFile.getName().split(".xls");
					fileName = arrayName[0] + " " + DateUtil.getDateTimeNow("yyyyMMdd_HHmmss",
					new Date(System.currentTimeMillis())) + ".xls";
				}
				
				procesoFDVClusterizacion.setArcVari(fileName);
				
				// Leemos el stream de entrada
				InputStream is = new FileInputStream(variablesExogFile);
				
				// Abrimos el stream de escritura, ubicacion al cual se grabara el archivo
				os = new FileOutputStream(new File(
				directorioTemp, procesoFDVClusterizacion.getArcVari()));
				
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
				throw new InvalidUploadException(variablesExogFile.getName(),"error");
			}
		}
	}
}