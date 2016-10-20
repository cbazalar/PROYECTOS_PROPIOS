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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLAREnviarSecuenciaPedidosForm;

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
public class InterfazLAREnviarSecuenciaPedidosAction extends BaseInterfazAbstractAction {

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
		InterfazLAREnviarSecuenciaPedidosForm interfazLAREnviarSecuenciaPedidosForm = new InterfazLAREnviarSecuenciaPedidosForm();
		return interfazLAREnviarSecuenciaPedidosForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }

        InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
        Pais pais =mPantallaPrincipalBean.getCurrentCountry();

        // Carga de los combos Marca y Canal
        siccMarcaList=interfazSiCCService.getMarcas();
        siccCanalList=interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma()
                .getCodigoISO());


        // Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        List periodos = interfazSiCCService.getPeriodosDefaultByPMC(criteria);
        
        if(periodos != null && periodos.size() > 0) {
            InterfazLAREnviarSecuenciaPedidosForm actionForm = (InterfazLAREnviarSecuenciaPedidosForm)formInterfaz;
            Base base = (Base)periodos.get(0);
            actionForm.setCodigoPeriodo(base.getCodigo());
        }
        formInterfaz.setCodigoPais(pais.getCodigo());
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		InterfazLAREnviarSecuenciaPedidosForm actionForm = (InterfazLAREnviarSecuenciaPedidosForm) this.formInterfaz;
		actionForm.setFechaFacturacion(DateUtil.getDate(actionForm.getFechaFacturacionDate()));
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


	
    

}
