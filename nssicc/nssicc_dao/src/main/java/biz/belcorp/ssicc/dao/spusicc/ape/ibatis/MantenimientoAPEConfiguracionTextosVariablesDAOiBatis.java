package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionTextosVariablesDAO;

/**
 * @author David Ramos
 */
@Repository("spusicc.mantenimientoAPEConfiguracionTextosVariablesDAO")
public class MantenimientoAPEConfiguracionTextosVariablesDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEConfiguracionTextosVariablesDAO{
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getNextOidEstimadoProducto()
	 */
	public int getNextOidConfiguracionTextosVariables() {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidConfiguracionTextosVariables");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#insertEstimadoProducto(java.util.Map)
	 */
	public void insertConfiguracionTextosVariables(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertConfiguracionTextosVariables",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#updateEstimadoProducto(java.util.Map)
	 */
	public void updateConfiguracionTextosVariables(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateConfiguracionTextosVariables", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#deleteEstimadoProducto(java.util.Map)
	 */
	public void deleteConfiguracionTextosVariables(Map criteria) {
		this.getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteConfiguracionTextosVariables", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getTipoClienteList()
	 */
	public List getTipoClienteList(){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoClienteList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getSubTiClienteList(java.util.Map)
	 */
	public List getSubTiClienteList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubTiClienteList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getTipoClasificacionByOidSubTipoClienteList(java.util.Map)
	 */
	public List getTipoClasificacionByOidSubTipoClienteList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoClasificacionByOidSubTipoClienteList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getClasificacionByOidTipoClasificacionList(java.util.Map)
	 */
	public List getClasificacionByOidTipoClasificacionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getClasificacionByOidTipoClasificacionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getConfiTextoVariaList(java.util.Map)
	 */
	public List getConfiTextoVariaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getConfiTextoVariaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEConfiguracionTextosVariablesDAO#getExisteConfiguracionTextosVariablesCD(java.util.Map)
	 */
	public int getExisteConfiguracionTextosVariablesCD(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteConfiguracionTextosVariablesCD", criteria);
	}



	
	
}