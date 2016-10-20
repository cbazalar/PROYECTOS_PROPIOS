package biz.belcorp.ssicc.web.scsicc.hip.action;

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
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPProgramasNuevasForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPCronogramaActividadesAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPProgramasNuevasAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = 168448331438766923L;
	
	private List hipProduDespaAutoList;
	private List hipProduSolicList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPProgramasNuevasForm consultaHIPProgramasNuevasForm = new ConsultaHIPProgramasNuevasForm();
		return consultaHIPProgramasNuevasForm;
	}
	
	

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPProgramasNuevasAction - setFindAttributes' method");
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
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPProgramasNuevasAction - setViewAtributes' method");
        }
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBeanService("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPProgramasNuevasForm f = (ConsultaHIPProgramasNuevasForm) this.formBusqueda;
		
		Map criteria = new HashMap();
		
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		//Seteo la informacion que se trae de la página anterior
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion().concat(" / ").concat(dtoDatosCliente.getDescripcionZona()).concat(" / ").concat(dtoDatosCliente.getCodigoTerritorio()));
		f.setEstatus(dtoDatosCliente.getStatus());
		f.setCampanaIngreso(dtoDatosCliente.getCampanaIngreso());
		
		//Seteo los parametros de la consulta
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("codigoConsultora", dtoDatosCliente.getCodigoCliente());
		
		//Obtengo las listas a mostrar
		List productosDespachadosAuto = service.getProductosDespachadosAutomaticamente(criteria);
		List productosSolicitadosConsultora = service.getProductosSolicitadosConsultoras(criteria);
		
		setHipProduDespaAutoList(productosDespachadosAuto);
		setHipProduSolicList(productosSolicitadosConsultora);
		
	}
	
	//GETTERS && SETTERS

	public List getHipProduDespaAutoList() {
		return hipProduDespaAutoList;
	}

	public void setHipProduDespaAutoList(List hipProduDespaAutoList) {
		this.hipProduDespaAutoList = hipProduDespaAutoList;
	}

	public List getHipProduSolicList() {
		return hipProduSolicList;
	}

	public void setHipProduSolicList(List hipProduSolicList) {
		this.hipProduSolicList = hipProduSolicList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}
	
	
	

}
