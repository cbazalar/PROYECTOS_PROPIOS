package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPReclamosForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPReclamosAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 *
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPReclamosAction extends BasePopupAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1620934761891961027L;
	private static final String ACCION_DETALLE_RECLAMOS = "DETALLERECLAMOS";
	
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	@ManagedProperty(value="#{consultaRECBoletaRecojoPopupAction}")
	private ConsultaRECBoletaRecojoPopupAction consultaRECBoletaRecojoPopupAction;
	
	private List hipReclamosCabeceraList= new ArrayList();
	private List hipReclamosDetalleList= new ArrayList();
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPReclamosForm consultaHIPReclamosForm = new ConsultaHIPReclamosForm();		
		return consultaHIPReclamosForm;
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPReclamosAction - setViewAtributes' method");
		}
		
		this.beanRegistroSeleccionado = null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPReclamosAction - setFindAttributes' method");
		}

		ConsultaHIPReclamosForm f = (ConsultaHIPReclamosForm) this.formBusqueda;
				
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		this.hipReclamosDetalleList = new ArrayList();
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
							+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService"); 
		List lista = new ArrayList();		
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		
		log.debug("accion:" + accion);
		
	
		//obtenemos los reclamos de la consultora
		Map criteria = new HashMap();
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		
		List listCabeceraReclamos = service.getCabeceraReclamos(criteria);
		this.setHipReclamosCabeceraList(listCabeceraReclamos);
				
		//recorremos los reclamos pa determinar el estado de cada uno de ellos en base a los campos EnviaReclamado y EnviaAtendido		
		for(int i=0; i<listCabeceraReclamos.size(); i++) {
			Map mapReclamo = (Map)listCabeceraReclamos.get(i);
			String enviaReclamado = reemplazarNulo(mapReclamo.get("enviaReclamado"));
			String enviaAtendido = reemplazarNulo(mapReclamo.get("enviaAtendido"));
			
			if(enviaReclamado.equals("") || enviaReclamado.equals("0")) {
				//Queda como esta el campo [estado Reclamo]
			} else {
				if(!enviaAtendido.equals(""))
					mapReclamo.put("estadoReclamo", Constants.HIP_RECLAMOS_ESTADO_FACTURADO);
				else
					mapReclamo.put("estadoReclamo", Constants.HIP_RECLAMOS_ESTADO_PENDIENTE);
			}
		}
		
		f.setSelectedItem("");
		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoOpcion", Constants.HIP_OPCION_BOLETA_RECOJO);
		criteria.put("codigoUsuario", usuario.getLogin());
		
		f.setMostrarBotonBoletaRecojo(service.validarOpcionSecundaria(criteria));
		
		lista =  listCabeceraReclamos;
		
			
		return lista;
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void search(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
				
		this.seleccionoRegistro= true;
		try {
			if (this.beanRegistroSeleccionado == null)
				seleccionoRegistro = false;
		}	
		catch (Exception e) {		
			seleccionoRegistro = false;
		}
		if (!seleccionoRegistro) 
			this.addWarn("Warning: ", this.getResourceMessage("consultaHIPReclamosForm.errors.valorZero"));	
		else {
			this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);
		}
		
		if (this.getBeanRegistroSeleccionado() != null) {
			
			String id = MapUtils.getString((Map)this.getBeanRegistroSeleccionado(), "numeroBr");
			
			if(StringUtils.isNotBlank(id) && !StringUtils.equals(id, Constants.NUMERO_CERO))
			{
				this.getConsultaRECBoletaRecojoPopupAction().setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
				this.getConsultaRECBoletaRecojoPopupAction().search(actionEvent);
			}
			else
			{
				this.seleccionoRegistro = false;
				this.addWarn("Warning: ", this.getResourceMessage("consultaHIPReclamosForm.errors.valorZero"));
			}			
		}		
	}
	
	/**
	 * @param actionEvent
	 */
	public void verDetalle(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		this.hipReclamosDetalleList = new ArrayList();
		log.debug("accion:" + accion);
		
		if(StringUtils.isNotBlank(accion)){
			
			if(accion.equals(this.ACCION_DETALLE_RECLAMOS)){
				ConsultaHIPReclamosForm f = (ConsultaHIPReclamosForm) this.formBusqueda;
				ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
				ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService"); 
				
				String oidCabecera = externalContext.getRequestParameterMap().get("parametro");
				String numeroReclamo = externalContext.getRequestParameterMap().get("parametro2");
				String estadoReclamo = externalContext.getRequestParameterMap().get("parametro3");
				
				f.setNumeroReclamo(numeroReclamo);
				f.setEstadoReclamo(estadoReclamo);
				
				Map criteriaCabRec = new HashMap();
				criteriaCabRec.put("oidCabecera", oidCabecera);
				criteriaCabRec.put("codConsultora", f.getCodConsultora());
				criteriaCabRec.put("numeroReclamo", f.getNumeroReclamo());
				
				//Obtenemos los datos adicionales a mostrar en la cabecera del reclamo
				List adicionales = service.getAdicionalesCabeceraReclamo(criteriaCabRec);
				
				if(adicionales != null && adicionales.size() > 0)
				{
					Map data = (Map)adicionales.get(0);
					f.setUsuarioRegistro(MapUtils.getString(data, "usuarioRegistro", ""));
					f.setFechaRegistro(MapUtils.getString(data, "fechaRegistro", ""));
					f.setUsuarioDigitacion(MapUtils.getString(data, "usuarioDigitacion", ""));
					f.setFechaDigitacion(MapUtils.getString(data, "fechaDigitacion", ""));
					f.setUsuarioAprobacion(MapUtils.getString(data, "usuarioAprobacion", ""));
					f.setFechaAprobacion(MapUtils.getString(data, "fechaAprobacion", ""));
					f.setOrigenCDR(MapUtils.getString(data, "origenCDR", ""));
				}
				//
				
				Map criteria = new HashMap();
				criteria.put("oidCabecera" , oidCabecera);
				criteria.put("oidIdioma" , dtoDatosCliente.getOidIdioma());
					
				List listDetalleReclamo = service.getDetalleReclamo(criteria);
				
				//pintamos informacion del reclamo
				List listReclamos = this.hipReclamosCabeceraList; 
										
				for(int i=0; i<listReclamos.size(); i++) {
					Map mapReclamo = (Map)listReclamos.get(i);
					String oidCabeceraAux = (String)mapReclamo.get("oidCabecera");
					
					if (oidCabeceraAux.equals(oidCabecera)) {
						f.setNumeroReclamo(reemplazarNulo(mapReclamo.get("nroReclamo")));
						
						/* INI SA PER-SiCC-2012-0788 */
						f.setEstadoReclamo(reemplazarNulo(mapReclamo.get("estadoReclamo")));
						/* FIN SA PER-SiCC-2012-0788 */
						
						break;
					}	
				}	
				
				//recorremos el detalle del reclamo y en caso de que no sea un movimiento Envia, se limpia los campos:
				//PrecioFactura, PrecioContable, Total Cargo Reclamado, Total Cargo Realizado
				for(int i=0; i<listDetalleReclamo.size(); i++) {
					Map mapDetalle = (Map)listDetalleReclamo.get(i);
					String codigoMovimiento = (String)mapDetalle.get("codigoMovimiento");
					
					if (!codigoMovimiento.equals(Constants.HIP_RECLAMOS_MOVIMIENTO_ENVIA)) {
						mapDetalle.put("precioContable", "");
						mapDetalle.put("precioFactura", "");
						mapDetalle.put("totalCargoReclamado", "");
						mapDetalle.put("totalCargoRealizado", "");
					}
				}
				
				this.hipReclamosDetalleList = listDetalleReclamo;
			}
		}
	}
	

	/**
	 * @param actionEvent
	 */
	public void verCabeceraDetalle(ActionEvent actionEvent) {
		this.viewAjax(actionEvent);
		this.verDetalle(actionEvent);
	}

	/**
	 * metodo auxiliar que me permite recuperar en cadena el valor de un objeto
	 * 
	 * @param obj
	 * @return
	 */	
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return (String)obj;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	/**
	 * @return the hipReclamosCabeceraList
	 */
	public List getHipReclamosCabeceraList() {
		return hipReclamosCabeceraList;
	}

	/**
	 * @param hipReclamosCabeceraList the hipReclamosCabeceraList to set
	 */
	public void setHipReclamosCabeceraList(List hipReclamosCabeceraList) {
		this.hipReclamosCabeceraList = hipReclamosCabeceraList;
	}

	/**
	 * @return the consultaRECBoletaRecojoPopupAction
	 */
	public ConsultaRECBoletaRecojoPopupAction getConsultaRECBoletaRecojoPopupAction() {
		return consultaRECBoletaRecojoPopupAction;
	}

	/**
	 * @param consultaRECBoletaRecojoPopupAction the consultaRECBoletaRecojoPopupAction to set
	 */
	public void setConsultaRECBoletaRecojoPopupAction(
			ConsultaRECBoletaRecojoPopupAction consultaRECBoletaRecojoPopupAction) {
		this.consultaRECBoletaRecojoPopupAction = consultaRECBoletaRecojoPopupAction;
	}

	/**
	 * @return the hipReclamosDetalleList
	 */
	public List getHipReclamosDetalleList() {
		return hipReclamosDetalleList;
	}

	/**
	 * @param hipReclamosDetalleList the hipReclamosDetalleList to set
	 */
	public void setHipReclamosDetalleList(List hipReclamosDetalleList) {
		this.hipReclamosDetalleList = hipReclamosDetalleList;
	}	
	
	
	
}
