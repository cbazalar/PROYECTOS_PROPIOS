package biz.belcorp.ssicc.service.spusicc.lar.impl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.spusicc.lar.ProcesoLARCargaInformacionTrackingService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

@Service("spusicc.procesoLARCargaInformacionTrackingService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLARCargaInformacionTrackingServiceImpl extends BaseService implements ProcesoLARCargaInformacionTrackingService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";
	
	@Resource(name = "sisicc.interfazExecutionService")
	private InterfazExecutionService interfazExecutionService;
	
	@Resource(name = "sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;
	
	@Resource(name = "scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name = "sisicc.interfazService")
	private InterfazService interfazService;
	
	@Resource(name = "genericoDAO")
	private GenericoDAO genericoDAO;
	
	@Resource(name = "spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService")
	private MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	
	/**
	 * @param procesoBatchService the procesoBatchService to set
	 */
	public void setProcesoBatchService(ProcesoBatchService procesoBatchService) {
		this.procesoBatchService = procesoBatchService;
	}

	/**
	 * @param interfazSiCCService the interfazSiCCService to set
	 */
	public void setInterfazSiCCService(InterfazSiCCService interfazSiCCService) {
		this.interfazSiCCService = interfazSiCCService;
	}

	/**
	 * @param interfazExecutionService the interfazExecutionService to set
	 */
	public void setInterfazExecutionService(
			InterfazExecutionService interfazExecutionService) {
		this.interfazExecutionService = interfazExecutionService;
	}

	/**
	 * @param interfazService the interfazService to set
	 */
	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}
	
	/**
	 * @param genericoDAO the genericoDAO to set
	 */
	public void setGenericoDAO(GenericoDAO genericoDAO) {
		this.genericoDAO = genericoDAO;
	}
	
	/**
	 * @param mantenimientoOCRPedidoControlFacturacionService the mantenimientoOCRPedidoControlFacturacionService to set
	 */
	public void setMantenimientoOCRPedidoControlFacturacionService(
			MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService) {
		this.mantenimientoOCRPedidoControlFacturacionService = mantenimientoOCRPedidoControlFacturacionService;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lar.ProcesoLARCargaInformacionTrackingService#executeCargaInformacionTracking()
	 */
	public void executeCargaInformacionTracking() throws Exception {
		log.debug("inicio job executeCargaInformacionTracking ");	
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		
		log.debug("Procesando...");
		
		/* Obteniendo Host */
		InetAddress address = InetAddress.getLocalHost();
		String sHostName = address.getHostName();
		log.debug("Nombre del Host: " + sHostName);
		
		boolean servidorOASdedicado = true;
		boolean ejecutarInterfaz = true;
		ParametroPais parametro = new ParametroPais();
		parametro.setCodigoSistema(Constants.SISTEMA_BAS);
		parametro.setCodigoParametro(Constants.BAS_SERVER_QUARTZ_DEFAULT);
		
		ParametroPais servidor = new ParametroPais();
		try {
			servidor = (ParametroPais)genericoDAO.getParametrosPais(parametro).get(0);
		}
		catch(Exception e) {
			servidorOASdedicado = false;
		}
		if (servidorOASdedicado) {
			if (!StringUtils.equalsIgnoreCase(sHostName, servidor.getValorParametro()))
				ejecutarInterfaz = false;
		}
		if (!ejecutarInterfaz) {
			return;
		}
		
		
		//Recuperamos el codigo Pais
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoSistema(Constants.SISTEMA_GEN);
		parametroPais.setCodigoParametro(Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);				
		
		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
		String codigoPais = pPais.getCodigoPais();		
		
		String codigoInterfaz = Constants.LAR_PROCESO_RECEPCIONAR_CARGA_TRACKING;
		String codigoProcesoBatch = Constants.LAR_CODIGO_PROCESO_BATCH_RECEPCIONAR_CARGA_TRACKING;
		
		if(isEjecucionProceso(codigoPais, codigoInterfaz, codigoProcesoBatch)){	
			
			Map params = prepareParamsBeforeExecute(codigoPais, codigoInterfaz);
			params.put("codigoPais", codigoPais);
			params.put("codigoInterfaz", codigoInterfaz);
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("descripcion", getDescripcionInterfaz(params));
			params.put("codigoUsuario", USUARIO_QUARTZ);
			
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", codigoPais);
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
			criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
			PedidoControlFacturacion controlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteriaPeriodo);
			
			String fechaProceso = controlFacturacion.getFechaProceso();
			params.put("fechaProceso", fechaProceso);
			
			
			//Recuperamos nroArchivosProcesarQuartzLARWeb
			params.put("nroArchivosProcesarQuartzLARWeb", "0");
			ParametroPais parametroPais2 = new ParametroPais();
			parametroPais.setCodigoPais(codigoPais);
			parametroPais.setCodigoSistema(Constants.LAR_CODIGO_SISTEMA);
			parametroPais.setNombreParametro(Constants.LAR_NRO_ARCHIVO_PROCESO_QUARTZ_RECEPCIONAR_CARGA_TRACKING);				
			
			List lista = genericoDAO.getParametrosPais(parametroPais2);
			if (lista != null && lista.size() > 0) {
				ParametroPais pbean = (ParametroPais)lista.get(0);
				String nroArchivosProcesarQuartzLARWeb = pbean.getValorParametro();
				params.put("nroArchivosProcesarQuartzLARWeb", nroArchivosProcesarQuartzLARWeb);
				
			}
			
			//insertamos el proceos batch
			Usuario usuario = (Usuario) params.get("usuario");
			procesoBatchService.deleteProcesoBatchActu(params, usuario);
			procesoBatchService.insertProcesoBatchActu(params, usuario);
		
			//ejecutamos proceso interfaz			
			params = this.interfazExecutionService.executeInterfazValidacionesPrevias(params);
			executionResult =interfazExecutionService.executeInterfaz(params);
			boolean completado = executionResult.ejecucionCompletada();
			if(!completado)				
				updateInterfazRegistroProcesoBatchError(params, usuario,new Exception(interfazResult.getMensaje()));
			else
				updateInterfazRegistroProcesoBatchOK(params,usuario);			
			
		}

		log.debug("fin job executeCargaInformacionTracking ");		
		
	}

	/**
	* Retorna la descripcion de la intefaz
	* @param params
	* @return
	*/
	private String getDescripcionInterfaz(Map params) {
		
	 	String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService
				.getInterfaz(interfazEjecucionPK);
		return (interfazEjecucion!=null?interfazEjecucion.getDescripcion():"");
	}


	/**
	 * @param micPrams
	 * @return
	 */
	private Map prepareParamsBeforeExecute(String codigoPais, String codigoInterfaz) {
		String codigoPeriodo =interfazSiCCService.getPeriodoDefaultByPaisCanal(codigoPais,Constants.CODIGO_CANAL_DEFAULT);
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
		
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaFacturacion",fechaFacturacion);
		params.put("numeroLote", "");
		params.put("codigoSistema", codigoSistema);
		params.put("codigoInterfaz", codigoInterfaz);	
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		
		//el usuario QUARTZ		
		Usuario usuario = new Usuario();
		usuario.setLogin(USUARIO_QUARTZ);
	    params.put("usuario",usuario);
		params.put("codigoUsuario", usuario.getLogin());
		return params;
	}
	
	/**
	 * Se encarga de verificar si ya esta en ejecucion
	 * @param micPrams
	 * @param codigoProcesoBatch 
	 * @return
	 */
	private boolean isEjecucionProceso(String codigoPais, String codigoInterfaz, String codigoProcesoBatch) {
		Map params = new HashMap();
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoSistema", codigoSistema);
		params.put("codigoProcesoBatch", codigoProcesoBatch);
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		
		/* Verificando que no se encuentre en Ejecucion */
		List listaProcesoBatch = procesoBatchService.getProcesoBatchActuByCriteria(params);
		if (listaProcesoBatch.size() > 0) {
			ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param params
	 * @param usuario
	 * @param e
	 */
	private void updateInterfazRegistroProcesoBatchError(Map params,
			Usuario usuario, Exception e) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR); 
		
		try{
			String descripcionLog = (e!=null?e.getMessage():"null");
		
			if (descripcionLog.length() >= 1000) {
				descripcionLog = descripcionLog.substring(1,999);
			}
		
			params.put("descripcionLog", descripcionLog);
			procesoBatchService.updateProcesoBatchActu(params, usuario);
		}catch(Exception ex){
			params.put("descripcionLog", ex.getMessage());
			procesoBatchService.updateProcesoBatchActu(params, usuario);
		}
		
	}

	/**
	 * @param params
	 * @param usuario
	 */
	private void updateInterfazRegistroProcesoBatchOK(Map params,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("descripcionLog", Constants.INTERFAZSICC_ARCHIVO_RECIBIDOGENERICO);
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
			
		procesoBatchService.updateProcesoBatchActu(params, usuario);		
	}

}
