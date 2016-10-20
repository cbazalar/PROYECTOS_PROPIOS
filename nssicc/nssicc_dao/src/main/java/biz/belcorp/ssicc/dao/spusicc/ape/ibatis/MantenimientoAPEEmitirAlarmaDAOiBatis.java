package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEmitirAlarmaDAO;

/**
 * @author David Ramos
 */
@Repository("spusicc.mantenimientoAPEEmitirAlarmaDAO")
public class MantenimientoAPEEmitirAlarmaDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEEmitirAlarmaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#updateEmitirAlarma(java.util.Map)
	 */
	public void updateEmitirAlarma(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateEmitirAlarma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getEmitirAlarmaList(java.util.Map)
	 */
	public List getEmitirAlarmaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getEmitirAlarmaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getMailEmitirAlarmaList(java.util.Map)
	 */
	public List getMailEmitirAlarmaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMailEmitirAlarmaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getOidPeriodobyMarcaCanal(java.util.Map)
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidPeriodobyMarcaCanal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getMapaZonaComboList(java.util.Map)
	 */
	public List getMapaZonaComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaZonaComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getCodigoMapaZona(java.util.Map)
	 */
	public String getCodigoMapaZona(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodigoMapaZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getExistePeriodobyMarcaCanal(java.util.Map)
	 */
	public int getExistePeriodobyMarcaCanal(Map criteria){
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExistePeriodobyMarcaCanal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#validaExisteFacturadosAlarma(java.util.Map)
	 */
	public String validaExisteFacturadosAlarma(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteFacturadosAlarma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getDescCentroDistribucion(java.util.Map)
	 */
	public String getDescCentroDistribucion(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescCentroDistribucion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getDescLineaArmado(java.util.Map)
	 */
	public String getDescLineaArmado(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescLineaArmado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaDAO#getDescMapaCentroCabec(java.util.Map)
	 */
	public String getDescMapaCentroCabec(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescMapaCentroCabec", criteria);
	}
}