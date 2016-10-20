package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCClasificacionParaINCDetalleSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCClasificacionParaINCForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCClasificacionParaINCSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoINCClasificacionParaINCAction extends BaseMantenimientoSearchAbstractAction
{
	private static final long serialVersionUID = 4238834944452443482L;
	private static final Integer OID_DESTINATARIO_DEFAULT = new Integer(1);
	private long oidPais;
	
	@ManagedProperty(value = "#{mantenimientoINCClasificacionParaINCDetalleAction}")
	private MantenimientoINCClasificacionParaINCDetalleAction mantenimientoINCClasificacionParaINCDetalleAction;

	@Override
	protected String getSalirForward() {
		return "mantenimientoINCClasificacionParaINCList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoINCClasificacionParaINCForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCClasificacionParaINCSearchForm formSearch = new MantenimientoINCClasificacionParaINCSearchForm();
		return formSearch;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;	
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        MantenimientoINCClasificacionParaINCSearchForm formSearch = new MantenimientoINCClasificacionParaINCSearchForm();
        formSearch.setCodigoPais(pais.getCodigo());
        this.oidPais = this.mPantallaPrincipalBean.getCurrentCountry().getOidPais();
		
	}
	
	
	@Override
	protected List setFindAttributes() throws Exception 
	{		
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		MantenimientoINCClasificacionParaINCSearchForm f = (MantenimientoINCClasificacionParaINCSearchForm) this.formBusqueda;
		
		Map params = new HashMap();
		params.put("oidPais", this.oidPais);
		
		if (StringUtils.isNotBlank(f.getDescripcion())) {
			params.put("descripcionLike", f.getDescripcion()+ "%");
		}
		
		List lista = service.getClasificacionParaINCByCriteria(params);		
		
		return lista;
	}
	
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoINCClasificacionParaINCAction");
		}
		
		MantenimientoINCClasificacionParaINCForm mantenimientoINCClasificacionParaINCForm = new MantenimientoINCClasificacionParaINCForm();
		mantenimientoINCClasificacionParaINCForm.setOidPais(this.oidPais);
		mantenimientoINCClasificacionParaINCForm.setOidDestinatario(OID_DESTINATARIO_DEFAULT);
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
	
		try {
			
			if (!this.accion.equals(this.ACCION_NUEVO))  {
				ClasificacionParaINC registroSeleccionado = (ClasificacionParaINC) this.beanRegistroSeleccionado;
				mantenimientoINCClasificacionParaINCForm.setNewRecord(false);
				
				Map params = new HashMap();
				params.put("oidPais", this.oidPais);
				params.put("secuencial", registroSeleccionado.getSecuencial());
				ClasificacionParaINC bean = service.getClasificacionParaINC(params);	
				BeanUtils.copyProperties (mantenimientoINCClasificacionParaINCForm, bean);
				
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return mantenimientoINCClasificacionParaINCForm;
	}
	
	

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		String idMensajeError = "mantenimientoINCClasificacionParaINCForm.error.referencia";
		String mensajeError = this.getResourceMessage(idMensajeError);
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		
		ClasificacionParaINC bean = (ClasificacionParaINC) this.beanRegistroSeleccionado;	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map params = new HashMap();
		params.put("secuencial", bean.getSecuencial());
		Integer valorExiste = service.getExisteRegistroClasificacionPartiConcu(params);
		if (valorExiste.intValue() > 0 ) {
			throw new Exception(mensajeError);
		}
		valorExiste = service.getExisteRegistroClasificacionDetalle(params);
		if (valorExiste.intValue() > 0 ) {
			throw new Exception(mensajeError);
		}
		service.deleteClasificacionParaINC(bean, usuario);
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoINCClasificacionParaINCAction");
		}
		
		MantenimientoINCClasificacionParaINCForm f = (MantenimientoINCClasificacionParaINCForm) this.formMantenimiento;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		ClasificacionParaINC bean = new ClasificacionParaINC();		
		BeanUtils.copyProperties(bean, f);
		if (isNew) {
    	   service.insertClasificacionParaINC(bean, usuario);
        }
        else {
    	   service.updateClasificacionParaINC(bean, usuario);
        }
        
        		
		return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		MantenimientoINCClasificacionParaINCForm f = (MantenimientoINCClasificacionParaINCForm) this.formMantenimiento;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		String mensaje = null;
		
		Map params = new HashMap();
		params.put("oidPais", this.oidPais);
		params.put("descripcion", f.getDescripcion());
		
		boolean isNew = f.isNewRecord();
		List lista = new ArrayList();
		if (!isNew) {
			params.put("diferenteSecuencial", f.getSecuencial());
		}
		lista = service.getClasificacionParaINCByCriteria(params);	
		if (lista != null && lista.size() >= 1) {
			mensaje = this.getResourceMessage("mantenimientoINCClasificacionParaINCForm.error.descripcionExistente");
		}
		
		return mensaje;
	}

	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoINCClasificacionParaINCForm f = (MantenimientoINCClasificacionParaINCForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoINCClasificacionParaINCForm.created";
		else
			return "mantenimientoINCClasificacionParaINCForm.updated";
	}

	
	/**
	 * Ir al Detalle 
	 * @param actionEvent
	 */
	public void verDetalle(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verDetalle' method");
		}
		try {	
			if (!this.verificarRegistroSeleccionado())
				return;
			ClasificacionParaINC registroSeleccionado = (ClasificacionParaINC) this.beanRegistroSeleccionado;
			MantenimientoINCClasificacionParaINCDetalleSearchForm formSearchDetalle = (MantenimientoINCClasificacionParaINCDetalleSearchForm) 
					   this.mantenimientoINCClasificacionParaINCDetalleAction.getFormBusqueda();
			Integer secuencial = registroSeleccionado.getSecuencial();
			formSearchDetalle.setSecuencialCabecera(secuencial);
			
			this.mantenimientoINCClasificacionParaINCDetalleAction.setSecuencialCabecera(secuencial);
			this.mantenimientoINCClasificacionParaINCDetalleAction.setDescripcionCabecera(registroSeleccionado.getDescripcion());
			this.mantenimientoINCClasificacionParaINCDetalleAction.find();
			this.redireccionarPagina("mantenimientoINCClasificacionParaINCDetalleList");
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'verDetalle' method");
		}
	}
	
	
	/* GET - SET */
	/**
	 * @return the oidPais
	 */
	public long getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(long oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the mantenimientoINCClasificacionParaINCDetalleAction
	 */
	public MantenimientoINCClasificacionParaINCDetalleAction getMantenimientoINCClasificacionParaINCDetalleAction() {
		return mantenimientoINCClasificacionParaINCDetalleAction;
	}

	/**
	 * @param mantenimientoINCClasificacionParaINCDetalleAction the mantenimientoINCClasificacionParaINCDetalleAction to set
	 */
	public void setMantenimientoINCClasificacionParaINCDetalleAction(
			MantenimientoINCClasificacionParaINCDetalleAction mantenimientoINCClasificacionParaINCDetalleAction) {
		this.mantenimientoINCClasificacionParaINCDetalleAction = mantenimientoINCClasificacionParaINCDetalleAction;
	}
	
	

}
