package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGeneracionArchivosProveedoresFTPService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBGeneracionArchivosProveedoresFTPForm;

/**
 * The Class ProcesoCOBGeneracionReportesFFVVFTPAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/02/2015
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoCOBGeneracionArchivosProveedoresFTPAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = -3883550899076707702L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBGeneracionArchivosProveedoresFTPForm form = new ProcesoCOBGeneracionArchivosProveedoresFTPForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entrando setViewAttributes - ProcesoCOBGeneracionArchivosProveedoresFTPAction");
		}
		
		//Recuperando datos de la sesion 
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ProcesoCOBGeneracionArchivosProveedoresFTPForm f = (ProcesoCOBGeneracionArchivosProveedoresFTPForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		ProcesoCOBGeneracionArchivosProveedoresFTPForm f = (ProcesoCOBGeneracionArchivosProveedoresFTPForm) this.formProceso;
		
		params = super.prepareParamsBeforeExecute(params, form);
		
		params.put("codigoPais", f.getCodigoPais());
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		params.put("rutaPath", path);
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entrando executeProcess - ProcesoCOBGeneracionArchivosProveedoresFTPAction");
		}
		
		ProcesoCOBGeneracionArchivosProveedoresFTPService service = (ProcesoCOBGeneracionArchivosProveedoresFTPService) 
																		getBean("spusicc.procesoCOBGeneracionArchivosProveedoresFTPService");
		
		service.executeReportes(params);
		
		return params;
	}
}