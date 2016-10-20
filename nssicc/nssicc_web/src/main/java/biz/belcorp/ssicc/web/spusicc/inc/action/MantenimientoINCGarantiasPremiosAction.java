package biz.belcorp.ssicc.web.spusicc.inc.action;

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
import biz.belcorp.ssicc.dao.spusicc.mae.model.GarantiasPremio;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCGarantiasPremiosForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaGarantiaPremiosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoINCGarantiasPremiosAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2985820238098017534L;
	
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	@Override
	protected String getSalirForward() {
		return "mantenimientoINCGarantiasPremiosForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoMAEEntidadGenericaGarantiaPremiosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCGarantiasPremiosForm formSearch = new MantenimientoINCGarantiasPremiosForm();
		return formSearch;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoINCGarantiasPremiosForm f =(MantenimientoINCGarantiasPremiosForm) this.formBusqueda;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		
		//para garantías de premios
		String codigoSAP = f.getCodigoSAP();
		String codigoEntidad = "INC_PRODU_GARAN";
		
		Map params = BeanUtils.describe(f);
		//Garantias Premios
		params.put("codigoSAP", (StringUtils.isBlank(codigoSAP)?"":StringUtils.trim(codigoSAP)));
		params.put("codigoEntidad", codigoEntidad);
				
		List motivos = (List)service.getDatosEntidadGenericaByCriteria(params);
		
		return motivos;
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaGarantiaPremiosAction");
		}
		
		MantenimientoMAEEntidadGenericaGarantiaPremiosForm f = (MantenimientoMAEEntidadGenericaGarantiaPremiosForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
				
		GarantiasPremio garantiasPremio = new GarantiasPremio();		
		BeanUtils.copyProperties(garantiasPremio, f);
		
        try {
            if (isNew) {
            	System.out.println("isnew");
            	if(!service.getExisteGarantiasPremio(garantiasPremio)){
	    			service.insertGarantiasPremio(garantiasPremio, usuario);	    			
            	}else{
            		this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));    	            
    	            return false;
            	}	
            }
            else {
	            	System.out.println("garantias_premios update");
	            if(service.getExisteGarantiasPremio(garantiasPremio)){	
	    			service.updateGarantiasPremio(garantiasPremio, usuario);
	            }else{
            		this.addError("Error:", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
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
        
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService) getBean("spusicc.mantenimientoMAEEntidadGenericaService");

		MantenimientoMAEEntidadGenericaGarantiaPremiosForm f = new MantenimientoMAEEntidadGenericaGarantiaPremiosForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setIndicadorRegistro(Constants.ESTADO_ACTIVO);
		f.setNombreEntidad("");
		f.setNumDias("");
		f.setCodigoSAP("");
		f.setCodigoPais(pais.getCodigo());
		this.mostrarBotonSave = true;
		
		try {
			
			Map registroSelec = (Map) this.beanRegistroSeleccionado;
			String codigoSAP = registroSelec.get("codigoSAP").toString();
			
			if (!this.accion.equals(this.ACCION_NUEVO)) 
			{
				if (StringUtils.isNotBlank(codigoSAP)) 
				{
					System.out.println("NO ES BLANCO");
					Map params = new HashMap();
					params.put("codigoSAP", codigoSAP);

					GarantiasPremio garantiasPremio = service.getGarantiasPremio(params);
					BeanUtils.copyProperties(f, garantiasPremio);
					f.setNewRecord(false);
					
					if(this.accion.equals(this.ACCION_CONSULTAR))
						this.mostrarBotonSave = false;
					else
						this.mostrarBotonSave = true;
				}
			}
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		MantenimientoINCGarantiasPremiosForm f =(MantenimientoINCGarantiasPremiosForm) this.formBusqueda;
		f.setCodigoPais("");
		f.setCodigoEntidad("");
		f.setCodigoSAP("");
		f.setCodigoInterfaz("");
		f.setNumDias("");
		f.setIndicadorRegistro("");		
		
		this.mostrarBotonEliminar = false;
		
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoMAEEntidadGenericaGarantiaPremiosForm f = (MantenimientoMAEEntidadGenericaGarantiaPremiosForm) this.formMantenimiento;
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
		MantenimientoMAEEntidadGenericaGarantiaPremiosForm f = (MantenimientoMAEEntidadGenericaGarantiaPremiosForm) this.formMantenimiento;
		
		if(Integer.parseInt(f.getNumDias()) <= 0)
		{
			mensaje = "La cantidad de días debe de ser mayor a 0.";
		}
				
		return mensaje;
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
