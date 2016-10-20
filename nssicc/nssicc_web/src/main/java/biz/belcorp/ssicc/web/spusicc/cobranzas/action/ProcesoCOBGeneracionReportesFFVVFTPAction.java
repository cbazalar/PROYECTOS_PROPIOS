package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGeneracionReportesFFVVFTPService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBGeneracionReportesFFVVFTPForm;

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
public class ProcesoCOBGeneracionReportesFFVVFTPAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 6307458875627916795L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBGeneracionReportesFFVVFTPForm form = new ProcesoCOBGeneracionReportesFFVVFTPForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entrando setViewAttributes - ProcesoCOBGeneracionReportesFFVVFTPAction");
		}
		
		//Recuperando datos de la sesion 
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ProcesoCOBGeneracionReportesFFVVFTPForm f = (ProcesoCOBGeneracionReportesFFVVFTPForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());
				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {	
		ProcesoCOBGeneracionReportesFFVVFTPForm f = (ProcesoCOBGeneracionReportesFFVVFTPForm) this.formProceso;
		
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
			log.debug("Entrando executeProcess - ProcesoCOBGeneracionReportesFFVVFTPAction");
		}
		
		ProcesoCOBGeneracionReportesFFVVFTPService service = (ProcesoCOBGeneracionReportesFFVVFTPService) 
																getBean("spusicc.procesoCOBGeneracionReportesFFVVFTPService");
		
		service.executeGenerarDataFFVVFTP(params);
		service.executeReportes(params);
		
		return params;
	}
}