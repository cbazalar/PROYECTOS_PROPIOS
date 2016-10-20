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
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPuntajeBonificadoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntajeConsultora;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaPuntajeBonificadoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.procesoINCCargaPuntajeBonificadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCargaPuntajeBonificadoServiceImpl extends BaseService implements
	ProcesoINCCargaPuntajeBonificadoService {
		
	@Resource(name="spusicc.procesoINCCargaPuntajeBonificadoDAO")
	private ProcesoINCCargaPuntajeBonificadoDAO procesoINCpuntajeDAO;
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	
	
	/**
	 * @return the procesoINCpuntajeDAO
	 */
	public ProcesoINCCargaPuntajeBonificadoDAO getProcesoINCpuntajeDAO() {
		return procesoINCpuntajeDAO;
	}

	/**
	 * @param procesoINCpuntajeDAO the procesoINCpuntajeDAO to set
	 */
	public void setProcesoINCpuntajeDAO(
			ProcesoINCCargaPuntajeBonificadoDAO procesoINCpuntajeDAO) {
		this.procesoINCpuntajeDAO = procesoINCpuntajeDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#cargarArchivoExcel(java.util.Map)
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
			
			
			String codigoCliente="";
			String numeroPuntaje="";
			String codigoCampanha="";
			
			if(StringUtils.equals(devuelveCampanha,Constants.NUMERO_CERO)){				
			//Recuperamos el codigo de consultora y el puntaje 
			  codigoCliente = (String)mapRow.get("0");
			  numeroPuntaje = (String)mapRow.get("1");
		    }else{
		      codigoCampanha = (String)mapRow.get("0"); 	
			  codigoCliente = (String)mapRow.get("1");
			  numeroPuntaje = (String)mapRow.get("2");
		    }
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
			 
			 //validamos periodo si es que este tuvo q ser devuleto
			 if(StringUtils.equals(devuelveCampanha,Constants.NUMERO_UNO)){
				 mensajeError=validarCampanha(codigoCampanha);
				 if(StringUtils.isNotEmpty(mensajeError)){
					 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noValidoPeriodo",null,getLocale(usuario));
					 if(!errorPorFila)numFilaError+=1;
					 indicadorCarga = Constants.NUMERO_CERO;
					 errorPorFila=true;
				 }				 
			 }
			 
			 if(StringUtils.isEmpty(codigoCliente)){
				 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noValidoFormato",null,getLocale(usuario));
				 if(!errorPorFila)numFilaError+=1;
				 indicadorCarga = Constants.NUMERO_CERO;
				 errorPorFila=true;
			 }	 
			 if(StringUtils.isEmpty(numeroPuntaje)){
				 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noValidoPuntaje",null,getLocale(usuario));
				 if(!errorPorFila)numFilaError+=1;
				 indicadorCarga = Constants.NUMERO_CERO;
				 errorPorFila=true;
			 }	 
			 
			 if(StringUtils.isEmpty(codigoCliente) && StringUtils.isEmpty(numeroPuntaje) ){
				 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noRegistroFila",null,getLocale(usuario));
				 if(!errorPorFila)numFilaError+=1;
				 indicadorCarga = Constants.NUMERO_CERO;
				 errorPorFila=true;
			 }

			 if(StringUtils.isNotEmpty(numeroPuntaje)){
				 if(!validoFormatoPuntaje(numeroPuntaje)){
				 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noFormatoValidoPuntaje",null,getLocale(usuario));
				 if(!errorPorFila)numFilaError+=1;
				  indicadorCarga = Constants.NUMERO_CERO;
				  errorPorFila=true;
				 }
			 }	 			 			 
			 if(StringUtils.isNotEmpty(codigoCliente)){			
				 if(!existeConsultora(oidPais,codigoCliente)){
					 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noExisteConsultora",null,getLocale(usuario));
					 if(!errorPorFila)numFilaError+=1;
					 indicadorCarga = Constants.NUMERO_CERO;
					 errorPorFila=true;
				 }
			 }
			 
			if(StringUtils.isNotEmpty(codigoCliente) && StringUtils.isNotEmpty(numeroPuntaje)){ 
			//insertCargaPuntajeConsultora
			CargaPuntajeConsultora cargaPuntajeConsultora = new CargaPuntajeConsultora();
			cargaPuntajeConsultora.setNumeroLote(numLote);
			cargaPuntajeConsultora.setCodigoCliente(codigoCliente);
			cargaPuntajeConsultora.setNumeroPuntaje(numeroPuntaje);
			cargaPuntajeConsultora.setNumeroConcurso(String.valueOf(criteria.get("numeroConcurso")));
			cargaPuntajeConsultora.setCodigoMotivo(String.valueOf(criteria.get("codigoMotivo")));
			cargaPuntajeConsultora.setMensajeError(mensajeError);
			cargaPuntajeConsultora.setIndicadorCarga(indicadorCarga);
			//ind_aplicacion nullo
			cargaPuntajeConsultora.setCodigoUsuario(usuario.getLogin());
			cargaPuntajeConsultora.setCodigoPeriodo(StringUtils.isEmpty(codigoCampanha)?String.valueOf(criteria.get("periodo")):codigoCampanha);
			//
			
			String message = insertCargaPuntajeConsultora(cargaPuntajeConsultora,usuario.getLogin());
			if(StringUtils.isNotEmpty(message) && 
					(message.indexOf(messageSource.
									getMessage("procesoINCCargaPuntajeBonificadoForm.error.uniqueConstraint",null,getLocale(usuario)))!=-1
					  || 				
					  message.indexOf(messageSource.
								getMessage("procesoINCCargaPuntajeBonificadoForm.error.uniqueCodigoConstraint",null,getLocale(usuario)))!=-1					  
					 )
									){
				mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.existenDuplicados",null,getLocale(usuario));
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
		mapFila.put("mensajeError", messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noRegistroFila",null,getLocale(usuario)));
		mapFila.put("numErrores", String.valueOf(numFilaError));
		mapFila.put("numeroLote",numLote);	
		listMapFila.add(mapFila);		
		
	}

	/**
	 * Se valida si el puntaje tine formato numerico
	 * @param numeroPuntaje
	 * @return
	 */
	private boolean validoFormatoPuntaje(String numeroPuntaje) {
		boolean valor=true;
		try{
			Double.parseDouble(numeroPuntaje);
		}catch(Exception e){
			valor=false;
		}
		return valor;
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#insertCargaPuntajeConsultora(biz.belcorp.ssicc.spusicc.inc.dao.model.CargaPuntajeConsultora)
	 */
	public void insertCargaPuntajeConsultora(
			CargaPuntajeConsultora cargaPuntajeConsultora) {
		 procesoINCpuntajeDAO.insertCargaPuntajeConsultora(cargaPuntajeConsultora);		
	}

	
	private String insertCargaPuntajeConsultora(
			CargaPuntajeConsultora cargaPuntajeConsultora,String login) {
		String valor="";
		try{
		 procesoINCpuntajeDAO.insertCargaPuntajeConsultora(cargaPuntajeConsultora);
		}catch(Exception e){
			//log.debug("insertCargaPuntajeConsultora error " +e.getMessage());
			valor = e.getMessage();
		}
		return valor;
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#executeInsercionCuentaCorrientePuntaje(java.util.Map)
	 */
	public void executeInsercionCuentaCorrientePuntaje(Map params) {
		String indicadorConsultoras = (String)params.get("indicadorConsultoras");
		
		if(!indicadorConsultoras.equals(Constants.NUMERO_UNO)) {
			Usuario usuario = (Usuario)params.get("usuario");
			String numeroLote = getNumeroLote();
			
			CargaPuntajeConsultora cargaPuntajeConsultora = new CargaPuntajeConsultora();
			cargaPuntajeConsultora.setNumeroLote(numeroLote);
			cargaPuntajeConsultora.setCodigoCliente(String.valueOf(params.get("codigoCliente")));
			cargaPuntajeConsultora.setNumeroPuntaje(String.valueOf(params.get("numeroPuntos")));
			cargaPuntajeConsultora.setNumeroConcurso(String.valueOf(params.get("numeroConcurso")));
			cargaPuntajeConsultora.setCodigoMotivo(String.valueOf(params.get("codigoMotivo")));
			cargaPuntajeConsultora.setMensajeError("");
			cargaPuntajeConsultora.setIndicadorCarga(Constants.NUMERO_UNO);
			cargaPuntajeConsultora.setCodigoUsuario(usuario.getLogin());
			cargaPuntajeConsultora.setCodigoPeriodo(String.valueOf(params.get("codigoPeriodo")));
			
			String message = insertCargaPuntajeConsultora(cargaPuntajeConsultora,usuario.getLogin());
			log.debug("executeInsercionCuentaCorrientePuntaje grabarCarga " + message);
			
			params.put("numeroLote", numeroLote);
		}
		
		procesoINCpuntajeDAO.executeInsercionCuentaCorrientePuntaje(params);
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
	
	/**
	 * Valida que el formato d ela campanha sea la correcta yyyymm donde mm va de 1 a 18
	 * @param campanha
	 * @return
	 */
	private String validarCampanha(String campanha) {
		String mensaje ="";
		if(StringUtils.isEmpty(campanha)) return campanha;
		int index = campanha.indexOf(".0");
		if(index!=-1 && campanha.length() == 8){
			campanha = campanha.substring(0, index);
		}	
		log.debug("campanha x validar " + campanha);
		if(campanha.length() != 6){
			return campanha;
		}		
		//
		int anho = Integer.parseInt(campanha.substring(0,4)); 
		int camp = Integer.parseInt(campanha.substring(4));
		log.debug("campanha validada " + anho + " - " + camp);
		if(anho >= 1900 && 
		   camp >= 1 && camp <=18) return mensaje;
		

		
		return campanha;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#validarDatosConsultora(java.lang.String, java.lang.String, java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public String validarDatosConsultora(String oidPais, String codigoPeriodo, String codigoCliente, Usuario usuario) {
		String mensajeError = "";
		
		if(!existeConsultora(oidPais,codigoCliente)){
			mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.noExisteConsultora",
											null,getLocale(usuario));
			
		} else {
			mensajeError=validarCampanha(codigoPeriodo);
			
			if(StringUtils.isNotEmpty(mensajeError))
				 mensajeError = messageSource.getMessage("procesoINCCargaPuntajeBonificadoForm.error.periodoIncorrecto",
						 					null,getLocale(usuario));
		}
		
		return mensajeError;
	}
	
}
