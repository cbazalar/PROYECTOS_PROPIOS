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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCDetalladoCuponesPagoForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCCCDetalladoCuponesPagoAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -6354031738581530682L;
	
	private String formatoReporte;
	private List siccRegionList;
	private List siccSituacionList;
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccZonaList = {};
	private Boolean cambioFiltroPeriodo;
	private Boolean cambioFiltroFecha;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalladoCuponesPagoForm form = new ReporteCCCDetalladoCuponesPagoForm();
		return form;
	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBReportePrimerosPedidosService";
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		this.siccSituacionList = reporteService.getListaGenerico(
				"getListaSituacionCuponesPago", criteria);
		this.cambioFiltroPeriodo = false;
		this.cambioFiltroFecha = false;

	}

	public String setValidarReporte() {
		ReporteCCCDetalladoCuponesPagoForm form = (ReporteCCCDetalladoCuponesPagoForm) this.formReporte;
		if (cambioFiltroPeriodo == true) {
			if (form.getCodigoPeriodoInicio().length()>0
					&& form.getCodigoPeriodoFin().length()>0) {
				int codperini = Integer.parseInt(form.getCodigoPeriodoInicio());
				int codperfin = Integer.parseInt(form.getCodigoPeriodoFin());
				if (codperfin < codperini) {
					String mensaje = "La Campaña Inicial debe ser menor o igual a la Campaña Final";
					return mensaje;
				}

			}else if(form.getCodigoPeriodoInicio().length()==0){
				String mensaje = "Debe Ingresar la Campaña Inicial";
				return mensaje;
			}else if(form.getCodigoPeriodoFin().length()==0){
				String mensaje = "Debe Ingresar la Campaña Final";
				return mensaje;
			}
		}else if(cambioFiltroFecha==true){
			if(form.getFechaInicioD()!=null && form.getFechaFinD()!=null){
				 if (form.getFechaFinD().compareTo(form.getFechaInicioD()) <0){
					 String mensaje = this.getResourceMessage("errors.compare.dates");
					 return mensaje;
				 }
			}else if(form.getFechaInicioD()==null){
				String mensaje = "Debe Ingresar la Fecha Desde";
				return mensaje;
			}else if(form.getFechaFinD()==null){
				String mensaje = "Debe Ingresar la Fecha Hasta";
				return mensaje;
			}
			
		}


		return null;
	}

	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String[] valores = (String[]) val.getNewValue();
		if (!val.equals(null) && valores.length > 0) {
			String[] regiones = (String[]) val.getNewValue();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones,
							Constants.FORMATO_TOTAL);

		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);

		}

	}

	//
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteCCCDetalladoCuponesPagoForm form = (ReporteCCCDetalladoCuponesPagoForm) this.formReporte;
		String[] regiones = (String[]) form.getRegionList();
		String[] zonas = (String[]) val.getNewValue();
		if (regiones.length > 0 && zonas.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionMultipleByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones, zonas,
							Constants.FORMATO_TOTAL));

		} else {
			setSiccSeccionList(null);

		}

	}

	/**
	 * Metodo para Cambiar Filtro
	 * 
	 * @param val
	 */
	public void loadFiltro(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadFiltro");
		}
		ReporteCCCDetalladoCuponesPagoForm form = (ReporteCCCDetalladoCuponesPagoForm) this.formReporte;
		String valor = (String) val.getNewValue();
		if (valor.equals("1")) {
			this.cambioFiltroPeriodo = true;
			this.cambioFiltroFecha = false;
			form.setFechaInicioD(null);
			form.setFechaFinD(null);
		} else if (valor.equals("2")) {
			this.cambioFiltroPeriodo = false;
			this.cambioFiltroFecha = true;
			form.setCodigoPeriodoInicio(null);
			form.setCodigoPeriodoFin(null);
		}
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCCCDetalladoCuponesPagoXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteCCCDetalladoCuponesPagoPDF";
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCDetalladoCuponesPagoForm f = (ReporteCCCDetalladoCuponesPagoForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		String fechaini = DateUtil.convertDateToString(f.getFechaInicioD());
		String fechafin = DateUtil.convertDateToString(f.getFechaFinD());
		f.setFechaInicio(fechaini);
		f.setFechaFin(fechafin);
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
		params.put("periodoInicio", f.getCodigoPeriodoInicio());
		params.put("periodoFin", f.getCodigoPeriodoFin());

		String condicionRegion = this.obtieneCondicion(f.getRegionList(),
				"ZR.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion" + condicionRegion.toString());

		String condicionZona = this.obtieneCondicion(f.getZonaList(),
				"ZZ.COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona" + condicionZona.toString());

		String condicionSeccion = this.obtieneCondicion(f.getSeccionList(),
				"ZS.COD_SECC", "'");
		params.put("condicionSeccion", condicionSeccion);
		log.debug("condicionSeccion" + condicionSeccion.toString());

		String condicionSituacion = this.obtieneCondicion(f.getSituacionList(),
				"d.sicu_oid_situ_cupo", "'");
		params.put("condicionSituacion", condicionSituacion);
		
		String msje=this.getResourceMessage("reporteCCCDetalladoCuponesPagoForm.titleReport");
		params.put(	"titulo",msje);
		f.setFormatoExportacion(formatoReporte);

		return params;
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
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
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
	 * @return the siccSituacionList
	 */
	public List getSiccSituacionList() {
		return siccSituacionList;
	}

	/**
	 * @param siccSituacionList
	 *            the siccSituacionList to set
	 */
	public void setSiccSituacionList(List siccSituacionList) {
		this.siccSituacionList = siccSituacionList;
	}

	/**
	 * @return the cambioFiltroPeriodo
	 */
	public Boolean getCambioFiltroPeriodo() {
		return cambioFiltroPeriodo;
	}

	/**
	 * @param cambioFiltroPeriodo
	 *            the cambioFiltroPeriodo to set
	 */
	public void setCambioFiltroPeriodo(Boolean cambioFiltroPeriodo) {
		this.cambioFiltroPeriodo = cambioFiltroPeriodo;
	}

	/**
	 * @return the cambioFiltroFecha
	 */
	public Boolean getCambioFiltroFecha() {
		return cambioFiltroFecha;
	}

	/**
	 * @param cambioFiltroFecha
	 *            the cambioFiltroFecha to set
	 */
	public void setCambioFiltroFecha(Boolean cambioFiltroFecha) {
		this.cambioFiltroFecha = cambioFiltroFecha;
	}

}
