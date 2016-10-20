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
import biz.belcorp.ssicc.dao.spusicc.zon.model.PerfilDirectorio;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONPerfilForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONPerfilSearchForm;


/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoZONPerfilAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4190018249014138254L;
	
	private List zonPerfilList; 
	
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoZONPerfilList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoZONPerfilForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoZONPerfilSearchForm searchForm = new MantenimientoZONPerfilSearchForm();
		return searchForm;
	}

	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoZONPerfilSearchAction");
		}
		
		MantenimientoZONPerfilSearchForm f = (MantenimientoZONPerfilSearchForm) this.formBusqueda;
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		String descripcion = f.getDescripcion();
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		
		List lista = new ArrayList();
		this.zonPerfilList = (List)service.getPerfilesByCriteria(params);		
		lista = zonPerfilList;
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
																									
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		PerfilDirectorio Seleccionado = (PerfilDirectorio)this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String id = Seleccionado.getCodigo();
		service.removePerfil(id, usuario);
		

		if (log.isDebugEnabled()) {
			log.debug("ID Perfil eliminado: " + id);
		}
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoZONPerfilAction");
		}
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			return false;
		}
		
		MantenimientoZONPerfilForm f = (MantenimientoZONPerfilForm) this.formMantenimiento;
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		PerfilDirectorio perfil = new PerfilDirectorio();		
		BeanUtils.copyProperties(perfil, f);
		
		if(perfil.getIndicadorActivo().equals("true"))
			perfil.setIndicadorActivo(Constants.NUMERO_UNO);
		else
			perfil.setIndicadorActivo(Constants.NUMERO_CERO);
		
		
        try {
            // agregamos los mensajes de exito
        	if (this.accion.equals(this.ACCION_NUEVO)) { //isNew
    			service.insertPerfil(perfil, usuario);
            }
            else {
    			service.updatePerfil(perfil, usuario);
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
			log.debug("setEditAttributes - MantenimientoZONPerfilAction");
		}
		
		this.mostrarBotonConsultar = false;
		
		PerfilDirectorio f = (PerfilDirectorio) this.beanRegistroSeleccionado;;
		MantenimientoZONPerfilForm objForm = new MantenimientoZONPerfilForm();
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		
		if (this.accion.equals(this.ACCION_CONSULTAR) ) { objForm.setActivo(true); }else{ objForm.setActivo(false); }
		
		if (!this.accion.equals(this.ACCION_NUEVO) ) {   
		
			String id = f.getCodigo();
			
			if(StringUtils.isNotBlank(id)){
				PerfilDirectorio rol = service.getPerfil(id);
				BeanUtils.copyProperties(objForm, rol);
				
				if(objForm.getIndicadorActivo().equals("1")){
					objForm.setIndicadorActivo("true");
				}else{
					objForm.setIndicadorActivo("false");
				}
			}
		}
		return objForm;
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
	
		MantenimientoZONPerfilForm objForm = (MantenimientoZONPerfilForm)this.formMantenimiento;
		boolean isNew = objForm.isNewRecord();
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoZONPerfilForm.created";
		}else{
			return "mantenimientoZONPerfilForm.updated";
		}	
	}

}
