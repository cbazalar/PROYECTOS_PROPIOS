package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizAlternativosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;

/**
 * 
 * @author Sigcomt
 *
 */
@Repository("spusicc.mantenimientoPREMatrizAlternativosDAO")
public class MantenimientoPREMatrizAlternativosDAOiBatis extends BaseDAOiBatis implements MantenimientoPREMatrizAlternativosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizAlternativosDAO#getAlternativos(java.util.Map)
	 */
	public List getAlternativos(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getAlternativos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizAlternativosDAO#updateAlternativo(biz.belcorp.ssicc.spusicc.pre.model.MatrizAlternativo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAlternativo(MatrizAlternativo ma, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateAlternativo", ma);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizAlternativosDAO#insertAlternativoAuditoria(biz.belcorp.ssicc.spusicc.pre.model.MatrizAlternativoAuditoria, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAlternativoAuditoria(MatrizAlternativoAuditoria audi,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.insertAlternativoAuditoria", audi);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizAlternativosDAO#getProductoPREMatrizAlternativo(java.lang.String, java.lang.String)
	 */
	public ProductoMatriz getProductoPREMatrizAlternativo(String codigoPeriodo,
			String cuv) {
		Map params = new HashMap();
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("cuv", cuv);
		
		ProductoMatriz r = null;
		
		List lista = getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getProductoPREMatrizAlternativo", params);
		
		
		if(lista != null && lista.size() > 0)
		{
			r = (ProductoMatriz)lista.get(0);
		}

		return r;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizAlternativosDAO#getAlternativosList(biz.belcorp.ssicc.spusicc.pre.model.MatrizAlternativo)
	 */
	public List getAlternativosList(MatrizAlternativo matrizAlternativo) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getAlternativosList", matrizAlternativo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.dao.MantenimientoPREMatrizAlternativosDAO#insertRecuperacion(biz.belcorp.ssicc.spusicc.pre.model.MatrizAlternativo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAlternativo(MatrizAlternativo matrizAlternativo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertAlternativo", matrizAlternativo);
	}
	
	
	
	
	
	
	
	
}
