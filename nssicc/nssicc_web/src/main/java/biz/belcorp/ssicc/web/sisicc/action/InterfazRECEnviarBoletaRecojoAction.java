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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRECEnviarBoletaRecojoForm;

/**
 * 
 * @author <a href="mailto:llizana@belcorp.biz">Leonardo Lizana</a>
 */
@ManagedBean
@SessionScoped
public class InterfazRECEnviarBoletaRecojoAction extends BaseInterfazAbstractAction {


	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazRECEnviarBoletaRecojoForm();
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
		
        // Carga de los combos de Marca, Canal, Accesos y Subaccesos
	    InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	    siccMarcaList = svc.getMarcas();
	    siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		      
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
		InterfazRECEnviarBoletaRecojoForm f = (InterfazRECEnviarBoletaRecojoForm) this.formInterfaz;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
			
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}
	
   /* (non-Javadoc)
    * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
   */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	log.debug("Entering 'reload' method");
    	InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
    	
    	svc.executeGenerarBoletasRecojo(params);
    	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult)
     */
    protected void afterExecuteInterfaz(Map params,	InterfazExecutionResult interfazExecutionResult) throws Exception{
    	super.afterExecuteInterfaz(params, interfazExecutionResult);
    	log.debug("Entering 'reload' method");
    	InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
    	svc.executeGenerarXMLBoletasRecojo(params);
    	
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
