package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoReferencia;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.SolicitudPostVentaCabecera;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSolicitudPostVentaCabeceraForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOSolicitudPostVentaCabeceraAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = -8231908951630461964L;
	
	private List stoClasificacionConsultoraList;
	private List stoEstadisticaUltimasCampanasList;
	private List stoBLoqueosConsultoraList;
	private List stoOperacionCodigoVentaList;
	private boolean editable;
	
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaSTODocumentoReferenciaSearchAction}")	
	private BusquedaSTODocumentoReferenciaSearchAction busquedaSTODocumentoReferencia;
	
	//Popup Cliente
	@ManagedProperty(value="#{consultaSTOPostVentaCabeceraAction}")	
	private ConsultaSTOPostVentaCabeceraAction consultaSTOPostVentaCabecera;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOSolicitudPostVentaCabeceraForm form = new MantenimientoSTOSolicitudPostVentaCabeceraForm();
		return form;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		 MantenimientoSTOSolicitudPostVentaCabeceraForm f =(MantenimientoSTOSolicitudPostVentaCabeceraForm)this.formBusqueda;
		 Map criteria = new HashMap(); 
		 criteria.put("codigoPais",f.getCodigoPais());
		 criteria.put("numDocumento",f.getNumSecuencia());
		 criteria.put("numLote",f.getNumLote());
		 criteria.put("codPeriodo",f.getCodPeriodo());
		 criteria.put("codCliente",f.getCodCliente());
		 criteria.put("tipoSolicitud",f.getTipoSolicitud());
		 criteria.put("codSubAcc",f.getCodSubAcceso());
		 criteria.put("estadoProceso",f.getEstadoProceso());
		 criteria.put("motivoRechazo",f.getCodMotivoRechazo());
		 criteria.put("codRegion",f.getCodRegion());
		 criteria.put("codZona",f.getCodZonaArribo());
		 criteria.put("accesoFisi",f.getAccFisi());
		 criteria.put("codCia",f.getCodCia());
		 criteria.put("numRuc",f.getNumDocuRUC());
		 criteria.put("observaciones",f.getObservaciones());
		 criteria.put("numeroDocumento", f.getNumDocumento());
			 
		 ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		 procesoSTOEjecucionValidacionesService.updateSolicitudPostVentaCabecera(criteria);
			
		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();	
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
	    ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");	
	    MantenimientoSTOSolicitudPostVentaCabeceraForm f =(MantenimientoSTOSolicitudPostVentaCabeceraForm)this.formBusqueda;
	    
		f.setCodigoPais(pais.getCodigo());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		
		Map criteria = new HashMap();		
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoTipo",gestion.getDocumento());
		criteria.put("numDocumento",gestion.getNumeroDocumento());
		criteria.put("numLote",gestion.getLote());
		
		criteriaOperacion.put("codigoUsuario", usuario.getLogin());
    	criteriaOperacion.put("codigoAccion", Constants.STO_PANTALLA_EDITABLE);
    	criteriaOperacion.put("codigoTipo", gestion.getDocumento());
    	
    	this.editable=false;
    	if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null)
    		this.editable=false;    	
    	else
    		this.editable=true;

		
		List ListaPostVentaCabecera=procesoSTOEjecucionValidacionesService.getSolicitudPostVentaCabecera(criteria);		
		SolicitudPostVentaCabecera solicitudPostVentaCabecera = (SolicitudPostVentaCabecera)ListaPostVentaCabecera.get(0);			
		setSolicitudPostVentaCabecera(f,solicitudPostVentaCabecera);
		
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		
		
		f.setOidPais(String.valueOf(oidPais));
		
		Map parametro = new HashMap();
		parametro.put("codCliente",f.getCodCliente());
		parametro.put("codigoPais",pais.getCodigo());
		parametro.put("numeroSecuencia",f.getNumSecuencia());

		List ListaClasificacionConsultora=procesoSTOEjecucionValidacionesService.executeClasificacionConsultora(parametro);
		this.stoClasificacionConsultoraList=ListaClasificacionConsultora;		
		List ListaEstadisticaUltimasCampanas=procesoSTOEjecucionValidacionesService.getEstadisticaUltimasCampanas(parametro);
		this.stoEstadisticaUltimasCampanasList=ListaEstadisticaUltimasCampanas;			
		List ListaBloqueosConsultora=procesoSTOEjecucionValidacionesService.getBloqueosConsultora(parametro);		
		this.stoBLoqueosConsultoraList=ListaBloqueosConsultora;
		List listaOperacionCodigoVenta=procesoSTOEjecucionValidacionesService.getOpeacionCodigoVenta(parametro);		
		this.stoOperacionCodigoVentaList=listaOperacionCodigoVenta;		
	}
	
	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=true;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonModificar=false;			
	} 
	
	//salir de la pantalla	
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo para setear los valores de la Solicitud Post Venta Cabecera	
	 */
	private  void setSolicitudPostVentaCabecera(MantenimientoSTOSolicitudPostVentaCabeceraForm f,SolicitudPostVentaCabecera solicitudPostVentaCabecera  ) {
	
		f.setCodigoPais(solicitudPostVentaCabecera.getCodigoPais());
		f.setAccFisi(solicitudPostVentaCabecera.getAccFisi());
		f.setCodCia(solicitudPostVentaCabecera.getCodCia());
		f.setCodCliente(solicitudPostVentaCabecera.getCodCliente());
		f.setCodMotivoRechazo(solicitudPostVentaCabecera.getCodMotivoRechazo());
		f.setCodPeriodo(solicitudPostVentaCabecera.getCodPeriodo());
		f.setCodRegion(solicitudPostVentaCabecera.getCodRegion());
		f.setCodSubAcceso(solicitudPostVentaCabecera.getCodSubAcceso());
		f.setCodTipoDocumento(solicitudPostVentaCabecera.getCodTipoDocumento());
		f.setCodZonaArribo(solicitudPostVentaCabecera.getCodZona());
		f.setEstadoProceso(solicitudPostVentaCabecera.getEstadoProceso());
		f.setFechaProcesoDoc(solicitudPostVentaCabecera.getFechaProcesoDoc());
		f.setNumDocumento(solicitudPostVentaCabecera.getNumDocumento());
		f.setNumDocuRUC(solicitudPostVentaCabecera.getNumDocuRUC());
		f.setNumLote(solicitudPostVentaCabecera.getNumLote());
		f.setNumSecuencia(solicitudPostVentaCabecera.getNumSecuencia());
		f.setTipoSolicitud(solicitudPostVentaCabecera.getTipoSolicitud());
		f.setObservaciones(solicitudPostVentaCabecera.getObservaciones());
		f.setNombre(solicitudPostVentaCabecera.getNombre());
	    f.setPeriodoReferencia(solicitudPostVentaCabecera.getPeriodoReferencia());
		
	}	
	
	//POPUP --busqueda Nro Solicitud
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			MantenimientoSTOSolicitudPostVentaCabeceraForm f =(MantenimientoSTOSolicitudPostVentaCabeceraForm)this.formBusqueda;
			String codigo=f.getCodCliente();			
			this.busquedaSTODocumentoReferencia.setCodigoCliente(codigo);	
			try {				
				this.busquedaSTODocumentoReferencia.iniciaValores();
				this.mostrarPopupCliente = true;
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}	
		
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {			
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaSTODocumentoReferencia.verificarRegistro(event);			
			if (this.busquedaSTODocumentoReferencia.isSeleccionoRegistro()) {					
					DocumentoReferencia docReferencia=(DocumentoReferencia)this.busquedaSTODocumentoReferencia.getBeanRegistroSeleccionado();
					MantenimientoSTOSolicitudPostVentaCabeceraForm f =(MantenimientoSTOSolicitudPostVentaCabeceraForm)this.formBusqueda;
					String numeroSolicitud=docReferencia.getNumeroSolicitud();
					f.setNumDocuRUC(numeroSolicitud);					
					this.busquedaSTODocumentoReferencia.setBeanRegistroSeleccionado(null);					
					this.formBusqueda=f;
			}
		}	
	}
		
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;			
		this.busquedaSTODocumentoReferencia.setBeanRegistroSeleccionado(null);
	}
	
	//Pop Codigo Cliente
	public void abrirPopupInfoCLiente(ActionEvent event){
		MantenimientoSTOSolicitudPostVentaCabeceraForm f =(MantenimientoSTOSolicitudPostVentaCabeceraForm)this.formBusqueda;
		try {
			this.consultaSTOPostVentaCabecera.setNroDocumentoRuc(f.getNumDocuRUC());
			this.consultaSTOPostVentaCabecera.setConsultarAttributes();			
			String ventana = "PF('dialogCliente').show()";
			this.getRequestContext().execute(ventana);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarMantenimiento(){
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();		
		MantenimientoSTOSolicitudPostVentaCabeceraForm f =(MantenimientoSTOSolicitudPostVentaCabeceraForm)this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");		
		if(StringUtils.isNotBlank(f.getCodCliente())){			
			String valor=ajax.validarCodigoCliente(f.getOidPais(), f.getCodCliente());
			if(StringUtils.equals(valor, "N"))
				return "El codigo de cliente no existe";
			else
				return "";			
			
		}else
			return "Debe de ingresar el codigo de cliente";
		
	}

	public List getStoClasificacionConsultoraList() {
		return stoClasificacionConsultoraList;
	}

	public void setStoClasificacionConsultoraList(
			List stoClasificacionConsultoraList) {
		this.stoClasificacionConsultoraList = stoClasificacionConsultoraList;
	}

	public List getStoEstadisticaUltimasCampanasList() {
		return stoEstadisticaUltimasCampanasList;
	}

	public void setStoEstadisticaUltimasCampanasList(
			List stoEstadisticaUltimasCampanasList) {
		this.stoEstadisticaUltimasCampanasList = stoEstadisticaUltimasCampanasList;
	}

	public List getStoBLoqueosConsultoraList() {
		return stoBLoqueosConsultoraList;
	}

	public void setStoBLoqueosConsultoraList(List stoBLoqueosConsultoraList) {
		this.stoBLoqueosConsultoraList = stoBLoqueosConsultoraList;
	}

	public List getStoOperacionCodigoVentaList() {
		return stoOperacionCodigoVentaList;
	}

	public void setStoOperacionCodigoVentaList(List stoOperacionCodigoVentaList) {
		this.stoOperacionCodigoVentaList = stoOperacionCodigoVentaList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaSTODocumentoReferenciaSearchAction getBusquedaSTODocumentoReferencia() {
		return busquedaSTODocumentoReferencia;
	}

	public void setBusquedaSTODocumentoReferencia(
			BusquedaSTODocumentoReferenciaSearchAction busquedaSTODocumentoReferencia) {
		this.busquedaSTODocumentoReferencia = busquedaSTODocumentoReferencia;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	public ConsultaSTOPostVentaCabeceraAction getConsultaSTOPostVentaCabecera() {
		return consultaSTOPostVentaCabecera;
	}

	public void setConsultaSTOPostVentaCabecera(
			ConsultaSTOPostVentaCabeceraAction consultaSTOPostVentaCabecera) {
		this.consultaSTOPostVentaCabecera = consultaSTOPostVentaCabecera;
	}
	
	
	

}
