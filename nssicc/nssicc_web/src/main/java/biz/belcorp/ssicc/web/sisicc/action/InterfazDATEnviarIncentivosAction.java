/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazDATEnviarIncentivosForm;

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
public class InterfazDATEnviarIncentivosAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoTodosList;
	private List siccSubAccesoList;
	
	private String codigoConexionExterna;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazDATEnviarIncentivosForm();
	}


	public void loadFechasPeriodo(String valor){
		log.debug(">>loadFechasPeriodo ");
		InterfazDATEnviarIncentivosForm form = (InterfazDATEnviarIncentivosForm) this.formInterfaz;	
		if (StringUtils.isNotBlank(valor)) {
 		   loadFechas(form, valor);
		}
	}
	
    public void loadFechas(InterfazDATEnviarIncentivosForm form, String periodo){
    	
    	AjaxService ajax = (AjaxService) getBean("ajaxService");
    	
    	form.setFechaInicio(ajax.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, periodo));
	    form.setFechaFin(ajax.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, periodo));
		
	    form.setValidarFechaFacturacion(ajax.getValidarDATFechaFacturacion(periodo));
    }
	
    public void showAccesoXCanal(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ val.getNewValue());
		InterfazDATEnviarIncentivosForm form = (InterfazDATEnviarIncentivosForm) this.formInterfaz;
		String canal = (String) val.getNewValue();		
	      
		
		if (log.isDebugEnabled()) {
			log.debug("loadAcceso");
		}
	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	
		if (canal == null) {
			this.siccAccesoList = svc
					.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
							.getCodigoISO());

		} else {

			this.siccAccesoList = getAccesoList(canal);
		}

		form.setCodigoAcceso(null);
	}
    
	@Override
	public String setValidarInterfaz() {
		InterfazDATEnviarIncentivosForm form = (InterfazDATEnviarIncentivosForm) this.formInterfaz;
		
		String fechaFacturacion=DateFormatUtils.format(form.getFechaFacturacionDate(),"dd/MM/yyyy");
		String validarFechaFacturacion=form.getValidarFechaFacturacion();
	    String fechaFinal = form.getFechaFin();
		String fechaInicio = form.getFechaInicio();
	    
		if (validarFechaFacturacion.equals(Constants.SI)){
			if (fechaFacturacion != "" ){
		    	int resultado  = DateUtil.compareDates(fechaFacturacion ,fechaFinal,"dd/MM/yyyy");    
		    	int resultado1 = DateUtil.compareDates(fechaInicio ,fechaFacturacion,"dd/MM/yyyy");  
		    	if ( resultado == 1 || resultado1==1) 
		        {	
		    		String mensaje = getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacion");
					return mensaje.concat(fechaInicio).concat(" - ").concat(fechaFinal);
		        }	    	  
	        }
			else {
	    		String mensaje = getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacionVacio");
	            return mensaje ; 
			}
		}

	    return null;
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
		InterfazDATEnviarIncentivosForm interfazDATForm = (InterfazDATEnviarIncentivosForm) this.formInterfaz ;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		interfazDATForm.setCodigoPais(pais.getCodigo());
		
		String codigoPais = pais.getCodigo();
		interfazDATForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(codigoPais, Constants.CODIGO_CANAL_DEFAULT));
		
		
        String conexionExterna = pais.getCodigoConexionExterna();
        String numSoliCabe = service.getExisteSolicitudesCabecera();
        if(numSoliCabe.equalsIgnoreCase(Constants.NUMERO_CERO)){
        	String msg = this.getResourceMessage("interfazDATEnviarAdministracionFlujosForm.message.NoExisteDatos");
        	this.addError("Error", msg);
        }
        
        Map criteriaPeriodo = new HashMap();
        criteriaPeriodo.put("codigoPais", pais.getCodigo());
        criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
        criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);
        criteriaPeriodo.put("codigoConexionExterna", conexionExterna);
        
        if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
        	 // Carga de los combos de Marca, Canal, Accesos y Subaccesos

            this.siccMarcaList = service.getMarcas();
    		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
    		this.siccAccesoList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
    		this.siccAccesoTodosList = service.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
    		this.siccSubAccesoList = service.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
    	
    		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService"); 
            PedidoControlFacturacion controlFacturacion = null; 
             
            controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);
            interfazDATForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			interfazDATForm.setFechaFacturacion(controlFacturacion.getFechaProceso());
			
			interfazDATForm.setFechaFacturacionDate(DateUtils.parseDate(interfazDATForm.getFechaFacturacion(),"dd/MM/yyyy"));
			loadFechas(interfazDATForm, interfazDATForm.getCodigoPeriodo());
			
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
        else {

        	Map datos = service.getPeriodoFechaCampanyaActivaSF(criteriaPeriodo);
        	String[] result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
        	interfazDATForm.setCodigoPeriodo(result[0]);
			interfazDATForm.setFechaFacturacion(result[1]);
			
			interfazDATForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			interfazDATForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			interfazDATForm.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
			interfazDATForm.setValidarFechaFacturacion(Constants.NO);
        }
        this.setCodigoConexionExterna(conexionExterna);
        interfazDATForm.setCodigoConexionExterna(conexionExterna);
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params =  super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		return params;
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

	/**
	 * @return the codigoConexionExterna
	 */
	public String getCodigoConexionExterna() {
		return codigoConexionExterna;
	}

	/**
	 * @param codigoConexionExterna the codigoConexionExterna to set
	 */
	public void setCodigoConexionExterna(String codigoConexionExterna) {
		this.codigoConexionExterna = codigoConexionExterna;
	}

    
    
    

}
