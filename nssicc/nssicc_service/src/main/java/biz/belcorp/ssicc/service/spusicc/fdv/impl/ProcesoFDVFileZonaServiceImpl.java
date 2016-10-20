package biz.belcorp.ssicc.service.spusicc.fdv.impl;

import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileZonaDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileZonaService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessFileException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.ValidationGenericUtil;

/**
 * <p>
 * <a href="ProcesoFDVFileZonaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

@Service("spusicc.procesoFDVFileZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFDVFileZonaServiceImpl extends BaseService implements ProcesoFDVFileZonaService{

	@Resource(name = "spusicc.procesoFDVFileZonaDAO")
	private ProcesoFDVFileZonaDAO procesoFDVFileZonaDAO;

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
	 * @return Returns the procesoFDVFileZonaDAO.
	 */
	public ProcesoFDVFileZonaDAO getProcesoFDVFileZonaDAO() {
		return this.procesoFDVFileZonaDAO;
	}

	/**
     * 
	 * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#loadfileZonaExcel(
     * 		java.lang.String, java.lang.String, java.lang.String)
     */
	private void loadfileZonaExcel(String directorioTemp, String fileNameZona,
			String codProc) throws Exception {

		if(StringUtils.isNotBlank(fileNameZona)){
		
			ExcelUtil excelUtil = null;
			List zonasList = null;
			int fila = 1;
			
			try {
				
				// Abrimos el archivo Excel para su procesamiento
				excelUtil = new ExcelUtil(directorioTemp, fileNameZona);
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
					
									
					zonasList = new ArrayList();
					Map mapZonas = null;				
					
					while(excelUtil.hasNext()){
						
						Map mapRow = excelUtil.next();					
						mapZonas = new HashMap();				
						fila = fila + 1;
						
						if (this.log.isDebugEnabled()) {
							this.log.debug("Carga Zonas mapRow: "+fila+" - "+mapRow.toString());
						}
						
						Long nPediReal = new Long("0");
						Long nCapiReal = new Long("0");
						Long nIngrReal = new Long("0");
						Long nReinReal = new Long("0");
						Long nEgreReal = new Long("0");
						Long nActiReal = new Long("0");
						
						BigDecimal nVentReal = BigDecimal.ZERO;
						BigDecimal nAcdaReal = BigDecimal.ZERO;
						BigDecimal nPmnpReal = BigDecimal.ZERO;
						BigDecimal nPupReal = BigDecimal.ZERO;						
						BigDecimal nPpuReal = BigDecimal.ZERO;
		
						String bzoRegi = (String.valueOf(mapRow.get("0"))).trim();
						String bzoAnyo = (String.valueOf(mapRow.get("1"))).trim();
						String bzoZona = (String.valueOf(mapRow.get("2"))).trim();
						String bzoCamp = (String.valueOf(mapRow.get("3"))).trim();
						String bzoVentReal = (String.valueOf(mapRow.get("4"))).trim();
						String bzoPediReal = (String.valueOf(mapRow.get("5"))).trim();				
						String bzoAcdaReal = (String.valueOf(mapRow.get("6"))).trim();
						String bzoCapiReal = (String.valueOf(mapRow.get("7"))).trim();
						String bzoIngrReal = (String.valueOf(mapRow.get("8"))).trim();				
						String bzoReinReal = (String.valueOf(mapRow.get("9"))).trim();
						String bzoEgreReal = (String.valueOf(mapRow.get("10"))).trim();
						String bzoPupReal  = (String.valueOf(mapRow.get("11"))).trim();
						String bzoPpuReal  = (String.valueOf(mapRow.get("12"))).trim();
						String bzoPmnpReal = (String.valueOf(mapRow.get("13"))).trim();
						String bzoActiReal = (String.valueOf(mapRow.get("14"))).trim();
						
						bzoRegi 	= ValidationGenericUtil.validateNullString(bzoRegi);
						bzoAnyo 	= ValidationGenericUtil.validateNullString(bzoAnyo);
						bzoZona  	= ValidationGenericUtil.validateNullString(bzoZona);
						bzoCamp 	= ValidationGenericUtil.validateNullString(bzoCamp);
						bzoVentReal = ValidationGenericUtil.validateNullString(bzoVentReal);
						bzoPediReal = ValidationGenericUtil.validateNullString(bzoPediReal);					
						bzoAcdaReal = ValidationGenericUtil.validateNullString(bzoAcdaReal);
						bzoCapiReal = ValidationGenericUtil.validateNullString(bzoCapiReal);
						bzoIngrReal = ValidationGenericUtil.validateNullString(bzoIngrReal);
						bzoReinReal = ValidationGenericUtil.validateNullString(bzoReinReal);
						bzoEgreReal = ValidationGenericUtil.validateNullString(bzoEgreReal);
						bzoPupReal  = ValidationGenericUtil.validateNullString(bzoPupReal);
						bzoPpuReal  = ValidationGenericUtil.validateNullString(bzoPpuReal);					
						bzoPmnpReal = ValidationGenericUtil.validateNullString(bzoPmnpReal);
						bzoActiReal = ValidationGenericUtil.validateNullString(bzoActiReal);
						
						if(bzoVentReal != null){
							try{							
								nVentReal = new BigDecimal(bzoVentReal);
							}catch(Exception pe){
								nVentReal = BigDecimal.ZERO;
							}
						}
						
						if(bzoAcdaReal != null){						
							if(bzoAcdaReal.endsWith("%")){
								String[] array = bzoAcdaReal.split("%");
								bzoAcdaReal = array[0].trim();
							}
							
							try{
								nAcdaReal = new BigDecimal(bzoAcdaReal);
							}catch(Exception pe){
								nAcdaReal = BigDecimal.ZERO;
							}
						}
						
						if(bzoPmnpReal != null){
							try{
								nPmnpReal = new BigDecimal(bzoPmnpReal);
							}catch(Exception pe){
								nPmnpReal = BigDecimal.ZERO;
							}
						}
						
						if(bzoPediReal != null){
							
							if(bzoPediReal.endsWith(".0")){
								bzoPediReal = bzoPediReal.substring(0, bzoPediReal.length()-2);
							}
							
							try{
								nPediReal = new Long(bzoPediReal);
							}catch(Exception pe){
								nPediReal = new Long("0");
							}
						}
						
						if(bzoCapiReal != null){
							
							if(bzoCapiReal.endsWith(".0")){
								bzoCapiReal = bzoCapiReal.substring(0, bzoCapiReal.length()-2);
							}
							
							try{
								nCapiReal = new Long(bzoCapiReal);
							}catch(Exception pe){
								nCapiReal = new Long("0");
							}
						}
						
						if(bzoIngrReal != null){
							
							if(bzoIngrReal.endsWith(".0")){
								bzoIngrReal = bzoIngrReal.substring(0, bzoIngrReal.length()-2);
							}
							
							try{
								nIngrReal = new Long(bzoIngrReal);
							}catch(Exception pe){
								nIngrReal = new Long("0");
							}
						}
						
						if(bzoReinReal != null){
							
							if(bzoReinReal.endsWith(".0")){
								bzoReinReal = bzoReinReal.substring(0, bzoReinReal.length()-2);
							}
							
							try{
								nReinReal = new Long(bzoReinReal);
							}catch(Exception pe){
								nReinReal = new Long("0");
							}
						}
						
						if(bzoEgreReal != null){
							
							if(bzoEgreReal.endsWith(".0")){
								bzoEgreReal = bzoEgreReal.substring(0, bzoEgreReal.length()-2);
							}
							
							try{
								nEgreReal = new Long(bzoEgreReal);
							}catch(Exception pe){
								nEgreReal = new Long("0");
							}
						}
												
						if(bzoPupReal != null){
							try{
								nPupReal = new BigDecimal(bzoPupReal);
							}catch(Exception pe){
								nPupReal = BigDecimal.ZERO;
							}
						}
						
						if(bzoPpuReal != null){
							try{
								nPpuReal = new BigDecimal(bzoPpuReal);
							}catch(Exception pe){
								nPpuReal = BigDecimal.ZERO;
							}
						}
						
						if(bzoActiReal != null){
							
							if(bzoActiReal.endsWith(".0")){
								bzoActiReal = bzoActiReal.substring(0, bzoActiReal.length()-2);
							}
							
							try{
								nActiReal = new Long(bzoActiReal);
							}catch(Exception pe){
								nActiReal = new Long("0");
							}
						}
						
						if(bzoZona != null){
							if(bzoZona.endsWith(".0")){
								bzoZona = bzoZona.substring(0, bzoZona.length()-2);
							}
						}
						
						if(bzoAnyo != null){
							if(bzoAnyo.endsWith(".0")){
								bzoAnyo = bzoAnyo.substring(0, bzoAnyo.length()-2);
							}
						}
						
						if(bzoCamp != null){
							bzoCamp = bzoCamp.substring(0, 2);
						}
						
						mapZonas.put("procCodProc", codProc);
						mapZonas.put("bzoRegi", 	bzoRegi);
						mapZonas.put("bzoAnyo", 	bzoAnyo);
						mapZonas.put("bzoZona", 	bzoZona);
						mapZonas.put("bzoCamp", 	bzoCamp);
						mapZonas.put("bzoVentReal", new BigDecimal(nVentReal.toPlainString()));
						mapZonas.put("bzoPediReal", nPediReal);
						mapZonas.put("bzoAcdaReal", new BigDecimal(nAcdaReal.toPlainString()));
						mapZonas.put("bzoCapiReal", nCapiReal);
						mapZonas.put("bzoIngrReal", nIngrReal);
						mapZonas.put("bzoReinReal", nReinReal);
						mapZonas.put("bzoEgreReal", nEgreReal);
						mapZonas.put("bzoPupReal", 	new BigDecimal(nPupReal.toPlainString()));
						mapZonas.put("bzoPpuReal", 	new BigDecimal(nPpuReal.toPlainString()));
						mapZonas.put("bzoPmnpReal", new BigDecimal(nPmnpReal.toPlainString()));
						mapZonas.put("bzoActiReal", nActiReal);
						
						// Validando que si tiene valor en la zona, entonces toda la fila es valida
						if(StringUtils.isNotBlank(bzoZona)){
							zonasList.add(mapZonas);
						}
					}
					
					if(!zonasList.isEmpty()){
						if(i == 0){
							this.procesoFDVFileZonaDAO.insertZonasFDV(zonasList, codProc, true);
						}else{
							this.procesoFDVFileZonaDAO.insertZonasFDV(zonasList, codProc, false);
						}
					}
				}			
				
				excelUtil.cerrar();			
				
			} catch (Exception e) {
				
				if(excelUtil != null){
					excelUtil.cerrar();
				}
				
				// Si existe algun error debemos de borrar el archivo de la carpeta temporal
				this.borrarFichero(directorioTemp, fileNameZona);
				
				if(e.getCause() instanceof DataIntegrityViolationException){
					// Se lanzara una excepcion si existe algun fallo en la integridad de los datos
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameZona);					
				}else if(e.getCause() instanceof UncategorizedSQLException){
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), fileNameZona);
				}else{
					throw e;
				}			
			}
		}
	}

	public void saveFileZonas(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {

		String codProc = procesoFDVClusterizacion.getCodProc();
		String directorioTemp = procesoFDVClusterizacion.getDirectorioTemporal();
		
		// Subiendo los archivos excel
		String fileNameZona = null;
		boolean processZona = false;
		
		try{
			
			/*FormFile zonaFile = procesoFDVClusterizacion.getZonaFile();*/
			File zonaFile = procesoFDVClusterizacion.getZonaFile();
			if(!"".equals(zonaFile.getName())){
						
				// EXCEL DE ZONAS
				fileNameZona = this.uploadArchivoZonas(procesoFDVClusterizacion, directorioTemp);
				this.loadfileZonaExcel(directorioTemp, fileNameZona, codProc);
				
				processZona = true;
				procesoFDVClusterizacion.setArcZona(fileNameZona);			
			}
			
		}catch(Exception e){
			
			if(!processZona){
				fileNameZona = null;
			}
			
			procesoFDVClusterizacion.setArcZona(fileNameZona);
			throw e;			
		}
	}

	/**
     * Adjunta el archivo de BD Zonas, en una carpeta temporal definida por el pais
     * y una vez hecho esto lee el archivo e inserta los datos en su respectiva tabla
     * 
     * @param 
     * 		procesoFDVClusterizacion
     * 		usuario
     */
	public void saveFileZonasAndProcesoCluster(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception{

		// Generamos el siguiente correlativo
		String codProc = this.procesoFDVFileZonaDAO.getNextID();
		String directorioTemp = procesoFDVClusterizacion.getDirectorioTemporal();

		try {
			// Verificamos que no exista un proceso con el mismo codigo
			this.procesoFDVFileZonaDAO.getProcesoCluster(codProc);
            throw new InvalidIdentifierException(ProcesoFDVClusterizacion.class, codProc);         
		}catch (ObjectRetrievalFailureException orfe){}
		
		//Verificamos que venga un valor en la descripcion
		if(StringUtils.isBlank(procesoFDVClusterizacion.getNomProc())){
			throw new InvalidDescriptionException(ProcesoFDVClusterizacion.class, procesoFDVClusterizacion.getNomProc());
		}
		
		// Verificamos que no exista un proceso con la misma descripción
        // Creamos el map que nos servirá como criterio de busqueda
		
		Map criteria = new HashMap();
		criteria.put("nomProc", procesoFDVClusterizacion.getNomProc());
		criteria.put("codigoPais", procesoFDVClusterizacion.getCodigoPais());
		List listProcesos = this.procesoFDVFileZonaDAO.getProcesosClusterByCriteria(criteria);
		if (listProcesos != null && listProcesos.size() > 0) {
            throw new InvalidDescriptionException(ProcesoFDVClusterizacion.class, procesoFDVClusterizacion.getNomProc());
        }
		
		// Realizamos el insert a la tabla principal
        try{
        	
        	procesoFDVClusterizacion.setArcZona(null);
        	procesoFDVClusterizacion.setArcSecc(null);
        	procesoFDVClusterizacion.setArcVari(null);
        	procesoFDVClusterizacion.setArcNore(null);
        	procesoFDVClusterizacion.setCodProc(codProc);
			procesoFDVClusterizacion.setEstProc(Constants.ESTADO_ACTIVO);
			procesoFDVClusterizacion.setStaProc(Constants.PROCESO_CLUSTER_COD_NUEVO);
			
			this.procesoFDVFileZonaDAO.insertProcesoClusterizacion(procesoFDVClusterizacion, usuario); // fdv_proc
			this.procesoFDVFileZonaDAO.insertProcesoParametro(procesoFDVClusterizacion); // fdv_proc_para
		
		}catch(DataIntegrityViolationException e){
			
			// Limpiando el campo del ID en caso de excepcion
			procesoFDVClusterizacion.setCodProc(null);
			
			// Se lanzara una excepcion si existe algun fallo en la integridad de los datos
			throw new InvalidTransactionProcessException(
			ProcesoFDVClusterizacion.class, e.getCause().getCause().getMessage(), "insertar");			
		}
		
		// Subiendo los archivos excel
		String fileNameZona = null;
		boolean processZona = false;
		
		try{			
		
			File zonaFile = procesoFDVClusterizacion.getZonaFile();
			if(!"".equals(zonaFile.getName())){
			
				// EXCEL DE ZONAS
				fileNameZona = this.uploadArchivoZonas(procesoFDVClusterizacion, directorioTemp);
				this.loadfileZonaExcel(directorioTemp, fileNameZona, codProc);
				
				processZona = true;			
				procesoFDVClusterizacion.setArcZona(fileNameZona);
			
			}
			
		}catch(Exception e){
			
			if(!processZona){
				fileNameZona = null;
			}
			
			// Limpiando el campo del ID en caso de excepcion
			procesoFDVClusterizacion.setCodProc(null);
			procesoFDVClusterizacion.setArcZona(fileNameZona);
			
			throw e;			
		}
	}
	
	/**
     * Setea el DAO para la comunicación con la capa de persistencia.
     * 
     * @param procesoFDVFileZonaDAO
     */
	public void setProcesoFDVFileZonaDAO(ProcesoFDVFileZonaDAO procesoFDVFileZonaDAO) {
		this.procesoFDVFileZonaDAO = procesoFDVFileZonaDAO;
	}

	/**
	 * Realiza la subida del archivo zonas a un directorio temporal
	 * @param zonaFile
	 * @param directorioTemp
	 * @throws Exception 
	 */
	private String uploadArchivoZonas(ProcesoFDVClusterizacion procesoFDVClusterizacion, 
			String directorioTemp) throws Exception{

		String fileName = null;
		//FormFile zonaFile = procesoFDVClusterizacion.getZonaFile();
		File zonaFile = procesoFDVClusterizacion.getZonaFile();
		FileOutputStream os = null;
		
		try{
		
			// verificamos que el archivo adjuntado sea del formato indicado
			if(!zonaFile.getName().endsWith(".xls")){
				throw new InvalidUploadException(zonaFile.getName(),"extension");
			}else{
				// modificamos el nombre del archivo y lo concatenamos con la fecha y hora actual para que sea unico
				String[] arrayName = zonaFile.getName().split(".xls");
				fileName = arrayName[0] + " " + DateUtil.getDateTimeNow("yyyyMMdd_HHmmss",
				new Date(System.currentTimeMillis())) + ".xls";
			}
			
			procesoFDVClusterizacion.setArcZona(fileName);
			
			// Leemos el stream de entrada
			InputStream is =  new FileInputStream(zonaFile);  
			
			// Abrimos el stream de escritura, ubicacion al cual se grabara el archivo
			os = new FileOutputStream(new File(
			directorioTemp, procesoFDVClusterizacion.getArcZona()));
			
			// Grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			
			os.close();
			
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
				throw new InvalidUploadException(zonaFile.getName(),"error");
			}
		}
	}
}