package biz.belcorp.ssicc.web.scdf.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scdf.ControlProcesoService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scdf.form.CierreProcesosDiariosForm;

@ManagedBean
@SessionScoped
public class CierreProcesosDiariosAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809019090425827519L;

	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		CierreProcesosDiariosForm formProceso = new CierreProcesosDiariosForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
        // Creamos las instancias de los objetos a usar
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

        // Obtenemos la instancia del servicio
        ControlProcesoService service = (ControlProcesoService) getBean("scdf.controlProcesoService");

        // Ejecutamos el proceso de actualizacion del numero de boleta
        service.executeCierreProcesosDiarios(pais.getCodigo());
        
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
