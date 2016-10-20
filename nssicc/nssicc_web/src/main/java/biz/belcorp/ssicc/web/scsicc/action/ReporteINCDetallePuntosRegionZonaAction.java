package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.cmp.GenRepContent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCDetallePuntosRegionZonaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteINCDetallePuntosRegionZonaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 999481364918965688L;
	private String formatoReporte;
	private String tipoReporte;

	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List siccConcursoList;
	private List incConcursoTipoProgramaPuntosList;
	private LabelValue[] campaniasIniFinByConcursoTipoProgramaPuntosList = {};
	private boolean regionDisabled;
	private boolean zonaDisabled;

	
	private AjaxService ajax;
	private String codigoPais;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCDetallePuntosRegionZonaForm reporteForm = new ReporteINCDetallePuntosRegionZonaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReportePDF=false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;

		ajax = (AjaxService) getBean("ajaxService");

		ReporteINCDetallePuntosRegionZonaForm f = (ReporteINCDetallePuntosRegionZonaForm) this.formReporte;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		f.setCodigoPais(codigoPais);

		Map<String,String> criteriaOperacion = new HashMap<String,String>();
		criteriaOperacion.put("codigoPais", codigoPais);

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

		this.incConcursoTipoProgramaPuntosList = service
				.getListConcursosTipoProgramaPuntos();
		
		Base base = (Base)incConcursoTipoProgramaPuntosList.get(0);
		this.campaniasIniFinByConcursoTipoProgramaPuntosList = ajax
				.getCampaniasIniFinByConcursoTipoProgramaPuntos(base.getCodigo(),codigoPais);

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			if ("N".equals(tipoReporte))
				return "reporteINCDetallePuntosRegionZonaByNacionalXLS";
			if ("R".equals(tipoReporte))
				return "reporteINCDetallePuntosRegionZonaByRegionXLS";
			if ("Z".equals(tipoReporte))
				return "reporteINCDetallePuntosRegionZonaByZonaXLS";
			if ("CO".equals(tipoReporte))
				return "reporteINCDetallePuntosRegionZonaByConsultoraXLS";
			if ("CA".equals(tipoReporte))
				return "reporteINCDetallePuntosRegionZonaByCampaniaXLS";
			else
				return "";
		} else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteINCDetallePuntosRegionZonaForm f = (ReporteINCDetallePuntosRegionZonaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String condicionPeriodo = "";
		String condicionRegion = "";
		String condicionZona = "";

		String oidConcurso = reporteService.getOidConcursoByNumConc(f.getNumeroConcurso());
		formatoReporte = f.getFormatoExportacion();
		tipoReporte = f.getCriterioAgrupacion();
		
		List<String> campanias = Arrays.asList(f.getCodigoCampania()); 
		if(!campanias.contains(""))
			condicionPeriodo = obtieneCondicion(f.getCodigoCampania(), "PERD_OID_PERI", "");
		
		if(f.getCodigoRegion()!=null){
			List<String> regiones = Arrays.asList(f.getCodigoRegion()); 
			if(!regiones.contains(""))
				condicionRegion = obtieneCondicion(f.getCodigoRegion(), "zr.cod_regi", "");
		}
		
		if(f.getCodigoZona()!=null){
			List<String> zonas = Arrays.asList(f.getCodigoZona()); 
			if(!zonas.contains(""))
				condicionZona = obtieneCondicion(f.getCodigoZona(), "zon.cod_zona", "");
		}
		
		params.put("tipoReporte", tipoReporte);
		params.put("formatoReporte", formatoReporte);
		params.put("numeroConcurso", oidConcurso);
		params.put("condicionPeriodo"," "+condicionPeriodo);
		
		if(tipoReporte.equals("N"))
			params.put("condicionPeriodo","  "+condicionPeriodo);

		if(tipoReporte.equals("R"))
			params.put("condicionRegion"," "+condicionRegion+" "+condicionPeriodo);

		if(tipoReporte.equals("Z"))
			params.put("condicionZona"," "+condicionRegion+" "+condicionZona+" "+condicionPeriodo);

		if(tipoReporte.equals("CO"))
			params.put("condicionConsultora"," "+condicionRegion+" "+condicionZona+" "+condicionPeriodo);

		if(tipoReporte.equals("CA"))
			params.put("condicionCampania"," "+condicionRegion+" "+condicionZona+" "+condicionPeriodo);

		if ("OCSV".equals(formatoReporte)){
			if(tipoReporte.equals("N"))
				params.put("condicionPeriodo", obtieneCondicionSoloIn(f.getCodigoCampania()));

			if(tipoReporte.equals("R")){
				params.put("condicionPeriodo", obtieneCondicionSoloIn(f.getCodigoCampania()));
				params.put("condicionRegion", obtieneCondicionSoloIn(f.getCodigoRegion()));
			}
			if(tipoReporte.equals("Z")){
				params.put("condicionPeriodo", obtieneCondicionSoloIn(f.getCodigoCampania()));
				params.put("condicionRegion", obtieneCondicionSoloIn(f.getCodigoRegion()));
			    params.put("condicionZona", obtieneCondicionSoloIn(f.getCodigoZona()));
			}
			if(tipoReporte.equals("CO")){
				params.put("condicionPeriodo", obtieneCondicionSoloIn(f.getCodigoCampania()));
				params.put("condicionRegion", obtieneCondicionSoloIn(f.getCodigoRegion()));
			    params.put("condicionZona", obtieneCondicionSoloIn(f.getCodigoZona()));
			}

			if(tipoReporte.equals("CA")){
				params.put("condicionPeriodo", obtieneCondicionSoloIn(f.getCodigoCampania()));
				params.put("condicionRegion", obtieneCondicionSoloIn(f.getCodigoRegion()));
			    params.put("condicionZona", obtieneCondicionSoloIn(f.getCodigoZona()));
			}
		}
		
		return params;

	}

	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());

		ReporteINCDetallePuntosRegionZonaForm f = (ReporteINCDetallePuntosRegionZonaForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			this.setSiccZonaList(ajax.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));
		} else
			this.siccZonaList = null;

		f.setCodigoZona(null);
	}

	public void getCampaniasIniFinByConcursoTipoProgramaPuntos(
			ValueChangeEvent val) {
		log.debug(">>numeroConcurso ");
		log.debug("val: " + val.getNewValue().toString());

		String numeroConcurso = (String) val.getNewValue();
		this.campaniasIniFinByConcursoTipoProgramaPuntosList = ajax
				.getCampaniasIniFinByConcursoTipoProgramaPuntos(numeroConcurso, codigoPais);

	}
	
	public void validacionCriterio(
			ValueChangeEvent val) {

		String valor = (String) val.getNewValue();
		zonaDisabled=false;
		regionDisabled=false;
		if(valor.equals("R"))
			zonaDisabled=true;
		if(valor.equals("N")){
			zonaDisabled=true;
			regionDisabled=true;
		}

	}
	
	public String obtieneCondicionSoloIn(String[] lista) {

		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isBlank(dato) || StringUtils.equals(dato, "Todos") || StringUtils.equals(dato, "T") )
					return "";
				if (i == 0)
					resultado = resultado  + dato;
				else
					resultado = resultado + "," + dato;
			}

			return resultado;
		}
	}
	
	protected String devuelveBeanReporteService(){
		return "reportes.reporteINCDetallePuntosRegionZonaService";
				
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getIncConcursoTipoProgramaPuntosList() {
		return incConcursoTipoProgramaPuntosList;
	}

	public void setIncConcursoTipoProgramaPuntosList(
			List incConcursoTipoProgramaPuntosList) {
		this.incConcursoTipoProgramaPuntosList = incConcursoTipoProgramaPuntosList;
	}

	public LabelValue[] getCampaniasIniFinByConcursoTipoProgramaPuntosList() {
		return campaniasIniFinByConcursoTipoProgramaPuntosList;
	}

	public void setCampaniasIniFinByConcursoTipoProgramaPuntosList(
			LabelValue[] campaniasIniFinByConcursoTipoProgramaPuntosList) {
		this.campaniasIniFinByConcursoTipoProgramaPuntosList = campaniasIniFinByConcursoTipoProgramaPuntosList;
	}

	public AjaxService getAjax() {
		return ajax;
	}

	public void setAjax(AjaxService ajax) {
		this.ajax = ajax;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public boolean isRegionDisabled() {
		return regionDisabled;
	}

	public void setRegionDisabled(boolean regionDisabled) {
		this.regionDisabled = regionDisabled;
	}

	public boolean isZonaDisabled() {
		return zonaDisabled;
	}

	public void setZonaDisabled(boolean zonaDisabled) {
		this.zonaDisabled = zonaDisabled;
	}
	
	
}
