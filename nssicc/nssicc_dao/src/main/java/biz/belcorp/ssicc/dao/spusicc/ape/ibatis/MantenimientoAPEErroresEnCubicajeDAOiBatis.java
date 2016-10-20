package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEErroresEnCubicajeDAO;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPEErroresEnCubicajeDAO") 
public class MantenimientoAPEErroresEnCubicajeDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEErroresEnCubicajeDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#getConsultaErroresCubicajeList(java.util.Map)
	 */
	public List getConsultaErroresCubicajeList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getConsultaErroresCubicajeList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#eliminarErrorCubicaje(java.util.Map)
	 */
	public void eliminarErrorCubicaje(Map criteria) {
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateEstadoEliminaErrorCubicaje", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#getValidaValoresProducto(java.util.Map)
	 */
	public String getValidaValoresProducto(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaValoresProducto", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#getValidaValorUndMedVolProducto(java.util.Map)
	 */
	public String getValidaValorUndMedVolProducto(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaValorUndMedVolProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#getValidaValorAltoProducto(java.util.Map)
	 */
	public String getValidaValorAltoProducto(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaValorAltoProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#getValidaValorLargoProducto(java.util.Map)
	 */
	public String getValidaValorLargoProducto(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaValorLargoProducto", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#getValidaValorAnchoProducto(java.util.Map)
	 */
	public String getValidaValorAnchoProducto(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaValorAnchoProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEErroresEnCubicajeDAO#deleteErrorCubicaje(java.util.Map)
	 */
	public void deleteErrorCubicaje(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.deleteErrorCubicaje", criteria);
	}

}