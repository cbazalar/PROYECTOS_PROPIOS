package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionCDRUsuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoOperacionCDRUsuarioService;
import biz.belcorp.ssicc.web.action.BusquedaUsuarioPOPUPSearchAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoOperacionCDRUsuarioForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoOperacionCDRUsuarioSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoOperacionCDRUsuarioSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 8623302512843405188L;

	private List siccOperacionesList;
	private List operacionCDRUsuarioList;
	private String indicadorCerrarVentana;
	private List stoExcepcionClientesList;
	private List tiposOperacionUsuarioList;
	private List operacionUsuarioList;

	private Object[] beanMantenimientoOperacionCDRUsuario;
	private DataTableModel dtMantenimientoOperacionCDRUsuario;
	private List operacionCDRUsuarioLista;
	private String parametroAccion = null;

	private boolean mostrarGrilla;

	@ManagedProperty(value = "#{busquedaUsuarioPOPUPSearchAction}")
	private BusquedaUsuarioPOPUPSearchAction popupBusquedaUsuario;

	private boolean mostrarPopUpUsuario = false;
	private static final String POPUP_USUARIO = "SUSUARIO";

	/**
	 * @return the beanMantenimientoOperacionCDRUsuario
	 */
	public Object[] getBeanMantenimientoOperacionCDRUsuario() {
		return beanMantenimientoOperacionCDRUsuario;
	}

	/**
	 * @param beanMantenimientoOperacionCDRUsuario
	 *            the beanMantenimientoOperacionCDRUsuario to set
	 */
	public void setBeanMantenimientoOperacionCDRUsuario(
			Object[] beanMantenimientoOperacionCDRUsuario) {
		this.beanMantenimientoOperacionCDRUsuario = beanMantenimientoOperacionCDRUsuario;
	}

	/**
	 * @return the dtMantenimientoOperacionCDRUsuario
	 */
	public DataTableModel getDtMantenimientoOperacionCDRUsuario() {
		return dtMantenimientoOperacionCDRUsuario;
	}

	/**
	 * @param dtMantenimientoOperacionCDRUsuario
	 *            the dtMantenimientoOperacionCDRUsuario to set
	 */
	public void setDtMantenimientoOperacionCDRUsuario(
			DataTableModel dtMantenimientoOperacionCDRUsuario) {
		this.dtMantenimientoOperacionCDRUsuario = dtMantenimientoOperacionCDRUsuario;
	}

	/**
	 * @return the operacionCDRUsuarioLista
	 */
	public List getOperacionCDRUsuarioLista() {
		return operacionCDRUsuarioLista;
	}

	/**
	 * @param operacionCDRUsuarioLista
	 *            the operacionCDRUsuarioLista to set
	 */
	public void setOperacionCDRUsuarioLista(List operacionCDRUsuarioLista) {
		this.operacionCDRUsuarioLista = operacionCDRUsuarioLista;
	}

	/**
	 * @return the parametroAccion
	 */
	public String getParametroAccion() {
		return parametroAccion;
	}

	/**
	 * @param parametroAccion
	 *            the parametroAccion to set
	 */
	public void setParametroAccion(String parametroAccion) {
		this.parametroAccion = parametroAccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoOperacionCDRUsuarioList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoOperacionCDRUsuarioForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoOperacionCDRUsuarioSearchForm searchForm = new MantenimientoOperacionCDRUsuarioSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaOperacionCDRUsuarioSearchAction - search' method");
		}

		// Map criteriaOperacion = new HashMap();
		// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// criteriaOperacion.put("codigoPais", pais.getCodigo());
		// InterfazSiCCService interfazSiCCService = (InterfazSiCCService)
		// getBean("sisicc.interfazSiCCService");
		// this.siccOperacionesList =
		// interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);

		MantenimientoOperacionCDRUsuarioSearchForm searchForm = (MantenimientoOperacionCDRUsuarioSearchForm) this.formBusqueda;

		List lista = cargarDatos(searchForm);
		this.indicadorCerrarVentana = Constants.NRO_CERO;

		this.operacionCDRUsuarioLista = lista;
		this.dtMantenimientoOperacionCDRUsuario = new DataTableModel(
				this.operacionCDRUsuarioLista);

		return lista;
	}

	public void delete(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		try {

			String mensaje = "";
			int tamanio = this.beanMantenimientoOperacionCDRUsuario.length;
			if (tamanio == 0) {
				this.addWarn("Error: ", "Debe seleccionar un registro");
				return;
			}

			String[] items = new String[tamanio];
			for (int i = 0; i < tamanio; i++) {
				OperacionCDRUsuario obj = (OperacionCDRUsuario) this.beanMantenimientoOperacionCDRUsuario[i];
				items[i] = obj.getCodigo();
			}

			String[] paramsGen = items;

			if (paramsGen != null && paramsGen.length > 0) {
				String[] params = null;

				for (int i = 0; i < paramsGen.length; i++) {
					params = paramsGen[i].split("_");

					String idUsuario = params[0];
					String idOperacion = params[1];
					String idTipoOperacion = params[2];

					Usuario usuario = this.mPantallaPrincipalBean
							.getCurrentUser();

					MantenimientoOperacionCDRUsuarioService service = (MantenimientoOperacionCDRUsuarioService) getBean("spusicc.mantenimientoOperacionCDRUsuarioService");

					Map criteria = new HashMap();
					criteria.put("usuario", idUsuario);
					criteria.put("operacion", idOperacion);

					OperacionCDRUsuario operacionCDRUsuario = new OperacionCDRUsuario();
					operacionCDRUsuario.setUsuario(idUsuario);
					operacionCDRUsuario.setOperacion(idOperacion);

					if (idTipoOperacion != null && idTipoOperacion.length() > 0
							&& !idTipoOperacion.equals("null")) {
						criteria.put("tipo", idTipoOperacion);
						operacionCDRUsuario.setTipo(idTipoOperacion);
					}

					List list = service.getOperacionesCDRxUsuario(criteria);
					if (list != null && list.size() > 0) {
						OperacionCDRUsuario operacionCDRUsuarioResult = (OperacionCDRUsuario) list
								.get(0);
						operacionCDRUsuario
								.setUsuarioCreacion(operacionCDRUsuarioResult
										.getUsuarioCreacion());
						operacionCDRUsuario
								.setFechaCreacion(operacionCDRUsuarioResult
										.getFechaCreacion());
					}

					// Removemos registro de REC_USUAR_OPERA
					service.removeOperacionCDRUsuario(criteria);

					// Insertamos registro eliminado en REC_AUDIT_USUAR_OPERA
					service.insertOperacionCDRUsuarioAudit(operacionCDRUsuario,
							usuario);
				}
			}

			this.indicadorCerrarVentana = Constants.NRO_CERO;

			this.operacionCDRUsuarioLista = this.setFindAttributes();
			this.dtMantenimientoOperacionCDRUsuario = new DataTableModel(
					operacionCDRUsuarioLista);

			mensaje = this
					.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.delete.exito");
			this.addInfo("Info : ", mensaje);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	public String setValidarConfirmar(String accion) {
		int tamanio = this.beanMantenimientoOperacionCDRUsuario.length;
		if (tamanio == 0) {
			return "Debe seleccionar un registro";
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoOperacionCDRUsuarioForm mantenimientoOperacionCDRUsuarioForm = (MantenimientoOperacionCDRUsuarioForm) this.formMantenimiento;

		boolean isNew = mantenimientoOperacionCDRUsuarioForm.isNewRecord();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		MantenimientoOperacionCDRUsuarioService service = (MantenimientoOperacionCDRUsuarioService) getBean("spusicc.mantenimientoOperacionCDRUsuarioService");

		OperacionCDRUsuario operacionCDRUsuario = new OperacionCDRUsuario();
		BeanUtils.copyProperties(operacionCDRUsuario,
				mantenimientoOperacionCDRUsuarioForm);

		String[] operacionesSeleccionadas = mantenimientoOperacionCDRUsuarioForm
				.getOperaciones();
		String[] tipoOperacionesSeleccionadas = mantenimientoOperacionCDRUsuarioForm
				.getTipos();

		try {
			// Salvamos nuevo registro
			if (isNew) {
				List listaClientes = this.stoExcepcionClientesList;

				if (listaClientes != null && listaClientes.size() > 0) {
					int size = 0;
					size = listaClientes.size();
					LabelValue labelValue = new LabelValue();
					String codCliente = "";
					String[] clienteList = new String[size];

					for (int i = 0; i < size; i++) {
						labelValue = (LabelValue) listaClientes.get(i);
						codCliente = labelValue.getLabel();
						clienteList[i] = codCliente;
					}

					mantenimientoOperacionCDRUsuarioForm
							.setClienteList(clienteList);

					// String codigosErradosFile =
					// mantenimientoOperacionCDRUsuarioForm.getCodigosErradosFile();
					int errados = mantenimientoOperacionCDRUsuarioForm
							.getErrados();

					if (errados == 0) {
						if (clienteList != null && clienteList.length > 0) {
							for (int i = 0; i < clienteList.length; i++) {
								String operacionesReginstradas = null;
								String[] valueOperacionTipo = new String[2];

								if (tipoOperacionesSeleccionadas != null
										&& tipoOperacionesSeleccionadas.length > 0) {
									for (int j = 0; j < tipoOperacionesSeleccionadas.length; j++) {
										valueOperacionTipo = tipoOperacionesSeleccionadas[j]
												.split("-");

										Map criteria = new HashMap();
										criteria.put("usuario", clienteList[i]);
										criteria.put("operacion",
												valueOperacionTipo[0]);
										// criteria.put("tipo",
										// valueOperacionTipo[1]);

										List operacionCDRUsuarioExistente = service
												.getOperacionesCDRxUsuario(criteria);

										if ((operacionCDRUsuarioExistente == null)
												|| (operacionCDRUsuarioExistente != null && operacionCDRUsuarioExistente
														.size() < 1)) {
											operacionCDRUsuario
													.setUsuario(clienteList[i]);
											operacionCDRUsuario
													.setOperacion(valueOperacionTipo[0]);
											operacionCDRUsuario
													.setTipo(valueOperacionTipo[1]);
											service.insertOperacionCDRUsuario(
													operacionCDRUsuario,
													usuario);
											if (operacionesReginstradas == null) {
												operacionesReginstradas = valueOperacionTipo[0];
											} else {
												operacionesReginstradas = operacionesReginstradas
														+ "-"
														+ valueOperacionTipo[0];
											}
										}
									}

									if (operacionesSeleccionadas != null
											&& operacionesSeleccionadas.length > 0
											&& operacionesReginstradas != null) {
										String[] operaciones = operacionesReginstradas
												.split("-");

										for (int j = 0; j < operacionesSeleccionadas.length; j++) {
											boolean operacionRegistrada = false;
											for (int k = 0; k < operaciones.length; k++) {
												if (operacionesSeleccionadas[j]
														.equals(operaciones[k])) {
													operacionRegistrada = true;
												}
											}

											if (!operacionRegistrada) {
												Map criteria = new HashMap();
												criteria.put("usuario",
														clienteList[i]);
												criteria.put(
														"operacion",
														operacionesSeleccionadas[j]);

												List operacionCDRUsuarioExistente = service
														.getOperacionesCDRxUsuario(criteria);
												if ((operacionCDRUsuarioExistente == null)
														|| (operacionCDRUsuarioExistente != null && operacionCDRUsuarioExistente
																.size() < 1)) {

		

													operacionCDRUsuario
															.setUsuario(clienteList[i]);
													operacionCDRUsuario
															.setOperacion(operacionesSeleccionadas[j]);
													operacionCDRUsuario
															.setTipo(null);
													service.insertOperacionCDRUsuario(
															operacionCDRUsuario,
															usuario);
												}
											}
										}
									}
								} else {
									if (operacionesSeleccionadas != null
											&& operacionesSeleccionadas.length > 0) {
										for (int j = 0; j < operacionesSeleccionadas.length; j++) {
											Map criteria = new HashMap();
											criteria.put("usuario",
													clienteList[i]);
											criteria.put("operacion",
													operacionesSeleccionadas[j]);
											// criteria.put("tipo", null);
											List operacionCDRUsuarioExistente = service
													.getOperacionesCDRxUsuario(criteria);

											if ((operacionCDRUsuarioExistente == null)
													|| (operacionCDRUsuarioExistente != null && operacionCDRUsuarioExistente
															.size() < 1)) {

					

												operacionCDRUsuario
														.setUsuario(clienteList[i]);
												operacionCDRUsuario
														.setOperacion(operacionesSeleccionadas[j]);
												operacionCDRUsuario
														.setTipo(null);
												service.insertOperacionCDRUsuario(
														operacionCDRUsuario,
														usuario);
											}
										}
									}
								}
							}
						}

					} else {
						this.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario.lista");
					}
				} else {
					Map criteriaUser = new HashMap();
					criteriaUser.put("loginUsuario",
							operacionCDRUsuario.getUsuario());
					UsuarioService usuarioService = (UsuarioService) getBean("usuarioService");
					List usuarioList = usuarioService
							.getUsuariosByCriteria(criteriaUser);

					// Verificacion si existe el usuario ingresado
					if (usuarioList != null && usuarioList.size() > 0) {

						String operacionesReginstradas = null;
						String[] valueOperacionTipo = new String[2];

						if (tipoOperacionesSeleccionadas != null
								&& tipoOperacionesSeleccionadas.length > 0) {

							for (int j = 0; j < tipoOperacionesSeleccionadas.length; j++) {

								valueOperacionTipo = tipoOperacionesSeleccionadas[j]
										.split("-");

								Map criteria = new HashMap();
								criteria.put("usuario",
										operacionCDRUsuario.getUsuario());
								criteria.put("operacion", valueOperacionTipo[0]);
								// criteria.put("tipo", valueOperacionTipo[1]);

								List operacionCDRUsuarioExistente = service
										.getOperacionesCDRxUsuario(criteria);

								if ((operacionCDRUsuarioExistente == null)
										|| (operacionCDRUsuarioExistente != null && operacionCDRUsuarioExistente
												.size() < 1)) {
									operacionCDRUsuario
											.setOperacion(valueOperacionTipo[0]);
									operacionCDRUsuario
											.setTipo(valueOperacionTipo[1]);
									service.insertOperacionCDRUsuario(
											operacionCDRUsuario, usuario);
									if (operacionesReginstradas == null) {
										operacionesReginstradas = valueOperacionTipo[0];
									} else {
										operacionesReginstradas = operacionesReginstradas
												+ "-" + valueOperacionTipo[0];
									}
								} else {
									this.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario.operacion");
								}
							}

							if (operacionesSeleccionadas != null
									&& operacionesSeleccionadas.length > 0
									&& operacionesReginstradas != null) {

								String[] operaciones = operacionesReginstradas
										.split("-");

								for (int j = 0; j < operacionesSeleccionadas.length; j++) {
									boolean operacionRegistrada = false;



									for (int k = 0; k < operaciones.length; k++) {
										if (operacionesSeleccionadas[j]
												.equals(operaciones[k])) {
											operacionRegistrada = true;
										}
									}

									if (!operacionRegistrada) {
										Map criteria = new HashMap();
										criteria.put("usuario",
												operacionCDRUsuario
														.getUsuario());
										criteria.put("operacion",
												operacionesSeleccionadas[j]);

										List operacionCDRUsuarioExistente = service
												.getOperacionesCDRxUsuario(criteria);
										if ((operacionCDRUsuarioExistente == null)
												|| (operacionCDRUsuarioExistente != null && operacionCDRUsuarioExistente
														.size() < 1)) {



											operacionCDRUsuario
													.setOperacion(operacionesSeleccionadas[j]);
											operacionCDRUsuario.setTipo(null);
											service.insertOperacionCDRUsuario(
													operacionCDRUsuario,
													usuario);
										} else {
											this.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario.operacion");
										}
									}
								}
							}
						} else {
							if (operacionesSeleccionadas != null
									&& operacionesSeleccionadas.length > 0) {
								for (int j = 0; j < operacionesSeleccionadas.length; j++) {
									Map criteria = new HashMap();
									criteria.put("usuario",
											operacionCDRUsuario.getUsuario());
									criteria.put("operacion",
											operacionesSeleccionadas[j]);
									// criteria.put("tipo", null);
									List operacionCDRUsuarioExistente = service
											.getOperacionesCDRxUsuario(criteria);

									if ((operacionCDRUsuarioExistente == null)
											|| (operacionCDRUsuarioExistente != null && operacionCDRUsuarioExistente
													.size() < 1)) {

			

										operacionCDRUsuario
												.setOperacion(operacionesSeleccionadas[j]);
										operacionCDRUsuario.setTipo(null);
										service.insertOperacionCDRUsuario(
												operacionCDRUsuario, usuario);
									} else {
										this.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario.operacion");
									}
								}
							}
						}

					} else {
						this.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario");
					}
				}
			}

			this.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.exito");
			this.indicadorCerrarVentana = Constants.NRO_UNO;
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id",
					new Object[] { codigo }));
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage(
					"errors.invalid.description", new Object[] { descripcion }));
		}

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		List l = new ArrayList();

		LabelValue[] lv = aSvc
				.getTiposOperacionesByOperacion((mantenimientoOperacionCDRUsuarioForm
						.getOperaciones() == null) ? new String[0]
						: mantenimientoOperacionCDRUsuarioForm.getOperaciones());

		if (lv != null && lv.length > 0) {
			for (int i = 0; i < lv.length; i++) {
				Base base = new Base();
				base.setCodigo(lv[i].getValue());
				base.setDescripcion(lv[i].getLabel());
				l.add(base);
			}

			this.tiposOperacionUsuarioList = l;
		}

		if (mantenimientoOperacionCDRUsuarioForm.getErrados() == 0) {
			mantenimientoOperacionCDRUsuarioForm.setIndicadorClientes("1");
		} else {
			mantenimientoOperacionCDRUsuarioForm.setIndicadorClientes("0");
		}

		// Actualizamos las listas que se encuentran en el contexto
		// StartupListener.setupContext(getServlet().getServletContext());

		// Regresamos a la página de búsqueda
		// return mapping.findForward("view");

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		this.stoExcepcionClientesList = new ArrayList();
		String login = getRequest().getParameter("login");
		MantenimientoOperacionCDRUsuarioForm f = new MantenimientoOperacionCDRUsuarioForm();

		if (login != null && login.length() > 0) {
			f.setUsuario(login);
		}

		Map criteriaOperacion = new HashMap();
		MantenimientoOperacionCDRUsuarioService service = (MantenimientoOperacionCDRUsuarioService) getBean("spusicc.mantenimientoOperacionCDRUsuarioService");
		this.operacionUsuarioList = service
				.getOperacionesUsuario(criteriaOperacion);

		// request.getSession().setAttribute(Constants.STO_EXCEPCION_CLIENTES_LIST,new
		// ArrayList());

		if (f.getOperaciones() != null && f.getOperaciones().length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			List l = new ArrayList();

			LabelValue[] lv = aSvc.getTiposOperacionesByOperacion(f
					.getOperaciones());

			if (lv != null && lv.length > 0) {
				for (int i = 1; i < lv.length; i++) {
					Base base = new Base();
					base.setCodigo(lv[i].getValue());
					base.setDescripcion(lv[i].getLabel());
					l.add(base);
				}
				;
				this.tiposOperacionUsuarioList = l;
			}
		}

		f.setErrados(0);
		f.setOperaciones(new String[0]);
		this.indicadorCerrarVentana = Constants.NRO_CERO;

		this.mostrarGrilla = false;
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;

		MantenimientoOperacionCDRUsuarioSearchForm f = (MantenimientoOperacionCDRUsuarioSearchForm) this.formBusqueda;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		f.setUsuario(null);
		f.setNombres(null);
		f.setApellidos(null);
		f.setOperacion(null);
		f.setNumeroFilas("25");

		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		List lista = cargarDatos(f);
		this.listaBusqueda = lista;
		this.dtMantenimientoOperacionCDRUsuario = new DataTableModel(this.listaBusqueda);

		this.mostrarListaBusqueda = false;
		this.mostrarBotonEliminar = false;
		this.parametroAccion = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_USUARIO)) {
			this.mostrarPopUpUsuario = true;
		}
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
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_USUARIO)) {
			this.popupBusquedaUsuario.verificarRegistro(event);
			if (this.popupBusquedaUsuario.isSeleccionoRegistro()) {
				Usuario usuarioMap = (Usuario) this.popupBusquedaUsuario
						.getBeanRegistroSeleccionado();
				MantenimientoOperacionCDRUsuarioForm f = (MantenimientoOperacionCDRUsuarioForm) this.formMantenimiento;

				f.setUsuario((usuarioMap.getLogin()));
				this.popupBusquedaUsuario.setBeanRegistroSeleccionado(null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopUpUsuario = false;
		this.popupBusquedaUsuario.setBeanRegistroSeleccionado(null);
	}

	/**
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private List cargarDatos(MantenimientoOperacionCDRUsuarioSearchForm f)
			throws Exception {
		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);

		String login = "";// usuario.getLogin();
		if (StringUtils.isNotBlank(login)) {
			criteria.put("usuario", login);
			f.setUsuario(login);
		} else {
			if (StringUtils.isNotBlank(f.getUsuario())) {
				criteria.put("usuario", f.getUsuario());
			}
		}

		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(f.getNombres())) {
			criteria.put("nombres", f.getNombres());
		}
		if (StringUtils.isNotBlank(f.getApellidos())) {
			criteria.put("apellidos", f.getApellidos());
		}
		if (StringUtils.isNotBlank(f.getOperacion())) {
			criteria.put("operacion", f.getOperacion());
		}

		if (log.isDebugEnabled()) {
			log.debug(criteria.toString());
		}

		MantenimientoOperacionCDRUsuarioService service = (MantenimientoOperacionCDRUsuarioService) getBean("spusicc.mantenimientoOperacionCDRUsuarioService");
		List lista = service.getOperacionesCDRxUsuario(criteria);

		return lista;
	}

	/**
	 * @param event
	 * @throws IOException
	 */
	public void loadfile(FileUploadEvent event) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		MantenimientoOperacionCDRUsuarioForm f = (MantenimientoOperacionCDRUsuarioForm) this.formMantenimiento;
		UsuarioService service = (UsuarioService) getBean("usuarioService");
		Usuario usuario = null;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		List listaClientes = new ArrayList();
		// UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		f.setClienteFile(event.getFile());
		InputStream is = event.getFile().getInputstream(); // archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		// Long longitudPais= pais.getLongitudCodigoCliente();
		String linea = "";
		String loginUsuario = "";
		int cont = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			loginUsuario = linea.trim();
			criteria.put("loginUsuario", loginUsuario);

			List list = service.getUsuariosByCriteria(criteria);

			LabelValue bean = null;
			if (list != null && list.size() > 0) {
				usuario = (Usuario) list.get(0);
				bean = new LabelValue(loginUsuario, "1");
			} else {
				bean = new LabelValue(loginUsuario, "0");
				cont++;
			}
			listaClientes.add(bean);

		}

		f.setCodigosErradosFile("");

		if (cont != 0) {
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");
			f.setErrados(cont);
		} else {
			f.setErrados(0);
		}

		this.stoExcepcionClientesList = listaClientes;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		List l = new ArrayList();

		LabelValue[] lv = aSvc
				.getTiposOperacionesByOperacion((f.getOperaciones() == null) ? new String[0]
						: f.getOperaciones());

		if (lv != null && lv.length > 0) {
			for (int i = 0; i < lv.length; i++) {
				Base base = new Base();
				base.setCodigo(lv[i].getValue());
				base.setDescripcion(lv[i].getLabel());
				l.add(base);
			}
			;

			this.tiposOperacionUsuarioList = l;
		}

		if (f.getErrados() == 0) {
			f.setIndicadorClientes("1");
		} else {
			f.setIndicadorClientes("0");
		}

		this.mostrarGrilla = true;
		f.setUsuario(null);

		this.indicadorCerrarVentana = Constants.NRO_CERO;
	}

	/**
	 * @param event
	 */
	public void loadTipoOperaciones(ValueChangeEvent event) {
		String[] operaciones = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue[] lv;
		List l = new ArrayList();

		lv = ajax.getTiposOperacionesByOperacion(operaciones);

		if (lv != null && lv.length > 0) {
			for (int i = 0; i < lv.length; i++) {
				Base base = new Base();
				base.setCodigo(lv[i].getValue());
				base.setDescripcion(lv[i].getLabel());
				l.add(base);
			}
		}

		this.tiposOperacionUsuarioList = l;

	}

	/**
	 * @param event
	 */
	public void limpiarArchivo(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiarArchivo' method");
		}

		MantenimientoOperacionCDRUsuarioForm f = (MantenimientoOperacionCDRUsuarioForm) this.formMantenimiento;
		f.setUsuario("");
		f.setErrados(0);
		f.setIndicadorClientes("0");
		f.setClienteFile(null);
		this.indicadorCerrarVentana = Constants.NRO_CERO;
		this.mostrarGrilla = false;
		this.stoExcepcionClientesList = new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		String mensaje = null;
		MantenimientoOperacionCDRUsuarioForm f = (MantenimientoOperacionCDRUsuarioForm) this.formMantenimiento;
		if (f.getUsuario().isEmpty() && f.getClienteFile() == null)
			mensaje = this
					.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario.validacion");

		if (f.getErrados() > 0)
			mensaje = this
					.getResourceMessage("mantenimientoOperacionCDRUsuarioForm.error.usuario.lista");

		return mensaje;
	}

	/**
	 * @return
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	public List getOperacionCDRUsuarioList() {
		return operacionCDRUsuarioList;
	}

	public void setOperacionCDRUsuarioList(List operacionCDRUsuarioList) {
		this.operacionCDRUsuarioList = operacionCDRUsuarioList;
	}

	public String getIndicadorCerrarVentana() {
		return indicadorCerrarVentana;
	}

	public void setIndicadorCerrarVentana(String indicadorCerrarVentana) {
		this.indicadorCerrarVentana = indicadorCerrarVentana;
	}

	public List getStoExcepcionClientesList() {
		return stoExcepcionClientesList;
	}

	public void setStoExcepcionClientesList(List stoExcepcionClientesList) {
		this.stoExcepcionClientesList = stoExcepcionClientesList;
	}

	public List getTiposOperacionUsuarioList() {
		return tiposOperacionUsuarioList;
	}

	public void setTiposOperacionUsuarioList(List tiposOperacionUsuarioList) {
		this.tiposOperacionUsuarioList = tiposOperacionUsuarioList;
	}

	public List getOperacionUsuarioList() {
		return operacionUsuarioList;
	}

	public void setOperacionUsuarioList(List operacionUsuarioList) {
		this.operacionUsuarioList = operacionUsuarioList;
	}

	public BusquedaUsuarioPOPUPSearchAction getPopupBusquedaUsuario() {
		return popupBusquedaUsuario;
	}

	public void setPopupBusquedaUsuario(
			BusquedaUsuarioPOPUPSearchAction popupBusquedaUsuario) {
		this.popupBusquedaUsuario = popupBusquedaUsuario;
	}

	public boolean isMostrarPopUpUsuario() {
		return mostrarPopUpUsuario;
	}

	public void setMostrarPopUpUsuario(boolean mostrarPopUpUsuario) {
		this.mostrarPopUpUsuario = mostrarPopUpUsuario;
	}

	public static String getPopupUsuario() {
		return POPUP_USUARIO;
	}

	public boolean isMostrarGrilla() {
		return mostrarGrilla;
	}

	public void setMostrarGrilla(boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}
}
