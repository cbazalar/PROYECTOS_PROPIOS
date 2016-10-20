package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERBaseRecuperacionCampanhasForm;

@ManagedBean
@SessionScoped
public class ReportePERBaseRecuperacionCampanhasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 2955692483642588L;
	private String tipoVista;
	
	private List siccMarcaList = new ArrayList();
	private List siccCanalList= new ArrayList();
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSubGerenciaList = {};
	private List siccSeccionList = new ArrayList();
	private Boolean cambioSubGerencia;
	private Boolean cambioRegion;
	private Boolean cambioZona = true;

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		ReportePERBaseRecuperacionCampanhasForm form = (ReportePERBaseRecuperacionCampanhasForm) this.formReporte;
		form.setCodigoPais(pais.getCodigo());

	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReportePERBaseRecuperacionCampanhasForm form = new ReportePERBaseRecuperacionCampanhasForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteMaestroHorizontalCustom";
	}
	
	/**
	 * Metodo para Cambiar Filtro
	 * 
	 * @param val
	 */
	public void loadFiltro(ValueChangeEvent val) {
		
		ReportePERBaseRecuperacionCampanhasForm form = (ReportePERBaseRecuperacionCampanhasForm) this.formReporte;
		
		String valor = (String) val.getNewValue();
		if (valor.equals("1")) {
			this.cambioSubGerencia = true;
			this.cambioRegion = true;
			this.cambioZona = true;
			form.setCodigoSubgerencia(null);
			form.setCodigoRegion(null);
			form.setCodigoZona(null);
			this.setSiccRegionList(null);
			this.setSiccZonaList(null);
			this.setSiccSubGerenciaList(null);
			//form.setFechaInicioD(null);
			//form.setFechaFinD(null);
		} else if (valor.equals("2")) {
			this.cambioSubGerencia = false;
			this.cambioRegion = false;
			this.cambioZona = true;
			form.setCodigoSubgerencia(null);
			loadRegiones();
			//form.setCodigoRegion(null);
			form.setCodigoZona(null);
			//this.setSiccRegionList(null);
			this.setSiccZonaList(null);
			this.setSiccSubGerenciaList(null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			String periodoActual =  this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
			form.setPeriodoDesde(periodoActual);
			if (StringUtils.isEmpty(form.getPeriodoDesde()))
				form.setPeriodoDesde(periodo);			
			form.setPeriodoHasta(periodoActual);
			if (StringUtils.isEmpty(form.getPeriodoHasta()))
				form.setPeriodoHasta(periodo);
			//form.setCodigoPeriodoInicio(null);
			//form.setCodigoPeriodoFin(null);
		}else if (valor.equals("3")){
			this.cambioSubGerencia = false;
			this.cambioRegion = false;
			this.cambioZona = false;
			form.setCodigoSubgerencia(null);
			loadRegiones();
			//form.setCodigoRegion(null);
			//form.setCodigoZona(null);
			//this.setSiccRegionList(null);
			//this.setSiccZonaList(null);
			this.setSiccSubGerenciaList(null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			String periodoActual =  this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
			form.setPeriodoDesde(periodoActual);
			if (StringUtils.isEmpty(form.getPeriodoDesde()))
				form.setPeriodoDesde(periodo);			
			form.setPeriodoHasta(periodoActual);
			if (StringUtils.isEmpty(form.getPeriodoHasta()))
				form.setPeriodoHasta(periodo);
		}
	}
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadSubGerencia(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		if (!val.equals(null)) {
			ReportePERBaseRecuperacionCampanhasForm f = (ReportePERBaseRecuperacionCampanhasForm) this.formReporte;
			String valor = (String) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccSubGerenciaList(ajaxService.getSubGerenciasByPaisMarcaCanal(
					this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					valor));

		} else
			this.setSiccSubGerenciaList(null);
	}
	
	public void loadRegiones() {
		Map criteriaOperacion = new HashMap();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
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
		

	}
	
	public void loadRegion2(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion2");
		}
		ReportePERBaseRecuperacionCampanhasForm form = (ReportePERBaseRecuperacionCampanhasForm) this.formReporte;

		String canal = (String) val.getNewValue();
		String marca = form.getCodigoMarca();
		String pais =  form.getCodigoPais();
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		try{
			LabelValue[] listado= ajaxService.getSubGerenciasByPaisMarcaCanal(
					pais,marca,	canal);
			siccSubGerenciaList =  listado;
		}catch(Exception ex){
			//String[] cas = {"TODAS"};
			//this.siccSubGerenciaList[0].setValue("TODAS");
			log.info("Error al Cargar Combo.");
		}		
		
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {
			loadRegiones();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			String periodoActual =  this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
			form.setPeriodoDesde(periodoActual);
			if (StringUtils.isEmpty(form.getPeriodoDesde()))
				form.setPeriodoDesde(periodo);			
			form.setPeriodoHasta(periodoActual);
			if (StringUtils.isEmpty(form.getPeriodoHasta()))
				form.setPeriodoHasta(periodo);
		} else {
			setSiccRegionList(null);
			setSiccZonaList(null);
			this.setSiccSubGerenciaList(null);
		}
	}
	
	public void loadRegion3(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion2");
		}
		ReportePERBaseRecuperacionCampanhasForm form = (ReportePERBaseRecuperacionCampanhasForm) this.formReporte;

		String marca = (String) val.getNewValue();
		String canal = form.getCodigoCanal();
		if (canal ==null){
			setSiccRegionList(null);
			setSiccZonaList(null);
		}else{		
			if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
					&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {
				loadRegiones();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				String periodo = sdf.format(new Date(System.currentTimeMillis()));
				String periodoActual =  this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
				form.setPeriodoDesde(periodoActual);
				if (StringUtils.isEmpty(form.getPeriodoDesde()))
					form.setPeriodoDesde(periodo);			
				form.setPeriodoHasta(periodoActual);
				if (StringUtils.isEmpty(form.getPeriodoHasta()))
					form.setPeriodoHasta(periodo);
	
			}else {
				setSiccRegionList(null);
				setSiccZonaList(null);
			}
		}
	}
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		if (!val.equals(null)) {
			String valor = (String) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService.getZonasByPaisMarcaCanalRegion(
					this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valor));

		} else
			this.setSiccZonaList(null);
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (tipoVista.equalsIgnoreCase(Constants.TIPO_VISTA_PAIS)) {
			return "recuperacionCampanhasPais";
		}
		if (tipoVista.equalsIgnoreCase(Constants.TIPO_VISTA_REGION)) {
			return "recuperacionCampanhasRegion";
		}
		if (tipoVista.equalsIgnoreCase(Constants.TIPO_VISTA_ZONA)) {
			return "recuperacionCampanhasZona";
		}
		return "recuperacionCampanhasPais";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReportePERBaseRecuperacionCampanhasForm f = (ReportePERBaseRecuperacionCampanhasForm) this.formReporte;
		tipoVista = f.getTipoVista();
		params.put("NroReporte","");
//		String descCanal = f.getDescripcionCanal();
//		String descMarca = f.getDescripcionMarca();
		String descSubGerencia = f.getCodigoSubgerencia();
		String descRegion = f.getDescripcionRegion();
		String descZona = f.getDescripcionZona();
		String codReg =  f.getCodigoRegion();
		String codZona = f.getCodigoZona();
	
//		if(siccZonaList==null){
//			this.siccZonaList[0].setValue("TODAS");
//		}
//		if(siccRegionList==null){
//			this.siccRegionList[0].setValue("TODAS");
//		}
//		if(siccSubGerenciaList==null){
//			((LabelValue)this.siccSubGerenciaList[0]).setValue("TODAS");
//		}
		
//		String descripcionCanal = null;
//		String descripcionMarca= null;
		String descripcionRegion= descRegion;
		String descripcionZona= descZona;
		String descripcionSubgerencia= descSubGerencia;
//		if(StringUtils.isNotBlank(descCanal)){
//			descripcionCanal = "TODAS";
//		}
//		if(StringUtils.isNotBlank(descMarca)){
//			descripcionMarca = "TODAS";
//		}
		if(StringUtils.isBlank(descSubGerencia)){
			descripcionSubgerencia = "TODAS";
		}
		if(StringUtils.isBlank(descRegion)){
			descripcionRegion = "TODAS";
		}
		if(StringUtils.isBlank(descZona)){
			descripcionZona = "TODAS";
		}
		
//		descripcionCanal = descripcionSimpleLista(descCanal, this.siccCanalList);
//		if(StringUtils.isBlank(descripcionCanal)){
//			descripcionCanal = "TODAS";
//		}
//		descripcionMarca = descripcionSimpleLista(descMarca, this.siccMarcaList);
//		if(StringUtils.isBlank(descripcionMarca)){
//			descripcionMarca = "TODAS";
//		}
//		descripcionRegion = descripcionSimpleLista(descRegion, this.siccRegionList);
//		if(StringUtils.isBlank(descripcionRegion)){
//			descripcionRegion = "TODAS";
//		}
//		descripcionZona = descripcionSimpleLista(descZona, this.siccZonaList);
//		if(StringUtils.isBlank(descripcionZona)){
//			descripcionZona = "TODAS";
//		}
//		descripcionSubgerencia = descripcionSimpleLista(descSubGerencia, this.siccSubGerenciaList);
//		if(StringUtils.isBlank(descripcionSubgerencia)){
//			descripcionSubgerencia = "TODAS";
//		}
		
//		
//		params.put("descripcionCanal", descripcionCanal);
//		params.put("descripcionMarca", descripcionMarca);
		params.put("descripcionRegion", descripcionRegion);
		params.put("descripcionZona", descripcionZona);
		params.put("descripcionSubgerencia", descripcionSubgerencia);
		
		
		if (f.getTipoVista().equalsIgnoreCase(Constants.TIPO_VISTA_PAIS)) {
			ClassPathResource resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "baseRecuperacionBasicoPais" + JASPER_EXTENSION);
			ClassPathResource resource2 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "baseRecuperacionTiposPais" + JASPER_EXTENSION);
			
				params.put("REPORT_DIR1", (JasperReport) JRLoader
						.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("REPORT_DIR2", (JasperReport) JRLoader
						.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));

		}
		if (f.getTipoVista().equalsIgnoreCase(Constants.TIPO_VISTA_REGION)) {
			ClassPathResource resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "baseRecuperacionBasicoRegion" + JASPER_EXTENSION);
			ClassPathResource resource2 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "baseRecuperacionTiposRegion" + JASPER_EXTENSION);
			
				params.put("REPORT_DIR1", (JasperReport) JRLoader
						.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("REPORT_DIR2", (JasperReport) JRLoader
						.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));

		}
		if (f.getTipoVista().equalsIgnoreCase(Constants.TIPO_VISTA_ZONA)) {
			ClassPathResource resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "baseRecuperacionBasicoZona" + JASPER_EXTENSION);
			ClassPathResource resource2 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "baseRecuperacionTiposZona" + JASPER_EXTENSION);
			
				params.put("REPORT_DIR1", (JasperReport) JRLoader
						.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("REPORT_DIR2", (JasperReport) JRLoader
						.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));

		}		
		params.put("titulo",getReportResourceMessage("reportePERBaseRecuperacionCampanhasForm.titulo"));
		return params;
	}
	
	protected String devuelveBeanReporteService(){
		return "reportes.reportePERBaseRecuperacionCampanhasService";
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
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
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
	
	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
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
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSubGerenciaList
	 */
	public LabelValue[] getSiccSubGerenciaList() {
		return siccSubGerenciaList;
	}

	/**
	 * @param siccSubGerenciaList the siccSubGerenciaList to set
	 */
	public void setSiccSubGerenciaList(LabelValue[] siccSubGerenciaList) {
		this.siccSubGerenciaList = siccSubGerenciaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the cambioSubGerencia
	 */
	public Boolean getCambioSubGerencia() {
		return cambioSubGerencia;
	}

	/**
	 * @param cambioSubGerencia the cambioSubGerencia to set
	 */
	public void setCambioSubGerencia(Boolean cambioSubGerencia) {
		this.cambioSubGerencia = cambioSubGerencia;
	}

	/**
	 * @return the cambioRegion
	 */
	public Boolean getCambioRegion() {
		return cambioRegion;
	}

	/**
	 * @param cambioRegion the cambioRegion to set
	 */
	public void setCambioRegion(Boolean cambioRegion) {
		this.cambioRegion = cambioRegion;
	}

	/**
	 * @return the cambioZona
	 */
	public Boolean getCambioZona() {
		return cambioZona;
	}

	/**
	 * @param cambioZona the cambioZona to set
	 */
	public void setCambioZona(Boolean cambioZona) {
		this.cambioZona = cambioZona;
	}
}