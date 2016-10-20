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
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazDATEnviarDatamartSacForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 */
@ManagedBean
@SessionScoped
public class InterfazDATEnviarDatamartSacAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazDATEnviarDatamartSacForm();
	}
	
	public void loadFechasPeriodo(String valor){
		log.debug(">>loadFechasPeriodo ");
		InterfazDATEnviarDatamartSacForm form = (InterfazDATEnviarDatamartSacForm) this.formInterfaz;	
		
		if (StringUtils.isNotBlank(valor)) {
			loadFechas(form, valor);
		}
	
	}
	
	public void showAccesoXCanal(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ val.getNewValue());
		InterfazDATEnviarDatamartSacForm form = (InterfazDATEnviarDatamartSacForm) this.formInterfaz;
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
	
	
	 public void loadFechas(InterfazDATEnviarDatamartSacForm form, String periodo){
	    	
	    	AjaxService ajax = (AjaxService) getBean("ajaxService");
	    	
	    	form.setFechaInicio(ajax.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, periodo));
		    form.setFechaFin(ajax.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, periodo));
		}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		
		InterfazDATEnviarDatamartSacForm enviarDatamartSacForm = (InterfazDATEnviarDatamartSacForm)this.formInterfaz;
        
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		enviarDatamartSacForm.setCodigoPais(pais.getCodigo());
		
		String valIndiImpu = (String) this.parametrosPantalla.get("valIndiImpu");
		String tipoDocumentoDetalle = (String) this.parametrosPantalla.get("tipoDocumentoDetalle");
		String tipoDocumentoCabecera = (String) this.parametrosPantalla.get("tipoDocumentoCabecera");
		String tipoDocumentoOCCabecera = (String) this.parametrosPantalla.get("tipoDocumentoOCCabecera");
		
		enviarDatamartSacForm.setValIndiImpu(valIndiImpu);
		enviarDatamartSacForm.setTipoDocumentoDetalle(tipoDocumentoDetalle);
		enviarDatamartSacForm.setTipoDocumentoCabecera(tipoDocumentoCabecera);
		enviarDatamartSacForm.setTipoDocumentoOCCabecera(tipoDocumentoOCCabecera);
		
		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
	    InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	    this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
	

        Map criteriaPeriodo = new HashMap();
		 criteriaPeriodo.put("codigoPais", pais.getCodigo());
		 criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		 criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		 MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		 PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteriaPeriodo);
		 
		 enviarDatamartSacForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		 enviarDatamartSacForm.setFechaFacturacionDate(DateUtils.parseDate(controlFacturacion.getFechaProceso(),"dd/MM/yyyy"));
        
   
		 loadFechas(enviarDatamartSacForm, enviarDatamartSacForm.getCodigoPeriodo());
	}
	
	@Override
	public String setValidarInterfaz() {
		InterfazDATEnviarDatamartSacForm form = (InterfazDATEnviarDatamartSacForm) this.formInterfaz;
		
		String fechaFacturacion=   DateUtil.convertDateToString(form.getFechaFacturacionDate()); 
	    String fechaFinal = form.getFechaFin();
		String fechaInicio = form.getFechaInicio();

			if (StringUtils.isNotBlank(fechaFacturacion) && 
					 StringUtils.isNotBlank(fechaFinal) && 
					     StringUtils.isNotBlank(fechaInicio)){
		    	int resultado  = DateUtil.compareDates(fechaFacturacion ,fechaFinal,"dd/MM/yyyy");    
		    	int resultado1 = DateUtil.compareDates(fechaInicio ,fechaFacturacion,"dd/MM/yyyy");  
		    	if ( resultado == 1 || resultado1==1) 
		        {	
		    		String mensaje = getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacion");
					return mensaje.concat(fechaInicio).concat(" - ").concat(fechaFinal);
		        }	    	  
	        }

	    return null;
	}
	
	
	

	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params = super.prepareParamsBeforeExecute(params, form);

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String numeroLote = service.getNumeroLoteIntHistoLotes(params,Constants.PREFIJO_SISTEMA_DATAMART);
		params.put("numeroLote", numeroLote);
		
		InterfazDATEnviarDatamartSacForm enviarDatamartSacForm = (InterfazDATEnviarDatamartSacForm)this.formInterfaz;
        
		params.put("valIndiImpu", enviarDatamartSacForm.getValIndiImpu());
		params.put("tipoDocumentoDetalle", enviarDatamartSacForm.getTipoDocumentoDetalle());
		params.put("tipoDocumentoCabecera", enviarDatamartSacForm.getTipoDocumentoCabecera());
		params.put("tipoDocumentoOCCabecera", enviarDatamartSacForm.getTipoDocumentoOCCabecera());
		
		
		String fechaFacturacion = DateUtil.convertDateToString(enviarDatamartSacForm.getFechaFacturacionDate());
		params.put("fechaFacturacion", fechaFacturacion);
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

	
    

}
