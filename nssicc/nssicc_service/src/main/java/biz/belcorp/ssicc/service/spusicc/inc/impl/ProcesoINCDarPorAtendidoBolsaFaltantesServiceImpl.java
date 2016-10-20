package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDarPorAtendidoBolsaFaltantesDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCDarPorAtendidoBolsaFaltantesService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoINCDarPorAtendidoBolsaFaltantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCDarPorAtendidoBolsaFaltantesServiceImpl extends BaseService implements
	ProcesoINCDarPorAtendidoBolsaFaltantesService {
		
	@Resource(name="spusicc.procesoINCDarPorAtendidoBolsaFaltantesDAO")
	private ProcesoINCDarPorAtendidoBolsaFaltantesDAO procesoINCpuntajeDAO;
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCDarPorAtendidoBolsaFaltantesService#getListConcursoVigentes()
	 */
	public List getListConcursoVigentes() {
		return procesoINCpuntajeDAO.getListConcursoVigentes();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCDarPorAtendidoBolsaFaltantesService#cargarArchivoExcel(java.util.Map)
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception {
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));
		
		//RECUPERAMOS SI LA ESTRUCTURA DEVULVE CAMPNHA 
		String devuelveCampanha = String.valueOf(criteria.get("devuelveCampanha"));
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int fila=0;
		int numFilaError=0;
		String numLote = getNumeroLote();
		boolean errorPorFila =false; 
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			fila +=1;
			errorPorFila= false;

			//obtenemos el codigo de cliente	
			String codigoCliente=(String)mapRow.get("0");
			String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
			 //
			 while(fila<Integer.parseInt(filaExcel)){
				 numFilaError+=1;
				 anhadirFilaVacia(listMapFila,numLote,fila,numFilaError,usuario);				 
				 fila +=1;
			 }
			 
			 //validaciones
			 String mensajeError=null;
			 String indicadorCarga = Constants.NUMERO_UNO;
			 
			 if(StringUtils.isEmpty(codigoCliente)){
				 mensajeError = messageSource.getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.noRegistroFila",null,getLocale(usuario));
				 if(!errorPorFila)numFilaError+=1;
				 indicadorCarga = Constants.NUMERO_CERO;
				 errorPorFila=true;
			 }	 
			 
			 if(StringUtils.isNotEmpty(codigoCliente)){			
				 if(!existeConsultora(oidPais,codigoCliente)){
					 mensajeError = messageSource.getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.noExisteConsultora",null,getLocale(usuario));
					 if(!errorPorFila)numFilaError+=1;
					 indicadorCarga = Constants.NUMERO_CERO;
					 errorPorFila=true;
				 }
			 }
			 
			if(StringUtils.isNotEmpty(codigoCliente)){ 
				Map param = new HashMap();
				param.put("numeroLote", numLote);
				param.put("codigoCliente", codigoCliente);
				param.put("numeroConcurso", String.valueOf(criteria.get("numeroConcurso")));
				param.put("codigoUsuario", usuario.getLogin());
				param.put("mensajeError", mensajeError);
				param.put("indicadorCarga", indicadorCarga);
				param.put("observaciones", String.valueOf(criteria.get("observaciones")));
				
				String message = insertBolsaAtendido(param, usuario.getLogin());
				if(StringUtils.isNotEmpty(message) && 
						(message.indexOf(messageSource.
										getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.uniqueConstraint",null,getLocale(usuario)))!=-1
						  || 				
						  message.indexOf(messageSource.
									getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.uniqueCodigoConstraint",null,getLocale(usuario)))!=-1					  
						 )
										){
					mensajeError = messageSource.getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.existenDuplicados",null,getLocale(usuario));
					if(!errorPorFila)numFilaError+=1;
					indicadorCarga = Constants.NUMERO_CERO;
					errorPorFila=true;
				}
			 
			}
			
			//Lo almacenamos en una lista temporal
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", String.valueOf(fila));
			mapFila.put("codigoCliente", codigoCliente);
			mapFila.put("mensajeError", mensajeError);
			mapFila.put("numErrores", String.valueOf(numFilaError));
			mapFila.put("numeroLote",numLote);	
			listMapFila.add(mapFila);
			
		}
		
		excelUtil.cerrar();
		return listMapFila;

	}


	/**
	 * Anhade fila vacia del excel a la lista que contiene informacion de excel procesado
	 * @param listMapFila
	 * @param numLote 
	 * @param fila
	 * @param numFilaError 
	 * @param usuario
	 */
	private void anhadirFilaVacia(List listMapFila, String numLote, int fila, int numFilaError, Usuario usuario) {
		Map mapFila = new HashMap();
		mapFila.put("numeroFila", String.valueOf(fila));
		mapFila.put("codigoCliente", "");
		mapFila.put("mensajeError", messageSource.getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.noRegistroFila",null,getLocale(usuario)));
		mapFila.put("numErrores", String.valueOf(numFilaError));
		mapFila.put("numeroLote",numLote);	
		listMapFila.add(mapFila);		
		
	}

	/**
	 * Retorna el numero de lote
	 * @return
	 */
	private String getNumeroLote() {
		return procesoINCpuntajeDAO.getNumeroLote();
	}

	/**
	 * Retorna true si la consultora existe, false caso contrario
	 * @param oidPais
	 * @param codigoCliente
	 * @return
	 */
	private boolean existeConsultora(String oidPais, String codigoCliente) {
		Map map = new HashMap();
		map.put("oidPais", oidPais);
		map.put("codigoCliente", codigoCliente);
		String codCliente =mantenimientoMAEClienteDAO.getExisteCodigoCliente(map);
		log.debug("existe consultora " + codCliente);
		return (codigoCliente.equals(codCliente)?true:false);
	}

	private String insertBolsaAtendido(Map params,String login) {
		String valor="";
		try{
			procesoINCpuntajeDAO.insertBolsaAtendido(params);
		}catch(Exception e){
			valor = e.getMessage();
		}
		return valor;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCDarPorAtendidoBolsaFaltantesService#executeActualizarBolsaFaltantes(java.util.Map)
	 */
	public void executeActualizarBolsaFaltantes(Map params) {
		procesoINCpuntajeDAO.executeActualizarBolsaFaltantes(params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCDarPorAtendidoBolsaFaltantesService#validarArchivoExcel(java.util.Map)
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
			if(mapRow.size()== 3){
				criteria.put("devuelveCampanha",Constants.NUMERO_CERO);
			}
		    if(mapRow.size()== 4){
		    	criteria.put("devuelveCampanha",Constants.NUMERO_UNO);
		    }
			if(mapRow.size()<=2 || mapRow.size()>4) {//las columnas recogidas + el numero de fila de fila procesda
				 //EL ARCHIVO AHORA CONTINE DOS ESTRUCTURAS DE 2 CAMPOS Y 3
				 valor = false;
				 break;
			}			
		}
		excelUtil.cerrar();
		return valor;
	}
	
}
