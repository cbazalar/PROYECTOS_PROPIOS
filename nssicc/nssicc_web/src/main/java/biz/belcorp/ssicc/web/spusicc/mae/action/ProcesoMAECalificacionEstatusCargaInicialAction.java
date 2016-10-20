package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECalificacionEstatusService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECalificacionEstatusCargaInicialForm;

/**
 * @author jpulido
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProcesoMAECalificacionEstatusCargaInicialAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1494141550408572632L;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAECalificacionEstatusCargaInicialForm J = new ProcesoMAECalificacionEstatusCargaInicialForm();
		return J;
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		log.debug("Los parametros del Proceso en el executeProcess son: "
				+ params.toString());
		
		ProcesoMAECalificacionEstatusService service = (ProcesoMAECalificacionEstatusService) 
											getBean("spusicc.procesoMAECalificacionEstatusService");

		service.executeCalificacionEstatusCargaInicial(params);
		return params;

	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ProcesoMAECalificacionEstatusCargaInicialForm f = (ProcesoMAECalificacionEstatusCargaInicialForm) this.formProceso;
		ProcesoMAECalificacionEstatusService service = (ProcesoMAECalificacionEstatusService) 
															getBean("spusicc.procesoMAECalificacionEstatusService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
		
		f.setCargaRealizada(service.verificarCargaInicialEstatus(pais.getCodigo()));

	}




	




}