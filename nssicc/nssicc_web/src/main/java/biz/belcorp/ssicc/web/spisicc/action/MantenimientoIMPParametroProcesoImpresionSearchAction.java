package biz.belcorp.ssicc.web.spisicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.model.ParametroProImpresion;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPParametroProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPParametroProcesoImpresionForm;
import biz.belcorp.ssicc.web.spisicc.form.MantenimientoIMPParametroProcesoImpresionSearchForm;

/**
 * The Class MantenimientoIMPParametroProcesoImpresionSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 29/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoIMPParametroProcesoImpresionSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 2066143489817552514L;
	private List procesoImpresionList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoIMPParametroProcesoImpresionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoIMPParametroProcesoImpresionSearchForm searchForm = new MantenimientoIMPParametroProcesoImpresionSearchForm();
		searchForm.setCodigoProceso(Constants.FORMATEAR_TODOS);
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		try {
			ProcesoImpresionService impresionService = (ProcesoImpresionService)getBean("spisicc.procesoImpresionService");	
			
		    this.procesoImpresionList = impresionService.getProcesosImpresion(null);
		    find();
		    this.mostrarBotonConsultar = false;
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));		
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {	
		MantenimientoIMPParametroProcesoImpresionService service = (MantenimientoIMPParametroProcesoImpresionService) getBean("spisicc.mantenimientoIMPParametroProcesoImpresionService");		
		MantenimientoIMPParametroProcesoImpresionSearchForm f = (MantenimientoIMPParametroProcesoImpresionSearchForm) this.formBusqueda;
		ParametroProImpresion bean = new ParametroProImpresion();
    	BeanUtils.copyProperties(bean,f);		
    	f.setCodigoProceso(f.getCodigoProceso()==null? "T":f.getCodigoProceso());
        if (f.getCodigoProceso().equals(Constants.FORMATEAR_TODOS)) {
        	bean.setCodigoParametro(null);
        	bean.setCodigoProceso(null);
		}
		List resultado = service.getParametroProImp(bean);		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {			
			ParametroProImpresion parametroImpresion = (ParametroProImpresion) this.beanRegistroSeleccionado;	
			MantenimientoIMPParametroProcesoImpresionService service = (MantenimientoIMPParametroProcesoImpresionService) getBean("spisicc.mantenimientoIMPParametroProcesoImpresionService");
			ParametroProImpresion baspp = new ParametroProImpresion();
			baspp.setCodigoProceso(parametroImpresion.getCodigoProceso());
			baspp.setCodigoParametro(parametroImpresion.getCodigoParametro());
			List list = service.getParametroProImp(baspp);
			ParametroProImpresion baspp1 = (ParametroProImpresion) list.get(0);
			baspp1.setDeletedBy(usuario.getLogin());
			service.deleteParametroProImp(baspp1);

			this.getResourceMessage("mantenimientoIMPParametroProcesoImpresionForm.deleted");
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));		
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoIMPParametroProcesoImpresionList";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoIMPParametroProcesoImpresionForm f = (MantenimientoIMPParametroProcesoImpresionForm) this.formMantenimiento;
		MantenimientoIMPParametroProcesoImpresionService service = (MantenimientoIMPParametroProcesoImpresionService) 
																	getBean("spisicc.mantenimientoIMPParametroProcesoImpresionService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ParametroProImpresion pp = new ParametroProImpresion();
		try {
			BeanUtils.copyProperties(pp, f);
			AuditInfo audi = usuario.getAuditInfo();
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				audi.setUpdatedBy(usuario.getLogin());
				pp.setAuditInfo(audi);
				service.updateParametroProImp(pp);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				 audi.setCreatedBy(usuario.getLogin());
				 pp.setAuditInfo(audi);
				 service.insertParametroProImp(pp);
			}
		}catch (Exception e) {
            throw new Exception(e.getMessage());
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoIMPParametroProcesoImpresionForm mantenimientoForm = new MantenimientoIMPParametroProcesoImpresionForm();		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			MantenimientoIMPParametroProcesoImpresionService service = (MantenimientoIMPParametroProcesoImpresionService) 
																			getBean("spisicc.mantenimientoIMPParametroProcesoImpresionService");
			
			ParametroProImpresion baspp = (ParametroProImpresion) this.beanRegistroSeleccionado;
	
			List list = service.getParametroProImp(baspp);
			ParametroProImpresion baspp1 = (ParametroProImpresion) list.get(0);
			
			BeanUtils.copyProperties(mantenimientoForm, baspp1);			
			
			mantenimientoForm.setNewRecord(false);
		} else {
			mantenimientoForm.setCodigoParametro(StringUtils.EMPTY);
			mantenimientoForm.setCodigoProceso(StringUtils.EMPTY);
			mantenimientoForm.setValorParametro(StringUtils.EMPTY);
			mantenimientoForm.setNombreParametro(StringUtils.EMPTY);
			mantenimientoForm.setEstado(Constants.ESTADO_ACTIVO);
			mantenimientoForm.setObsParametro(StringUtils.EMPTY);
			mantenimientoForm.setNewRecord(true);
		}					
		return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoIMPParametroProcesoImpresionForm mantenimientoForm = (MantenimientoIMPParametroProcesoImpresionForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoIMPParametroProcesoImpresionForm.add";
		} else {
			return "mantenimientoIMPParametroProcesoImpresionForm.updated";
		}	
	}	

	/**
	 * @return the procesoImpresionList
	 */
	public List getProcesoImpresionList() {
		return procesoImpresionList;
	}

	/**
	 * @param procesoImpresionList the procesoImpresionList to set
	 */
	public void setProcesoImpresionList(List procesoImpresionList) {
		this.procesoImpresionList = procesoImpresionList;
	}

}
