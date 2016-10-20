package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPRecuperacionAnulacionForm;

/**
 * The Class ConsultaHIPMetasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/02/2014
 */
@ManagedBean  
@SessionScoped
public class ConsultaHIPRecuperacionAnulacionAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;

	private List hipRecuperacionAnulacionList;
	private Object[] seleccionados = {};

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPRecuperacionAnulacionForm form = new ConsultaHIPRecuperacionAnulacionForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a setFindAttributes");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPRecuperacionAnulacionForm f = (ConsultaHIPRecuperacionAnulacionForm) this.formBusqueda;
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
					
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());

		Map mapDatosEjecutiva = service.getDatosEjecutiva(criteria);

		List listRecuperacionAnulacion = service.getDetalleRecuperacionAnulacion(criteria);
		this.setHipRecuperacionAnulacionList(listRecuperacionAnulacion);
		
		this.beanRegistroSeleccionado = null;
		
		return listRecuperacionAnulacion;
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
			log.debug("Entro a setViewAttributes");
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		this.seleccionoRegistro= true;
		try {
			if (this.beanRegistroSeleccionado == null)
				seleccionoRegistro = false;
		}	
		catch (Exception e) {		
			seleccionoRegistro = false;
		}
		
		if (!seleccionoRegistro) 
			return this.getResourceMessage("errors.select.item");	
		else 
			this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);
		
		return null;
	}
	
	/**
	 * Eliminar premios.
	 *
	 * @throws Exception the exception
	 */
	public void eliminarPremios(ActionEvent e) throws Exception{
		if (log.isDebugEnabled()){
			log.debug("Entering 'eliminarPremios' ");
		}
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		ConsultaHIPRecuperacionAnulacionForm f = (ConsultaHIPRecuperacionAnulacionForm) this.formBusqueda;
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		

		Map recuperacionesMap = (Map)this.beanRegistroSeleccionado;
		log.debug("recuperacionesMap :"+ recuperacionesMap);
		
		try
		{
			if(!StringUtils.equals(MapUtils.getString(recuperacionesMap, "gp"), Constants.NUMERO_CINCO)){
				//SE PUEDEN ELIMINAR
				Map criteria = new HashMap();
				criteria.put("usuario", usuario.getLogin());
				criteria.put("oidSoliPosi", MapUtils.getString(recuperacionesMap, "oidSoliPosi"));
				
				consultaHIPDatosClienteService.insertRecuperacionAnulacionAudit(criteria);
				
				String oidSoliPosi = MapUtils.getString(recuperacionesMap, "oidSoliPosi");
				consultaHIPDatosClienteService.deleteRecuperacionAnulacion(oidSoliPosi);
				
				String oidSoliCabe = MapUtils.getString(recuperacionesMap, "oidSoliCabe");
				int existeIntegridad = Integer.parseInt(consultaHIPDatosClienteService.getExisteIntegridadCabecera(oidSoliCabe));
				
				if(existeIntegridad == 0){
					consultaHIPDatosClienteService.deleteRecuperacionAnulacionCabecera(oidSoliCabe);
				}
				
				this.addInfo("Info: ", this.getResourceMessage("consultaHIPRecuperacionAnulacionForm.eliminado"));
				
			}else{
				//NO SE PUEDEN ELIMINAR, MOSTRAR ERROR
				this.addError("", this.getResourceMessage("consultaHIPRecuperacionAnulacionForm.error.gpIgual5"));
			}
		}
		catch(Exception ex)
		{
			this.addError("Error: ", ex.getMessage());
			
			log.error(ex.getMessage(), ex);
		}
			
	}
	
	public void ejecutarReporte(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("ejecutarReporte");
		}
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getHipRecuperacionAnulacionList() {
		return hipRecuperacionAnulacionList;
	}

	public void setHipRecuperacionAnulacionList(List hipRecuperacionAnulacionList) {
		this.hipRecuperacionAnulacionList = hipRecuperacionAnulacionList;
	}

	public Object[] getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(Object[] seleccionados) {
		this.seleccionados = seleccionados;
	}
}