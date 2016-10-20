package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarOrdenAnaquelesDAO;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPECopiarOrdenAnaquelesDAO")
public class MantenimientoAPECopiarOrdenAnaquelesDAOiBatis extends BaseDAOiBatis implements MantenimientoAPECopiarOrdenAnaquelesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarOrdenAnaquelesDAO#getMapaZonaDefault(java.util.Map)
	 */
	public String getMapaZonaDefault(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMapaZonaDefault", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarOrdenAnaquelesDAO#getOidOrdenAnaquel(java.util.Map)
	 */
	public String getOidOrdenAnaquel(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidOrdenAnaqueles", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarOrdenAnaquelesDAO#getOrdenAnaquelList(java.util.Map)
	 */
	public List getOrdenAnaquelList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getOrdenAnaquelList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarOrdenAnaquelesDAO#executeGenerarOrdenAnaquelDetalle(java.util.Map)
	 */
	public void executeGenerarOrdenAnaquelDetalle(Map criteria) {
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGenerarOrdenAnaquelDet", criteria);
	}

}
