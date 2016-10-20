package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.ocr.gen.GenericoOCRComercialFacadeService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * Service que executa las validaciones de STO
 *  
 * <p>
 * <a href="ProcesoSTOEjecucionValidacionesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman</a>
 * 
 */
@Service("spusicc.procesoSTOEjecucionValidacionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOEjecucionValidacionesServiceImpl extends BaseService implements ProcesoSTOEjecucionValidacionesService {
	
	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
	
	@Resource(name="spusicc.procesoSTOService")
	private ProcesoSTOService procesoSTOService;	
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	private ProcesoSTOExecutionService procesoSTOExecutionService;
	
	@Resource(name="genericoService")
	private GenericoService genericoService;
	
	@Resource(name="ocr.genericoOCRComercialFacadeService")
	private GenericoOCRComercialFacadeService genericoOCRComercialFacadeService;
	
	@Resource(name="spusicc.procesoSTOEjecucionValidacionesDAO")
	private ProcesoSTOEjecucionValidacionesDAO procesoSTOEjecucionValidacionesDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTiposDocumentosSTO()
	 */
	public List getTiposDocumentosSTO(Map criteria) {
		log.debug("Entering method 'getTiposDocumentosSTO()'");
		return procesoSTOEjecucionValidacionesDAO.getTiposDocumentosSTO(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getComponentesDocumentos(java.util.Map)
	 */
	public List getComponentesDocumentos(Map criteria) {
		log.debug("Entering method 'getComponentesDocumentos()'");
		return procesoSTOEjecucionValidacionesDAO.getComponentesDocumentos(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeValidacion(java.lang.String, java.util.Map)
	 */
	public void executeValidacion(String method, Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeValidacion(method,criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTipoDocumentoDigitado(java.lang.String)
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado(String codigo) {
				
		return procesoSTOEjecucionValidacionesDAO.getTipoDocumentoDigitado(codigo);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeValidacionConsolidado(java.lang.String, java.util.Map)
	 */
	public void executeValidacionConsolidado(String method, Map criteria) {
		procesoSTOEjecucionValidacionesDAO.executeValidacionConsolidado(method,criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getProcesoSTO(java.util.Map)
	 */
	public String getProcesoSTO(Map criteria) {
		String numeroProceso = procesoSTOEjecucionValidacionesDAO.getNumeroProcesoActual(criteria);
		criteria.put("numeroProceso", numeroProceso);
		procesoSTOEjecucionValidacionesDAO.insertInicioProcesoSTO(criteria);
		
		return numeroProceso;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTiposDocumentosIdentidadSTO()
	 */
	public List getTiposDocumentosIdentidadSTO() {
		log.debug("Entering method 'getTiposDocumentosIdentidadSTO()'");
		return procesoSTOEjecucionValidacionesDAO.getTiposDocumentosIdentidadSTO();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getConsultaClientesList(java.util.Map)
	 */
	public List getConsultaClientesList(Map criteria) {
		log.debug("Entering method 'getConsultaClientesList()'");
		return procesoSTOEjecucionValidacionesDAO.getConsultaClientesList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeGenerarCodigoClienteSTO(java.util.Map)
	 */
	public String executeGenerarCodigoClienteSTO(Map criteria) {
		log.debug("Entering method 'executeGenerarCodigoClienteSTO()'");
		return procesoSTOEjecucionValidacionesDAO.executeGenerarCodigoClienteSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateNumeroSolicitud(java.util.Map)
	 */
	public void updateNumeroSolicitud(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.updateNumeroSolicitud(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateRegistrosControlValidacion(java.util.Map)
	 */
	public void updateRegistrosControlValidacion(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateRegistrosControlValidacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#STOGrabarPedRec(java.util.Map)
	 */
	public void STOGrabarPedRec(Map criteria){
		procesoSTOEjecucionValidacionesDAO.STOGrabarPedRec(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateFinProcesoSTO(java.util.Map)
	 */
	public void updateFinProcesoSTO(Map criteria) {
		
		procesoSTOEjecucionValidacionesDAO.updateFinProcesoSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateNumeroProceso(java.util.Map)
	 */
	public void updateNumeroProceso(Map criteria){
		
		procesoSTOEjecucionValidacionesDAO.updateNumeroProceso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateNumeroProcesoDetalles(java.util.Map)
	 */
	public void updateNumeroProcesoDetalles(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateNumeroProcesoDetalles(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoDetalle(java.util.Map)
	 */
	public String getCodigoDetalle(Map criteria) {		
		return procesoSTOEjecucionValidacionesDAO.getCodigoDetalle(criteria);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getOrdenCompraCabecera(java.util.Map)
	 */
	public List getOrdenCompraCabecera(Map criteria){
		log.debug("Entering method 'getOrdenCompraCabecera()'");
		return procesoSTOEjecucionValidacionesDAO.getOrdenCompraCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getOrdenCompraDetalle(java.util.Map)
	 */
	public List getOrdenCompraDetalle(Map criteria){
		log.debug("Entering method 'getOrdenCompraDetalle()'");
		return procesoSTOEjecucionValidacionesDAO.getOrdenCompraDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getSolicitudCredito(java.util.Map)
	 */
	public List getSolicitudCredito(Map criteria){
		log.debug("Entering method 'getSolicitudCredito()'");
		return procesoSTOEjecucionValidacionesDAO.getSolicitudCredito(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateOrdenCompraCabecera(java.util.Map)
	 */
	public void updateOrdenCompraCabecera(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateOrdenCompraCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateOrdenCompraDetalle(java.util.Map)
	 */
	public void updateOrdenCompraDetalle(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateOrdenCompraDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getSolicitudPostVentaDetalle(java.util.Map)
	 */
	public List getSolicitudPostVentaDetalle(Map criteria){
		log.debug("Entering method 'getSolicitudPostVentaDetalle()'");
		return procesoSTOEjecucionValidacionesDAO.getSolicitudPostVentaDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getSolicitudPostVentaCabecera(java.util.Map)
	 */
	public List getSolicitudPostVentaCabecera(Map criteria){
		log.debug("Entering method 'getSolicitudPostVentaCabecera()'");
		return procesoSTOEjecucionValidacionesDAO.getSolicitudPostVentaCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateSolicitudPostVentaCabecera(java.util.Map)
	 */
	public void updateSolicitudPostVentaCabecera(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateSolicitudPostVentaCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateSolicitudPostVentaDetalle(java.util.Map)
	 */
	public void updateSolicitudPostVentaDetalle(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateSolicitudPostVentaDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateSolicitudCredito(java.util.Map)
	 */
	public void updateSolicitudCredito(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateSolicitudCredito(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeAlmacenamientoHistorico(java.lang.String, java.util.Map)
	 */
	public void executeAlmacenamientoHistorico(String metodo, Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeAlmacenamientoHistorico(metodo, criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTiposDocumentosHIstoricoSTO()
	 */
	public List getTiposDocumentosHistoricoSTO(Map criteria) {
		
		return procesoSTOEjecucionValidacionesDAO.getTiposDocumentosHistoricoSTO(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeInicializacionRegistrosProceso(java.util.Map)
	 */
	public void executeInicializacionRegistrosProceso(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.executeInicializacionRegistrosProceso(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeClasificacionConsultora(java.util.Map)
	 */
	public List executeClasificacionConsultora(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.executeClasificacionConsultora(criteria);
	}	

	public List getEstadisticaUltimasCampanas(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getEstadisticaUltimasCampanas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#insercionDetalles(java.util.Map)
	 */
	public void insercionDetalles(Map criteria){
		procesoSTOEjecucionValidacionesDAO.insercionDetalles(criteria); 
		}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getSecuenciaSTONextValue()
	 */
	public String getSecuenciaSTONextValue(){
		return procesoSTOEjecucionValidacionesDAO.getSecuenciaSTONextValue();
	} 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#insercionDetallesSTODocumDigit(java.util.Map)
	 */
	public void insercionDetallesSTODocumDigit(Map criteria){
		procesoSTOEjecucionValidacionesDAO.insercionDetallesSTODocumDigit(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDetallesOCC(java.util.Map)
	 */
	public List getDetallesOCC(Map criteria){
	return 	procesoSTOEjecucionValidacionesDAO.getDetallesOCC(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#deleteDetalle(java.lang.String, java.util.Map)
	 */
	public void deleteDetalle(String metodo, Map criteria){
		procesoSTOEjecucionValidacionesDAO.deleteDetalle(metodo, criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateDetalle(java.util.Map)
	 */
	public void updateDetalle(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCabeceraDesactiva(java.util.Map)
	 */
	public void updateCabeceraDesactiva(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateCabeceraDesactiva(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getIndicadorMontoMinimo(java.util.Map)
	 */
	public String getIndicadorMontoMinimo(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getIndicadorMontoMinimo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getIndicadorMontoMaximo(java.util.Map)
	 */
	public String getIndicadorMontoMaximo(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getIndicadorMontoMaximo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeMontoMinimo(java.util.Map)
	 */
	public void executeMontoMinimo(Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeMontoMinimo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeRevertirMontoMinimo(java.util.Map)
	 */
	public void executeRevertirMontoMinimo(Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeRevertirMontoMinimo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeMontoMaximo(java.util.Map)
	 */
	public void executeMontoMaximo(Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeMontoMaximo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeRevertirMontoMaximo(java.util.Map)
	 */
	public void executeRevertirMontoMaximo(Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeRevertirMontoMaximo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#verificacionDetalles(java.util.Map)
	 */
	public List verificacionDetalles(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.verificacionDetalles(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getParametroSTO(java.util.Map)
	 */
	public String getParametroSTO(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getParametroSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getListExcepciones(java.util.Map)
	 */
	public List getListExcepciones(Map criteria){
		return 	procesoSTOEjecucionValidacionesDAO.getListExcepciones(criteria);
		}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getIndicadorSTO(java.util.Map)
	 */
	public String getIndicadorSTO(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getIndicadorSTO(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getActualizacionDatos(java.util.Map)
	 */
	public List getActualizacionDatos(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getActualizacionDatos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateActualizacionDatos(java.util.Map)
	 */
	public void updateActualizacionDatos(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateActualizacionDatos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDuplaCyzone(java.util.Map)
	 */
	public List getDuplaCyzone(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getDuplaCyzone(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateDuplaCyzone(java.util.Map)
	 */
	public void updateDuplaCyzone(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateDuplaCyzone(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCuponPago(java.util.Map)
	 */
	public List getCuponPago(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCuponPago(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCuponPago(java.util.Map)
	 */
	public void updateCuponPago(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateCuponPago(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getAccionEditable(java.util.Map)
	 */
	public String getAccionEditable(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getAccionEditable(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getBloqueosConsultora(java.util.Map)
	 */
	public List getBloqueosConsultora(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getBloqueosConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDocumentosReferenciaConsultora(java.util.Map)
	 */
	public List getDocumentosReferenciaConsultora(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getDocumentosReferenciaConsultora(criteria);
	}
	
	public void insertCuponPago(Map criteria){
		procesoSTOEjecucionValidacionesDAO.insertCuponPago(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoCompania(java.util.Map)
	 */
	public String getCodigoCompania(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCodigoCompania(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoCliente(java.util.Map)
	 */
	public String getCodigoCliente(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getCodigoCliente(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCuponPagoSearchList(java.util.Map)
	 */
	public List getCuponPagoSearchList(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCuponPagoSearchList(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#deleteCuponPago(java.util.Map)
	 */
	public void deleteCuponPago(Map criteria){
		procesoSTOEjecucionValidacionesDAO.deleteCuponPago(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCuponPagoModificar(java.util.Map)
	 */
	public List getCuponPagoModificar(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCuponPagoModificar(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCuponPagoModificar(java.util.Map)
	 */
	public void updateCuponPagoModificar(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateCuponPagoModificar(criteria);
	}
	 	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeEnvioMailSCC(java.util.Map)
	 */
	public void executeEnvioMailSCC(Map criteria){
		procesoSTOEjecucionValidacionesDAO.executeEnvioMailSCC(criteria);
		List listaCorreos = new ArrayList();
		listaCorreos = procesoSTOEjecucionValidacionesDAO.getEnvioMailsSCCList(criteria);		
		Map params = new HashMap();
		params = (Map)(procesoSTOEjecucionValidacionesDAO.getEnvioMailsSCCParams(criteria).get(0));
		
		//------Parametros Generales------		
		String correoOrigen     = params.get("mailOrigen").toString();
		String plantillaOK      = params.get("plantillaOK").toString();
		String plantillaRechazo = params.get("plantillaRechazo").toString();
		//--------------------------------
		
		for (int i = 0; i < listaCorreos.size(); i++) {
			Map result = new HashMap();
			result = (Map)listaCorreos.get(i);
			
			criteria.put("nombreCliente", result.get("nombreCliente"));
			criteria.put("tipoDocumento", result.get("tipoDocumento"));
			criteria.put("codigoCliente", result.get("codigoCliente"));
			
			if(result.get("indicadorRechazo").equals(Constants.STO_INDICADOR_RECHAZADO)){
				//Rechazo
				mailUtil.enviarMail(correoOrigen,                              //correoOrigen
									result.get("emailGerentaZona").toString(), //correo Destino 
									result.get("subjectCorreo").toString(),    //Subject  
									plantillaRechazo,                               //plantilla 
						            criteria);
			}
			else{
				//Paso
				mailUtil.enviarMail(correoOrigen,                              //correoOrigen
									result.get("emailGerentaZona").toString(), //correo Destino 
									result.get("subjectCorreo").toString(),    //Subject  
									plantillaOK,                          //plantilla
						            criteria);
			}
		}
		
		//procesoSTOEjecucionValidacionesDAO.deleteEnvioMailsSCC(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getSecuenciaCuponPagoSTONextValue()
	 */
	public String  getSecuenciaCuponPagoSTONextValue(){
		return procesoSTOEjecucionValidacionesDAO.getSecuenciaCuponPagoSTONextValue();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getNombreConsultora(java.util.Map)
	 */
	public String getNombreConsultora(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getNombreConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getOpeacionCodigoVenta(java.util.Map)
	 */
	public List getOpeacionCodigoVenta(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getOpeacionCodigoVenta(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getOpeacionCodigoVentaSPVD(java.util.Map)
	 */
	public List getOpeacionCodigoVentaSPVD(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getOpeacionCodigoVentaSPVD(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCantidadDevuelveList(java.util.Map)
	 */
	public List getCantidadDevuelveList(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCantidadDevuelveList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoVentaPedidoList(java.util.Map)
	 */
	public List getCodigoVentaPedidoList(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCodigoVentaPedidoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoVentaMatrizList(java.util.Map)
	 */
	public List getCodigoVentaMatrizList(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCodigoVentaMatrizList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getPeriodoReferencia(java.util.Map)
	 */
	public String getPeriodoReferencia(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getPeriodoReferencia(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoVentaMatrizPrecioList(java.util.Map)
	 */
	public List getCodigoVentaMatrizPrecioList(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCodigoVentaMatrizPrecioList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCodigoVentaMatrizIncentivoList(java.util.Map)
	 */
	public List getCodigoVentaMatrizIncentivoList(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCodigoVentaMatrizIncentivoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getReferenciaOperacionList()
	 */
	public List getReferenciaOperacionList(){
		return procesoSTOEjecucionValidacionesDAO.getReferenciaOperacionList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCantidadDevuelveDetalleList(java.util.Map)
	 */
	public List getCantidadDevuelveDetalleList(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getCantidadDevuelveDetalleList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getListaValidacionExistenciaCronograma(java.util.Map)
	 */
	public List getListaValidacionExistenciaCronograma(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getListaValidacionExistenciaCronograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getListaValidacionVencimientoCronograma(java.util.Map)
	 */
	public List getListaValidacionVencimientoCronograma(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getListaValidacionVencimientoCronograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getListaValidacionVencimientoCronogramaSegundoCaso(java.util.Map)
	 */
	public List getListaValidacionVencimientoCronogramaSegundoCaso(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getListaValidacionVencimientoCronogramaSegundoCaso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getListaValidacionVencimientoCronogramaTercerCaso(java.util.Map)
	 */
	public List getListaValidacionVencimientoCronogramaTercerCaso(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getListaValidacionVencimientoCronogramaTercerCaso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCodigoClienteOrdenCompraDetalle(java.util.Map)
	 */
	public void updateCodigoClienteOrdenCompraDetalle(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateCodigoClienteOrdenCompraDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCodigoClienteSTODocumDigit(java.util.Map)
	 */
	public void updateCodigoClienteSTODocumDigit(Map criteria){
		procesoSTOEjecucionValidacionesDAO.updateCodigoClienteSTODocumDigit(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTipoViaCliente()
	 */
	public List getTipoViaCliente(){
		return procesoSTOEjecucionValidacionesDAO.getTipoViaCliente();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getMotivosDevolucion(java.util.Map)
	 */
	public List getMotivosDevolucion(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getMotivosDevolucion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTipoDocumento()
	 */
	public List getTipoDocumento() {
		return  procesoSTOEjecucionValidacionesDAO.getTipoDocumento();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#insertAuditoriaCambioCodCliente(java.util.Map)
	 */
	public void insertAuditoriaCambioCodCliente(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.insertAuditoriaCambioCodCliente(criteria);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getOrdenTransporte(java.util.Map)
	 */
	public List getOrdenTransporte(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateOrdenTransporte(java.util.Map)
	 */
	public void updateOrdenTransporte(Map criteria){		
		procesoSTOEjecucionValidacionesDAO.updateOrdenTransporte(criteria);
		procesoSTOEjecucionValidacionesDAO.executeModificarOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCalificacionesOrdenTransporte()
	 */
	public List getCalificacionesOrdenTransporte(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCalificacionesOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getNovedadesAccionesOrdenTransporte(java.util.Map)
	 */
	public List getNovedadesAccionesOrdenTransporte(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getNovedadesAccionesOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCentrosDeAcopio(java.util.Map)
	 */
	public List getCentrosDeAcopio(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getCentrosDeAcopio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateIndicadorEnvio()
	 */
	public void updateIndicadorEnvio(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.updateIndicadorEnvio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCentrosDeAcopioSolucionesCentroAcopioAutomatico()
	 */
	public List getCentrosDeAcopioSolucionesCentroAcopioAutomatico(){
		return procesoSTOEjecucionValidacionesDAO.getCentrosDeAcopioSolucionesCentroAcopioAutomatico();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCompaniasTransporte()
	 */
	public List getCompaniasTransporte(){
		return procesoSTOEjecucionValidacionesDAO.getCompaniasTransporte();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCentrosAcopio()
	 */
	public List getCentrosAcopio(){
		return procesoSTOEjecucionValidacionesDAO.getCentrosAcopio();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCodigoConsultora(java.util.Map)
	 */
	public void updateCodigoConsultora(Map criteria){
		// Proceso previo que homologa consultoras en base al 
		// documento de identidad o al preimpreso segun parametria
//		procesoSTOEjecucionValidacionesDAO.executeHomologarCodigoConsultora(criteria);
		
		// Proceso que realiza la actualizacion del codigo de consultora
		procesoSTOEjecucionValidacionesDAO.executeUpdateCodigoConsultora(criteria);
		
		String valMontoMinimo=getIndicadorMontoMinimo(criteria);
		String valMontoMaximo=getIndicadorMontoMaximo(criteria);
		String fmontoMaximo = criteria.get("montoMaximo").toString();
		String fmontoMinimo = criteria.get("montoMinimo").toString();
		 
		if(valMontoMinimo==null) valMontoMinimo="0";
		 
		if(valMontoMaximo==null) valMontoMaximo="0";
			 						
		if(fmontoMinimo.compareToIgnoreCase("S")==0){
			if(valMontoMinimo.compareToIgnoreCase("0")==0){
				executeMontoMinimo(criteria);
			}
		}
		else{
			if(valMontoMinimo.compareToIgnoreCase("1")==0){
				executeRevertirMontoMinimo(criteria);
			}
		}
			 			 
		if(fmontoMaximo.compareToIgnoreCase("S")==0){
			if(valMontoMaximo.compareToIgnoreCase("0")==0){
				executeMontoMaximo(criteria);
			}
		}
		else{
			if(valMontoMaximo.compareToIgnoreCase("1")==0){
				executeRevertirMontoMaximo(criteria);
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getIngresoMetas(java.util.Map)
	 */
	public List getIngresoMetas(Map criteria) {
		return this.procesoSTOEjecucionValidacionesDAO.getIngresoMetas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCartaInvitacionFlexipago(java.util.Map)
	 */
	public List getCartaInvitacionFlexipago(Map criteria) {
		return this.procesoSTOEjecucionValidacionesDAO.getCartaInvitacionFlexipago(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateIngresoMetas(java.util.Map)
	 */
	public void updateIngresoMetas(Map criteria) {
		this.procesoSTOEjecucionValidacionesDAO.updateIngresoMetas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateCartaInvitacionFlexipago(java.util.Map)
	 */
	public void updateCartaInvitacionFlexipago(Map criteria) {
		this.procesoSTOEjecucionValidacionesDAO.updateCartaInvitacionFlexipago(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getValoresOidDetalleOferta(java.util.Map)
	 */
	public DetalleOferta getValoresOidDetalleOferta(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getValoresOidDetalleOferta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDetalleOfertaList(java.util.Map)
	 */
	public List getDetalleOfertaList(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getDetalleOfertaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDetalleMovimientoProductoList(java.util.Map)
	 */
	public List getDetalleMovimientoProductoList(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getDetalleMovimientoProductoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getFamiliaSegura(java.util.Map)
	 */
	public List getFamiliaSegura(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getFamiliaSegura(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateFamiliaSegura(java.util.Map)
	 */
	public void updateFamiliaSegura(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.updateFamiliaSegura(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDatosClienteActual(java.util.Map)
	 */
	public List getDatosClienteActual(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getDatosClienteActual(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTiposDocumentosHistoricoSTOAut(java.util.Map)
	 */
	public List getTiposDocumentosHistoricoSTOAut(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getTiposDocumentosHistoricoSTOAut(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDescripcionOnline(java.util.Map)
	 */
	public String getDescripcionOnline(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getDescripcionOnline(criteria);
	}

	public List getDetallePedidoFolio(String id) {
		return procesoSTOEjecucionValidacionesDAO.getDetallePedidoFolio(id);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTiposDocumentosHistoricoSTOSinGestion(java.util.Map)
	 */
	public List getTiposDocumentosHistoricoSTOSinGestion(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getTiposDocumentosHistoricoSTOSinGestion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeHistorico(java.util.Map)
	 */
	public void executeHistoricoSinGestionar(Map documentoMap) {
		procesoSTOEjecucionValidacionesDAO.executeHistoricoSinGestionar(documentoMap);
		
	}

	public List getListaPeriodosHistoricosSinGestion(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getListaPeriodosHistoricosSinGestion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getURLOCRParametro()
	 */
	public String getURLOCRParametro(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getURLOCRParametro(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getMarcaPais(java.util.Map)
	 */
	public String getMarcaPais(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getMarcaPais(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getValorTipoDocumento(java.util.Map)
	 */
	public String getValorTipoDocumento(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getValorTipoDocumento(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getListaHistoricoSolicitudAprobados(java.lang.String)
	 */
	public Map getListaHistoricoSolicitudAprobados(Map criteria) {
		
		return procesoSTOEjecucionValidacionesDAO.getListaHistoricoSolicitudAprobados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateDocumentoDigital(java.util.Map)
	 */
	public void updateDocumentoDigital(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.updateDocumentoDigital(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeRecepcionCuponPago(java.util.Map)
	 */
	public void executeRecepcionCuponPago(Map params) throws Exception {
		String codigoPais= (String)params.get("codigoPais");
		//obteniendo lista SQL SERVER
		ConexionOCRWrapper conexion = getDevuelveConexionComercial(codigoPais);
		log.debug("conexion comercial "+conexion.getHost() + " usu: " +conexion.getUsuario());
		List listCuponComercial=genericoOCRComercialFacadeService.getListProcesoCargaCupon(conexion, params);
		log.debug("lista comercial "+listCuponComercial.size());
		Iterator it = listCuponComercial.iterator();
		boolean hayInsercion=false;
		while(it.hasNext()){
			Map map = (Map)it.next();
			String fecha = (String) map.get("fechaRegistro");//ddmmyyyy
			params.put("rutCliente", map.get("rutCliente"));			
			params.put("valorPagado", map.get("valorPagado")!=null?String.valueOf(map.get("valorPagado")):"0");
			params.put("fechaRegistro",fecha.substring(0,2)+"/"+fecha.substring(2, 4)+"/"+fecha.substring(4));
			params.put("numeroCuponPago", map.get("numeroCuponPago"));		
			
			
			
			
			
			String secuenciaCupon= getSecuenciaCuponPagoSTONextValue();
			params.put("secuenciaCupon",secuenciaCupon);
			String secuenciaSTO = getSecuenciaSTONextValue();
			params.put("secuenciaSTO",secuenciaSTO);
			
			//validamos que no existe cupond epago para el periodo y cliente
			Integer cont = procesoSTOService.getValidarCuponPeriodoCliente(params);
			
			Integer cont2 = procesoSTOService.getValidarDeudaCliente(params);
			if(cont==0 && cont2>0){//no existe
				hayInsercion=true;
				//obteniendo codCliente
				params.put("numeroDocumentoIdentidad", map.get("rutCliente"));
				String codCliente=procesoSTOEjecucionValidacionesDAO.getCodigoClienteByDocumentoIdentidad(params);
				params.put("codCliente", codCliente);
				procesoSTOEjecucionValidacionesDAO.insertCuponPago(params);
			}
		}
		//ejecutando validsaciones STO
		params.remove("rutCliente");
		params.remove("valorPagado");
		params.remove("fechaRegistro");
		params.remove("numeroDocumentoIdentidad");
		log.debug(">>>>realiza validacion sto: >>> ");
		//if(hayInsercion){	se ejecuta validacion haya o no carga		
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais, Constants.STO_TIPO_DOCUMENTO_CUP, Constants.STO_ACCI_ELIM_ONLI);
			List listaSTO = procesoSTOService.getDocumentoDigitadoPKByLote(params);
			procesoSTOExecutionService.execute(accionTipoDocumento,params,listaSTO);
		//}
	}

	/**
	 * Retorna la conexion a comercial
	 * @param codigoPais
	 * @return
	 */
	private ConexionOCRWrapper getDevuelveConexionComercial(String codigoPais) {
		ConexionOCRWrapper conexion = new ConexionOCRWrapper();
		conexion.setCodigoPais(codigoPais);
		conexion.setHost(genericoService.getParametroPais(codigoPais,Constants.SISTEMA_STO, Constants.STO_HOST_COMERCIAL));
		conexion.setPassword(genericoService.getParametroPais(codigoPais,Constants.SISTEMA_STO, Constants.STO_PWD_COMERCIAL));
		conexion.setTipoConexionExterna(genericoService.getParametroPais(codigoPais,Constants.SISTEMA_STO, Constants.STO_TIPO_CONEXION));
		conexion.setUsuario(genericoService.getParametroPais(codigoPais,Constants.SISTEMA_STO, Constants.STO_USER_COMERCIAL));		
		return conexion;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTiposDocumentosExcepcionSTO(java.util.Map)
	 */
	public List getTiposDocumentosExcepcionSTO(Map criteria) {		
		return procesoSTOEjecucionValidacionesDAO.getTiposDocumentosExcepcionSTO(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getContratoEjecutiva(java.util.Map)
	 */
	public Map getContratoEjecutiva(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getContratoEjecutiva(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#updateContratoEjecutiva(java.util.Map)
	 */
	public void updateContratoEjecutiva(Map criteria) {
		procesoSTOEjecucionValidacionesDAO.updateContratoEjecutiva(criteria);		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getDatosClienteActualNumDocIdent(java.util.Map)
	 */
	public List getDatosClienteActualNumDocIdent(Map criteria){
		return procesoSTOEjecucionValidacionesDAO.getDatosClienteActualNumDocIdent(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getCentrosDeAcopioFacturado(java.util.Map)
	 */
	public List getCentrosDeAcopioFacturado(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getCentrosDeAcopioFacturado(criteria);		

	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getValidaMostrarCombo(java.util.Map)
	 */
	public String getValidaMostrarCombo(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getValidaMostrarCombo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#getTipoDocumento()
	 */
	public List getMotivosGestion() {
		return  procesoSTOEjecucionValidacionesDAO.getMotivosGestion();		
	}

	@Override
	public String getIndicadorTerritorioBuzon(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getIndicadorTerritorioBuzon(criteria);
	}

	@Override
	public String validateSolicitudPedidoOrdenTransporte(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.validateSolicitudPedidoOrdenTransporte(criteria);
	}

	@Override
	public String validateBoletaReciboOrdenTransporte(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.validateBoletaReciboOrdenTransporte(criteria);
	}

	@Override
	public String getIndInformacionOk(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getIndInformacionOk(criteria);
	}

	@Override
	public String getIndSitCrediticia(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getIndSitCrediticia(criteria);
	}

	@Override
	public String getIndSinSalAmbas(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getIndSinSalAmbas(criteria);
	}

	@Override
	public List getMotivosGestionDocumento(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getMotivosGestionDocumento(criteria);
	}

	@Override
	public String getFechaNacimientoByCodigoCliente(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getFechaNacimientoByCodigoCliente(criteria);

	}

	public List getTipoDocumentosDigitList() {
		return procesoSTOEjecucionValidacionesDAO.getTipoDocumentosDigitList();
	}	

	public List getValidacionesSTO(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getValidacionesSTO(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getEstadoCivil()
	 */
	@Override
	public List getEstadoCivil() {
		return procesoSTOEjecucionValidacionesDAO.getEstadoCivil();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getTipoPersona()
	 */
	@Override
	public List getTipoPersona() {
		return procesoSTOEjecucionValidacionesDAO.getTipoPersona();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getTipoPersonaCodigo()
	 */
	public List getTipoPersonaCodigo() {
		return procesoSTOEjecucionValidacionesDAO.getTipoPersonaCodigo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getNacionalidad()
	 */
	@Override
	public List getNacionalidad() {
		return procesoSTOEjecucionValidacionesDAO.getNacionalidad();
	}
	
	public List getNacionalidadCodigo() {
		return procesoSTOEjecucionValidacionesDAO.getNacionalidadCodigo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getOrigenIngreso()
	 */
	@Override
	public List getOrigenIngreso() {
		return procesoSTOEjecucionValidacionesDAO.getOrigenIngreso();
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getCuadroOfertaOCC(java.util.Map)
	 */
	public List getCuadroOfertaOCC(Map criteria) {
		return procesoSTOEjecucionValidacionesDAO.getCuadroOfertaOCC(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService#getOrigenIngresoCodigo(java.util.Map)
	 */
	public List getOrigenIngresoCodigo(){
		return procesoSTOEjecucionValidacionesDAO.getOrigenIngresoCodigo();
	}

	@Override
	public List getDataCrediticiaInfoComercial(String codigo) {
		return procesoSTOEjecucionValidacionesDAO.getDataCrediticiaInfoComercial(codigo);
	}
	
	
}