package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteZONListaUnidadesAdministrativasForm;

/**
 * @author César Estrada
 * @company Sigcomt
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteZONListaUnidadesAdministrativasAction extends
		BaseReporteAbstractAction implements Serializable {

	private String formatoReporte;
	private List siccSubgerenciasList;

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
	public List getSiccSubgerenciasList() {
		return siccSubgerenciasList;
	}

	/**
	 * @param siccSubgerenciasList
	 */
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
		ReporteZONListaUnidadesAdministrativasForm reporteForm = new ReporteZONListaUnidadesAdministrativasForm();
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
		if ("XLS".equals(formatoReporte))
			return "reporteZONListaUnidadesAdministrativasXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(this.formatoReporte)))
			return "reporteZONListaUnidadesAdministrativas";
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
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteriaOperacion.put("indicadorActivo", "1");
		criteriaOperacion.put("indicadorBorrado", "0");
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		this.siccSubgerenciasList=reporteService.getListaGenerico("getSubGerenciasByPaisMarcaCanal", criteriaOperacion);

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

		ReporteZONListaUnidadesAdministrativasForm reporteINCForm = (ReporteZONListaUnidadesAdministrativasForm) this.formReporte;
		this.formatoReporte = reporteINCForm.getFormatoExportacion();
		
		params.put("NroReporte", " ");
		params.put("titulo",getResourceMessage("reporteZONListaUnidadesAdministrativasForm.title"));
	
		return params;

	}
}