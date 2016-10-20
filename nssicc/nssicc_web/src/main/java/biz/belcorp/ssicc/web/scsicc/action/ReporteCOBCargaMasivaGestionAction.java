package biz.belcorp.ssicc.web.scsicc.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBCargaMasivaGestionForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@SuppressWarnings({"unchecked","rawtypes"})
@SessionScoped
@ManagedBean
public class ReporteCOBCargaMasivaGestionAction extends BaseReporteAbstractAction{

	/**
	 * JPPS
	 */
	private static final long serialVersionUID = 6940637165931106388L;
	private String codigoIdiomaISO = "";
	
	private String tipoVista;
	
	private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList;
	private LabelValue[] siccCobradoresList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		
		ReporteCOBCargaMasivaGestionForm r = new ReporteCOBCargaMasivaGestionForm();
		return r;
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBCargaMasivaGestionService";
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOBCargaMasivaGestion" +   tipoVista  + "XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBCargaMasivaGestionForm reporteCOBForm = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		reporteCOBForm.setCodigoPais(pais.getCodigo());
								
		this.tipoVista = reporteCOBForm.getTipoVista();						
				        				                      
		String codigoPais =  reporteCOBForm.getCodigoPais();
		String codigoSociedad =  reporteCOBForm.getCodigoSociedad();
		String codigoZona =  reporteCOBForm.getCodigoZona();
		String codigoPeriodoInicio = reporteCOBForm.getCodigoPeriodoInicio();
		String codigoPeriodoFin = reporteCOBForm.getCodigoPeriodoFin();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);
		params.put("codigoZona", codigoZona);			
		params.put("codigoPeriodoInicio", codigoPeriodoInicio);
		params.put("codigoPeriodoFin", codigoPeriodoFin);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ReporteCOBCargaMasivaGestionForm f = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
		this.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
				
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocaleIdiomaNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		ParametroPais parametro = null;
		String idiomaReporte = pais.getCodigoIdiomaIso();
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			idiomaReporte = parametro.getValorParametro();
		}
		f.setIdiomaReporte(idiomaReporte);
		
		parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocatePaisNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		lstParametros = genericoService.getParametrosPais(parametroPais);
		
		String paisReporte = pais.getCodigoPaisIso();
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			paisReporte = parametro.getValorParametro();
		}
		f.setPaisReporte(paisReporte);

		f.setMostrarBotonExcel(this.esVisibleBotonExcel(pais.getCodigo()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodoInicio(periodo);
		f.setCodigoPeriodoFin(periodo);
		
		// ----------- LISTA DE SOCIEDADES --------------
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());

		AjaxService ajax = (AjaxService) getBean("ajaxService");

		// ------------ LISTA DE REGIONES -----------------
		this.siccRegionList=ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo( pais.getCodigo(), f.getCodigoSociedad(),f.getCodigoEtapaDeuda(), f.getCodigoPeriodo());	 		 	
		this.siccEtapaDeudaList=ajax.getEtapasDeudaByPaisSociedad(pais.getCodigo(), f.getCodigoSociedad());
	
        LabelValue[] temSociedad=ListToLabelValue(this.siccSociedadList); 
		f.setCodigoEtapaDeuda(this.siccEtapaDeudaList==null?"":this.siccEtapaDeudaList[0].getValue());
		f.setCodigoSociedad(temSociedad==null?"":temSociedad[0].getValue());
		
		}
	
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
			
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		boolean activo = false;
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			activo = true;
		}
		
		return activo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#configReporteParams(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Map parameterMap = (Map)params.get("parameterMap");
		String idiomaReporte = (String)parameterMap.get("idiomaReporte");
		String paisReporte = (String)parameterMap.get("paisReporte");
		
		Locale locale = new Locale(idiomaReporte,paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	private void loadZonas(ValueChangeEvent val) {
		
		ReporteCOBCargaMasivaGestionForm f = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valor = (String)val.getNewValue();
		if (valor.length() > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajax.getZonasByPaisSociedadEtapaDeudaPeriodoRegion(pais.getCodigo(), f.getCodigoSociedad(), f.getCodigoEtapaDeuda(), f.getCodigoPeriodo(), valor);
		}

	}

	/**
	 * Metodo para obtener Lista de Etapas
	 * 
	 * @param val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valor = (String) val.getNewValue();
		ReporteCOBCargaMasivaGestionForm f = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		
		if (valor.length() > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] cargar = ajax.getEtapasDeudaByPaisSociedad(pais.getCodigo(), valor);
			this.siccEtapaDeudaList = cargar;
			if(siccEtapaDeudaList.length>0 && siccEtapaDeudaList!=null)
			f.setCodigoEtapaDeuda(getSiccEtapaDeudaList()[0].getValue());
		}
		
	}
	
	public void loadCobradores(ValueChangeEvent val) {
		
		ReporteCOBCargaMasivaGestionForm f = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valor = (String) val.getNewValue();
	//	f.setCodigoEtapaDeuda(valor);
		if(f.getCodigoEtapaDeuda()==null)
		{
			addInfo("Mensaje: ", "Asigne un valor al campo Etapa");
			limpiarTipoVista();
			return;
		}
		
		if (valor.length() > 0) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue[] cargarCobrador = ajax.getCobradoresByPaisSociedadEtapaDeuda(pais.getCodigo(), f.getCodigoSociedad(), f.getCodigoEtapaDeuda());
		this.siccCobradoresList = cargarCobrador;
		}
	}
	
	private void limpiarTipoVista()
	{	
		ReporteCOBCargaMasivaGestionForm f = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		f.setTipoVista("");
	}
	
	private void loadCobradoresUA(ValueChangeEvent val) {
		ReporteCOBCargaMasivaGestionForm f = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valor = (String) val.getNewValue();
		
		if (valor.length() > 0) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue[] cargarCobrador = ajax.getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(pais.getCodigo(), f.getCodigoSociedad(), f.getCodigoEtapaDeuda(), f.getCodigoPeriodo(), f.getCodigoRegion(), valor);
	    this.siccCobradoresList = cargarCobrador;
		}
	}
	
	public void loadZonasCobradores (ValueChangeEvent val)
	{
		this.loadCobradoresUA(val);
		this.loadZonas(val);
	}
	
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte()... - ReporteCOBCargaMasivaGestionAction");
		}
		
		ReporteCOBCargaMasivaGestionForm form = (ReporteCOBCargaMasivaGestionForm) this.formReporte;
		
		if(!StringUtils.isBlank(form.getCodigoPeriodoInicio()) && !StringUtils.isBlank(form.getCodigoPeriodoFin())){
			int periodoFin = Integer.parseInt(form.getCodigoPeriodoFin()); //parseInt(document.getElementById("codigoPeriodoFin").value);
			int periodoInicio = Integer.parseInt(form.getCodigoPeriodoInicio());  //parseInt(document.getElementById("codigoPeriodoInicio").value);
		
			if (periodoFin < periodoInicio){
				return this.getResourceMessage("reporteCOBCargaMasivaGestionForm.error.periodoInicioMenor");
            }
		}
		
		return super.setValidarReporte();
	}
	
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	public String getTipoVista() {
		return tipoVista;
	}

	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	public LabelValue[] getSiccCobradoresList() {
		return siccCobradoresList;
	}

	public void setSiccCobradoresList(LabelValue[] siccCobradoresList) {
		this.siccCobradoresList = siccCobradoresList;
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
  
   /**
 * @param siccSociedadList
 * @return Convierte una Lista a LabelValue 
 */
public LabelValue[] ListToLabelValue(List siccSociedadList)
   {
	   if (siccSociedadList==null)
		  return null;		   
	   LabelValue[] tempSociedadList= new LabelValue[siccSociedadList.size()];
       int z = 0;
       for (Object object : siccSociedadList) {
                       LabelValue labelValue = new LabelValue();
                       labelValue.setLabel(((Base) object).getDescripcion());
                       labelValue.setValue(((Base) object).getCodigo());
                       tempSociedadList[z] = labelValue;
                       z++;
       } 
       
       return tempSociedadList;
   }
	
}
