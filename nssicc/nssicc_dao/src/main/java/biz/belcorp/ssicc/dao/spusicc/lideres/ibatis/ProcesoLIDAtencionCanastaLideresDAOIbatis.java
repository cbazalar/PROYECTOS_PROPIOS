package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDAtencionCanastaLideresDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que permite despachar 
 * los productos que forman la canasta de Lideres
 * <p>
 * <a href="ProcesoLIDAtencionCanastaLideresDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDAtencionCanastaLideresDAO")
public class ProcesoLIDAtencionCanastaLideresDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDAtencionCanastaLideresDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDAtencionCanastaLideresDAO#executeAtencionCanastaLideres(java.util.Map)
	 */
	public void executeAtencionCanastaLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeAtencionCanastaLideres",params);
		
	}

}