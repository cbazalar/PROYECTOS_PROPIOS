package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.text.ParseException;
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
import biz.belcorp.ssicc.dao.spusicc.sto.model.DuplaCyzone;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTODuplaCyzoneForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTODuplaCyzoneAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = -8003247984944841078L;
	
	private boolean editable;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTODuplaCyzoneForm form = new MantenimientoSTODuplaCyzoneForm();
		return form;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {		 
		 MantenimientoSTODuplaCyzoneForm f =(MantenimientoSTODuplaCyzoneForm)this.formBusqueda;
		 if(f.getFechaProcesoDate()!=null)
			 f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoDate()));
		 if(f.getFechaNacimientoDate()!=null)
			 f.setFechaNacimiento(DateUtil.convertDateToString(f.getFechaNacimientoDate()));
		 
		 f.setSalirPantalla(Constants.SI);
		 Map criteria = new HashMap(); 
		 criteria.put("codigoPais",f.getCodigoPais());
		 criteria.put("numDocumento",f.getNumSecuencia());
		 criteria.put("numLote",f.getNumLote());
		 criteria.put("codPeriodo",f.getCodPeriodo());
		 criteria.put("codCliente",f.getCodCliente());
		 criteria.put("codCompania",f.getCodCompania());
		 criteria.put("codTipoDocumento",f.getCodTipoDocumento());
		 criteria.put("indEstaProceso",f.getIndEstaProceso());
		 criteria.put("indMotivoRechazo",f.getIndMotivoRechazo());
		 criteria.put("indDuplaNueva",f.getIndDuplaNueva());
		 criteria.put("indActuDatos",f.getIndActuDatos());
		 criteria.put("indEnvio",f.getIndEnvio());
		 criteria.put("apellido1",f.getValApellido1());
		 criteria.put("apellido2",f.getValApellido2());
		 criteria.put("celularCliente",f.getValCeluCliente());
		 criteria.put("nombre1",f.getValNombre1());
		 criteria.put("nombre2",f.getValNombre2());
		 criteria.put("telfCliente",f.getValTelfCliente());
		 criteria.put("codRegion",f.getValRegionArribo());
		 criteria.put("codZona",f.getValZonaArribo());
		 criteria.put("email",f.getValMailCliente());
		 criteria.put("fecProceso",f.getFechaProceso());
		 criteria.put("fecNacimiento",f.getFechaNacimiento());
		 
		 ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
	     procesoSTOEjecucionValidacionesService.updateDuplaCyzone(criteria);	
		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTODuplaCyzoneForm f =(MantenimientoSTODuplaCyzoneForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);
		
		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		
		f.setOidPais(String.valueOf(oidPais));		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    			
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
    	if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null){
    		this.editable=false;
    	}
    	else
    		this.editable=true;
	
		List ListaSolicitudCredito=procesoSTOEjecucionValidacionesService.getDuplaCyzone(criteria);
		
		DuplaCyzone duplaCyzone = (DuplaCyzone)ListaSolicitudCredito.get(0);

		setDuplaCyzone(f,duplaCyzone);
		
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
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
	 * Metodo que setea los valores del jsp de dupla cyzone	
	 * @throws ParseException 
	 */
	private  void setDuplaCyzone(MantenimientoSTODuplaCyzoneForm f,DuplaCyzone duplaCyzone) throws ParseException {

		f.setCodigoPais(duplaCyzone.getCodPais());
		f.setNumLote(duplaCyzone.getNumLote());
		f.setCodCliente(duplaCyzone.getCodCliente());
		f.setCodCompania(duplaCyzone.getCodCompania());
		f.setCodPeriodo(duplaCyzone.getCodPeriodo());
		f.setCodTipoDocumento(duplaCyzone.getCodTipoDocumento());
		f.setFechaNacimiento(duplaCyzone.getFechaNacimiento());
		f.setFechaProceso(duplaCyzone.getFechaProceso());
		f.setIndEstaProceso(duplaCyzone.getIndEstaProceso());
		f.setIndMotivoRechazo(duplaCyzone.getIndMotivoRechazo());
        f.setIndEnvio(duplaCyzone.getIndEnvio());
        f.setIndDuplaNueva(duplaCyzone.getIndDuplaNueva());
        f.setIndActuDatos(duplaCyzone.getIndActuDatos());
        f.setNumDocumento(duplaCyzone.getNumDocumento());
		f.setValApellido1(duplaCyzone.getValApellido1());
		f.setValApellido2(duplaCyzone.getValApellido2());
		f.setValCeluCliente(duplaCyzone.getValCeluCliente());
		f.setValNombre1(duplaCyzone.getValNombre1());
		f.setValNombre2(duplaCyzone.getValNombre2());
		f.setValRegionArribo(duplaCyzone.getValRegionArribo());
		f.setValTelfCliente(duplaCyzone.getValTelfCliente());
		f.setValZonaArribo(duplaCyzone.getValZonaArribo());
		f.setNumSecuencia(duplaCyzone.getNumSecuencia());
		f.setValMailCliente(duplaCyzone.getValMailCliente());	
		//date
		if(StringUtils.isNotBlank(f.getFechaNacimiento()))
			f.setFechaNacimientoDate(DateUtil.convertStringToDate(f.getFechaNacimiento()));
		if(StringUtils.isNotBlank(f.getFechaProceso()))
			f.setFechaProcesoDate(DateUtil.convertStringToDate(f.getFechaProceso()));
	}
	
	//Salir de la Ventana
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
