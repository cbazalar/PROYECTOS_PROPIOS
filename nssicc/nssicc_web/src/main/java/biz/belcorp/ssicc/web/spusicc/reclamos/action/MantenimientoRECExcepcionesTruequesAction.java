package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECExcepcionesTruequesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECExcepcionesTruequesForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRECExcepcionesTruequesAction extends BaseMantenimientoSearchAbstractAction{
	
	private static final long serialVersionUID = -3146579580513553107L;
	
	private List recExcepcionesTruequesList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECExcepcionesTruequesForm searchForm = new MantenimientoRECExcepcionesTruequesForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoRECExcepcionesTruequesForm f = (MantenimientoRECExcepcionesTruequesForm) this.formBusqueda;
		MantenimientoRECExcepcionesTruequesService service = (MantenimientoRECExcepcionesTruequesService) getBean("spusicc.mantenimientoRECExcepcionesTruequesService");
	
				
		Map criteria = new HashMap();
		criteria.put("codigoSapDevuelve", f.getCodigoSapDevuelve());
		criteria.put("codigoSapEnvia", f.getCodigoSapEnvia());
		
		List resultado = service.getExcepcionesTrueques(criteria);
		//this.recExcepcionesTruequesList=resultado;		
		return resultado;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		Map sistemaBusqueda= (Map)this.beanRegistroSeleccionado;
		String id =sistemaBusqueda.get("oid").toString();
		
		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			try {			
				MantenimientoRECExcepcionesTruequesService service = (MantenimientoRECExcepcionesTruequesService) getBean("spusicc.mantenimientoRECExcepcionesTruequesService");
				
				Map criteria = new HashMap();				
				criteria.put("oid", id);
				criteria.put("usuarioEliminacion", usuario.getLogin());				
				service.deleteExcepcionTrueque(criteria);				
			}catch (Exception e) {
				this.addError("Error : ", this.obtieneMensajeErrorException(e));
			}
			return true;
		}else{
			return false;
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento(){		
		MantenimientoRECExcepcionesTruequesForm f = (MantenimientoRECExcepcionesTruequesForm) this.formBusqueda;		
		if(StringUtils.isBlank(f.getCodigoSapDevuelve())){
			return "Debe de ingresar el código SAP que devuelve";
		}else{
			if(StringUtils.isBlank(f.getDescripcionCodigoSapDevuelve()))
				return "Debe de ingresar un código SAP válido";
		}
		
		if(StringUtils.isBlank(f.getCodigoSapEnvia())){
			return "Debe de ingresar el código SAP que envía";
		}else{
			if(StringUtils.isBlank(f.getDescripcionCodigoSapEnvia()))
				return "Debe de ingresar un código SAP válido";
		}
		return null;
	}

	public void guardar(ActionEvent act) {
		
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		try{
			String mensajeValidacion=setValidarMantenimiento();
			if (StringUtils.isNotBlank(mensajeValidacion)) {
				this.addInfo(
						"Info : ",
						mensajeValidacion);
				return;
			}
			this.recExcepcionesTruequesList = new ArrayList();
			MantenimientoRECExcepcionesTruequesForm f = (MantenimientoRECExcepcionesTruequesForm) this.formBusqueda;
			MantenimientoRECExcepcionesTruequesService service = (MantenimientoRECExcepcionesTruequesService) getBean ("spusicc.mantenimientoRECExcepcionesTruequesService");
			
			Map criteria = new HashMap();
			criteria.put("codigoSapDevuelve", f.getCodigoSapDevuelve());
			criteria.put("codigoSapEnvia", f.getCodigoSapEnvia());
			criteria.put("usuarioDigitacion", usuario.getLogin());
			
			service.insertExcepcionTrueque(criteria);			
			List resultado = service.getExcepcionesTrueques(criteria);
			this.mostrarListaBusqueda = true;
			this.recExcepcionesTruequesList=resultado;	
			this.listaBusqueda = this.recExcepcionesTruequesList;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			f.setCodigoSapDevuelve("");
			f.setCodigoSapEnvia("");
			f.setDescripcionCodigoSapDevuelve("");
			f.setDescripcionCodigoSapEnvia("");
			this.addInfo("Info : ", this.getResourceMessage("mantenimientoRECExcepcionesTruequesForm.insert"));
		}
		catch(Exception e)
		{	
			this.addError("Error : " , this.getResourceMessage("mantenimientoRECExcepcionesTruequesForm.duplicated"));
		}	
	}
	
/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {	
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonSave=true;
		MantenimientoRECExcepcionesTruequesForm f = (MantenimientoRECExcepcionesTruequesForm) this.formBusqueda;		
		f.setCodigoSapDevuelve("");
		f.setCodigoSapEnvia("");
		
	}
	
	/**
	 * @throws Exception
	 */
	public void validaSapDevuelve() throws Exception{
		MantenimientoRECExcepcionesTruequesForm f = (MantenimientoRECExcepcionesTruequesForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String codigo = f.getCodigoSapDevuelve();
		if (!codigo.equals(null)) {
			LabelValue[] lista=ajax.getDescripcionByProducto(codigo);
			if(lista.length>0){
				String descripcion=lista[0].getLabel();
				f.setDescripcionCodigoSapDevuelve(descripcion);
			}			
		}else{
			f.setDescripcionCodigoSapDevuelve(null);
		}					
	}
	
	/**
	 * @throws Exception
	 */
	public void validaSapEnvia() throws Exception{
		MantenimientoRECExcepcionesTruequesForm f = (MantenimientoRECExcepcionesTruequesForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String codigo = f.getCodigoSapEnvia();
		if (!codigo.equals(null)) {
			LabelValue[] lista=ajax.getDescripcionByProducto(codigo);
			if(lista.length>0){
				String descripcion=lista[0].getLabel();
				f.setDescripcionCodigoSapEnvia(descripcion);
			}			
		}else{
			f.setDescripcionCodigoSapDevuelve(null);
		}					
	}

	/**
	 * @return
	 */
	public List getRecExcepcionesTruequesList() {
		return recExcepcionesTruequesList;
	}

	/**
	 * @param recExcepcionesTruequesList
	 */
	public void setRecExcepcionesTruequesList(List recExcepcionesTruequesList) {
		this.recExcepcionesTruequesList = recExcepcionesTruequesList;
	}
}