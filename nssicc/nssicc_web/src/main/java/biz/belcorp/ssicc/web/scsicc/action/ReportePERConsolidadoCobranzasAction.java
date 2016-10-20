//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERConsolidadoCobranzasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ReportePERConsolidadoCobranzasAction extends
		BaseReporteAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1042209734573068766L;
	private List siccSociedadList;
	private List siccBancoList;
	private LabelValue[] siccCuentaCorrienteList;
	private String tipoReporte;
	private Boolean cambiotipoVista;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERConsolidadoCobranzasForm reporteForm = new ReportePERConsolidadoCobranzasForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		ReportePERConsolidadoCobranzasForm form = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccSociedadList = service.getSociedadesByCodigoPais(codpais);
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		siccBancoList = service.getBancosByPais(criteria);

	}

	public String setValidarReporte() {
		ReportePERConsolidadoCobranzasForm form = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReportePERConsolidadoCobranzasForm f = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		String titulo = "";
		if (f.getTipoVista().equalsIgnoreCase(Constants.TIPO_VISTA_BANCO)) {
			tipoReporte = "Bancos";
			titulo = getReportResourceMessage("reportePERConsolidadoCobranzasForm.titulo.bancos");
		} else {
			tipoReporte = "Ctactes";
			titulo = getReportResourceMessage("reportePERConsolidadoCobranzasForm.titulo.ctactes");
		}
		params.put(
				"titulo",
				getReportResourceMessage("reportePERConsolidadoCobranzasForm.titulo")
						+ titulo);
		
		params.put("NroReporte", "");
		
		f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));
		f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaD()));
		
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		
		return params;
	}
	
	public void loadCuentasCorrientes(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadCuentasCorrientes");
		}
		String valor = (String) val.getNewValue();
		String[] valores = new String[1];
		valores[0] = valor;
		ReportePERConsolidadoCobranzasForm form = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccCuentaCorrienteList(ajaxService
					.getCuentasCorrientesPorPaisSociedadBanco(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), valor, form.getCodigoBanco()));
		}
	}
	
	public void loadCuentasCorrientes2(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadCuentasCorrientes");
		}
		
		
		
		String valor = (String) val.getNewValue();
		String[] valores = new String[1];
		valores[0] = valor;
		ReportePERConsolidadoCobranzasForm form = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccCuentaCorrienteList(ajaxService
					.getCuentasCorrientesPorPaisSociedadBanco(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), form.getCodigoSociedad(), valor));
		}
	}
	
	/**
	 * Metodo para Cambiar Tipo de Vista
	 * 
	 * @param val
	 */
	public void loadTipoVista(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoVista");
		}
		ReportePERConsolidadoCobranzasForm form = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		String valor = (String) val.getNewValue();
		if (valor.equals("1")) {
			this.setCambiotipoVista(true);
			form.setCodigoCuentaCorriente(null);
		} else if (valor.equals("2")) {
			this.setCambiotipoVista(false);
			
		}
	}
	


	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePERConsolidadoCobranzasForm form = (ReportePERConsolidadoCobranzasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteMaestroHorizontalCustom";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("Bancos".equals(this.tipoReporte))
			return "reportePERCobranzasBancos";
		else
			return "reportePERCobranzasCtactes";
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
	 * @return the siccCuentaCorrienteList
	 */
	public LabelValue[] getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList
	 *            the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(LabelValue[] siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
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
	 * @return the cambiotipoVista
	 */
	public Boolean getCambiotipoVista() {
		return cambiotipoVista;
	}

	/**
	 * @param cambiotipoVista the cambiotipoVista to set
	 */
	public void setCambiotipoVista(Boolean cambiotipoVista) {
		this.cambiotipoVista = cambiotipoVista;
	}


	
	

}
