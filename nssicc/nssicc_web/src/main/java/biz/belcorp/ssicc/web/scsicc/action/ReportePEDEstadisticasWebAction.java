// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDEstadisticasWebForm;

/**
 * @author César Estrada
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReportePEDEstadisticasWebAction extends BaseReporteAbstractAction
		implements Serializable {

	private String tipoReporte;
	private List siccSubgerenciasList;

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List getSiccSubgerenciasList() {
		return siccSubgerenciasList;
	}

	public void setSiccSubgerenciasList(List siccSubgerenciasList) {
		this.siccSubgerenciasList = siccSubgerenciasList;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDEstadisticasWebForm reporteForm = new ReportePEDEstadisticasWebForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		return "reportePEDEstadisticasWeb" + tipoReporte + "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReportePEDEstadisticasWebForm f = (ReportePEDEstadisticasWebForm) this.formReporte;
		f.reset();
		

		log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReportePEDEstadisticasWebForm f = (ReportePEDEstadisticasWebForm) this.formReporte;

		this.tipoReporte = f.getTipoReporte();

		params.put("codigoPeriodo", f.getCodigoPeriodo());


		return params;

	}
}