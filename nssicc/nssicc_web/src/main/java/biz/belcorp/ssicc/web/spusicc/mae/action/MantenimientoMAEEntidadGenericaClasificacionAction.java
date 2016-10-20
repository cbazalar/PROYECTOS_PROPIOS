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
import biz.belcorp.ssicc.dao.spusicc.mae.model.Clasificacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaClasificacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaClasificacionAction extends BaseMantenimientoSearchAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 161136159520206778L;
	
	private String nombreEntidad;
	
	private List maeCodigoTipoClienteList;
	private LabelValue[] maeCodigoSubTipoClienteList;
	private LabelValue[] maeCodigoTipoClasificacionList;
	
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaClasificacionAction");
		}
		
		MantenimientoMAEEntidadGenericaClasificacionForm f = (MantenimientoMAEEntidadGenericaClasificacionForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		Clasificacion clasificacion = new Clasificacion();		
		BeanUtils.copyProperties(clasificacion, f);
		clasificacion.setIndicadorHiperconsultas(f.getIndicadorHiperconsulta());
		clasificacion.setIndicadorIVR(f.getIndicadorIVR());
		
        try {
            if (isNew) {
            	//garantiasPremio.setCodigoSAP(null);
            	System.out.println("isnew");
            	if(!service.getExisteClasificacion(clasificacion)){
            		
            		Map params = new HashMap();
        			params.put("codigoTipoClasificacion", clasificacion.getCodigoTipoClasificacion());
        			params.put("codigoClasificacion", clasificacion.getCodigoClasificacion());
        			params.put("descripcionClasificacion", clasificacion.getDescripcionClasificacion());
        			params.put("indicadorRegistro", clasificacion.getIndicadorRegistro());
        			params.put("indicadorHiperconsulta", clasificacion.getIndicadorHiperconsulta());
        			params.put("indicadorIVR", clasificacion.getIndicadorIVR());
            		
	    			service.insertClasificacion(params, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
	            	System.out.println("garantias_premios update");
	            	
	            	Map params = new HashMap();
	    			params.put("oidClasificacion", f.getOidClasificacion());
	    			
	    			Clasificacion clas = service.getClasificacion(params);
	    			if(clas.getCodigoClasificacion().equals(f.getCodigoClasificacion()) 
	    						&& clas.getCodigoTipoClasificacion().equals(f.getCodigoTipoClasificacion())){
	    				
	    				if(service.getExisteClasificacion(clasificacion)){	
	    	    			service.updateClasificacion(clasificacion, usuario);
	    	            }else{
	                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
	        	            return false;
	                	}
	    			}else{
	    				if(!service.getExisteClasificacion(clasificacion)){	
	    	    			service.updateClasificacion(clasificacion, usuario);
	    	            }else{
	                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
	        	            return false;
	                	}
	    			}            
            }
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            this.addError("Error", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
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
			log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaClasificacionAction");
		}
		try{
		MantenimientoMAEEntidadGenericaClasificacionForm f = new MantenimientoMAEEntidadGenericaClasificacionForm();
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// RESET
		f.setOidClasificacion("");
		f.setCodigoTipoClienteCL("");
		f.setCodigoTipoClasificacion("");
		f.setCodigoTipoSubCliente("");
		f.setCodigoClasificacion("");
		f.setDescripcionClasificacion("");
		f.setIndicadorRegistro(Constants.ESTADO_ACTIVO);
		f.setNombreEntidad("");		
		f.setIndicadorHiperconsulta(Constants.ESTADO_ACTIVO);
		f.setIndicadorIVR(Constants.ESTADO_ACTIVO);
		// FIN RESET
		f.setCodigoPais(pais.getCodigo());

		f.setNombreEntidad(getNombreEntidad());

		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		this.maeCodigoTipoClienteList = service.getTipoClienteList(criteria);
		
		this.formMantenimiento = f;
		this.mostrarBotonSave = true;
				
		if(this.accion.equals(this.ACCION_MODIFICAR))
		{
			criteria = new HashMap();
			
			Map bean = (Map)this.beanRegistroSeleccionado;
			
			String id = bean.get("codigoClas").toString(); 
			
			if(StringUtils.isNotBlank(id)){
				
				Map params = new HashMap();
				params.put("oidClasificacion", id);
				
				Clasificacion clasificacion = service.getClasificacion(params);
				BeanUtils.copyProperties(f, clasificacion);
				f.setNombreEntidad(getNombreEntidad());					
				f.setOidClasificacion(id);
				f.setNewRecord(false);
				this.formMantenimiento = f;
			
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
				String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

				// Seteamos los filtros seleccionados por el usuario
				if(StringUtils.isNotBlank(clasificacion.getCodigoTipoClienteCL()))
				{
					ArrayList codTipoCliente = new ArrayList();
					codTipoCliente.add(clasificacion.getCodigoTipoClienteCL());
								
					LabelValue[] tiposCliente = ajaxService.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO, codTipoCliente);
					this.maeCodigoSubTipoClienteList = tiposCliente;
									
					if(StringUtils.isNotBlank(clasificacion.getCodigoTipoSubCliente()))
					{
						ArrayList codSubTipoCliente = new ArrayList();
						codSubTipoCliente.add(clasificacion.getCodigoTipoSubCliente());
						
						LabelValue[] tiposClasificacion = ajaxService.getTiposClasificacionesByCriteriaMultipleOID(codigoIdiomaISO, clasificacion.getCodigoTipoClienteCL(), codSubTipoCliente);
						this.maeCodigoTipoClasificacionList = tiposClasificacion;
					}
				}	
			}			
		}else{
			if(this.accion.equals(this.ACCION_CONSULTAR))
			{
				Map bean = (Map)this.beanRegistroSeleccionado;				
				String id = bean.get("codigoClas").toString();
				
				criteria = new HashMap();
				criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
								
				if(StringUtils.isNotBlank(id))
				{
					this.mostrarBotonSave = false;
					Map params = new HashMap();
					params.put("oidClasificacion", id);
					
					Clasificacion clasificacion = service.getClasificacion(params);
					BeanUtils.copyProperties(f, clasificacion);
					f.setNombreEntidad(getNombreEntidad());					
					f.setOidClasificacion(id);
					f.setNewRecord(false);
					this.formMantenimiento = f;
				
					AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
					String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
					
					// Seteamos los filtros seleccionados por el usuario
					if(StringUtils.isNotBlank(clasificacion.getCodigoTipoClienteCL()))
					{
						ArrayList codTipoCliente = new ArrayList();
						codTipoCliente.add(clasificacion.getCodigoTipoClienteCL());
									
						LabelValue []tiposCliente = ajaxService.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO, codTipoCliente);
						this.maeCodigoSubTipoClienteList = tiposCliente;
											
						if(StringUtils.isNotBlank(clasificacion.getCodigoTipoSubCliente()))
						{
							ArrayList codSubTipoCliente = new ArrayList();
							codSubTipoCliente.add(clasificacion.getCodigoTipoSubCliente());
							
							LabelValue []tiposClasificacion = ajaxService.getTiposClasificacionesByCriteriaMultipleOID(codigoIdiomaISO, clasificacion.getCodigoTipoClienteCL(), codSubTipoCliente);
							this.maeCodigoTipoClasificacionList = tiposClasificacion;
						}
					}				
				}				
			}
		}
		}catch(Exception e)
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(e));			
		}
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
			this.maeCodigoTipoClasificacionList = null;
		}else
		{
			this.maeCodigoSubTipoClienteList = null;	
			this.maeCodigoTipoClasificacionList = null;
		}				
	}
	
	public void loadTiposClasificaciones(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ArrayList valores = new ArrayList();

		valores.add(valor);
		this.maeCodigoTipoClasificacionList = ajax.getTipoClasificacionMultipleByOidSubTipoCliente(valores, "T");		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoMAEEntidadGenericaClasificacionForm f = (MantenimientoMAEEntidadGenericaClasificacionForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoMAEEntidadGenericaForm.created";
		else
			return "mantenimientoMAEEntidadGenericaForm.updated";		
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoMAEEntidadGenericaClasificacionForm f = (MantenimientoMAEEntidadGenericaClasificacionForm) this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getCodigoTipoClienteCL()))
			return mensaje = "'Tipo Cliente' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getCodigoTipoSubCliente()))
			return mensaje = "'SubTipo Cliente' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getCodigoTipoClasificacion()))
			return mensaje = "'Tipo Clasificación' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getCodigoClasificacion()))
			return mensaje = "'Cod. Clasificación' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getDescripcionClasificacion()))
			return mensaje = "'Clasificación' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getIndicadorRegistro()))
			return mensaje = "'Estado' es un campo requerido.";
				
		if(StringUtils.isBlank(f.getIndicadorHiperconsulta()))
			return mensaje = "'Hiperconsulta' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getIndicadorIVR()))
			return mensaje = "'IVR' es un campo requerido.";
		
		return mensaje;
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

	public LabelValue[] getMaeCodigoTipoClasificacionList() {
		return maeCodigoTipoClasificacionList;
	}

	public void setMaeCodigoTipoClasificacionList(
			LabelValue[] maeCodigoTipoClasificacionList) {
		this.maeCodigoTipoClasificacionList = maeCodigoTipoClasificacionList;
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
