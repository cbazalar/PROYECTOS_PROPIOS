/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;

/**
 * @author Danny Amaro
 *RreporteSTOSolucionesCentroAcopioManualServiceImpl
 */
@Service("reportes.reporteSTOSolucionesCentroAcopioManualService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTOSolucionesCentroAcopioManualServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857702131L;
		

//	@Resource(name="spusicc.procesoSTOEjecucionValidacionesService")
//	private ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService; 

	@Override
	public void beforeGrabarReporte(BaseReporteForm form) {
		super.beforeGrabarReporte(form);		
	}
	
}
