package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCierreProcesosCampannaDAO;


/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoCOMCierreProcesosCampannaDAO")
public class ProcesoCOMCierreProcesosCampannaDAOiBatis extends BaseDAOiBatis 
    implements ProcesoCOMCierreProcesosCampannaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCierreProcesosCampannaDAO#executeCierreProcesosCampanna(java.util.Map)
	 */
	public void executeCierreProcesosCampanna(Map criteria) {
		this.getSqlMapClientTemplate().delete("sisicc.ProcesosCOMSQL.executeCierreProcesosCampanna",criteria);
		
	}

	
}
