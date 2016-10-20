package biz.belcorp.ssicc.web.scsicc.action;


import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteZONUnidadesGeograficasForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteZONUnidadesGeograficasAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2884591880905529644L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONUnidadesGeograficasForm reporteForm = new ReporteZONUnidadesGeograficasForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteZONUnidadesGeograficasAction - setViewAtributes");
		}

		this.mostrarReporteXLS = false;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;

	
		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteZONUnidadesGeograficasService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteZONUnidadesGeograficasForm form = (ReporteZONUnidadesGeograficasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteZONUnidadesGeoXLS";
		else
			if ("OCSV".equals(formReporte.getFormatoExportacion())) 
				return null;
			else
				return "reporteMaestroHorizontal";
		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteZONUnidadesGeograficasForm reporteINCForm = (ReporteZONUnidadesGeograficasForm) this.formReporte;
		formatoReporte = reporteINCForm.getFormatoExportacion();
		
		params.put("NroReporte", " ");
		params.put("titulo",getResourceMessage("reporteZONListaUnidadesAdministrativasForm.titulo"));
		
		return params;
	}


	

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	
}
