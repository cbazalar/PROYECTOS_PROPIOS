package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPSolicitudesPolizaForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPSolicitudesPolizaAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaHIPSolicitudesPolizaAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = 4510179486824484173L;
	private static final String ACCION_CARGOS_POLIZA = "CARGOSPOLIZA";
	private List hipSolicitudesPolizaList;
	private List hipHistoricoCargosPolizaList;
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPSolicitudesPolizaForm consultaHIPSolicitudesPolizaForm = new ConsultaHIPSolicitudesPolizaForm();
		return consultaHIPSolicitudesPolizaForm;
	}



	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ConsultaHipSolicitudesPolizaAction - setFindAttributes" );
		}
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String numeroPoliza = externalContext.getRequestParameterMap().get("numeroPoliza");
		String codigoPoliza = externalContext.getRequestParameterMap().get("codigoPoliza");
		String accion = externalContext.getRequestParameterMap().get("accion");
		
		log.debug("numeroPoliza: "+numeroPoliza);
		log.debug("codigoPoliza: "+codigoPoliza);
		log.debug("accion: "+accion);
		
		
		if(accion.equals(ACCION_CARGOS_POLIZA)){
			ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBeanService("scsicc.consultaHIPDatosClienteService");
			ConsultaHIPSolicitudesPolizaForm f = (ConsultaHIPSolicitudesPolizaForm) this.formBusqueda;
			ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
			Map criteria = new HashMap();
			criteria.put("codCliente", dtoDatosCliente.getCodigoCliente());
			criteria.put("nomCompleto", dtoDatosCliente.getNombreCompleto());
			criteria.put("desRegion", dtoDatosCliente.getDescripcionRegion());
			criteria.put("desZona", dtoDatosCliente.getDescripcionZona());
			criteria.put("codTerritorio", dtoDatosCliente.getCodigoTerritorio());
			
			criteria.put("numeroPoliza" , numeroPoliza);
			criteria.put("codigoPoliza" , codigoPoliza);
			
			
			//Seteo la informacion que se trae de la página anterior
			f.setCodConsultora((String)criteria.get("codCliente"));
			f.setNomConsultora((String)criteria.get("nomcompleto"));
			f.setDesRegZonTerri(criteria.get("desRegion") + " / " +  criteria.get("desZona") + " / " + criteria.get("codTerritorio"));
			f.setNumeroPoliza(numeroPoliza);
			
			List historicoCargosList = service.getHistoricoCargosPoliza(criteria);
			return getHipHistoricoCargosPolizaList();
			
		}else
			return getHipSolicitudesPolizaList();
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
			log.debug("ConsultaHipSolicitudesPolizaAction - setViewAtributes" );
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBeanService("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPSolicitudesPolizaForm f = (ConsultaHIPSolicitudesPolizaForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		Map criteria = new HashMap();
		criteria.put("codCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("nomCompleto", dtoDatosCliente.getNombreCompleto());
		criteria.put("desRegion", dtoDatosCliente.getDescripcionRegion());
		criteria.put("desZona", dtoDatosCliente.getDescripcionZona());
		criteria.put("codTerritorio", dtoDatosCliente.getCodigoTerritorio());
		
		
		//Seteo la informacion que se trae de la pgina anterior
		f.setCodConsultora((String)criteria.get("codCliente"));
		f.setNomConsultora((String)criteria.get("nomCompleto"));
		f.setDesRegZonTerri(criteria.get("desRegion") + " / " + criteria.get("desZona") + " / " + criteria.get("codTerritorio"));
		
		//Obtengo las listas a mostrar
		List pedidosConsultora = service.getSolicitudesPoliza(criteria);
		setHipSolicitudesPolizaList(pedidosConsultora);
		
	}
	
	
	//Getters & Setters
	
	public List getHipSolicitudesPolizaList() {
		return hipSolicitudesPolizaList;
	}

	public void setHipSolicitudesPolizaList(List hipSolicitudesPolizaList) {
		this.hipSolicitudesPolizaList = hipSolicitudesPolizaList;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getHipHistoricoCargosPolizaList() {
		return hipHistoricoCargosPolizaList;
	}

	public void setHipHistoricoCargosPolizaList(List hipHistoricoCargosPolizaList) {
		this.hipHistoricoCargosPolizaList = hipHistoricoCargosPolizaList;
	}

}