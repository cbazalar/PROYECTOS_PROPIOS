/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazRECService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.scsicc.action.ReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction;
import biz.belcorp.ssicc.web.scsicc.action.ReporteRECEnviarTransferenciaBoletasRecojoDetalleAction;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRECEnviarTransferenciaBoletasRecojoForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazRECEnviarTransferenciaBoletasRecojoAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4244216716479325587L;
	private List siccRegionList;
	private List siccOperacionList;
	private List recIndicTransBorecAnula;
	private Boolean mostrarDatatable;
	
	@ManagedProperty(value = "#{reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction}")
    private ReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction;
	
	@ManagedProperty(value = "#{reporteRECEnviarTransferenciaBoletasRecojoDetalleAction}")
    private ReporteRECEnviarTransferenciaBoletasRecojoDetalleAction reporteRECEnviarTransferenciaBoletasRecojoDetalleAction;
	
	
	
	public List getRecIndicTransBorecAnula() {
		return recIndicTransBorecAnula;
	}


	public void setRecIndicTransBorecAnula(List recIndicTransBorecAnula) {
		this.recIndicTransBorecAnula = recIndicTransBorecAnula;
	}


	public List getSiccRegionList() {
		return siccRegionList;
	}


	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}


	public List getSiccOperacionList() {
		return siccOperacionList;
	}


	public void setSiccOperacionList(List siccOperacionList) {
		this.siccOperacionList = siccOperacionList;
	}
	
	


	/**
	 * @return the reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction
	 */
	public ReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction getReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction() {
		return reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction;
	}


	/**
	 * @param reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction the reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction to set
	 */
	public void setReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction(
			ReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction) {
		this.reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction = reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction;
	}


	/**
	 * @return the reporteRECEnviarTransferenciaBoletasRecojoDetalleAction
	 */
	public ReporteRECEnviarTransferenciaBoletasRecojoDetalleAction getReporteRECEnviarTransferenciaBoletasRecojoDetalleAction() {
		return reporteRECEnviarTransferenciaBoletasRecojoDetalleAction;
	}


	/**
	 * @param reporteRECEnviarTransferenciaBoletasRecojoDetalleAction the reporteRECEnviarTransferenciaBoletasRecojoDetalleAction to set
	 */
	public void setReporteRECEnviarTransferenciaBoletasRecojoDetalleAction(
			ReporteRECEnviarTransferenciaBoletasRecojoDetalleAction reporteRECEnviarTransferenciaBoletasRecojoDetalleAction) {
		this.reporteRECEnviarTransferenciaBoletasRecojoDetalleAction = reporteRECEnviarTransferenciaBoletasRecojoDetalleAction;
	}


	/**
	 * @return the mostrarDatatable
	 */
	public Boolean getMostrarDatatable() {
		return mostrarDatatable;
	}


	/**
	 * @param mostrarDatatable the mostrarDatatable to set
	 */
	public void setMostrarDatatable(Boolean mostrarDatatable) {
		this.mostrarDatatable = mostrarDatatable;
	}


	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRECEnviarTransferenciaBoletasRecojoForm interfazRECEnviarTransferenciaBoletasRecojoForm = new InterfazRECEnviarTransferenciaBoletasRecojoForm();
		return interfazRECEnviarTransferenciaBoletasRecojoForm;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		this.mostrarBotonBuscar=true;
		this.mostrarListaBusqueda=true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaRegion = new HashMap();
		// Ccarga las regiones
		siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaRegion);
		MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");
		Map criteriaOperacion = new HashMap();
        criteriaOperacion.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        siccOperacionList=operacionService.getOperacionesHomologadasByCodigoPais(criteriaOperacion);
        InterfazRECEnviarTransferenciaBoletasRecojoForm interfazForm = (InterfazRECEnviarTransferenciaBoletasRecojoForm)formInterfaz;
        interfazForm.setFechaFinalD(new Date(System.currentTimeMillis()));  
        interfazForm.setFechaInicialD(new Date(System.currentTimeMillis()));;
        this.mostrarDatatable=false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{

		InterfazRECEnviarTransferenciaBoletasRecojoForm interfazForm = (InterfazRECEnviarTransferenciaBoletasRecojoForm)formInterfaz;
		
		params.put("regionList",(interfazForm.getRegionList() == null) ? new ArrayList(): Arrays.asList(interfazForm.getRegionList()));
		
		if(interfazForm.getFechaInicialD() != null){
			interfazForm.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, interfazForm.getFechaInicialD()));
		}
		
		if(interfazForm.getFechaFinalD() != null){
			interfazForm.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, interfazForm.getFechaFinalD()));
		}
		
		params.put("fechaInicial", interfazForm.getFechaInicial());
		params.put("fechaFinal", interfazForm.getFechaFinal());
		params.put("usuario", this.getmPantallaPrincipalBean().getCurrentUser());
		params.put("codigoUsuario", this.getmPantallaPrincipalBean().getCurrentUser().getCodigo());
		
		//INI PARAMS CABECERA
		String condicion="";
		
		String titulo = null;
		
		titulo= this.getResourceMessage("reporteRECEnviarTransferenciaBoletasRecojoAnulacionZona.titulo");
		String condicionRegion;
		
		if (interfazForm.getCodigoOperacion().equals(Constants.REC_INDIC_TRANS_BOREC_ANULA))
		 condicionRegion = obtieneCondicion(interfazForm.getRegionList(), "RE.COD_REGI", "'");
		else condicionRegion = obtieneCondicion(interfazForm.getRegionList(), "CB.COD_REGI", "'");
		
		condicion = condicionRegion;
		
		params.put("condicion", condicion);
		params.put("titulo", titulo);
		
		//FIN PARAMS CABECERA
		
		return params;
	
		
	}
	
	/**
	 * @return
	 */
	public String setValidarReporte() {
		InterfazRECEnviarTransferenciaBoletasRecojoForm interfazForm = (InterfazRECEnviarTransferenciaBoletasRecojoForm)formInterfaz;
		interfazForm.setFechaInicial(DateUtil.getDate(interfazForm.getFechaInicialD()));
		interfazForm.setFechaFinal(DateUtil.getDate(interfazForm.getFechaFinalD()));
		if(!StringUtils.isBlank(interfazForm.getFechaFinal()) && !StringUtils.isBlank(interfazForm.getFechaFinal()) )
			if (interfazForm.getFechaInicialD().compareTo(interfazForm.getFechaFinalD()) < 0) 
					return "Error al comparar las fechas";
			
	    return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setFindAttributes()
	 */
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) log.debug("Entering 'search' method");
        
        List listTransferenciaBoletasRecojo = null;
        this.mostrarDatatable=false;
        
        InterfazRECEnviarTransferenciaBoletasRecojoForm f = (InterfazRECEnviarTransferenciaBoletasRecojoForm) formInterfaz;
        Map criteria = BeanUtils.describe(f);
        
        InterfazRECEnviarTransferenciaBoletasRecojoForm interfazForm = (InterfazRECEnviarTransferenciaBoletasRecojoForm)formInterfaz;
        criteria.put("regionList",(interfazForm.getRegionList() == null) ? new ArrayList(): Arrays.asList(interfazForm.getRegionList()));
        String fechaFinal = DateUtil.convertDateToString(f.getFechaFinalD());
        String fechaIncial = DateUtil.convertDateToString(f.getFechaInicialD());
        criteria.put("fechaFinal", fechaFinal);
        criteria.put("fechaInicial", fechaIncial);

        InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
        if (f.getCodigoOperacion().equals(Constants.REC_INDIC_TRANS_BOREC_ANULA))listTransferenciaBoletasRecojo = interfazSiCCService.getConsolidadoTransferenciaBoletasRecojoConAnulacion(criteria);
        else listTransferenciaBoletasRecojo = interfazSiCCService.getConsolidadoTransferenciaBoletasRecojoSinAnulacion(criteria);
        
        recIndicTransBorecAnula=listTransferenciaBoletasRecojo;
        
        if(listTransferenciaBoletasRecojo.size()>0){
        	this.mostrarDatatable=true;
        }
        
        String condicion="";		
		String condicionRegion;
		if (f.getCodigoOperacion().equals(Constants.REC_INDIC_TRANS_BOREC_ANULA))
		 condicionRegion = obtieneCondicion(f.getRegionList(), "RE.COD_REGI", "'");
		else condicionRegion = obtieneCondicion(f.getRegionList(), "CB.COD_REGI", "'");
		
		condicion = condicionRegion;
		
		criteria.put("condicion", condicion);

        // Seteamos los valores para pasarlos a los reportes
     	this.reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction.setMapProperties(criteria);
     	this.reporteRECEnviarTransferenciaBoletasRecojoDetalleAction.setMapProperties(criteria);
        
        return recIndicTransBorecAnula;
	}
	
	/**
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	protected String obtieneCondicion(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	}
	
    /**
     * @param actionEvent
     * @throws Exception
     */
    public void generarReporteCabecera(ActionEvent actionEvent) throws Exception{
    	InterfazRECEnviarTransferenciaBoletasRecojoForm interfazForm = (InterfazRECEnviarTransferenciaBoletasRecojoForm)formInterfaz;
    	this.reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction.setFormatoExportacion("VPDF");
    	this.reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction.setTipoOperacion(interfazForm.getCodigoOperacion());
    	this.reporteRECEnviarTransferenciaBoletasRecojoCabeceraAction.executeReporte();
    	this.redireccionarPagina("reporteRECEnviarTransferenciaBoletasRecojoForm");
    }

    /**
     * @param actionEvent
     * @throws Exception
     */
    public void generarReporteDetalle(ActionEvent actionEvent) throws Exception{
    	InterfazRECEnviarTransferenciaBoletasRecojoForm interfazForm = (InterfazRECEnviarTransferenciaBoletasRecojoForm)formInterfaz;
    	this.reporteRECEnviarTransferenciaBoletasRecojoDetalleAction.setFormatoExportacion("XLS");
    	this.reporteRECEnviarTransferenciaBoletasRecojoDetalleAction.setTipoOperacion(interfazForm.getCodigoOperacion());
    	this.reporteRECEnviarTransferenciaBoletasRecojoDetalleAction.executeReporte();
    }

    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult)
     */
    @Override
    protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception {
    	// Invocamos al proceso que actualiza el status de envio de las consultoras
        if(log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'afterExecuteInterfaz'");
        }
        // Validamos el resultado de la ejecucion
        
        InterfazRECService svc = (InterfazRECService) getBean("sisicc.interfazRECService");
        svc.updateInterfazEnviarTransferenciaBoletasRecojoExitosa(params);
    }

    

}
