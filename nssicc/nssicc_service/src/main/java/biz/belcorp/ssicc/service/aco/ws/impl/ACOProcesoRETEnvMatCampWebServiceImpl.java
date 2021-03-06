package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRETEnvMatCampWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoRETEnvMatCampWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoRETEnvMatCampWebService{

	InterfazSiCCService interfazSiCCService;
	HistoricoService historicoService;
	AjaxService ajaxService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoRETEnvMatCamp(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,					
			String codigoAcceso,
			String codigoSubacceso,
			String periodoDesde,
			String periodoHasta) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		final String CODIGO_BATCH = "07";
		final String CODIGO_INTERFAZ = "RET-1";
		final String CODIGO_SISTEMA = "RET";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoRETEnvMatCamp");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("codigoUsuario", codigoUsuario);
	        criteria.put("usuario", usuario);
	        
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
			List siccAccesoList = this.interfazSiCCService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
			List siccSubaccesoList = this.interfazSiCCService.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
			
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoMarca) || StringUtils.isEmpty(codigoMarca)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, codigoMarca)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de marca vacio.");
				}
			}
			
			/**
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoCanal) || StringUtils.isEmpty(codigoCanal)){
				codigoCanal = Constants.CODIGO_CANAL_DEFAULT;				
			}else{
				if(siccCanalList.size()!=0){
					if(!existeCodigoEnLista(siccCanalList, codigoCanal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
			
			/**
			 * Si acceso es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoAcceso) || StringUtils.isEmpty(codigoAcceso)){
				codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
				if(siccSubaccesoList.size()!=0){
					EqualPredicate nameEqlPredicate = new EqualPredicate(codigoAcceso);
					BeanPredicate beanPredicate = new BeanPredicate("codigoAcceso", nameEqlPredicate);
					Collection filtredList = CollectionUtils.select(siccSubaccesoList, beanPredicate);
					siccSubaccesoList = (List) filtredList;
				}
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoAcceso);
				BeanPredicate beanPredicate = new BeanPredicate("codigoAcceso", nameEqlPredicate);
				if(siccAccesoList.size()!=0){
					if(!CollectionUtils.exists(siccAccesoList,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteAcceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
					if(siccSubaccesoList.size()!=0){
						EqualPredicate eqlPredicate = new EqualPredicate(codigoAcceso);
						BeanPredicate predicate = new BeanPredicate("codigoAcceso", eqlPredicate);
						Collection filtredList = CollectionUtils.select(siccSubaccesoList, predicate);
						siccSubaccesoList = (List) filtredList;
					}
				}else{
					log.debug("Listado de acceso vacio.");
				}
			}
			
			/**
			 * Si subAcceso es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoSubacceso) || StringUtils.isEmpty(codigoSubacceso)){
				codigoSubacceso = Constants.CODIGO_SUBACCESO_DEFAULT;
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoSubacceso);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(siccSubaccesoList.size()!=0){
					if(!CollectionUtils.exists(siccSubaccesoList,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteSubacceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de subacceso vacio.");
				}
			}
			
			if (StringUtils.isBlank(periodoDesde) || StringUtils.isEmpty(periodoDesde)) {
	        	periodoDesde = ajaxService.getPeriodoDefaultByPaisMarcaCanal(codigoPais, codigoMarca, codigoCanal);
			}
	        if (StringUtils.isBlank(periodoHasta) || StringUtils.isEmpty(periodoHasta)) {
	        	periodoHasta = ajaxService.getPeriodoDefaultByPaisMarcaCanal(codigoPais, codigoMarca, codigoCanal);
			}
			criteria.put("codigoPeriodoDesde", periodoDesde);
			criteria.put("codigoPeriodoHasta", periodoHasta);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoAcceso", codigoAcceso);
			criteria.put("codigoSubacceso", codigoSubacceso);
			
			executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());
				
				List<ParametroACOWebService> respuestaWebService =  new ArrayList<ParametroACOWebService>();				
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));
			}
	     
		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	
		return objetoRespuesta;
	}	

}
