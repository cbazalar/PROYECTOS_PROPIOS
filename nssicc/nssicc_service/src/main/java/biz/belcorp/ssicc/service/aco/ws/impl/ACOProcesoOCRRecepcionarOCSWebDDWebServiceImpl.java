/**
 * 
 */
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
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCRRecepcionarOCSWebDDWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoOCRRecepcionarOCSWebDDWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoOCRRecepcionarOCSWebDDWebServiceImpl extends
		BaseInterfazHiloAbstractWebService implements
		ACOProcesoOCRRecepcionarOCSWebDDWebService {

	ProcesoSTOService procesoSTOService;
	InterfazSiCCService interfazSiCCService;;
	ProcesoSTOExecutionService procesoSTOExecutionService;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		procesoSTOService = (ProcesoSTOService) getApplicationContext().getBean("spusicc.procesoSTOService");
		interfazSiCCService = (InterfazSiCCService) getApplicationContext().getBean("sisicc.interfazSiCCService");
		procesoSTOExecutionService = (ProcesoSTOExecutionService) getApplicationContext().getBean("spusicc.procesoSTOExecutionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoOCRRecepcionarOCSWebDDWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String indValidacionSTO, String codigoUsuario) throws RemoteException {
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_OCSWEBDD;
		String CODIGO_INTERFAZ = Constants.OCR_CODIGO_INTERFAZ_RECEPCIONAR_OCSWEBDD;
		String CODIGO_SISTEMA = Constants.OCR_CODIGO_SISTEMA;

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProceso");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)	|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(codigoUsuario) || StringUtils.isEmpty(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			if (StringUtils.isBlank(indValidacionSTO) || StringUtils.isEmpty(indValidacionSTO)) {
				indValidacionSTO = Constants.SI;
			}
						
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
			criteria.put("usuario", usuario);	
			criteria.put("indValidacionSTO", indValidacionSTO);
						
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

				List<ParametroACOWebService> respuestaWebService = new ArrayList<ParametroACOWebService>();

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
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("indicadorProceso");
				parametroACOWebService.setValor((String) criteria.get("indicadorProceso"));
				respuestaWebService.add(parametroACOWebService);

				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));
			}


		} catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);
			objetoRespuesta.setEjecucionExitosa(estado);
		} finally {
			log.debug("Estado del servicio: " + estado);
			if (estado) {
				log.info("Se ejecuto el servicio con exito.");
			} else {
				log.error("Excepcion en el servicio.");
			}
		}

		return objetoRespuesta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		Map queryParams = super.prepareParamsBeforeExecute(params, pais);
		
		String numLoteSTO = procesoSTOService.updateLoteSTO(new TipoDocumentoDigitadoPK(pais.getCodigo(),Constants.STO_TIPO_DOCUMENTO_OCC));
		
		queryParams.put("fechaDemanda", null);		
		queryParams.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		queryParams.put("indOrigen", Constants.STO_ORIGEN_WEB);	
		queryParams.put("indicadorOCRComercial", pais.getIndicadorOCRComercial());
		queryParams.put("numLoteSTO", numLoteSTO); 
				
		interfazSiCCService.updateInterfazProcesoBatch(queryParams);
		
		return queryParams;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (interfazExecutionResult.ejecucionCompletada()){
	        String codigoPais = (String)params.get("codigoPais");
	        String indValidacionSTO = (String)params.get("indValidacionSTO");
	        
	        if (indValidacionSTO!=null && indValidacionSTO.equals(Constants.SI))
	        {	        
	        	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
	        	procesoSTOExecutionService.execute(accionTipoDocumento,params, null);	
	        }
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#executeProcessBeforeInterfaz(java.util.Map)
	 */
	@Override
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		params = super.executeProcessBeforeInterfaz(params);
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("descripcionEtapaProceso", Constants.DESCRIPCION_RECEPCIONAR_OCR_PROCESO);
		procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
		return params;	
	}

}
