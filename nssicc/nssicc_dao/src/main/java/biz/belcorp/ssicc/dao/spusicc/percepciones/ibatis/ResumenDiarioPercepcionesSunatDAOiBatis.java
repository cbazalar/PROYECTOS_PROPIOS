package biz.belcorp.ssicc.dao.spusicc.percepciones.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.percepciones.ResumenDiarioPercepcionesSunatDAO;


@Repository("spusicc.resumenDiarioPercepcionesSunatDAO")
public class ResumenDiarioPercepcionesSunatDAOiBatis extends BaseDAOiBatis implements  ResumenDiarioPercepcionesSunatDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.percepciones.ResumenDiarioPercepcionesSunatDAO#executeGenerarResumenDiarioPercepcionesSunat(java.util.Map)
	 */
	public void executeGenerarResumenDiarioPercepcionesSunat(Map parametros) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.executeGenerarResumenDiarioPercepcionesSunat", parametros);		
	}

}
