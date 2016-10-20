/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECCargarTablaBoletaRecojoEspecialDAO;


/**
 * @author Cristhian Roman
 *
 */
@Repository("spusicc.procesoRECCargarTablaBoletaRecojoEspecialDAO")
public class ProcesoRECCargarTablaBoletaRecojoEspecialDAOIbatis extends BaseDAOiBatis implements 
			ProcesoRECCargarTablaBoletaRecojoEspecialDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCargarTablaBoletaRecojoEspecialDAO#executeProcesoRECCargarTablaBoletaRecojoEspecial(java.util.Map)
	 */
	public void executeProcesoRECCargarTablaBoletaRecojoEspecial(Map params) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.executeProcesoRECCargarTablaBoletaRecojoEspecial",params);
		
	}
     
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCargarTablaBoletaRecojoEspecialDAO#executeVerificacionTablaBoletaRecojoEspecial(java.util.Map)
	 */
	public List executeVerificacionTablaBoletaRecojoEspecial(Map params){
		return  getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeVerificacionTablaBoletaRecojoEspecial",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECCargarTablaBoletaRecojoEspecialDAO#getDescripcionProducto(java.util.Map)
	 */
	public String getDescripcionProducto(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getDescripcionProducto", criteria);
	}
	
	public void insertTablaCodigosVenta(Map params){
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertTablaCodigosVenta", params);
	}
}
