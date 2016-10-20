package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.OrdenTransporte;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOOrdenTransporteForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOOrdenTransporteAction extends BaseMantenimientoSTOGestionAction{
	
	private static final long serialVersionUID = -6843674854051412028L;
	
	private List stoClasificacionesOrdenTransporteList;
	private boolean editable;
	private boolean indicadorMensajeEditable;
	private boolean indicadorDireccionEditable;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOOrdenTransporteForm  form = new MantenimientoSTOOrdenTransporteForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {	
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=true;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonModificar=false;	
		this.indicadorMensajeEditable=true;	
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoSTOOrdenTransporteForm f =(MantenimientoSTOOrdenTransporteForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		f.setSalirPantalla(Constants.NO);
		String existeNumeroDocumento = null;
		Map criteria = getCriteria(f);
		criteria.put("codigoDocumento", f.getCodigoDocumento());
		if(StringUtils.equalsIgnoreCase(f.getTipoOrden().substring(0, 2), "BE")){
			existeNumeroDocumento = procesoSTOEjecucionValidacionesService.validateSolicitudPedidoOrdenTransporte(criteria);
		}else{
			existeNumeroDocumento = procesoSTOEjecucionValidacionesService.validateBoletaReciboOrdenTransporte(criteria);
		}
		
		if(StringUtils.isNotEmpty(existeNumeroDocumento)){
			if(StringUtils.equalsIgnoreCase(existeNumeroDocumento, "0")){				
				this.addError("ERROR: ", this.getResourceMessage("mantenimientoSTOOrdenTransporteFormList.notfound"));				
				return false;				
			}else{
				procesoSTOEjecucionValidacionesService.updateOrdenTransporte(criteria);
				f.setSalirPantalla(Constants.SI);				
				return true;
			}
		}else{		
			this.addError("ERROR: ", this.getResourceMessage("mantenimientoSTOOrdenTransporteFormList.notfound"));			
			return false;
		}		
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.indicadorDireccionEditable=false;
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOOrdenTransporteForm f =(MantenimientoSTOOrdenTransporteForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);
		//Map criteriaOperacion = new HashMap();
		Map criteria = new HashMap();
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	
    	GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoTipo",gestion.getDocumento());
		criteria.put("numDocumento",gestion.getNumeroDocumento());
		criteria.put("numLote",gestion.getLote());		
		criteria.put("codigoUsuario",usuario.getLogin());
    	criteria.put("codigoAccion", Constants.STO_PANTALLA_EDITABLE);
    	
		//request.removeAttribute(Constants.STO_GESTION_DATA);
		//session.setAttribute(Constants.STO_GESTION_DATA, gestion);
		
    	if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteria)==null)
    		this.editable=false;  	
    	else
    		this.editable=true;
    					
		List ListaOrdenTransporte=procesoSTOEjecucionValidacionesService.getOrdenTransporte(criteria);		
		OrdenTransporte ordenTransporte = (OrdenTransporte)ListaOrdenTransporte.get(0);
		criteria.put("codigoEstadoEntrega",ordenTransporte.getCodigoEstadoEntrega());
		criteria.put("codigoNovedad",ordenTransporte.getCodigoNovedad());
		criteria.put("codigoRecibiConforme",ordenTransporte.getCodigoRecibiConforme());
		
		List ListaCalificacionesOrdenTransporte = procesoSTOEjecucionValidacionesService.getCalificacionesOrdenTransporte(criteria);
		if(ListaCalificacionesOrdenTransporte.size()==0) this.editable=false;		
		this.stoClasificacionesOrdenTransporteList=ListaCalificacionesOrdenTransporte;		
		setOrdenTransporte(f,ordenTransporte);
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
		this.activarGrabarWindowClose=true;
		
		
	}
	
	/**
	 * Metodo que setea los valores del jsp de orden de transporte	
	 */
	private  void setOrdenTransporte(MantenimientoSTOOrdenTransporteForm f,OrdenTransporte ordenTransporte){
		f.setCodigoPais(ordenTransporte.getCodigoPais());
		
		f.setCodigoCompaniaTransporte(ordenTransporte.getCodigoCompaniaTransporte());
		f.setCodigoCentroAcopio(ordenTransporte.getCodigoCentroAcopio());			
		f.setCalificacion(ordenTransporte.getCodigoCalificacion());
		f.setMensaje(ordenTransporte.getMensaje());
		f.setDireccionCliente(ordenTransporte.getDireccion());
		f.setCodigoCliente(ordenTransporte.getCodigoCliente());
		f.setNombreCliente(ordenTransporte.getNombreCliente());
		f.setCodigoZona(ordenTransporte.getCodigoZona());
		f.setFechaFact(ordenTransporte.getFechaFacturacion());		
		f.setNumeroLote(ordenTransporte.getNumeroLote());
		f.setNumeroDocumento(ordenTransporte.getNumeroSecuencia());
		f.setCodigoDocumento(ordenTransporte.getCodigoDocumento());
		if(StringUtils.isNotBlank(ordenTransporte.getCodigoEstadoEntrega()))
			f.setCodigoEstadoEntrega(ordenTransporte.getCodigoEstadoEntrega());
		else
			f.setCodigoEstadoEntrega("");
		if(StringUtils.isNotBlank(ordenTransporte.getCodigoNovedad()))
			f.setCodigoNovedad(ordenTransporte.getCodigoNovedad());	
		else
			f.setCodigoNovedad("");			
		
		f.setDireccionClienteSearch(ordenTransporte.getDireccion());		
		
		if(StringUtils.isNotBlank(ordenTransporte.getCodigoZonaArribo()))
			f.setCodigoZonaArribo(ordenTransporte.getCodigoZonaArribo());
		else
			f.setCodigoZonaArribo("");	
		
		f.setTelefonoCliente(ordenTransporte.getTelefonoCliente());
		f.setCorreoElectronico(ordenTransporte.getValEmail());
		f.setMotivoRechazo(ordenTransporte.getCodigoNovedad());
		f.setMotivoRechazoDescripcion(ordenTransporte.getValDescricpcion());
		f.setNumeroOt(ordenTransporte.getNumeroDocumento());
		f.setNombreCentroAcopio(ordenTransporte.getNombreCentroAcopio());
		f.setNombreCompanhiaTransporte(ordenTransporte.getNombreCompanhiaTransporte());
		f.setFechaRecibo(ordenTransporte.getFechaFacturacion());
		f.setTipoOrden(ordenTransporte.getTipoOrden());
		f.setDireccionDelCliente(ordenTransporte.getDireccionDelCliente());
		
		f.setIndicadorComprobanteCaja(ordenTransporte.getIndicadorComprobanteCaja());
		f.setIndicadorFueraCaja(ordenTransporte.getIndicadorFueraCaja());
		
	}
	
	/**
	 * @param request
	 * @param f
	 * @return
	 */
	private Map getCriteria(MantenimientoSTOOrdenTransporteForm f) {
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap(); 
		criteria.put("codigoPais",              f.getCodigoPais());
		criteria.put("codigoCompaniaTransporte",f.getCodigoCompaniaTransporte());
		criteria.put("codigoCentroAcopio",      f.getCodigoCentroAcopio());
		criteria.put("calificacion",     		f.getCalificacion());
		criteria.put("mensaje",      			f.getMensaje());
		criteria.put("direccionCliente",        f.getDireccionCliente());		 				
		criteria.put("numeroLote",              f.getNumeroLote());
		criteria.put("numeroDocumento",         f.getNumeroDocumento());		
		criteria.put("codigoEstadoEntrega",     f.getCodigoEstadoEntrega());
		criteria.put("codigoNovedad",           f.getCodigoNovedad());				
		criteria.put("codigoTipodoc",           Constants.STO_TIPO_DOCUMENTO_OT);
		criteria.put("codigoZonaArribo",        f.getCodigoZonaArribo());
		
		//Obtiene el indicador de novedad
		String indicadorNovedad;
		String indicadorGeneraNovedad;
		List l = procesoSTOEjecucionValidacionesService.getNovedadesAccionesOrdenTransporte(criteria);
		try {			
			indicadorNovedad = ((Map)l.get(0)).get("indicadorEnviaNovedad").toString();
		} catch (Exception e) {
			indicadorNovedad = "";
		}		
		try {			
			indicadorGeneraNovedad = ((Map)l.get(0)).get("indicadorGeneraNovedad").toString();
		} catch (Exception e) {
			indicadorGeneraNovedad = "";
		}		
		criteria.put("indicadorNovedad", indicadorNovedad);
		criteria.put("indicadorGeneraNovedad", indicadorGeneraNovedad);
		criteria.put("usuario", usuario.getLogin());
		return criteria;
	}
	
	public void changeCalificacion(ValueChangeEvent val){
		MantenimientoSTOOrdenTransporteForm f =(MantenimientoSTOOrdenTransporteForm)this.formBusqueda;
		this.indicadorDireccionEditable=false;
		this.indicadorMensajeEditable=false;		
		String califica= val.getNewValue().toString();
		
		if (!val.equals(null) && califica.length() > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String msje=ajax.getMensajeCalificacion(f.getCodigoEstadoEntrega(),f.getCodigoNovedad(), califica);
			if(StringUtils.isNotBlank(msje)){
				f.setMensaje(msje);
				this.indicadorMensajeEditable=false;				
			}else{
				f.setMensaje("");
				this.indicadorMensajeEditable=true;
			}
			
			String data=ajax.getIndCambioDireccionCalificacion(f.getCodigoEstadoEntrega(),f.getCodigoNovedad(), califica);
			if(StringUtils.equals(data, "1")){
				f.setDireccionCliente("");
				this.indicadorDireccionEditable=true;
			
				
			}else{
				f.setDireccionCliente(f.getDireccionClienteSearch());
				this.indicadorDireccionEditable=false;
				
			}
			
		}
	}
	
	public String setValidarMantenimiento(){
		MantenimientoSTOOrdenTransporteForm f =(MantenimientoSTOOrdenTransporteForm)this.formBusqueda;
		if(this.indicadorMensajeEditable && StringUtils.isBlank(f.getMensaje()))
			return "Debe ingresar el mensaje";
		if(this.indicadorDireccionEditable && StringUtils.isBlank(f.getDireccionCliente()))
			return "Debe ingresar la dirección del cliente";		
		
		return "";
	}
	

	
	//Salir  a la Pantalla
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	public List getStoClasificacionesOrdenTransporteList() {
		return stoClasificacionesOrdenTransporteList;
	}

	public void setStoClasificacionesOrdenTransporteList(
			List stoClasificacionesOrdenTransporteList) {
		this.stoClasificacionesOrdenTransporteList = stoClasificacionesOrdenTransporteList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isIndicadorMensajeEditable() {
		return indicadorMensajeEditable;
	}

	public void setIndicadorMensajeEditable(boolean indicadorMensajeEditable) {
		this.indicadorMensajeEditable = indicadorMensajeEditable;
	}

	public boolean isIndicadorDireccionEditable() {
		return indicadorDireccionEditable;
	}

	public void setIndicadorDireccionEditable(boolean indicadorDireccionEditable) {
		this.indicadorDireccionEditable = indicadorDireccionEditable;
	}
	
	
	
}
