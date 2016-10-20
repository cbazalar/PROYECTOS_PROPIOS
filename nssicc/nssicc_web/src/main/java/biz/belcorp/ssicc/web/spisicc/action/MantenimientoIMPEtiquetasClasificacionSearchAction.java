package biz.belcorp.ssicc.web.spisicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.axis.transport.jms.MapUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;

import biz.belcorp.ssicc.dao.spisicc.model.Etiqueta;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaClasificacion;
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasClasificacionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasClasificacionSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionSearchForm;

/**
 * The Class MantenimientoIMPEtiquetasClasificacionSearchAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 29/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoIMPEtiquetasClasificacionSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3232125560695212385L;
	private List impEtiquetasClasificacionList;
	private List impTipoClasificacionList;

	private List impClasificacionList;
	private List impTipoEtiquetasList;
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoIMPEtiquetasClasificacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub

		return "mantenimientoIMPEtiquetasClasificacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoIMPEtiquetasClasificacionSearchForm formSearch = new MantenimientoIMPEtiquetasClasificacionSearchForm();
		return formSearch;
	}

	@Override
	protected List setFindAttributes() throws Exception {
	
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoIMPEtiquetasClasificacionSearchAction");
		}

		MantenimientoIMPEtiquetasClasificacionSearchForm f = (MantenimientoIMPEtiquetasClasificacionSearchForm) this.formBusqueda;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
	
		Map params = BeanUtils.describe(f);
		
		params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
		params.put("oidClasificacion", f.getOidClasificacion());
		params.put("oidEtiqueta", f.getOidEtiqueta());
		
		List etiquetasClasificacion = (List)service.getEtiquetaClasificacionByCriteria(params);
		this.impEtiquetasClasificacionList.clear();
		this.impEtiquetasClasificacionList=etiquetasClasificacion;
		
		return etiquetasClasificacion;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		String mensaje = "";
		try{
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
			Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
			HashMap map = (HashMap) this.beanRegistroSeleccionado;
			String id = map.get("oid").toString();

			if (StringUtils.isNotBlank(id)) {
				Map criteria = new HashMap();
				criteria.put("oid", id);
				EtiquetaClasificacion etiquetaClasificacion = service.getEtiquetaClasificacion(criteria);
				service.deleteEtiquetaClasificacion(etiquetaClasificacion);
			}

			mensaje = getResourceMessage(
					"mantenimientoIMPEtiquetasClasificacionForm.deleted",
					new Object[] { id });
			addInfo("Mensaje", mensaje);
			
		}catch(Exception e){
			
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));	
		}
		
		return true;
	}
	

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoIMPEtiquetasClasificacionAction");
		}
		
		String mensaje = "";
		MantenimientoIMPEtiquetasClasificacionForm f = (MantenimientoIMPEtiquetasClasificacionForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		boolean isNew = f.isNewRecord();
		EtiquetaClasificacion etiquetaClasificacion = new EtiquetaClasificacion();
		BeanUtils.copyProperties(etiquetaClasificacion, f);

		try {
			// agregamos los mensajes de exito
			if (isNew) {
				// verificamos que no exista el registro
				Map criteria = new HashMap();
				criteria.put("oid", etiquetaClasificacion.getOid());
				EtiquetaClasificacion etiquet = service.getEtiquetaClasificacion(criteria);
				if (etiquet == null) {
					service.insertEtiquetaClasificacion(etiquetaClasificacion, usuario);
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPEtiquetasClasificacionForm.created"));

				} else {
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPEtiquetasClasificacionForm.error.existe"));

					return false;
				}
			} 
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			mensaje = getResourceMessage("errors.invalid.id",
					new Object[] { codigo });
			addInfo("Mensaje", mensaje);

			return false;
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			mensaje = getResourceMessage("errors.invalid.description",
					new Object[] { descripcion });
			addInfo("Mensaje", mensaje);

			return false;
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoIMPEtiquetasClasificacionAction");
		}
		MantenimientoIMPEtiquetasClasificacionForm f = new MantenimientoIMPEtiquetasClasificacionForm();
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

			HashMap map = (HashMap) this.beanRegistroSeleccionado;
				
				if (map != null) 
				
				{

					String id = map.get("oid").toString();
				
					f.setNewRecord(false);
					if (StringUtils.isNotBlank(id)) {
						Map criteria = new HashMap();
						criteria.put("oid", id);
						EtiquetaClasificacion etiqueta = service.getEtiquetaClasificacion(criteria);
						BeanUtils.copyProperties(f, etiqueta);
				}
				
			}
		}else
		{
			
			f.setOid(StringUtils.EMPTY);
			f.setNewRecord(true);
			
		}
		

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		String mensaje = "";
		try {
			if (log.isDebugEnabled()) {
				log.debug("setViewAttributes - MantenimientoIMPEtiquetasSearchAction");
			}

			MantenimientoIMPEtiquetasClasificacionSearchForm f = (MantenimientoIMPEtiquetasClasificacionSearchForm) formBusqueda;
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

			Map criteria = new HashMap();
			criteria.put("codigoIdioma", mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		    
			impTipoClasificacionList=new ArrayList();   
			impTipoClasificacionList=service.getTipoClasificacionList(criteria);
		    impClasificacionList=new ArrayList();   
		    impTipoEtiquetasList=service.getEtiquetasList();
		    impEtiquetasClasificacionList=new ArrayList();
		    this.mostrarBotonConsultar=false;
		    this.mostrarBotonModificar=false;
		    f.setOidClasificacion(null);
		    f.setOidTipoClasificacion(null);
		    f.setOidEtiqueta(null);
		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
				error = e.getLocalizedMessage();
				if (StringUtils.isBlank(error))
			mensaje = getResourceMessage("errors.detail",
					new Object[] { error });
			addInfo("Mensaje", mensaje);

		}
	}

	protected void setAddAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setAddAttributes - MantenimientoIMPEtiquetasClasificacionAction");
		}
		MantenimientoIMPEtiquetasClasificacionForm f = (MantenimientoIMPEtiquetasClasificacionForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
	    
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		

		impTipoClasificacionList=service.getTipoClasificacionList(criteria);
	    
	    impClasificacionList=new ArrayList();
	    
	    impTipoEtiquetasList=service.getEtiquetasList();
	    
	    f.setOidClasificacion(null);
	    f.setOidTipoClasificacion(null);
	    f.setOidEtiqueta(null);
	}
	
	public void loadClasificacion(ValueChangeEvent val)
	{
		if(log.isDebugEnabled()){
			log.debug("loadClasificacion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String clasificacionListado = (String)val.getNewValue();
			MantenimientoIMPEtiquetasClasificacionSearchForm f = (MantenimientoIMPEtiquetasClasificacionSearchForm) formBusqueda;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(clasificacionListado.length()>0){
				impClasificacionList=Arrays.asList(ajax.getClasificacionByOidTipoClasificacionList(clasificacionListado));
				                             
			}else{
				impClasificacionList = new ArrayList();
			}
		}
	}
	
	
	protected String getSaveForward() {
		return "search";
	}

	public List getImpEtiquetasClasificacionList() {
		return impEtiquetasClasificacionList;
	}

	public void setImpEtiquetasClasificacionList(
			List impEtiquetasClasificacionList) {
		this.impEtiquetasClasificacionList = impEtiquetasClasificacionList;
	}

	
	public List getImpTipoClasificacionList() {
		return impTipoClasificacionList;
	}

	public void setImpTipoClasificacionList(List impTipoClasificacionList) {
		this.impTipoClasificacionList = impTipoClasificacionList;
	}

	public List getImpClasificacionList() {
		return impClasificacionList;
	}

	public void setImpClasificacionList(List impClasificacionList) {
		this.impClasificacionList = impClasificacionList;
	}

	public List getImpTipoEtiquetasList() {
		return impTipoEtiquetasList;
	}

	public void setImpTipoEtiquetasList(List impTipoEtiquetasList) {
		this.impTipoEtiquetasList = impTipoEtiquetasList;
	}

	

}
