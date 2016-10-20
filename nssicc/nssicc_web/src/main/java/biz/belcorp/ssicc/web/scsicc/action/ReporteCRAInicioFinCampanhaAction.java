package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.AccesoCanal;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCRAInicioFinCampanhaForm;

@ManagedBean
@SessionScoped
public class ReporteCRAInicioFinCampanhaAction extends
		BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6982513705857207905L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoListTodos;
	private int opcionImpresion;

	@Override
	protected void setViewAtributes() throws Exception {

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCRAInicioFinCampanhaForm f = (ReporteCRAInicioFinCampanhaForm) this.formReporte;

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		String periodoActual = reporteService.getStringGenerico(
				"getPeriodoByFechaActual", criteriaOperacion);
		f.setCodigoPeriodo(periodoActual);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// ################### LISTA MARCA ################
		this.siccMarcaList = svc.getMarcas();

		// ################### LISTA CANAL ################
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		// ################### LISTA ACCESO ################
		this.siccAccesoList = svc.getAccesosByCanalByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		List listaAcceso = new ArrayList();
		List b = new ArrayList();
		listaAcceso = svc.getAccesosByCanalByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		String e = "";
		for (int j = 0; j < listaAcceso.size(); j++) {
			e = ((AccesoCanal) siccAccesoList.get(j)).getCodigoCanal();
			if (e.equals(Constants.CODIGO_CANAL_DEFAULT)) {
				b.add(siccAccesoList.get(j));
			}

		}
		this.siccAccesoListTodos = b;
		f.setCodigoAcceso(null);

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCRAInicioFinCampanhaForm form = new ReporteCRAInicioFinCampanhaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCRAInicioFinCampanhaForm form = (ReporteCRAInicioFinCampanhaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			return "reporteMaestroHorizontal";
		} else
			return "";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			String nombre = "";
			switch (opcionImpresion) {
			case 1:
				nombre = "reporteCRAInicioFinCampanhaCompleto";
				break;
			case 2:
				nombre = "reporteCRAInicioFinCampanhaSinVenta";
				break;
			case 3:
				nombre = "reporteCRAInicioFinCampanhaSinFacturacion";
				break;
			}

			return nombre;
		} else
			return "";

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCRAInicioFinCampanhaForm form = (ReporteCRAInicioFinCampanhaForm) this.formReporte;
		if (form.getFechaInicioPeriodoFacturacionD() != null && form.getFechaFinalPeriodoFacturacionD()!= null) 
			if (form.getFechaFinalPeriodoFacturacionD().compareTo(
					form.getFechaInicioPeriodoFacturacionD()) < 0) {
				String mensaje = this.getResourceMessage("errors.compare.dates");
				return mensaje;
			}

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ReporteCRAInicioFinCampanhaForm reporteCRAForm = (ReporteCRAInicioFinCampanhaForm) this.formReporte;
		formatoReporte = reporteCRAForm.getFormatoExportacion();

		Map criteria = new HashMap();
		criteria.put("codigoPais", reporteCRAForm.getCodigoPais());
		criteria.put("codigoMarca", reporteCRAForm.getCodigoMarca());
		criteria.put("codigoCanal", reporteCRAForm.getCodigoCanal());
		criteria.put("codigoPeriodo", reporteCRAForm.getCodigoPeriodo());
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		params.put("anio", reporteCRAForm.getAnio());
		params.put("oidPeriodo", new Long(oidPeriodo));
		params.put(
				"oidPais",
				new Long(reporteService.getOidString("getOidPaisByCodigoPais",
						params)));
		// oid marca //oid canal
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);

		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);
		params.put("NroReporte", " ");
		params.put("titulo",
				getResourceMessage("reporteCRAInicioFinCampanhaForm.titulo"));
		params.put("condicionFechaInicio", "");
		params.put("condicionFechaFinal", "");

		// Recuperamos el idioma
		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
		String s = this.mPantallaPrincipalBean.getLocaleKey().getLanguage();
		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		Long oidIdiomaIso = new Long(reporteService.getOidString(
				"getOidIdiomaByCodigoIdiomaIso", parameterMap));
		params.put("oidIdioma", oidIdiomaIso);

		//
		this.opcionImpresion = new Integer(
				reporteCRAForm.getOpcionesImpresion());

		String fechaini ="";
		String fechafin="";
		if(reporteCRAForm
				.getFechaInicioPeriodoFacturacionD()!=null){
			fechaini=DateUtil.convertDateToString(reporteCRAForm
					.getFechaInicioPeriodoFacturacionD());
		}
		if(reporteCRAForm
				.getFechaFinalPeriodoFacturacionD()!=null){
			fechafin=DateUtil.convertDateToString(reporteCRAForm
					.getFechaFinalPeriodoFacturacionD());
		}
		if (this.opcionImpresion != 3) {
			if (StringUtils.isNotEmpty(fechaini)) {
				params.put(
						"condicionFechaInicio",
						"AND cra_crono.fec_inic > TO_DATE ( ' "
								+ fechaini
								+ "', 'dd/MM/yyyy')");
			}
			if (StringUtils.isNotEmpty(fechafin)) {
				params.put(
						"condicionFechaFinal",
						"AND cra_crono.fec_inic < TO_DATE ( ' "
								+ fechafin
								+ "', 'dd/MM/yyyy')");
			}
		}

		log.debug("Parametros Cargados");
		return params;
	}

	public void loadAcceso(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadAcceso");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String valor = (String) val.getNewValue();
		if (valor.equals("T")) {
			this.siccAccesoListTodos = svc
					.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
							.getCodigoISO());
		} else {
			this.siccAccesoList = svc.getAccesosByCanalByCodigoISO(usuario
					.getIdioma().getCodigoISO());

			List listaAcceso = new ArrayList();
			List b = new ArrayList();
			listaAcceso = svc.getAccesosByCanalByCodigoISO(usuario.getIdioma()
					.getCodigoISO());

			String e = "";
			for (int j = 0; j < listaAcceso.size(); j++) {
				e = ((AccesoCanal) siccAccesoList.get(j)).getCodigoCanal();
				if (e.equals(valor)) {
					b.add(siccAccesoList.get(j));
					
				}

			}
			this.siccAccesoListTodos = b;
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
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList
	 *            the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	/**
	 * @return the siccAccesoListTodos
	 */
	public List getSiccAccesoListTodos() {
		return siccAccesoListTodos;
	}

	/**
	 * @param siccAccesoListTodos
	 *            the siccAccesoListTodos to set
	 */
	public void setSiccAccesoListTodos(List siccAccesoListTodos) {
		this.siccAccesoListTodos = siccAccesoListTodos;
	}

	/**
	 * @return the opcionImpresion
	 */
	public int getOpcionImpresion() {
		return opcionImpresion;
	}

	/**
	 * @param opcionImpresion
	 *            the opcionImpresion to set
	 */
	public void setOpcionImpresion(int opcionImpresion) {
		this.opcionImpresion = opcionImpresion;
	}

}
