package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPremiosExcelDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaPremiosExcelService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 *
 */
@Service("spusicc.procesoINCCargaPremiosExcelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCargaPremiosExcelServiceImpl extends BaseService implements
	ProcesoINCCargaPremiosExcelService {
	
	@Resource(name="spusicc.procesoINCCargaPremiosExcelDAO")
	private ProcesoINCCargaPremiosExcelDAO procesoINCCargaPremiosExcelDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#getListConcursoCreadosVigentes()
	 */
	public List getListConcursoCreadosVigentes() {
		return this.procesoINCCargaPremiosExcelDAO.getListConcursoCreadosVigentes();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#validarArchivoExcel(java.util.Map)
	 */
	public boolean validarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		boolean valor = true;
		String codConcurso = "";
	    //Abrimos el archivo Excel para su procesamiento
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);	
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();	
			
			log.debug("Cant Columnas :"+mapRow.size());
			if(mapRow.size()<=1 || mapRow.size()>7) {
				 //las columnas recogidas + el numero de fila de fila procesda
				 //EL ARCHIVO AHORA CONTIENE DOS ESTRUCTURAS DE 1 CAMPOS Y 5
				 valor = false;
				 break;
			}
			
		}
		excelUtil.cerrar();
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#cargarArchivoExcel(java.util.Map)
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception {
		List listMapFila = new ArrayList();
		ArrayList lstCarga = null;
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));
		String mensajeError=null;
		String numRegError="";
		String numNivFaltan="";
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int fila=0;
		boolean errorPorFila =false;
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			fila +=1;
			
			String codigoConcurso="";
			String numeroNivel="";
			String numeroPremio="";
			String codigoProducto = new String();
			String numeroUnidades="";
			
			codigoConcurso = (String)mapRow.get("0");
			numeroNivel = (String)mapRow.get("1");
			numeroPremio = (String)mapRow.get("2");
			codigoProducto = mapRow.get("3").toString();
			numeroUnidades = (String)mapRow.get("4");
			
			//log.debug("Fila [" + fila +"] " + "Codigo Concurso :"+codigoConcurso);
			//log.debug("Fila [" + fila +"] " + "Nmero Nivel :"+numeroNivel);
			//log.debug("Fila [" + fila +"] " + "Nmero Premio :"+numeroPremio);
			//log.debug("Fila [" + fila +"] " + "Cdigo Producto :"+codigoProducto);
			//log.debug("Fila [" + fila +"] " + "Nmero Unidades :"+numeroUnidades);
			
			 if(!validoFormatoNumero(numeroNivel)){
				 mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noFormatoValidoNivel",null,getLocale(usuario));
				 errorPorFila = true;
				 //break;
			 }

			log.debug("Numero Premio Logitud:"+numeroPremio.length()+" Num Premio: "+numeroPremio);
			 if (numeroPremio.length()>4){
				 mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noFormatoValidoPremio",null,getLocale(usuario));
				 errorPorFila = true;
			 }
				 
			 if(!validoFormatoNumero(numeroPremio)){
				 mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noFormatoValidoPremio",null,getLocale(usuario));
				 errorPorFila = true;
			 }
			
			 if(!validoFormatoNumero(numeroUnidades)){
				 mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noFormatoValidoUnidad",null,getLocale(usuario));
				 errorPorFila = true;
			 }
			
			if (errorPorFila!=true){
				criteria.put("codigoConcurso",codigoConcurso);
				criteria.put("numeroNivel",numeroNivel);
				criteria.put("numeroPremio",numeroPremio);
				criteria.put("codigoSAP",codigoProducto);
				criteria.put("numeroUnidad",numeroUnidades);
			
				// Realizamos la insercin fila por fila a la tabla temporal
				executeInsercionTempCargaPremios(criteria);
				String indicadorCarga = Constants.NUMERO_UNO;
			}else{
				String indicadorCarga = Constants.NUMERO_CERO;
			}
			
		}
		
		excelUtil.cerrar();
		Map params = null;
		
		log.debug("Error por Fila :"+errorPorFila);
		
		if (errorPorFila!=true){
			
			// Ejecutamos el proceso de validacin de la carga de premios
			
			executeProcesoValidaCargaPremios(criteria);
			
			numRegError = (String)criteria.get("numRegError");
			
			numNivFaltan = (String)criteria.get("numNivFaltan"); 
			
			
			// Listamos los errores de la carga de premios
			lstCarga = (ArrayList)getListarErroresCargaPremios(criteria);
			
			Map parametro = null;
			
			for (int i=0;i<lstCarga.size();i++){
				params = new HashMap();
				parametro = (Map)lstCarga.get(i);
				params.put("numeroFila", i+1);
								
				if (parametro.get("codigoError").equals("01")){
					mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noValidoConcurso",null,getLocale(usuario));
					params.put("mensajeError", mensajeError);
				}
				if (parametro.get("codigoError").equals("02")){
					mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noValidoNivel",null,getLocale(usuario));
					params.put("mensajeError", mensajeError);
				}
				if (parametro.get("codigoError").equals("03")){
					mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noValidoPremio",null,getLocale(usuario));
					params.put("mensajeError", mensajeError);
				}
				if (parametro.get("codigoError").equals("04")){
					mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.noValidoProducto",null,getLocale(usuario));
					params.put("mensajeError", mensajeError);
				}
				if (parametro.get("codigoError").equals("05")){
					mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.premioIncorrecto",null,getLocale(usuario));
					params.put("mensajeError", mensajeError);
				}
				if (parametro.get("codigoError").equals("06")){
					mensajeError = messageSource.getMessage("procesoINCCargaPremiosExcelForm.error.unidadInvalida",null,getLocale(usuario));
					params.put("mensajeError", mensajeError);
				}
				params.put("codigoConcurso",(String)parametro.get("codigoConcurso"));
				params.put("numNivel", (String)parametro.get("numNivel"));
				params.put("numPremio", (String)parametro.get("numPremio"));
				params.put("codigoProducto", (String)parametro.get("codigoProducto"));
				
				listMapFila.add(params);
				
			}
	
			criteria.put("numErrores", numRegError);
			criteria.put("numNivFalta", numNivFaltan);
			
			
		}else{
			params = new HashMap();
			params.put("codigoConcurso","");
			params.put("numNivel", "");
			params.put("numPremio", "");
			params.put("codigoProducto", "");
			params.put("mensajeError", mensajeError);
			
			listMapFila.add(params);
			
			criteria.put("numErrores", "1");
			criteria.put("numNivFalta", "0");
			criteria.put("numRegTotal","0");
			
		}
		return listMapFila;

	}

	/**
	 * Se valida si el valor tiene formato nmerico
	 * @param numeroPuntaje
	 * @return
	 */
	private boolean validoFormatoNumero(String numero) {
		boolean valor=true;
		try{
			Double.parseDouble(numero);
		}catch(Exception e){
			valor=false;
		}
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#executeInsercionTempCargaPremios(java.util.Map)
	 */
	public void executeInsercionTempCargaPremios(Map criteria) {
		this.procesoINCCargaPremiosExcelDAO.executeInsercionTempCargaPremios(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#getObtenerSecTempCargaPremios()
	 */
	public String getObtenerSecTempCargaPremios() {
		return this.procesoINCCargaPremiosExcelDAO.getObtenerSecTempCargaPremios();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#executeProcesoValidaCargaPremios(java.util.Map)
	 */
	public void executeProcesoValidaCargaPremios(Map criteria) {
		this.procesoINCCargaPremiosExcelDAO.executeProcesoValidaCargaPremios(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#getListarErroresCargaPremios(java.util.Map)
	 */
	public List getListarErroresCargaPremios(Map criteria) {
		return this.procesoINCCargaPremiosExcelDAO.getListarErroresCargaPremios(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#executeProcesoCargaPremiosExcel(java.util.Map)
	 */
	public void executeProcesoCargaPremiosExcel(Map criteria) {
		this.procesoINCCargaPremiosExcelDAO.executeProcesoCargaPremiosExcel(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPremiosExcelService#eliminarRegistrosTablaTempINCCargaPremios(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempINCCargaPremios(Map criteria) {
		this.procesoINCCargaPremiosExcelDAO.eliminarRegistrosTablaTempINCCargaPremios(criteria);
	}
	
}
