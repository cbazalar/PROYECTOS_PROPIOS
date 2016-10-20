package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTOMatrizDescuentoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOMatrizDescuentoForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOMatrizDescuentoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoDTOMatrizDescuentoAction extends BaseMantenimientoSearchAbstractAction 
{
	private static final long serialVersionUID = -8971049025564935987L;

	private List listaMatriz;
	private List listaCategoria;
	private List listaSubCategoria1;
	private LabelValue[] listaSubCategoria2;
	private List listaGDescuento;

	private List listaNegocio;
	private List listaOferta;
	private List listaUnidadNegocio;

	LabelValue[] categoria1;

	private Boolean opcion1;
	private Boolean opcion2;
	private Boolean opcion3;
	private Boolean opcion4;
	private Boolean opcion5;
	private Boolean opcion6; 

	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupBuscar= false;
	private static final String POPUP_BUSCAR_PRODUTOS = "MATRIZDESC";
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoDTOMatrizDescuentoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoDTOMatrizDescuentoForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoDTOMatrizDescuentoSearchForm objForm = new MantenimientoDTOMatrizDescuentoSearchForm();
		return objForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoDTOMatrizDescuentoSearchForm f = (MantenimientoDTOMatrizDescuentoSearchForm) this.formBusqueda;
		MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) this
				.getBean("spusicc.mantenimientoDTOMatrizDescuentoService");

		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);

		/* Obteniendo Lista */
		this.listaMatriz = service.getListMatrizDescuento(criteria);
		return this.listaMatriz;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map bean = (HashMap) this.beanRegistroSeleccionado;
		MantenimientoDTOMatrizDescuentoForm obj = new MantenimientoDTOMatrizDescuentoForm();

		BeanUtils.copyProperties(obj, bean);

		String id = obj.getCodigoCategoria();
		log.debug("row id " + id);
		if (id != null) {
			try {
				MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) this
						.getBean("spusicc.mantenimientoDTOMatrizDescuentoService");

				bean.put("codigoUsuario", usuario.getLogin());
				service.deleteMatrizDescuento(bean);

			} catch (Exception e) {
				String error = e.getMessage();
				this.addError("Error: ", this.getResourceMessage(
						"errors.detail", new Object[] { error }));
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
		MantenimientoDTOMatrizDescuentoForm f = (MantenimientoDTOMatrizDescuentoForm) this.formMantenimiento;

		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());

		try {
			/* INI SA PER-SiCC-2013-0268 */
			if (f.getCodigoCategoria().equals("0")) { // Producto
				AjaxService aSvc = (AjaxService) getBean("ajaxService");

				params.put("subCategoria1", aSvc.validarCodigoSAP(f.getCodigoPais(), f.getCodigoProducto()));
				params.put("subCategoria2", "0");
			}
			/* FIN SA PER-SiCC-2013-0268 */
			if (f.getCodigoCategoria().equals("1")) { // Tipo/SubTipo Cliente
				params.put("subCategoria1", f.getOidTipoCliente());
				if (f.getOidSubTipoCliente() != null
						&& !f.getOidSubTipoCliente().equals(""))
					params.put("subCategoria2", f.getOidSubTipoCliente());
				else
					params.put("subCategoria2", "0");
			}
			if (f.getCodigoCategoria().equals("2")) { // Tipo de Oferta
				params.put("subCategoria1", f.getOidTipoOferta());
				params.put("subCategoria2", "0");
			}
			if (f.getCodigoCategoria().equals("3")) { // Negocio/Unidad de
														// Negocio
				if (f.getOidNegocio() != null && !f.getOidNegocio().equals(""))
					params.put("subCategoria1", f.getOidNegocio());
				else
					params.put("subCategoria1", "0");

				if (f.getOidUnidadNegocio() != null
						&& !f.getOidUnidadNegocio().equals(""))
					params.put("subCategoria2", f.getOidUnidadNegocio());
				else
					params.put("subCategoria2", "0");
			}
			if (f.getCodigoCategoria().equals("4")) { // Ninguno
				params.put("subCategoria1", "0");
				params.put("subCategoria2", "0");
			}

			// Valida que no exista la combinacion =
			// cod_cate+sub_cat1+sub_cat2+est_regi=1
			if (service.existeMatrizDescuento(params)) {

				this.addError("Error: ", this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.existeMatrizDescuento"));
				return false;
			}

			if (f.isNewRecord()) {
				service.insertMatrizDescuento(params);// inserta
			} else {
				service.updateMatrizDescuento(params);// upadte
			}

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));

			return false;
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoDTOMatrizDescuentoForm f = new MantenimientoDTOMatrizDescuentoForm();
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoCategoria("1");
		cargarTodo(f.getCodigoCategoria());
		
		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			Map bean = (HashMap) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, bean);

			String id = f.getCodigoCategoria();

			log.debug("row id " + id);
			if (id != null) 
			{
				try {
					// List
					// list=(List)session.getAttribute(Constants.DTO_MATRIZ_DESCUENTO_LIST);
					log.debug("map " + bean);
					f.setCodigoPais(String.valueOf(bean.get("codigoPais")));
					f.setCodigoGrupo(String.valueOf(bean.get("codigoGrupo")));
					f.setCodigoCategoria(String.valueOf(bean.get("codigoCategoria")));
					f.setSubCategoria1(String.valueOf(bean.get("subCategoria1")));
					f.setSubCategoria2(String.valueOf(bean.get("subCategoria2")));

					if (f.getCodigoCategoria().equals("1")) 
					{ // Tipo/SubTipo Cliente
						f.setOidTipoCliente(f.getSubCategoria1());
						f.setOidSubTipoCliente(f.getSubCategoria2());
					}
					
					if (f.getCodigoCategoria().equals("2")) { // Tipo de Oferta
						f.setOidTipoOferta(f.getSubCategoria1());
					}
					
					if (f.getCodigoCategoria().equals("3")) { // Negocio/Unidad
																// de Negocio
						f.setOidNegocio(f.getSubCategoria1());
						f.setOidUnidadNegocio(f.getSubCategoria2());
					}

					cargarCombos();
					cargarTodo(f.getCodigoCategoria());

					AjaxService aSvc = (AjaxService) getBean("ajaxService");
					ArrayList temp = new ArrayList();
					temp = new ArrayList();
					temp.add(f.getOidTipoCliente());

					this.listaSubCategoria2 = aSvc
							.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), temp);
					/* INI SA PER-SiCC-2013-0268 */
					if (f.getCodigoCategoria().equals("0")) { // Codigo Producto
																// y Descripcion
																// Producto
						String datosProducto = String.valueOf(bean.get("descripcionSubCategoria1"));
						int posicion = datosProducto.indexOf(" - ");
						f.setCodigoProducto(datosProducto.substring(0, posicion));
						f.setDescripcionProducto(datosProducto.substring(posicion + 3));
					}
					/* FIN SA PER-SiCC-2013-0268 */
					
					f.setNewRecord(false);

					log.debug("enviando para editar");
				} catch (Exception e) {
					String error = e.getMessage();
					this.addError("Error: ", this.getResourceMessage(
							"errors.detail", new Object[] { error }));

				}
			}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoDTOMatrizDescuentoSearchForm f = (MantenimientoDTOMatrizDescuentoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		cargarCombos();
		f.setCodigoPais(pais.getCodigo());
		// f.setCodigoCategoria("1");
		this.mostrarBotonConsultar = false;

	}

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_BUSCAR_PRODUTOS)) 
		{
			this.busquedaProductoSearchAction.verificarRegistro(event);
			Map seleccionado = (HashMap) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado();
			MantenimientoDTOMatrizDescuentoForm f = (MantenimientoDTOMatrizDescuentoForm) this.formMantenimiento;
			String codigoProducto = seleccionado.get("codigoSap")==null? "":seleccionado.get("codigoSap").toString();
			String descripcionProducto = seleccionado.get("descripcionCorta")==null? "":seleccionado.get("descripcionCorta").toString();
			
			f.setCodigoProducto(codigoProducto);
			f.setDescripcionProducto(descripcionProducto);
			this.mostrarPopupBuscar = false;
			this.busquedaProductoSearchAction.setListaBusqueda(null);
			this.busquedaProductoSearchAction.setDatatableBusqueda(null);
			this.setBeanRegistroSeleccionado(null);
			this.busquedaProductoSearchAction.limpiarSalir();
						
		}
	}
	
	@Override
	protected void setInvocarPopup(String accion) 
	{
		this.mostrarProcesoBatch = false;
		
		if (accion.equals(this.POPUP_BUSCAR_PRODUTOS)) {
			this.mostrarPopupBuscar = true;
		}		
	}

	@Override
	protected void setSalirPopup() 
	{
		this.mostrarPopupBuscar = false;
		this.busquedaProductoSearchAction.setListaBusqueda(null);
		this.busquedaProductoSearchAction.setDatatableBusqueda(null);
		this.setBeanRegistroSeleccionado(null);
		this.busquedaProductoSearchAction.limpiarSalir();		
	}
		
	/**
	 * 
	 */
	private void cargarCombos() {
		try {
			MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) getBean("spusicc.mantenimientoDTOMatrizDescuentoService");

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			this.listaGDescuento = service.getGruposDescuento(null);
			this.listaCategoria = service.getCategoriasDescuento(null);

			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

			this.listaSubCategoria1 = interfazSiCCService
					.getTiposClientesByCodigoISOOID(usuario.getIdioma()
							.getCodigoISO());

			this.listaOferta = service.getTiposOferta(null);

			this.listaNegocio = service.getNegocios(null);
			this.listaUnidadNegocio = service.getUnidadesNegocio(null);
			LabelValue[] limpia = {};
			this.listaSubCategoria2 = limpia;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		// session.setAttribute(Constants.SICC_SUB_TIPO_CLIENTE_LIST, new
		// ArrayList());
	}

	/**
	 * Carga los combos
	 * 
	 * @param evt
	 */
	public void cargarTodo(ValueChangeEvent obj) 
	{
		if (obj == null) {
			return;
		}
		try {
			if (obj.getNewValue() == null) {
				this.opcion1 = false;
				this.opcion4 = false;
				this.opcion3 = false;
				this.opcion5 = false;
				this.opcion2 = false;
				this.opcion6 = false;
				return;
			}
			
			String valor = obj.getNewValue().toString();

			if (valor.equals("4")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion3 = false;
				this.opcion5 = false;
				this.opcion2 = true;
				this.opcion6 = false;
			}
			if (valor.equals("3")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion5 = false;
				this.opcion3 = true;
				this.opcion6 = false;
			}
			if (valor.equals("1")) {
				this.opcion4 = true;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion5 = false;
				this.opcion6 = false;
				this.opcion3 = false;
			}
			if (valor.equals("2")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion3 = false;
				this.opcion5 = true;
				this.opcion6 = false;
			}
			if(valor.equals("0")){
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion3 = false;
				this.opcion5 = false;
				this.opcion6 = true;				
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void cargarTodo(String valor) {

		try {
			if (StringUtils.isBlank(valor)) {
				this.opcion1 = false;
				this.opcion4 = false;
				this.opcion3 = false;
				this.opcion5 = false;
				this.opcion2 = false;
				this.opcion6 = false;
				return;
			}

			if (valor.equals("4")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion3 = false;
				this.opcion5 = false;
				this.opcion2 = true;
				this.opcion6 = false;
			}
			if (valor.equals("3")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion5 = false;
				this.opcion3 = true;
				this.opcion6 = false;
			}
			if (valor.equals("1")) {
				this.opcion4 = true;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion5 = false;
				this.opcion6 = false;
				this.opcion3 = false;
			}
			if (valor.equals("2")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion3 = false;
				this.opcion5 = true;
				this.opcion6 = false;
			}
			if (valor.equals("0")) {
				this.opcion4 = false;
				this.opcion1 = true;
				this.opcion2 = false;
				this.opcion3 = false;
				this.opcion5 = false;
				this.opcion6 = true;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void searchProductoOnEnter()
	{
		System.out.println("---------------------------------entro-------------------------------------------");
		MantenimientoDTOMatrizDescuentoForm f = (MantenimientoDTOMatrizDescuentoForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		f.setCodigoProducto(f.getCodigoProducto().trim());
		String resultado = ajax.getDescripcionInternacionalizableProducto(f.getCodigoProducto());
		if(StringUtils.isNotBlank(resultado))
		{
			f.setDescripcionProducto(resultado);
		}else
		{
			this.addError("Error: ", this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.producto.no.existe"));
			f.setDescripcionProducto("");
			this.getRequestContext().execute("PrimeFaces.focus('codigoProducto')");
		}
	}
	
	private String cargarDescripcionProducto(String valor)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String resultado = ajax.getDescripcionInternacionalizableProducto(valor);
		return resultado;
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		MantenimientoDTOMatrizDescuentoForm f = (MantenimientoDTOMatrizDescuentoForm) this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getCodigoCategoria())){
			String mensaje = "'Categoria' es un campo Requerido.";
			return mensaje;
		}
		
		if(StringUtils.isBlank(f.getCodigoGrupo())){
			String mensaje = "'Grupo Descuento' es un campo Requerido.";
			return mensaje;
		}
		
		if(!f.getCodigoCategoria().equals("4"))
		{
			if(f.getCodigoCategoria().equals("0")) {
    			String codigoProducto = f.getCodigoProducto();
    			if(StringUtils.isBlank(codigoProducto)) {
    				String mensaje = this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.subCategoria1Requerido");
    				this.getRequestContext().execute("PrimeFaces.focus('codigoProducto')");
    				return mensaje;
    			}
    			
    			f.setDescripcionProducto(cargarDescripcionProducto(f.getCodigoProducto()));
    	    	
    	    	String descripcionProducto = f.getDescripcionProducto();
    	    	if(StringUtils.isBlank(descripcionProducto)) {
    	    		String mensaje = this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.producto.no.existe");
    				f.setDescripcionProducto("");
    				this.getRequestContext().execute("PrimeFaces.focus('codigoProducto')");
    	    		return mensaje;
    	    	}
    		}
			
			if(f.getCodigoCategoria().equals("1")) 
			{
    			String oidTipoCliente = f.getOidTipoCliente();
    			if(StringUtils.isBlank(oidTipoCliente)) {
    				String mensaje = this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.subCategoria1Requerido");
    				return mensaje;
    			}
    		}
			
			if(f.getCodigoCategoria().equals("2")) 
			{
    			String oidTipoOferta = f.getOidTipoOferta();
    			if(StringUtils.isBlank(oidTipoOferta)) {
    				String mensaje = this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.subCategoria1Requerido");
    				return mensaje;
    			}
    		}
			
			if(f.getCodigoCategoria().equals("3")) {
    			String oidNegocio = f.getOidNegocio();
    			String oidUnidadNegocio = f.getOidUnidadNegocio();
    			if(StringUtils.isBlank(oidNegocio) && StringUtils.isBlank(oidUnidadNegocio)) {
    				String mensaje = this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.negocioOUnidadNegocioRequerido");
    				return mensaje;
    			}
    		}			
		}		
		
		return null;
	}

	/**
	 * Carga SubTipos Clientes Por Pais Tipo Clientes OID
	 * 
	 * @param obj
	 */
	public void cargarSubCategoria(ValueChangeEvent obj) {
		String valor = (String) obj.getNewValue();
		if (StringUtils.isBlank(valor)) {
			this.listaSubCategoria2 = null;
			return;
		}
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			ArrayList lista = new ArrayList();
			lista.add(valor);
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			LabelValue[] subCliente = ajax.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), lista);
			this.listaSubCategoria2 = subCliente;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {

		if (this.accion.equals(this.ACCION_NUEVO)) {
			return "mantenimientoDTOMatrizDescuentoForm.insert";
		} else {
			return "mantenimientoDTOMatrizDescuentoForm.update";
		}
	}

	/**
	 * @return the opcion1
	 */
	public Boolean getOpcion1() {
		return opcion1;
	}

	/**
	 * @param opcion1
	 *            the opcion1 to set
	 */
	public void setOpcion1(Boolean opcion1) {
		this.opcion1 = opcion1;
	}

	/**
	 * @return the opcion2
	 */
	public Boolean getOpcion2() {
		return opcion2;
	}

	/**
	 * @param opcion2
	 *            the opcion2 to set
	 */
	public void setOpcion2(Boolean opcion2) {
		this.opcion2 = opcion2;
	}

	/**
	 * @return the opcion3
	 */
	public Boolean getOpcion3() {
		return opcion3;
	}

	/**
	 * @param opcion3
	 *            the opcion3 to set
	 */
	public void setOpcion3(Boolean opcion3) {
		this.opcion3 = opcion3;
	}

	/**
	 * @return the opcion4
	 */
	public Boolean getOpcion4() {
		return opcion4;
	}

	/**
	 * @param opcion4
	 *            the opcion4 to set
	 */
	public void setOpcion4(Boolean opcion4) {
		this.opcion4 = opcion4;
	}

	/**
	 * @return the opcion5
	 */
	public Boolean getOpcion5() {
		return opcion5;
	}

	/**
	 * @param opcion5
	 *            the opcion5 to set
	 */
	public void setOpcion5(Boolean opcion5) {
		this.opcion5 = opcion5;
	}
	

	/**
	 * @return the listaNegocio
	 */
	public List getListaNegocio() {
		return listaNegocio;
	}

	/**
	 * @param listaNegocio
	 *            the listaNegocio to set
	 */
	public void setListaNegocio(List listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	/**
	 * @return the listaOferta
	 */
	public List getListaOferta() {
		return listaOferta;
	}

	/**
	 * @param listaOferta
	 *            the listaOferta to set
	 */
	public void setListaOferta(List listaOferta) {
		this.listaOferta = listaOferta;
	}

	/**
	 * @return the listaUnidadNegocio
	 */
	public List getListaUnidadNegocio() {
		return listaUnidadNegocio;
	}

	/**
	 * @param listaUnidadNegocio
	 *            the listaUnidadNegocio to set
	 */
	public void setListaUnidadNegocio(List listaUnidadNegocio) {
		this.listaUnidadNegocio = listaUnidadNegocio;
	}

	/**
	 * @return the categoria1
	 */
	public LabelValue[] getCategoria1() {
		return categoria1;
	}

	/**
	 * @param categoria1
	 *            the categoria1 to set
	 */
	public void setCategoria1(LabelValue[] categoria1) {
		this.categoria1 = categoria1;
	}

	/**
	 * @return the listaMatriz
	 */
	public List getListaMatriz() {
		return listaMatriz;
	}

	/**
	 * @param listaMatriz
	 *            the listaMatriz to set
	 */
	public void setListaMatriz(List listaMatriz) {
		this.listaMatriz = listaMatriz;
	}

	/**
	 * @return the listaCategoria
	 */
	public List getListaCategoria() {
		return listaCategoria;
	}

	/**
	 * @param listaCategoria
	 *            the listaCategoria to set
	 */
	public void setListaCategoria(List listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	/**
	 * @return the listaSubCategoria1
	 */
	public List getListaSubCategoria1() {
		return listaSubCategoria1;
	}

	/**
	 * @param listaSubCategoria1
	 *            the listaSubCategoria1 to set
	 */
	public void setListaSubCategoria1(List listaSubCategoria1) {
		this.listaSubCategoria1 = listaSubCategoria1;
	}

	/**
	 * @return the listaSubCategoria2
	 */
	public LabelValue[] getListaSubCategoria2() {
		return listaSubCategoria2;
	}

	/**
	 * @param listaSubCategoria2
	 *            the listaSubCategoria2 to set
	 */
	public void setListaSubCategoria2(LabelValue[] listaSubCategoria2) {
		this.listaSubCategoria2 = listaSubCategoria2;
	}

	/**
	 * @return the listaGDescuento
	 */
	public List getListaGDescuento() {
		return listaGDescuento;
	}

	/**
	 * @param listaGDescuento
	 *            the listaGDescuento to set
	 */
	public void setListaGDescuento(List listaGDescuento) {
		this.listaGDescuento = listaGDescuento;
	}

	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	public boolean isMostrarPopupBuscar() {
		return mostrarPopupBuscar;
	}

	public void setMostrarPopupBuscar(boolean mostrarPopupBuscar) {
		this.mostrarPopupBuscar = mostrarPopupBuscar;
	}

	public static String getPopupBuscarProdutos() {
		return POPUP_BUSCAR_PRODUTOS;
	}

	public Boolean getOpcion6() {
		return opcion6;
	}

	public void setOpcion6(Boolean opcion6) {
		this.opcion6 = opcion6;
	}
}