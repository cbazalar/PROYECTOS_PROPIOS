package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRankingDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que permite enviar  
 * los ranking de las lideres por camapana y periodo
 * <p>
 * <a href="ProcesoLIDEnviarRankingDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Repository("spusicc.procesoLIDEvaluarRankingDAO")
public class ProcesoLIDEvaluarRankingDAOIbatis extends BaseDAOiBatis implements 
					ProcesoLIDEvaluarRankingDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarRankingDAO#executeEvaluarRankingLideresCampana(java.util.Map)
	 */
	public void executeEvaluarRankingLideresCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRankingLideresCampana",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarRankingDAO#executeEvaluarRankingLideresPeriodo(java.util.Map)
	 */
	public void executeEvaluarRankingLideresPeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarRankingLideresPeriodo",params);		
	}

}