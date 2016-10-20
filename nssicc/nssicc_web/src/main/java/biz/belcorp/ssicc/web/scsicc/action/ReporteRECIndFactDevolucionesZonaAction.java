package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECIndFactDevolucionesZonaForm;

/**
 * 
 * @author RRG
 * 
 **/
@ManagedBean
@SessionScoped
public class ReporteRECIndFactDevolucionesZonaAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECIndFactDevolucionesZonaForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		
		return "ReporteRECIndFactDevolucionesZonaXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECIndFactDevolucionesZonaForm f = (ReporteRECIndFactDevolucionesZonaForm) this.formReporte;
			
		log.debug("f.getCodigoPeriodoInicio(): "+f.getCodigoPeriodoInicio()+" f.getCodigoPeriodoFin(): "+f.getCodigoPeriodoFin());
		params.put("CodigoPeriodoInicio",f.getCodigoPeriodoInicio());
		params.put("CodigoPeriodoFin",f.getCodigoPeriodoFin());
		params.put("titulo", getResourceMessage("reporteRECIndFactDevolucionesZonaForm.title"));																		
	
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteRECIndFactDevolucionesZonaForm f = (ReporteRECIndFactDevolucionesZonaForm) this.formReporte;
		
		f.setCodigoPeriodoInicio(null);
		f.setCodigoPeriodoFin(null);
	}

	

	
}