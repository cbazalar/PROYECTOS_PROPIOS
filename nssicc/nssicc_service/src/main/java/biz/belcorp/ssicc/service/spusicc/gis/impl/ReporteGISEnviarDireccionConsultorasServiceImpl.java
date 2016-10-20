/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.gis.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.gis.ReporteGISEnviarDireccionConsultorasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.gis.ReporteGISEnviarDireccionConsultorasService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteGISEnviarDireccionConsultorasServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("spusicc.reporteGISEnviarDireccionConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteGISEnviarDireccionConsultorasServiceImpl extends
		BaseService implements ReporteGISEnviarDireccionConsultorasService {

	@Resource(name = "spusicc.reporteGISEnviarDireccionConsultorasDAO")
	ReporteGISEnviarDireccionConsultorasDAO reporteGISEnviarDireccionConsultorasDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gis.service.ReporteGISEnviarDireccionConsultorasService#getEstadoDireccion()
	 */
	public List getEstadoDireccion() {
		return reporteGISEnviarDireccionConsultorasDAO.getEstadoDireccion();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gis.service.ReporteGISEnviarDireccionConsultorasService#getEstructurasGeopoliticas(java.lang.String)
	 */
	public List getEstructurasGeopoliticas(String codigo) {
		return reporteGISEnviarDireccionConsultorasDAO.getEstructurasGeopoliticas(codigo);
	}
	
}
