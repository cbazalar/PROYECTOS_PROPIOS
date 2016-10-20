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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENProcesarGP3WebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoGENProcesarGP3WebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */
public class ACOProcesoGENProcesarGP3WebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENProcesarGP3WebService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#
	 * ejecutarProcesoGENProcesarGP3Action(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoGENProcesarGP3(
			String codigoPais, String codigoPeriodo, String fechaFacturacion,
			String codigoUsuario) throws RemoteException {

		String CODIGO_SISTEMA = "GEN";
		String CODIGO_INTERFAZ = "GEN-P4";
		String CODIGO_BATCH = "06";

		ACOWebServiceResponse resultado = new ACOWebServiceResponse();
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		ParametroACOWebService parametroACOWebService = null;
		String mensajeError = "";
		boolean estado = false;

		PedidoControlFacturacion pedidoControlFacturacion = null;
		try {
			Pais pais = this.paisService.getPais(codigoPais);

			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
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

			if (StringUtils.isBlank(codigoPeriodo)) {
				codigoPeriodo = pedidoControlFacturacion.getCodigoPeriodo();
			}

			if (StringUtils.isBlank(fechaFacturacion)) {
				fechaFacturacion = pedidoControlFacturacion.getFechaProceso();
			}

			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoUsuario", codigoUsuario);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			/* INI SA PER-SiCC-2012-0375 */
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			/* FIN SA PER-SiCC-2012-0375 */
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("fechaFacturacion", fechaFacturacion);

			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("codigoSistema", CODIGO_SISTEMA);
			params.put("codigoInterfaz", CODIGO_INTERFAZ);
		
			executionResult = this.executeInterfaz(params);
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);

			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				resultado.setMensaje(interfazResult.getMensaje());

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
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resultado.setEjecucionExitosa(estado);
			resultado.setMensaje(e.getMessage());

			ParametroACOWebService respuestaWebService[] = new ParametroACOWebService[1];
			respuestaWebService[0] = new ParametroACOWebService();
			respuestaWebService[0].setNombre("numeroRegistros");
			respuestaWebService[0].setValor(Constants.NUMERO_CERO);
			return resultado;
		}
		return resultado;
	}

	

}
