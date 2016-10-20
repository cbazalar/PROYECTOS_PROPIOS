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
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.model.ParametroProImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPParametroProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPParametroProcesoImpresionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPParametroProcesoImpresionSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPProcesoImpresionSearchForm;

/**
 * The Class MantenimientoIMPParametroProcesoImpresionSearchAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 29/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoIMPProcesoImpresionSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3232125560695212385L;
	private List impProcesoImpresionList;
	private List procesoImpresionList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoIMPProcesoImpresionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub

		return "mantenimientoIMPProcesoImpresionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoIMPProcesoImpresionSearchForm formSearch = new MantenimientoIMPProcesoImpresionSearchForm();
		//formSearch.setCodigoProceso(Constants.FORMATEAR_TODOS);
		return formSearch;

	}

	@Override
	protected List setFindAttributes() throws Exception {

		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoIMPProcesoImpresionSearchAction");
		}

		MantenimientoIMPProcesoImpresionSearchForm f = (MantenimientoIMPProcesoImpresionSearchForm) this.formBusqueda;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

		String descripcionProceso = StringUtils.isBlank(f
				.getDescripcionProceso()) ? "" : StringUtils.trim(f
				.getDescripcionProceso());

		Map params = BeanUtils.describe(f);

		if (f.getCodigoProceso().equals(Constants.FORMATEAR_TODOS)) {
			params.put("codigoProceso", null);
		} else {
			params.put("codigoProceso", f.getCodigoProceso());
		}

		params.put("descripcionProceso", descripcionProceso);

		List procesos = (List) service.getProcesosImpresionByCriteria(params);
		// request.getSession().setAttribute(Constants.IMP_PROCESO_IMPRESION_LIST,
		// procesos);
		setImpProcesoImpresionList(procesos);

		return procesos;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		String mensaje = "";
		try {
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
			Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
			HashMap map = (HashMap) this.beanRegistroSeleccionado;
			String id = map.get("codigoProceso").toString();

			if (StringUtils.isNotBlank(id)) {
				Map criteria = new HashMap();
				criteria.put("codigoProceso", id);
				ProceImpresion proceImpresion = service
						.getProcesosImpresion(criteria);
				proceImpresion.setIndicadorEstado(Constants.NUMERO_CERO);
				service.updateProcesoImpresion(proceImpresion, usuario);
			}
		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail",
					new Object[] { error }));
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		
		return "mantenimientoIMPProcesoImpresionForm.deleted";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoIMPProcesoImpresionAction");
		}
		String mensaje = "";		
		MantenimientoIMPProcesoImpresionForm f = (MantenimientoIMPProcesoImpresionForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		boolean isNew = f.isNewRecord();

		ProceImpresion proceso = new ProceImpresion();

		BeanUtils.copyProperties(proceso, f);

		try {
			// agregamos los mensajes de exito
			if (isNew) {
				// verificamos que no exista el registro
				Map criteria = new HashMap();
				criteria.put("codigoProceso", proceso.getCodigoProceso());
				ProceImpresion process = service.getProcesosImpresion(criteria);
				if (process == null) {
					service.insertProcesoImpresion(proceso, usuario);					
				} else {
					addInfo("Mensaje", getResourceMessage("mantenimientoIMPProcesoImpresionForm.error.existe"));
					return false;
				}
			} else {
				service.updateProcesoImpresion(proceso, usuario);

			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			mensaje = getResourceMessage("errors.invalid.id",
					new Object[] { codigo });
			addInfo("Mensaje", mensaje);

			return false;
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			mensaje = getResourceMessage("errors.invalid.id",
					new Object[] { descripcion });
			addInfo("Mensaje", mensaje);

			return false;
		}

		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		
		if(StringUtils.equals(this.accion, "NUEVO")){
			return "mantenimientoIMPProcesoImpresionForm.created";
		}
		
		if(StringUtils.equals(this.accion, "MODIFICAR")){
			return "mantenimientoIMPProcesoImpresionForm.updated";
		}
		
		
		return null;
		
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoIMPProcesoImpresionAction");
		}
		MantenimientoIMPProcesoImpresionForm f = new MantenimientoIMPProcesoImpresionForm();
		if (!this.accion.equals(this.ACCION_NUEVO)) {

			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

			HashMap map = (HashMap) this.beanRegistroSeleccionado;
			if (map != null) {

				String id = map.get("codigoProceso").toString();

				f.setNewRecord(false);
				if (StringUtils.isNotBlank(id)) {
					Map criteria = new HashMap();
					criteria.put("codigoProceso", id);
					ProceImpresion proceso = service
							.getProcesosImpresion(criteria);
					BeanUtils.copyProperties(f, proceso);
				}

			}
		} else {

			f.setCodigoProceso(StringUtils.EMPTY);
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
				log.debug("setViewAttributes - MantenimientoIMPProcesoImpresionSearchAction");
			}
			this.mostrarBotonConsultar = false; 
			ProcesoImpresionService impresionService = (ProcesoImpresionService) getBean("spisicc.procesoImpresionService");
			MantenimientoIMPProcesoImpresionSearchForm f = (MantenimientoIMPProcesoImpresionSearchForm) formBusqueda;

			List list = impresionService.getProcesosImpresion(null);
			// session.removeAttribute(Constants.PROCESO_IMPRESION_LIST);
			// session.setAttribute(Constants.PROCESO_IMPRESION_LIST, list);
			this.procesoImpresionList = list;
			this.impProcesoImpresionList = list;
			// session.removeAttribute(Constants.IMP_PROCESO_IMPRESION_LIST);
			// session.setAttribute(Constants.IMP_PROCESO_IMPRESION_LIST, new
			// ArrayList());
			// setProcesoImpresionList(new List());
			// setImpProcesoImpresionList(new List());
			// f.setCodigoProceso(null);
			// f.setDescripcionProceso(null);
		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();

			// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
			// "errors.detail",error));
			mensaje = getResourceMessage("errors.detail",
					new Object[] { error });
			addInfo("Mensaje", mensaje);

		}
	}

	protected void setAddAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setAddAttributes - MantenimientoIMPProcesoImpresionAction");
		}
		MantenimientoIMPProcesoImpresionForm f = (MantenimientoIMPProcesoImpresionForm) formMantenimiento;

	}

	protected String getSaveForward() {
		return "search";
	}

	/**
	 * @return the procesoImpresionList
	 */
	public List getProcesoImpresionList() {
		return procesoImpresionList;
	}

	/**
	 * @param procesoImpresionList
	 *            the procesoImpresionList to set
	 */
	public void setProcesoImpresionList(List procesoImpresionList) {
		this.procesoImpresionList = procesoImpresionList;
	}

	public List getImpProcesoImpresionList() {
		return impProcesoImpresionList;
	}

	public void setImpProcesoImpresionList(List impProcesoImpresionList) {
		this.impProcesoImpresionList = impProcesoImpresionList;
	}

}
