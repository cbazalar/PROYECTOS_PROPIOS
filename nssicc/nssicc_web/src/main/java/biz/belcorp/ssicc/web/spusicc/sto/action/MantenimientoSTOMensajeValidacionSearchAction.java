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
import biz.belcorp.ssicc.dao.spusicc.sto.model.MensajeValidacionSTO;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOMensajeValidacionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOMensajeValidacionSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSTOMensajeValidacionSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 7216772989577392808L;
	
	private List stoTipoDocumentoValidacionesList;
	private LabelValue[] listaValidaciones;
	private LabelValue[] listaValidaForm;
	private boolean indTipoMensaje;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {		
		return "mantenimientoSTOMensajeValidacionList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoSTOMensajeValidacionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOMensajeValidacionSearchForm searchForm = new MantenimientoSTOMensajeValidacionSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoSTOMensajeValidacionSearchForm f = (MantenimientoSTOMensajeValidacionSearchForm) this.formBusqueda;
		MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
		
		Map criteria = BeanUtils.describe(f);		
		List mensajeValidacionesSTOList = (List)service.getMensajeValidacionListSTO(criteria);
		//request.getSession().setAttribute(Constants.STO_MENSAJE_VALIDACION_LIST, mensajeValidacionesSTOList);
		
		return mensajeValidacionesSTOList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Map elemento=(Map)this.beanRegistroSeleccionado;
		
		String id = elemento.get("codigo").toString();		
		if (id != null) {				
			String[] arrayId = StringUtils.split(id, "|");
			String codigoMensaje = arrayId[0];
			String codigoValidacion = arrayId[1];
				
				Map criteria = new HashMap();
				criteria.put("codigoMensaje", codigoMensaje); 
				criteria.put("codigoValidacion", codigoValidacion); 
				
				MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
				service.deleteMensajeValidacionSTO(criteria);				
				return true;
			
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		MantenimientoSTOMensajeValidacionForm f = (MantenimientoSTOMensajeValidacionForm) this.formMantenimiento;
		MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");
        if(this.indTipoMensaje)
        	f.setIndicadorTipoMensaje(Constants.NUMERO_UNO);
        else
        	f.setIndicadorTipoMensaje(Constants.NUMERO_CERO);
        
        if(StringUtils.isBlank(f.getCodigoIdioma()))
        	f.setCodigoIdioma(" ");
        
		boolean isNew = f.isNewRecord();		
		MensajeValidacionSTO mensajeValidacionSto = new MensajeValidacionSTO();		
		BeanUtils.copyProperties(mensajeValidacionSto, f);
		
		 try {
			 Map params = BeanUtils.describe(f);
			 List lista = service.getMensajeValidacionListSTO(params);
			 
			 if(isNew){
				 if(lista != null && lista.size() > 0)	    			  
	        		throw new Exception(this.getResourceMessage("mantenimientoSTOMensajeValidacionForm.error.duplicado"));
	    		else
	        		service.insertMensajeValidacionSTO(mensajeValidacionSto, usuario);	       			 
			 }else{				 
				 boolean actualizar = true;
				 if(lista != null && lista.size() > 0){
					 
	    			if(lista.size() == 1){	    				
	    				String oid = MapUtils.getString((Map)lista.get(0), "codigo", "");
	    				if(!StringUtils.equals(oid, mensajeValidacionSto.getCodigoMensaje()+"|"+mensajeValidacionSto.getCodigoValidacion()))	    					
	    					actualizar = false;    					
	    				else	    					
	    					actualizar = true;   					
	    			}else	    				
	    					actualizar = false;	    				
	    			}

	    			if(actualizar)
	        			service.updateMensajeValidacionSTO(mensajeValidacionSto, usuario);	
	        		else   		
	        			throw new Exception(this.getResourceMessage("mantenimientoSTOMensajeValidacionForm.error.duplicado")); 	
	    	 }			 
		 }
	     catch (InvalidIdentifierException iie) {
	            String codigo = iie.getIdentifier().toString();	          
	            throw new Exception(this.getResourceMessage("errors.invalid.id",new Object[]{codigo}));
	     }
	     catch (InvalidDescriptionException ide) {
	            String descripcion = ide.getDescription();	            
	            throw new Exception(this.getResourceMessage("errors.invalid.description",new Object[]{descripcion }));
	     }
					
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map elemento=(Map)this.beanRegistroSeleccionado;		
		
		MantenimientoSTOMensajeValidacionForm f = new MantenimientoSTOMensajeValidacionForm();
		MantenimientoSTOValidacionesService service = (MantenimientoSTOValidacionesService)getBean("spusicc.mantenimientoSTOValidacionesService");

		String codigoPais = pais.getCodigo();		
		f.setTipoDocumento("");
		f.setCodigoValidacion("");
		f.setCodigoPais(codigoPais);
		this.listaValidaForm=null;
		this.indTipoMensaje=false;
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = elemento.get("codigo").toString();							
			if (id != null && codigoPais != null) {					
				
				String[] arrayId = StringUtils.split(id, "|");
				String codigoMensaje = arrayId[0];
				String codigoValidacion = arrayId[1];
				
				MensajeValidacionSTO mensajeValidacionSTO = new MensajeValidacionSTO();
				mensajeValidacionSTO.setCodigoMensaje(codigoMensaje);
				mensajeValidacionSTO.setCodigoValidacion(codigoValidacion);
				
				mensajeValidacionSTO = service.getMensajeValidacionSTO(mensajeValidacionSTO);
				
				if(mensajeValidacionSTO!=null)
					BeanUtils.copyProperties(f, mensajeValidacionSTO);
				
				f.setNewRecord(false);					
				if(StringUtils.equals(f.getIndicadorTipoMensaje(), Constants.NUMERO_UNO))
					this.indTipoMensaje=true;
				else
					this.indTipoMensaje=false;
			}
		}
		return f;		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOMensajeValidacionSearchForm f = (MantenimientoSTOMensajeValidacionSearchForm) this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		this.stoTipoDocumentoValidacionesList=procesoSTOEjecucionValidacionesService.getTipoDocumentosDigitList();		
		f.setTipoDocumento("");
		f.setCodigoValidacion("");
		f.setCodigoPais(pais.getCodigo());
		this.mostrarBotonConsultar=false;	
	}
	
	/**
	 * Carga validaciones
	 */
	public void loadValidaciones(ValueChangeEvent val){
		try {
			MantenimientoSTOMensajeValidacionSearchForm f = (MantenimientoSTOMensajeValidacionSearchForm) this.formBusqueda;
			String accion=val.getNewValue().toString();
			if(StringUtils.isNotBlank(accion)){
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.listaValidaciones=ajax.getValidaciones(accion, f.getCodigoPais());
				f.setCodigoValidacion("");
			}else{
				this.listaValidaciones=null;
				f.setCodigoValidacion("");
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}	
	}
	
	/**
	 * @param val
	 */
	public void loadValidaForm(ValueChangeEvent val){
		try {
			MantenimientoSTOMensajeValidacionForm f = (MantenimientoSTOMensajeValidacionForm)this.formMantenimiento;
			String accion=val.getNewValue().toString();
			if(StringUtils.isNotBlank(accion)){
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.listaValidaForm=ajax.getValidaciones(accion, f.getCodigoPais());
				f.setCodigoValidacion("");
				f.setCodigoMensaje("");	
			}else{
				this.listaValidaForm=null;
				f.setCodigoValidacion("");
				f.setCodigoMensaje("");	
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @param val
	 */
	public void obtenerCodigoMensaje(ValueChangeEvent val){
		try {
			MantenimientoSTOMensajeValidacionForm f = (MantenimientoSTOMensajeValidacionForm)this.formMantenimiento;
			String accion=val.getNewValue().toString();
			if(StringUtils.isNotBlank(accion)){
				AjaxService ajax = (AjaxService) getBean("ajaxService");			
				String valor=ajax.getCodigoMensaje(accion);
				f.setCodigoMensaje(valor);			
			}else{
				f.setCodigoMensaje("");			
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
			
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoSTOMensajeValidacionForm f = (MantenimientoSTOMensajeValidacionForm)this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if (isNew) 
			return "mantenimientoSTOMensajeValidacionForm.created";
		 else 
			return "mantenimientoSTOMensajeValidacionForm.updated";
		
	}

	/**
	 * @return
	 */
	public List getStoTipoDocumentoValidacionesList() {
		return stoTipoDocumentoValidacionesList;
	}

	/**
	 * @param stoTipoDocumentoValidacionesList
	 */
	public void setStoTipoDocumentoValidacionesList(
			List stoTipoDocumentoValidacionesList) {
		this.stoTipoDocumentoValidacionesList = stoTipoDocumentoValidacionesList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getListaValidaciones() {
		return listaValidaciones;
	}

	/**
	 * @param listaValidaciones
	 */
	public void setListaValidaciones(LabelValue[] listaValidaciones) {
		this.listaValidaciones = listaValidaciones;
	}

	/**
	 * @return
	 */
	public LabelValue[] getListaValidaForm() {
		return listaValidaForm;
	}

	/**
	 * @param listaValidaForm
	 */
	public void setListaValidaForm(LabelValue[] listaValidaForm) {
		this.listaValidaForm = listaValidaForm;
	}

	/**
	 * @return
	 */
	public boolean isIndTipoMensaje() {
		return indTipoMensaje;
	}

	/**
	 * @param indTipoMensaje
	 */
	public void setIndTipoMensaje(boolean indTipoMensaje) {
		this.indTipoMensaje = indTipoMensaje;
	}
}