package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionBalanceoSubLineaDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceoSubLinea;

/**
 * 
 * <p>
 * <a href="MantenimientoAPEConfiguracionBalanceoSubLineaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */
@Repository("spusicc.mantenimientoAPEConfiguracionBalanceoSubLineaDAO")
public class MantenimientoAPEConfiguracionBalanceoSubLineaDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEConfiguracionBalanceoSubLineaDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getConfiguracionBalanceoList(java.util.Map)
	 */
	public List getConfiguracionBlSublineaList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getConfiguracionBlSublineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoSubLineaDAO#getObtenerConfiguracionBalanceoSubLinea(java.util.Map)
	 */
	public ConfiguracionBalanceoSubLinea getObtenerConfiguracionBalanceoSubLinea(Map criteria){
		return (ConfiguracionBalanceoSubLinea)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerConfiguracionBalSubLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoSubLineaDAO#actualizaConfiguracionSubLinea(java.util.Map)
	 */
	public void actualizaConfiguracionSubLinea(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateConfiguracionSubLinea", criteria);
	}
	
}
