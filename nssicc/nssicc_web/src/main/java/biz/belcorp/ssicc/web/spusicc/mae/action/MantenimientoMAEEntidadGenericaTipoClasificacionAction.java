package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoClasificacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaTipoClasificacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaTipoClasificacionAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7071271601230924240L;
	
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;
	private String nombreEntidad;

	private List maeCodigoTipoClienteList;
	private LabelValue[] maeCodigoSubTipoClienteList;

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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaTipoClasificacionAction");
		}
		
		MantenimientoMAEEntidadGenericaTipoClasificacionForm f = (MantenimientoMAEEntidadGenericaTipoClasificacionForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		TipoClasificacion tipoClasificacion = new TipoClasificacion();		
		BeanUtils.copyProperties(tipoClasificacion, f);
		
        try {
            if (isNew) {
            	System.out.println("isnew");
            	if(!service.getExisteTipoClasificacion(tipoClasificacion)){
            		
            		Map params = new HashMap();
            		params.put("codigoTipoSubCliente", tipoClasificacion.getCodigoTipoSubCliente());
            		params.put("tipoClasificacionPais", tipoClasificacion.getTipoClasificacionPais());
            		params.put("codigoTipoClasificacion", tipoClasificacion.getCodigoTipoClasificacion());
        			params.put("descripcionTipoClasificacion", tipoClasificacion.getDescripcionTipoClasificacion());
        			params.put("indicadorRegistro", tipoClasificacion.getIndicadorRegistro());
            		
	    			service.insertTipoClasificacion(params, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
            	Map params = new HashMap();
    			params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
    			
    			TipoClasificacion tClasificacion = service.getTipoClasificacion(params);
    			
    			if(tClasificacion.getCodigoTipoClasificacion().equals(f.getCodigoTipoClasificacion())
    			      && tClasificacion.getCodigoTipoSubCliente().equals(f.getCodigoTipoSubCliente())){
    				if(service.getExisteTipoClasificacion(tipoClasificacion)){	
    	    			service.updateTipoClasificacion(tipoClasificacion, usuario);
    	            }else{
                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
        	            return false;
                	}
    			}else{
    				if(!service.getExisteTipoClasificacion(tipoClasificacion)){	
    	    			service.updateTipoClasificacion(tipoClasificacion, usuario);
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
			log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaTipoClasificacionAction");
		}
		try {
			MantenimientoMAEEntidadGenericaTipoClasificacionForm f = new MantenimientoMAEEntidadGenericaTipoClasificacionForm();
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			//RESET
			f.setOidTipoClasificacion("");
			f.setCodigoTipoClienteCL("");
			f.setCodigoTipoSubCliente("");
			f.setCodigoTipoClasificacion("");
			f.setDescripcionTipoClasificacion("");
			f.setIndicadorRegistro(Constants.ESTADO_ACTIVO);
			f.setTipoClasificacionPais(Constants.ESTADO_ACTIVO);
			
			//FIN RESET
			f.setNombreEntidad(getNombreEntidad());

			Map criteria = new HashMap();
			criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			this.maeCodigoTipoClienteList = service.getTipoClienteList(criteria);
						
			f.setNombreEntidad(getNombreEntidad());
			
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
			if(this.accion.equals(this.ACCION_MODIFICAR))
			{
				if(log.isDebugEnabled()){
					log.debug("setEditAttributes - MantenimientoMAEEntidadGenericaTipoClasificacionAction");
				}
				
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("codigoTipoClas").toString(); //request.getParameter("id");
				
				if(StringUtils.isNotBlank(id)){
					
					Map params = new HashMap();
					params.put("oidTipoClasificacion", id);
					
					TipoClasificacion tipoClasificacion = service.getTipoClasificacion(params);
					BeanUtils.copyProperties(f, tipoClasificacion);
//comente yo					f.setNombreEntidad(getNombreEntidad());
					//f.setCodigoTipoSubCliente();
					//f.setCodigoTipoClasificacion(codigoTipoClasificacion);
					
					f.setOidTipoClasificacion(id);
					f.setNewRecord(false);
				
					this.formMantenimiento = f;
					AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
					String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
					
					// Seteamos los filtros seleccionados por el usuario
					if(StringUtils.isNotBlank(tipoClasificacion.getCodigoTipoClienteCL()))
					{
						ArrayList codTipoCliente = new ArrayList();
						codTipoCliente.add(tipoClasificacion.getCodigoTipoClienteCL());
									
						LabelValue[] tiposCliente = ajaxService.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO, codTipoCliente);
						this.maeCodigoSubTipoClienteList = tiposCliente;						
					}					
				}				
			}else{
				if(this.accion.equals(this.ACCION_CONSULTAR))
				{
					Map bean = (Map)this.beanRegistroSeleccionado;
					String id = bean.get("codigoTipoClas").toString(); //request.getParameter("id");
					
//					Map criteria = new HashMap();
//					criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
					
					if(StringUtils.isNotBlank(id)){
						
						Map params = new HashMap();
						params.put("oidTipoClasificacion", id);
						
						TipoClasificacion tipoClasificacion = service.getTipoClasificacion(params);
						BeanUtils.copyProperties(f, tipoClasificacion);
						f.setNombreEntidad(getNombreEntidad());
						f.setOidTipoClasificacion(id);
						f.setNewRecord(false);
						
						this.formMantenimiento = f;
						this.mostrarBotonSave = false;
					
						AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
						String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
						
						// Seteamos los filtros seleccionados por el usuario
						if(StringUtils.isNotBlank(tipoClasificacion.getCodigoTipoClienteCL()))
						{
							ArrayList codTipoCliente = new ArrayList();
							codTipoCliente.add(tipoClasificacion.getCodigoTipoClienteCL());
										
							LabelValue []tiposCliente = ajaxService.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO, codTipoCliente);
							this.maeCodigoSubTipoClienteList = tiposCliente;	
						}	
					}
				}
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEEntidadGenericaTipoClasificacionForm f = (MantenimientoMAEEntidadGenericaTipoClasificacionForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoMAEEntidadGenericaForm.created";
		else
			return "mantenimientoMAEEntidadGenericaForm.updated";	
	}

	@Override
	public String setValidarMantenimiento() {
		String mensaje = null;
		MantenimientoMAEEntidadGenericaTipoClasificacionForm f = (MantenimientoMAEEntidadGenericaTipoClasificacionForm) this.formMantenimiento;
		if(StringUtils.isBlank(f.getCodigoTipoClienteCL()))
			return mensaje ="'Tipo Cliente' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getCodigoTipoSubCliente()))
			return mensaje ="'SubTipo Cliente' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getCodigoTipoClasificacion()))
			return mensaje ="'Cod. Tipo Clasificación' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getDescripcionTipoClasificacion()))
			return mensaje ="'Tipo Clasificación' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getTipoClasificacionPais()))
			return mensaje ="' Ind. Tipo Clasif. País' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getIndicadorRegistro()))
			return mensaje ="'Estado' es un campo requerido.";
		
		return mensaje;
	}
	
	public void loadSubTiposClientes(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ArrayList valores = new ArrayList();
		
		if(valor != null)
		{
			valores.add(valor);
			this.maeCodigoSubTipoClienteList = ajax.getSubTiposClientesPorPaisTipoClientesOID(pais.getCodigoIdiomaIso(), valores);
		}else
			this.maeCodigoSubTipoClienteList = null;			
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

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public List getMaeCodigoTipoClienteList() {
		return maeCodigoTipoClienteList;
	}

	public void setMaeCodigoTipoClienteList(List maeCodigoTipoClienteList) {
		this.maeCodigoTipoClienteList = maeCodigoTipoClienteList;
	}

	public LabelValue[] getMaeCodigoSubTipoClienteList() {
		return maeCodigoSubTipoClienteList;
	}

	public void setMaeCodigoSubTipoClienteList(
			LabelValue[] maeCodigoSubTipoClienteList) {
		this.maeCodigoSubTipoClienteList = maeCodigoSubTipoClienteList;
	}
	

}
