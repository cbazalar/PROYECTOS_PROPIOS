//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
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
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRRechazoBoletinComercialForm;

@ManagedBean
@SessionScoped
public class ReporteOCRRechazoBoletinComercialAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841250864214074144L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRRechazoBoletinComercialForm reporteForm = new ReporteOCRRechazoBoletinComercialForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReporteXLS = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteOCRRechazoBoletinComercialForm f = (ReporteOCRRechazoBoletinComercialForm) this.formReporte;
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
		f.setOidIdiomaIso(this.mPantallaPrincipalBean.getCurrentIdioma()
				.getCodigoISO());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		// Carga Fecha y Periodo
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		this.siccZonaList = new LabelValue[1];
		LabelValue labelValue = new LabelValue();
		labelValue.setLabel("Todos");
		labelValue.setValue("");
		this.siccZonaList[0] = labelValue;
	}

	@Override
	public String setValidarReporte() 
	{
		ReporteOCRRechazoBoletinComercialForm form = (ReporteOCRRechazoBoletinComercialForm) this.formReporte;
		
		if(form.getFechaDesdeD() == null && form.getFechaHastaD() == null){
    		if(StringUtils.isBlank(form.getCodigoPeriodo()))
    		{
    			String mensaje = "Ingresar Periodo";
    			return mensaje;
    		}
    	}
		
		if(StringUtils.isBlank(form.getCodigoPeriodo()))
		{
			if(form.getFechaDesdeD() == null  && form.getFechaHastaD() == null){
    			String mensaje = "Ingresar Fecha Inicio y Fecha Fin";
    			return mensaje;
    		}
    		
    		if(form.getFechaDesdeD() == null){
    			String mensaje ="Ingresar Fecha Inicio";
    			return mensaje;
    		}
    		
    		if(form.getFechaHastaD() == null){
    			String mensaje = "Ingresar Fecha Fin";
    			return mensaje;
    		}
		}
		
		if (form.getFechaHastaD() == null && form.getFechaDesdeD() != null) 
		{
			String mensaje = "Ingresar Fecha Fin";
			return mensaje;
		}
		
		if (form.getFechaHastaD() != null && form.getFechaDesdeD() == null) 
		{
			String mensaje = "Ingresar Fecha Inicio";
			return mensaje;
		}
		
		if (form.getFechaHastaD() != null && form.getFechaDesdeD() != null) 
		{
			if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) 
			{
				String mensaje = this.getResourceMessage("reporteRETResumenComisionVentaRetailForm.validar.fechas");
				return mensaje;
			}
		}
		
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		ReporteOCRRechazoBoletinComercialForm f = (ReporteOCRRechazoBoletinComercialForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		/*
		 * Map criteria = params;
		 * criteria.put("codigoPeriodo",f.getCodigoPeriodo()); String oidPeriodo
		 * =
		 * reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		 */
		String fechaDesde = "";
        String fechaHasta = "";
        if(f.getFechaHastaD()!=null && f.getFechaDesdeD()!=null){
        	fechaDesde=DateUtil.convertDateToString(f.getFechaDesdeD());
        	fechaHasta=DateUtil.convertDateToString(f.getFechaHastaD());
        }

		String condicionZonas = obtieneCondicion(f.getZonaList(),
				"substr(cc.uni_admi,3,4)", "'");
		String condicionRegion = obtieneCondicion(f.getRegionList(),
				"substr(cc.uni_admi,1,2)", "'");

		String condicionPeriodo = "";
		String condicionFechas = "";
		if (StringUtils.isNotEmpty(f.getCodigoPeriodo()))
			condicionPeriodo = " AND cc.cod_peri = '" + f.getCodigoPeriodo()
					+ "'";

		if (StringUtils.isNotEmpty(fechaDesde)
				&& StringUtils.isNotEmpty(fechaHasta)) {
			condicionFechas = " and trunc(dd.fec_modi) >= to_date('"
					+ fechaDesde + "','dd/MM/yyyy') "
					+ " and trunc(dd.fec_modi) <= to_date('" + fechaHasta
					+ "','dd/MM/yyyy')";
		}

		String condicion = condicionZonas + condicionRegion + condicionPeriodo
				+ condicionFechas;

		log.debug("condicion " + condicion);
		params.put("condicion", condicion);
		params.put("NroReporte", "");
		params.put("fechaDesde", fechaDesde);
		params.put("fechaHasta", fechaHasta);
		params.put(
				"titulo",
				getResourceMessage("reporteOCRRechazoBoletinComercialForm.title")
						+ "" + f.getCodigoPeriodo());

		return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteOCRRechazoBoletinComercialForm form = (ReporteOCRRechazoBoletinComercialForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteOCRRechazoBoletinComercialXLS";
		else
			return "reporteOCRRechazoBoletinComercialPDF";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		if (valor.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			this.siccZonaList = ajax
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);

		} else {

			setSiccZonaList(null);
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
