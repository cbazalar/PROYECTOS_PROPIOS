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
import biz.belcorp.ssicc.dao.spusicc.sto.model.CuponPago;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOActualizacionDatosForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOCuponPagoForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOFamiliaSeguraForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOCuponPagoAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = 2471197805052863605L;
	
	private boolean editable;
	private boolean indicadorRechazoSello;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOCuponPagoForm  form = new MantenimientoSTOCuponPagoForm();
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
		MantenimientoSTOCuponPagoForm f =(MantenimientoSTOCuponPagoForm)this.formBusqueda;
		f.setSalirPantalla(Constants.SI);
		if(this.indicadorRechazoSello)
			f.setIndRechazoSello("S");
		else
			f.setIndRechazoSello("N");
		if(f.getFechaProcesoDate()!=null)
			f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoDate()));
		 criteria.put("codigoPais",f.getCodigoPais());
		 criteria.put("numSecDocumento",f.getNumSecuencia());
		 criteria.put("numDocumento",f.getNumDocumento());
		 criteria.put("numLote",f.getNumLote());
		 criteria.put("codPeriodo",f.getCodPeriodo());
		 criteria.put("codCliente",f.getCodCliente());		 
		 criteria.put("codCompania",f.getCodCompania());
		 criteria.put("codTipoDocumento",f.getCodTipoDocumento());
		 criteria.put("impValor",f.getImpValor());
		 criteria.put("valorDeuda",f.getValorDeuda());
		 criteria.put("estadoCupon",f.getEstadoCupon());
		
		 if(f.getIndRechazoSello().compareToIgnoreCase("S")==0)			 
			 criteria.put("indRechazoSello","1");		 
		 else			 
			 criteria.put("indRechazoSello","0");		 
		 
		 criteria.put("indEstaProceso",f.getIndEstaProceso());
		 criteria.put("indMotivoRechazo",f.getIndMotivoRechazo());
		 criteria.put("codRegion",f.getCodRegion());
		 criteria.put("codZona",f.getCodZona());		 
		 criteria.put("fecProceso",f.getFechaProceso());
		 criteria.put("codigoVerificador",f.getCodigoVerificador());		 
		 criteria.put("codigoZonaArribo",f.getCodigoZonaArribo());
		 
		 ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		 procesoSTOEjecucionValidacionesService.updateCuponPago(criteria);	

		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOCuponPagoForm f =(MantenimientoSTOCuponPagoForm)this.formBusqueda;
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
    	if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null){
    		this.editable=false;
    	}
    	else
    		this.editable=true;
    	
		List ListaCuponPago=procesoSTOEjecucionValidacionesService.getCuponPago(criteria);

		CuponPago cuponPago = (CuponPago)ListaCuponPago.get(0);

		setCuponPago(f,cuponPago,pais.getCodigo());
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
		
		criteria.put("codCliente",f.getCodCliente());
		f.setNombreConsultora(procesoSTOEjecucionValidacionesService.getNombreConsultora(criteria));
	}
	
	/**
	 * Metodo que setea los valores del jsp de cupon pago	
	 * @throws ParseException 
	 */
	private  void setCuponPago(MantenimientoSTOCuponPagoForm f,CuponPago cuponPago, String pais  ) throws Exception {
		
		f.setCodigoPais(cuponPago.getCodPais());
		f.setNumLote(cuponPago.getNumLote());
		f.setCodCliente(cuponPago.getCodCliente());
		f.setCodCompania(cuponPago.getCodCompania());
		f.setCodPeriodo(cuponPago.getCodPeriodo());
		f.setCodTipoDocumento(cuponPago.getCodTipoDocumento());
		f.setFechaProceso(cuponPago.getFechaProceso());
		f.setIndEstaProceso(cuponPago.getIndEstaProceso());
		f.setIndMotivoRechazo(cuponPago.getIndMotivoRechazo());
		f.setNumDocumento(cuponPago.getNumDocumento());
		f.setEstadoCupon(cuponPago.getEstadoCupon());
		f.setImpValor(cuponPago.getImpValor());
		if(cuponPago.getIndRechazoSello()!=null){
		if(cuponPago.getIndRechazoSello().compareToIgnoreCase("1")==0)
			 f.setIndRechazoSello("S");
		 else
			 f.setIndRechazoSello("N");
		}
		else
			f.setIndRechazoSello("N");
		
		f.setValorDeuda(cuponPago.getValorDeuda());
		f.setCodRegion(cuponPago.getCodRegion());
		f.setCodZona(cuponPago.getCodZona());
		f.setNumSecuencia(cuponPago.getNumSecuencia());
		f.setCodigoVerificador(cuponPago.getCodigoVerificador());		
		f.setCodigoZonaArribo(cuponPago.getCodigoZonaArribo());
		if(StringUtils.equals(f.getIndRechazoSello(), "S"))
			this.indicadorRechazoSello=true;
		else
			this.indicadorRechazoSello=false;
		
		if(StringUtils.isNotBlank(f.getFechaProceso()))
			f.setFechaProcesoDate(DateUtil.convertStringToDate(f.getFechaProceso()));
		 
				
	}
	
	//Obtener Nombre de la Consultora
	public void getNombre(){
		MantenimientoSTOCuponPagoForm f =(MantenimientoSTOCuponPagoForm)this.formBusqueda;
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
	
	//Salir a la PantallaPadre
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

	public boolean isIndicadorRechazoSello() {
		return indicadorRechazoSello;
	}

	public void setIndicadorRechazoSello(boolean indicadorRechazoSello) {
		this.indicadorRechazoSello = indicadorRechazoSello;
	}
	

}
