package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoDocumento;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaTipoDocumentoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaTipoDocumentoAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 812118881460408596L;
	
	private String nombreEntidad;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	private List maeTipoDocumentoList;

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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaTipoDocumentoAction");
		}
		
		MantenimientoMAEEntidadGenericaTipoDocumentoForm f = (MantenimientoMAEEntidadGenericaTipoDocumentoForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		boolean isNew = f.isNewRecord();
				
		TipoDocumento tipoDocumento = new TipoDocumento();		
		BeanUtils.copyProperties(tipoDocumento, f);
	
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		
		tipoDocumento.setCodigoPais(oidPais);
		
        try {
            if (isNew) {
            	System.out.println("isnew");
            	if(!service.getExisteTipoDocumento(tipoDocumento)){            		
	    			service.insertTipoDocumento(tipoDocumento, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
            	Map params = new HashMap();
    			params.put("oidTipoDoc", f.getOidTipoDoc());
    			
    			TipoDocumento tDocumento = service.getTipoDocumento(params);
    			
    			if(tDocumento.getCodigo().equals(f.getCodigo())){
    				if(service.getExisteTipoDocumento(tipoDocumento)){	
    	    			service.updateTipoDocumento(tipoDocumento, usuario);
    	            }else{
                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
        	            return false;
                	}
    			}else{
    				if(!service.getExisteTipoDocumento(tipoDocumento)){	
    	    			service.updateTipoDocumento(tipoDocumento, usuario);
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
			MantenimientoMAEEntidadGenericaTipoDocumentoForm f = new MantenimientoMAEEntidadGenericaTipoDocumentoForm(); 
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			
			//RESET
			f.setOidTipoDoc("");
			f.setCodigo("");
			f.setObligatorio("");
			f.setDescripcion("");
			f.setSiglas("");
			f.setLongitud("");
			f.setDni("");
			f.setFiscal("");
			f.setTipoDocu("");
			f.setEstado(Constants.ACTIVO);			
			//FIN RESET
			
			f.setNombreEntidad(getNombreEntidad());
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;			
			this.maeTipoDocumentoList = service.getTipoDocumentosLista();
						
			if(this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR))
			{				
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oidTipoDoc").toString(); 
				
				if(StringUtils.isNotBlank(id)){
					
					Map params = new HashMap();
					params.put("oidTipoDoc", id);
					
					TipoDocumento tipoDocumento = service.getTipoDocumento(params);
					BeanUtils.copyProperties(f, tipoDocumento);
					f.setNombreEntidad(getNombreEntidad());
					//f.setOidTipoDoc(id);
					f.setNewRecord(false);
					this.formMantenimiento = f;
					this.maeTipoDocumentoList = service.getTipoDocumentosLista();					
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
		MantenimientoMAEEntidadGenericaTipoDocumentoForm f = (MantenimientoMAEEntidadGenericaTipoDocumentoForm) this.formMantenimiento;
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

	public List getMaeTipoDocumentoList() {
		return maeTipoDocumentoList;
	}

	public void setMaeTipoDocumentoList(List maeTipoDocumentoList) {
		this.maeTipoDocumentoList = maeTipoDocumentoList;
	}
	

}
