package biz.belcorp.ssicc.service.spusicc.lideres.impl;

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
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

@Service("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDGenerarNumeroPedidosSeccionPeriodoServiceImpl extends BaseService implements ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService {

	@Resource(name="spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO")
	ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO;
	

	/**
	 * @param params
	 * @return
	 */
	public boolean verificaRegionProcesada(Map params) {
		return procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.verificaRegionProcesada(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService#executeGenerarNumeroPedidosSeccionPeriodo(java.util.Map)
	 */
	public void executeGenerarNumeroPedidosSeccionPeriodo(Map params) {
		procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.executeGenerarNumeroPedidosSeccionPeriodo(params);
		
	}

	/**
	 * @return Returns the procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.
	 */
	public ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO getProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO() {
		return procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO;
	}

	/**
	 * @param procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO The procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO to set.
	 */
	public void setProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO(
			ProcesoLIDGenerarNumeroPedidosSeccionPeriodoDAO procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO) {
		this.procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO = procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService#executeCargaArchivoExcel(java.util.Map)
	 */
	public void executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String codigoPais= (String)criteria.get("codigoPais");
		String codigoPeriodo= (String)criteria.get("codigoPeriodo");
		String  [] codigoRegion= (String  [])criteria.get("codigoRegion");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoMarca = (String)criteria.get("codigoMarca");
		String codigoCanal = (String)criteria.get("codigoCanal");
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		int i=1;
		List list = new ArrayList();
		String mensajeError=null;

		//procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.deleteTemporalCargaPedidosObjetivos();
		
		while(excelUtil.hasNext()) {
			Map mapRow = getParseRow(excelUtil.next());//caso de que las columnas vengan tipo numericos en region y zona
			mapRow.put("numFila", i);
			
			mapRow.put("codigoMarca", codigoMarca);
			mapRow.put("codigoCanal", codigoCanal);
			
			//validacion pais
			if(!codigoPais.equals(mapRow.get("codigoPais"))){
				mensajeError = i + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.codigoPais",null,getLocale(usuario));
				throw new Exception(mensajeError);
			}
			//validacion periodo
			if(!codigoPeriodo.equals(mapRow.get("codigoPeriodo"))){
				mensajeError = i + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.codigoPeriodo",null,getLocale(usuario));
				throw new Exception(mensajeError);
			}
			//validacion region
			if( !isRegionInArray((String)mapRow.get("codigoRegion"),codigoRegion)){
				mensajeError = i + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.codigoRegion",null,getLocale(usuario));
				throw new Exception(mensajeError);
			}
			//validacion zona exist y activa
//			if(!isZonaValida((String)mapRow.get("codigoZona"),(String)mapRow.get("codigoRegion"))){
//				mensajeError = i + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.codigoZona",null,getLocale(usuario));
//				throw new Exception(mensajeError);
//			}else{
//				// validacion seccion exista y activa
//				if(!isSeccionValida(mapRow)){
//					mensajeError = i + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.codigoSeccion",null,getLocale(usuario));
//					throw new Exception(mensajeError);
//				}else{
//					if(!isZonaNuevaRezonificada(mapRow) && !isSeccioNueva(mapRow)){
//						mensajeError = i + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.codigoSeccion.codigoZona.NoNueva.NoRezonificada",null,getLocale(usuario));
//						throw new Exception(mensajeError);
//					}
//				}
//			}
			
			//validacion de no registros duplicados
			if(list.size() >0){
				String rangoFilasDuplicadas = getRangoDuplicadas(i,list,(String)mapRow.get("codigoZona"),(String)mapRow.get("codigoSeccion"));
				if(StringUtils.isNotEmpty(rangoFilasDuplicadas)){
					mensajeError = rangoFilasDuplicadas + ": "+messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.rango.duplicado",null,getLocale(usuario));
					throw new Exception(mensajeError);
				}
			}
			
			//procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.insertTemporalCargaPedidosObjetivos(mapRow);
			
			list.add(mapRow);
				 
			i++;
		}
		
		/*Map params = new HashMap();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("codigoMarca", codigoMarca);
		
		procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.executeValidacionPedidosEstimados(params);
		
		String codigoRetorno = (String)params.get("codigoRetorno");
		String codigoZona = (String)params.get("codigoZona");*/
		
		//if(codigoRetorno.equals("0")){			
			for(int x=0;x<list.size();x++){
				Map mapRow = (Map)list.get(x);
				//verificar si ya existe pedido objetivo para seccion
				if(!isRegistroValido(mapRow)){//no es valido si encuentra registro
					procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.updateSeccionNumeroPedido(mapRow);
				}else{
					procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.insertSeccionNumeroPedido(mapRow);
				}
			}			
		/*}else{
			mensajeError = messageSource.getMessage("procesoLIDCargaPedidosObjetivoForm.error.pedidosEstimados",null,getLocale(usuario))+" "+codigoZona;
			throw new Exception(mensajeError);
		}*/
			
		excelUtil.cerrar();
		//hacemos el proceso de validacion y actualizacion
		//procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.executeCargaPedidosObjetivos(criteria);
	}

	/**
	 * Retorna null si es que no existe duplicado por agrpacion de zona y seccion, caso contraio devuelve las
	 * filas que hay duplicado 
	 * @param fila
	 * @param list
	 * @param zona
	 * @param seccion
	 * @return
	 */
	private String getRangoDuplicadas(int fila,List list, String zona, String seccion) {
		String rango="";
		if(list.size() == 0) return rango;
		
		Iterator it= list.iterator();
		int i=1;
		while (it.hasNext()){
			Map map = (Map)it.next();
			String codigoZona =(String)map.get("codigoZona");
			String codigoSeccion=(String)map.get("codigoSeccion");
			
			if(codigoSeccion.equals(seccion) &&
					codigoZona.equals(zona)){
				//hay registro duplicado
				rango = i +", "+fila;
				return rango;
			}
			i++;
		}
		return rango;
	}

	/**
	 * Retorna true si es que no existe el registro, caso contraio false
	 * @param mapRow
	 * @return
	 */
	private boolean isRegistroValido(Map mapRow) {
		Integer val=procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.getPedidoObjetivo(mapRow);
		return (val==0?true:false);
	}

	/**
	 * Valida si la seccion es valida
	 * @param string
	 * @return
	 */
	private boolean isSeccionValida(Map mapRow) {
		Integer val=procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.getIndicadorValidoSeccion(mapRow);
		return (val==1?true:false);
	}

	/**
	 * Valida si la zona existe y es activa
	 * @param string 
	 * @param string
	 * @return
	 */
	private boolean isZonaValida(String codigoZona, String codigoRegion) {
		Map criteria = new HashMap();
		
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoRegion", codigoRegion);
		
		Integer val=procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.getIndicadorValidoZona(criteria);
		return (val==1?true:false);
	}

	/**
	 * verifica que la region pertenezca al arreglo
	 * @param codigoRegion
	 * @param regionesList
	 * @return
	 */
	private boolean isRegionInArray(String codigoRegion, String[] regionesList) {
		for(int i=0; i<regionesList.length;i++){
			if(codigoRegion.equals(regionesList[i])){
				return true;
			}
		}
		return false;
	}

	/**
	 * se encarga de parsear el valor correcto de region y zona
	 * @param next
	 * @return
	 */
	private Map getParseRow(Map mapRow) {
		 Map param = new HashMap();
		 String codigoRegion =(String)mapRow.get("3");//puede venir 10.0 o 10
		 String codigoZona =  (String)mapRow.get("4");//puede venir 1011.0 o 1011
		 
		 param.put("codigoPais",mapRow.get("0"));//codigo pais, no hay parseo
		 param.put("codigoPeriodo",mapRow.get("1"));//codigo periodo, no hay parseo
		 param.put("codigoMarca",mapRow.get("2"));//codigo marca, no hay parseo
		 param.put("codigoRegion",codigoRegion.substring(0,codigoRegion.indexOf(".")!=-1?codigoRegion.indexOf("."):codigoRegion.length()));
		 param.put("codigoZona",codigoZona.substring(0,codigoZona.indexOf(".")!=-1?codigoZona.indexOf("."):codigoZona.length()));
		 param.put("codigoSeccion",mapRow.get("5"));//SECCION :A, B,C igual no hay parseo
		 param.put("valNumPedido",mapRow.get("6"));//num pedido objetivos, igual no hay parseo
		return param;
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
			 if(mapRow.size()>8) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }			
		}
		excelUtil.cerrar();
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService#getRegionesCerradas(java.util.Map)
	 */
	public List getRegionesCerradas(Map params) {
		return procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.getRegionesCerradas(params);
	}


	/**
	 * Verifica si la zona es nueva o rezonificada
	 * @param mapRow
	 * @return
	 */
	public boolean isZonaNuevaRezonificada(Map mapRow) {
		Integer val = procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.getZonaNuevaRezonificada(mapRow);
		return (val==1?true:false);
	}
	
	/**
	 * Verifica si la seccion es nueva
	 * @param mapRow
	 * @return
	 */
	public boolean isSeccioNueva(Map mapRow) {
		Integer val = procesoLIDGenerarNumeroPedidosSeccionPeriodoDAO.getSeccioNueva(mapRow);
		return (val==1?true:false);
	}
}