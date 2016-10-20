package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarAsignacionProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CopiarAsignarProductos;

/**
 * @author Nicols Lopez
 *
 */
@Repository("spusicc.mantenimientoAPECopiarAsignacionProductosDAO")
public class MantenimientoAPECopiarAsignacionProductosDAOiBatis extends BaseDAOiBatis implements MantenimientoAPECopiarAsignacionProductosDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarAsignacionProductosDAO#getCopiarAsignacionProductosList(java.util.Map)
	 */
	public List getCopiarAsignacionProductosList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCopiarAsignacionProductosList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarAsignacionProductosDAO#getValidaVersionByCDyPeriodo(java.util.Map)
	 */
	public String getValidaVersionByCDyPeriodo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaVersionByCDyPeriodo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarAsignacionProductosDAO#generaCopiaAsignacionProductosAnaqueles(java.util.Map)
	 */
	public void generaCopiaAsignacionProductosAnaqueles(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGeneraCopiaAsignarProductosAnaquel",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarAsignacionProductosDAO#getCopiarAsigProdAnaquelObject(java.util.Map)
	 */
	public CopiarAsignarProductos getCopiarAsigProdAnaquelObject(Map criteria){
		return (CopiarAsignarProductos)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCopiarAsigProdAnaquelObject",criteria);
	}
	
}