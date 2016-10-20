package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCOBEnvCobSalPendWebService;
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
public class ACOProcesoCOBEnvCobSalPendWebServiceImpl extends BaseInterfazAbstractWebService implements ACOProcesoCOBEnvCobSalPendWebService {

	InterfazSiCCService interfazSiCCService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoACOProcesoCOBEnvCobSalPend(
			String codigoUsuario,
			String codigoPais,
			String codigoSociedad) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_INTERFAZ = "COB-3";
		final String CODIGO_SISTEMA = "COB";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoACOProcesoCOBEnvCobSalPend");
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
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);
	    	criteria.put("usuario", usuario);
	    	
	    	List listSociedad = this.interfazSiCCService.getSociedadesByCodigoPais(pais.getCodigo());
			
	    	/**
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoSociedad) || StringUtils.isEmpty(codigoSociedad)){
				codigoSociedad = Constants.CODIGO_SOCIEDAD_DEFAULT;
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoSociedad);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(listSociedad.size()!=0){
					if(!CollectionUtils.exists(listSociedad,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoSociedad",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de sociedad vacio.");
				}
			}
			
			criteria.put("codigoSociedad", codigoSociedad);
			criteria.put("sociedad",this.interfazSiCCService.getSociedadEquivalenciaSAP(criteria));
			
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				estado = interfazResult.isCompletado();
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(interfazResult.getMensaje());
				
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
	
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		String titulo   = new String();
		String titulo1  = new String();
		String titulo2  = new String();
		String titulo3  = new String();
		String titulo4  = new String();
		String titulo5  = new String();
		String titulo6  = new String();
		String titulo7  = new String();
		String titulo8  = new String();
		String titulo9  = new String();
		String titulo10 = new String();
		String titulo11 = new String();
		String titulo12 = new String();
		String titulo13 = new String();
		
		titulo1 = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.ejercicio",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo2  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.perCont",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo3  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.codPeri",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo4  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.socFI",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo5  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.paisSAP",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo6  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.canal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo7  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.codRegion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo8  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.codZona",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo9  = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.tCob",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo10 = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.tSaldo",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo11 = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.fecProceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo12 = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.fecFactIni",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		titulo13 = getWebApplicationContext().getMessage("interfazCOBEnviarCobranzaSaldoPendienteForm.titulo.fecFactFin",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		
		titulo = titulo1 + ";" + titulo2 + ";" + titulo3 + ";" + titulo4 + ";" + titulo5 + ";" + titulo6 + ";" + titulo7 + ";" +
			     titulo8 + ";" + titulo9 + ";" + titulo10 + ";" + titulo11 + ";" + titulo12 + ";" + titulo13;
		
		params.put("titulo", titulo);
		
		if (log.isDebugEnabled()) {
			log.debug(params.toString() );
		}
		return super.prepareParamsBeforeExecute(params, pais);
	}
	
}
