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
import biz.belcorp.ssicc.dao.spusicc.mae.model.EstatusCliente;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaEstatusClienteForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaEstatusClienteAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3351345064102905418L;
	
	private String nombreEntidad;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	private List maeCodigoTipoEstatusList;
	private List maeEstatusPosteriorPosibleList;

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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaEstatusClienteAction");
		}
		
		MantenimientoMAEEntidadGenericaEstatusClienteForm f = (MantenimientoMAEEntidadGenericaEstatusClienteForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		EstatusCliente estatusCliente = new EstatusCliente();		
		BeanUtils.copyProperties(estatusCliente, f);
		
        try {
            if (isNew) {
            	estatusCliente.setOid(null);
            	if(!service.getExisteOidEstatusCliente(estatusCliente)){
	    			service.insertEstatusCliente(estatusCliente, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
	            	
	            if(service.getExisteOidEstatusCliente(estatusCliente)){	
	    			service.updateEstatusCliente(estatusCliente, usuario);
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
				log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaEstatusClienteAction");
			}
			
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAEEntidadGenericaEstatusClienteForm f = new MantenimientoMAEEntidadGenericaEstatusClienteForm(); 
			f.setNombreEntidad(getNombreEntidad());
			
			//RESET
			f.setCodigoTipoEstatus("");
			f.setCodigo("");
			f.setDescripcion("");
			f.setEstatusPosteriorPosible("");
			f.setIndicadorEstado(Constants.ESTADO_ACTIVO);			
			//FIN RESET
			
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
			Map criteria = new HashMap();
			criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			
			this.maeCodigoTipoEstatusList = service.getTipoEstadosCliente();
			this.maeEstatusPosteriorPosibleList = service.getEstadoPosteriorPosible(criteria);
					
			if(this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR))
			{
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oid").toString();
				
				if(StringUtils.isNotBlank(id)){
					Map params = new HashMap();
					params.put("oid", id);
					params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
					
					EstatusCliente estatusCliente = service.getEstatusCliente(params);
					BeanUtils.copyProperties(f, estatusCliente);
					f.setNombreEntidad(getNombreEntidad());
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
		MantenimientoMAEEntidadGenericaEstatusClienteForm f = (MantenimientoMAEEntidadGenericaEstatusClienteForm) this.formMantenimiento;
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

	public List getMaeCodigoTipoEstatusList() {
		return maeCodigoTipoEstatusList;
	}

	public void setMaeCodigoTipoEstatusList(List maeCodigoTipoEstatusList) {
		this.maeCodigoTipoEstatusList = maeCodigoTipoEstatusList;
	}

	public List getMaeEstatusPosteriorPosibleList() {
		return maeEstatusPosteriorPosibleList;
	}

	public void setMaeEstatusPosteriorPosibleList(
			List maeEstatusPosteriorPosibleList) {
		this.maeEstatusPosteriorPosibleList = maeEstatusPosteriorPosibleList;
	}
}