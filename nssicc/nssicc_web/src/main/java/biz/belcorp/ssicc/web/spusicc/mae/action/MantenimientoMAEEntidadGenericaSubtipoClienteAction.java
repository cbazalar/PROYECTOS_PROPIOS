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
import biz.belcorp.ssicc.dao.spusicc.mae.model.SubtipoCliente;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaSubtipoClienteForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaSubtipoClienteAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5737195748671027441L;
	
	private List maeCodigoTipoClienteList;
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaSubtipoClienteAction");
		}
		
		MantenimientoMAEEntidadGenericaSubtipoClienteForm f = (MantenimientoMAEEntidadGenericaSubtipoClienteForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		SubtipoCliente subtipoCliente = new SubtipoCliente();		
		BeanUtils.copyProperties(subtipoCliente, f);
		
        try {
            if (isNew) {
            	subtipoCliente.setOid(null);
            	if(!service.getExisteOidSubtipoCliente(subtipoCliente)){
	    			service.insertSubtipoCliente(subtipoCliente, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	} 
            }
            else 
            {            	
            	if(!service.getExisteOidSubtipoCliente(subtipoCliente)){
	    			service.updateSubtipoCliente(subtipoCliente, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
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
				log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaSubtipoClienteAction");
			}
			
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAEEntidadGenericaSubtipoClienteForm f = new MantenimientoMAEEntidadGenericaSubtipoClienteForm(); 
			f.setNombreEntidad(getNombreEntidad());

			//RESET
			f.setOidTipoCliente("");
			f.setIndicadorEstado(Constants.ESTADO_ACTIVO);
			f.setCodigo("");
			f.setDescripcion("");			
			//FIN RESET
			
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
			Map criteria = new HashMap();
			criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			
			this.maeCodigoTipoClienteList = service.getTipoClienteList(criteria);
			
			if(this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR))
			{
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oid").toString();	
				
				if(StringUtils.isNotBlank(id)){
					Map params = new HashMap();
					params.put("oid", id);
					params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
					
					SubtipoCliente tipoCliente = service.getSubtipoCliente(params);
					BeanUtils.copyProperties(f, tipoCliente);
					f.setNombreEntidad(getNombreEntidad());
					f.setNewRecord(false);
					this.formMantenimiento = f;
				}
								
				if(this.accion.equals(this.ACCION_CONSULTAR))
					this.mostrarBotonSave = true;
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEEntidadGenericaSubtipoClienteForm f = (MantenimientoMAEEntidadGenericaSubtipoClienteForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoMAEEntidadGenericaForm.created";
		else
			return "mantenimientoMAEEntidadGenericaForm.updated";
	}

	public List getMaeCodigoTipoClienteList() {
		return maeCodigoTipoClienteList;
	}

	public void setMaeCodigoTipoClienteList(List maeCodigoTipoClienteList) {
		this.maeCodigoTipoClienteList = maeCodigoTipoClienteList;
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
