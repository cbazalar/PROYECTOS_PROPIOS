package biz.belcorp.ssicc.web.framework.base.form;

/**
 * Form base para las interfaces de paquete, extiende BaseInterfazForm. Agrega
 * los codigos de las interfaces que pertenecen al paquete. Los forms de las
 * interfaces de paquete deben heredar de este Form.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 * @struts.form name = "baseInterfazPaqueteForm" extends = "baseInterfazForm"
 */
public class BaseInterfazPaqueteForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	
	private String[] codigoInterfacesEmpaquetadas;

	public String[] getCodigoInterfacesEmpaquetadas() {
		return codigoInterfacesEmpaquetadas;
	}

	public void setCodigoInterfacesEmpaquetadas(
			String[] codigoInterfacesEmpaquetadas) {
		this.codigoInterfacesEmpaquetadas = codigoInterfacesEmpaquetadas;
	}
}