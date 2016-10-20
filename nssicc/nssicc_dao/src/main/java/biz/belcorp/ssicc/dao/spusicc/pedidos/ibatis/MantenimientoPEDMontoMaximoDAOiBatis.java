package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDMontoMaximoDAO;

@Repository("spusicc.mantenimientoPEDMontoMaximoDAO")
public class MantenimientoPEDMontoMaximoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDMontoMaximoDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getOidPais(java.util.Map)
	 */
	public String getOidPais(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidPais", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getOidZonaByCodZona(java.util.Map)
	 */
	public String getOidZonaByCodZona(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidZonaByCodZona", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getOidRegionByCodRegion(java.util.Map)
	 */
	public String getOidRegionByCodRegion(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidRegionByCodRegion", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getRegionByZonaPais(java.util.Map)
	 */
	public String getOidRegionByCodZona(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidRegionByCodZona", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getNivelRiesgo()
	 */
	public List getNivelRiesgo() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getNivelRiesgo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getCountExisteMontoMaximo()
	 */
	public String getCountExisteMontoMaximo(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getCountExisteMontoMaximo", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * insertCarParamCarte(java.util.Map)
	 */
	public void insertCarParamCarte(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCarParamCarte", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * insertCarAsignCodigConfi(java.util.Map)
	 */
	public void insertCarAsignCodigConfi(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCarAsignCodigConfi", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * insertCarParamCarteAudit(java.util.Map)
	 */
	public void insertCarParamCarteAudit(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCarParamCarteAudit", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * insertCarAsignCodigConfiAudit(java.util.Map)
	 */
	public void insertCarAsignCodigConfiAudit(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCarAsignCodigConfiAudit", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getIdSgteCarParamCarte()
	 */
	public Long getIdSgteCarParamCarte() {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIdSgteCarParamCarte");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getIdSgteCarAsignCodigConfi()
	 */
	public Long getIdSgteCarAsignCodigConfi() {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIdSgteCarAsignCodigConfi");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getIdSgteCarParamCarteAudit()
	 */
	public Long getIdSgteCarParamCarteAudit() {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIdSgteCarParamCarteAudit");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getIdSgteCarAsignCodigConfiAudit()
	 */
	public Long getIdSgteCarAsignCodigConfiAudit() {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIdSgteCarAsignCodigConfiAudit");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getMontoMaximoList(java.util.Map)
	 */
	public List getMontoMaximoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getMontoMaximoList",criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * deleteCarParamParte(java.util.Map)
	 */
	public void deleteCarParamCarte(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCarParamCarte", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * deleteCarAsignCodigConfi(java.util.Map)
	 */
	public void deleteCarAsignCodigConfi(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCarAsignCodigConfi", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getCarParamCarte(java.util.Map)
	 */
	public List getCarParamCarte(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCarParamCarte", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * getCarAsignCodigConfi(java.util.Map)
	 */
	public List getCarAsignCodigConfi(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCarAsignCodigConfi", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#
	 * updateIndMontMaxi(java.util.Map)
	 */
	public void updateIndMontMaxi(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateIndMontMaxi", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMaximoDAO#updateRegMontMaxi(java.util.Map)
	 */
	public void updateRegMontMaxi(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateRegMontMaxi", criteria);
	}

	public List getCodigoRegionUA(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodigoRegionUA", criteria);
	}

	public List getCodigoZonaUA(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodigoZonaUA", criteria);
	}

	public List getCodigoSeccionUA(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodigoSeccionUA", criteria);
	}

}
