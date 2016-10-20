package biz.belcorp.ssicc.web.edu.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.bean.ReporteEnviadoMail;
import biz.belcorp.ssicc.reportes.web.edu.form.ProcesoEDUGenerarPlanillaProgramacionForm;
import biz.belcorp.ssicc.service.AjaxService2;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */

@ManagedBean
@SessionScoped
public class ReporteEDUGenerarPlanillaProgramacionProcesoAction extends	BaseReporteAbstractAction {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -2547530344465671149L;
	private List empresaComercializadoraList;
	private LabelValue[] regionesCursosCapacitacionList;
	private List eduTipoProcesoList;
	private List cargaArchivosList;

	//nombre del subreporte 
	private String nameSubReporte;
	
	private String   indicadorReporte;
	private String	 nivelGenerarPlanilla; 
	
	private String[] listaRegiones;
	private String[] listaTotal;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = new ProcesoEDUGenerarPlanillaProgramacionForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {				
		return "reporteMaestroVertical";
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {	
		return this.nameSubReporte;			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteEDUGenerarPlanillaProgramacionProcesoService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "edu.mailReporteGenerarPlanillaProgramacionService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteEDUGenerarPlanillaProgramacionProcesoAction.setViewAtributes' method");            
        }
					
			ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formReporte;			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
					
			String codigoProcesoBatch = this.mPantallaPrincipalBean.getCodigoProcesoBatch();
			reporteForm.setCodigoProcesoBatch(codigoProcesoBatch);
			reporteForm.setCodigoPais(pais.getCodigo());
			loadCombos();
			
			/*Inicializamos la Empresa, siempre despues de LoadCombos*/
			List listaEmpresa = this.empresaComercializadoraList; 
					
			if ((listaEmpresa!=null) && (listaEmpresa.size() > 0)){
				EmpresaComercializadora empresa = new EmpresaComercializadora();
				empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				reporteForm.setCodigoEmpresa(empresa.getCodigoEmpresa());				
			}
			
			reporteForm.setTipoProceso(Constants.EDU_TIPO_PROCESO_NORMAL);
			reporteForm.setCampanhaProceso(getCampanhaProceso(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa()));
			
			//Adiconar Lista de Procesos - Sergio Buchelli
		
			/* Carga Prametros */
			cargaParametros();
			//setListProcesosPorMostrar();
			
			this.visualizarReporte = false;
			this.mostrarReporteMailPDF = true;
			this.mostrarReportePDF = false;
	}
	
	/**
	 * Seteando Combos
	 * @param request
	 * @throws Exception
	 */
	private void loadCombos()throws Exception {
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService)this.getBeanService("edu.mantenimientoEDUGenericoService");				
		List listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		
		this.empresaComercializadoraList = listaEmpresa;
		
		ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formReporte;
		
		AjaxService2 ajaxService = (AjaxService2)this.getBeanService("ajaxService2");	
		this.regionesCursosCapacitacionList = ajaxService.getRegionesEDUByPaisEmpresa(reporteForm.getCodigoPais(),reporteForm.getCodigoEmpresa());
						
		/* Tipos de Proceso */
		this.eduTipoProcesoList = loadProcesos(); 
			
	}
		
	/**
	 * @return
	 */
	private List loadProcesos() {
		
		List resultado=new ArrayList();
		Base[] mes = new Base[3];
				
		String mensajeTipoEnvioNormal = this.getResourceMessage("procesoEDUGenerarPlanillaProgramacionForm.tipoProcesoNormal");				
		String mensajeTipoProcesoReproceso = this.getResourceMessage("procesoEDUGenerarPlanillaProgramacionForm.tipoProcesoReproceso");		
		String mensajeTipoEnvioReenvio = this.getResourceMessage("procesoEDUGenerarPlanillaProgramacionForm.tipoProcesoReenvio"); 
					
		mes[0] = new Base();
		mes[0].setCodigo(Constants.EDU_TIPO_PROCESO_NORMAL);
		mes[0].setDescripcion(mensajeTipoEnvioNormal);
		resultado.add(mes[0]);
		
		mes[1] = new Base();
		mes[1].setCodigo(Constants.EDU_TIPO_PROCESO_REPROCESO);
		mes[1].setDescripcion(mensajeTipoProcesoReproceso);
		resultado.add(mes[1]);
		
		mes[2] = new Base();
		mes[2].setCodigo(Constants.EDU_TIPO_PROCESO_REENVIO);
		mes[2].setDescripcion(mensajeTipoEnvioReenvio);
		resultado.add(mes[2]);
		
		return resultado;
	}
	
	/**
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @return
	 */
	private String getCampanhaProceso(String codigoPais, String codigoEmpresa){
		
		// Obteniendo Campaña de Proceso
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService)
				this.getBeanService("edu.procesoEDUCalificacionAptasAutomaticaService");				
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		return codigoPeriodo;
		
	}
	
	/**
	 * 
	 */
	private void cargaParametros() {
		
		ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = (ProcesoEDUGenerarPlanillaProgramacionForm)this.formReporte; 
		
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(reporteForm.getCodigoPais());
		parametro.setCodigoEmpresa(reporteForm.getCodigoEmpresa());
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro =	service.getParametroCurso(parametro);
		reporteForm.setNivelUnidadAdm(parametro.getNivelUnidadAdm());
		reporteForm.setIndicadorEnvioProgramadas(parametro.getIndicadorEnvioProgramadas());
		reporteForm.setIndicadorEnvioResumen(parametro.getIndicadorEnvioResumen());
		reporteForm.setIndicadorConsultoraRezagada(parametro.getIndicadorConsultoraRezagada());
	}
	
	/**
	 * 
	 */
	/*
	private void setListProcesosPorMostrar() {
		// Colocando procesos a ejecutar 
		ArrayList resultado = new ArrayList();
		ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = (ProcesoEDUGenerarPlanillaProgramacionForm)this.formReporte;
		
		for(int i=1, j=1; i <= 6; i++ ) {
			String cadena = new Integer(i).toString().trim();
			String cadenaMostrar = new Integer(j).toString().trim();
			switch (i) {
			case 1: 
				if (Constants.NUMERO_UNO.equals(reporteForm.getIndicadorConsultoraRezagada())) { 
				 this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
				 j++;
				} 
				 break;
			case 2: 
				 this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
				 j++;
				 break;

			case 3: 
				 this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
				 j++;
				 break;
				 
			case 4: 
				if (!Constants.EDU_PARAM_CURSO_NO_GENERA_PLANILLA.equals(reporteForm.getNivelUnidadAdm())) { 
					this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
					j++;
				}
				break;
			case 5:
				if (Constants.EDU_INDICADOR_ENVIO_PROGRAMADAS_SI.equals(reporteForm.getIndicadorEnvioProgramadas())) { 
					this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
					j++;
				}
				break;
			case 6:
				if (Constants.EDU_INDICADOR_ENVIO_RESUMEN_SI.equals(reporteForm.getIndicadorEnvioResumen())) { 
					this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
					j++;
				}
				break;	
			}
				
		}			
		this.cargaArchivosList = resultado;		
	}
	/**
	 * Obtiene descripcion del proceso del archivo de recursos
	 * @param resultado
	 * @param codigo
	 * @param keyMensaje
	 */
	/*
	private void adicionarProceso(ArrayList resultado, String codigo, String keyMensaje) {
		Base bean = new Base();
		String proceso = this.getReportResourceMessage(keyMensaje);				
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);			
		resultado.add(bean);
	}
	*/	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{		
							
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteEDUGenerarPlanillaProgramacionProcesoAction.prepareParamsBeforeExecute' method");
		}		
		
		ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = (ProcesoEDUGenerarPlanillaProgramacionForm)this.formReporte;
		
		reporteForm.setNroReporte("");
		reporteForm.setTitulo(this.getResourceMessage("reporteEDUPlanillaProgramacionForm.titulo"));
		reporteForm.setFormatoExportacion("PDF");
		reporteForm.setCodigoProceso(reporteForm.getCodigoProcesoBatch());
		reporteForm.setUsuario(this.mPantallaPrincipalBean.getCurrentUser());
		
		ReporteEnviadoMail reporteEnviadoMail = this.currentReporteEnviadoMail; 
		
		if (!this.isVisualizarReporte()) {
			String temp = this.listaTotal[this.getNroReporteProcesando() - 1 ];
		    int pos = StringUtils.indexOf(temp, '|');
		    
		    MantenimientoEDUCursoCapacitacionService service = (MantenimientoEDUCursoCapacitacionService)this.getBeanService("edu.mantenimientoEDUCursoCapacitacionService");		    
		    		    
		    if (pos < 0) {
		    	this.indicadorReporte = Constants.REPORTE_EDU_GENERACION_PLANILLA_REGION;
		    	reporteForm.setCodigoRegion(temp);
		    	reporteForm.setCodigoZona(null);
		    	
		    	RegionCursoCapacitacion region = new RegionCursoCapacitacion();
				region.setCodigoPais(reporteForm.getCodigoPais());
				region.setCodigoEmpresa(reporteForm.getCodigoEmpresa());
				region.setCodigoRegion(temp);
				List listaRegion = (List)service.getRegion(region);
				RegionCursoCapacitacion regionCursoCapacitacion = (RegionCursoCapacitacion)(listaRegion.get(0));
		    	
				reporteEnviadoMail.setRegion(regionCursoCapacitacion.getDescripcionRegion());		    	
		    }
		    else {
		    	this.indicadorReporte = Constants.REPORTE_EDU_GENERACION_PLANILLA_ZONA;
		    	reporteForm.setCodigoRegion(StringUtils.substringBefore(temp,"|"));
		    	reporteForm.setCodigoZona(StringUtils.substringAfter(temp,"|"));
		    		    	
		    	ZonaCursoCapacitacion zona = new ZonaCursoCapacitacion();
		    	zona.setCodigoPais(reporteForm.getCodigoPais());
		    	zona.setCodigoEmpresa(reporteForm.getCodigoEmpresa());
		    	zona.setCodigoRegion(reporteForm.getCodigoRegion());
		    	zona.setCodigoZona(reporteForm.getCodigoZona());										    	
				List listaZona = (List)service.getZona(zona);						
				ZonaCursoCapacitacion zonaResult =  (ZonaCursoCapacitacion)(listaZona.get(0)); 				
				reporteEnviadoMail.setRegion(zonaResult.getDescripcionRegion());							    	
		    	reporteEnviadoMail.setZona(zonaResult.getDescripcionZona());				    	
		    }
		    reporteForm.setIndicadorReporte(this.indicadorReporte);
		    reporteForm.setNivelGenerarPlanilla(this.nivelGenerarPlanilla);
		    
		}		
		
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte()
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteEDUGenerarPlanillaProgramacion.error.nivelGenerarPlanilla";
	}	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {
		
		ProcesoEDUGenerarPlanillaProgramacionForm reporteForm = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formReporte;
		
		MantenimientoEDUCursoCapacitacionService siccService = (MantenimientoEDUCursoCapacitacionService) 
				this.getBeanService("edu.mantenimientoEDUCursoCapacitacionService");
				
		MantenimientoEDUGenericoService eduService = (MantenimientoEDUGenericoService) 
				this.getBeanService("edu.mantenimientoEDUGenericoService");
						
		boolean encontrotodos = false;
		
		this.listaRegiones = reporteForm.getRegiones();		
		for (int i=0; i < this.listaRegiones.length; i++ ) {
			if ("Todos".equals(this.listaRegiones[i])) {
				encontrotodos = true;
				break;
			}
		}
		
		/* En caso haya seleccionado Todos */
		if (encontrotodos) {
			RegionCursoCapacitacion region = new RegionCursoCapacitacion();
			region.setCodigoPais(reporteForm.getCodigoPais());
			region.setCodigoEmpresa(reporteForm.getCodigoEmpresa());
			List resultado = siccService.getRegion(region);
			this.listaRegiones = new String[resultado.size()];
			for (int i=0; i < resultado.size(); i++ ) {
				RegionCursoCapacitacion regTemp = (RegionCursoCapacitacion) resultado.get(i);
				String temp = regTemp.getCodigoRegion();
				this.listaRegiones[i] = new String(temp);
			}	
		}
		
		/* obteniendo el nivel de parametrizacion de la Generacin de Planilla */
		ParametroCursoCapacitacion cabecera = new ParametroCursoCapacitacion();
		cabecera.setCodigoPais(reporteForm.getCodigoPais());
		cabecera.setCodigoEmpresa(reporteForm.getCodigoEmpresa());
		cabecera.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		cabecera = (ParametroCursoCapacitacion) eduService.getParametroCurso(cabecera);
		this.nivelGenerarPlanilla = cabecera.getNivelGenerarPlanilla();
		
		if (Constants.INDICADOR_REPORTE_EDU_GENERACION_PLANILLA_SIN_GENERAR.equals(this.nivelGenerarPlanilla)) {
			return -1;
		}
		
		/* obteniendo lista total */
		if (Constants.INDICADOR_REPORTE_EDU_GENERACION_PLANILLA_ZONA.equals(this.nivelGenerarPlanilla)) {
			Map zonaMap = new HashMap();
			zonaMap.put("codigoPais", reporteForm.getCodigoPais());
			zonaMap.put("codigoEmpresa", reporteForm.getCodigoEmpresa());
			zonaMap.put("codigoRegion", this.listaRegiones);
			List listaZonas = siccService.getZonaByRegionSelected(zonaMap);
			
			int tamanno = this.listaRegiones.length + listaZonas.size();
			this.listaTotal = new String[tamanno];
			for (int i=0; i < this.listaRegiones.length; i++) {
				this.listaTotal[i] = this.listaRegiones[i]; 
			}
			for (int i=0, j=this.listaRegiones.length; i < listaZonas.size() ; i++, j++) {
				ZonaCursoCapacitacion zona = (ZonaCursoCapacitacion) listaZonas.get(i); 
				this.listaTotal[j] = zona.getCodigoRegion() + "|" + zona.getCodigoZona();
			}
		}
		else {
			this.listaTotal = this.listaRegiones;
		}
		return this.listaTotal.length;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String campanhaProceso = (String)parameterMap.get("campanhaProceso");
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		String regionZona = this.listaTotal[this.getNroReporteProcesando() - 1 ];
		regionZona = StringUtils.remove(regionZona,'|');
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		if(Constants.REPORTE_EDU_GENERACION_PLANILLA_ZONA.equals(indicadorReporte)){
			regionZona=codigoRegion+ regionZona;
		}
		nombreArchivoReporte = this.getPrefijoArchivo() + this.indicadorReporte +  "_" +regionZona  
					 + "_" + campanhaProceso + "_"+
					sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getValorFiltroGrabarReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = new String();
		if (Constants.REPORTE_EDU_GENERACION_PLANILLA_ZONA.equals(this.indicadorReporte)) 
			filtro = "Zona: ";
		else
			filtro = "Región: ";
		return filtro + this.listaTotal[this.getNroReporteProcesando() - 1 ];
	}

	/**
	 * @return the empresaComercializadoraList
	 */
	public List getEmpresaComercializadoraList() {
		return empresaComercializadoraList;
	}

	/**
	 * @param empresaComercializadoraList the empresaComercializadoraList to set
	 */
	public void setEmpresaComercializadoraList(List empresaComercializadoraList) {
		this.empresaComercializadoraList = empresaComercializadoraList;
	}

	/**
	 * @return the eduTipoProcesoList
	 */
	public List getEduTipoProcesoList() {
		return eduTipoProcesoList;
	}

	/**
	 * @param eduTipoProcesoList the eduTipoProcesoList to set
	 */
	public void setEduTipoProcesoList(List eduTipoProcesoList) {
		this.eduTipoProcesoList = eduTipoProcesoList;
	}
	
	/**
	 * @return the nameSubReporte
	 */
	public String getNameSubReporte() {
		return nameSubReporte;
	}

	/**
	 * @param nameSubReporte the nameSubReporte to set
	 */
	public void setNameSubReporte(String nameSubReporte) {
		this.nameSubReporte = nameSubReporte;
	}
	
	/**
	 * @return the cargaArchivosList
	 */
	public List getCargaArchivosList() {
		return cargaArchivosList;
	}

	/**
	 * @param cargaArchivosList the cargaArchivosList to set
	 */
	public void setCargaArchivosList(List cargaArchivosList) {
		this.cargaArchivosList = cargaArchivosList;
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
