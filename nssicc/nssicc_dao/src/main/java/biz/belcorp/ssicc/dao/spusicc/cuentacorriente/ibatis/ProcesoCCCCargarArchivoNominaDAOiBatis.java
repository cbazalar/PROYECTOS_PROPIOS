/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarArchivoNominaDAO;

/**
 * @author Gonzalo Huertas
 *
 */
@Repository("spusicc.procesoCCCCargarArchivoNominaDAO")
public class ProcesoCCCCargarArchivoNominaDAOiBatis extends BaseDAOiBatis implements ProcesoCCCCargarArchivoNominaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarArchivoNominaDAO#executeCargarArchivoNomina(java.util.Map)
	 */
	public void executeProcesoCCCCargarArchivoNominaFTP() {
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeProcesoCCCCargarArchivoNominaFTP", null);
	}
	
	public void executeProcesoCCCCargarArchivoNominaMAIL() {
		getSqlMapClientTemplate().update("sisicc.InterfazCCCSQL.executeProcesoCCCCargarArchivoNominaMAIL", null);
	}

}
