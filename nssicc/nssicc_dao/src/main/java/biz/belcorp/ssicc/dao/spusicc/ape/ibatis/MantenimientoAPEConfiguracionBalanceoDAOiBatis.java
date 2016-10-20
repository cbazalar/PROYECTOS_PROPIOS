package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionBalanceoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceo;

/**
 * 
 * <p>
 * <a href="MantenimientoAPEConfiguracionBalanceoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */
@Repository("spusicc.mantenimientoAPEConfiguracionBalanceoDAO")
public class MantenimientoAPEConfiguracionBalanceoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEConfiguracionBalanceoDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getConfiguracionBalanceoList(java.util.Map)
	 */
	public List getConfiguracionBalanceoList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getConfiguracionBalanceoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getFuncionDistribucionList(java.util.Map)
	 */
	public List getFuncionDistribucionList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getFuncionDistribucionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getOidFuncionDist(java.util.Map)
	 */
	public String getOidFuncionDist(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidFuncionDist", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#insertarConfiguracionBalanceo(java.util.Map)
	 */
	public void insertarConfiguracionBalanceo(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.insertarConfiguracionBalanceo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getValidaExisteConfiguracionBalanceo(java.util.Map)
	 */
	public String getValidaExisteConfiguracionBalanceo(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaExisteConfiguracionBalanceo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getConfiguracionBalanceoObject(java.util.Map)
	 */
	public ConfiguracionBalanceo getConfiguracionBalanceoObject(Map criteria){
		return (ConfiguracionBalanceo)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getConfiguracionBalanceoObject", criteria);
	}
	
	/* (non-Javadoc)   
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#eliminarConfiguracionBalanceo(java.util.Map)
	 */
	public void eliminarConfiguracionBalanceo(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.deleteConfiguracionBalanceo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionBalanceoDAO#getOidLineaArmadoxOidCD(java.util.Map)
	 */
	public String getOidLineaArmadoxOidCD(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidLineaArmadoxOidCD", criteria);
	}
	
}
