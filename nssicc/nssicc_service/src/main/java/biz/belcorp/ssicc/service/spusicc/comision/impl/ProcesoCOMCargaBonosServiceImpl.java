package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMBonosDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCargaBonosDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCargaBonosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.procesoCOMCargaBonosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOMCargaBonosServiceImpl extends BaseService implements
	ProcesoCOMCargaBonosService {
	
	@Resource(name="spusicc.procesoCOMCargaBonosDAO")
	private ProcesoCOMCargaBonosDAO procesoCOMCargaBonosDAO;	
	
	@Resource(name="spusicc.mantenimientoCOMBonosDAO")
	private MantenimientoCOMBonosDAO mantenimientoCOMBonosDAO;
	
	



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCargaBonosService#cargarArchivoExcel(java.util.Map)
	 */
	public void executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = null;
		int numfila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = getParseRow(excelUtil.next());//caso de que las columnas vengan tipo numericos en region y zona
			 ++numfila;
			//Recuperamos  el registro del excel			
			 DetalleBonos detalle = new DetalleBonos();
			 detalle.setCodigoPais(codigoPais);
			 detalle.setCodigoConcepto((String)mapRow.get("0"));
			 if(numfila==1)
				 procesoCOMCargaBonosDAO.deleteDetalleBonos(detalle);
			 //validaciones
			 String mensajeError=null;
			   if(isValidoRegistro(list,mapRow)){//es valido cuando el registro no existe en la lista
				    mapRow.put("codigoPais",codigoPais);
				    if(list==null) list = new ArrayList();
				    if(StringUtils.isNotEmpty((String)mapRow.get("0")))
				    	list.add(mapRow);	
			   }else{//existe duplicados
					 mensajeError = messageSource.getMessage("procesoCOMCargaBonosForm.error.existenDuplicados",
							 new String[]{(String)mapRow.get("0"),(String)mapRow.get("1"),(String)mapRow.get("2")},
							 getLocale(usuario));
					 excelUtil.cerrar();
					 throw new Exception(mensajeError);
				}				 			 
		}
		excelUtil.cerrar();
		//hacemos el proceso de registro en batch
		procesoCOMCargaBonosDAO.insertListDetalleBonos(list);
	}



	/**
	 * se encarga de parsear el valor correcto de region y zona
	 * @param next
	 * @return
	 */
	private Map getParseRow(Map mapRow) {
		 Map param = new HashMap();
		 String codigoRegion =(String)mapRow.get("1");//puede venir 10.0 o 10
		 String codigoZona =  (String)mapRow.get("2");//puede venir 1011.0 o 1011
		 
		 param.put("0",mapRow.get("0"));//concepto 04,01... igual, no hay parseo
		 param.put("1",codigoRegion.substring(0,codigoRegion.indexOf(".")!=-1?codigoRegion.indexOf("."):codigoRegion.length()));
		 param.put("2",codigoZona.substring(0,codigoZona.indexOf(".")!=-1?codigoZona.indexOf("."):codigoZona.length()));
		 param.put("3",mapRow.get("3"));//SECCION :A, B,C igual no hay parseo
		 param.put("4",mapRow.get("4"));//MONTO : 15.26, igual no hay parseo
		return param;
	}

	/**
	 * Retorna true cuando el registro es valido es decir no existe repetido, retorna false
	 * cuando el registro esta duplicado
	 * @param list
	 * @param mapRow
	 * @return
	 */
	private boolean isValidoRegistro(List list, Map mapRow) {
		 boolean valor=true;
		 String codigoConcepto = (String)mapRow.get("0");
		 String codigoRegion =(String)mapRow.get("1");
		 String codigoZona =  (String)mapRow.get("2");
		 String codigoSeccion = (String)mapRow.get("3");
		 if(list!=null && list.size()>0){
			 Iterator it= list.iterator();
			 while(it.hasNext()){
				 Map params = (Map)it.next();
				 if(codigoConcepto.equals(params.get("0")) &&
					codigoRegion.equals(params.get("1"))&&
					codigoZona.equals(params.get("2"))&&
					codigoSeccion.equals(params.get("3"))){
					 valor=false;
					 break;
				 }
			 }	 
		 }else{
			 return valor;
		 }		 
		 return valor;//hay duplicado false , caso contrario true
	}

	/**
	 * Valida si concepto existe en tabla de detalle de bonos
	 * @param mapRow
	 * @param codigoPais
	 * @return
	 */
	private boolean isValidoCodigoConceptoEnDetalle(Map mapRow,String codigoPais) {		
		DetalleBonos detalleBonos = new DetalleBonos();
		detalleBonos.setCodigoPais(codigoPais);
		detalleBonos.setCodigoConcepto((String)mapRow.get("0"));
		List list=mantenimientoCOMBonosDAO.getListDetalleBonos(detalleBonos);		
		return list.size()>0 ?true:false;
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#validarArchivoExcel(java.util.Map)
	 */
	public boolean validarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		boolean valor = true;
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();		
			 if(mapRow.size()>6) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }			
		}
		excelUtil.cerrar();
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCargaBonosService#validarCargaExcel(java.util.Map)
	 */
	public boolean validarCargaExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);		
		if(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			 //validaciones
			 String mensajeError=null;
			 if(existeCodigoConcepto(mapRow,codigoPais)){
				 if(isValidoCodigoConceptoEnDetalle(mapRow,codigoPais)){//si existe se tendra que preguntar si se continua
					 criteria.put("codigoConcepto", mapRow.get("0"));
					 return true;
				 }
			 }else{
			 	mensajeError = messageSource.getMessage("procesoCOMCargaBonosForm.error.noExisteConcepto",new String[]{(String)mapRow.get("0")},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			 }	
		}
		excelUtil.cerrar();
		return false;//no existe detalle se sigue con la craga
	}

	/**
	 * Retorna true si concepto existe en Bonos cabecera
	 * @param mapRow
	 * @param codigoPais
	 * @return
	 */
	private boolean existeCodigoConcepto(Map mapRow,String codigoPais) {
		Map bonos = new HashMap();
		bonos.put("codigoPais",codigoPais);
		bonos.put("codigoConcepto",(String)mapRow.get("0"));
		List list=mantenimientoCOMBonosDAO.getBonosEjecutivas(bonos);		
		return list.size()>0 ?true:false;
		
	}
	
}
