package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelPedidosConsoDetalValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoAnuladoFacturado;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProcesoOCRActualizarUnidadesMaximas;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;

@Repository("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionDAO")
public class MantenimientoOCRPedidoControlFacturacionDAOiBatis extends
	BaseDAOiBatis implements MantenimientoOCRPedidoControlFacturacionDAO {
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getControlFacturacionByCriteria(java.util.Map)
	*/
	public List getControlFacturacionByCriteria(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getControlFacturacionByCriteria",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getProductoAgregacionByCriteria(java.util.Map)
	*/
	public List getProductoAgregacionByCriteria(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoAgregacionByCriteria",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getSolicitudCabeceraByCriteria(java.util.Map)
	*/
	public List getDeudaPedidosByCriteria(Map criteria) {
	
	
	String indicador= (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorSTO", criteria);
	
	if(indicador.compareToIgnoreCase("1")==0)
		criteria.put("indicador", indicador);
	else
		criteria.put("indicador", "");
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDeudaPedidosByCriteria",
			criteria);
	}
	
	public List getDeudaPedidosMasivaByCriteria(Map criteria) {
	
	String indicador= (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorSTO", criteria);
	
	if(indicador.compareToIgnoreCase("1")==0)
		criteria.put("indicador", indicador);
	else
		criteria.put("indicador", "");
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDeudaPedidosMasivaByCriteria",
			criteria);
	}
	
	public List getPedidosFacturadosAnuladosByCriteria(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosFacturadosAnuladosByCriteria",
			criteria);
	}
	
	public List getBloqueoPedidosMasivoByCriteria(Map criteria) {
	
	String indicador= (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorSTO", criteria);
	
	if(indicador.compareToIgnoreCase("1")==0)
		criteria.put("indicador", indicador);
	else
		criteria.put("indicador", "");
	
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getBloqueoPedidosMasivoByCriteria",
			criteria);
	}
	
	public void deleteTemporalDeudaMasiva() {
	getSqlMapClientTemplate().delete(
			"spusicc.pedidos.PedidosSQL.deleteTemporalDeudaMasiva",
			null);
	}
	
	public void deleteTemporalBloqueoMasivo() {
	getSqlMapClientTemplate().delete(
			"spusicc.pedidos.PedidosSQL.deleteTemporalBloqueoMasivo",
			null);
	}
	
	public void insertTemporalDeudaMasiva(Map criteria) {
	getSqlMapClientTemplate().insert(
			"spusicc.pedidos.PedidosSQL.insertTemporalDeudaMasiva", criteria);
	}
	
	public void insertTemporalBloqueoMasivo(Map criteria) {
	getSqlMapClientTemplate().insert(
			"spusicc.pedidos.PedidosSQL.insertTemporalBloqueoMasivo", criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#grabarPedidoFacturadoAnulado(java.util.Map)
	*/
	public void grabarPedidoFacturadoAnulado(Map criteria) {
	getSqlMapClientTemplate().insert(
			"spusicc.pedidos.PedidosSQL.insertPedidoFacturadoAnulado", criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getBolsaPedidosByCriteria(java.util.Map)
	*/
	public List getBolsaPedidosByCriteria(Map criteria) {
	
	String indicador= (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getIndicadorSTO", criteria);
	
	if(indicador.compareToIgnoreCase("1")==0)
		criteria.put("indicador", indicador);
	else
		criteria.put("indicador", "");
	
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getBolsaPedidosByCriteria",
			criteria);
	}
	
	public List getOCREnviaOCSList(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOCREnviaOCSList",
			criteria);
	}
	
	public List getOCRConsultorasInactivasList(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOCRConsultorasInactivasList",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getSolicitudCabeceraById(java.util.Map)
	*/
	public SolicitudConsolidadoCabecera getDeudaPedidosById(Map criteria) {
	return (SolicitudConsolidadoCabecera)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getSolicitudCabeceraById",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getBolsaPedidosById(java.util.Map)
	*/
	public SolicitudConsolidadoCabecera getBolsaPedidosById(Map criteria) {
	return (SolicitudConsolidadoCabecera)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getSolicitudCabeceraById",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getProductoAgregacionById(java.util.Map)
	*/
	public ProductoAgregacion getProductoAgregacionById(Map criteria) {
	return (ProductoAgregacion)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getProductoAgregacionByCriteria",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getOfertaDetalleById(java.util.Map)
	*/
	public ProductoAgregacion getOfertaDetalleById(Map criteria) {
	return (ProductoAgregacion)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOfertaDetalleById",
			criteria);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getOfertaDetalleByPeriodoCodigoVenta(java.util.Map)
	*/
	public BigDecimal getOfertaDetalleByPeriodoCodigoVenta(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getOfertaDetalleByPeriodoCodigoVenta'");
	log.debug("params=" + params);
	
	BigDecimal decimal = new BigDecimal(0);
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOfertaDetalleByPeriodoCodigoVenta",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getMatrizFacturacionByPeriodo(java.util.Map)
	*/
	public BigDecimal getMatrizFacturacionByPeriodo(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getMatrizFacturacionByPeriodo'");
	log.debug("params=" + params);
	
	BigDecimal decimal = new BigDecimal(0);
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getMatrizFacturacionByPeriodo",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getDetOfertaIndicaDigitableById(java.util.Map)
	*/
	public BigDecimal getDetOfertaIndicaDigitableById(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getDetOfertaIndicaDigitableById'");
	log.debug("params=" + params);
	
	BigDecimal decimal = new BigDecimal(0);
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetOfertaIndicaDigitableById",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getDetOfertaPrecioCatalogoById(java.util.Map)
	*/
	public BigDecimal getDetOfertaPrecioCatalogoById(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getDetOfertaPrecioCatalogoById'");
	log.debug("params=" + params);
	
	BigDecimal decimal = new BigDecimal(0);
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetOfertaPrecioCatalogoById",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getDetOfertaEstrategiaIndividualById(java.util.Map)
	*/
	public BigDecimal getDetOfertaEstrategiaIndividualById(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getDetOfertaEstrategiaIndividualById'");
	log.debug("params=" + params);
	
	BigDecimal decimal = new BigDecimal(0);
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetOfertaEstrategiaIndividualById",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getCodVentaAgregacById(java.util.Map)
	*/
	public BigDecimal getCodVentaAgregacById(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getCodVentaAgregacById'");
	log.debug("params=" + params);
	
	BigDecimal decimal = new BigDecimal(0);
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodVentaAgregacById",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeActualizaPrioridad(java.util.Map)
	*/
	public void executeActualizaPrioridad(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeActualizaPrioridad",
			queryParams);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeProcesoOCRProcesosEspecialesOCS(java.util.Map)
	*/
	public void executeProcesoOCRProcesosEspecialesOCS(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeProcesoOCRProcesosEspecialesOCS",
			queryParams);
	}
	
	public void executeProcesoOCRActualizaGP2(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeProcesoOCRActualizaGP2",
			queryParams);
	}
	
	public void actualizaOCSHistoricoCabecera(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.actualizaOCSHistoricoCabecera",
			queryParams);
	}
	
	public void actualizaOCSHistoricoDetalle(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.actualizaOCSHistoricoDetalle",
			queryParams);
	}
	
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getControlFacturacionById(java.util.Map)
	*/
	public PedidoControlFacturacion getControlFacturacionById(Map criteria) {
	return (PedidoControlFacturacion)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getControlFacturacionByCriteria",
			criteria);
	}
	
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#insertControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion, biz.belcorp.ssicc.model.Usuario)
	*/
	public void insertControlFacturacion(PedidoControlFacturacion controlFacturacion, Usuario usuario) {
	getSqlMapClientTemplate().insert(
	        "spusicc.pedidos.PedidosSQL.insertControlFacturacion", controlFacturacion);
	// Auditoria
	getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertControlFacturacionAudit", controlFacturacion);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#insertProductoPedidoMinimo(biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion, biz.belcorp.ssicc.model.Usuario)
	*/
	public void insertProductoPedidoMinimo(ProductoAgregacion productoAgregacion, Usuario usuario) {
	getSqlMapClientTemplate().insert(
	        "spusicc.pedidos.PedidosSQL.insertProductoPedidoMinimo", productoAgregacion);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion, biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateControlFacturacion(PedidoControlFacturacion controlFacturacion, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateControlFacturacion", controlFacturacion);
	// Auditoria
	getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertControlFacturacionAudit", controlFacturacion);        
	}
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateDeuda(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateDeuda(SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateDeuda", cabecera);
	}
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateBolsaPedidosBloqueoIndividual(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateBolsaPedidosBloqueoIndividual(SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateBolsaPedidosBloqueoIndividual", cabecera);
	
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateDeudaGeneral(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateDeudaGeneral(SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateDeudaGeneral", cabecera);
	}
	
	public void updatePedidoAnuladoFacturado(PedidoAnuladoFacturado anuladoFacturado, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updatePedidoAnuladoFacturado", anuladoFacturado);
	}
	
	public void updatePedidosAnuladoConsultora(PedidoAnuladoFacturado anuladoFacturado, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updatePedidosAnuladoConsultora", anuladoFacturado);
	}
	
	public void updatePedidosAnulaciones(PedidoAnuladoFacturado anuladoFacturado, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updatePedidosAnulaciones", anuladoFacturado);
	}
	
	public void updateDeudaGeneralMasiva(SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateDeudaGeneralMasiva", cabecera);
	}
	
	public void updateBloqueoGeneral(SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateBloqueoGeneral", cabecera);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateBolsaPedidosBloqueo(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateBolsaPedidosBloqueo(SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.updateBolsaPedidosBloqueo", cabecera);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#deleteControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
	*/
	public void deleteControlFacturacion(PedidoControlFacturacion controlFacturacion) {
	getSqlMapClientTemplate().delete(
	        "spusicc.pedidos.PedidosSQL.deleteControlFacturacion", controlFacturacion);
	}
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#cerrarCampanaControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
	*/
	public void cerrarCampanaControlFacturacion(PedidoControlFacturacion controlFacturacion) {
	getSqlMapClientTemplate().delete(
	        "spusicc.pedidos.PedidosSQL.cerrarCampanaControlFacturacion", controlFacturacion);
	getSqlMapClientTemplate().delete(
	        "spusicc.pedidos.PedidosSQL.updateCampanaFacturacionPais", controlFacturacion);
	// Auditoria
	getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertControlFacturacionAudit", controlFacturacion);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#cerrarCampanaControlFacturacion(java.util.Map)
	*/
	public void cerrarPeriodo(Map map) {
	getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.cerrarPeriodo", map);
	}
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeActualizaNumeroLote(java.util.Map)
	*/
	public void executeActualizaNumeroLote(Map params) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL.executeActualizaNumeroLote", params);
	PedidoControlFacturacion controlFacturacion;
	controlFacturacion = (PedidoControlFacturacion)params.get("controlFacturacion");
	// Auditoria
	getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertControlFacturacionAudit", controlFacturacion);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#desactivarProductoPedidoMinimo(biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion)
	*/
	public void desactivarProductoPedidoMinimo(ProductoAgregacion productoAgregacion) {
	getSqlMapClientTemplate().delete(
	        "spusicc.pedidos.PedidosSQL.desactivarProductoPedidoMinimo", productoAgregacion);
	}
	
	/*
	*  (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getPedidosSiguienteCampanha(java.util.Map)
	*/
	public String getPedidosSiguienteCampanha(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getPedidosSiguiente'");
	log.debug("params=" + params);
	
	String campanha = null;
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosSiguienteCampanha",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		campanha = (String) iter.next();
	}
	
	return campanha;
	}
	
	public String getPedidosNSiguienteCampanha(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getPedidosNSiguienteCampanha'");
	log.debug("params=" + params);
	
	String campanha = null;
	// Obtenemos los clientes nuevos a actualizar
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosNSiguienteCampanha",
											params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		campanha = (String) iter.next();
	}
	
	return campanha;
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeInterfaz(java.lang.String, java.util.Map)
	*/
	public void executePedido(String method, Map params) {
	getSqlMapClientTemplate().update(
	        "spusicc.pedidos.PedidosSQL."+method, params);
	}
	
	/**
	*  Carga Pedidos Anulados desde Temporal hacia Maestro
	*	@param  
	*/
	public void updateCargaPedidosMasivoAnulados(Map params){
	getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateCargaPedidosMasivoAnulados", params);
	
	}
	
	public void deleteCargaPedidosAnulados() {
	getSqlMapClientTemplate().delete(
			"spusicc.pedidos.PedidosSQL.deletePedidoMasivoFacturadoAnulado",
			null);
	}
	
	public void insertPedidoMasivoFacturadoAnulado(Map criteria, Usuario usuario) {
	String codusuario = (String)usuario.getLogin();
	criteria.put("codusuario", codusuario);
	getSqlMapClientTemplate().insert(
			"spusicc.pedidos.PedidosSQL.insertPedidoMasivoFacturadoAnulado", criteria);
	}
	
	public List getConsultoraPedidoCabeceraByCriteria(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getConsultoraPedidoCabeceraByCriteria",
			criteria);
	}
	
	public String getNumLotes(Map params){
	String numLotes="0";
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getNumLotesByPeriodo",
			params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		numLotes = (String) iter.next();
	}
	return numLotes;
	}
	
	public String getNumeroLote(Map params){
	String nLote = "00000001";
	List list  = new ArrayList(); 
	list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getNumLotesByPais",
				 params);
	if(list.size()!=0)
		nLote = (String)list.get(0);
	return nLote;		
	}
	
	public String getNumeroUnidades(Map params){
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getNumeroUnidades",params);
	String numUnidades = "0";
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		numUnidades = (String) iter.next();
	}
	return numUnidades;
	}
	
	public LabelPedidosConsoDetalValue getDetalleById(Map params){
	LabelPedidosConsoDetalValue labelPedidosConsoDetalValue = null;
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetalleById",params);		
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		labelPedidosConsoDetalValue = (LabelPedidosConsoDetalValue) iter.next();
	}
	log.debug(labelPedidosConsoDetalValue);
	log.debug("__region = "+labelPedidosConsoDetalValue.getRegion());
	log.debug("__zona = "+labelPedidosConsoDetalValue.getZona());
	return labelPedidosConsoDetalValue;
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateUnidades(java.util.Map)
	*/
	public void updateUnidades(Map params){
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.updateUnidades", params);
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.updateUnidadesSicc", params);
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateUnidadesTotal(biz.belcorp.ssicc.spusicc.pedidos.model.ProcesoOCRActualizarUnidadesMaximas)
	*/
	public boolean updateUnidadesTotal(ProcesoOCRActualizarUnidadesMaximas bean){
	boolean modificar = false;
	String[] listaId = bean.getListaId()  ;
	String[] listaUnidadDemanda = bean.getListaUnidadDemanda();
	String[] listaUnidadDemandaIni = bean.getListaUnidadDemandaIni();
	String[] listaCodigoCliente = bean.getListaCodigoCliente();
	String[] listaCodigoPais = bean.getListaCodigoPais();
	String[] listaCodigoPeriodo = bean.getListaCodigoPeriodo();
	String[] listaCodigoVta = bean.getListaCodigoVta();
	String[] listaNumLote = bean.getListaNumLote();
	String[] listaIndicadorPROL = bean.getListaIndicadorPROL();
	
	int tamanno = listaId.length;
	for (int i=0; i<tamanno; i++) {
		Integer unidadDemanda = new Integer(listaUnidadDemanda[i]);
		Integer unidadDemandaIni = new Integer(listaUnidadDemandaIni[i]);
		if (unidadDemanda.intValue() != unidadDemandaIni.intValue()) {
			modificar = true;
			Map params = new HashMap();
			params.put("codigoPais", listaCodigoPais[i]);
			params.put("codigoCliente", listaCodigoCliente[i]);
			params.put("codigoPeriodo", listaCodigoPeriodo[i]);
			params.put("codigoVta", listaCodigoVta[i]);
			params.put("numLote", listaNumLote[i]);
			params.put("unidades", new Integer(listaUnidadDemanda[i]));
			params.put("usuario", bean.getUsuario());
			
			if(listaIndicadorPROL[i].equals("NO")){
	    		getSqlMapClientTemplate().update(
	    				"spusicc.pedidos.PedidosSQL.updateUnidades", params);
	    		getSqlMapClientTemplate().update(
	    				"spusicc.pedidos.PedidosSQL.updateUnidadesSicc", params);
			}
		}
	}
	return modificar;
	}
	
	
	public void executeProcesoRECCargaTablaBoletaRecojoEspecial(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeProcesoRECCargaTablaBoletaRecojoEspecial",
			queryParams);
	}
	
	public List getCampanhasActivasByCriteria(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCampanhasActivasByCriteria",criteria);
	}
	
	public List getCampanhasActivasById(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getControlFacturacionByCriteria",
			criteria);
	}
	
	public List getOCRDetalladoAptasList(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOCRDetalladoAptasList",
			criteria);
	}
	
	public List getOCRPedidosDuplicadosList(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOCRPedidosDuplicadosList",
			criteria);
	}
	
	public BigDecimal getCodigoCuponInicioRango(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getCodigoCuponInicioRango'");		
	
	BigDecimal decimal = new BigDecimal(0);
	
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodigoCuponInicioRango",params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	public BigDecimal getCodigoCuponFinRango(Map params) {
	if (log.isDebugEnabled())
		log.debug("Entering method 'getCodigoCuponFinRango'");		
	
	BigDecimal decimal = new BigDecimal(0);
	
	List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodigoCuponFinRango",params);
	for (Iterator iter = list.iterator(); iter.hasNext();) {
		decimal = (BigDecimal) iter.next();
	}
	
	return decimal;
	}
	
	public ProductoAgregacion getDetallePedidoCupon(Map criteria){		
	return (ProductoAgregacion)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getDetallePedidoCupon",
			criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getEstadoConsultora(java.util.Map)
	*/
	public String getEstadoConsultora(Map params){		 
	return getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getEstadoConsultora",params).toString();						
	}
	
	public String getExistePedidoConsultora(Map params){
	String res = "";
	try {
		res = getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getExistePedidoConsultora",params).toString(); 
	} catch (Exception e) {
	}
	return res;
	}
	
	public ProductoAgregacion getDetalleProductoById(Map criteria) {
	return (ProductoAgregacion)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getDetalleProductoById",
			criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeProcesoMontoMinimoMaximo(java.util.Map)
	*/
	public void executeProcesoMontoMinimoMaximo(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeProcesoMontoMinimoMaximo",
			queryParams);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeProcesoRevertirCambiosGP1STO(java.util.Map)
	*/
	public void executeProcesoRevertirCambiosGP1STO(Map queryParams) {
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeProcesoRevertirCambiosGP1STO",
			queryParams);
	}
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getMaximoUnidades(java.util.Map)
	*/
	public String getMaximoUnidades(Map params){
	String res = "";
	try {
		res = getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getMaximoUnidades",params).toString(); 
	} 
	catch (Exception e) {
	}
	return res;
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getLongitudMaximoUnidades(java.util.Map)
	*/
	public String getLongitudMaximoUnidades(Map params){
	String res = "";
	try {
		res = getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getLongitudMaximoUnidades",params).toString(); 
	} 
	catch (Exception e) {
	}
	return res;
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeActualizarContrarioIndicadores(java.util.Map)
	*/
	public void executeActualizarContrarioIndicadores(Map criteria){
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.executeActualizarContrarioIndicadores",
			criteria);
	PedidoControlFacturacion controlFacturacion;
	controlFacturacion = (PedidoControlFacturacion)criteria.get("controlFacturacion");
	// Auditoria
	getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertControlFacturacionAudit", controlFacturacion);
	}
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#anularPedidoFacturado(java.util.Map)
	*/
	public void anularPedidoFacturado(Map map){
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.anularPedidoFacturado",
			map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getPedidosByCriteria(java.util.Map)
	*/
	public List getPedidosByCriteria(Map map){
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosByCriteria",
			map);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#eliminarPedidoDigitado(java.util.Map)
	*/
	public void eliminarPedidoDigitado(Map criteria){
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.eliminarPedidoDigitado",
			criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#grabarEliminacionPedidoDigitado(java.util.Map)
	*/
	public void grabarEliminacionPedidoDigitado(Map criteria){
	try {
		this.getSqlMapClientTemplate().insert(
			"spusicc.pedidos.PedidosSQL.grabarEliminacionPedidoDigitado", criteria);
	}
	catch(Exception e) {
		this.getSqlMapClientTemplate().update(
				"spusicc.pedidos.PedidosSQL.updateGrabarEliminacionPedidoDigitado", criteria);
	}
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getPedidosAEliminarByCriteria(java.util.Map)
	*/
	public List getPedidosAEliminarByCriteria(Map map){
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosAEliminarByCriteria",
			map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getConsultoraCabeceraSimpleByCriteria(java.util.Map)
	*/
	public List getConsultoraCabeceraSimpleByCriteria(Map criteria) {
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getConsultoraCabeceraSimpleByCriteria",
			criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#anularPedidoFacturadoSTO(java.util.Map)
	*/
	public void anularPedidoFacturadoSTO(Map map){
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.anularPedidoFacturadoCUP",
			map);
	getSqlMapClientTemplate().update(
			"spusicc.pedidos.PedidosSQL.anularPedidoFacturadoSTO",
			map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#deleteOCSClasificaciones(java.util.Map)
	*/
	public void deleteOCSClasificaciones(Map map){
	getSqlMapClientTemplate().delete(
			"spusicc.pedidos.PedidosSQL.deleteOCSClasificaciones",
			map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getListaCodigoPlantilla(java.util.Map)
	*/
	public List getListaCodigoPlantilla(Map map){
	 return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaCodigoPlantilla",map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getListaPlantillas(java.util.Map)
	*/
	public List getListaPlantillas(Map map){
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaPlantillas",map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#insertPlantillas(java.util.Map)
	*/
	public void insertPlantillas(Map map){
	getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertPlantillas", map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeRegeneraPlantillas(java.util.Map)
	*/
	public void executeRegeneraPlantillas(Map map){
	getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeRegeneraPlantillas",map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getListaSolicitudes(java.util.Map)
	*/
	public List getListaSolicitudes(Map map){
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaSolicitudes",map);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeOCSHistorico(java.util.Map)
	*/
	public void executeOCSHistorico(Map params) {
	getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.actualizaOCSHistoricoCabecera",params);
	getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.actualizaOCSHistoricoDetalle",params);
	
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getNumeroPedidosByLote(java.util.Map)
	*/
	public String getNumeroPedidosByLote(Map criteria) {		
	return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNumeroPedidosByLote",criteria);						
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getNumeroLoteByPk(java.util.Map)
	*/
	public String getNumeroLoteByPk(Map criteria){
	return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNumeroLoteByPk",criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#saveHistoricoPedidos()
	*/
	public void saveHistoricoPedidos() {
	getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeHistoricoPedidos");
	}	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getConsolidadoCabeceraByPK(java.util.Map)
	*/
	public List getConsolidadoCabeceraByPK(Map criteria){
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getConsolidadoCabeceraByPK",criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateLevantarRetornarDeuda(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateLevantarRetornarDeuda(Map criteria) {
	getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateLevantarRetornarDeuda", criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#validarPedidoMayorGP1(java.util.Map)
	*/
	public String validarPedidoMayorGP1(Map params){		
	return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.validarPedidoMayorGP1",params);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getIndicadorActivaNuevaCampana(java.util.Map)
	*/
	public String getIndicadorActivaNuevaCampana(Map criteria){
	return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorActivaNuevaCampana",criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getExistePeriodoActivo(java.util.Map)
	*/
	public String getExistePeriodoActivo(Map criteria){
	return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getExistePeriodoActivo",criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getPeriodoInicioHistorico(java.util.Map)
	*/
	public String getPeriodoInicioHistorico(Map criteria){
	return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getPeriodoInicioHistorico",criteria);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getListaPeriodosHistoricos(java.util.Map)
	*/
	public List getListaPeriodosHistoricos(Map criteria){
	return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaPeriodosHistoricos",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeFacturacionAdicional(java.util.Map)
	 */
	public void executeFacturacionAdicional(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeFacturacionAdicional", params);
	}
}