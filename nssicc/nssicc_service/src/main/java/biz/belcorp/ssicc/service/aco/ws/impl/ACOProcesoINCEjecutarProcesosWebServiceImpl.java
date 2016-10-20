/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoINCEjecutarProcesosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoGENProcesarGP3WebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */
public class ACOProcesoINCEjecutarProcesosWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoINCEjecutarProcesosWebService {

	
	InterfazSiCCService interfazSiCCService;
	MantenimientoFACGenericoService mantenimientoFACGenericoService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoFACGenericoService = (MantenimientoFACGenericoService) getWebApplicationContext().getBean("spusicc.mantenimientoFACGenericoService");
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoINCEjecutarProcesosWebService#ejecutarProcesoINCEjecutarProcesos(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoINCEjecutarProcesos(
			String codigoPais, String codigoUsuario, String marca, String canal,
			String codigoPeriodo, String fechaFacturacion, String indicadorProceso)
			throws RemoteException {
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = "05";
		String INDICADOR_PROCESO = "GP3";
		String CODIGO_INTERFAZ = "INC-P5";
		String CODIGO_SISTEMA = "INC";

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoINCEjecutarProcesos");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)
					|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage(
						"procesoACOWebService.msg.validarPais", null,
						getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(codigoUsuario)
					|| StringUtils.isEmpty(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage(
						"procesoACOWebService.msg.validarUsuario", null,
						getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());

			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if (StringUtils.isBlank(marca) || StringUtils.isEmpty(marca)) {
				marca = Constants.CODIGO_MARCA_DEFAULT;
			} else {
				if (siccMarcaList.size() != 0) {
					if (!existeCodigoEnLista(siccMarcaList, marca)) {
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca", null,
										getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				} else {
					log.debug("Listado de marca vacio.");
				}
			}

			/**
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if (StringUtils.isBlank(canal) || StringUtils.isEmpty(canal)) {
				canal = Constants.CODIGO_CANAL_DEFAULT;
			} else {
				if (siccCanalList.size() != 0) {
					/**
					 * Si canal no existe en la lista, enviar execpcion.
					 */
					if (!existeCodigoEnLista(siccCanalList, canal)) {
						mensajeError = getWebApplicationContext()
								.getMessage(
										"procesoACOWebService.msg.mensajeNotExisteCanal",
										null,
										getLocaleIdioma(pais
												.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				} else {
					log.debug("Listado de canal vacio.");
				}
			}

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente

			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);

			if (StringUtils.isBlank(codigoPeriodo)) {
				codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}

			if (StringUtils.isBlank(fechaFacturacion)) {
				fechaFacturacion = controlFacturacion.getFechaProceso();
			}

			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("fechaFacturacion", fechaFacturacion);

			criteria.put("codigoCanal", canal);
			criteria.put("codigoMarca", marca);

			criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_ZONA);
			if (StringUtils.isBlank(indicadorProceso)) {
				indicadorProceso = INDICADOR_PROCESO;
			}
			log.debug("Indicador Proceso: " + indicadorProceso);
			
			criteria.put("indicadorProceso", indicadorProceso);
			/* INI JJ PER-SiCC-2012-0637 */
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
	
			executionResult = this.executeInterfaz(criteria);

			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);

			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(interfazResult.getMensaje());

				List<ParametroACOWebService> respuestaWebService = new ArrayList<ParametroACOWebService>();

				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult
						.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);

				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService
						.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);

				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("indicadorProceso");
				parametroACOWebService.setValor((String) criteria.get("indicadorProceso"));
				respuestaWebService.add(parametroACOWebService);

				objetoRespuesta.setRespuestaWebService(respuestaWebService
						.toArray(new ParametroACOWebService[respuestaWebService
								.size()]));
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
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		params = super.prepareParamsBeforeExecute(params, pais);
	    Usuario usuario = (Usuario) params.get("usuario");
		params.put("zonaList", new ArrayList());
		
		/* INI SA PER-SiCC-2012-0900 */
		String indicadorProceso = (String) params.get("indicadorProceso");
		
		return params;
	}
	
	

}
