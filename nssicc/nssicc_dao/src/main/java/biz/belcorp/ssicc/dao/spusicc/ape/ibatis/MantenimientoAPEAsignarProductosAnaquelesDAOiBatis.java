package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarProductosAnaquelesDAO;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEAsignarProductosAnaquelesDAO")
public class MantenimientoAPEAsignarProductosAnaquelesDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEAsignarProductosAnaquelesDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getProductosAnaquelesList(java.util.Map)
	 */
	public List getProductosAnaquelesList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getProductosAnaquelesList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getOidAsignacionVersion(java.util.Map)
	 */
	public String getOidAsignacionVersion(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidAsignacionVersion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#desasignarProductoAnaquel(java.util.Map)
	 */
	public void desasignarProductoAnaquel(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.desasignarProductoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getAnaquelesDestinoList(java.util.Map)
	 */
	public List getAnaquelesDestinoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getAnaquelesDestinoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#asignarProductoAnaquel(java.util.Map)
	 */
	public void asignarProductoAnaquel(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.asignarProductoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#validaProductoAnaquel(java.util.Map)
	 */
	public void validaProductoAnaquel(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.validaProductoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#intercambioProductoAnaquel(java.util.Map)
	 */
	public void intercambioProductoAnaquel(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.intercambioProductoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getOidProductoAsignacionDetalle(java.util.Map)
	 */
	public String getOidProductoAsignacionDetalle(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidProductoAsignacionDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getOidProductoByCodigoyPais(java.util.Map)
	 */
	public String getOidProductoByCodigoyPais(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidProductoByCodigoyPais", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getMapaCentroSubLineaList(java.util.Map)
	 */
	public String getMapaCentroSubLineaList(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMapaCentroSubLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getObtenerSubLineaxOidMapaCDDet(java.util.Map)
	 */
	public String getObtenerSubLineaxOidMapaCDDet(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerSubLineaxOidMapaCDDet", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarProductosAnaquelesDAO#getOidAsignacionProdAnaDet(java.util.Map)
	 */
	public String getOidAsignacionProdAnaDet(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidAsignacionProdAnaDet", criteria);
	}
}