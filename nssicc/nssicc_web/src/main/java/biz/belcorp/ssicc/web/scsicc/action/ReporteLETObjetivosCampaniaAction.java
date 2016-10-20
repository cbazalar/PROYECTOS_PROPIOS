// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETObjetivosCampaniaForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLETObjetivosCampaniaAction extends
		BaseReporteAbstractAction implements Serializable {

	private String formatoReporte;
	private List siccRegionList;

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

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
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
		ReporteLETObjetivosCampaniaForm reporteForm = new ReporteLETObjetivosCampaniaForm();
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

		return "reporteLETObjetivosCampaniaXLS";
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
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteLETObjetivosCampaniaForm reporteForm = (ReporteLETObjetivosCampaniaForm) this.formReporte;
		reporteForm.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		// se carga el combo de regiones
		this.siccRegionList=reporteService.getListaGenerico(
				"getRegionesByPaisActivasNoActivas",
				criteriaOperacion);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteLETObjetivosCampaniaForm reporteForm = (ReporteLETObjetivosCampaniaForm) this.formReporte;

		
		String codigoRegion = obtieneCondicion(reporteForm.getCodigoRegion(), "RG.COD_REGI", "'");
		params.put("region", codigoRegion);
		params.put("campana", reporteForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("titulo", "");

		return params;

	}
}