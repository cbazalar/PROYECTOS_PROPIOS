/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.dir.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.spusicc.dir.ws.ProcesoDIRWebService;
import biz.belcorp.ssicc.service.spusicc.dir.ws.beans.ClienteDIRWebService;
import biz.belcorp.ssicc.service.spusicc.dir.ws.beans.ProcesoDIRWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales </a>
 */
public class ProcesoDIRWebServiceImpl extends ServletEndpointSupport implements ProcesoDIRWebService {

	
	Log log = LogFactory.getLog(ProcesoDIRWebServiceImpl.class);
	private static final String EXITO = "0";
	private static final String FALLO = "-1";
	private static final String CONEXION_EXTERNA_ORACLE = "ORA";
	private static final String CONEXION_EXTERNA_FOX = "FOX";

	PaisService paisService;
	UsuarioService usuarioService;
	MantenimientoZONDirectorioService mantenimientoZONDirectorioService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.usuarioService = (UsuarioService) getWebApplicationContext().getBean("usuarioService");
		this.mantenimientoZONDirectorioService = (MantenimientoZONDirectorioService) getWebApplicationContext().getBean("spusicc.mantenimientoZonDirectorioService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dir.ws.ProcesoDIRWebService#obtenerClientesDirectorio(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ProcesoDIRWebServiceResultado obtenerClientesDirectorio(
			String codigoPais,
			String codigoRegion,
			String codigoZona, 
			String codigoTipoCargo,
			String codigoPeriodo,
			String estadoCargo) throws RemoteException {
		
		if (log.isDebugEnabled()) {
			log.debug("obtenerClientesDirectorio");
		}
	    String mensajeError = "";
		ProcesoDIRWebServiceResultado objetoRespuesta = new ProcesoDIRWebServiceResultado();
		try {
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoDIRWebService.msg.validarPais", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			Pais pais = this.paisService.getPais(codigoPais);
			/*if (StringUtils.isBlank(codigoPeriodo)) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", codigoPais);
				criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
				criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
				PedidoControlFacturacion controlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
				codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}*/
			
			String conexionExterna = pais.getCodigoConexionExterna();
			
			/* Obteniendo Asignaciones */
			List resultado = new ArrayList();
			Map params = new HashMap();
			String [] splitCodigoTipoCargo = StringUtils.split(codigoTipoCargo, ",");
			String [] splitEstadoCargo = StringUtils.split(estadoCargo, ",");
			Long longitudCodigoCliente = pais.getLongitudCodigoCliente();
			
			params.put("codigoPais", codigoPais);
			params.put("codigoRegion", codigoRegion);
			params.put("codigoZona", codigoZona);
			params.put("codigoTipoCargo", splitCodigoTipoCargo);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("estadoCargo", splitEstadoCargo);
			params.put("longitudCodigoCliente", longitudCodigoCliente);
			
			
			if (StringUtils.equals(conexionExterna, CONEXION_EXTERNA_ORACLE)) {
				resultado = this.mantenimientoZONDirectorioService.getObtenerAsignacionesConsultoraSSiCC(params);
			}
			else {
				resultado = this.mantenimientoZONDirectorioService.getObtenerAsignacionesConsultoraFOX(params);	
			}
			if (resultado == null || resultado.size() == 0)  {
				mensajeError = getWebApplicationContext().getMessage("procesoDIRWebService.msg.validarAsignaciones", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			objetoRespuesta.setCodigo(EXITO);
			objetoRespuesta.setMensaje("");
			ClienteDIRWebService[] lista = new ClienteDIRWebService[resultado.size()];
			for(int i=0; i< resultado.size(); i++) {
				Map mapResultado = (Map)resultado.get(i);
				ClienteDIRWebService beanResultado = new ClienteDIRWebService();
				BeanUtils.populate(beanResultado, mapResultado);
				lista[i] = beanResultado;
			}
			objetoRespuesta.setClienteDIRWebService(lista);
			objetoRespuesta.setCodigo(EXITO);
			objetoRespuesta.setMensaje("");
		}
		catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setCodigo(FALLO);
			objetoRespuesta.setClienteDIRWebService(null);
		}
    	return objetoRespuesta;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dir.ws.ProcesoDIRWebService#buscarClientesDirectorio(java.lang.String, java.lang.String)
	 */
	public ProcesoDIRWebServiceResultado buscarClientesDirectorio (
			String codigoPais,
			String usuarioRed,
			String codigoCUB) throws RemoteException {
		
		if (log.isDebugEnabled()) {
			log.debug("buscarClientesDirectorio");
		}
        String mensajeError = "";
		ProcesoDIRWebServiceResultado objetoRespuesta = new ProcesoDIRWebServiceResultado();

		try {
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoDIRWebService.msg.validarPais", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			Pais pais = this.paisService.getPais(codigoPais);
			
			if (StringUtils.isBlank(usuarioRed) && StringUtils.isBlank(codigoCUB))  {
				mensajeError = getWebApplicationContext().getMessage("procesoDIRWebService.msg.validarUsuarioRed_CUB", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			
			String conexionExterna = pais.getCodigoConexionExterna();
			Map criteria = new HashMap();
			criteria.put("codigoCUB", codigoCUB);
			criteria.put("usuarioRed", usuarioRed);
			String codigoConsultora = new String("");
			
			/* Validando Consultora */
			if (StringUtils.equals(conexionExterna, CONEXION_EXTERNA_ORACLE)) {
				codigoConsultora = this.mantenimientoZONDirectorioService.getVerificaClienteSSiCC(criteria);
			}
			else {
				codigoConsultora = this.mantenimientoZONDirectorioService.getVerificaClienteFOX(criteria);	
			}
			if (StringUtils.isBlank(codigoConsultora)) {
				mensajeError = getWebApplicationContext().getMessage("procesoDIRWebService.msg.validarConsultora", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/* Obteniendo Asignaciones */
			List resultado = new ArrayList();
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoCliente", codigoConsultora);
			params.put("validarFechaRegiFin", Constants.SI);
			Long longitudCodigoCliente = pais.getLongitudCodigoCliente();
			params.put("longitudCodigoCliente", longitudCodigoCliente);
			
			if (StringUtils.equals(conexionExterna, CONEXION_EXTERNA_ORACLE)) {
				resultado = this.mantenimientoZONDirectorioService.getObtenerAsignacionesConsultoraSSiCC(params);
			}
			else {
				resultado = this.mantenimientoZONDirectorioService.getObtenerAsignacionesConsultoraFOX(params);	
			}
			if (resultado == null || resultado.size() == 0)  {
				mensajeError = getWebApplicationContext().getMessage("procesoDIRWebService.msg.validarAsignaciones", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			ClienteDIRWebService[] lista = new ClienteDIRWebService[resultado.size()];
			for(int i=0; i< resultado.size(); i++) {
				Map mapResultado = (Map)resultado.get(i);
				ClienteDIRWebService beanResultado = new ClienteDIRWebService();
				BeanUtils.populate(beanResultado, mapResultado);
				lista[i] = beanResultado;
			}
			objetoRespuesta.setClienteDIRWebService(lista);
			objetoRespuesta.setCodigo(EXITO);
			objetoRespuesta.setMensaje("");
			
			
		}
		catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setCodigo(FALLO);
			objetoRespuesta.setClienteDIRWebService(null);
		}
		
		
		return objetoRespuesta;
	}
	
	
	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected Locale getLocaleIdioma(String codigoIsoIdioma) {
		if (StringUtils.isNotEmpty(codigoIsoIdioma)) {
			if (Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
				return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			else {
				log.debug("codigoIsoIdioma " + codigoIsoIdioma);
				return new Locale(codigoIsoIdioma.toLowerCase());
			}
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#ejecutarProcesoFaltanteFacturacionDia(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	/*
	public ACOWebServiceResponse ejecutarProcesoFaltanteFacturacionDia(String codigoPais,
			String fechaFacturacion, String numeroVersion,
			String numeroProdGrupo, String presentacion, 
			String codigoUsuario, String formatoExportacion,
			String tipoReporte)throws RemoteException {
		String mensajeError = "";
		
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoFaltanteFacturacionDia");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			Usuario usuario = this.usuarioService.getUsuarioByUsername(codigoUsuario);
			params.put("usuarioTemp", usuario);
			params.put("fechaFacturacion", fechaFacturacion);
			
			
			if(StringUtils.isBlank(tipoReporte) 
					|| StringUtils.isEmpty(tipoReporte)){
				tipoReporte = "1";
			}
			
			params.put("tipoReporte", tipoReporte);
			if (StringUtils.isBlank(numeroVersion))
				numeroVersion = "1";

			params.put("numeroVersion", numeroVersion);
			params.put("tipoPresentacion", presentacion);
			
			if(StringUtils.isBlank(formatoExportacion) 
					|| StringUtils.isEmpty(formatoExportacion)){
				formatoExportacion = "VXLS";
			}
			
			params.put("formatoExportacion",formatoExportacion);
			
			if ("1".equals(params.get("tipoReporte"))) {
				params.put("titulo", getWebApplicationContext().getMessage("reportePRYProyeccionFaltanteDiaForm.titulo1", null, getLocaleIdioma(pais.getCodigoIdiomaIso())));
						
			} else if ("2".equals(params.get("tipoReporte"))) {
				params.put("titulo", getWebApplicationContext().getMessage("reportePRYProyeccionFaltanteDiaForm.titulo2", null, getLocaleIdioma(pais.getCodigoIdiomaIso())));
			}

			reportePRYProyeccionFaltanteDiaService.grabarReporte(params);
			estado = true;
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
*/
	

}
