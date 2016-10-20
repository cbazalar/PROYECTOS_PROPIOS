/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCOMRecepcionarActualizaCodigoProveedor;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;

/**
 * 
 * <p>
 * <a href="InterfazSiCCDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

@Repository("sisicc.interfazSiCCDAO")
public class InterfazSiCCDAOiBatis extends BaseDAOiBatis implements InterfazSiCCDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * deleteInterfazLLIRecepcionarActualizaEstimadosYobel()
	 */
	public void deleteInterfazLLIRecepcionarActualizaEstimadosYobel() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazLLIRecepcionarActualizaEstimadosYobel",
						null);
	}

	public void deleteInterfazMovimientoCliente() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazPRIRecepcionarMovimientoCliente",
						null);
	}

	public void deleteInterfazOCRConsolidadoOCSCabecera() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterfazOCRConsolidadoOCSCabecera",
				null);
	}

	public void deleteInterfazOCRRecepcionarActualizacionDatos() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazOCRRecepcionarActualizacionDatos",
						null);

	}

	public void deleteInterfazOCRRecepcionarDWHOCR() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterfazOCRRecepcionarDWHOCR", null);
	}

	public void deleteInterfazOCRRecepcionarFichaInscripcionPrivilege() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazOCRRecepcionarFichaInscripcionPrivilege",
						null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * deleteInterfazOCRRecepcionarIngresoMetas()
	 */
	public void deleteInterfazOCRRecepcionarIngresoMetas() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterfazOCRRecepcionarIngresoMetas",
				null);
	}

	public void deleteInterfazOCRRecepcionarPagare() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterfazOCRRecepcionarPagare", null);

	}

	public void deleteInterfazOCRRecepcionarSolicitudCredito() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazOCRRecepcionarSolicitudCredito",
						null);

	}

	public void deleteInterfazOCRRecepcionarSolicitudPremiosPrivilegeCabecera() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazOCRRecepcionarSolicitudPremiosPrivilegeCabecera",
						null);

	}

	public void deleteInterfazOCRRecepcionarTablaControlMultiformato() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazOCRRecepcionarTablaControlMultiformato",
						null);

	}

	public void deleteInterfazPRIRecepcionarCalificacion() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterfazPRIRecepcionarCalificacion",
				null);
	}

	public void deleteInterfazPRIRecepcionarClientes() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazPRIRecepcionarClientes",
						null);
	}

	public void deleteInterfazRECEnviarTransferenciaBoletasRecojo() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazRECEnviarTransferenciaBoletasRecojo",
						null);

	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getAccesosTodosByCanalByCodigoISO
	 * (java.lang.String)
	 */

	public void deleteInterfazRECRecepcionarBoletasRecojoCabecera() {

		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazRECRecepcionarBoletasRecojoCabecera",
						null);

	}

	public void deleteInterfazRECRecepcionarBoletasRecojoDetalle() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazRECRecepcionarBoletasRecojoDetalle",
						null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * deleteInterfazRETProductosReclamados(java.util.Map)
	 */
	public void deleteInterfazRETProductosReclamados(Map queryParams) {
		getSqlMapClientTemplate()
				.delete("spusicc.reclamos.ReclamosSQL.deleteInterfazRETProductosReclamados",
						queryParams);

	}

	public void deleteInterfazRETRecepcionarVentasRetailCab() {

		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazRETRecepcionarVentasRetailCab",
						null);
	}

	public void deleteInterfazRETRecepcionarVentasRetailDet() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazRETRecepcionarVentasRetailDet",
						null);
	}

	public void deleteInterfazRETRecepcionarVentasRetailDetDet() {
		getSqlMapClientTemplate()
				.delete("sisicc.InterfazSQL.deleteInterfazRETRecepcionarVentasRetailDetDet",
						null);
	}

	public void deleteParamInterfaz(Map params) {
		Map queryParams = new HashMap();
		String codigoInterfaz = params.get("codigoInterfaz") != null ? params
				.get("codigoInterfaz").toString()
				: Constants.NO_CODIGO_INTERFAZ;
		queryParams.put("codigoInterfaz", codigoInterfaz);
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterGttParametros", queryParams);

	}

	public void deleteParamTmpInterfaz(Map params) {
		Map queryParams = new HashMap();
		String codigoInterfaz = params.get("codigoInterfaz") != null ? params
				.get("codigoInterfaz").toString()
				: Constants.NO_CODIGO_INTERFAZ;
		queryParams.put("codigoInterfaz", codigoInterfaz);
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterTmpParametros", queryParams);

	}

	public void deleteTablaControlIVR() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteTablaControlIVR", null);
	}

	public void deleteVentaBaseConsultoras() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteVentaBaseConsultoras", null);
	}

	public void executeActualizaNumeroLoteMica(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeActualizaNumeroLoteMica", params);
	}

	public void executeCambioCodigoVenta(Map parametros) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.procesoCambioCodigoVenta", parametros);
	}

	public String executeExportarMatrizLBEL(Map parametros) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.procesoExportarMatriz", parametros);
		return ((String) parametros.get("codRetorno"));
	}

	public void executeGenerarBoletasRecojo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeGenerarBoletasRecojo", params);

	}

	public void executeGenerarXMLBoletasRecojo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeGenerarXMLBoletasRecojo", params);

	}
	
	public void executeGenerarXMLBoletasRecojoORA(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeGenerarXMLBoletasRecojoORA", params);

	}

	public void executeInterfazBELEnviarDireccionClientes(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazBELEnviarDireccionClientes",
				queryParams);
	}

	public void executeInterfazBELEnviarFacturasCabecera(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazBELEnviarFacturasCabecera",
				queryParams);
	}

	public void executeInterfazBELEnviarFacturasDetalle(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazBELEnviarFacturasDetalle",
				queryParams);
	}

	public void executeInterfazBELEnviarPorcentajesReferencia(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazBELEnviarPorcentajesReferencia",
						queryParams);
	}

	public void executeInterfazBELEnviarUbicacionesGeograficas(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazBELEnviarUbicacionesGeograficas",
						queryParams);
	}

	public void executeInterfazBELEnviarUnidadesAtendidas(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazBELEnviarUnidadesAtendidas",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazCOBCargarDatosInfocorp(java.util.Map)
	 */
	public void executeInterfazCOBCargarDatosInfocorp(Map queryParams) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazCOBCargarDatosInfocorp",
				queryParams);
	}

	public void executeInterfazCOBEnviarDatosInfocorp(Map queryParams) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazCOBEnviarDatosInfocorp",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRGenerarFicherosAdam(java.util.Map)
	 */
	public void executeInterfazCOMEnviarArchivoAdam(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazCOMEnviarArchivoAdam",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazCOMEnviarArchivoEjecutivaNuevaAspirante(java.util.Map)
	 */
	public void executeInterfazCOMEnviarArchivoEjecutivaNuevaAspirante(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazCOMEnviarArchivoEjecutivaNuevaAspirante",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazCOMEnviarCargaMasivaEjecutivaNueva(java.util.Map)
	 */
	public void executeInterfazCOMEnviarCargaMasivaEjecutivaNueva(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazCOMEnviarCargaMasivaEjecutivaNueva",
						params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazCOMEnviarFicheroPagoComisionEjecutiva(java.util.Map)
	 */
	public void executeInterfazCOMEnviarFicheroPagoComisionEjecutiva(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazCOMEnviarFicheroPagoComisionEjecutiva",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazDATAdicionales
	 * (java.util.Map)
	 */
	public void executeInterfazDATDEbelistaDatosAdic(Map params) {
		if (log.isDebugEnabled()) {
			log.debug("Iniciando executeInterfazDATDEbelistaDatosAdic, params="
					+ params);
		}
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazReuConsUtmPro", params);

		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDatAdicionales", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarArchivoControl(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarArchivoControl",
				queryParams);
	}

	public void executeInterfazDATEnviarCanalesVenta(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCanalesVenta",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionApta(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionApta(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionApta",
				queryParams);
	}

	public void executeInterfazDATEnviarCapacitacionAptaReenvio(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionAptaReenvio",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionCliente(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionCliente(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionCliente",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionClienteReenvio(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionClienteReenvio(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionClienteReenvio",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionCursoCliente(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionCursoCliente(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionCursoCliente",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionCursoClienteReenvio(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionCursoClienteReenvio(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionCursoClienteReenvio",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionProgramada(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionProgramada(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionProgramada",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCapacitacionProgramadaReenvio(java.util.Map)
	 */
	public void executeInterfazDATEnviarCapacitacionProgramadaReenvio(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCapacitacionProgramadaReenvio",
						queryParams);
	}

	public void executeInterfazDATEnviarCDRConsultora(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCDRConsultora",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarClienteGeografia(java.util.Map)
	 */
	public void executeInterfazDATEnviarClienteGeografia(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarClienteGeografia",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarClienteGeografiaReenvio(java.util.Map)
	 */
	public void executeInterfazDATEnviarClienteGeografiaReenvio(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarClienteGeografiaReenvio",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazDATEnviarControl
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarControl(Map queryParams) {
		log.info("Entro a InterfazSiCCDAOiBatis - executeInterfazDATEnviarControl(Map)");
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarControl",
				queryParams);
		log.info("Salio a InterfazSiCCDAOiBatis - executeInterfazDATEnviarControl(Map)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursoAmbito(java.util.Map)
	 */
	public void executeInterfazDATEnviarCursoAmbito(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCursoAmbito",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazDATEnviarCursos
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarCursos(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCursos",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictados(java.util.Map)
	 */
	public void executeInterfazDATEnviarCursosDictados(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCursosDictados",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarCursosDictadosHistoria(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCursosDictadosHistoria",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoriaReenvio(java.util.Map)
	 */
	public void executeInterfazDATEnviarCursosDictadosHistoriaReenvio(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCursosDictadosHistoriaReenvio",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosReenvio(java.util.Map)
	 */
	public void executeInterfazDATEnviarCursosDictadosReenvio(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarCursosDictadosReenvio",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazDATEnviarEmpresa
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarEmpresa(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarEmpresa",
				queryParams);
	}

	public void executeInterfazDATEnviarEstadosConsultoraCierrePeriodo(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarEstadosConsultoraCierrePeriodo",
						params);
	}

	public void executeInterfazDATEnviarHistoricoPremiosDespachadosConcurso(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarHistoricoPremiosDespachadosConcurso",
						params);
	}

	public void executeInterfazDATEnviarImpuestosProducto(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarImpuestosProducto",
				params);
	}

	public void executeInterfazDATEnviarIncentivosTiempo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarIncentivosTiempo",
				params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarInstructoraGeografia(java.util.Map)
	 */
	public void executeInterfazDATEnviarInstructoraGeografia(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarInstructoraGeografia",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarInstructoras(java.util.Map)
	 */
	public void executeInterfazDATEnviarInstructoras(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarInstructoras",
				queryParams);
	}

	public void executeInterfazDATEnviarMaestroConcursos(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroConcursos",
				params);
	}

	public void executeInterfazDATEnviarMaestroEbelistasSessionExpert(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroEbelistasSessionExpert",
						params);

	}

	public void executeInterfazDATEnviarMaestroEstadosCobranza(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroEstadosCobranza",
						params);
	}

	public void executeInterfazDATEnviarMaestroMarcasProducto(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroMarcasProducto",
						params);
	}

	public void executeInterfazDATEnviarMaestroNivelesConcurso(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroNivelesConcurso",
						params);
	}

	public void executeInterfazDATEnviarMaestroProductos(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroProductos",
				params);
	}

	public void executeInterfazDATEnviarMaestroProductosSessionExpert(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroProductosSessionExpert",
						params);

	}

	public void executeInterfazDATEnviarMaestroTiposDocumentoPedido(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroTiposDocumentoPedido",
						params);
	}

	public void executeInterfazDATEnviarMaestroZonas(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroZonas",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarObjetivoPaisAnual(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarObjetivoPaisAnual",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarObjetivoPaisPeriodo(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarObjetivoPaisPeriodo",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarObjetivoRegionAnual(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarObjetivoRegionAnual",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarObjetivoRegionPeriodo(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarObjetivoRegionPeriodo",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarObjetivoZonaAnual(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarObjetivoZonaAnual",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCursosDictadosHistoria(java.util.Map)
	 */
	public void executeInterfazDATEnviarObjetivoZonaPeriodo(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarObjetivoZonaPeriodo",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazDATEnviarPais
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarPais(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarPais", queryParams);
	}

	public void executeInterfazDATEnviarParametriaOpcionesPremioConcurso(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarParametriaOpcionesPremioConcurso",
						params);
	}

	public void executeInterfazDATEnviarParticipantesConcurso(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarParticipantesConcurso",
						params);
	}

	public void executeInterfazDATEnviarPremiosAsignadosTotal(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarPremiosAsignadosTotal",
						params);

	}

	public void executeInterfazDATEnviarPremiosSeleccionados(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarPremiosSeleccionados",
						params);
	}

	public void executeInterfazDATEnviarPremiosSolicitados(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarPremiosSolicitados",
						params);
	}

	public void executeInterfazDATEnviarProductosConcurso(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazDATEnviarProductosConcurso",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazDATEnviarPrograma
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarPrograma(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarPrograma",
				queryParams);
	}

	public void executeInterfazDATEnviarPuntajePeriodoConcursoVigente(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarPuntajePeriodoConcursoVigente",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarRankingLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarRankingLideres(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarRankingLideres",
				params);

	}

	public void executeInterfazDATEnviarRecomendantesRecomendadasConcurso(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarRecomendantesRecomendadasConcurso",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarResponsablesSeccion(java.util.Map)
	 */
	public void executeInterfazDATEnviarResponsablesSeccion(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarResponsablesSeccion",
						params);
	}

	public void executeInterfazDATEnviarResultadoConcursoLideres(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarResultadoConcursoLideres",
						params);
	}

	public void executeInterfazDATEnviarSaldoPromedioPeriodo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarSaldoPromedioPeriodo",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarTablaClientes(java.util.Map)
	 */
	public void executeInterfazDATEnviarTablaClientes(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarTablaClientes",
				params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarTipoClasificacionPrograma(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoClasificacionPrograma(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarTipoClasificacionPrograma",
						params);

	}

	public void executeInterfazDATEnviarUnidadesAdministrativasConcurso(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarUnidadesAdministrativasConcurso",
						params);
	}

	public void executeInterfazDATEnviarVentaSeccionPeriodo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarVentaSeccionPeriodo",
						params);
	}

	public void executeInterfazECOEnviarPuntajesXCampanya(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazECOEnviarVentasPuntajesxCampanya",
						params);
	}

	public void executeInterfazECOEnviarVentaBaseConsultoras(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazECOEnviarVentaBaseConsultoras",
						queryParams);
	}

	public void executeInterfazEVIEnviarCabecerasPedidosDigitados(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazEVIEnviarCabecerasPedidosDigitados",
						params);

	}

	public void executeInterfazEVIEnviarHistoricoConsultoras(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazEVIEnviarHistoricoConsultoras",
						params);
	}

	public void executeInterfazEVIEnviarResultadosCobranza(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazEVIEnviarResultadosCobranza",
						params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getPeriodosByPaisMarcaCanalAcceso(java.util.Map)
	 */

	public void executeInterfazEVIEnviarResultadosFacturacion(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazEVIEnviarResultadosFacturacion",
						params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodosByPMC(java.util
	 * .Map)
	 */

	public void executeInterfazEVIEnviarSaldosConsultora(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazEVIEnviarSaldosConsultora",
				params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodoDefaultByPaisCanal
	 * (java.util.Map)
	 */

	public void executeInterfazIVREnviarBasesIncumplidas(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarBasesIncumplidas",
				params);
	}

	public void executeInterfazIVREnviarConcursos(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarConcursos", params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getPeriodoDefaultByPaisMarcaCanal(java.util.Map)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazIVREnviarMaestroClientes(java.util.Map)
	 */
	public void executeInterfazIVREnviarMaestroClientes(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarMaestroClientes",
				params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getPeriodoDefaultByPaisMarcaCanalAcceso(java.util.Map)
	 */

	public void executeInterfazIVREnviarNiveles(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarNiveles", params);
	}

	public void executeInterfazIVREnviarPremios(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarPremios", params);
	}

	public void executeInterfazIVREnviarTablaClientes(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarTablaClientes",
				params);
	}

	public void executeInterfazIVREnviarTablaConcursos(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarTablaConcursos",
				params);
	}

	public void executeInterfazIVREnviarTablaControl(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarTablaControl",
				params);
	}

	public void executeInterfazIVREnviarTablaMotivosRechazo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazIVREnviarTablaMotivosRechazo",
						params);
	}

	public void executeInterfazIVREnviarTablaPostVenta(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazIVREnviarTablaPostVenta",
				params);

	}

	public void executeInterfazLLIEnviarVentaPeriodo(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazLLIEnviarVentaPeriodo",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazLLIEnviarVentaPeriodoEquivalencia(java.util.Map)
	 */
	public void executeInterfazLLIEnviarVentaPeriodoEquivalencia(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazLLIEnviarVentaPeriodoEquivalencia",
						params);
	}

	public void executeInterfazLLIEnviarVentaRealDiariaAcumulada(Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazLLIEnviarVentaRealDiariaAcumulada",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazLLIEnviarVentaRealDiariaAcumuladaEquivalencia
	 * (java.util.Map)
	 */
	public void executeInterfazLLIEnviarVentaRealDiariaAcumuladaEquivalencia(
			Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazLLIEnviarVentaRealDiariaAcumuladaEquivalencia",
						queryParams);
	}

	public void executeInterfazMYEEnviarArchivoPremios(Map queryParams) {
		if (log.isDebugEnabled()) {
			log.debug(queryParams.get("codigoPais"));
			log.debug(queryParams.get("codigoSistema"));
			log.debug(queryParams.get("codigoInterfaz"));
			log.debug(queryParams.get("nombreArchivo"));
		}

		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazMYEEnviarArchivoPremios",
				queryParams);
	}

	public void executeInterfazMYEEnviarCabeceraPedidos(Map queryParams) {
		if (log.isDebugEnabled()) {
			log.debug("codigoPais : " + queryParams.get("codigoPais"));
			log.debug("codigoSistema : " + queryParams.get("codigoSistema"));
			log.debug("codigoInterfaz : " + queryParams.get("codigoInterfaz"));
			log.debug("nombreArchivo : " + queryParams.get("nombreArchivo"));
			log.debug("codigoPeriodo : " + queryParams.get("codigoPeriodo"));
			log.debug("codigoMarca : " + queryParams.get("codigoMarca"));
			log.debug("codigoCanal : " + queryParams.get("codigoCanal"));
			log.debug("codigoTipoCliente : "
					+ queryParams.get("codigoTipoCliente"));
			log.debug("fechaFacturacion : "
					+ queryParams.get("fechaFacturacion"));
		}

		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazMYEEnviarCabeceraPedidos",
				queryParams);
	}

	public void executeInterfazMYEEnviarFaltantesAnunciados(Map queryParams) {

		if (log.isDebugEnabled()) {
			log.debug(queryParams.get("codigoPais"));
			log.debug(queryParams.get("codigoSistema"));
			log.debug(queryParams.get("codigoInterfaz"));
			log.debug(queryParams.get("nombreArchivo"));
			log.debug(queryParams.get("codigoPeriodo"));
		}

		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazMYEEnviarFaltantesAnunciados",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazMYEEnviarHistoricoPegs(java.util.Map)
	 */
	public void executeInterfazMYEEnviarHistoricoPegs(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazMYEEnviarHistoricoPegs",
				params);

	}

	public void executeInterfazMYEEnviarInterfacesDiarias(Map queryParams) {
		if (log.isDebugEnabled()) {
			log.debug(queryParams.get("codigoPais"));
			log.debug(queryParams.get("codigoSistema"));
			log.debug(queryParams.get("codigoInterfaz"));
			log.debug(queryParams.get("nombreArchivo"));
			log.debug(queryParams.get("codigoPeriodo"));
			log.debug(queryParams.get("codigoMarca"));
			log.debug(queryParams.get("codigoCanal"));
			log.debug(queryParams.get("fechaFacturacion"));
		}

		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazMYEEnviarInterfacesDiarias",
				queryParams);
	}

	public void executeInterfazMYEEnviarTipoProductoCatalogo(Map queryParams) {

		if (log.isDebugEnabled()) {
			log.debug(queryParams.get("codigoPais"));
			log.debug(queryParams.get("codigoSistema"));
			log.debug(queryParams.get("codigoInterfaz"));
			log.debug(queryParams.get("nombreArchivo"));
			log.debug(queryParams.get("codigoCanal"));
			log.debug(queryParams.get("codigoMarca"));
		}

		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazMYEEnviarTipoProductoCatalogo",
						queryParams);
	}

	public void executeInterfazMYEEnviarVentaDirectaCabecera(Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazMYEEnviarVentaDirectaCabecera",
						queryParams);
	}

	public void executeInterfazMYEEnviarVentaDirectaDetalle(Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazMYEEnviarVentaDirectaDetalle",
						queryParams);
	}

	public void executeInterfazMYEMovimientosCuentaCorriente(Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazMYEMovimientosCuentaCorriente",
						queryParams);
	}

	public void executeInterfazOCREnviarMaestroClientesCorporativo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazOCREnviarMaestroClientesCorporativo",
						params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazOCREnviarMatriz
	 * (java.util.Map)
	 */
	public void executeInterfazOCREnviarMatriz(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazOCREnviarMatriz", params);
	}

	public void executeInterfazOCREnviarMatrizFacturacionCorporativo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazOCREnviarMatrizFacturacionCorporativo",
						params);

	}

	public void executeInterfazOCREnviarOCSCabecera(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCREnviarOCSCabecera",
				queryParams);
	}

	public void executeInterfazOCREnviarOCSDetalle(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCREnviarOCSDetalle",
				queryParams);
	}

	public void executeInterfazOCREnviarRangoPeriodosVigenteCorporativo(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazOCREnviarRangoPeriodosVigenteCorporativo",
						params);

	}

	public void executeInterfazOCREnviarReclamosPostventa(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCREnviarReclamosPostventa",
				params);
	}

	public void executeInterfazOCREnviarRegionesCorporativo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazOCREnviarRegionesCorporativo",
						params);

	}

	public void executeInterfazOCREnviarZonasCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazOCREnviarZonasCorporativo",
				params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRProcesarConsolidadoActualizacionDatos(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoActualizacionDatos(
			Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoActualizacionDatos",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRProcesarConsolidadoIngresoMetas(java.util.Map)
	 */
	public void executeInterfazOCRProcesarConsolidadoIngresoMetas(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoIngresoMetas",
						params);
	}

	public void executeInterfazOCRProcesarConsolidadoOCS(Map map) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoOCS",
				map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRProcesarConsolidadoOCSCabeceraCorporativo()
	 */
	public void executeInterfazOCRProcesarConsolidadoOCSCabeceraCorporativo(
			Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoOCSCabeceraCorporativo",
						params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRProcesarConsolidadoOCSDetalleCorporativo()
	 */
	public void executeInterfazOCRProcesarConsolidadoOCSDetalleCorporativo(
			Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoOCSDetalleCorporativo",
						params);

	}

	public void executeInterfazOCRProcesarConsolidadoPostventaCabecera(
			Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoPostventaCabecera",
						params);
	}

	public void executeInterfazOCRProcesarConsolidadoPostventaDetalle(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoPostventaDetalle",
						params);
	}

	public void executeInterfazOCRProcesarConsolidadoPostventaDetalleOCR(
			Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoPostventaDetalleOCR",
						params);
	}

	public void executeInterfazOCRProcesarConsolidadoSolicitudCredito(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRProcesarConsolidadoSolicitudCredito",
						params);

	}

	public void executeInterfazOCRRecepcionarActualizacionDatos(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarActualizacionDatos",
						params);
	}

	public void executeInterfazOCRRecepcionarArriboZonas(Map map) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCRRecepcionarArriboZonas",
				map);

	}

	public void executeInterfazOCRRecepcionarConsolidadoOCSCabecera(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarConsolidadoOCSCabecera",
						queryParams);
	}

	public void executeInterfazOCRRecepcionarConsolidadoOCSCabeceraCorporativo(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarConsolidadoOCSCabeceraCorporativo",
						queryParams);
	}

	public void executeInterfazOCRRecepcionarConsolidadoOCSDetalle(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarConsolidadoOCSDetalle",
						queryParams);
	}

	public void executeInterfazOCRRecepcionarConsolidadoOCSDetalleCorporativo(
			Map queryParams) {

		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarConsolidadoOCSDetalleCorporativo",
						queryParams);

	}

	public void executeInterfazOCRRecepcionarDWHOCR(Map parametros) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCRRecepcionarDWHOCR",
				parametros);
	}

	public void executeInterfazOCRRecepcionarFichaInscripcionPrivilege(
			Map parametros) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarFichaInscripcionPrivilege",
						parametros);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRRecepcionarIngresoMetas(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarIngresoMetas(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCRRecepcionarIngresoMetas",
				params);
	}

	public void executeInterfazOCRRecepcionarPagare(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazOCRRecepcionarPagare",
				params);

	}

	public void executeInterfazOCRRecepcionarSeguimientoPedido(Map map) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarSeguimientoPedido",
						map);

	}

	public void executeInterfazOCRRecepcionarServiciosPostventasCabec(
			Map parametros) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarServiciosPostventasCabec",
						parametros);
	}

	public void executeInterfazOCRRecepcionarServiciosPostventasDetal(
			Map parametros) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarServiciosPostventasDetal",
						parametros);
	}

	public void executeInterfazOCRRecepcionarSolicitudCredito(Map map) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarSolicitudCredito",
						map);

	}

	public void executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeCabecera(
			Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeCabecera",
						params);

	}

	public void executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeDetalle(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeDetalle",
						queryParams);

	}

	public void executeInterfazOCRRecepcionarTablaControlMultiformato(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarTablaControlMultiformato",
						params);

	}

	public void executeInterfazPRIEnviarCalificacion(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazPRIEnviarCalificacion",
				queryParams);
	}

	public void executeInterfazRECEnviarBoletasRecojoCabecera(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRECEnviarBoletasRecojoCabecera",
						params);

	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazIMPGenerarBanDominicana(java.util.Map)
	 */
	public void executeInterfazIMPGenerarBanDominicana(Map params) {
		getSqlMapClientTemplate()
		.queryForList(
				"sisicc.InterfazSQL.executeInterfazIMPGenerarBanDominicana",
				params);
	}
	
	public void executeInterfazRECEnviarBoletasRecojoControl(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRECEnviarBoletasRecojoControl",
						params);

	}

	public void executeInterfazRECEnviarBoletasRecojoDetalle(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRECEnviarBoletasRecojoDetalle",
						params);

	}

	public void executeInterfazRECEnviarTransferenciaBoletasRecojo(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRECEnviarTransferenciaBoletasRecojo",
						params);
	}

	public void executeInterfazRECEnviarUnidadesAlmacenVirtual(Map params) {

		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRECEnviarUnidadesAlmacenVirtual",
						params);
	}

	public void executeInterfazRECProductosReclamados(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazRECProductosReclamados",
				queryParams);
	}

	public void executeInterfazRECProductosReclamadosCab(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazRECProductosReclamadosCab",
				queryParams);
	}

	public void executeInterfazRECProductosReclamadosDet(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazRECProductosReclamadosDet",
				queryParams);
	}

	public void executeInterfazRECProductosReclamadosDetDet(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazRECProductosReclamadosDetDet",
						queryParams);
	}

	public void executeInterfazRECRecepcionarBoletasRecojoCabecera(
			Map queryParams) {

		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazRECRecepcionarBoletasRecojoCabecera",
						queryParams);

	}

	public void executeInterfazRECRecepcionarBoletasRecojoDetalle(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazRECRecepcionarBoletasRecojoDetalle",
						queryParams);
	}

	public void executeInterfazRETEnviarComisiones(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazRETEnviarComisiones",
				queryParams);
	}
	
	public void executeInterfazIMPProgramaPunto(Map params) {
		getSqlMapClientTemplate()
		.queryForList(
				"sisicc.InterfazSQL.executeInterfazIMPProgramaPunto",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazRETEnviarComplementoFacturaVentaDirecta(java.util.Map)
	 */
	public void executeInterfazRETEnviarCompleFacturaVentaDirecta(
			Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRETEnviarCompleFacturaVentaDirecta",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazRETEnviarFacturaVentaDirecta(java.util.Map)
	 */
	public void executeInterfazRETEnviarFacturaVentaDirecta(Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRETEnviarFacturaVentaDirecta",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazRETEnviarInformacionVenta(java.util.Map)
	 */
	public void executeInterfazRETEnviarInformacionVenta(Map queryParams) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazRETEnviarInformacionVenta",
				queryParams);
	}

	public void executeInterfazRETEnviarMatrizCampanya(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazRETEnviarMatrizCampanya",
				queryParams);
	}

	public void executeInterfazRETEnviarMatrizPuntajesCalypso(Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazRETEnviarMatrizPuntajesCalypso",
						queryParams);
	}

	public void executeInterfazRETEnviarSolicitudesMonetariasCabecera(
			Map queryParams) {
		if (log.isDebugEnabled()) {
			log.debug(queryParams.get("codigoPais"));
			log.debug(queryParams.get("codigoSistema"));
			log.debug(queryParams.get("codigoInterfaz"));
			log.debug(queryParams.get("numeroLoteSolicitud"));
			log.debug(queryParams.get("codigoTipoOrigenDatos"));
			log.debug(queryParams.get("codigoAccesoFisico"));
			log.debug(queryParams.get("tipoDespacho"));
		}

		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazRETEnviarSolicitudesMonetariasCabecera",
						queryParams);
	}

	public void executeInterfazRETEnviarSolicitudesMonetariasPosicion(
			Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazRETEnviarSolicitudesMonetariasPosicion",
						queryParams);
	}

	/* (non-Javadoc)
	 * JPJC PER-SiCC-2013-0692
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazPEREnviarArchivoPDT(java.util.Map)
	 */
	public void executeInterfazPEREnviarArchivoPDT(
			Map queryParams) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazPEREnviarArchivoPDT", 
						queryParams);
	}

	public void executeInterfazREUEnviarConsultoras(Map queryParams) {
		String enviarHistorico = MapUtils.getString(queryParams,
				"enviarHistorico", Constants.NO);
		String cargarInformacion = MapUtils.getString(queryParams,
				"cargarInformacion", Constants.NO);

		getSqlMapClientTemplate()
				.update("spusicc.ProcesosSTOSQL.executeHabilitarDesabilitarProlConsultoras",
						null);
		// Validamos si generamos nuevamente la informacion
		if (StringUtils.equals(cargarInformacion, Constants.SI)) {
			// Validamos si generamos historico o novedades
			if (StringUtils.equals(enviarHistorico, Constants.SI)) {
				getSqlMapClientTemplate()
						.queryForList(
								"sisicc.InterfazSQL.executeInterfazREUCargarConsultorasHistorico",
								queryParams);

			} else {
				getSqlMapClientTemplate()
						.queryForList(
								"sisicc.InterfazSQL.executeInterfazREUIdentificarNovedades",
								queryParams);
				getSqlMapClientTemplate()
						.queryForList(
								"sisicc.InterfazSQL.executeInterfazREUCargarConsultoras",
								queryParams);
			}
		}
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeInterfazREUEnviarConsultoras",
				queryParams);
	}

	public void executeInterfazREUEnviarMatrizCampanya(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazREUEnviarMatrizCampanya",
				queryParams);
	}

	public void executeInterfazSIAPEnviarAnalisisPrecios(Map queryParams) {
		if (log.isDebugEnabled()) {
			log.debug("codigoPais : " + queryParams.get("codigoPais"));
			log.debug("codigoSistema : " + queryParams.get("codigoSistema"));
			log.debug("codigoInterfaz : " + queryParams.get("codigoInterfaz"));
			log.debug("nombreArchivo : " + queryParams.get("nombreArchivo"));
			log.debug("codigoPeriodo : " + queryParams.get("codigoPeriodo"));
			log.debug("codigoMarca   : " + queryParams.get("codigoMarca"));
		}
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazSIAPEnviarAnalisisPrecios",
				queryParams);
	}

	public void executeInterfazSICEnviarInscritas(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazSICEnviarInscritas",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeOCRRecepcionarConsolidadoOCSCargaSTO(java.util.Map)
	 */
	public void executeOCRRecepcionarConsolidadoOCSCargaSTO(Map queryParams) {

		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeOCRRecepcionarConsolidadoOCSCargaSTO",
						queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeProcesoRECActualizaUnidadesDevueltas(java.util.Map)
	 */
	public void executeProcesoRECActualizaUnidadesDevueltas(Map params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeProcesoRECActualizaUnidadesDevueltas",
						params);
	}

	public void executeTruncateGenerarXMLBoletasRecojo() {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.executeTruncateGenerarXMLBoletasRecojo",
				null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getAccesoByCanal(java.util
	 * .Map)
	 */
	public List getAccesoByCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getAccesoByCanal", params);
	}
	
	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getAccesosByCanalByCodigoISO(java
	 * .lang.String)
	 */
	public List getAccesosByCanalByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getAccesosByCanalByCodigoISO", codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getAccesosById(java.util.Map)
	 */
	public Base getAccesosByCodigo(Map criteria) {
		return (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getAccesosByCodigo", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getAccesosByCodigoISO(java.lang
	 * .String)
	 */
	public List getAccesosByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getAccesosByCodigoISO", codigo);
	}

	public List getAccesosTodosByCanalByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getAccesosTodosByCanalByCodigoISO", codigo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getActividades(java.lang.String,
	 * java.lang.String)
	 */
	public List getActividades(String codigoIsoIdioma, String codigoMarca) {
		Map params = new HashMap();
		params.put("codigoIsoIdioma", codigoIsoIdioma);
		params.put("codigoMarca", codigoMarca);
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getActividades", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getActividadesByCodigoISO(java.
	 * lang.String)
	 */
	public List getActividadesByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getActividadesByCodigoISO", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getAlmacenByCodigo(java.
	 * util.Map)
	 */
	public Base getAlmacenByCodigo(Map criteria) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getAlmacenesById", criteria);
		return base;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getAlmacenesByCodigoISO(java.lang
	 * .String)
	 */
	public List getAlmacenesByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getAlmacenesByCodigoISO", codigo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getAlmacenesByCodigoISO(java.lang
	 * .String)
	 */
	public List getAlmacenesByCodigoISOPais(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getAlmacenesByCodigoISOPais", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getBancosByPais(java.util
	 * .Map)
	 */
	public List getBancosByPais(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getBancosByPais", criteria);
	}

	public List getBoletaRecojoCabeceraList(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getBoletaRecojoCabeceraList", params);
	}

	public List getBoletaRecojoLineaList(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getBoletaRecojoLineaList", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getCanalByCodigo(java.util
	 * .Map)
	 */
	public Base getCanalByCodigo(Map criteria) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCanalesById", criteria);
		return base;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCanalesByCodigoISO(java.lang
	 * .String)
	 */
	public List getCanalesByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCanalesByCodigoISO", codigo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCanalesRolByCodigoISO(java.lang
	 * .String)
	 */
	public List getCanalesRolByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCanalesRolByCodigoISO", codigo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.BloqueoLiderDAO#getBloqueoLidersByCriteria(java
	 * .util.Map)
	 */
	public String getCantidadClientesByPK(Map criteria) {
		String cantidadClientes = (String) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.ProcesosCOMSQL.getCantidadClientesByPK",
						criteria);
		return cantidadClientes;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getCantidadLibretaAhorrobyDNI
	 * (java.util.Map)
	 */
	public String getCantidadLibretaAhorrobyDNI(String dni) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getCantidadLibretaAhorrobyDNI", dni);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.BloqueoLiderDAO#getBloqueoLidersByCriteria(java
	 * .util.Map)
	 */
	public String getCantidadPeriodosByPK(Map criteria) {
		String cantidadPeriodos = (String) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.ProcesosCOMSQL.getCantidadPeriodosByPK",
						criteria);
		return cantidadPeriodos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getCentroDByPaisMarcaCanal
	 * (java.util.Map)
	 */
	public List getCentroDistribucionByPais(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCentroDistribucionByPais", params);
	}

	public List getClasificacionesByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getClasificacionesByCriteria", criteria);
	}

	public List getClasificacionesByCriteriaMultiple(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getClasificacionesByCriteriaMultiple",
				criteria);

	}

	public List getClasificacionesByCriteriaMultipleOID(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getClasificacionesByCriteriaMultipleOID",
				criteria);
	}
	
	public List getClasificacionesByCriteriaMultipleCodigo(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getClasificacionesByCriteriaMultipleCodigo",
				criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getClienteSICCByCodigo(java.lang
	 * .String)
	 */
	public Cliente getClienteSICCByCodigo(Map criteria) {
		Cliente cliente = null;
		List clientes = getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosGEOSQL.getClienteSICCByCodigo", criteria);
		// Debido a que el query puede retornar mas de un registro para
		// un mismo codigo debido al tipo asociado, tomamos el primero
		if (clientes != null && clientes.size() > 0) {
			cliente = (Cliente) clientes.get(0);
		} else {
			throw new ObjectRetrievalFailureException(Cliente.class,
					criteria.get("codigoCliente"));
		}
		return cliente;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getClientesPendientesActualizarUA(java.util.Map)
	 */
	public List getClientesPendientesActualizarUA(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getClientesPendientesActualizarUA",
				criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getClientesPendientesActualizarUA(java.util.Map)
	 */
	public List getClientesPendientesActualizarXLSUA(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getClientesPendientesActualizarXLSUA",
				criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getClientesSICCByCriteria(java.
	 * util.Map)
	 */
	public List getClientesSICCByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosGEOSQL.getClientesSICCByCriteria", criteria);
	}

	public String getCodigoCompania(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getCodigoCompania", params);
	}

	public String getCodigoDocumento(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getCodigoDocumento", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getCodigoConsultorabyCodigoPlanilla()
	 */
	public String getCodigoSociedadbyCodigoPlanilla(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCodigoConsultorabyCodigoPlanilla",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getCodigoSociedadbyCuentaCorrienteBancaria()
	 */
	public String getCodigoSociedadbyCuentaCorrienteBancaria(Map params) {
		return (String) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.GenericoSQL.getCodigoSociedadbyCuentaCorrienteBancaria",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getComision()
	 */
	public List getComision() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getComision", null);
	}

	public List getComprobantesByPaisBancoCuentaCorriente(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getComprobantesByPaisBancoCuentaCorriente",
				criteria);
	}

	public List getConcursos(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConcursos", criteria);
	}

	public List getConcursosByPaisMarcaCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConcursosByPaisMarcaCanal", params);
	}

	public List getConcursosByPaisMarcaCanalAnhioTodos(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConcursosByPaisMarcaCanalAnhioTodos",
				params);
	}

	public List getConcursosByPaisMarcaCanalDellate(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConcursosByPaisMarcaCanalDetalle",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getConcursosByPaisMarcaCanalDetallePremiosElec(java.util.Map)
	 */
	public List getConcursosByPaisMarcaCanalDetallePremiosElec(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.GenericoSQL.getConcursosByPaisMarcaCanalDetallePremiosElec",
						criteria);
	}

	public List getConcursosByPaisMarcaCanalPeriodo(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConcursosByPaisMarcaCanalPeriodo",
				criteria);
	}

	public List getConsolidadoTransferenciaBoletasRecojoConAnulacion(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getConsolidadoTransferenciaBoletasRecojoConAnulacion",
						params);
	}

	public List getConsolidadoTransferenciaBoletasRecojoSinAnulacion(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getConsolidadoTransferenciaBoletasRecojoSinAnulacion",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getConsultaRECProductosList
	 * (java.util.Map)
	 */
	public List getConsultaRECProductosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getConsultaRECProductosList", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getConsultoraByNumeroDocumento
	 * (java.util.Map)
	 */
	public String getConsultoraByNumeroDocumento(Map params) {
		String valor = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCodigoConsultorabyNumeroDocumento",
				params);
		return valor;
	}

	public String getConsultoraExistenteByCriteria(Map params) {
		String defecto = "";
		Base base = new Base();
		List aux = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConsultoraExistenteByCriteria", params);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}

	public List getConsultorasByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getConsultorasByCriteria", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getContCierreCampaByCodigoPeriodo(java.lang.String)
	 */
	public Integer getContCierreCampaByCodigoPeriodo(String codigoPeriodo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getContCierreCampaByCodigoPeriodo",
				codigoPeriodo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCountTerritorioByZona(java.util
	 * .Map)
	 */
	public Integer getCountTerritorioByZona(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCountTerritorioByZona", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCountTerritoriosByCriteria(java
	 * .util.Map)
	 */
	public Integer getCountTerritoriosByCriteria(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCountTerritoriosByCriteria", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCountUbigeoByTerritorio(java
	 * .util.Map)
	 */
	public Integer getCountUbigeoByTerritorio(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCountUbigeoByTerritorio", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCountZonasByCriteria(java.util
	 * .Map)
	 */
	public Integer[] getCountZonasByCriteria(Map criteria) {
		Integer[] resultado = new Integer[2];
		resultado[0] = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getDeterminaClienteMarca", criteria);
		resultado[1] = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCountZonasByCriteria", criteria);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getCuentasCorrientesByCodigoPais
	 * (java.lang.String)
	 */
	public List getCuentasCorrientesByCodigoPais(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCuentasCorrientesByCodigoPais", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getCuentasCorrientesByPaisBancoSociedad(java.util.Map)
	 */
	public List getCuentasCorrientesByPaisBancoSociedad(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCuentasCorrientesByPaisBancoSociedad",
				criteria);
	}

	public List getCuentasCorrientesPorPaisSociedad(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCuentasCorrientesPorPaisSociedad",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getDatosProcesoBatchSICC
	 * (java.lang.String)
	 */
	public Map getDatosProcesoBatchSICC(String codigoProcesoBatch) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getDatosProcesoBatchSICC",
				codigoProcesoBatch);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getDescripcionByProducto
	 * (java.util.Map)
	 */
	public List getDescripcionByProducto(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getDescripcionByProducto", params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getDescuentoEspecifico(int)
	 */
	public String getDescuentoEspecifico(int idCabecera) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getDescuentoEspecifico",
				new Integer(idCabecera));
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getDescuentoVarios(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getDescuentoVarios(String codigoPeriodo,
			String codigoTipoCliente, String codigoSubtipoCliente,
			String codigoTipoOferta, String codigoNegocio,
			String codigoUnidadNegocio, String codigoMarcaProducto,
			String exclusionTipoOferta) {
		Map params = new HashMap();
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("codigoTipoCliente", codigoTipoCliente);
		params.put("codigoSubtipoCliente", codigoSubtipoCliente);
		params.put("codigoTipoOferta", codigoTipoOferta);
		params.put("codigoNegocio", codigoNegocio);
		params.put("codigoUnidadNegocio", codigoUnidadNegocio);
		params.put("codigoMarcaProducto", codigoMarcaProducto);
		params.put("exclusionTipoOferta", exclusionTipoOferta);

		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getDescuentoVarios", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getDesZonasByPaisMarcaCanalRegion(java.util.Map)
	 */
	public List getDesZonasByPaisMarcaCanalRegion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getDesZonasByPaisMarcaCanalRegion", params);
	}

	public List getDetalleOfertaByPeriodoCUV(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getDetalleOfertaByPeriodoCUV", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteConsultora()
	 */
	public Long getDevuelveIdPais(String codigo) {
		Long valor = (Long) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getDevuelveIdPais", codigo);
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getEquivalenciasCount()
	 */
	public Integer getEquivalenciasCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getEquivalenciasCount", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getEstadosBoletasRecojo(
	 * java.lang.String)
	 */
	public List getEstadosBoletasRecojo(String codigoPais) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.reclamos.ReclamosSQL.getEstadosBoletasRecojo",
				codigoPais);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getEstadosMercaderiaByCodigoISO
	 * (java.lang.String)
	 */
	public List getEstadosMercaderiaByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getEstadosMercaderiaByCodigoISO", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPais(java.util
	 * .Map)
	 */
	public List getEstadosPedidos() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getEstadosPedidos");
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCanalesRolByCodigoISO(java.lang
	 * .String)
	 */
	public List getEstadosRolByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getEstadosRolByCodigoISO", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getExisteComisionComercializacion(java.util.Map)
	 */
	public String getExisteComisionComercializacion(Map criteria) {
		String cantidad = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getExisteComisionComercializacion",
				criteria);
		return cantidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteComisionIngreso
	 * (java.util.Map)
	 */
	public String getExisteComisionIngreso(Map criteria) {
		String cantidad = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getExisteComision", criteria);
		return cantidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteComisionRecuperacion
	 * (java.util.Map)
	 */
	public String getExisteComisionRecuperacion(Map criteria) {
		String cantidad = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getExisteComision", criteria);
		return cantidad;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteComisionRecuperacionEjecutiva(java.util.Map)
	 */
	public Integer getExisteComisionRecuperacionEjecutiva(Map criteria) {
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getExisteComisionRecuperacionEjecutiva", criteria);
		return cantidad;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteComisionRecuperacionRegion(java.util.Map)
	 */
	public Integer getExisteComisionRecuperacionRegion(Map criteria) {
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getExisteComisionRecuperacionRegion", criteria);
		return cantidad;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteComisionRecuperacionZona(java.util.Map)
	 */
	public Integer getExisteComisionRecuperacionZona(Map criteria) {
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOMSQL.getExisteComisionRecuperacionZona", criteria);
		return cantidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteConsultora()
	 */
	public Integer getExisteConsultora(Map params) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getExisteConsultora", params);
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteConsultoraByCodigoPais
	 * (java.util.Map)
	 */
	public Integer getExisteConsultoraByCodigoPais(Map params) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getExisteConsultoraByCodigoPais", params);
		return valor;
	}

	public Integer getExisteCuentaCorrienteBancaria(Map params) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getExisteCuentaCorrienteBancaria", params);
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteProyeccionFaltanteDia
	 * (java.util.Map)
	 */
	public String getExisteProyeccionFaltanteDia(Map criteria) {
		String cantidad = (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPRYSQL.getExisteProyeccionFaltanteDia",
				criteria);
		return cantidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteTipoTransaccionBancaria
	 * ()
	 */
	public Integer getExisteTipoTransaccionBancaria(Map params) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getExisteTipoTransaccionBancaria", params);
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getFacturacion(java.util
	 * .Map)
	 */
	public List getFacturacion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getFacturacion", params);
	}

	public String getFormatoFecha(Map criteria) {
		List formato = null;
		formato = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getFormatoFecha", criteria);

		return formato.get(0).toString().substring(13);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getGrupoProceso()
	 */
	public List getGrupoProceso() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getGrupoProceso", null);
	}

	public String getIndicadorAnulacionByCriteria(Map map) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getIndicadorAnulacionByCriteria", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getIndicadorEquivalencia
	 * (java.util.Map)
	 */
	public String getIndicadorEquivalencia(Map params) {
		return getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getIndicadorEquivalencia", params)
				.toString();
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazBELDireccionClientes
	 * (java.util.Map)
	 */
	public List getInterfazBELDireccionClientes(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazBELDireccionClientes", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazBELFacturasCabecera(
	 * java.util.Map)
	 */
	public List getInterfazBELFacturasCabecera(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazBELFacturasCabecera", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazBELFacturasDetalle(java
	 * .util.Map)
	 */
	public List getInterfazBELFacturasDetalle(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazBELFacturasDetalle", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazBELPorcentajesReferencia
	 * (java.util.Map)
	 */
	public List getInterfazBELPorcentajesReferencia(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazBELPorcentajesReferencia",
				params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazMyEbelCronogramaFacturacion
	 * (java.util.Map)
	 */
	public List getInterfazBELUbicacionesGeograficas(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazBELUbicacionesGeograficas",
				params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazBELUnidadesAtendidas
	 * (java.util.Map)
	 */
	public List getInterfazBELUnidadesAtendidas(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazBELUnidadesAtendidas", params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazCOMEnviarLibretaAhorros(java.util.Map)
	 */
	public List getInterfazCOMEnviarLibretaAhorros(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazCOMEnviarLibretaAhorros",
						params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazCOMEnviarLideresNuevas(java.util.Map)
	 */
	public List getInterfazCOMEnviarLideresNuevas(Map params) {

		// Cargamos los parámetros de entrada
		// Ejecutamos el SP
		log.debug("para" + params);

		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeCOM_PR_LIDER_NUEVA", params);
		// Leemos los datos procesados
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazCOMEnviarLideresNuevas", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazCOMEnviarPagoLideres
	 * (java.util.Map)
	 */
	public List getInterfazCOMEnviarPagoLideres(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazCOMEnviarPagoLideres", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazSAMInicializacionStocks
	 * (java.util.Map)
	 */
	public List getInterfazCOMRecopla(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazCOMRecopla", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarControlCierresErrorTipoPeriodo(java.util.Map)
	 */
	public List getInterfazDATEnviarControlCierresErrorCierrePeriodo(
			Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarControlCierresErrorCierrePeriodo",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarControlCierresProcErrorZonaRegion(java.util.Map)
	 */
	public List getInterfazDATEnviarControlCierresErrorZonasRegiones(
			Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarControlCierresErrorZonasRegiones",
						criteria);
	}

	public List getInterfazDATEnviarControlCierresPeriodos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarControlCierresPeriodos",
						criteria);

	}

	public List getInterfazDATEnviarControlCierresPeriodosSalida(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarControlCierresPeriodosSalida",
						criteria);

	}

	public List getInterfazDATEnviarControlCierresZonasRegiones(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarControlCierresZonasRegiones",
						criteria);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazDATEnviarCursos
	 * (java.util.Map)
	 */
	public List getInterfazDATEnviarCursos(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarCursos", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazDATEnviarEmpresa
	 * (java.util.Map)
	 */
	public List getInterfazDATEnviarEmpresa(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarEmpresa", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarIncentivosEstadoConcurso(java.util.Map)
	 */
	public List getInterfazDATEnviarIncentivosEstadoConcurso(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosEstadoConcurso",
						params);
	}

	public List getInterfazDATEnviarIncentivosProductosAlcance(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosProductosAlcance",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarIncentivosTipoAgrupacion1Concurso(java.util.Map)
	 */
	public List getInterfazDATEnviarIncentivosTipoAgrupacion1Concurso(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosTipoAgrupacion1Concurso",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarIncentivosTipoAgrupacion2Concurso(java.util.Map)
	 */
	public List getInterfazDATEnviarIncentivosTipoAgrupacion2Concurso(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosTipoAgrupacion2Concurso",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarIncentivosTipoClienteDirigidoConcurso(java.util.Map)
	 */
	public List getInterfazDATEnviarIncentivosTipoClienteDirigidoConcurso(
			Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosTipoClienteDirigidoConcurso",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarIncentivosTipoEfectividad(java.util.Map)
	 */
	public List getInterfazDATEnviarIncentivosTipoEfectividad(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosTipoEfectividad",
						params);
	}

	public List getInterfazDATEnviarIncentivosTipoTiempo(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosTipoTiempo",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarIncentivosTipoUnidadMedida(java.util.Map)
	 */
	public List getInterfazDATEnviarIncentivosTipoUnidadMedida(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazDATEnviarIncentivosTipoUnidadMedida",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazDATEnviarInstructoraGeografia(java.util.Map)
	 */
	public List getInterfazDATEnviarInstructoraGeografia(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarInstructoraGeografia",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazDATEnviarInstructoras
	 * (java.util.Map)
	 */
	public List getInterfazDATEnviarInstructoras(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarInstructoras", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazDATEnviarPais
	 * (java.util.Map)
	 */
	public List getInterfazDATEnviarPais(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarPais", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazDATEnviarPrograma
	 * (java.util.Map)
	 */
	public List getInterfazDATEnviarPrograma(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazDATEnviarPrograma", params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazGISEnviarDireccionConsultoras(java.util.Map)
	 */
	public List getInterfazGISEnviarDireccionConsultoras(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazGISEnviarDireccionConsultoras",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazLAREnviarSecuenciaPedidos(java.util.Map)
	 */
	public List getInterfazLAREnviarSecuenciaPedidos(Map queryParams) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazLAREnviarSecuenciaPedidos",
				queryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazLAREnviarVisibilidadCronogramaFacturacion(java.util.Map)
	 */
	public List getInterfazLAREnviarVisibilidadCronogramaFacturacion(
			Map queryParams) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazLAREnviarVisibilidadCronogramaFacturacion",
						queryParams);
	}

	public List getInterfazLLIEnviarVentaPeriodo(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazLLIEnviarVentaPeriodo", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazMyEbelCronogramaFacturacion
	 * (java.util.Map)
	 */
	public List getInterfazMyEbelCronogramaFacturacion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazMyEbelCronogramaFacturacion",
				params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */
	public List getInterfazMyEbelMovimientosCuentaCorriente(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazMyEbelMovimientosCuentaCorriente",
						params);
	}

	public List getInterfazMYECodigoAutorizacionSunat(Map params) {

		List listCabecera = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazMYECodigoAutorizacionSunat",
				params);
		return listCabecera;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazMYEEnviarInstructoras
	 * (java.util.Map)
	 */
	public List getInterfazMYEEnviarInstructoras(Map params) {
		List lista = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazMYEEnviarInstructoras", params);
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazMYEEnviarRegiones
	 * (java.util.Map)
	 */
	public List getInterfazMYEEnviarRegiones(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazMYEEnviarRegiones", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazMYEEnviarZonas
	 * (java.util.Map)
	 */
	public List getInterfazMYEEnviarZonas(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazMYEEnviarZonas", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazMYEMovimientosCuentaCorriente(java.util.Map)
	 */
	public List getInterfazMYEMovimientosCuentaCorriente(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazMYEMovimientosCuentaCorriente",
				params);
	}

	public List getInterfazMYEPercepcionesVentaDirectaCabecera(Map params) {
		List listCabecera = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazMYEPercepcionesVentaDirectaCabecera",
						params);
		return listCabecera;
	}

	public List getInterfazMYEPercepcionesVentaDirectaDetalle(Map params) {
		List listCabecera = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazMYEPercepcionesVentaDirectaDetalle",
						params);

		return listCabecera;
	}

	public List getInterfazOCRConsolidadoOCSCabecera(Map criteria) {
		log.debug("Entering method 'getInterfazOCRConsolidadoOCSCabecera'");
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazOCRConsolidadoOCSCabecera",
				criteria);
	}

	public List getInterfazOCRConsolidadoOCSDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazOCRConsolidadoOCSDetalle",
				criteria);
	}

	public List getInterfazOCREnviarRangosPeriodo(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazOCREnviarRangosPeriodo", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazOCREnviarRegiones(java
	 * .util.Map)
	 */
	public List getInterfazOCREnviarRegiones(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazOCREnviarRegiones", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazOCREnviarZonas(java.
	 * util.Map)
	 */
	public List getInterfazOCREnviarZonas(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazOCREnviarZonas", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazPERActualizarPercepcionesConsolidadoCantidad
	 * (biz.belcorp.ssicc.
	 * sisicc.model.InterfazPERActualizarPercepcionesConsolidado)
	 */
	public String getInterfazPERActualizarPercepcionesConsolidadoCantidad(
			InterfazPERActualizarPercepcionesConsolidado consolidado) {
		log.info("Entro a InterfazSiCCDAOiBatis - getInterfazPERActualizarPercepcionesConsolidadoCantidad(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado)");
		String resultado = (String) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.InterfazSQL.getInterfazPERActualizarPercepcionesConsolidadoCantidad",
						consolidado);
		log.info("Salio a InterfazSiCCDAOiBatis - getInterfazPERActualizarPercepcionesConsolidadoCantidad(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado) - Resultado: "
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazPERArchivoPDT
	 * (java.util.Map)
	 */
	public List getInterfazPERArchivoPDT(Map params) {
		log.debug("Codigo de Pais: " + params.get("codigoPais"));
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazPERArchivoPDT", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazPRIPeriodoEvaluar
	 * (java.util.Map)
	 */
	public String getInterfazPRIPeriodoEvaluar(Map params) {
		// Obtenemos la lista de periodos ordenada descendentemente
		List listPeriodos = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazSICEnviarClasificacionConsultorasNuevasPeriodoEvaluar",
						params);
		int campanhas = Integer.parseInt((String) params.get("campanhas"));
		if (log.isDebugEnabled()) {
			log.debug("periodos=" + listPeriodos);
			log.debug("campanhas=" + campanhas);
		}
		if (campanhas < listPeriodos.size()) {
			String periodoEvaluar = (String) listPeriodos.get(campanhas);
			if (log.isDebugEnabled()) {
				log.debug("periodoEvaluar=" + periodoEvaluar);
			}
			return periodoEvaluar;
		} else {
			return null;
		}
	}

	public List getInterfazRECProductosReclamadosSubAccesos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazRECProductosReclamadosSubAccesos",
						criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazRETEnviarComplementoFacturasVentaDirecta(java.util.Map)
	 */
	public List getInterfazRETEnviarComplementoFacturasVentaDirecta(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazRETEnviarComplementoFacturasVentaDirecta",
						params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazRETEnviarFacturasVentaDirecta(java.util.Map)
	 */
	public List getInterfazRETEnviarFacturasVentaDirecta(Map params) {
		logger.debug("getInterfazRETEnviarFacturasVentaDirecta:::::::::");
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazRETEnviarFacturasVentaDirecta",
				params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazRETEnviarMatrizCampanya
	 * (java.util.Map)
	 */
	public List getInterfazRETEnviarMatrizCampanya(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazRETEnviarMatrizCampanya",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazREUCronogramaFacturacion(java.util.Map)
	 */
	public List getInterfazREUCronogramaFacturacion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazREUCronogramaFacturacion",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazREUDocumentosAnulados
	 * (java.util.Map)
	 */
	public List getInterfazREUDocumentosAnulados(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazREUDocumentosAnulados", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazREUEnviarConsultoras
	 * (java.util.Map)
	 */
	public List getInterfazREUEnviarConsultoras(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazREUEnviarConsultoras", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazREUMatrizCampanya
	 * (java.util.Map)
	 */
	public List getInterfazREUMatrizCampanya(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazREUMatrizCampanya", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazMyEbelCronogramaFacturacion
	 * (java.util.Map)
	 */
	public List getInterfazREUPeriodosFacturacion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazREUPeriodosFacturacion", params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazReutilizacionDocumentosAnulados(java.util.Map)
	 */
	public List getInterfazReutilizacionDocumentosAnulados(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazReutilizacionDocumentosAnulados",
						params);
	}

	/**
	 * @see biz.belcorp.ssicc.dao.ReporteDAO#getInterfazReutilizacionMatrizCampanya(java.util.Map)
	 * 
	 * @deprecated
	 */
	public List getInterfazReutilizacionMatrizCampanya(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazReutilizacionMatrizCampanya",
				params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazSABEnviarFuenteVentasPrevista(java.util.Map)
	 */
	public List getInterfazSABEnviarFuenteVentasPrevista(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSABEnviarFuenteVentasPrevista",
				params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazSABEnviarIncentivosConsultoras(java.util.Map)
	 */
	public List getInterfazSABEnviarIncentivosConsultoras(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSABEnviarIncentivosConsultoras",
				params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getInterfazSABEnviarRentabilidadPorZona
	 * (java.util.Map)
	 */
	public List getInterfazSABEnviarRentabilidadPorZona(Map params) {
		// Determinamos los Valores a partir del SP: CCC_PR_RENTA_ZONA
		// Cargamos los parámetros de entrada
		// Ejecutamos el SP
		String codigoPeriodoCierre = (String) params.get("periodoCalculado");
		params.put("codigoPeriodoCierre", codigoPeriodoCierre);
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazSABEnviarRentabilidadPorZona",
						params);
		// Leemos los datos procesados
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSABEnviarRentabilidadPorZona",
				params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getInterfazSAMEnviarInicializacionStocks(java.util.Map)
	 */
	public List getInterfazSAMEnviarInicializacionStocks(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSAMEnviarInicializacionStocks",
				params);
	}

	public List getInterfazSATEnviarAFPConsultora(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviarAFPConsultora",
				criteria);
	}

	public List getInterfazSATEnviarAFPEstibados(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviaAFPEst", criteria);
	}

	/**
	 * Interfaz de Control
	 * 
	 * */

	public List getInterfazSATEnviarArchivoControl(Map criteria) {
		if (criteria.get("bandera") != null) {
			return getSqlMapClientTemplate()
					.queryForList(
							"sisicc.InterfazSQL.getInterfazSATEnviarArchivoControlTodo",
							criteria);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"sisicc.InterfazSQL.getInterfazSATEnviarArchivoControlUno",
					criteria);
		}

	}

	/*
	 * Interfaz APE que envia cronograma por zona.
	 */

	public List getInterfazSATEnviarBoletaDespachoA(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviarBoleta", criteria);
	}

	public List getInterfazSATEnviarCostosPorCaja(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviarCostosCaja", criteria);
	}

	public List getInterfazSATEnviarCronogramaZonas(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviarCronogramaZonas",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazSATEnviarHojaPicking
	 * (java.util.Map)
	 */
	public List getInterfazSATEnviarHojaPicking(Map criteria) {
		List formato = null;
		formato = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getFormatoFecha", criteria);
		for (int i = 0; i < formato.size(); i++) {
			System.out.println("LISTA "
					+ formato.get(i).toString().substring(13));
		}
		criteria.put("formatoFecha", formato.get(0).toString().substring(13));
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviarHojaPicking", criteria);
	}

	public List getInterfazSATEnviarVolumenesValorizacionPorSeccionDia(
			Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSATEnviaVolumenValorizacion",
				criteria);
	}

	public List getInterfazSICDuplaCyZoneInscritasList(Map params) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getInterfazSICEnviarClasificacionConsultorasNuevas(java.util.Map)
	 */
	public List getInterfazSICEnviarClasificacionConsultorasNuevas(Map params) {
		if (log.isDebugEnabled()) {
			log.debug("Entering method 'getInterfazSICEnviarClasificacionConsultorasNuevas'");
		}
		log.debug("params=" + params);

		List listClasificacionConsultorasNuevas = new ArrayList();

		// Obtenemos los clientes nuevos a actualizar
		List listNuevo = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazSICEnviarClasificacionConsultorasNuevasNuevo",
						params);
		Iterator iterator = listNuevo.iterator();
		// Agregamos los clientes nuevos a la lista
		while (iterator.hasNext()) {
			Map row = new HashMap();
			row.put("periodo", params.get("periodo"));
			row.put("codigoPais", params.get("codigoPais"));
			row.put("codigoCliente", iterator.next());
			row.put("codigoTipoClasificacion",
					params.get("codigoTipoClasificacionNuevo"));
			listClasificacionConsultorasNuevas.add(row);
		}

		// Obtenemos los clientes antiguos a actualizar
		List listAntiguo = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazSICEnviarClasificacionConsultorasNuevasAntiguo",
						params);
		iterator = listAntiguo.iterator();
		// Agregamos los clientes antiguos a la lista
		while (iterator.hasNext()) {
			Map row = new HashMap();
			row.put("periodo", params.get("periodo"));
			row.put("codigoPais", params.get("codigoPais"));
			row.put("codigoCliente", iterator.next());
			row.put("codigoTipoClasificacion",
					params.get("codigoTipoClasificacionAntiguo"));
			listClasificacionConsultorasNuevas.add(row);
		}
		// Devuelve la lista con los clientes nuevos y antiguos a actualizar
		return listClasificacionConsultorasNuevas;
	}

	public List getInterfazSICInscritasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSICInscritasList", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getInterfazPRITiposClasificacion
	 * (java.util.Map)
	 */
	public String getInterfazSICTipoClasificacion(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getInterfazSICTipoClasificacion", params);
	}

	public List getInterfazSICVentaBaseConsultorasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSICVentaBaseConsultorasList",
				criteria);
	}

	public List getInterfazSICVentasPuntajesXCampanyaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazSICVentasPuntajesXCampanyaList",
				criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getIntervalosFechaComplementoFacturasVentaDirecta(java.util.Map)
	 */
	public Base getIntervalosFechaComplementoFacturasVentaDirecta(Map params) {
		return (Base) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.GenericoSQL.getIntervalosFechasPeriodoByPaisMarcaCanal",
						params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getIntervalosFechaFacturasVentaDirecta
	 * (java.util.Map)
	 */
	public Base getIntervalosFechaFacturasVentaDirecta(Map params) {
		return (Base) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.GenericoSQL.getIntervalosFechasPeriodoByPaisMarcaCanal",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getLinea(java.util.Map)
	 */
	public List getLinea(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getLinea", params);
	}

	public List getLista(String tipoLista, Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL." + tipoLista, params);

	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getListaComisionesByPeriodo()
	 */
	public List getListaComisionesByPeriodo(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaComisionesByPeriodo", params);
	}

	public List getListaEstimados(Map params) {
		return null;
	}

	public EstimadoProductos getListaEstimadosProducto(Map datosJSP,
			EstimadoProductos cabecera) {
		String lineaAFP = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.consultaLineaAFP", datosJSP);
		EstimadoProductos lista = null;
		List mapa = null;
		Integer listaOIDEstimado = (Integer) getSqlMapClientTemplate()
				.queryForObject("sisicc.InterfazSQL.getOIDEstimado", "");
		String actualiza = datosJSP.get("actualiza").toString();
		String conM = datosJSP.get("conM").toString();
		datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
		if (lineaAFP.compareTo("TRUE") == 0) {
			// Si es AFP
			if ((actualiza.compareTo("on") == 0) && (conM.compareTo("on") == 0)) {

				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				HashMap listaTodo = new HashMap();
				Integer listaOidPeriodo = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDPeriodo",
								datosJSP.get("periodo"));
				Integer listaOidLinea = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDLinea",
								datosJSP.get("linea"));

				cabecera.setPeriodo(listaOidPeriodo.toString());
				cabecera.setLinea(listaOidLinea.intValue());
				listaTodo.put("periodo", datosJSP.get("periodo").toString());
				listaTodo.put("linea", datosJSP.get("linea").toString());
				listaTodo.put("codigoSAPI", cabecera.getCodigoSAP());
				mapa = getSqlMapClientTemplate().queryForList(
						"sisicc.InterfazSQL.seleccionListaTodos", listaTodo);
				getSqlMapClientTemplate().update(
						"sisicc.InterfazSQL.updateEstimadoProductos", cabecera);

				if (mapa.size() == 0) {
					EstimadoProductos oidProduct = (EstimadoProductos) getSqlMapClientTemplate()
							.queryForObject(
									"sisicc.InterfazSQL.seleccionListaProductosAFP",
									datosJSP.get("codigoSAP"));
					if (oidProduct != null) {

						Integer listaOIDProductos = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDProducto",
										datosJSP.get("codigoSAP"));
						listaOidPeriodo = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDPeriodo",
										datosJSP.get("periodo"));
						listaOidLinea = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDLinea",
										datosJSP.get("linea"));

						cabecera.setPeriodo(listaOidPeriodo.toString());
						cabecera.setLinea(listaOidLinea.intValue());
						cabecera.setOidProducto(listaOIDProductos.intValue());
						cabecera.setProcedencia(1);
						cabecera.setCodigoEstimado(listaOIDEstimado.intValue() + 1);
						getSqlMapClientTemplate().insert(
								"sisicc.InterfazSQL.insertEstimadoProductos",
								cabecera);
					}

				}
			} else if ((actualiza.compareTo("on") == 0)
					&& (conM.compareTo("off") == 0)) {

				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				HashMap listaTodo = new HashMap();
				Integer listaOidPeriodo = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDPeriodo",
								datosJSP.get("periodo"));
				Integer listaOidLinea = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDLinea",
								datosJSP.get("linea"));

				cabecera.setPeriodo(listaOidPeriodo.toString());
				cabecera.setLinea(listaOidLinea.intValue());
				listaTodo.put("periodo", datosJSP.get("periodo").toString());
				listaTodo.put("linea", datosJSP.get("linea").toString());
				listaTodo.put("codigoSAPI", cabecera.getCodigoSAP());
				mapa = getSqlMapClientTemplate()
						.queryForList(
								"sisicc.InterfazSQL.seleccionListaTodosSinM",
								listaTodo);
				EstimadoProductos oidProduct = (EstimadoProductos) getSqlMapClientTemplate()
						.queryForObject(
								"sisicc.InterfazSQL.seleccionListaProductosAFP",
								datosJSP.get("codigoSAP"));

				if (mapa.size() == 0) {
					if (oidProduct != null) {
						Integer listaOIDProductos = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDProducto",
										datosJSP.get("codigoSAP"));
						listaOidPeriodo = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDPeriodo",
										datosJSP.get("periodo"));
						listaOidLinea = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDLinea",
										datosJSP.get("linea"));

						cabecera.setPeriodo(listaOidPeriodo.toString());
						cabecera.setLinea(listaOidLinea.intValue());
						cabecera.setOidProducto(listaOIDProductos.intValue());
						cabecera.setProcedencia(1);
						cabecera.setCodigoEstimado(listaOIDEstimado.intValue() + 1);
						getSqlMapClientTemplate().insert(
								"sisicc.InterfazSQL.insertEstimadoProductos",
								cabecera);
					}

				}

				else if (((EstimadoProductos) mapa.get(0)).getProcedencia() != 2) {
					getSqlMapClientTemplate().update(
							"sisicc.InterfazSQL.updateEstimadoProductos",
							cabecera);
				}

			} else {
				getSqlMapClientTemplate().insert(
						"sisicc.InterfazSQL.insertEstimadoProductos", cabecera);
			}
		} else {
			// Linea NO AFP
			if ((actualiza.compareTo("on") == 0) && (conM.compareTo("on") == 0)) {
				Integer listaOidPeriodo = null;
				Integer listaOidLinea = null;
				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				HashMap listaTodo = new HashMap();
				listaOidPeriodo = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDPeriodo",
								datosJSP.get("periodo"));
				listaOidLinea = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDLinea",
								datosJSP.get("linea"));

				cabecera.setPeriodo(listaOidPeriodo.toString());
				cabecera.setLinea(listaOidLinea.intValue());
				listaTodo.put("periodo", datosJSP.get("periodo").toString());
				listaTodo.put("linea", datosJSP.get("linea").toString());
				listaTodo.put("codigoSAPI", cabecera.getCodigoSAP());
				mapa = getSqlMapClientTemplate().queryForList(
						"sisicc.InterfazSQL.seleccionListaTodos", listaTodo);

				if (mapa.size() == 0) {
					Integer listaOIDProductos = (Integer) getSqlMapClientTemplate()
							.queryForObject(
									"sisicc.InterfazSQL.getOIDProducto",
									datosJSP.get("codigoSAP"));
					listaOidPeriodo = (Integer) getSqlMapClientTemplate()
							.queryForObject("sisicc.InterfazSQL.getOIDPeriodo",
									datosJSP.get("periodo"));
					listaOidLinea = (Integer) getSqlMapClientTemplate()
							.queryForObject("sisicc.InterfazSQL.getOIDLinea",
									datosJSP.get("linea"));

					cabecera.setPeriodo(listaOidPeriodo.toString());
					cabecera.setLinea(listaOidLinea.intValue());
					cabecera.setOidProducto(listaOIDProductos.intValue());
					cabecera.setProcedencia(1);
					cabecera.setCodigoEstimado(listaOIDEstimado.intValue() + 1);
					getSqlMapClientTemplate().insert(
							"sisicc.InterfazSQL.insertEstimadoProductos",
							cabecera);
				} else {
					getSqlMapClientTemplate().update(
							"sisicc.InterfazSQL.updateEstimadoProductos",
							cabecera);
				}

			} else if ((actualiza.compareTo("on") == 0)
					&& (conM.compareTo("off") == 0)) {

				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				datosJSP.put("codigoSAP", cabecera.getCodigoSAP());
				HashMap listaTodo = new HashMap();
				Integer listaOidPeriodo = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDPeriodo",
								datosJSP.get("periodo"));
				Integer listaOidLinea = (Integer) getSqlMapClientTemplate()
						.queryForObject("sisicc.InterfazSQL.getOIDLinea",
								datosJSP.get("linea"));

				cabecera.setPeriodo(listaOidPeriodo.toString());
				cabecera.setLinea(listaOidLinea.intValue());
				listaTodo.put("periodo", datosJSP.get("periodo").toString());
				listaTodo.put("linea", datosJSP.get("linea").toString());
				listaTodo.put("codigoSAPI", cabecera.getCodigoSAP());
				mapa = getSqlMapClientTemplate().queryForList(
						"sisicc.InterfazSQL.seleccionListaTodos", listaTodo);

				EstimadoProductos oidProduct = (EstimadoProductos) getSqlMapClientTemplate()
						.queryForObject(
								"sisicc.InterfazSQL.seleccionListaProductosAFP",
								datosJSP.get("codigoSAP"));

				if (mapa.size() == 0) {
					if (oidProduct != null) {
						Integer listaOIDProductos = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDProducto",
										datosJSP.get("codigoSAP"));
						listaOidPeriodo = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDPeriodo",
										datosJSP.get("periodo"));
						listaOidLinea = (Integer) getSqlMapClientTemplate()
								.queryForObject(
										"sisicc.InterfazSQL.getOIDLinea",
										datosJSP.get("linea"));

						cabecera.setPeriodo(listaOidPeriodo.toString());
						cabecera.setLinea(listaOidLinea.intValue());
						cabecera.setOidProducto(listaOIDProductos.intValue());
						cabecera.setProcedencia(1);
						cabecera.setCodigoEstimado(listaOIDEstimado.intValue() + 1);
						getSqlMapClientTemplate().insert(
								"sisicc.InterfazSQL.insertEstimadoProductos",
								cabecera);
					}
				} else if (((EstimadoProductos) mapa.get(0)).getProcedencia() != 2) {
					getSqlMapClientTemplate().update(
							"sisicc.InterfazSQL.updateEstimadoProductos",
							cabecera);
				}

			} else {

				getSqlMapClientTemplate().insert(
						"sisicc.InterfazSQL.insertEstimadoProductos", cabecera);
			}
		}
		return lista;
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getListaPeriodosByPaisCanal()
	 */
	public List getListaPeriodosByBasCtrlFact(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaPeriodosByBasCtrlFact", params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getListaPeriodosByPaisCanal()
	 */
	public List getListaPeriodosByPaisCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisCanal", params);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getListaPeriodosInicial()
	 */
	public List getListaPeriodosInicial(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaPeriodosInicial", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getListaProcesosCierreFacturacion(java.util.Map)
	 */
	public List getListaProcesosCierreFacturacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getListaProcesosCierreFacturacion",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getListaProcesosGenCierreCampania(java.util.Map)
	 */
	public List getListaProcesosGenCierreCampania(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getListaProcesosGenCierreCampania",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getListaProcesosIncentivos
	 * (java.util.Map)
	 */
	public List getListaProcesosIncentivos(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getListaProcesosIncentivos", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getListaProcesosLet(java
	 * .util.Map)
	 */
	public List getListaProcesosLet(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getListaProcesosLet", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getListCodComision(java.
	 * lang.String)
	 */
	public List getListCodComision(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListCodComision", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getMapaByCentro(java.util
	 * .Map)
	 */
	public List getMapaByCentro(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getMapaByCentroPais", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getMapaZonasByCentro(java
	 * .util.Map)
	 */
	public List getMapaZonasByCentro(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getMapaZonasByCentro", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getMarcaByCodigo(java.lang
	 * .String)
	 */
	public Base getMarcaByCodigo(String codigoMarca) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getMarcasById", codigoMarca);
		return base;
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getMarcas()
	 */
	public List getMarcas() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getMarcas", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getMotivosNoRecojoBoletasRecojo
	 * (java.lang.String)
	 */
	public List getMotivosNoRecojoBoletasRecojo(String codigoPais) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.reclamos.ReclamosSQL.getMotivosNoRecojoBoletasRecojo",
				codigoPais);
	}

	public String getNombreArchivoIVR(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getNombreArchivoIVR", params);
	}

	public List getNumeroLoteByFact(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getNumeroLoteByFact", criteria);
	}

	public String getNumeroLoteIntHistoLotes(Map params, String interFaz) {
		/* Se coloca un map por si mas adelante se requiera de mas parametros */
		params.put("prefijoInterfaz", interFaz);
		String valor = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getNumeroLoteIntHistoLotes", params);
		return valor;
	}

	public Integer getNumeroRegionesCerradas(Map params) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getNumeroRegionesCerradas", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getNumeroVersionesFaltanteDia
	 * (java.util.Map)
	 */
	public List getNumeroVersionesFaltanteDia(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPRYSQL.getNumeroVersionesFaltanteDia", params);
	}

	public String getNumLoteSTO(Map params) {
		String numeroLoteSTO;
		numeroLoteSTO = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getNumLoteSTO", params);
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.updateNumLote",
				params);

		return numeroLoteSTO;
	}

	public String getOidGenerico(String funcion, Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL." + funcion, params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getOidPaisInterfazLARRecepcionarActualizaResultadoChequeo
	 * (java.lang.String)
	 */
	public Integer getOidPaisInterfazLARRecepcionarActualizaResultadoChequeo(
			String codigoPais) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.InterfazSQL.getOidPaisInterfazLARRecepcionarActualizaResultadoChequeo",
						codigoPais);
	}

	public String getOidString(String string, Map mapQueryParams) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL." + string, mapQueryParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getOperacionesByCodigoPais
	 * (java.util.Map)
	 */
	public List getOperacionesByCodigoPais(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getOperacionesByCodigoPais", criteria);
	}

	public String getPercepcionesConsolidadoCorrelativoSiguiente() {
		String correlativo = (String) getSqlMapClientTemplate()
				.queryForObject(
						"sisicc.InterfazSQL.getPercepcionesConsolidadoCorrelativoSiguiente",
						null);
		return correlativo;
	}

	public String getPeriodoDefaultByPaisCanal(Map params) {
		String defecto = "";
		Base base = new Base();
		List aux = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodoDefaultByPaisCanal", params);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}

	public String getPeriodoDefaultByPaisMarcaCanal(Map params) {
		String defecto = "";
		Base base = new Base();
		List aux = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodoDefaultByPaisMarcaCanal", params);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}

	public String getPeriodoDefaultByPaisMarcaCanalAcceso(Map params) {
		String defecto = "";
		Base base = new Base();
		List aux = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodoDefaultByPaisMarcaCanalAcceso",
				params);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodoFechaCampanyaActiva
	 * ()
	 */
	public Map getPeriodoFechaCampanyaActiva() {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getPeriodoFechaCampanyaActiva", null);
	}

	public Map getPeriodoFechaCampanyaActivaSF(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getPeriodoFechaCampanyaActivaSF", params);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getCargoBaseById
	 * ()
	 */
	public Map getCargoBaseById(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getCargoBaseById", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodoFechaProcesoActual
	 * (java.util.Map)
	 */
	public List getPeriodoFechaProcesoActual(Map criteria) {
		log.info("Entro a InterfazSiCCDAOiBatis - getPeriodoFechaProcesoActual(Map)");
		List lista = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getPeriodoFechaProcesoActual", criteria);
		log.info("Salio a InterfazSiCCDAOiBatis - getPeriodoFechaProcesoActual(Map) - Resultado:"
				+ lista.size());
		return lista;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodoFechaProceso(java.util.Map)
	 */
	public List getPeriodoFechaProceso(Map criteria) {
		List lista = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getPeriodoFechaProceso", criteria);
		return lista;
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodoFin()
	 */
	public String getPeriodoFin(String codigoRango) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getPeriodoFin", codigoRango);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodoInicio()
	 */
	public String getPeriodoInicio(String codigoRango) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getPeriodoInicio", codigoRango);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodosByPaisMarcaCanal
	 * (java.util.Map)
	 */
	public List getPeriodosByPaisMarcaCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanal", params);
	}

	public List getPeriodosByPaisMarcaCanalAcceso(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanalAcceso", params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getPeriodosByPaisMarcaCanalOrderFechaFin(java.util.Map)
	 */
	public List getPeriodosByPaisMarcaCanalOrderFechaFin(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanalOrderFechaFin",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getPeriodosByPaisMarcaCanalRangos(java.util.Map)
	 */
	public List getPeriodosByPaisMarcaCanalRangos(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanalRangos", params);
	}

	public List getPeriodosByPMC(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodosByTipo(java.lang.String)
	 */
	public List getPeriodosByTipo(String tipo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getPeriodosByTipo", tipo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodosByTipoPMC(java.lang.
	 * String)
	 */
	public List getPeriodosByTipoPMC(String tipo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByTipoPMC", tipo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodosByTipoPMCA(java.lang
	 * .String)
	 */
	public List getPeriodosByTipoPMCA(String tipo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByTipoPMCA", tipo);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getPeriodosComplementoFacturasVentaDirecta(java.util.Map)
	 */
	public List getPeriodosComplementoFacturasVentaDirecta(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodosDefaultByPMC(
	 * java.util.Map)
	 */
	public List getPeriodosDefaultByPMC(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodoDefaultByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodosFacturasVentaDirecta
	 * (java.util.Map)
	 */
	public List getPeriodosFacturasVentaDirecta(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getPeriodosSABRentabilidadPorZona
	 * (java.util.Map)
	 */
	public List getPeriodosSABRentabilidadPorZona(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosByPaisMarcaCanal", params);
	}

	public List getPlantillasConcursos() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPlantillasConcursos", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getProcesoBatchByInterfaz
	 * (java.util.Map)
	 */
	public List getProcesoBatchByInterfaz(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchByInterfaz",
				criteria);
	}

	public List getProductosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getProductosByCriteria", criteria);
	}

	/* Valida si existe Proveedor */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getProveedorCount(java.util
	 * .Map)
	 */
	public Integer getProveedorCount(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getProveedorCount", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRangoPeriodoByCodigo(
	 * java.util.Map)
	 */
	public Base getRangoPeriodoByCodigo(String periodo) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getRangoPeriodoById", periodo);
		return base;
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getRangosPeriodo()
	 */
	public List getRangosPeriodo() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRangosPeriodo", null);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getRecaudosBancarios(java.util.Map)
	 */
	public List getRecaudosBancarios(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRecaudosBancarios", params);
	}

	public List getRECConsolidadoUnidadesAlmacenVirtual(Map params) {

		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECConsolidadoUnidadesAlmacenVirtual",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECLoteProductosList(
	 * java.util.Map)
	 */
	public List getRECLoteProductosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECLoteProductosList", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECProductosCabList(java
	 * .util.Map)
	 */
	public List getRECProductosCabList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECProductosCabList", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECProductosDetDetList
	 * (java.util.Map)
	 */
	public List getRECProductosDetDetList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECProductosDetDetList", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECProductosDetList(java
	 * .util.Map)
	 */
	public List getRECProductosDetList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECProductosDetList", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECProductosList(java
	 * .util.Map)
	 */
	public List getRECProductosList(Map criteria) {

		List list = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECProductosList", criteria);
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			HashMap element = (HashMap) iter.next();
			element.put("flag", Constants.ESTADO_ACTIVO);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECProductosListxLote
	 * (java.util.Map)
	 */
	public List getRECProductosListxLote(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECProductosListxLote", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRECProductosSAM7List(
	 * java.util.Map)
	 */
	public List getRECProductosSAM7List(Map criteria) {

		List list = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getRECProductosSAM7List", criteria);
		return list;
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getPeriodosComplementoFacturasVentaDirecta(java.util.Map)
	 */
	public List getRegion() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRegionByCodigo(java.util
	 * .Map)
	 */
	public Base getRegionByCodigo(Map criteria) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getRegionById", criteria);
		return base;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRegionByPaisCanalSubGerencia
	 * (java.util.Map)
	 */
	public List getRegionByPaisCanalSubGerencia(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionByPaisCanalSubGerencia", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getRegionByPaisMarcaCanalSubGerencia(java.util.Map)
	 */
	public List getRegionByPaisMarcaCanalSubGerencia(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionByPaisMarcaCanalSubGerencia",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getRegionesActivasCerradasByPeriodo(java.util.Map)
	 */
	public List getRegionesActivasCerradasByPeriodo(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"spusicc.let.ProcesosLETSQL.getRegionesActivasCerradasByPeriodo",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRegionesByPais(java.util
	 * .Map)
	 */
	public List getRegionesByPais(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesByPais", params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getClientesPendientesActualizarUA(java.util.Map)
	 */
	public List getRegionesByPaisCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesByPaisCanal", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRegionesByPaisLet(java
	 * .util.Map)
	 */
	public List getRegionesByPaisLet(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesByPaisLet", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getRegionesByPaisMarcaCanal(java
	 * .util.Map)
	 */
	public List getRegionesByPaisMarcaCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getRegionesByPeriodoBasCtrlFact
	 * (java.util.Map)
	 */
	public List getRegionesByPeriodoBasCtrlFact(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesByPeriodoBasCtrlFact", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getRegionesByPeriodoIntEviPerioRegio
	 * (java.util.Map)
	 */
	public List getRegionesByPeriodoIntEviPerioRegio(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesByPeriodoIntEviPerioRegio",
				params);
	}

	public List getReporteBaseRecuperacionCampanhasPais(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteBaseRecuperacionCampanhasPais",
				criteria);
	}

	public List getReporteBaseRecuperacionCampanhasRegion(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteBaseRecuperacionCampanhasRegion",
				criteria);
	}

	public List getReporteBaseRecuperacionCampanhasZona(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteBaseRecuperacionCampanhasZona",
				criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getReporteConsultorasRecomendadasPDF(java.util.Map)
	 */
	public List getReporteConsultorasRecomendadasPDF(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteConsultorasRecomendadasPDF",
				criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getReporteConsultorasRecomendadasXLS(java.util.Map)
	 */
	public List getReporteConsultorasRecomendadasXLS(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteConsultorasRecomendadasXLS",
				criteria);
	}

	public List getReporteEVIMicaRecepcionPedidosRegion(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteEVIMicaRecepcionPedidosRegion",
				criteria);
	}

	public List getReporteEVIMicaRecepcionPedidosZona(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReporteEVIMicaRecepcionPedidosZona",
				criteria);
	}

	public List getReportePERAntiguedadDeudasChequearDatos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERAntiguedadDeudasChequearDatos",
						criteria);
	}

	public List getReportePERAntiguedadDeudasMesesActual(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReportePERAntiguedadDeudasXMesesActual",
				criteria);
	}

	public List getReportePERAntiguedadDeudasMesesHaceDosAnhos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERAntiguedadDeudasXMesesHaceDosAnhos",
						criteria);
	}

	public List getReportePERAntiguedadDeudasMesesHaceUnAnho(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERAntiguedadDeudasXMesesHaceUnAnho",
						criteria);
	}

	public List getReportePERAntiguedadDeudasPeriodosActual(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERAntiguedadDeudasXPeriodoActual",
						criteria);
	}

	public List getReportePERAntiguedadDeudasPeriodosHaceDosAnhos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERAntiguedadDeudasXPeriodoHaceDosAnhos",
						criteria);
	}

	public List getReportePERAntiguedadDeudasPeriodosHaceUnAnho(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERAntiguedadDeudasXPeriodoHaceUnAnho",
						criteria);
	}

	/*
	 * Interfaz SAT -Enviar Boleta de Despacho
	 */

	public List getReportePERFacturasPendientesSeccion(Map criteria) {
		log.debug("InterfazSiCCDAOiBatis.getReportePERFacturasPendientesSeccion");
		List lista = null;
		try {
			log.debug("En el try");
			int val = getSqlMapClientTemplate().update(
					"sisicc.ReportesSQL.generaFacturasPendientesSeccion",
					criteria);
			log.debug("Mostrando resultado " + val);
			HashMap map = new HashMap();
			map.put("SYSDATE", new java.util.Date());
			lista.add(map);
		} catch (Exception e) {
			log.debug("En el catch " + e.getMessage());
		}
		return lista;
	}

	/*
	 * Interfaz SAT -Enviar Boleta de Despacho
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getReportePERFacturasPendientesSeccionDetalle(java.util.Map)
	 */
	public List getReportePERFacturasPendientesSeccionDetalle(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERFacturasPendientesSeccionDetalle",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getReportePERFacturasPendientesSeccionPedidosRegion(java.util.Map)
	 */
	public List getReportePERFacturasPendientesSeccionPedidosRegion(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERFacturasPendientesSeccionPedidosRegion",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getReportePERFacturasPendientesSeccionPedidosZona(java.util.Map)
	 */
	public List getReportePERFacturasPendientesSeccionPedidosZona(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERFacturasPendientesSeccionPedidosZona",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getReportePERFacturasPendientesSeccionResumen(java.util.Map)
	 */
	public List getReportePERFacturasPendientesSeccionResumen(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERFacturasPendientesSeccionResumen",
						criteria);
	}

	public List getReportePERLibroPercepciones(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReportePERLibroPercepciones", criteria);
	}

	public List getReportePERLiquidacionCobranza(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERLiquidacionCobranza",
						criteria);
	}

	public List getReportePERListaAbonosDirectos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERListaAbonosDirectos",
						criteria);
	}

	public List getReportePERListaAbonosDirectosPercepciones(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERListaAbonosDirectosPercepciones",
						criteria);
	}

	public List getReportePERListaAbonosPorCobranza(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReportePERListaAbonosPorCobranza",
				criteria);
	}

	public List getReportePERListaAbonosPorNotaCredito(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReportePERListaAbonosPorNotaCredito",
				criteria);
	}

	public List getReportePERListaCargosDirectos(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERListaCargosDirectos",
						criteria);
	}

	public List getReportePERListaCargosFacturacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReportePERListaCargosFacturacion",
				criteria);
	}

	public List getReportePERListaControlCliente(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ReportesSQL.getReportePERListaControlCliente",
						criteria);
	}

	public List getReportePERListaGenerica(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getReportePERListaGenerica", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getResultadosBoletasRecojo
	 * (java.lang.String)
	 */
	public List getResultadosBoletasRecojo(String codigoPais) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.reclamos.ReclamosSQL.getResultadosBoletasRecojo",
				codigoPais);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getSaldoCtaCteCliente(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Double getSaldoCtaCteCliente(String codigoPais,
			String codigoCliente, String fechaDocumentoDesde,
			String fechaDocumentoHasta, String fechaVencimientoDesde,
			String fechaVencimientoHasta) {
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoCliente", codigoCliente);
		params.put("fechaDocumentoDesde", fechaDocumentoDesde);
		params.put("fechaDocumentoHasta", fechaDocumentoHasta);
		params.put("fechaVencimientoDesde", fechaVencimientoDesde);
		params.put("fechaVencimientoHasta", fechaVencimientoHasta);

		return (Double) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getSaldoCtaCteCliente", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getSeccionesByPaisMarcaCanalRegionZona(java.util.Map)
	 */
	public List getSeccionesByPaisMarcaCanalRegionZona(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSeccionesByPaisMarcaCanalRegionZona",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getSociedadByCodigo(java
	 * .util.Map)
	 */
	public Base getSociedadByCodigo(Map criteria) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getSociedadesById", criteria);
		return base;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#getSociedadEquivalenciaSAP
	 * (java.util.Map)
	 */
	public String getSociedadEquivalenciaSAP(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazCOBSQL.getSociedadEquivalenciaSAP", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getSociedadesByCodigoPais(java.
	 * lang.String)
	 */
	public List getSociedadesByCodigoPais(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSociedadesByCodigoPais", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getSubaccesoByAcceso(java
	 * .util.Map)
	 */
	public List getSubaccesoByAcceso(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubaccesoByAcceso", params);
	}

	public Base getSubaccesosByCodigo(Map criteria) {
		return (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getSubaccesosByCodigo", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getSubaccesosByCodigoISO(java.lang
	 * .String)
	 */
	public List getSubaccesosByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubaccesosByCodigoISO", codigo);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getSubaccesosByCriteria(java.util
	 * .Map)
	 */
	public List getSubaccesosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubaccesosByCriteria", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getSubGerenciaByPaisMarcaCanal(
	 * java.util.Map)
	 */
	public List getSubGerenciaByPaisMarcaCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubGerenciaByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getSubGerenciasByPaisMarcaCanal
	 * (java.util.Map)
	 */
	public List getSubGerenciasByPaisMarcaCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubGerenciasByPaisMarcaCanal", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getSubGerenciaxPaisMarcaCanal
	 * (java.util.Map)
	 */
	public List getSubGerenciaxPaisMarcaCanal(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubGerenciaxPaisMarcaCanal", params);
	}

	public List getSublineaByLinea(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSublineaByLinea", params);
	}

	public List getSubProcesoByProceso(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubProcesoByProceso", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getSubTiposClientesByCriteria(java
	 * .lang.String)
	 */
	public List getSubTiposClientesByCriteria(Map criteria) {
		List listSubTiposCliente = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubTiposClientesByCriteria", criteria);
		return listSubTiposCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getSubTiposClientesMultipleByCriteria(java.util.Map)
	 */
	public List getSubTiposClientesMultipleByCriteria(Map criteria) {
		List listSubTiposCliente = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubTiposClientesMultipleByCriteria",
				criteria);
		return listSubTiposCliente;
	}

	public List getSubTiposClientesMultipleByCriteriaOID(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubTiposClientesMultipleByCriteriaOID",
				criteria);
	}
	
	public List getSubTiposClientesMultipleByCriteriaCodigo(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getSubTiposClientesMultipleByCriteriaCodigo",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getSeccionesByPaisMarcaCanalRegionZona(java.util.Map)
	 */
	public List getTerritoriosByPaisMarcaCanalRegionZonaSeccion(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.GenericoSQL.getTerritoriosByPaisMarcaCanalRegionZonaSeccion",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getTerritoriosByPeriodoRegioZona
	 * (java.util.Map)
	 */
	public List getTerritoriosByPeriodoRegioZona(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTerritoriosByPeriodoRegioZona", params);
	}

	public List getTipoComprobantesPago() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoComprobantesPago", null);
	}

	public List getTipoDocumentosByCodigoISO(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoDocumentosByCodigoISO", criteria);
	}

	public List getTipoOrigenDatos() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoOrigenDatos", null);
	}

	public List getTipoOrigenDatos2(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoOrigenDatosDescripcion", criteria);
	}

	public List getTipoOrigenDatosRecaudosBancarios() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoOrigenDatosRecaudosBancarios", null);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getTiposCierres(java.lang.String)
	 */
	public List getTiposCierres() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposCierres", null);
	}

	public List getTiposClasificacionesByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposClasificacionesByCriteria",
				criteria);
	}

	public List getTiposClasificacionesByCriteriaMultiple(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposClasificacionesByCriteriaMultiple",
				criteria);
	}

	public List getTiposClasificacionesByCriteriaMultipleOID(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.GenericoSQL.getTiposClasificacionesByCriteriaMultipleOID",
						criteria);
	}
	
	public List getTiposClasificacionesByCriteriaMultipleCodigo(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.GenericoSQL.getTiposClasificacionesByCriteriaMultipleCodigo",
						criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.dao.InterfazSiCCDAO#getTiposClientesByCodigoISO(java
	 * .lang.String)
	 */
	public List getTiposClientesByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposClientesByCodigoISO", codigo);
	}

	public List getTiposClientesByCodigoISOOID(String codigoISO) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposClientesByCodigoISOOID", codigoISO);
	}

	public List getTiposIngresoByCodigoISO(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposIngresoByCodigoISO", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getTiposInterfazSMS()
	 */
	public List getTiposInterfazSMS() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposInterfazSMS", null);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#
	 * getPeriodosComplementoFacturasVentaDirecta(java.util.Map)
	 */
	public List getTipoSol(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoSol", params);
	}

	public List getTiposRecepcionMICAyOCS() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposRecepcionMICAyOCS", null);
	}

	public List getTiposSolicitudPais(String codigoISO) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposSolicitudPais", codigoISO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getTiposTransaccionesByCodigoPais(java.lang.String)
	 */
	public List getTiposTransaccionesByCodigoPais(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposTransaccionesByCodigoPais", codigo);
	}

	public List getTiposVinculosByPais(String codigoPais) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposVinculosByPais", codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getValidaExistenciaProductos
	 * (java.util.Map)
	 */
	public List getValidaExistenciaProductos(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getValidaExistenciaProductos", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPais(java.util
	 * .Map)
	 */
	public List getVersionByMapa(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getVersionByMapa", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPais(java.util
	 * .Map)
	 */
	public List getVersionByMapaAnt(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getVersionByMapaAnt", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getVersionSinP(java.util
	 * .Map)
	 */
	public List getVersionSinP(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getVersionSinP", params);
	}

	public List getWebServiceLAREnvioSecuenciacionzonasList() {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getWebServiceLAREnvioSecuenciacionzonasList");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonaByCodigo(java.util
	 * .Map)
	 */
	public Base getZonaByCodigo(Map criteria) {
		Base base = (Base) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getZonaById", criteria);
		return base;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonaByPaisZona(java.util
	 * .Map)
	 */
	public List getZonaByPaisZona(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonaByPaisZona", params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPais(java.util
	 * .Map)
	 */
	public List getZonasByPais(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPais", params);
	}

	public List getZonasByPaisActivasNoActivas(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPaisActivasNoActivas", params);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPaisCanalRegion
	 * (java.util.Map)
	 */
	public List getZonasByPaisCanalRegion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPaisCanalRegion", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPaisCanalRegion
	 * (java.util.Map)
	 */
	public List getZonasByPaisMarcaCanalRegion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPaisMarcaCanalRegion", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPaisRegionLet(
	 * java.util.Map)
	 */
	public List getZonasByPaisRegionLet(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPaisRegionLet", criteria);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getZonasByPaisCanalRegion
	 * (java.util.Map)
	 */
	public List getZonasByPeriodoBasCtrlFact(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPeriodoBasCtrlFact", params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getZonasByPeriodoIntEviPerioRegioZona(java.util.Map)
	 */
	public List getZonasByPeriodoIntEviPerioRegioZona(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getZonasByPeriodoIntEviPerioRegioZona",
				params);
	}

	public void inserParamInterfaz(Map params, String string,
			String codigoParametro, boolean valorString) {
		Map queryParams = new HashMap();
		String[] tempString = (String[]) params.get(string);
		List temp = tempString == null ? new ArrayList() : Arrays
				.asList(tempString);
		String codigoInterfaz = params.get("codigoInterfaz") != null ? params
				.get("codigoInterfaz").toString()
				: Constants.NO_CODIGO_INTERFAZ;
		queryParams.put("codigoInterfaz", codigoInterfaz);

		for (int i = 0; i < temp.size(); i++) {
			queryParams.put("codigoParametro", codigoParametro);
			if (valorString) {
				queryParams.put("parametroString", temp.get(i));
				queryParams.put("parametroNumerico", null);
			} else {
				queryParams.put("parametroNumerico", temp.get(i));
				queryParams.put("parametroString", null);
			}

			getSqlMapClientTemplate().insert(
					"sisicc.InterfazSQL.insertInterGttParametros", queryParams);
		}

	}

	public void inserParamTmpInterfaz(Map params, String string,
			String codigoParametro, boolean valorString) {
		Map queryParams = new HashMap();
		String[] tempString = (String[]) params.get(string);
		List temp = tempString == null ? new ArrayList() : Arrays
				.asList(tempString);
		String codigoInterfaz = params.get("codigoInterfaz") != null ? params
				.get("codigoInterfaz").toString()
				: Constants.NO_CODIGO_INTERFAZ;
		queryParams.put("codigoInterfaz", codigoInterfaz);

		for (int i = 0; i < temp.size(); i++) {
			queryParams.put("codigoParametro", codigoParametro);
			if (valorString) {
				queryParams.put("parametroString", temp.get(i));
				queryParams.put("parametroNumerico", null);
			} else {
				queryParams.put("parametroNumerico", temp.get(i));
				queryParams.put("parametroString", null);
			}

			getSqlMapClientTemplate().insert(
					"sisicc.InterfazSQL.insertInterTmpParametros", queryParams);
		}

	}

	public void insertarInterfazRETRecepcionarCOM1(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertarInterfazRETRecepcionarCOM1",
						params);
	}

	public void insertarInterfazRETRecepcionatCOM2(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertarInterfazRETRecepcionatCOM2",
						params);
	}

	public void insertarInterfazRETRecepcionatCOM3(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertarInterfazRETRecepcionatCOM3",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * insertInterfazLLIRecepcionarActualizaEstimadosYobel(java.util.Map)
	 */
	public void insertInterfazLLIRecepcionarActualizaEstimadosYobel(Map map) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazLLIRecepcionarActualizaEstimadosYobel",
						map);
	}

	public void insertInterfazOCRConsolidadoOCSCabecera(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazOCRConsolidadoOCSCabecera",
				params);
	}

	public void insertInterfazOCRConsolidadoOCSDetalle(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazOCRConsolidadoOCSDetalle",
				params);
	}

	public void insertInterfazPERActualizarPercepcionesConsolidado(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazPERActualizarPercepcionesConsolidado",
						consolidado);
	}

	public void insertInterfazPRIRecepcionarCalificacion(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazPRIRecepcionarCalificacion",
				params);
	}

	public void insertInterfazPRIRecepcionarClientes(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazPRIRecepcionarClientes",
				params);
	}

	public void insertInterfazRECEnviarTransferenciaBoletasRecojo(Map map) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRECEnviarTransferenciaBoletasRecojo",
						map);
	}

	public void insertInterfazRECEnviarTransferenciaBoletasRecojoAnulacion(
			Map map) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRECEnviarTransferenciaBoletasRecojoAnulacion",
						map);

	}

	public void insertInterfazRECRecepcionarBoletasRecojoCabecera(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRECRecepcionarBoletasRecojoCabecera",
						params);

	}

	public void insertInterfazRECRecepcionarBoletasRecojoControl(Map queryParams) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRECRecepcionarBoletasRecojoControl",
						queryParams);
	}

	public void insertInterfazRECRecepcionarBoletasRecojoDetalle(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRECRecepcionarBoletasRecojoDetalle",
						params);

	}

	public void insertInterfazRETRecepcionarVentasRetailCab(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRETRecepcionarVentasRetailCab",
						params);
	}

	public void insertInterfazRETRecepcionarVentasRetailDet(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRETRecepcionarVentasRetailDet",
						params);
	}

	public void insertInterfazRETRecepcionarVentasRetailDetDet(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertInterfazRETRecepcionarVentasRetailDetDet",
						params);
	}

	/**
	 * ************************************ PERCEPCIONES
	 * ***************************************
	 */

	public void insertPercepcionesCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertMovimientosBancariosCabecera",
				cabecera);
	}

	public void insertPercepcionesDetalle(MovimientosBancariosDetalle detalle,
			Usuario usuario) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertMovimientosBancariosDetalle",
						detalle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertRECHistoricoEnvios
	 * (java.util.Map)
	 */
	public void insertRECHistoricoEnvios(Map criteria) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertRecHistoricoEnvio", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertRECLineaOper(java.
	 * util.Map)
	 */
	public void insertRECLineaOper(Map criteria) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertRecLineaOper", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertRECOperNumLoteSap(
	 * java.util.Map)
	 */
	public void insertRECOperNumLoteSap(Map criteria) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertRecOperNumLoteSap", criteria);
	}

	public void insertRETConsolidadoVentasRetail(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertRETConsolidadoVentasRetail", params);
	}

	public void insertRETConsolidadoVentasRetailxZona(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertRETConsolidadoVentasRetailxZona",
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertSATEstimadoProductos
	 * (biz.belcorp.ssicc.sisicc.model.EstimadoProductos,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSATEstimadoProductos(EstimadoProductos cabecera,
			Usuario usuario) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertSATEstimadoProductos()
	 */
	public void insertSATEstimadoProductos(EstimadoProductos cabecera,
			Usuario usuario, Map datosJSP) {
		EstimadoProductos lista = null;
		datosJSP.put("linea", "5");
		datosJSP.put("periodo", "%200807");
		Integer listaOIDEstimado = (Integer) getSqlMapClientTemplate()
				.queryForObject("sisicc.InterfazSQL.getOIDEstimado", "");
		// Validacion de Linea
		lista = getListaEstimadosProducto(datosJSP, cabecera);
		if (lista == null) {
			Integer listaOIDProductos = (Integer) getSqlMapClientTemplate()
					.queryForObject("sisicc.InterfazSQL.getOIDProducto",
							datosJSP.get("codigoSAP"));
			Integer listaOidPeriodo = (Integer) getSqlMapClientTemplate()
					.queryForObject("sisicc.InterfazSQL.getOIDPeriodo",
							datosJSP.get("periodo"));
			cabecera.setLinea(3);
			cabecera.setPeriodo(listaOidPeriodo.toString());
			cabecera.setOidProducto(listaOIDProductos.intValue());
			cabecera.setProcedencia(1);
			cabecera.setCodigoEstimado(listaOIDEstimado.intValue() + 1);
			getSqlMapClientTemplate().insert(
					"sisicc.InterfazSQL.insertEstimadoProductos", cabecera);

		} else {
			lista.setCodigoEstimado(listaOIDEstimado.intValue() + 1);
			getSqlMapClientTemplate().insert(
					"sisicc.InterfazSQL.insertEstimadoProductos", lista);
		}

	}

	public void insertVentaBaseConsultoras(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertVentaBaseConsultoras", params);
	}

	public boolean isPeriodoValido(String periodo) {
		String value = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.isPeriodoValido", periodo);
		if (StringUtils.equals(value, "1")) {
			return true;
		} else {
			return false;
		}
	}

	public List obtenerSeccionesTrasvaseActualizacionUnidadAdministrativa(
			Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.obtenerSeccionesTrasvaseActuUnidAdmi",
				criteria);
	}

	public List obtenerTerritoriosTrasvaseActualizacionUnidadAdministrativa(
			Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.obtenerTerritoriosTrasvaseActuUnidAdmi",
				criteria);
	}

	public void saveXMLBoletasRecojo(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.saveXMLBoletasRecojo", params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * updateInterfazCOMRecepcionarActualizaCodigoProveedor
	 * (biz.belcorp.ssicc.sisicc
	 * .model.InterfazCOMRecepcionarActualizaCodigoProveedor,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateInterfazCOMRecepcionarActualizaCodigoProveedor(
			InterfazCOMRecepcionarActualizaCodigoProveedor resumen,
			Usuario usuario) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateInterfazCOMRecepcionarActualizaCodigoProveedor",
						resumen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * updateInterfazLARRecepcionarActualizaResultadoChequeo(java.util.Map)
	 */
	public void updateInterfazLARRecepcionarActualizaResultadoChequeo(Map Params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateInterfazLARRecepcionarActualizaResultadoChequeo",
						Params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * updateInterfazLARRecepcionarActualizaSecuenciacionzonas(java.util.Map)
	 */
	public void updateInterfazLARRecepcionarActualizaSecuenciacionzonas(
			Map Params) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateInterfazLARRecepcionarActualizaSecuenciacionzonas",
						Params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * updateInterfazPERActualizarPercepcionesConsolidado
	 * (biz.belcorp.ssicc.sisicc
	 * .model.InterfazPERActualizarPercepcionesConsolidado,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateInterfazPERActualizarPercepcionesConsolidado(
			InterfazPERActualizarPercepcionesConsolidado consolidado,
			Usuario usuario) {
		log.info("Entro a InterfazSiCCDAOiBatis - updateInterfazPERActualizarPercepcionesConsolidado(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado, biz.belcorp.ssicc.model.Usuario)");
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateInterfazPERActualizarPercepcionesConsolidado",
						consolidado);
		log.info("Salio a InterfazSiCCDAOiBatis - updateInterfazPERActualizarPercepcionesConsolidado(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado, biz.belcorp.ssicc.model.Usuario)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#updateInterfazProcesoBatch
	 * (java.util.Map)
	 */
	public void updateInterfazProcesoBatch(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.updateInterfazProcesoBatch", queryParams);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * updateInterfazREUIndicadorTransferenciaClientes()
	 */
	public void updateInterfazREUIndicadorTransferenciaClientes() {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateInterfazREUIndicadorTransferenciaClientes",
						null);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#updateLibretaAhorro(java
	 * .util.Map)
	 */
	public void updateLibretaAhorro(LibretaAhorro libretaAhorro) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateLibretaAhorroInterfaz",
						libretaAhorro);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#updateLibretaAhorro(java
	 * .util.Map, Usuario)
	 */
	public void updateLibretaAhorro(LibretaAhorro libretaAhorro, Usuario usuario) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.updateLibretaAhorroInterfaz",
						libretaAhorro);
	}

	public void updateRETConsolidadoVentasRetail(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.updateRETConsolidadoVentasRetail", params);
	}

	public List getListaAlmacen(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getListaAlmacen", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getTiposRecepcionXRX(java
	 * .lang.String, java.lang.String)
	 */
	public List getTiposRecepcionXRX(String codigoInterfaz1,
			String codigoInterfaz2) {

		Map parms = new HashMap();
		parms.put("codigoInterfaz1", codigoInterfaz1);
		parms.put("codigoInterfaz2", codigoInterfaz2);

		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposRecepcionXRX", parms);
	}

	/* INI JJ PER-SiCC-2012-0345 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarMaestroProgramaNuevas(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroProgramaNuevas(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarMaestroProgramaNuevas",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCuponesPremiosKitsProgramaNuevas(java.util.Map)
	 */
	public void executeInterfazDATEnviarCuponesPremiosKitsProgramaNuevas(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarCuponesPremiosKitsProgramaNuevas",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCuponesPremiosKitsCuvProgramaNuevas
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarCuponesPremiosKitsCuvProgramaNuevas(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarCuponesPremiosKitsCuvProgramaNuevas",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarUnidadesAdministrativasProgramaNuevas
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarUnidadesAdministrativasProgramaNuevas(
			Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarUnidadesAdministrativasProgramaNuevas",
						params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazDATEnviarCuponesUsadosProgramaNuevas(java.util.Map)
	 */
	public void executeInterfazDATEnviarCuponesUsadosProgramaNuevas(Map params) {
		getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.executeInterfazDATEnviarCuponesUsadosProgramaNuevas",
						params);
	}

	/* FIN JJ PER-SiCC-2012-0345 */

	/* INI JR PER-SiCC-2012-0444 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazOCRRecepcionarConsolidadoOCSFlexipago(java.util.Map)
	 */
	public void executeInterfazOCRRecepcionarConsolidadoOCSFlexipago(
			Map queryParams) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazOCRRecepcionarConsolidadoOCSFlexipago",
						queryParams);
	}

	/* FIN JR PER-SiCC-2012-0444 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazIMPEnviarFacturasCabecera(java.util.Map)
	 */
	public void executeInterfazIMPEnviarFacturasCabecera(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazIMPEnviarFacturasCabecera",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazIMPEnviarFacturasDetalle(java.util.Map)
	 */
	public void executeInterfazIMPEnviarFacturasDetalle(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazIMPEnviarFacturasDetalle",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazIMPEnviarNotasCreditoCabecera(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoCabecera(Map criteria) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazIMPEnviarNotasCreditoCabecera",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazIMPEnviarNotasCreditoDetalle(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoDetalle(Map criteria) {
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazIMPEnviarNotasCreditoDetalle",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeInterfazIMPEnviarProductos(java.util.Map)
	 */
	public void executeInterfazIMPEnviarProductos(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazIMPEnviarProductos",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeIMPEnviarEjecucionNivel
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarEjecucionNivel(Map criteria) {
		// DAT-125
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarEjecucionNivel",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeIMPEnviarCicloEjecucion
	 * (java.util.Map)
	 */
	public void executeInterfazDATEnviarCicloEjecucion(Map criteria) {
		// DAT-126
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazDATEnviarCicloEjecucion",
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * executeIMPEnviarResultadoEjecucion(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoEjecucion(Map criteria) {
		// DAT-127
		getSqlMapClientTemplate()
				.update("sisicc.InterfazSQL.executeInterfazDATEnviarResultadoEjecucion",
						criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getActualDireccionBR(java
	 * .util.Map)
	 */
	public Integer getActualDireccionBR(Map criteria) {
		Integer resultado = 0;
		resultado = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getActualDireccionBR", criteria);
		;
		return resultado.intValue();
	}

	/* INI JJ PER-SiCC-2012-0388 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#
	 * getContCierreZonaByCodigoPeriodoOidZona(java.lang.String,
	 * java.lang.Integer)
	 */
	public Integer getContCierreZonaByCodigoPeriodoOidZona(
			String codigoPeriodo, Integer oidZona) {
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("oidZona", oidZona);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getContCierreZonaByCodigoPeriodoOidZona",
				criteria);
	}
	/* FIN JJ PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getContCierreRegionByCodigoPeriodoOidRegion(java.lang.String, java.lang.Integer)
	 */
	public Integer getContCierreRegionByCodigoPeriodoOidRegion(String codigoPeriodo, Integer oidRegion) {
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("oidRegion", oidRegion);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getContCierreRegionByCodigoPeriodoOidRegion", criteria);
	}
	/* FIN SA PER-SiCC-2012-1120 */


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getContRegistrosAsociadosCargaArchivosBolVent(java.util.Map)
	 */
	public Integer getContRegistrosAsociadosCargaArchivosBolVent(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getContRegistrosAsociadosCargaArchivosBolVent", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getContRegistrosAsociadosCargaArchivosNotasCred(java.util.Map)
	 */
	public Integer getContRegistrosAsociadosCargaArchivosNotasCred(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getContRegistrosAsociadosCargaArchivosNotasCred", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getArchivosPendientesBolVent(java.util.Map)
	 */
	public List getArchivosPendientesBolVent(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.getArchivosPendientesBolVent", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getArchivosPendientesNotasCred(java.util.Map)
	 */
	public List getArchivosPendientesNotasCred(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.getArchivosPendientesNotasCred", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRegionesByPaisMarcaCanalPeriodo(java.util.Map)
	 */
	public List getRegionesByPaisMarcaCanalPeriodo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getRegionesByPaisMarcaCanalPeriodo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getConcursosFaltantesByPaisMarcaCanalDetalle(java.util.Map)
	 */
	public List getConcursosFaltantesByPaisMarcaCanalDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getConcursosFaltantesByPaisMarcaCanalDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getEstadosConsultoraByReporte(java.util.Map)
	 */
	public List getEstadosConsultoraByReporte(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getEstadosConsultoraByReporte", criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getFechaFacturacion()
	 */
	public String getFechaFacturacion() {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getFechaFacturacion");
	}
	
	public boolean existePercepcionesDetalle(MovimientosBancariosDetalle detalle){
		Integer valor =(Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSQL.existePercepcionesDetalle", detalle);
		return valor.intValue()==1?true:false;
	}
	
	public void updateIndRepePercepcionesDetalle(MovimientosBancariosDetalle detalle){
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.updateIndRepePercepcionesDetalle", detalle);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getListaEstadosIncentivos()
	 */
	public List getListaEstadosIncentivos() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaEstadosIncentivos", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getListaAsientos()
	 */
	public List getListaAsientos() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaAsientos", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getRegionesACerrar(java.util.Map)
	 */
	public List getRegionesACerrar(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRegionesACerrar", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeInterfazMAEEnviarConsultorasBloqueadasDesbloquedas(java.util.Map)
	 */
	public void executeInterfazMAEEnviarConsultorasBloqueadasDesbloquedas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeInterfazMAEEnviarConsultorasBloqueadasDesbloquedas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#updateInterfazCCCCargasDeudasWeb(java.util.Map)
	 */
	public void updateInterfazCCCCargasDeudasWeb(Map params){
		getSqlMapClientTemplate()
		.update("sisicc.InterfazCCCSQL.updateInterfazCCCCargasDeudasWeb",
				params);
	}
	public List getListaPagosLec(){
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaPagosLec", null);		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeEvaluarExigenciasDescuentos(java.util.Map)
	 */
	public void executeEvaluarExigenciasDescuentos(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeEvaluarExigenciasDescuentos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getTipoReemplazo()
	 */
	public List getTipoReemplazo() {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getTipoReemplazo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getTipoDocumentosPago()
	 */
	public List getTipoDocumentosPago() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTipoDocumentosPago", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteSolicitudesCabecera()
	 */
	public String getExisteSolicitudesCabecera() {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSQL.getExisteSolicitudesCabecera");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getExisteAsistencia(java.util.HashMap)
	 */
	public int getExisteAsistenciaConferencia(HashMap criteria) {
		Integer valor = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSQL.getExisteAsistenciaConferencia", criteria);
		
		if(valor == null)
			return 0;
		else
			return valor.intValue();
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertAsistenciaConferencia(java.util.Map)
	 */
	public void insertAsistenciaConferencia(Map params) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertAsistenciaConferencia",
						params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#updateAsistenciaConferencia(java.util.Map)
	 */
	public void updateAsistenciaConferencia(Map queryParams) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.updateAsistenciaConferencia", queryParams);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getPeriodosDefaultByPMCF(java.util.Map)
	 */
	public List getPeriodosDefaultByPMCF(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodoDefaultByPaisMarcaCanalFacturacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getOidClienteByCodigoCliente(java.util.Map)
	 */
	public String getOidClienteByCodigoCliente(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSQL.getOidClienteByCodigoCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#updateIndicadorPaqueteDocumentario(java.util.Map)
	 */
	public void updateIndicadorPaqueteDocumentario(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.updateIndicadorPaqueteDocumentario", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeReestructurarUnidadAdministrativa(java.util.Map)
	 */
	public void executeReestructurarUnidadAdministrativa(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeReestructurarUnidadAdministrativa", params);
	}
	
	public String getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma(
			Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma", criteria);
	}

	public String getDescripcionSubTipoClienteByCriteria(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getDescripcionSubTipoClienteByCriteria", criteria); 
	}

	public String getDescripcionTipoClasificacionByCriteria(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getDescripcionTipoClasificacionByCriteria", criteria);
	}

	public String getClasificacionByCriteria(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getClasificacionByCriteria", criteria);
	}

	public List getTiposClientesByCodigoISO01(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposClientesByCodigoISO01", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeEnviarSMSBoletaPrimerRecojo(java.util.Map)
	 */
	public void executeEnviarSMSBoletaPrimerRecojo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeEnviarSMSBoletaPrimerRecojo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeEnviarSMSBoletaSegundoRecojo(java.util.Map)
	 */
	public void executeEnviarSMSBoletaSegundoRecojo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeEnviarSMSBoletaSegundoRecojo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#executeEnviarSMSBoletaSegundoRecojoNoExitoso(java.util.Map)
	 */
	public void executeEnviarSMSBoletaSegundoRecojoNoExitoso(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.executeEnviarSMSBoletaSegundoRecojoNoExitoso", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#deleteInterfazLLIRecepcionarCargaProductosPlanit()
	 */
	public void deleteInterfazLLIRecepcionarCargaProductosPlanit() {
		getSqlMapClientTemplate().delete("sisicc.InterfazSQL.deleteInterfazLLIRecepcionarCargaProductosPlanit", null);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertInterfazLLIRecepcionarCargaProductosPlanit(java.util.Map)
	 */
	public void insertInterfazLLIRecepcionarCargaProductosPlanit(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSQL.insertInterfazLLIRecepcionarCargaProductosPlanit", params);
	}
	
/* INI FRAMRWORK NSSICC PRUEBAS TRANSACCION */
    public void insertHistorico3(Historico historico, Usuario usuario) {
    	getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.insertHistorico", historico);
	}
	
	public void updateHistorico3(Historico historico, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.HistoricoLoteSQL.updateHistorico", historico);
	}
	/* FIN FRAMRWORK NSSICC PRUEBAS TRANSACCION */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO#executeGenerarAtencionesInteligentes(java.util.Map)
	 */
	public void executeGenerarAtencionesInteligentes(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeGenerarAtencionesInteligentes", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO#executeEliminarAtencionesInteligentes(java.util.Map)
	 */
	public void executeEliminarAtencionesInteligentes(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeEliminarAtencionesInteligentes", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO#executeEliminarDespachosConsultorasNoConstantes(java.util.Map)
	 */
	@Override
	public void executeEliminarDespachosConsultorasNoConstantes(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeEliminarDespachosConsultorasNoConstantes", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO#getErrorProcesoBatchSICC(java.lang.String)
	 */
	public String getErrorProcesoBatchSICC(String codigoProcesoBatch) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getErrorProcesoBatchSICC", codigoProcesoBatch);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO#executeInterfazEnviarResumenDiarioPercepcionesSunat(java.util.Map)
	 */
	@Override
	public void executeInterfazEnviarResumenDiarioPercepcionesSunat(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.executeInterfazEnviarResumenDiarioPercepcionesSunat", params);
	}
	
}