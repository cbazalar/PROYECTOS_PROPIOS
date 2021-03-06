package biz.belcorp.ssicc.web.spusicc.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.apache.bcel.classfile.Constant;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAError;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerar;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAErrorService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoGEOZonaTerritorioClienteForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoGEOZonaTerritorioClienteSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoGEOZonaTerritorioClienteSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8951772185487859629L;
	
	private String flagBusqueda;
	private Long numeroClientesGenerar;
	private Integer longitudCampoClientes;
	private Boolean flagValidarCampoZona;
	private Boolean flagValidarCampoTerritorio;
	private Boolean statusOpcional;
	private Boolean statusValidObl1;
	private Boolean statusValidObl2;
	private Boolean statusValidObl3;
	
	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoGEOZonaTerritorioClienteList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoGEOZonaTerritorioClienteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoGEOZonaTerritorioClienteSearchForm searchForm = new MantenimientoGEOZonaTerritorioClienteSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		
		this.flagBusqueda = "si";		
		MantenimientoGEOZonaTerritorioClienteSearchForm searchForm = (MantenimientoGEOZonaTerritorioClienteSearchForm) this.formBusqueda;

		log.debug("VALOR DEL FLAG " + flagBusqueda);

		if (StringUtils.isNotEmpty(this.flagBusqueda)) {
			if (flagBusqueda.equalsIgnoreCase("no")) {
				searchForm.inicializar();
			}
		} else {
			searchForm.inicializar();
			flagBusqueda = "no";
		}

		Map criteria = BeanUtils.describe(searchForm);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());

		if (StringUtils.isNotBlank(searchForm.getCodigo())) {
			criteria.put("codigo", searchForm.getCodigo().trim() + "%");
		}
		if (StringUtils.isNotBlank(searchForm.getNumeroDocumento())) {
			criteria.put("numeroDocumento", searchForm.getNumeroDocumento()
					.trim()	+ "%");
		}
		if (StringUtils.isNotBlank(searchForm.getApellidoPaterno())) {
			criteria.put("apellidoPaterno", searchForm.getApellidoPaterno()
					.trim()	+ "%");
		}
		if (StringUtils.isNotBlank(searchForm.getApellidoMaterno())) {
			criteria.put("apellidoMaterno", searchForm.getApellidoMaterno()
					.trim()	+ "%");
		}
		if (StringUtils.isNotBlank(searchForm.getNombre())) {
			criteria.put("nombre", searchForm.getNombre().trim() + "%");
		}
		
		log.info(criteria.toString());

		// La busqueda solo la realizaremos en las interfaces activas
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Cambio hecho por rcdlrp 08/06/06 inicio
		List lista = service.getClientesSICCByCriteria(criteria);

		log.debug("Pintando el tamaño de la lista " + lista.size());

		if (lista.size() == 0) {
			this.getResourceMessage("mantenimientoGEOZonaTerritorioClienteList.notfound");
        }
		// fin

		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.numeroClientesGenerar = svc.getNumeroClientesUAGenerar();
		
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
			log.debug("Entering 'save' method");
		}

		// Extraemos atributos y parámetros a usar
		MantenimientoGEOZonaTerritorioClienteForm clienteUAForm = (MantenimientoGEOZonaTerritorioClienteForm) this.formMantenimiento;

		this.flagBusqueda ="no";
		
		// Extreamos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ClienteUAGenerarService service = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		ClienteUAErrorService errService = (ClienteUAErrorService) getBean("sisicc.clienteUAErrorService");
		int existeRegistro = 0;
		try {

			if (clienteUAForm.getEstadoValidacion().equals(	Constants.VALIDACION_TOTAL_ERR)) 
			{
				ClienteUAError clienteError = new ClienteUAError();
				clienteError.setCodigoPais(pais.getCodigo());
				clienteError.setCodigoCliente(clienteUAForm.getCodigo());
				clienteError.setNumeroDocumento(clienteUAForm.getNumeroDocumento());
				clienteError.setNombre(clienteUAForm.getNombre());
				clienteError.setApellidoPaterno(clienteUAForm.getApellidoPaterno());
				clienteError.setNombreVia(clienteUAForm.getNombreVia());
				clienteError.setNumeroPrincipal(clienteUAForm.getNumeroPrincipal());
				clienteError.setCodigoUbigeo(clienteUAForm.getOrden1()
						+ clienteUAForm.getOrden2() + clienteUAForm.getOrden3()
						+ clienteUAForm.getOrden4());
				clienteError.setDescripcionUbigeo(StringUtils.trim(clienteUAForm.getDescripcionOrden1())
						+ ","+ StringUtils.trim(clienteUAForm.getDescripcionOrden2())
						+ ","+ StringUtils.trim(clienteUAForm.getDescripcionOrden3())
						+ ","+ StringUtils.trim(clienteUAForm.getDescripcionOrden4()));
				
				// restriccion con la BD
				clienteError.setDescripcionUbigeo(StringUtils.substring(clienteError.getDescripcionUbigeo(), 0, 58));
				clienteError.setObservacionDireccion(clienteUAForm.getObservaciones());
				clienteError.setValorError1(clienteUAForm.getEstadoError1());
				clienteError.setValorError2(clienteUAForm.getEstadoError2());
				clienteError.setValorError3(clienteUAForm.getEstadoError3());
				existeRegistro = errService.selectClienteUAError(clienteError);
				
				if (existeRegistro == 0)
					errService.insertClienteUAError(clienteError, usuario);
				else
					errService.updateClienteUAError(clienteError, usuario);

			} else {
				ClienteUAGenerar clienteGenerar = new ClienteUAGenerar();

				clienteGenerar.setCodigoPais(pais.getCodigo());
				clienteGenerar.setCodigoCliente(clienteUAForm.getCodigo());
				clienteGenerar.setTipoDireccion(clienteUAForm.getcodigoTipoDireccion());
				clienteGenerar.setTipoVia(clienteUAForm.getcodigoTipoVia());
				clienteGenerar.setNombreVia(clienteUAForm.getNombreVia());
				clienteGenerar.setValorNumero(clienteUAForm.getNumeroPrincipal());
				clienteGenerar.setValorInterior(null);
				clienteGenerar.setValorManzana(null);
				clienteGenerar.setValorLote(null);
				clienteGenerar.setValorKilometro(null);
				clienteGenerar.setCodigoUbigeoNivel1(clienteUAForm.getOrden1());
				clienteGenerar.setCodigoUbigeoNivel2(clienteUAForm.getOrden2());
				clienteGenerar.setCodigoUbigeoNivel3(clienteUAForm.getOrden3());
				clienteGenerar.setCodigoUbigeoNivel4(clienteUAForm.getOrden4());
				clienteGenerar.setNivelSocioeconomico("  ");
				clienteGenerar.setValorCoordenadaX(null);
				clienteGenerar.setValorCoordenadaY(null);
				clienteGenerar.setValorCoordenadaZ(null);
				if (StringUtils.isNotBlank(clienteUAForm.getCodigoTerritorio()))
					clienteGenerar.setCodigoTerritorio(new Long(clienteUAForm.getCodigoTerritorio()));
				else
					clienteGenerar.setCodigoTerritorio(null);
				clienteGenerar.setEstadoValidacion(clienteUAForm.getEstadoValidacion());
				clienteGenerar.setCodigoZona(clienteUAForm.getCodigoZona());

				existeRegistro = service.selectClienteUAGenerar(clienteGenerar);
				if (existeRegistro == 0)
					service.insertClienteUAGenerar(clienteGenerar, usuario);
				else
					service.updateClienteUAGenerar(clienteGenerar, usuario);
			}

			this.numeroClientesGenerar = service.getNumeroClientesUAGenerar();

		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			this.getResourceMessage("errors.invalid.id", new Object[]{codigo});
			
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			this.getResourceMessage("errors.invalid.description", new Object[]{descripcion});
		}
	
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		flagBusqueda = "no";
		MantenimientoGEOZonaTerritorioClienteForm clienteUAForm = new MantenimientoGEOZonaTerritorioClienteForm();

		Cliente clienteSeleccionado = (Cliente) this.beanRegistroSeleccionado;
		// Si el id ha sido enviado, buscamos la informacion
		// en caso contrario, no hacemos nada, se esta insertando
		// un nuevo registro a la aplicación
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			String codigoCliente = clienteSeleccionado.getCodigo();
			
			if (codigoCliente != null) 
			{
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoCliente", codigoCliente);


				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoCliente);
				}

				InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

				Cliente cliente = service.getClienteSICCByCodigo(criteria);
				if (log.isDebugEnabled()) {
					Timestamp fechaIng = cliente.getFechaIngreso();
					log.debug("Obteniendo el valor de la fecha..." + fechaIng);
				}
				try {
					BeanUtils.copyProperties(clienteUAForm, cliente);
				} catch (Exception e) {
					log.debug("Atrapamos la exception y continuamos");
					clienteUAForm.setFechaIngreso(null);
				}
				
				clienteUAForm.setNewRecord(false);
				this.flagValidarCampoZona = false;
				this.flagValidarCampoTerritorio = false;
				
				this.statusOpcional = true;
				this.statusValidObl1 = true;
				this.statusValidObl2 = true;
				this.statusValidObl3 = true;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("DISPLAYTAG - Obtenemos el valor de la pagina seleccionada "
					+ clienteUAForm.getPageSelected());
		}
		
		this.mostrarBotonSave = false;

		return clienteUAForm;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		
		this.longitudCampoClientes = svc.getTamanhoNumeroCliente(pais.getCodigo());
		this.numeroClientesGenerar = svc.getNumeroClientesUAGenerar();	
		
		this.salirGrabarPantallaPadre = true;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoGEOZonaTerritorioClienteForm f = (MantenimientoGEOZonaTerritorioClienteForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if(isNew){
			return "";
		}else{
			return "ClienteUA.added";
		}	
	}
	
	public void validateZonaOnEnter()
	{
		MantenimientoGEOZonaTerritorioClienteForm f = (MantenimientoGEOZonaTerritorioClienteForm) this.formMantenimiento;
		String  zona= f.getCodigoZona();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.flagValidarCampoZona = true;
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		int[] resultado = ajax.getStatusZonaByCliente(pais.getCodigo(), zona, f.getCodigo());
		
		if(resultado[0] == 0)
		{
			String ventana = "PF('errorMarca_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventana);
		}
		
		if(resultado[1] == 0)
		{
			this.statusValidObl1 = false;
			String ventana = "PF('confirmDialogValidar_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventana);
		}else
			this.statusValidObl1 = true;
	}
	
	public void validateTerritorioOnEnter()
	{
		MantenimientoGEOZonaTerritorioClienteForm f = (MantenimientoGEOZonaTerritorioClienteForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.flagValidarCampoTerritorio = true;
		
		int[] resultado = ajax.getStatusTerritorioByCliente(pais.getCodigo(), f.getCodigoTerritorio(), f.getCodigoZona(), f.getOrden1(), 
				f.getOrden2(), f.getOrden3(), f.getCodigo());
		
		if (resultado[0] == 0) 
		{
			this.statusValidObl2 = false;
			String ventana = "PF('errorTerritorio_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventana);			
		}
		
		if (resultado[1] == 0) 
		{
			this.statusValidObl3 = false;
			String ventana = "PF('errorTerritorioZona_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventana);						
		}
		
		if(resultado[2] == 0){
			this.statusOpcional = false;
		}
		
		if(resultado[0] == 1)this.statusValidObl2 = true;
		if(resultado[1] == 1)this.statusValidObl3 = true;
		if(resultado[2] == 1)this.statusOpcional = true;
	}
	
	public void aceptar(ActionEvent event)
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
		
		if(accion.equals("territorioCliente"))
		{
			this.getRequestContext().execute("PF('confirmationDialogConfirmarSave').show()");
			return;
		}		
	}
		
	public void validarGuardar(ActionEvent event)
	{
		String mensaje = null;
		MantenimientoGEOZonaTerritorioClienteForm f = (MantenimientoGEOZonaTerritorioClienteForm) this.formMantenimiento;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String ventana = externalContext.getRequestParameterMap().get("parametroVentana");
		
		if(StringUtils.isBlank(f.getOrden1()) || StringUtils.isBlank(f.getOrden2()) || StringUtils.isBlank(f.getOrden3()))
		{
			mensaje = this.getResourceMessage("mantenimientoGEOZonaTerritorioClienteForm.error.direccion");
			this.setMensajeAlertaDefault(mensaje);
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return;
		}		
		
		if(!this.flagValidarCampoZona)
		{
			mensaje = this.getResourceMessage("mantenimientoGEOZonaTerritorioClienteForm.warn.zona");
			this.setMensajeAlertaDefault(mensaje);
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return;			
		}
		
		if(!this.flagValidarCampoTerritorio)
		{
			mensaje = this.getResourceMessage("mantenimientoGEOZonaTerritorioClienteForm.warn.territorio");
			this.setMensajeAlertaDefault(mensaje);
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return;			
		}
		
		//Estado de validaciones
    	if(this.statusValidObl1) f.setEstadoError1(Constants.SI);
    	else f.setEstadoError1(Constants.NO);

    	if(statusValidObl2) f.setEstadoError2(Constants.SI);
    	else f.setEstadoError2(Constants.NO);

    	if(statusValidObl3) f.setEstadoError3(Constants.SI);
    	else f.setEstadoError3(Constants.NO);
    	 	
    	if(this.statusValidObl1 && this.statusValidObl2 && this.statusValidObl3 && this.statusOpcional){
    		f.setEstadoValidacion(Constants.VALIDACION_TOTAL_OK);
    	}
    	else if(this.statusValidObl1 && this.statusValidObl2 && this.statusValidObl3 && !this.statusOpcional){
    		f.setEstadoValidacion(Constants.VALIDACION_PARCIAL_OK);
    	}
    	else if(!(statusValidObl1 && statusValidObl2 && statusValidObl3)){
    		f.setEstadoValidacion(Constants.VALIDACION_TOTAL_ERR);
    	}
    	    	
//		este if hace que salte la ventana de confirmacion que llamara a la ventana de confirmacion del guardar
		if(!this.statusOpcional)
		{
			String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return;
		}
		
		this.getRequestContext().execute("PF('confirmationDialogConfirmarSave').show()");
		return;
	}
	
	public String getFlagBusqueda() {
		return flagBusqueda;
	}

	public void setFlagBusqueda(String flagBusqueda) {
		this.flagBusqueda = flagBusqueda;
	}

	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public Long getNumeroClientesGenerar() {
		return numeroClientesGenerar;
	}

	public void setNumeroClientesGenerar(Long numeroClientesGenerar) {
		this.numeroClientesGenerar = numeroClientesGenerar;
	}

	public Boolean getFlagValidarCampoZona() {
		return flagValidarCampoZona;
	}

	public void setFlagValidarCampoZona(Boolean flagValidarCampoZona) {
		this.flagValidarCampoZona = flagValidarCampoZona;
	}

	public Boolean getFlagValidarCampoTerritorio() {
		return flagValidarCampoTerritorio;
	}

	public void setFlagValidarCampoTerritorio(Boolean flagValidarCampoTerritorio) {
		this.flagValidarCampoTerritorio = flagValidarCampoTerritorio;
	}

	public Boolean getStatusOpcional() {
		return statusOpcional;
	}

	public void setStatusOpcional(Boolean statusOpcional) {
		this.statusOpcional = statusOpcional;
	}

	public Boolean getStatusValidObl1() {
		return statusValidObl1;
	}

	public void setStatusValidObl1(Boolean statusValidObl1) {
		this.statusValidObl1 = statusValidObl1;
	}

	public Boolean getStatusValidObl2() {
		return statusValidObl2;
	}

	public void setStatusValidObl2(Boolean statusValidObl2) {
		this.statusValidObl2 = statusValidObl2;
	}

	public Boolean getStatusValidObl3() {
		return statusValidObl3;
	}

	public void setStatusValidObl3(Boolean statusValidObl3) {
		this.statusValidObl3 = statusValidObl3;
	}
}
