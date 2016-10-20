package biz.belcorp.ssicc.web.spisicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.axis.transport.jms.MapUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;

import biz.belcorp.ssicc.dao.spisicc.model.Etiqueta;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaEstatus;
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasEstatusForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasEstatusSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionSearchForm;

/**
 * The Class MantenimientoIMPEtiquetasEstatusSearchAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 29/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoIMPEtiquetasEstatusSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3103222007263694492L;
	/**
	 * 
	 */

	private List impEtiquetasEstatusList;
	private List impTipoEtiquetasList;
	private List impTipoEstatusList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoIMPEtiquetasEstatusList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub

		return "mantenimientoIMPEtiquetasEstatusForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoIMPEtiquetasEstatusSearchForm formSearch = new MantenimientoIMPEtiquetasEstatusSearchForm();
		return formSearch;

	}

	@Override
	protected List setFindAttributes() throws Exception {
	
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoIMPEtiquetasEstatusSearchAction");
		}

		MantenimientoIMPEtiquetasEstatusSearchForm f = (MantenimientoIMPEtiquetasEstatusSearchForm) this.formBusqueda;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		
		Map params = BeanUtils.describe(f);
		
		params.put("oidEtiqueta", f.getOidEtiqueta());
		params.put("oidEstatus", f.getOidEstatus());
		
		List etiquetas = (List)service.getEtiquetaEstatusByCriteria(params);
		this.impEtiquetasEstatusList.clear();
		this.impEtiquetasEstatusList= etiquetas;
		
		return etiquetas;
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
				EtiquetaEstatus etiquetaEstatus = service.getEtiquetaEstatus(criteria);
				service.deleteEtiquetaEstatus(etiquetaEstatus);
			}

			mensaje = getResourceMessage(
					"mantenimientoIMPEtiquetasEstatusForm.deleted",
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoIMPEtiquetasEstatusAction");
		}
		
		String mensaje = "";
		MantenimientoIMPEtiquetasEstatusForm f = (MantenimientoIMPEtiquetasEstatusForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		boolean isNew = f.isNewRecord();
		EtiquetaEstatus etiquetaEstatus = new EtiquetaEstatus();
		BeanUtils.copyProperties(etiquetaEstatus, f);

		try {
			// agregamos los mensajes de exito
			if (isNew) {
				// verificamos que no exista el registro
				Map criteria = new HashMap();
				criteria.put("oid", etiquetaEstatus.getOid());
				EtiquetaEstatus etiquet = service.getEtiquetaEstatus(criteria);
				if (etiquet == null) {
					service.insertEtiquetaEstatus(etiquetaEstatus, usuario);
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPEtiquetasEstatusForm.created"));

				} else {
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPEtiquetasEstatusForm.error.existe"));

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
			log.debug("setEditAttributes - MantenimientoIMPProcesoImpresionAction");
		}
		MantenimientoIMPEtiquetasEstatusForm f = new MantenimientoIMPEtiquetasEstatusForm();
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
			HashMap map = (HashMap) this.beanRegistroSeleccionado;
			if (map != null) {

				String id = map.get("oid").toString();
			
				f.setNewRecord(false);
				if (StringUtils.isNotBlank(id)) {
					Map criteria = new HashMap();
					criteria.put("oid", id);
					EtiquetaEstatus etiqueta = service.getEtiquetaEstatus(criteria);
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
				log.debug("setViewAttributes - MantenimientoIMPEtiquetasEstatusSearchAction");
			}

			MantenimientoIMPEtiquetasEstatusSearchForm f = (MantenimientoIMPEtiquetasEstatusSearchForm) formBusqueda;
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
			Map criteria = new HashMap();
			criteria.put("codigoIdioma",mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());			
			this.impEtiquetasEstatusList=new ArrayList();
			this.impTipoEtiquetasList=new ArrayList();
			this.impTipoEstatusList=new ArrayList();
			this.impTipoEtiquetasList=service.getEtiquetasList();
			this.impTipoEstatusList=service.getEstatusList(criteria);
						
			this.mostrarBotonModificar=false;
			this.mostrarBotonConsultar=false;
			
			f.setOidEstatus(null);
			f.setOidEtiqueta(null);
		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();

			mensaje = getResourceMessage("errors.detail",
					new Object[] { error });
			addInfo("Mensaje", mensaje);

		}
	}

	protected void setAddAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setAddAttributes - MantenimientoIMPEtiquetasEstatusAction");
		}
		MantenimientoIMPEtiquetasEstatusForm f = (MantenimientoIMPEtiquetasEstatusForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		this.impTipoEtiquetasList.clear();
		this.impTipoEstatusList.clear();
		this.impTipoEtiquetasList=service.getEtiquetasList();
		this.impTipoEstatusList=service.getEstatusList(criteria);
		

	}

	protected String getSaveForward() {
		return "search";
	}

	public List getImpEtiquetasEstatusList() {
		return impEtiquetasEstatusList;
	}

	public void setImpEtiquetasEstatusList(List impEtiquetasList) {
		this.impEtiquetasEstatusList = impEtiquetasList;
	}

	public List getImpTipoEtiquetasList() {
		return impTipoEtiquetasList;
	}

	public void setImpTipoEtiquetasList(List impTipoEtiquetasList) {
		this.impTipoEtiquetasList = impTipoEtiquetasList;
	}

	public List getImpTipoEstatusList() {
		return impTipoEstatusList;
	}

	public void setImpTipoEstatusList(List impTipoEstatusList) {
		this.impTipoEstatusList = impTipoEstatusList;
	}

	
	

}
