package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spncd.model.UnidadAdministrativaCupon;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCReemplazosForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCReemplazosSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoINCReemplazosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8351284707521380668L;
	private String codigoPais;
	private MantenimientoINCConfiguracionConcursoService service;
	private List incReemplazosPremiosList;
	private List incReemplazosList;
	private List incReemplazosCompuestosList;
	private List incCriterioReemplazosList;
	private List incAmbitoList;
	private List incDetalleReemplazosList;
	private Map data;
	private Object beanRegistroDetallePremio;
	private Object beanRegistroDetalleReemplazo;
	private Object beanRegistroDetalleCompuesto;
	private Object beanRegistroDetalleRegionZona;
	private DataTableModel dataTablePremio;
	private DataTableModel dataTableReemplazo;
	private DataTableModel dataTableDetalleReemplazo;
	private DataTableModel dataTableModel;
	private DataTableModel dataTableRegionZona;
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	@ManagedProperty(value = "#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
	private boolean cambiarCriterioBoolean;
	private boolean indicadorActivoBoolean;
	private boolean consultarBoolean;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		MantenimientoINCReemplazosForm form = (MantenimientoINCReemplazosForm) this.formMantenimiento;

		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;

			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {

				Map prodMap = (Map) this.busquedaProductoSearchAction
						.getBeanRegistroSeleccionado();

				form.setCodigoProducto(MapUtils.getString(prodMap, "codigoSap"));
				form.setDescripcionProducto(MapUtils.getString(prodMap,
						"descripcionCorta"));
				this.busquedaProductoSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}

		this.formMantenimiento = form;

	}

	public void cambiarCriterio(ValueChangeEvent val) {
		MantenimientoINCReemplazosForm form = (MantenimientoINCReemplazosForm) this.formMantenimiento;
		String valor = (String) val.getNewValue();
		if (valor.equals("C")) {
			Base data = (Base) incCriterioReemplazosList.get(0);
			form.setOidCriterio(data.getCodigo());
			this.cambiarCriterioBoolean = true;
		} else
			this.cambiarCriterioBoolean = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoINCReemplazosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoINCReemplazosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoINCReemplazosSearchForm form = new MantenimientoINCReemplazosSearchForm();
		return form;
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		
		MantenimientoINCReemplazosForm form = (MantenimientoINCReemplazosForm) this.formMantenimiento;
        if(StringUtils.isBlank(form.getCodigoProducto()))
        	return "'Código Producto' es un campo requerido";
		if(StringUtils.isNotBlank(form.getCodigoProductoInicio()) && !form.getCodigoProductoInicio().equals(form.getCodigoProducto()) && !form.getPosicionReemplazo().equals("0")) {
    		form.setCodigoProducto(form.getCodigoProductoInicio());
			form.setDescripcionProducto(form.getDescripcionProductoInicio());
			return this.getResourceMessage("mantenimientoINCReemplazosForm.msg.atencionesModificar"); 
    	}
		return "";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoINCReemplazosForm f = (MantenimientoINCReemplazosForm) this.formMantenimiento;
		boolean bGrabar = true;
		f.setIndicadorActivo("0");
		if (indicadorActivoBoolean)
			f.setIndicadorActivo("1");
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());

		try {// parseamos el campo precio
			params.put("precio", new BigDecimal(f.getPrecio()));

			// Validamos el codigo SAP ingresado
			Map criteriaBusqueda = new HashMap();
			criteriaBusqueda.put("codigoSAP", f.getCodigoProducto());
			criteriaBusqueda.put("codigoPais", codigoPais);

			MantenimientoRECProductosFFNNEEService serviceProd = (MantenimientoRECProductosFFNNEEService) getBean("spusicc.mantenimientoRECProductosFFNNEEService");
			String oidProducto = serviceProd.getOidProducto(criteriaBusqueda);
			criteriaBusqueda.put("oidProducto", oidProducto);

			List listAmbitos = this.incAmbitoList;
			params.put("listAmbitos", listAmbitos);

			if (oidProducto == null) {
				this.addError(
						"Error",
						this.getResourceMessage("mantenimientoINCDefinirPremioForm.msg.codigoProductoNoExiste"));
				bGrabar = false;
			} else {
				if (f.isNewRecord()) {
					params.put("indicadorActivo", "1");
					service.insertReemplazoPremio(params);// inserta
					cargarLista();
				} else {
					service.updateReemplazoPremio(params);// upadte
					cargarLista();
				}
			}

		} catch (Exception e) {
			log.debug("error " + e.getMessage());
			this.addError("Error", this.obtieneMensajeErrorException(e));
			bGrabar = false;
		}

		return bGrabar;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		try {
			this.mostrarBotonModificar = false;
			this.mostrarBotonNuevo = false;
			this.mostrarBotonConsultar = false;
			this.mostrarBotonEliminar = false;
			this.mostrarBotonBuscar = false;

			cargarLista();
		} catch (Exception e) {
		}

	}

	public void cargarLista() throws IOException {
		MantenimientoINCReemplazosSearchForm f = (MantenimientoINCReemplazosSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

		f.setOidConcurso(data.get("oidConcurso").toString());
		f.setConcurso(data.get("numeroConcurso") + " "
				+ data.get("nombreConcurso"));

		Map criteria = new HashMap();
		criteria.put("oidConcurso", f.getOidConcurso());
		this.incReemplazosPremiosList = service.getPremiosConcurso(criteria);
		this.dataTablePremio = new DataTableModel(incReemplazosPremiosList);
		this.incReemplazosList = service.getFaltantesConcurso(criteria);
		this.dataTableReemplazo = new DataTableModel(incReemplazosList);
		criteria.put("indicadorCompuestos", "indicadorCompuestos");
		this.incReemplazosCompuestosList = service
				.getFaltantesConcurso(criteria);
		this.dataTableModel = new DataTableModel(incReemplazosCompuestosList);
		this.redireccionarPagina("mantenimientoINCReemplazosList");
	}

	public void addMantenimientoINCReemplazos(ActionEvent event) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if (this.incReemplazosPremiosList == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroDetallePremio == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			Map dataPremio = (Map) this.beanRegistroDetallePremio;
			MantenimientoINCReemplazosForm f = new MantenimientoINCReemplazosForm();
			if (dataPremio != null) {
				try {
					BigDecimal numero = (BigDecimal) dataPremio
							.get("numeroPremio");
					f.setNumeroPremio(String.valueOf(numero.toString()));
					f.setCodigoSAP((String) dataPremio.get("codigoSAP"));
					f.setCodigoPremio((String) dataPremio.get("codigoVenta"));
					f.setDescripcionPremio((String) dataPremio
							.get("descripcionProducto"));
					f.setOidArticuloLote((String) dataPremio.get("oid"));
					f.setPrecio("0.00");
					List listCriterios = service
							.getListCriterioDeReemplazo(null);
					this.incCriterioReemplazosList = listCriterios;
					cambiarCriterioBoolean = false;
					this.siccRegionList = ajax.getRegionesByOidSubGerencia("",
							"X");
					this.incAmbitoList = new ArrayList();
					this.dataTableRegionZona = new DataTableModel(
							this.incAmbitoList);
					this.formMantenimiento = f;
					log.debug("enviando para añadir");
					f.setNewRecord(true);
					setConsultarBoolean(false);
					this.redireccionarPagina("mantenimientoINCReemplazosForm");
				} catch (Exception e) {
					this.addError("Error", this.obtieneMensajeErrorException(e));
				}
			}
		}
	}

	public void loadZonas(ValueChangeEvent val) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String[] valores = (String[]) val.getNewValue();
		if (valores.length > 0) {
			String[] valoresN = new String[valores.length];
			int j = 0;
			for (int i = 0; i < valores.length; i++) {
				if (valores[i] != null) {
					valoresN[j] = valores[i].split("__")[0];
					j++;
				}
			}

			this.siccZonaList = ajax.getZonasByMultipleOidRegiones(
					new ArrayList(Arrays.asList(valoresN)), "X");
		} else
			this.siccZonaList = null;
	}

	public void insertAmbito(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertAmbitoGeografico' method");
		}
		MantenimientoINCReemplazosForm f = (MantenimientoINCReemplazosForm) this.formMantenimiento;

		List detalList = this.incAmbitoList;
		log.debug("formulario  :  " + f);

		// obtenemos las regiones ingresadas
		Map mapRegiones = new HashMap();
		if (f.getRegiones() != null) {
			for (int i = 0; i < f.getRegiones().length; i++) {
				String region = f.getRegiones()[i];

				String[] datosRegion = region.split("__");
				mapRegiones.put(datosRegion[0], datosRegion[1]);
			}
		}

		// obtenemos las zonas ingresadas y verificamos si ya han sido
		// ingresadas anteriormente
		if (f.getZonas() != null) {
			for (int i = 0; i < f.getZonas().length; i++) {
				String zona = f.getZonas()[i];

				String[] datosZona = zona.split("__");
				String oidZona = datosZona[1];
				String oidRegion = datosZona[0];
				boolean encontrado = false;

				for (int j = 0; j < detalList.size(); j++) {
					Map ambitoAux = (Map) detalList.get(j);
					String oidZonaAux = (String) ambitoAux.get("oidZona");

					if ((oidZonaAux != null) && (oidZonaAux.equals(oidZona))) {
						// ya se encuentre en la lista de ambitos actual
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					Map ambito = new HashMap();
					ambito.put("oidRegion", oidRegion); // oidRegion
					ambito.put("oidZona", oidZona);
					ambito.put("descripcionZona", datosZona[2]);
					String descripcionRegion = (String) mapRegiones
							.get(oidRegion.toString());
					ambito.put("descripcionRegion", descripcionRegion);

					// si existe la region sin zona registrada, lo eliminamos
					for (int j = 0; j < detalList.size(); j++) {
						Map ambitoAux = (Map) detalList.get(j);
						String oidRegionAux = (String) ambitoAux
								.get("oidRegion");
						String oidZonaAux = (String) ambitoAux.get("oidZona");

						if (oidRegionAux.equals(oidRegion)
								&& oidZonaAux == null) {
							detalList.remove(j);
							break;
						}
					}

					detalList.add(ambito);
				}
			}

		}

		// obtenemos las regiones ingresadas y verificamos si ya han sido
		// ingresadas anteriormente
		if (f.getRegiones() != null) {
			for (int i = 0; i < f.getRegiones().length; i++) {
				String region = f.getRegiones()[i];

				String[] datosRegion = region.split("__");
				String oidRegion = datosRegion[0];
				String descripcionRegion = datosRegion[1];

				boolean encontrado = false;

				for (int j = 0; j < detalList.size(); j++) {
					Map ambitoAux = (Map) detalList.get(j);
					String oidRegionAux = (String) ambitoAux.get("oidRegion");

					if (oidRegionAux.equals(oidRegion)) {
						// ya se encuentre en la lista de ambitos actual
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					Map ambito = new HashMap();
					ambito.put("oidRegion", oidRegion); // oidRegion
					ambito.put("oidZona", null);
					ambito.put("descripcionZona", "");
					ambito.put("descripcionRegion", descripcionRegion);

					detalList.add(ambito);
				}
			}
		}
		this.incAmbitoList = detalList;
		this.dataTableRegionZona = new DataTableModel(incAmbitoList);
		f.setIndActAmbitos("1");
	}

	public void deleteAmbito(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteAmbito' method");
		}
		MantenimientoINCReemplazosForm f = (MantenimientoINCReemplazosForm) this.formMantenimiento;
		Map data = (Map) this.beanRegistroDetalleRegionZona;
		List detalList = new ArrayList();
		if (data != null) {
			String oidRegion = data.get("oidRegion").toString();
			String oidZona = (String) data.get("oidZona");

			for (int j = 0; j < incAmbitoList.size(); j++) {
				Map mapAux = (Map) incAmbitoList.get(j);
				String region = mapAux.get("oidRegion").toString();
				String zona = (String) mapAux.get("oidZona");

				if (StringUtils.isBlank(oidZona) && StringUtils.isBlank(zona)) {
					if (!oidRegion.equals(region))
						detalList.add(mapAux);
				} else if (oidRegion.equals(region) && oidZona.equals(zona)) {

				} else
					detalList.add(mapAux);
			}

			this.incAmbitoList = detalList;
		}
		this.dataTableRegionZona = new DataTableModel(incAmbitoList);
		f.setIndActAmbitos("1");

	}

	public void insertCompuesto(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertCompuesto ' method");
		}
		if (this.incReemplazosList == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroDetalleReemplazo == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			Map data = (Map) this.beanRegistroDetalleReemplazo;
			MantenimientoINCReemplazosForm f = new MantenimientoINCReemplazosForm();
			if (data.get("tipoAgrupacion").toString().equals("C")) {
				f.setEditable(true);
				f.setNewRecord(true);

				if (data != null) {
					try {

						f.setNumeroPremio(String.valueOf(data
								.get("numeroPremio")));
						f.setPrecio("0.00");
						f.setCodigoSAP(String.valueOf(data
								.get("codigoSAPReemplazo")));
						f.setCodigoPremio(String.valueOf(data
								.get("codigoVentaReemplazo")));
						f.setDescripcionPremio(String.valueOf(data
								.get("descripcionProducto")));

						f.setOidArticuloLote(String.valueOf(data
								.get("oidArticuloLote")));
						f.setOidCompuesto(String.valueOf(data.get("oid")));
						f.setNumeroOrden(String.valueOf(data.get("numeroOrden")));
						f.setIndicadorActivo(String.valueOf(data
								.get("indicadorActivo")));
						f.setOidCriterio(String.valueOf(data.get("oidCriterio")));
						f.setTipoAgrupacion("C"); // Tipo Compuesta
						f.setNewRecord(true);
						if ("1".equals(f.getIndicadorActivo()))
							f.setHabilitarIndicadorActivo(true);
						else
							f.setHabilitarIndicadorActivo(false);

						incAmbitoList = new ArrayList();
						formMantenimiento = f;
						this.redireccionarPagina("mantenimientoINCReemplazosCompuestoForm");
						// this.mostrarBotonSave=false;
						log.debug("enviando para añadir");
					} catch (Exception e) {
						addError("", obtieneMensajeErrorException(e));
					}
				}
			} else
				this.addError(
						"Error",
						this.getResourceMessage("mantenimientoINCReemplazosForm.msg.validacionReemplazoCompuesto"));
		}

	}

	public void editCompuesto(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editCompuesto ' method");
		}

		if (this.incReemplazosCompuestosList == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroDetalleCompuesto == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			Map map = (Map) this.beanRegistroDetalleCompuesto;
			MantenimientoINCReemplazosForm f = new MantenimientoINCReemplazosForm();

			try {
				List listPrincipales = this.incReemplazosList;
				String oidCompuesto = String.valueOf(map.get("oidCompuesto"));
				for (int i = 0; i < listPrincipales.size(); i++) {
					Map mapCompuesto = (Map) listPrincipales.get(i);
					String oid = String.valueOf(mapCompuesto.get("oid"));

					if (oid.equalsIgnoreCase(oidCompuesto)) {
						f.setNumeroPremio(String.valueOf(mapCompuesto
								.get("numeroPremio")));
						f.setCodigoSAP(String.valueOf(mapCompuesto
								.get("codigoSAPReemplazo")));
						f.setCodigoPremio(String.valueOf(mapCompuesto
								.get("codigoVentaReemplazo")));
						f.setDescripcionPremio(String.valueOf(mapCompuesto
								.get("descripcionProductoReem")));

						String indicadorActivo = String.valueOf(mapCompuesto
								.get("indicadorActivo"));
						f.setNewRecord(false);
						if ("1".equals(indicadorActivo)) {
							f.setHabilitarIndicadorActivo(true);
							indicadorActivoBoolean = true;
						} else
							f.setHabilitarIndicadorActivo(false);

						break;
					}
				}

				f.setOidArticuloLote(String.valueOf(map.get("oidArticuloLote")));
				f.setOidCompuesto(String.valueOf(map.get("oidCompuesto")));
				f.setNumeroOrden(String.valueOf(map.get("numeroOrden")));
				f.setIndicadorActivo(String.valueOf(map.get("indicadorActivo")));
				f.setOidCriterio(String.valueOf(map.get("oidCriterio")));
				f.setOid(String.valueOf(map.get("oid")));

				f.setCodigoProducto(String.valueOf(map
						.get("codigoSAPReemplazo")));
				f.setDescripcionProducto(String.valueOf(map
						.get("descripcionProducto")));
				f.setNumeroUnidades(String.valueOf(map.get("numeroUnidades")));
				f.setPrecio(String.valueOf(map.get("precio")));
				f.setTipoAgrupacion(String.valueOf(map.get("tipoAgrupacion")));

				f.setCodigoProductoInicio(String.valueOf(map
						.get("codigoSAPReemplazo")));
				f.setDescripcionProductoInicio(String.valueOf(map
						.get("descripcionProducto")));
				f.setPosicionReemplazo(String.valueOf(map
						.get("posicionReemplazo")));

				this.incAmbitoList = new ArrayList();
				formMantenimiento = f;
				this.redireccionarPagina("mantenimientoINCReemplazosCompuestoForm");
				log.debug("enviando para editar");
			} catch (Exception e) {
				addError("", obtieneMensajeErrorException(e));
			}
		}

	}

	public void setEditAttributes(ActionEvent event) throws Exception {

		if (this.incReemplazosList == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroDetalleReemplazo == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			Map map = (Map) this.beanRegistroDetalleReemplazo;
			MantenimientoINCReemplazosForm f = new MantenimientoINCReemplazosForm();

			if (data != null) {
				try {
					String codigoProductoInicio = map.get("codigoSAPReemplazo").toString();
					String posicionReemplazo = map.get("posicionReemplazo").toString();
					String descripcionProducto = map.get("descripcionProducto").toString();
					f.setPosicionReemplazo(posicionReemplazo);
					f.setCodigoProductoInicio(codigoProductoInicio);
					f.setDescripcionProductoInicio(descripcionProducto);					
					cargarData(map, f);
					f.setNewRecord(false);
					setConsultarBoolean(false);
					this.redireccionarPagina("mantenimientoINCReemplazosForm");
					log.debug("enviando para editar");
				} catch (Exception e) {
					this.addError("Error", obtieneMensajeErrorException(e));
				}
			}
		}

	}

	public void consultar(ActionEvent event) {

		if (this.incReemplazosList == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroDetalleReemplazo == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			Map map = (Map) this.beanRegistroDetalleReemplazo;
			MantenimientoINCReemplazosForm f = new MantenimientoINCReemplazosForm();

			try {
				cargarData(map, f);
				setConsultarBoolean(true);
				f.setNewRecord(false);
				setCambiarCriterioBoolean(true);
				this.redireccionarPagina("mantenimientoINCReemplazosForm");
			} catch (Exception e) {
				this.addError("Error", obtieneMensajeErrorException(e));
			}
		}

	}

	public void cargarData(Map map, MantenimientoINCReemplazosForm f) {

		log.debug("map " + map);
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		f.setNumeroPremio(String.valueOf(map.get("numeroPremio")));
		f.setCodigoSAP(String.valueOf(map.get("codigoSAPReemplazado")));
		f.setCodigoPremio(String.valueOf(map.get("codigoVentaReemplazado")));
		f.setDescripcionPremio(String.valueOf(map
				.get("descripcionProductoReem")));
		f.setNumeroOrden(String.valueOf(map.get("numeroOrden")));
		f.setIndicadorActivo(String.valueOf(map.get("indicadorActivo")));

		f.setCodigoProducto(String.valueOf(map.get("codigoSAPReemplazo")));
		f.setDescripcionProducto(String.valueOf(map.get("descripcionProducto")));
		f.setNumeroUnidades(String.valueOf(map.get("numeroUnidades")));
		f.setOidCriterio(String.valueOf(map.get("oidCriterio")));
		f.setOidArticuloLote(String.valueOf(map.get("oidArticuloLote")));
		f.setOid(String.valueOf(map.get("oid")));

		f.setCodigoProductoInicio(String.valueOf(map.get("codigoSAPReemplazo")));
		f.setDescripcionProductoInicio(String.valueOf(map
				.get("descripcionProducto")));
		f.setPosicionReemplazo(String.valueOf(map.get("posicionReemplazo")));

		f.setPrecio(String.valueOf(map.get("precio")));
		if (map.get("tipoAgrupacion") != null)
			f.setTipoAgrupacion(String.valueOf(map.get("tipoAgrupacion")));
		if (map.get("oidCompuesto") != null)
			f.setOidCompuesto(String.valueOf(map.get("oidCompuesto")));

		if (f.getTipoAgrupacion().equals("C"))
			cambiarCriterioBoolean = true;
		List listCriterios = service.getListCriterioDeReemplazo(null);
		this.incCriterioReemplazosList = listCriterios;
		this.siccRegionList = ajax.getRegionesByOidSubGerencia("", "X");
		if (f.getIndicadorActivo().equals("1"))
			indicadorActivoBoolean = true;
		else
			indicadorActivoBoolean = false;
		// RECUPERAMOS LA LISTA DE AMBITOS GEOGRAFICOS DEL REEMPLAZO
		List listReemplazoPremioAmbito = service.getListReemplazoPremioAmbito(f
				.getOid());
		this.incAmbitoList = listReemplazoPremioAmbito;
		this.dataTableRegionZona = new DataTableModel(incAmbitoList);
		Map criteria = new HashMap();
		criteria.put("oidCompuesto", f.getOid());
		criteria.put("oidConcurso", String.valueOf(map.get("oidConcurso")));
		criteria.put("indicadorCompuestos", "indicadorCompuestos");

		this.incDetalleReemplazosList = service.getFaltantesConcurso(criteria);
		this.dataTableDetalleReemplazo = new DataTableModel(this.incDetalleReemplazosList);
		formMantenimiento = f;
		log.debug("enviando para consultar");
	}

	public void eliminarReemplazo(ActionEvent event) throws Exception {
		MantenimientoINCReemplazosSearchForm f = (MantenimientoINCReemplazosSearchForm) this.formBusqueda;
		try {
			if (this.incReemplazosList == null)
				this.getRequestContext().execute(
						"PF('dialogSinRegistros_alertDialog').show()");
			else if (beanRegistroDetalleReemplazo == null)
				this.getRequestContext().execute(
						"PF('dialogSinItem_alertDialog').show()");
			else {
				Map params = (Map) this.beanRegistroDetalleReemplazo;
				String oid = params.get("oid").toString();
				String posicionReemplazo = params.get("posicionReemplazo").toString();
				if(!posicionReemplazo.equals("0")) 
					throw new Exception(this.getResourceMessage("mantenimientoINCReemplazosForm.msg.atenciones"));
	 		   
				service.deleteReemplazoPremio(oid);
				addInfo("",
						getResourceMessage("mantenimientoINCReemplazosForm.deleted"));
				Map criteria = new HashMap();
				criteria.put("oidConcurso", f.getOidConcurso());
				this.incReemplazosPremiosList = service
						.getPremiosConcurso(criteria);
				dataTablePremio = new DataTableModel(incReemplazosPremiosList);
				List listFaltantes = service.getFaltantesConcurso(criteria);
				this.incReemplazosList = listFaltantes;
				dataTableReemplazo = new DataTableModel(incReemplazosList);
				criteria.put("indicadorCompuestos", "indicadorCompuestos");
				incReemplazosCompuestosList = service
						.getFaltantesConcurso(criteria);
				this.dataTableModel = new DataTableModel(
						incReemplazosCompuestosList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", this.obtieneMensajeErrorException(e));
		}
		
	}

	public void eliminarCompuesto(ActionEvent event) {
		MantenimientoINCReemplazosSearchForm f = (MantenimientoINCReemplazosSearchForm) this.formBusqueda;
		try {
			if (this.incReemplazosCompuestosList == null)
				this.getRequestContext().execute(
						"PF('dialogSinRegistros_alertDialog').show()");
			else if (beanRegistroDetalleCompuesto == null)
				this.getRequestContext().execute(
						"PF('dialogSinItem_alertDialog').show()");
			else {
				Map params = (Map) this.beanRegistroDetalleCompuesto;
				String oid = params.get("oid").toString();
				String posicionReemplazo = params.get("posicionReemplazo").toString();
				if(!posicionReemplazo.equals("0")) 
					throw new Exception(this.getResourceMessage("mantenimientoINCReemplazosForm.msg.atenciones"));
				service.deleteReemplazoPremio(oid);
				addInfo("",
						getResourceMessage("mantenimientoINCReemplazosForm.deleted"));
				Map criteria = new HashMap();
				criteria.put("oidConcurso", f.getOidConcurso());
				criteria.put("indicadorCompuestos", "indicadorCompuestos");
				incReemplazosCompuestosList = service
						.getFaltantesConcurso(criteria);
				this.dataTableModel = new DataTableModel(
						incReemplazosCompuestosList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", this.obtieneMensajeErrorException(e));
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
		MantenimientoINCReemplazosForm form = (MantenimientoINCReemplazosForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoINCReemplazosForm.insert";
		} else {
			return "mantenimientoINCReemplazosForm.update";
		}
	}

	public void salirPagina(ActionEvent event) throws IOException {
		this.redireccionarPagina("mantenimientoINCConfiguracionConcursoList");
	}

	public void validarProducto() {
		MantenimientoINCReemplazosForm f = (MantenimientoINCReemplazosForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		try {
			String data = ajax.getDescripcionInternacionalizableProducto(f
					.getCodigoProducto());
			if (StringUtils.isBlank(data))
				throw new Exception("El producto Ingresado no existe");
			f.setDescripcionProducto(data);
		} catch (Exception e) {
			// TODO: handle exception
			f.setDescripcionProducto(null);
			addError("Mensaje", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the service
	 */
	public MantenimientoINCConfiguracionConcursoService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(MantenimientoINCConfiguracionConcursoService service) {
		this.service = service;
	}

	/**
	 * @return the incReemplazosPremiosList
	 */
	public List getIncReemplazosPremiosList() {
		return incReemplazosPremiosList;
	}

	/**
	 * @param incReemplazosPremiosList
	 *            the incReemplazosPremiosList to set
	 */
	public void setIncReemplazosPremiosList(List incReemplazosPremiosList) {
		this.incReemplazosPremiosList = incReemplazosPremiosList;
	}

	/**
	 * @return the incReemplazosList
	 */
	public List getIncReemplazosList() {
		return incReemplazosList;
	}

	/**
	 * @param incReemplazosList
	 *            the incReemplazosList to set
	 */
	public void setIncReemplazosList(List incReemplazosList) {
		this.incReemplazosList = incReemplazosList;
	}

	/**
	 * @return the incReemplazosCompuestosList
	 */
	public List getIncReemplazosCompuestosList() {
		return incReemplazosCompuestosList;
	}

	/**
	 * @param incReemplazosCompuestosList
	 *            the incReemplazosCompuestosList to set
	 */
	public void setIncReemplazosCompuestosList(List incReemplazosCompuestosList) {
		this.incReemplazosCompuestosList = incReemplazosCompuestosList;
	}

	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}

	/**
	 * @return the beanRegistroDetallePremio
	 */
	public Object getBeanRegistroDetallePremio() {
		return beanRegistroDetallePremio;
	}

	/**
	 * @param beanRegistroDetallePremio
	 *            the beanRegistroDetallePremio to set
	 */
	public void setBeanRegistroDetallePremio(Object beanRegistroDetallePremio) {
		this.beanRegistroDetallePremio = beanRegistroDetallePremio;
	}

	/**
	 * @return the beanRegistroDetalleReemplazo
	 */
	public Object getBeanRegistroDetalleReemplazo() {
		return beanRegistroDetalleReemplazo;
	}

	/**
	 * @param beanRegistroDetalleReemplazo
	 *            the beanRegistroDetalleReemplazo to set
	 */
	public void setBeanRegistroDetalleReemplazo(
			Object beanRegistroDetalleReemplazo) {
		this.beanRegistroDetalleReemplazo = beanRegistroDetalleReemplazo;
	}

	/**
	 * @return the beanRegistroDetalle
	 */
	public Object getBeanRegistroDetalleCompuesto() {
		return beanRegistroDetalleCompuesto;
	}

	/**
	 * @param beanRegistroDetalle
	 *            the beanRegistroDetalle to set
	 */
	public void setBeanRegistroDetalleCompuesto(
			Object beanRegistroDetalleCompuesto) {
		this.beanRegistroDetalleCompuesto = beanRegistroDetalleCompuesto;
	}

	/**
	 * @return the dataTableReemplazo
	 */
	public DataTableModel getDataTableReemplazo() {
		return dataTableReemplazo;
	}

	/**
	 * @param dataTableReemplazo
	 *            the dataTableReemplazo to set
	 */
	public void setDataTableReemplazo(DataTableModel dataTableReemplazo) {
		this.dataTableReemplazo = dataTableReemplazo;
	}

	/**
	 * @return the dataTableModel
	 */
	public DataTableModel getDataTableModel() {
		return dataTableModel;
	}

	/**
	 * @param dataTableModel
	 *            the dataTableModel to set
	 */
	public void setDataTableModel(DataTableModel dataTableModel) {
		this.dataTableModel = dataTableModel;
	}

	/**
	 * @return the dataTablePremio
	 */
	public DataTableModel getDataTablePremio() {
		return dataTablePremio;
	}

	/**
	 * @param dataTablePremio
	 *            the dataTablePremio to set
	 */
	public void setDataTablePremio(DataTableModel dataTablePremio) {
		this.dataTablePremio = dataTablePremio;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction
	 *            the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the mostrarPopupProducto
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto
	 *            the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	/**
	 * @return the popupSacproducto
	 */
	public static String getPopupSacproducto() {
		return POPUP_SACPRODUCTO;
	}

	/**
	 * @return the incCriterioReemplazosList
	 */
	public List getIncCriterioReemplazosList() {
		return incCriterioReemplazosList;
	}

	/**
	 * @param incCriterioReemplazosList
	 *            the incCriterioReemplazosList to set
	 */
	public void setIncCriterioReemplazosList(List incCriterioReemplazosList) {
		this.incCriterioReemplazosList = incCriterioReemplazosList;
	}

	/**
	 * @return the cambiarCriterioBoolean
	 */
	public boolean isCambiarCriterioBoolean() {
		return cambiarCriterioBoolean;
	}

	/**
	 * @param cambiarCriterioBoolean
	 *            the cambiarCriterioBoolean to set
	 */
	public void setCambiarCriterioBoolean(boolean cambiarCriterioBoolean) {
		this.cambiarCriterioBoolean = cambiarCriterioBoolean;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the beanRegistroDetalleRegionZona
	 */
	public Object getBeanRegistroDetalleRegionZona() {
		return beanRegistroDetalleRegionZona;
	}

	/**
	 * @param beanRegistroDetalleRegionZona
	 *            the beanRegistroDetalleRegionZona to set
	 */
	public void setBeanRegistroDetalleRegionZona(
			Object beanRegistroDetalleRegionZona) {
		this.beanRegistroDetalleRegionZona = beanRegistroDetalleRegionZona;
	}

	/**
	 * @return the dataTableRegionZona
	 */
	public DataTableModel getDataTableRegionZona() {
		return dataTableRegionZona;
	}

	/**
	 * @param dataTableRegionZona
	 *            the dataTableRegionZona to set
	 */
	public void setDataTableRegionZona(DataTableModel dataTableRegionZona) {
		this.dataTableRegionZona = dataTableRegionZona;
	}

	/**
	 * @return the incAmbitoList
	 */
	public List getIncAmbitoList() {
		return incAmbitoList;
	}

	/**
	 * @param incAmbitoList
	 *            the incAmbitoList to set
	 */
	public void setIncAmbitoList(List incAmbitoList) {
		this.incAmbitoList = incAmbitoList;
	}

	/**
	 * @return the incDetalleReemplazosList
	 */
	public List getIncDetalleReemplazosList() {
		return incDetalleReemplazosList;
	}

	/**
	 * @param incDetalleReemplazosList
	 *            the incDetalleReemplazosList to set
	 */
	public void setIncDetalleReemplazosList(List incDetalleReemplazosList) {
		this.incDetalleReemplazosList = incDetalleReemplazosList;
	}

	/**
	 * @return the indicadorActivoBoolean
	 */
	public boolean isIndicadorActivoBoolean() {
		return indicadorActivoBoolean;
	}

	/**
	 * @param indicadorActivoBoolean
	 *            the indicadorActivoBoolean to set
	 */
	public void setIndicadorActivoBoolean(boolean indicadorActivoBoolean) {
		this.indicadorActivoBoolean = indicadorActivoBoolean;
	}

	/**
	 * @return the consultarBoolean
	 */
	public boolean isConsultarBoolean() {
		return consultarBoolean;
	}

	/**
	 * @param consultarBoolean
	 *            the consultarBoolean to set
	 */
	public void setConsultarBoolean(boolean consultarBoolean) {
		this.consultarBoolean = consultarBoolean;
	}

	public DataTableModel getDataTableDetalleReemplazo() {
		return dataTableDetalleReemplazo;
	}

	public void setDataTableDetalleReemplazo(
			DataTableModel dataTableDetalleReemplazo) {
		this.dataTableDetalleReemplazo = dataTableDetalleReemplazo;
	}
	
	

}
