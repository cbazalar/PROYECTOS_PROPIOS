package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO;


@Repository("spusicc.mantenimientoPRETipoOfertaDAO")
public class MantenimientoPRETipoOfertaDAOiBatis extends BaseDAOiBatis implements MantenimientoPRETipoOfertaDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#getManPRETipoOfertaList(java.util.Map)
	 */
	public List getManPRETipoOfertaList(Map param) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getManPRETipoOfertaList", param);
	}


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#getManPRETipoOferta(java.util.Map)
	 */
	public Map getManPRETipoOferta(Map param) {
	  return (Map)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getManPRETipoOferta",param);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#insertManPRETipoOferta(java.util.Map)
	 */
	public void insertManPRETipoOferta(Map param) {		
	       getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertManPRETipoOferta", param);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#insertManPRETipoOfertaDetalle(java.util.Map)
	 */
	public void insertManPRETipoOfertaDetalle(Map param) {		  
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertManPRETipoOfertaDetalle", param);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#updateManPRETipoDetalle(java.util.Map)
	 */
	public void updateManPRETipoDetalle(Map param) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateManPRETipoOferta", param);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#updateManPRETipoOferta(java.util.Map)
	 */
	public void updateManPRETipoOferta(Map param) {
	  getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateManPRETipoOfertaDetalle", param);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#deleteManPRETipoOferta(java.util.Map)
	 */
	@Override
	public void deleteManPRETipoOferta(Map param) {
		
	 getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.deleteManPRETipoOferta", param);
	
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO#validaManPRETipoOferta(java.lang.String)
	 */
	@Override
	public int validaManPRETipoOferta(String codigo) {
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.validaManPRETipoOferta",codigo);
	}
	
	
}
