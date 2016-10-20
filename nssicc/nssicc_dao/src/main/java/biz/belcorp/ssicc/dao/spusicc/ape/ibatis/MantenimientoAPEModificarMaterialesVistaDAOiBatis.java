package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEModificarMaterialesVistaDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MaterialesVista;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEModificarMaterialesVistaDAO")
public class MantenimientoAPEModificarMaterialesVistaDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEModificarMaterialesVistaDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getTipoDispensacionList(java.util.Map)
	 */
	public List getTipoDispensacionList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoDispensacionList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getMaterialesVistaList(java.util.Map)
	 */
	public List getMaterialesVistaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMaterialesVistaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getMaterialesVistaObject(java.util.Map)
	 */
	public MaterialesVista getMaterialesVistaObject(Map criteria){
		return (MaterialesVista)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaterialesVistaObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getCodTipoCajaProductoList(java.util.Map)
	 */
	public List getCodTipoCajaProductoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCodTipoCajaProductoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#updateMaterialesVista(java.util.Map)
	 */
	public void updateMaterialesVista(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateMaterialesVista", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getOidTipoDispensacionByCodigo(java.util.Map)
	 */
	public String getOidTipoDispensacionByCodigo(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoDispensacionByCodigo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getOidTipoAnaquelbyCodigo(java.util.Map)
	 */
	public String getOidTipoAnaquelbyCodigo(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoAnaquelbyCodigo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getTipoAnaquelProductoList(java.util.Map)
	 */
	public List getTipoAnaquelProductoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoAnaquelProductoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#insertTipoAnaquelProducto(java.util.Map)
	 */
	public void insertTipoAnaquelProducto(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoAnaquelProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#deleteTipoAnaquelProducto(java.util.Map)
	 */
	public void deleteTipoAnaquelProducto(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoAnaquelProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#updateTipoAnaquelProducto(java.util.Map)
	 */
	public void updateTipoAnaquelProducto(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateTipoAnaquelProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#deleteTipoAnaquelbyOidProducto(java.lang.String)
	 */
	public void deleteTipoAnaquelbyOidProducto(String OidSap){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoAnaquelbyOidProducto", OidSap);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getExisteTipoAnaquelProducto(java.util.Map)
	 */
	public String getExisteTipoAnaquelProducto(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteTipoAnaquelProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getMaxNumeroPrioridad(java.util.Map)
	 */
	public int getMaxNumeroPrioridad(Map criteria){
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getMaxNumeroPrioridad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getOidProductoAPE(java.util.Map)
	 */
	public String getOidProductoAPE(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidProductoByCodigoyPais", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#validaProductos(java.util.Map)
	 */
	public void validaProductos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.validaProductos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getOidTipoCajaProducto(java.util.Map)
	 */
	public String getOidTipoCajaProducto(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoCajaProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#executeActulizaNumeroPrioridad(java.util.Map)
	 */
	public void executeActulizaNumeroPrioridad(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeActulizaNumeroPrioridad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEModificarMaterialesVistaDAO#getDesUnidadMedidaAPE(java.util.Map)
	 */
	public String getDesUnidadMedidaAPE(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDesUnidadMedidaAPE", criteria);
	}
}