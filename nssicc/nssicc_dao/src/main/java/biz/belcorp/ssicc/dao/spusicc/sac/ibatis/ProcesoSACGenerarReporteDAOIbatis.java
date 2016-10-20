package biz.belcorp.ssicc.dao.spusicc.sac.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sac.ProcesoSACGenerarReporteDAO;
/**
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 *
 */
@Repository("spusicc.procesoSACGenerarReporteDAO")
public class ProcesoSACGenerarReporteDAOIbatis extends BaseDAOiBatis implements
				ProcesoSACGenerarReporteDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getListaCorreoReporteSACAsistenciaCompartamosEsika(java.util.Map)
	 */
	public List getListaCorreoReporteSACAsistenciaCompartamosEsika(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoReporteSACAsistenciaCompartamosEsika",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getListaCorreoReporteSACActivasSaldo(java.util.Map)
	 */
	public List getListaCorreoReporteSACActivasSaldo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoReporteSACActivasSaldo",criteria);
	}
	
	public List getListaCorreoZonaReportePEJProgramaEjecutivas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoZonaReportePEJProgramaEjecutivas",criteria);
	}
	
	public List getListaCorreoSeccionReportePEJProgramaEjecutivas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoSeccionReportePEJProgramaEjecutivas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getListaCorreoMAENovedadesZona(java.util.Map)
	 */
	public String getListaCorreoMAENovedadesZona(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoMAENovedadesZona",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getListaCorreoRegionReportePEJProgramaEjecutivas(java.util.Map)
	 */
	public List getListaCorreoRegionReportePEJProgramaEjecutivas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoRegionReportePEJProgramaEjecutivas",criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getListaCorreoFLXNovedadesZona(java.util.Map)
	 */
	public String getListaCorreoFLXNovedadesZona(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.sac.ProcesoSACGenerarReporteSQL.getListaCorreoFLXNovedadesZona",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getResultConsulorasHabiles(java.util.Map)
	 */
	public String getResultConsulorasHabiles(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.sac.ProcesoSACGenerarReporteSQL.getResultConsulorasHabiles", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#getDatosZonaRecuperacionCobranza(java.util.Map)
	 */
	public Map getDatosZonaRecuperacionCobranza(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.sac.ProcesoSACGenerarReporteSQL.getDatosZonaRecuperacionCobranza", criteria);
	}
	
	
}
