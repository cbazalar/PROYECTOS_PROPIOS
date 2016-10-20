package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoEstatus;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaTipoEstatusClienteForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaTipoEstatusClienteAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8198311384571667710L;
	
	private String nombreEntidad;

	private List maeMarcasList;

	@Override
	protected String getSalirForward() 
	{
		MantenimientoMAEEntidadGenericaSearchAction man = this.findManageBean("mantenimientoMAEEntidadGenericaSearchAction");
		man.find();
		return "mantenimientoMAEEntidadGenericaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaTipoEstatusClienteAction");
		}
		
		MantenimientoMAEEntidadGenericaTipoEstatusClienteForm f = (MantenimientoMAEEntidadGenericaTipoEstatusClienteForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		TipoEstatus tipoEstatus = new TipoEstatus();		
		BeanUtils.copyProperties(tipoEstatus, f);
		
        try {
            if (isNew) {
            	System.out.println("isnew");
            	if(!service.getExisteTipoEstatusCliente(tipoEstatus)){            		
	    			service.insertEstatusCliente(tipoEstatus, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
            	Map params = new HashMap();
    			params.put("oidEstatus", f.getOidEstatus());
    			
    			TipoEstatus tEstatus = service.getTipoEstatusCliente(params);
    			
    			if(tEstatus.getCodigo().equals(f.getCodigo())){
    				if(service.getExisteTipoEstatusCliente(tipoEstatus)){	
    	    			service.updateTipoEstatusCliente(tipoEstatus, usuario);
    	            }else{
    	            	this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
        	            return false;
                	}
    			}else{
    				if(!service.getExisteTipoEstatusCliente(tipoEstatus)){	
    	    			service.updateTipoEstatusCliente(tipoEstatus, usuario);
    	            }else{
                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
        	            return false;
                	}
    			}	            
            }
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            return false;
        }
        catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();
            this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
            return false;
        }
              
        this.invocarFindLuegoGrabar = false;
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void inicializaPantallaMantenimiento() 
	{
		try {
			if(log.isDebugEnabled()){
				log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaTipoEstatusClienteAction");
			}
			
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAEEntidadGenericaTipoEstatusClienteForm f = new MantenimientoMAEEntidadGenericaTipoEstatusClienteForm(); 
			f.setNombreEntidad(getNombreEntidad());
			
			//RESET
			f.setOidEstatus("");
			f.setCodigo("");
			f.setMarca("");
			f.setDescripcion("");
			f.setCodigoEntidad("");
			f.setCodigoPais("");
			// FIN RESET

			Map criteria = new HashMap();
			criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			this.maeMarcasList = service.getMarcas();
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
			if(this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR))
			{				
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oidEstatus").toString();	
				
				criteria = new HashMap();
				criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
								
				if(StringUtils.isNotBlank(id)){
					
					Map params = new HashMap();
					params.put("oidEstatus", id);
					
					TipoEstatus tipoEstatus = service.getTipoEstatusCliente(params);
					BeanUtils.copyProperties(f, tipoEstatus);
					f.setNombreEntidad(getNombreEntidad());
					f.setOidEstatus(id);
					f.setMarca(tipoEstatus.getMarca());
					f.setNewRecord(false);
					this.formMantenimiento = f;
				}	
				
				if(this.accion.equals(this.ACCION_CONSULTAR))
					this.mostrarBotonSave = false;
			}		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEEntidadGenericaTipoEstatusClienteForm f = (MantenimientoMAEEntidadGenericaTipoEstatusClienteForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoMAEEntidadGenericaForm.created";
		else
			return "mantenimientoMAEEntidadGenericaForm.updated";
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public List getMaeMarcasList() {
		return maeMarcasList;
	}

	public void setMaeMarcasList(List maeMarcasList) {
		this.maeMarcasList = maeMarcasList;
	}
	

}
