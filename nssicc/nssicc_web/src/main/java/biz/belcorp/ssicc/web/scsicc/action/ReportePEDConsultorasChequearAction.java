//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDConsultorasChequearForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePEDConsultorasChequearAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841250864214074144L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private Date fechaini;
	private Date fechafin;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDConsultorasChequearForm reporteForm = new ReportePEDConsultorasChequearForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReporteXLS = true;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();


		// Carga de los Periodos
		ReportePEDConsultorasChequearForm reportePEDForm = (ReportePEDConsultorasChequearForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
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
		String periodoActual = reporteService.getStringGenerico("getPeriodoByFechaActual", criteriaOperacion);
		reportePEDForm.setCodigoPeriodo(periodoActual);
		criteriaOperacion.put("codigoPeriodo", periodoActual);
		List aux = reporteService.getListaGenerico(	"getFechaInicioPeriodoByPaisMarcaCanal", criteriaOperacion);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio;
		if (aux.size() > 0) {
			Base base = (Base) aux.get(0);
			fechaInicio = base.getCodigo();
		} else
			fechaInicio = sdf.format(new Date(System.currentTimeMillis()));

//		reportePEDForm.setFechaFinFacturacion(sdf.format(new Date(System.currentTimeMillis() - 1)));
//		reportePEDForm.setFechaInicioFacturacion(fechaInicio);
		
		// Carga Fecha y Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        String codPeriodo= service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
        reportePEDForm.setCodigoPeriodo(codPeriodo);
        reportePEDForm.setCodigoPais(pais.getCodigo());
        
        AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String fechaDesde = ajaxService
				.getFechaInicioPeriodoByPaisMarcaCanal(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, codPeriodo);
		String fechaHasta = ajaxService
				.getFechaFinalPeriodoByPaisMarcaCanal(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, codPeriodo);
		reportePEDForm.setFechaInicioFacturacionD(DateUtil.convertStringToDate(fechaDesde));
		reportePEDForm.setFechaFinFacturacionD(DateUtil.convertStringToDate(fechaHasta));
		
		this.fechaini=DateUtil.convertStringToDate(fechaDesde);
		this.fechafin=DateUtil.convertStringToDate(fechaHasta);

	}

	public String setValidarReporte() {
		ReportePEDConsultorasChequearForm form = (ReportePEDConsultorasChequearForm) this.formReporte;
		if (form.getFechaFinFacturacionD().compareTo(form.getFechaInicioFacturacionD()) < 0) {
			String mensaje = this
					.getResourceMessage("reporteRETResumenComisionVentaRetailForm.validar.fechas");
			return mensaje;
		}
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		ReportePEDConsultorasChequearForm f = (ReportePEDConsultorasChequearForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		
		String condicion = null;
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "A.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "A.COD_ZONA", "'");
		String titulo = getResourceMessage("reportePEDConsultorasChequearForm.title");
		
		condicion = condicionRegion + condicionZona;
		if(StringUtils.isBlank(condicion) || StringUtils.isEmpty(condicion)){
			condicion = null;
		}
		String fechaini=DateUtil.convertDateToString(f.getFechaInicioFacturacionD());
		String fechafin=DateUtil.convertDateToString(f.getFechaFinFacturacionD());
		params.put("fechaInicioFacturacion", fechaini);
		params.put("fechaFinFacturacion", fechafin);
		params.put("titulo", titulo);
		params.put("condicion", condicion);
		
		return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDConsultorasChequearForm form = (ReportePEDConsultorasChequearForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			  return "reportePEDConsultorasChequearXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reportePEDConsultorasChequearPDF";	
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

		}

	}
	
	/**
	 * Metodo para Cambiar el Rango de Fechas
	 * @param val
	 * @throws ParseException 
	 */
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}
		ReportePEDConsultorasChequearForm reporteForm = (ReportePEDConsultorasChequearForm) this.formReporte;
		try {
			if (valor.length() > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String fechaDesde = ajaxService
						.getFechaInicioPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				String fechaHasta = ajaxService
						.getFechaFinalPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				reporteForm.setFechaInicioFacturacionD(DateUtil.convertStringToDate(fechaDesde));
				reporteForm.setFechaFinFacturacionD(DateUtil.convertStringToDate(fechaHasta));
				
				this.fechaini=DateUtil.convertStringToDate(fechaDesde);
				this.fechafin=DateUtil.convertStringToDate(fechaHasta);
			}
		}
		catch (Exception e) {
			
		}
		
	}

	protected String devuelveBeanReporteService(){
		return "reportes.reportePEDConsultorasChequearService";
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

	/**
	 * @return the fechaini
	 */
	public Date getFechaini() {
		return fechaini;
	}

	/**
	 * @param fechaini the fechaini to set
	 */
	public void setFechaini(Date fechaini) {
		this.fechaini = fechaini;
	}

	/**
	 * @return the fechafin
	 */
	public Date getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechafin the fechafin to set
	 */
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	
	



}

