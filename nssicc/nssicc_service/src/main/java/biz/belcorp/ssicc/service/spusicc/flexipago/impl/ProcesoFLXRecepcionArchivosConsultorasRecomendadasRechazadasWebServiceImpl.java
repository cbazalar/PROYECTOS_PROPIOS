package biz.belcorp.ssicc.service.spusicc.flexipago.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.spusicc.flexipago.ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService;

@Service("spusicc.procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebServiceImpl extends BaseService implements ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService {

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
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService#executeRecepcionArchivosConsultorasRecomendadasRechazadasWeb()
	 */
	public void executeRecepcionArchivosConsultorasRecomendadasRechazadasWeb() throws Exception {
		log.debug("inicio job executeRecepcionArchivosConsultorasRecomendadasRechazadasWeb ");	
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		
		log.debug("Procesando...");
		
		//Recuperamos el codigo Pais
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoSistema(Constants.SISTEMA_GEN);
		parametroPais.setCodigoParametro(Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);				
		
		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
		String codigoPais = pPais.getCodigoPais();		
		
		String codigoInterfaz = Constants.FLX_PROCESO_RECEPCIONAR_ARCHIVOS_WEB;
		String codigoProcesoBatch = Constants.FLX_CODIGO_PROCESO_BATCH_RECEPCIONAR_ARCHIVOS_WEB;
		
		if(isEjecucionProceso(codigoPais, codigoInterfaz, codigoProcesoBatch)){	
			
			Map params = prepareParamsBeforeExecute(codigoPais, codigoInterfaz);
			params.put("codigoInterfaz", codigoInterfaz);//el unitario
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("descripcion", getDescripcionInterfaz(params));
			
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

		log.debug("fin job executeRecepcionArchivosConsultorasRecomendadasRechazadasWeb ");		
		
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
