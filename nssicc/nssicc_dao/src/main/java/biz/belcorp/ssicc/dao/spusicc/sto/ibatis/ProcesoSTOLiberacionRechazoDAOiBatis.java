package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOLiberacionRechazoDAO;

/**
 * Service con metodos para las consultas invocados por la pantalla de Liberacion de Rechazos
 * 
 * <p>
 * <a href="ProcesoSTOLiberacionRechazoDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
@Repository("spusicc.procesoSTOLiberacionRechazoDAO")
public class ProcesoSTOLiberacionRechazoDAOiBatis extends BaseDAOiBatis implements ProcesoSTOLiberacionRechazoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOLiberacionRechazoDAOiBatis#getProcesosEjecutadosRechazoDocumentoByCriteria(java.util.Map)
	 */
	public List getProcesosEjecutadosRechazoDocumentoByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getProcesosEjecutadosRechazoDocumentoByCriteria", params);
	}
	
	


}