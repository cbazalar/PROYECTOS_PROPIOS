/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPFacturacionAdicionalForm;

/**
 * The Class ConsultaHIPFacturacionAdicionalAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/01/2014
 */
@ManagedBean
@SessionScoped
public class ConsultaHIPFacturacionAdicionalAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List hipFacturacionAdicionalList = new ArrayList();
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPFacturacionAdicionalForm f = new ConsultaHIPFacturacionAdicionalForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		return null;
	}
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - view' method");
        }
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.putAll(parametros);
		try {
			this.formBusqueda = this.devuelveFormBusqueda();
		}
		catch (Exception e) {
			
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPFacturacionAdicionalForm f = (ConsultaHIPFacturacionAdicionalForm)this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion()+ "/" + dtoDatosCliente.getDescripcionZona() + "/" + dtoDatosCliente.getCodigoTerritorio());

		List lista = consultaHIPDatosClienteService.getFacturacionAdicionalList(dtoDatosCliente.getCodigoCliente());
		if(!lista.isEmpty()){
			if(lista.size()>0){
				this.setHipFacturacionAdicionalList(lista);
			}
		}
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getHipFacturacionAdicionalList() {
		return hipFacturacionAdicionalList;
	}

	public void setHipFacturacionAdicionalList(List hipFacturacionAdicionalList) {
		this.hipFacturacionAdicionalList = hipFacturacionAdicionalList;
	}
}