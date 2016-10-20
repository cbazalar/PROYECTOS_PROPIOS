package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.OrdenCompraDetalle;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOOrdenCompraDetalleForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOOrdenCompraDetalleAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = -8132721937643880354L;
	
	private boolean editable;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOOrdenCompraDetalleForm  form = new MantenimientoSTOOrdenCompraDetalleForm();
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
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOOrdenCompraDetalleForm f =(MantenimientoSTOOrdenCompraDetalleForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
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
    	String origen = gestion.getDescripcionOrigen();
    	criteria.put("codigoOnLine", "OL");
    	String online = procesoSTOEjecucionValidacionesService.getDescripcionOnline(criteria);
    	if (online.equals(origen)){
    		this.editable=false;
    	}else{
    		if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null){
    			this.editable=false;
        	}
        	else
        		this.editable=true;
    	}
		List ListaOrdenCompraDetalle=procesoSTOEjecucionValidacionesService.getOrdenCompraDetalle(criteria);
		OrdenCompraDetalle ordenCompraDetalle = (OrdenCompraDetalle)ListaOrdenCompraDetalle.get(0);
		setOrdenCompraDetalle(f,ordenCompraDetalle);
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());		
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {

		 Map criteria = new HashMap(); 
		 MantenimientoSTOOrdenCompraDetalleForm f =(MantenimientoSTOOrdenCompraDetalleForm)this.formBusqueda;
		
		 criteria.put("codigoPais",f.getCodigoPais());
		 criteria.put("numDocumento",f.getNumSecuencia());
		 criteria.put("numLote",f.getNumLote());
		 criteria.put("codPeriodo",f.getCodPeriodo());
		 criteria.put("codCliente",f.getCodCliente());
		 criteria.put("codVenta",f.getCodVenta());
		 criteria.put("tipoPosicion",f.getTipoPosicion());
		 criteria.put("valUnidadesDemandadas",f.getValUniDemandadas());
		 criteria.put("estadoProceso",f.getEstadoProceso());
		 criteria.put("motivoRechazo",f.getCodMotivoRechazo());		 
		 
		 ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		 procesoSTOEjecucionValidacionesService.updateOrdenCompraDetalle(criteria);

		return true;
	}
	

	/**
	 * Metodo para setear los valores en el jsp de Orden de compra detalle	
	 */
	private  void setOrdenCompraDetalle(MantenimientoSTOOrdenCompraDetalleForm f,OrdenCompraDetalle ordenCompraDetalle  ) {
		
		f.setCodigoPais(ordenCompraDetalle.getCodPais());
		f.setCodCliente(ordenCompraDetalle.getCodCliente());
		f.setCodMotivoRechazo(ordenCompraDetalle.getCodMotivoRechazo());
		f.setCodPeriodo(ordenCompraDetalle.getCodPeriodo());
		f.setNumSecuencia(ordenCompraDetalle.getNumSecuencia());
		f.setEstadoProceso(ordenCompraDetalle.getEstadoProceso());
		f.setNumDocumento(ordenCompraDetalle.getNumDocumento());
		f.setNumLote(ordenCompraDetalle.getNumLote());
		f.setCodVenta(ordenCompraDetalle.getCodVenta());
		f.setTipoPosicion(ordenCompraDetalle.getTipoPosicion());
		f.setValUniDemandadas(ordenCompraDetalle.getValUniDemandadas());

	}
	
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	
	
	
}
