/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazDATEnviarAdministracionFlujosForm;

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
public class InterfazDATEnviarAdministracionFlujosAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoTodosList;
	private List siccSubAccesoList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazDATEnviarAdministracionFlujosForm interfazDATEnviarAdministracionFlujosForm = new InterfazDATEnviarAdministracionFlujosForm();
		return interfazDATEnviarAdministracionFlujosForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		 
		InterfazDATEnviarAdministracionFlujosForm interfazDATForm = (InterfazDATEnviarAdministracionFlujosForm) this.formInterfaz ;
	    InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	    
        String conexionExterna = pais.getCodigoConexionExterna();
        String numSoliCabe = service.getExisteSolicitudesCabecera();
        if(numSoliCabe.equalsIgnoreCase(Constants.NUMERO_CERO)){
        	this.addError("", this.getResourceMessage("interfazDATEnviarAdministracionFlujosForm.message.NoExisteDatos"));
        }

        Map criteriaPeriodo = new HashMap();
        criteriaPeriodo.put("codigoPais", pais.getCodigo());
        criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
        criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);
        criteriaPeriodo.put("codigoConexionExterna", conexionExterna);

        if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
        	// Carga de los combos de Marca, Canal, Accesos y Subaccesos
        	InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        	this.siccMarcaList = svc.getMarcas();
			this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
			this.siccAccesoList = svc.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
			this.siccAccesoTodosList = svc.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
			this.siccSubAccesoList = svc.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
		
            String codigoPais = pais.getCodigo();
            interfazDATForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(codigoPais, Constants.CODIGO_CANAL_DEFAULT));

            MantenimientoOCRPedidoControlFacturacionService serviceCF =(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService"); 
            PedidoControlFacturacion controlFacturacion = null; 
            controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);
            interfazDATForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
            interfazDATForm.setFechaFacturacion(controlFacturacion.getFechaProceso());
            Date fechaFacturacionDate = DateUtil.convertStringToDate(controlFacturacion.getFechaProceso());
            interfazDATForm.setFechaFacturacionDate(fechaFacturacionDate);

            interfazDATForm.setValidarFechaFacturacion(Constants.SI);
			Map criteriaProgramacionCierre = new HashMap();
			criteriaProgramacionCierre.put("tipoCierre", Constants.LET_TIPO_CIERRE_CAMPANHA);
			criteriaProgramacionCierre.put("estadoCierre", Constants.DAT_ESTADO_CIERRE); 
			criteriaProgramacionCierre.put("campanhaProceso", interfazDATForm.getCodigoPeriodo()); 
			MantenimientoFACGenericoService facservice = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");		
			List listCierreCampanha = facservice.getCierreFacturacion(criteriaProgramacionCierre);
			if (listCierreCampanha != null && listCierreCampanha.size() > 0)
				interfazDATForm.setValidarFechaFacturacion(Constants.NO);

        }
        else{
        	this.siccMarcaList = new ArrayList();        
            this.siccCanalList =new ArrayList();
            this.siccAccesoList = new ArrayList();
            this.siccAccesoTodosList = new ArrayList();
            this.siccSubAccesoList = new ArrayList();
        	
        	Map datos = service.getPeriodoFechaCampanyaActivaSF(criteriaPeriodo);
        	String[] result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
        	interfazDATForm.setCodigoPeriodo(result[0]);
			interfazDATForm.setFechaFacturacion(result[1]);
			
			interfazDATForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			interfazDATForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			interfazDATForm.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
			interfazDATForm.setValidarFechaFacturacion(Constants.NO);
        }
          interfazDATForm.setCodigoConexionExterna(conexionExterna);
          //log.debug(getViewForward());
          
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		InterfazDATEnviarAdministracionFlujosForm f = (InterfazDATEnviarAdministracionFlujosForm) this.formInterfaz ;
		params =  super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		
		String fechaFacturacion = DateUtil.convertDateToString(f.getFechaFacturacionDate());
		params.put("fechaFacturacion", fechaFacturacion);
		return params;
	}

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	log.debug("Entering 'before' method");
    	InterfazDATService svc = (InterfazDATService) getBean("sisicc.interfazDATService");
    	String codigoConexionExterna = (String) params.get("codigoConexionExterna");
    	if (StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
    		svc.executeCargarTablaInterfaz(params);
    	}
    	return;
    }
	
    //PER-SiCC-2016-0051 Validaciones Datamart - INICIO
	@Override
	public String setValidarInterfaz() {
		log.debug("Entering 'setValidarInterfaz' method");
		InterfazDATEnviarAdministracionFlujosForm f = (InterfazDATEnviarAdministracionFlujosForm) this.formInterfaz;
    	InterfazDATService svc = (InterfazDATService) getBean("sisicc.interfazDATService");
    	
    	Map criteria = new HashMap();
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
    	criteria.put("codigoPais", pais.getCodigo());
    	String mensaje = svc.getValidacionInterfazDatamart(criteria);
    	
    	if(StringUtils.isBlank(mensaje))
    	{
        	criteria.put("codigoPeriodo", f.getCodigoPeriodo());
        	String vccp = svc.getValidacionCierreCampanyaPendiente(criteria); 
        	
        	setMensajeConfirmacionEjecucion(this.getResourceMessage("confirm.execute.interfaz"));
        	if(StringUtils.equals(vccp, Constants.ESTADO_ACTIVO))
        	{
        		setMensajeConfirmacionEjecucion(this.getResourceMessage("interfazDATEnviarAdministracionFlujosForm.message.validacion.ccp") + "<br/><br/>" + this.getResourceMessage("confirm.execute.interfaz"));
        	}
    	}
    	
    	return mensaje;
	}
	//PER-SiCC-2016-0051 Validaciones Datamart - FIN

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
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	/**
	 * @return the siccAccesoTodosList
	 */
	public List getSiccAccesoTodosList() {
		return siccAccesoTodosList;
	}

	/**
	 * @param siccAccesoTodosList the siccAccesoTodosList to set
	 */
	public void setSiccAccesoTodosList(List siccAccesoTodosList) {
		this.siccAccesoTodosList = siccAccesoTodosList;
	}

	/**
	 * @return the siccSubAccesoList
	 */
	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	/**
	 * @param siccSubAccesoList the siccSubAccesoList to set
	 */
	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}

	
    

}
