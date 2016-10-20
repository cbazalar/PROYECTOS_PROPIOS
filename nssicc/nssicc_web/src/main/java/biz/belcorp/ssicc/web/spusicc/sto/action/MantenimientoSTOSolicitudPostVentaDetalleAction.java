package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CodigoVentaMatriz;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CodigoVentaPedido;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.SolicitudPostVentaDetalle;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.action.ConsultaPREMatrizFacturacionAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSolicitudPostVentaDetalleForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOSolicitudPostVentaDetalleAction extends BaseMantenimientoSTOGestionAction{
	
	private static final long serialVersionUID = 2305312932696586546L;
	
	private List stoClasificacionConsultoraList;
	private List stoEstadisticaUltimasCampanasList;
	private List stoBLoqueosConsultoraList;
	private List stoOperacionCodigoVentaList;
	private List stoMotivosDevolucionList;
	
	private boolean mostrarPopupCliente;
	private boolean mostrarPopupCodigo;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	private static final String POPUP_CODIGO= "POPUP_CODIGO";
	
	
	// DEVUELVE --popup codigo de venta pedido--lupa
	@ManagedProperty(value="#{busquedaSTOCodigoVentaPedidoAction}")	
	private BusquedaSTOCodigoVentaPedidoAction busquedaSTOCodigoVentaPedido;
	//poup codigo de venta pedido --info
	@ManagedProperty(value="#{consultaPREMatrizFacturacionAction}")	
	private ConsultaPREMatrizFacturacionAction consultaPREMatrizFacturacion;	
	//ventana Cantidad Productos Devuelve--lupa
	@ManagedProperty(value="#{busquedaSTOCantidadDevuelveAction}")	
	private BusquedaSTOCantidadDevuelveAction busquedaSTOCantidadDevuelve;
	
	//DESEA--popup Codigo Venta Produc.Desea
	@ManagedProperty(value="#{busquedaSTOCodigoVentaMatrizAction}")	
	private BusquedaSTOCodigoVentaMatrizAction busquedaSTOCodigoVentaMatriz;
	
	//OPERACION- Tipo Referencia
	@ManagedProperty(value="#{busquedaSTOReferenciaOperacionAction}")	
	private BusquedaSTOReferenciaOperacionAction busquedaSTOReferenciaOperacion;
	
	
	private boolean editable;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOSolicitudPostVentaDetalleForm form = new MantenimientoSTOSolicitudPostVentaDetalleForm();
		return form;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		 MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;
		 Map criteria = new HashMap(); 
		     criteria.put("codigoPais",f.getCodigoPais());
			 criteria.put("numDocumento",f.getNumSecuencia());
			 criteria.put("numLote",f.getNumLote());
			 criteria.put("codPeriodo",f.getCodPeriodo());
			 criteria.put("codCliente",f.getCodCliente());
			 criteria.put("estadoProceso",f.getEstadoProceso());
			 criteria.put("motivoRechazo",f.getCodMotivoRechazo());
			 criteria.put("motivoDevolucion",f.getCodMotDevolucion());
			 criteria.put("codRegion",f.getCodRegion());
			 criteria.put("codZona",f.getCodZona());
			 criteria.put("canVentaDese",f.getCanVentaDese());
			 criteria.put("canVentaDevu",f.getCanVentaDevu());
			 criteria.put("codVentaDese",f.getCodVentaDese());
			 criteria.put("codVentaDevu",f.getCodVentaDevu());
			 criteria.put("codCia",f.getCodCia());
			 criteria.put("codOperacion",f.getCodOperacion());
			 criteria.put("codTipoOperacion",f.getCodTipoOperacion());
			 criteria.put("tipoReferencia",f.getTipoReferencia());
			 criteria.put("observaciones",f.getObservaciones());
			 criteria.put("numeroCruce",f.getNumeroCruce());
			 criteria.put("oidSoli", f.getOidSoliDevuelve());
			 ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			 procesoSTOEjecucionValidacionesService.updateSolicitudPostVentaDetalle(criteria);	
		
		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	    Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		f.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		Map criteria = new HashMap();		
			
		GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoTipo",gestion.getDocumento());
		criteria.put("numDocumento",gestion.getNumeroDocumento());
		criteria.put("numLote",gestion.getLote());
		criteria.put("codigoIso",pais.getCodigoIdiomaIso());
		
		criteriaOperacion.put("codigoUsuario", usuario.getLogin());
	    criteriaOperacion.put("codigoAccion", Constants.STO_PANTALLA_EDITABLE);
	    criteriaOperacion.put("codigoTipo", gestion.getDocumento());
	    	
	    this.editable=false;
	    if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null)
	    	this.editable=false;
	    else
	    	this.editable=true;
	    	
		List ListaPostVentaDetalle=procesoSTOEjecucionValidacionesService.getSolicitudPostVentaDetalle(criteria);
		
		SolicitudPostVentaDetalle solicitudPostVentaDetalle = (SolicitudPostVentaDetalle)ListaPostVentaDetalle.get(0);
			
		setSolicitudPostVentaDetalle(f,solicitudPostVentaDetalle);			
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());

		Map parametro = new HashMap();
		parametro.put("codCliente",f.getCodCliente());
		parametro.put("codigoPais",pais.getCodigo());
		parametro.put("idiomaIso",usuario.getIdioma().getCodigoISO());
		parametro.put("numeroDocumento",f.getNumDocumento());
		parametro.put("numLote", f.getNumLote());
			
		List ListaClasificacionConsultora=procesoSTOEjecucionValidacionesService.executeClasificacionConsultora(parametro);			
		this.stoClasificacionConsultoraList=ListaClasificacionConsultora;	 
		List ListaEstadisticaUltimasCampanas=procesoSTOEjecucionValidacionesService.getEstadisticaUltimasCampanas(parametro);			
		this.stoEstadisticaUltimasCampanasList=	ListaEstadisticaUltimasCampanas;
	    List ListaBloqueosConsultora=procesoSTOEjecucionValidacionesService.getBloqueosConsultora(parametro);			
		this.stoBLoqueosConsultoraList=	ListaBloqueosConsultora;
		List listaOperacionCodigoVenta=procesoSTOEjecucionValidacionesService.getOpeacionCodigoVentaSPVD(parametro);				
		this.stoOperacionCodigoVentaList=listaOperacionCodigoVenta;
			
		this.stoMotivosDevolucionList=procesoSTOEjecucionValidacionesService.getMotivosDevolucion(parametro);
		
			
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
	
	/**
	 * Metodo para setear los valores en el jsp de Solicitud Post Venta Detalle	 
	 */
	private  void setSolicitudPostVentaDetalle(MantenimientoSTOSolicitudPostVentaDetalleForm f,SolicitudPostVentaDetalle solicitudPostVentaDetalle) {
	
		f.setCodigoPais(solicitudPostVentaDetalle.getCodigoPais());
		f.setCanVentaDese(solicitudPostVentaDetalle.getCanVentaDese());
		f.setCanVentaDevu(solicitudPostVentaDetalle.getCanVentaDevu());
		f.setCodCia(solicitudPostVentaDetalle.getCodCia());
		f.setCodCliente(solicitudPostVentaDetalle.getCodCliente());
		f.setCodMotivoRechazo(solicitudPostVentaDetalle.getCodMotivoRechazo());
		f.setCodOperacion(solicitudPostVentaDetalle.getCodOperacion());
		f.setCodPeriodo(solicitudPostVentaDetalle.getCodPeriodo());
		f.setCodRegion(solicitudPostVentaDetalle.getCodRegion());
		f.setCodTipoDocumento(solicitudPostVentaDetalle.getCodTipoDocumento());
		f.setCodTipoOperacion(solicitudPostVentaDetalle.getCodTipoOperacion());
		f.setCodVentaDese(solicitudPostVentaDetalle.getCodVentaDese());
		f.setCodVentaDevu(solicitudPostVentaDetalle.getCodVentaDevu());
		f.setCodZona(solicitudPostVentaDetalle.getCodZona());
		f.setEstadoProceso(solicitudPostVentaDetalle.getEstadoProceso());
		f.setNumDocumento(solicitudPostVentaDetalle.getNumDocumento());
		f.setNumLote(solicitudPostVentaDetalle.getNumLote());
		f.setNumSecuencia(solicitudPostVentaDetalle.getNumSecuencia());
		f.setTipoReferencia(solicitudPostVentaDetalle.getTipoReferencia());
		f.setObservaciones(solicitudPostVentaDetalle.getObservaciones());
		f.setNombre(solicitudPostVentaDetalle.getNombre());
		f.setDescVentaDevu(solicitudPostVentaDetalle.getDescVentaDevu());
		f.setDescVentaDese(solicitudPostVentaDetalle.getDescVentaDese());
		f.setCodMotDevolucion(solicitudPostVentaDetalle.getCodMotDevolucion());
		f.setDescMotDevolucion(solicitudPostVentaDetalle.getDescMotDevolucion());
		f.setMontoMinimo(solicitudPostVentaDetalle.getMontoMinimo());
		f.setValorDeseado(solicitudPostVentaDetalle.getValorDeseado());
		f.setValorDevuelve(solicitudPostVentaDetalle.getValorDevuelve());
		f.setMontoTotal(solicitudPostVentaDetalle.getMontoTotal());
		f.setNumeroCruce(solicitudPostVentaDetalle.getNumeroCruce());
		f.setPeriodoReferencia(solicitudPostVentaDetalle.getPeriodoReferencia());
		f.setOidSoliDevuelve(solicitudPostVentaDetalle.getOidSoliDevuelve());
		f.setPrecioUniDevuelve(solicitudPostVentaDetalle.getPrecioUniDevuelve());
		
		f.setOrigen(solicitudPostVentaDetalle.getOrigen());
		f.setUsuarioRegistro(solicitudPostVentaDetalle.getUsuarioRegistro());
		f.setFechaRegistro(solicitudPostVentaDetalle.getFechaRegistro());
		f.setUsuarioModificacion(solicitudPostVentaDetalle.getUsuarioModificacion());
		f.setFechaModificacion(solicitudPostVentaDetalle.getFechaModificacion());
	}
	
	//POPUP --busqueda Nro Solicitud
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;
		if (accion.equals(this.POPUP_CLIENTE)){ 			
			String nroCruce=f.getNumeroCruce();	
			this.busquedaSTOCodigoVentaPedido.setNroCruce(nroCruce);				
			try {				
				this.busquedaSTOCodigoVentaPedido.iniciaValores();
				this.mostrarPopupCliente = true;			
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		if (accion.equals(this.POPUP_CODIGO)){ 			
			String nroCruce=f.getNumeroCruce();	
			this.busquedaSTOCodigoVentaMatriz.setNroCruce(nroCruce);							
			try {				
				this.busquedaSTOCodigoVentaMatriz.iniciaValores();				
				this.mostrarPopupCodigo = true;	
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
		this.mostrarPopupCodigo = false;	
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaSTOCodigoVentaPedido.verificarRegistro(event);			
				if (this.busquedaSTOCodigoVentaPedido.isSeleccionoRegistro()) {					
					CodigoVentaPedido codVentaPedido=(CodigoVentaPedido)this.busquedaSTOCodigoVentaPedido.getBeanRegistroSeleccionado();
					MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;						
					int codigo=Integer.parseInt(codVentaPedido.getCodigoVenta());
					f.setCodVentaDevu(codigo);	
					f.setOidSoliDevuelve(codVentaPedido.getOidSoli());
					this.busquedaSTOCodigoVentaPedido.setBeanRegistroSeleccionado(null);					
					this.formBusqueda=f;
				}
		}
		
		if (accion.equals(this.POPUP_CODIGO)) {
			this.busquedaSTOCodigoVentaMatriz.verificarRegistro(event);			
				if (this.busquedaSTOCodigoVentaMatriz.isSeleccionoRegistro()) {
					CodigoVentaMatriz codVentaMatriz=(CodigoVentaMatriz)this.busquedaSTOCodigoVentaMatriz.getBeanRegistroSeleccionado();
					MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;						
					int codigo=Integer.parseInt(codVentaMatriz.getCodigoVenta());
					f.setCodVentaDese(codigo);															
					this.busquedaSTOCodigoVentaMatriz.setBeanRegistroSeleccionado(null);					
					this.formBusqueda=f;
				}
		}		
		
	}
	
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.mostrarPopupCodigo = false;	
		this.busquedaSTOCodigoVentaPedido.setBeanRegistroSeleccionado(null);
		this.busquedaSTOCodigoVentaMatriz.setBeanRegistroSeleccionado(null);
	}			

	
	//salir de la pantalla	
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	//Abrir Ventana de Busqueda
	public void abrirVentanaBusquedaSTO(ActionEvent actionEvent){
		try {
			MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;
			String oidSoliDevuelve=f.getOidSoliDevuelve();
			String codPeriodo=f.getCodigoPeriodo();
			String codigoCliente=f.getCodCliente();
			this.busquedaSTOCantidadDevuelve.setOidSoliDevuelve(oidSoliDevuelve);
			this.busquedaSTOCantidadDevuelve.setCodPeriodo(codPeriodo);
			this.busquedaSTOCantidadDevuelve.setCodigoCliente(codigoCliente);
			this.busquedaSTOCantidadDevuelve.iniciaValores();			
			this.redireccionarPagina("busquedaSTOCantidadDevuelveForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	//abrir ventana ConsultaPreMatriz
	public void abrirVentanaConsultaPreMatriz(ActionEvent actionEvent){
		try {
			MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;			
			String varCuv=f.getCodVentaDevu().toString();
			String campanya=f.getPeriodoReferencia();
			this.consultaPREMatrizFacturacion.setVarCuv(varCuv);
			this.consultaPREMatrizFacturacion.setCampanya(campanya);
			this.consultaPREMatrizFacturacion.iniciaValores();						
			this.redireccionarPagina("consultaPREMatrizFacturacionFormSTO");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//abrir ventana
	public void abrirVentanaConsultaPreMatrizDesea(ActionEvent actionEvent){
		try {
			MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;			
			String varCuv=f.getCodVentaDese().toString();
			String campanya=f.getPeriodoReferencia();
			this.consultaPREMatrizFacturacion.setVarCuv(varCuv);
			this.consultaPREMatrizFacturacion.setCampanya(campanya);
			this.consultaPREMatrizFacturacion.iniciaValores();						
			this.redireccionarPagina("consultaPREMatrizFacturacionFormSTO");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//Abrir Ventana de Tipo de Operacion
	public void abrirVentanaSTOReferencia(ActionEvent actionEvent){
		try {
			MantenimientoSTOSolicitudPostVentaDetalleForm f =(MantenimientoSTOSolicitudPostVentaDetalleForm)this.formBusqueda;			
			String codCliente=f.getCodCliente();
			this.busquedaSTOReferenciaOperacion.setCodCliente(codCliente);
			this.busquedaSTOReferenciaOperacion.iniciaValores();								
			this.redireccionarPagina("busquedaSTOReferenciaOperacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
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

	public List getStoMotivosDevolucionList() {
		return stoMotivosDevolucionList;
	}

	public void setStoMotivosDevolucionList(List stoMotivosDevolucionList) {
		this.stoMotivosDevolucionList = stoMotivosDevolucionList;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaSTOCodigoVentaPedidoAction getBusquedaSTOCodigoVentaPedido() {
		return busquedaSTOCodigoVentaPedido;
	}

	public void setBusquedaSTOCodigoVentaPedido(
			BusquedaSTOCodigoVentaPedidoAction busquedaSTOCodigoVentaPedido) {
		this.busquedaSTOCodigoVentaPedido = busquedaSTOCodigoVentaPedido;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	public BusquedaSTOCantidadDevuelveAction getBusquedaSTOCantidadDevuelve() {
		return busquedaSTOCantidadDevuelve;
	}

	public void setBusquedaSTOCantidadDevuelve(
			BusquedaSTOCantidadDevuelveAction busquedaSTOCantidadDevuelve) {
		this.busquedaSTOCantidadDevuelve = busquedaSTOCantidadDevuelve;
	}

	public ConsultaPREMatrizFacturacionAction getConsultaPREMatrizFacturacion() {
		return consultaPREMatrizFacturacion;
	}

	public void setConsultaPREMatrizFacturacion(
			ConsultaPREMatrizFacturacionAction consultaPREMatrizFacturacion) {
		this.consultaPREMatrizFacturacion = consultaPREMatrizFacturacion;
	}

	public static String getPopupCodigo() {
		return POPUP_CODIGO;
	}

	public boolean isMostrarPopupCodigo() {
		return mostrarPopupCodigo;
	}

	public void setMostrarPopupCodigo(boolean mostrarPopupCodigo) {
		this.mostrarPopupCodigo = mostrarPopupCodigo;
	}

	public BusquedaSTOCodigoVentaMatrizAction getBusquedaSTOCodigoVentaMatriz() {
		return busquedaSTOCodigoVentaMatriz;
	}

	public void setBusquedaSTOCodigoVentaMatriz(
			BusquedaSTOCodigoVentaMatrizAction busquedaSTOCodigoVentaMatriz) {
		this.busquedaSTOCodigoVentaMatriz = busquedaSTOCodigoVentaMatriz;
	}

	public BusquedaSTOReferenciaOperacionAction getBusquedaSTOReferenciaOperacion() {
		return busquedaSTOReferenciaOperacion;
	}

	public void setBusquedaSTOReferenciaOperacion(
			BusquedaSTOReferenciaOperacionAction busquedaSTOReferenciaOperacion) {
		this.busquedaSTOReferenciaOperacion = busquedaSTOReferenciaOperacion;
	}
	
	
}
