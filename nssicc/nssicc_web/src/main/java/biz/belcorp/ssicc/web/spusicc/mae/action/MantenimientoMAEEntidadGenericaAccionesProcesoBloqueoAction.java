package biz.belcorp.ssicc.web.spusicc.mae.action;

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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.AccionesProcesoBloqueo;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public class MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5810920164843572772L;
	
	private List maeTipoBloqueoAccionList;
	private List maeProcesoBloqueoAccionList;
	private LabelValue[] maeAccionBloqueoAccionList;
	
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;
	
	private String nombreEntidad;

	@Override
	protected String getSalirForward() {
		
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction");
		}
		
		MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm f = (MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		boolean isNew = f.isNewRecord();
		f.setCodigoPais(pais.getCodigo());
				
		AccionesProcesoBloqueo accionesProcesoBloqueo = new AccionesProcesoBloqueo();		
		BeanUtils.copyProperties(accionesProcesoBloqueo, f);
		
        try {
            if (isNew) {
            	accionesProcesoBloqueo.setOid(null);
            	if(!service.getExisteOidAccionesProcesoBloqueo(accionesProcesoBloqueo)){
		    		service.insertAccionesProcesoBloqueo(accionesProcesoBloqueo, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}
            }
            else {
            	if(!service.getExisteOidAccionesProcesoBloqueo(accionesProcesoBloqueo)){
            		service.updateAccionesProcesoBloqueo(accionesProcesoBloqueo, usuario);
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
		if (log.isDebugEnabled()) {
			log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction");
		}
		try {
			MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm f = new MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm();
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService) getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			f.setNombreEntidad(getNombreEntidad());
			f.setOidAccionBloqueo("");
			f.setOidProcesoBloqueo("");
			f.setOidTipoBloqueo("");
			f.setIndicadorEstado("");
			f.setCodigoPais(pais.getCodigo());

			Map criteria = new HashMap();
			criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());

			this.maeTipoBloqueoAccionList = service.getTipoBloqueoList(criteria);
			this.maeProcesoBloqueoAccionList = service.getProcesoBloqueoList();
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;

			if (this.accion.equals(this.ACCION_MODIFICAR)) 
			{
				if (log.isDebugEnabled()) {
					log.debug("setEditAttributes - MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction");
				}

				criteria = new HashMap();
				List aux = service.getAccionBloqueoList(criteria);
				LabelValue[] temp = new LabelValue[aux.size()];
				Map bean = (Map)this.beanRegistroSeleccionado;
				
				criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
				String id = bean.get("oid").toString();				
				
				for (int i = 0; i < aux.size(); i++) {
					LabelValue aux1= new LabelValue();
					aux1.setLabel(((Base)aux.get(i)).getDescripcion());
					aux1.setValue(((Base)aux.get(i)).getCodigo());
					temp[i] = aux1;
				}
				
				this.maeAccionBloqueoAccionList = temp;	

				if (StringUtils.isNotBlank(id)) {
					criteria.put("oid", id);
					AccionesProcesoBloqueo AccionesProcesoBloqueo = service.getAccionesProcesoBloqueo(criteria);
					BeanUtils.copyProperties(f, AccionesProcesoBloqueo);
					f.setNombreEntidad(getNombreEntidad());
					f.setOid(id);
					f.setNewRecord(false);

					this.formMantenimiento = f;
				}

			} else {
				if (this.accion.equals(this.ACCION_CONSULTAR)) 
				{
					Map bean = (Map)this.beanRegistroSeleccionado;
					String id = bean.get("oid").toString();  

					criteria = new HashMap();
					criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
					List aux = service.getAccionBloqueoList(criteria);
					LabelValue[] temp = new LabelValue[aux.size()];
					
					for (int i = 0; i < aux.size(); i++) {
						LabelValue aux1= new LabelValue();
						aux1.setLabel(((Base)aux.get(i)).getDescripcion());
						aux1.setValue(((Base)aux.get(i)).getCodigo());
						temp[i] = aux1;
					}
					
					this.maeAccionBloqueoAccionList =  temp;

					if (StringUtils.isNotBlank(id)) {
						Map params = new HashMap();
						params.put("oid", id);

						AccionesProcesoBloqueo AccionesProcesoBloqueo = service.getAccionesProcesoBloqueo(params);
						BeanUtils.copyProperties(f, AccionesProcesoBloqueo);
						f.setNombreEntidad(getNombreEntidad());
						f.setOid(id);
						f.setNewRecord(false);

						this.formMantenimiento = f;
						this.mostrarBotonSave = false;
					}
				}
			}

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			System.out.println(e);
		}
	}
		
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm f = (MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm)this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoMAEEntidadGenericaForm.created";
		else
			return "mantenimientoMAEEntidadGenericaForm.updated";
	}
	
	public void loadAccionBloqueo(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		if(valor != null)
			this.maeAccionBloqueoAccionList = ajax.getDesAccionesByProcesosBloqueo(valor);
		else
			this.maeAccionBloqueoAccionList = null;
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;;
		MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm f = (MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm)this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getOidTipoBloqueo()))
			return mensaje = "'Tipo Bloqueo' es un campo requerido.";
		
		if(StringUtils.isBlank(f.getOidProcesoBloqueo()))
			return mensaje = "'Proceso Bloqueo' es un campo requerido.";
				
		if(StringUtils.isBlank(f.getOidAccionBloqueo()))
			return mensaje = "'Accion Bloqueo' es un campo requerido.";
					
		if(StringUtils.isBlank(f.getIndicadorEstado()))
			return mensaje = "'Estado' es un campo requerido.";
		
		return mensaje;	
	}

	public List getMaeTipoBloqueoAccionList() {
		return maeTipoBloqueoAccionList;
	}

	public void setMaeTipoBloqueoAccionList(List maeTipoBloqueoAccionList) {
		this.maeTipoBloqueoAccionList = maeTipoBloqueoAccionList;
	}

	public List getMaeProcesoBloqueoAccionList() {
		return maeProcesoBloqueoAccionList;
	}

	public void setMaeProcesoBloqueoAccionList(List maeProcesoBloqueoAccionList) {
		this.maeProcesoBloqueoAccionList = maeProcesoBloqueoAccionList;
	}

	public LabelValue[] getMaeAccionBloqueoAccionList() {
		return maeAccionBloqueoAccionList;
	}

	public void setMaeAccionBloqueoAccionList(LabelValue[] maeAccionBloqueoAccionList) {
		this.maeAccionBloqueoAccionList = maeAccionBloqueoAccionList;
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

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}
}