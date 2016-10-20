package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalculaPuntajeMultiMarcaDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCCalculaPuntajeMultiMarcaDAO")
public class ProcesoINCCalculaPuntajeMultiMarcaDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCalculaPuntajeMultiMarcaDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCalculaPuntajeMultiMarcaDAO#executeCalculaPuntajeMultiMarca(java.util.Map)
	 */
	public void executeCalculaPuntajeMultiMarca(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCalculaPuntajeMultiMarca",params);
		
	}

}
