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
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBSaldosPendientesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBSaldosPendientesAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 378691043370198066L;
	private String codigoIdiomaISO;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccTerritorioList = {};
	private LabelValue[] siccZonaList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		this.codigoIdiomaISO  = this.mPantallaPrincipalBean.getCurrentUser().getIdioma().getCodigoISO();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}	
		ReporteCOBSaldosPendientesForm f = (ReporteCOBSaldosPendientesForm)this.formReporte;
		f.setMostrarBotonExcel(this.esVisibleBotonExcel(pais.getCodigo()));	
		f.setCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodoInicial(periodo);
		f.setCodigoPeriodoFinal(periodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCOBSaldosPendientesService";
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
     */
    @Override
	protected Map prepareParameterMap(Map params) throws Exception {
    	ReporteCOBSaldosPendientesForm reporteCOBForm = (ReporteCOBSaldosPendientesForm) this.formReporte;
    	reporteCOBForm.setFormatoExportacion(this.formatoExportacion);
    	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String condicion = "";
		
		if(StringUtils.equals(this.formatoExportacion, "XLS")){
			String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(), "ZZ.COD_ZONA", "'");
			String condicionRegion = obtieneCondicion(reporteCOBForm.getRegionList(), "ZR.COD_REGI", "'");
			String condicionTerritorio = obtieneCondicion(reporteCOBForm.getTerritorioList(), "ZT.COD_TERR", "'");
			String condicionSeccion = obtieneCondicion(reporteCOBForm.getSeccionList(), "ZS.COD_SECC", "'");
			condicion = condicionZonas + condicionRegion + condicionTerritorio + condicionSeccion;
		}else{
			String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(), "R.COD_ZONA", "'");
			String condicionRegion = obtieneCondicion(reporteCOBForm.getRegionList(), "R.COD_REGI", "'");
			String condicionTerritorio = obtieneCondicion(reporteCOBForm.getTerritorioList(), "R.COD_TERR", "'");
			String condicionSeccion = obtieneCondicion(reporteCOBForm.getSeccionList(), "R.COD_SECC", "'");
			condicion = condicionZonas + condicionRegion + condicionTerritorio + condicionSeccion;
		}
		
		String orderBy = "ORDER BY ";
		Map criteria = params;
		
		String oidPais =reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		criteria.put("codigoPeriodo",reporteCOBForm.getCodigoPeriodoInicial());
		String oidPeriodoInicial =reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		criteria.put("codigoPeriodo",reporteCOBForm.getCodigoPeriodoFinal());
		String oidPeriodoFinal =reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		
		boolean flagComa = false;
		if (StringUtils.equals(reporteCOBForm.getFlagCodigo(), "S")) {
			flagComa = true;
			orderBy = orderBy + "MC.COD_CLIE ";
		}
		if (StringUtils.equals(reporteCOBForm.getFlagZona(), "S")) {
			if (flagComa)
				orderBy = orderBy + ", ZZ.COD_ZONA ";
			else {
				orderBy = orderBy + "ZZ.COD_ZONA ";
				flagComa = true;
			}
		}
		if (StringUtils.equals(reporteCOBForm.getFlagTerritorio(), "S")) {
			if (flagComa)
				orderBy = orderBy + ", ZT.COD_TERR ";
			else
				orderBy = orderBy + "ZT.COD_TERR ";
		}
		if (orderBy.length() < 10)
			orderBy = "";
		
		String descripcionRegionList = descripcionMultipleLista(reporteCOBForm.getRegionList(), this.siccRegionList);
		String descripcionZonaList = descripcionMultipleLista(reporteCOBForm.getZonaList(), this.siccZonaList);
		String descripcionTerritorioList = descripcionMultipleLista(reporteCOBForm.getTerritorioList(), this.siccTerritorioList);
		String descripcionSeccionList = descripcionMultipleLista(reporteCOBForm.getSeccionList(), this.siccSeccionList);

		
		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);		
		params.put("descripcionTerritorioList", descripcionTerritorioList);		
		params.put("descripcionSeccionList", descripcionSeccionList);
		params.put("formatoExportacion", this.formatoExportacion);
		params.put("codigoPeriodo", reporteCOBForm.getCodigoPeriodoFinal());
		
		params.put("oidPais", oidPais);
		params.put("oidPeriodoInicial",oidPeriodoInicial);
		params.put("oidPeriodoFinal",oidPeriodoFinal);
		params.put("orderBy", orderBy);
		params.put("condicion", condicion);
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteRECListadoDeudaPendPeriodoForm.titulo"));
		return params;
	}
    
    /**
	 * @param Muestra las zonas por regiones escogidas.
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		try {
			ReporteCOBSaldosPendientesForm form = (ReporteCOBSaldosPendientesForm) this.formReporte;
			String [] regiones = (String [])val.getNewValue();
			if(!val.equals(null) && regiones.length > 0 ){
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
						Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
				form.setZonaList(null);	
			}else {
				this.siccSeccionList = null;
				this.siccTerritorioList = null;
				this.siccZonaList= null;
				form.setZonaList(null);
				form.setTerritorioList(null);
				form.setSeccionList(null);
			}		
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}			
	}
	
	/**
	 * @param Muestra las secciones por las zonas seleccionadas.
	 */
	public void showSeccionxZona(ValueChangeEvent val){
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteCOBSaldosPendientesForm form = (ReporteCOBSaldosPendientesForm) this.formReporte;
			
			String[] regiones = (String [])form.getRegionList();
			
			String[] zonas = (String [])val.getNewValue();
			
			if(!val.equals(null) && zonas.length > 0 ){
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccSeccionList(aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones, zonas, Constants.FORMATO_TOTAL));

				form.setSeccionList(null);
			}else {
				this.siccSeccionList = null;
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
				form.setSeccionList(null);
			}		
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @param Muestra los territorios por la secciones escogidas.
	 */
	public void showTerritorioxSeccion(ValueChangeEvent val){
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteCOBSaldosPendientesForm form = (ReporteCOBSaldosPendientesForm) this.formReporte;
			
			String[] regiones = (String [])form.getRegionList();
			
			String[] zonas = (String [])form.getZonaList();
			
			String[] secciones = (String [])val.getNewValue();
			
			if(!val.equals(null) && secciones.length > 0 ){
				ArrayList<String> listaRegiones = new ArrayList<String>(Arrays.asList(regiones));
				ArrayList<String> listaZonas = new ArrayList<String>(Arrays.asList(zonas));
				ArrayList<String> listaSecciones = new ArrayList<String>(Arrays.asList(secciones));
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTerritorioList(aSvc.getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,listaRegiones,listaZonas, listaSecciones, Constants.FORMATO_TOTAL));
				form.setTerritorioList(null);
			}else {
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
				
	}
	
	/**
	 * 
     * @param codigoPais
     * @return
     */
    private boolean esVisibleBotonExcel(String codigoPais) {
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");
		this.mostrarReporteOCSV = true;
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
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBSaldosPendientesForm form = new ReporteCOBSaldosPendientesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion))
			return "reporteCOBSaldosPendientesXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOBSaldosPendientesPDF";
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
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
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
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
}