package biz.belcorp.ssicc.web.spusicc.ruv.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ruv.form.ReporteRUVDocumentosContablesMantenimientoForm;

@ManagedBean
@SessionScoped
public class ReporteRUVDocumentosContablesMantenimientoAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 4426315811271032167L;
	private String tipoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRUVDocumentosContablesMantenimientoForm reporteForm = new ReporteRUVDocumentosContablesMantenimientoForm();
		return reporteForm;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = false;
		
	}	
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		
		if ("L".equals(this.tipoReporte))
			return "reporteRUVDocumentosLegales";
		else
			return "reporteRUVDocumentosInternos";
		
	}
	
		
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("ReporteRUVDocumentosContablesAction - prepareParameterMap");
		return params;			
		
	}


	
	/* GET - SET */
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}


	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
	
	
}
