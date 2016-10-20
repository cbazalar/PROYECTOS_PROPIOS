package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDDesvinculacionAutomaticaLideresDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Permite desasignar una lider de una 
 * seccion cuando cumpla con ciertos criterios
 * <p>
 * <a href="ProcesoLIDDesvinculacionAutomaticaLideresDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDDesvinculacionAutomaticaLideresDAO")
public class ProcesoLIDDesvinculacionAutomaticaLideresDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDDesvinculacionAutomaticaLideresDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDDesvinculacionAutomaticaLideresDAO#executeDesvinculacionAutomaticaLideres(java.util.Map)
	 */
	public void executeDesvinculacionAutomaticaLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeDesvinculacionAutomaticaLideres",params);
		
	}

}