package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoDAO")
public class ProcesoINCRecepcionarTransaccionesPagoConcursoDAOIbatis extends BaseDAOiBatis implements
	ProcesoINCRecepcionarTransaccionesPagoConcursoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO#getListPagoConcurso()
	 */
	public List getListPagoConcurso() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListPagoConcurso");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO#getListMotivoPago()
	 */
	public List getListMotivoPago() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListMotivoPago");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO#insertTransaccionPagoConcurso(java.util.Map)
	 */
	public void insertTransaccionPagoConcurso(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.ProcesoINCSQL.insertTransaccionPagoConcurso", params);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO#executeActualizarTransaccionesPagoConcurso(java.util.Map)
	 */
	public void executeActualizarTransaccionesPagoConcurso(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActualizarTransaccionesPagoConcurso", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCRecepcionarTransaccionesPagoConcursoDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.ProcesoINCSQL.getNumeroTransaccionesPagoConcurso");
	}

}