/*
 * Created on 22-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.scsicc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.ProcesoBatchDAO;
import biz.belcorp.ssicc.dao.sisicc.model.NroLoteMultiHilo;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoBatchServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
@Service("scsicc.procesoBatchService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoBatchServiceImpl extends BaseService implements	ProcesoBatchService {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="scsicc.procesoBatchDAO")
	private ProcesoBatchDAO procesoBatchDAO;
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionDAO")
	MantenimientoOCRPedidoControlFacturacionDAO mantenimientoOCRPedidoControlFacturacionDAO;
	
	
	public Long getSecuenciaSiguienteProcesoBatchActu() {
		return procesoBatchDAO.getSecuenciaSiguienteProcesoBatchActu();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#getProcesoBatchByCriteria(java.util.Map)
	 */
	public List getProcesoBatchByCriteria(Map criteria) {
    	return procesoBatchDAO.getProcesoBatchByCriteria(criteria);
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#getProcesoBatchActuByCriteria(java.util.Map)
     */
    public List getProcesoBatchActuByCriteria(Map criteria) {
    	return procesoBatchDAO.getProcesoBatchActuByCriteria(criteria);
    }
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#getObtieneNroLoteProcesoBatchActu(java.util.Map)
     */
    public List<NroLoteMultiHilo> getObtieneNroLoteProcesoBatchActu(Map criteria) {
    	return procesoBatchDAO.getObtieneNroLoteProcesoBatchActu(criteria);
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#getProcesoBatchHistoByCriteria(java.util.Map)
	 */
	public List getProcesoBatchHistoByCriteria(Map criteria) {
		return procesoBatchDAO.getProcesoBatchHistoByCriteria(criteria);
	}

	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#insertProcesoBatchActu(java.util.Map, biz.belcorp.ssicc.model.Usuario)
     */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void insertProcesoBatchActu(Map criteria, Usuario usuario) {
    	Map criteriaAux = new HashMap();
    	criteriaAux.put("codigoPais", (String)criteria.get("codigoPais"));
    	criteriaAux.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
    	criteriaAux.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionDAO.getControlFacturacionById(criteriaAux);
    	
		if(controlFacturacion != null) {
			criteria.put("periodoBatch", controlFacturacion.getCodigoPeriodo());
			criteria.put("fechaBatch", controlFacturacion.getFechaProceso());
		} 
		procesoBatchDAO.insertProcesoBatchActu(criteria, usuario);
    	
    	
    }
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void insertProcesoBatchActu2(Map criteria, Usuario usuario) {
		procesoBatchDAO.deleteProcesoBatchActu(criteria, usuario);
		
    	Map criteriaAux = new HashMap();
    	criteriaAux.put("codigoPais", (String)criteria.get("codigoPais"));
    	criteriaAux.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
    	criteriaAux.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionDAO.getControlFacturacionById(criteriaAux);
    	
		if(controlFacturacion != null) {
			criteria.put("periodoBatch", controlFacturacion.getCodigoPeriodo());
			criteria.put("fechaBatch", controlFacturacion.getFechaProceso());
		} 
		procesoBatchDAO.insertProcesoBatchActu(criteria, usuario);
    	
    	
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.scsicc.ProcesoBatchService#deleteProcesoBatchActu(java.util.Map, biz.belcorp.ssicc.dao.model.Usuario)
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteProcesoBatchActu(Map criteria, Usuario usuario) {
    	procesoBatchDAO.deleteProcesoBatchActu(criteria, usuario);
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#updateProcesoBatchActu(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateProcesoBatchActu(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateProcesoBatchActu(criteria, usuario);
	}
    
    public void updateProcesoBatchActu02(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateProcesoBatchActu(criteria, usuario);
	}
    
    @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateProcesoBatchActu03(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateProcesoBatchActu(criteria, usuario);
		procesoBatchDAO.updateProcesoBatchEtapaProceso(criteria, usuario);
		procesoBatchDAO.executeProcesoBatchActuRecomendacionError(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#updateEstadoProcesoBatchActu(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoProcesoBatchActu(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateEstadoProcesoBatchActu(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#updateEstadoProcesoBatchActu(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateEstadoProcesoBatchActu2(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateEstadoProcesoBatchActu(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#updateProcesoBatchEtapaProceso(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateProcesoBatchEtapaProceso(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateProcesoBatchEtapaProceso(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ProcesoBatchService#updateProcesoBatchEtapaProceso02(java.util.Map, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateProcesoBatchEtapaProceso02(Map criteria, Usuario usuario) {
		procesoBatchDAO.updateProcesoBatchEtapaProceso(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#executeProcesoBatchActuRecomendacionError(java.util.Map)
	 */	
	public void executeProcesoBatchActuRecomendacionError(Map criteria) {
		log.debug("Entering 'executeProcesoBatchActuRecomendacionError' method");
		Usuario usuario = (Usuario)criteria.get("usuario");
		try {
			procesoBatchDAO.executeProcesoBatchActuRecomendacionError(criteria);
		}
		catch(Exception e) {
			String finProceso = messageSource.getMessage("titulo.recomendacion.finProceso",null,getLocale(usuario));
			criteria.put("descripcionEtapaProceso",finProceso);
			this.updateProcesoBatchEtapaProceso02(criteria, usuario); //FRAMNEWORK NSSICC
			return;
		}
		String recomendacionError = (String)criteria.get("recomendacionError");
		if (StringUtils.isNotBlank(recomendacionError)) {
			String prefijoProceso = messageSource.getMessage("titulo.recomendacion.prefijoProceso",null,getLocale(usuario));
			String sufijoProceso = (String)criteria.get("descripcionEtapaProceso");
			log.debug("Entering 'executeProcesoBatchActuRecomendacionError recomendacionError: ' " + recomendacionError);
			log.debug("Entering 'executeProcesoBatchActuRecomendacionError Sufijo: ' " + sufijoProceso);
			
			if (Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT.equals(sufijoProceso))
				sufijoProceso = " ";
			String recomendacionFinal = recomendacionError + prefijoProceso + " " + sufijoProceso;
			criteria.put("recomendacionError",recomendacionFinal);
			this.updateProcesoBatchRecomendacion(criteria, usuario);
		}
		else {
			String finProceso = messageSource.getMessage("titulo.recomendacion.finProceso",null,getLocale(usuario));
			criteria.put("descripcionEtapaProceso",finProceso);
			this.updateProcesoBatchEtapaProceso02(criteria, usuario); //FRAMNEWORK NSSICC
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#getProcesoBatchActuDependientesByCriteria(java.util.Map)
	 */
	public List getProcesoBatchActuDependientesByCriteria(Map criteria) {
		return procesoBatchDAO.getProcesoBatchActuDependientesByCriteria(criteria);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ProcesoBatchService#getParametrosProcesoBatch(java.util.Map)
	 */
	public Map getParametrosProcesoBatch(Map criteria){
		List l = new ArrayList();
		Map result = new HashMap();
		l = procesoBatchDAO.getParametrosProcesoBatch(criteria);
		for (int i = 0; i < l.size(); i++) {
			Map map = (Map)l.get(i);
			result.put(map.get("nombreParametro"), map.get("valorParametro"));
		}
		return result;
	}
	
	/* INI FRAMEWORK NSSICC */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ProcesoBatchService#updateProcesoBatchActuLote(java.util.Map, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateProcesoBatchActuLote(Map criteria, Usuario usuario) {
		this.procesoBatchDAO.updateProcesoBatchActuLote(criteria, usuario);
	}
	
	public void updateProcesoBatchRecomendacion(Map criteria, Usuario usuario) {
		this.procesoBatchDAO.updateProcesoBatchRecomendacion(criteria, usuario);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#executeInterfazValidacionesPrevias(java.util.Map)
	 */
	public Map<String, Object> executeProcesoValidacionesPrevias(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeProcesoValidacionesPrevias' method");
		
		params = this.executeProcesoValidacionesPreviasBase(params);
		params = this.executeProcesoValidacionesPreviasPaquete(params);
		
		
		
		if (log.isDebugEnabled())
			log.debug("Fin 'executeInterfazValidacionesPrevias' method");
		return params;
	}
	
	/**
	 * Ejecuta las validaciones BASE antes de la ejecucion del Proceso
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> executeProcesoValidacionesPreviasBase(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeProcesoValidacionesPrevias' method");
		
		Usuario usuario = (Usuario) params.get("usuario");
		
	    /* Validaciones INICIALES */
		String codigoPais = (String) params.get("codigoPais");
		if (StringUtils.isBlank(codigoPais)) {
		    String error = this.getKeyMessage("interfaz.error.codigoPais", usuario);
			throw new Exception(error);
		}
		
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isNotBlank(codigoProcesoBatch)) {
			String codigoSistema = (String) params.get("codigoSistema");
			if (StringUtils.isBlank(codigoSistema)) {
			    String error = this.getKeyMessage("interfaz.error.codigoSistema", usuario);
				throw new Exception(error);
			}
		}
		return params;
	}
	
	
	/**
	 * Ejecuta las validaciones BASE antes de la ejecucion de Procesos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> executeProcesoValidacionesPreviasPaquete(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeProcesoValidacionesPreviasPaquete' method");
		
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		
		
		/* Validando validaciones propias de Paquete de Interfaz */
		if (StringUtils.isNotBlank(codigoProcesoBatch)) {
			
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSistema", codigoSistema);
			
			if (StringUtils.isNotBlank(codigoProcesoBatch)) {
				criteria.put("codigoProcesoBatch", codigoProcesoBatch);
				
				/* Verificando que se encuentre registrado en Base de Datos */
				List listaProcesoBatch = this.getProcesoBatchByCriteria(params);
				if (listaProcesoBatch == null || listaProcesoBatch.size() == 0 ) {
					String mensajeWarnig03 = this.getKeyMessage("procesoBatch.error.registrarProcesoBD", new Object[]{codigoProcesoBatch});
					throw new Exception(mensajeWarnig03);
				}
				
				
				/* Verificando que no se este ejecutando el Proceso Batch */
				String ejecucion = this.verificarProcesoBatchEnEjecucion(criteria);
				if (StringUtils.isNotBlank(ejecucion)) 
					throw new Exception(ejecucion);
				
				/* Verificando que no se este ejecutando Procesos Batch Dependientes */
				ejecucion =  this.verificarProcesoBatchEnEjecucionDependientes(criteria);
				if (StringUtils.isNotBlank(ejecucion)) 
					throw new Exception(ejecucion);
		    }
				
		
		}
		
		if (log.isDebugEnabled())
			log.debug("Fin 'executeProcesoValidacionesPreviasPaquete' method");
		
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#verificarProcesoBatchEnEjecucion(java.util.Map)
	 */
	public String verificarProcesoBatchEnEjecucion(Map<String, Object> params) {
		String codigpProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigpProcesoBatch)) return null;
		
		/* Verificando que no se encuentre en Ejecucion */
		String mensajeWarnig02 = this.getKeyMessage("procesoBatch.error.procesoEnEjecucion");
		try {
			List listaProcesoBatch = this.getProcesoBatchActuByCriteria(params);
			if (listaProcesoBatch.size() > 0) {
				ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
				if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
					return mensajeWarnig02;
				}
			}
		}
		catch (Exception e) {
			return this.obtieneMensajeErrorException(e);
		}
		return null;
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#verificarProcesoBatchEnEjecucionDependientes(java.util.Map)
	 */
	public String verificarProcesoBatchEnEjecucionDependientes(Map<String, Object> params) {
		String codigpProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigpProcesoBatch)) return null;
		
		try {
			List listaProcesoBatchDependientes = this.getProcesoBatchActuDependientesByCriteria(params);
			if (listaProcesoBatchDependientes.size() > 0) {
				
				String mensaje = this.getKeyMessage("procesoBatch.error.procesoDependienteEnEjecucion");
				for (int i = 0; i < listaProcesoBatchDependientes.size(); i++) {
					ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatchDependientes.get(i);
					mensaje  +=  "\n" + procesoBatchActu.getCodigoSistema() + "-" + procesoBatchActu.getProcesoBatch().getCodigoProcesoBatch();
					mensaje  +=  " " + procesoBatchActu.getProcesoBatch().getDescripcionProcesoBatch();
					mensaje += "; ";
				}
				mensaje +=  "\n" + this.getKeyMessage("procesoBatch.error.espereProcesoDependienteEnEjecucion");
				return mensaje;			
			}
		}
		catch (Exception e) {
			return this.obtieneMensajeErrorException(e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ProcesoBatchService#beforeNewTransaction(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams, biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void beforeNewTransaction(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception {
		service.beforeNewTransaction(interfazParams);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ProcesoBatchService#afterNewTransaction(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams, biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void afterNewTransaction(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception {
		service.afterNewTransaction(interfazParams);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void afterNewProcessInterfaz(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception {
		service.afterNewProcessInterfaz(interfazParams);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void afterNewExecuteInterfaz(Map params, BaseInterfazAbstractService service) throws Exception {
		service.afterNewExecuteInterfaz(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ProcesoBatchService#processInterfazNEW(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams, biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public int processInterfazNEW(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception {
		return service.processInterfazNEW(interfazParams);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateInterfazRegistroProcesoBatch(Map params,			
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult,
			Usuario usuario, SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl service) {
		service.updateInterfazRegistroProcesoBatch(params, interfazExecutionResult, usuario);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateEstadoEjecucionInterfazProcesoBatch(Map params,
			Usuario usuario, SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl service) {
		service.updateEstadoEjecucionInterfazProcesoBatch(params, usuario);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateInterfazRegistroProcesoBatch(Map params, Usuario usuario,
			Exception exception, SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl service) {
		service.updateInterfazRegistroProcesoBatch(params, usuario, exception);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void afterProcessValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams, BaseProcesoSTOAbstractService service){
		service.afterProcessValidacionDocumentoSTO(documentoSTOParams);
	}
	
	/* FIN FRAMEWORK NSSICC */

	
}
