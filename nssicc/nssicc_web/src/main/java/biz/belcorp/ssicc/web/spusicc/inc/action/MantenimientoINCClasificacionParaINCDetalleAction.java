package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoGestionCobranzaForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCClasificacionParaINCDetalleForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCClasificacionParaINCDetalleSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoINCClasificacionParaINCDetalleAction extends BaseMantenimientoSearchAbstractAction
{
	private static final long serialVersionUID = -2981240709942710117L;
	private long oidPais;
	private Integer secuencialCabecera;
	private String  descripcionCabecera;
	
	private List listaTipoCliente;
	private List listaSubtipoCliente;
	private List listaTipoClasificacion;
	private List listaClasificacion;

	@Override
	protected String getSalirForward() {
		return "mantenimientoINCClasificacionParaINCDetalleList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoINCClasificacionParaINCDetalleForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCClasificacionParaINCDetalleSearchForm formSearch = new MantenimientoINCClasificacionParaINCDetalleSearchForm();
		return formSearch;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;	
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        MantenimientoINCClasificacionParaINCDetalleSearchForm formSearch = new MantenimientoINCClasificacionParaINCDetalleSearchForm();
        MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		
        formSearch.setCodigoPais(pais.getCodigo());
        this.oidPais = this.mPantallaPrincipalBean.getCurrentCountry().getOidPais();
        this.listaTipoCliente = service.getTipoClienteList(null);
		
	}
	
	
	
	
	@Override
	protected List setFindAttributes() throws Exception 
	{		
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		MantenimientoINCClasificacionParaINCDetalleSearchForm f = (MantenimientoINCClasificacionParaINCDetalleSearchForm) this.formBusqueda;
		
		Map params = new HashMap();
		params.put("secuencialCabecera", this.secuencialCabecera);
		
		if (StringUtils.isNotBlank(f.getDescripcion())) {
			params.put("descripcionLike", f.getDescripcion()+ "%");
		}
		
		List lista = service.getClasificacionParaINCDetalleByCriteria(params);		
		return lista;
	}
	
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoINCClasificacionParaINCDetalleAction");
		}
		
		MantenimientoINCClasificacionParaINCDetalleForm mantenimientoINCClasificacionParaINCDetalleForm = new MantenimientoINCClasificacionParaINCDetalleForm();
		mantenimientoINCClasificacionParaINCDetalleForm.setSecuencialCabecera(this.secuencialCabecera);
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
	
		try {
			
			if (!this.accion.equals(this.ACCION_NUEVO))  {
				ClasificacionParaINCDetalle registroSeleccionado = (ClasificacionParaINCDetalle) this.beanRegistroSeleccionado;
				mantenimientoINCClasificacionParaINCDetalleForm.setNewRecord(false);
				
				Map params = new HashMap();
				params.put("secuencialCabecera", this.secuencialCabecera);
				params.put("secuencialDetalle", registroSeleccionado.getSecuencialDetalle());
				ClasificacionParaINCDetalle bean = service.getClasificacionParaINCDetalle(params);	
				BeanUtils.copyProperties (mantenimientoINCClasificacionParaINCDetalleForm, bean);
				
				Integer oidTipocliente = registroSeleccionado.getOidTipocliente();
				params = new HashMap();
				params.put("oidTipocliente", oidTipocliente);
				this.listaSubtipoCliente = service.getSubTipoClienteList(params);
				
				Integer oidSubtipocliente = registroSeleccionado.getOidSubtipocliente();
				params = new HashMap();
				params.put("oidSubtipocliente", oidSubtipocliente);
				this.listaTipoClasificacion = service.getTipoClasificacionList(params);
				
				Integer oidTipoClasificacion = registroSeleccionado.getOidTipoClasificacion();
				params = new HashMap();
				params.put("oidTipoClasificacion", oidTipoClasificacion);
				this.listaClasificacion = service.getClasificacionList(params);
				
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return mantenimientoINCClasificacionParaINCDetalleForm;
	}
	
	

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		
		ClasificacionParaINCDetalle bean = (ClasificacionParaINCDetalle) this.beanRegistroSeleccionado;		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		service.deleteClasificacionParaINCDetalle(bean, usuario);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoINCClasificacionParaINCAction");
		}
		
		MantenimientoINCClasificacionParaINCDetalleForm f = (MantenimientoINCClasificacionParaINCDetalleForm) this.formMantenimiento;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		ClasificacionParaINCDetalle bean = new ClasificacionParaINCDetalle();		
		BeanUtils.copyProperties(bean, f);
		
        if (isNew) {
    	    service.insertClasificacionParaINCDetalle(bean, usuario);
        }
        else {
    	   service.updateClasificacionParaINCDetalle(bean, usuario);
        }
        return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		MantenimientoINCClasificacionParaINCDetalleForm f = (MantenimientoINCClasificacionParaINCDetalleForm) this.formMantenimiento;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		String mensaje = null;
		
		Map params = new HashMap();
		params.put("descripcion", f.getDescripcion());
		
		boolean isNew = f.isNewRecord();
		List lista = new ArrayList();
		if (!isNew) {
			params.put("diferenteSecuencialDetalle", f.getSecuencialDetalle());
		}
		lista = service.getClasificacionParaINCDetalleByCriteria(params);	
		if (lista != null && lista.size() >= 1) {
			mensaje = this.getResourceMessage("mantenimientoINCClasificacionParaINCDetalleForm.error.descripcionExistente");
		}
		
		return mensaje;
	}

	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoINCClasificacionParaINCDetalleForm f = (MantenimientoINCClasificacionParaINCDetalleForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoINCClasificacionParaINCDetalleForm.created";
		else
			return "mantenimientoINCClasificacionParaINCDetalleForm.updated";
	}

	
	
	/**
	 * Regresa a la pantalla de Cabecera
	 * @param actionEvent
	 */
	public void regresar(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'regresar' method");
		}
		try {	
			this.redireccionarPagina("mantenimientoINCClasificacionParaINCList");
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'regresar' method");
		}
	}
	
	
	/**
	 * @param val
	 */
	public void loadSubtipoCliente(ValueChangeEvent val) {
		try {
			log.debug(">>loadSubtipoCliente ");
			String svalor = val.getNewValue().toString();
			if (StringUtils.isBlank(svalor)) return;
			Integer valor = new Integer(svalor);
			MantenimientoINCClasificacionParaINCDetalleForm f = (MantenimientoINCClasificacionParaINCDetalleForm) this.formMantenimiento;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			
			this.listaSubtipoCliente = new ArrayList();
			this.listaTipoClasificacion = new ArrayList();
			this.listaClasificacion = new ArrayList();
			
			f.setOidSubtipocliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			
			Map params = new HashMap();
			params.put("oidTipocliente", valor);
			this.listaSubtipoCliente = service.getSubTipoClienteList(params);
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	
	/**
	 * @param val
	 */
	public void loadTipoClasificacion(ValueChangeEvent val) {
		try {
			log.debug(">>loadTipoClasificacion ");
			String svalor = val.getNewValue().toString();
			if (StringUtils.isBlank(svalor)) return;
			Integer valor = new Integer(svalor);
			MantenimientoINCClasificacionParaINCDetalleForm f = (MantenimientoINCClasificacionParaINCDetalleForm) this.formMantenimiento;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			
			this.listaTipoClasificacion = new ArrayList();
			this.listaClasificacion = new ArrayList();
			
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			
			Map params = new HashMap();
			params.put("oidSubtipocliente", valor);
			this.listaTipoClasificacion = service.getTipoClasificacionList(params);
			
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * @param val
	 */
	public void loadClasificacion(ValueChangeEvent val) {
		try {
			log.debug(">>loadClasificacion ");
			String svalor = val.getNewValue().toString();
			if (StringUtils.isBlank(svalor)) return;
			Integer valor = new Integer(svalor);
			MantenimientoINCClasificacionParaINCDetalleForm f = (MantenimientoINCClasificacionParaINCDetalleForm) this.formMantenimiento;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			
			this.listaClasificacion = new ArrayList();
			
			f.setOidClasificacion(null);
			
			Map params = new HashMap();
			params.put("oidTipoClasificacion", valor);
			this.listaClasificacion = service.getClasificacionList(params);
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	
	/* GET -SET */
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
	 * @return the secuencialCabecera
	 */
	public Integer getSecuencialCabecera() {
		return secuencialCabecera;
	}

	/**
	 * @param secuencialCabecera the secuencialCabecera to set
	 */
	public void setSecuencialCabecera(Integer secuencialCabecera) {
		this.secuencialCabecera = secuencialCabecera;
	}

	/**
	 * @return the descripcionCabecera
	 */
	public String getDescripcionCabecera() {
		return descripcionCabecera;
	}

	/**
	 * @param descripcionCabecera the descripcionCabecera to set
	 */
	public void setDescripcionCabecera(String descripcionCabecera) {
		this.descripcionCabecera = descripcionCabecera;
	}

	/**
	 * @return the listaSubtipoCliente
	 */
	public List getListaSubtipoCliente() {
		return listaSubtipoCliente;
	}

	/**
	 * @param listaSubtipoCliente the listaSubtipoCliente to set
	 */
	public void setListaSubtipoCliente(List listaSubtipoCliente) {
		this.listaSubtipoCliente = listaSubtipoCliente;
	}

	/**
	 * @return the listaTipoCliente
	 */
	public List getListaTipoCliente() {
		return listaTipoCliente;
	}

	/**
	 * @param listaTipoCliente the listaTipoCliente to set
	 */
	public void setListaTipoCliente(List listaTipoCliente) {
		this.listaTipoCliente = listaTipoCliente;
	}

	/**
	 * @return the listaTipoClasificacion
	 */
	public List getListaTipoClasificacion() {
		return listaTipoClasificacion;
	}

	/**
	 * @param listaTipoClasificacion the listaTipoClasificacion to set
	 */
	public void setListaTipoClasificacion(List listaTipoClasificacion) {
		this.listaTipoClasificacion = listaTipoClasificacion;
	}

	/**
	 * @return the listaClasificacion
	 */
	public List getListaClasificacion() {
		return listaClasificacion;
	}

	/**
	 * @param listaClasificacion the listaClasificacion to set
	 */
	public void setListaClasificacion(List listaClasificacion) {
		this.listaClasificacion = listaClasificacion;
	}
	
	
	
	

}
