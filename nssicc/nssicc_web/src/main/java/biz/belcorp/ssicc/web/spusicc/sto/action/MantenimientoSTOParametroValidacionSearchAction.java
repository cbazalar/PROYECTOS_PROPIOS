package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ParametroValidacion;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOParametroValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOParametroValidacionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOParametroValidacionSearchForm;

/**
 * The Class MantenimientoSTOParametroValidacionSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/02/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoSTOParametroValidacionSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -3888371094091050284L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOParametroValidacionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOParametroValidacionSearchForm searchForm = new MantenimientoSTOParametroValidacionSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		find();
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {														   
		MantenimientoSTOParametroValidacionService service = (MantenimientoSTOParametroValidacionService) getBean("spusicc.mantenimientoSTOParametroValidacionService");		
		MantenimientoSTOParametroValidacionSearchForm f = (MantenimientoSTOParametroValidacionSearchForm) this.formBusqueda;
		ParametroValidacion bean = new ParametroValidacion();
		if(f.getCodigoParametro() == null){
			f.setCodigoParametro(StringUtils.EMPTY);
		}
		String concat = "%".concat(f.getCodigoParametro()).concat("%");
    	BeanUtils.copyProperties(bean,f);		
    	bean.setCodigoParametro(concat);
       
		List resultado = service.getParametroValidacion(bean);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOParametroValidacionList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		try {		
			ParametroValidacion parametro = (ParametroValidacion) this.beanRegistroSeleccionado;
			
			MantenimientoSTOParametroValidacionService service = (MantenimientoSTOParametroValidacionService) 
																	getBean("spusicc.mantenimientoSTOParametroValidacionService");
			ParametroValidacion baspp = new ParametroValidacion();

			baspp.setCodigoParametro(parametro.getCodigoParametro());
			baspp.setCodigoPais(pais.getCodigo());
			
			List list = service.getParametroValidacion(baspp);
			ParametroValidacion baspp1 = (ParametroValidacion) list.get(0);
			baspp1.setDeletedBy(usuario.getLogin());
			
			service.deleteParametroValidacion(baspp1);
			this.getResourceMessage("mantenimientoSTOParametroValidacionForm.deleted");
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
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoSTOParametroValidacionForm f = (MantenimientoSTOParametroValidacionForm) this.formMantenimiento;
		MantenimientoSTOParametroValidacionService service = (MantenimientoSTOParametroValidacionService) 
																getBean("spusicc.mantenimientoSTOParametroValidacionService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ParametroValidacion pp = new ParametroValidacion();
		try {
			BeanUtils.copyProperties(pp, f);
			AuditInfo audi = usuario.getAuditInfo();
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				
				audi.setUpdatedBy(usuario.getLogin());
				pp.setAuditInfo(audi);
				service.updateParametroValidacion(pp);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}

				audi.setCreatedBy(usuario.getLogin());
				pp.setAuditInfo(audi);
				service.insertParametrValidacion(pp);
			}
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
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoSTOParametroValidacionForm f = new MantenimientoSTOParametroValidacionForm();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			ParametroValidacion parametro = (ParametroValidacion) this.beanRegistroSeleccionado;
			
			MantenimientoSTOParametroValidacionService service = (MantenimientoSTOParametroValidacionService) getBean("spusicc.mantenimientoSTOParametroValidacionService");
			ParametroValidacion baspp = new ParametroValidacion();
			baspp.setCodigoParametro(parametro.getCodigoParametro());
			baspp.setCodigoPais(pais.getCodigo());
			List list = service.getParametroValidacion(baspp);
			ParametroValidacion baspp1 = (ParametroValidacion) list.get(0);	
			
			BeanUtils.copyProperties(f, baspp1);
			
			f.setNewRecord(false);
		} else {
			f.setCodigoParametro(StringUtils.EMPTY);
			f.setValorParametro(StringUtils.EMPTY);
			f.setNombreParametro(StringUtils.EMPTY);
			f.setCodigoPais(pais.getCodigo());
			f.setNewRecord(true);
		}		
		
		return f;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoSTOParametroValidacionForm mantenimientoForm = (MantenimientoSTOParametroValidacionForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoSTOParametroValidacionForm.add";
		} else {
			return "mantenimientoSTOParametroValidacionForm.updated";
		}	
	}
	

}
