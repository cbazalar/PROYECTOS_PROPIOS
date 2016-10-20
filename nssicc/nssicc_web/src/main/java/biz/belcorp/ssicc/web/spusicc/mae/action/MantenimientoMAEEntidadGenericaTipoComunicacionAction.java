package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoComunicacion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaTipoComunicacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaTipoComunicacionAction extends BaseMantenimientoSearchAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6995792711827845987L;
	
	private String nombreEntidad;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaTipoComunicacionAction");
		}
		
		MantenimientoMAEEntidadGenericaTipoComunicacionForm f = (MantenimientoMAEEntidadGenericaTipoComunicacionForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		TipoComunicacion tipoComunicacion = new TipoComunicacion();		
		BeanUtils.copyProperties(tipoComunicacion, f);
		
        try {
            if (isNew) {
            	System.out.println("isnew");
            	if(!service.getExisteTipoComunicacion(tipoComunicacion)){
            		
	    			service.insertTipoComunicacion(tipoComunicacion, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
            	Map params = new HashMap();
    			params.put("oidTipoComu", f.getOidTipoComu());
    			
    			TipoComunicacion tComunicacion = service.getTipoComunicacion(params);
    			
    			if(tComunicacion.getCodigo().equals(f.getCodigo())){
    				if(service.getExisteTipoComunicacion(tipoComunicacion)){	
    	    			service.updateTipoComunicacion(tipoComunicacion, usuario);
    	            }else{
                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
        	            return false;
                	}
    			}else{
    				if(!service.getExisteTipoComunicacion(tipoComunicacion)){	
    	    			service.updateTipoComunicacion(tipoComunicacion, usuario);
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
		if(log.isDebugEnabled()){
			log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaTipoComunicacionAction");
		}
		try {	
			MantenimientoMAEEntidadGenericaTipoComunicacionForm f = new MantenimientoMAEEntidadGenericaTipoComunicacionForm(); 
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		
			//RESET
			f.setCodigo("");
			f.setEstado(Constants.ACTIVO);
			f.setDescripcion("");			
			//FIN RESET
			f.setNombreEntidad(getNombreEntidad());
			
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
			if(this.accion.equals(this.ACCION_MODIFICAR))
			{
				if(log.isDebugEnabled()){
					log.debug("setEditAttributes - MantenimientoMAEEntidadGenericaTipoComunicacionAction");
				}
								
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oidTipoComu").toString(); 
				
				if(StringUtils.isNotBlank(id)){
					
					Map params = new HashMap();
					params.put("oidTipoComu", id);
					
					TipoComunicacion tipoComunicacion = service.getTipoComunicacion(params);
					BeanUtils.copyProperties(f, tipoComunicacion);
					f.setNombreEntidad(getNombreEntidad());
					f.setOidTipoComu(id);
					f.setNewRecord(false);
					
					this.formMantenimiento = f;
				}				
			}else{
				if(this.accion.equals(this.ACCION_CONSULTAR))
				{
					this.mostrarBotonSave = false;
					Map bean = (Map)this.beanRegistroSeleccionado;
					String id = bean.get("oidTipoComu").toString(); 
					
					if(StringUtils.isNotBlank(id)){						
						Map params = new HashMap();
						params.put("oidTipoComu", id);
						
						TipoComunicacion tipoComunicacion = service.getTipoComunicacion(params);
						BeanUtils.copyProperties(f, tipoComunicacion);
						f.setNombreEntidad(getNombreEntidad());
						f.setOidTipoComu(id);
						f.setNewRecord(false);
						
						this.formMantenimiento = f;
					}					
				}
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));			
		}		
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEEntidadGenericaTipoComunicacionForm f = (MantenimientoMAEEntidadGenericaTipoComunicacionForm) this.formMantenimiento;
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

	public String getNumeroUno() {
		return numeroUno;
	}

	public void setNumeroUno(String numeroUno) {
		this.numeroUno = numeroUno;
	}

	public String getNumeroCero() {
		return numeroCero;
	}

	public void setNumeroCero(String numeroCero) {
		this.numeroCero = numeroCero;
	}
	

}
