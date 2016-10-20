package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;

@Repository("spusicc.procesoPEDDAO")
public class ProcesoPEDDAOiBatis extends BaseDAOiBatis implements ProcesoPEDDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeCargaPedidosDigitados(java.util.Map)
	 */
	public void executeCargaPedidosDigitados(Map params) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.executeCargaPedidosDigitados", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeConsolidadoPedidos(java.util.Map)
	 */
	public void executeConsolidadoPedidos(Map params) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.executeConsolidadoPedidos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getPedidosDigitadosByCriteria(java.util.Map)
	 */
	public List getPedidosDigitadosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosDigitadosByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getIndicadorActividadPROL(java.util.Map)
	 */
	public String getIndicadorActividadPROL(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorActividadPROL", params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeInicializaStock()
	 */
	public void executeInicializaStock() {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeInicializaStock");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeActivaFlagControlActividadPROL(java.util.Map)
	 */
	public void executeEnvioPortalSICCFinDia(Map params){
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeEnvioPortalSICCFinDia", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#insertaArchivoEntradaPROL(java.util.Map)
	 */
	public void insertaArchivoEntradaPROL(Map archivoEntrada){
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertaArchivoEntradaPROL",archivoEntrada);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getCadenaResultadoPROL(java.util.Map)
	 */
	public String getCadenaResultadoPROL(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getCadenaResultadoPROL", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#generaArchivoSalidaPROL(java.util.Map)
	 */
	public void generaArchivoSalidaPROL(Map criteria){
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.generaArchivoSalidaPROL",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getPaisFecha(java.util.Map)
	 */
	public List getPaisFecha(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPaisFecha",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getTotalesPROL(java.util.Map)
	 */
	public List getTotalesPROL(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTotalesPROL",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getDetallePedidoPROL(java.util.Map)
	 */
	public List getDetallePedidoPROL(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetallePedidoPROL",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getPedidoTemporalById(java.util.Map)
	 */
	public List getPedidoTemporalById(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidoTemporalById",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getOidSolicitudPROL(java.util.Map)
	 */
	public String getOidSolicitudPROL(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidSolicitudPROL", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getPedidoTemporalById(java.util.Map)
	 */
	public List getPedidoDocumentoDigitadoPKByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidoDocumentoDigitadoPKByCriteria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeActualizaIndicadorPROL(java.util.Map)
	 */
	public void executeActualizaIndicadorPROL(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeActualizaIndicadorPROL",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getIndicadorActividadPROL2()
	 */
	public String getIndicadorActividadPROL2() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorActividadPROL2");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getTiposCargaStock()
	 */
	public List getTiposCargaStock() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTiposCargaStock");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#insertarAuditoriaPROL(java.util.Map)
	 */
	public void insertarAuditoriaPROL(Map criteria) {
		synchronized (this){
			BigDecimal oidAuditoriaPROL = (BigDecimal)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidAuditoriaPROL");
			criteria.put("oidAuditoriaPROL", oidAuditoriaPROL);
		}
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertarAuditoriaPROL",criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#updateAuditoriaPROL(java.util.Map)
	 */
	public void updateAuditoriaPROL(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateAuditoriaPROL",criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getProcesoPROL(java.util.Map)
	 */
	public int getProcesoPROL(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getProcesoPROL",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getProcesoColaPROL(java.util.Map)
	 */
	public Map getProcesoColaPROL(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getProcesoColaPROL",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#sendMailOcr(java.util.Map)
	 */
	public void sendMailOcr(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.sendMailOcr",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getProcesosNuevoPROL(java.util.Map)
	 */
	public List getProcesosNuevoPROL(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProcesosNuevoPROL");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeProceso(java.util.Map)
	 */
	public void executeProceso(Map criteria, Map queryParams) {
		String nameSpace = (String) criteria.get("nameSpace");
		String idProceso = (String) criteria.get("idProceso");
		String cadena = nameSpace + "." + idProceso;
		if (StringUtils.isNotBlank(cadena)) 
			getSqlMapClientTemplate().update(cadena, queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeProcesoAsignacionStockGP3(java.util.Map)
	 */
	public void executeProcesoAsignacionStockGP3(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoAsignacionStockGP3", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#executeProcesoGeneracionOportunidadAhorro(java.util.Map)
	 */
	public void executeProcesoGeneracionOportunidadAhorro(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoGeneracionOportunidadAhorro", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#insertDemandaAnticipada(java.util.Map)
	 */
	public void insertDemandaAnticipada(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertDemandaAnticipada", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#deleteDemandaAnticipada(java.util.Map)
	 */
	public void deleteDemandaAnticipada(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.deleteDemandaAnticipada", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#getSubacceso()
	 */
	public List getSubacceso() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getSubacceso");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDDAO#eliminaRegistroRUV(java.util.Map)
	 */
	public void eliminaRegistroRUV(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.eliminaRegistroRUV", criteria);
	}	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeProcesoPEDPedidoInventariado(java.util.Map)
	 */
	public void executeProcesoPEDPedidoInventariado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoPEDPedidoInventariado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeProcesoPEDServicioGenerico(java.util.Map)
	 */
	public void executeProcesoPEDServicioGenerico(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoPEDServicioGenerico", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getNextCorrelativoPedidoRechazadas()
	 */
	@Override
	public String getNextCorrelativoPedidoRechazadas() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNextCorrelativoPedidoRechazadas", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#insertaCabeceraEntradaPedidoRechazado(java.util.Map)
	 */
	@Override
	public void insertaCabeceraEntradaPedidoRechazado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertaCabeceraEntradaPedidoRechazado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#insertaDetalleEntradaPedidoRechazado(java.util.Map)
	 */
	@Override
	public void insertaDetalleEntradaPedidoRechazado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertaDetalleEntradaPedidoRechazado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#insertaAlternativosEntradaPedidoRechazado(java.util.Map)
	 */
	@Override
	public void insertaAlternativosEntradaPedidoRechazado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.insertaAlternativosEntradaPedidoRechazado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeProcesoPEDPedidoRechazado(java.util.Map)
	 */
	@Override
	public void executeProcesoPEDPedidoRechazado(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoPEDPedidoRechazado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getPedidosCabeceraRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosCabeceraRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosCabeceraRechazados",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getPedidosDetalleRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosDetalleRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosDetalleRechazados",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getPedidosAlternativosRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosAlternativosRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosAlternativosRechazados",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getPedidosErroresRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosErroresRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosErroresRechazados",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getPedidosMensajesRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosMensajesRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosMensajesRechazados",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getListaZonasByCampanaFechaFacturacion(java.util.Map)
	 */
	public List getListaZonasByCampanaFechaFacturacion(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaZonasByCampanaFechaFacturacion",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#getListaPedidosByZonasCampanaFechaFacturacion(java.util.Map)
	 */
	public List getListaPedidosByZonasCampanaFechaFacturacion(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaPedidosByZonasCampanaFechaFacturacion",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeCuadrarOfertasPedidoZonaMultihilo(java.util.Map)
	 */
	public void executeCuadrarOfertasPedidoZonaMultihilo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeCuadrarOfertasPedidoZonaMultihilo",criteria);	
		}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeMontoMinimoPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeMontoMinimoPedidoZonaMultihilo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeMontoMinimoPedidoZonaMultihilo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeMontoMaximoPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeMontoMaximoPedidoZonaMultihilo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeMontoMaximoPedidoZonaMultihilo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeRecuperacionObligatoriaPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeRecuperacionObligatoriaPedidoZonaMultihilo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeRecuperacionObligatoriaPedidoZonaMultihilo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeValidarAgregadosMavPedidoZonaMultihilo(java.util.Map)
	 */
	@Override
	public void executeValidarAgregadosMavPedidoZonaMultihilo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeValidarAgregadosMavPedidoZonaMultihilo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeCambiarPedidoGP1aGP3(java.util.Map)
	 */
	public void executeCambiarPedidoGP1aGP3(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeCambiarPedidoGP1aGP3",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO#executeProcesosEspeciales(java.util.Map)
	 */
	public void executeEjecutarProcesosEspeciales(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeEjecutarProcesosEspeciales",criteria);
	}
	
}