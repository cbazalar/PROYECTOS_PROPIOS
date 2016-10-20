package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionSTO;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOValidacionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOValidacionSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({"unchecked","rawtypes"})
public class MantenimientoSTOValidacionSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3054781044311804542L;
	private List stoTipoDocumentoValidacionesList;
	private List stoValidacionList;
	private LabelValue[] stoMotivoRechazoList;
	
	private boolean flagIndicadorGestionable;
	private boolean flagIndicadorContinuacion;
	private boolean flagIndicadorRechazoAutomatico;
	private boolean flagIndicadorHistoricoExcepciones;
	private boolean flagIndicadorRechazoGestion;
	private boolean flagIndicadorModificacionConsultora;
	private boolean flagIndicadorExcepcionValidacion;
	private boolean flagIndicadorMensajeAdicionalError;
	private boolean flagIndicadorVisualizarMotivoGestion;
	private boolean flagIndicadorForzarValidacion;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoSTOValidacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoSTOValidacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		
		MantenimientoSTOValidacionSearchForm f = new MantenimientoSTOValidacionSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
		MantenimientoSTOValidacionSearchForm f = (MantenimientoSTOValidacionSearchForm) this.formBusqueda;
		MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
		
		Map criteria = BeanUtils.describe(f);
		
		List validacionesSTOList = (List)service.getValidacionListSTO(criteria);
		this.stoValidacionList = validacionesSTOList;
				
		return validacionesSTOList;
	
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		Map bean = (Map) this.beanRegistroSeleccionado;
		String codigoValidacion =  bean.get("codigoValidacion").toString();
		if (codigoValidacion != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + codigoValidacion);
			}
			try {			
				MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
				service.deleteValidacionSTO(codigoValidacion);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
							
			}
		}
	
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoSTOValidacionAction");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoSTOValidacionForm f = (MantenimientoSTOValidacionForm) this.formMantenimiento;
		MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		
		boolean isNew = f.isNewRecord();
		
		ValidacionSTO validacionSto = new ValidacionSTO();
		
		if(flagIndicadorGestionable)
			f.setIndicadorGestionable("1");
		else
			f.setIndicadorGestionable("0");
		
		if(flagIndicadorContinuacion)
			f.setIndicadorContinuacion("S");
		else
			f.setIndicadorContinuacion("N");
		
		if(flagIndicadorRechazoAutomatico)
			f.setIndicadorRechazoAutomatico("1");
		else
			f.setIndicadorRechazoAutomatico("0");
		
		if(flagIndicadorHistoricoExcepciones)
			f.setIndicadorHistoricoExcepciones("1");
		else
			f.setIndicadorHistoricoExcepciones("0");
		
		if(flagIndicadorRechazoGestion)
			f.setIndicadorRechazoGestion("1");
		else
			f.setIndicadorRechazoGestion("0");
		
		if(flagIndicadorModificacionConsultora)
			f.setIndicadorModificacionConsultora("1");
		else
			f.setIndicadorModificacionConsultora("0");
		
		if(flagIndicadorExcepcionValidacion)
			f.setIndicadorExcepcionValidacion("S");
		else
			f.setIndicadorExcepcionValidacion("N");
			
		if(flagIndicadorMensajeAdicionalError)
			f.setIndicadorMensajeAdicionalError("S");
		else
			f.setIndicadorMensajeAdicionalError("N");
			
		if(flagIndicadorVisualizarMotivoGestion)
			f.setIndicadorVisualizarMotivoGestion("S");
		else
			f.setIndicadorVisualizarMotivoGestion("N");
			
		if(flagIndicadorForzarValidacion)	
			f.setIndicadorForzarValidacion("S");
		else
			f.setIndicadorForzarValidacion("N");
		
		validacionSto.setCodigoPais(f.getCodigoPais());
		validacionSto.setTipoDocumento(f.getTipoDocumento());
		validacionSto.setDescripcionDocumento(f.getDescripcionDocumento());
		validacionSto.setCodigoValidacion(f.getCodigoValidacion());
		validacionSto.setDescripcionValidacion(f.getDescripcionValidacion());
		validacionSto.setProcesoValidacion(f.getProcesoValidacion());
		validacionSto.setProcesoAprobar(f.getProcesoAprobar());
		validacionSto.setProcesoDesaprobar(f.getProcesoDesaprobar());
		validacionSto.setMotivoRechazo(f.getMotivoRechazo());
		validacionSto.setIndicadorGestionable(f.getIndicadorGestionable());
		validacionSto.setIndicadorContinuacion(f.getIndicadorContinuacion());
		validacionSto.setIndicadorRechazoAutomatico(f.getIndicadorRechazoAutomatico());
		validacionSto.setIndicadorHistoricoExcepciones(f.getIndicadorHistoricoExcepciones());
		validacionSto.setIndicadorRechazoGestion(f.getIndicadorRechazoGestion());
		validacionSto.setIndicadorModificacionConsultora(f.getIndicadorModificacionConsultora());
		validacionSto.setIndicadorExcepcionValidacion(f.getIndicadorExcepcionValidacion());
		validacionSto.setIndicadorMensajeAdicionalError(f.getIndicadorMensajeAdicionalError());
		validacionSto.setIndicadorVisualizarMotivoGestion(f.getIndicadorVisualizarMotivoGestion());
		validacionSto.setIndicadorForzarValidacion(f.getIndicadorForzarValidacion());
		
//		BeanUtils.copyProperties(validacionSto, f);
		Map params = BeanUtils.describe(f);
		List lista = service.getValidacionListSTO(params);
		
		try {	 
			 if(isNew){
				 if(lista != null && lista.size() > 0)
	    			{
	    				addInfo("Mensaje: ", getResourceMessage("mantenimientoSTOValidacionForm.error.duplicado"));		    				        			        			
	        			return false;
	    			}else
	    			{
	        			service.insertValidacionSTO(validacionSto, usuario);   				
	    			}
				 
			 }else{
				 boolean actualizar = true;
	    			if(lista != null && lista.size() > 0)
	    			{
	    				if(lista.size() == 1)
	    				{
	    					String oid = MapUtils.getString((Map)lista.get(0), "codigoValidacion", "");
	    					if(!StringUtils.equals(oid, validacionSto.getCodigoValidacion()))
	    					{actualizar = false;}
	    					else
	    					{actualizar = true;}
	    				}
	    				else
	    				{actualizar = false;}
	    			}

	    			if(actualizar)
	    			{
	        			service.updateValidacionSTO(validacionSto, usuario);
	        			addInfo("Mensaje: ", getResourceMessage("mantenimientoSTOValidacionForm.updated"));
	        			
	    			}else{
	    				addInfo("Mensaje: ", getResourceMessage("mantenimientoSTOValidacionForm.error.duplicado")); 				
	        			return false;
	    			}
			 }
			 
		 }
	     catch (InvalidIdentifierException iie) {
	            String codigo = iie.getIdentifier().toString();
	            addError("Error: ", getReportResourceMessage("errors.invalid.id")+ codigo);
	            return false;
	     }
	     catch (InvalidDescriptionException ide) {
	            String descripcion = ide.getDescription();
	            addError("Error: ", getReportResourceMessage("errors.invalid.description") + descripcion);
	            return false;
	     }
	
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
			
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo()); 
		
		MantenimientoSTOValidacionForm f = new MantenimientoSTOValidacionForm();
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		this.stoTipoDocumentoValidacionesList =  procesoSTOEjecucionValidacionesService.getTipoDocumentosDigitList();
		
		f.setTipoDocumento("");
		f.setCodigoValidacion("");
		this.flagIndicadorGestionable = false;
		this.flagIndicadorContinuacion = false;
		this.flagIndicadorRechazoAutomatico = false;
		this.flagIndicadorHistoricoExcepciones = false;
		this.flagIndicadorRechazoGestion = false;
		this.flagIndicadorModificacionConsultora = false;
		this.flagIndicadorExcepcionValidacion = false;
		this.flagIndicadorMensajeAdicionalError = false;
		this.flagIndicadorVisualizarMotivoGestion = false;
		this.flagIndicadorForzarValidacion = false;
		
		if(!this.accion.equals(this.ACCION_NUEVO))
		{
			MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
			
			Map bean = (Map) this.beanRegistroSeleccionado;
			
			String codigoValidacion =  bean.get("codigoValidacion").toString();
			ValidacionSTO validacionSTO = new ValidacionSTO();
			validacionSTO.setCodigoValidacion(codigoValidacion);
			
			if(StringUtils.isNotBlank(codigoValidacion)){
				 validacionSTO = service.getValidacionSTO(validacionSTO);
				
				if(validacionSTO!=null){
					BeanUtils.copyProperties(f, validacionSTO);
					this.loadMotivosRechazo(f.getTipoDocumento());
					this.cargarFlags(f);
				}		                
			}
			
			f.setNewRecord(false);
		}
	
		return f;		
	}

	@Override
	protected void setViewAtributes() throws Exception {
	
		this.mostrarBotonConsultar = false;
		MantenimientoSTOValidacionSearchForm f = (MantenimientoSTOValidacionSearchForm) this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		this.stoTipoDocumentoValidacionesList = procesoSTOEjecucionValidacionesService.getTipoDocumentosDigitList();
		
		f.setTipoDocumento("");
		f.setCodigoValidacion("");
		f.setDescripcionValidacion("");
	
		
	}
	
	public void cambiaTipoDocumentoDigi(ValueChangeEvent val){
		MantenimientoSTOValidacionForm f =  (MantenimientoSTOValidacionForm) this.formMantenimiento;
		f.setTipoDocumento((String)val.getNewValue());
		String codTipoDocumento = f.getTipoDocumento();

	    if(codTipoDocumento != null && codTipoDocumento.length()>0){

			obtenerCodigoValidacion(codTipoDocumento, f);
			loadMotivosRechazo(codTipoDocumento);
	    }else{
	    	f.setCodigoPais("");
	    }
	}

	private void obtenerCodigoValidacion(String codTipoDocumento, MantenimientoSTOValidacionForm f){
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		f.setCodigoValidacion(ajax.getCodigoValidacion(codTipoDocumento));
		
	}

	

	private void loadMotivosRechazo(String codTipoDocumento){
		 
		 Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		 if(codTipoDocumento != null && codTipoDocumento != "" && codTipoDocumento != " "){
			 AjaxService ajax = (AjaxService) getBean("ajaxService");
			 
			this.setStoMotivoRechazoList(ajax.getMotivosRechazoSTOList(codTipoDocumento, pais.getCodigo()));
		 }
	}
	
	private void cargarFlags(MantenimientoSTOValidacionForm f)
	{
		
		if(f.getIndicadorGestionable().equals("1"))
			flagIndicadorGestionable = true;
		else
			flagIndicadorGestionable = false;
		
		if(f.getIndicadorContinuacion().equals("S"))
			flagIndicadorContinuacion = true;
		else
			flagIndicadorContinuacion = false;
		
		if(f.getIndicadorRechazoAutomatico().equals("1"))
			flagIndicadorRechazoAutomatico = true;
		else
			flagIndicadorRechazoAutomatico = false;
		
		if(f.getIndicadorHistoricoExcepciones().equals("1"))
			flagIndicadorHistoricoExcepciones = true;
		else
			flagIndicadorHistoricoExcepciones = false;
		
		if(f.getIndicadorRechazoGestion().equals("1"))
			flagIndicadorRechazoGestion = true;
		else
			flagIndicadorRechazoGestion = false;
		
		if(f.getIndicadorModificacionConsultora().equals("1"))
			flagIndicadorModificacionConsultora = true;
		else
			flagIndicadorModificacionConsultora = false;
		
		if(f.getIndicadorExcepcionValidacion().equals("S"))
			flagIndicadorExcepcionValidacion = true;
		else
			flagIndicadorExcepcionValidacion = false;
			
		if(f.getIndicadorMensajeAdicionalError().equals("S"))
			flagIndicadorMensajeAdicionalError = true;
		else
			flagIndicadorMensajeAdicionalError = false;
			
		if(f.getIndicadorVisualizarMotivoGestion().equals("S"))
			flagIndicadorVisualizarMotivoGestion = true;
		else
			flagIndicadorVisualizarMotivoGestion = false;
			
		if(f.getIndicadorForzarValidacion().equals("S"))	
			flagIndicadorForzarValidacion = true;
		else
			flagIndicadorForzarValidacion = false;
	}
	
	public List getStoTipoDocumentoValidacionesList() {
		return stoTipoDocumentoValidacionesList;
	}

	public void setStoTipoDocumentoValidacionesList(
			List stoTipoDocumentoValidacionesList) {
		this.stoTipoDocumentoValidacionesList = stoTipoDocumentoValidacionesList;
	}

	public List getStoValidacionList() {
		return stoValidacionList;
	}

	public void setStoValidacionList(List stoValidacionList) {
		this.stoValidacionList = stoValidacionList;
	}

	public LabelValue[] getStoMotivoRechazoList() {
		return stoMotivoRechazoList;
	}

	public void setStoMotivoRechazoList(LabelValue[] stoMotivoRechazoList) {
		this.stoMotivoRechazoList = stoMotivoRechazoList;
	}

	public boolean isFlagIndicadorGestionable() {
		return flagIndicadorGestionable;
	}

	public void setFlagIndicadorGestionable(boolean flagIndicadorGestionable) {
		this.flagIndicadorGestionable = flagIndicadorGestionable;
	}

	public boolean isFlagIndicadorContinuacion() {
		return flagIndicadorContinuacion;
	}

	public void setFlagIndicadorContinuacion(boolean flagIndicadorContinuacion) {
		this.flagIndicadorContinuacion = flagIndicadorContinuacion;
	}

	public boolean isFlagIndicadorRechazoAutomatico() {
		return flagIndicadorRechazoAutomatico;
	}

	public void setFlagIndicadorRechazoAutomatico(
			boolean flagIndicadorRechazoAutomatico) {
		this.flagIndicadorRechazoAutomatico = flagIndicadorRechazoAutomatico;
	}

	public boolean isFlagIndicadorHistoricoExcepciones() {
		return flagIndicadorHistoricoExcepciones;
	}

	public void setFlagIndicadorHistoricoExcepciones(
			boolean flagIndicadorHistoricoExcepciones) {
		this.flagIndicadorHistoricoExcepciones = flagIndicadorHistoricoExcepciones;
	}

	public boolean isFlagIndicadorRechazoGestion() {
		return flagIndicadorRechazoGestion;
	}

	public void setFlagIndicadorRechazoGestion(boolean flagIndicadorRechazoGestion) {
		this.flagIndicadorRechazoGestion = flagIndicadorRechazoGestion;
	}

	public boolean isFlagIndicadorModificacionConsultora() {
		return flagIndicadorModificacionConsultora;
	}

	public void setFlagIndicadorModificacionConsultora(
			boolean flagIndicadorModificacionConsultora) {
		this.flagIndicadorModificacionConsultora = flagIndicadorModificacionConsultora;
	}

	public boolean isFlagIndicadorExcepcionValidacion() {
		return flagIndicadorExcepcionValidacion;
	}

	public void setFlagIndicadorExcepcionValidacion(
			boolean flagIndicadorExcepcionValidacion) {
		this.flagIndicadorExcepcionValidacion = flagIndicadorExcepcionValidacion;
	}

	public boolean isFlagIndicadorMensajeAdicionalError() {
		return flagIndicadorMensajeAdicionalError;
	}

	public void setFlagIndicadorMensajeAdicionalError(
			boolean flagIndicadorMensajeAdicionalError) {
		this.flagIndicadorMensajeAdicionalError = flagIndicadorMensajeAdicionalError;
	}

	public boolean isFlagIndicadorVisualizarMotivoGestion() {
		return flagIndicadorVisualizarMotivoGestion;
	}

	public void setFlagIndicadorVisualizarMotivoGestion(
			boolean flagIndicadorVisualizarMotivoGestion) {
		this.flagIndicadorVisualizarMotivoGestion = flagIndicadorVisualizarMotivoGestion;
	}

	public boolean isFlagIndicadorForzarValidacion() {
		return flagIndicadorForzarValidacion;
	}

	public void setFlagIndicadorForzarValidacion(
			boolean flagIndicadorForzarValidacion) {
		this.flagIndicadorForzarValidacion = flagIndicadorForzarValidacion;
	}
	

}
