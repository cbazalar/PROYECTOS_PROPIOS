package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECOperacionesReclamoPedidoForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECOperacionesReclamoPedidoAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 2902924560189411725L;

	private String formatoReporte;

	private String tipoReporte;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList = {};
	private List siccTipoPeriodoList = new ArrayList();
	private List siccTipoMovimientoList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ReporteRECOperacionesReclamoPedidoForm reporteRECform = (ReporteRECOperacionesReclamoPedidoForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		reporteRECform.setCodigoPeriodoInicial(service
				.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
						Constants.CODIGO_CANAL_DEFAULT));

		reporteRECform.setCodigoPeriodoFinal(service
				.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
						Constants.CODIGO_CANAL_DEFAULT));
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccOperacionesList = service
				.getOperacionesByCodigoPais(criteriaOperacion);

		ArrayList resultado = new ArrayList();
		Base[] tipo = new Base[2];
		String[] tipoPeriodo = {
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.referencia"),
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.registro") };
		for (int i = 0; i < tipo.length; i++) {
			tipo[i] = new Base();
			tipo[i].setCodigo("" + (i));
			tipo[i].setDescripcion(tipoPeriodo[i]);
			resultado.add(tipo[i]);
		}
		this.siccTipoPeriodoList = resultado;
		resultado = new ArrayList();
		tipo = new Base[2];
		String[] tipoMovimiento = {
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.devuelve"),
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.envia") };
		for (int i = 0; i < tipo.length; i++) {
			tipo[i] = new Base();
			tipo[i].setCodigo("" + (2 - i));
			tipo[i].setDescripcion(tipoMovimiento[i]);
			resultado.add(tipo[i]);
		}
		this.siccTipoMovimientoList = resultado;

		/* colocando fechas */
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String date = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,
				reporteRECform.getCodigoPeriodoInicial());
		// reporteRECform.setFechaFacDesde(date);
		String date2 = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,
				reporteRECform.getCodigoPeriodoFinal());
		// reporteRECform.setFechaFacHasta(date2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj1 = sdf.parse(date);
		reporteRECform.setFechaFacDesdeD(dateObj1);
		Date dateObj2 = sdf.parse(date2);
		reporteRECform.setFechaFacHastaD(dateObj2);
		reporteRECform.setCodigoPais(pais.getCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECOperacionesReclamoPedidoForm form = (ReporteRECOperacionesReclamoPedidoForm) this.formReporte;
		Integer fecha1, fecha2;
		Date fecha1D, fecha2D;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		fecha1D = form.getFechaFacDesdeD();
		fecha2D = form.getFechaFacHastaD();

		if (fecha1 > fecha2) {
			String mensaje = this
					.getResourceMessage("reporteRECOperacionesReclamoPedidoForm.validacionFechas");
			return mensaje;
		}
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("La Fecha Facturacion Inicio debe ser mayor a la Fecha Facturacion Final");
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
		ReporteRECOperacionesReclamoPedidoForm form = new ReporteRECOperacionesReclamoPedidoForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseSubReporteAbstractAction
	 * #obtieneCondicionIN(java.lang.String[], java.lang.String,
	 * java.lang.String)
	 */
	protected String obtieneCondicionIN(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato)
						|| StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + "";

			return resultado;
		}
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
		if ("XLS".equals(formatoReporte)) {
			return "reporteRECOperacionesReclamoPedido" + tipoReporte + "XLS";
		} else {
			if (tipoReporte.equals("Detalle"))
				return "reporteMaestroHorizontal";
			else
				return "reporteMaestroVertical";
		}
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
		if ("PDF".equals(formatoReporte))
			return "reporteRECOperacionesReclamoPedido" + tipoReporte + "PDF";
		else
			return "";
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
		ReporteRECOperacionesReclamoPedidoForm reporteRECForm = (ReporteRECOperacionesReclamoPedidoForm) this.formReporte;
		this.formatoReporte = reporteRECForm.getFormatoExportacion();
		Map criteria = new HashMap();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String secuencia = "";

		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteRECForm.getFechaFacDesdeD());
		fecha2 = DateUtil.getDate(reporteRECForm.getFechaFacHastaD());

		reporteRECForm.setFechaFacDesde(fecha1);
		reporteRECForm.setFechaFacHasta(fecha2);

		// Obtengo el oid del periodo inicial
		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoInicial());
		String oidPeriodoInicial = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		// Obtengo el oid del periodo final
		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoFinal());
		String oidPeriodoFinal = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		params.put("periodoInicial", reporteRECForm.getCodigoPeriodoInicial());
		params.put("periodoFinal", reporteRECForm.getCodigoPeriodoFinal());
		params.put("fechaDesde", reporteRECForm.getFechaFacDesde());
		params.put("fechaHasta", reporteRECForm.getFechaFacHasta());
		String condicionD = this.obtieneCondicionIN(
				reporteRECForm.getCodigoOperacion(), "", "");
		String condicionTipoOperacionD = this.obtieneCondicionIN(
				reporteRECForm.getTipoOperacionList(), "", "");

		if (reporteRECForm.getTipoReporte().equals("C")) {
			if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
				this.tipoReporte = "Referencia";
				params.put("oidPeriodoReferenciaInicial", oidPeriodoInicial);
				params.put("oidPeriodoReferenciaFinal", oidPeriodoFinal);

			} else {
				this.tipoReporte = "Registro";
				params.put("oidPeriodoRegistroInicial", oidPeriodoInicial);
				params.put("oidPeriodoRegistroFinal", oidPeriodoFinal);
			}
		} else {
			// Proceso de carga de informacin a tabla temporal
			this.tipoReporte = "Detalle";
			secuencia = reporteService
					.getObtenerSecuenciaTempOperaReclam(criteria);
			params.put("codigoPais", pais.getCodigo());
			params.put("oidPeriInicial", oidPeriodoInicial);
			params.put("oidPeriFinal", oidPeriodoFinal);
			params.put("oidSecuencia", secuencia);
			params.put("condicionD", condicionD);

			if (StringUtils.isNotBlank(condicionTipoOperacionD)) {
				params.put("flagTipoOpera", Constants.NUMERO_UNO);
				params.put("condicionTipoOperacionD", condicionTipoOperacionD);
			} else {
				params.put("flagTipoOpera", Constants.NUMERO_CERO);
				params.put("condicionTipoOperacionD", "");
			}
			reporteService.executeGenerarReporteRECOperaReclaPedidos(params);
		}
		String condicion = this.obtieneCondicion(
				reporteRECForm.getCodigoOperacion(), " REC_OPERA.COD_OPER ",
				"'");
		String condicionTipoOperacion = this.obtieneCondicion(
				reporteRECForm.getTipoOperacionList(),
				" REC_OPERA.COD_OPER || '-' || REC_TIPOS_OPERA.VAL_TIPO_OPER ",
				"'");

		params.put("NroReporte", "");
		params.put("condicion", condicion);
		params.put("condicionTipoOperacion", condicionTipoOperacion);
		params.put(
				"titulo",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.titulo"));
		params.put(
				"pedidosFacturados",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.pedidosFacturados"));
		params.put(
				"pedidosFaltante",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.pedidosFaltante"));
		params.put(
				"pedidosFaltanteSinMAV",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.pedidosFaltanteSinMAV"));

		params.put(
				"pedidosFaltanteSinMAVsinTO",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.pedidosFaltanteSinMAVsinTO"));
		params.put(
				"pedidosFaltanteSinMAVsinTOsinLBEL",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.pedidosFaltanteSinMAVsinTOsinLBEL"));
		params.put(
				"pedidosFaltanteSinMAVsinTO2",
				getReportResourceMessage("reporteRECOperacionesReclamoPedidoForm.pedidosFaltanteSinMAVsinTO2"));

		return params;
	}
	
	public void loadFechaPeriodoInicial(String val)
	{
		
		log.debug(">>showZonasxRegion ");
		try {
			ReporteRECOperacionesReclamoPedidoForm form = (ReporteRECOperacionesReclamoPedidoForm) this.formReporte;
			String periodoInicial = (String) val;//;.getNewValue();
			if (!val.equals(null) && periodoInicial.length() > 0) {
	
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String fecha=aSvc.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(), "T", "VD", periodoInicial);
				form.setFechaFacDesde(fecha);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date obj=sdf.parse(form.getFechaFacDesde());
				form.setFechaFacDesdeD(obj);
				
		
			} else {
				
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	
	public void loadFechaPeriodoFinal(String val)
	{
		
		log.debug(">>showZonasxRegion ");
		try {
			ReporteRECOperacionesReclamoPedidoForm form = (ReporteRECOperacionesReclamoPedidoForm) this.formReporte;
			String periodoFinal = (String) val;//.getNewValue();
			if (!val.equals(null) && periodoFinal.length() > 0) {
	
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String fecha=aSvc.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(), "T", "VD", periodoFinal);
				form.setFechaFacHasta(fecha);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date obj=sdf.parse(form.getFechaFacHasta());
				form.setFechaFacHastaD(obj);
		
			} else {
				
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	/**
	 * Obtener Lista de operaciones
	 * 
	 * @param val
	 */
	public void showTipoOperacionXOperacion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteRECOperacionesReclamoPedidoForm form = (ReporteRECOperacionesReclamoPedidoForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				ArrayList<String> listaValores = new ArrayList<String>(
						Arrays.asList(regiones));

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTipoOperacionList(aSvc
						.getTiposOperaMultipleByOpera(form.getCodigoPais(),
								listaValores, "T"));
				form.setTipoOperacionList(null);
			} else {
				siccTipoOperacionList = null;
				form.setTipoOperacionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 *            the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 *            the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return the siccTipoPeriodoList
	 */
	public List getSiccTipoPeriodoList() {
		return siccTipoPeriodoList;
	}

	/**
	 * @param siccTipoPeriodoList
	 *            the siccTipoPeriodoList to set
	 */
	public void setSiccTipoPeriodoList(List siccTipoPeriodoList) {
		this.siccTipoPeriodoList = siccTipoPeriodoList;
	}

	/**
	 * @return the siccTipoMovimientoList
	 */
	public List getSiccTipoMovimientoList() {
		return siccTipoMovimientoList;
	}

	/**
	 * @param siccTipoMovimientoList
	 *            the siccTipoMovimientoList to set
	 */
	public void setSiccTipoMovimientoList(List siccTipoMovimientoList) {
		this.siccTipoMovimientoList = siccTipoMovimientoList;
	}
}