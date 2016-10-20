package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.model.RolDirectorio;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONRolForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONRolSearchForm;

/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoZONRolAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5123247071118241544L;
	
	private List zonRolList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoZONRolList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoZONRolForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoZONRolSearchForm searchForm = new MantenimientoZONRolSearchForm();
		
		return searchForm;
	}

	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoZONRolSearchAction");
		}
		
		MantenimientoZONRolSearchForm f = (MantenimientoZONRolSearchForm) this.formBusqueda;		
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		String descripcion = f.getDescripcion();
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
			
		//request.getSession().setAttribute(Constants.ZON_ROL_LIST, estatus);
		List estatus = new ArrayList();
		estatus = service.getRolesByCriteria(params);
		this.setZonRolList(estatus);
		return estatus;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' method");
		}
																										
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		RolDirectorio bean = (RolDirectorio)this.beanRegistroSeleccionado;
		String id = bean.getCodigo();
	
		service.removeRol(id, usuario);
		
		addInfo("Mensaje: ", getResourceMessage("mantenimientoZONRolForm.deleted"));
				
		if (log.isDebugEnabled()) {
			log.debug("ID sistema eliminado: " + id);
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoZONRolAction");
		}
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			return false;
		}
		
		MantenimientoZONRolForm f = (MantenimientoZONRolForm) this.formMantenimiento;
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		RolDirectorio rol = new RolDirectorio();		
		BeanUtils.copyProperties(rol, f);
		
		if(rol.getIndicadorActivo().equals("true"))
			rol.setIndicadorActivo(Constants.NUMERO_UNO);
		else
			rol.setIndicadorActivo(Constants.NUMERO_CERO);
		
        try {
            // agregamos los mensajes de exito
            if (this.accion.equals(this.ACCION_NUEVO)) { //isNew
    			service.insertRol(rol, usuario);
            }
            else {
    			service.updateRol(rol, usuario);
            }
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            
        }
        catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();
            throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
            
        }
        
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		if(log.isDebugEnabled()){
			log.debug("setEditAttributes - MantenimientoZONRolAction");
		}
		this.mostrarBotonConsultar = false;
		RolDirectorio f = (RolDirectorio) this.beanRegistroSeleccionado;
		MantenimientoZONRolForm objForm = new MantenimientoZONRolForm();							  
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			objForm.setActivo(true);
		}else{
			objForm.setActivo(false);
		}
		
		if (!this.accion.equals(this.ACCION_NUEVO) ) {   
			
			String id = f.getCodigo();
			if(StringUtils.isNotBlank(id)){
				RolDirectorio rol = service.getRol(id);
				BeanUtils.copyProperties(objForm, rol);
				if(objForm.getIndicadorActivo().equals("1")){
					objForm.setIndicadorActivo("true");
				}else{
					objForm.setIndicadorActivo("false");
				}
			}
		}
		
		return  objForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try
		{
			this.mostrarBotonConsultar = false;
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		}
		catch(Exception ex){}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		MantenimientoZONRolForm objForm = (MantenimientoZONRolForm)this.formMantenimiento;
		boolean isNew = objForm.isNewRecord();
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoZONRolForm.created";
		}else{
			return "mantenimientoZONRolForm.updated";
		}	
	}

	public List getZonRolList() {
		return zonRolList;
	}

	public void setZonRolList(List zonRolList) {
		this.zonRolList = zonRolList;
	}
	
}
