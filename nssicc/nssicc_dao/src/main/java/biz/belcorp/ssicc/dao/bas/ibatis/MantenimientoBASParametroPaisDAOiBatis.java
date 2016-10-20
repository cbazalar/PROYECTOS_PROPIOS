package biz.belcorp.ssicc.dao.bas.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.bas.MantenimientoBASParametroPaisDAO;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * Implementacion de consultas del modulo de HiperConsulta
 * 
 * <p>
 * <a href="MantenimientoBASParametroPaisDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */
@Repository("bas.mantenimientoBASParametroPaisDAO")
public class MantenimientoBASParametroPaisDAOiBatis extends BaseDAOiBatis implements MantenimientoBASParametroPaisDAO {

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#getParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
     */
	public List getParametroPais(BASParametroPais basParametroPais) {
		List resultado = getSqlMapClientTemplate().queryForList("bas.MantenimientoBASSQL.getParametroPais", basParametroPais);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#insertParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void insertParametroPais(BASParametroPais basParametroPais) {
		getSqlMapClientTemplate().insert("bas.MantenimientoBASSQL.insertParametroPais", 
				basParametroPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#insertHistoricoParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void insertHistoricoParametroPais(BASParametroPais basParametroPais) {
		getSqlMapClientTemplate().insert("bas.MantenimientoBASSQL.insertHistoParametroPais", 
				basParametroPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#updateParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void updateParametroPais(BASParametroPais basParametroPais) {
		getSqlMapClientTemplate().update("bas.MantenimientoBASSQL.updateParametroPais", 
				basParametroPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoBASParametroPaisDAO#deleteParametroPais(biz.belcorp.ssicc.bas.model.BASParametroPais)
	 */
	public void deleteParametroPais(BASParametroPais basParametroPais) {
		getSqlMapClientTemplate().delete("bas.MantenimientoBASSQL.deleteParametroPais", 
				basParametroPais);
	}
	
}