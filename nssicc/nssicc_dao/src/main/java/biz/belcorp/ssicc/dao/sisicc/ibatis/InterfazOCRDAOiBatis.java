/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.sisicc.model.PaisCompania;

/**
 * 
 * <p>
 * <a href="InterfazOCRDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
@Repository("sisicc.interfazOCRDAO")
public class InterfazOCRDAOiBatis extends BaseDAOiBatis implements
	InterfazOCRDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getDevuelveConexionOCR(java.lang.String)
	 */
	public ConexionOCRWrapper getDevuelveConexionOCR(String codigoPais) throws Exception {
		ConexionOCRWrapper conexion = new ConexionOCRWrapper();
		conexion.setCodigoPais(codigoPais);
		
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		
		//Obteniendo el tipo de conexion
		params.put("codigoParametro", "OCR_TIPO_CONEXION");
		String valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getSTOParametroGeneralOCR", params);
		conexion.setTipoConexionExterna(valor);
		
		//Obteniendo Host
		params.put("codigoParametro", "OCR_HOST");
		valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getSTOParametroGeneralOCR", params);
		conexion.setHost(valor);
		
		//Obteniendo usuario de conexion 
		params.put("codigoParametro", "OCR_USUARIO");
		valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getSTOParametroGeneralOCR", params);
		conexion.setUsuario(valor);
		
		//Obteniendo password de conexion
		params.put("codigoParametro", "OCR_PASSWORD");
		valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getSTOParametroGeneralOCR", params);
		conexion.setPassword(valor);
		return conexion;
	}
	
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteOCRCUVErradosScaneo(java.util.Map)
	 */
	public void deleteOCRCUVErradosScaneo(Map params) throws Exception {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazOCRSQL.deleteOCRCUVErradosScaneo", params);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertOCRCUVErradosScaneo(java.util.List, java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOCRCUVErradosScaneo(List listaOCR, Map params, Usuario usuario) throws Exception  {
		Iterator listIterator = listaOCR.iterator();
		while (listIterator.hasNext()) {
			Map dataInsert = (Map) listIterator.next();
			String codigoConsultora = (String) dataInsert.get("codigoConsultora");
			String codigoProducto = (String) dataInsert.get("codigoProducto");
			String numPreImpreso  = (String) dataInsert.get("numPreImpreso");
			Integer indiceProducto = (Integer) dataInsert.get("indiceProducto");
			Integer cantidad = (Integer) dataInsert.get("cantidad");
			params.put("codigoConsultora",codigoConsultora);
			params.put("codigoProducto",codigoProducto);
			params.put("numPreImpreso",numPreImpreso);
			params.put("indiceProducto",indiceProducto);
			params.put("cantidad",cantidad);
			params.put("codigoUsuario",usuario.getLogin());
			try {
			getSqlMapClientTemplate().insert(
					"sisicc.InterfazOCRSQL.insertOCRCUVErradosScaneo", params);
		}
			catch (Exception e) {
				log.error("Error en insertOCRCUVErradosScaneo " + e.getMessage());	
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRWebDDCabecera(java.util.Map)
	 */
	public void executeInterfazOCRWebDDCabecera(Map queryParams) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRWebDDCabecera",queryParams);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRWebDDDetalle(java.util.Map)
	 */
	public void executeInterfazOCRWebDDDetalle(Map queryParams) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRWebDDDetalle",queryParams);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarOCSWebDDConsolidado(java.util.Map)
	 */
	public void executeConsolidadoPedidos(Map queryParams) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeConsolidadoPedidos",queryParams);
		
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarDuplaCyzone(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarDuplaCyzone(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarDuplaCyzone",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarDuplaCyzone()
	 */
	public void deleteInterfazOCRRecepcionarDuplaCyzone() {
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarDuplaCyzone",null);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazOCRProcesarConsolidadoDuplaCyzone(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoDuplaCyzone(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoDuplaCyzone",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarCuponPago()
	 */
	public void deleteInterfazOCRRecepcionarCuponPago(){
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarCuponPago",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarCuponPago(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarCuponPago(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarCuponPago",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoCuponPago(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoCuponPago(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoCuponPago",params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeConsolidadoPostVenta(java.util.Map)
	 */
	public void executeConsolidadoPostVenta(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeConsolidadoPostVenta",params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNombreArchivoCarga(java.util.Map)
	 */
	public String getNombreArchivoCarga(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNombreArchivoCarga", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNumeroDocumentoOCC(java.util.Map)
	 */
	public String getNumeroDocumentoOCC(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNumeroDocumentoOCC", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertTablaArchivosCargados(java.util.Map)
	 */
	public void insertTablaArchivosCargadosOCC(Map params){
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazOCRSQL.insertTablaArchivosCargadosOCC", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNumeroDocumentoSCC(java.util.Map)
	 */
	public String getNumeroDocumentoSCC(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNumeroDocumentoSCC", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNumeroDocumentoSPVC(java.util.Map)
	 */
	public String getNumeroDocumentoSPVC(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNumeroDocumentoSPVC", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNumeroDocumentoSAD(java.util.Map)
	 */
	public String getNumeroDocumentoSAD(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNumeroDocumentoSAD", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNumeroDocumentoDCYZ(java.util.Map)
	 */
	public String getNumeroDocumentoDCYZ(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNumeroDocumentoDCYZ", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getNumeroDocumentoCUP(java.util.Map)
	 */
	public String getNumeroDocumentoCUP(Map params){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getNumeroDocumentoCUP", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertTablaArchivosCargadosSCC(java.util.Map)
	 */
	public void insertTablaArchivosCargadosSCC(Map params){
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazOCRSQL.insertTablaArchivosCargadosSCC", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertTablaArchivosCargadosSPVC(java.util.Map)
	 */
	public void insertTablaArchivosCargadosSPVC(Map params){
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazOCRSQL.insertTablaArchivosCargadosSPVC", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertTablaArchivosCargadosSAD(java.util.Map)
	 */
	public void insertTablaArchivosCargadosSAD(Map params){
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazOCRSQL.insertTablaArchivosCargadosSAD", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertTablaArchivosCargadosDCYZ(java.util.Map)
	 */
	public void insertTablaArchivosCargadosDCYZ(Map params){
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazOCRSQL.insertTablaArchivosCargadosDCYZ", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#insertTablaArchivosCargadosCUP(java.util.Map)
	 */
	public void insertTablaArchivosCargadosCUP(Map params){
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazOCRSQL.insertTablaArchivosCargadosCUP", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarSolicitudCreditoCorporativa(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarSolicitudCreditoCorporativa(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarSolicitudCreditoCorporativa",map);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoSolicitudCreditoCorporativa(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoSolicitudCredito(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoSolicitudCredito",params);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarSolicitudCreditoWeb()
	 */
	public void deleteInterfazOCRRecepcionarSolicitudCreditoWeb() {
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarSolicitudCreditoWeb",null);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarSolicitudCreditoWeb(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarSolicitudCreditoWeb(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarSolicitudCreditoWeb",map);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarActualizacionDatosCorporativa(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarActualizacionDatosCorporativa(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarActualizacionDatosCorporativa",map);		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarActualizacionDatosWeb(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarActualizacionDatosWeb(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarActualizacionDatosWeb",map);		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarServiciosPostventasDetalAccion(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarServiciosPostventasDetalAccion(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarServiciosPostventasDetalAccion",params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarActualizacionDatos()
	 */
	public void deleteInterfazOCRRecepcionarActualizacionDatos(){
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarActualizacionDatos",null);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoActualizacionDatos(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoActualizacionDatos(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoActualizacionDatos",params);	
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarSolicitudCreditoCorporativa()
	 */
	public void deleteInterfazOCRRecepcionarSolicitudCreditoCorporativa() {
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarSolicitudCreditoCorporativa",null);		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarClientesWeb(java.util.Map)
	 */
	public void executeInterfazOCREnviarClientesWeb(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarClientesWeb",params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarTerritoriosWeb(java.util.Map)
	 */
	public void executeInterfazOCREnviarTerritoriosWeb(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarTerritoriosWeb",params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarSegmentosWeb(java.util.Map)
	 */
	public void executeInterfazOCREnviarSegmentosWeb(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarSegmentosWeb",params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getCodigoCompaniaBasCompa(java.util.Map)
	 */
	public PaisCompania getBasPaisCompa(Map params){
		return (PaisCompania)getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getBasPaisCompa", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarCodigosVentaRechazados()
	 */
	public void deleteInterfazOCRRecepcionarCodigosVentaRechazados(){
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarCodigosVentaRechazados",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarCodigosVentaRechazados(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarCodigosVentaRechazados(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarCodigosVentaRechazados",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarOrdenTransporte(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarOrdenTransporte(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarOrdenTransporte",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoOrdenTransporte(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoOrdenTransporte(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoOrdenTransporte",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarOrdenTransporteFacturacion(java.util.Map)
	 */
	public void executeInterfazOCREnviarOrdenTransporteFacturacion(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarOrdenTransporteFacturacion",params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarOrdenTransporteAnulada(java.util.Map)
	 */
	public void executeInterfazOCREnviarOrdenTransporteAnulada(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarOrdenTransporteAnulada",params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRetornoCodigosAsignados(java.util.Map)
	 */
	public void executeInterfazOCRRetornoCodigosAsignados(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRetornoCodigosAsignado",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarIngresoMetas()
	 */
	public void deleteInterfazOCRRecepcionarIngresoMetas(){
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarIngresoMetas",null);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarCartaInvitacionFlexipago()
	 */
	public void deleteInterfazOCRRecepcionarCartaInvitacionFlexipago() {
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarCartaInvitacionFlexipago", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarIngresoMetas(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarIngresoMetas(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarIngresoMetas",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarCartaInvitacionFlexipago(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarCartaInvitacionFlexipago(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarCartaInvitacionFlexipago", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoIngresoMetas(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoIngresoMetas(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoIngresoMetas",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoCartaInvitacionFlexipago(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoCartaInvitacionFlexipago(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoCartaInvitacionFlexipago",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#deleteInterfazOCRRecepcionarFamiliaSegura()
	 */
	public void deleteInterfazOCRRecepcionarFamiliaSegura(){
		getSqlMapClientTemplate().delete("sisicc.InterfazOCRSQL.deleteInterfazOCRRecepcionarFamiliaSegura",null);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarFamiliaSegura(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarFamiliaSegura(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarFamiliaSegura",params);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoFamiliaSegura(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoFamiliaSegura(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoFamiliaSegura",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeConsolidadoMicaWeb(java.util.Map)
	 */
	public void executeConsolidadoMicaWeb(Map queryParams) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeConsolidadoMicaWeb",queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getListWsSocCredito(java.util.Map)
	 */
	public List getListWsSocCredito(Map map) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazOCRSQL.getListWsSocCredito",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#updateInformacionInfoCom(java.util.Map)
	 */
	public void updateInformacionInfoCom(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.updateInformacionInfoCom",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRWebDDPROLCabecera(java.util.Map)
	 */
	public void executeInterfazOCRWebDDPROLCabecera(Map queryParams) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRWebDDPROLCabecera",queryParams);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazWEBRecepcionarFamiliaSegura(java.util.Map)
	 */
	public void executeInterfazWEBRecepcionarFamiliaSegura(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazWEBRecepcionarFamiliaSegura",params);		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazWEBProcesarConsolidadoFamiliaSegura(java.util.Map)
	 */
	public void executeInterfazWEBProcesarConsolidadoFamiliaSegura(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazWEBProcesarConsolidadoFamiliaSegura",params);		
	}

	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRMaestroDeProductosProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarMaestroDeProductosProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarMaestroDeProductosProl",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRDescripcionDeProductosProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarDescripcionDeProductosProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarDescripcionDeProductosProl",params);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRDetalleDeOfertasProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarDetalleDeOfertasProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarDetalleDeOfertasProl",params);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCROfertasProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarOfertasProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarOfertasProl",params);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarPrecioCatalogo(java.util.Map)
	 */
	public void executeInterfazOCREnviarPrecioCatalogo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarPrecioCatalogo",params);
	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRMatrizDeFacturacionProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarMatrizDeFacturacionProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarMatrizDeFacturacionProl",params);
		
	}
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRangoDePromocionesProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarRangoDePromocionesProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarRangoDePromocionesProl",params);	
		
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRGruposDeOfertaProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarGruposDeOfertaProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarGruposDeOfertaProl",params);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRMatrizDeRemplazosProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarMatrizDeRemplazosProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarMatrizDeRemplazosProl",params);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRPromocionesProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarPromocionesProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarPromocionesProl",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRCronogramaDeFacturacionProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarCronogramaDeFacturacionProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarCronogramaDeFacturacionProl",params);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRCabeceraDeMatrizDeFacturacionProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarCabeceraDeMatrizDeFacturacionProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarCabeceraDeMatrizDeFacturacionProl",params);	
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRGestionDeStockProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarGestionDeStockProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarGestionDeStockProl",params);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRLimiteDeVentaProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarLimiteDeVentaProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarLimiteDeVentaProl",params);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarInformacionNuevosFaltantes(java.util.Map)
	 */
	public void executeInterfazOCREnviarInformacionNuevosFaltantes(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarInformacionNuevosFaltantes",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRContratoEjecutiva(java.util.Map)
	 */
	public void executeInterfazOCRContratoEjecutiva(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRContratoEjecutiva",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRProcesarConsolidadoContratoEjecutiva(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoContratoEjecutiva(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRProcesarConsolidadoContratoEjecutiva",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarCuponProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarCuponProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarCuponProl", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarCuponHomologacionProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarCuponHomologacionProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarCuponHomologacionProl", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCREnviarCuponParticipantesProl(java.util.Map)
	 */
	public void executeInterfazOCREnviarCuponParticipantesProl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCREnviarCuponParticipantesProl", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarBoletaEntrega(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarBoletaEntrega(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarBoletaEntrega", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeInterfazOCRRecepcionarBoletaRecojo(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarBoletaRecojo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeInterfazOCRRecepcionarBoletaRecojo", params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#executeEnviarMatrizFacturacionFutura(java.util.Map)
	 */
	public void executeEnviarMatrizFacturacionFutura(Map queryParams) {
		getSqlMapClientTemplate().update("sisicc.InterfazOCRSQL.executeEnviarMatrizFacturacionFutura",queryParams);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazOCRDAO#getSTOParametroGeneralOCR(java.util.Map)
	 */
	public String getSTOParametroGeneralOCR(Map params)  {
		String valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazOCRSQL.getSTOParametroGeneralOCR", params);
		return valor;
	}
		
}