package biz.belcorp.ssicc.dao.spusicc.mic.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mic.MantenimientoMICSegurosDAO;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoMICSegurosDAO")
public class MantenimientoMICSegurosDAOIbatis extends BaseDAOiBatis implements
															MantenimientoMICSegurosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getCronogramaMicroseguros(java.util.Map)
	 */
	public List getCronogramaMicroseguros(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getCronogramaMicroseguros",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getTipoOperacion()
	 */
	public List getTipoOperacion() {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getTipoOperacion");
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertCronograma(java.util.Map)
	 */
	public void insertCronograma(Map map) {
		getSqlMapClientTemplate().insert("spusicc.microseguro.MantenimientoMICSQL.insertCronograma",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateCronograma(java.util.Map)
	 */
	public void updateCronograma(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateCronograma",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertMicroseguros(java.util.Map)
	 */
	public void insertMicroseguros(Map map) {
		getSqlMapClientTemplate().insert("spusicc.microseguro.MantenimientoMICSQL.insertMicroseguros",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateMicroseguros(java.util.Map)
	 */
	public void updateMicroseguros(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateMicroseguros",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertCobertura(java.util.Map)
	 */
	public void insertCobertura(Map map) {
		getSqlMapClientTemplate().insert("spusicc.microseguro.MantenimientoMICSQL.insertCobertura",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateCobertura(java.util.Map)
	 */
	public void updateCobertura(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateCobertura",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertBancos(java.util.Map)
	 */
	public void insertBancos(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.insertBancos",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateBancos(java.util.Map)
	 */
	public void updateBancos(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateBancos",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertGrupos(java.util.Map)
	 */
	public void insertGrupos(Map map) {
		getSqlMapClientTemplate().insert("spusicc.microseguro.MantenimientoMICSQL.insertGrupos",map);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertGruposDetalle(java.util.Map)
	 */
	public void insertGruposDetalle(Map map) {
		getSqlMapClientTemplate().insert("spusicc.microseguro.MantenimientoMICSQL.insertGruposDetalle",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateGrupos(java.util.Map)
	 */
	public void updateGrupos(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateGrupos",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateGruposDetalle(java.util.Map)
	 */
	public void updateGruposDetalle(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateGruposDetalle",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getBancos(java.util.Map)
	 */
	public List getBancos(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getBancos",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getCobertura(java.util.Map)
	 */
	public List getCobertura(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getCobertura",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getGrupos(java.util.Map)
	 */
	public List getGrupos(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getGrupos",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getGruposDetalle(java.util.Map)
	 */
	public List getGruposDetalle(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getGruposDetalle",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getMicroseguros(java.util.Map)
	 */
	public List getMicroseguros(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getMicroseguros",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#deleteGruposDetalle(java.util.Map)
	 */
	public void deleteGruposDetalle(Map map) {
		getSqlMapClientTemplate().delete("spusicc.microseguro.MantenimientoMICSQL.deleteGruposDetalle",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getParametrosMicroSeguro()
	 */
	public Map getParametrosMicroSeguro() {
		Map map =(Map)getSqlMapClientTemplate().queryForObject("spusicc.microseguro.ProcesoMICSQL.getParametrosMicroSeguro");
		return map;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getCoberturaGrupos(java.util.Map)
	 */
	public List getCoberturaGrupos(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.microseguro.MantenimientoMICSQL.getCoberturaGrupos",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#insertCoberturaGrupos(java.util.Map)
	 */
	public void insertCoberturaGrupos(Map map) {
		getSqlMapClientTemplate().insert("spusicc.microseguro.MantenimientoMICSQL.insertCoberturaGrupos",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#updateCoberturaGrupos(java.util.Map)
	 */
	public void updateCoberturaGrupos(Map map) {
		getSqlMapClientTemplate().update("spusicc.microseguro.MantenimientoMICSQL.updateCoberturaGrupos",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.dao.MantenimientoMICSegurosDAO#getValidacionFechaInicioCronograma(java.util.Map)
	 */
	public Integer getValidacionFechaInicioCronograma(Map map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.microseguro.MantenimientoMICSQL.getValidacionFechaInicioCronograma", map);
	}
}
