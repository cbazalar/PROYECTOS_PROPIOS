package biz.belcorp.ssicc.service.spusicc.sto.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOHistoricoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoValidacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * @author PEJCAIRAMPOMA
 *
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public  class BaseProcesoSTOAbstractService extends BaseService implements BaseProcesoSTOService {
	protected final Log log = LogFactory.getLog(getClass());

	protected ProcesoSTODAO procesoSTODAO;
	
	protected ProcesoSTOHistoricoDAO procesoSTOHistoricoDAO;
	
	protected ProcesoBatchService procesoBatchService;
	
	private Map stoValidations;
	
	/**
	 * @param stoProcess
	 */
	public void setStoValidations(Map stoValidations) {
		this.stoValidations = stoValidations;
	}

	
	/**
	 * Obtiene la implementacion especifica del Tipo de Documento del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 *            utilizado como key del Map
	 * @return Implementacion especifica el documento STO
	 */
	private BaseProcesoSTOValidationExecutorAbstractService getStoValidations(String codigoValidacion) {
		BaseProcesoSTOValidationExecutorAbstractService base = (BaseProcesoSTOValidationExecutorAbstractService) stoValidations.get(codigoValidacion);
		return base;
		
	}
	
	/**
	 * Obtiene la implementacion especifica del Tipo de Documento del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 *            utilizado como key del Map
	 * @return Implementacion especifica el documento STO
	 */
	private ProcesoSTOValidationExecutorService getStoValidationsInterface(String codigoValidacion) {
		ProcesoSTOValidationExecutorService base = (ProcesoSTOValidationExecutorService) stoValidations.get(codigoValidacion);
		return base;
		
	}
	
		/**
	 * @param procesoSTODAO the procesoSTODAO to set
	 */
	public void setProcesoSTODAO(ProcesoSTODAO procesoSTODAO) {
		this.procesoSTODAO = procesoSTODAO;
	}
	
	/**
	 * @param procesoSTOHistoricoService
	 */
	public void setProcesoSTOHistoricoDAO(ProcesoSTOHistoricoDAO procesoSTOHistoricoDAO) {
		this.procesoSTOHistoricoDAO = procesoSTOHistoricoDAO;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framRework.BaseProcesoSTOService#approveValidacionDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams, java.util.List)
	 */
	public void approveValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception{
		
		log.debug("Entering 'executeValidacionDocumentoSTO' method");
		
	    Map queryParams = documentoSTOParams.getQueryParams();
	    String namespaceSTO =  documentoSTOParams.getTipoDocumentoDigitado().getNamespace();
	    
		List lista =documentoSTOParams.getStoList();
	    for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    	 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
	    	 queryParams.put("gestionDocumento",gestionDocumento);
	    	 String indicadorGestionable = gestionDocumento.getIndGestionable(); 
	    	 if (Constants.STO_TIPO_GESTIONABLE_SI.equals(indicadorGestionable))
	    	 {
	    		 procesoSTODAO.approveValidacionDocumentoSTO(queryParams,namespaceSTO);
	    	 }
	  	}			
	    
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOService#disapproveValidacionDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams, java.util.List)
	 */
	public void disapproveValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception {
		    	
	    Map queryParams = documentoSTOParams.getQueryParams();
	    String namespaceSTO =  documentoSTOParams.getTipoDocumentoDigitado().getNamespace();
		
		List lista =documentoSTOParams.getStoList();
	    for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    	 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
	    	 queryParams.put("gestionDocumento",gestionDocumento);
	    	 
	    	 String indicadorGestionable = gestionDocumento.getIndGestionable(); 
	    	 if (Constants.STO_TIPO_GESTIONABLE_SI.equals(indicadorGestionable))
	    	 {
	    		 procesoSTODAO.disapproveValidacionDocumentoSTO(queryParams,namespaceSTO);
	    	 }
	    }			
	}
	
	/**
	 * @param documentoSTOParams
	 * @throws Exception
	 */
	public void prepareRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception{

	    Map queryParams = documentoSTOParams.getQueryParams();
		String motivoRechazo = (String) queryParams.get("motivoRechazo");
 		String observaciones = (String) queryParams.get("observaciones");	

 		List lista =documentoSTOParams.getStoList();

 		List listaRechazo = new ArrayList();

	    for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    	 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
	    	 gestionDocumento.setMotivoRechazo(motivoRechazo);
	    	 gestionDocumento.setObservaciones(observaciones);
	    	 String indicadorRechazoOrigen = gestionDocumento.getIndRechazoOrigen();
	    	 if ((Constants.SI.equals(indicadorRechazoOrigen)) || (indicadorRechazoOrigen == null)){
		    	 String indicadorGestionRechazo = gestionDocumento.getIndGestionRechazo();
		    	 if (Constants.STO_TIPO_GESTION_RECHAZO_SI.equals(indicadorGestionRechazo)){
		    		 listaRechazo.add(gestionDocumento);
		    	 }
	    	 }

	  	}
		documentoSTOParams.setStoList(listaRechazo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOService#rejectDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams)
	 */
	public void rejectDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception{
		log.warn("Iniciando rejectDocumentoSTO");

		prepareRejectDocumentoSTO(documentoSTOParams);
		beforeRejectDocumentoSTO(documentoSTOParams);
		
		Map queryParams = documentoSTOParams.getQueryParams();
		List lista =documentoSTOParams.getStoList();

	    for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    	 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
	    	 queryParams.put("gestionDocumento",gestionDocumento);
	    	 procesoSTODAO.rejectDocumentoSTO(queryParams);
	  	}
	    
	    procesoSTODAO.executeAuditoriaProcesoSTO(queryParams);

	    afterRejectDocumentoSTO(documentoSTOParams);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOService#executeHistoricoDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams)
	 */
	public void executeHistoricoDocumentoSTO(DocumentoSTOParams documentoSTOParams){}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOService#executeValidacionDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams)
	 */
	public void executeValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception{
		
		beforeProcessValidacionDocumentoSTO(documentoSTOParams);			

    	Map queryParams = documentoSTOParams.getQueryParams();
    	String namespaceSTO =  documentoSTOParams.getTipoDocumentoDigitado().getNamespace();
    	HistoricoTipoDocumento historico = documentoSTOParams.getHistoricoTipoDocumento();
    	
    	procesoSTODAO.executeDeleteErrores(queryParams);
    	List listaValidaciones =procesoSTODAO.getValidacionesProceso(historico);
    	for (Iterator iterator = listaValidaciones.iterator(); iterator.hasNext();) {
    		
    		ValidacionDocumento validacionDocumento = (ValidacionDocumento) iterator.next();
    		queryParams.put("validacionDocumento",validacionDocumento);
    		String codigoValidacion = validacionDocumento.getCodValidacion();
    		HistoricoValidacion historicoValidacion = new HistoricoValidacion(validacionDocumento.getCodigoPais(), validacionDocumento.getCodigoTipo(), validacionDocumento.getCodValidacion(),historico.getNumeroProceso()); 
    		procesoSTOHistoricoDAO.updateInicioValidacion(historicoValidacion);
    		try {
    			 if (getStoValidationsInterface(codigoValidacion)==null){
    				 procesoSTODAO.executeValidacion(queryParams,namespaceSTO);
    			 }
    			 else {
    				 ProcesoSTOValidationExecutorService baseStoValidation = getStoValidationsInterface(codigoValidacion);
    				 baseStoValidation.executeValidation(validacionDocumento,namespaceSTO,queryParams);
    			 }
    			 
			} catch (Exception e) {
				historicoValidacion.updateOnError(e);
			}
    		finally{
    			procesoSTOHistoricoDAO.updateFinValidacion(historicoValidacion);
    			if (StringUtils.equals(historicoValidacion.getFlagError(), Constants.SI)){
    				throw new Exception(historicoValidacion.getLogError());
    			}
    		}
  		}	
    	
        this.procesoSTODAO.executeCargaRegistrosValidos(queryParams,namespaceSTO);
		
        this.afterProcessValidacionDocumentoSTO(documentoSTOParams);	
	}
	
	
	/**
	 * Metodo ejecutado antes de 'processInterfaz'. Este método no tiene
	 * implementación, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional antes del procesamiento de la Interfaz.
	 * 
	 * @param documentoSTOParams
	 * @throws InterfazException
	 */
	protected void beforeProcessValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessValidacionDocumentoSTO' method");
	}
	
	

	/**
	 * Metodo ejecutado despues de 'processInterfaz'. Este método no tiene
	 * implementación, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional despues del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	public void afterProcessValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterProcessValidacionDocumentoSTO' method");
	}
	
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void beforeRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' method");
	}
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void afterRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' method");
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOService#executeEliminacionPedidosSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams)
	 */
	public void deleteDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception {

		List stoList =  documentoSTOParams.getStoList();
		
		Map queryParams = documentoSTOParams.getQueryParams();
		
		for (int i = 0; i < stoList.size(); i++) {
			DocumentoDigitadoPK documento = (DocumentoDigitadoPK)stoList.get(i);
			queryParams.put("documento", documento);
			executeDeleteDocumentoSTO(documentoSTOParams);
		}
	}

	/**
	 * @param documentoSTOParams
	 */
	public void executeDeleteDocumentoSTO(DocumentoSTOParams documentoSTOParams){};
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOService#recoverRejectDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams)
	 */
	public void recoverRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception{
		
		log.warn("Iniciando recoverRejectDocumentoSTO");

		beforeRecoverRejectDocumentoSTO(documentoSTOParams);
		
		Map queryParams = documentoSTOParams.getQueryParams();
		List lista =documentoSTOParams.getStoList();

	    for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    	DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
	    	queryParams.put("documentoDigitadoPK",documentoDigitadoPK);
	    	procesoSTODAO.executeRecoverRejectDocumentoSTO(queryParams);
	  	}
	    
	    procesoSTODAO.executeAuditoriaProcesoSTO(queryParams);

	    afterRecoverRejectDocumentoSTO(documentoSTOParams);
		
	}
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void beforeRecoverRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' method");
	}
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void afterRecoverRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' method");
	}


	
	
	
	
	/**
	 * @return the procesoBatchService
	 */
	public ProcesoBatchService getProcesoBatchService() {
		return procesoBatchService;
	}


	/**
	 * @param procesoBatchService the procesoBatchService to set
	 */
	public void setProcesoBatchService(ProcesoBatchService procesoBatchService) {
		this.procesoBatchService = procesoBatchService;
	}
	
	
	
}
