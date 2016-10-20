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
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPEtiquetasSearchForm;
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
public class MantenimientoIMPEtiquetasSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3232125560695212385L;
	private List impEtiquetasList;


	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoIMPEtiquetasList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub

		return "mantenimientoIMPEtiquetasForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoIMPEtiquetasSearchForm formSearch = new MantenimientoIMPEtiquetasSearchForm();
		return formSearch;

	}

	@Override
	protected List setFindAttributes() throws Exception {
	
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoIMPProcesoImpresionSearchAction");
		}

		MantenimientoIMPEtiquetasSearchForm f = (MantenimientoIMPEtiquetasSearchForm) this.formBusqueda;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

		String valorEtiqueta = StringUtils.isBlank(f.getValorEtiqueta())?"":StringUtils.trim(f.getValorEtiqueta());
		
		Map params = BeanUtils.describe(f);
		
		params.put("valorEtiqueta", valorEtiqueta);
		
		List etiquetas = (List)service.getEtiquetaByCriteria(params);
		this.impEtiquetasList= etiquetas;
		
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
				Etiqueta etiqueta = service.getEtiqueta(criteria);
				service.deleteEtiqueta(etiqueta);
			}

			mensaje = getResourceMessage(
					"mantenimientoIMPProcesoImpresionForm.deleted",
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoIMPProcesoImpresionAction");
		}
		
		String mensaje = "";
		ActionMessages messages = new ActionMessages();
		MantenimientoIMPEtiquetasForm f = (MantenimientoIMPEtiquetasForm) formMantenimiento;
		MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		boolean isNew = f.isNewRecord();
		Etiqueta etiqueta = new Etiqueta();
		BeanUtils.copyProperties(etiqueta, f);

		try {
			// agregamos los mensajes de exito
			if (isNew) {
				// verificamos que no exista el registro
				Map criteria = new HashMap();
				criteria.put("oid", etiqueta.getOid());
//				criteria.put("valorEtiqueta", etiqueta.getValorEtiqueta());
//				criteria.put("indicadorEstado", etiqueta.getIndicadorEstado());
				Etiqueta etiquet = service.getEtiqueta(criteria);
				if (etiquet == null) {
					service.insertEtiqueta(etiqueta, usuario);
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPEtiquetasForm.created"));

				} else {
					addInfo("Mensaje",
							getResourceMessage("mantenimientoIMPEtiquetasForm.error.existe"));

					return false;
				}
			} else {
				service.updateEtiqueta(etiqueta, usuario);
				addInfo("Mensaje",
						getResourceMessage("mantenimientoIMPProcesoImpresionForm.updated"));

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
		MantenimientoIMPEtiquetasForm f = new MantenimientoIMPEtiquetasForm();
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			
			MantenimientoIMPProcesoImpresionService service = (MantenimientoIMPProcesoImpresionService) getBean("sto.mantenimientoIMPProcesoImpresionService");

			HashMap map = (HashMap) this.beanRegistroSeleccionado;
			if (map != null) {

				String id = map.get("oid").toString();
			
				f.setNewRecord(false);
				if (StringUtils.isNotBlank(id)) {
					Map criteria = new HashMap();
					criteria.put("oid", id);
					Etiqueta etiqueta = service.getEtiqueta(criteria);
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

			MantenimientoIMPEtiquetasSearchForm f = (MantenimientoIMPEtiquetasSearchForm) formBusqueda;
			
			this.impEtiquetasList=new ArrayList();
			this.mostrarBotonConsultar=false;

			f.setValorEtiqueta(null);
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
			log.debug("setAddAttributes - MantenimientoIMPEtiquetasAction");
		}
		MantenimientoIMPEtiquetasForm f = (MantenimientoIMPEtiquetasForm) formMantenimiento;

	}

	protected String getSaveForward() {
		return "search";
	}

	public List getImpEtiquetasList() {
		return impEtiquetasList;
	}

	public void setImpEtiquetasList(List impEtiquetasList) {
		this.impEtiquetasList = impEtiquetasList;
	}

	
	

}
