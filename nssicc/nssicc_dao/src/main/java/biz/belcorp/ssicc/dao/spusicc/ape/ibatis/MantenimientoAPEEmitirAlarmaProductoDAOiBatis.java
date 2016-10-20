package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEmitirAlarmaProductoDAO;

/**
 * @author Jose Luis Rodriguez
 */
@Repository("spusicc.mantenimientoAPEEmitirAlarmaProductoDAO")
public class MantenimientoAPEEmitirAlarmaProductoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEEmitirAlarmaProductoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaProductoDAO#getEmitirAlarmaProductoList(java.util.Map)
	 */
	public List getEmitirAlarmaProductoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getEmitirAlarmaProductoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaProductoDAO#getMailAlarmaProductoList(java.util.Map)
	 */
	public List getMailAlarmaProductoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMailAlarmaProductoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEmitirAlarmaProductoDAO#getOidPeriodoSiguiente(java.util.Map)
	 */
	public String getOidPeriodoSiguiente( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidPeriodoSiguiente", criteria);
	}
}