package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;

/**
 * Implementacion del DAO que ejecutara las Validadciones
 * <p>
 * <a href="ProcesoSTOEjecucionValidacionesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:croman@belcorp.biz">Cristian Roman</a>
 */
@Repository("spusicc.procesoSTOEjecucionValidacionesDAO")
public class ProcesoSTOEjecucionValidacionesDAOiBatis extends BaseDAOiBatis implements ProcesoSTOEjecucionValidacionesDAO {

/**/
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeActualizarCartera(java.util.Map)
	 */
	public List getTiposDocumentosSTO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposDocumentosSTO", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getComponentesDocumentos(java.util.Map)
	 */
	public List getComponentesDocumentos(Map criteria){	
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getComponentesDocumentosSTO", criteria);
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeValidacion(java.lang.String, java.util.Map)
	 */
	public void executeValidacion(String method, Map criteria) {
		log.debug("spusicc.ProcesosSTOSQL.".concat(method));
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.".concat(method), criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTipoDocumentoDigitado(java.lang.String)
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado(String codigo) {
		return (TipoDocumentoDigitado)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getTipoDocumentoDigitado", codigo);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeValidacionConsolidado(java.lang.String, java.util.Map)
	 */
	public void executeValidacionConsolidado(String method, Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.".concat(method), criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeInicializacionRegistrosProceso(java.util.Map)
	 */
	public void executeInicializacionRegistrosProceso(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeInicializacionRegistrosProceso", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateRegistrosControlValidacion(java.util.Map)
	 */
	public void updateRegistrosControlValidacion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateRegistrosControlValidacion", criteria);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTiposDocumentosIdentidadSTO()
	 */
	public List getTiposDocumentosIdentidadSTO() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposDocumentosIdentidadSTO", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getConsultaClientesList(java.util.Map)
	 */
	public List getConsultaClientesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaClientesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeGenerarCodigoClienteSTO(java.util.Map)
	 */
	public String executeGenerarCodigoClienteSTO(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.executeGenerarCodigoClienteSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateNumeroSolicitud(java.util.Map)
	 */
	public void updateNumeroSolicitud(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateNumeroSolicitud", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#STOGrabarPedRec(java.util.Map)
	 */
	public void STOGrabarPedRec(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.STOGrabarPedRec", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getNumeroProcesoActual(java.util.Map)
	 */
	public String getNumeroProcesoActual(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getNumeroProcesoActual", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#insertInicioProcesoSTO(java.util.Map)
	 */
	public void insertInicioProcesoSTO(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertInicioProcesoSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateFinProcesoSTO(java.util.Map)
	 */
	public void updateFinProcesoSTO(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.updateFinProcesoSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateNumeroProceso(java.util.Map)
	 */
	public void updateNumeroProceso(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateNumeroProceso", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateNumeroProcesoDetalles(java.util.Map)
	 */
	public void updateNumeroProcesoDetalles(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateNumeroProcesoDetalles", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoDetalle(java.util.Map)
	 */
	public String getCodigoDetalle(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getOrdenCompraCabecera(java.util.Map)
	 */
	public List getOrdenCompraCabecera(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOrdenCompraCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getOrdenCompraDetalle(java.util.Map)
	 */
	public List getOrdenCompraDetalle(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOrdenCompraDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getSolicitudCredito(java.util.Map)
	 */
	public List getSolicitudCredito(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getSolicitudCredito", criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateOrdenCompraCabecera(java.util.Map)
     */
    public void updateOrdenCompraCabecera(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateOrdenCompraCabecera", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateOrdenCompraDetalle(java.util.Map)
     */
    public void updateOrdenCompraDetalle(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateOrdenCompraDetalle", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getSolicitudPostVentaCabecera(java.util.Map)
     */
    public List getSolicitudPostVentaCabecera(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getSolicitudPostVentaCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getSolicitudPostVentaDetalle(java.util.Map)
	 */
	public List getSolicitudPostVentaDetalle(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getSolicitudPostVentaDetalle", criteria);
	}
    
	 /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateSolicitudPostVentaCabecera(java.util.Map)
	 */
	public void updateSolicitudPostVentaCabecera(Map criteria){
		    
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSolicitudPostVentaCabecera", criteria);
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSolicitudPostVentaDetalleCliente", criteria);
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOCodigoCliente", criteria);  
		}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateSolicitudPostVentaDetalle(java.util.Map)
	 */
	public void updateSolicitudPostVentaDetalle(Map criteria){
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSolicitudPostVentaDetalle", criteria);
		}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateSolicitudCredito(java.util.Map)
	 */
	public void updateSolicitudCredito(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSolicitudCredito", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOSolicitudCredito", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeAlmacenamientoHistorico(java.lang.String, java.util.Map)
	 */
	public void executeAlmacenamientoHistorico(String metodo, Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.".concat(metodo), criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTiposDocumentosHistoricoSTO()
	 */
	public List getTiposDocumentosHistoricoSTO(Map criteria) {

		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposDocumentosHistoricoSTO", criteria);
	}
	
		/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#insercionDetalles(java.util.Map)
	 */
	public void insercionDetalles(Map criteria){
		logger.info("Entro a ProcesoSTOEjecucionValidacionesDAOiBatis - insercionDetalles(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insercionDetalles", criteria);
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insercionDetallesSTODocumDigit", criteria);
		logger.info("Salio a ProcesoSTOEjecucionValidacionesDAOiBatis - insercionDetalles(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getSecuenciaSTONextValue()
	 */
	public String getSecuenciaSTONextValue(){
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getSecuenciaSTONextValue", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#insercionDetallesSTODocumDigit(java.util.Map)
	 */
	public void insercionDetallesSTODocumDigit(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insercionDetallesSTODocumDigit", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDetallesOCC(java.util.Map)
	 */
	public List getDetallesOCC(Map criteria){
		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDetallesOCC", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#deleteDetalle(java.lang.String, java.util.Map)
	 */
	public void deleteDetalle(String method, Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL." + method,
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateDetalle(java.util.Map)
	 */
	public void updateDetalle(Map criteria){
		logger.info("Entro a ProcesoSTOEjecucionValidacionesDAOiBatis - updateDetalle(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateDetalle", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCabeceraDesactiva", criteria);
		logger.info("Salio a ProcesoSTOEjecucionValidacionesDAOiBatis - updateDetalle(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateCabeceraDesactiva(java.util.Map)
	 */
	public void updateCabeceraDesactiva(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCabeceraDesactiva", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndicadorMontoMinimo(java.util.Map)
	 */
	public String getIndicadorMontoMinimo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorMontoMinimo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndicadorMontoMaximo(java.util.Map)
	 */
	public String getIndicadorMontoMaximo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorMontoMaximo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeMontoMinimo(java.util.Map)
	 */
	public void executeMontoMinimo(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeMontoMinimo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeRevertirMontoMinimo(java.util.Map)
	 */
	public void executeRevertirMontoMinimo(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeRevertirMontoMinimo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeMontoMaximo(java.util.Map)
	 */
	public void executeMontoMaximo(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeMontoMaximo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeRevertirMontoMaximo(java.util.Map)
	 */
	public void executeRevertirMontoMaximo(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeRevertirMontoMaximo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#verificacionDetalles(java.util.Map)
	 */
	public List verificacionDetalles(Map criteria) {

		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.verificacionDetalles", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getParametroSTO(java.util.Map)
	 */
	public String getParametroSTO(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getParametroSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListExcepciones(java.util.Map)
	 */
	public List getListExcepciones(Map criteria){
		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListExcepciones", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeClasificacionConsultora(java.util.Map)
	 */
	public List executeClasificacionConsultora(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeClasificacionConsultora", criteria);
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getClasificacionConsultora", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getEstadisticaUltimasCampanas(java.util.Map)
	 */
	public List getEstadisticaUltimasCampanas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getEstadisticaUltimasCampanas",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndicadorSTO(java.util.Map)
	 */
	public String getIndicadorSTO(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getActualizacionDatos(java.util.Map)
	 */
	public List getActualizacionDatos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getActualizacionDatos",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateActualizacionDatos(java.util.Map)
	 */
	public void updateActualizacionDatos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateActualizacionDatos", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOCodigoCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDuplaCyzone(java.util.Map)
	 */
	public List getDuplaCyzone(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDuplaCyzone",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateDuplaCyzone(java.util.Map)
	 */
	public void updateDuplaCyzone(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateDuplaCyzone", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOCodigoCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCuponPago(java.util.Map)
	 */
	public List getCuponPago(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCuponPago",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateCuponPago(java.util.Map)
	 */
	public void updateCuponPago(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCuponPago", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOCuponPago", criteria); 
	}
   
   /* (non-Javadoc)
 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getAccionEditable(java.util.Map)
 */
public String getAccionEditable(Map criteria){
	   return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getAccionEditable", criteria);
   }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeEnvioMailSCC(java.util.Map)
	 */
	public void executeEnvioMailSCC(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeEnvioMailSCC", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getEnvioMailsSCCList(java.util.Map)
	 */
	public List getEnvioMailsSCCList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getEnvioMailsSCCList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getEnvioMailsSCCParams(java.util.Map)
	 */
	public List getEnvioMailsSCCParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getEnvioMailsSCCParams",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#deleteEnvioMailsSCC(java.util.Map)
	 */
	public void deleteEnvioMailsSCC(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deleteEnvioMailsSCC", criteria);
	}
	
	  
   /* (non-Javadoc)
 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getBloqueosConsultora(java.util.Map)
 */
public List getBloqueosConsultora(Map criteria){
	   return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getBloqueosConsultora",criteria);
   }

  /* (non-Javadoc)
 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDocumentosReferenciaConsultora(java.util.Map)
 */
public List getDocumentosReferenciaConsultora(Map criteria){
	  return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDocumentosReferenciaConsultora",criteria);
  }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#insertCuponPago(java.util.Map)
	 */
	public void insertCuponPago(Map criteria){
	
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertCuponPago", criteria);
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertCuponPagoSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoCompania(java.util.Map)
	 */
	public String getCodigoCompania(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoCompania", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoCliente(java.util.Map)
	 */
	public String getCodigoCliente(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCuponPagoSearchList(java.util.Map)
	 */
	public List getCuponPagoSearchList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCuponPagoSearchList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#deleteCuponPago(java.util.Map)
	 */
	public void deleteCuponPago(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deleteCuponPago",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCuponPagoModificar(java.util.Map)
	 */
	public List getCuponPagoModificar(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCuponPagoModificar",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateCuponPagoModificar(java.util.Map)
	 */
	public void updateCuponPagoModificar(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCuponPagoModificar", criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getSecuenciaCuponPagoSTONextValue()
	 */
	public String getSecuenciaCuponPagoSTONextValue(){
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getSecuenciaCuponPagoSTONextValue", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getNombreConsultora(java.util.Map)
	 */
	public String getNombreConsultora(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getNombreConsultora", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getOpeacionCodigoVenta(java.util.Map)
	 */
	public List getOpeacionCodigoVenta(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOpeacionCodigoVenta",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getOpeacionCodigoVentaSPVD(java.util.Map)
	 */
	public List getOpeacionCodigoVentaSPVD(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOpeacionCodigoVentaSPVD",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCantidadDevuelveList(java.util.Map)
	 */
	public List getCantidadDevuelveList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCantidadDevuelveList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoVentaPedidoList(java.util.Map)
	 */
	public List getCodigoVentaPedidoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCodigoVentaPedidoList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoVentaMatrizList(java.util.Map)
	 */
	public List getCodigoVentaMatrizList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCodigoVentaMatrizList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getPeriodoReferencia(java.util.Map)
	 */
	public String getPeriodoReferencia(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getPeriodoReferencia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoVentaMatrizPrecioList(java.util.Map)
	 */
	public List getCodigoVentaMatrizPrecioList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCodigoVentaMatrizPrecioList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoVentaMatrizIncentivoList(java.util.Map)
	 */
	public List getCodigoVentaMatrizIncentivoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCodigoVentaMatrizIncentivoList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getReferenciaOperacionList()
	 */
	public List getReferenciaOperacionList(){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getReferenciaOperacionList",null);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCantidadDevuelveDetalleList(java.util.Map)
	 */
	public List getCantidadDevuelveDetalleList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCantidadDevuelveDetalleList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListaValidacionExistenciaCronograma(java.util.Map)
	 */
	public List getListaValidacionExistenciaCronograma(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaValidacionExistenciaCronograma",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListaValidacionVencimientoCronograma(java.util.Map)
	 */
	public List getListaValidacionVencimientoCronograma(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaValidacionVencimientoCronograma",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListaValidacionVencimientoCronogramaSegundoCaso(java.util.Map)
	 */
	public List getListaValidacionVencimientoCronogramaSegundoCaso(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaValidacionVencimientoCronogramaSegundoCaso",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListaValidacionVencimientoCronogramaTercerCaso(java.util.Map)
	 */
	public List getListaValidacionVencimientoCronogramaTercerCaso(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaValidacionVencimientoCronogramaTercerCaso",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateCodigoClienteOrdenCompraDetalle(java.util.Map)
	 */
	public void updateCodigoClienteOrdenCompraDetalle(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCodigoClienteOrdenCompraDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateCodigoClienteSTODocumDigit(java.util.Map)
	 */
	public void updateCodigoClienteSTODocumDigit(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCodigoClienteSTODocumDigit", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTipoViaCliente()
	 */
	public List getTipoViaCliente(){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTipoViaCliente");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getMotivosDevolucion(java.util.Map)
	 */
	public List getMotivosDevolucion(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getMotivosDevolucion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTipoDocumento()
	 */
	public List getTipoDocumento() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTipoDocumento","");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#insertAuditoriaCambioCodCliente(java.util.Map)
	 */
	public void insertAuditoriaCambioCodCliente(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertAuditoriaCambioCodCliente", criteria);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getOrdenTransporte(java.util.Map)
	 */
	public List getOrdenTransporte(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOrdenTransporte",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateOrdenTransporte(java.util.Map)
	 */
	public void updateOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateOrdenTransporte", criteria);
		//getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOCodigoCliente", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOZonaArribo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCalificacionesOrdenTransporte()
	 */
	public List getCalificacionesOrdenTransporte(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCalificacionOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getNovedadesAccionesOrdenTransporte(java.util.Map)
	 */
	public List getNovedadesAccionesOrdenTransporte(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getNovedadesAccionesOrdenTransporte",criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeModificarOrdenTransporte(java.util.Map)
	 */
	public void executeModificarOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeModificarOrdenTransporte", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCentrosDeAcopio(java.util.Map)
	 */
	public List getCentrosDeAcopio(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCentrosDeAcopio",criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateIndicadorEnvio()
	 */
	public void updateIndicadorEnvio(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateIndicadorEnvio", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCentrosDeAcopioSolucionesCentroAcopioAutomatico()
	 */
	public List getCentrosDeAcopioSolucionesCentroAcopioAutomatico(){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCentrosDeAcopioSolucionesCentroAcopioAutomatico",null);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCompaniasTransporte()
	 */
	public List getCompaniasTransporte(){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCompaniasTransporte");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCentrosAcopio()
	 */
	public List getCentrosAcopio(){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCentrosAcopio");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeUpdateCodigoConsultora(java.util.Map)
	 */
	public void executeUpdateCodigoConsultora(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeUpdateCodigoConsultora", criteria);
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getObtenerValParametroSTO(java.util.Map)
     */
    public String getObtenerValParametroSTO(Map criteria) {
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getObtenerValParametroSTO", criteria);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeHomologarCodigoConsultora(java.util.Map)
     */
    public void executeHomologarCodigoConsultora(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeHomologarCodigoConsultora", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCodigoClienteByDocumentoIdentidad(java.util.Map)
     */
    public String getCodigoClienteByDocumentoIdentidad(Map criteria) {
    	List l = new ArrayList();
    	l = getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCodigoClienteByDocumentoIdentidad", criteria);
    	if(l.size()!= 0)
    		return l.get(0).toString();
    	else
    		return "";
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIngresoMetas(java.util.Map)
	 */
	public List getIngresoMetas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getIngresoMetas",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCartaInvitacionFlexipago(java.util.Map)
	 */
	public List getCartaInvitacionFlexipago(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCartaInvitacionFlexipago", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateIngresoMetas(java.util.Map)
	 */
	public void updateIngresoMetas(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateIngresoMetas", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOIngresoMetas", criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateCartaInvitacionFlexipago(java.util.Map)
	 */
	public void updateCartaInvitacionFlexipago(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateCartaInvitacionFlexipago", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOCartaInvitacionFlexipago", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getValoresOidDetalleOferta(java.util.Map)
	 */
	public DetalleOferta getValoresOidDetalleOferta(Map criteria){
		return (DetalleOferta)this.getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValoresOidDetalleOferta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDetalleOfertaList(java.util.Map)
	 */
	public List getDetalleOfertaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDetalleOfertaList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDetalleMovimientoProductoList(java.util.Map)
	 */
	public List getDetalleMovimientoProductoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaDetalleMovimientoList",criteria);
	}
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getFamiliaSegura(java.util.Map)
	 */
	public List getFamiliaSegura(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getFamiliaSegura",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateFamiliaSegura(java.util.Map)
	 */
	public void updateFamiliaSegura(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateFamiliaSegura", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOFamiliaSegura", criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDatosClienteActual(java.util.Map)
	 */
	public List getDatosClienteActual(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDatosClienteActual",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTiposDocumentosHistoricoSTOAut(java.util.Map)
	 */
	public List getTiposDocumentosHistoricoSTOAut(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposDocumentosHistoricoSTOAut", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDescripcionOnline(java.util.Map)
	 */
	public String getDescripcionOnline(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getDescripcionOnline", criteria);
	}

	public List getDetallePedidoFolio(String id) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDetallePedidoFolio", id);
	}

	public List getTiposDocumentosHistoricoSTOSinGestion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposDocumentosHistoricoSTOSinGestion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeHistorico(java.util.Map)
	 */
	public void executeHistoricoSinGestionar(Map criteria) {

		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeHistoricoSinGestionar", criteria);
//		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeDeshabilitarIndicesConstraints", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeHistoricoPostVenta", criteria);
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeHistoricoSTO", criteria);
//		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeHabilitarIndicesConstraints", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListaPeriodosHistoricosSinGestion(java.util.Map)
	 */
	public List getListaPeriodosHistoricosSinGestion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaPeriodosHistoricosSinGestion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getURLOCRParametro()
	 */
	public String getURLOCRParametro(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getURLOCRParametro",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getMarcaPais(java.util.Map)
	 */
	public String getMarcaPais(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getMarcaPais",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getValorTipoDocumento(java.util.Map)
	 */
	public String getValorTipoDocumento(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValorTipoDocumento",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getListaHistoricoSolicitudAprobados(java.util.Map)
	 */
	public Map getListaHistoricoSolicitudAprobados(Map criteria) {
		Map mapListGrupos = new HashMap();
		
		
		//Para Grupo Devoluciones
		criteria.put("tipoGrupoNc", Constants.TIPO_GRUPO_DEVOLUCIONES_NC);
		criteria.put("tipoGrupo", Constants.TIPO_GRUPO_DEVOLUCIONES);
		List lstDevoluciones = getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaHistoricoSolicitudAprobados", criteria);
		mapListGrupos.put("lstDevoluciones", lstDevoluciones);		
		
		//Para Grupo Cambios
		criteria.put("tipoGrupoNc", Constants.TIPO_GRUPO_CAMBIOS_NC);
		criteria.put("tipoGrupo", Constants.TIPO_GRUPO_CAMBIOS);
		List lstCambios = getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaHistoricoSolicitudAprobados", criteria);
		mapListGrupos.put("lstCambios", lstCambios);	
				
		//Para Grupo FFNE
		criteria.put("tipoGrupoNc", Constants.TIPO_GRUPO_FFNE_NC);
		criteria.put("tipoGrupo", Constants.TIPO_GRUPO_FFNE);
		List lstFFNE = getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaHistoricoSolicitudAprobados", criteria);
		mapListGrupos.put("lstFFNE", lstFFNE);	
				
		//Para Grupo Otros
		criteria.put("tipoGrupoNc", Constants.TIPO_GRUPO_OTROS_NC);
		criteria.put("tipoGrupo", Constants.TIPO_GRUPO_OTROS);
		List lstOtros = getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getListaHistoricoSolicitudAprobados", criteria);
		mapListGrupos.put("lstOtros", lstOtros);	
		
		
		return mapListGrupos;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateDocumentoDigital(java.util.Map)
	 */
	public void updateDocumentoDigital(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateDocumentoDigital", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getTiposDocumentosExcepcionSTO(java.util.Map)
	 */
	public List getTiposDocumentosExcepcionSTO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposDocumentosExcepcionSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getContratoEjecutiva(java.util.Map)
	 */
	public Map getContratoEjecutiva(Map criteria) {
		Map retorno = (Map)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getContratoEjecutiva",criteria);
		//String codCliente = (String)retorno.get("codCliente");
		if(retorno == null) return new HashMap();
		Map direccion = (Map)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getDireccionEjecutiva",retorno);
		try {					
		retorno.put("valBarrCliente", (String)direccion.get("valBarrCliente"));
		retorno.put("valCiudCliente", (String)direccion.get("valCiudCliente"));
		retorno.put("valDepaCliente", (String)direccion.get("valDepaCliente"));
		} catch (Exception e) {
			log.debug("no se encontro barrio - ciudad - departamento");
		}
		return retorno;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#updateContratoEjecutiva(java.util.Map)
	 */
	public void updateContratoEjecutiva(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateContratoEjecutiva", criteria);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getDatosClienteActualNumDocIdent(java.util.Map)
	 */
	public List getDatosClienteActualNumDocIdent(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDatosClienteActualNumDocIdent",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getCentrosDeAcopioFacturado(java.util.Map)
	 */
	public List getCentrosDeAcopioFacturado(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCentrosDeAcopioFacturado",null);		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getValidaMostrarCombo(java.util.Map)
	 */
	public String getValidaMostrarCombo(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValidaMostrarCombo",criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getMotivoGestion(java.util.Map)
	 */
	public List getMotivosGestion() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getMotivosGestion","");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndicadorTerritorioBuzon(java.util.Map)
	 */
	public String getIndicadorTerritorioBuzon(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorTerritorioBuzon",criteria);		
	}
	
	public String getCodigoNumeroTipoDocumentoConsultoraPorCodigo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoNumeroTipoDocumentoConsultoraPorCodigo", criteria);
	}
	
	public String getCodigoNumeroTipoDocumentoConsultoraPorDocumento(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoNumeroTipoDocumentoConsultoraPorDocumento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#validateSolicitudPedidoOrdenTransporte(java.util.Map)
	 */
	public String validateSolicitudPedidoOrdenTransporte(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.validateSolicitudPedidoOrdenTransporte", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#validateBoletaReciboOrdenTransporte(java.util.Map)
	 */
	public String validateBoletaReciboOrdenTransporte(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.validateBoletaReciboOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndInformacionOk(java.util.Map)
	 */
	public String getIndInformacionOk(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndInformacionOk", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndSitCrediticia(java.util.Map)
	 */
	public String getIndSitCrediticia(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndSitCrediticia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getIndSinSalAmbas(java.util.Map)
	 */
	public String getIndSinSalAmbas(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndSinSalAmbas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getMotivosGestionDocumento(java.util.Map)
	 */
	public List getMotivosGestionDocumento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getMotivosGestionDocumento",criteria);
	}

	public String getEstructGeopoByUA(String codigoPais, String unidadAdmin) {

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("unidadAdmin", unidadAdmin);
		
		return String.valueOf(getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getEstructGeopoByUA", criteria));
	}
	
	
	public String getExisteOidDetaOferta(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidDetaOferByCodigoCUVPeriodo", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getFechaNacimientoByCodigoCliente(java.util.Map)
	 */
	public String getFechaNacimientoByCodigoCliente(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getFechaNacimientoByCodigoCliente", criteria);
	}

	public List getTipoDocumentosDigitList() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTipoDocumentosDigitList","");
	}
	
	public List getMotivosRechazoSTOList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getMotivosRechazoSTOList",criteria);
	}
	
	public String getCodigoValidacion(String codigoTipoDocumentoDigi) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoValidacion", codigoTipoDocumentoDigi);
	}

	public List getValidacionesSTOList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getValidacionesSTOList",criteria);
	}

	public String getCodigoMensaje(String codigoValidacion) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCodigoMensaje", codigoValidacion);
	}

	public List getValidacionesSTO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getValidacionesSTO",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getEstadoCivil()
	 */
	@Override
	public List getEstadoCivil() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getEstadoCivil","");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getTipoPersona()
	 */
	@Override
	public List getTipoPersona() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTipoPersona","");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getTipoPersonaCodigo()
	 */
	public List getTipoPersonaCodigo() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTipoPersonaCodigo","");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getNacionalidad()
	 */
	@Override
	public List getNacionalidad() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getNacionalidad","");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getNacionalidadCodigo()
	 */
	public List getNacionalidadCodigo() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getNacionalidadCodigo","");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getOrigenIngreso()
	 */
	@Override
	public List getOrigenIngreso() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOrigenIngreso","");
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getCuadroOfertaOCC(java.util.Map)
	 */
	public List getCuadroOfertaOCC(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCuadroOfertaOCC", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getOrigenIngresoCodigo(java.util.Map)
	 */
	public List getOrigenIngresoCodigo() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getOrigenIngresoCodigo", "");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO#getDataCrediticiaInfoComercial(java.lang.String)
	 */
	@Override
	public List getDataCrediticiaInfoComercial(String codigo) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getDataCrediticiaInfoComercial", codigo);
	}
	
	
}