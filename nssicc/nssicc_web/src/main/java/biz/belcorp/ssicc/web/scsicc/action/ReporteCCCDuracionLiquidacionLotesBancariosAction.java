package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDuracionLiquidacionLotesBancariosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDuracionLiquidacionLotesBancariosAction extends
		BaseReporteAbstractAction implements Serializable {

	private List siccBancoList = new ArrayList();
	private List cccTiposOrigenLotesBancariosList = new ArrayList();

	private static final long serialVersionUID = -7489728109755170357L;

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

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		// Lista de Cuentas Corrientes Bancarias
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

		this.siccBancoList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);
		this.cccTiposOrigenLotesBancariosList = serviceCCC
				.getTipoOrigenLotesBancarios();

		// SETEANDO VALORES POR DEFAULT A FECHAS
		ReporteCCCDuracionLiquidacionLotesBancariosForm form = (ReporteCCCDuracionLiquidacionLotesBancariosForm) this.formReporte;
		form.setFechaProcDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaProcHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCDuracionLiquidacionLotesBancariosForm form = (ReporteCCCDuracionLiquidacionLotesBancariosForm) this.formReporte;
		Date fecha3D, fecha4D;

		fecha3D = form.getFechaProcDesdeD();
		fecha4D = form.getFechaProcHastaD();

		if (fecha4D.compareTo(fecha3D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCDuracionLiquidacionLotesBancariosForm.errors.compare.fechaProc");
			return mensaje;
		}
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

		ReporteCCCDuracionLiquidacionLotesBancariosForm form = (ReporteCCCDuracionLiquidacionLotesBancariosForm) this.formReporte;

		if(form.getFechaProcDesdeD() != null){
			form.setFechaProcDesde(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaProcDesdeD()));
		}
		
		if(form.getFechaProcHastaD() != null){
			form.setFechaProcHasta(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaProcHastaD()));
		}
		
		params.put("fechaProcDesde", form.getFechaProcDesde());
		params.put("fechaProcHasta", form.getFechaProcHasta());

		return params;
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
		ReporteCCCDuracionLiquidacionLotesBancariosForm form = new ReporteCCCDuracionLiquidacionLotesBancariosForm();
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
		return "reporteCCCDuracionLiquidacionLotesBancariosXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/**
	 * @return the siccBancoList
	 */
	public List getSiccBancoList() {
		return siccBancoList;
	}

	/**
	 * @param siccBancoList
	 *            the siccBancoList to set
	 */
	public void setSiccBancoList(List siccBancoList) {
		this.siccBancoList = siccBancoList;
	}

	/**
	 * @return the cccTiposOrigenLotesBancariosList
	 */
	public List getCccTiposOrigenLotesBancariosList() {
		return cccTiposOrigenLotesBancariosList;
	}

	/**
	 * @param cccTiposOrigenLotesBancariosList
	 *            the cccTiposOrigenLotesBancariosList to set
	 */
	public void setCccTiposOrigenLotesBancariosList(
			List cccTiposOrigenLotesBancariosList) {
		this.cccTiposOrigenLotesBancariosList = cccTiposOrigenLotesBancariosList;
	}
}