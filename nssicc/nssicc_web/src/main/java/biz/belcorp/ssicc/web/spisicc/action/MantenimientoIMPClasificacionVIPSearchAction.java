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

import biz.belcorp.ssicc.dao.spisicc.model.ClasificacionVIP;
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

import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPClasificacionVIPForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPClasificacionVIPSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasClasificacionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasClasificacionSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionSearchForm;

/**
 * The Class MantenimientoIMPEtiquetasClasificacionSearchAction.
 * 
 * @autor: Danny Santa Cruz R.
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoIMPClasificacionVIPSearchAction extends
		BaseMantenimientoSearchAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5346734411240223048L;
	/**
	 * 
	 */
	private List impTipoClasificacionList;
	private List impClasificacionVIPList;
	private List impClasificacionList;
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoIMPClasificacionVIPList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub

		return "mantenimientoIMPClasificacionVIPForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoIMPClasificacionVIPSearchForm formSearch = new MantenimientoIMPClasificacionVIPSearchForm();
		return formSearch;
	}

	@Override
	protected List setFindAttributes() throws Exception {
	
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoIMPClasificacionVIPSearchAction");
		}

		MantenimientoIMPClasificacionVIPSearchForm f = (MantenimientoIMPClasificacionVIPSearchForm) this.formBusqueda;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
	
		Map params = BeanUtils.describe(f);
		
		params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
		params.put("oidClasificacion", f.getOidClasificacion());
		params.put("oidValorLAR", f.getValorLAR());
		params.put("oidNumeroOCS", f.getNumeroOCS());
		
		List clasificacionVIP = (List)service.getClasificacionVIPByCriteria(params);
		impClasificacionVIPList=clasificacionVIP;
		//this.setImpClasificacionVIPList(clasificacionVIP);
		
		return clasificacionVIP;
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
				ClasificacionVIP clasificacionVIP = service.getClasificacionVIP(criteria);
				service.deleteClasificacionVIP(clasificacionVIP);
			}

			mensaje = getResourceMessage(
					"mantenimientoIMPClasificacionVIPForm.deleted",
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoIMPClasificacionVIPAction");
		}
		
		String mensaje = "";
		MantenimientoIMPClasificacionVIPForm f = (MantenimientoIMPClasificacionVIPForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		boolean isNew = f.isNewRecord();
		ClasificacionVIP clasificacionVIP = new ClasificacionVIP();
		BeanUtils.copyProperties(clasificacionVIP, f);

		try {
			// agregamos los mensajes de exito
			if (isNew) {
				// verificamos que no exista el registro
				Map criteria = new HashMap();
				criteria.put("oid", clasificacionVIP.getOid());
				ClasificacionVIP etiquet = service.getClasificacionVIP(criteria);
				if (etiquet == null) {
					service.insertClasificacionVIP(clasificacionVIP, usuario);
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPClasificacionVIPForm.created"));

				} else {
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPClasificacionVIPForm.error.existe"));

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
		MantenimientoIMPClasificacionVIPForm f = new MantenimientoIMPClasificacionVIPForm();
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
						ClasificacionVIP clasificacionVIP = service.getClasificacionVIP(criteria);
						//EtiquetaClasificacion etiqueta = service.getEtiquetaClasificacion(criteria);
						BeanUtils.copyProperties(f, clasificacionVIP);
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
				log.debug("setViewAttributes - MantenimientoIMPClasificacionVIPSearchAction");
			}

			MantenimientoIMPClasificacionVIPSearchForm f = (MantenimientoIMPClasificacionVIPSearchForm) formBusqueda;
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

			Map criteria = new HashMap();
			criteria.put("codigoIdioma", mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		    
			impTipoClasificacionList=new ArrayList();   
			impTipoClasificacionList=service.getTipoClasificacionList(criteria);
		    impClasificacionList=new ArrayList();   
		    impClasificacionVIPList=new ArrayList();
		    //setImpClasificacionVIPList(new ArrayList());
		    this.mostrarBotonConsultar=false;
		    this.mostrarBotonModificar=false;
		    f.setOidClasificacion(null);
		    f.setOidTipoClasificacion(null);
		    f.setValorLAR(null);
		    f.setNumeroOCS(null);
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
			log.debug("setAddAttributes - MantenimientoIMPClasificacionVIPAction");
		}
		MantenimientoIMPClasificacionVIPForm f = (MantenimientoIMPClasificacionVIPForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
	    
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		

		impTipoClasificacionList=service.getTipoClasificacionList(criteria);
	    
	    impClasificacionList=new ArrayList();
	    impClasificacionVIPList=new ArrayList();
	  //  impTipoEtiquetaList=service.getEtiquetasList();
	    
	    f.setOidClasificacion(null);
	    f.setOidTipoClasificacion(null);
	    f.setValorLAR(null);
	    f.setNumeroOCS(null);
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


	private List getImpClasificacionVIPList() {
		return impClasificacionVIPList;
	}

	private void setImpClasificacionVIPList(List impClasificacionVIPList) {
		this.impClasificacionVIPList = impClasificacionVIPList;
	}



	

}
