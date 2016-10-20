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
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoBloqueo;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaTipoBloqueoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaTipoBloqueoAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1765477143285088839L;
	
	private String nombreEntidad;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	private List maeMotivoRechazoList;
	private List maeFormaBloqueoList;

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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaTipoBloqueoAction");
		}
		
		MantenimientoMAEEntidadGenericaTipoBloqueoForm f = (MantenimientoMAEEntidadGenericaTipoBloqueoForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		TipoBloqueo tipoBloqueo = new TipoBloqueo();		
		BeanUtils.copyProperties(tipoBloqueo, f);
		
        try {
            if (isNew) {
    			service.insertTipoBloqueo(tipoBloqueo, usuario);
            }
            else {
    			service.updateTipoBloqueo(tipoBloqueo, usuario);
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
				log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaTipoBloqueoAction");
			}
			
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAEEntidadGenericaTipoBloqueoForm f = new MantenimientoMAEEntidadGenericaTipoBloqueoForm(); 
			f.setNombreEntidad(getNombreEntidad());
			
			//RESET
			f.setIndicadorBloqueoFinanciero(Constants.NUMERO_CERO);
			f.setIndicadorEstado(Constants.ESTADO_ACTIVO);
			f.setOidFormaBloqueo(Constants.MAE_OID_FORMA_BLOQUEO_AMBOS);
			f.setOidFormaDesbloqueo(Constants.MAE_OID_FORMA_BLOQUEO_AMBOS);
			f.setCodigo("");
			f.setDescripcion("");
			f.setOidMotivoRechazo("");
			f.setNivelGravedad("");			
			//FIN RESET
			
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
			this.maeMotivoRechazoList = service.getMotivosRechazo();
			this.maeFormaBloqueoList = service.getFormasBloqueo();
			
			if(this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR))
			{
				if(log.isDebugEnabled()){
					log.debug("setEditAttributes - MantenimientoMAEEntidadGenericaTipoBloqueoAction");
				}
				
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oid").toString();	
				
				if(StringUtils.isNotBlank(id)){
					Map params = new HashMap();
					params.put("oid", id);
					params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
					
					TipoBloqueo tipoBloqueo = service.getTipoBloqueo(params);
					BeanUtils.copyProperties(f, tipoBloqueo);
					f.setNombreEntidad(getNombreEntidad());
					
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
		MantenimientoMAEEntidadGenericaTipoBloqueoForm f = (MantenimientoMAEEntidadGenericaTipoBloqueoForm) this.formMantenimiento;
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

	public List getMaeMotivoRechazoList() {
		return maeMotivoRechazoList;
	}

	public void setMaeMotivoRechazoList(List maeMotivoRechazoList) {
		this.maeMotivoRechazoList = maeMotivoRechazoList;
	}

	public List getMaeFormaBloqueoList() {
		return maeFormaBloqueoList;
	}

	public void setMaeFormaBloqueoList(List maeFormaBloqueoList) {
		this.maeFormaBloqueoList = maeFormaBloqueoList;
	}

}
