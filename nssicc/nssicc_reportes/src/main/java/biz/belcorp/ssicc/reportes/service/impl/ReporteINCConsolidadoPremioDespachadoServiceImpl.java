package biz.belcorp.ssicc.reportes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteINCConsolidadoPremioDespachadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteINCConsolidadoPremioDespachadoServiceImpl extends BaseReporteInterfaceImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944805260569907509L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.base.BaseReporte#beforeGrabarReporte(biz.belcorp.ssicc.reportes.framework.base.BaseReporteForm)
	 */
	public void beforeGrabarReporte(BaseReporteForm form) {
		log.debug("Entering 'beforeGrabarReporte' method");		
		super.beforeGrabarReporte(form);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.base.BaseReporte#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.base.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {		
		log.debug("Entering 'beforeExecuteReporte' method");		
		super.beforeExecuteReporte(reporteParams, formReporte);
		return reporteParams;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.base.BaseReporte#afterGrabarReporte(biz.belcorp.ssicc.reportes.framework.base.BaseReporteForm)
	 */
	public void afterGrabarReporte(BaseReporteForm form) throws Exception{
		
		log.debug("Entering 'afterGrabarReporte' method");		
		String key = "reporteINCConsolidadoPremioDespachadoForm.msg.envioMail";		
		
		if(form.isErrorEnvioEmail()){
			// Manejo de error
			/*
			key = getMessageErrorEmail();
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail",key));
			saveErrors(request, msg);
			*/
		}else{
			//Mensaje OK
			/*
		  	msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key));
			saveMessages(request, msg);
			*/
		}
	}
}
