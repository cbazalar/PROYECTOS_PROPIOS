/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.gis.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.gis.ReporteGISEnviarDireccionConsultorasDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteGISEnviarDireccionConsultorasDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Repository("spusicc.reporteGISEnviarDireccionConsultorasDAO")
public class ReporteGISEnviarDireccionConsultorasDAOIbatis extends
		BaseDAOiBatis implements ReporteGISEnviarDireccionConsultorasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gis.dao.ReporteGISEnviarDireccionConsultorasDAO#getEstadoDireccion()
	 */
	public List getEstadoDireccion() {
		return getSqlMapClientTemplate().queryForList("spusicc.gis.ReporteGISSQL.getEstadoDireccion", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gis.dao.ReporteGISEnviarDireccionConsultorasDAO#getEstructurasGeopoliticas(java.lang.String)
	 */
	public List getEstructurasGeopoliticas(String codigo) {
		return getSqlMapClientTemplate().queryForList("spusicc.gis.ReporteGISSQL.getEstructurasGeopoliticas", codigo);
	}
	
}
