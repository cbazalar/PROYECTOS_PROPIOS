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
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazDANEnviarAdministracionFlujosForm;


@ManagedBean
@SessionScoped
public class InterfazDANEnviarAdministracionFlujosAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8023948104174337618L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoListTodos;
	private List siccSubAccesoList;
		

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazDANEnviarAdministracionFlujosForm form=new InterfazDANEnviarAdministracionFlujosForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		 if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	        }

		 Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		 Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		 
		 // Carga de los combos de Marca, Canal, Accesos y Subaccesos
	     InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList=svc.getMarcas();
		this.siccCanalList= svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList=svc.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoListTodos= svc.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccSubAccesoList= svc.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());

		 InterfazDANEnviarAdministracionFlujosForm interfazDATForm = (InterfazDANEnviarAdministracionFlujosForm) this.formInterfaz;
	     InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	     
	     interfazDATForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(usuario.getPais().getCodigo(),Constants.CODIGO_CANAL_DEFAULT)); 
	     interfazDATForm.setCodigoPais(pais.getCodigo());
	     MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService"); 
	     PedidoControlFacturacion controlFacturacion = null; 
	        
	     Map criteriaPeriodo = new HashMap();
	     criteriaPeriodo.put("codigoPais", usuario.getPais().getCodigo());
	     criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
	     criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);
	     
	     controlFacturacion=serviceCF.getControlFacturacionById(criteriaPeriodo);
        interfazDATForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        
        
        interfazDATForm.setCodigoMarca( Constants.CODIGO_MARCA_DEFAULT);
        interfazDATForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        interfazDATForm.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);

	
   
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{
		
		params = super.prepareParamsBeforeExecute(params, form);
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();    	
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());

		return params;
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	log.debug("Entering 'beforeExecuteInterfaz' method");
    	    	
    }

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	public List getSiccAccesoListTodos() {
		return siccAccesoListTodos;
	}

	public void setSiccAccesoListTodos(List siccAccesoListTodos) {
		this.siccAccesoListTodos = siccAccesoListTodos;
	}

	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}
	
	
	

}
