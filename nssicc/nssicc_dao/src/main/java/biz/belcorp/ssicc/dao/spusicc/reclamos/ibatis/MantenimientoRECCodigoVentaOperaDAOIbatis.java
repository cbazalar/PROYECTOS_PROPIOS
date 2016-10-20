/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECCodigoVentaOperaDAO;


/**
 * @author Cristhian Roman
 * 
 */
@Repository("spusicc.mantenimientoRECCodigoVentaOperaDAO")
public class MantenimientoRECCodigoVentaOperaDAOIbatis extends		BaseDAOiBatis implements MantenimientoRECCodigoVentaOperaDAO {

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#getTipoOfertaList(java.util.Map)
	 */
	public List getTipoOfertaList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTipoOfertaList",map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#getCodigoCatalogoList(java.util.Map)
	 */
	public List getCodigoCatalogoList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoCatalogoList",map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#getCodigoVentaOperaList(java.util.Map)
	 */
	public List getCodigoVentaOperaList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoVentaOperaList",map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#deleteCodigoVentaOpera(java.util.Map)
	 */
	public void deleteCodigoVentaOpera(Map map){
		getSqlMapClientTemplate().delete(
                "spusicc.reclamos.ReclamosSQL.deleteCodigoVentaOpera", map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#insertCodigoVentaOpera(java.util.Map)
	 */
	public void insertCodigoVentaOpera(Map map){
		getSqlMapClientTemplate().insert(
				"spusicc.reclamos.ReclamosSQL.insertCodigoVentaOpera", map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#updateCodigoVentaOpera(java.util.Map)
	 */
	public void updateCodigoVentaOpera(Map map){
		getSqlMapClientTemplate().update(
				"spusicc.reclamos.ReclamosSQL.updateCodigoVentaOpera",
				map);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCodigoVentaOperaDAO#getValidaCodigoVentaOpera(java.util.Map)
	 */
	public int getValidaCodigoVentaOpera(Map criteria) {
		log.info("Entro a MantenimientoRECCodigoVentaOperaDAOIbatis - getValidaCodigoVentaOpera(java.util.Map)");
		int resultado = ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidaCodigoVentaOpera",criteria)).intValue();	
		log.info("Salio a MantenimientoRECCodigoVentaOperaDAOIbatis - getValidaCodigoVentaOpera(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
}	
