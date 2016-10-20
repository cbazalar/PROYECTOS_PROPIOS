package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.context.RequestContext;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCPremiosElectivosService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDFleteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCPremiosElectivosSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENMensajeCodigoVentaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENMensajeCodigoVentaSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteDetalleForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteSearchForm;

/**
 * @author Giovanni Ascarza
 */
@ManagedBean
@SessionScoped
public class MantenimientoINCPremiosElectivosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;

	private List mantenimientoINCConcursoElectivosList;
	private List incCodigoVentasFicticioList;
	private DataTableModel dataModelDetalle;
	private List listaDetalle;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	private boolean mostrarDatosConsultora;
	private String[] unidadesItems;
	private int[][] filaNivel;
	private boolean entroGrabaSelected;
	private String countRows;

	private boolean seleccionable;

	@ManagedProperty(value = "#{busquedaClientesPOPUPSearchAction}")
	private BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction;

	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.mostrarPopupCliente = true;
			this.mostrarProcesoBatch = false;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}

		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaClientesPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaClientesPOPUPSearchAction.isSeleccionoRegistro()) {

				MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;

				Map cliente = (Map) this.busquedaClientesPOPUPSearchAction
						.getBeanRegistroSeleccionado();

				f.setCodigoClienteBuscar(MapUtils.getString(cliente, "codigo"));

				this.mostrarPopupCliente = false;
				this.mostrarProcesoBatch = true;
				this.busquedaClientesPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);

			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}

	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaClientesPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoINCPremiosElectivosSearchForm();
	}

	public String grabar() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'save' method");
		}

		MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		int puntajeObtenido = NumberUtils.toInt(f.getPuntajeObtenido());
		if (puntajeObtenido <= 0) {
			addWarn("",
					this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.puntajeObtenido.cero"));
			return null;
		}

		try {
			MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			List listPremiosElectivos = this.listaDetalle;
			int size = CollectionUtils.size(this.listaDetalle);

			String[] items = new String[size];
			String[] unidadesItems = getUnidadesItems();
			String[] itemsNuevos = null;

			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", f.getCodigoPais());
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																			// Campanha
																			// Activa
			criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																				// Campanha
																				// activa
																				// q
																				// se
																				// procesa
																				// actualmente

			MantenimientoOCRPedidoControlFacturacionService serviceAux = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = serviceAux
					.getControlFacturacionById(criteriaPeriodo);
			// =this.
			for (int i = 0; i < size; i++) {
				Map premio = (Map) listPremiosElectivos.get(i);

				premio.put("codigoUsuario", usuario.getLogin());
				premio.put("codigoPeriodo",
						controlFacturacion.getCodigoPeriodo());
				premio.put("tipoRecepcion", "M");

				items[i] = String.valueOf(premio.get("selectedItems"));
			}

			int count = 0;
			for (int i = 0; i < items.length; i++) {
				if (StringUtils.equalsIgnoreCase(items[i], "true"))
					count++;
			}

			if (count > 0) {
				int indice = 0;
				itemsNuevos = new String[count];
				for (int i = 0; i < items.length; i++) {
					if (StringUtils.equalsIgnoreCase(items[i], "true")) {
						itemsNuevos[indice] = String.valueOf(i + 1);
						indice++;
					}
				}
			}

			if (ArrayUtils.isNotEmpty(itemsNuevos)) {
				service.insertPremiosElectivos(listPremiosElectivos,
						itemsNuevos, unidadesItems);
			} else {
				Map map = (Map) listPremiosElectivos.get(0);
				service.deletePremiosElectivos(map);
			}

			addInfo("", this.getResourceMessage(
					"mantenimientoINCPremiosElectivosSearchForm.cabecera.save",
					new Object[] { f.getCodigoClienteBuscar() }));

			setParametrosIniciales(usuario, pais, clienteService, f);
			this.setMostrarDatosConsultora(false);
			this.listaDetalle = null;
			this.dataModelDetalle = null;
			f.setCodigoClienteBuscar(null);
			f.setOidConcurso(null);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return null;
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'save' method");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes
	 * ()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;

		MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
		// se cargara la lista de parametros de concurso activos
		this.mantenimientoINCConcursoElectivosList = service
				.getListParametrosConcursosElectivosDiferido();

		// Asignamos al codigo del periodo el valor por defecto
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		f.setOidConcurso(null);
		f.setUltimo(1);
		f.setNumeroPeriodos(Constants.NUMERO_CERO);
		f.setMsgNoExistePuntajeDisponible(this
				.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
		f.setMsgPremioNiveles(this
				.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.niveles"));
		f.setMsgPremioUnicaNiveles(this
				.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.unica.niveles"));

		setParametrosIniciales(usuario, pais, clienteService, f);
		// session.removeAttribute(DATOS_CONSULTORA);
		// session.removeAttribute("notSave");
		this.setMostrarBotonConsultar(false);
		this.setMostrarBotonEliminar(false);
		this.setMostrarBotonModificar(false);
		this.setMostrarBotonNuevo(false);
		this.setMostrarListaBusqueda(false);
		this.setMostrarDatosConsultora(false);
	}

	public void loadDataInput() {

		MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
		String codigoCliente = f.getCodigoClienteBuscar();
		String oidPais = f.getOidPais();
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (StringUtils.isNotBlank(codigoCliente)) {
			String result = ajax.getExisteCodigoCliente(oidPais,
					f.getCodigoClienteBuscar());

			if (StringUtils.isBlank(result)) {
				addWarn("",
						this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.consultora.not.existe"));
				return;
			}
		}
	}

	/**
	 * Pone en session el oid del pais y la longitud de caracteres del cliente
	 * 
	 * @param usuario
	 * @param pais
	 * @param clienteService
	 * @param f
	 */
	private void setParametrosIniciales(Usuario usuario, Pais pais,
			MantenimientoMAEClienteService clienteService,
			MantenimientoINCPremiosElectivosSearchForm f) {

		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);
		criteria.put("oidPais", oidPais);
		// recuperamos la longitud del codigo de cliente para el pais logueado
		f.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
	}

	public void viewPopup() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		try {
			Map map = (Map) dataModelDetalle.getRowData();
			MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
			MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");

			this.incCodigoVentasFicticioList = service.getListCodigoVentas(map);
			// f.setCodigoPais(codigoPais);
			f.setNumeroPremio(String.valueOf(map.get("numPremio")));

			this.listaBusqueda = null;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");

		List list = ListUtils.EMPTY_LIST;
		try {

			MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
			MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");

			Map map = executeValidacionPremiosElectivos(f, service);
			// se setea los datos de la consultora si la validacion fue correcta
			setDatosConsultora(f, map);
			// se recupera la lista de permios ya consolidado
			this.listaDetalle = service.getPremiosElectivos();
			unidadesItems = new String[listaDetalle.size()];

			int i = 0;
			for (Object obj : listaDetalle) {
				Map mapp = (Map) obj;
				// Cargando los valores a la caja de texto.
				unidadesItems[i] = mapp.get("numUnidades").toString();
				// Agreando un item mas para los checkboxs
				mapp.put("selectedItems", Boolean.FALSE);
				listaDetalle.set(i, mapp);
				i++;
			}

			// Selecciona Elegidos
			seleccionaElegidos();
			setCountRows(String.valueOf(CollectionUtils.size(listaDetalle)));

			f.setCodigoCliente(f.getCodigoClienteBuscar());
			f.setIndicadorBusqueda(true);
			// if(listPremiosElectivos.size()==0)
			// request.getSession().setAttribute("notSave",Constants.NUMERO_UNO);
			this.setMostrarDatosConsultora(true);

			list = this.listaDetalle;

			dataModelDetalle = new DataTableModel(this.listaDetalle);

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail",
					new Object[] { error }));
		}

		return list;
	}

	public void seleccionaElegidos() {

		int length = CollectionUtils.size(listaDetalle);

		for (int i = 0; i < length; i++) {
			Map map = (Map) listaDetalle.get(i);
			// indicadorElegido
			String hdIndicadorElegido = map.get("indicadorElegido").toString();
			// String hdIndicadorPendiente =
			// map.get("indicadorPendiente").toString();
			if (hdIndicadorElegido.equals("1")) {
				map.put("selectedItems", Boolean.TRUE);
				listaDetalle.set(i, map);
				/*
				 * if(hdIndicadorPendiente.equals("0")){ //despachado
				 * indice[i].disabled = true; unidadesItems[i].disabled = true;
				 * }
				 */
			} else {
				map.put("selectedItems", Boolean.FALSE);
				listaDetalle.set(i, map);
			}
		}

	}

	public void actualizaPuntajesFila() {
		MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
		Map map = (Map) dataModelDetalle.getRowData();// Selecciono
		int index = dataModelDetalle.getRowIndex();

		String indicadorNoValidaPuntaje = f.getIndicadorNoValidaPuntaje();
		if (indicadorNoValidaPuntaje.equals("0")) {
			if (isPremioNivelAcumularivo(f, map))
				actualizaPuntajesMaximoNivel(f, map, index);
			else
				actualizaPuntajes(f, map, index);
		}
	}

	public boolean isPremioNivelAcumularivo(
			MantenimientoINCPremiosElectivosSearchForm f, Map map) {

		String hdTipoPremio = map.get("tipoPremio").toString();
		String hdIndicadorPremioAcum = map.get("indicadorPremioAcum")
				.toString();
		if (hdTipoPremio.equals("2")) {// tipo de premio por niveles
			if (hdIndicadorPremioAcum.equals("1")) {// seleccion de premios es
													// acumularivo
				return true;
			} else
				return false;
		}
		return false;
	}

	public Boolean getSelectedItem(int index) {
		Map map = (Map) listaDetalle.get(index);
		return (Boolean) map.get("selectedItems");
	}

	public void grabarUnidadItemAListaDetalle(int index, Boolean selected) {
		Map map = (Map) listaDetalle.get(index);
		map.put("selectedItems", selected);
		listaDetalle.set(index, map);
	}

	public void actualizaPuntajesMaximoNivel(
			MantenimientoINCPremiosElectivosSearchForm f, Map map, int index) {
		// obtenemos el maximo nivel, y el maximo costo a descontar
		// descontamos solo para el nivel obtenido
		// para los demas niveles inferiores no hay dscto

		/*
		 * {numNivel=2,indicadorValido=0, oidConcurso=4635,
		 * indicadorPendiente=1, indicadorNivelDiferido=0,
		 * codigoVentaFicticio=null, tipoPremio=2, oidLotePremioArticulo=8671,
		 * oidCliente=49937499, descripcionProducto=Nivel 2 - Opc 1,
		 * indicadorPremioAcum=1, oidNivel=7962, numPremio=2,
		 * indicadorElegido=0, numUnidades=1, oidProducto=null,
		 * costoPuntos=1001, indicadorAnulado=null, menorCodVenta=null}
		 */
		int total = dataModelDetalle.getRowCount();
		String hdNumNivel = map.get("numNivel").toString();
		boolean indice = getSelectedItem(index);
		int hdCostePuntos = NumberUtils
				.toInt(map.get("costoPuntos").toString());
		int valorCanjeado = NumberUtils.toInt(f.getPuntajeCanjeado());
		int valorObtenido = NumberUtils.toInt(f.getPuntajeObtenido());
		int unidadesItems = NumberUtils.toInt(getUnidadesItems()[index]);
		String hdIndicadorPendiente = map.get("indicadorPendiente").toString();
		String hdIndicadorNivelDiferido = map.get("indicadorNivelDiferido")
				.toString();

		int max = total - 1;
		String maxNivel = ((Map) listaDetalle.get(max)).get("numNivel")
				.toString();
		// String maxCosto =
		// ((Map)listaBusqueda.get(max)).get("costoPuntos").toString();
		int valorDisponible = NumberUtils.toInt(f.getPuntajeDisponible());

		int valor = 0;
		int vPuntajeComprometido = 0;
		int vPuntajeDisponible = 0;

		if (hdIndicadorNivelDiferido.equals("1")) {
			return;
		}
		// si nos encontramos en el maximo level
		if (hdNumNivel.equals(maxNivel)) {
			if (indice == Boolean.TRUE && hdIndicadorPendiente.equals("1")) {
				int valor1 = (hdCostePuntos) * 1;
				int valor2 = (unidadesItems) * 1;
				valor = (valor1 * valor2);

				if (valor * 1 <= (valorObtenido * 1 - valorCanjeado * 1)) {
					if (valorDisponible * 1 > 0) {
						vPuntajeComprometido = valor * 1;
						f.setPuntajeComprometido(String
								.valueOf(vPuntajeComprometido));
						vPuntajeDisponible = valorObtenido * 1 - valor * 1
								- valorCanjeado * 1;
						f.setPuntajeDisponible(String
								.valueOf(vPuntajeDisponible));
					} else {
						this.unidadesItems[index] = "1";
						grabarUnidadItemAListaDetalle(index, Boolean.FALSE);
						this.entroGrabaSelected = true;
						addWarn("",
								this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
						return;
					}
				} else {

					this.unidadesItems[index] = "1";
					grabarUnidadItemAListaDetalle(index, Boolean.FALSE);
					this.entroGrabaSelected = true;
					addWarn("",
							this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
					return;
				}

				vPuntajeComprometido = valor * 1;
				vPuntajeDisponible = valorObtenido * 1 - valor * 1
						- valorCanjeado * 1;

				f.setPuntajeComprometido(String.valueOf(vPuntajeComprometido));
				f.setPuntajeDisponible(String.valueOf(vPuntajeDisponible));
			} else {
				vPuntajeComprometido = valor * 1;
				vPuntajeDisponible = valorObtenido * 1 - valor * 1
						- valorCanjeado * 1;

				f.setPuntajeComprometido(String.valueOf(vPuntajeComprometido));
				f.setPuntajeDisponible(String.valueOf(vPuntajeDisponible));
			}

		}
	}

	public void actualizaPuntajes(MantenimientoINCPremiosElectivosSearchForm f,
			Map map, int index) {

		boolean indice = getSelectedItem(index);
		int valorCanjeado = NumberUtils.toInt(f.getPuntajeCanjeado());
		int valorObtenido = NumberUtils.toInt(f.getPuntajeObtenido());
		int valorDisponible = NumberUtils.toInt(f.getPuntajeDisponible());
		String hdIndicadorPendiente = map.get("indicadorPendiente").toString();

		String nivelDiferido = ((Map) listaBusqueda.get(index)).get(
				"indicadorNivelDiferido").toString();
		if (nivelDiferido.equals("1")) {
			return;
		}

		int vPuntajeComprometido = 0;
		int vPuntajeDisponible = 0;
		int valor = 0;

		// sumarizando el total
		for (int i = 0; i < this.unidadesItems.length; i++) {

			if (indice == Boolean.TRUE && hdIndicadorPendiente.equals("1")) {
				int valor1 = NumberUtils.toInt(((Map) listaDetalle.get(i)).get(
						"costoPuntos").toString()) * 1;
				int valor2 = (NumberUtils.toInt(this.unidadesItems[i])) * 1;
				valor = valor + (valor1 * valor2);
			}
		}

		if (valor * 1 <= (valorObtenido * 1 - valorCanjeado * 1)) {
			if (valorDisponible * 1 >= 0) {
				vPuntajeComprometido = valor * 1;
				vPuntajeDisponible = valorObtenido * 1 - valor * 1
						- valorCanjeado * 1;

				f.setPuntajeComprometido(String.valueOf(vPuntajeComprometido));
				f.setPuntajeDisponible(String.valueOf(vPuntajeDisponible));
			} else {
				this.unidadesItems[index] = "1";
				grabarUnidadItemAListaDetalle(index, Boolean.FALSE);
				this.entroGrabaSelected = true;
				addWarn("",
						this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
				return;
			}
		} else {
			this.unidadesItems[index] = "1";
			grabarUnidadItemAListaDetalle(index, Boolean.FALSE);
			this.entroGrabaSelected = true;
			addWarn("",
					this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
			return;
		}

	}

	public boolean verificaSeleccionNiveles(int index) {
		Map map = (Map) dataModelDetalle.getRowData();

		String hdTipoPremio = map.get("tipoPremio").toString();
		int hdNumNivel = NumberUtils.toInt(map.get("numNivel").toString());

		String hdIndicadorPremioAcum = map.get("indicadorPremioAcum")
				.toString();

		setNumFilasPorNIvel();

		int cont = 0;
		if (hdTipoPremio.equals("2")) {// tipo de premio por niveles

			if (hdIndicadorPremioAcum.equals("1")) {// seleccion de premios es
													// acumularivo
				// solo se permite la seleccion unica por nivel
				cont = getNumFilasSeleccionadasPorNivel(hdNumNivel);
				if (cont > 1) {
					grabarUnidadItemAListaDetalle(index, Boolean.FALSE);
					MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
					f.setPuntajeComprometido("20");
					addWarn("",
							this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.niveles"));
					return false;
				}
			} else {
				// solo se permite selccion de un solo premio entre todos los
				// nivels
				cont = getNumFilasSeleccionadas();
				if (cont > 1) {
					grabarUnidadItemAListaDetalle(index, Boolean.FALSE);
					addWarn("",
							this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.unica.niveles"));
					return false;
				}
			}
		}

		return true;
	}

	/* retorm a el numero de filas seleccionadas en todos los niveles */
	public int getNumFilasSeleccionadas() {
		int n = 0;
		for (int i = 0; i < filaNivel.length; i++) {
			n = n + filaNivel[i][1];
		}
		return n;
	}

	/* retorna el num de filas seleccionadas que se tiene por nivel */
	public int getNumFilasSeleccionadasPorNivel(int nivel) {
		int n = 0;
		for (int i = 0; i < filaNivel.length; i++) {
			if (filaNivel[i][0] == nivel) {
				n = filaNivel[i][1];
				break;
			}
		}
		return n;
	}

	public void setNumFilasPorNIvel() {
		int length = CollectionUtils.size(listaDetalle);
		int numNiveles = getNumNiveles();
		// inicializando arreglo
		filaNivel = new int[numNiveles][2];
		for (int k = 0; k < numNiveles; k++) {
			filaNivel[k][0] = k + 1;// nivel
			filaNivel[k][1] = 0;// numFilaSeleccionadas
		}
		int numFilas = 0;

		for (int j = 1; j <= numNiveles; j++) {
			for (int i = 0; i < length; i++) {
				Map map = (Map) listaDetalle.get(i);
				int nivel = NumberUtils.toInt(map.get("numNivel").toString());
				Boolean indice = getSelectedItem(i);

				if (indice == Boolean.TRUE && nivel == j) {
					filaNivel[j - 1][1] = ++numFilas;// numFilaSeleccionadas
				}
			}
		}
	}

	public int getNumNiveles() {
		int length = CollectionUtils.size(listaDetalle);
		int nivel = -1;
		int num = 1;

		if (length > 0)
			nivel = NumberUtils.toInt(((Map) listaDetalle.get(0)).get(
					"numNivel").toString());

		for (int i = 0; i < length; i++) {
			Map map = (Map) listaDetalle.get(i);
			int hdNumNivel = NumberUtils.toInt(map.get("numNivel").toString());
			if (hdNumNivel != nivel) {
				nivel = hdNumNivel;
				num++;
			}
		}
		return num;
	}

	/**
	 * Ejecuta Validaciones , si es conrrecta las validacion retorna los datos
	 * de la cabecera quese mostararn en patalla asi mismo consolida la lista de
	 * elegidos y seleccionables
	 * 
	 * @param f
	 * @param service
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private Map executeValidacionPremiosElectivos(
			MantenimientoINCPremiosElectivosSearchForm f,
			MantenimientoINCPremiosElectivosService service) throws Exception {
		Map map = new HashMap();
		map.put("codigoPais", f.getCodigoPais());
		map.put("oidConcurso", f.getOidConcurso());
		map.put("codigoConsultora", f.getCodigoClienteBuscar());
		map.put("tipo", Constants.NUMERO_CERO);// seleccion de premios
		// valores de salida
		map.put("mensajeError", null);
		map.put("mensajeResultado", null);// una cadena decaracteres de
		// nombreConsultora,meta,puntajeObtenido,puntajeCanjeado,
		// puntajeSaldo,puntajeComprometido,puntajeDisponible
		service.executeValidacionesPremiosElectivos(map);

		String mensajeError = (String) map.get("mensajeError");
		if (StringUtils.isNotEmpty(mensajeError)) {
			log.debug("error en validacion " + mensajeError);
			throw new Exception(mensajeError);
		}
		return map;
	}

	/**
	 * Setea los datos de la consultora
	 * 
	 * @param f
	 * @param map
	 */
	private void setDatosConsultora(
			MantenimientoINCPremiosElectivosSearchForm f, Map map) {
		String resultado = (String) map.get("mensajeResultado");
		log.debug("mensaje Resultado " + resultado);
		String[] parametros = StringUtils.split(resultado, ",");

		f.setNombreConsultora(parametros[0]);
		f.setMeta(parametros[1]);// si es fase de calificacion (Activo : '1')
		// se muestra meta (>=0)en cualiquier otro caso ese valor viene en
		// blanco y no mostrar
		f.setPuntajeObtenido(parametros[2]);
		f.setPuntajeCanjeado(parametros[3]);
		f.setPuntajeSaldo(parametros[4]);
		f.setPuntajeComprometido(parametros[5]);
		f.setPuntajeDisponible(parametros[6]);
		f.setNumeroPeriodos(parametros[7]);
		// ultimo parametro el indicadorde NO validacion de puntajes de premio
		f.setIndicadorNoValidaPuntaje(parametros[8]);// 0:valida 1:no valida
														// puntajes
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("'setDeleteAttributes'");
		return true;
	}

	@Override
	protected String getSalirForward() {
		return null;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	@Override
	public String setValidarFind() {
		MantenimientoINCPremiosElectivosSearchForm f = (MantenimientoINCPremiosElectivosSearchForm) this.formBusqueda;
		String codigoCliente = f.getCodigoClienteBuscar();
		String oidPais = f.getOidPais();
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (StringUtils.isNotBlank(codigoCliente)) {
			String result = ajax.getExisteCodigoCliente(oidPais,
					f.getCodigoClienteBuscar());

			if (StringUtils.isBlank(result)) {
				return this
						.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.consultora.not.existe");
			}
		}

		return null;
	}

	public void validarCheckbox() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String index = externalContext.getRequestParameterMap().get("index");
		Map data = (Map) listaDetalle.get(Integer.parseInt(index));
		data.put("selectedItems", Boolean.FALSE);

	}

	/**
	 * @return the mantenimientoINCConcursoElectivosList
	 */
	public List getMantenimientoINCConcursoElectivosList() {
		return mantenimientoINCConcursoElectivosList;
	}

	/**
	 * @param mantenimientoINCConcursoElectivosList
	 *            the mantenimientoINCConcursoElectivosList to set
	 */
	public void setMantenimientoINCConcursoElectivosList(
			List mantenimientoINCConcursoElectivosList) {
		this.mantenimientoINCConcursoElectivosList = mantenimientoINCConcursoElectivosList;
	}

	/**
	 * @return the dataModelDetalle
	 */
	public DataTableModel getDataModelDetalle() {
		return dataModelDetalle;
	}

	/**
	 * @param dataModelDetalle
	 *            the dataModelDetalle to set
	 */
	public void setDataModelDetalle(DataTableModel dataModelDetalle) {
		this.dataModelDetalle = dataModelDetalle;
	}

	/**
	 * @return the listaDetalle
	 */
	public List getListaDetalle() {
		return listaDetalle;
	}

	/**
	 * @param listaDetalle
	 *            the listaDetalle to set
	 */
	public void setListaDetalle(List listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente
	 *            the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return the busquedaClientesPOPUPSearchAction
	 */
	public BusquedaClientesPOPUPSearchAction getBusquedaClientesPOPUPSearchAction() {
		return busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @param busquedaClientesPOPUPSearchAction
	 *            the busquedaClientesPOPUPSearchAction to set
	 */
	public void setBusquedaClientesPOPUPSearchAction(
			BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction) {
		this.busquedaClientesPOPUPSearchAction = busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @return the mostrarDatosConsultora
	 */
	public boolean isMostrarDatosConsultora() {
		return mostrarDatosConsultora;
	}

	/**
	 * @param mostrarDatosConsultora
	 *            the mostrarDatosConsultora to set
	 */
	public void setMostrarDatosConsultora(boolean mostrarDatosConsultora) {
		this.mostrarDatosConsultora = mostrarDatosConsultora;
	}

	/**
	 * @return the incCodigoVentasFicticioList
	 */
	public List getIncCodigoVentasFicticioList() {
		return incCodigoVentasFicticioList;
	}

	/**
	 * @param incCodigoVentasFicticioList
	 *            the incCodigoVentasFicticioList to set
	 */
	public void setIncCodigoVentasFicticioList(List incCodigoVentasFicticioList) {
		this.incCodigoVentasFicticioList = incCodigoVentasFicticioList;
	}

	/**
	 * @return the unidadesItems
	 */
	public String[] getUnidadesItems() {
		return unidadesItems;
	}

	/**
	 * @param unidadesItems
	 *            the unidadesItems to set
	 */
	public void setUnidadesItems(String[] unidadesItems) {
		this.unidadesItems = unidadesItems;
	}

	/**
	 * @return the seleccionable
	 */
	public boolean isSeleccionable() {
		return seleccionable;
	}

	/**
	 * @param seleccionable
	 *            the seleccionable to set
	 */
	public void setSeleccionable(boolean seleccionable) {
		this.seleccionable = seleccionable;
	}

	/**
	 * @return the filaNivel
	 */
	public int[][] getFilaNivel() {
		return filaNivel;
	}

	/**
	 * @param filaNivel
	 *            the filaNivel to set
	 */
	public void setFilaNivel(int[][] filaNivel) {
		this.filaNivel = filaNivel;
	}

	/**
	 * @return the countRows
	 */
	public String getCountRows() {
		return countRows;
	}

	/**
	 * @param countRows
	 *            the countRows to set
	 */
	public void setCountRows(String countRows) {
		this.countRows = countRows;
	}

}
