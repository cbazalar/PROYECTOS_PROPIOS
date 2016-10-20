package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CartaInvitacionFlexipago;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOCartaInvitacionFlexipagoForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOCartaInvitacionFlexipagoAction extends BaseMantenimientoSTOGestionAction{
	
	private static final long serialVersionUID = -1677316628400685341L;
	
	private LabelValue[] siccRegionStoFlxList;
	private LabelValue[] siccZonaStoFlxList;
	private List siccTipoDocumentoList;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")	
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearch;
	
	private boolean editable;
	private int tamDocIdentidad=0;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOCartaInvitacionFlexipagoForm  form = new MantenimientoSTOCartaInvitacionFlexipagoForm();
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
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Map criteria =new HashMap();
		MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService =(ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		f.setSalirPantalla(Constants.SI);		
		criteria = BeanUtils.describe(f);		
		procesoSTOEjecucionValidacionesService.updateCartaInvitacionFlexipago(criteria);		
		return true;
		
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm) this.formBusqueda;
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		Map criteriaOperacion =new HashMap();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);		
		
		Map criteria = new HashMap();
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		
		GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoTipo",gestion.getDocumento());
		criteria.put("numDocumento",gestion.getNumeroDocumento());
		criteria.put("numLote",gestion.getLote());
		criteria.put("codCliente",f.getCodigoConsultora());
		criteria.put("oidPais",oidPais);
				
		criteriaOperacion.put("codigoUsuario", usuario.getLogin());
    	criteriaOperacion.put("codigoAccion", Constants.STO_PANTALLA_EDITABLE);
    	criteriaOperacion.put("codigoTipo", gestion.getDocumento());
    	
    	this.editable=false;
    	if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null){
    		this.editable=false;
    	}else
    		this.editable=true;
    	
		List ListaCartaInvitacionFlexipago =procesoSTOEjecucionValidacionesService.getCartaInvitacionFlexipago(criteria);
	   
		CartaInvitacionFlexipago cartaInvitacionFlexipago = null;
		
		if (ListaCartaInvitacionFlexipago.size() > 0){
			cartaInvitacionFlexipago = (CartaInvitacionFlexipago)ListaCartaInvitacionFlexipago.get(0);
		}
		else{
			cartaInvitacionFlexipago = new CartaInvitacionFlexipago();
		}
		
		try{
			BeanUtils.copyProperties(f, cartaInvitacionFlexipago);	
		}catch(Exception e){
			
		}
		
		String noPermitirCompletarCerosIdentidad = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD);
		
		if(noPermitirCompletarCerosIdentidad != null)
			f.setPermitirCompletarCerosIdentidad(false);
		
		f.setOidPais(String.valueOf(oidPais));
		f.setLongitudTipoDocumento(ajaxService.getLongitudTipoDocumento(f.getOidPais(), f.getTipoDocumentoIden()));
		if(StringUtils.isNotBlank(f.getLongitudTipoDocumento()))
			this.tamDocIdentidad=Integer.parseInt(f.getLongitudTipoDocumento());
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);
		//Cargar regiones
		this.siccRegionStoFlxList = ajaxService.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);		
		//Cargar Zonas		
		if(StringUtils.isNotBlank(f.getCodigoRegion()))
			this.siccZonaStoFlxList = ajaxService.getZonasByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion());
		else
			this.siccZonaStoFlxList=null;
			
		criteria.put("oidPais", f.getOidPais());
		this.siccTipoDocumentoList=clienteService.getTiposDocumentoIdentidad(criteria);		
	}
	
	//Obtener Nombre de la Consultora
	public void getNombre(){
		MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		 if(StringUtils.isNotBlank(f.getCodigoConsultora())){
	  	    	String nombre=ajax.getNombreCliente(f.getCodigoConsultora());
	  	    	if(StringUtils.isNotBlank(nombre))
	  	    		f.setDescripcionConsultora(nombre);
	  	    	else
	  	    		f.setDescripcionConsultora("");
	  	} else    	
	  		  f.setDescripcionConsultora("");  	
	}
	
	//Mostrar las Zonas
	public void loadZonas(ValueChangeEvent val) {		
		MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm) this.formBusqueda;	
		if(val.getNewValue()!=null && val.getNewValue()!=""){
			String region = val.getNewValue().toString();
			if (StringUtils.isNotBlank(region)) {			
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.siccZonaStoFlxList=ajax.getZonasByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, region);
				f.setCodigoZona(null);			
			} 
		}else {
			this.siccZonaStoFlxList = null;
			f.setCodigoZona(null);	
		}
	}
	
	//ajustar Longitud Tipo Documento
	public void ajustarLongitudTipoDocumento(ValueChangeEvent val){		
		MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm) this.formBusqueda;
		String tipoDocumento = val.getNewValue().toString();
		if (!val.equals(null) && tipoDocumento.length() > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String data=ajax.getLongitudTipoDocumento(f.getOidPais(), tipoDocumento);
			if(StringUtils.isNotBlank(data)){
				f.setLongitudTipoDocumento(data);
				this.tamDocIdentidad=Integer.parseInt(data);
				String valor="";
				if(f.isPermitirCompletarCerosIdentidad())
					valor=completarCaracteres(f.getNumDocumentoIden(), f.getLongitudTipoDocumento(), "0");
				else
					valor=completarCaracteres(f.getNumDocumentoIden(), f.getLongitudTipoDocumento(), "");
				f.setNumDocumentoIden(valor);
			}
			
		}		
	}
	
	//Completar Caracteres de Num Consultora y Num Identidad
	public String completarCaracteres(String valor, String longitud, String caracter){
		String valorAux = "";
		int faltante=0;
		int nLongitud=Integer.parseInt(longitud) ;
		if (valor.length() != 0) {
			faltante = nLongitud - valor.length();
			valorAux = "";
			
			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			}
			else {
			
				faltante = valor.length() -nLongitud;
				valorAux = valor.substring(faltante, nLongitud);
			}
		}
		
		return valorAux;
	}
	
	//Popup openSearchConsultoraPopup
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm) this.formBusqueda;		
			try {				
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
			this.busquedaConsultoraPOPUPSearch.verificarRegistro(event);			
			if (this.busquedaConsultoraPOPUPSearch.isSeleccionoRegistro()) {					
					Cliente cliente=(Cliente)this.busquedaConsultoraPOPUPSearch.getBeanRegistroSeleccionado();
					MantenimientoSTOCartaInvitacionFlexipagoForm f =(MantenimientoSTOCartaInvitacionFlexipagoForm)this.formBusqueda;
					String codigoCliente=cliente.getCodigo();
					f.setCodigoConsultora(codigoCliente);														
					this.busquedaConsultoraPOPUPSearch.setBeanRegistroSeleccionado(null);					
					this.formBusqueda=f;
					this.getNombre();
			}
		}	
	}
		
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;			
		this.busquedaConsultoraPOPUPSearch.setBeanRegistroSeleccionado(null);
	}
	
	//Salir a la Pantalla Padre
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	public LabelValue[] getSiccRegionStoFlxList() {
		return siccRegionStoFlxList;
	}

	public void setSiccRegionStoFlxList(LabelValue[] siccRegionStoFlxList) {
		this.siccRegionStoFlxList = siccRegionStoFlxList;
	}

	public LabelValue[] getSiccZonaStoFlxList() {
		return siccZonaStoFlxList;
	}

	public void setSiccZonaStoFlxList(LabelValue[] siccZonaStoFlxList) {
		this.siccZonaStoFlxList = siccZonaStoFlxList;
	}

	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearch() {
		return busquedaConsultoraPOPUPSearch;
	}

	public void setBusquedaConsultoraPOPUPSearch(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearch) {
		this.busquedaConsultoraPOPUPSearch = busquedaConsultoraPOPUPSearch;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getTamDocIdentidad() {
		return tamDocIdentidad;
	}

	public void setTamDocIdentidad(int tamDocIdentidad) {
		this.tamDocIdentidad = tamDocIdentidad;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}
	
}
