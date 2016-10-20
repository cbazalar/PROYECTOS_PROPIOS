package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAEEstadisticoClienteForm;

@ManagedBean
@SessionScoped
public class ReporteMAEEstadisticoClienteAction extends
		BaseReporteAbstractAction implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3356373114449522474L;
	private String formatoReporte;
	private String tipoReporte;
	private List siccRegionList;
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAEEstadisticoClienteForm form = new ReporteMAEEstadisticoClienteForm();
		return form;
	}

		
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList= reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		
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
			setSiccTerritorioList(null);

		}

	}

	
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteMAEEstadisticoClienteForm form = (ReporteMAEEstadisticoClienteForm) this.formReporte;
		String[] regiones = (String[]) form.getRegion();
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
			setSiccTerritorioList(null);

		}

	}
	
	public void loadterritorio(ValueChangeEvent val) {
		log.debug("loadterritorio");
		ReporteMAEEstadisticoClienteForm form = (ReporteMAEEstadisticoClienteForm) this.formReporte;
		String[] regiones = (String[]) form.getRegion();
		String[] zonas = (String[]) form.getZona();
		String[] secciones = (String[]) val.getNewValue();
		ArrayList<String> wregiones =new ArrayList<String>(Arrays.asList(regiones));
		ArrayList<String> wzonas =new ArrayList<String>(Arrays.asList(zonas));
		ArrayList<String> wsecciones =new ArrayList<String>(Arrays.asList(secciones));
		if (regiones.length > 0 && zonas.length > 0 && secciones.length>0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
			setSiccTerritorioList(aSvc
					.getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(
							this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
							Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, wregiones, 
							wzonas, wsecciones, Constants.OPCION_TODOS));

		} else {
			setSiccTerritorioList(null);

		}


	}
	
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteMAEEstadisticoCliente" + tipoReporte + "XLS";
		else
			return "reporteMaestroHorizontal";


	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteMAEEstadisticoCliente" + tipoReporte + "PDF";
		else
			return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		   
				ReporteMAEEstadisticoClienteForm f = (ReporteMAEEstadisticoClienteForm) this.formReporte;
				formatoReporte = f.getFormatoExportacion();
				
				ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
				Map criteria = params;
				criteria.put("codigoPeriodo", f.getCodigoPeriodo());
						
				params.put("codigoPeriodo",reporteService
						.getOidString("getDesPerioByOidPerio", criteria));
				
				log.debug("Periodo "+f.getCodigoPeriodo()+" oid:"+params.get("codigoPeriodo"));	
						
				if (f.getRegion() != null && f.getRegion().length > 0)
				{			
					log.debug("Regiones "+this.obtieneCondicion(f.getRegion(), "ZON_REGIO.cod_REGI", "'"));
					params.put("region",this.obtieneCondicion(f.getRegion(), "ZON_REGIO.cod_REGI", "'"));
					this.tipoReporte="Region";

				}
				
				if (f.getZona() != null && f.getZona().length > 0)
				{
					log.debug("Zonas "+this.obtieneCondicion(f.getZona(), "ZON_ZONA.cod_ZONA", "'"));
					params.put("zona",this.obtieneCondicion(f.getZona(), "ZON_ZONA.cod_ZONA", "'"));
					this.tipoReporte="Zona";
				}
				
				if (f.getSeccion() != null && f.getSeccion().length > 0)
				{
					log.debug("Secciones " +this.obtieneCondicion(f.getSeccion(), "ZON_SECCI.cod_secc", "'"));
					params.put("seccion",this.obtieneCondicion(f.getSeccion(), "ZON_SECCI.cod_secc", "'"));
					int eee = f.getSeccion().length;
					this.tipoReporte="Seccion";
					
					
				}

				if (f.getTerritorio() != null && f.getTerritorio().length > 0)
				{	log.debug("territorios "+this.obtieneCondicion(f.getTerritorio(), " ZON_TERRI.cod_terr", "'"));
					params.put("territorio",this.obtieneCondicion(f.getTerritorio(), " ZON_TERRI.cod_terr", "'"));
					this.tipoReporte="Territorio";
				}
				log.debug("this.tipoReporte "+this.tipoReporte);
				params.put("titulo", getReportResourceMessage("reporteMAEEstadisticoClienteForm.title")
						+ "\n"
						+getReportResourceMessage("reporteMAEEstadisticoClienteForm.periodo")+ ": "+f.getCodigoPeriodo());
				
				
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}


	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}


	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}
	
	
	
	
}

