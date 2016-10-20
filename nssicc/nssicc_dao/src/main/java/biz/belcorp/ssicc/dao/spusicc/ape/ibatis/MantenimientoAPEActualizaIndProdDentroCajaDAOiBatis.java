package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEActualizaIndProdDentroCajaDAO;
/**
 * @author Nicols Lpez Ramos
 *
 */
@Repository("spusicc.mantenimientoAPEActualizaIndProdDentroCajaDAO")
public class MantenimientoAPEActualizaIndProdDentroCajaDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEActualizaIndProdDentroCajaDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdCajaDentroDAO#getIndProductoCajaDentroList(java.util.Map)
	 */
	public List getIndProductoCajaDentroList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getIndProductoCajaDentroList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdCajaDentroDAO#getOidIndProductoCajaDentro(java.util.Map)
	 */
	public String getOidIndProductoCajaDentro( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidIndProductoCajaDentro", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdDentroCajaDAO#getProductoIndicadorDentroCajaList(java.util.Map)
	 */
	public List getProductoIndicadorDentroCajaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getProductoIndicadorDentroCajaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdDentroCajaDAO#getIndProdDentroCajaObject(java.util.Map)
	 */
	public List getIndProdDentroCajaObject(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getIndProdDentroCajaObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdDentroCajaDAO#updateIndicadorProducto(java.util.Map)
	 */
	public void updateIndicadorProducto(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateIndicadorProducto", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdDentroCajaDAO#getValidaExisteIndicadorCaja(java.util.Map)
	 */
	public String getValidaExisteIndicadorCaja(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaExisteIndicadorCaja", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEActualizaIndProdDentroCajaDAO#updateIndProductoDFCaja(java.util.Map)
	 */
	public void updateIndProductoDFCaja(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateIndProductoDFCaja", criteria);	
	}
	
}