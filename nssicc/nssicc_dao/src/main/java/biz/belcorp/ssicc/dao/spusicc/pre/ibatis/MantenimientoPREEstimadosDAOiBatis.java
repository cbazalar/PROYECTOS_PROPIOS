package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREEstimadosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizAlternativosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;

/**
 * 
 * @author Sigcomt
 *
 */
@Repository("spusicc.mantenimientoPREEstimadosDAO")
public class MantenimientoPREEstimadosDAOiBatis extends BaseDAOiBatis implements MantenimientoPREEstimadosDAO{

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREEstimadosDAO#getManPREEstimadosList(java.util.Map)
	 */
	public List getManPREEstimadosList(Map param) {		
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getManPREEstimadosList", param);
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREEstimadosDAO#deleteManPREEstimados(java.util.Map)
	 */	
	public void deleteManPREEstimados(Map param) {		
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteManPREEstimados",param);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREEstimadosDAO#getManPREEstimadosCatalogoList()
	 */
	public List getManPREEstimadosCatalogoList() {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getManPREEstimadosCatalogoList");
	}

	

	
	
	
	
	
}
