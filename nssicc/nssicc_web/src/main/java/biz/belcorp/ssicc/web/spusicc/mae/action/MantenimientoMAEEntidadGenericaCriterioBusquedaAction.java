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
import biz.belcorp.ssicc.dao.spusicc.mae.model.CriterioBusqueda;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaCriterioBusquedaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoMAEEntidadGenericaCriterioBusquedaAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4260709365048590610L;
	
	private String nombreEntidad;
	private List maeCriteriosList;
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
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaCriterioBusquedaAction");
		}
		
		MantenimientoMAEEntidadGenericaCriterioBusquedaForm f = (MantenimientoMAEEntidadGenericaCriterioBusquedaForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		CriterioBusqueda criterioBusqueda = new CriterioBusqueda();		
		BeanUtils.copyProperties(criterioBusqueda, f);
		
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		
		criterioBusqueda.setCodigoPais(oidPais);
		
        try {
            if (isNew) {
            	//garantiasPremio.setCodigoSAP(null);
            	System.out.println("isnew");
            	if(!service.getExisteCriterioBusqueda(criterioBusqueda)){
            		
	    			service.insertCriterioBusqueda(criterioBusqueda, usuario);
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
    	            return false;
            	}	
            }
            else {
            	Map params = new HashMap();
    			params.put("oidCriterioBus", f.getOidCriterioBus());
    			
    			CriterioBusqueda cb = service.getCriterioBusqueda(params);
    			if(cb.getAtributo1().equals(f.getAtributo1()) && cb.getAtributo2().equals(f.getAtributo2())){
    				if(service.getExisteCriterioBusqueda(criterioBusqueda)){	
    	    			service.updateCriterioBusqueda(criterioBusqueda, usuario);
    	            }else{
                		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
        	            return false;
                	}
    			}else{
    				if(!service.getExisteCriterioBusqueda(criterioBusqueda)){	
    	    			service.updateCriterioBusqueda(criterioBusqueda, usuario);
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
			log.debug("setAddAttributes - MantenimientoMAEEntidadGenericaCriterioBusquedaAction");
		}
		
		try {
			MantenimientoMAEEntidadGenericaCriterioBusquedaForm f = new MantenimientoMAEEntidadGenericaCriterioBusquedaForm();		
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			//RESET
			f.setOidCriterioBus("");
			f.setEstado(Constants.ACTIVO);
			f.setAtributo1("");
			f.setAtributo2("");
			f.setNombreEntidad("");			
			//FIN RESET
			
			f.setNombreEntidad(getNombreEntidad());
			f.setCodigoPais(pais.getCodigo());
			
			this.maeCriteriosList = service.getCriterios();
			this.formMantenimiento = f;
			this.mostrarBotonSave = true;
			
//			Map criteria = new HashMap();
//			criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			
			if(this.accion.equals(this.ACCION_MODIFICAR))
			{
				if(log.isDebugEnabled()){
					log.debug("setEditAttributes - MantenimientoMAEEntidadGenericaCriterioBusquedaAction");
				}

//				criteria = new HashMap();
				
//				System.out.println("id:"+request.getParameter("id"));
				//System.out.println("codigoSAP:"+request.getParameter("codigoSAP"));
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oidCriterioBus").toString(); //request.getParameter("id");
				
				if(StringUtils.isNotBlank(id)){				
					Map params = new HashMap();
					params.put("oidCriterioBus", id);
					
					CriterioBusqueda criterioBusqueda = service.getCriterioBusqueda(params);
					BeanUtils.copyProperties(f, criterioBusqueda);
					f.setNombreEntidad(getNombreEntidad());
					f.setOidCriterioBus(id);
					f.setAtributo1(criterioBusqueda.getAtributo1());
					f.setAtributo2(criterioBusqueda.getAtributo2());
					f.setNewRecord(false);
					this.formMantenimiento = f;
				}			
			}else{
				if(this.accion.equals(this.ACCION_CONSULTAR))
				{
					Map bean = (Map)this.beanRegistroSeleccionado;
					String id = bean.get("oidCriterioBus").toString();  //request.getParameter("id");
					
//					criteria = new HashMap();
//					criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());					
					
					if(StringUtils.isNotBlank(id)){
						Map params = new HashMap();
						params.put("oidCriterioBus", id);					
						CriterioBusqueda criterioBusqueda = service.getCriterioBusqueda(params);
						BeanUtils.copyProperties(f, criterioBusqueda);
						f.setNombreEntidad(getNombreEntidad());
						f.setOidCriterioBus(id);
						f.setAtributo1(criterioBusqueda.getAtributo1());
						f.setAtributo2(criterioBusqueda.getAtributo2());
						f.setNewRecord(false);
						this.formMantenimiento = f;
						this.mostrarBotonSave = false;
					}				
				}
			}			
		}catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoMAEEntidadGenericaCriterioBusquedaForm f = (MantenimientoMAEEntidadGenericaCriterioBusquedaForm) this.formMantenimiento;
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

	public List getMaeCriteriosList() {
		return maeCriteriosList;
	}

	public void setMaeCriteriosList(List maeCriteriosList) {
		this.maeCriteriosList = maeCriteriosList;
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
