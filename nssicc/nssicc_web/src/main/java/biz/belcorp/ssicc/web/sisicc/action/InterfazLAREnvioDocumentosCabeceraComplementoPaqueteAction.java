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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazLARService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm;


@ManagedBean
@SessionScoped
public class InterfazLAREnvioDocumentosCabeceraComplementoPaqueteAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm();
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
		
	    InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm f = (InterfazLAREnvioDocumentosCabeceraComplementoPaqueteForm) this.formInterfaz;
	    
	    PedidoControlFacturacion periodo = getCodigoPeriodo(pais);
	    
	    f.setCodigoPeriodo(periodo.getCodigoPeriodo());
	    
	        // Carga de los combos de Marca, Canal, Accesos y Subaccesos
	    InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	    
	    siccMarcaList = svc.getMarcas(); 
	    siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
	
	}

	
	 /**
     * @param session
     * @return
     */
    private PedidoControlFacturacion getCodigoPeriodo(Pais pais) {

		Map criteriaPeriodo = new HashMap();
		MantenimientoOCRPedidoControlFacturacionService service = 
			 (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);
	    PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
	    log.debug("periodo actual " + controlFacturacion.getCodigoPeriodo());
		return controlFacturacion;
	}
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
     */
    protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception {    	
    	super.afterExecuteInterfaz(params, interfazExecutionResult);
    	InterfazLARService service = (InterfazLARService) getBean("sisicc.interfazLARService");
    	service.updateLARDocumentosCabeceraComplemento(params);
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
	

	
    

}
