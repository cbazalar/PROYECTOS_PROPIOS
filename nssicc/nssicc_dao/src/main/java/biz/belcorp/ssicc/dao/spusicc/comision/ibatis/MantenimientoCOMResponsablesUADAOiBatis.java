package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMResponsablesUADAO;


/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
@Repository("spusicc.mantenimientoCOMResponsablesUADAO")
public class MantenimientoCOMResponsablesUADAOiBatis extends BaseDAOiBatis implements MantenimientoCOMResponsablesUADAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#getListaResponsablesUA(java.util.Map)
	 */
	public List getListaResponsablesUA(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getListaResponsablesUA", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#updateResponsableUA(java.util.Map)
	 */
	public void updateResponsableUA(Map criteria){
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateResponsablesUA",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#getMaximoOidDesde(java.util.Map)
	 */
	public Integer getMaximoOidDesde(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getMaximoOidDesde", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#getMaximoOidHasta(java.util.Map)
	 */
	public Integer getMaximoOidHasta(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getMaximoOidHasta", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#getValidaResponsable(java.util.Map)
	 */
	public String getValidaResponsable(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getValidaResponsable", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#getCodigosSubGerenciaRegionByCodigoZona(java.lang.String)
	 */
	public Map getCodigosSubGerenciaRegionByCodigoZona(String codigoZona) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCodigosSubGerenciaRegionByCodigoZona", codigoZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#insertResponsableUA(java.util.Map)
	 */
	public void insertResponsableUA(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.comision.mantenimientoCOMSQL.insertResponsableUA", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMResponsablesUADAO#deleteResponsableUA(java.lang.Integer)
	 */
	public void deleteResponsableUA(Integer oidHistoGere) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteResponsableUA", oidHistoGere);
	}
}
