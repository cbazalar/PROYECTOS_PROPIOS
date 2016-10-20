package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionSobreFlete;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEExcencionSobreFleteForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEExcencionSobreFleteSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoMAEExcencionSobreFleteSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4219331366569445857L;
	
	private List siccTipoClienteList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private List maeExcencionSobreFleteList;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	@Override
	protected String getSalirForward() {
		return "mantenimientoMAEExcencionSobreFleteList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoMAEExcencionSobreFleteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoMAEExcencionSobreFleteSearchForm searchForm = new MantenimientoMAEExcencionSobreFleteSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoMAEExcencionSobreFleteSearchAction");
		}
		
		MantenimientoMAEExcencionSobreFleteSearchForm f = (MantenimientoMAEExcencionSobreFleteSearchForm) this.formBusqueda;		
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		
		Map params = BeanUtils.describe(f);
		params.put("tipoBusqueda", Constants.NUMERO_UNO);
		
		List lista = service.getExcencionesSobreFletesByCriteria(params);
				
		this.maeExcencionSobreFleteList = lista;
		
		// Seteamos los filtros seleccionados por el usuario
		if(StringUtils.isNotBlank(f.getCodTipoCliente()))
		{
			ArrayList codTipoCliente = new ArrayList();
			codTipoCliente.add(f.getCodTipoCliente());
						
			LabelValue[] tiposCliente = ajaxService.getSubTiposClientesPorPaisTipoClientesCodigo(codigoIdiomaISO, codTipoCliente);
			this.siccSubTipoClienteList = tiposCliente;
			
			if(StringUtils.isNotBlank(f.getCodSubTipoCliente()))
			{
				ArrayList codSubTipoCliente = new ArrayList();
				codSubTipoCliente.add(f.getCodSubTipoCliente());
				
				LabelValue[] tiposClasificacion = ajaxService.getTiposClasificacionesByCriteriaMultipleCodigo(codigoIdiomaISO, f.getCodTipoCliente(), codSubTipoCliente);
				this.siccTipoClasificacionList = tiposClasificacion;
				
				if(StringUtils.isNotBlank(f.getCodTipoClasificacion()))
				{
					ArrayList codTipoClasificacion = new ArrayList();
					codTipoClasificacion.add(f.getCodTipoClasificacion());
				
					LabelValue[] clasificaciones = ajaxService.getClasificacionesByCriteriaMultipleCodigo(codigoIdiomaISO, f.getCodTipoCliente(), codSubTipoCliente, codTipoClasificacion);
					this.siccClasificacionList = clasificaciones;
				}
			}			
		}
		
		return lista;
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEExcencionSobreFleteAction");
		}

		MantenimientoMAEExcencionSobreFleteForm f = (MantenimientoMAEExcencionSobreFleteForm) this.formMantenimiento;
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

		boolean isNew = f.isNewRecord();

		ExcencionSobreFlete excencionSobreFlete = new ExcencionSobreFlete();
		BeanUtils.copyProperties(excencionSobreFlete, f);
		excencionSobreFlete.setCodPais(pais.getCodigo());

		try {
			Map params = BeanUtils.describe(f);

			params.put("indicadorExcepcionFlete", null);
			params.put("tipoBusqueda", Constants.NUMERO_DOS);
			params.put("codPais", pais.getCodigo());

			List lista = service.getExcencionesSobreFletesByCriteria(params);

			if (isNew) 
			{
				if (lista != null && lista.size() > 0) 
				{
					recargarCombos(f, codigoIdiomaISO);
					this.addError("Error: ", this.getResourceMessage("mantenimientoMAEExcencionSobreFleteForm.error.duplicado"));
					return false;
				} else 
				{
					service.insertExcencionSobreFlete(excencionSobreFlete, usuario);
				}
			} else 
			{
				boolean actualizar = true;
				if (lista != null && lista.size() > 0) 
				{
					if (lista.size() == 1) 
					{
						String oid = MapUtils.getString((Map) lista.get(0), "codExenSfle", "");
						if (StringUtils.equals(oid, excencionSobreFlete.getCodExenSfle())) {
							actualizar = true;
						} else {
							actualizar = false;
						}
					} else {
						actualizar = false;
					}
				}

				if (actualizar) {
					service.updateExcencionSobreFlete(excencionSobreFlete, usuario);
				} else {
					recargarCombos(f, codigoIdiomaISO);
					this.addError("Error: ", this.getResourceMessage("mantenimientoMAEExcencionSobreFleteForm.error.duplicado"));
					return false;
				}
			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
			return false;
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
			return false;
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoMAEExcencionSobreFleteAction");
		}

		MantenimientoMAEExcencionSobreFleteForm f = new MantenimientoMAEExcencionSobreFleteForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// RESET
		f.setCodExenSfle("");
		f.setCodTipoCliente("");
		f.setCodSubTipoCliente("");
		f.setCodTipoClasificacion("");
		f.setCodClasificacion("");
		f.setIndicadorExcepcionFlete(Constants.NUMERO_UNO);
		f.setCodigoPais(pais.getCodigo());
		// FIN RESET

		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISO01(usuario.getIdioma().getCodigoISO());
		this.mostrarBotonSave = true;
		
		this.siccSubTipoClienteList = null;
		this.siccTipoClasificacionList  = null;
		this.siccClasificacionList  = null;

		try {

			if (!this.accion.equals(this.ACCION_NUEVO)) 
			{
				Map bean = (Map) this.beanRegistroSeleccionado;
				String id = bean.get("codExenSfle").toString();
				String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

				if (StringUtils.isNotBlank(id)) 
				{
					ExcencionSobreFlete excencionSobreFlete = service.getExcencionSobreFlete(id);
					Map params = BeanUtils.describe(f);
					BeanUtils.copyProperties(f, excencionSobreFlete);
					recargarCombos(f, codigoIdiomaISO);
					
					f.setNewRecord(false);
					
					if(this.accion.equals(this.ACCION_CONSULTAR))
					{
						this.mostrarBotonSave = false;
						params = BeanUtils.describe(f);
						params.put("codIdioma", codigoIdiomaISO);

						String desTipoCliente = interfazSiCCService.getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma(params);
						String desSubTipoCliente = interfazSiCCService.getDescripcionSubTipoClienteByCriteria(params);
						String desTipoClasCliente = interfazSiCCService.getDescripcionTipoClasificacionByCriteria(params);
						String desClasificacion = interfazSiCCService.getClasificacionByCriteria(params);

						f.setCodTipoCliente(desTipoCliente);
						f.setCodSubTipoCliente(desSubTipoCliente);
						f.setCodTipoClasificacion(desTipoClasCliente);
						f.setCodClasificacion(desClasificacion);
					}
					else
						this.mostrarBotonSave = true;
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoMAEExcencionSobreFleteSearchAction");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService"); 

		MantenimientoMAEExcencionSobreFleteSearchForm f = (MantenimientoMAEExcencionSobreFleteSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
    	f.setCodTipoCliente("");
    	f.setCodSubTipoCliente("");
    	f.setCodTipoClasificacion("");
    	f.setCodClasificacion("");
    	f.setIndicadorExcepcionFlete("");
    	f.setCodigoPais(pais.getCodigo());
    	f.setCodPais(pais.getCodigo());

		this.siccTipoClienteList = service.getTiposClientesByCodigoISO01(usuario.getIdioma().getCodigoISO());
		this.mostrarBotonEliminar = false;
		
		this.siccSubTipoClienteList = null;
		this.siccTipoClasificacionList  = null;
		this.siccClasificacionList  = null;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoMAEExcencionSobreFleteForm f = (MantenimientoMAEExcencionSobreFleteForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoMAEExcencionSobreFleteForm.created";
		else
			return "mantenimientoMAEExcencionSobreFleteForm.updated";		
	}
	
	private void recargarCombos(MantenimientoMAEExcencionSobreFleteForm f, String codigoIdiomaISO) 
	{
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		if(StringUtils.isNotBlank(f.getCodTipoCliente()))
		{
			ArrayList codTipoCliente = new ArrayList();
			codTipoCliente.add(f.getCodTipoCliente());
						
			LabelValue []tiposCliente = ajaxService.getSubTiposClientesPorPaisTipoClientesCodigo(codigoIdiomaISO, codTipoCliente);
			this.siccSubTipoClienteList = tiposCliente;
			
			if(StringUtils.isNotBlank(f.getCodSubTipoCliente()))
			{
				ArrayList codSubTipoCliente = new ArrayList();
				codSubTipoCliente.add(f.getCodSubTipoCliente());
				
				LabelValue []tiposClasificacion = ajaxService.getTiposClasificacionesByCriteriaMultipleCodigo(codigoIdiomaISO, f.getCodTipoCliente(), codSubTipoCliente);
				this.siccTipoClasificacionList = tiposClasificacion;
				
				if(StringUtils.isNotBlank(f.getCodTipoClasificacion()))
				{
					ArrayList codTipoClasificacion = new ArrayList();
					codTipoClasificacion.add(f.getCodTipoClasificacion());
				
					LabelValue []clasificaciones = ajaxService.getClasificacionesByCriteriaMultipleCodigo(codigoIdiomaISO, f.getCodTipoCliente(), codSubTipoCliente, codTipoClasificacion);
					this.siccClasificacionList = clasificaciones;
				}
			}			
		}
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoMAEExcencionSobreFleteForm f = (MantenimientoMAEExcencionSobreFleteForm) this.formMantenimiento;
		
		if (StringUtils.isBlank(f.getCodTipoCliente())) {
			mensaje = "'Tipo Cliente' es un campo requerido";			
		}
		
		return mensaje;	
	}
	
	public void loadSubTiposClientes(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ArrayList codigoTipoClientes = new ArrayList();
		
		if(valor != null)
		{
			codigoTipoClientes.add(valor);
			this.siccSubTipoClienteList = ajax.getSubTiposClientesPorPaisTipoClientesCodigo(pais.getCodigoIdiomaIso(), codigoTipoClientes);
		}else
		{
			this.siccSubTipoClienteList = null;	
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}		
	}
	
	public void loadTiposClasificaciones(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionSobreFleteSearchForm f = (MantenimientoMAEExcencionSobreFleteSearchForm) this.formBusqueda;
		
		if(valor != null)
		{
			ArrayList codigoSubTipoCliente = new ArrayList();
			codigoSubTipoCliente.add(valor);
			this.siccTipoClasificacionList = ajax.getTiposClasificacionesByCriteriaMultipleCodigo(pais.getCodigoIdiomaIso(), f.getCodTipoCliente(), codigoSubTipoCliente);
		}
		else
		{
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}
	}
	
	public void loadClasificaciones(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionSobreFleteSearchForm f = (MantenimientoMAEExcencionSobreFleteSearchForm) this.formBusqueda;
		
		if(valor != null)
		{
			ArrayList codigoSubTipoCliente = new ArrayList();
			ArrayList codigoTipoClasificacion = new ArrayList();
			
			codigoSubTipoCliente.add(f.getCodSubTipoCliente());
			codigoTipoClasificacion.add(valor);
			
			this.siccClasificacionList = ajax.getClasificacionesByCriteriaMultipleCodigo(pais.getCodigoIdiomaIso(), f.getCodTipoCliente(), 
					codigoSubTipoCliente, codigoTipoClasificacion);
		}
		else
		{
			this.siccClasificacionList = null;
		}			
	}
	
	public void loadTiposClasificacionesMante(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionSobreFleteForm f = (MantenimientoMAEExcencionSobreFleteForm) this.formMantenimiento;
		
		if(valor != null)
		{
			ArrayList codigoSubTipoCliente = new ArrayList();
			codigoSubTipoCliente.add(valor);
			this.siccTipoClasificacionList = ajax.getTiposClasificacionesByCriteriaMultipleCodigo(pais.getCodigoIdiomaIso(), f.getCodTipoCliente(), codigoSubTipoCliente);
		}
		else
		{
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}
	}
	
	public void loadClasificacionesMante(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionSobreFleteForm f = (MantenimientoMAEExcencionSobreFleteForm) this.formMantenimiento;
		
		if(valor != null)
		{
			ArrayList codigoSubTipoCliente = new ArrayList();
			ArrayList codigoTipoClasificacion = new ArrayList();
			
			codigoSubTipoCliente.add(f.getCodSubTipoCliente());
			codigoTipoClasificacion.add(valor);
			
			this.siccClasificacionList = ajax.getClasificacionesByCriteriaMultipleCodigo(pais.getCodigoIdiomaIso(), f.getCodTipoCliente(), 
					codigoSubTipoCliente, codigoTipoClasificacion);
		}
		else
		{
			this.siccClasificacionList = null;
		}			
	}
	

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

		
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	
	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	
	public void setSiccTipoClasificacionList(LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	
	public List getMaeExcencionSobreFleteList() {
		return maeExcencionSobreFleteList;
	}

	
	public void setMaeExcencionSobreFleteList(List maeExcencionSobreFleteList) {
		this.maeExcencionSobreFleteList = maeExcencionSobreFleteList;
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