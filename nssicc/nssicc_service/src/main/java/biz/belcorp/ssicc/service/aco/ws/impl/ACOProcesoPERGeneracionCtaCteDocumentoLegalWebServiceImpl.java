package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoPERGeneracionCtaCteDocumentoLegalWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERGeneracionCtaCteDocumentoLegalService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoPERGeneracionCtaCteDocumentoLegalWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoPERGeneracionCtaCteDocumentoLegalWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoPERGeneracionCtaCteDocumentoLegalWebService {

	InterfazService interfazService;
	ProcesoPERGeneracionCtaCteDocumentoLegalService procesoPERGeneracionCtaCteDocumentoLegalService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		
		interfazService = (InterfazService) getWebApplicationContext().getBean("sisicc.interfazService");
		procesoPERGeneracionCtaCteDocumentoLegalService = (ProcesoPERGeneracionCtaCteDocumentoLegalService) getWebApplicationContext().getBean("sisicc.procesoPERGeneracionCtaCteDocumentoLegalService");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoPERGeneracionCtaCteDocumentoLegalWebService#ejecutarProcesoPERGeneracionCtaCteDocumentoLegal(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoPERGeneracionCtaCteDocumentoLegal(
			String codigoPais, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.PER_CODIGO_PROCESO_BATCH_GENERACION_CTACTE_DOCUMENTO_LEGAL;
		final String CODIGO_INTERFAZ = Constants.PER_CODIGO_INTERFAZ_GENERACION_CTACTE_DOCUMENTO_LEGAL;
		final String CODIGO_SISTEMA = Constants.PER_CODIGO_SISTEMA;
		final String TIPO_ORIGEN_DATOS = "06";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoEMPEnvioVariables");
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
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);
	    	criteria.put("usuario", usuario);
	    	criteria.put("tipOrigenDatos", TIPO_ORIGEN_DATOS);
	        
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#executeProcessBeforeInterfaz(java.util.Map)
	 */
	@Override
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		
		Pais pais = (Pais)params.get("pais");
		Usuario usuario = (Usuario) params.get("usuario");
		String codigo   = (String) params.get("codigoInterfaz");
		String tipo     = (String) params.get("tipOrigenDatos"); 
		String exito    = "-1";
		params.put("exito", exito);

		InterfazPK interfazPK = new InterfazPK(pais.getCodigo(), codigo.substring(0, 3), codigo);
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		params.put("descripcion", interfaz.getDescripcion());
		
		Map resultado = procesoPERGeneracionCtaCteDocumentoLegalService.executeGeneracionCtaCteDocumentoLegal(pais, usuario, codigo, tipo);
		params.put("numeroLote", resultado.get("numeroLote"));
		params.put("numeroLoteSolicitud", resultado.get("numeroLote"));
		params.put("codigoTipoOrigenDatos", resultado.get("codigoTipoOrigenDatos"));
		log.debug("Mostrando el numero de Lote " + resultado.get("numeroLote"));
		exito = "1";
		params.put("exito", exito);	
		
		return super.executeProcessBeforeInterfaz(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#continueExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected boolean continueExecuteInterfaz(Map params) {
		log.debug("ProcesoPERGeneracionCtaCteDocumentoLegalAction.continueExecuteInterfaz");
		boolean flag = false;
		if (params != null) {
			if (((String) params.get("exito")).equalsIgnoreCase("1"))
				flag = true;
			else
				flag = false;
		}
		log.debug("Mostrando el valor de flag: " + flag);
		return flag;
	}	
	
}
