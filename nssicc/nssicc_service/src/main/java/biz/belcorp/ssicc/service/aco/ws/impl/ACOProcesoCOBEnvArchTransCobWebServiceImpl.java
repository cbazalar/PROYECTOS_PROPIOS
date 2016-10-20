package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCOBEnvArchTransCobWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
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
public class ACOProcesoCOBEnvArchTransCobWebServiceImpl extends BaseInterfazAbstractWebService implements ACOProcesoCOBEnvArchTransCobWebService {

	InterfazSiCCService interfazSiCCService;
	ReporteService reporteService; 
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.reporteService = (ReporteService) getWebApplicationContext().getBean("scsicc.reporteService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoCOBEnvArchTransCob(
			String codigoUsuario,
			String codigoPais,
			String codigoSociedad,
			String anho,
			String mes) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_INTERFAZ = "COB-2";
		final String CODIGO_SISTEMA = "COB";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoCOBEnvArchTransCob");
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
		    
		    Calendar fecha = new GregorianCalendar(); 
	    	
	        int imes = fecha.get(Calendar.MONTH) + 1;
	        int ianhio = fecha.get(Calendar.YEAR);
	        
			String vanhio = String.valueOf(ianhio);
			String vmes = String.valueOf(imes); 
			
			if (vmes.length()==1){
				vmes = "0" + vmes;
			}
		    
		    if(StringUtils.isEmpty(anho) || StringUtils.isBlank(anho)){
		    	anho = vanhio;
		    }
		    
		    if(StringUtils.isEmpty(mes) || StringUtils.isBlank(mes)){
		    	mes = vmes;
		    }
		    
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("oidPais", this.reporteService.getOidString("getOidPaisByCodigoPais", criteria));
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);	        
	    	criteria.put("codigoUsuario", codigoUsuario);
	    	criteria.put("usuario", usuario);
	    	criteria.put("anhio", anho);
	    	criteria.put("mes", mes);	    	
	    	
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
			
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				estado = interfazResult.isCompletado();
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(interfazResult.getMensaje());
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
	
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		String titulo = "";
		params.put("sociedad",this.interfazSiCCService.getSociedadEquivalenciaSAP(params));
		
		titulo = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoSociedad",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		
		params.put("titulo", titulo);
		
		if (log.isDebugEnabled()) {
			log.debug(params.toString() );
		}
		return super.prepareParamsBeforeExecute(params, pais);
	}
	
}
