package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECListaBlancaProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ListaBlancaProductos;

@Repository("spusicc.mantenimientoRECListaBlancaProductosDAO")
public class MantenimientoRECListaBlancaProductosDAOIbatis extends BaseDAOiBatis  implements
		MantenimientoRECListaBlancaProductosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#getListMotivoDevolucion()
	 */
	public List getListMotivoDevolucion() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListMotivoDevolucion");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#getListaBlancaProductosList(java.util.Map)
	 */
	public List getListaBlancaProductosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaBlancaProductosList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#getValidaListaBlancaProductos(java.util.Map)
	 */
	public int getValidaListaBlancaProductos(Map criteria) {
		int resultado = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidaListaBlancaProductos",criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#insertListaBlancaProductos(java.util.Map)
	 */
	public void insertListaBlancaProductos(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertListaBlancaProductos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#updateListaBlancaProductos(java.util.Map)
	 */
	public void updateListaBlancaProductos(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateListaBlancaProductos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#consultarListaBlancaProductos(java.util.Map)
	 */
	public ListaBlancaProductos consultarListaBlancaProductos(Map criteria) {
		return (ListaBlancaProductos)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.consultarListaBlancaProductos",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#deleteListaBlancaProductos(java.util.Map)
	 */
	public void deleteListaBlancaProductos(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.deleteListaBlancaProductos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#getValidaListaBlanca(java.util.Map)
	 */
	public int getValidaListaBlanca(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidaListaBlanca",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#getValidaRegionxZona(java.util.Map)
	 */
	public int getValidaRegionxZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidaRegionxZona",criteria);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECListaBlancaProductosDAO#getValidaCodigoCliente(java.lang.String)
	 */
	public Integer getValidaCodigoCliente(String value) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidaCodigoCliente",value);
	}

}
