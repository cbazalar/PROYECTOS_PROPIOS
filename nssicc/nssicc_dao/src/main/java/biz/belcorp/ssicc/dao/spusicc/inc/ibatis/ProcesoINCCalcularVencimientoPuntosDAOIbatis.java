package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularVencimientoPuntosDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Vencimiento de Puntos
 *  
 * <p>
 * <a href="ProcesoINCCalcularVencimientoPuntosDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCCalcularVencimientoPuntosDAO")
public class ProcesoINCCalcularVencimientoPuntosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCalcularVencimientoPuntosDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularVencimientoPuntosDAO#executeCalcularVencimientoPuntos(java.util.Map)
	 */
	public void executeCalcularVencimientoPuntos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCalcularVencimientoPuntos",params);
	}

}