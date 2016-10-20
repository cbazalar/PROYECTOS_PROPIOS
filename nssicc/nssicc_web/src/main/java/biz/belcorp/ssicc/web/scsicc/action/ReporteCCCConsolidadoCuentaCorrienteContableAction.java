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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCConsolidadoCuentaCorrienteContableForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCConsolidadoCuentaCorrienteContableAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 662436107750934915L;
	private String tipoReporte;
	private List siccSociedadList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCConsolidadoCuentaCorrienteContableForm form = new ReporteCCCConsolidadoCuentaCorrienteContableForm();
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
		return "reporteCCCConsolidadoCuentaCorrienteContable"
				+ this.tipoReporte + "XLS";
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
		ReporteCCCConsolidadoCuentaCorrienteContableForm reporteCCCForm = (ReporteCCCConsolidadoCuentaCorrienteContableForm) this.formReporte;

		this.tipoReporte = reporteCCCForm.getTipoReporte();
		
		if(reporteCCCForm.getFechaProcDesdeD() != null){
			reporteCCCForm.setFechaProcDesde(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteCCCForm.getFechaProcDesdeD()));
		}
		
		if(reporteCCCForm.getFechaProcHastaD() != null){
			reporteCCCForm.setFechaProcHasta(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteCCCForm.getFechaProcHastaD()));
		}

		params.put("fechaProcDesde", reporteCCCForm.getFechaProcDesde());
		params.put("fechaProcHasta", reporteCCCForm.getFechaProcHasta());

		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
		ReporteCCCConsolidadoCuentaCorrienteContableForm f = (ReporteCCCConsolidadoCuentaCorrienteContableForm) this.formReporte;
		f.setFechaProcDesdeD(new Date(System.currentTimeMillis()));

		f.setFechaProcHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCConsolidadoCuentaCorrienteContableForm form = (ReporteCCCConsolidadoCuentaCorrienteContableForm) this.formReporte;

		String vFechaDesde = DateUtil.getDate(form.getFechaProcDesdeD());
		String vFechaHasta = DateUtil.getDate(form.getFechaProcHastaD());

		if (!vFechaDesde.isEmpty() || !vFechaHasta.isEmpty()) {
			if (vFechaDesde.isEmpty() || vFechaHasta.isEmpty()) {
				return "Por favor complete ambas fechas de pago.";
			} else {
				if (vFechaDesde.compareTo(vFechaHasta) > 0) {
					return "Fecha 'Desde' debe ser menor a Fecha 'Hasta'";
				} else
					return null;
			}
		}
		return null;
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