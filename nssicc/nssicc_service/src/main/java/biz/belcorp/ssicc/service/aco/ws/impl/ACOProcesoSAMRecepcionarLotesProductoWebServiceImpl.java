/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoSAMRecepcionarLotesProductoWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoSAMRecepcionarLotesProductoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoSAMRecepcionarLotesProductoWebServiceImpl extends
		BaseInterfazHiloAbstractWebService implements
		ACOProcesoSAMRecepcionarLotesProductoWebService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoSAMRecepcionarLotesProductoWebService#ejecutarProceso(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String codigoUsuario) throws RemoteException {

		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.SAM_CODIGO_PROCESO_BATCH_RECEPCIONAR_LOTES_PRODUCTO;
		String CODIGO_INTERFAZ = Constants.SAM_CODIGO_INTERFAZ_RECEPCIONAR_LOTES_PRODUCTO;
		String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_SAM;

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
			
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
			criteria.put("usuario", usuario);	
			
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

}
