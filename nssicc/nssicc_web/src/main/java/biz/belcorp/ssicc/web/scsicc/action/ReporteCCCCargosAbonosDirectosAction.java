package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionCADService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCCargosAbonosDirectosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCCargosAbonosDirectosAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -2556777613129586134L;
	private String tipoReporte;
	private List siccSociedadList = new ArrayList();
	private List cccTiposCargosAbonosDiretosList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA: Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de lista para cargar los combos en el JSP
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Paiss
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		List listaTipoCAD = new ArrayList();

		MantenimientoCCCDigitacionCADService serviceCCC = (MantenimientoCCCDigitacionCADService) getBean("spusicc.mantenimientoCCCDigitacionCADService");
		listaTipoCAD = serviceCCC.getTiposCargoAbonoDirectos();

		this.cccTiposCargosAbonosDiretosList = listaTipoCAD;

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
			log.debug("JFA Entering 'prepareParameterMap' method");
		}

		ReporteCCCCargosAbonosDirectosForm reporteCCCCargosAbonosDirectosForm = (ReporteCCCCargosAbonosDirectosForm) this.formReporte;

		String fecha1, fecha2;

		fecha1 = DateUtil.getDate(reporteCCCCargosAbonosDirectosForm
				.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCCargosAbonosDirectosForm
				.getFechaHastaD());

		reporteCCCCargosAbonosDirectosForm.setFechaDesde(fecha1);
		reporteCCCCargosAbonosDirectosForm.setFechaHasta(fecha2);

		this.tipoReporte = reporteCCCCargosAbonosDirectosForm.getTipoReporte();

		if (log.isDebugEnabled()) {
			log.debug(params);
		}
		return params;
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
		return "reporteCCCCargosAbonosDirectos" + this.tipoReporte + "XLS";
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
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCCargosAbonosDirectosForm form = new ReporteCCCCargosAbonosDirectosForm();
		return form;
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

	/**
	 * @return the cccTiposCargosAbonosDiretosList
	 */
	public List getCccTiposCargosAbonosDiretosList() {
		return cccTiposCargosAbonosDiretosList;
	}

	/**
	 * @param cccTiposCargosAbonosDiretosList
	 *            the cccTiposCargosAbonosDiretosList to set
	 */
	public void setCccTiposCargosAbonosDiretosList(
			List cccTiposCargosAbonosDiretosList) {
		this.cccTiposCargosAbonosDiretosList = cccTiposCargosAbonosDiretosList;
	}
}