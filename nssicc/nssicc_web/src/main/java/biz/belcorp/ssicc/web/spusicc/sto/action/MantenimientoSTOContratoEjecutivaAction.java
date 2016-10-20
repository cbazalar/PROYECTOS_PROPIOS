package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOContratoEjecutivaForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOContratoEjecutivaAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = 6620598973835030048L;
	
	private List stoTipoDocList;
	private boolean editable;
	private boolean indicadorFirma;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOContratoEjecutivaForm   form = new MantenimientoSTOContratoEjecutivaForm ();
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
		MantenimientoSTOContratoEjecutivaForm f =(MantenimientoSTOContratoEjecutivaForm)this.formBusqueda;
		f.setSalirPantalla(Constants.SI);
		if(this.indicadorFirma)
			f.setIndicadorFirma(Constants.NUMERO_UNO);
		else
			f.setIndicadorFirma(Constants.NUMERO_CERO);
		
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("numSecuencia",f.getNumSecuencia());
		criteria.put("numLote",f.getNumLote());
		criteria.put("codPeriodo",f.getCodPeriodo());
		criteria.put("codCliente",f.getCodCliente());
		criteria.put("numeroPreImpreso", f.getNumDocumento());
		criteria.put("numDocuIdentidad", f.getNumDocuIdentidad());
		criteria.put("tipoDocumento", f.getTipoDocumento());
		criteria.put("numDocuLega", f.getNumDocuLegal());
		criteria.put("indicadorFirma",StringUtils.isNotEmpty(f.getIndicadorFirma())?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
		
						
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
			 (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		procesoSTOEjecucionValidacionesService.updateContratoEjecutiva(criteria);	

		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOContratoEjecutivaForm f =(MantenimientoSTOContratoEjecutivaForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		//				
		Map criteria = new HashMap();
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	
    	GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoTipo",gestion.getDocumento());
		criteria.put("numSecuenciaDocumento",gestion.getNumeroDocumento());
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

    	f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
    	if(StringUtils.equals(f.getIndicadorFirma(),Constants.NUMERO_UNO))
    		this.indicadorFirma=true;
    	else
    		this.indicadorFirma=false;
    	
    	//-- Setea combo tipo documento    	
    	this.stoTipoDocList=procesoSTOEjecucionValidacionesService.getTipoDocumento();
		Map mapContratoEjecutiva=procesoSTOEjecucionValidacionesService.getContratoEjecutiva(criteria);
		try {
			if(mapContratoEjecutiva!=null){
				BeanUtils.copyProperties(f, mapContratoEjecutiva);
				if(f.getValNombre1()==null) f.setValNombre1("");
				if(f.getValNombre2()==null) f.setValNombre2("");
				if(f.getValApellido1()==null) f.setValApellido1("");
				if(f.getValApellido2()==null) f.setValApellido2("");
				
				f.setNombreActual(f.getValNombre1()+" "+f.getValNombre2()+" "+f.getValApellido1()+" "+f.getValApellido2());				
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Salir a la Pantalla Padre
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	public List getStoTipoDocList() {
		return stoTipoDocList;
	}

	public void setStoTipoDocList(List stoTipoDocList) {
		this.stoTipoDocList = stoTipoDocList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isIndicadorFirma() {
		return indicadorFirma;
	}

	public void setIndicadorFirma(boolean indicadorFirma) {
		this.indicadorFirma = indicadorFirma;
	}	

}
