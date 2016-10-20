package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDDeterminarGanadorasCanastaLideresDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Determina Ganadoras de Canasta de Lideres
 * <p>
 * <a href="ProcesoLIDDeterminarGanadorasCanastaLideresDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDDeterminarGanadorasCanastaLideresDAO")
public class ProcesoLIDDeterminarGanadorasCanastaLideresDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDDeterminarGanadorasCanastaLideresDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDDeterminarGanadorasCanastaLideresDAO#executeDeterminarGanadorasCanastaLideres(java.util.Map)
	 */
	public void executeDeterminarGanadorasCanastaLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeDeterminarGanadorasCanastaLideres",params);
		
	}

}
