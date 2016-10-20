package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCPrimSegPedDeudForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCCCPrimSegPedDeudAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 5094155516559014785L;
	private String tipoVista;
	private Integer oid_ejecu_repor = 0;
	private List siccSociedadList;
	private String periodoActual = null;

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
		ReporteCCCPrimSegPedDeudForm form = (ReporteCCCPrimSegPedDeudForm) this.formReporte;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.periodoActual = this.mPantallaPrincipalBean.getAnyoActualperiodo();
		form.setCodigoPeriodoInicial(this.periodoActual);
		if (StringUtils.isEmpty(form.getCodigoPeriodoInicial()))
			;
		form.setCodigoPeriodoInicial(periodo);
		form.setCodigoPeriodoFinal(this.periodoActual);
		if (StringUtils.isEmpty(form.getCodigoPeriodoFinal()))
			;
		form.setCodigoPeriodoFinal(periodo);
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
		String nombreReporte = null;
		if ("D".equals(this.tipoVista))
			nombreReporte = "reporteCCCPrimSegPedDeudDXLS";
		else
			nombreReporte = "reporteCCCPrimSegPedDeudSXLS";
		return nombreReporte;
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
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCPrimSegPedDeudForm form = (ReporteCCCPrimSegPedDeudForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (fecha1 > fecha2) {
			String mensaje = this
					.getResourceMessage("reporteRECIndFactVentasProductoForm.errorInicioMayor");
			return mensaje;
		}
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
		ReporteCCCPrimSegPedDeudForm form = new ReporteCCCPrimSegPedDeudForm();
		return form;
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
		ReporteCCCPrimSegPedDeudForm reporteCCCForm = (ReporteCCCPrimSegPedDeudForm) this.formReporte;
		this.tipoVista = reporteCCCForm.getTipoVista();
		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCPrimSegPedDeudService";
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the oid_ejecu_repor
	 */
	public Integer getOid_ejecu_repor() {
		return oid_ejecu_repor;
	}

	/**
	 * @param oid_ejecu_repor
	 *            the oid_ejecu_repor to set
	 */
	public void setOid_ejecu_repor(Integer oid_ejecu_repor) {
		this.oid_ejecu_repor = oid_ejecu_repor;
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
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual
	 *            the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}
}