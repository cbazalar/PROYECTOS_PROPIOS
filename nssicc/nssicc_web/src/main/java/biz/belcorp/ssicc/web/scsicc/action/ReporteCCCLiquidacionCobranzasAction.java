package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCLiquidacionCobranzasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCLiquidacionCobranzasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 2894483507447533619L;
	private String tipoNombreReporte;
	private List siccBancoList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

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

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		// Lista de Cuentas Corrientes Bancarias
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.siccBancoList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);

		ReporteCCCLiquidacionCobranzasForm f = (ReporteCCCLiquidacionCobranzasForm) this.formReporte;
		f.setMostrarBotonExcel(this.esVisibleBotonExcel(pais.getCodigo()));
		f.setCodigoPais(pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		f.setFechaProcDesdeD(new Date(System.currentTimeMillis()));
		f.setFechaProcHastaD(new Date(System.currentTimeMillis()));
		f.setFechaPagoDesdeD(new Date(System.currentTimeMillis()));
		f.setFechaPagoHastaD(new Date(System.currentTimeMillis()));
		
		f.setTipoVista("B");
		
		this.siccZonaList = new LabelValue[1];
		LabelValue todos = new LabelValue("Todos", "");
		this.siccZonaList[0] = todos;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCLiquidacionCobranzasForm form = (ReporteCCCLiquidacionCobranzasForm) this.formReporte;
		Date fecha1D, fecha2D, fecha3D, fecha4D;
		fecha1D = form.getFechaPagoDesdeD();
		fecha2D = form.getFechaPagoHastaD();
		fecha3D = form.getFechaProcDesdeD();
		fecha4D = form.getFechaProcHastaD();

		if (fecha1D != null && fecha2D != null && fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaPago");
			return mensaje;
		}

		if (fecha3D != null && fecha4D != null && fecha4D.compareTo(fecha3D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaProc");
			return mensaje;
		}
		return null;
	}

	public void cambiarTipoReporte(ValueChangeEvent val)
	{
		log.debug(">>cambiarTipoReporte " + val.getNewValue().toString());
		
		ReporteCCCLiquidacionCobranzasForm f = (ReporteCCCLiquidacionCobranzasForm) this.formReporte;
		
		f.setTipoVista(val.getNewValue().toString());
	}
	
	public void showZonasxRegion(ValueChangeEvent val) {

		log.debug(">>showZonasxRegion ");
		try {
			ReporteCCCLiquidacionCobranzasForm f = (ReporteCCCLiquidacionCobranzasForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();

			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								f.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL));
				f.setZonaList(null);
			} else {
				
				this.siccZonaList = new LabelValue[1];
				LabelValue todos = new LabelValue("Todos", "");
				this.siccZonaList[0] = todos;
				f.setZonaList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param codigoPais
	 * @return
	 */
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
			this.mostrarReporteXLS = true;
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
		ReporteCCCLiquidacionCobranzasForm form = new ReporteCCCLiquidacionCobranzasForm();
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
		return "reporteCCCLiquidacionCobranzas" + this.tipoNombreReporte
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
		ReporteCCCLiquidacionCobranzasForm reporteCCCLiquidacionCobranzasForm = (ReporteCCCLiquidacionCobranzasForm) this.formReporte;

		String fecha1, fecha2, fecha3, fecha4;
		fecha1 = DateUtil.getDate(reporteCCCLiquidacionCobranzasForm
				.getFechaProcDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCLiquidacionCobranzasForm
				.getFechaProcHastaD());
		fecha3 = DateUtil.getDate(reporteCCCLiquidacionCobranzasForm
				.getFechaPagoDesdeD());
		fecha4 = DateUtil.getDate(reporteCCCLiquidacionCobranzasForm
				.getFechaPagoHastaD());

		reporteCCCLiquidacionCobranzasForm.setFechaProcDesde(fecha1);
		reporteCCCLiquidacionCobranzasForm.setFechaProcHasta(fecha2);
		reporteCCCLiquidacionCobranzasForm.setFechaPagoDesde(fecha3);
		reporteCCCLiquidacionCobranzasForm.setFechaPagoHasta(fecha4);

		String codigoProceso = (Constants.RECAUDO_BANCARIO_PROCESO);
		String codigoSubproceso = "";

		if (reporteCCCLiquidacionCobranzasForm.getTipoAbono().equalsIgnoreCase(
				Constants.TIPO_RECAUDO_BANCARIO_TODOS)) {
			codigoSubproceso = null;
		} else if (reporteCCCLiquidacionCobranzasForm.getTipoAbono()
				.equalsIgnoreCase(Constants.TIPO_RECAUDO_BANCARIO_AUTOMATICO)) {
			codigoSubproceso = (Constants.RECAUDO_BANCARIO_AUTOMATICO_SUBPROCESO);
		} else if (reporteCCCLiquidacionCobranzasForm.getTipoAbono()
				.equalsIgnoreCase(Constants.TIPO_RECAUDO_BANCARIO_MANUAL)) {
			codigoSubproceso = (Constants.RECAUDO_BANCARIO_MANUAL_SUBPROCESO);
		} else {
			codigoSubproceso = (Constants.RECAUDO_BANCARIO_WEB_SUBPROCESO);
		}		

		log.debug("Tipo Abono");
		log.debug(codigoProceso);
		log.debug(codigoSubproceso);

		this.tipoNombreReporte = reporteCCCLiquidacionCobranzasForm
				.getTipoReporte()
				+ reporteCCCLiquidacionCobranzasForm.getTipoVista();

		params.put("codigoProceso", codigoProceso);
		params.put("codigoSubproceso", codigoSubproceso);
		params.put("tipoNombreReporte", this.tipoNombreReporte);
		params.put("condicionZona", "");
		params.put("condicionZona", "");
		
		params.put("fechaProcDesde", fecha1);
		params.put("fechaProcHasta", fecha2);
		params.put("fechaPagoDesde", fecha3);
		params.put("fechaPagoHasta", fecha4);

		if (reporteCCCLiquidacionCobranzasForm.getTipoVista().equals("Z")) {
			String condicionRegion = this.obtieneCondicion(
					reporteCCCLiquidacionCobranzasForm.getRegionList(),
					"zorg.COD_REGI", "'");
			String condicionZonas = this.obtieneCondicion(
					reporteCCCLiquidacionCobranzasForm.getZonaList(),
					"zzon.COD_ZONA", "'");
			String condicion = " " + condicionRegion + condicionZonas;
			params.put("condicionZona", condicion);
			params.put("codigoBanco", "");
			reporteCCCLiquidacionCobranzasForm.setCodigoBanco("");

		}
		if (reporteCCCLiquidacionCobranzasForm.getTipoVista().equals("L")) {
			params.put("codigoBanco", "");
			reporteCCCLiquidacionCobranzasForm.setCodigoBanco("");
		}
		return params;
	}

	/**
	 * @return the tipoNombreReporte
	 */
	public String getTipoNombreReporte() {
		return tipoNombreReporte;
	}

	/**
	 * @param tipoNombreReporte
	 *            the tipoNombreReporte to set
	 */
	public void setTipoNombreReporte(String tipoNombreReporte) {
		this.tipoNombreReporte = tipoNombreReporte;
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
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}