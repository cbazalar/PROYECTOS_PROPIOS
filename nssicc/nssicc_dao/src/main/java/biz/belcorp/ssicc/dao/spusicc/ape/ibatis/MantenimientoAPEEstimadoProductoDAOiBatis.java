package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEstimadoProductoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstimadoProducto;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoCajaProducto;

/**
 * @author David Ramos
 *
 */
@Repository("spusicc.mantenimientoAPEEstimadoProductoDAO")
public class MantenimientoAPEEstimadoProductoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEEstimadoProductoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getLineaArmadobyOidCentro(java.util.Map)
	 */
	public List getLineaArmadobyOidCentro(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadobyOidCentro", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getCodLineaArmadaDefecto(java.util.Map)
	 */
	public String getCodLineaArmadaDefecto( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodLineaArmadaDefecto", criteria);
	}
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getEstimadoProductoList(java.util.Map)
      */
    public List getEstimadoProductoList(Map criteria){
     	return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getEstimadoProductoList", criteria);
    }


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getOidLineaArmadobyCodigo(java.util.Map)
	 */
	public String getOidLineaArmadobyCodigo(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidLineaArmadobyCodigo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#deleteEstimadoProducto(java.util.Map)
	 */
	public void deleteEstimadoProducto(Map criteria) {
		this.getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteEstimadoProducto", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getLineaArmadoComboList(java.util.Map)
	 */
	public List getLineaArmadoComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getLineaArmadoComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getCodLineaArmadaDefectoList(java.util.Map)
	 */
	public String getCodLineaArmadaDefectoList(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodLineaArmadaDefectoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getSubLineaArmadoObject(java.util.Map)
	 */
	public EstimadoProducto getSubLineaArmadoObject(Map criteria) {
		return (EstimadoProducto)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getSubLineaArmadoObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getNextOidEstimadoProducto()
	 */
	public int getNextOidEstimadoProducto() {
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidEstimadoProducto");
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#insertEstimadoProducto(java.util.Map)
	 */
	public void insertEstimadoProducto(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertEstimadoProducto",criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#updateEstimadoProducto(java.util.Map)
	 */
	public void updateEstimadoProducto(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateEstimadoProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getExisteLetraAnquelCD(java.util.Map)
	 */
	public int getExisteEstimadoProductoCD(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteEstimadoProductoCD", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getCodigoLineaArmadobyOid(java.util.Map)
	 */
	public String getCodigoLineaArmadobyOid(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getCodigoLineaArmadobyOid", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getCodigoLineaArmadobyOid(java.util.Map)
	 */
	public String getOidProductoByCodigoyPais(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidProductoByCodigoyPais", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPESubLineaArmadoDAO#getExisteLetraAnquelCD(java.util.Map)
	 */
	public int getExisteProductoCD(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteProductoCD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#getTipoCajaProductoObject(java.util.Map)
	 */
	public TipoCajaProducto getTipoCajaProductoObject(Map criteria){
		return (TipoCajaProducto)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getTipoCajaProductoObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEEstimadoProductoDAO#getProductoByCodSapyOidPaisObject(java.util.Map)
	 */
	public EstimadoProducto getProductoByCodSapyOidPaisObject(Map criteria){
		return (EstimadoProducto)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getProductoByCodSapyOidPaisObject", criteria);
	}
	
	
}