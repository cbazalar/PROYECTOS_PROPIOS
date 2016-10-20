package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoMENGeneracionMensajesWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
import biz.belcorp.ssicc.service.util.FileUtil;

public class ACOProcesoMENGeneracionMensajesWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoMENGeneracionMensajesWebService {

	MantenimientoMENPlantillaService mantenimientoMENPlantillaService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoMENPlantillaService = (MantenimientoMENPlantillaService) getWebApplicationContext().getBean("spusicc.mantenimientoMENPlantillaService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoMENGeneracionMensajesWebService#
	 * ejecutarProcesoMENGeneracionMensajesWevService(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoMENGeneracionMensajesWevService(
			String codigoPais, String codigoCampanha, String fechaFacturacion,
			String codigoTipoProceso, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse response = new ACOWebServiceResponse();
		String CODIGO_SISTEMA = "MSG";
		String CODIGO_INTERFAZ = "MSG-P1";
		String CODIGO_BATCH = "01";
		String mensajeError = "";
		boolean estado = false;

		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;

		PedidoControlFacturacion pedidoControlFacturacion = null;
		try {
			Pais pais = this.paisService.getPais(codigoPais);

			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(codigoTipoProceso)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoTipoProceso", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			if (StringUtils.isBlank(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO);
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);

			pedidoControlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);

			if (StringUtils.isBlank(codigoCampanha)) {
				codigoCampanha = pedidoControlFacturacion.getCodigoPeriodo();
			}

			if (StringUtils.isBlank(fechaFacturacion)) {
				fechaFacturacion = pedidoControlFacturacion.getFechaProceso();
			}

			String indicadorProcesoActivo = Integer.parseInt(this.mantenimientoMENPlantillaService.getIndicadorProcesoActivo()) > 0 ? Constants.NUMERO_UNO : Constants.NUMERO_CERO;

			Usuario usuario =this.obtenerUsuarioByDefault(codigoUsuario) ;
					//this.usuarioService.getUsuarioByUsername(codigoUsuario);

			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoUsuario", codigoUsuario);

			params.put("codigoPeriodo", codigoCampanha);
			params.put("fechaFacturacion", fechaFacturacion);
			params.put("tipoProceso", codigoTipoProceso);
			params.put("indicadorProcesoActivo", indicadorProcesoActivo);

			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("codigoSistema", CODIGO_SISTEMA);
			params.put("codigoInterfaz", CODIGO_INTERFAZ);
			params.put("usuario", usuario);

			executionResult = this.executeInterfaz(params);
			List listInterfazResults = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) listInterfazResults.get(0);

			if (interfazResult != null) {
				
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				
				response.setEjecucionExitosa(estado);
				response.setMensaje(interfazResult.getMensaje());

				ParametroACOWebService respuestaWebService[] = new ParametroACOWebService[3];

				respuestaWebService[0] = new ParametroACOWebService();
				respuestaWebService[0].setNombre("numeroRegistros");
				respuestaWebService[0].setValor(String.valueOf(interfazResult.getRegistrosProcesados()));

				respuestaWebService[1] = new ParametroACOWebService();
				respuestaWebService[1].setNombre("nombreArchivoEntradaSalida");
				respuestaWebService[1].setValor(getNombreArchivoEntradaSalida(interfazResult));

				respuestaWebService[2] = new ParametroACOWebService();
				respuestaWebService[2].setNombre("numeroLote");
				respuestaWebService[2].setValor(interfazResult.getNumeroLote());

				response.setRespuestaWebService(respuestaWebService);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setEjecucionExitosa(estado);
			response.setMensaje(e.getMessage());

			ParametroACOWebService respuestaWebService[] = new ParametroACOWebService[1];
			respuestaWebService[0] = new ParametroACOWebService();
			respuestaWebService[0].setNombre("numeroRegistros");
			respuestaWebService[0].setValor(Constants.NUMERO_CERO);

			response.setRespuestaWebService(respuestaWebService);
			return response;
		}
		return response;
	}

	@Override
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		Map map = super.executeProcessBeforeInterfaz(params);
		// ejecutamos proecdimiento de llenado de temporal
		map.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);

		map.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		this.mantenimientoMENPlantillaService.executePedidoCliente(params);
		return map;
	}

	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		// se valida que haya proceos habilitados
		params = super.prepareParamsBeforeExecute(params, pais);
		List list = this.mantenimientoMENPlantillaService.getProcesosHabilitados(params);
		if (list.size() == 0) {
			String mensaje = getWebApplicationContext().getMessage("procesoMENGeneracionMensajesForm.no.procesos.habilitados", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
			throw new Exception(mensaje);
		}
		String codigoUsuario = (String) params.get("codigoUsuario");
		Usuario usuario = this.obtenerUsuarioByDefault("codigoUsuario");
				//this.usuarioService.getUsuarioByUsername(codigoUsuario);

		// llenamos el compo paquete para el tipo de operacion seleccionado
		//params.put("rutaPath", getWebApplicationContext().getServletContext().getRealPath("/"));
		//params.put("rutaPath", this.usuarioService.getRutaPath());
		
		 MessageContext msgCtx = MessageContext.getCurrentContext();
	     HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	    		 						getProperty("transport.http.servletRequest"); 
		params.put("rutaPath", FileUtil.formatDirectory(httpServletRequest.getRealPath("/"))); 
		params.put("usuarioTemp", usuario);
		this.mantenimientoMENPlantillaService.setProcesoPaquete(params);
		log.debug("finalizando prepareParamsBeforeExecute ===>" + params);
		return params;
	}

}
