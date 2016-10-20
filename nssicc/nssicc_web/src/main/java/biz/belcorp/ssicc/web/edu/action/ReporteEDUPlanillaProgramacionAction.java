package biz.belcorp.ssicc.web.edu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.edu.form.ReporteEDUPlanillaProgramacionForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.AjaxService2;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * 
 * @author <a href="">Marco Agurto</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ReporteEDUPlanillaProgramacionAction extends BaseReporteAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6438019453939507948L;
	
	private String nameSubReporte;
	private String nameReporte;
	private String variableHorizontal;
	
	protected List listaEmpresa;
	protected LabelValue[] regionesCursosCapacitacionList;
	protected LabelValue[] eduZonaList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteEDUPlanillaProgramacionForm reporteForm = new ReporteEDUPlanillaProgramacionForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {	
				
		ReporteEDUPlanillaProgramacionForm reporteForm = (ReporteEDUPlanillaProgramacionForm)this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();
		
		if(formatoReporte.equals("XLS") || formatoReporte.equals("CSV")){
			return this.nameReporte;
		}else{
			if(variableHorizontal.equals("H")){
				return "reporteMaestroHorizontal";	
			}else{
				return "reporteMaestroVertical";
			}
		}		
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {		
		
		ReporteEDUPlanillaProgramacionForm reporteForm = (ReporteEDUPlanillaProgramacionForm)this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();
		
		if(formatoReporte.equals("XLS") || formatoReporte.equals("CSV")){
			return "";
		}		
		return this.nameSubReporte;
				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteEDUPlanillaProgramacionAction.setViewAtributes' method");            
        }
		
		//Seteo de valores por default de nuevos registros
		ReporteEDUPlanillaProgramacionForm reporteForm = (ReporteEDUPlanillaProgramacionForm)this.formReporte;
		
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		//TODO	
		AjaxService2 ajaxService = (AjaxService2)this.getBeanService("ajaxService2");				
		reporteForm.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) this.getBeanService("edu.mantenimientoEDUGenericoService");
								
		/*Inicializamos la Empresa, siempre despues de LoadCombos*/
		this.listaEmpresa =  siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		if ((listaEmpresa!=null) && (listaEmpresa.size() > 0)){
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);			
			reporteForm.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}
		
		this.regionesCursosCapacitacionList = ajaxService.getRegionesEDUByPaisEmpresa(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa());
		AjaxService ajaxService1 = (AjaxService)this.getBeanService("ajaxService");
		String region0= getRegionesCursosCapacitacionList()[0].getValue(); 
		this.eduZonaList = ajaxService1.getZonaByPaisEmpresaRegion(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa(), region0);
				
		reporteForm.setCampanhaProceso(getCampanhaProceso(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa()));
				
		//steando el nombre del reporte
		String codigoPais = pais.getCodigo();
		log.debug("codigoPais " + codigoPais);
		
		MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService)this.getBeanService("spusicc.mantenimientoSTOBloqueoControlService");
				
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "EDU");
		criteriaParam.put("nombreParametro", "indOrienPlani");
			
		this.variableHorizontal = stoService.getParametroGenericoSistema(criteriaParam);	
		
		if(codigoPais.equals(Constants.EDU_COLOMBIA_ESIKA)){
			if(variableHorizontal.equals("H")){
				this.nameSubReporte="reporteEDUPlanillaProgramacionEsikaHorizontalPDF";
				this.nameSubReporte="reporteEDUPlanillaProgramacionEsikaHorizontalXLS";
			}else{
				this.nameSubReporte="reporteEDUPlanillaProgramacionEsikaPDF";
				this.nameReporte = "reporteEDUPlanillaProgramacionEsikaXLS";
			}			
			
		}else{	
			if(variableHorizontal.equals("H")){
				this.nameSubReporte="reporteEDUPlanillaProgramacionHorizontalPDF";
				this.nameReporte = "reporteEDUPlanillaProgramacionHorizontalXLS";
			}else{
				this.nameSubReporte="reporteEDUPlanillaProgramacionPDF";
				this.nameReporte = "reporteEDUPlanillaProgramacionXLS";
			}			
			
		}
		
		this.mostrarReporteXLS = true;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteEDUPlanillaProgramacionAction.prepareParameterMap' method");
		}
				
		ReporteEDUPlanillaProgramacionForm reporteForm = (ReporteEDUPlanillaProgramacionForm) this.formReporte;
				
		params.put("NroReporte", "");
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("codigoEmpresa", reporteForm.getCodigoEmpresa());
		params.put("codigoRegion", reporteForm.getRegion());
		params.put("codigoZona",reporteForm.getCodigoZona());
		params.put("campanhaProceso", reporteForm.getCampanhaProceso());
		params.put("nivelGenerarPlanilla", Constants.REPORTE_EDU_GENERACION_PLANILLA_REGION);
		params.put("titulo", this.getResourceMessage("reporteEDUPlanillaProgramacionForm.titulo"));
		return params;
	}

	private String getCampanhaProceso(String codigoPais,String codigoEmpresa){
		// Obteniendo Campa�a de Proceso
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService)
				this.getBeanService("edu.procesoEDUCalificacionAptasAutomaticaService");
					
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		return codigoPeriodo;
	}
	
	public void loadCargarDatos(ValueChangeEvent value)
	{
		if (log.isDebugEnabled()) {
			log.debug("loadCargarDatos");
		}

		try {
			String valor = (String) value.getNewValue();
			if (valor.trim().length() > 0) {
				ReporteEDUPlanillaProgramacionForm reporteForm = (ReporteEDUPlanillaProgramacionForm)this.formReporte;
				getCampanhaProceso(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa());
				
				AjaxService ajaxService = (AjaxService) this.getBeanService("ajaxService");
				regionesCursosCapacitacionList = ajaxService.getRegionesEDUByPaisEmpresa( reporteForm.getCodigoPais(), reporteForm.getCodigoEmpresa());
				this.eduZonaList = ajaxService.getZonaByPaisEmpresaRegion(reporteForm.getCodigoPais(), reporteForm.getCodigoEmpresa(),
						regionesCursosCapacitacionList[0].getValue());			
			}
		}catch (Exception e) {
				// TODO: handle exception
			}
	}
		
	public void loadZonas(ValueChangeEvent value)
	{
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) value.getNewValue();
		if (valor.trim().length() > 0) {
			ReporteEDUPlanillaProgramacionForm reporteForm = (ReporteEDUPlanillaProgramacionForm) this.formReporte;
			AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
			this.eduZonaList = ajaxService.getZonaByPaisEmpresaRegion(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa(), valor);
		}else
		{
			this.eduZonaList = null;
		}
	}
	
	/**
	 * @return the listaEmpresa
	 */
	public List getListaEmpresa() {
		return listaEmpresa;
	}

	/**
	 * @param listaEmpresa the listaEmpresa to set
	 */
	public void setListaEmpresa(List listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}
		
	/**
	 * @return the eduZonaList
	 */
	public LabelValue[] getEduZonaList() {
		return eduZonaList;
	}

	/**
	 * @param eduZonaList the eduZonaList to set
	 */
	public void setEduZonaList(LabelValue[] eduZonaList) {
		this.eduZonaList = eduZonaList;
	}
	
	/**
	 * @return the regionesCursosCapacitacionList
	 */
	public LabelValue[] getRegionesCursosCapacitacionList() {
		return regionesCursosCapacitacionList;
	}

	/**
	 * @param regionesCursosCapacitacionList the regionesCursosCapacitacionList to set
	 */
	public void setRegionesCursosCapacitacionList(
			LabelValue[] regionesCursosCapacitacionList) {
		this.regionesCursosCapacitacionList = regionesCursosCapacitacionList;
	}
	
}