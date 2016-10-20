package biz.belcorp.ssicc.service.spusicc.sto.framework.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOExecutorAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOHilo;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOPool;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * Implementacion de ProcesoSTOExcecutionServiceImpl que utiliza un Map con las
 * implementaciones especificas de los Documentos STO SiCC inyectados mediante
 * Spring. Nuevos documentos requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOValidateExecutorServiceImpl extends BaseProcesoSTOExecutorAbstractService {
	
	/**
	 * @param documentoSTOParams
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	protected void execute(DocumentoSTOParams documentoSTOParams) throws Exception{
		
		log.info("Iniciando procesamiento de Validaciones");
		
		prepareDocumentoSTOForValidate(documentoSTOParams);			
		HistoricoTipoDocumento historicoTipoDocumento = documentoSTOParams.getHistoricoTipoDocumento();
		
		List listaProcesos = procesoSTOHistoricoService.getListaProcesosHijos(historicoTipoDocumento);
		String indValiMultHilo = documentoSTOParams.getTipoDocumentoDigitado().getIndValiMultHilo();
		
		
		Integer nThreads= documentoSTOParams.getTipoDocumentoDigitado().getNumMaxiHilo();
		
		if (nThreads>0) {

			ExecutorService executor = Executors.newFixedThreadPool(nThreads);
			List<Future<DocumentoSTOParams>> list = new ArrayList<Future<DocumentoSTOParams>>();
			for (int i = 0; i < listaProcesos.size(); i++) {
				
				DocumentoSTOParams documentoSTOParamsHijo = (DocumentoSTOParams)documentoSTOParams.clone();
				HistoricoTipoDocumento historicoTipoDocumentoHijo = (HistoricoTipoDocumento)listaProcesos.get(i);
				documentoSTOParamsHijo.setHistoricoTipoDocumento(historicoTipoDocumentoHijo);
				documentoSTOParamsHijo.prepareQueryParams();
				
				
				Callable<DocumentoSTOParams> worker = new ProcesoSTOPool(this, documentoSTOParamsHijo);
				Future<DocumentoSTOParams> submit = executor.submit(worker);
				list.add(submit);
			}
			
			String mensajeError="";
		    boolean isSuccess = true;
		    
			for (Future<DocumentoSTOParams> future : list) {
				
				DocumentoSTOParams  proceso = (DocumentoSTOParams)future.get();
				HistoricoTipoDocumento historicoTipoDocumentoHijo = proceso.getHistoricoTipoDocumento();
		        
				if (proceso.getHistoricoTipoDocumento().getFlagError().equals(Constants.SI)){
		        	isSuccess=false;
		        	mensajeError+=historicoTipoDocumentoHijo.getNumeroProceso() + " " + historicoTipoDocumentoHijo.getDescripcionError() + " \n";
		        	log.error(mensajeError);
		        }
				
			}
			
			executor.shutdown();
			
			if (!isSuccess) throw new Exception(mensajeError); 
			
		}
		
		else {
		
			ArrayList<ProcesoSTOHilo> listaHilos = new ArrayList<ProcesoSTOHilo>();
			for (int i = 0; i < listaProcesos.size(); i++) {
				
				DocumentoSTOParams documentoSTOParamsHijo = (DocumentoSTOParams)documentoSTOParams.clone();
				HistoricoTipoDocumento historicoTipoDocumentoHijo = (HistoricoTipoDocumento)listaProcesos.get(i);
				documentoSTOParamsHijo.setHistoricoTipoDocumento(historicoTipoDocumentoHijo);
				documentoSTOParamsHijo.prepareQueryParams();
				if (StringUtils.equals(indValiMultHilo, "S")){
					ProcesoSTOHilo hilo = new ProcesoSTOHilo(this, documentoSTOParamsHijo);
					listaHilos.add(hilo);				
				}
				else executeValidacionDocumentoSTO(documentoSTOParamsHijo);
			}
			/*LANZANDO LOS HILOS*/
			for (int i = 0; i < listaHilos.size(); i++) {
				ProcesoSTOHilo hilo = listaHilos.get(i);
				hilo.start();
			}
			
			/*ESPERANDO QUE FINALICEN*/
			for (int i = 0; i < listaHilos.size(); i++) {
				ProcesoSTOHilo hilo = listaHilos.get(i);
				hilo.join();
			}
			
			
			listaProcesos = procesoSTOHistoricoService.getListaProcesosHijos(historicoTipoDocumento);
		    
			String mensajeError="";
			boolean isProcessError = false;
			for (int i = 0; i < listaProcesos.size(); i++) {
				HistoricoTipoDocumento historicoTipoDocumentoHijo = (HistoricoTipoDocumento)listaProcesos.get(i);
				
				String indicadorError = historicoTipoDocumentoHijo.getFlagError();
							
				if (StringUtils.equals(indicadorError, "S")) {
					mensajeError += historicoTipoDocumentoHijo.getNumeroProceso() + " " +historicoTipoDocumentoHijo.getDescripcionError() + " \n";
					
					if (mensajeError.length() > 3800)
						mensajeError = mensajeError.substring(1,3800);
					isProcessError=true;
				}
			}
			if (isProcessError) throw new Exception(mensajeError);
		}
		
	}
	
	
	/**
	 * Ejecuta la Interfaz SiSiCC, prepara los parametros para la ejecucin y
	 * delega la ejecucin al 'InterfazExecutionService'.
	 */
	private void executeValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception {
		
		BaseProcesoSTOService stoImpl = getSTOImplementation(documentoSTOParams);
		
		HistoricoTipoDocumento historicoTipoDocumentoHijo = documentoSTOParams.getHistoricoTipoDocumento();
		
		procesoSTOHistoricoService.UpdateInicioProceso(historicoTipoDocumentoHijo);
		insertHistoricoValidaciones(historicoTipoDocumentoHijo);
		try {
			stoImpl.executeValidacionDocumentoSTO(documentoSTOParams);
		}catch (Exception e) {
			log.error("Hilo " + historicoTipoDocumentoHijo.getNumeroProceso() + " " + e);
			historicoTipoDocumentoHijo.updateSTOHistoricoOnException(e);			
		}
		procesoSTOHistoricoService.updateFinProceso(historicoTipoDocumentoHijo,documentoSTOParams.getUsuario());
	}
	

	/**
	 * Prepara los documentos enviados en la lista para que puedan ser validados
	 * 
	 * @param documentoSTOParams
	 *            parametros STO
	 */
	protected  void insertHistoricoValidaciones(HistoricoTipoDocumento historico){
		procesoSTOHistoricoService.insertHistoricoValidaciones(historico);
	}

	/**
	 * Ejecuta la Interfaz SiSiCC, prepara los parametros para la ejecucin y
	 * delega la ejecucin al 'InterfazExecutionService'.
	 */
	public void executeHilo(DocumentoSTOParams documentoSTOParams) throws Exception {
		executeValidacionDocumentoSTO(documentoSTOParams);
	}
	
	
	/**
	 * Prepara los documentos enviados en la lista para que puedan ser validados
	 * 
	 * @param documentoSTOParams
	 *            parametros STO
	 */
	protected  void prepareDocumentoSTOForValidate(DocumentoSTOParams documentoSTOParams){
    	
		Map queryParams = documentoSTOParams.getQueryParams();
    	
    	List stoList = documentoSTOParams.getStoList();
    	HistoricoTipoDocumento historicoTipoDocumento = documentoSTOParams.getHistoricoTipoDocumento();
    	if (StringUtils.equals(historicoTipoDocumento.getIndicadorMasivo(), "N")){
    		
    		HashSet hashset = new HashSet();
    		for (int i = 0; i < stoList.size(); i++) {
    			Object stoItem =  stoList.get(i);
    			DocumentoDigitadoPK documento = null;
    			if(stoItem instanceof GestionDocumento) {
    				GestionDocumento gestionDocumento=(GestionDocumento)stoItem;
    				documento=new DocumentoDigitadoPK();
        			documento.setCodPais(gestionDocumento.getCodigoPais());
        			documento.setCodTipoDocu(gestionDocumento.getDocumento());
        			documento.setNumLote(gestionDocumento.getLote());
        			documento.setSecNumeDocu(gestionDocumento.getNumeroDocumento());	
    			}
    			else{
    				documento = (DocumentoDigitadoPK)stoItem;
    			}
    			if (!hashset.contains(documento)) hashset.add(documento);
			}
    		queryParams.put("HashSetSTO", hashset);
			procesoSTOService.updateDocumentoForValidate(queryParams);
    	}
    	else procesoSTOService.inicializeRegistrosProcesados(queryParams);
	}

}
