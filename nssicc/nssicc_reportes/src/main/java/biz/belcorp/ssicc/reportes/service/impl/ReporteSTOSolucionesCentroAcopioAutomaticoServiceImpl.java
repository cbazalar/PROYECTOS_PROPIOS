/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteSTOSolucionesCentroAcopioAutomaticoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTOSolucionesCentroAcopioAutomaticoServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857702131L;
		

	@Resource(name="spusicc.procesoSTOEjecucionValidacionesService")
	private ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService; 

	

	@Override
	public void beforeGrabarReporte(BaseReporteForm form) {
		super.beforeGrabarReporte(form);		
	}

	@Override
	public void afterGrabarReporte(BaseReporteForm form) throws Exception {
		super.afterGrabarReporte(form);	
		procesoSTOEjecucionValidacionesService.updateIndicadorEnvio(null);
	}
	


	
	
	
}
