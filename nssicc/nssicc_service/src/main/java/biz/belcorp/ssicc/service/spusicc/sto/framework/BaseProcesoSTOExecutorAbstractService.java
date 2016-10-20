package biz.belcorp.ssicc.service.spusicc.sto.framework;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOHistoricoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOResult;
import biz.belcorp.ssicc.service.spusicc.sto.framework.exception.ProcesoSTOException;
/**
 * Implementacion de ProcesoSTOExcecutionServiceImpl que utiliza un Map con las
 * implementaciones especificas de los Documentos STO SiCC inyectados mediante
 * Spring. Nuevos documentos requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseProcesoSTOExecutorAbstractService extends BaseService implements	ProcesoSTOExecutorService {
	protected final Log log = LogFactory.getLog(getClass());
	
	protected ProcesoSTOService procesoSTOService; 

	protected ProcesoSTOHistoricoService procesoSTOHistoricoService;
	
	/**
	 * @return the indicadorEjecutar
	 */
	public String getIndicadorEjecutar() {
		return indicadorEjecutar;
	}


	/**
	 * @param indicadorEjecutar the indicadorEjecutar to set
	 */
	public void setIndicadorEjecutar(String indicadorEjecutar) {
		this.indicadorEjecutar = indicadorEjecutar;
	}

	private Map stoImplementations;
	
	private String indicadorEjecutar = "S";
	
	
	/**
	 * @return the procesoSTOService
	 */
	public ProcesoSTOService getProcesoSTOService() {
		return procesoSTOService;
	}


	/**
	 * @return the procesoSTOHistoricoService
	 */
	public ProcesoSTOHistoricoService getProcesoSTOHistoricoService() {
		return procesoSTOHistoricoService;
	}


	/**
	 * @return the indicadorEjecutar
	 */
	protected boolean isProcesoActivo(DocumentoSTOParams documentoSTOParams) {
		return true;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.ProcesoSTOExecutionService#approveValidacionDocumentoSTO(java.util.Map, java.util.List)
	 */
	public DocumentoSTOResult executeProcess(AccionTipoDocumento accionTipoDocumento,Map params, List documentoValidacionList) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Entering 'executeProcess' method");
		
		
			DocumentoSTOParams documentoSTOParams = initializeDocumentoSTOParams(accionTipoDocumento,params, documentoValidacionList);
			
			List lista =documentoSTOParams.getStoList();
			int registrosProcesados= 0;
			
			if (lista!=null) registrosProcesados=lista.size();		
			DocumentoSTOResult documentoSTOResult = new DocumentoSTOResult(documentoSTOParams);
			
			BaseProcesoSTOService stoImpl = getSTOImplementation(documentoSTOParams);
			try {
				if (isProcesoActivo(documentoSTOParams)){
			     beforeExecute(documentoSTOParams);
				 execute(documentoSTOParams);
				 afterExecute(documentoSTOParams);
				 
				}
				 documentoSTOResult.updateSTOResultOnSuccess( registrosProcesados);
			     documentoSTOParams.getHistoricoTipoDocumento().updateSTOHistoricoOnSuccess(registrosProcesados);		
			
			} catch (Exception e) {
				log.error("Ocurrio una excepcion no controlada.");
				log.error(e);
			    documentoSTOResult.updateSTOResultOnException(e);
				documentoSTOParams.getHistoricoTipoDocumento().updateSTOHistoricoOnException(e);
				
			} finally {
				finalizeProcesoSTO( documentoSTOParams, documentoSTOResult);
			}
		return documentoSTOResult;
	}
	
	protected  void executeHilo(DocumentoSTOParams documentoSTOParams) throws Exception{};
	
	/**
	 * @param documentoSTOParams
	 * @throws Exception
	 */
	protected  void beforeExecute(DocumentoSTOParams documentoSTOParams) throws Exception{};
	
	/**
	 * @param documentoSTOParams
	 * @throws Exception
	 */
	protected  void afterExecute(DocumentoSTOParams documentoSTOParams) throws Exception{};
	
	/**
	 * @param documentoSTOParams
	 * @return
	 */
	protected abstract void execute(DocumentoSTOParams documentoSTOParams) throws Exception ;
	

	/**
	 * @param params
	 * @param stoList
	 */
	private synchronized DocumentoSTOParams initializeDocumentoSTOParams(AccionTipoDocumento accionTipoDocumento,Map params, List stoList) throws ProcesoSTOException {
		// Obtengo el usuario de los parametros 
		Usuario usuario = (Usuario) params.get("usuario");
		
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(accionTipoDocumento.getCodPais(),accionTipoDocumento.getCodTipoDocu());
		
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);
		
		String numeroProceso = (String) params.get("numeroProceso");
		
		if (StringUtils.isEmpty(numeroProceso))	numeroProceso = procesoSTOHistoricoService.getNumeroProceso(accionTipoDocumento);
		
		HistoricoTipoDocumento historico = new HistoricoTipoDocumento(accionTipoDocumento.getCodPais(),
				                                                      accionTipoDocumento.getCodTipoDocu(),
				                                                      accionTipoDocumento.getCodAcciTipoDocu(),
				                                                      usuario.getLogin(),numeroProceso,
				                                                      stoList);
			
		procesoSTOHistoricoService.insertHistoricoProceso(historico,usuario);
		
		DocumentoSTOParams documentoSTOParams = new DocumentoSTOParams(tipoDocumentoDigitado,historico, usuario,params,stoList);
		
		return documentoSTOParams;
	}
	
	/**
	 * @param params
	 * @param stoList
	 */
	private void finalizeProcesoSTO(DocumentoSTOParams documentoSTOParams, DocumentoSTOResult documentoSTOResult) throws ProcesoSTOException {
		
		HistoricoTipoDocumento historicoEjecucion = documentoSTOParams.getHistoricoTipoDocumento();
		Usuario usuario = documentoSTOParams.getUsuario();		
		procesoSTOHistoricoService.updateFinProceso(historicoEjecucion,usuario);
		log.info("Resultado de la ejecucion, result=" + documentoSTOResult);
		if (!documentoSTOResult.isCompletado()) {
			throw new ProcesoSTOException(documentoSTOResult.getMensaje());
		}
	}

	

	/**
	 * Obtiene la implementacion especifica del Tipo de Documento del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 *            utilizado como key del Map
	 * @return Implementacion especifica el documento STO
	 */
	protected BaseProcesoSTOService getSTOImplementation(DocumentoSTOParams documentoSTOParams) {
		String codigo = documentoSTOParams.getTipoDocumentoDigitado().getCodTipoDocu();
		return (BaseProcesoSTOService) stoImplementations.get(codigo);
	}

	/**
	 * @return the stoImplementations
	 */
	public Map getStoImplementations() {
		return stoImplementations;
	}

	/**
	 * @param stoImplementations
	 *            the stoImplementations to set
	 */
	public void setStoImplementations(Map stoImplementations) {
		this.stoImplementations = stoImplementations;
	}

	/**
	 * @param procesoSTOService
	 *            the procesoSTOService to set
	 */
	public void setProcesoSTOService(ProcesoSTOService procesoSTOService) {
		this.procesoSTOService = procesoSTOService;
	}

	/**
	 * @param procesoSTOHistoricoService
	 *            the procesoSTOHistoricoService to set
	 */
	public void setProcesoSTOHistoricoService(ProcesoSTOHistoricoService procesoSTOHistoricoService) {
		this.procesoSTOHistoricoService = procesoSTOHistoricoService;
	}

	
}
