package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAplicarRequisitosPremiacionZonaDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Aplicar Requisitos Premiacion en Cierre de Zona
 *  
 * <p>
 * <a href="ProcesoINCAplicarRequisitosPremiacionZonaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCAplicarRequisitosPremiacionZonaDAO")
public class ProcesoINCAplicarRequisitosPremiacionZonaDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCAplicarRequisitosPremiacionZonaDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCAplicarRequisitosPremiacionDAO#executeAplicarRequisitosPremiacionZona(java.util.Map)
	 */
	public void executeAplicarRequisitosPremiacionZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeAplicarRequisitosPremiacionZona",params);
	}

}