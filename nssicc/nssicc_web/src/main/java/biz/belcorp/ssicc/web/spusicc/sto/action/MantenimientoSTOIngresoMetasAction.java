package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.IngresoMetas;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOIngresoMetasForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOIngresoMetasAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = 6672027042144504461L;
	
	private boolean editable;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOIngresoMetasForm  form = new MantenimientoSTOIngresoMetasForm();
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
		Map criteria = new HashMap(); 
		MantenimientoSTOIngresoMetasForm f =(MantenimientoSTOIngresoMetasForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
			(ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		 
		f.setSalirPantalla(Constants.SI);
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("numSecDocumento",f.getNumSecuencia());
		criteria.put("numLote",f.getNumLote());
		criteria.put("codCliente",f.getCodCliente());
		criteria.put("codigoCampanaInicio",f.getCodCampanaInicio());
		criteria.put("tipMeta",f.getTipMeta());
		criteria.put("montoMeta",f.getMontoMeta());
		criteria.put("codigoRegionArribo",f.getCodigoRegionArribo());
		criteria.put("codigoZonaArribo",f.getCodigoZonaArribo());

		procesoSTOEjecucionValidacionesService.updateIngresoMetas(criteria);	

		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOIngresoMetasForm f =(MantenimientoSTOIngresoMetasForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);
		Map criteriaOperacion = new HashMap();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		
		f.setOidPais(String.valueOf(oidPais));
		Map criteria = new HashMap();
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	
    	GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
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
    	
		List ListaIngresoMetas=procesoSTOEjecucionValidacionesService.getIngresoMetas(criteria);	   
		IngresoMetas ingreMetas = null;
		
		if (ListaIngresoMetas.size()>0)
			ingreMetas = (IngresoMetas)ListaIngresoMetas.get(0);		
		else
			ingreMetas = new IngresoMetas();		
		
		setIngresoMetas(f,ingreMetas);

		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
		
		criteria.put("codCliente",f.getCodCliente());
		f.setNombreConsultora(procesoSTOEjecucionValidacionesService.getNombreConsultora(criteria));
	}
	
	/**
	 * Metodo que setea los valores del jsp de Ingreso Metas	
	 */
	private  void setIngresoMetas(MantenimientoSTOIngresoMetasForm f,IngresoMetas ingreMetas  ) {
		
		f.setCodigoPais(ingreMetas.getCodPais());
		f.setNumLote(ingreMetas.getNumLote());
		f.setCodCliente(ingreMetas.getCodCliente());
		f.setCodCompania(ingreMetas.getCodCompania());
		f.setNumDocumento(ingreMetas.getNumDocumento());
		f.setNumLine(ingreMetas.getNumLine());
		f.setFechaProceso(ingreMetas.getFechaProceso());		
		f.setCodCampanaProc(ingreMetas.getCodCampanaProc());
		f.setTipMeta(ingreMetas.getTipMeta());
		f.setMontoMeta(ingreMetas.getMontoMeta());
		f.setCodCampanaInicio(ingreMetas.getCodCampanaInicio());
		f.setCodigoRegionArribo(ingreMetas.getCodigoRegionArribo());
		f.setCodigoZonaArribo(ingreMetas.getCodigoZonaArribo());
		f.setNumSecuencia(ingreMetas.getNumSecuencia());
		f.setDireccion(ingreMetas.getDireccion());
		f.setCodigoEstado(ingreMetas.getCodigoEstado());
		f.setIndMotivoRechazo(ingreMetas.getIndMotivoRechazo());
		f.setDescripcionEstado(ingreMetas.getDescripcionEstado());
	}

	
	//Salir a la Pantalla Padre
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	//Obtener Nombre Consultora
	public void getNombre(){
		MantenimientoSTOIngresoMetasForm f =(MantenimientoSTOIngresoMetasForm)this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		 if(StringUtils.isNotBlank(f.getCodCliente())){
	  	    	String nombre=ajax.getNombreCliente(f.getCodCliente());
	  	    	if(StringUtils.isNotBlank(nombre))
	  	    		f.setNombreConsultora(nombre);
	  	    	else
	  	    		f.setNombreConsultora("");
	  	} else    	
	  		f.setNombreConsultora("");  	
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	

}
