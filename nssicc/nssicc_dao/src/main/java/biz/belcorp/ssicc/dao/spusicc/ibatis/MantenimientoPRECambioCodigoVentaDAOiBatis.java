/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO;
import biz.belcorp.ssicc.dao.spusicc.model.CodigoVentaMod;


/**
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Repository("spusicc.mantenimientoPRECambioCodigoVentaDAO")
public class MantenimientoPRECambioCodigoVentaDAOiBatis extends	BaseDAOiBatis implements MantenimientoPRECambioCodigoVentaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getTipoOfertaList(java.util.Map)
	 */
	public List getTipoOfertaList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getTipoOfertaList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getTipoVarianteList(java.util.Map)
	 */
	public List getTipoVarianteList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getTipoVarianteList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getFormaPagoList(java.util.Map)
	 */
	public List getFormaPagoList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getFormaPagoList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getTipoClienteList(java.util.Map)
	 */
	public List getTipoClienteList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getTipoClienteList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getSubTipoClienteList(java.util.Map)
	 */
	public List getSubTipoClienteList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getSubTipoClienteList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getTipoClasificacionList(java.util.Map)
	 */
	public List getTipoClasificacionList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getTipoClasificacionList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getClasificacionList(java.util.Map)
	 */
	public List getClasificacionList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getClasificacionList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getEstatusClienteList(java.util.Map)
	 */
	public List getEstatusClienteList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getEstatusClienteList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getOidVariante(java.util.Map)
	 */
	public String getOidVariante(Map map) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getOidVariante", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getCodFormaPago(java.util.Map)
	 */
	public String getCodFormaPago(Map map) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getCodFormaPago", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getCodigoVentaList(java.util.Map)
	 */
	public List getCodigoVentaList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getCodigoVentaList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#executeEliminarOferta(java.util.Map)
	 */
	public void executeEliminarOferta(Map map){
		getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.executeEliminarOferta",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#executeEliminarCUV(java.util.Map)
	 */
	public void executeEliminarCUV(Map map){
		getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.executeEliminarCUV",map);
	}
	
	
	public CodigoVentaMod getCodigoVentaObject(Map map){
		return (CodigoVentaMod)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getCodigoVentaObject",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#executeModificarDatosCUV(java.util.Map)
	 */
	public void executeModificarDatosCUV(Map map){
		getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.executeModificarDatosCUV",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#executeModificarDatosOferta(java.util.Map)
	 */
	public void executeModificarDatosOferta(Map map){
		getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.executeModificarDatosOferta",map);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#executeInsertVentaExclusiva(java.util.Map)
	 */
	public void executeInsertVentaExclusiva(Map map){
		getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.executeInsertVentaExclusiva", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#deleteVentaExclusiva(java.util.Map)
	 */
	public void deleteVentaExclusiva(Map map){
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteVentaExclusiva",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#executeCopiarOferta(java.util.Map)
	 */
	public void executeCopiarOferta(Map map){
		getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.executeCopiarOferta", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#deleteCUVTemporal(java.util.Map)
	 */
	public void deleteCUVTemporal(Map map){
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteCUVTemporal", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#insertaCUVTemporal(java.util.Map)
	 */
	public void insertaCUVTemporal(Map map){
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertaCUVTemporal", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getCodigoVentaModificarList(java.util.Map)
	 */
	public List getCodigoVentaModificarList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getCodigoVentaModificarList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getExisteCUV(java.util.Map)
	 */
	public Integer getExisteCUV(Map map) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoPRESQL.getExisteCUV", map);
		return valor;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getExisteCUVFacturado(java.util.Map)
	 */
	public Integer getExisteCUVFacturado(Map map) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoPRESQL.getExisteCUVFacturado", map);
		return valor;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getNumeroSecuenciaUsuario()
	 */
	public String getNumeroSecuenciaUsuario(){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getNumeroSecuenciaUsuario");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getEstrategiaList(java.util.Map)
	 */
	public List getEstrategiaList(Map map){
		  return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getEstrategiaList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getPeriodoActivo(java.util.Map)
	 */
	public String getPeriodoActivo(Map map){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getPeriodoActivo", map);
	}
	
	public void getExisteOfertasFacturados(Map map){
		getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getExisteOfertasFacturados", map);
    }
	
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getListGrupo(java.util.Map)
     */
    public Map getListGrupo(Map map){
    	return (Map)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getListGrupo",map);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getIndicadorCuadreList(java.util.Map)
	 */
	public List getIndicadorCuadreList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getIndicadorCuadreList",map);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#updateFactorCuadre(java.util.Map)
	 */
	public void updateFactorCuadre(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.MantenimientoPRESQL.updateFactorCuadre", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#updateFactorIndicadorCuadre(java.util.Map)
	 */
	public void updateFactorIndicadorCuadre(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.MantenimientoPRESQL.updateFactorIndicadorCuadre", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getCriteriosList(java.util.Map)
	 */
	public List getCriteriosList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getCriteriosList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getSeccionesList(java.util.Map)
	 */
	public Map getSeccionesList(Map map) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getSeccionesList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getTipoCuadreList(java.util.Map)
	 */
	public List getTipoCuadreList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getTipoCuadreList",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getCatalogoList(java.util.Map)
	 */
	public List getCatalogoList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getCatalogoList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#deleteCriterio(java.util.Map)
	 */
	public void deleteCriterio(Map map) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteCriterio",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#deletePromo(java.util.Map)
	 */
	public void deletePromo(Map map) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deletePromo",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#insertaCriterio(java.util.Map)
	 */
	public void insertaCriterio(Map map) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertaCriterio", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#insertaPromo(java.util.Map)
	 */
	public void insertaPromo(Map map) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertaPromo", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getComponentesList(java.util.Map)
	 */
	public List getComponentesList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getComponentesList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getProductoBySAP(java.util.Map)
	 */
	public String getProductoBySAP(Map map) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getProductoBySAP", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#insertHistoricoCUV(java.util.Map)
	 */
	public void insertHistoricoCUV(Map map) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertHistoricoCUV", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#updateFactorCuadreCondicion(java.util.Map)
	 */
	public void updateFactorCuadreCondicion(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.MantenimientoPRESQL.updateFactorCuadreCondicion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPRECambioCodigoVentaDAO#getCriterioById(java.util.Map)
	 */
	public Map getCriterioById(Map map) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getCriterioById",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#getPanelGrupos(java.util.Map)
	 */
	@Override
	public List getPanelGrupos(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getPanelGrupos",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#deleteProductoGrupoOfertaDetalle(java.util.Map)
	 */
	@Override
	public void deleteProductoGrupoOfertaDetalle(Map map) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteProductoGrupoMatrizFacturacion",map);
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteProductoGrupoOfertaDetalle",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#insertProductoGrupoOfertaDetalle(java.util.Map)
	 */
	@Override
	public void insertProductoGrupoOfertaDetalle(Map map) {
		String nextNumeroLineaOferta = (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getNextNumeroLineaOferta", map);
		map.put("numeroLineaOferta", nextNumeroLineaOferta);
		
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertProductoGrupoOfertaDetalle", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#updateProductoGrupoOfertaDetalle(java.util.Map)
	 */
	@Override
	public void updateProductoGrupoOfertaDetalle(Map map) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateProductoGrupoOfertaDetalle",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#getOidTipoOferta(java.util.Map)
	 */
	@Override
	public List getOidTipoOferta(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getOidTipoOferta",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#updateProductoPrincipalAsociadoOferta(java.util.Map)
	 */
	@Override
	public void updateProductoPrincipalAsociadoOferta(Map map) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateProductoPrincipalAsociadoOferta",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#insertProductoPrincipalAsociadoOferta(java.util.Map)
	 */
	@Override
	public void insertProductoPrincipalAsociadoOferta(Map map) {
		String nextNumeroLineaOferta = (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getNextNumeroLineaOferta", map);
		map.put("numeroLineaOferta", nextNumeroLineaOferta);
		
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertProductoPrincipalAsociadoOferta", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#getVentaExclusivaOferta(java.util.Map)
	 */
	@Override
	public List getVentaExclusivaOferta(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getVentaExclusivaOferta", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#deleteVentaExclusivaPorOidVentaExclusiva(java.util.Map)
	 */
	@Override
	public void deleteVentaExclusivaPorOidVentaExclusiva(Map map) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteVentaExclusivaPorOidVentaExclusiva",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#deleteOfertaIndividual(java.util.Map)
	 */
	@Override
	public void deleteOfertaIndividual(Map map) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteOfertaIndividual",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#insertPromoProduCompoTemporal(java.util.Map)
	 */
	@Override
	public void insertPromoProduCompoTemporal(Map map) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertPromoProduCompoTemporal",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#deletePromoProduCompoTemporal(java.util.Map)
	 */
	@Override
	public void deletePromoProduCompoTemporal(Map map) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deletePromoProduCompoTemporal",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO#getPromoProduCompoTemporalList(java.util.Map)
	 */
	@Override
	public List getPromoProduCompoTemporalList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getPromoProduCompoTemporalList", map);
	}
}