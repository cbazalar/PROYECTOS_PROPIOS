package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCCampanaDespachoDiferidaDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoINCCampanaDespachoDiferidaDAO")
public class MantenimientoINCCampanaDespachoDiferidaDAOIbatis extends BaseDAOiBatis implements
	MantenimientoINCCampanaDespachoDiferidaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoCampanaDespachoDiferidaDAO#getListConcursoDespachoDiferido()
	 */
	public List getListConcursoDespachoDiferido() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursoDespachoDiferido");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoCampanaDespachoDiferidaDAO#getNivelesConcurso(java.util.Map)
	 */
	public List getNivelesConcurso(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getNivelesConcurso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoCampanaDespachoDiferidaDAO#getPeriodoNivelDespachoDiferido(java.util.Map)
	 */
	public Map getPeriodoNivelDespachoDiferido(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject
			("spusicc.incentivos.MantenimientoINCSQL.getPeriodoNivelDespachoDiferido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoCampanaDespachoDiferidaDAO#insertNivelDespachoDiferido(java.util.Map)
	 */
	public void insertNivelDespachoDiferido(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.MantenimientoINCSQL.insertNivelDespachoDiferido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoCampanaDespachoDiferidaDAO#updateNivelDespachoDiferido(java.util.Map)
	 */
	public void updateNivelDespachoDiferido(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.updateNivelDespachoDiferido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCCampanaDespachoDiferidaDAO#deleteNivelDespachoDiferido(java.util.Map)
	 */
	public void deleteNivelDespachoDiferido(Map criteria) {
		getSqlMapClientTemplate().delete(
				"spusicc.incentivos.MantenimientoINCSQL.deleteNivelDespachoDiferido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCCampanaDespachoDiferidaDAO#getExistePeriodoNivelDespachoDiferido(java.util.Map)
	 */
	public boolean getExistePeriodoNivelDespachoDiferido(Map criteria) {
		String resultado = (String)getSqlMapClientTemplate().queryForObject
				("spusicc.incentivos.MantenimientoINCSQL.getExistePeriodoNivelDespachoDiferido", criteria);
		
		if (resultado.equals("0"))
			return false;
		else
			return true;
				
	}

}
