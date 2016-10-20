package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCMigracionPuntosConsultoraDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoINCMigracionPuntosConsultoraDAO")
public class ProcesoINCMigracionPuntosConsultoraDAOIbatis extends BaseDAOiBatis implements
	ProcesoINCMigracionPuntosConsultoraDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#getListConcursosMigracionPuntos()
	 */
	public List getListConcursosMigracionPuntos() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListConcursosMigracionPuntos");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#insertMigracionPuntosAmbito(java.util.Map)
	 */
	public void insertMigracionPuntosAmbito(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.ProcesoINCSQL.insertMigracionPuntosAmbito", params);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#insertMigracionPuntosConsultora(java.util.Map)
	 */
	public void insertMigracionPuntosConsultora(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.ProcesoINCSQL.insertMigracionPuntosConsultora", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#executeValidarMigracionPuntosConsultora(java.util.Map)
	 */
	public void executeValidarMigracionPuntosConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeValidarMigracionPuntosConsultora", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#getMigracionPuntosConsultoraList(java.util.Map)
	 */
	public List getMigracionPuntosConsultoraList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getMigracionPuntosConsultoraList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#executeActualizarMigracionPuntosConsultora(java.util.Map)
	 */
	public void executeActualizarMigracionPuntosConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActualizarMigracionPuntosConsultora", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMigracionPuntosConsultoraDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.ProcesoINCSQL.getNumeroMigracionPuntosConsultora");
	}

}