/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizRecuperacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacionAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;

/**
 * @author Sigcomt
 *
 */
@Repository("spusicc.mantenimientoPREMatrizRecuperacionesDAO")
public class MantenimientoPREMatrizRecuperacionesDAOiBatis extends BaseDAOiBatis implements MantenimientoPREMatrizRecuperacionesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizRecuperacionesDAO#getRecuperaciones(java.util.Map)
	 */
	public List getRecuperaciones(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getRecuperaciones", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizRecuperacionesDAO#updateRecuperacion(biz.belcorp.ssicc.spusicc.pre.model.MatrizRecuperacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRecuperacion(MatrizRecuperacion mr, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateRecuperacion", mr);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizRecuperacionesDAO#insertRecuperacionAuditoria(biz.belcorp.ssicc.spusicc.pre.model.MatrizRecuperacionAuditoria, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRecuperacionAuditoria(MatrizRecuperacionAuditoria audi, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertRecuperacionAuditoria", audi);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizRecuperacionesDAO#getProductoPREMatrizRecuperaciones(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ProductoMatriz getProductoPREMatrizRecuperaciones(String codigoPeriodo, String cuv, String flagRecuperacion) {
		
		Map params = new HashMap();
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("cuv", cuv);
		
		ProductoMatriz r = null;
		
		List lista = getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getProductoPREMatrizRecuperaciones", params);
				
		if(lista != null && lista.size() > 0)
		{
			r = (ProductoMatriz)lista.get(0);
			
			if(StringUtils.equals(flagRecuperacion, Constants.SI))
			{
				lista = getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getProductoPREMatrizRecuperacionesValida", params);
				
				if(lista != null && lista.size() > 0)
					r.setFlagRecuperableExiste(Constants.SI);
			}
		}

		return r;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizRecuperacionesDAO#insertRecuperacion(biz.belcorp.ssicc.spusicc.pre.model.MatrizRecuperacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRecuperacion(MatrizRecuperacion matrizRecuperacion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertRecuperacion", matrizRecuperacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizRecuperacionesDAO#getRecuperacionList(biz.belcorp.ssicc.spusicc.pre.model.MatrizRecuperacion)
	 */
	public List getRecuperacionList(MatrizRecuperacion matrizRecuperacion) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getRecuperacionList", matrizRecuperacion);
	}

}
