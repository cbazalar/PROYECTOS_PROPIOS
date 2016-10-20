package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFacturacionForm;

/**
 * The Class ReporteSICFacturacionAction.
 * 
 * @autor: Belcorp
 * @version: 1.0 12/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICFacturacionAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -7372175913822687524L;
	private String tipoVista;
	private String tipoVenta;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List siccTipoVentaList = new ArrayList();

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICFacturacionForm form = new ReporteSICFacturacionForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICFacturacionForm form = (ReporteSICFacturacionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if (Constants.NUMERO_CERO.equals(tipoVenta)) {// venta neta
			if ("XLS".equals(this.formReporte.getFormatoExportacion())) {
				if (tipoVista.equals(Constants.SIC_TIPO_REPORTE_REGION))
					return "reporteSICFacturacionXLS";
				else
					return "reporteSICFacturacionZonasXLS";
			}
		} else {// venta catalogo
			if (StringUtils.equals(this.formReporte.getFormatoExportacion(), "XLS")) {
				if (tipoVista.equals(Constants.SIC_TIPO_REPORTE_REGION))
					return "reporteSICFacturacionCatalogoXLS";
				else
					return "reporteSICFacturacionZonasCatalogoXLS";
			}
		}
		
		return "reporteOCRMaestroHorizontalMM";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICFacturacionForm form = (ReporteSICFacturacionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if (Constants.NUMERO_CERO.equals(tipoVenta)) {// venta neta
			if (tipoVista.equals(Constants.SIC_TIPO_REPORTE_REGION)) {
				return "reporteSICFacturacionPDF";
			} else {
				return "reporteSICFacturacionZonasPDF";
			}
		} else {// venta catalogo
			if (tipoVista.equals(Constants.SIC_TIPO_REPORTE_REGION)) {
				return "reporteSICFacturacionCatalogoPDF";
			} else {
				return "reporteSICFacturacionZonasCatalogoPDF";
			}
		}
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap");
		}

		ReporteSICFacturacionForm reporteSICForm = (ReporteSICFacturacionForm) this.formReporte;

		log.debug(reporteSICForm.getFormatoExportacion());
		tipoVista = reporteSICForm.getTipoReporte();
		tipoVenta = reporteSICForm.getTipoVenta();

		String condicionRegion = obtieneCondicion(reporteSICForm.getCodigoRegion(), "zon.zorg_oid_regi", "'");
		String condicionRegion1 = obtieneCondicion(reporteSICForm.getCodigoRegion(), "reg.oid_regi", "'");
		String condicionZona = obtieneCondicion(reporteSICForm.getCodigoZona(),"zon.oid_zona", "'");
		String condicionZona1 = obtieneCondicion(reporteSICForm.getCodigoZona(), "zona.cod_zona", "'");

		Map criteria = params;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		String oidPeriodoAnterior = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodoAnterior", criteria);
		String desPeriodoAnterior = reporteService.getOidString(
				"getDesPeriodoByCodigoPeriodoAnterior", criteria);
		criteria.put("codigoPais", reporteSICForm.getCodigoPais());
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String indSolPedFactAn = pais.getIndicadorExcluirPedidosAnulados();

		criteria.put("codigoMarca", "T");
		String oidMarca = reporteService.getOidString("getOidMarcaByCodigoMarca", criteria);

		criteria.put("codigoCanal", "VD");
		String oidCanal = reporteService.getOidString("getOidCanalByCodigoCanal", criteria);

		// -- Logica regiones y zonas activas
		String condicionRegionAdicional = " and ( (zon.ind_acti=1 and zon.ind_borr=0) or (zon.ind_acti=0 and zon.ind_borr<>1) )";
		String condicionRegion1Adicional = " and ( (reg.ind_acti=1 and reg.ind_borr=0) or (reg.ind_acti=0 and reg.ind_borr<>1) )";
		String condicionZonaAdicional = " and ( (zon.ind_acti=1 and zon.ind_borr=0) or (zon.ind_acti=0 and zon.ind_borr<>1) )";
		String condicionZona1Adicional = " and ( (zona.ind_acti=1 and zona.ind_borr=0) or (zona.ind_acti=0 and zona.ind_borr<>1) )";

		params.put("NroReporte", "");
		params.put("condicion", condicionRegion + condicionRegionAdicional);
		params.put("condicion1", condicionRegion1 + condicionRegion1Adicional);

		params.put("condicionZona", condicionZona + condicionZonaAdicional);
		params.put("condicionZona1", condicionZona1 + condicionZona1Adicional);

		params.put("oidPais", oidPais);
		params.put("oidPeriodo", oidPeriodo);
		params.put("oidPeriodoAnterior", oidPeriodoAnterior);
		params.put("desPeriodoAnterior", desPeriodoAnterior);

		params.put("indSolPedFactAn", indSolPedFactAn);
		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);
		String titulo = "";

		if (tipoVista.equals(Constants.SIC_TIPO_REPORTE_REGION)) {
			if (Constants.NUMERO_CERO.equals(tipoVenta)) {// venta neta
				titulo = getResourceMessage("reporteSICFacturacionForm.titulo.region");
			} else {
				titulo = getResourceMessage("reporteSICFacturacionForm.titulo.region.catalogo");
			}
		} else {
			if (Constants.NUMERO_CERO.equals(tipoVenta)) {// venta neta
				titulo = getResourceMessage("reporteSICFacturacionForm.titulo.zona");
			} else {
				titulo = getResourceMessage("reporteSICFacturacionForm.titulo.zona.catalogo");
			}
		}
		
		if(reporteSICForm.getFechaInicioFacturacionDate() != null){
			reporteSICForm.setFechaInicioFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSICForm.getFechaInicioFacturacionDate()));
			params.put("fechaInicioFacturacion", reporteSICForm.getFechaInicioFacturacion());
		}
		
		if(reporteSICForm.getFechaFinFacturacionDate() != null){
			reporteSICForm.setFechaFinFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSICForm.getFechaFinFacturacionDate()));
			params.put("fechaFinFacturacion", reporteSICForm.getFechaFinFacturacion());
		}
		
		params.put("titulo", titulo
				+ " "
				+ reporteSICForm.getFechaInicioFacturacion()
				+ " "
				+ getResourceMessage("reporte.generico.al")
				+ " "
				+ reporteSICForm.getFechaFinFacturacion());

		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		
		ReporteSICFacturacionForm reporteSICForm = (ReporteSICFacturacionForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		List listaRegiones = reporteService.getListaGenerico("getRegionesOIDByPais", criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		
		int i = 0;
		
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((BaseOID) object).getDescripcion());
			labelValue.setValue(((BaseOID) object).getOid().toString());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}

		String periodoActual = reporteService.getStringGenerico("getPeriodoByFechaActual", criteriaOperacion);
		reporteSICForm.setCodigoPeriodo(periodoActual);
		
		criteriaOperacion.put("codigoPeriodo", periodoActual);
		List aux = reporteService.getListaGenerico("getFechaInicioPeriodoByPaisMarcaCanal", criteriaOperacion);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio;
		
		if (aux.size() > 0) {
			Base base = (Base) aux.get(0);
			fechaInicio = base.getCodigo();
		} else {
			fechaInicio = sdf.format(new Date(System.currentTimeMillis()));
		}

		reporteSICForm.setFechaFinFacturacionDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, sdf.format(new Date(System.currentTimeMillis() - 1))));
		reporteSICForm.setFechaInicioFacturacionDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, fechaInicio));
		loadComboVentas();
	}

	private void loadComboVentas() {
		List resultado = new ArrayList();
		Base[] mes = new Base[2];
		String ventaNeta = this
				.getResourceMessage("reporteSICFacturacionForm.ventaNeta");
		String ventaCatalogo = this
				.getResourceMessage("reporteSICFacturacionForm.ventaCatalogo");

		mes[0] = new Base();
		mes[0].setCodigo(Constants.NUMERO_CERO);
		mes[0].setDescripcion(ventaNeta);
		resultado.add(mes[0]);

		mes[1] = new Base();
		mes[1].setCodigo(Constants.NUMERO_UNO);
		mes[1].setDescripcion(ventaCatalogo);
		resultado.add(mes[1]);

		siccTipoVentaList = resultado;
	}

	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteSICFacturacionForm reporteSICForm = (ReporteSICFacturacionForm) this.formReporte;

		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegionOid(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,
				valor, Constants.FORMATEAR_TODOS);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}

		ReporteSICFacturacionForm form = (ReporteSICFacturacionForm) this.formReporte;
		
		if(form.getFechaInicioFacturacionDate() != null && form.getFechaFinFacturacionDate() != null){
			if(form.getFechaInicioFacturacionDate().compareTo(form.getFechaFinFacturacionDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		if(StringUtils.equals("RZ", form.getTipoReporte())){
			if(form.getCodigoRegion() == null || form.getCodigoRegion().length <= 0){
				return "Reporte de Zona - Debe seleccionar al menos una Region";
			}
		}
		
		return null;
	}

	public String getTipoVista() {
		return tipoVista;
	}

	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	public String getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public List getSiccTipoVentaList() {
		return siccTipoVentaList;
	}

	public void setSiccTipoVentaList(List siccTipoVentaList) {
		this.siccTipoVentaList = siccTipoVentaList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}
