package biz.belcorp.ssicc.dao.spusicc.ruv.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVDocumentosContablesDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.mantenimientoRUVDocumentosContablesDAO")
public class MantenimientoRUVDocumentosContablesDAOiBatis extends BaseDAOiBatis implements MantenimientoRUVDocumentosContablesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getTipoDocumentoContable()
	 */
	public List getTipoDocumentoContable(){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getTipoDocumentoContable", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getLimitesDocumentosLegales(java.util.Map)
	 */
	public List getLimitesDocumentosLegales(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getLimitesDocumentosLegales", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getTotalDocumentosLegales(java.util.Map)
	 */
	public String getTotalDocumentosLegales(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getTotalDocumentosLegales", criteria).toString();
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getSinImpresionSinUnidades(java.util.Map)
	 */
	public List getSinImpresionSinUnidades(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getSinImpresionSinUnidades", criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#deleteRegistroUnicoVentasById(java.lang.String)
	 */
	public void deleteRegistroUnicoVentasById(String oidRUV){
		getSqlMapClientTemplate().delete("spusicc.ruv.MantenimientoRUVSQL.deleteRegistroUnicoVentasById",oidRUV);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getLegalesDuplicados(java.util.Map)
	 */
	public List getLegalesDuplicados(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getLegalesDuplicados", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getSinAsignarNumeroDocumento(java.util.Map)
	 */
	public List getSinAsignarNumeroDocumento(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getSinAsignarNumeroDocumento", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeGenerarDataDocumentosContables(java.util.Map)
	 */
	public void executeGenerarDataDocumentosContables(Map params) {
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeGenerarDataDocumentosContables", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getAsignarNulosPorDias(java.util.Map)
	 */
	public String getAsignarNulosPorDias(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.getAsignarNulosPorDias",criteria);
		return criteria.get("cantidad").toString();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getAsignarNulosPorRango(java.util.Map)
	 */
	public String getAsignarNulosPorRango(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.getAsignarNulosPorRango",criteria);
		return criteria.get("cantidad").toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeAsignarNulosPorDias(java.util.Map)
	 */
	public void executeAsignarNulosPorDias(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeAsignarNulosPorDias",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeAsignarNulosPorRango(java.util.Map)
	 */
	public void executeAsignarNulosPorRango(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeAsignarNulosPorRango",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getDescripcionAcceso(java.util.Map)
	 */
	public String getDescripcionAcceso(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getDescripcionAcceso",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getOidSegAcceso(java.util.Map)
	 */
	public String getOidSegAcceso(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getOidSegAcceso",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getDescripcionSubAcceso(java.util.Map)
	 */
	public String getDescripcionSubAcceso(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getDescripcionSubAcceso",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getOidSegSubAcceso(java.util.Map)
	 */
	public String getOidSegSubAcceso(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getOidSegSubAcceso",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getDocContablesLegales(java.util.Map)
	 */
	public List getDocContablesLegales(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getDocContablesLegales", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getDocContablesInternos(java.util.Map)
	 */
	public List getDocContablesInternos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getDocContablesInternos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeGenerarDataDocumentosLegalDuplicado(java.util.Map)
	 */
	public List executeGenerarDataDocumentosLegalDuplicado(Map params){
		this.getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeGenerarDataDocumentosLegalDuplicado", params);
		return getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getDocLegalDuplicadosList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getDescCanal(java.util.Map)
	 */
	public String getDescCanal( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getDescCanal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getOidCanalxCod(java.util.Map)
	 */
	public String getOidCanalxCod(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getOidCanalxCod", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#deleteDocumentoLegales(java.util.Map)
	 */
	public void deleteDocuLegalesLimites(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ruv.MantenimientoRUVSQL.deleteDocuLegalesLimites", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#deleteDocuLegales(java.util.Map)
	 */
	public void deleteDocuLegales(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ruv.MantenimientoRUVSQL.deleteDocuLegales", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#deleteDocuInternos(java.util.Map)
	 */
	public void deleteDocuInternos(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ruv.MantenimientoRUVSQL.deleteDocuInternos", criteria);
	}

	public String getIndicadorRUVEliminarDocumentoContable(String codigoPais) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getIndicadorRUVEliminarDocumentoContable", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getIndicadorActivacionDocumentoContable(java.util.Map)
	 */
	public String getIndicadorActivacionDocumentoContable(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getIndicadorActivacionDocumentoContable(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getIndicadorActivacionDocumentoContable", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getIndicadorActivacionDocumentoContable(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getLimitesControlDocumentosLegales(java.util.Map)
	 */
	public List getLimitesControlDocumentosLegales(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getLimitesControlDocumentosLegales(java.util.Map)");
		List resultado = getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getLimitesControlDocumentosLegales", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getLimitesControlDocumentosLegales(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getTotalControlDocumentosLegales(java.util.Map)
	 */
	public String getTotalControlDocumentosLegales(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getTotalControlDocumentosLegales(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getTotalControlDocumentosLegales", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getTotalControlDocumentosLegales(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getNumeroControlDocLeg(java.util.Map)
	 */
	public List getNumeroControlDocLeg(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getNumeroControlDocLeg(java.util.Map)");
		List resultado = getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getNumeroControlDocLeg", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getNumeroControlDocLeg(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeGenerarDataNumeroControlDuplicado(java.util.Map)
	 */
	public List executeGenerarDataNumeroControlDuplicado(Map params) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - executeGenerarDataNumeroControlDuplicado(java.util.Map)");
		this.getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeGenerarDataNumeroControlDuplicado", params);
		List resultado = getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getNumeroControlDuplicados", null);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - executeGenerarDataNumeroControlDuplicado(java.util.Map) - Resultado:"+resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getSinAsignarNumeroControl(java.util.Map)
	 */
	public List getSinAsignarNumeroControl(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getSinAsignarNumeroControl(java.util.Map)");
		List resultado = getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getSinAsignarNumeroControl", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getSinAsignarNumeroControl(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getAsignarNulosDiasPais(java.util.Map)
	 */
	public List getAsignarNulosDiasPais(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getAsignarNulosDiasPais(java.util.Map)");
		
		this.getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeAsignarNulosPorDiasPorPais", criteria);
		List resultado = getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getAsignarNulosDiasPais", null);
		
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getAsignarNulosDiasPais(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#asignarNulosDiasPais(java.util.Map)
	 */
	public void updateNulosDiasPais(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - updateNulosDiasPais(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.updateNulosDiasPais", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - updateNulosDiasPais(java.util.Map)");
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getValidarAsignarNulosDocLeg(java.util.Map)
	 */
	public String getValidarAsignarNulosDocLeg(Map criteria){
		log.info("Entro a MantenimientoRUVDocumentosContablesDAOiBatis - getValidarAsignarNulosDocLeg(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.ruv.MantenimientoRUVSQL.getValidarAsignarNulosDocLeg", criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesDAOiBatis - getAsignarNulosDiasPais(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getAsignarNulosPorDiasVenezuela(java.util.Map)
	 */
	public String getAsignarNulosPorDiasVenezuela(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.getAsignarNulosPorDiasVenezuela",criteria);
		return criteria.get("cantidad").toString();	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getAsignarNulosPorRangoVenezuela(java.util.Map)
	 */
	public String getAsignarNulosPorRangoVenezuela(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.getAsignarNulosPorRangoVenezuela",criteria);
		return criteria.get("cantidad").toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeAsignarNulosPorDiasVenezuela(java.util.Map)
	 */
	public void executeAsignarNulosPorDiasVenezuela(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeAsignarNulosPorDiasVenezuela",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#executeAsignarNulosPorRangoVenezuela(java.util.Map)
	 */
	public void executeAsignarNulosPorRangoVenezuela(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeAsignarNulosPorRangoVenezuela",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#getAsignarNulosDiasVenezuela(java.util.Map)
	 */
	public List getAsignarNulosDiasVenezuela(Map criteria) {
		this.getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.executeAsignarNulosPorDiasPorVenezuela", criteria);
		List resultado = getSqlMapClientTemplate().queryForList("spusicc.ruv.MantenimientoRUVSQL.getAsignarNulosDiasVenezuela", null);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.dao.MantenimientoRUVDocumentosContablesDAO#updateNulosDiasVenezuela(java.util.Map)
	 */
	public void updateNulosDiasVenezuela(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ruv.MantenimientoRUVSQL.updateNulosDiasVenezuela", criteria);
	}
	
}