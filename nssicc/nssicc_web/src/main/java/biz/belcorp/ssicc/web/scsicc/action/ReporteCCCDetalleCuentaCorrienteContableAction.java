package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalleCuentaCorrienteContableForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDetalleCuentaCorrienteContableAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -5419480483708353099L;
	private String tipoReporte;
	private List siccSociedadList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		this.mostrarReportePDF = false;
		this.mostrarReporteCSV = true;
		this.mostrarReporteXLS = true;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de lista para cargar los combos en el JSP
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Paiss
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());

		// Map para almacenar los parametros
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		ReporteCCCDetalleCuentaCorrienteContableForm f = (ReporteCCCDetalleCuentaCorrienteContableForm) this.formReporte;
		f.setMostrarBotonExcel(this.esVisibleBotonExcel(pais.getCodigo()));
		f.setFechaHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCDetalleCuentaCorrienteContableService";
	}

	/**
	 * @param codigoPais
	 * @return
	 */
	private boolean esVisibleBotonExcel(String codigoPais) {
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");

		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(codigoPais);
		parametroPais1.setCodigoSistema(Constants.SISTEMA_GEN);
		parametroPais1.setNombreParametro("mostrarBotonReporteXLS");
		parametroPais1.setIndicadorActivo("1");

		List lstParametros1 = genericoService1
				.getParametrosPais(parametroPais1);
		boolean activo = false;

		if (lstParametros1 != null && lstParametros1.size() > 0) {
			activo = true;
		}
		return activo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalleCuentaCorrienteContableForm form = new ReporteCCCDetalleCuentaCorrienteContableForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCDetalleCuentaCorrienteContable" + this.tipoReporte
				+ "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		// Preparando los parametros
		ReporteCCCDetalleCuentaCorrienteContableForm reporteCCCDetalleCuentaCorrienteContableForm = (ReporteCCCDetalleCuentaCorrienteContableForm) this.formReporte;

		String fecha1;
		fecha1 = DateUtil.getDate(reporteCCCDetalleCuentaCorrienteContableForm
				.getFechaHastaD());

		reporteCCCDetalleCuentaCorrienteContableForm.setFechaHasta(fecha1);

		reporteCCCDetalleCuentaCorrienteContableForm
				.setFormatoExportacion(this.formatoExportacion);
		this.tipoReporte = reporteCCCDetalleCuentaCorrienteContableForm
				.getTipoReporte();
		params.put("fechaHasta", fecha1);
		params.put("formatoReporte",
				reporteCCCDetalleCuentaCorrienteContableForm
						.getFormatoExportacion());
		params.put("tipoReporte", this.tipoReporte);
		return params;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
}