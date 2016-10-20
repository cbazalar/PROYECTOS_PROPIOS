package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYERecepcionarActualizacionDatosConsultoraForm;

/**
 * Action del Recepcion de Actualizacion de Datos de Consultora
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma</a>
 * 
 */
@ManagedBean
@SessionScoped
public class InterfazMYERecepcionarActualizacionDatosConsultoraAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4428466055879041139L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazMYERecepcionarActualizacionDatosConsultoraForm f = new InterfazMYERecepcionarActualizacionDatosConsultoraForm();
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazMYERecepcionarActualizacionDatosConsultoraForm f =(InterfazMYERecepcionarActualizacionDatosConsultoraForm)this.formInterfaz;
	}
	

	
	
}