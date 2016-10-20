package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECPedidosExpressPremiosBloqueadosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECPedidosExpressPremiosBloqueadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRECPedidosExpressPremiosBloqueadosAction extends
		BaseMantenimientoSearchAbstractAction {

	private List recConcursoList = new ArrayList();
	private List recPremioList = new ArrayList();
	private List recRegionList = new ArrayList();
	private List recPedidosExpressPremBloqSearchList = new ArrayList();
	private List recPedidosExpressPremBloqProcessList = new ArrayList();
	private List recClientesExpressPremBloqList = new ArrayList();
	private List listResultConsultoras = new ArrayList();
	private String numeroLote;

	/**
	 * @return
	 */
	public List getRecPedidosExpressPremBloqSearchList() {
		return recPedidosExpressPremBloqSearchList;
	}

	/**
	 * @param recPedidosExpressPremBloqSearchList
	 */
	public void setRecPedidosExpressPremBloqSearchList(
			List recPedidosExpressPremBloqSearchList) {
		this.recPedidosExpressPremBloqSearchList = recPedidosExpressPremBloqSearchList;
	}

	/**
	 * @return
	 */
	public List getRecPedidosExpressPremBloqProcessList() {
		return recPedidosExpressPremBloqProcessList;
	}

	/**
	 * @param recPedidosExpressPremBloqProcessList
	 */
	public void setRecPedidosExpressPremBloqProcessList(
			List recPedidosExpressPremBloqProcessList) {
		this.recPedidosExpressPremBloqProcessList = recPedidosExpressPremBloqProcessList;
	}

	/**
	 * @return
	 */
	public List getRecClientesExpressPremBloqList() {
		return recClientesExpressPremBloqList;
	}

	/**
	 * @param recClientesExpressPremBloqList
	 */
	public void setRecClientesExpressPremBloqList(
			List recClientesExpressPremBloqList) {
		this.recClientesExpressPremBloqList = recClientesExpressPremBloqList;
	}

	/**
	 * @return
	 */
	public List getListResultConsultoras() {
		return listResultConsultoras;
	}

	/**
	 * @param listResultConsultoras
	 */
	public void setListResultConsultoras(List listResultConsultoras) {
		this.listResultConsultoras = listResultConsultoras;
	}

	/**
	 * @return
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return
	 */
	public List getRecConcursoList() {
		return recConcursoList;
	}

	/**
	 * @param recConcursoList
	 */
	public void setRecConcursoList(List recConcursoList) {
		this.recConcursoList = recConcursoList;
	}

	/**
	 * @return
	 */
	public List getRecPremioList() {
		return recPremioList;
	}

	/**
	 * @param recPremioList
	 */
	public void setRecPremioList(List recPremioList) {
		this.recPremioList = recPremioList;
	}

	/**
	 * @return
	 */
	public List getRecRegionList() {
		return recRegionList;
	}

	/**
	 * @param recRegionList
	 */
	public void setRecRegionList(List recRegionList) {
		this.recRegionList = recRegionList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2398758803189335345L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECPedidosExpressPremiosBloqueadosForm f = new MantenimientoRECPedidosExpressPremiosBloqueadosForm();
		return f;
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void validar(ActionEvent event) {
		try {
			MantenimientoRECPedidosExpressPremiosBloqueadosForm f = (MantenimientoRECPedidosExpressPremiosBloqueadosForm) this.formBusqueda;
			List newBusquedaList = new ArrayList();
			List clientesList = new ArrayList();
			List busquedalist = recPedidosExpressPremBloqSearchList;

			List procesarList = recPedidosExpressPremBloqProcessList;
			if (procesarList == null)
				procesarList = new ArrayList();

			boolean flag = false;
			for (int i = 0; i < busquedalist.size(); i++) {
				Map brDetalle = (Map) busquedalist.get(i);

				flag = false;
				for (int j = 0; j < clientesList.size(); j++) {
					String codCliente = (String) clientesList.get(j);

					if (codCliente.equals(brDetalle.get("codCliente"))) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					clientesList.add(brDetalle.get("codCliente"));
				}

				flag = false;
				for (int j = 0; j < f.getSelectedItems().length; j++) {
					StringTokenizer st = new StringTokenizer(
							f.getSelectedItems()[j], "|");
					String codigoVenta = st.nextToken();
					String codigoSAP = st.nextToken();
					if (codigoVenta.equals(brDetalle.get("cuv"))
							&& codigoSAP.equals(brDetalle.get("sap"))) {
						flag = true;
						break;
					}
				}
				if (flag) {
					procesarList.add(brDetalle);
				} else {
					newBusquedaList.add(brDetalle);
				}
			}


			this.addInfo("Mensaje",
					this.getReportResourceMessage("proceso.info.message"));
			this.recClientesExpressPremBloqList = clientesList;
			this.recPedidosExpressPremBloqSearchList = newBusquedaList;
			this.recPedidosExpressPremBloqProcessList = procesarList;
		} catch (Exception ex) {
			this.addError("Error: ", this.obtieneMensajeErrorException(ex));
		}
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoRECPedidosExpressPremiosBloqueadosForm f = (MantenimientoRECPedidosExpressPremiosBloqueadosForm) this.formBusqueda;
		MantenimientoRECPedidosExpressPremiosBloqueadosService service = (MantenimientoRECPedidosExpressPremiosBloqueadosService) getBean("spusicc.mantenimientoRECPedidosExpressPremiosBloqueadosService");

		if (f.getConcurso() == null) {
			f.setConcurso("");
		}
		if (f.getPremio() == null) {
			f.setPremio("");
		}
		if (f.getRegion() == null) {
			f.setRegion("");
		}
		Map criteria = new HashMap();
		criteria.put("codCampania", f.getCodigoPeriodoSel().equals("") ? null
				: f.getCodigoPeriodoSel());
		criteria.put("codConcurso",
				f.getConcurso().equals("") ? null : f.getConcurso());
		criteria.put("codPremio",
				f.getPremio().equals("") ? null : f.getPremio());
		criteria.put("codRegion",
				f.getRegion().equals("") ? null : f.getRegion());
		List list = service.getPedidosExpressPremBloqList(criteria);

		/*
		 * ActionMessages messages = new ActionMessages();
		 * messages.add(ActionMessages.GLOBAL_MESSAGE, new
		 * ActionMessage("search.info.message"));
		 * saveMessages(request.getSession(), messages);
		 */
		if (f.getNumeroFilas() != null && f.getNumeroFilas().equals("")) {
			f.setNumeroFilas(String.valueOf(list.size()));
		}
		this.recPedidosExpressPremBloqSearchList = list;
		this.recPedidosExpressPremBloqProcessList.clear();
		this.recClientesExpressPremBloqList.clear();
		this.listResultConsultoras.clear();

		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarPaginacionDatatableSpinner = false;
		this.nroPaginacionDatatable = 25;
		this.mostrarBotonBuscar = true;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonModificar = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECPedidosExpressPremiosBloqueadosForm f = (MantenimientoRECPedidosExpressPremiosBloqueadosForm) this.formBusqueda;
		MantenimientoRECPedidosExpressPremiosBloqueadosService service = (MantenimientoRECPedidosExpressPremiosBloqueadosService) getBean("spusicc.mantenimientoRECPedidosExpressPremiosBloqueadosService");
		f.reset();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		String campahniaActual = service.getObtenerCampahniaActiva(criteria);

		if (campahniaActual == null) {
			campahniaActual = "";
		}
		f.setCodigoPeriodo(campahniaActual);
		f.setCodigoPeriodoSel(campahniaActual);

		List concursoList = service.getConcursosList();
		List premioList = service.getPremiosList();
		List regionList = service.getRegionesList();

		this.recConcursoList = concursoList;
		this.recPremioList = premioList;
		this.recRegionList = regionList;

		// session.setAttribute(Constants.REC_CLIENTES_EXPRESS_PREM_BLOQ_VALFLAG,f.getValFlag());
		f.setValFlag("1");
		initForm(f);

		this.addInfo("Mensaje", getResourceMessage("search.info.message"));

	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void procesar(ActionEvent event) {

		try {
			String numeroLote = "";

			Pais pais = mPantallaPrincipalBean.getCurrentCountry();

			MantenimientoRECPedidosExpressPremiosBloqueadosForm f = (MantenimientoRECPedidosExpressPremiosBloqueadosForm) formBusqueda;
			List listResultado = new ArrayList();// se guarad la lista de
													// clientes que al final se
													// mostarra con ok o error

			System.out.println("valFlag:" + f.getValFlag());

			Map criteria = new HashMap();
			criteria.put("tipoProducto", "Premio");
			criteria.put("tipoAtencion", "Express");
			criteria.put("campanhaProceso", f.getCodigoPeriodoSel());
			criteria.put("campanhaReferencia", f.getCodigoPeriodo());
			criteria.put("codigoPais", pais.getCodigo());
			Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
			criteria.put("codigoUsuario", usuario.getLogin().toUpperCase());
			criteria.put("mensajeError", null);
			criteria.put("tipoOperacion", "01");

			List procesarList = recPedidosExpressPremBloqProcessList;
			List clientesList = recClientesExpressPremBloqList;
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");

			// Se ejecuta el proceso para cada cliente de la lista
			List procesarListCliente = null;
			for (int i = 0; i < clientesList.size(); i++) {
				String codCliente = (String) clientesList.get(i);

				procesarListCliente = new ArrayList();
				for (int j = 0; j < procesarList.size(); j++) {
					Map map = (Map) procesarList.get(j);

					if (codCliente.equals(map.get("codCliente"))) {
						BoletaRecojoDetalle brDetalle = new BoletaRecojoDetalle();
						brDetalle.setCodigoVenta((String) map.get("cuv"));
						brDetalle.setPrecio((String) map.get("valPrec"));
						brDetalle.setPrecioContable((String) map
								.get("valPrecCont"));
						brDetalle.setCodigoSAP((String) map.get("sap"));
						brDetalle.setDescripcionProducto(null);
						brDetalle.setIdTipoOferta(null);
						brDetalle
								.setIdProducto(map.get("prodOidProd") == null ? ""
										: ((BigDecimal) map.get("prodOidProd"))
												.toString());
						brDetalle.setIdMatrizFacturacion(null);
						brDetalle.setIdOperacionReclamo(null);
						brDetalle
								.setIdSolicitud(map.get("copaOidParaGral") == null ? ""
										: ((BigDecimal) map
												.get("copaOidParaGral"))
												.toString());
						brDetalle.setIdParametroNivel(map
								.get("panpOidParaNivePrem") == null ? ""
								: ((BigDecimal) map.get("panpOidParaNivePrem"))
										.toString());
						brDetalle.setIdLoteArticulo(map
								.get("lopaOidLotePremArti") == null ? ""
								: ((BigDecimal) map.get("lopaOidLotePremArti"))
										.toString());
						brDetalle.setIdTipoConcurso(map
								.get("ictpOidConcTipoProg") == null ? ""
								: ((BigDecimal) map.get("ictpOidConcTipoProg"))
										.toString());
						brDetalle.setIdDetalleOferta(null);
						brDetalle.setIdFormaPago(null);
						brDetalle
								.setUnidadesReclamadas(map.get("unidades") == null ? ""
										: ((BigDecimal) map.get("unidades"))
												.toString());
						procesarListCliente.add(brDetalle);
					}
				}


				criteria.put("codigoCliente", codCliente);
				service.procesarAtenciones(criteria, procesarListCliente);

				numeroLote = (String) criteria.get("numeroLote");
				String mensaje = (String) criteria.get("mensajeError");
				Map mapResultado = new HashMap();
				mapResultado.put("codigoCliente", codCliente);
				mapResultado.put("mensajeError", mensaje);
				listResultado.add(mapResultado);

				if (StringUtils.isBlank(mensaje)) {
					service.actualizarRegistroSinError(criteria,
							procesarListCliente);
				}
			}

			this.addInfo("Mensaje",
					this.getResourceMessage("mantenimientoRECPedidosExpressPremiosBloqueadosForm.procesado"));

			this.recPedidosExpressPremBloqProcessList.clear();
			this.recPedidosExpressPremBloqSearchList.clear();
			this.recClientesExpressPremBloqList.clear();
			this.listResultConsultoras = listResultado;
			// request.getSession().setAttribute("listResultConsultoras",
			// listResultado);
			this.numeroLote = numeroLote;

		} catch (Exception e) {
			this.addError(
					"Error",
					this.getResourceMessage("mantenimientoRECPedidosExpressPremiosBloqueadosForm.procesado"));

		}

	}

	private void initForm(MantenimientoRECPedidosExpressPremiosBloqueadosForm f) {

		this.recPedidosExpressPremBloqProcessList.clear();
		this.recPedidosExpressPremBloqSearchList.clear();
		this.recClientesExpressPremBloqList.clear();
		this.listResultConsultoras.clear();
		this.numeroLote = null;

		f.setSelectedItems(null);
		f.setConcurso("");
		f.setPremio("");
		f.setRegion("");
		f.setNumeroFilas("");

	}

}
