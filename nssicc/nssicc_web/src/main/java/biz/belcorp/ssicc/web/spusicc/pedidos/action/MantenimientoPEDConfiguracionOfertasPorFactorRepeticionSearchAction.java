package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchAction
		extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private static final String TIPO_NIVEL_PRECIO = "Niveles de Precios";
	
	private List pedOfertaFactorRepeticionCatalogoList;
	private List pedOfertaFactorRepeticionList;
	private Boolean mostrarSegundaGrilla;
	public Boolean nuevo;
	public Boolean editar;
	private List pedOfertasPorFactorRepeticionRangosList;
	private List ssiccCatalogoList;
	private Object beanRangosList;
	private DataTableModel dataModelRangosList;
	private DataTableModel dataModelGratisList;
	private DataTableModel dataModelCriterioList;
	private DataTableModel dataModelComponentesList;
	private Boolean mostrarBotonRangoEliminar;
	private String oidOferta;
	private String oidRango;
	private String codigoPeriodo;
	private List pedOfertasPorFactorRepeticionRangosGratisList;
	private MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm;
	private String precioUnitario;
	private String factorRepeticion;
	private Object beanGratisList;
	private Object beanCriterioList;
	private List pedOfertasPorFactorRepeticionRegalosList;
	private Boolean mostrarBotonGratisEliminar;
	private List siccCatalagoList;
	private List pedOfertasPorFactorRepeticionCriteriosList;
	private List pedOfertasPorFactorRepeticionCriteriosProductosList;
	private String tipoRango;
	private String tipoProducto;
	private String oidProductoFinal;
	private Boolean mostrarTodo;
	private Boolean mostrarElPrimero;
	private Boolean mostrarElSegundo;
	private Boolean mostrarBotonEliminarCriterio;
	private Boolean bloquearPrecio;
	private List beanRegistroDetalleComponente;
	
	private boolean activarModificarPrecio = false;
	private boolean activarEjecutarCriterio = true;

	/**
	 * @param event
	 */
	public void bloqueandoPrecio(ValueChangeEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		String valor = (String) event.getNewValue();
		if (StringUtils.isBlank(valor)) {
			this.bloquearPrecio = false;
		} else if (StringUtils
				.equals(valor,
						Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS)) {
			this.bloquearPrecio = false;
		} else if (StringUtils
				.equals(valor,
						Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS)) {
			this.bloquearPrecio = true;

		}
	}

	/**
	 * 
	 */
	public void inicializandoCriterio() {
		this.mostrarElPrimero = false;
		this.mostrarElSegundo = false;
		this.mostrarTodo = false;
		this.mostrarBotonEliminarCriterio = false;
		this.mostrarBotonRangoEliminar = false;
	}

	/**
	 * @param event
	 */
	public void cambiosCriterios(ValueChangeEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) event.getNewValue();
		if (StringUtils.isBlank(valor)) {
			this.mostrarTodo = false;
			this.mostrarElPrimero = false;
			this.mostrarElSegundo = false;
		} else if (StringUtils.equals(valor,
				Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO)) {
			this.mostrarElPrimero = true;
			this.mostrarElSegundo = false;
			this.esNumeroUno = false;

		} else if (StringUtils.equals(valor,
				Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)) {
			this.mostrarElSegundo = true;
			this.mostrarElPrimero = false;
			this.esNumeroUno = true;
		}
	}

	/**
	 * @param evt
	 */
	public void calcularproductos(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'calcularproductos' method");
		}

		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
		String mensaje = "";
		try {
			executeCalcularProductos(editForm, service);
			mensaje = this
					.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.criterio.productos.calculados");
			this.addInfo("Info : ", mensaje);
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
		}
		editForm.setMostrarDetalles(true);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminarcriterio(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarcriterio' method");
		}
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
		HashMap objetosCriteriosSeleccionados = (HashMap) this.beanCriterioList;
		List criterioList = this.pedOfertasPorFactorRepeticionCriteriosList;
		String oidCriterio = objetosCriteriosSeleccionados.get("oidCriterio")
				.toString();
		String mensaje = "";
		try {

			if (!StringUtils.isBlank(oidCriterio)) {

				for (int i = 0; i < this.pedOfertasPorFactorRepeticionCriteriosList
						.size(); i++) {

					HashMap objetoMarcado = (HashMap) this.pedOfertasPorFactorRepeticionCriteriosList
							.get(i);
					String oidCriterioMarcado = objetoMarcado
							.get("oidCriterio").toString();
					if (StringUtils.equals(oidCriterioMarcado, oidCriterio)) {
						criterioList.remove(i);

						// Eliminados de BD
						String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
						service.removeCriterioOfertaFactorRepeticion(oidCriterio, codigoUsuario);
						//
						break;
					}

				}
			}
			this.pedOfertasPorFactorRepeticionCriteriosList = criterioList;
			int tamanio = this.pedOfertasPorFactorRepeticionCriteriosList
					.size();
			if (tamanio == 0) {
				this.mostrarBotonEliminarCriterio = false;

			}

			this.dataModelCriterioList = new DataTableModel(
					this.pedOfertasPorFactorRepeticionCriteriosList);
			// Invocamos al proceso de calculo de productos
			executeCalcularProductos(editForm, service);
			//
			mensaje = this
					.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.criterio.eliminado");
			this.addInfo("Error : ", mensaje);
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));

		}
		editForm.setMostrarDetalles(true);

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void agregarcriterio(ActionEvent evt) {
		try {
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String validar=validarAgregarcriterio();
		if(StringUtils.isNotBlank(validar)){
			this.addError("Error:", validar);
			return;
		}		
		
		List productosSeleccionados = this.beanRegistroDetalleComponente;

		List criterioList = this.pedOfertasPorFactorRepeticionCriteriosList;
		if (criterioList == null)
			criterioList = new ArrayList();	
		
			
			Map criterio = new HashMap();
			criterio.put("oidOferta", editForm.getOidOferta());
			criterio.put("oidCatalogoCriterio",	editForm.getOidCatalogoCriterio());
			criterio.put("codigoTipoRango", editForm.getCodigoTipoRango());
			criterio.put("numeroPaginaInicial",editForm.getNumeroPaginaInicial());
			criterio.put("numeroPaginaFinal", editForm.getNumeroPaginaFinal());

			if (!this.esNumeroUno) {
				editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);
				criterio.put("indicadorExclusionRango",
						editForm.getIndicadorExclusionRango());
			}
			if (this.esNumeroUno) {
				editForm.setIndicadorExclusionRango(Constants.NUMERO_UNO);
				criterio.put("indicadorExclusionRango",
						editForm.getIndicadorExclusionRango());
			}

			criterio.put("codigoUsuario", usuario.getLogin());			
		
			if(editForm.getCodigoTipoRango().equals(Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
				for (int i = 0; i < productosSeleccionados.size(); i++) {
					Map criterioSeleccionado = (Map) productosSeleccionados.get(i);
					
					this.oidProductoFinal = ajaxService.validarCodigoSAP(editForm.getCodigoPais(), MapUtils.getString(criterioSeleccionado, "codigoSap"));
					criterio.put("oidProducto", this.oidProductoFinal);
					// Insertamos en BD
					service.insertCriterioOfertaFactorRepeticion(criterio);
					//
				}
			}else{
				criterio.put("oidProducto", this.oidProductoFinal);
				service.insertCriterioOfertaFactorRepeticion(criterio);
			}
			
			criterioList = service.getCriterioOfertaFactorRepeticionList(editForm.getOidOferta());
			this.pedOfertasPorFactorRepeticionCriteriosList = criterioList;
			int tamanio = this.pedOfertasPorFactorRepeticionCriteriosList.size();
			if (tamanio > 0) 
				this.mostrarBotonEliminarCriterio = true;
			
			this.dataModelCriterioList = new DataTableModel(this.pedOfertasPorFactorRepeticionCriteriosList);
			// Invocamos al proceso de calculo de productos
			executeCalcularProductos(editForm, service);
			//
			String mensaje = this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.criterio.agregado");
			this.addInfo("Info:", mensaje);

			editForm.setOidCatalogoCriterio("");
			editForm.setCodigoTipoRango("");
			editForm.setNumeroPaginaInicial("");
			editForm.setNumeroPaginaFinal("");
			editForm.setCodigoProducto("");
			editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);
			editForm.setMostrarDetalles(true);
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));

		}

		

	}
	
	private String validarAgregarcriterio(){
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		if(StringUtils.isBlank(editForm.getOidCatalogoCriterio()))
			return "Catálogo: Campo requerido";		
		
		if(StringUtils.isBlank(editForm.getCodigoTipoRango()))
			return "Tipo de Rango: Campo requerido";
		
		if(StringUtils.equals(editForm.getCodigoTipoRango(), this.tipoRango)){
			if(StringUtils.isBlank(editForm.getNumeroPaginaInicial()))
				return "Pagina Inicial: Campo requerido";
			
			if(StringUtils.isBlank(editForm.getNumeroPaginaFinal()))
				return "Pagina Final: Campo requerido";

		}
		
		if(StringUtils.equals(editForm.getCodigoTipoRango(), this.tipoProducto)){
			this.oidProductoFinal=null;
			if (StringUtils.isNotBlank(editForm.getCodigoProducto())) {
				// validamos si el producto ingresado existe
				this.oidProductoFinal = ajaxService.validarCodigoSAP(editForm.getCodigoPais(),editForm.getCodigoProducto());
				if (StringUtils.isBlank(this.oidProductoFinal)) {
					editForm.setMostrarDetalles(true);
					String mensaje=this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.error.criterio.producto.noexiste",
									new String[] { editForm.getCodigoProducto() });
					return mensaje;
				}
			}
		}
		
		return null;
	}
	private void executeCalcularProductos(
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm,
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map params = new HashMap();
		params.put("oidOferta", editForm.getOidOferta());
		params.put("codigoPeriodo", editForm.getCodigoPeriodo());
		params.put("codigoUsuario", usuario.getLogin());

		List productoList = new ArrayList();
		productoList = service.executeCalcularProductos(params);
		this.pedOfertasPorFactorRepeticionCriteriosProductosList = productoList;
		this.dataModelComponentesList = new DataTableModel(
				this.pedOfertasPorFactorRepeticionCriteriosProductosList);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showgratis(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'showgratis' method");
		}
		// HashMap objetoSeleccionado = (HashMap) this.beanRangosList;
		try {
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			this.oidOferta = externalContext.getRequestParameterMap()
					.get("oidOferta").toString();
			this.oidRango = externalContext.getRequestParameterMap()
					.get("oidRango").toString();
			this.factorRepeticion = externalContext.getRequestParameterMap()
					.get("factorRepeticion").toString();
			this.precioUnitario = externalContext.getRequestParameterMap()
					.get("precioUnitario").toString();

			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			this.gratisForm = new MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm();

			if (log.isDebugEnabled())
				log.debug("oidOferta: " + oidOferta);

			Map params = new HashMap();
			params.put("oidOferta", this.oidOferta);
			params.put("oidRango", this.oidRango);
			List rangos = service.getRangoOfertaFactorRepeticionList(params);

			if (rangos != null && rangos.size() > 0) {
				Map rango = (Map) rangos.get(0);

				BeanUtils.copyProperties(gratisForm, rango);
			}

			// Lista
			List gratis = service
					.getRangoGratisOfertaFactorRepeticionList(params);
			this.pedOfertasPorFactorRepeticionRangosGratisList = gratis;
			this.dataModelGratisList = new DataTableModel(
					this.pedOfertasPorFactorRepeticionRangosGratisList);
			String ventana = "PF('viewmantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisPopup').show()";
			this.getRequestContext().execute(ventana);
			// this.mostrarBotonSalir = false;
			this.mostrarBotonSave = false;
			this.codigoPeriodo = editForm.getCodigoPeriodo();
			gratisForm.setCodigoPeriodo(this.codigoPeriodo);
			gratisForm.setOidRango(this.oidRango);

			gratisForm.setCuv("");
			gratisForm.setUnidades("");

			this.mostrarBotonGratisEliminar = this
					.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRangosGratisList);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminarrango(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarrango' method");
		}

		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");

		List rangoList = this.pedOfertasPorFactorRepeticionRangosList;
		HashMap objetoSeleccionado = (HashMap) this.beanRangosList;

		String mensaje = "";
		String oidRango = objetoSeleccionado.get("oidRango").toString();
		try {

			if (!StringUtils.isBlank(oidRango)) {
				for (int i = 0; i < rangoList.size(); i++) {

					HashMap objetoMarcado = (HashMap) rangoList.get(i);

					String oidRangoMarcado = objetoMarcado.get("oidRango")
							.toString();
					if (StringUtils.equals(oidRangoMarcado, oidRango)) {
						rangoList.remove(i);
						String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
						
						// Eliminados de BD
						service.removeRangoOfertaFactorRepeticion(oidRango, codigoUsuario);
						//

						break;
					}

				}
			}
			this.pedOfertasPorFactorRepeticionRangosList = rangoList;
			int tamanio = this.pedOfertasPorFactorRepeticionRangosList.size();
			this.mostrarBotonRangoEliminar = true;
			if (tamanio == 0) {
				this.mostrarBotonRangoEliminar = false;
			}
			this.dataModelRangosList = new DataTableModel(
					this.pedOfertasPorFactorRepeticionRangosList);
			mensaje = this
					.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.rango.eliminado");
			this.addInfo("Info : ", mensaje);
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
		}
		editForm.setMostrarDetalles(true);

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void agregarrango(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarrango' method");
		}
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");

		List rangoList = this.pedOfertasPorFactorRepeticionRangosList;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoTipoPrograma = editForm.getCodigoTipoPrograma();
		
		if (rangoList == null)
			rangoList = new ArrayList();
		String mensaje = "";
		try {
			
			if(StringUtils.isBlank(editForm.getFactorRepeticion()))
			{
				mensaje = "Factor Repetición es un campo requerido";
				this.addError("Error : ", mensaje);

				return;
			}
			
			if (codigoTipoPrograma.equals(Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS)) {
				if(StringUtils.isBlank(editForm.getPrecioUnitario()))
				{
					mensaje = "Precio Unitario es un campo requerido";
					this.addError("Error : ", mensaje);
	
					return;
				}
			}

			Map rango = new HashMap();

			rango.put("oidOferta", editForm.getOidOferta());
			rango.put("factorRepeticion", editForm.getFactorRepeticion());
			rango.put("precioUnitario", editForm.getPrecioUnitario());
			rango.put("codigoUsuario", usuario.getLogin());

			// Insertamos en BD
			service.insertRangoOfertaFactorRepeticion(rango);
			//

			rangoList.add(rango);

			this.pedOfertasPorFactorRepeticionRangosList = rangoList;
			int tamanio = this.pedOfertasPorFactorRepeticionRangosList.size();
			if (tamanio > 0) {
				this.mostrarBotonRangoEliminar = true;

			}
			this.dataModelRangosList = new DataTableModel(this.pedOfertasPorFactorRepeticionRangosList);
			mensaje = this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.rango.agregado");

			editForm.setFactorRepeticion("");
			editForm.setPrecioUnitario("");
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
		}

		editForm.setMostrarDetalles(true);

	}
	
	
	/**
	 * @param evt
	 */
	public void modificarRango() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'modificarRango' method");
		}
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");

		List rangoList = this.pedOfertasPorFactorRepeticionRangosList;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		if (rangoList == null) {
			return;
		}
		
		String retorno = service.updateRangoOfertaFactorRepeticion(rangoList, editForm.getOidOferta(), usuario.getLogin());
		if (StringUtils.isNotBlank(retorno)) 
			throw new Exception(retorno);
		return;
	}
	

	public void guardar(ActionEvent evt) {
		try {
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Map params = BeanUtils.describe(editForm);
			params.put("codigoUsuario", usuario.getLogin());
			String mensaje = "";
			if (editForm.isNewRecord()) {
				params.put("codigoTipoCuadre", Constants.NUMERO_UNO);
				service.insertOfertaFactorRepeticion(params);
				editForm.setOidOferta(MapUtils.getString(params, "oidOferta"));
				editForm.setNewRecord(false);
				this.mostrarSegundaGrilla = true;
				mensaje = this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.oferta.grabada");
			} else {
				if (this.activarModificarPrecio)
					modificarRango();
				
				service.updateOfertaFactorRepeticion(params);
				mensaje = this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm.oferta.actualizada");
			}
			editForm.setMostrarDetalles(true);
			this.addInfo("Info : ", mensaje);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 * Devuelve Lista
	 */
	public List buscar() {
		List lista = new ArrayList();
		try {
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm searchForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm) this.formBusqueda;
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");

			Map params = BeanUtils.describe(searchForm);

			lista = service.getOfertaFactorRepeticionList(params);
			this.pedOfertaFactorRepeticionList = lista;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		return lista;
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDConfiguracionOfertasPorFactorRepeticionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm form = new MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		List lista = buscar();

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		HashMap objetoSeleccionado = (HashMap) this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String oidOferta = objetoSeleccionado.get("oidOferta").toString();
		String codigoUsuario = usuario.getLogin();
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
		service.removeOfertaFactorRepeticion(oidOferta, codigoUsuario);
		this.listaBusqueda = new ArrayList();
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	private Boolean esNumeroUno;
	private Boolean esNumeroCero;

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		inicializandoCriterio();
		this.mostrarBotonSave = false;
		this.activarModificarPrecio = false;
		this.activarEjecutarCriterio = true;
		
		this.tipoProducto = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
		this.tipoRango = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm form = new MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm();
		form.setIndicadorExclusionRango(Constants.NUMERO_UNO);
		this.esNumeroUno = false;
		this.esNumeroCero = false;
		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.nuevo = true;
		
		this.pedOfertasPorConcursoTipoProgramaNivelesPrecios = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
		this.pedOfertasPorConcursoTipoProgramaNivelesConcursos = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;

		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) form;
		editForm.setNewRecord(true);

		editForm.setCodigoPais(pais.getCodigo());
		Map params = BeanUtils.describe(editForm);

		// Moneda
		Map moneda = service.getMoneda(params);
		editForm.setNumeroDecimales(MapUtils.getString(moneda,
				"numeroDecimales"));

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccCatalagoList = reporteService.getListaGenerico(
				"getOidCatalogoProductos", null);

		this.ssiccCatalogoList = reporteService.getListaGenerico(
				"getOidCatalogoProductos", null);
		this.gratisForm = new MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm();
		editForm.setCodigoPeriodo("");
		
		this.pedOfertasPorFactorRepeticionRangosList = new ArrayList();
		this.dataModelRangosList = new DataTableModel(
				this.pedOfertasPorFactorRepeticionRangosList);
		
		this.pedOfertasPorFactorRepeticionRegalosList = new ArrayList();
		this.dataModelGratisList = new DataTableModel(
				this.pedOfertasPorFactorRepeticionRegalosList);
		
		this.pedOfertasPorFactorRepeticionCriteriosList = new ArrayList();
		this.dataModelCriterioList = new DataTableModel(pedOfertasPorFactorRepeticionCriteriosList);

		this.pedOfertasPorFactorRepeticionCriteriosProductosList = new ArrayList();			
		this.dataModelComponentesList = new DataTableModel(pedOfertasPorFactorRepeticionCriteriosProductosList);
		if (! StringUtils.equalsIgnoreCase(this.accion, ACCION_NUEVO)) {
			editForm.setNewRecord(false);

			HashMap objetoSeleccionado = (HashMap) this.beanRegistroSeleccionado;

			if (objetoSeleccionado != null) {
				String oidOferta = objetoSeleccionado.get("oidOferta").toString();
				if (!StringUtils.isBlank(oidOferta)) {
					
					String tipoNivel = (String)objetoSeleccionado.get("tipoNivel");
					if (StringUtils.equalsIgnoreCase(tipoNivel, TIPO_NIVEL_PRECIO)) {
						this.activarModificarPrecio = true;
					}
					String numeroLotePlan = (String)objetoSeleccionado.get("numeroLotePlan");
					if (StringUtils.isNotBlank(numeroLotePlan))
						this.activarEjecutarCriterio = false;
					
					Map params2 = new HashMap();
					params2.put("oidOferta", oidOferta);
					params2.put("codigoPais", pais.getCodigo());

					List ofertas = service
							.getOfertaFactorRepeticionList(params2);

					this.mostrarBotonEliminarCriterio = true;
					this.mostrarBotonRangoEliminar = true;
					this.mostrarSegundaGrilla = true;
					Map oferta = (Map) ofertas.get(0);

					BeanUtils.copyProperties(editForm, oferta);

					editForm.setCodigoPais(pais.getCodigo());
					editForm.setCodigoPeriodo(MapUtils.getString(oferta,
							"periodo", ""));
					editForm.setNumeroPagina(MapUtils.getString(oferta,
							"pagina", ""));

					this.pedOfertasPorFactorRepeticionRangosList = service
							.getRangoOfertaFactorRepeticionList(params2);
					this.pedOfertasPorFactorRepeticionCriteriosList = service
							.getCriterioOfertaFactorRepeticionList(oidOferta);
					this.dataModelCriterioList = new DataTableModel(
							this.pedOfertasPorFactorRepeticionCriteriosList);
					this.dataModelRangosList = new DataTableModel(
							this.pedOfertasPorFactorRepeticionRangosList);
					params2.put("codigoPeriodo", editForm.getCodigoPeriodo());
					this.pedOfertasPorFactorRepeticionCriteriosProductosList = service
							.getProductoOfertaFactorRepeticionList1(params2);
					this.dataModelComponentesList = new DataTableModel(
							this.pedOfertasPorFactorRepeticionCriteriosProductosList);
					editForm.setMostrarDetalles(true);
				}

			}
		}else{
			this.mostrarSegundaGrilla = false;
		}

		return editForm;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminargratis(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminargratis' method");
		}
		String mensaje = "";
		try {

			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm = this.gratisForm;
			HashMap objetosGratisSeleccionados = (HashMap) this.beanGratisList;
			if (objetosGratisSeleccionados != null) {
				String id = objetosGratisSeleccionados.get("oidGratis")
						.toString();
				// String[] seleccionados = gratisForm.getSelectedItems();

				if (!StringUtils.isBlank(id)) {
					String oidGratis = id;
					if (log.isDebugEnabled())
						log.debug("oidGratis: " + oidGratis);

					Map params = BeanUtils.describe(gratisForm);
					String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
					service.removeRangoGratisOfertaFactorRepeticion(oidGratis, codigoUsuario);

					// Lista
					List regalos = service
							.getRangoGratisOfertaFactorRepeticionList(params);
					this.pedOfertasPorFactorRepeticionRegalosList = regalos;
				}
				mensaje = this
						.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm.regalo.eliminado");
				this.addInfo("Info : ", mensaje);
				this.dataModelGratisList = new DataTableModel(
						this.pedOfertasPorFactorRepeticionRegalosList);
				this.mostrarBotonGratisEliminar = this
						.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRegalosList);
			} else {
				mensaje = this.getResourceMessage("errors.select.item");
				this.addWarn("Info : ", mensaje);
			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void agregargratis(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregargratis' method");
		}

		String mensaje = "";
		try {

			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService service = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm = this.gratisForm;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			List regaloList = this.pedOfertasPorFactorRepeticionRegalosList;

			if (regaloList == null)
				regaloList = new ArrayList();

			Map regalo = new HashMap();
			regalo.put("oidRango", gratisForm.getOidRango());
			regalo.put("unidades", gratisForm.getUnidades());
			regalo.put("correlativo", regaloList.size() + 1);
			regalo.put("codigoUsuario", usuario.getLogin());
			
			if(StringUtils.isBlank(gratisForm.getCuv()))
			{
				mensaje = "CUV es un campo requerido";
				this.addError("Error : ", mensaje);

				return;
			}
			
			if(StringUtils.isBlank(gratisForm.getUnidades()))
			{
				mensaje = "Unidades a entregar es un campo requerido";
				this.addError("Error : ", mensaje);

				return;
			}

			// Buscamos el CUV
			Map params = new HashMap();
			params.put("codigoPeriodo", gratisForm.getCodigoPeriodo());
			params.put("cuv", gratisForm.getCuv());

			List cuvList = service.getCuvs(params);

			if (cuvList != null && cuvList.size() > 0) {
				Map cuv = (Map) cuvList.get(0);
				regalo.put("oidDetalleOferta",
						MapUtils.getString(cuv, "oidDetalleOferta"));
			} else {
				mensaje = this
						.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm.error.cuv.noexiste");
				this.addError("Error : ", mensaje);

				return;
			}
			
			//

			// Insertamos en BD
			service.insertRangoGratisOfertaFactorRepeticion(regalo);
			//

			regaloList = service
					.getRangoGratisOfertaFactorRepeticionList(regalo);
			this.pedOfertasPorFactorRepeticionRegalosList = regaloList;
			this.dataModelGratisList = new DataTableModel(
					this.pedOfertasPorFactorRepeticionRegalosList);

			this.mostrarBotonGratisEliminar = this
					.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRegalosList);

			gratisForm.setCuv("");
			gratisForm.setUnidades("");

			mensaje = this
					.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm.regalo.agregado");
			this.addInfo("Info : ", mensaje);
		} catch (Exception e) {
			this.mostrarBotonGratisEliminar = this
					.mostrarBotonGratisEliminar(this.pedOfertasPorFactorRepeticionRegalosList);

			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	private String pedOfertasPorConcursoTipoProgramaNivelesPrecios;
	private String pedOfertasPorConcursoTipoProgramaNivelesConcursos;

	public void inicializando() {
		this.mostrarSegundaGrilla = false;
		this.nuevo = false;
		this.editar = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonGratisEliminar = false;
		this.tipoProducto = "";
		this.tipoRango = "";
		this.mostrarBotonEliminar = true;
		this.mostrarBotonRangoEliminar = false;
		this.mostrarBotonConsultar = false;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm searchForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.pedOfertaFactorRepeticionCatalogoList = reporteService
				.getListaGenerico("getCatalogoProductos", null);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.inicializandoCriterio();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
		this.mostrarBotonConsultar = false;
		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService
				.getControlFacturacionById(criteriaPeriodo);

		searchForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		searchForm.setCodigoPais(pais.getCodigo());
		searchForm.setNumeroPagina(null);
		searchForm.setCodigoCatalogo(null);

		this.pedOfertaFactorRepeticionList = new ArrayList();

		List lista = buscar();
		this.listaBusqueda = lista;
		this.datatableBusqueda = new DataTableModel(listaBusqueda);
		this.mostrarListaBusqueda = true;
		this.activarModificarPrecio = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm editForm = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionForm) this.formMantenimiento;
		
		if(accion.equals("INSERTAR_DETALLE_CRITERIO")){
			if (StringUtils.isBlank(editForm.getOidCatalogoCriterio()))
				return "'Catálogo' es un campo requerido";
			if (StringUtils.isBlank(editForm.getCodigoTipoRango()))
				return "'Tipo de Rango' es un campo requerido";
			
			if(StringUtils.equals(editForm.getCodigoTipoRango(), Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO)){
				if (this.beanRegistroDetalleComponente.size() <= 0)
					return this.getResourceMessage("errors.select.item");
			}
		}
		
		return null;
	}

	
	/* GET - SET */
	
	/**
	 * @return the pedOfertaFactorRepeticionCatalogoList
	 */
	public List getPedOfertaFactorRepeticionCatalogoList() {
		return pedOfertaFactorRepeticionCatalogoList;
	}

	/**
	 * @param pedOfertaFactorRepeticionCatalogoList
	 *            the pedOfertaFactorRepeticionCatalogoList to set
	 */
	public void setPedOfertaFactorRepeticionCatalogoList(
			List pedOfertaFactorRepeticionCatalogoList) {
		this.pedOfertaFactorRepeticionCatalogoList = pedOfertaFactorRepeticionCatalogoList;
	}

	/**
	 * @return the pedOfertaFactorRepeticionList
	 */
	public List getPedOfertaFactorRepeticionList() {
		return pedOfertaFactorRepeticionList;
	}

	/**
	 * @param pedOfertaFactorRepeticionList
	 *            the pedOfertaFactorRepeticionList to set
	 */
	public void setPedOfertaFactorRepeticionList(
			List pedOfertaFactorRepeticionList) {
		this.pedOfertaFactorRepeticionList = pedOfertaFactorRepeticionList;
	}

	/**
	 * @return the pedOfertasPorConcursoTipoProgramaNivelesPrecios
	 */
	public String getPedOfertasPorConcursoTipoProgramaNivelesPrecios() {
		return pedOfertasPorConcursoTipoProgramaNivelesPrecios;
	}

	/**
	 * @param pedOfertasPorConcursoTipoProgramaNivelesPrecios
	 *            the pedOfertasPorConcursoTipoProgramaNivelesPrecios to set
	 */
	public void setPedOfertasPorConcursoTipoProgramaNivelesPrecios(
			String pedOfertasPorConcursoTipoProgramaNivelesPrecios) {
		this.pedOfertasPorConcursoTipoProgramaNivelesPrecios = pedOfertasPorConcursoTipoProgramaNivelesPrecios;
	}

	/**
	 * @return the pedOfertasPorConcursoTipoProgramaNivelesConcursos
	 */
	public String getPedOfertasPorConcursoTipoProgramaNivelesConcursos() {
		return pedOfertasPorConcursoTipoProgramaNivelesConcursos;
	}

	/**
	 * @param pedOfertasPorConcursoTipoProgramaNivelesConcursos
	 *            the pedOfertasPorConcursoTipoProgramaNivelesConcursos to set
	 */
	public void setPedOfertasPorConcursoTipoProgramaNivelesConcursos(
			String pedOfertasPorConcursoTipoProgramaNivelesConcursos) {
		this.pedOfertasPorConcursoTipoProgramaNivelesConcursos = pedOfertasPorConcursoTipoProgramaNivelesConcursos;
	}

	/**
	 * @return the mostrarSegundaGrilla
	 */
	public Boolean getMostrarSegundaGrilla() {
		return mostrarSegundaGrilla;
	}

	/**
	 * @param mostrarSegundaGrilla
	 *            the mostrarSegundaGrilla to set
	 */
	public void setMostrarSegundaGrilla(Boolean mostrarSegundaGrilla) {
		this.mostrarSegundaGrilla = mostrarSegundaGrilla;
	}

	/**
	 * @return the nuevo
	 */
	public Boolean getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo
	 *            the nuevo to set
	 */
	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the editar
	 */
	public Boolean getEditar() {
		return editar;
	}

	/**
	 * @param editar
	 *            the editar to set
	 */
	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	/**
	 * @return the pedOfertasPorFactorRepeticionRangosList
	 */
	public List getPedOfertasPorFactorRepeticionRangosList() {
		return pedOfertasPorFactorRepeticionRangosList;
	}

	/**
	 * @param pedOfertasPorFactorRepeticionRangosList
	 *            the pedOfertasPorFactorRepeticionRangosList to set
	 */
	public void setPedOfertasPorFactorRepeticionRangosList(
			List pedOfertasPorFactorRepeticionRangosList) {
		this.pedOfertasPorFactorRepeticionRangosList = pedOfertasPorFactorRepeticionRangosList;
	}

	/**
	 * @return the beanRangosList
	 */
	public Object getBeanRangosList() {
		return beanRangosList;
	}

	/**
	 * @param beanRangosList
	 *            the beanRangosList to set
	 */
	public void setBeanRangosList(Object beanRangosList) {
		this.beanRangosList = beanRangosList;
	}

	/**
	 * @return the dataModelRangosList
	 */
	public DataTableModel getDataModelRangosList() {
		return dataModelRangosList;
	}

	/**
	 * @param dataModelRangosList
	 *            the dataModelRangosList to set
	 */
	public void setDataModelRangosList(DataTableModel dataModelRangosList) {
		this.dataModelRangosList = dataModelRangosList;
	}

	/**
	 * @return the mostrarBotonEliminar
	 */
	public Boolean getMostrarBotonEliminar() {
		return mostrarBotonEliminar;
	}

	/**
	 * @param mostrarBotonEliminar
	 *            the mostrarBotonEliminar to set
	 */
	public void setMostrarBotonEliminar(Boolean mostrarBotonEliminar) {
		this.mostrarBotonEliminar = mostrarBotonEliminar;
	}

	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta
	 *            the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the oidRango
	 */
	public String getOidRango() {
		return oidRango;
	}

	/**
	 * @param oidRango
	 *            the oidRango to set
	 */
	public void setOidRango(String oidRango) {
		this.oidRango = oidRango;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the pedOfertasPorFactorRepeticionRangosGratisList
	 */
	public List getPedOfertasPorFactorRepeticionRangosGratisList() {
		return pedOfertasPorFactorRepeticionRangosGratisList;
	}

	/**
	 * @param pedOfertasPorFactorRepeticionRangosGratisList
	 *            the pedOfertasPorFactorRepeticionRangosGratisList to set
	 */
	public void setPedOfertasPorFactorRepeticionRangosGratisList(
			List pedOfertasPorFactorRepeticionRangosGratisList) {
		this.pedOfertasPorFactorRepeticionRangosGratisList = pedOfertasPorFactorRepeticionRangosGratisList;
	}

	/**
	 * @return the gratisForm
	 */
	public MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm getGratisForm() {
		return gratisForm;
	}

	/**
	 * @param gratisForm
	 *            the gratisForm to set
	 */
	public void setGratisForm(
			MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm gratisForm) {
		this.gratisForm = gratisForm;
	}

	/**
	 * @return the precioUnitario
	 */
	public String getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario
	 *            the precioUnitario to set
	 */
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the factorRepeticion
	 */
	public String getFactorRepeticion() {
		return factorRepeticion;
	}

	/**
	 * @param factorRepeticion
	 *            the factorRepeticion to set
	 */
	public void setFactorRepeticion(String factorRepeticion) {
		this.factorRepeticion = factorRepeticion;
	}

	/**
	 * @return the dataModelGratisList
	 */
	public DataTableModel getDataModelGratisList() {
		return dataModelGratisList;
	}

	/**
	 * @param dataModelGratisList
	 *            the dataModelGratisList to set
	 */
	public void setDataModelGratisList(DataTableModel dataModelGratisList) {
		this.dataModelGratisList = dataModelGratisList;
	}

	/**
	 * @return the beanGratisList
	 */
	public Object getBeanGratisList() {
		return beanGratisList;
	}

	/**
	 * @param beanGratisList
	 *            the beanGratisList to set
	 */
	public void setBeanGratisList(Object beanGratisList) {
		this.beanGratisList = beanGratisList;
	}

	/**
	 * @return the pedOfertasPorFactorRepeticionRegalosList
	 */
	public List getPedOfertasPorFactorRepeticionRegalosList() {
		return pedOfertasPorFactorRepeticionRegalosList;
	}

	/**
	 * @param pedOfertasPorFactorRepeticionRegalosList
	 *            the pedOfertasPorFactorRepeticionRegalosList to set
	 */
	public void setPedOfertasPorFactorRepeticionRegalosList(
			List pedOfertasPorFactorRepeticionRegalosList) {
		this.pedOfertasPorFactorRepeticionRegalosList = pedOfertasPorFactorRepeticionRegalosList;
	}

	/**
	 * @return the mostrarBotonGratisEliminar
	 */
	public Boolean getMostrarBotonGratisEliminar() {
		return mostrarBotonGratisEliminar;
	}

	/**
	 * @param mostrarBotonGratisEliminar
	 *            the mostrarBotonGratisEliminar to set
	 */
	public void setMostrarBotonGratisEliminar(Boolean mostrarBotonGratisEliminar) {
		this.mostrarBotonGratisEliminar = mostrarBotonGratisEliminar;
	}

	/**
	 * @return the siccCatalagoList
	 */
	public List getSiccCatalagoList() {
		return siccCatalagoList;
	}

	/**
	 * @param siccCatalagoList
	 *            the siccCatalagoList to set
	 */
	public void setSiccCatalagoList(List siccCatalagoList) {
		this.siccCatalagoList = siccCatalagoList;
	}

	/**
	 * @return the pedOfertasPorFactorRepeticionCriteriosList
	 */
	public List getPedOfertasPorFactorRepeticionCriteriosList() {
		return pedOfertasPorFactorRepeticionCriteriosList;
	}

	/**
	 * @param pedOfertasPorFactorRepeticionCriteriosList
	 *            the pedOfertasPorFactorRepeticionCriteriosList to set
	 */
	public void setPedOfertasPorFactorRepeticionCriteriosList(
			List pedOfertasPorFactorRepeticionCriteriosList) {
		this.pedOfertasPorFactorRepeticionCriteriosList = pedOfertasPorFactorRepeticionCriteriosList;
	}

	/**
	 * @return the pedOfertasPorFactorRepeticionCriteriosProductosList
	 */
	public List getPedOfertasPorFactorRepeticionCriteriosProductosList() {
		return pedOfertasPorFactorRepeticionCriteriosProductosList;
	}

	/**
	 * @param pedOfertasPorFactorRepeticionCriteriosProductosList
	 *            the pedOfertasPorFactorRepeticionCriteriosProductosList to set
	 */
	public void setPedOfertasPorFactorRepeticionCriteriosProductosList(
			List pedOfertasPorFactorRepeticionCriteriosProductosList) {
		this.pedOfertasPorFactorRepeticionCriteriosProductosList = pedOfertasPorFactorRepeticionCriteriosProductosList;
	}

	/**
	 * @return the tipoRango
	 */
	public String getTipoRango() {
		return tipoRango;
	}

	/**
	 * @param tipoRango
	 *            the tipoRango to set
	 */
	public void setTipoRango(String tipoRango) {
		this.tipoRango = tipoRango;
	}

	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto
	 *            the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @return the dataModelCriterioList
	 */
	public DataTableModel getDataModelCriterioList() {
		return dataModelCriterioList;
	}

	/**
	 * @param dataModelCriterioList
	 *            the dataModelCriterioList to set
	 */
	public void setDataModelCriterioList(DataTableModel dataModelCriterioList) {
		this.dataModelCriterioList = dataModelCriterioList;
	}

	/**
	 * @return the esNumeroUno
	 */
	public Boolean getEsNumeroUno() {
		return esNumeroUno;
	}

	/**
	 * @param esNumeroUno
	 *            the esNumeroUno to set
	 */
	public void setEsNumeroUno(Boolean esNumeroUno) {
		this.esNumeroUno = esNumeroUno;
	}

	/**
	 * @return the esNumeroCero
	 */
	public Boolean getEsNumeroCero() {
		return esNumeroCero;
	}

	/**
	 * @param esNumeroCero
	 *            the esNumeroCero to set
	 */
	public void setEsNumeroCero(Boolean esNumeroCero) {
		this.esNumeroCero = esNumeroCero;
	}

	/**
	 * @return the beanCriterioList
	 */
	public Object getBeanCriterioList() {
		return beanCriterioList;
	}

	/**
	 * @param beanCriterioList
	 *            the beanCriterioList to set
	 */
	public void setBeanCriterioList(Object beanCriterioList) {
		this.beanCriterioList = beanCriterioList;
	}

	/**
	 * @return the mostrarTodo
	 */
	public Boolean getMostrarTodo() {
		return mostrarTodo;
	}

	/**
	 * @param mostrarTodo
	 *            the mostrarTodo to set
	 */
	public void setMostrarTodo(Boolean mostrarTodo) {
		this.mostrarTodo = mostrarTodo;
	}

	/**
	 * @return the mostrarElPrimero
	 */
	public Boolean getMostrarElPrimero() {
		return mostrarElPrimero;
	}

	/**
	 * @param mostrarElPrimero
	 *            the mostrarElPrimero to set
	 */
	public void setMostrarElPrimero(Boolean mostrarElPrimero) {
		this.mostrarElPrimero = mostrarElPrimero;
	}

	/**
	 * @return the mostrarElSegundo
	 */
	public Boolean getMostrarElSegundo() {
		return mostrarElSegundo;
	}

	/**
	 * @param mostrarElSegundo
	 *            the mostrarElSegundo to set
	 */
	public void setMostrarElSegundo(Boolean mostrarElSegundo) {
		this.mostrarElSegundo = mostrarElSegundo;
	}

	/**
	 * @return the mostrarBotonEliminarCriterio
	 */
	public Boolean getMostrarBotonEliminarCriterio() {
		return mostrarBotonEliminarCriterio;
	}

	/**
	 * @param mostrarBotonEliminarCriterio
	 *            the mostrarBotonEliminarCriterio to set
	 */
	public void setMostrarBotonEliminarCriterio(
			Boolean mostrarBotonEliminarCriterio) {
		this.mostrarBotonEliminarCriterio = mostrarBotonEliminarCriterio;
	}

	/**
	 * @return the dataModelComponentesList
	 */
	public DataTableModel getDataModelComponentesList() {
		return dataModelComponentesList;
	}

	/**
	 * @param dataModelComponentesList
	 *            the dataModelComponentesList to set
	 */
	public void setDataModelComponentesList(
			DataTableModel dataModelComponentesList) {
		this.dataModelComponentesList = dataModelComponentesList;
	}

	/**
	 * @return the bloquearPrecio
	 */
	public Boolean getBloquearPrecio() {
		return bloquearPrecio;
	}

	/**
	 * @param bloquearPrecio
	 *            the bloquearPrecio to set
	 */
	public void setBloquearPrecio(Boolean bloquearPrecio) {
		this.bloquearPrecio = bloquearPrecio;
	}

	/**
	 * @return the mostrarBotonRangoEliminar
	 */
	public Boolean getMostrarBotonRangoEliminar() {
		return mostrarBotonRangoEliminar;
	}

	/**
	 * @param mostrarBotonRangoEliminar
	 *            the mostrarBotonRangoEliminar to set
	 */
	public void setMostrarBotonRangoEliminar(Boolean mostrarBotonRangoEliminar) {
		this.mostrarBotonRangoEliminar = mostrarBotonRangoEliminar;
	}

	/**
	 * @return the ssiccCatalogoList
	 */
	public List getSsiccCatalogoList() {
		return ssiccCatalogoList;
	}

	/**
	 * @param ssiccCatalogoList
	 *            the ssiccCatalogoList to set
	 */
	public void setSsiccCatalogoList(List ssiccCatalogoList) {
		this.ssiccCatalogoList = ssiccCatalogoList;
	}
	

	public String getOidProductoFinal() {
		return oidProductoFinal;
	}

	public void setOidProductoFinal(String oidProductoFinal) {
		this.oidProductoFinal = oidProductoFinal;
	}

	public boolean mostrarBotonGratisEliminar(List lista) {
		boolean mostrar = false;
		if (lista != null && lista.size() > 0) {
			mostrar = true;
		}
		return mostrar;
	}

	/**
	 * @return the beanRegistroDetalleComponente
	 */
	public List getBeanRegistroDetalleComponente() {
		return beanRegistroDetalleComponente;
	}

	/**
	 * @param beanRegistroDetalleComponente the beanRegistroDetalleComponente to set
	 */
	public void setBeanRegistroDetalleComponente(List beanRegistroDetalleComponente) {
		this.beanRegistroDetalleComponente = beanRegistroDetalleComponente;
	}

	/**
	 * @return the activarModificarPrecio
	 */
	public boolean isActivarModificarPrecio() {
		return activarModificarPrecio;
	}

	/**
	 * @param activarModificarPrecio the activarModificarPrecio to set
	 */
	public void setActivarModificarPrecio(boolean activarModificarPrecio) {
		this.activarModificarPrecio = activarModificarPrecio;
	}

	/**
	 * @return the activarEjecutarCriterio
	 */
	public boolean isActivarEjecutarCriterio() {
		return activarEjecutarCriterio;
	}

	/**
	 * @param activarEjecutarCriterio the activarEjecutarCriterio to set
	 */
	public void setActivarEjecutarCriterio(boolean activarEjecutarCriterio) {
		this.activarEjecutarCriterio = activarEjecutarCriterio;
	}
	
	
}