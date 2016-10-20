/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoPEDAsignacionStockWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDAsignacionStockService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoPEDAsignacionStockWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoPEDAsignacionStockWebService {

	private static final String CODIGO_SISTEMA = "PED";
	private static final String CODIGO_PROCESO_BATCH = "02";
	InterfazSiCCService interfazSiCCService;
	ProcesoPEDAsignacionStockService procesoPEDAsignacionStockService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.procesoPEDAsignacionStockService = (ProcesoPEDAsignacionStockService) getWebApplicationContext().getBean("spusicc.pedidos.procesoPEDAsignacionStockService");
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#executeProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	protected Map executeProcess(Map params) throws Exception {
		log.info("Entro a ProcesoPEDAsignacionStockService - executeProcess");
		
		//-- Agregar parametro
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("psusuario",usuario.getLogin());
		
		//-- Logica
		this.procesoPEDAsignacionStockService.executeAsignacionStock(params);
		
		log.info("Salio a ProcesoPEDAsignacionStockAction - executeProcess");
        return params;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#ejecutarProcesoPEDAsignacionStock(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoPEDAsignacionStock(
			String codigoPais, String usuarioLogin, String fechaFacturacion) throws RemoteException {
		String mensajeError = "";
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		String codigoSistema = CODIGO_SISTEMA;
		String codigoProcesoBatch = CODIGO_PROCESO_BATCH;

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoPEDAsignacionStock");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)
					|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(usuarioLogin)
					|| StringUtils.isEmpty(usuarioLogin)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			List lista = this.interfazSiCCService.getPeriodoFechaProcesoActual(criteria);
			HashMap mapeo = (HashMap) lista.get(0);

			String codigoPeriodo = mapeo.get("cod_peri").toString();

			if (StringUtils.isBlank(fechaFacturacion)) {
				fechaFacturacion = mapeo.get("fec_proc").toString();
			}

			criteria.put("fechaFact", fechaFacturacion);
			criteria.put("psusuario", usuarioLogin);
			criteria.put("codigoUsuario", usuarioLogin);
			criteria.put("codigoSistema", codigoSistema);
			criteria.put("codigoProcesoBatch", codigoProcesoBatch);
			
			this.executeProceso(criteria);
			objetoRespuesta.setMensaje("");
			estado = true;
			objetoRespuesta.setEjecucionExitosa(estado);
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

}
