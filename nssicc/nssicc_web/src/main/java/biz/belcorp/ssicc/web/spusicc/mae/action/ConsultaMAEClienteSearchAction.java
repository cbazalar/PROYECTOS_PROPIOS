package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteVinculo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Concurso;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Premio;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ConsultaMAEClienteSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEModificacionClienteForm;
import biz.belcorp.ssicc.web.spusicc.sto.action.MantenimientoSTOSolicitudCreditoAction;

@ManagedBean
@SessionScoped
public class ConsultaMAEClienteSearchAction extends
		BaseMantenimientoSearchAbstractAction {
	
	public static final String MAE_TIPO_DIRECCION_DOMICILIO = "01";
	public static final String MAE_TIPO_DIRECCION_TRABAJO = "03";

	public static final String MAE_OID_TIPO_VIA_DEFAULT = "2001";
	public static final String MAE_OID_ESTADO_CIVIL_DEFAULT = "2001";
	public static final String MAE_COD_SEXO_DEFAULT = "F";
	public static final String MAE_OID_TRATAMIENTO_DEFAULT = "2";
	public static final String MAE_OID_TIPO_DIRECCION_DISTRIBUCION = "2007";

	public String maeEstadoRegistrada = Constants.MAE_ESTADO_REGISTRADA;
	public String maeEstadoRetirada = Constants.MAE_ESTADO_RETIRADA;
	private List maeDeudorasList;
	public static final String MAE_CONTROL_APELLIDOPATERNO = "apellidoPaterno";
	public static final String MAE_CONTROL_SUBTIPOCLIENTE = "subTipoCliente";
	public static final String MAE_CONTROL_ZONA = "codigoZona";
	public static final String MAE_CONTROL_NIVEL1 = "nivel1";
	public static final String MAE_CONTROL_CLASIFICACION = "tipoClasificacion";
	public static final String MAE_CONTROL_NOMBREVIA = "nombreVia";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA = "TR";
	private String codigoZona;
	public static final Long MAE_CLIENTE_CONTACTO_DUMMY = new Long(461343);
	public static final String MAE_LONGITUD_CODIGO_ZONA = "4";
	private static final String MAE_DEUDORAS_LIST = "maeClienteDeudorasAval";
	private static final String MAE_FLAG_DEUDORAS = "maeFlagDeudoras";

	private LabelValue[] maeClienteNivel3AvalList;
	private LabelValue[] maeClienteNivel2AvalList;
	private Integer indPrincipalComunicacion;
	private String pathPremios = "";
	private List maeClienteClasificacionList = new ArrayList();
	private LabelValue[] maeTerritorioList;
	private List consultaMaeClienteList = new ArrayList();
	private List maeZonaList = new ArrayList();
	private List siccSubTipoClienteList = new ArrayList();
	private List maeIndicadorActivoList;
	private LabelValue[] siccPeriodoList;
	private List siccPeriodoInicialList = new ArrayList();
	private List siccTipoClasificacionList = new ArrayList();
	private LabelValue[] siccClasificacionList;
	private List siccTipoDocumentoList = new ArrayList();
	
	private List siccSubTipoDocumentoList = new ArrayList();
	private List siccSubTipoDocumentoList2 = new ArrayList();

	private List maeClienteEstadoCivilList = new ArrayList();
	private List maeClienteTratamientoList = new ArrayList();
	private List maeClienteSexoList = new ArrayList();
	private Cliente maeDtoCliente;
	private List maeClienteTipoViaList = new ArrayList();
	private List maeClienteSubTipoList = new ArrayList();
	private LabelValue[] maeClienteNivel1List;
	private LabelValue[] maeClienteNivel1AvalList;

	private List maeClienteNivelEstudioList = new ArrayList();
	private List maeClienteNacionalidadList = new ArrayList();
	private List maeClienteTipoDireccionList = new ArrayList();
	private List maeClienteTipoVinculoList = new ArrayList();

	private List maeTipoCutisList = new ArrayList();
	private List maeOtrasMarcasList = new ArrayList();
	private LabelValue[] maeCiudadList;
	private LabelValue[] maeCiudadCTList;
	private LabelValue[] maeClienteNivel2List;
	private LabelValue[] maeClienteNivel3List;
	private LabelValue[] maeClienteNivel4List;
	private LabelValue[] maeClienteNivel5List;
	private LabelValue[] maeClienteNivel6List;

	private LabelValue[] maeClienteNivel3CTList;
	private LabelValue[] maeClienteNivel2CTList;
	private LabelValue[] maeClienteNivel4CTList;
	private LabelValue[] maeClienteNivel5CTList;
	private LabelValue[] maeClienteNivel6CTList;

	private LabelValue[] maeCiudadVacList;
	private LabelValue[] maeClienteNivel2VACList;
	private LabelValue[] maeClienteNivel3VACList;
	private LabelValue[] maeClienteNivel4VACList;
	private LabelValue[] maeClienteNivel5VACList;
	private LabelValue[] maeClienteNivel6VACList;
	
	private LabelValue[] maeClienteNivel2AVAList = {};
	private LabelValue[] maeClienteNivel3AVAList = {};
	private LabelValue[] maeClienteNivel4AVAList = {};
	private LabelValue[] maeCiudadAVAList = {};
	
	private boolean indicadorFactElect;
	private boolean chkTiposCliente;
	private boolean chkDireccionDomicilio;
	private boolean chkDireccionOpcional;
	private boolean chkDireccionVacaciones;
	private boolean chkVinculos;
	private boolean chkReferencias;
	private boolean chkSeccionOtros;

	private boolean principalPrimero = false;
	private boolean principalSegundo = true;
	private boolean flagMostrarDocumentoFiscal;
	private String maeFlagDeudoras;
	private String idPremiosMaeModificacion;
	private Object beanRegistroTipoClienteSeleccionado;
	private Object beanRegistroSubTipoClienteSeleccionado;
	private String codigoFinalCliente;
	private boolean primeraVezDespliegueDireccionEntrega = true;
	private boolean primeraVezDespliegueReferencias = true;
	private boolean valorIndicadorFactElectB;
	private boolean valorIndicadorFactElectAuxB;
	private boolean primeraVez = true;
	
	
	private String paginaPadre;
	private String codigoConsultora;
	private boolean subConsulta;
	
	private String mensajeCaracteres1;
	private String mensajeCaracteres2;
	private String mensajeCaracteres3;
	
	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;
	
	private boolean mostrarPopupCliente;
	private boolean mostrarPopupConsultora;

	private boolean mostrarBotonEliminarDireccion;
	private boolean mostrarBotonEliminarDireccionCT;
	
	private boolean mostrarBotonEliminarTipo1;
	private boolean mostrarBotonEliminarTipo2;
	
	
	private boolean indicadorImpresionPaqDocBoolean;
	
	//INI ECU-SiCC-2015-0036
		private List maeTipoPersonaList = new ArrayList();
		private List maeOrigenIngresoList = new ArrayList();
		private String copiaDireccionEntrega ="";
	//FIN ECU-SiCC-2015-0036
		
	private List maeBancoList = new ArrayList();
	private boolean showBancoCuenta;
	
	private List maeClienteCodigoTerritorialCorrespondeList = new ArrayList();
	
	private List maeTipoCuentaList = new ArrayList();
	private boolean showTipoCuentaCuentaCorriente;
	

	public void abrirPopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		try {
			this.getRequestContext().execute("PF('popUpPeriodo').show()");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void redefinirPeriodo(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'redefinirPeriodo' method");
		}
		String mensaje = "";
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		// datos del cliente IMPORTENTE
		Cliente cliente = maeDtoCliente;

		Map criteriaConcurso = new HashMap();
		List listClienteConcursoPremio = new ArrayList();
		boolean mostrarPantallaPremios = false;		

		try {
			criteriaConcurso.put("oidPais", f.getOidPais());
			criteriaConcurso.put("oidPeriodo", f.getOidPeriodoRedefinir());

			// grabamos los concursos y premios para el cliente nuevo
			if (f.getCodigoConsultoraRecomendante() != null
					&& !("".equals(f.getCodigoConsultoraRecomendante()))) {
				criteriaConcurso.put("oidDirigConsul",
						Constants.MAE_OID_DIRIG_CONSUL);
				criteriaConcurso.put("oidBaseCalcu",
						Constants.MAE_OID_BASE_CALCU_RECOMEN);

				String codigoZona = clienteService.getZonaCliente(
						f.getCodigoPais(), f.getCodigoConsultoraRecomendante());
				criteriaConcurso.put("codigoZona", codigoZona);
				criteriaConcurso.put("codigoCliente",
						f.getCodigoConsultoraRecomendante());
				//

				// session.setAttribute("codigoZonaConcurso", codigoZona);

				List listConcursos = clienteService
						.getConcursos(criteriaConcurso);
				Iterator itConcursos = listConcursos.iterator();

				while (itConcursos.hasNext()) {
					Concurso concurso = (Concurso) itConcursos.next();

					// si no pertenece a la zona geografica del concurso, no se
					// le asigna dicho concurso a la consultora
					if (concurso.getTotalGeografico().intValue() > 0
							&& concurso.getCodigoZona() == null)
						continue;

					criteriaConcurso.put("oidConcurso",
							concurso.getOidConcurso());
					List listPremios = clienteService
							.getPremios(criteriaConcurso);

					// Si el Concurso de Rec. No tiene Niveles Electivos, no se
					// muestra,
					// Anterior: if(concurso.getNumeroNivel().intValue() > 1)
					if (concurso.getNumeroNivelSelectivo().intValue() == 0) {
						ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
						concursoPremio.setOidModulo(new Long(
								Constants.MAE_CLIENTE_MODULO_MAE));

						concursoPremio.setCodigoClienteRecomendante(f
								.getCodigoConsultoraRecomendante());
						concursoPremio
								.setOidConcurso(concurso.getOidConcurso());

						concursoPremio.setCodigoClienteRecomendado(cliente
								.getCodigo());
						concursoPremio.setOidPeriodo(new Long(f
								.getOidPeriodoRedefinir()));

						listClienteConcursoPremio.add(concursoPremio);
					} else {

						// si el premio es de un nivel se graba
						if (listPremios.size() > 1)
							mostrarPantallaPremios = true;

						ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
						concursoPremio.setOidModulo(new Long(
								Constants.MAE_CLIENTE_MODULO_MAE));

						concursoPremio.setCodigoClienteRecomendante(f
								.getCodigoConsultoraRecomendante());
						concursoPremio
								.setOidConcurso(concurso.getOidConcurso());

						concursoPremio.setCodigoClienteRecomendado(cliente
								.getCodigo());
						concursoPremio.setOidPeriodo(new Long(f
								.getOidPeriodoRedefinir()));

						// Seteamos a null si el indicador esta en uno
						if (StringUtils.equals(concurso.getTipoPremio(),
								Constants.ESTADO_ACTIVO)) {
							concursoPremio.setOidNivelPremio(null);
							concursoPremio.setNumeroPremio(null);
						} else {
							Premio premio = (Premio) listPremios.get(0);
							concursoPremio.setOidNivelPremio(premio
									.getOidPremio());
							concursoPremio.setNumeroPremio(premio
									.getNumeroPremio());
						}

						listClienteConcursoPremio.add(concursoPremio);
					}
				}

				// OCULTAR LA PANTALLA DE PREMIOS
				if (listConcursos != null && listConcursos.size() > 0) {
					// verificamos si el indicador TPRM_OID_TIPO_PION tipopremio
					// esta en 1
					// TODOS los registros de la lista van a tenr el mismo valor
					Concurso concurso = (Concurso) listConcursos.get(0);

					// No se muestra la pantalla de premios
					if (StringUtils.equals(concurso.getTipoPremio(),
							Constants.ESTADO_ACTIVO)) {
						f.setMostrarPantallaPremios(false);
						mostrarPantallaPremios = false;
					}
				}
				//

			}

			criteriaConcurso.put("oidCliente", cliente.getOid().toString());
			criteriaConcurso.put("listClienteConcursoPremio",
					listClienteConcursoPremio);
			criteriaConcurso.put("codigoUsuario", usuario.getLogin());

			if (f.getCodigoEstatus().equals(Constants.MAE_ESTADO_REGISTRADA))
				clienteService.updateClientePeriodo(criteriaConcurso);
			else if (f.getCodigoEstatus().equals(Constants.MAE_ESTADO_RETIRADA))
				clienteService.updateClientePeriodoRetiradas(criteriaConcurso);

			if (!mostrarPantallaPremios) {
				/*
				 * messages.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage(
				 * "mantenimientoMAEModificacionClienteForm.updated",
				 * cliente.getCodigo()));
				 */
				mensaje = getResourceMessage(
						"mantenimientoMAEModificacionClienteForm.updated",
						new Object[] { cliente.getCodigo() });
				addInfo("Mensaje", mensaje);

			}

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			addInfo(this.getResourceMessage("errors.detail"),
					this.getResourceMessage("mantenimientoMAEModificacionClienteForm.updated"));

			// return mapping.findForward("redefinirPeriodo");
		}

		if (mostrarPantallaPremios) {
			this.pathPremios = f.getOidPeriodoRedefinir() + "|"
					+ f.getOidPais() + "|" + f.getCodigoCliente() + "|"
					+ f.getCodigoConsultoraRecomendante();
			// session.setAttribute("idPremiosMAEModificacion",
			// this.pathPremios);

			// return mapping.findForward("premios");
		} else
			this.getRequestContext().execute("PF('popUpPeriodo').hide()");
		try {
			redireccionarPagina("consultaMAEClienteList.xhtml",
					"?codigoCliente=" + cliente.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void calcularEdad() {
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		Calendar cal = Calendar.getInstance();
		cal.setTime(f.getFechaNacimientoD());
		int anioNac = cal.get(Calendar.YEAR);
		cal.setTime(new Date());
		int anioActual = cal.get(Calendar.YEAR);
		if (anioActual - anioNac > 0)
			f.setEdad(String.valueOf(anioActual - anioNac));

	}

	public String obtenerCodigoCliente(String codigoCliente) {
		return getResourceMessage(
				"mantenimientoMAEModificacionClienteForm.updated",
				new Object[] { codigoCliente });

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393984440572918487L;

	@Override
	protected String getSalirForward() {		
		if(this.subConsulta){
			try {
				MantenimientoSTOSolicitudCreditoAction action = findManageBean("mantenimientoSTOSolicitudCreditoAction");
				action.inicializarValores();
				return "mantenimientoSTOSolicitudCreditoForm";
			} catch (Exception e) {
				return "";
			}				
		}else
				return "consultaMAEClienteList";
		
		
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMAEModificacionClienteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ConsultaMAEClienteSearchForm objForm = new ConsultaMAEClienteSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("inicio setFindAttributes");
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		ConsultaMAEClienteSearchForm searchForm = (ConsultaMAEClienteSearchForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		List resultado = clienteService
				.getListClientesByCriteria(getCriteriaSearch(searchForm));

		log.debug("Pintando el tamaño de la lista " + resultado.size());
		setPrimeraVez(true);
		consultaMaeClienteList = resultado;

		if (resultado.size() == 0) {

			addInfo("Mensaje",
					getResourceMessage("busquedaClienteList.notfound"));
			searchForm.setClienteUnico("");
		} else {
			Iterator it = resultado.iterator();
			Map mapPrimerRegistro = (Map) it.next();
			String codigoCliente = (String) mapPrimerRegistro.get("codigo");
			boolean unico = true;

			while (it.hasNext()) {
				Map mapRegistro = (Map) it.next();
				String codigoAux = (String) mapRegistro.get("codigo");

				if (!codigoCliente.equals(codigoAux)) {
					unico = false;
					break;
				}
			}

			if (unico)
				searchForm.setClienteUnico(codigoCliente);
			else
				searchForm.setClienteUnico("");
		}

		if (StringUtils.isNotEmpty(searchForm.getCodigoZona())) {// se recargan
																	// los
																	// territoris
			LabelValue[] listTerritorio = ajaxService
					.getTerritoriosByPaisMarcaCanalZona(pais.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT,
							searchForm.getCodigoZona());
			maeTerritorioList = listTerritorio;

		}
		String primerValor = "";
		boolean cargaInmediata = true;
		if (resultado.size() > 0) {
			primerValor = ((HashMap) resultado.get(0)).get("codigo").toString();
		} else {
			cargaInmediata = false;
		}
		for (int i = 0; i < resultado.size(); i++) {
			if (!((HashMap) resultado.get(i)).get("codigo").toString()
					.equals(primerValor)) {
				cargaInmediata = false;
				break;
			}
		}
		if (cargaInmediata) {
			beanRegistroSeleccionado = resultado.get(0);
			edit();
		}

		return resultado;
	}

	private String obtenerFechaPeriodo(
			MantenimientoMAEClienteService clienteService, String oidPeriodo,
			String fechaActual) {
		String fechaAux = fechaActual;

		// validamos que la fecha actual se encuentre dentro del rango de la
		// campaña de ingreso
		Map criteriaFechas = new HashMap();
		criteriaFechas.put("oidPeriodo", oidPeriodo);
		criteriaFechas.put("fecha", fechaAux);

		String fechaInicio = clienteService
				.getFechaInicioPeriodo(criteriaFechas);
		if (fechaInicio == null) {
			criteriaFechas.remove("fecha");
			fechaAux = clienteService.getFechaInicioPeriodo(criteriaFechas);
		}

		return fechaAux;
	}

	private void obtenerCriteriosBusqueda(Cliente cliente,
			MantenimientoMAEModificacionClienteForm f) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		String criterioBusqueda1 = clienteService.getCriterioBusqueda1(f
				.getOidPais());
		String criterioBusqueda2 = clienteService.getCriterioBusqueda2(f
				.getOidPais());
		String resultado1 = null;
		String resultado2 = null;

		log.debug("criterioBusqueda1 : " + criterioBusqueda1);
		log.debug("criterioBusqueda2 : " + criterioBusqueda2);

		if (criterioBusqueda1 != null) {
			if (criterioBusqueda1.equals("MAECLIEAPELL1"))
				resultado1 = cliente.getApellido1();
			if (criterioBusqueda1.equals("MAECLIEAPELL2"))
				resultado1 = cliente.getApellido2();
			if (criterioBusqueda1.equals("MAECLIEAPELLCA"))
				resultado1 = cliente.getApellidoCasada();
			if (criterioBusqueda1.equals("MAECLIENOM1"))
				resultado1 = cliente.getNombre1();
			if (criterioBusqueda1.equals("MAECLIENOM2"))
				resultado1 = cliente.getNombre2();
			if (criterioBusqueda1.equals("MAECLIETRAT"))
				resultado1 = cliente.getTratamiento();
			if (criterioBusqueda1.equals("MAECLIESEXO"))
				resultado1 = cliente.getSexo();
			if (criterioBusqueda1.equals("MAECLIEFECHING"))
				resultado1 = String
						.valueOf(cliente.getFechaIngreso().getTime());
			if (criterioBusqueda1.equals("MAECLIEFORMPA"))
				resultado1 = cliente.getOidFormaPago().toString();
		}

		if (criterioBusqueda2 != null) {
			if (criterioBusqueda2.equals("MAECLIEAPELL1"))
				resultado2 = cliente.getApellido1();
			if (criterioBusqueda2.equals("MAECLIEAPELL2"))
				resultado2 = cliente.getApellido2();
			if (criterioBusqueda2.equals("MAECLIEAPELLCA"))
				resultado2 = cliente.getApellidoCasada();
			if (criterioBusqueda2.equals("MAECLIENOM1"))
				resultado2 = cliente.getNombre1();
			if (criterioBusqueda2.equals("MAECLIENOM2"))
				resultado2 = cliente.getNombre2();
			if (criterioBusqueda2.equals("MAECLIETRAT"))
				resultado2 = cliente.getTratamiento();
			if (criterioBusqueda2.equals("MAECLIESEXO"))
				resultado2 = cliente.getSexo();
			if (criterioBusqueda2.equals("MAECLIEFECHING"))
				resultado2 = String
						.valueOf(cliente.getFechaIngreso().getTime());
			if (criterioBusqueda2.equals("MAECLIEFORMPA"))
				resultado2 = cliente.getOidFormaPago().toString();
		}

		log.debug("resultado1 : " + resultado1);
		log.debug("resultado2 : " + resultado2);

		cliente.setCriterioBusqueda1(resultado1);
		cliente.setCriterioBusqueda2(resultado2);
	}

	private Cliente obtenerCliente(MantenimientoMAEModificacionClienteForm f,
			List listSubTipo, List listClasificacion) throws Exception {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(new Date(System.currentTimeMillis()));

		boolean tieneTipoClienteConsultora = false;
		String oidSubTipoClientePrincipal = ((ClienteSubTipo) listSubTipo
				.get(0)).getOidSubTipoCliente().toString();

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		// Verifico si el cliente tiene un tipo de cliente CONSULTORA
		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO, listSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
						listSubTipo))) {
			tieneTipoClienteConsultora = true;
		}

		// datos del cliente
		Cliente cliente = maeDtoCliente;
		ClienteAdicional clienteAdicional = cliente.getClienteAdicional();

		cliente.setOidPais(new Long(f.getOidPais()));
		cliente.setCodigo(f.getCodigoCliente());
		cliente.setDigitoControl(f.getDigitoControl());
		cliente.setApellido1(f.getApellidoPaterno());
		cliente.setApellido2(f.getApellidoMaterno());
		cliente.setApellidoCasada(f.getApellidoCasada());
		cliente.setNombre1(f.getNombre1());
		cliente.setNombre2(f.getNombre2());
		cliente.setTratamiento(f.getTratamiento());
		cliente.setSexo(f.getSexo());
		if (cliente.getIndicadorFichaInscripcion() == null)
			cliente.setIndicadorFichaInscripcion(new Integer(0));
		if (cliente.getOidFormaPago() == null)
			cliente.setOidFormaPago(clienteService
					.getOidFormaPagoSubTipoCliente(f.getOidPais(),
							oidSubTipoClientePrincipal));
		cliente.setOidPeriodoIngreso(new Long(f.getOidPeriodo()));
		cliente.setCodigoUsuario(usuario.getLogin());
		cliente.setCodigoAnterior(f.getCodigoAnterior());
		obtenerCriteriosBusqueda(cliente, f);

		// SUBTIPOS DEL CLIENTE
		Iterator itClienteSubTipo = cliente.getListClienteSubTipo().iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) itClienteSubTipo
					.next();
			boolean encontrado = false;

			for (int i = 0; i < listSubTipo.size(); i++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) listSubTipo
						.get(i);

				if ((clienteSubTipo.getCodigoTipoCliente()
						.equals(clienteSubTipoAux.getCodigoTipoCliente()))
						&& (clienteSubTipo.getCodigoSubTipoCliente()
								.equals(clienteSubTipoAux
										.getCodigoSubTipoCliente()))) {
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				clienteSubTipo.setEliminar(true);

				// Si el subTipoCliente ha sido eliminado, tambien sera
				// eliminado sus clasificaciones
				Iterator itClasificaciones = clienteSubTipo
						.getListClienteClasificacion().iterator();
				while (itClasificaciones.hasNext()) {
					ClienteClasificacion clienteClasificacion = (ClienteClasificacion) itClasificaciones
							.next();

					clienteClasificacion.setEliminar(true);
				}
			}
		}

		itClienteSubTipo = listSubTipo.iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) itClienteSubTipo
					.next();
			boolean encontrado = false;

			for (int i = 0; i < cliente.getListClienteSubTipo().size(); i++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) cliente
						.getListClienteSubTipo().get(i);

				if ((clienteSubTipo.getCodigoTipoCliente()
						.equals(clienteSubTipoAux.getCodigoTipoCliente()))
						&& (clienteSubTipo.getCodigoSubTipoCliente()
								.equals(clienteSubTipoAux
										.getCodigoSubTipoCliente()))) {
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				clienteSubTipo.setOidCliente(cliente.getOid());
				cliente.getListClienteSubTipo().add(clienteSubTipo);
			}
		}

		// CLASIFICACIONES DEL CLIENTE
		if (listClasificacion != null && listClasificacion.size() > 0) {
			Iterator it = listClasificacion.iterator();

			while (it.hasNext()) {
				ClienteClasificacion clienteClasificacion = (ClienteClasificacion) it
						.next();

				/* INI SA PER-SiCC-2013-0147 */
				if (clienteClasificacion.getOidPeriodo() == null)
					clienteClasificacion.setOidPeriodo(new Long(f
							.getOidPeriodo()));
				/* FIN SA PER-SiCC-2013-0147 */

				clienteClasificacion.setFechaClasificacion(sdf.parse(f
						.getFechaIngreso()));

				Long oidSubTipo = clienteClasificacion.getOidClienteSubTipo();

				for (int i = 0; i < cliente.getListClienteSubTipo().size(); i++) {
					ClienteSubTipo clienteSubTipo = (ClienteSubTipo) cliente
							.getListClienteSubTipo().get(i);

					if (oidSubTipo.longValue() == clienteSubTipo
							.getOidSubTipoCliente().longValue()) {
						if (clienteSubTipo.getListClienteClasificacion() == null)
							clienteSubTipo
									.setListClienteClasificacion(new ArrayList());

						// Verificamos si la clasificacion ya existe para el
						// subTipoCliente
						boolean encontrado = false;
						Iterator itClasi = clienteSubTipo
								.getListClienteClasificacion().iterator();
						while (itClasi.hasNext()) {
							ClienteClasificacion clienteClasiAux = (ClienteClasificacion) itClasi
									.next();
							if (clienteClasiAux.getOidClasificacion()
									.intValue() == clienteClasificacion
									.getOidClasificacion().intValue()) {
								encontrado = true;
								break;
							}
						}

						if (!encontrado)
							clienteSubTipo.getListClienteClasificacion().add(
									clienteClasificacion);

						break;
					}
				}
			}

			for (int i = 0; i < cliente.getListClienteSubTipo().size(); i++) {
				ClienteSubTipo clienteSubTipo = (ClienteSubTipo) cliente
						.getListClienteSubTipo().get(i);

				if (!clienteSubTipo.isEliminar())
					for (int j = 0; j < clienteSubTipo
							.getListClienteClasificacion().size(); j++) {
						boolean encontrado = false;
						ClienteClasificacion clienteClasificacion = (ClienteClasificacion) clienteSubTipo
								.getListClienteClasificacion().get(j);

						Iterator itClasi = listClasificacion.iterator();
						while (itClasi.hasNext()) {
							ClienteClasificacion clienteClasiAux = (ClienteClasificacion) itClasi
									.next();

							if (clienteClasiAux.getOidClasificacion()
									.intValue() == clienteClasificacion
									.getOidClasificacion().intValue()) {
								encontrado = true;
								break;
							}
						}

						if (!encontrado) {
							clienteClasificacion.setEliminar(true);
						}
					}
			}

		}

		// ESTATUS INICIAL DEL CLIENTE
		if (tieneTipoClienteConsultora
				&& (clienteAdicional.getOidEstatusCliente() == null)) {
			ClienteHistoricoEstatus clienteHistoricoEstatus = new ClienteHistoricoEstatus();
			clienteHistoricoEstatus.setOidEstatus(new Long(1));
			clienteHistoricoEstatus.setOidPeriodo(new Long(f.getOidPeriodo()));
			clienteHistoricoEstatus.setOidPeriodoFin(null);
			cliente.setClienteHistoricoEstatus(clienteHistoricoEstatus);
		}

		// DATOS ADICIONALES DEL CLIENTE
		if (tieneTipoClienteConsultora
				&& (clienteAdicional.getOidEstatusCliente() == null))
			clienteAdicional.setOidEstatusCliente(new Long(1));
		clienteAdicional.setFechaNacimiento(sdf.parse(f.getFechaNacimiento()));
		clienteAdicional.setEdad(new Integer(f.getEdad()));
		clienteAdicional.setOidEstadoCivil(new Long(f.getEstadoCivil()));
		clienteAdicional.setCodigoEmpleado(f.getCodigoEmpleado());
		/* INI JJ PER-SiCC-2012-0329 */
		clienteAdicional.setCodigoCUB(f.getCodigoCUB());
		/* FIN JJ PER-SiCC-2012-0329 */
		
		/* INI ECU-SiCC-2015-0036*/
		if(f.isIndicadorCamposAdicionales()){
			clienteAdicional.setValTipoPersona(f.getTipoPersona());
			clienteAdicional.setValOrigenIngreso(f.getOrigenIngreso());
		}
		/* FIN ECU-SiCC-2015-0036*/
		
		//INICIO PER-SiCC-2015-0462
		clienteAdicional.setCodigoBanco(f.getCodigoBanco());
		clienteAdicional.setCuentaBancaria(f.getCuentaBancaria());
		//FIN PER-SiCC-2015-0462
		
		//INICIO PER-SiCC-2015-0662
		clienteAdicional.setValBanco(f.getBanco());
		clienteAdicional.setValTipoCuenta(f.getTipoCuenta());
		clienteAdicional.setValCuentaCorriente(f.getCuentaCorriente());
		//FIN PER-SiCC-2015-0662
		
		/*
		 * if(f.isMostrarUnidadAdministrativa()) { //NO SE GRABA PARA AVAL NI
		 * PARA DUPLACYZONE if(clienteAdicional.getOidNivelRiesgo()==null)
		 * clienteAdicional.setOidNivelRiesgo(new Long(4));
		 * if(clienteAdicional.getMontoLineaCredito()==null)
		 * clienteAdicional.setMontoLineaCredito(new Double(0)); }
		 */

		if (f.getGradoInstruccion() != null
				&& !("".equals(f.getGradoInstruccion())))
			clienteAdicional.setOidNivelEstudios(new Long(f
					.getGradoInstruccion()));
		if (f.getNacionalidad() != null && !("".equals(f.getNacionalidad())))
			clienteAdicional.setOidNacionalidad(new Long(f.getNacionalidad()));
		if (tieneTipoClienteConsultora) {
			if (clienteAdicional.getOidPeriodoLineaCredito() == null)
				clienteAdicional.setOidPeriodoLineaCredito(new Long(f
						.getOidPeriodo()));
			if (clienteAdicional.getOidPeriodoNivelRiesgo() == null)
				clienteAdicional.setOidPeriodoNivelRiesgo(new Long(f
						.getOidPeriodo()));
		}
		if (clienteAdicional.getIndicadorCorrespondencia() == null)
			clienteAdicional.setIndicadorCorrespondencia(new Integer(1));

		/* INI PER-SiCC-2015-0007 */

		if (f.getIndicadorDocFiscal() != null) {
			if (f.isIndicadorDocFiscalB()) {
				clienteAdicional.setIndDocFiscal("S");
			} else {
				clienteAdicional.setIndDocFiscal("N");
			}

		}

		/* FIN PER-SiCC-2015-0007 */

		if (f.getTipoCutis() != null && !("".equals(f.getTipoCutis())))
			clienteAdicional.setOidTipoCutis(new Long(f.getTipoCutis()));
		else
			clienteAdicional.setOidTipoCutis(null);

		if (f.getOtrasMarcas() != null && !("".equals(f.getOtrasMarcas())))
			clienteAdicional.setOidOtrasMarcas(new Long(f.getOtrasMarcas()));
		else
			clienteAdicional.setOidOtrasMarcas(null);

		clienteAdicional
				.setIndicadorCompromiso(f.getValorIndicadorCompromiso());
		clienteAdicional.setMotivo(f.getMotivo());
		if (StringUtils.equals(clienteAdicional.getIndicadorCompromiso(),
				Constants.NUMERO_CERO))
			clienteAdicional.setMotivo("");

		/* INI SA COS-SiCC-2013-0031 */
		if (f.isIndicadorSeccionOtros()
				&& f.isMostrarIndicadorImpresionPaqDoc()) {
			//if (Constants.SI.equals(f.getIndicadorImpresionPaqDoc()))
			if (this.indicadorImpresionPaqDocBoolean)
				clienteAdicional.setIndicadorImpresionPaqDoc(null);
			else {
				clienteAdicional.setIndicadorImpresionPaqDoc(Constants.NO);
			}
		}
		/* FIN SA COS-SiCC-2013-0031 */

		/* INI JP PER-SiCC-2013-0480 */
		String indicadorDocumentosLegales = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_DOCUM_LEGAL);
		if (Constants.MAE_IMPRI_DOCUM_LEGAL.equals(indicadorDocumentosLegales)) {
			f.setIndicadorDocumentosLegales(true);
		} else {
			f.setIndicadorDocumentosLegales(false);
		}

		if (f.isIndicadorDocumentosLegales()) {

			if (Constants.NUMERO_UNO.equals(f.getIndDocumentoPrincipal())) {
				String codTipoDocLegal = clienteService.getCodigoTipoDocLegal(f
						.getTipoDocumentoIdentidad());
				if (StringUtils.isNotEmpty(codTipoDocLegal)) {
					if (codTipoDocLegal.equals(f.getTipoDocumentoIdentidad()))
						clienteAdicional.setIndicadorImpresionDocumentos("0");
					else
						clienteAdicional.setIndicadorImpresionDocumentos("1");
				} else
					clienteAdicional.setIndicadorImpresionDocumentos("1");
				/* INI PER-SiCC-2014-0162 */
				if (f.getTipoDocumentoIdentidad().compareTo(
						Constants.MAE_CODIGO_RUC_2) == 0
						|| f.getTipoDocumentoIdentidad()
								.compareTo(
										Constants.MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT) == 0) {
					clienteAdicional.setUsuCarg(null);
					clienteAdicional.setFecCarg(null);
					clienteAdicional.setTipCarg(null);
					clienteAdicional.setIndImprDocu("0");

				}
				if (f.getTipoDocumentoIdentidad().compareTo(
						Constants.MAE_CODIGO_RUC_2) != 0
						&& f.getTipoDocumentoIdentidad()
								.compareTo(
										Constants.MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT) != 0) {
					clienteAdicional.setUsuCarg(usuario.getLogin());
					clienteAdicional.setFecCarg(new Date(System
							.currentTimeMillis()));
					clienteAdicional.setTipCarg("3");
					clienteAdicional.setIndImprDocu(f
							.getValorIndicadorFactElect());
				}
				/* FIN PER-SiCC-2014-0162 */
			} else if (Constants.NUMERO_DOS
					.equals(f.getIndDocumentoPrincipal())) {
				String codTipoDocLegal2 = clienteService
						.getCodigoTipoDocLegal(f.getTipoDocumentoIdentidad2());
				if (StringUtils.isNotEmpty(codTipoDocLegal2)) {
					if (codTipoDocLegal2.equals(f.getTipoDocumentoIdentidad2()))
						clienteAdicional.setIndicadorImpresionDocumentos("0");
					else
						clienteAdicional.setIndicadorImpresionDocumentos("1");
				} else
					clienteAdicional.setIndicadorImpresionDocumentos("1");
				/* INI PER-SiCC-2014-0162 */
				if (f.getTipoDocumentoIdentidad2().compareTo(
						Constants.MAE_CODIGO_RUC_2) == 0
						|| f.getTipoDocumentoIdentidad2()
								.compareTo(
										Constants.MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT) == 0) {
					clienteAdicional.setUsuCarg(null);
					clienteAdicional.setFecCarg(null);
					clienteAdicional.setTipCarg(null);
					clienteAdicional.setIndImprDocu("0");
				}
				if (f.getTipoDocumentoIdentidad2().compareTo(
						Constants.MAE_CODIGO_RUC_2) != 0
						&& f.getTipoDocumentoIdentidad2()
								.compareTo(
										Constants.MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT) != 0) {
					clienteAdicional.setUsuCarg(usuario.getLogin());
					clienteAdicional.setFecCarg(new Date(System
							.currentTimeMillis()));
					clienteAdicional.setTipCarg("3");
					clienteAdicional.setIndImprDocu(f
							.getValorIndicadorFactElectAux());

				}
				/* FIN PER-SiCC-2014-0162 */
			}

		} else
			clienteAdicional.setIndicadorImpresionDocumentos("1");
		/* FIN JP PER-SiCC-2013-0480 */
		
		//INI PER-SiCC-2015-0589 
		if(f.isIndicadorCalcPercep()){
			clienteAdicional.setIndicadorCalularPercepcion(f.isBooleanValorIndicadorCalcPercep()?"S":"N");
		}else{
			clienteAdicional.setIndicadorCalularPercepcion(null);
		}
		//FIN PER-SiCC-2015-0589

		cliente.setClienteAdicional(clienteAdicional);

		if (!f.isEsDuplaCyzone()) {
			int i2 = 0;
			int i3 = 0;

			for (int i = 0; i < cliente.getListClienteIdentificacion().size()
					&& i < 3; i++) {
				ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) cliente
						.getListClienteIdentificacion().get(i);

				if (i == 0) {
					if (f.isBorradoDocumentoIdentidad1()) {
						clienteIdentificacion.setEliminar(true);

						i2 = 1;
						i3 = 2;
					} else {
						if (clienteIdentificacion.getIndicadorPrincipal()
								.intValue() == 1) { // Representa el documento
													// principal de la
													// consultora
							clienteIdentificacion.setOidTipoDocumento(new Long(
									f.getTipoDocumentoIdentidad()));
							clienteIdentificacion.setNumeroDocumento(f
									.getNumeroDocumentoIdentidad());
							
							clienteIdentificacion.setCodigoSubtipoDocumento(f.getSubTipoDocumentoIdentidad());
							// clienteIdentificacion.setIndicadorPrincipal(new
							// Integer(1));

							i2 = 1;
							i3 = 2;

							if (Constants.NUMERO_DOS.equals(f
									.getIndDocumentoPrincipal())) {
								clienteIdentificacion
										.setIndicadorPrincipal(new Integer(0));
							} else {
								clienteIdentificacion
										.setIndicadorPrincipal(new Integer(1));
							}
						} else {
							i2 = 0;
							i3 = 1;
						}
					}
				}

				if (i == i2) { // Representa documento auxiliar
					if (f.isBorradoDocumentoIdentidad2())
						clienteIdentificacion.setEliminar(true);
					else {
						if (f.getTipoDocumentoIdentidad2().equals("")) { // si
																			// ha
																			// limpiado
																			// tipo
																			// documento,
																			// se
																			// asume
																			// que
																			// se
																			// esta
																			// eliminando
							clienteIdentificacion.setEliminar(true);
						} else {
							clienteIdentificacion.setOidTipoDocumento(new Long(
									f.getTipoDocumentoIdentidad2()));
							clienteIdentificacion.setNumeroDocumento(f
									.getNumeroDocumentoIdentidad2());
							clienteIdentificacion.setCodigoSubtipoDocumento(f.getSubTipoDocumentoIdentidad2());
							// clienteIdentificacion.setIndicadorPrincipal(new
							// Integer(0));

							if (Constants.NUMERO_DOS.equals(f
									.getIndDocumentoPrincipal())) {
								clienteIdentificacion
										.setIndicadorPrincipal(new Integer(1));
							} else {
								clienteIdentificacion
										.setIndicadorPrincipal(new Integer(0));
							}
						}
					}
				}

				if (i == i3) { // Representa documento auxiliar
					if (f.isBorradoDocumentoIdentidad3())
						clienteIdentificacion.setEliminar(true);
					else {
						clienteIdentificacion.setOidTipoDocumento(new Long(f
								.getTipoDocumentoIdentidad3()));
						clienteIdentificacion.setNumeroDocumento(f
								.getNumeroDocumentoIdentidad3());
						clienteIdentificacion.setCodigoSubtipoDocumento(f.getSubTipoDocumentoIdentidad3());
						clienteIdentificacion
								.setIndicadorPrincipal(new Integer(0));
					}
				}
			}

			if ((f.isBorradoDocumentoIdentidad1() && (f
					.getTipoDocumentoIdentidad().length() > 0))
					|| (!f.isTieneIdentificacionPrincipal() && (f
							.getTipoDocumentoIdentidad().length() > 0))) {
				ClienteIdentificacion clienteIdentificacion = new ClienteIdentificacion();
				clienteIdentificacion.setOidCliente(cliente.getOid());
				clienteIdentificacion.setOidTipoDocumento(new Long(f
						.getTipoDocumentoIdentidad()));
				clienteIdentificacion.setNumeroDocumento(f
						.getNumeroDocumentoIdentidad());
				clienteIdentificacion.setCodigoSubtipoDocumento(f.getSubTipoDocumentoIdentidad());

				if (Constants.NUMERO_UNO.equals(f.getIndDocumentoPrincipal())) {
					clienteIdentificacion.setIndicadorPrincipal(new Integer(1));
				} else {
					clienteIdentificacion.setIndicadorPrincipal(new Integer(0));
				}

				cliente.getListClienteIdentificacion().add(
						clienteIdentificacion);
			}
			if(StringUtils.isNotBlank(f.getTipoDocumentoIdentidad2())){
			if ((f.isBorradoDocumentoIdentidad2() && (f.getTipoDocumentoIdentidad2().length() > 0))
					|| (!f.isTieneDocumentoIdentidad2() && (f.getTipoDocumentoIdentidad2().length() > 0))) {
				ClienteIdentificacion clienteIdentificacion = new ClienteIdentificacion();
				clienteIdentificacion.setOidCliente(cliente.getOid());
				clienteIdentificacion.setOidTipoDocumento(new Long(f
						.getTipoDocumentoIdentidad2()));
				clienteIdentificacion.setNumeroDocumento(f
						.getNumeroDocumentoIdentidad2());
				clienteIdentificacion.setCodigoSubtipoDocumento(f.getSubTipoDocumentoIdentidad2());
				
				if (Constants.NUMERO_DOS.equals(f.getIndDocumentoPrincipal())) 
					clienteIdentificacion.setIndicadorPrincipal(new Integer(1));
				else 
					clienteIdentificacion.setIndicadorPrincipal(new Integer(0));				

				cliente.getListClienteIdentificacion().add(clienteIdentificacion);
			}}
			
			if(StringUtils.isNotBlank(f.getTipoDocumentoIdentidad3())){
			if ((f.isBorradoDocumentoIdentidad3() && (f.getTipoDocumentoIdentidad3().length() > 0))
					|| (!f.isTieneDocumentoIdentidad3() && (f.getTipoDocumentoIdentidad3().length() > 0))) {
				ClienteIdentificacion clienteIdentificacion = new ClienteIdentificacion();
				clienteIdentificacion.setOidCliente(cliente.getOid());
				clienteIdentificacion.setOidTipoDocumento(new Long(f
						.getTipoDocumentoIdentidad3()));
				clienteIdentificacion.setNumeroDocumento(f
						.getNumeroDocumentoIdentidad3());
				
				clienteIdentificacion.setCodigoSubtipoDocumento(f.getSubTipoDocumentoIdentidad3());
				
				clienteIdentificacion.setIndicadorPrincipal(new Integer(0));

				cliente.getListClienteIdentificacion().add(
						clienteIdentificacion);
			}}

			for (int i = 0; i < cliente.getListClienteIdentificacion().size()
					&& i < 3; i++) {
				ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) cliente
						.getListClienteIdentificacion().get(i);
				String identificadorPersonal = "P";

				clienteIdentificacion
						.setIdentificadorPersonal(identificadorPersonal);
			}
		}

		// VALIDAMOS SI LA CONSULTORA SE VA A QUEDAR SIN DOCUMENTO PRINCIPAL
		if (tieneTipoClienteConsultora) {
			boolean verificarDocumentoPrincipal = false;
			for (int i = 0; i < cliente.getListClienteIdentificacion().size(); i++) {
				ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) cliente
						.getListClienteIdentificacion().get(i);

				if (!clienteIdentificacion.isEliminar()) {
					if (clienteIdentificacion.getIndicadorPrincipal()
							.intValue() == 1)
						verificarDocumentoPrincipal = true;
				}
			}

			if (!verificarDocumentoPrincipal) {
				List listClienteIdentificacion = clienteService
						.getListIdentificacionCliente(cliente.getOid()
								.toString());
				cliente.setListClienteIdentificacion(listClienteIdentificacion);
				addInfo("Mensaje",
						"mantenimientoMAEModificacionClienteForm.msg.validarDocumentoprincipal");

			}
		}

		for (int i = 0; i < cliente.getListClienteIdentificacion().size(); i++) {
			ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) cliente
					.getListClienteIdentificacion().get(i);
			Map criteriaDocumento = new HashMap();
			criteriaDocumento.put("oidPais", f.getOidPais());

			if (!clienteIdentificacion.isEliminar()) {
				criteriaDocumento.put("oidTipoDocumento",
						clienteIdentificacion.getOidTipoDocumento());

				boolean siglaRUC = clienteService
						.esTipoDocumentoSiglaRUC(criteriaDocumento);

				if (siglaRUC)
					clienteIdentificacion
							.setIdentificadorPersonal(Constants.MAE_IDENTIFICACION_TIPO_EMPRESA);
				else
					clienteIdentificacion
							.setIdentificadorPersonal(Constants.MAE_IDENTIFICACION_TIPO_PERSONA);
			}

		}

		// DIRECCIONES DEL CLIENTE
		if (f.isMostrarDireccion()) {
			ClienteDireccion clienteDireccion = null;

			// Para la direccion principal del cliente
			if (f.isTieneDireccionPrincipal()) {
				clienteDireccion = (ClienteDireccion) cliente
						.getListClienteDireccion().get(0);

				if (f.isBorradoDireccionPrincipal()) {
					clienteDireccion.setEliminar(true);
					clienteDireccion = new ClienteDireccion();
					clienteDireccion.setOidTipoDireccion(clienteService
							.getOidTipoDireccion(MAE_TIPO_DIRECCION_DOMICILIO));
				}
			} else {
				clienteDireccion = new ClienteDireccion();
				clienteDireccion.setOidTipoDireccion(clienteService
						.getOidTipoDireccion(MAE_TIPO_DIRECCION_DOMICILIO));
			}

			if (f.isMostrarUnidadAdministrativa()) {
				clienteDireccion
						.setOidTerritorio(new Long(f.getOidTerritorio()));
				clienteDireccion.setIndicadorEstandarizacionGIS("S");
			}

			if (f.getTipoVia() != null && !("".equals(f.getTipoVia())))
				clienteDireccion.setOidTipoVia(new Long(f.getTipoVia()));
			clienteDireccion.setNombreVia(f.getNombreVia());
			clienteDireccion.setCorreVia(f.getCorreVia());
			clienteDireccion.setNumeroPrincipal(f.getNumeroPrincipal());
			clienteDireccion.setObservaciones(f.getObservacionDireccion());
			clienteDireccion.setBarrio(f.getBarrio());
			clienteDireccion.setIndicadorDireccionPrincipal(new Integer(1));
			clienteDireccion.setIndicadorEliminacion(new Integer(0));
			clienteDireccion.setCodigoPostal(f.getCodigoPostal());
			
			//INI ECU-SiCC-2015-0036
			clienteDireccion.setIndicadorCamposAdicionales(Constants.NO);
			if(f.isIndicadorCamposAdicionales()){
				clienteDireccion.setIndicadorCamposAdicionales(Constants.SI);
				clienteDireccion.setValNomManzana(f.getManzanaLetraDD());
				clienteDireccion.setValNomBarrio(f.getBarrioDD());
				clienteDireccion.setValEtapaConjunto(f.getEtapaConjuntoDD());
				clienteDireccion.setValCallePrincipal(f.getCallePrincipalDD());
				clienteDireccion.setValCalleSecundaria(f.getCalleSecundariaDD());
				clienteDireccion.setValCodigoTerritorialCorresponde(f.getCodigoTerritorialCorrespondeDD());
			}
			//FIN ECU-SiCC-2015-0036

			/* INI SA PER-SiCC-2012-0459 */
			if (f.getCodigoCiudad() != null
					&& !("".equals(f.getCodigoCiudad()))) {
				StringTokenizer stCodigoCiudad = new StringTokenizer(
						f.getCodigoCiudad(), "__");
				clienteDireccion.setCodigoUbigeo1(stCodigoCiudad.nextToken());
				clienteDireccion.setCodigoCiudad(stCodigoCiudad.nextToken());
			}
			clienteDireccion.setVillaPoblacion(f.getVillaPoblacion());
			/* FIN SA PER-SiCC-2012-0459 */

			if (f.getNivel6() != null && !("".equals(f.getNivel6())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel6());
			else if (f.getNivel5() != null && !("".equals(f.getNivel5())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel5());
			else if (f.getNivel4() != null && !("".equals(f.getNivel4())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel4());
			else if (f.getNivel3() != null && !("".equals(f.getNivel3())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel3());

			if (clienteDireccion.getOid() == null)
				cliente.getListClienteDireccion().add(clienteDireccion);

			// para la direccion Opcional del Cliente
			ClienteDireccion clienteDireccionOpcional = null;
			if (f.isTieneDireccionOpcional()) {

				if (f.isTieneDireccionPrincipal()) {
					clienteDireccionOpcional = (ClienteDireccion) cliente
							.getListClienteDireccion().get(1);
				} else {
					clienteDireccionOpcional = (ClienteDireccion) cliente
							.getListClienteDireccion().get(0);
				}

				if (f.isBorradoDireccionOpcional()) {
					clienteDireccionOpcional.setEliminar(true);
					clienteDireccionOpcional = new ClienteDireccion();
				}
			} else {
				clienteDireccionOpcional = new ClienteDireccion();
			}
			//INI ECU-SiCC-2015-0036
			clienteDireccionOpcional.setIndicadorCamposAdicionales(Constants.NO);
			if(f.isIndicadorCamposAdicionales()){
				clienteDireccionOpcional.setIndicadorCamposAdicionales(Constants.SI);
				if((f.getCallePrincipalDE().length()>0) ||
						(f.getObservacionDireccionCT().length() > 0)){
					clienteDireccionOpcional.setOidTipoDireccion(new Long(f
							.getTipoDireccionCT()));
					clienteDireccionOpcional.setOidTipoVia(new Long(f
							.getTipoViaCT()));
					clienteDireccionOpcional.setCorreVia(f.getCorreViaCT());
					clienteDireccionOpcional.setNumeroPrincipal(f
							.getNumeroPrincipalCT());
					clienteDireccionOpcional.setObservaciones(f
							.getObservacionDireccionCT());
					clienteDireccionOpcional.setBarrio(f.getBarrioCT());
					clienteDireccionOpcional
							.setIndicadorDireccionPrincipal(new Integer(0));
					clienteDireccionOpcional
							.setIndicadorEliminacion(new Integer(0));
					clienteDireccionOpcional.setIndicadorEstandarizacionGIS(null);
					clienteDireccionOpcional.setCodigoPostal(f.getCodigoPostalCT());
					
					//INI ECU-SiCC-2015-0036
					clienteDireccionOpcional.setValNomManzana(f.getManzanaLetraDE());
					clienteDireccionOpcional.setValNomBarrio(f.getBarrioDE());
					clienteDireccionOpcional.setValEtapaConjunto(f.getEtapaConjuntoDE());
					clienteDireccionOpcional.setValCallePrincipal(f.getCallePrincipalDE());
					clienteDireccionOpcional.setValCalleSecundaria(f.getCalleSecundariaDE());
					//FIN ECU-SiCC-2015-0036

					/* INI SA PER-SiCC-2012-0459 */
					if (f.getCodigoCiudadCT() != null
							&& !("".equals(f.getCodigoCiudadCT()))) {
						StringTokenizer stCodigoCiudad = new StringTokenizer(
								f.getCodigoCiudadCT(), "__");
						clienteDireccionOpcional.setCodigoUbigeo1(stCodigoCiudad
								.nextToken());
						clienteDireccionOpcional.setCodigoCiudad(stCodigoCiudad
								.nextToken());
					}
					clienteDireccionOpcional.setVillaPoblacion(f
							.getVillaPoblacionCT());
					/* FIN SA PER-SiCC-2012-0459 */

					if (f.isMostrarUbigeoEntrega()) {
						if (f.getNivel6CT() != null
								&& !("".equals(f.getNivel6CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel6CT());
						else if (f.getNivel5CT() != null
								&& !("".equals(f.getNivel5CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel5CT());
						else if (f.getNivel4CT() != null
								&& !("".equals(f.getNivel4CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel4CT());
						else if (f.getNivel3CT() != null
								&& !("".equals(f.getNivel3CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel3CT());
					} else {
						// en caso se haya creado una nueva direccion principal, o
						// la direccion de entrega
						// recien se crea para el cliente, se actualiza su unidad
						// geografica con la de la principal
						if ((clienteDireccion.getOid() == null)
								|| (clienteDireccionOpcional.getOid() == null))
							clienteDireccionOpcional
									.setCodigoUnidadGeografica(clienteDireccion
											.getCodigoUnidadGeografica());
					}

					if (clienteDireccionOpcional.getOid() == null)
						cliente.getListClienteDireccion().add(
								clienteDireccionOpcional);
				}
			}
			
			//FIN ECU-SiCC-2015-0036
			
			if(!f.isIndicadorCamposAdicionales()){
				if ((f.getNombreViaCT().length() > 0)
						|| (f.getObservacionDireccionCT().length() > 0)) {
					clienteDireccionOpcional.setOidTipoDireccion(new Long(f
							.getTipoDireccionCT()));
					clienteDireccionOpcional.setOidTipoVia(new Long(f
							.getTipoViaCT()));
					clienteDireccionOpcional.setNombreVia(f.getNombreViaCT());
					clienteDireccionOpcional.setCorreVia(f.getCorreViaCT());
					clienteDireccionOpcional.setNumeroPrincipal(f
							.getNumeroPrincipalCT());
					clienteDireccionOpcional.setObservaciones(f
							.getObservacionDireccionCT());
					clienteDireccionOpcional.setBarrio(f.getBarrioCT());
					clienteDireccionOpcional
							.setIndicadorDireccionPrincipal(new Integer(0));
					clienteDireccionOpcional
							.setIndicadorEliminacion(new Integer(0));
					clienteDireccionOpcional.setIndicadorEstandarizacionGIS(null);
					clienteDireccionOpcional.setCodigoPostal(f.getCodigoPostalCT());
					

					/* INI SA PER-SiCC-2012-0459 */
					if (f.getCodigoCiudadCT() != null
							&& !("".equals(f.getCodigoCiudadCT()))) {
						StringTokenizer stCodigoCiudad = new StringTokenizer(
								f.getCodigoCiudadCT(), "__");
						clienteDireccionOpcional.setCodigoUbigeo1(stCodigoCiudad
								.nextToken());
						clienteDireccionOpcional.setCodigoCiudad(stCodigoCiudad
								.nextToken());
					}
					clienteDireccionOpcional.setVillaPoblacion(f
							.getVillaPoblacionCT());
					/* FIN SA PER-SiCC-2012-0459 */

					if (f.isMostrarUbigeoEntrega()) {
						if (f.getNivel6CT() != null
								&& !("".equals(f.getNivel6CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel6CT());
						else if (f.getNivel5CT() != null
								&& !("".equals(f.getNivel5CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel5CT());
						else if (f.getNivel4CT() != null
								&& !("".equals(f.getNivel4CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel4CT());
						else if (f.getNivel3CT() != null
								&& !("".equals(f.getNivel3CT())))
							clienteDireccionOpcional.setCodigoUnidadGeografica(f
									.getNivel3CT());
					} else {
						// en caso se haya creado una nueva direccion principal, o
						// la direccion de entrega
						// recien se crea para el cliente, se actualiza su unidad
						// geografica con la de la principal
						if ((clienteDireccion.getOid() == null)
								|| (clienteDireccionOpcional.getOid() == null))
							clienteDireccionOpcional
									.setCodigoUnidadGeografica(clienteDireccion
											.getCodigoUnidadGeografica());
					}

					if (clienteDireccionOpcional.getOid() == null)
						cliente.getListClienteDireccion().add(
								clienteDireccionOpcional);
				}
			}
			

			/* INI SA PER-SiCC-2012-0365 */
			// para la direccion Vacaciones del Cliente
			ClienteDireccion clienteDireccionVacaciones = null;
			if (f.isTieneDireccionVacaciones()) {
				if (cliente.getListClienteDireccion().size() > 1) {
					ClienteDireccion aux = (ClienteDireccion) cliente
							.getListClienteDireccion().get(1);
					if ((aux.getCodigoTipoDireccion() != null)
							&& aux.getCodigoTipoDireccion().equals(
									Constants.MAE_TIPO_DIRECCION_VACACIONES)) {
						clienteDireccionVacaciones = aux;
					} else {
						if (cliente.getListClienteDireccion().size() > 2) {
							aux = (ClienteDireccion) cliente
									.getListClienteDireccion().get(2);
							if ((aux.getCodigoTipoDireccion() != null)
									&& aux.getCodigoTipoDireccion()
											.equals(Constants.MAE_TIPO_DIRECCION_VACACIONES)) {
								clienteDireccionVacaciones = aux;
							}
						}
					}
				}

				if (f.isBorradoDireccionVacaciones()) {
					clienteDireccionVacaciones.setEliminar(true);
					clienteDireccionVacaciones = new ClienteDireccion();
				}
			} else {
				clienteDireccionVacaciones = new ClienteDireccion();
			}

			if ((f.getNombreViaVacaciones().length() > 0)
					|| (f.getObservacionDireccionVacaciones().length() > 0)) {
				if (clienteDireccionVacaciones.getOidTipoDireccion() == null)
					clienteDireccionVacaciones
							.setOidTipoDireccion(clienteService
									.getOidTipoDireccion(Constants.MAE_TIPO_DIRECCION_VACACIONES));

				clienteDireccionVacaciones.setCodigoPeriodoInicio(f
						.getCodigoPeriodoInicio());
				clienteDireccionVacaciones.setCodigoPeriodoFin(f
						.getCodigoPeriodoFin());
				clienteDireccionVacaciones.setOidTipoVia(new Long(f
						.getTipoViaVacaciones()));
				clienteDireccionVacaciones.setNombreVia(f
						.getNombreViaVacaciones());
				clienteDireccionVacaciones.setCorreVia(f
						.getCorreViaVacaciones());
				clienteDireccionVacaciones.setNumeroPrincipal(f
						.getNumeroPrincipalVacaciones());
				clienteDireccionVacaciones.setObservaciones(f
						.getObservacionDireccionVacaciones());
				clienteDireccionVacaciones.setBarrio(f.getBarrioVacaciones());
				clienteDireccionVacaciones
						.setIndicadorDireccionPrincipal(new Integer(0));
				clienteDireccionVacaciones.setIndicadorEliminacion(new Integer(
						0));
				clienteDireccionVacaciones.setIndicadorEstandarizacionGIS(null);

				/* INI SA PER-SiCC-2012-0459 */
				if (f.getCodigoCiudadVacaciones() != null
						&& !("".equals(f.getCodigoCiudadVacaciones()))) {
					StringTokenizer stCodigoCiudad = new StringTokenizer(
							f.getCodigoCiudadVacaciones(), "__");
					clienteDireccionVacaciones.setCodigoUbigeo1(stCodigoCiudad
							.nextToken());
					clienteDireccionVacaciones.setCodigoCiudad(stCodigoCiudad
							.nextToken());
				}
				clienteDireccionVacaciones.setVillaPoblacion(f
						.getVillaPoblacionVacaciones());
				/* FIN SA PER-SiCC-2012-0459 */

				if (f.isMostrarUbigeoEntrega()) {
					if (f.getNivel6Vacaciones() != null
							&& !("".equals(f.getNivel6Vacaciones())))
						clienteDireccionVacaciones.setCodigoUnidadGeografica(f
								.getNivel6Vacaciones());
					else if (f.getNivel5Vacaciones() != null
							&& !("".equals(f.getNivel5Vacaciones())))
						clienteDireccionVacaciones.setCodigoUnidadGeografica(f
								.getNivel5Vacaciones());
					else if (f.getNivel4Vacaciones() != null
							&& !("".equals(f.getNivel4Vacaciones())))
						clienteDireccionVacaciones.setCodigoUnidadGeografica(f
								.getNivel4Vacaciones());
					else if (f.getNivel3Vacaciones() != null
							&& !("".equals(f.getNivel3Vacaciones())))
						clienteDireccionVacaciones.setCodigoUnidadGeografica(f
								.getNivel3Vacaciones());
				} else {
					// en caso se haya creado una nueva direccion principal, o
					// la direccion de entrega
					// recien se crea para el cliente, se actualiza su unidad
					// geografica con la de la principal
					if ((clienteDireccion.getOid() == null)
							|| (clienteDireccionVacaciones.getOid() == null))
						clienteDireccionVacaciones
								.setCodigoUnidadGeografica(clienteDireccion
										.getCodigoUnidadGeografica());
				}

				if (clienteDireccionVacaciones.getOid() == null)
					cliente.getListClienteDireccion().add(
							clienteDireccionVacaciones);
			}
			/* FIN SA PER-SiCC-2012-0365 */
		}

		// UNIDAD ADMINISTRATIVA DEL CLIENTE
		if (f.isMostrarUnidadAdministrativa() && f.isCambioZonaTerritorio()) {
			ClienteUnidadAdministrativa clienteUnidadAdministrativaOld = cliente
					.getClienteUnidadAdministrativa();
			ClienteUnidadAdministrativa clienteUnidadAdministrativaNew = new ClienteUnidadAdministrativa();

			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("oidPeriodo", f.getOidPeriodo());
			Base basePeriodoFin = clienteService
					.getPeriodoAnterior(criteriaPeriodo);
			String oidPeriodoFin = basePeriodoFin.getCodigo();
			criteriaPeriodo.put("oidPeriodo", oidPeriodoFin);

			boolean esPeriodoFinCerrado = clienteService
					.esPeriodoCerrado(criteriaPeriodo);
			Integer indActivoUANuevo;
			Integer indActivoUAAnterior;

			if (!esPeriodoFinCerrado && f.isEsMayorPeriodoVigente()) {
				indActivoUANuevo = new Integer(0);
				indActivoUAAnterior = new Integer(1);
				f.setMostrarMensajePeriodoFinCerrado(true);
			} else {
				indActivoUANuevo = new Integer(1);
				indActivoUAAnterior = new Integer(0);
			}

			// Actualizamos el periodo Fin de la actual unidad administrativa
			if (clienteUnidadAdministrativaOld != null) {
				clienteUnidadAdministrativaOld.setPeriodoFin(new Long(
						oidPeriodoFin));
				clienteUnidadAdministrativaOld
						.setIndicadorActivo(indActivoUAAnterior);

				/* INI SA PER-SiCC-2012-0682 */
				// si es registrada y no paso pedido, que limpie la unidad
				// administrativa
				if (f.getCodigoEstatus()
						.equals(Constants.MAE_ESTADO_REGISTRADA)
						&& !f.isConsultoraPasoPedido())
					clienteUnidadAdministrativaOld
							.setEsPeriodoInicioMayorIgualPeriodoVigente(true);
				else
					// verificamos si el periodo de inicio de la unidad
					// administrativa ultima, es mayor o igual al periodo
					// vigente
					clienteUnidadAdministrativaOld
							.setEsPeriodoInicioMayorIgualPeriodoVigente(validarPeriodoInicioMayorAPeriodoVigente(
									clienteUnidadAdministrativaOld
											.getPeriodoInicio().toString(), f
											.getOidPeriodo().toString()));
				/* FIN SA PER-SiCC-2012-0682 */
			}

			// creamos una nueva unidad administrativa
			clienteUnidadAdministrativaNew.setOidCliente(cliente.getOid());
			clienteUnidadAdministrativaNew
					.setOidTerritorioAdministrativo(new Long(f
							.getOidTerritorioAdministrativo()));
			clienteUnidadAdministrativaNew.setIndicadorActivo(new Integer(1));
			clienteUnidadAdministrativaNew.setPeriodoInicio(new Long(f
					.getOidPeriodo()));
			// clienteUnidadAdministrativaNew.setCodigoPeriodoInicio(f.getCodigoPeriodoIniUA());
			clienteUnidadAdministrativaNew.setRequiereGenerarEstatus(f
					.isRequiereGenerarEstatus());
			clienteUnidadAdministrativaNew.setPeriodoFin(null);
			clienteUnidadAdministrativaNew.setIndicadorActivo(indActivoUANuevo);
			clienteUnidadAdministrativaNew.setCodigoZona(f.getCodigoZona());

			cliente.setClienteUnidadAdministrativaNew(clienteUnidadAdministrativaNew);
		}

		// PRIMER CONTACTO DEL CLIENTE
		ClientePrimerContacto clientePrimerContacto = null;
		if (cliente.getClientePrimerContacto() != null) {
			clientePrimerContacto = cliente.getClientePrimerContacto();
			if (f.isEsClientePotencialAval())
				clientePrimerContacto
						.setOidPeriodo(new Long(f.getOidPeriodo()));
			else
				clientePrimerContacto.setOidPeriodo(new Long(f
						.getOidPeriodoPrimerPedido()));

			String fechaContacto = sdf.format(clientePrimerContacto
					.getFechaContacto());

			String fechaContactoAux = "";
			if (f.isEsClientePotencialAval())
				fechaContactoAux = obtenerFechaPeriodo(clienteService,
						f.getOidPeriodo(), fechaContacto);
			else
				fechaContactoAux = obtenerFechaPeriodo(clienteService,
						f.getOidPeriodoPrimerPedido(), fechaContacto);

			if (!fechaContacto.equals(fechaContactoAux)) {
				clientePrimerContacto.setFechaContacto(sdf
						.parse(fechaContactoAux));

				String fechaSiguienteContacto = sdf
						.format(obtenerFechaAñoSiguiente(clientePrimerContacto
								.getFechaContacto()));
				clientePrimerContacto.setFechaSiguienteContacto(sdf
						.parse(fechaSiguienteContacto));
			}

		} else {
			clientePrimerContacto = new ClientePrimerContacto();
			clientePrimerContacto.setOidCliente(cliente.getOid());
			if (f.isEsClientePotencialAval())
				clientePrimerContacto
						.setOidPeriodo(new Long(f.getOidPeriodo()));
			else
				clientePrimerContacto.setOidPeriodo(new Long(f
						.getOidPeriodoPrimerPedido()));
			clientePrimerContacto.setOidMarca(oidMarca);
			clientePrimerContacto.setOidCanal(oidCanal);
			clientePrimerContacto.setCodigoTipoContacto("I");

			String fechaContacto = "";
			if (f.isEsClientePotencialAval())
				fechaContacto = obtenerFechaPeriodo(clienteService,
						f.getOidPeriodo(), fechaActual);
			else
				fechaContacto = obtenerFechaPeriodo(clienteService,
						f.getOidPeriodoPrimerPedido(), fechaActual);

			clientePrimerContacto.setFechaContacto(sdf.parse(fechaContacto));

			String fechaSiguienteContacto = sdf
					.format(obtenerFechaAñoSiguiente(clientePrimerContacto
							.getFechaContacto()));
			clientePrimerContacto.setFechaSiguienteContacto(sdf
					.parse(fechaSiguienteContacto));
			cliente.setClientePrimerContacto(clientePrimerContacto);
		}

		// MARCA DEL CLIENTE
		if (cliente.getClienteMarca() == null) {
			ClienteMarca clienteMarca = new ClienteMarca();

			clienteMarca.setIndicadorPrincipal(new Integer(1));
			clienteMarca.setOidMarca(oidMarca);
			cliente.setClienteMarca(clienteMarca);
		}

		// CHI-SiCC-2014-0116 INI
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoSistema", Constants.MAE_CODIGO_SISTEMA);
		criteria.put("valorParametro", Constants.NUMERO_UNO);
		criteria.put("indicadorActivo", Constants.NUMERO_UNO);
		criteria.put("nombreParametro",
				Constants.MAE_PARAMETRO_VALIDA_INGRESO_TELEFONO);

		if (!clienteService.validaIngresoTelefono(criteria)) {
			if ((f.getTelefonoCasa() == null || ("".equals(f.getTelefonoCasa())))
					&& (f.getTelefonoCelular() == null || ("".equals(f
							.getTelefonoCelular())))
					&& (f.getTelefonoTrabajo() == null || ("".equals(f
							.getTelefonoTrabajo())))
					&& (f.getTelefonoReferencia() == null || ("".equals(f
							.getTelefonoReferencia())))
					&& (f.getTelefonoAdicional() == null || ("".equals(f
							.getTelefonoAdicional())))) {
				addInfo("Mensaje",
						getReportResourceMessage("mantenimientoMAEClienteForm.msg.validaIngresoTelefono"));

			}
		}

		// CHI-SiCC-2014-0116 FIN

		// GRABAMOS LOS TIPOS DE COMUNICACION DEL CLIENTE
		this.indPrincipalComunicacion = new Integer(1);

		verificarComunicacion(clienteService, cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA,
				f.getTelefonoCasa());
		verificarComunicacion(clienteService, cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_MOVIL,
				f.getTelefonoCelular());
		verificarComunicacion(clienteService, cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO,
				f.getTelefonoTrabajo());
		verificarComunicacion(clienteService, cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA,
				f.getTelefonoReferencia());
		verificarComunicacion(clienteService, cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_ADICIONAL,
				f.getTelefonoAdicional());
		verificarComunicacion(clienteService, cliente,
				Constants.MAE_TIPO_COMUNICACION_MAIL, f.getMail());
		this.indPrincipalComunicacion = new Integer(0);
		verificarComunicacion(
				clienteService,
				cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_ENTREGA,
				f.getTelefonoCasaDireccionEntrega());
		verificarComunicacion(
				clienteService,
				cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_ENTREGA,
				f.getTelefonoCelularDireccionEntrega());

		/* INI SA PER-SiCC-2012-0365 */
		verificarComunicacion(
				clienteService,
				cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_VACACIONES,
				f.getTelefonoCasaDireccionVacaciones());
		verificarComunicacion(
				clienteService,
				cliente,
				Constants.MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_VACACIONES,
				f.getTelefonoCelularDireccionVacaciones());
		/* FIN SA PER-SiCC-2012-0365 */
		
		//INI MEX-SiCC-2015-0005
		//Recuperamos el vinculo
		List listClienteVinculoAux = new ArrayList();
		Iterator itClienteVinculo = cliente.getListClienteVinculo().iterator();
		while(itClienteVinculo.hasNext()) {
			ClienteVinculo clienteVinculo = (ClienteVinculo)itClienteVinculo.next();
			
			if((f.isEsDuplaCyzone() && clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_DUPLACYZONE)) ||
               (f.isMostrarUnidadAdministrativa() && clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_RECOMENDANTE)) ) { 
				//ES TIPO CLIENTE = CONSULTORA, HIJA DUPLA
			
				clienteVinculo.setCodigoClienteVinculante(f.getCodigoConsultoraVinculo());
				listClienteVinculoAux.add(clienteVinculo);
				//break;
			}
		}
		//FIN MEX-SiCC-2015-0005

		// GRABAMOS LOS VINCULOS DEL CLIENTE
		if (f.isEsDuplaCyzone()) {
			if (f.isModificarConsultoraVinculo()) {
				ClienteVinculo clienteVinculo = new ClienteVinculo();

				clienteVinculo
						.setCodigoTipoVinculo(Constants.MAE_TIPO_VINCULO_DUPLACYZONE);
				Long oidTipoVinculo = clienteService.getOidTipoVinculo(
						f.getOidPais(), Constants.MAE_TIPO_VINCULO_DUPLACYZONE);

				clienteVinculo.setOidCliente(cliente.getOid());
				clienteVinculo.setOidClienteVinculante(new Long(f
						.getOidConsultoraVinculo()));
				clienteVinculo.setOidTipoVinculo(oidTipoVinculo);
				clienteVinculo.setFechaDesde(sdf.parse(f.getFechaDesde()));
				clienteVinculo.setFechaHasta(null);
				clienteVinculo.setIndicadorPrincipal(new Integer(0));

				cliente.getListClienteVinculo().add(clienteVinculo);
			}

		} else {
			if ((!f.getCodigoConsultoraRecomendante().equals(
					f.getCodigoConsultoraVinculo()))
					|| ((f.getCodigoEstatus()
							.equals(Constants.MAE_ESTADO_RETIRADA))
							&& (f.isModificarConsultoraVinculo())
							&& (!f.getCodigoConsultoraVinculo().equals("")) && (f
								.getCodigoConsultoraRecomendante().equals(f
							.getCodigoConsultoraVinculo())))) {

				ClienteVinculo clienteVinculo = null;

				if (f.getCodigoConsultoraRecomendante().equals("")) {
					clienteVinculo = new ClienteVinculo();
					cliente.getListClienteVinculo().add(clienteVinculo);

					clienteVinculo
							.setCodigoTipoVinculo(Constants.MAE_TIPO_VINCULO_RECOMENDANTE);
					Long oidTipoVinculo = clienteService.getOidTipoVinculo(
							f.getOidPais(),
							Constants.MAE_TIPO_VINCULO_RECOMENDANTE);
					clienteVinculo.setOidTipoVinculo(oidTipoVinculo);
				} else {

					Iterator it = cliente.getListClienteVinculo().iterator();
					while (it.hasNext()) {
						ClienteVinculo clienteVinculoAux = (ClienteVinculo) it
								.next();

						if (clienteVinculoAux.getCodigoTipoVinculo().equals(
								Constants.MAE_TIPO_VINCULO_RECOMENDANTE)) {
							clienteVinculo = clienteVinculoAux;
							break;
						}

					}
				}

				clienteVinculo.setOidCliente(cliente.getOid());
				clienteVinculo.setOidClienteVinculante(new Long(f
						.getOidConsultoraVinculo()));

				String oidPeriodo = f.getOidPeriodo();
				if (f.getCodigoEstatus().equals(Constants.MAE_ESTADO_NUEVA)) {
					oidPeriodo = clienteService.getPeriodoRecomendacion(
							f.getOidPais(), f.getOidCliente());
				}

				String fechaDesde = obtenerFechaPeriodo(clienteService, oidPeriodo, f.getFechaDesde());
				 
				//INI MEX-SiCC-2015-0005
				if(f.getCodigoConsultoraVinculo()!=null && f.getCodigoConsultoraVinculo().trim().length()>0){
					clienteVinculo.setCodigoClienteVinculante(f.getCodigoConsultoraVinculo());
				}
				//FIN MEX-SiCC-2015-0005
				
				if(fechaDesde.equals(f.getFechaDesde())) {
					clienteVinculo.setFechaDesde(sdf.parse(f.getFechaDesde()));
					clienteVinculo.setFechaHasta(sdf.parse(f.getFechaHasta()));
				} else {
					clienteVinculo.setFechaDesde(sdf.parse(fechaDesde));

					String fechaHasta = sdf
							.format(obtenerFechaAñoSiguiente(clienteVinculo
									.getFechaDesde()));
					clienteVinculo.setFechaHasta(sdf.parse(fechaHasta));
				}

				/*
				 * if(clienteVinculo.getOidClienteVinculanteAnterior()!=null &&
				 * !f.isBorrarConcursosPremios()) {
				 * clienteVinculo.setOidClienteVinculanteAnterior(null); }
				 */

				clienteVinculo.setIndicadorPrincipal(new Integer(1));

				// grabamos los concursos y premios para el cliente
				Map criteriaConcurso = new HashMap();
				criteriaConcurso.put("oidPeriodo", oidPeriodo);
				criteriaConcurso.put("oidPais", f.getOidPais());
				criteriaConcurso.put("oidDirigConsul",
						Constants.MAE_OID_DIRIG_CONSUL);
				criteriaConcurso.put("oidBaseCalcu",
						Constants.MAE_OID_BASE_CALCU_RECOMEN);

				//INI MEX-SiCC-2015-0005
				if(f.getCodigoConsultoraVinculo()!=null && f.getCodigoConsultoraVinculo().trim().length()>0){
				//FIN MEX-SiCC-2015-0005	
					String codigoZona = clienteService.getZonaCliente(f.getCodigoPais(), f.getCodigoConsultoraVinculo());
					criteriaConcurso.put("codigoZona", codigoZona);
					criteriaConcurso.put("codigoCliente", f.getCodigoConsultoraVinculo());
					this.codigoZona = codigoZona;
					
					List listConcursos = clienteService.getConcursos(criteriaConcurso);
					Iterator itConcursos = listConcursos.iterator();
					List listClienteConcursoPremio = new ArrayList();
					
					while(itConcursos.hasNext()) {
						Concurso concurso = (Concurso)itConcursos.next();
						
						//si no pertenece a la zona geografica del concurso, no se le asigna dicho concurso a la consultora
						if(concurso.getTotalGeografico().intValue()>0 && concurso.getCodigoZona()==null)
							continue;
						
						criteriaConcurso.put("oidConcurso", concurso.getOidConcurso());
						List listPremios = clienteService.getPremios(criteriaConcurso);
						
						//si el concurso es de mas de un Nivel, no se muestra
						//Si el Concurso de Rec. No tiene Niveles Electivos, no se muestra, //Anterior: if(concurso.getNumeroNivel().intValue() > 1)
						if(concurso.getNumeroNivelSelectivo().intValue() == 0) {
							ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
							concursoPremio.setOidModulo(new Long(Constants.MAE_CLIENTE_MODULO_MAE));
							
							concursoPremio.setCodigoClienteRecomendante(f.getCodigoConsultoraVinculo());
							concursoPremio.setOidConcurso(concurso.getOidConcurso());
							
							concursoPremio.setCodigoClienteRecomendado(cliente.getCodigo());
							concursoPremio.setOidPeriodo(new Long(oidPeriodo));
							
							listClienteConcursoPremio.add(concursoPremio);
						} else {
				
							//si el premio es de un nivel se graba	
							if(listPremios.size() > 1)
								f.setMostrarPantallaPremios(true);
							
							ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
							concursoPremio.setOidModulo(new Long(Constants.MAE_CLIENTE_MODULO_MAE));
							
							concursoPremio.setCodigoClienteRecomendante(f.getCodigoConsultoraVinculo());
							concursoPremio.setOidConcurso(concurso.getOidConcurso());
							
							concursoPremio.setCodigoClienteRecomendado(cliente.getCodigo());
							concursoPremio.setOidPeriodo(new Long(oidPeriodo));
							
							// Seteamos a null si el indicador esta en uno
							if(StringUtils.equals(concurso.getTipoPremio(), Constants.ESTADO_ACTIVO))
							{
								concursoPremio.setOidNivelPremio(null);
								concursoPremio.setNumeroPremio(null); 
							}
							else
							{
								Premio premio = (Premio)listPremios.get(0);
								concursoPremio.setOidNivelPremio(premio.getOidPremio());
								concursoPremio.setNumeroPremio(premio.getNumeroPremio());
							}
							
							listClienteConcursoPremio.add(concursoPremio);
						}	
					}
					
					//OCULTAR LA PANTALLA DE PREMIOS
					if(listConcursos != null && listConcursos.size() > 0)
					{
						//verificamos si el indicador TPRM_OID_TIPO_PION tipopremio esta en 1
						//TODOS los registros de la lista van a tenr el mismo valor
						Concurso concurso = (Concurso)listConcursos.get(0);
						
						//No se muestra la pantalla de premios
						if(StringUtils.equals(concurso.getTipoPremio(), Constants.ESTADO_ACTIVO))
						{
							f.setMostrarPantallaPremios(false);
						}
					}
					//
					
					cliente.setListClienteConcursoPremio(listClienteConcursoPremio);
				}

			} 
			
		}

		// VINCULO LIDER
		if (StringUtils.isNotEmpty(f.getCodigoLiderVinculo())) {
			ClienteVinculo clienteVinculo = null;

			Iterator it = cliente.getListClienteVinculo().iterator();
			while (it.hasNext()) {
				ClienteVinculo clienteVinculoAux = (ClienteVinculo) it.next();

				if (clienteVinculoAux.getCodigoTipoVinculo().equals(
						Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA)) {
					clienteVinculo = clienteVinculoAux;
					break;
				}

			}

			if (clienteVinculo == null) {
				Long oidTipoVinculo = clienteService.getOidTipoVinculo(
						f.getOidPais(),
						Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA);
				clienteVinculo = new ClienteVinculo();

				clienteVinculo
						.setCodigoTipoVinculo(Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA);
				clienteVinculo.setOidTipoVinculo(oidTipoVinculo);
				clienteVinculo.setOidCliente(cliente.getOid());
				cliente.getListClienteVinculo().add(clienteVinculo);
			}

			clienteVinculo.setOidClienteVinculante(new Long(f.getOidLiderVinculo()));
			clienteVinculo.setCodigoClienteVinculante(f.getCodigoLiderVinculo());
			if (StringUtils.isNotEmpty(f.getFechaDesdeLider()))
				clienteVinculo.setFechaDesde(sdf.parse(f.getFechaDesdeLider()));
			else
				clienteVinculo.setFechaDesde(null);

			if (StringUtils.isNotEmpty(f.getFechaHastaLider()))
				clienteVinculo.setFechaHasta(sdf.parse(f.getFechaHastaLider()));
			else
				clienteVinculo.setFechaHasta(null);

			clienteVinculo.setIndicadorPrincipal(new Integer(0));

		}

		// seteando la referencia
		setClienteReferencia(cliente, f);

		// recuperamos la lista de Consultoras Avaladas
		if (f.isAprobarAvaladas()) {
			List listConsultorasDeudoras = maeDeudorasList;
			cliente.setListClienteAval(listConsultorasDeudoras);
		}

		ClienteHistoricoDatos clienteHistoricoDatos = cliente
				.getClienteHistoricoDatos();
		clienteHistoricoDatos.setPrimerApellido(f.getApellidoPaterno());
		clienteHistoricoDatos.setSegundoApellido(f.getApellidoMaterno());
		clienteHistoricoDatos.setPrimerNombre(f.getNombre1());
		clienteHistoricoDatos.setSegundoNombre(f.getNombre2());
		clienteHistoricoDatos.setFechaNacimiento(f.getFechaNacimiento());
		clienteHistoricoDatos.setNumeroDocumento(f
				.getNumeroDocumentoIdentidad());

		clienteHistoricoDatos.setTelefonoCelular(f.getTelefonoCelular());
		clienteHistoricoDatos.setTelefonoFijo(f.getTelefonoCasa());
		clienteHistoricoDatos.setEmail(f.getMail());

		if (StringUtils.isNotEmpty(f.getNivel6()))
			clienteHistoricoDatos.setUbigeo(f.getNivel6());
		if (StringUtils.isNotEmpty(f.getNivel5()))
			clienteHistoricoDatos.setUbigeo(f.getNivel5());
		if (StringUtils.isNotEmpty(f.getNivel4()))
			clienteHistoricoDatos.setUbigeo(f.getNivel4());
		if (StringUtils.isNotEmpty(f.getNivel3()))
			clienteHistoricoDatos.setUbigeo(f.getNivel3());

		clienteHistoricoDatos.setTipoVia(f.getTipoVia());
		clienteHistoricoDatos.setNumeroPrincipal(f.getNumeroPrincipal());
		clienteHistoricoDatos.setBarrio(f.getBarrio());
		clienteHistoricoDatos.setDireccion(f.getNombreVia());
		clienteHistoricoDatos.setReferencia(f.getObservacionDireccion());

		clienteHistoricoDatos.setZona(f.getCodigoZona());
		clienteHistoricoDatos.setTerritorio(f.getCodigoTerritorio());

		// Obtenemos codigoRegion y Codigo de Seccion
		if (f.isCambioZonaTerritorio()) {
			Map mapRegionSeccion = clienteService.getCodigoRegionySeccion(
					f.getCodigoZona(), f.getCodigoTerritorio());

			if (mapRegionSeccion != null) {
				clienteHistoricoDatos.setRegion(MapUtils.getString(
						mapRegionSeccion, "codigoRegion"));
				clienteHistoricoDatos.setSeccion(MapUtils.getString(
						mapRegionSeccion, "codigoSeccion"));
			} else {
				clienteHistoricoDatos.setRegion(f.getCodigoRegion());
				clienteHistoricoDatos.setSeccion(f.getCodigoSeccion());
			}
		} else {
			clienteHistoricoDatos.setRegion(f.getCodigoRegion());
			clienteHistoricoDatos.setSeccion(f.getCodigoSeccion());
		}

		clienteHistoricoDatos.setCodigoUsuario(usuario.getLogin());
		cliente.setIndicadorCamposAdicionales(f.isIndicadorCamposAdicionales());
		
		if (!f.isEsDuplaCyzone())
			cliente.setClienteHistoricoDatos(clienteHistoricoDatos);
		else
			cliente.setClienteHistoricoDatos(null);

		return cliente;
	}

	private void verificarComunicacion(
			MantenimientoMAEClienteService clienteService, Cliente cliente,
			String codigoTipoComunicacion, String valorComunicacion) {
		ClienteComunicacion clienteComunicacion = null; // new
														// ClienteComunicacion();
		boolean encontrado = false;

		Iterator it = cliente.getListClienteComunicacion().iterator();
		while (it.hasNext()) {
			clienteComunicacion = (ClienteComunicacion) it.next();

			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					codigoTipoComunicacion)) {
				encontrado = true;
				break;
			}
		}
		if (valorComunicacion != null && !("".equals(valorComunicacion))) {
			if (!encontrado) {
				clienteComunicacion = new ClienteComunicacion();
				clienteComunicacion
						.setCodigoTipoComunicacion(codigoTipoComunicacion);
				clienteComunicacion.setOidCliente(cliente.getOid());
			}

			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(codigoTipoComunicacion);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(valorComunicacion);
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);

			if (clienteComunicacion.getOid() == null)
				cliente.getListClienteComunicacion().add(clienteComunicacion);
		} else {
			if (encontrado)
				clienteComunicacion.setEliminar(true);
		}
	}

	private Map getCriteriaSearch(ConsultaMAEClienteSearchForm searchForm) {
		Map criteria = new HashMap();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		String subtipo = searchForm.getSubTipoCliente();
		String[] split = StringUtils.split(subtipo, "-");
		String oidTipoCliente = (StringUtils.isNotEmpty(subtipo) ? split[2]
				: "");
		String oidSubTipoCliente = (StringUtils.isNotEmpty(subtipo) ? split[3]
				: "");

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", clienteService.getOidPais(criteria));
		criteria.put("codigoCliente", searchForm.getCodigoCliente());
		criteria.put("nombre1", searchForm.getNombre1());
		criteria.put("nombre2", searchForm.getNombre2());
		criteria.put("apellido1", searchForm.getApellido1());
		criteria.put("apellido2", searchForm.getApellido2());
		criteria.put("codigoZona", searchForm.getCodigoZona());
		criteria.put("codigoTerritorio", searchForm.getCodigoTerritorio());
		criteria.put("documentoIdentidad", searchForm.getNumeroDocIdentidad());
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("activo", searchForm.getIndicadorActivo());

		return criteria;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private void setClienteReferencia(Cliente cliente,
			MantenimientoMAEModificacionClienteForm f)
			throws IllegalAccessException, InvocationTargetException {
		ClienteReferencias clienteReferencias = new ClienteReferencias();
		BeanUtils.copyProperties(clienteReferencias, f);
		log.debug("ClienteReferencia upadte " + clienteReferencias);
		cliente.setClienteReferencias(clienteReferencias);
	}

	private boolean validarPeriodoInicioMayorAPeriodoVigente(
			String oidPeriodoInicio, String oidPeriodoVigente) {
		MantenimientoLETLideresService lideresService = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");

		/* INI SA PER-SiCC-2012-0682 */
		LabelValue[] listPeriodosVigentes = siccPeriodoList;
		LabelValue basePeriodoVigente = listPeriodosVigentes[0];
		boolean esPeriodoMayorIgual = false;

		String codigoPeriodoInicio = lideresService
				.getCodigoPeriodoByOidPeriodo(new Integer(oidPeriodoInicio));

		if (codigoPeriodoInicio.compareTo(basePeriodoVigente.getLabel()) >= 0) {
			esPeriodoMayorIgual = true;
		}
		/* FIN SA PER-SiCC-2012-0682 */

		return esPeriodoMayorIgual;
	}

	private void evaluarCodigoCliente(Map criteria) {
		Cliente cliente = maeDtoCliente;

		String codigoClienteAux = (String) criteria.get("codigoCliente");
		String codigoCliente = cliente.getCodigo();

		if (codigoCliente.equals(codigoClienteAux)) {
			criteria.put("codigoCliente", "");
		}
	}

	private void evaluarCambioZonaTerritorio(
			MantenimientoMAEModificacionClienteForm f, Map criteria) {
		boolean cambio = true;
		String confirmacionTerritorio = (String) criteria
				.get("confirmacionTerritorio");

		if (!confirmacionTerritorio.equals("ok")) {
			if (f.isMostrarUnidadAdministrativa()) {
				if ((f.getCodigoZonaInicial().equals(f.getCodigoZona()))
						&& (f.getCodigoTerritorioInicial().equals(f
								.getCodigoTerritorio()))) {
					cambio = false;
				}
			}

			if (!cambio) {
				criteria.put("confirmacionTerritorio", "ok");
			} else {
				criteria.put("confirmacionTerritorio", "");
			}
		}
	}

	private boolean existeSubTipoCliente(String codigoTipoCliente,
			String codigoSubTipoCliente, List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteSubTipo ccd = (ClienteSubTipo) it.next();

			if (ccd.getCodigoTipoCliente().equals(codigoTipoCliente)) {
				return true;
			}
		}
		return false;
	}

	private boolean existeSubTipoClienteAux(String codigoTipoCliente,
			String codigoSubTipoCliente, List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteSubTipo ccd = (ClienteSubTipo) it.next();

			if ((ccd.getCodigoTipoCliente().equals(codigoTipoCliente))
					&& (ccd.getCodigoSubTipoCliente()
							.equals(codigoSubTipoCliente))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		if (StringUtils.isBlank(f.getCodigoZona())) {
			return "Ingrese Zona";
		}
		if (StringUtils.isBlank(f.getCodigoTerritorio())) {
			return "Ingrese Territorio";
		}
		if (StringUtils.isBlank(f.getOidPeriodo())) {
			return "Ingrese Periodo";
		}
		if (StringUtils.isBlank(f.getApellidoPaterno())) {
			return "Ingrese Apellido Paterno";
		}
		if (StringUtils.isBlank(f.getTipoDocumentoIdentidad())) {
			return "Ingrese Documento Identidad";
		}
		if (StringUtils.isBlank(f.getNombre1())) {
			return "Ingrese Primer Nombre";
		}
		if (f.getFechaNacimientoD() == null) {
			return "Ingrese Fecha Nacimiento";
		}
		if (StringUtils.isBlank(f.getNivel1())) {
			return "Ingrese " + f.getDescripcionNivel1();
		}
		if (StringUtils.isBlank(f.getNivel2())) {
			return "Ingrese " + f.getDescripcionNivel2();
		}
		if (StringUtils.isBlank(f.getNivel3())) {
			return "Ingrese " + f.getDescripcionNivel3();
		}
		
		if (StringUtils.isBlank(f.getTipoVia())) {
			return "Ingrese Tipo de Vía";
		}

		if (StringUtils.isBlank(f.getNumeroDocumentoIdentidad())) {
			return "Ingrese Documento de Identidad";
		}
		if (siccSubTipoClienteList.size() == 0) {
			return "Debe Ingresar un Tipo/SubTipo Cliente";
		}

		if (StringUtils.isEmpty(f.getApellidoMaterno())) {
			return "Ingrese Apellido Materno";
		}
		
		if(!validarEmail(f)) {
			return getResourceMessage("mantenimientoMAEClienteForm.msg.mailIncorrecto");
		}
		
		if(!f.isIndicadorCamposAdicionales()){
			if (StringUtils.isBlank(f.getNombreVia()) && StringUtils.isBlank(f.getObservacionDireccion())) {
				return "Ingrese Ingresar al menos uno de los 2 campos de Dirección Domicilio(Dirección,Referencias)";
			}
		}

		
		//INI ECU-SiCC-2015-0036
				if(f.isIndicadorCamposAdicionales()){
					if (StringUtils.isBlank(f.getCallePrincipalDD()) 
							&& StringUtils.isBlank(f.getObservacionDireccion())) {
						return "Ingrese Ingresar al menos uno de los 2 campos de Dirección Domicilio(Calle Principal ,Referencias)";
					}
				}
		//FIN ECU-SiCC-2015-0036
		
		if (f.getFechaNacimientoD().compareTo(new Date()) > 0) {
			return "La fecha de nacimiento no puede ser mayor a la fecha actual";
		}

		String fechaNac= DateUtil.convertDateToString(f.getFechaNacimientoD());
		int edad= CalcuEdad(fechaNac);
		if(f.getEdadMinima()!=null && f.getEdadMaxima()!=null) {
			if (edad < Integer.parseInt(f.getEdadMinima())
					|| Integer.parseInt(f.getEdadMaxima()) < (edad)) {
				return "El cliente no cumple con el rango de edades permitidas";
			}
		}
		
		if(f.getCodigoPais().equals("BOE")){
			if( f.getTipoDocumentoIdentidad().equals("2001")){
				String validacion = validarNumeroCarnetIdentidad();
				if(!validacion.equals(""))
					return validacion;
			}
		}
		
		if(!f.isCambioZonaTerritorio())	{	
			if(!f.getCodigoTerritorioInicial().equals(f.getCodigoTerritorio())) {
				return "Debe Validar Zona/Territorio";
			}
		}
		
		if (!validarCamposEntradaCaracteres1(f)) {
			log.debug("mensajeCaracteres1 : " + this.mensajeCaracteres1);
			return this.mensajeCaracteres1;
		}
		
		if (!validarCamposEntradaCaracteres2(f)) {
			log.debug("mensajeCaracteres2 : " + this.mensajeCaracteres2);
			return this.mensajeCaracteres2;
		}
		
		if (!validarCamposEntradaCaracteres3(f)) {
			log.debug("mensajeCaracteres3 : " + this.mensajeCaracteres3);
			return this.mensajeCaracteres3;
		}
		
		if(this.showBancoCuenta && this.showTipoCuentaCuentaCorriente){
			if((f.getBanco()!=null && StringUtils.isBlank(f.getCuentaCorriente()))||
					(f.getBanco()==null && StringUtils.isNotBlank(f.getCuentaCorriente())))
					return "Si Banco cuenta con valor, se debe completar el valor Cuenta Corriente o viceversa";
			
			if((f.getTipoCuenta()!=null && StringUtils.isBlank(f.getCuentaCorriente()))||
					(f.getTipoCuenta()==null && StringUtils.isNotBlank(f.getCuentaCorriente())))
					return "Si Tipo de Cuenta cuenta con valor elegido, se debe completar el valor Cuenta Corriente o viceversa";
		}
		
		if(f.isModificarConsultoraVinculo()){
			if((StringUtils.isNotBlank(f.getCodigoConsultoraVinculo()) && f.getFechaDesdeD() == null))
					return "La Fecha de Inicio es obligatoria para registrar un vínculo";
		}
		
		if((StringUtils.isNotBlank(f.getCodigoLiderVinculo()) && f.getFechaDesdeLiderD() == null))
			return "La Fecha de Inicio es obligatoria para registrar un vínculo";
		
		return "";
	}

	private boolean validarEmail(MantenimientoMAEModificacionClienteForm f){
		if("true".equals(f.getValidarCaracteres3())) {
			return true;
		}
		
		if (StringUtils.isNotEmpty(f.getMail())) {
		
			if (f.getMail().indexOf('@') < 0) 
				return false;
			if (f.getMail().indexOf('.') < 0) 
				return false;
			
			if (f.getMail().indexOf('#') >= 0) 
				return false;
			if (f.getMail().indexOf('%') >= 0) 
				return false;
			if (f.getMail().indexOf('&') >= 0) 
				return false;
			if (f.getMail().indexOf('/') >= 0) 
				return false;
			if (f.getMail().indexOf('(') >= 0) 
				return false;
			
			if (f.getMail().indexOf(')') >= 0) 
				return false;
			if (f.getMail().indexOf('\\') >= 0) 
				return false;
			if (f.getMail().indexOf('|') >= 0) 
				return false;
			if (f.getMail().indexOf('!') >= 0) 
				return false;
			if (f.getMail().indexOf('"') >= 0) 
				return false;
			
			if (f.getMail().indexOf("'") >= 0) 
				return false;
			if (f.getMail().indexOf('?') >= 0) 
				return false;
			if (f.getMail().indexOf('*') >= 0) 
				return false;
			if (f.getMail().indexOf(',') >= 0) 
				return false;
			
			for (int i = 0; i < f.getMail().length(); i++) {
				int car = f.getMail().codePointAt(i);
				if (car == 193) 
					return false; //A ACENTUADA
				if (car == 201) 
					return false; //E ACENTUADA
				if (car == 205) 
					return false; //I ACENTUADA
				if (car == 211) 
					return false; //O ACENTUADA
				if (car == 218) 
					return false; //U ACENTUADA
				if (car == 191) 
					return false; //SIGNO DE INTERROGACI?N ABIERTA
			}
		}
		
		return true;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		this.salirGrabarPantallaPadre = false;
		this.codigoFinalCliente = "";
		String codigoFinal = "";

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		// Conversiones Checkbox SSICC a NSSICC

		if (f.isValorIndicadorFactElectB()) {
			f.setValorIndicadorFactElect("0");
		} else {
			f.setValorIndicadorFactElect("1");
		}

		if (f.getTipoDocumentoIdentidad().equals("2011")) {
			if (f.isIndicadorDocFiscalB()) {
				f.setIndicadorDocFiscal("S");
			} else {
				f.setIndicadorDocFiscal("N");
			}
		}

		if (f.getTipoDocumentoIdentidad().equals("2011")) {
			if (f.isIndicadorDocFiscalAux()) {
				f.setIndicadorDocFiscal("S");
			} else {
				f.setIndicadorDocFiscal("N");
			}
		}

		// if (f.isIndicadorDocFiscalB()) {
		// f.setIndicadorDocFiscal("S");
		// } else {
		// f.setIndicadorDocFiscal("N");
		// }

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		// ****************** NSSICC CONVERSIONES ********
		if (f.getFechaNacimientoD() != null)
			f.setFechaNacimiento(DateUtil.getDate(f.getFechaNacimientoD()));
		if (f.getFechaIngresoD() != null)
			f.setFechaIngreso(DateUtil.getDate(f.getFechaIngresoD()));
		if (f.getFechaDesdeD() != null)
			f.setFechaDesde(DateUtil.getDate(f.getFechaDesdeD()));
		if (f.getFechaHastaD() != null)
			f.setFechaHasta(DateUtil.getDate(f.getFechaHastaD()));
		if (f.getFechaDesdeLiderD() != null)
			f.setFechaDesdeLider(DateUtil.getDate(f.getFechaDesdeLiderD()));
		if (f.getFechaHastaLiderD() != null)
			f.setFechaHastaLider(DateUtil.getDate(f.getFechaHastaLiderD()));

		Map criteria = BeanUtils.describe(f);
		List detalListSubTipo = maeClienteSubTipoList;
		for (int i = 0; i < detalListSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) detalListSubTipo
					.get(i);
			String tipo = clienteSubTipo.getCodigoTipoCliente();
			String subtipo = clienteSubTipo.getCodigoSubTipoCliente();
			if ((tipo.equals(Constants.MAE_CODIGO_TIPO_CONSULTORA) && subtipo
					.equals(Constants.MAE_CODIGO_SUBTIPO_OFICINA))
					|| (tipo.equals(Constants.MAE_TIPO_CLIENTE_GERENTE) && subtipo
							.equals(Constants.MAE_SUBTIPO_GERENTE_REGION))
					|| (tipo.equals(Constants.MAE_TIPO_CLIENTE_GERENTE) && subtipo
							.equals(Constants.MAE_SUBTIPO_GERENTE_ZONA))) {
				String codigoCUB = f.getCodigoCUB();
				if (StringUtils.isBlank(codigoCUB)) {
					this.addWarn(
							"Advertencia",
							this.getResourceMessage("mantenimientoMAEClienteForm.msg.codigoCUBRequerido"));

					return false;
				}
			}
		}
		maeFlagDeudoras = Constants.NUMERO_CERO;
		boolean flagValidarDocumento = validarDocumentoIdentidad(f,
				clienteService, criteria);
		if (!flagValidarDocumento) {
			String mensajeError = (String) criteria.get("mensajeError");
			log.debug("mensajeError " + mensajeError);
			if (StringUtils.isNotEmpty(mensajeError)) {
				List listConsultorasDeudoras = (List) criteria
						.get("listDeudoras");// clienteService.getListConsultorasDeudorasAval(criteria);
				maeDeudorasList = listConsultorasDeudoras;
				maeFlagDeudoras = Constants.NUMERO_UNO;

			}

			return false;
		}

		if (!f.isPermitirModificarUbigeo()) {
			f.setNivel1(f.getCodNivel1());
			f.setNivel2(f.getCodNivel2());
			f.setNivel3(f.getCodNivel3());
			f.setNivel4(f.getCodNivel4());
			f.setNivel5(f.getCodNivel5());
			f.setNivel6(f.getCodNivel6());
		}

		// Obtenemos la unidad geografica de la direccion principal
		String codigoUbigeo = "";
		if (f.getNivel6() != null && !("".equals(f.getNivel6())))
			codigoUbigeo = f.getNivel6();
		else if (f.getNivel5() != null && !("".equals(f.getNivel5())))
			codigoUbigeo = f.getNivel5();
		else if (f.getNivel4() != null && !("".equals(f.getNivel4())))
			codigoUbigeo = f.getNivel4();
		else if (f.getNivel3() != null && !("".equals(f.getNivel3())))
			codigoUbigeo = f.getNivel3();
		criteria.put("codigoUbigeo", codigoUbigeo);
		criteria.put("listSubTipo", detalListSubTipo);

		// Verificamos si ha modificado el codigo de cliente
		evaluarCodigoCliente(criteria);

		// Verificamos si cambios Zona y Territorio
		evaluarCambioZonaTerritorio(f, criteria);

		List erroresEncontrados = clienteService.validarDatosCliente(criteria);
		if (erroresEncontrados.size() > 0) {
			boolean validacion = false;
			Iterator it = erroresEncontrados.iterator();
			while (it.hasNext()) {
				String error = (String) it.next();

				if (error
						.equals("mantenimientoMAEClienteForm.msg.TerritorioNoCorrespondeDistrito")) {

					String texto = this.getResourceMessage(error);
					f.setMensajeConfirmacion(texto);
					
					validacion = true;
					f.setOidTerritorioAdministrativo((String) criteria.get("oidTerritorioAdministrativo"));
					f.setOidTerritorio((String) criteria.get("oidTerritorio"));
					/*return false;*/
				} else 
					this.addError("Error", this.getResourceMessage(error));

			}

			if(!validacion) {
				return false;
			}

		} else {
			f.setOidTerritorioAdministrativo((String) criteria
					.get("oidTerritorioAdministrativo"));
			f.setOidTerritorio((String) criteria.get("oidTerritorio"));
		}

		List detalListClasificacion = maeClienteClasificacionList;

		/* INI SA PER-SiCC-2013-0265 */
		if (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_HIJADUPLA,
				Constants.MAE_SUBTIPO_HIJADUPLA_HIJADUPLA, detalListSubTipo)) {
			String oidTipoDocDuplaCyzone = clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais());
			if (!f.getTipoDocumentoIdentidad().equals(oidTipoDocDuplaCyzone)) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarTipoDocDuplaCyzone"));

				return false;
			}

			if (detalListSubTipo.size() > 1) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarSubTipo.hijaDupla"));

				return false;
			}
		}
		if (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
				detalListSubTipo)) {
			if (detalListSubTipo.size() > 1) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarSubTipo.consultoraNegocio"));
				return false;
			}
		}

		if (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
				Constants.MAE_SUBTIPO_GERENTE_REGION, detalListSubTipo)
				|| existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalListSubTipo)) {
			boolean valido = false;

			if (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
					detalListSubTipo)) {
				if (detalListSubTipo.size() == 2) {
					valido = true;
				}
			}
			if (!valido) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarSubTipo.Gerente"));

				return false;
			}
		}

		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
				detalListSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
						detalListSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_REGION, detalListSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalListSubTipo))) {
			if (!f.isMostrarUnidadAdministrativa()) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarNivelRiesgo"));

				actualizarListarUbigeo(f);

				return false;
			}

			String oidTipoDocDuplaCyzone = clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais());
			if (f.getTipoDocumentoIdentidad().equals(oidTipoDocDuplaCyzone)) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarTipoDocConsultora"));

				return false;
			}
		} else {
			String oidTipoDocDuplaCyzone = clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais());
			if (!f.getTipoDocumentoIdentidad().equals(oidTipoDocDuplaCyzone)) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarTipoDocDuplaCyzone"));

				actualizarListarUbigeo(f);

				return false;
			}
		}
		/* FIN SA PER-SiCC-2013-0265 */

		/* Lider que la recomienda no puede se la misma cliente */
		if (StringUtils.equals(f.getCodigoCliente(), f.getCodigoLiderVinculo())) {
			this.addError(
					"Mensaje",
					getResourceMessage("mantenimientoMAEModificacionClienteForm.vinculoLiderRecomendada.error.misma"));

			return false;
		}
		/**/

		boolean bGrabarCab = false;
		try {

			Cliente cliente = obtenerCliente(f, detalListSubTipo,
					detalListClasificacion);

			for (int i = 0; i < cliente.getListClienteVinculo().size(); i++) {
				ClienteVinculo vinculoAux = (ClienteVinculo) cliente.getListClienteVinculo().get(i);
				
				if(Constants.MAE_TIPO_VINCULO_RECOMENDANTE.equals(vinculoAux.getCodigoTipoVinculo())) {
					vinculoAux.setFechaDesde(f.getFechaDesdeD());
					vinculoAux.setFechaHasta(f.getFechaHastaD());
				}
				
			}

			// Actualiza los datos del cliente
			clienteService.updateCliente(cliente);
			//

			// Verificamos si para el pais se tiene que mostrar el digito de
			// control
			if (f.isMostrarDigitoControl()) {
				/*
				 * this.addError("Mensaje", getResourceMessage(
				 * "mantenimientoMAEModificacionClienteForm.updated", new
				 * Object[] { cliente.getCodigo() + "-" +
				 * cliente.getDigitoControl() }));
				 */
				 codigoFinal = cliente.getCodigo() + "-" +  cliente.getDigitoControl();

			} else {
				/*
				 * this.addError("Mensaje", getResourceMessage(
				 * "mantenimientoMAEModificacionClienteForm.updated", new
				 * Object[] { cliente.getCodigo() })); 
				*/				
				codigoFinal =cliente.getCodigo();
				

			}

			if (f.isMostrarMensajePeriodoFinCerrado()) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.periodoFinCerrado"));

			}

			f.setActualizoOK(true);
			bGrabarCab = true;

			// validamos si ha ingresado cliente recomendante
			if (!f.isEsDuplaCyzone()) {
				if (f.isMostrarConsultoraVinculo()) {
					if (!f.getCodigoConsultoraVinculo().equals(
							f.getCodigoConsultoraRecomendante())
							&& (!f.getCodigoConsultoraVinculo().equals(""))) {
						f.setCodigoConsultoraRecomendante(f
								.getCodigoConsultoraVinculo());
					} else {
						f.setCodigoConsultoraRecomendante("");
					}
				} else {
					f.setCodigoConsultoraRecomendante("");
				}

				if (!f.getCodigoConsultoraRecomendante().equals("")) {

					// verificamos si se muestra o no la pantalla
					// la logica ya se encuentra en el metodo obtenerCliente
					if (f.isMostrarPantallaPremios()) {
						this.pathPremios = f.getOidPeriodo() + "|"
								+ f.getOidPais() + "|" + f.getCodigoCliente()
								+ "|" + f.getCodigoConsultoraRecomendante();
						idPremiosMaeModificacion = this.pathPremios;

					} else {
						this.pathPremios = "";
					}
				} else {
					this.pathPremios = "";
				}
			}

		} catch (Exception e) {
			bGrabarCab = false;
			this.addError(
					"Mensaje",
					getResourceMessage("mantenimientoEDULocal.cabecera.error",
							new Object[] { e.getMessage() }));

		}
		if (bGrabarCab) {
			this.salirGrabarPantallaPadre = true;
			this.codigoFinalCliente = codigoFinal;
			// redireccionarPagina("consultaMAEClienteList.xhtml",
			// "?codigoCliente=" + codigoFinal);
		}
		return bGrabarCab;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #devuelveMensajeAlertaDefaultAction()
	 */
	public String devuelveMensajeAlertaDefaultAction() throws Exception {
		return this.obtenerCodigoCliente(this.codigoFinalCliente);
	}

	/**
	 * @param f
	 */
	private void limpiar(MantenimientoMAEModificacionClienteForm f) {
		f.setCodigoCliente("");
		f.setEstatus("");
		f.setFechaIngreso("");
		f.setFechaNacimiento("");
		f.setFechaDesde("");
		f.setFechaHasta("");

		/* INI SA PER-SiCC-2013-0147 */
		// f.setOidPeriodo("");
		/* FIN SA PER-SiCC-2013-0147 */

		f.setOidPeriodoPrimerPedido("");

		f.setApellidoPaterno("");
		f.setApellidoMaterno("");
		f.setApellidoCasada("");
		f.setNombre1("");
		f.setNombre2("");
		f.setEdad("0");
		f.setGradoInstruccion("");
		f.setNacionalidad("");
		f.setSexo("");
		f.setTratamiento("");
		f.setEstadoCivil("");
		f.setCodigoAnterior("");
		f.setTipoCutis("");
		f.setOtrasMarcas("");

		f.setTipoDocumentoIdentidad("");
		f.setNumeroDocumentoIdentidad("");
		f.setTipoDocumentoIdentidad2("");
		f.setNumeroDocumentoIdentidad2("");
		f.setTipoDocumentoIdentidad3("");
		f.setNumeroDocumentoIdentidad3("");

		f.setCodigoZona("");
		f.setCodigoTerritorio("");
		f.setCodigoZonaInicial("");
		f.setCodigoTerritorioInicial("");

		f.setNivel1("");
		f.setNivel2("");
		f.setNivel3("");
		f.setNivel4("");
		f.setNivel5("");
		f.setNivel6("");
		f.setTipoVia("");
		f.setNumeroPrincipal("");
		f.setNombreVia("");
		f.setCorreVia("");
		f.setObservacionDireccion("");
		f.setBarrio("");
		f.setTelefonoCasa("");
		f.setTelefonoCelular("");
		f.setTelefonoTrabajo("");
		f.setTelefonoReferencia("");
		f.setMail("");
		f.setCodigoPostal("");

		f.setNivel1CT("");
		f.setNivel2CT("");
		f.setNivel3CT("");
		f.setNivel4CT("");
		f.setNivel5CT("");
		f.setNivel6CT("");
		f.setTipoViaCT("");
		f.setNumeroPrincipalCT("");
		f.setNombreViaCT("");
		f.setCorreViaCT("");
		f.setObservacionDireccionCT("");
		f.setBarrioCT("");
		f.setTipoDireccionCT("");
		f.setTelefonoCasaDireccionEntrega("");
		f.setTelefonoCelularDireccionEntrega("");
		f.setCodigoPostalCT("");

		f.setOidConsultoraVinculo("");
		f.setCodigoConsultoraVinculo("");
		f.setNombreConsultoraVinculo("");
		f.setFechaDesde("");
		f.setFechaHasta("");

		f.setConfirmacionTerritorio("");
		f.setMensajeConfirmacion("");

		f.setTipoDireccionCT(MAE_OID_TIPO_DIRECCION_DISTRIBUCION);
		f.setTieneDireccionOpcional(false);

		f.setEsDuplaCyzone(false);
		f.setEsTipoConsultora(false);
		f.setMostrarNumeroIdentidad(true);
		f.setMostrarDireccion(true);
		f.setMostrarUnidadAdministrativa(false);
		f.setMostrarConsultoraVinculo(false);

		f.setBorradoDireccionPrincipal(false);
		f.setBorradoDireccionOpcional(false);
		f.setBorradoDocumentoIdentidad1(false);
		f.setBorradoDocumentoIdentidad2(false);
		f.setBorradoDocumentoIdentidad3(false);

		f.setTieneDocumentoIdentidad2(false);
		f.setTieneDocumentoIdentidad3(false);

		f.setTieneTipologiaConsultora(false);
		f.setTieneTipologiaConsultoraInicio(false);

		f.setModificarConsultoraVinculo(false);
		f.setCodigoConsultoraRecomendante("");
		f.setTipoClasificacion("");

		f.setMostrarPantallaPremios(false);
		f.setPrimeraVezDespliegueDireccionEntrega(false);
		f.setMostrarDigitoControl(true);
		f.setCambioZonaTerritorio(false);

		f.setPrimeraVezDespliegueReferencias(false);
		f.setPermitirModificarPeriodoIngreso(true);
		f.setPermitirModificarPeriodoVigente(true);
		f.setMostrarMensajeCambioPeriodoVigente(false);
		f.setMostrarMensajePeriodoFinCerrado(false);
		f.setMostrarMensajePedidoExtemporaneo(false);
		f.setConsultoraPasoPedido(false);

		f.setLongitudCodigoZona(MAE_LONGITUD_CODIGO_ZONA);
		f.setPermitirModificarUbigeo(true);
		f.setMostrarTipoVia(true);
		f.setMostrarNumeroPrincipal(true);
		f.setMostrarUbigeoEntrega(true);
		f.setMostrarCodigoEmpleado(false);
		/* INI JJ PER-SiCC-2012-0329 */
		f.setMostrarCodigoCUB(false);
		/* FIN JJ PER-SiCC-2012-0329 */

		f.setBorrarConcursosPremios(true);

		f.setActualizoOK(false);
		f.setEsClienteCaducado(false);
		this.pathPremios = "";

		f.setMostrarRedefinirPeriodo(false);
		f.setEsClientePotencialAval(false);
		f.setAprobarAvaladas(false);

		f.setPermitirCompletarCerosIdentidad(true);

		f.setValidarCaracteres1("");
		f.setValidarCaracteres2("");
		f.setValidarCaracteres3("");
		f.setValidarCaracteresIdentidad("");
		f.setPermitirComenzarCerosIdentidad(true);

		f.setMostrarBarrio(false);

		/* INI SA PER-SiCC-2012-0459 */
		f.setMostrarCiudad(false);
		f.setMostrarVillaPoblacion(false);
		f.setCodigoCiudad("");
		f.setVillaPoblacion("");
		f.setCodigoCiudadCT("");
		f.setVillaPoblacionCT("");
		/* FIN SA PER-SiCC-2012-0459 */

		/* INI SA PER-SiCC-2012-0365 */
		f.setTipoViaVacaciones("");
		f.setNumeroPrincipalVacaciones("");
		f.setNombreViaVacaciones("");
		f.setCorreViaVacaciones("");
		f.setObservacionDireccionVacaciones("");
		f.setBarrioVacaciones("");

		f.setCodigoPeriodoInicio("");
		f.setCodigoPeriodoFin("");
		f.setNivel1Vacaciones("");
		f.setNivel2Vacaciones("");
		f.setNivel3Vacaciones("");
		f.setNivel4Vacaciones("");
		f.setNivel5Vacaciones("");
		f.setNivel6Vacaciones("");
		f.setCodNivel2Vacaciones("");
		f.setCodNivel3Vacaciones("");
		f.setCodNivel4Vacaciones("");
		f.setCodNivel5Vacaciones("");
		f.setCodNivel6Vacaciones("");

		f.setCodigoCiudadVacaciones("");
		f.setVillaPoblacionVacaciones("");

		f.setTelefonoCasaDireccionVacaciones("");
		f.setTelefonoCelularDireccionVacaciones("");

		f.setMostrarDireccionVacaciones(false);
		f.setIndicadorDireccionVacaciones("0");
		f.setPrimeraVezDespliegueDireccionVacaciones(false);
		f.setBorradoDireccionVacaciones(false);
		f.setTieneDireccionVacaciones(false);
		f.setActualizaUbigeoDirecciones(false);
		f.setTienePedidoEnProcesoFacturacion(false);
		f.setLineaDireccionVacaciones("");
		/* FIN SA PER-SiCC-2012-0365 */

		f.setIndicadorCompromiso(Constants.NUMERO_CERO);
		f.setMotivo("");

		/* INI SA COS-SiCC-2013-0031 */
		f.setMostrarIndicadorImpresionPaqDoc(false);
		f.setIndicadorImpresionPaqDoc(Constants.NO);
		this.indicadorImpresionPaqDocBoolean = false;
		/* FIN SA COS-SiCC-2013-0031 */

		f.setEsMayorPeriodoVigente(false);
		f.setRequiereGenerarEstatus(false);
		f.setCodigoPeriodoIniUA("");
		f.setMostrarBotonRedifinirVigenciaUA(false);

		f.setTelefonoAdicional("");

		f.setMostrarVinculoLider(false);
		f.setOidLiderVinculo("");
		f.setCodigoLiderVinculo("");
		f.setNombreLiderVinculo("");
		f.setFechaDesdeLider("");
		f.setFechaHastaLider("");
		f.setIndicadorDocFiscal(Constants.IND_DOC_FISC_NO);
		f.setIndicadorDocFiscalB(false);
		
		this.setMostrarBotonEliminarDireccion(true);
		f.setValorIndicadorFactElect(Constants.UNO);
		f.setValorIndicadorFactElectAux(Constants.UNO);
		f.setLongitudTipoDocumento2("15");
		
		//INI ECU-SiCC-2015-0036
		f.setTipoPersona("");
		f.setOrigenIngreso("");
		f.setIndicadorCamposAdicionales(false);
		f.setBarrioDD("");
		f.setManzanaLetraDD("");
		f.setEtapaConjuntoDD("");
		f.setCallePrincipalDD("");
		f.setCalleSecundariaDD("");
			
		f.setBarrioDE("");
		f.setManzanaLetraDE("");
		f.setEtapaConjuntoDE("");
		f.setCallePrincipalDE("");
		f.setCalleSecundariaDE("");
		setCopiaDireccionEntrega(Constants.NO);
		//FIN ECU-SiCC-2015-0036
		
		this.setMostrarBotonEliminarTipo1(false);
		this.setMostrarBotonEliminarTipo2(false);
		this.setMostrarBotonEliminarDireccion(false);
	}

	private void setearCamposModuloPais(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService) {
		String permitirModificarUbigeo = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_UBIGEO);
		String mostrarTipoVia = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_TIPO_VIA);
		String mostrarNumeroPrincipal = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_NUMERO_PRINCIPAL);
		String mostrarUbigeoEntrega = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_DIRECCION_ENTREGA);
		String noPermitirCompletarCerosIdentidad = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD);

		String validarCaractererNV1 = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_NOVALIDO1);
		String validarCaractererNV2 = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_NOVALIDO2);
		String validarCaractererNV3 = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_NOVALIDO3);
		String validarCaracteresDocIdent = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD);

		String validarBarrio = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_BARRIO);
		String noPermitirComenzarCerosIdentidad = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.VAL_IDENT_CERO);

		/* INI SA PER-SiCC-2012-0459 */
		String mostrarCiudad = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CIUDAD);
		String mostrarVillaPoblacion = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_VILLA);
		/* FIN SA PER-SiCC-2012-0459 */

		/* INI SA PER-SiCC-2012-0365 */
		String mostrarDireccionVacaciones = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_DIRECCION_VACACIONES);
		/* INI SA PER-SiCC-2012-0365 */

		/* INI SA COS-SiCC-2013-0031 */
		String indicadorPaqueteDocumentario = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_PAQUE_DOCUM);
		/* FIN SA COS-SiCC-2013-0031 */

		String mostrarVinculoLider = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_LIDER_RECOM);

		if (permitirModificarUbigeo != null)
			f.setPermitirModificarUbigeo(false);

		if (mostrarTipoVia != null) {
			f.setMostrarTipoVia(false);
			f.setTipoVia(clienteService.getOidTipoVia(mostrarTipoVia)
					.toString());
			f.setTipoViaCT(clienteService.getOidTipoVia(mostrarTipoVia)
					.toString());
		}

		if (mostrarNumeroPrincipal != null)
			f.setMostrarNumeroPrincipal(false);

		if (mostrarUbigeoEntrega != null)
			f.setMostrarUbigeoEntrega(false);

		if (noPermitirCompletarCerosIdentidad != null)
			f.setPermitirCompletarCerosIdentidad(false);

		if (validarCaractererNV1 != null) {
			f.setValidarCaracteres1(validarCaractererNV1);
			f.setCadenaCaracteresV1(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO1, "S"));
			f.setCadenaCaracteresNV1(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO1, "N"));
		}
		if (validarCaractererNV2 != null) {
			f.setValidarCaracteres2(validarCaractererNV2);
			f.setCadenaCaracteresV2(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO2, "S"));
			f.setCadenaCaracteresNV2(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO2, "N"));
		}
		if (validarCaractererNV3 != null) {
			f.setValidarCaracteres3(validarCaractererNV3);
			f.setCadenaCaracteresV3(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO3, "S"));
			f.setCadenaCaracteresNV3(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO3, "N"));
		}
		if (validarCaracteresDocIdent != null) {
			f.setValidarCaracteresIdentidad(validarCaracteresDocIdent);
			f.setCadenaCaracteresVIdentidad(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD,
							"S"));
			f.setCadenaCaracteresNVIdentidad(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD,
							"N"));
		}

		if (validarBarrio != null)
			f.setMostrarBarrio(true);

		if (noPermitirComenzarCerosIdentidad != null)
			f.setPermitirComenzarCerosIdentidad(false);

		/* INI SA PER-SiCC-2012-0459 */
		if (mostrarCiudad != null)
			f.setMostrarCiudad(true);

		if (mostrarVillaPoblacion != null)
			f.setMostrarVillaPoblacion(true);
		/* FIN SA PER-SiCC-2012-0459 */

		/* INI SA PER-SiCC-2012-0365 */
		if (mostrarDireccionVacaciones != null)
			f.setMostrarDireccionVacaciones(true);
		/* INI SA PER-SiCC-2012-0365 */

		/* INI SA COS-SiCC-2013-0031 */
		if (Constants.MAE_IMPRI_PAQUE_DOCUM
				.equals(indicadorPaqueteDocumentario))
			f.setMostrarIndicadorImpresionPaqDoc(true);
		/* FIN SA COS-SiCC-2013-0031 */

		if (Constants.MAE_LIDER_RECOM.equals(mostrarVinculoLider))
			f.setMostrarVinculoLider(true);

	}

	private Date obtenerFechaAñoSiguiente(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.YEAR, 1);
		Date fechaAñoSiguiente = calendar.getTime();

		return fechaAñoSiguiente;
	}

	/**
	 * metodo auxiliar que me permite recuperar en cadena el valor de un objeto
	 * 
	 * @param obj
	 * @return
	 */
	private String reemplazarNulo(Object obj) {
		if (obj == null)
			return "";
		else
			return String.valueOf(obj);
	}

	private void initCabecera(MantenimientoMAEModificacionClienteForm f,
			Cliente cliente) throws IllegalAccessException,
			InvocationTargetException {

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ClienteAdicional clienteAdicional = cliente.getClienteAdicional();
		ClientePrimerContacto clientePrimerContacto = cliente
				.getClientePrimerContacto();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoy = new Date(System.currentTimeMillis());

		ConsultaMAEClienteSearchForm fB = (ConsultaMAEClienteSearchForm) this.formBusqueda;

		f.setIndicadorDocFiscal(cliente.getClienteAdicional().getIndDocFiscal());
		f.setFechaActual(sdf.format(hoy));
		f.setFechaDesde(sdf.format(hoy));
		f.setFechaHasta(sdf.format(obtenerFechaAñoSiguiente(hoy)));

		f.setOidCliente(String.valueOf(cliente.getOid()));
		f.setCodigoCliente(cliente.getCodigo());
		f.setDigitoControl(reemplazarNulo(cliente.getDigitoControl()));
		f.setEstatus(clienteAdicional.getDescripcionStatus());
		if (clienteAdicional.getIndicadorActivo().intValue() == 1)
			f.setIndicadorActivo("Si");
		else
			f.setIndicadorActivo("No");

		// Recuperamos los datos principales del cliente
		f.setApellidoPaterno(cliente.getApellido1());
		f.setApellidoMaterno(cliente.getApellido2());
		f.setApellidoCasada(cliente.getApellidoCasada());
		f.setNombre1(cliente.getNombre1());
		f.setNombre2(cliente.getNombre2());
		f.setSexo(reemplazarNulo(cliente.getSexo()));
		f.setTratamiento(reemplazarNulo(cliente.getTratamiento()));
		f.setFechaIngreso(sdf.format(cliente.getFechaIngreso()));
		f.setCodigoAnterior(reemplazarNulo(cliente.getCodigoAnterior()));

		// Recuperamos los datos adicionales del cliente
		if (clienteAdicional.getFechaNacimiento() != null)
			f.setFechaNacimiento(sdf.format(clienteAdicional
					.getFechaNacimiento()));
		f.setEstadoCivil(reemplazarNulo(clienteAdicional.getOidEstadoCivil()));
		f.setGradoInstruccion(reemplazarNulo(clienteAdicional
				.getOidNivelEstudios()));
		f.setNacionalidad(reemplazarNulo(clienteAdicional.getOidNacionalidad()));
		f.setCodigoEstatus(clienteAdicional.getCodigoStatus());
		f.setCodigoEmpleado(reemplazarNulo(clienteAdicional.getCodigoEmpleado()));
		/* INI JJ PER-SiCC-2012-0329 */
		f.setCodigoCUB(reemplazarNulo(clienteAdicional.getCodigoCUB()));
		f.setCodigoCUBAnterior(reemplazarNulo(clienteAdicional.getCodigoCUB()));
		/* FIN JJ PER-SiCC-2012-0329 */
		f.setTipoCutis(reemplazarNulo(clienteAdicional.getOidTipoCutis()));
		f.setOtrasMarcas(reemplazarNulo(clienteAdicional.getOidOtrasMarcas()));
		f.setTipoPersona(reemplazarNulo(clienteAdicional.getValTipoPersona()));
		//INI ECU-SiCC-2015-0036
		f.setTipoPersona(reemplazarNulo(clienteAdicional.getValTipoPersona()));
		f.setOrigenIngreso(reemplazarNulo(clienteAdicional.getValOrigenIngreso()));
		//FIN ECU-SiCC-2015-0036
		f.setCodigoBanco(clienteAdicional.getCodigoBanco());
		f.setCuentaBancaria(reemplazarNulo(clienteAdicional.getCuentaBancaria()));
		
		f.setBanco(clienteAdicional.getValBanco());
		f.setTipoCuenta(clienteAdicional.getValTipoCuenta());
		f.setCuentaCorriente(clienteAdicional.getValCuentaCorriente());

		if (clientePrimerContacto != null) {
			f.setOidPeriodoPrimerPedido(String.valueOf(clientePrimerContacto
					.getOidPeriodo()));
			f.setPermitirModificarPeriodoIngreso(false);
		}
		f.setValorIndicadorFactElect(clienteAdicional.getIndImprDocu());
		f.setValorIndicadorFactElectAux(clienteAdicional.getIndImprDocu());
		String S = clienteAdicional.getIndImprDocu();
		// Pestaña OTROS
		f.setIndicadorCompromiso(clienteAdicional.getIndicadorCompromiso());
		f.setMotivo(clienteAdicional.getMotivo());
		f.setValorIndicadorCompromiso(clienteAdicional.getIndicadorCompromiso());

		/* INI SA COS-SiCC-2013-0031 */
		if (Constants.NO.equals(clienteAdicional.getIndicadorImpresionPaqDoc())) {
			f.setIndicadorImpresionPaqDoc(Constants.NO);
			this.indicadorImpresionPaqDocBoolean = false;
		} else {
			f.setIndicadorImpresionPaqDoc(Constants.SI);
			this.indicadorImpresionPaqDocBoolean = true;
		}
		/* FIN SA COS-SiCC-2013-0031 */
		
		//INI PER-SiCC-2015-0589 - Mostrar indicador calcular percepcion
		if(f.isIndicadorCalcPercep()){
			if(StringUtils.isBlank(clienteAdicional.getIndicadorCalularPercepcion()) || StringUtils.equalsIgnoreCase(clienteAdicional.getIndicadorCalularPercepcion(), Constants.SI)){
				f.setBooleanValorIndicadorCalcPercep(true);
			}else{
				if(StringUtils.equalsIgnoreCase(clienteAdicional.getIndicadorCalularPercepcion(), Constants.SI)){
					f.setBooleanValorIndicadorCalcPercep(false);
				}
			}
		}
		//FIN PER-SiCC-2015-0589 

		// Recuperamos las identificaciones del cliente
		List listClienteIdentificacion = cliente.getListClienteIdentificacion();

		boolean encontradoIdentificacionPrincipal = false;
		int i2 = 0;
		int i3 = 0;
		for (int i = 0; i < listClienteIdentificacion.size() && i < 3; i++) {
			ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) listClienteIdentificacion
					.get(i);

			if (i == 0) {
				if (clienteIdentificacion.getIndicadorPrincipal().intValue() == 1) {
					encontradoIdentificacionPrincipal = true;

					f.setTipoDocumentoIdentidad(String
							.valueOf(clienteIdentificacion
									.getOidTipoDocumento()));
					f.setNumeroDocumentoIdentidad(clienteIdentificacion
							.getNumeroDocumento());
					f.setSubTipoDocumentoIdentidad(clienteIdentificacion.getCodigoSubtipoDocumento());
					i2 = 1;
					i3 = 2;
				} else {
					i2 = 0;
					i3 = 1;
				}
			}

			if (i == i2) { // Representa documento auxiliar
				f.setTipoDocumentoIdentidad2(String
						.valueOf(clienteIdentificacion.getOidTipoDocumento()));
				f.setNumeroDocumentoIdentidad2(clienteIdentificacion
						.getNumeroDocumento());
				f.setSubTipoDocumentoIdentidad2(clienteIdentificacion.getCodigoSubtipoDocumento());
				f.setTieneDocumentoIdentidad2(true);
				this.mostrarBotonEliminarTipo2 = true;
			}

			if (i == i3) { // Representa documento auxiliar
				f.setTipoDocumentoIdentidad3(String
						.valueOf(clienteIdentificacion.getOidTipoDocumento()));
				f.setNumeroDocumentoIdentidad3(clienteIdentificacion
						.getNumeroDocumento());
				f.setSubTipoDocumentoIdentidad3(clienteIdentificacion.getCodigoSubtipoDocumento());
				f.setTieneDocumentoIdentidad3(true);
			}
		}
		f.setTieneIdentificacionPrincipal(encontradoIdentificacionPrincipal);

		// Recuperamos los Tipos del Cliente/ Clasificacion del Cliente
		List listClienteSubTipo = new ArrayList();
		Iterator it = cliente.getListClienteSubTipo().iterator();
		while (it.hasNext()) {
			listClienteSubTipo.add(it.next());
		}

		maeClienteSubTipoList = listClienteSubTipo;
		// session.setAttribute(Constants.MAE_CLIENTE_SUBTIPO_LIST,
		// listClienteSubTipo);
		updateTiposClasificaciones(listClienteSubTipo);

		List listClienteClasificacion = new ArrayList();
		for (int i = 0; i < listClienteSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) listClienteSubTipo
					.get(i);

			if (clienteSubTipo.getCodigoTipoCliente().equals(
					Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
				f.setEsDuplaCyzone(true);
				f.setEsTipoConsultora(false);
				f.setMostrarDireccion(false);
				f.setMostrarConsultoraVinculo(true);
			}

			if (clienteSubTipo.getCodigoTipoCliente().equals(
					Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
				f.setEsDuplaCyzone(false);
				f.setEsTipoConsultora(true);
				f.setMostrarUnidadAdministrativa(true);
				f.setMostrarConsultoraVinculo(true);

				f.setTieneTipologiaConsultora(true);
				f.setTieneTipologiaConsultoraInicio(true);

				if (clienteSubTipo.getCodigoSubTipoCliente().equals(
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA)) {
					f.setMostrarCodigoEmpleado(true);
				}
			}

			if (clienteSubTipo.getCodigoTipoCliente().equals(
					Constants.MAE_TIPO_CLIENTE_GERENTE)) {
				if ((clienteSubTipo.getCodigoSubTipoCliente()
						.equals(Constants.MAE_SUBTIPO_GERENTE_REGION))
						|| (clienteSubTipo.getCodigoSubTipoCliente()
								.equals(Constants.MAE_SUBTIPO_GERENTE_ZONA)))
					f.setMostrarCodigoEmpleado(true);
				f.setMostrarUnidadAdministrativa(true);
				/* INI JJ PER-SiCC-2012-0329 */
				f.setMostrarCodigoCUB(true);
				/* FIN JJ PER-SiCC-2012-0329 */
			}
			if (clienteSubTipo.getCodigoTipoCliente().equals(
					Constants.MAE_CODIGO_TIPO_CONSULTORA)) {
				if (clienteSubTipo.getCodigoSubTipoCliente().equals(
						Constants.MAE_CODIGO_SUBTIPO_OFICINA)) {
					f.setMostrarCodigoCUB(true);
				}
			}

			listClienteClasificacion.addAll(clienteSubTipo
					.getListClienteClasificacion());
		}

		maeClienteClasificacionList = listClienteClasificacion;

		// Recuperamos la Zona y Territorio del cliente
		if (cliente.getClienteUnidadAdministrativa() != null) {
			ClienteUnidadAdministrativa clienteUnidadAdministrativa = cliente
					.getClienteUnidadAdministrativa();

			f.setCodigoZona(clienteUnidadAdministrativa.getCodigoZona());
			f.setCodigoTerritorio(clienteUnidadAdministrativa
					.getCodigoTerritorio());
			f.setCodigoRegion(clienteUnidadAdministrativa.getCodigoRegion());
			f.setCodigoSeccion(clienteUnidadAdministrativa.getCodigoSeccion());

			f.setCodigoZonaInicial(f.getCodigoZona());
			f.setCodigoTerritorioInicial(f.getCodigoTerritorio());
			f.setCodigoPeriodoIniUA(clienteUnidadAdministrativa
					.getCodigoPeriodoInicio());
		}

		// Recuperamos las direcciones del cliente
		boolean encontradoDireccionPrincipal = false;
		List listClienteDireccion = cliente.getListClienteDireccion();
		List listClienteDireccionAux = new ArrayList();
		if (listClienteDireccion.size() > 0) {
			// obtenemos la direccion principal del cliente
			ClienteDireccion clienteDireccion = (ClienteDireccion) cliente
					.getListClienteDireccion().get(0);
			ClienteDireccion clienteDireccionOpcional = null;
			ClienteDireccion clienteDireccionVacaciones = null;

			if (clienteDireccion.getIndicadorDireccionPrincipal().intValue() == 1) {
				encontradoDireccionPrincipal = true;
			} else {
				clienteDireccionOpcional = clienteDireccion;
			}

			if (encontradoDireccionPrincipal) {
				String codigoUnidadGeografica = reemplazarNulo(clienteDireccion
						.getCodigoUnidadGeografica());

				if (codigoUnidadGeografica.length() > 0) {
					f.setNivel1(codigoUnidadGeografica.substring(0, 6));

					if (codigoUnidadGeografica.length() > 6)
						f.setNivel2(f.getNivel1()
								+ codigoUnidadGeografica.substring(6, 12));
					if (codigoUnidadGeografica.length() > 12)
						f.setNivel3(f.getNivel2()
								+ codigoUnidadGeografica.substring(12, 18));
					if (codigoUnidadGeografica.length() > 18)
						f.setNivel4(f.getNivel3()
								+ codigoUnidadGeografica.substring(18, 24));
					if (codigoUnidadGeografica.length() > 24)
						f.setNivel5(f.getNivel4()
								+ codigoUnidadGeografica.substring(24, 30));
					if (codigoUnidadGeografica.length() > 30)
						f.setNivel6(f.getNivel5()
								+ codigoUnidadGeografica.substring(30, 36));

					f.setCodNivel1(f.getNivel1());
					f.setCodNivel2(f.getNivel2());
					f.setCodNivel3(f.getNivel3());
					f.setCodNivel4(f.getNivel4());
					f.setCodNivel5(f.getNivel5());
					f.setCodNivel6(f.getNivel6());
				}

				/* INI SA PER-SiCC-2012-0459 */
				String ubigeo1 = reemplazarNulo(clienteDireccion
						.getCodigoUbigeo1());
				String ciudad = reemplazarNulo(clienteDireccion
						.getCodigoCiudad());

				if (f.isMostrarCiudad()) {
					maeCiudadList = ajax.getCiudadesByRegion(f.getNivel1());
				}
				if (ciudad.length() > 0)
					f.setCodigoCiudad(ubigeo1 + "__" + ciudad);

				f.setVillaPoblacion(reemplazarNulo(clienteDireccion
						.getVillaPoblacion()));
				/* FIN SA PER-SiCC-2012-0459 */

				f.setTipoVia(clienteDireccion.getOidTipoVia().toString());
				f.setNumeroPrincipal(clienteDireccion.getNumeroPrincipal());
				f.setCorreVia(clienteDireccion.getCorreVia());
				f.setNombreVia(clienteDireccion.getNombreVia());
				f.setObservacionDireccion(clienteDireccion.getObservaciones());
				f.setBarrio(clienteDireccion.getBarrio());
				f.setCodigoPostal(clienteDireccion.getCodigoPostal());
				//INI ECU-SiCC-2015-0036
				f.setManzanaLetraDD(clienteDireccion.getValNomManzana());
				f.setBarrioDD(clienteDireccion.getValNomBarrio());
				f.setEtapaConjuntoDD(clienteDireccion.getValEtapaConjunto());
				f.setCallePrincipalDD(clienteDireccion.getValCallePrincipal());
				f.setCalleSecundariaDD(clienteDireccion.getValCalleSecundaria());
				//FIN ECU-SiCC-2015-0036
				
				f.setCodigoTerritorialCorrespondeDD(clienteDireccion.getValCodigoTerritorialCorresponde());
				
				listClienteDireccionAux.add(clienteDireccion);
			}

			if ((clienteDireccionOpcional == null)
					&& encontradoDireccionPrincipal
					&& (listClienteDireccion.size() > 1)) {
				ClienteDireccion aux = (ClienteDireccion) cliente
						.getListClienteDireccion().get(1);
				if (!aux.getCodigoTipoDireccion().equals(
						Constants.MAE_TIPO_DIRECCION_VACACIONES)) {
					clienteDireccionOpcional = aux;
				}
			}

			if (clienteDireccionOpcional != null) {
				String codigoUnidadGeograficaCT = reemplazarNulo(clienteDireccionOpcional
						.getCodigoUnidadGeografica());

				if (codigoUnidadGeograficaCT.length() > 0 && StringUtils.isNotBlank(codigoUnidadGeograficaCT)) {
					f.setNivel1CT(codigoUnidadGeograficaCT.substring(0, 6));
					// agregando ciudad en caso este habilitada
					if (f.isMostrarCiudad()) {
						maeCiudadCTList = ajax.getCiudadesByRegion(f
								.getNivel1CT());
					}

					if (codigoUnidadGeograficaCT.length() > 6)
						f.setNivel2CT(f.getNivel1CT()
								+ codigoUnidadGeograficaCT.substring(6, 12));
					if (codigoUnidadGeograficaCT.length() > 12)
						f.setNivel3CT(f.getNivel2CT()
								+ codigoUnidadGeograficaCT.substring(12, 18));
					if (codigoUnidadGeograficaCT.length() > 18)
						f.setNivel4CT(f.getNivel3CT()
								+ codigoUnidadGeograficaCT.substring(18, 24));
					if (codigoUnidadGeograficaCT.length() > 24)
						f.setNivel5CT(f.getNivel4CT()
								+ codigoUnidadGeograficaCT.substring(24, 30));
					if (codigoUnidadGeograficaCT.length() > 30)
						f.setNivel6CT(f.getNivel5CT()
								+ codigoUnidadGeograficaCT.substring(30, 36));
				}

				/* INI SA PER-SiCC-2012-0459 */
				String ubigeo1CT = reemplazarNulo(clienteDireccionOpcional
						.getCodigoUbigeo1());
				String ciudadCT = reemplazarNulo(clienteDireccionOpcional
						.getCodigoCiudad());

				if (ciudadCT.length() > 0)
					f.setCodigoCiudadCT(ubigeo1CT + "__" + ciudadCT);

				f.setVillaPoblacionCT(reemplazarNulo(clienteDireccionOpcional
						.getVillaPoblacion()));
				/* FIN SA PER-SiCC-2012-0459 */

				f.setTipoDireccionCT(clienteDireccionOpcional
						.getOidTipoDireccion().toString());
				f.setTipoViaCT(clienteDireccionOpcional.getOidTipoVia()
						.toString());
				f.setNumeroPrincipalCT(clienteDireccionOpcional
						.getNumeroPrincipal());
				f.setNombreViaCT(clienteDireccionOpcional.getNombreVia());
				f.setCorreViaCT(clienteDireccionOpcional.getCorreVia());
				f.setObservacionDireccionCT(clienteDireccionOpcional
						.getObservaciones());
				f.setBarrioCT(clienteDireccionOpcional.getBarrio());
				f.setCodigoPostalCT(clienteDireccionOpcional.getCodigoPostal());
				f.setTieneDireccionOpcional(true);
				this.mostrarBotonEliminarDireccionCT = true;
				
				//INI ECU-SiCC-2015-0036
				f.setManzanaLetraDE(clienteDireccionOpcional.getValNomManzana());
				f.setBarrioDE(clienteDireccionOpcional.getValNomBarrio());
				f.setEtapaConjuntoDE(clienteDireccionOpcional.getValEtapaConjunto());
				f.setCallePrincipalDE(clienteDireccionOpcional.getValCallePrincipal());
				f.setCalleSecundariaDE(clienteDireccionOpcional.getValCalleSecundaria());
				//FIN ECU-SiCC-2015-0036

				listClienteDireccionAux.add(clienteDireccionOpcional);
			}

			/* INI SA PER-SiCC-2012-0365 */
			if (listClienteDireccion.size() > 1) {
				ClienteDireccion aux = (ClienteDireccion) cliente
						.getListClienteDireccion().get(1);
				if (aux.getCodigoTipoDireccion().equals(
						Constants.MAE_TIPO_DIRECCION_VACACIONES)) {
					clienteDireccionVacaciones = aux;
				}
			}
			if (listClienteDireccion.size() > 2) {
				ClienteDireccion aux = (ClienteDireccion) cliente
						.getListClienteDireccion().get(2);
				if (aux.getCodigoTipoDireccion().equals(
						Constants.MAE_TIPO_DIRECCION_VACACIONES)) {
					clienteDireccionVacaciones = aux;
				}
			}
			if (clienteDireccionVacaciones != null) {
				f.setCodigoPeriodoInicio(reemplazarNulo(clienteDireccionVacaciones
						.getCodigoPeriodoInicio()));
				f.setCodigoPeriodoFin(reemplazarNulo(clienteDireccionVacaciones
						.getCodigoPeriodoFin()));

				String codigoUnidadGeograficaVacaciones = reemplazarNulo(clienteDireccionVacaciones
						.getCodigoUnidadGeografica());

				if (codigoUnidadGeograficaVacaciones.length() > 0) {
					f.setNivel1Vacaciones(codigoUnidadGeograficaVacaciones
							.substring(0, 6));

					if (f.isMostrarCiudad()) {
						maeCiudadVacList = ajax.getCiudadesByRegion(f
								.getNivel1Vacaciones());
					}
					if (codigoUnidadGeograficaVacaciones.length() > 6)
						f.setNivel2Vacaciones(f.getNivel1Vacaciones()
								+ codigoUnidadGeograficaVacaciones.substring(6,
										12));
					if (codigoUnidadGeograficaVacaciones.length() > 12)
						f.setNivel3Vacaciones(f.getNivel2Vacaciones()
								+ codigoUnidadGeograficaVacaciones.substring(
										12, 18));
					if (codigoUnidadGeograficaVacaciones.length() > 18)
						f.setNivel4Vacaciones(f.getNivel3Vacaciones()
								+ codigoUnidadGeograficaVacaciones.substring(
										18, 24));
					if (codigoUnidadGeograficaVacaciones.length() > 24)
						f.setNivel5Vacaciones(f.getNivel4Vacaciones()
								+ codigoUnidadGeograficaVacaciones.substring(
										24, 30));
					if (codigoUnidadGeograficaVacaciones.length() > 30)
						f.setNivel6Vacaciones(f.getNivel5Vacaciones()
								+ codigoUnidadGeograficaVacaciones.substring(
										30, 36));
				}

				String ubigeo1Vacaciones = reemplazarNulo(clienteDireccionVacaciones
						.getCodigoUbigeo1());
				String ciudadVacaciones = reemplazarNulo(clienteDireccionVacaciones
						.getCodigoCiudad());

				if (ciudadVacaciones.length() > 0)
					f.setCodigoCiudadVacaciones(ubigeo1Vacaciones + "__"
							+ ciudadVacaciones);

				f.setVillaPoblacionVacaciones(reemplazarNulo(clienteDireccionVacaciones
						.getVillaPoblacion()));

				f.setTipoViaVacaciones(clienteDireccionVacaciones
						.getOidTipoVia().toString());
				f.setNumeroPrincipalVacaciones(clienteDireccionVacaciones
						.getNumeroPrincipal());
				f.setNombreViaVacaciones(clienteDireccionVacaciones
						.getNombreVia());
				f.setCorreViaVacaciones(clienteDireccionVacaciones
						.getCorreVia());
				f.setObservacionDireccionVacaciones(clienteDireccionVacaciones
						.getObservaciones());
				f.setBarrioVacaciones(clienteDireccionVacaciones.getBarrio());
				f.setTieneDireccionVacaciones(true);

				f.setLineaDireccionVacaciones(this.obtenerLineaDireccion(
						clienteDireccionVacaciones, f));

				listClienteDireccionAux.add(clienteDireccionVacaciones);
			}
			/* FIN SA PER-SiCC-2012-0365 */

			cliente.setListClienteDireccion(listClienteDireccionAux);
		}
		f.setTieneDireccionPrincipal(encontradoDireccionPrincipal);

		// Recuperamos los telefonos y el mail
		Iterator itClienteComunicacion = cliente.getListClienteComunicacion()
				.iterator();
		while (itClienteComunicacion.hasNext()) {
			ClienteComunicacion clienteComunicacion = (ClienteComunicacion) itClienteComunicacion
					.next();

			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA)) {
				f.setTelefonoCasa(clienteComunicacion.getTextoComunicacion());
			}
			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					Constants.MAE_TIPO_COMUNICACION_TELEFONO_MOVIL)) {
				f.setTelefonoCelular(clienteComunicacion.getTextoComunicacion());
			}
			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					Constants.MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO)) {
				f.setTelefonoTrabajo(clienteComunicacion.getTextoComunicacion());
			}
			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					Constants.MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA)) {
				f.setTelefonoReferencia(clienteComunicacion
						.getTextoComunicacion());
			}
			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					Constants.MAE_TIPO_COMUNICACION_TELEFONO_ADICIONAL)) {
				f.setTelefonoAdicional(clienteComunicacion
						.getTextoComunicacion());
			}
			if (clienteComunicacion.getCodigoTipoComunicacion().equals(
					Constants.MAE_TIPO_COMUNICACION_MAIL)) {
				f.setMail(clienteComunicacion.getTextoComunicacion());
			}
			if (clienteComunicacion
					.getCodigoTipoComunicacion()
					.equals(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_ENTREGA)) {
				f.setTelefonoCasaDireccionEntrega(clienteComunicacion
						.getTextoComunicacion());
			}
			if (clienteComunicacion
					.getCodigoTipoComunicacion()
					.equals(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_ENTREGA)) {
				f.setTelefonoCelularDireccionEntrega(clienteComunicacion
						.getTextoComunicacion());
			}
			/* INI SA PER-SiCC-2012-0365 */
			if (clienteComunicacion
					.getCodigoTipoComunicacion()
					.equals(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_VACACIONES)) {
				f.setTelefonoCasaDireccionVacaciones(clienteComunicacion
						.getTextoComunicacion());
			}
			if (clienteComunicacion
					.getCodigoTipoComunicacion()
					.equals(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_VACACIONES)) {
				f.setTelefonoCelularDireccionVacaciones(clienteComunicacion
						.getTextoComunicacion());
			}
			/* FIN SA PER-SiCC-2012-0365 */
		}

		// Recuperamos el vinculo
		List listClienteVinculoAux = new ArrayList();
		Iterator itClienteVinculo = cliente.getListClienteVinculo().iterator();
		while (itClienteVinculo.hasNext()) {
			ClienteVinculo clienteVinculo = (ClienteVinculo) itClienteVinculo
					.next();

			if (clienteVinculo.getCodigoTipoVinculo().equals(
					Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA)) {
				f.setOidLiderVinculo(clienteVinculo.getOidClienteVinculante()
						.toString());
				f.setCodigoLiderVinculo(clienteVinculo
						.getCodigoClienteVinculante());

				if (clienteVinculo.getFechaDesde() != null)
					f.setFechaDesdeLider(sdf.format(clienteVinculo
							.getFechaDesde()));

				if (clienteVinculo.getFechaHasta() != null)
					f.setFechaHastaLider(sdf.format(clienteVinculo
							.getFechaHasta()));

				listClienteVinculoAux.add(clienteVinculo);
			}

			if ((f.isEsDuplaCyzone() && clienteVinculo.getCodigoTipoVinculo()
					.equals(Constants.MAE_TIPO_VINCULO_DUPLACYZONE))
					|| (f.isMostrarUnidadAdministrativa() && clienteVinculo
							.getCodigoTipoVinculo().equals(
									Constants.MAE_TIPO_VINCULO_RECOMENDANTE))) {
				// ES TIPO CLIENTE = CONSULTORA, HIJA DUPLA
				f.setOidConsultoraVinculo(clienteVinculo
						.getOidClienteVinculante().toString());
				f.setCodigoConsultoraVinculo(clienteVinculo
						.getCodigoClienteVinculante());
				f.setFechaDesde(sdf.format(clienteVinculo.getFechaDesde()));

				if (clienteVinculo.getFechaHasta() != null) {
					f.setFechaHasta(sdf.format(clienteVinculo.getFechaHasta()));

					if (f.isEsDuplaCyzone()) {
						f.setEsClienteCaducado(true);
					}
				}

				if (clienteVinculo.getCodigoTipoVinculo().equals(
						Constants.MAE_TIPO_VINCULO_RECOMENDANTE)) {
					f.setCodigoConsultoraRecomendante(f
							.getCodigoConsultoraVinculo());
					clienteVinculo
							.setOidClienteVinculanteAnterior(clienteVinculo
									.getOidClienteVinculante());
				}

				listClienteVinculoAux.add(clienteVinculo);
				// break;
			}
		}
		cliente.setListClienteVinculo(listClienteVinculoAux);

		f.setIndicadoObligatorioNacionalidad(false);
		f.setIndicadoObligatorioGradoInstruccion(false);
		f.setIndicadoObligatorioTratamiento(false);

		f.setControlFoco(MAE_CONTROL_APELLIDOPATERNO);

		f.setIndicadorTiposCliente("0");
		f.setIndicadorDireccionDomicilio("0");
		f.setIndicadorDireccionOpcional("0");
		f.setIndicadorConsultoraVinculo("0");

		// setamos las referencias
		String codigoCliente = f.getCodigoCliente();// obtenemos el cod cliente
		ClienteReferencias clienteReferencias = cliente.getClienteReferencias();
		log.debug("clienteReferencias " + clienteReferencias);
		BeanUtils.copyProperties(f, clienteReferencias);
		f.setCodigoCliente(codigoCliente);

		// Obtenemos los datos iniciales del Cliente
		ClienteHistoricoDatos clienteHistoricoDatos = new ClienteHistoricoDatos();
		clienteHistoricoDatos.setCodigoCliente(f.getCodigoCliente());
		clienteHistoricoDatos.setCodigoPais(f.getCodigoPais());
		clienteHistoricoDatos.setPrimerApellidoAnterior(f.getApellidoPaterno());
		clienteHistoricoDatos
				.setSegundoApellidoAnterior(f.getApellidoMaterno());
		clienteHistoricoDatos.setPrimerNombreAnterior(f.getNombre1());
		clienteHistoricoDatos.setSegundoNombreAnterior(f.getNombre2());
		clienteHistoricoDatos
				.setFechaNacimientoAnterior(f.getFechaNacimiento());
		clienteHistoricoDatos.setNumeroDocumentoAnterior(f
				.getNumeroDocumentoIdentidad());

		clienteHistoricoDatos
				.setTelefonoCelularAnterior(f.getTelefonoCelular());
		clienteHistoricoDatos.setTelefonoFijoAnterior(f.getTelefonoCasa());
		clienteHistoricoDatos.setEmailAnterior(f.getMail());

		if (StringUtils.isNotEmpty(f.getNivel6()))
			clienteHistoricoDatos.setUbigeoAnterior(f.getNivel6());
		if (StringUtils.isNotEmpty(f.getNivel5()))
			clienteHistoricoDatos.setUbigeoAnterior(f.getNivel5());
		if (StringUtils.isNotEmpty(f.getNivel4()))
			clienteHistoricoDatos.setUbigeoAnterior(f.getNivel4());
		if (StringUtils.isNotEmpty(f.getNivel3()))
			clienteHistoricoDatos.setUbigeoAnterior(f.getNivel3());

		clienteHistoricoDatos.setTipoViaAnterior(f.getTipoVia());
		clienteHistoricoDatos
				.setNumeroPrincipalAnterior(f.getNumeroPrincipal());
		clienteHistoricoDatos.setBarrioAnterior(f.getBarrio());
		clienteHistoricoDatos.setDireccionAnterior(f.getNombreVia());
		clienteHistoricoDatos
				.setReferenciaAnterior(f.getObservacionDireccion());

		clienteHistoricoDatos.setZonaAnterior(f.getCodigoZona());
		clienteHistoricoDatos.setTerritorioAnterior(f.getCodigoTerritorio());
		clienteHistoricoDatos.setRegionAnterior(f.getCodigoRegion());
		clienteHistoricoDatos.setSeccionAnterior(f.getCodigoSeccion());
		clienteHistoricoDatos.setIndicadorOrigen("CM");
		cliente.setClienteHistoricoDatos(clienteHistoricoDatos);
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("edit");
		this.salirGrabarPantallaPadre = false;
		MantenimientoMAEModificacionClienteForm f = new MantenimientoMAEModificacionClienteForm();
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		// InterfazSiCCService service = (InterfazSiCCService)
		// getBean("sisicc.interfazSiCCService");

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		//INI CHI-SiCC-2015-0052 - Mostramos el sub tipo documento
		Map criteriaSubTipoDocumento = new HashMap();
		criteriaSubTipoDocumento.put("tipoValidacion", Constants.MAE_SUBTIPO_DOCUMENTO_CONSULTORA_TIPO_VALIDACION);
		criteriaSubTipoDocumento.put("moduloValidacion", Constants.MAE_SUBTIPO_DOCUMENTO_CONSULTORA_MODULO_VALIDACION);
		criteriaSubTipoDocumento.put("indicadorEstado", Constants.NUMERO_UNO);
		criteriaSubTipoDocumento.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		f.setMostrarSubTipoDocumento(clienteService.getMostrarSubTipoDocumentoIdentidad(criteriaSubTipoDocumento));
		//FIN CHI-SiCC-2015-0052
		
		// INI PER-SiCC-2015-0589  - Mostrar Calcular percepcion
		
		ParametroPais paramPaisCalcPercep = new ParametroPais();

		paramPaisCalcPercep.setCodigoPais(mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		paramPaisCalcPercep.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPaisCalcPercep.setNombreParametro(Constants.MAE_NOMB_PARAM_CALC_PERCEP);
		paramPaisCalcPercep.setValorParametro(Constants.NUMERO_UNO);
		paramPaisCalcPercep.setIndicadorActivo(Constants.NUMERO_UNO);
		
		List lstParametrosCalcPercep = genericoService.getParametrosPais(paramPaisCalcPercep);

		if (lstParametrosCalcPercep != null && lstParametrosCalcPercep.size() > 0) {
			f.setIndicadorCalcPercep(true);
		} else {
			f.setIndicadorCalcPercep(false);
		}
		// FIN PER-SiCC-2015-0589 		

		
		

		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);
		criteria.put("oidPais", oidPais);

		/* INI SA PER-SiCC-2012-0365 */
		// RECUPERAMOS EL PERIODO DE PROCESO
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga Periodo Proceso
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		/* FIN SA PER-SiCC-2012-0365 */

		// recuperamos la longitud del codigo de cliente para el pais logueado
		f.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
		criteria.put("longitudCodigoCliente",
				clienteService.getLongitudCodigoCliente(criteria));

		LabelValue[] periodos = clienteService
				.getPeriodosVigentesByPaisMarcaCanal(criteria);
		List subTiposCliente = clienteService
				.getSubTiposClienteInsertar(criteria);
		List nivelesGeograficos = clienteService
				.getNivelesGeograficos(criteria);

		// seteamos los niveles geograficos del pais
		if (nivelesGeograficos != null && nivelesGeograficos.size() > 0) {
			f.setTotalNiveles(String.valueOf(nivelesGeograficos.size()));

			for (int i = 0; i < nivelesGeograficos.size(); i++) {
				Base nivel = (Base) nivelesGeograficos.get(i);

				if (i == 0)
					f.setDescripcionNivel1(nivel.getDescripcion());
				if (i == 1)
					f.setDescripcionNivel2(nivel.getDescripcion());
				if (i == 2)
					f.setDescripcionNivel3(nivel.getDescripcion());
				if (i == 3)
					f.setDescripcionNivel4(nivel.getDescripcion());
				if (i == 4)
					f.setDescripcionNivel5(nivel.getDescripcion());
				if (i == 5)
					f.setDescripcionNivel6(nivel.getDescripcion());
			}
		}

		// seteamos el periodo actual
		if (periodos != null && periodos.length > 0) {
			LabelValue base = periodos[0];
			f.setOidPeriodo(base.getValue());
		}
		// seteamos al primer tipo/subtipo cliente encontrado
		if (subTiposCliente != null && subTiposCliente.size() > 0) {
			Base base = (Base) subTiposCliente.get(0);
			f.setSubTipoCliente(base.getCodigo());
		}

		// seteamos la edad minima y maxima
		f.setEdadMinima(clienteService.getEdadMinima(criteria));
		f.setEdadMaxima(clienteService.getEdadMaxima(criteria));

		// VERIFICAMOS SI LA GENERACION DEL CODIGO DE CLIENTE ES AUTOMATICA O
		// MANUAL
		boolean esCodigoClienteAutomatico = clienteService
				.isCodigoClienteAutomatico(criteria);
		f.setEsCodigoClienteAutomatico(esCodigoClienteAutomatico);

		// request.getSession().setAttribute("codigoIdiomaISO",
		// getUsuario(session).getIdioma().getCodigoISO() );

		// configuramos la lista de periodos, subtipo de clientes

		siccPeriodoList = periodos;
		List listPeriodosContacto = clienteService
				.getPeriodosByPaisMarcaCanal(criteria);
		siccPeriodoInicialList = listPeriodosContacto;
		siccSubTipoClienteList = subTiposCliente;
		maeClienteEstadoCivilList = clienteService.getEstadosCiviles(criteria);
		maeClienteTratamientoList = clienteService.getTratamientos(criteria);
		maeClienteSexoList = clienteService.getSexos(criteria);
		maeClienteTipoViaList = clienteService.getTiposVias(criteria);
		maeClienteNivel1List = ajaxService.getUnidadesGeograficas(oidPais, "");
		maeClienteNivel1AvalList = ajaxService.getUnidadesGeograficas(oidPais,
				"");
		maeClienteNivelEstudioList = clienteService.getNivelEstudios(criteria);
		maeClienteNacionalidadList = clienteService.getNacionalidades(criteria);
		maeClienteTipoDireccionList = clienteService
				.getTiposDireccion(criteria);
		maeTipoCutisList = clienteService.getTipoCutis(criteria);
		maeOtrasMarcasList = clienteService.getOtrasMarcas(criteria);
		
		maeClienteCodigoTerritorialCorrespondeList = clienteService.getCodigosTerritorialCorresponde(criteria);

		consultaMaeClienteList.clear();

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		f.setCodigoPeriodoInicioVacaciones(clienteService
				.getPeriodoInicioVacaciones(criteria));
		f.setCodigoPeriodoFinVacaciones(clienteService
				.getPeriodoFinVacaciones(criteria));
		/* FIN SA PER-SiCC-2012-0365 */

		// recuperamos el cliente a modificar

		// REVISAR ESTA PARTE,, funciona bien
		Map f2 = (HashMap) beanRegistroSeleccionado;
		
		//Condicion para la subconsulta
		String id="";
		if(this.subConsulta)
			id=this.codigoConsultora;
		else
			id = f2.get("codigo").toString();

		
		// limpiamos los datos de pantalla
		limpiar(f);

		// seteamos datos de tipoVia, NumeroPrincipal, ubigeo.
		setearCamposModuloPais(f, clienteService);

		// obtenemos los datos del Cliente a Modificar
		ParametroPais paramPais01 = new ParametroPais();
		paramPais01.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		paramPais01.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais01.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
		paramPais01.setValorParametro(Constants.NUMERO_UNO);

		List listParametros01 = genericoService.getParametrosPais(paramPais01);
		f.setIndicadorCamposAdicionales(false);
		if (listParametros01 != null && listParametros01.size() > 0) {
			f.setIndicadorCamposAdicionales(true);
		}

		Cliente cliente = obtenerDatosCliente(f, f.getCodigoPais(), id,
				clienteService, subTiposCliente);

		// seteamos los datos del formulario con los datos a modificar del
		// Cliente
		initCabecera(f, cliente);
		
		//seteamos las listas de subtipodocumentos.
		if(f.isMostrarSubTipoDocumento()){
			Map criteriaSubTipoDoc = new HashMap();
			criteriaSubTipoDoc.put("oidTipoDocumento", f.getTipoDocumentoIdentidad());
			siccSubTipoDocumentoList = clienteService.getSubTiposDocumentoIdentidad(criteriaSubTipoDoc);
			
			criteriaSubTipoDoc.put("oidTipoDocumento", f.getTipoDocumentoIdentidad2());
			siccSubTipoDocumentoList2 = clienteService.getSubTiposDocumentoIdentidad(criteriaSubTipoDoc);
		}

		// seteamos las longitudes de los documentos de identidad del cliente
		f.setLongitudTipoDocumento(ajaxService.getLongitudTipoDocumento(
				f.getOidPais(), f.getTipoDocumentoIdentidad()));
		if (!f.getTipoDocumentoIdentidad2().equals(""))
			f.setLongitudTipoDocumento2(ajaxService.getLongitudTipoDocumento(
					f.getOidPais(), f.getTipoDocumentoIdentidad2()));
		if (!f.getTipoDocumentoIdentidad3().equals(""))
			f.setLongitudTipoDocumento3(ajaxService.getLongitudTipoDocumento(
					f.getOidPais(), f.getTipoDocumentoIdentidad3()));

		// recuperamos el nombre del cliente recomendante / madre dupla
		if (f.getCodigoConsultoraVinculo().length() > 0) {
			String datosCliente = ajaxService.getExisteCodigoCliente(
					f.getOidPais(), f.getCodigoConsultoraVinculo());
			StringTokenizer st = new StringTokenizer(datosCliente, "|");
			st.nextToken();
			f.setNombreConsultoraVinculo(st.nextToken());
		}

		// recuperamos el nombre del cliente lider
		if (f.getCodigoLiderVinculo().length() > 0) {
			String datosCliente = ajaxService.getExisteCodigoCliente(
					f.getOidPais(), f.getCodigoLiderVinculo());
			StringTokenizer st = new StringTokenizer(datosCliente, "|");
			st.nextToken();
			f.setNombreLiderVinculo(st.nextToken());
		}

		// a ctualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		// actualizamos la lista d eubigeo aval
		actualizarInicialListaUbigeoAval(f);

		// Colocamos los campos obligatorios de acuerdo al subTipoCliente
		if (cliente.getListClienteSubTipo().size() > 0) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) cliente
					.getListClienteSubTipo().get(0);

			setearValorPorDefectoPorSubTipoCliente(clienteService, f,
					clienteSubTipo.getOidSubTipoCliente().toString(), true);
		}

		// eliminamos el item DuplaCyzone o lo dejamos solo a el
		if (f.isEsDuplaCyzone()) {
			for (int i = 0; i < subTiposCliente.size(); i++) {
				Base base = (Base) subTiposCliente.get(i);

				StringTokenizer st = new StringTokenizer(base.getCodigo(), "-");
				String codigoTipoCliente = st.nextToken();

				if (codigoTipoCliente
						.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
					subTiposCliente = new ArrayList();
					subTiposCliente.add(base);
					break;
				}
			}

			f.setNivel1("-");
			f.setNivel2("-");
			f.setNivel3("-");
		} else {
			for (int i = 0; i < subTiposCliente.size(); i++) {
				Base base = (Base) subTiposCliente.get(i);

				StringTokenizer st = new StringTokenizer(base.getCodigo(), "-");
				String codigoTipoCliente = st.nextToken();

				if (codigoTipoCliente
						.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
					subTiposCliente.remove(i);
					break;
				}
			}
		}

		if (!f.isMostrarUnidadAdministrativa()) {
			f.setCodigoZona("-");
			f.setCodigoTerritorio("-");
			f.setTipoVia("-");
		}

		// Seteamos el objeto que contiene los datos del cliente en Sesion

		// IMPORTANTE
		maeDtoCliente = cliente;

		// verificamos si tiene pedido facturado
		Map criterioPedido = new HashMap();
		criterioPedido.put("oidPais", f.getOidPais());
		criterioPedido.put("oidCliente", cliente.getOid().toString());
		criterioPedido.put("periodo", new String[0]);
		f.setTienePedidoFacturado(clienteService
				.esClienteHaFacturadoPeriodos(criterioPedido)); //

		// validamos si se puede modificar identificacion principal
		f.setModificarIdentificacionPrincipal(true);
		if (!f.isEsCodigoClienteAutomatico()) {
			f.setModificarIdentificacionPrincipal(false);

			if (f.getCodigoEstatus() != null
					&& f.getCodigoEstatus().equals(
							Constants.MAE_ESTADO_REGISTRADA)) {
				if (!f.isTienePedidoFacturado()) {
					f.setModificarIdentificacionPrincipal(true);
				}
			}
		}

		// validamos si puede redefinir periodo de ingreso
		if (f.getCodigoEstatus() != null
				&& f.getCodigoEstatus().equals(Constants.MAE_ESTADO_REGISTRADA)) {
			f.setMostrarRedefinirPeriodo(true);
		}
		if (f.getCodigoEstatus() != null
				&& f.getCodigoEstatus().equals(Constants.MAE_ESTADO_RETIRADA)) {
			boolean pasoPedido = clienteService
					.esClienteHaFacturadoPeriodos(criteria);

			if (!pasoPedido)
				f.setMostrarRedefinirPeriodo(true);
		}

		// actualizamos la lista de periodos vigentes
		actualizarPeriodoVigente(f, clienteService, listPeriodosContacto,
				periodos);

		// Verificamos si tiene pedido facturado en los periodos vigentes
		LabelValue[] periodosVig = siccPeriodoList;
		String[] periodoVig = new String[periodosVig.length];
		for (int i = 0; i < periodosVig.length; i++) {
			periodoVig[i] = periodosVig[i].getValue();
		}
		criterioPedido.put("periodo", periodoVig);
		criterioPedido.put("listPeriodos", periodoVig);
		f.setTienePedidoFacturadoVigente(clienteService
				.esClienteHaFacturadoPeriodos(criterioPedido));

		// validamos si se puede modificar la seccion de Vinculos
		validacionModificacionRecomendante(f, clienteService, cliente,
				listPeriodosContacto);

		// mostramos mensaje en pantalla si el cliente Hija Dupla ha caducado
		if (f.isEsClienteCaducado()) {
			addInfo("Mensaje",
					this.getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.clienteCaducado"));

		}

		// Verificamos si para el pais se tiene que mostrar el digito de control
		String mostrarDigitoControl = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_MOSTRAR_DIGITO_CONTROL);
		if (mostrarDigitoControl != null) {
			f.setMostrarDigitoControl(true);
		} else {
			f.setMostrarDigitoControl(false);
		}

		// Verificamos si tiene Direccion de Entrega
		if (!f.isTieneDireccionOpcional()) {
			f.setPrimeraVezDespliegueDireccionEntrega(true);
		}

		// Verificamos si tiene ya la referencia: tipo vinculo Aval
		if ((f.getCodigoTipoVinculoAval() == null)
				|| (f.getCodigoTipoVinculoAval().equals(""))) {
			f.setPrimeraVezDespliegueReferencias(true);
		}

		// sbuchelli carga los tipos de vinculos del ssicc

		maeClienteTipoVinculoList = clienteService.getTipoVinculo();

		// setea el tipo de documento por defaul al aval
		// f.setOidTipoDocumentoAval(clienteService.getTipoDocumentoObligatorio(f.getOidPais()));

		// actualizamos los tipos de documento
		actualizarListaTiposDocumento(f, clienteService, f.isEsDuplaCyzone());
		// limpiamos la lista de deudoras

		// En Revision
		/*
		 * session.removeAttribute(MAE_DEUDORAS_LIST);
		 * session.removeAttribute(MAE_FLAG_DEUDORAS);
		 */
		// validacion Grupo Direcciones
		validarGrupoDirecciones(f, clienteService, cliente, periodos);

		// validamos si es cliente potencial/aval
		if (cliente.getOidSubTipoAval() != null)
			f.setEsClientePotencialAval(true);

		// en caso que no haya cruces, y sea un registrada o retirada, y no paso
		// pedidos en la campaña vigente
		if (f.getCodigoEstatus() != null
				&& ((f.getCodigoEstatus()
						.equals(Constants.MAE_ESTADO_REGISTRADA)) || (f
						.getCodigoEstatus()
						.equals(Constants.MAE_ESTADO_RETIRADA)))) {

			actualizarPeriodoVigenteSinCruce(f, clienteService);
		}

		/* INI SA PER-SiCC-2012-0365 */
		if (!f.isTieneDireccionVacaciones()) {
			f.setPrimeraVezDespliegueDireccionVacaciones(true);
		}

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("oidCliente", cliente.getOid().toString());
		f.setTienePedidoFacturadoProceso(clienteService
				.tienePedidoFacturado(criteria));

		f.setTienePedidoEnProcesoFacturacion(clienteService
				.tienePedidoEnProcesoFacturacion(criteria));
		/* FIN SA PER-SiCC-2012-0365 */

		/* INI JV CHI-SiCC-2012-0003 */
		ParametroPais paramPais = new ParametroPais();

		paramPais.setCodigoPais(f.getCodigoPais());
		paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais.setNombreParametro(Constants.MAE_NOMB_PARAM_OTROS);
		paramPais.setValorParametro(Constants.NUMERO_UNO);

		List lstParametros = genericoService.getParametrosPais(paramPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			f.setIndicadorSeccionOtros(true);
		} else {
			f.setIndicadorSeccionOtros(false);
		}

		paramPais.setNombreParametro(Constants.MAE_NOMB_PARAM);
		List lstParametrosCompromiso = genericoService
				.getParametrosPais(paramPais);

		if (lstParametrosCompromiso != null
				&& lstParametrosCompromiso.size() > 0) {
			f.setIndicadorSeccionCompromiso(true);
		} else {
			f.setIndicadorSeccionCompromiso(false);
		}

		/* FIN JV CHI-SiCC-2012-0003 */

		/* INI SA PER-SiCC-2013-0147 */
		paramPais.setNombreParametro(Constants.MAE_NOMB_PARAM_PERIODO_VIGENTE);
		List lstParametrosPeriodoVigente = genericoService
				.getParametrosPais(paramPais);

		if (lstParametrosPeriodoVigente != null
				&& lstParametrosPeriodoVigente.size() > 0) {
			f.setPermitirModificarPeriodoVigente(true);
		} else {
			f.setPermitirModificarPeriodoVigente(false);
		}

		/* FIN SA PER-SiCC-2013-0147 */

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		parametroPais.setNombreParametro(Constants.MAE_IND_BUSCAR_DIRECCION);
		parametroPais.setIndicadorActivo(Constants.NUMERO_UNO);
		List parametros = genericoService.getParametrosPais(parametroPais);

		f.setMostrarBuscarDireccion(false);
		ParametroPais parametro = null;
		if (parametros.size() == 1) {
			parametro = (ParametroPais) parametros.get(0);
			if (parametro.getValorParametro().equals(Constants.NUMERO_UNO)) {
				f.setMostrarBuscarDireccion(true);
			}
		}

		// Validamos si se muestra el boton de Redefinir Vigencia UA
		if (f.getCodigoEstatus() != null
				&& !((f.getCodigoEstatus()
						.equals(Constants.MAE_ESTADO_REGISTRADA)) || (f
						.getCodigoEstatus()
						.equals(Constants.MAE_ESTADO_RETIRADA)))) {

			Map criteriaFact = new HashMap();
			criteriaFact.put("codigoPeriodo", f.getCodigoPeriodo());
			criteriaFact.put("oidCliente", cliente.getOid().toString());
			boolean tienePedidoFacturadoPeriodoProceso = clienteService
					.esClienteHaFacturadoPeriodos(criteriaFact);

			if (!tienePedidoFacturadoPeriodoProceso
					&& f.getCodigoPeriodo()
							.compareTo(f.getCodigoPeriodoIniUA()) < 0) {
				f.setMostrarBotonRedifinirVigenciaUA(true);
			}
		}
		/* INI PER-SiCC-2014-0162 */
		ParametroPais paramPaisFactElect = new ParametroPais();

		paramPaisFactElect.setCodigoPais(mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		paramPaisFactElect.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPaisFactElect
				.setNombreParametro(Constants.MAE_NOMB_PARAM_FACT_ELECT);
		paramPaisFactElect.setValorParametro(Constants.NUMERO_UNO);
		paramPaisFactElect.setIndicadorActivo(Constants.NUMERO_UNO);

		List lstParametrosFactElec = genericoService
				.getParametrosPais(paramPaisFactElect);

		if (lstParametrosFactElec != null && lstParametrosFactElec.size() > 0) {
			f.setIndicadorFactElect(true);
		} else {
			f.setIndicadorFactElect(false);
		}

		// FACTURACION ELECTRONICA CHECKBOX

		if ("0".equals(f.getValorIndicadorFactElect())) {
			f.setValorIndicadorFactElectB(true);
		} else {
			f.setValorIndicadorFactElectB(false);
		}

		if ("0".equals(f.getValorIndicadorFactElectAux())) {
			f.setValorIndicadorFactElectAuxB(true);
		} else {
			f.setValorIndicadorFactElectAuxB(false);
		}

		if (cliente.getClienteAdicional().getIndImprDocu() != null)
			f.setValorIndicadorFactElect(cliente.getClienteAdicional()
					.getIndImprDocu());
		/* FIN PER-SiCC-2014-0162 */

		/* INI PER-SiCC-2015-0007 */
		ParametroPais paramPaisDocFis = new ParametroPais();

		paramPaisDocFis.setCodigoPais(mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		paramPaisDocFis.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPaisDocFis.setNombreParametro(Constants.MAE_NOMB_PARAM_DOC_FISCAL);
		paramPaisDocFis.setValorParametro(Constants.NUMERO_UNO);
		paramPaisDocFis.setIndicadorActivo(Constants.NUMERO_UNO);

		List lstParametrosDocFis = genericoService
				.getParametrosPais(paramPaisDocFis);

		if (lstParametrosDocFis != null && lstParametrosDocFis.size() > 0) {
			f.setIndicadorDocFiscalAux(true);
		} else {
			f.setIndicadorDocFiscalAux(false);
		}
		/* FIN PER-SiCC-2015-0007 */
		
		//INI ECU-SiCC-2015-0036
				paramPais = new ParametroPais();
				paramPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
				paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
				paramPais.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
				paramPais.setValorParametro(Constants.NUMERO_UNO);

				List listParametros = genericoService.getParametrosPais(paramPais);

				if (listParametros != null && listParametros.size() > 0) {
					f.setIndicadorCamposAdicionales(true);
					maeTipoPersonaList = clienteService.getTipoPersona(criteria);
					maeOrigenIngresoList = clienteService.getOrigenIngreso(criteria);

				} else {
					f.setIndicadorCamposAdicionales(false);
				}
		//FIN ECU-SiCC-2015-0036

		
		// Seteamos edad Minimo y Edad Maxima para tipo Cliente: Hija Dupla o
		// Consultora
		if (cliente.getListClienteSubTipo().size() > 0) {
			Iterator itSubtipos = cliente.getListClienteSubTipo().iterator();
			while (itSubtipos.hasNext()) {
				ClienteSubTipo clienteSubTipo = (ClienteSubTipo) itSubtipos
						.next();

				if (clienteSubTipo.getCodigoTipoCliente().equals(
						Constants.MAE_TIPO_CLIENTE_HIJADUPLA)
						|| clienteSubTipo.getCodigoTipoCliente().equals(
								Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {

					criteria.put("oidPais", f.getOidPais());
					criteria.put("oidSubTipoCliente",
							clienteSubTipo.getOidSubTipoCliente());

					// seteamos la edad minima y maxima
					f.setEdadMinima(clienteService.getEdadMinima(criteria));
					f.setEdadMaxima(clienteService.getEdadMaxima(criteria));
				}
			}
		}

		if (f.getIndDocumentoPrincipal() == null) {
			f.setIndDocumentoPrincipal("1");
		} else {
			f.setIndDocumentoPrincipal("1");
		}
		if (!f.getFechaNacimiento().equals("")) {
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			try {
				f.setFechaNacimientoD(formatoDeFecha.parse(f
						.getFechaNacimiento()));
				Calendar cal = Calendar.getInstance();
				cal.setTime(f.getFechaNacimientoD());
				int anioNac = cal.get(Calendar.YEAR);
				cal.setTime(new Date());
				int anioActual = cal.get(Calendar.YEAR);
				if (anioActual - anioNac > 0)
					f.setEdad(String.valueOf(CalcuEdad(f.getFechaNacimiento())));
			} catch (Exception ex) {
				addInfo("s", ex.getMessage());
			}

		}
		// habilitamos o desabilitamos el documento fiscal

		if (f.getTipoDocumentoIdentidad().equals("2011")) {
			this.flagMostrarDocumentoFiscal = true;
			if (f.getIndicadorDocFiscal().equals("S")) {
				f.setIndicadorDocFiscalB(true);
				f.setIndicadorDocFiscalAux(true);
			} else {
				f.setIndicadorDocFiscalB(false);
				f.setIndicadorDocFiscalAux(false);
			}
		} else
			this.flagMostrarDocumentoFiscal = false;
		if (f.getFechaDesde() != null) {
			f.setFechaDesdeD(DateUtil.convertStringToDate(f.getFechaDesde()));
		}
		if (f.getFechaHasta() != null) {
			f.setFechaHastaD(DateUtil.convertStringToDate(f.getFechaHasta()));
		}
		
		if (StringUtils.isNotEmpty(f.getFechaDesdeLider())) {
			f.setFechaDesdeLiderD(DateUtil.convertStringToDate(f.getFechaDesdeLider()));
		}
		if (StringUtils.isNotEmpty(f.getFechaHastaLider())) {
			f.setFechaHastaLiderD(DateUtil.convertStringToDate(f.getFechaHastaLider()));
		}
		
		//INICIO PER-SiCC-2015-0462		
		String valor=clienteService.getMostrarBancoCuentaCorriente(criteria);		
		if(StringUtils.equals(valor, "1")){
			this.showBancoCuenta = true;
			Map criteriaBanco = new HashMap();		
			criteriaBanco.put("codigoPais", f.getCodigoPais());
			this.maeBancoList = clienteService.getBancos(criteriaBanco);
		}else
			this.showBancoCuenta=false;
		//FIN PER-SiCC-2015-0462
		
		//INICIO PER-SiCC-2015-0662
		String valorTCCC = clienteService.getMostrarTipoCuentaCuentaCorriente(criteria);
		if(StringUtils.equals(valorTCCC, "1")){
			this.showTipoCuentaCuentaCorriente = true;
			Map criteriaTCCC = new HashMap();
			this.maeTipoCuentaList = clienteService.getTiposCuenta(criteriaTCCC);
		}else
			this.showTipoCuentaCuentaCorriente = false;
		//FIN PER-SiCC-2015-0662
		
		f = validarDireccionCT(f);

		f = validarDireccionAval(f);

		return f;
	}

	public MantenimientoMAEModificacionClienteForm validarDireccionAval(
			MantenimientoMAEModificacionClienteForm f) {

		if (primeraVezDespliegueReferencias) {

			f = desplegarDireccionAval(f);

		}
		return f;

	}

	private MantenimientoMAEModificacionClienteForm desplegarDireccionAval(
			MantenimientoMAEModificacionClienteForm f) {

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		if (primeraVezDespliegueReferencias) {

			if (f.getNivel1() != null && f.getNivel1().length() > 0) {
				f.setCodigoDepartamentoAval(f.getNivel1());
				maeClienteNivel2AvalList = ajaxService.getUnidadesGeograficas(f.getOidPais(), f.getCodigoDepartamentoAval());
			}

			if (f.getNivel2() != null && f.getNivel2() != null) {
				f.setCodigoProvinciaAval(f.getNivel2());
				if (f.getNivel2().length() > 0) {
					maeClienteNivel3AvalList = ajaxService.getUnidadesGeograficas(f.getOidPais(),f.getNivel2());
				}
			}
			if (f.getNivel3() != null)
				f.setCodigoDistritoAval(f.getNivel3());

		}
		primeraVezDespliegueReferencias = false;
		return f;
	}

	public MantenimientoMAEModificacionClienteForm validarDireccionCT(
			MantenimientoMAEModificacionClienteForm f) {

		boolean remplazarCT = false;

		if (f.isMostrarDireccion()) {
			if (f.isMostrarTipoVia() && f.getTipoViaCT().equals("")) {
				remplazarCT = true;
			}
			if (f.isMostrarNumeroPrincipal() && f.getNumeroPrincipal() != null
					&& !f.getNumeroPrincipal().equals("")) {
				remplazarCT = true;
			}
			if (f.getNombreViaCT() != null
					&& f.getObservacionDireccionCT() != null
					&& !f.getNombreViaCT().equals("")
					&& !f.getObservacionDireccionCT().equals("")) {
				remplazarCT = true;
			}

		}

		if (remplazarCT) {
			if (f.getNivel1CT() != null && f.getNivel2CT() != null
					&& f.getNivel3CT() != null && f.getNivel1CT().equals("")
					|| f.getNivel2CT().equals("") || f.getNivel3CT().equals("")) {
				f = desplegarDireccionCT(f);
			}
			if (f.isMostrarTipoVia() || f.getTipoViaCT().equals("")) {
				f = desplegarDireccionCT(f);
			}
			if (f.getNombreViaCT() == null
					|| f.getObservacionDireccionCT() == null
					|| f.getNombreViaCT().equals("")
					|| f.getObservacionDireccionCT().equals("")) {
				f = desplegarDireccionCT(f);
			}
			if (f.isMostrarCiudad()) {
				if (f.getCodigoCiudadCT() == null
						|| f.getCodigoCiudadCT().equals("")) {
					f = desplegarDireccionCT(f);
				}
			}
		}
		return f;

	}

	private MantenimientoMAEModificacionClienteForm desplegarDireccionCT(
			MantenimientoMAEModificacionClienteForm f) {

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		if (primeraVezDespliegueDireccionEntrega) {
			f.setNivel1CT(f.getNivel1());

			if (f.getNivel1().length() > 0) {

				maeClienteNivel2CTList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel1());
			}

			f.setNivel2CT(f.getNivel2());

			if (f.getNivel2() != null) {
				if (f.getNivel2().length() > 0) {
					maeClienteNivel3CTList = ajaxService
							.getUnidadesGeograficas(f.getOidPais(),
									f.getNivel2());
				}
			}

			f.setNivel3CT(f.getNivel3());

			if (f.getNivel3() != null) {
				if (f.getNivel3().length() > 0) {
					maeClienteNivel4CTList = ajaxService
							.getUnidadesGeograficas(f.getOidPais(),
									f.getNivel3());
				}
			}

			if (f.getNivel4() != null) {
				f.setNivel4CT(f.getNivel4());
			}

			if (f.getNivel5() != null) {
				f.setNivel5CT(f.getNivel4());
			}
		}
		primeraVezDespliegueDireccionEntrega = false;
		return f;
	}

	public int CalcuEdad(String fecha_nac) { // fecha_nac debe tener el formato
		// dd/MM/yyyy

		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaActual);
		String[] dat1 = fecha_nac.split("/");
		String[] dat2 = hoy.split("/");
		int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
		int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		if (mes < 0) {
			anos = anos - 1;
		} else if (mes == 0) {
			int dia = Integer.parseInt(dat1[0]) - Integer.parseInt(dat2[0]);
			if (dia > 0) {
				anos = anos - 1;
			}
		}
		return anos;
	}

	private void actualizarListaTiposDocumento(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService, boolean esDuplaCyzone) {
		Map criteriaDoc = new HashMap();
		criteriaDoc.put("oidPais", f.getOidPais());

		if (f.isEsDuplaCyzone())
			criteriaDoc.put("siglaDocumento", "DCZ");
		else
			criteriaDoc.put("indicadorDocFiscal", "1");

		siccTipoDocumentoList = clienteService
				.getTiposDocumentoIdentidad(criteriaDoc);

	}

	private Cliente obtenerDatosCliente(MantenimientoMAEModificacionClienteForm f, String codigoPais,
			String codigoCliente,
			MantenimientoMAEClienteService clienteService, List subTiposCliente) {
		
		Cliente cliente = clienteService.getDatosBasicosCliente(codigoPais,
				codigoCliente);
		cliente.setCodigoPais(codigoPais);
		ClienteAdicional clienteAdicional = clienteService
				.getDatosAdicionalesCliente(cliente.getOid().toString());
		ClientePrimerContacto clientePrimerContacto = clienteService
				.getPrimerContactoCliente(cliente.getOid().toString());

		List listClienteIdentificacion = clienteService
				.getListIdentificacionCliente(cliente.getOid().toString());
		List listClienteSubTipo = clienteService
				.getListTipoSubtipoCliente(cliente.getOid().toString());
		List listClienteComunicacion = clienteService
				.getListComunicacionCliente(cliente.getOid().toString());
		List listClienteDireccion = null;
		if (f.isIndicadorCamposAdicionales())
		    listClienteDireccion = clienteService.getListDireccionClienteCamposAdicionales(cliente.getOid().toString());
		else
			listClienteDireccion = clienteService.getListDireccionCliente(cliente.getOid().toString());
		
		List listClienteVinculo = clienteService.getListVinculoCliente(cliente
				.getOid().toString());

		cliente.setClienteAdicional(clienteAdicional);
		cliente.setClientePrimerContacto(clientePrimerContacto);
		cliente.setListClienteIdentificacion(listClienteIdentificacion);
		cliente.setListClienteComunicacion(listClienteComunicacion);
		cliente.setListClienteDireccion(listClienteDireccion);
		cliente.setListClienteVinculo(listClienteVinculo);

		ClienteUnidadAdministrativa clienteUnidadAdministrativa = clienteService
				.getUnidadAdministrativaCliente(cliente.getOid().toString());
		cliente.setClienteUnidadAdministrativa(clienteUnidadAdministrativa);

		/***** obtenemos las referencias *******/
		Map map = new HashMap();
		map.put("codigoCliente", codigoCliente);
		map.put("tipoReferencia", "");// obtenemos todas las referencias
		List listReferenciaCliente = clienteService
				.getListClienteReferencia(map);
		ClienteReferencias clienteReferencias = getClienteReferencia(listReferenciaCliente);
		cliente.setClienteReferencias(clienteReferencias);
		/************************/
		// Ordenamos la lista de Identificacion de Clientes, poniendo el tipo
		// Principal al inicio
		boolean encontradoPrincipal = false;
		ClienteIdentificacion clienteIdentificacionAux = null;

		Iterator itClienteIdentificacion = cliente
				.getListClienteIdentificacion().iterator();
		while (itClienteIdentificacion.hasNext()) {
			clienteIdentificacionAux = (ClienteIdentificacion) itClienteIdentificacion
					.next();

			if (clienteIdentificacionAux.getIndicadorPrincipal().intValue() == 1) {
				itClienteIdentificacion.remove();
				encontradoPrincipal = true;
				break;
			}
		}
		if (encontradoPrincipal) {
			cliente.getListClienteIdentificacion().add(0,
					clienteIdentificacionAux);
		}

		// Ordenamos la lista de SubTipoClientes, poniendo al tipo Cliente:
		// Consultora al inicio
		Map mapTiposCliente = new HashMap();
		Iterator itSubTiposCliente = subTiposCliente.iterator();
		while (itSubTiposCliente.hasNext()) {
			Base base = (Base) itSubTiposCliente.next();

			mapTiposCliente.put(base.getCodigo().substring(0, 5), "--");
		}

		List listClienteSubTipoAux = new ArrayList();
		Iterator itClienteSubTipo = listClienteSubTipo.iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) itClienteSubTipo
					.next();

			String clave = clienteSubTipoAux.getCodigoTipoCliente() + "-"
					+ clienteSubTipoAux.getCodigoSubTipoCliente();

			if (mapTiposCliente.get(clave) != null) {
				listClienteSubTipoAux.add(clienteSubTipoAux);

				clienteSubTipoAux.setListClienteClasificacion(clienteService
						.getListClasificacionCliente(clienteSubTipoAux.getOid()
								.toString()));
			}
		}

		cliente.setListClienteSubTipo(listClienteSubTipoAux);

		cliente.setListClienteObservacion(new ArrayList());

		return cliente;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Inicio view");
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSave = true;
		this.mostrarBotonSalir = true;
		this.invocarFindLuegoGrabar = false;
		this.subConsulta=false;

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ConsultaMAEClienteSearchForm searchForm = (ConsultaMAEClienteSearchForm) formBusqueda;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// pone en session la lista de tipo subtipo
		setListaTipoSubtipoCliente(criteria, clienteService);
		setListaZona(criteria, clienteService);
		setListaIndicadorActivo(clienteService);

		// longitu de codigo de cliente para el pais
		searchForm.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));

		String indicadorCaracterDocumento = Constants.SI;
		try {
			String modVali = clienteService.getValorModuloxPaisTipoValidacion(
					pais.getCodigo(), Constants.MAE_VALID_NUME_DOCUM);
			if (StringUtils.isNotBlank(modVali))
				indicadorCaracterDocumento = Constants.NO;
		} catch (Exception e) {
			indicadorCaracterDocumento = Constants.SI;
		}
		searchForm.setIndicadorCaracterDocumento(indicadorCaracterDocumento);

		consultaMaeClienteList.clear();	
		
	}

	protected void mostrarDatosModificar() {

	}

	@Override
	protected String devuelveMensajeKeySaveOK() {

		return "mantenimientoMAEActualizacionPeriodosRetiradasForm.msg.updated";
	}

	private void setListaZona(Map criteria,
			MantenimientoMAEClienteService clienteService) {
		List listZonas = clienteService.getZonasByPaisMarcaCanal(criteria);
		maeZonaList = listZonas;

	}

	private void setListaIndicadorActivo(
			MantenimientoMAEClienteService clienteService) {
		List resultado = new ArrayList();
		Base[] mes = new Base[3];
		String activo = getResourceMessage("consultaMAEClienteSearchForm.activo");

		String inactivo = getResourceMessage("consultaMAEClienteSearchForm.inactivo");

		mes[0] = new Base();
		mes[0].setCodigo("");
		mes[0].setDescripcion("");
		resultado.add(mes[0]);

		mes[1] = new Base();
		mes[1].setCodigo(Constants.NUMERO_UNO);
		mes[1].setDescripcion(activo);
		resultado.add(mes[1]);

		mes[2] = new Base();
		mes[2].setCodigo(Constants.NUMERO_CERO);
		mes[2].setDescripcion(inactivo);
		resultado.add(mes[2]);
		maeIndicadorActivoList = resultado;

	}

	/**
	 * Pone en session la lista de tipo subtipo cliente
	 * 
	 * @param session
	 * @param criteria
	 * @param clienteService
	 */
	private void setListaTipoSubtipoCliente(Map criteria,
			MantenimientoMAEClienteService clienteService) {
		List subTiposCliente = clienteService
				.getSubTiposClienteInsertar(criteria);
		siccSubTipoClienteList = subTiposCliente;

	}

	public void obtenerTerritorioPorZona(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ConsultaMAEClienteSearchForm f = (ConsultaMAEClienteSearchForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			// ajax.getTerritoriosByPaisMarcaCanalZona(codigoPais.value,"T","VD",codigoZona.value,
			// loadTerritoriosCallback);
			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeTerritorioList = ajax.getTerritoriosByPaisMarcaCanalZona(
					mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
					"T", "VD", valor);

		} else {
			maeTerritorioList = null;

		}
	}

	private void updateTiposClasificaciones(List detalList) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		List listSubTipoClientes = new ArrayList();

		Iterator it = detalList.iterator();
		while (it.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) it.next();
			listSubTipoClientes.add(clienteSubTipo.getOidSubTipoCliente());
		}

		Map criteria = new HashMap();
		criteria.put("listSubTipoClientes", listSubTipoClientes);
		siccTipoClasificacionList = clienteService
				.getTiposClasificaciones(criteria);

	}

	public List getMaeClienteSubTipoList() {
		return maeClienteSubTipoList;
	}

	public void setMaeClienteSubTipoList(List maeClienteSubTipoList) {
		this.maeClienteSubTipoList = maeClienteSubTipoList;
	}

	public List getMaeClienteClasificacionList() {
		return maeClienteClasificacionList;
	}

	public void setMaeClienteClasificacionList(List maeClienteClasificacionList) {
		this.maeClienteClasificacionList = maeClienteClasificacionList;
	}

	public String obtenerLineaDireccion(ClienteDireccion clienteDireccion,
			MantenimientoMAEModificacionClienteForm f) {
		StringBuffer sbDireccion = new StringBuffer("");
		String nivel1 = "";
		String nivel2 = "";
		String nivel3 = "";
		String nivel4 = "";

		String codigoUnidadGeografica = reemplazarNulo(clienteDireccion
				.getCodigoUnidadGeografica());

		if (codigoUnidadGeografica.length() > 0) {
			nivel1 = codigoUnidadGeografica.substring(0, 6);

			if (codigoUnidadGeografica.length() > 6)
				nivel2 = nivel1 + codigoUnidadGeografica.substring(6, 12);
			if (codigoUnidadGeografica.length() > 12)
				nivel3 = nivel2 + codigoUnidadGeografica.substring(12, 18);
			if (codigoUnidadGeografica.length() > 18)
				nivel4 = nivel3 + codigoUnidadGeografica.substring(18, 24);
		}
		sbDireccion.append(nivel1).append("__");
		sbDireccion.append(nivel2).append("__");
		sbDireccion.append(nivel3).append("__");
		sbDireccion.append(nivel4).append("__");

		sbDireccion.append(
				reemplazarNulo(clienteDireccion.getCodigoPeriodoInicio()))
				.append("__");
		sbDireccion.append(
				reemplazarNulo(clienteDireccion.getCodigoPeriodoFin())).append(
				"__");

		if (f.isMostrarCiudad()) {
			String ubigeo1 = reemplazarNulo(clienteDireccion.getCodigoUbigeo1());
			String ciudad = reemplazarNulo(clienteDireccion.getCodigoCiudad());

			if (ciudad.length() > 0)
				sbDireccion.append(ubigeo1 + "__" + ciudad).append("__");
		}

		if (f.isMostrarVillaPoblacion())
			sbDireccion.append(
					reemplazarNulo(clienteDireccion.getVillaPoblacion()))
					.append("__");

		if (f.isMostrarTipoVia())
			sbDireccion
					.append(reemplazarNulo(clienteDireccion.getOidTipoVia()))
					.append("__");

		if (f.isMostrarNumeroPrincipal())
			sbDireccion.append(
					reemplazarNulo(clienteDireccion.getNumeroPrincipal()))
					.append("__");

		sbDireccion.append(reemplazarNulo(clienteDireccion.getNombreVia()))
				.append("__");
		sbDireccion.append(reemplazarNulo(clienteDireccion.getObservaciones()))
				.append("__");

		if (f.isMostrarBarrio())
			sbDireccion.append(reemplazarNulo(clienteDireccion.getBarrio()))
					.append("__");

		return sbDireccion.toString();
	}

	private void actualizarListarUbigeo(
			MantenimientoMAEModificacionClienteForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// actualizamos las listas de ubigeo de Direccion Domicilio
		if (f.getNivel1().length() > 0) {

			maeClienteNivel2List = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel1());
		}

		/* INI SA PER-SiCC-2012-0459 */
		if (f.isMostrarCiudad()) {
			if (f.getNivel1().length() > 0) {

				maeCiudadList = ajaxService
						.getCiudadesByRegion(f.getNivel1());
			}
			if (f.getNivel1CT().length() > 0) {
				maeCiudadCTList = ajaxService.getCiudadesByRegion(f
						.getNivel1CT());

			}
		}
		/* FIN SA PER-SiCC-2012-0459 */
		if (f.getNivel2() != null) {
			if (f.getNivel2().length() > 0) {
				maeClienteNivel3List = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel2());
			}
		}
		if (f.getNivel3() != null) {
			if (f.getNivel3().length() > 0) {
				maeClienteNivel4List = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel3());
			}
		}
		if (f.getNivel4() != null) {
			if (f.getNivel4().length() > 0) {

				maeClienteNivel5List = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel4());
			}
		}
		if (f.getNivel5() != null) {
			if (f.getNivel5().length() > 0) {

				maeClienteNivel6List = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel5());
			}
		}

		// actualizamos las listas de ubigeo de Direccion Opcional
		if (f.getNivel1CT() != null) {
			if (f.getNivel1CT().length() > 0) {
				maeClienteNivel2CTList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel1CT());
			}
		}
		if (f.getNivel2CT() != null) {
			if (f.getNivel2CT().length() > 0) {

				maeClienteNivel3CTList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel2CT());
			}
		}
		if (f.getNivel3CT() != null) {
			if (f.getNivel3CT().length() > 0) {

				maeClienteNivel4CTList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel3CT());
			}
		}
		if (f.getNivel4CT() != null) {
			if (f.getNivel4CT().length() > 0) {

				maeClienteNivel5CTList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel4CT());
			}
		}
		if (f.getNivel5CT() != null) {
			if (f.getNivel5CT().length() > 0) {

				maeClienteNivel6CTList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel5CT());
			}
		}

		/* INI SA PER-SiCC-2012-0365 */
		if (f.getNivel1Vacaciones() != null) {
			if (f.getNivel1Vacaciones().length() > 0) {
				maeClienteNivel2VACList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel1Vacaciones());

			}
		}

		if (f.isMostrarCiudad()) {
			if (f.getNivel1Vacaciones().length() > 0) {
				maeCiudadVacList = ajaxService.getCiudadesByRegion(f
						.getNivel1Vacaciones());

			}
		}

		if (f.getNivel2Vacaciones() != null) {
			if (f.getNivel2Vacaciones().length() > 0) {
				maeClienteNivel3VACList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel2Vacaciones());

			}
		}

		if (f.getNivel3Vacaciones() != null) {
			if (f.getNivel3Vacaciones().length() > 0) {
				maeClienteNivel4VACList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel3Vacaciones());

			}
		}
		if (f.getNivel4Vacaciones() != null) {
			if (f.getNivel4Vacaciones().length() > 0) {
				maeClienteNivel5VACList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel4Vacaciones());

			}
		}

		if (f.getNivel5Vacaciones() != null) {
			if (f.getNivel5Vacaciones().length() > 0) {
				maeClienteNivel6VACList = ajaxService.getUnidadesGeograficas(
						f.getOidPais(), f.getNivel5Vacaciones());
			}
		}
		/* FIN SA PER-SiCC-2012-0365 */

		// actualizamos las longitudes de los tipos de documento
		if (f.getTipoDocumentoIdentidad() != null) {
			if (f.getTipoDocumentoIdentidad().length() > 0) {
				f.setLongitudTipoDocumento(ajaxService
						.getLongitudTipoDocumento(f.getOidPais(),
								f.getTipoDocumentoIdentidad()));
			} else {
				f.setLongitudTipoDocumento("15");
			}
		}

		if (f.getTipoDocumentoIdentidad2() != null) {
			if (f.getTipoDocumentoIdentidad2().length() > 0) {
				f.setLongitudTipoDocumento2(ajaxService
						.getLongitudTipoDocumento(f.getOidPais(),
								f.getTipoDocumentoIdentidad2()));
			} else {
				f.setLongitudTipoDocumento2("15");
			}
		}

		if (f.getTipoDocumentoIdentidad3() != null) {
			if (f.getTipoDocumentoIdentidad3().length() > 0) {
				f.setLongitudTipoDocumento3(ajaxService
						.getLongitudTipoDocumento(f.getOidPais(),
								f.getTipoDocumentoIdentidad3()));
			} else {
				f.setLongitudTipoDocumento3("15");
			}
		}
		actualizarListarUbigeoAval(f);
	}

	/**
	 * metodo auxiliar que permite recuperar el tipo de cliente agregado por el
	 * usuario y verificamos si existe el tipo/subtipo para el cliente
	 * recuperado al ingresar a esta pantalla se recuperar dicha informacion
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	private ClienteSubTipo getSubTipoCliente(
			MantenimientoMAEModificacionClienteForm f) {
		Cliente cliente = maeDtoCliente;
		String subTipoCliente = f.getSubTipoCliente();

		StringTokenizer st = new StringTokenizer(subTipoCliente, "-");

		ClienteSubTipo clienteSubTipo = new ClienteSubTipo();
		clienteSubTipo.setCodigoTipoCliente(st.nextToken());
		clienteSubTipo.setCodigoSubTipoCliente(st.nextToken());
		clienteSubTipo.setOidTipoCliente(new Long(st.nextToken()));
		clienteSubTipo.setOidSubTipoCliente(new Long(st.nextToken()));

		StringTokenizer st2 = new StringTokenizer(st.nextToken(), "/");
		clienteSubTipo.setDescripcionTipoCliente(st2.nextToken());

		if (st2.hasMoreTokens())
			clienteSubTipo.setDescripcionSubTipoCliente(st2.nextToken());

		Iterator itClienteSubTipo = cliente.getListClienteSubTipo().iterator();
		while (itClienteSubTipo.hasNext()) {
			ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo) itClienteSubTipo
					.next();

			if ((clienteSubTipoAux.getCodigoTipoCliente().equals(clienteSubTipo
					.getCodigoTipoCliente()))
					&& (clienteSubTipoAux.getCodigoSubTipoCliente()
							.equals(clienteSubTipo.getCodigoSubTipoCliente()))) {
				clienteSubTipo = clienteSubTipoAux;
				break;
			}
		}

		return clienteSubTipo;
	}

	private List getDetalSubTipoClienteList() {
		List list = maeClienteSubTipoList;
		if (list == null) {
			list = new ArrayList();
		}
		maeClienteSubTipoList = list;

		return list;

	}

	public void addDetalSubTipoCliente(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalSubTipoCliente' method");
		}
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		List detalList = getDetalSubTipoClienteList();
		log.debug("formulario  :  " + f);
		ClienteSubTipo clienteSubTipo = getSubTipoCliente(f);

		if (!existeSubTipoCliente(clienteSubTipo.getCodigoTipoCliente(),
				clienteSubTipo.getCodigoSubTipoCliente(), detalList)) {
			// SI ES CONSULTORA, SE PONDRA EL TIPO DE DOCUMENTO OBLIGATORIO PARA
			// EL PAIS
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA))
					&& ((clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO)) || (clienteSubTipo
							.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA)))) {
				f.setMostrarUnidadAdministrativa(true);
				f.setMostrarConsultoraVinculo(true);
				f.setMostrarDireccion(true);
				f.setMostrarNumeroIdentidad(true);
				f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

				if ("-".equals(f.getCodigoZona())) {
					f.setCodigoZona("");
					f.setCodigoTerritorio("");
				}

				// seteamos datos de tipoVia, NumeroPrincipal, ubigeo.
				setearCamposModuloPais(f, clienteService);

				f.setTieneTipologiaConsultora(true);

				if (clienteSubTipo.getCodigoSubTipoCliente().equals(
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA))
					f.setMostrarCodigoEmpleado(true);

				f.setEsDuplaCyzone(false);
				f.setEsTipoConsultora(true);

				Map criteria = new HashMap();
				criteria.put("oidPais", f.getOidPais());
				criteria.put("oidSubTipoCliente",
						clienteSubTipo.getOidSubTipoCliente());

				// seteamos la edad minima y maxima
				f.setEdadMinima(clienteService.getEdadMinima(criteria));
				f.setEdadMaxima(clienteService.getEdadMaxima(criteria));

			}

			if (clienteSubTipo.getCodigoTipoCliente().equals(
					Constants.MAE_CODIGO_TIPO_CONSULTORA)) {
				if (clienteSubTipo.getCodigoSubTipoCliente().equals(
						Constants.MAE_CODIGO_SUBTIPO_OFICINA)) {
					f.setMostrarCodigoCUB(true);
				}
			}

			// SI ES GERENTE, NO SE MUESTRA CONSULTORA RECOMENDANTE
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_GERENTE))
					&& ((clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_GERENTE_REGION)) || (clienteSubTipo
							.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_GERENTE_ZONA)))) {
				f.setMostrarCodigoEmpleado(true);
				/* INI JJ PER-SiCC-2012-0329 */
				f.setMostrarCodigoCUB(true);
				/* FIN JJ PER-SiCC-2012-0329 */
			}

			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_GERENTE))
					&& ((clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_GERENTE_REGION)) || (clienteSubTipo
							.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_GERENTE_ZONA)))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
							detalList))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
							detalList))) {
				f.setMostrarConsultoraVinculo(false);

				f.setMostrarDireccion(true);
				f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

				if ("-".equals(f.getCodigoZona())) {
					f.setCodigoZona("");
					f.setCodigoTerritorio("");
				}
			}

			// SI ES DUPLACYZONE, SE PONDRA EL TIPO DE DOCUMENTO DE DUPLA CYZONE
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA))
					&& (clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_HIJADUPLA_HIJADUPLA))) {

				/*
				 * f.setTipoDocumentoIdentidad(clienteService.
				 * getTipoDocumentoDuplaCyzone(f.getOidPais()));
				 */
				f.setMostrarNumeroIdentidad(false);
				f.setMostrarDireccion(false);
				f.setMostrarConsultoraVinculo(true);
				f.setEsDuplaCyzone(true);
				f.setEsTipoConsultora(false);
				f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

				f.setCodigoZona("-");
				f.setCodigoTerritorio("-");
				/* f.setNumeroDocumentoIdentidad("-"); */

				f.setNivel1("-");
				f.setNivel2("-");
				f.setNivel3("-");

				Map criteria = new HashMap();
				criteria.put("oidPais", f.getOidPais());
				criteria.put("oidSubTipoCliente",
						clienteSubTipo.getOidSubTipoCliente());

				// seteamos la edad minima y maxima
				f.setEdadMinima(clienteService.getEdadMinima(criteria));
				f.setEdadMaxima(clienteService.getEdadMaxima(criteria));
			}

			// SI ES AVAL, SE PONDRA EL TIPO DE DOCUMENTO OBLIGATORIO PARA EL
			// PAIS
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_POTENCIAL))
					&& (clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_POTENCIAL_AVAL))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
							detalList))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
							detalList))) {
				f.setMostrarUnidadAdministrativa(false);
				f.setMostrarConsultoraVinculo(false);

				f.setMostrarDireccion(true);
				f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

				if (detalList.size() == 0) {
					f.setCodigoZona("-");
					f.setCodigoTerritorio("-");
				}
			}

			if (detalList.size() == 0) {
				clienteSubTipo.setIndicadorPrincipal(new Integer(1));

				// si es consultora/gerente/aval, entonces no se mostrara el
				// SubTipoCliente DuplaCyzone
				if (!clienteSubTipo.getCodigoTipoCliente().equals(
						Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
					List subTiposCliente = siccSubTipoClienteList;

					for (int i = 0; i < subTiposCliente.size(); i++) {
						Base base = (Base) subTiposCliente.get(i);

						StringTokenizer st = new StringTokenizer(
								base.getCodigo(), "-");
						String codigoTipoCliente = st.nextToken();

						if (codigoTipoCliente
								.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
							subTiposCliente.remove(i);
							break;
						}
					}
				} else {
					List subTipoClienteDupla = new ArrayList();
					List subTiposCliente = siccSubTipoClienteList;

					for (int i = 0; i < subTiposCliente.size(); i++) {
						Base base = (Base) subTiposCliente.get(i);

						StringTokenizer st = new StringTokenizer(
								base.getCodigo(), "-");
						String codigoTipoCliente = st.nextToken();

						if (codigoTipoCliente
								.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
							subTipoClienteDupla.add(base);
							break;
						}
					}
					siccSubTipoClienteList = subTipoClienteDupla;
				}

				// Colocamos valores por Defecto
				setearValorPorDefectoPorSubTipoCliente(clienteService, f,
						clienteSubTipo.getOidSubTipoCliente().toString(), false);
			} else
				clienteSubTipo.setIndicadorPrincipal(new Integer(0));

			log.debug("- seteando lista : "
					+ clienteSubTipo.getDescripcionTipoCliente() + "/"
					+ clienteSubTipo.getDescripcionSubTipoCliente());
			detalList.add(clienteSubTipo);
			log.debug("lista size : " + detalList.size());

			f.setMostrarCodigoCUB(false);
			if ((existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA, detalList))
					|| (existeSubTipoClienteAux(
							Constants.MAE_TIPO_CLIENTE_GERENTE,
							Constants.MAE_SUBTIPO_GERENTE_REGION, detalList))
					|| (existeSubTipoClienteAux(
							Constants.MAE_TIPO_CLIENTE_GERENTE,
							Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList))) {
				f.setMostrarCodigoCUB(true);
			}

			// En caso el SubTipoCliente ya tenga asociado clasificaciones, esto
			// se le agrega a la lista de Clasificaciones
			if ((clienteSubTipo.getListClienteClasificacion() != null)
					&& clienteSubTipo.getListClienteClasificacion().size() > 0) {
				List detalListClasificaciones = getDetalClasificacionList();
				detalListClasificaciones.addAll(clienteSubTipo
						.getListClienteClasificacion());
			}

			// Agregamos la clasificacion por Defecto relacionado al
			// SubTipoCliente
			String tipoClasificacionDefault = clienteService
					.getTipoClasificacionDefault(
							clienteSubTipo.getCodigoTipoCliente(),
							clienteSubTipo.getCodigoSubTipoCliente());

			String clasificacionDefault = clienteService
					.getClasificacionDefault(
							clienteSubTipo.getCodigoTipoCliente(),
							clienteSubTipo.getCodigoSubTipoCliente());

			f.setTipoClasificacion(tipoClasificacionDefault);
			f.setClasificacion(clasificacionDefault);
			addDetalClasificacion();

		} else {
			String cadError = clienteSubTipo.getDescripcionTipoCliente();
			String mensaje2 = getResourceMessage(
					"mantenimientoMAEClienteForm.msg.SubTipoExiste",
					new Object[] { cadError });
			addInfo("Mensaje", mensaje2);

		}

		updateTiposClasificaciones(detalList);

		// actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

	}

	/**
	 * Pone en session la lista actualizada de ubigeo aval
	 * 
	 * @param request
	 * @param f
	 */
	private void actualizarListarUbigeoAval(
			MantenimientoMAEModificacionClienteForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// String codDpto=
		// (f.getCodigoDepartamentoAval()==null?"":f.getCodigoDepartamentoAval());
		String codPvr = (f.getCodigoProvinciaAval() == null ? "" : f
				.getCodigoProvinciaAval());
		String codDist = (f.getCodigoDistritoAval() == null ? "" : f
				.getCodigoDistritoAval());
		if (codPvr.length() > 0) {
			maeClienteNivel2AvalList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), codDist);

		}
		// log.debug("f.getCodigoDistritoAval()" + f.getCodigoDistritoAval());
		if (codDist.length() > 0) {
			maeClienteNivel3AvalList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), codPvr);

		}

		log.debug("codigo distrito Aval  " + f.getCodigoProvinciaAval());
		log.debug("codigo distrito Aval  " + f.getCodigoDistritoAval());

		if (f.getOidTipoDocumentoAval() != null
				&& f.getOidTipoDocumentoAval().length() > 0) {
			f.setLongitudTipoDocumentoAval(ajaxService
					.getLongitudTipoDocumento(f.getOidPais(),
							f.getOidTipoDocumentoAval()));
		} else {
			f.setLongitudTipoDocumentoAval("");
		}

	}

	public LabelValue[] getMaeClienteNivel3AvalList() {
		return maeClienteNivel3AvalList;
	}

	public void setMaeClienteNivel3AvalList(
			LabelValue[] maeClienteNivel3AvalList) {
		this.maeClienteNivel3AvalList = maeClienteNivel3AvalList;
	}

	public LabelValue[] getMaeClienteNivel2AvalList() {
		return maeClienteNivel2AvalList;
	}

	public void setMaeClienteNivel2AvalList(
			LabelValue[] maeClienteNivel2AvalList) {
		this.maeClienteNivel2AvalList = maeClienteNivel2AvalList;
	}

	private void actualizarPeriodoVigenteSinCruce(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService) {
		LabelValue[] periodosVig = siccPeriodoList;
		LabelValue[] periodosVigAct = new LabelValue[periodosVig.length + 1];
		Map criteria = new HashMap();

		if (periodosVig.length == 1) {
			// VERIFICAMOS SI LA CONSULTORA PASO PEDIDOS EN PERIODOS VIGENTES
			String[] periodoVigAux = new String[periodosVig.length];
			for (int i = 0; i < periodosVig.length; i++) {
				periodoVigAux[i] = periodosVig[i].getValue();
			}
			criteria.put("oidCliente", f.getOidCliente());
			criteria.put("listPeriodos", periodoVigAux);
			boolean pasoPedido = clienteService
					.esClienteHaFacturadoPeriodos(criteria);

			if (!pasoPedido) {
				criteria.put("oidPeriodo", periodosVig[0].getValue());
				Base basePeriodoAnt = clienteService
						.getPeriodoAnterior(criteria);

				periodosVigAct[0] = periodosVig[0];
				LabelValue periodoAnt = new LabelValue(
						basePeriodoAnt.getDescripcion(),
						basePeriodoAnt.getCodigo());

				periodosVigAct[1] = periodoAnt;
				siccPeriodoList = periodosVigAct;

			}
		}
	}

	private void actualizarInicialListaUbigeoAval(
			MantenimientoMAEModificacionClienteForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		log.debug("f.getCodigoDepartamentoAval()"
				+ f.getCodigoDepartamentoAval());
		log.debug("f.getCodigoProvinciaAval()" + f.getCodigoProvinciaAval());
		log.debug("f.getCodigoDistritoAval()" + f.getCodigoDistritoAval());
		// String codDpto=
		// (f.getCodigoDepartamentoAval()==null?"":f.getCodigoDepartamentoAval());
		String codPvr = (f.getCodigoProvinciaAval() == null ? "" : f
				.getCodigoProvinciaAval());
		String codDist = (f.getCodigoDistritoAval() == null ? "" : f
				.getCodigoDistritoAval());
		if (codPvr.length() > 0) {
			maeClienteNivel2AvalList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getCodigoDepartamentoAval());

		}
		// log.debug("f.getCodigoDistritoAval()" + f.getCodigoDistritoAval());
		if (codDist.length() > 0) {
			maeClienteNivel3AvalList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(),
					f.getCodigoDepartamentoAval() + f.getCodigoProvinciaAval());

		}

		f.setCodigoProvinciaAval(f.getCodigoDepartamentoAval()
				+ f.getCodigoProvinciaAval());
		f.setCodigoDistritoAval(f.getCodigoProvinciaAval()
				+ f.getCodigoDistritoAval());
		log.debug("codigo distrito Aval  " + f.getCodigoDistritoAval());
		// actualizamos las longitudes de los tipos de documento
		if (f.getOidTipoDocumentoAval() != null
				&& f.getOidTipoDocumentoAval().length() > 0) {
			f.setLongitudTipoDocumentoAval(ajaxService
					.getLongitudTipoDocumento(f.getOidPais(),
							f.getOidTipoDocumentoAval()));
		} else {
			f.setLongitudTipoDocumentoAval("");
		}
	}

	private void validarGrupoDirecciones(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService, Cliente cliente,
			LabelValue[] periodos) {
		Map map = new HashMap();
		List listPeriodos = new ArrayList();
		if (periodos != null && periodos.length > 0) {
			for (int i = 0; i < periodos.length; i++) {
				LabelValue base = periodos[i];
				listPeriodos.add(base.getValue());
			}
		}
		map.put("codigoCliente", cliente.getCodigo());
		map.put("oidPeriodo", listPeriodos);
		Integer numPedidos = clienteService.getSizePedidosEnviados(map);
		log.debug("numPedidos >>> " + numPedidos);
		if (numPedidos > 0)
			f.setEditable(false);
		else
			f.setEditable(true);
		// f.setOidPeriodo("");
	}

	private void setearValorPorDefectoPorSubTipoCliente(
			MantenimientoMAEClienteService clienteService,
			MantenimientoMAEModificacionClienteForm f,
			String oidSubTipoCliente, boolean cargaInicial) {

		Map mapRespuesta = null;
		String indObligatorio = null;
		String oidCampo;

		// Colocamos valores por Defecto para Nacionalidad
		List listNacionalidades = maeClienteNacionalidadList;
		mapRespuesta = setearValorDefaultCampo(clienteService, f.getOidPais(),
				oidSubTipoCliente, Constants.MAE_OID_CONFI_CAMPO_NACIONALIDAD,
				listNacionalidades);
		indObligatorio = (String) mapRespuesta.get("indicadorObligatorio");
		oidCampo = (String) mapRespuesta.get("oidCampo");

		if ((indObligatorio != null)) {
			if ("1".equals(indObligatorio))
				f.setIndicadoObligatorioNacionalidad(true);
			else
				f.setIndicadoObligatorioNacionalidad(false);

			if (f.getNacionalidad().equals("") && !cargaInicial)
				f.setNacionalidad(oidCampo);
		}

		// Colocamos valores por Defecto para Nacionalidad
		List listNivelEstudios = maeClienteNivelEstudioList;
		mapRespuesta = setearValorDefaultCampo(clienteService, f.getOidPais(),
				oidSubTipoCliente,
				Constants.MAE_OID_CONFI_CAMPO_GRADO_INSTRUCCION,
				listNivelEstudios);
		indObligatorio = (String) mapRespuesta.get("indicadorObligatorio");
		oidCampo = (String) mapRespuesta.get("oidCampo");

		if ((indObligatorio != null)) {
			if ("1".equals(indObligatorio))
				f.setIndicadoObligatorioGradoInstruccion(true);
			else
				f.setIndicadoObligatorioGradoInstruccion(false);

			if (f.getGradoInstruccion().equals("") && !cargaInicial)
				f.setGradoInstruccion(oidCampo);
		}

		// Colocamos valores por Defecto para Tratamiento

		List listTratamientos = maeClienteTratamientoList;
		mapRespuesta = setearValorDefaultCampo(clienteService, f.getOidPais(),
				oidSubTipoCliente, Constants.MAE_OID_CONFI_CAMPO_TRATAMIENTO,
				listTratamientos);
		indObligatorio = (String) mapRespuesta.get("indicadorObligatorio");
		oidCampo = (String) mapRespuesta.get("oidCampo");

		if ((indObligatorio != null)) {
			if ("1".equals(indObligatorio))
				f.setIndicadoObligatorioTratamiento(true);
			else
				f.setIndicadoObligatorioTratamiento(false);

			if (f.getTratamiento().equals("") && !cargaInicial)
				if (!oidCampo.equals(""))
					f.setTratamiento(oidCampo);
				else
					f.setTratamiento(MAE_OID_TRATAMIENTO_DEFAULT);
		}
	}

	private Map setearValorDefaultCampo(
			MantenimientoMAEClienteService clienteService, String oidPais,
			String oidSubTipoCliente, String oidCampoConfiguracion,
			List listCampos) {
		String campoDefault = clienteService.getValorConfiCampoSubTipoCliente(
				oidPais, oidSubTipoCliente, oidCampoConfiguracion);
		Map mapRespuesta = new HashMap();

		log.debug(" campoDefault(" + oidCampoConfiguracion + ": "
				+ campoDefault);

		if (campoDefault != null) {
			StringTokenizer st = new StringTokenizer(campoDefault, "-");
			String indicadorObligatorio = st.nextToken();
			String oidCampo = "";
			String valorDefault = st.nextToken().toUpperCase();

			for (int i = 0; i < listCampos.size(); i++) {
				Base base = (Base) listCampos.get(i);

				if (base.getDescripcion().toUpperCase().equals(valorDefault)) {
					oidCampo = base.getCodigo();
					break;
				}
			}

			mapRespuesta.put("indicadorObligatorio", indicadorObligatorio);
			mapRespuesta.put("oidCampo", oidCampo);
		}

		return mapRespuesta;
	}

	private ClienteReferencias getClienteReferencia(List listReferenciaCliente) {
		ClienteReferencias clienteReferencias = new ClienteReferencias();
		Iterator it = listReferenciaCliente.iterator();
		while (it.hasNext()) {
			Map c = (Map) it.next();
			int tipoReferencia = Integer.parseInt(String.valueOf(c
					.get("tipoReferencia")));
			switch (tipoReferencia) {
			case 1:
				clienteReferencias.setApellido1RefFamiliar((String) (c
						.get("apellido1")));
				clienteReferencias.setApellido2RefFamiliar((String) (c
						.get("apellido2")));
				clienteReferencias.setNombre1RefFamiliar((String) (c
						.get("nombre1")));
				clienteReferencias.setNombre2RefFamiliar((String) (c
						.get("nombre2")));
				clienteReferencias.setDireccionRefFamiliar((String) (c
						.get("direccion")));
				clienteReferencias.setBarrioRefFamiliar((String) (c
						.get("barrio")));
				clienteReferencias.setTelefonoCasaRefFamiliar((String) (c
						.get("telefonoCasa")));
				clienteReferencias.setTelefonoCelRefFamiliar((String) (c
						.get("telefonoCelular")));
				clienteReferencias.setCodigoTipoVinculoRefFamiliar((String) (c
						.get("tipoVinculo")));
				break;
			case 2:
				clienteReferencias.setApellido1RefNoFamiliar((String) (c
						.get("apellido1")));
				clienteReferencias.setApellido2RefNoFamiliar((String) (c
						.get("apellido2")));
				clienteReferencias.setNombre1RefNoFamiliar((String) (c
						.get("nombre1")));
				clienteReferencias.setNombre2RefNoFamiliar((String) (c
						.get("nombre2")));
				clienteReferencias.setDireccionRefNoFamiliar((String) (c
						.get("direccion")));
				clienteReferencias.setBarrioRefNoFamiliar((String) (c
						.get("barrio")));
				clienteReferencias.setTelefonoCasaRefNoFamiliar((String) (c
						.get("telefonoCasa")));
				clienteReferencias.setTelefonoCelRefNoFamiliar((String) (c
						.get("telefonoCelular")));
				clienteReferencias
						.setCodigoTipoVinculoRefNoFamiliar((String) (c
								.get("tipoVinculo")));
				break;
			case 3:
				clienteReferencias.setApellido1Aval((String) (c
						.get("apellido1")));
				clienteReferencias.setApellido2Aval((String) (c
						.get("apellido2")));
				clienteReferencias.setNombre1Aval((String) (c.get("nombre1")));
				clienteReferencias.setNombre2Aval((String) (c.get("nombre2")));
				clienteReferencias.setDireccionAval((String) (c
						.get("direccion")));
				clienteReferencias.setBarrioAval((String) (c.get("barrio")));
				clienteReferencias.setTelefonoCasaAval((String) (c
						.get("telefonoCasa")));
				clienteReferencias.setTelefonoCelAval((String) (c
						.get("telefonoCelular")));
				clienteReferencias.setCodigoTipoVinculoAval((String) (c
						.get("tipoVinculo")));
				clienteReferencias.setOidTipoDocumentoAval(String.valueOf((c
						.get("oidTipoDocumento"))));
				clienteReferencias.setNumeroDocumentoAval((String) (c
						.get("numeroDocumento")));
				clienteReferencias.setCodigoDepartamentoAval((String) (c
						.get("codigoDpto")));
				clienteReferencias.setCodigoProvinciaAval((String) (c
						.get("codigoProvincia")));
				clienteReferencias.setCodigoDistritoAval((String) (c
						.get("codigoDistrito")));
				break;
			}

		}
		return clienteReferencias;
	}

	private void actualizarPeriodoVigente(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService,
			List listPeriodosContacto, LabelValue[] periodos) {
		if (f.getOidPeriodoPrimerPedido() != null
				&& !f.getOidPeriodoPrimerPedido().equals("")) {
			Base basePeriodo = null;
			boolean esPeriodoMayor = false;

			Iterator it = listPeriodosContacto.iterator();
			while (it.hasNext()) {
				Base base = (Base) it.next();

				if (base.getCodigo().equals(f.getOidPeriodoPrimerPedido())) {
					basePeriodo = base;
					break;
				}
			}

			LabelValue periodo = periodos[periodos.length - 1];

			if (basePeriodo.getDescripcion().compareTo(periodo.getLabel()) > 0) {
				esPeriodoMayor = true;
			}

			if (esPeriodoMayor) {
				String oidPeriodo = basePeriodo.getCodigo();

				LabelValue[] periodosAux = new LabelValue[periodos.length + 1];
				for (int j = 0; j < periodos.length; j++) {
					periodosAux[j] = periodos[j];
				}

				LabelValue lblSiguientePeriodo = new LabelValue(
						basePeriodo.getDescripcion(), basePeriodo.getCodigo());
				periodosAux[periodos.length] = lblSiguientePeriodo;
				siccPeriodoList = periodosAux;

				f.setOidPeriodo(oidPeriodo);
			}
		}
	}
	
	private void validacionModificacionRecomendante(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService, Cliente cliente,
			List listPeriodosContacto) {
		if(f.isEsDuplaCyzone()) {
			if(f.getCodigoConsultoraVinculo().length()==0) {
				f.setModificarConsultoraVinculo(true);
			}
		} else {
			boolean esPeriodoIngresoVigente = false;
			boolean esPeriodoIngresoMenor = false;
			boolean rangoPeriodoValida = false;
			Base basePeriodo =  null;
			LabelValue[] periodos = siccPeriodoList;

			//obtenemos los datos del primer periodo
			Iterator it = listPeriodosContacto.iterator();
			while(it.hasNext()) {
				Base base = (Base)it.next();

				if(base.getCodigo().equals(f.getOidPeriodoPrimerPedido())) {
					basePeriodo = base;
					break;
				}
			}
			if(basePeriodo!=null) {
				for(int j=0; j< periodos.length; j++) {
					if(periodos[j].getValue().equals(basePeriodo.getCodigo())) {
						esPeriodoIngresoVigente = true;

						//INI MEX-SiCC-2015-0005
						//Si vinculo actual de consultora tiene la “fecha desde” dentro del rango del periodo vigente
						if(esPeriodoIngresoVigente){
							Map criteria = new HashMap();
							criteria.put("oidPeriodoVigente", basePeriodo.getCodigo());
							criteria.put("fechaDesde", f.getFechaDesde());
							GenericoService genericoService = (GenericoService) getBean("genericoService");
							if(genericoService.getValidacionDentroRangoPeriodoVigente(criteria)){
								rangoPeriodoValida = true;
							}
						}
						//FIN MEX-SiCC-2015-0005

						break;
					}

				}

				if(basePeriodo.getDescripcion().compareTo(periodos[0].getLabel())<0) {
					esPeriodoIngresoMenor = true;
					//INI MEX-SiCC-2015-0005
					//Si vinculo actual de consultora tiene la “fecha desde” dentro del rango del periodo vigente
					if(esPeriodoIngresoMenor){
						Map criteria = new HashMap();
						criteria.put("oidPeriodoVigente", periodos[0].getValue());
						criteria.put("fechaDesde", f.getFechaDesde());
						GenericoService genericoService = (GenericoService) getBean("genericoService");
						if(genericoService.getValidacionDentroRangoPeriodoVigente(criteria)){
							rangoPeriodoValida = true;
						}
					}
					//FIN MEX-SiCC-2015-0005
				}
			}else{
				//INI MEX-SiCC-2015-0005
				//Si vinculo actual de consultora tiene la “fecha desde” dentro del rango del periodo vigente
				Map criteria = new HashMap();
				criteria.put("oidPeriodoVigente", periodos[0].getValue());
				criteria.put("fechaDesde", f.getFechaDesde());
				GenericoService genericoService = (GenericoService) getBean("genericoService");
				if(genericoService.getValidacionDentroRangoPeriodoVigente(criteria)){
					rangoPeriodoValida = true;
				}
				//FIN MEX-SiCC-2015-0005
			}

			//VALIDAMOS PARA EL CASO DE LAS REGISTRADAS
			if(f.isTieneTipologiaConsultoraInicio() && f.getCodigoEstatus().equals(Constants.MAE_ESTADO_REGISTRADA)) {
				if(!f.isTienePedidoFacturado()) {
					f.setModificarConsultoraVinculo(true);
					//if(f.getCodigoConsultoraVinculo().equals(""))
					//f.setModificarConsultoraVinculo(true);
					//else {
					////si campaña de primer pedido = alguno de los periodos vigentes
					//if(basePeriodo!=null && esPeriodoIngresoVigente) {
					//f.setModificarConsultoraVinculo(true);
					//}
					//}
				}
			} 

			//VALIDAMOS PARA EL CASO DE LAS RETIRADAS
			if((f.isTieneTipologiaConsultoraInicio() && f.getCodigoEstatus().equals(Constants.MAE_ESTADO_RETIRADA)) ) {
				if(cliente.getClienteAdicional().getIndicadorActivo().intValue() == 1) {

					//si campaña de primer pedido < periodos vigentes
					if(basePeriodo!=null && esPeriodoIngresoMenor) {

						f.setBorrarConcursosPremios(false);

						//INI MEX-SiCC-2015-0005
						if(rangoPeriodoValida){
							//Carga información de vínculo con campos editables código, fecha desde y fecha hasta 
							f.setModificarConsultoraVinculo(true);
						}else{
							//Muestra campos en blanco y editables código, fecha desde y fecha hasta 
							f.setModificarConsultoraVinculo(true);
							Date hoy = new Date(System.currentTimeMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							f.setFechaDesde(sdf.format(hoy));
							f.setFechaHasta(sdf.format(obtenerFechaAñoSiguiente(hoy)));
							f.setCodigoConsultoraVinculo("");
							f.setNombreConsultoraVinculo("");

						}
						//FIN MEX-SiCC-2015-0005
					}


					//si campaña de primer pedido = alguno de los periodos vigentes y no facturo en los periodos Vigentes
					if(basePeriodo!=null && esPeriodoIngresoVigente && !f.isTienePedidoFacturadoVigente()) {


						//INI MEX-SiCC-2015-0005
						if(rangoPeriodoValida){
							//Carga información de vínculo con campos editables código, fecha desde y fecha hasta 
							f.setModificarConsultoraVinculo(true);
						}else{
							//Muestra campos en blanco y editables código, fecha desde y fecha hasta 
							f.setModificarConsultoraVinculo(true);
							Date hoy = new Date(System.currentTimeMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							f.setFechaDesde(sdf.format(hoy));
							f.setFechaHasta(sdf.format(obtenerFechaAñoSiguiente(hoy)));
							f.setCodigoConsultoraVinculo("");
							f.setNombreConsultoraVinculo("");

						}
						//FIN MEX-SiCC-2015-0005

					}

					if(f.getOidPeriodoPrimerPedido().equals("")) {


						//INI MEX-SiCC-2015-0005
						if(rangoPeriodoValida){
							//Carga información de vínculo con campos editables código, fecha desde y fecha hasta 
							f.setModificarConsultoraVinculo(true);
						}else{
							//Muestra campos en blanco y editables código, fecha desde y fecha hasta 
							f.setModificarConsultoraVinculo(true);
							Date hoy = new Date(System.currentTimeMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							f.setFechaDesde(sdf.format(hoy));
							f.setFechaHasta(sdf.format(obtenerFechaAñoSiguiente(hoy)));
							f.setCodigoConsultoraVinculo("");
							f.setNombreConsultoraVinculo("");

						}
						//FIN MEX-SiCC-2015-0005

					}
				}
			}

			//VALIDAMOS PARA EL CASO DE LAS NUEVAS
			if((f.isTieneTipologiaConsultoraInicio() && f.getCodigoEstatus().equals(Constants.MAE_ESTADO_NUEVA)) ) {
				f.setModificarConsultoraVinculo(true);
				/*Map criteria = new HashMap();
					criteria.put("oidCliente", cliente.getOid().toString());
					criteria.put("oidPeriodo", periodos[0].getValue());
					
					String validacion = clienteService.getValidacionVigenciaRecomendacion(criteria);
					if(validacion.equals("1")) {
					f.setModificarConsultoraVinculo(true);
					}*/
			}
		}

		//Recuperamos el vinculo
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List listClienteVinculoAux = new ArrayList();
		Iterator itClienteVinculo = cliente.getListClienteVinculo().iterator();
		while(itClienteVinculo.hasNext()) {
			ClienteVinculo clienteVinculo = (ClienteVinculo)itClienteVinculo.next();

			if((f.isEsDuplaCyzone() && clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_DUPLACYZONE)) ||
					(f.isMostrarUnidadAdministrativa() && clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_RECOMENDANTE)) ) { 
				//ES TIPO CLIENTE = CONSULTORA, HIJA DUPLA

				clienteVinculo.setCodigoClienteVinculante(f.getCodigoConsultoraVinculo());
				listClienteVinculoAux.add(clienteVinculo);
				//break;
			}

			if(clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA)) {
				clienteVinculo.setCodigoClienteVinculante(f.getCodigoConsultoraVinculo());
				listClienteVinculoAux.add(clienteVinculo);
			}
		}
		cliente.setListClienteVinculo(listClienteVinculoAux);

	}

	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	public List getMaeClienteTipoVinculoList() {
		return maeClienteTipoVinculoList;
	}

	public void setMaeClienteTipoVinculoList(List maeClienteTipoVinculoList) {
		this.maeClienteTipoVinculoList = maeClienteTipoVinculoList;
	}

	public void buscarClasificacionesPorTipo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String tipoClasificacion = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = tipoClasificacion.split("-")[1];
			siccClasificacionList = ajax.getClasificacionesByTipoClasificacion(
					valor, mPantallaPrincipalBean.getCurrentIdioma()
							.getCodigoISO());

		} else {
			siccClasificacionList = null;
		}
	}

	public void ajustarLongitudTipoDocumento(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			String tipoDocumento = val.getNewValue().toString();
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			f.setLongitudTipoDocumento(ajax.getLongitudTipoDocumento(
					f.getOidPais(), tipoDocumento));
			// 2011 es NIT
			if (valor.equals("2011")) {
				this.flagMostrarDocumentoFiscal = true;
			} else {
				this.flagMostrarDocumentoFiscal = false;
			}

			if(f.isMostrarSubTipoDocumento()){
				MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
				Map criteria = new HashMap();
				criteria.put("oidTipoDocumento", tipoDocumento);
				siccSubTipoDocumentoList = clienteService.getSubTiposDocumentoIdentidad(criteria);
			}
		}
	}

	public void ajustarLongitudTipoDocumento2(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			String tipoDocumento = val.getNewValue().toString();
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			f.setLongitudTipoDocumento2(ajax.getLongitudTipoDocumento(
					f.getOidPais(), tipoDocumento));
			
			if(f.isMostrarSubTipoDocumento()){
				MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
				Map criteria = new HashMap();
				criteria.put("oidTipoDocumento", tipoDocumento);
				siccSubTipoDocumentoList2 = clienteService.getSubTiposDocumentoIdentidad(criteria);
			}	
		}
	}

	public void validarZonaTerritorio() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarZonaTerritorio' method");
		}
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Map criteria = BeanUtils.describe(f);
		List resultados = clienteService.validarZonaTerritorio(criteria);
		String result = (String) resultados.get(0);

		if (result.equals("ok")) {
			// if(f.isTieneDireccionPrincipal()) {
			Map mapUbigeo = (Map) resultados.get(1);
			f.setNivel1((String) mapUbigeo.get("ubigeo1"));
			f.setCodNivel1(f.getNivel1());

			if ((String) mapUbigeo.get("ubigeo2") != null)
				f.setNivel2(f.getNivel1() + (String) mapUbigeo.get("ubigeo2"));
			if ((String) mapUbigeo.get("ubigeo3") != null)
				f.setNivel3(f.getNivel2() + (String) mapUbigeo.get("ubigeo3"));
			if ((String) mapUbigeo.get("ubigeo4") != null)
				f.setNivel4(f.getNivel3() + (String) mapUbigeo.get("ubigeo4"));
			if ((String) mapUbigeo.get("ubigeo5") != null)
				f.setNivel5(f.getNivel4() + (String) mapUbigeo.get("ubigeo5"));
			if ((String) mapUbigeo.get("ubigeo6") != null)
				f.setNivel6(f.getNivel5() + (String) mapUbigeo.get("ubigeo6"));

			f.setCodNivel2(f.getNivel2());
			f.setCodNivel3(f.getNivel3());
			f.setCodNivel4(f.getNivel4());
			f.setCodNivel5(f.getNivel5());
			f.setCodNivel6(f.getNivel6());
			// }

			// Verificamos que haya cambio de Zona y Territorio, si ese es el
			// caso, se evaluar periodo actual
			boolean cambio = true;

			if ((f.getCodigoZonaInicial().equals(f.getCodigoZona()))
					&& (f.getCodigoTerritorioInicial().equals(f
							.getCodigoTerritorio()))) {
				cambio = false;
			}

			f.setCambioZonaTerritorio(cambio);

			/* INI SA PER-SiCC-2012-0365 */
			f.setActualizaUbigeoDirecciones(true);
			/* FIN SA PER-SiCC-2012-0365 */

			// Si se aplica calculo de periodo de ingreso en base al cierre de
			// Region y Zona
			// boolean esPaisCalculoPeriodoIngreso =
			// clienteService.esPaisCalculaPeriodoIngreso(f.getCodigoPais());
			// if(esPaisCalculoPeriodoIngreso)
			obtenerPeriodoActual(clienteService, f);

			if (f.isPermitirModificarUbigeo())
				f.setControlFoco(MAE_CONTROL_NIVEL1);
			else
				f.setControlFoco(MAE_CONTROL_NOMBREVIA);
			
			if(!f.isBorradoDireccionPrincipal()) {
				deleteDireccionPrincipal(f);
			}
			
		} else {
			Iterator it = resultados.iterator();
			while (it.hasNext()) {
				String error = (String) it.next();
				addInfo("Mensaje", error);
				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage(error));
			}
			f.setControlFoco(MAE_CONTROL_ZONA);
			// saveErrors(request, messages);
		}

		// actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

	}

	private void obtenerPeriodoActual(
			MantenimientoMAEClienteService clienteService,
			MantenimientoMAEModificacionClienteForm f) {
		// datos del cliente
		Cliente cliente = maeDtoCliente;

		Map resultados = clienteService.obtenerDatosCambioUA(f.getCodigoPais(),
				f.getCodigoMarca(), f.getCodigoCanal(), cliente.getOid()
						.toString(), f.getCodigoZonaInicial(), f
						.getCodigoZona());

		log.debug("resultados obtenerDatosCambioUA : " + resultados);

		String oidPeriodo = (String) resultados.get("oidPeriodo");
		String codigoPeriodo = (String) resultados.get("codigoPeriodo");
		String mostrarPedidoExtemporaneo = (String) resultados
				.get("mostrarPedidoExtemporaneo");
		String mostrarMensajeCambioPeriodoVigente = (String) resultados
				.get("mostrarMensajeCambioPeriodoVigente");
		String indicadorPasoPedido = (String) resultados
				.get("indicadorPasoPedido");
		String requiereGenerarEstatus = (String) resultados
				.get("requiereGenerarEstatus");

		if (Constants.SI.equals(mostrarPedidoExtemporaneo))
			f.setMostrarMensajePedidoExtemporaneo(true);
		else
			f.setMostrarMensajePedidoExtemporaneo(false);

		if (Constants.SI.equals(mostrarMensajeCambioPeriodoVigente))
			f.setMostrarMensajeCambioPeriodoVigente(true);
		else
			f.setMostrarMensajeCambioPeriodoVigente(false);

		if (Constants.SI.equals(indicadorPasoPedido))
			f.setConsultoraPasoPedido(true);
		else
			f.setConsultoraPasoPedido(false);

		if (Constants.SI.equals(requiereGenerarEstatus))
			f.setRequiereGenerarEstatus(true);
		else
			f.setRequiereGenerarEstatus(false);

		// RECUPERAMOS LOS PERIODOS VIGENTES
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", f.getCodigoMarca());
		criteria.put("codigoCanal", f.getCodigoCanal());
		LabelValue[] periodos = clienteService
				.getPeriodosVigentesByPaisMarcaCanal(criteria);

		// Verificamos si el periodo calculado se encuentra dentro de los
		// periodos vigentes
		boolean esPeriodoVigente = false;
		for (int i = 0; i < periodos.length; i++) {
			LabelValue periodo = periodos[i];

			if (periodo.getValue().equals(oidPeriodo)) {
				esPeriodoVigente = true;
			}
		}

		// Verificamos si el periodo Inicial Calculado es mayor al periodo
		// Vigente
		boolean esMayorPeriodoVigente = false;
		if (Integer.parseInt(codigoPeriodo) > Integer.parseInt(periodos[0]
				.getLabel()))
			esMayorPeriodoVigente = true;
		f.setEsMayorPeriodoVigente(esMayorPeriodoVigente);

		if (!esPeriodoVigente) {
			LabelValue[] periodosAux = new LabelValue[periodos.length + 1];
			for (int j = 0; j < periodos.length; j++) {
				periodosAux[j] = periodos[j];
			}

			LabelValue lblSiguientePeriodo = new LabelValue(codigoPeriodo,
					oidPeriodo);
			periodosAux[periodos.length] = lblSiguientePeriodo;
			siccPeriodoList = periodosAux;
			// session.setAttribute(Constants.SICC_PERIODO_LIST, periodosAux);
		} else {
			siccPeriodoList = periodos;
			// session.setAttribute(Constants.SICC_PERIODO_LIST, periodos);
		}

		f.setOidPeriodo(oidPeriodo);
		f.setOidPeriodoConcurso(oidPeriodo);
		f.setPermitirModificarPeriodoVigente(false);
		// f.setCodigoPeriodoIniUA(codigoPeriodo);
	}

	public void habilitarPaneles(AjaxBehaviorEvent e) {
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
		this.setPrimeraVez(false);
		String valor = f.getIndDocumentoPrincipal();
		if (valor.equals("1")) {
			this.principalSegundo = true;
			this.principalPrimero = false;

			this.mostrarBotonEliminarTipo2 = true;
			this.mostrarBotonEliminarTipo1 = false;
			
		} else if (valor.equals("2")) {

			this.principalPrimero = true;
			this.principalSegundo = false;
			
						
			this.mostrarBotonEliminarTipo2 = false;
			this.mostrarBotonEliminarTipo1 = true;
			// form.setCodigoPeriodoFin(null);
		}
	}

	/******************************* Combos CT ***************/
	// /// 3

	public void buscarNivel2CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel2CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel3CTList = null;
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel2CTList = null;
			maeClienteNivel3CTList = null;
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel3CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel3CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel3CTList = null;
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	private List getDetalClasificacionList() {
		List list = maeClienteClasificacionList;
		if (list == null) {
			list = new ArrayList();
		}
		maeClienteClasificacionList = list;

		return list;
	}

	private boolean existeClasificacionCliente(Long oidClasificacion,
			List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteClasificacion ccd = (ClienteClasificacion) it.next();

			if (ccd.getOidClasificacion().equals(oidClasificacion)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica si ya se ha agregado la clasificacion para el cliente
	 * 
	 * @param oidClasificacion
	 * @param detalList
	 * @return
	 */

	private ClienteClasificacion getClasificacionCliente(
			MantenimientoMAEModificacionClienteForm f) {
		String tipoClasificacion = f.getTipoClasificacion();
		String clasificacion = f.getClasificacion();

		StringTokenizer st = new StringTokenizer(tipoClasificacion, "-");

		ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
		clienteClasificacion.setOidClienteSubTipo(new Long(st.nextToken()));
		clienteClasificacion.setOidTipoClasificacion(new Long(st.nextToken()));
		clienteClasificacion.setDescripcionTipoClasificacion(st.nextToken());

		StringTokenizer st2 = new StringTokenizer(clasificacion, "-");
		clienteClasificacion.setOidClasificacion(new Long(st2.nextToken()));
		clienteClasificacion.setDescripcionClasificacion(st2.nextToken());

		return clienteClasificacion;
	}

	private boolean existeClasificacionClienteNuevaAntigua(
			Long oidClasificacion, List detalList) {
		Iterator it = detalList.iterator();
		int cont = 0;

		if (oidClasificacion.toString().equals("2008"))
			cont++;

		if (oidClasificacion.toString().equals("2009"))
			cont++;

		while (it.hasNext()) {
			ClienteClasificacion ccd = (ClienteClasificacion) it.next();

			if (ccd.getOidClasificacion().toString().equals("2008"))
				cont++;

			if (ccd.getOidClasificacion().toString().equals("2009"))
				cont++;
		}

		if (cont > 1)
			return true;

		return false;
	}

	public void deleteDetalSubTipoCliente(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetalSubTipoCliente' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		ClienteSubTipo m = (ClienteSubTipo) beanRegistroTipoClienteSeleccionado;
		// String id = request.getParameter("id");

		if(f.isTienePedidoFacturado() && m.getCodigoTipoCliente().equals("02")) {
			addInfo("Mensaje",
					getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.eliminarTipoSubTipo"));
			return;	
		} 

		List detalList = maeClienteSubTipoList;
		int posicion = 0;
		for (int i = 0; i < maeClienteSubTipoList.size(); i++) {
			if (((ClienteSubTipo) maeClienteSubTipoList.get(i))
					.getCodigoSubTipoCliente().equals(
							m.getCodigoSubTipoCliente())) {
				posicion = i;
			}
		}
		ClienteSubTipo clienteSubTipo = (ClienteSubTipo) detalList
				.get(posicion);
		detalList.remove(posicion);

		// BORRAMOS LOS TIPOS DE CLASIFICACION RELACIONADOS
		List listClasificaciones = getDetalClasificacionList();
		for (int i = 0; i < listClasificaciones.size(); i++) {
			ClienteClasificacion clienteClasificacion = (ClienteClasificacion) listClasificaciones
					.get(i);

			if ((clienteClasificacion.getOidClienteSubTipo()
					.equals(clienteSubTipo.getOidSubTipoCliente()))
					|| (clienteSubTipo.getOid() != null && clienteClasificacion
							.getOidClienteSubTipo().equals(
									clienteSubTipo.getOid()))) {
				listClasificaciones.remove(i);
				i = i - 1;
			}
		}

		// SI ES CONSULTORA
		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO, detalList))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
						detalList))) {
			f.setTieneTipologiaConsultora(true);
			/* INI JJ PER-SiCC-2012-0329 */
			f.setMostrarCodigoCUB(true);

			if (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO, detalList)) {
				f.setMostrarCodigoCUB(false);
			}

			/* FIN JJ PER-SiCC-2012-0329 */
		} else {
			f.setTieneTipologiaConsultora(false);

			// SI ES GERENTE
			if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
					Constants.MAE_SUBTIPO_GERENTE_REGION, detalList))
					|| (existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_GERENTE,
							Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList))) {
				f.setMostrarConsultoraVinculo(false);
				/* INI JJ PER-SiCC-2012-0329 */
				f.setMostrarCodigoCUB(true);
				/* FIN JJ PER-SiCC-2012-0329 */
			} else {
				// SI ES AVAL
				if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_POTENCIAL,
						Constants.MAE_SUBTIPO_POTENCIAL_AVAL, detalList))) {
					f.setMostrarConsultoraVinculo(false);

					f.setCodigoZona("-");
					f.setCodigoTerritorio("-");
				}
			}
		}

		// actualizamos las listas de ubigeo
		f.setMostrarCodigoCUB(false);
		if ((existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA, detalList))
				|| (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_REGION, detalList))
				|| (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList))) {
			f.setMostrarCodigoCUB(true);
		}

		actualizarListarUbigeo(f);

		f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

		updateTiposClasificaciones(detalList);

	}

	public void deleteDetalClasificacionCliente(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetalClasificacionCliente' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		ClienteClasificacion m = (ClienteClasificacion) beanRegistroTipoClienteSeleccionado;

		List detalList = maeClienteClasificacionList;
		int posicion = 0;
		for (int i = 0; i < maeClienteClasificacionList.size(); i++) {
			if (((ClienteClasificacion) maeClienteClasificacionList.get(i))
					.getOidClasificacion().toString()
					.equals(m.getOidClasificacion().toString())) {
				posicion = i;
			}
		}
		detalList.remove(posicion);

		// actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		f.setControlFoco(MAE_CONTROL_CLASIFICACION);

	}
	
	public void duplicarDireccionDomicilio(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("duplicarDireccionDomicilio");
		}
		
		String valor = (String)val.getNewValue();
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		
		if(StringUtils.equals(valor, Constants.SI)){
			
			f.setNivel1CT(f.getNivel1());//Departamento
			/*INI CARGA PROVINCIA*/
			
			if (StringUtils.isNotEmpty(f.getNivel1CT())
					|| StringUtils.isNotBlank(f.getNivel1CT())) {
				log.debug(f.getNivel1CT());
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				maeClienteNivel2CTList = ajax.getUnidadesGeograficas(f.getOidPais(),
						f.getNivel1CT());
				
				if(f.isMostrarCiudad()){
					maeCiudadCTList = ajax.getCiudadesByRegion(
							f.getNivel1CT());
				}
				maeClienteNivel3CTList = null;
				maeClienteNivel4CTList = null;

			} else {
				maeClienteNivel2CTList = null;
				maeClienteNivel3CTList = null;
				maeClienteNivel4CTList = null;
				
			}

			f.setNivel2CT(f.getNivel2());//Provincia
			/*FIN CARGA PROVINCIA*/
			
			/*INI CARGA DISTRITO*/
			if (StringUtils.isNotEmpty(f.getNivel2CT())
					|| StringUtils.isNotBlank(f.getNivel2CT())) {
				log.debug(f.getNivel2CT());
				AjaxService ajax = (AjaxService) getBean("ajaxService");

			
				maeClienteNivel3CTList = ajax.getUnidadesGeograficas(f.getOidPais(),
						f.getNivel2CT());
				maeClienteNivel4CTList = null;

			} else {
				maeClienteNivel3CTList = null;
				maeClienteNivel4CTList = null;
			}

//			f.setCodigoCiudadCT(f.getCodigoCiudad());//Ciudad			
			f.setNivel3CT(f.getNivel3());//Distrito
			/*FIN CARGA DISTRITO*/
			
			
			/* INI CARGA CENTRO POBLADO*/
			if (StringUtils.isNotEmpty(f.getNivel3CT())
					|| StringUtils.isNotBlank(f.getNivel3CT())) {
				log.debug(f.getNivel3CT());		
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				maeClienteNivel4CTList = ajax.getUnidadesGeograficas(f.getOidPais(),
						f.getNivel3CT());

			} else {
				maeClienteNivel4CTList = null;
			}
			f.setNivel4CT(f.getNivel4()); //Centro poblado
			/* FIN CARGA CENTRO POBLADO*/
			
			
			
			f.setTipoViaCT(f.getTipoVia()) ;
			f.setNumeroPrincipalCT(f.getNumeroPrincipal());
			f.setCodigoPostalCT(f.getCodigoPostal());
			f.setNombreViaCT(f.getNombreVia()); //Direccion
			f.setObservacionDireccionCT(f.getObservacionDireccion()); //Referencia
			f.setBarrioCT(f.getBarrio());
			f.setManzanaLetraDE(f.getManzanaLetraDD());
			f.setEtapaConjuntoDE(f.getEtapaConjuntoDD());
			f.setCallePrincipalDE(f.getCallePrincipalDD());
			f.setCalleSecundariaDE(f.getCalleSecundariaDD());
		}else if(StringUtils.equals(valor, Constants.NO)){
			f.setTipoViaCT("") ;
			f.setNumeroPrincipalCT("");
			f.setCodigoPostalCT("");
			f.setNombreViaCT(""); //Direccion
			f.setObservacionDireccionCT(""); //Referencia
			f.setBarrioCT("");
			f.setManzanaLetraDE("");
			f.setEtapaConjuntoDE("");
			f.setCallePrincipalDE("");
			f.setCalleSecundariaDE("");
		}
		//RequestContext.getCurrentInstance().reset("principalForm:panelDireccionEntrega01");
		
	}

	public void addDetalClasificacion(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalClasificacion' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		List detalList = getDetalClasificacionList();

		ClienteClasificacion clienteClasificacion = getClasificacionCliente(f);

		if (!existeClasificacionCliente(
				clienteClasificacion.getOidClasificacion(), detalList)) {
			if (!existeClasificacionClienteNuevaAntigua(
					clienteClasificacion.getOidClasificacion(), detalList)) {
				if (detalList.size() == 0)
					clienteClasificacion.setIndicadorPrincipal(new Integer(1));
				else
					clienteClasificacion.setIndicadorPrincipal(new Integer(0));

				log.debug("- seteando lista : "
						+ clienteClasificacion
								.getDescripcionTipoClasificacion() + "/"
						+ clienteClasificacion.getDescripcionClasificacion());
				detalList.add(clienteClasificacion);
				log.debug("lista size : " + detalList.size());
			} else {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.ClasificacionExisteNuevaAntigua"));

			}
		} else {

			String cadError = clienteClasificacion
					.getDescripcionTipoClasificacion()
					+ "/"
					+ clienteClasificacion.getDescripcionClasificacion();
			addInfo("Mensaje",
					getResourceMessage(
							"mantenimientoMAEClienteForm.msg.ClasificacionExiste",
							new Object[] { cadError }));

		}

		// actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		f.setControlFoco(MAE_CONTROL_CLASIFICACION);

	}

	public void addDetalClasificacion() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalClasificacion' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		List detalList = getDetalClasificacionList();

		ClienteClasificacion clienteClasificacion = getClasificacionCliente(f);

		if (!existeClasificacionCliente(
				clienteClasificacion.getOidClasificacion(), detalList)) {
			if (!existeClasificacionClienteNuevaAntigua(
					clienteClasificacion.getOidClasificacion(), detalList)) {
				if (detalList.size() == 0)
					clienteClasificacion.setIndicadorPrincipal(new Integer(1));
				else
					clienteClasificacion.setIndicadorPrincipal(new Integer(0));

				log.debug("- seteando lista : "
						+ clienteClasificacion
								.getDescripcionTipoClasificacion() + "/"
						+ clienteClasificacion.getDescripcionClasificacion());
				detalList.add(clienteClasificacion);
				log.debug("lista size : " + detalList.size());
			} else {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.ClasificacionExisteNuevaAntigua"));

			}
		} else {

			String cadError = clienteClasificacion
					.getDescripcionTipoClasificacion()
					+ "/"
					+ clienteClasificacion.getDescripcionClasificacion();
			addInfo("Mensaje",
					getResourceMessage(
							"mantenimientoMAEClienteForm.msg.ClasificacionExiste",
							new Object[] { cadError }));

		}

		// actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		f.setControlFoco(MAE_CONTROL_CLASIFICACION);

	}

	public void buscarNivel4CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel5CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel5CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel6CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel6CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);

		} else {
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel2AvalDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel2VACList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel3VACList = null;
			maeClienteNivel4VACList = null;

		} else {
			maeClienteNivel2VACList = null;
			maeClienteNivel3VACList = null;
			maeClienteNivel4VACList = null;
		}
	}

	public void buscarNivel3AvalDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel3VACList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel4VACList = null;

		} else {
			maeClienteNivel3VACList = null;
			maeClienteNivel4VACList = null;

		}
	}

	public void buscarNivel4AvalDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		if (val.getNewValue() == null)
			return;
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4VACList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);

		} else {
			maeClienteNivel4VACList = null;
		}
	}

	/******************************* Combos Vacaciones ***************/
	// /// 3

	public void buscarNivel2VacacionesDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("buscarNivel2VacacionesDomicilio");
		}
		
		if (StringUtils.isNotEmpty((String)val.getNewValue())
				|| StringUtils.isNotBlank((String)val.getNewValue())) {
			log.debug(val.getNewValue().toString());
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel2AVAList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);

			if(f.isMostrarCiudad()){
				maeCiudadAVAList= ajax.getCiudadesByRegion(
						valor);
			}
			
			maeClienteNivel3AVAList = null;
			maeClienteNivel4AVAList = null;

		} else {
			maeClienteNivel2AVAList = null;
			maeClienteNivel3AVAList = null;
			maeClienteNivel4AVAList = null;
		}
	}

	public void buscarNivel3VacacionesDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		
		if (StringUtils.isNotEmpty((String)val.getNewValue())
				|| StringUtils.isNotBlank((String)val.getNewValue())) {
			log.debug(val.getNewValue().toString());
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel3AVAList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel4AVAList = null;

		} else {
			maeClienteNivel3AVAList = null;
			maeClienteNivel4AVAList = null;

		}
	}

	public void buscarNivel4VacacionesDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		
		if (StringUtils.isNotEmpty((String)val.getNewValue())
				|| StringUtils.isNotBlank((String)val.getNewValue())) {
			log.debug(val.getNewValue().toString());
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4AVAList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);

		} else {
			maeClienteNivel4AVAList = null;
		}
	}

	private boolean validarDocumentoIdentidad(
			MantenimientoMAEModificacionClienteForm f,
			MantenimientoMAEClienteService clienteService, Map criteria) {
		Cliente cliente = maeDtoCliente;
		boolean flagOK = true;

		/* RCR COL-SiCC-2015-0054 - INI */
		GenericoService genericoService = (GenericoService) getBean("genericoService");

		ParametroPais paramPais = new ParametroPais();

		paramPais.setCodigoPais(f.getCodigoPais());
		paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais
				.setNombreParametro(Constants.MAE_NOMB_PARAM_VALIDA_DOCUMENTO_IDENTIDAD);
		paramPais.setValorParametro(Constants.NUMERO_UNO);
		paramPais.setIndicadorActivo(Constants.NUMERO_UNO);

		List lstParametros = genericoService.getParametrosPais(paramPais);
		if (lstParametros != null && lstParametros.size() > 0) {
			Map criterios = new HashMap();
			criterios.put("numeroDocumento", f.getNumeroDocumentoIdentidad());
			criterios.put("codigoPais", f.getCodigoPais());
			criterios.put("codigoCliente", f.getCodigoCliente());
			String result = clienteService
					.getValidaDocumentoIdentidad(criterios);
			if (!(result == null || StringUtils.isBlank(result))) {
				flagOK = false;
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.ClienteExiste"));

				return (flagOK);
			}
			if (f.getNumeroDocumentoIdentidad2() != null
					&& StringUtils.isNotBlank(f.getNumeroDocumentoIdentidad2())) {
				criterios.put("numeroDocumento",
						f.getNumeroDocumentoIdentidad2());
				result = clienteService.getValidaDocumentoIdentidad(criterios);
				if (!(result == null || StringUtils.isBlank(result))) {
					flagOK = false;
					addInfo("Mensaje",
							getResourceMessage("mantenimientoMAEClienteForm.msg.ClienteExiste"));
					return (flagOK);
				}
			}
		}
		/* RCR COL-SiCC-2015-0054 - FIN */

		if (f.isTieneIdentificacionPrincipal()) {
			ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) cliente
					.getListClienteIdentificacion().get(0);

			String documentoPrincipal = clienteIdentificacion
					.getNumeroDocumento();

			/* INI SA PER-SiCC-2012-0265 */
			if (documentoPrincipal.equals(f.getNumeroDocumentoIdentidad())) {
				criteria.put("validarSinDocumentos", "");
				String documento = clienteService
						.validarDocumentoIdentidad(criteria);

				if ((documento != null) && documento.equals("Modulo11V")) {
					addInfo("Mensaje",
							getResourceMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValidoRUT"));
					return false;
				} else {
					if(StringUtils.isNotBlank(f.getTipoDocumentoIdentidad2())){
					if ((f.getTipoDocumentoIdentidad2().length() > 0)) {

						if (cliente.getListClienteIdentificacion().size() > 1) {
							ClienteIdentificacion clienteIdentificacionAux = (ClienteIdentificacion) cliente
									.getListClienteIdentificacion().get(1);

							if ((!clienteIdentificacionAux
									.getOidTipoDocumento()
									.toString()
									.equalsIgnoreCase(
											f.getTipoDocumentoIdentidad2()))
									|| (!clienteIdentificacionAux
											.getNumeroDocumento()
											.equalsIgnoreCase(
													f.getNumeroDocumentoIdentidad2()))) {

								Map criteriaAux = new HashMap();
								criteriaAux.put("oidPais", f.getOidPais());
								criteriaAux.put("tipoDocumentoIdentidad",
										f.getTipoDocumentoIdentidad2());
								criteriaAux.put("numeroDocumentoIdentidad",
										f.getNumeroDocumentoIdentidad2());

								if (clienteService
										.verificarDocumentoIdentidad(criteriaAux)) {
									flagOK = false;
									addInfo("Mensajes",
											getReportResourceMessage("mantenimientoMAEClienteForm.msg.SegundoDocumentoExiste"));

								}
							}
						} else {
							Map criteriaAux = new HashMap();
							criteriaAux.put("oidPais", f.getOidPais());
							criteriaAux.put("tipoDocumentoIdentidad",
									f.getTipoDocumentoIdentidad2());
							criteriaAux.put("numeroDocumentoIdentidad",
									f.getNumeroDocumentoIdentidad2());

							if (clienteService
									.verificarDocumentoIdentidad(criteriaAux)) {
								flagOK = false;
								addInfo("Mensaje",
										this.getResourceMessage("mantenimientoMAEClienteForm.msg.SegundoDocumentoExiste"));

							}
						}

					}

					if (flagOK) {
						if (!f.isAprobarAvaladas()) {
							// Modifcacion 20/10/2009 S.B se verificara si es
							// aval de consultoras que tienen deuda si es asi
							// no se le permitira registar
							criteria.put("mensajeError", "");
							criteria.put("tipo", Constants.NUMERO_UNO);// tipo
																		// de
																		// registro
																		// vinculo
																		// +
																		// referencia
							criteria.put("codigoConsultora",
									cliente.getCodigo());
							log.debug("criteria " + criteria);
							clienteService
									.executeValidacionDeudoraConsultoraAval(criteria);
							String mensajeError = (String) criteria
									.get("mensajeError");
							if (StringUtils.isNotEmpty(mensajeError)) {
								addInfo("Mensaje",
										getResourceMessage("Errors.detail"));

								flagOK = false;
							}
							log.debug(">>>> " + flagOK);
						}
					}

					return (flagOK);// true
				}
			}}
			/* FIN SA PER-SiCC-2012-0265 */
		}

		String documento = clienteService.validarDocumentoIdentidad(criteria);

		if (documento != null) {
			flagOK = false;

			if (documento.equals("Modulo10")) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEModificacionClienteForm.msg.DocIdentidadNoValido"));

				/* INI SA PER-SiCC-2012-0265 */
			} else if (documento.equals("Modulo11V")) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValidoRUT"));

				/* FIN SA PER-SiCC-2012-0265 */
			} else {
				StringTokenizer st = new StringTokenizer(documento, "-");
				String saldo = st.nextToken();

				if (saldo.equals(" ")) {
					st.nextToken();
					String codigoCliente = st.nextToken();

					// si se encontro el documento, se restaura a su documento
					// original
					if (f.isTieneIdentificacionPrincipal()) {
						ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion) cliente
								.getListClienteIdentificacion().get(0);

						f.setTipoDocumentoIdentidad(clienteIdentificacion
								.getOidTipoDocumento().toString());
						f.setNumeroDocumentoIdentidad(clienteIdentificacion
								.getNumeroDocumento());
					}

					addInfo("Mensaje",
							getResourceMessage(
									"mantenimientoMAEModificacionClienteForm.msg.documentoPrincipalExiste",
									new Object[] { codigoCliente }));

				}
				/*
				 * else messages.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage
				 * ("mantenimientoMAEClienteForm.msg.ClienteTieneCuentasCastigadas"
				 * , saldo));
				 */
			}
		} else {
			if(StringUtils.isNotBlank(f.getTipoDocumentoIdentidad2())){
			if ((f.getTipoDocumentoIdentidad2().length() > 0)) {

				if (cliente.getListClienteIdentificacion().size() > 1) {
					ClienteIdentificacion clienteIdentificacionAux = (ClienteIdentificacion) cliente
							.getListClienteIdentificacion().get(1);

					if ((!clienteIdentificacionAux.getOidTipoDocumento()
							.toString()
							.equalsIgnoreCase(f.getTipoDocumentoIdentidad2()))
							|| (!clienteIdentificacionAux.getNumeroDocumento()
									.equalsIgnoreCase(
											f.getNumeroDocumentoIdentidad2()))) {

						Map criteriaAux = new HashMap();
						criteriaAux.put("oidPais", f.getOidPais());
						criteriaAux.put("tipoDocumentoIdentidad",
								f.getTipoDocumentoIdentidad2());
						criteriaAux.put("numeroDocumentoIdentidad",
								f.getNumeroDocumentoIdentidad2());

						if (clienteService
								.verificarDocumentoIdentidad(criteriaAux)) {
							flagOK = false;
							addInfo("Mensaje",
									getResourceMessage("mantenimientoMAEClienteForm.msg.SegundoDocumentoExiste"));

						}
					}
				} else {
					Map criteriaAux = new HashMap();
					criteriaAux.put("oidPais", f.getOidPais());
					criteriaAux.put("tipoDocumentoIdentidad",
							f.getTipoDocumentoIdentidad2());
					criteriaAux.put("numeroDocumentoIdentidad",
							f.getNumeroDocumentoIdentidad2());

					if (clienteService.verificarDocumentoIdentidad(criteriaAux)) {
						flagOK = false;
						addInfo("Mensaje",
								getResourceMessage("mantenimientoMAEClienteForm.msg.SegundoDocumentoExiste"));

					}
				}
			}

			if (flagOK) {
				if (!f.isAprobarAvaladas()) {
					// Modifcacion 20/10/2009 S.B se verificara si es aval de
					// consultoras que tienen deuda si es asi
					// no se le permitira registar
					criteria.put("mensajeError", "");
					criteria.put("tipo", Constants.NUMERO_UNO);// tipo de
																// registro
																// referencia
					criteria.put("codigoConsultora", cliente.getCodigo());
					log.debug("criteria " + criteria);
					clienteService
							.executeValidacionDeudoraConsultoraAval(criteria);
					String mensajeError = (String) criteria.get("mensajeError");
					if (StringUtils.isNotEmpty(mensajeError)) {
						addInfo("Mensaje", getResourceMessage("errors.detail"));
						flagOK = false;
					}
				}
			}
		}}

		return flagOK;
	}
	
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina(this.paginaPadre);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
			
	}
	
	public void iniciaValores()throws Exception{
		this.subConsulta=true;		
		this.formMantenimiento = this.setObtenerRegistroAttributes();
		this.formMantenimiento.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		this.mostrarBotonSalir=false;
		this.mostrarBotonSave=true;
	}
	
	//carga el combo Fiador Aval Codeudor- Nivel 2 --provincia
	public void loadValNivel2(ValueChangeEvent val) {	
		if (val.getNewValue() == null)
			return;
		String valor=val.getNewValue().toString();
		if (StringUtils.isNotEmpty(valor)) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;			
			AjaxService ajax = (AjaxService) getBean("ajaxService");		
			this.maeClienteNivel2AvalList = ajax.getUnidadesGeograficas(f.getOidPais(), valor);
			this.maeClienteNivel3AvalList=null;
		} else {
			this.maeClienteNivel2AvalList = null;
			this.maeClienteNivel3AvalList = null;			
		}		
	}
	
	//carga el combo Fiador Aval Codeudor- Nivel 3 --distrito
	public void loadValNivel3(ValueChangeEvent val) {	
		if (val.getNewValue() == null)
			return;
		String valor=val.getNewValue().toString();
		if (StringUtils.isNotEmpty(valor)) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;			
			AjaxService ajax = (AjaxService) getBean("ajaxService");		
			maeClienteNivel3AvalList = ajax.getUnidadesGeograficas(f.getOidPais(), valor);
		} else {			
			this.maeClienteNivel3AvalList = null;			
		}		
	}
	
	//carga el combo Direccion Domicilio --provincia
	public void buscarNivel2Domicilio(ValueChangeEvent val) {
		if (val.getNewValue() == null)
			return;
		String valor=val.getNewValue().toString();
		if (StringUtils.isNotEmpty(valor)) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;			
			AjaxService ajax = (AjaxService) getBean("ajaxService");		
			this.maeClienteNivel2List = ajax.getUnidadesGeograficas(f.getOidPais(), valor);
			this.maeClienteNivel3List =null;	
			maeClienteNivel4List = null;
		} else {
			this.maeClienteNivel2List = null;
			this.maeClienteNivel3List = null;		
			maeClienteNivel4List = null;
		}	
	}
	
	//carga el combo Direccion Domicilio --Distrito
	public void buscarNivel3Domicilio(ValueChangeEvent val) {
		if (val.getNewValue() == null)
			return;
		String valor=val.getNewValue().toString();
		if (StringUtils.isNotEmpty(valor)) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;			
			AjaxService ajax = (AjaxService) getBean("ajaxService");		
			this.maeClienteNivel3List = ajax.getUnidadesGeograficas(f.getOidPais(), valor);	
			maeClienteNivel4List = null;
		} else {
			this.maeClienteNivel3List = null;	
			maeClienteNivel4List = null;
		}	
	}	

	public void buscarNivel4Domicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) this.formMantenimiento;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4List = ajax.getUnidadesGeograficas(f.getOidPais(),
					valor);
		} else {
			maeClienteNivel4List = null;
		}
	}
	
	public String getCodigoFinalCliente() {
		return codigoFinalCliente;
	}

	public void setCodigoFinalCliente(String codigoFinalCliente) {
		this.codigoFinalCliente = codigoFinalCliente;
	}

	public boolean isPrimeraVezDespliegueDireccionEntrega() {
		return primeraVezDespliegueDireccionEntrega;
	}

	public void setPrimeraVezDespliegueDireccionEntrega(
			boolean primeraVezDespliegueDireccionEntrega) {
		this.primeraVezDespliegueDireccionEntrega = primeraVezDespliegueDireccionEntrega;
	}

	public boolean isPrimeraVezDespliegueReferencias() {
		return primeraVezDespliegueReferencias;
	}

	public void setPrimeraVezDespliegueReferencias(
			boolean primeraVezDespliegueReferencias) {
		this.primeraVezDespliegueReferencias = primeraVezDespliegueReferencias;
	}

	public Object getBeanRegistroTipoClienteSeleccionado() {
		return beanRegistroTipoClienteSeleccionado;
	}

	public void setBeanRegistroTipoClienteSeleccionado(
			Object beanRegistroTipoClienteSeleccionado) {
		this.beanRegistroTipoClienteSeleccionado = beanRegistroTipoClienteSeleccionado;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public boolean isFlagMostrarDocumentoFiscal() {
		return flagMostrarDocumentoFiscal;
	}

	public void setFlagMostrarDocumentoFiscal(boolean flagMostrarDocumentoFiscal) {
		this.flagMostrarDocumentoFiscal = flagMostrarDocumentoFiscal;
	}

	public boolean isPrincipalPrimero() {
		return principalPrimero;
	}

	public String getIdPremiosMaeModificacion() {
		return idPremiosMaeModificacion;
	}

	public void setIdPremiosMaeModificacion(String idPremiosMaeModificacion) {
		this.idPremiosMaeModificacion = idPremiosMaeModificacion;
	}

	public void setMaeDeudorasList(List maeDeudorasList) {
		this.maeDeudorasList = maeDeudorasList;
	}

	public void setMaeFlagDeudoras(String maeFlagDeudoras) {
		this.maeFlagDeudoras = maeFlagDeudoras;
	}

	public void setPrincipalPrimero(boolean principalPrimero) {
		this.principalPrimero = principalPrimero;
	}

	public boolean isPrincipalSegundo() {
		return principalSegundo;
	}

	public void setPrincipalSegundo(boolean principalSegundo) {
		this.principalSegundo = principalSegundo;
	}

	public boolean isValorIndicadorFactElectB() {
		return valorIndicadorFactElectB;
	}

	public void setValorIndicadorFactElectB(boolean valorIndicadorFactElectB) {
		this.valorIndicadorFactElectB = valorIndicadorFactElectB;
	}

	public boolean isValorIndicadorFactElectAuxB() {
		return valorIndicadorFactElectAuxB;
	}

	public void setValorIndicadorFactElectAuxB(
			boolean valorIndicadorFactElectAuxB) {
		this.valorIndicadorFactElectAuxB = valorIndicadorFactElectAuxB;
	}

	public boolean isPrimeraVez() {
		return primeraVez;
	}

	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public void cerrarPopUpPeriodo(ActionEvent event) {
		this.getRequestContext().execute("PF('popUpPeriodo').hide()");
	}

	public boolean isChkVinculos() {
		return chkVinculos;
	}

	public boolean isChkSeccionOtros() {
		return chkSeccionOtros;
	}

	public void setChkSeccionOtros(boolean chkSeccionOtros) {
		this.chkSeccionOtros = chkSeccionOtros;
	}

	public boolean isChkReferencias() {
		return chkReferencias;
	}

	public void setChkReferencias(boolean chkReferencias) {
		this.chkReferencias = chkReferencias;
	}

	public String getMaeEstadoRegistrada() {
		return maeEstadoRegistrada;
	}

	public void setMaeEstadoRegistrada(String maeEstadoRegistrada) {
		this.maeEstadoRegistrada = maeEstadoRegistrada;
	}

	public String getMaeEstadoRetirada() {
		return maeEstadoRetirada;
	}

	public void setMaeEstadoRetirada(String maeEstadoRetirada) {
		this.maeEstadoRetirada = maeEstadoRetirada;
	}

	public void setChkVinculos(boolean chkVinculos) {
		this.chkVinculos = chkVinculos;
	}

	public boolean isChkDireccionVacaciones() {
		return chkDireccionVacaciones;
	}

	public void setChkDireccionVacaciones(boolean chkDireccionVacaciones) {
		this.chkDireccionVacaciones = chkDireccionVacaciones;
	}

	public boolean isChkDireccionOpcional() {
		return chkDireccionOpcional;
	}

	public void setChkDireccionOpcional(boolean chkDireccionOpcional) {
		this.chkDireccionOpcional = chkDireccionOpcional;
	}

	public boolean isChkDireccionDomicilio() {
		return chkDireccionDomicilio;
	}

	public void setChkDireccionDomicilio(boolean chkDireccionDomicilio) {
		this.chkDireccionDomicilio = chkDireccionDomicilio;
	}

	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	public boolean isChkTiposCliente() {
		return chkTiposCliente;
	}

	public void setChkTiposCliente(boolean chkTiposCliente) {
		this.chkTiposCliente = chkTiposCliente;
	}

	public boolean isIndicadorFactElect() {
		return indicadorFactElect;
	}

	public void setIndicadorFactElect(boolean indicadorFactElect) {
		this.indicadorFactElect = indicadorFactElect;
	}

	public void setMaeCiudadCTList(LabelValue[] maeCiudadCTList) {
		this.maeCiudadCTList = maeCiudadCTList;
	}

	public List getMaeIndicadorActivoList() {
		return maeIndicadorActivoList;
	}

	public void setMaeIndicadorActivoList(List maeIndicadorActivoList) {
		this.maeIndicadorActivoList = maeIndicadorActivoList;
	}

	public List getMaeZonaList() {
		return maeZonaList;
	}

	public void setMaeZonaList(List maeZonaList) {
		this.maeZonaList = maeZonaList;
	}

	public List getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	public void setSiccSubTipoClienteList(List siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	public LabelValue[] getMaeTerritorioList() {
		return maeTerritorioList;
	}

	public void setMaeTerritorioList(LabelValue[] maeTerritorioList) {
		this.maeTerritorioList = maeTerritorioList;
	}

	public List getConsultaMaeClienteList() {
		return consultaMaeClienteList;
	}

	public void setConsultaMaeClienteList(List consultaMaeClienteList) {
		this.consultaMaeClienteList = consultaMaeClienteList;
	}

	public Cliente getMaeDtoCliente() {
		return maeDtoCliente;
	}

	public void setMaeDtoCliente(Cliente maeDtoCliente) {
		this.maeDtoCliente = maeDtoCliente;
	}

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public List getSiccPeriodoInicialList() {
		return siccPeriodoInicialList;
	}

	public void setSiccPeriodoInicialList(List siccPeriodoInicialList) {
		this.siccPeriodoInicialList = siccPeriodoInicialList;
	}

	public List getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	public void setSiccTipoClasificacionList(List siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	public List getMaeClienteEstadoCivilList() {
		return maeClienteEstadoCivilList;
	}

	public void setMaeClienteEstadoCivilList(List maeClienteEstadoCivilList) {
		this.maeClienteEstadoCivilList = maeClienteEstadoCivilList;
	}

	public List getMaeClienteTratamientoList() {
		return maeClienteTratamientoList;
	}

	public void setMaeClienteTratamientoList(List maeClienteTratamientoList) {
		this.maeClienteTratamientoList = maeClienteTratamientoList;
	}

	public List getMaeClienteSexoList() {
		return maeClienteSexoList;
	}

	public void setMaeClienteSexoList(List maeClienteSexoList) {
		this.maeClienteSexoList = maeClienteSexoList;
	}

	public List getMaeClienteTipoViaList() {
		return maeClienteTipoViaList;
	}

	public void setMaeClienteTipoViaList(List maeClienteTipoViaList) {
		this.maeClienteTipoViaList = maeClienteTipoViaList;
	}

	public LabelValue[] getMaeClienteNivel1List() {
		return maeClienteNivel1List;
	}

	public void setMaeClienteNivel1List(LabelValue[] maeClienteNivel1List) {
		this.maeClienteNivel1List = maeClienteNivel1List;
	}

	public LabelValue[] getMaeClienteNivel1AvalList() {
		return maeClienteNivel1AvalList;
	}

	public void setMaeClienteNivel1AvalList(
			LabelValue[] maeClienteNivel1AvalList) {
		this.maeClienteNivel1AvalList = maeClienteNivel1AvalList;
	}

	public List getMaeClienteNivelEstudioList() {
		return maeClienteNivelEstudioList;
	}

	public void setMaeClienteNivelEstudioList(List maeClienteNivelEstudioList) {
		this.maeClienteNivelEstudioList = maeClienteNivelEstudioList;
	}

	public List getMaeClienteNacionalidadList() {
		return maeClienteNacionalidadList;
	}

	public void setMaeClienteNacionalidadList(List maeClienteNacionalidadList) {
		this.maeClienteNacionalidadList = maeClienteNacionalidadList;
	}

	public List getMaeClienteTipoDireccionList() {
		return maeClienteTipoDireccionList;
	}

	public void setMaeClienteTipoDireccionList(List maeClienteTipoDireccionList) {
		this.maeClienteTipoDireccionList = maeClienteTipoDireccionList;
	}

	public List getMaeTipoCutisList() {
		return maeTipoCutisList;
	}

	public void setMaeTipoCutisList(List maeTipoCutisList) {
		this.maeTipoCutisList = maeTipoCutisList;
	}

	public List getMaeOtrasMarcasList() {
		return maeOtrasMarcasList;
	}

	public void setMaeOtrasMarcasList(List maeOtrasMarcasList) {
		this.maeOtrasMarcasList = maeOtrasMarcasList;
	}

	public LabelValue[] getMaeCiudadCTList() {
		return maeCiudadCTList;
	}

	public LabelValue[] getMaeClienteNivel2List() {
		return maeClienteNivel2List;
	}

	public void setMaeClienteNivel2List(LabelValue[] maeClienteNivel2List) {
		this.maeClienteNivel2List = maeClienteNivel2List;
	}

	public LabelValue[] getMaeClienteNivel3List() {
		return maeClienteNivel3List;
	}

	public void setMaeClienteNivel3List(LabelValue[] maeClienteNivel3List) {
		this.maeClienteNivel3List = maeClienteNivel3List;
	}

	public LabelValue[] getMaeClienteNivel4List() {
		return maeClienteNivel4List;
	}

	public void setMaeClienteNivel4List(LabelValue[] maeClienteNivel4List) {
		this.maeClienteNivel4List = maeClienteNivel4List;
	}

	public LabelValue[] getMaeClienteNivel5List() {
		return maeClienteNivel5List;
	}

	public void setMaeClienteNivel5List(LabelValue[] maeClienteNivel5List) {
		this.maeClienteNivel5List = maeClienteNivel5List;
	}

	public LabelValue[] getMaeClienteNivel6List() {
		return maeClienteNivel6List;
	}

	public void setMaeClienteNivel6List(LabelValue[] maeClienteNivel6List) {
		this.maeClienteNivel6List = maeClienteNivel6List;
	}

	public LabelValue[] getMaeClienteNivel2CTList() {
		return maeClienteNivel2CTList;
	}

	public void setMaeClienteNivel2CTList(LabelValue[] maeClienteNivel2CTList) {
		this.maeClienteNivel2CTList = maeClienteNivel2CTList;
	}

	public LabelValue[] getMaeClienteNivel3CTList() {
		return maeClienteNivel3CTList;
	}

	public void setMaeClienteNivel3CTList(LabelValue[] maeClienteNivel3CTList) {
		this.maeClienteNivel3CTList = maeClienteNivel3CTList;
	}

	public LabelValue[] getMaeClienteNivel4CTList() {
		return maeClienteNivel4CTList;
	}

	public void setMaeClienteNivel4CTList(LabelValue[] maeClienteNivel4CTList) {
		this.maeClienteNivel4CTList = maeClienteNivel4CTList;
	}

	public LabelValue[] getMaeClienteNivel5CTList() {
		return maeClienteNivel5CTList;
	}

	public void setMaeClienteNivel5CTList(LabelValue[] maeClienteNivel5CTList) {
		this.maeClienteNivel5CTList = maeClienteNivel5CTList;
	}

	public LabelValue[] getMaeClienteNivel6CTList() {
		return maeClienteNivel6CTList;
	}

	public void setMaeClienteNivel6CTList(LabelValue[] maeClienteNivel6CTList) {
		this.maeClienteNivel6CTList = maeClienteNivel6CTList;
	}

	public LabelValue[] getMaeCiudadVacList() {
		return maeCiudadVacList;
	}

	public void setMaeCiudadVacList(LabelValue[] maeCiudadVacList) {
		this.maeCiudadVacList = maeCiudadVacList;
	}

	public LabelValue[] getMaeClienteNivel2VACList() {
		return maeClienteNivel2VACList;
	}

	public void setMaeClienteNivel2VACList(LabelValue[] maeClienteNivel2VACList) {
		this.maeClienteNivel2VACList = maeClienteNivel2VACList;
	}

	public LabelValue[] getMaeClienteNivel3VACList() {
		return maeClienteNivel3VACList;
	}

	public void setMaeClienteNivel3VACList(LabelValue[] maeClienteNivel3VACList) {
		this.maeClienteNivel3VACList = maeClienteNivel3VACList;
	}

	public LabelValue[] getMaeClienteNivel4VACList() {
		return maeClienteNivel4VACList;
	}

	public void setMaeClienteNivel4VACList(LabelValue[] maeClienteNivel4VACList) {
		this.maeClienteNivel4VACList = maeClienteNivel4VACList;
	}

	public LabelValue[] getMaeClienteNivel5VACList() {
		return maeClienteNivel5VACList;
	}

	public void setMaeClienteNivel5VACList(LabelValue[] maeClienteNivel5VACList) {
		this.maeClienteNivel5VACList = maeClienteNivel5VACList;
	}

	public LabelValue[] getMaeClienteNivel6VACList() {
		return maeClienteNivel6VACList;
	}

	public void setMaeClienteNivel6VACList(LabelValue[] maeClienteNivel6VACList) {
		this.maeClienteNivel6VACList = maeClienteNivel6VACList;
	}

	public Integer getIndPrincipalComunicacion() {
		return indPrincipalComunicacion;
	}

	public void setIndPrincipalComunicacion(Integer indPrincipalComunicacion) {
		this.indPrincipalComunicacion = indPrincipalComunicacion;
	}

	public String getPathPremios() {
		return pathPremios;
	}

	public void setPathPremios(String pathPremios) {
		this.pathPremios = pathPremios;
	}

	public static String getMaeTipoDireccionDomicilio() {
		return MAE_TIPO_DIRECCION_DOMICILIO;
	}

	public static String getMaeTipoDireccionTrabajo() {
		return MAE_TIPO_DIRECCION_TRABAJO;
	}

	public static String getMaeOidTipoViaDefault() {
		return MAE_OID_TIPO_VIA_DEFAULT;
	}

	public static String getMaeOidEstadoCivilDefault() {
		return MAE_OID_ESTADO_CIVIL_DEFAULT;
	}

	public static String getMaeCodSexoDefault() {
		return MAE_COD_SEXO_DEFAULT;
	}

	public static String getMaeOidTratamientoDefault() {
		return MAE_OID_TRATAMIENTO_DEFAULT;
	}

	public static String getMaeOidTipoDireccionDistribucion() {
		return MAE_OID_TIPO_DIRECCION_DISTRIBUCION;
	}

	public static String getMaeControlApellidopaterno() {
		return MAE_CONTROL_APELLIDOPATERNO;
	}

	public static String getMaeControlSubtipocliente() {
		return MAE_CONTROL_SUBTIPOCLIENTE;
	}

	public static String getMaeControlZona() {
		return MAE_CONTROL_ZONA;
	}

	public static String getMaeControlNivel1() {
		return MAE_CONTROL_NIVEL1;
	}

	public static String getMaeControlClasificacion() {
		return MAE_CONTROL_CLASIFICACION;
	}

	public static String getMaeControlNombrevia() {
		return MAE_CONTROL_NOMBREVIA;
	}

	public static String getMaeTipoComunicacionTelefonoReferencia() {
		return MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA;
	}

	public static Long getMaeClienteContactoDummy() {
		return MAE_CLIENTE_CONTACTO_DUMMY;
	}

	public static String getMaeLongitudCodigoZona() {
		return MAE_LONGITUD_CODIGO_ZONA;
	}

	public static String getMaeDeudorasList() {
		return MAE_DEUDORAS_LIST;
	}

	public static String getMaeFlagDeudoras() {
		return MAE_FLAG_DEUDORAS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Object getBeanRegistroSubTipoClienteSeleccionado() {
		return beanRegistroSubTipoClienteSeleccionado;
	}

	public void setBeanRegistroSubTipoClienteSeleccionado(
			Object beanRegistroSubTipoClienteSeleccionado) {
		this.beanRegistroSubTipoClienteSeleccionado = beanRegistroSubTipoClienteSeleccionado;
	}

	public String getPaginaPadre() {
		return paginaPadre;
	}

	public void setPaginaPadre(String paginaPadre) {
		this.paginaPadre = paginaPadre;
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	public boolean isSubConsulta() {
		return subConsulta;
	}

	public void setSubConsulta(boolean subConsulta) {
		this.subConsulta = subConsulta;
	}

	private String validarNumeroCarnetIdentidad() {
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		String dato = ajaxService.validarNumeroCarnetIdentidad(f.getNumeroDocumentoIdentidad());
			
		if(dato!=null && dato.trim()!=""){
    		return dato;
		}	
		
		return "";
	}
	
	private void deleteDireccionPrincipal(MantenimientoMAEModificacionClienteForm f) {
		f.setBorradoDireccionPrincipal(true);
		//document.getElementById("imagenDireccionPrincipal").style.display = 'none';
		
		if(f.isMostrarTipoVia())
			f.setTipoVia("");
			
		if(f.isMostrarNumeroPrincipal())
			f.setNumeroPrincipal("");
		
		f.setNombreVia("");
		f.setObservacionDireccion("");
		
		/*if(f.isMostrarTipoVia())
			tipoVia.focus();
		else
			nombreVia.focus();	  	*/
	}
	  
	public boolean validarCamposEntradaCaracteres1(MantenimientoMAEModificacionClienteForm f) {	
		String validarCaracteres1 = f.getValidarCaracteres1();
		String campos1 = "";
		String cadenaCaracteres ="";
		
		String msgValidarCaracteresInicio = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		String msgValidarCaracteresFinal2 = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal2");

		if (!(f.getValidarCaracteres1().equals("CV") || f.getValidarCaracteres1().equals("CNV"))) {
			return true;
		}

		if (f.getValidarCaracteres1().equals("CV") || f.getValidarCaracteres1().equals("CNV")) {
			if(f.getValidarCaracteres1().equals("CV"))
				cadenaCaracteres = f.getCadenaCaracteresV1();
			else	 
				cadenaCaracteres = f.getCadenaCaracteresNV1();
		
			
			String d_apellidoPaterno = getResourceMessage("mantenimientoMAEClienteForm.apellidoPaterno");
			String d_apellidoMaterno = getResourceMessage("mantenimientoMAEClienteForm.apellidoMaterno");
			String d_apellidoCasada = getResourceMessage("mantenimientoMAEClienteForm.apellidoCasada");
			String d_nombre1 = getResourceMessage("mantenimientoMAEClienteForm.nombre1");
			String d_nombre2 = getResourceMessage("mantenimientoMAEClienteForm.nombre2");
			String d_numeroPrincipal = getResourceMessage("mantenimientoMAEClienteForm.numeroPrincipal");
			String d_nombreVia = getResourceMessage("mantenimientoMAEClienteForm.nombreVia");
			String d_codigoPostal = getResourceMessage("mantenimientoMAEClienteForm.codigoPostal");
			String d_observacionDireccion = getResourceMessage("mantenimientoMAEClienteForm.observacionDireccion");
			String d_barrio = getResourceMessage("mantenimientoMAEClienteForm.barrio");		
			String d_numeroPrincipalCT = getResourceMessage("mantenimientoMAEClienteForm.numeroPrincipalCT");
			String d_nombreViaCT = getResourceMessage("mantenimientoMAEClienteForm.nombreViaCT");
			String d_codigoPostalCT = getResourceMessage("mantenimientoMAEClienteForm.codigoPostal");
			String d_observacionDireccionCT = getResourceMessage("mantenimientoMAEClienteForm.observacionDireccionCT");
			String d_barrioCT = getResourceMessage("mantenimientoMAEClienteForm.barrioCT");
			String d_numeroPrincipalVacaciones = getResourceMessage("mantenimientoMAEClienteForm.numeroPrincipalVacaciones");
			String d_nombreViaVacaciones = getResourceMessage("mantenimientoMAEClienteForm.nombreViaVacaciones");
			String d_observacionDireccionVacaciones = getResourceMessage("mantenimientoMAEClienteForm.observacionDireccionVacaciones");
			String d_barrioVacaciones = getResourceMessage("mantenimientoMAEClienteForm.barrioVacaciones");
			String d_telefonoReferencia = getResourceMessage("mantenimientoMAEClienteForm.telefonoReferencia");
			String d_apellido1RefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.apellido1RefFamiliar");
			String d_apellido2RefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.apellido2RefFamiliar");
			String d_nombre1RefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.nombre1RefFamiliar");
			String d_nombre2RefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.nombre2RefFamiliar");
			String d_direccionRefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.direccionRefFamiliar");
			String d_barrioRefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.barrioRefFamiliar");
			String d_apellido1RefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.apellido1RefNoFamiliar");
			String d_apellido2RefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.apellido2RefNoFamiliar");
			String d_nombre1RefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.nombre1RefNoFamiliar");
			String d_nombre2RefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.nombre2RefNoFamiliar");
			String d_direccionRefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.direccionRefNoFamiliar");																				
			String d_barrioRefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.barrioRefNoFamiliar");
			String d_apellido1Aval = getResourceMessage("mantenimientoMAEClienteForm.apellido1Aval");
			String d_apellido2Aval = getResourceMessage("mantenimientoMAEClienteForm.apellido2Aval");
			String d_nombre1Aval = getResourceMessage("mantenimientoMAEClienteForm.nombre1Aval");
			String d_nombre2Aval = getResourceMessage("mantenimientoMAEClienteForm.nombre2Aval");
			String d_direccionAval = getResourceMessage("mantenimientoMAEClienteForm.direccionAval");																				
			String d_barrioAval = getResourceMessage("mantenimientoMAEClienteForm.barrioAval");

			String v_apellidoPaterno = f.getApellidoPaterno();
			String v_apellidoMaterno = f.getApellidoMaterno();
			String v_apellidoCasada = f.getApellidoCasada();
			String v_nombre1 = f.getNombre1();
			String v_nombre2 = f.getNombre2();
			String v_numeroPrincipal = f.getNumeroPrincipal();
			String v_nombreVia = f.getNombreVia();
			String v_codigoPostal = f.getCodigoPostal();
			String v_observacionDireccion = f.getObservacionDireccion();
			String v_barrio = f.getBarrio();
			String v_numeroPrincipalCT = f.getNumeroPrincipalCT();
			String v_nombreViaCT = f.getNombreViaCT();
			String v_codigoPostalCT = f.getCodigoPostalCT();
			String v_observacionDireccionCT = f.getObservacionDireccionCT();
			String v_barrioCT = f.getBarrioCT();
			String v_numeroPrincipalVacaciones = f.getNumeroPrincipalVacaciones();
			String v_nombreViaVacaciones = f.getNombreViaVacaciones();
			String v_observacionDireccionVacaciones = f.getObservacionDireccionVacaciones();
			String v_barrioVacaciones = f.getBarrioVacaciones();
			String v_telefonoReferencia = f.getTelefonoReferencia();
			String v_apellido1RefFamiliar = f.getApellido1RefFamiliar();
			String v_apellido2RefFamiliar = f.getApellido2RefFamiliar();
			String v_nombre1RefFamiliar = f.getNombre1RefFamiliar();
			String v_nombre2RefFamiliar = f.getNombre2RefFamiliar();
			String v_direccionRefFamiliar = f.getDireccionRefFamiliar();
			String v_barrioRefFamiliar = f.getBarrioRefFamiliar();
			String v_apellido1RefNoFamiliar = f.getApellido1RefNoFamiliar();
			String v_apellido2RefNoFamiliar = f.getApellido2RefNoFamiliar();
			String v_nombre1RefNoFamiliar = f.getNombre1RefNoFamiliar();
			String v_nombre2RefNoFamiliar = f.getNombre2RefNoFamiliar();
			String v_direccionRefNoFamiliar = f.getDireccionRefNoFamiliar();
			String v_barrioRefNoFamiliar = f.getBarrioRefNoFamiliar();
			String v_apellido1Aval = f.getApellido1Aval();
			String v_apellido2Aval = f.getApellido2Aval();
			String v_nombre1Aval = f.getNombre1Aval();
			String v_nombre2Aval = f.getNombre2Aval();
			String v_direccionAval = f.getDireccionAval();		
			String v_barrioAval = f.getBarrioAval();
			
			boolean b_apellidoPaterno = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellidoPaterno);
			boolean b_apellidoMaterno =  validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellidoMaterno);
			boolean b_apellidoCasada =  validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellidoCasada);
			boolean b_nombre1 =validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre1);
			boolean b_nombre2 = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre2);
			boolean b_numeroPrincipal = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_numeroPrincipal);
			boolean b_nombreVia = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombreVia);
			boolean b_codigoPostal = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_codigoPostal);
			boolean b_observacionDireccion = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_observacionDireccion);
			boolean b_barrio = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_barrio);
			boolean b_numeroPrincipalCT = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_numeroPrincipalCT);
			boolean b_nombreViaCT = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombreViaCT);
			boolean b_codigoPostalCT = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_codigoPostalCT);
			boolean b_observacionDireccionCT = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_observacionDireccionCT);
			boolean b_barrioCT = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_barrioCT);
			boolean b_numeroPrincipalVacaciones = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_numeroPrincipalVacaciones);
			boolean b_nombreViaVacaciones = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombreViaVacaciones);
			boolean b_observacionDireccionVacaciones = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_observacionDireccionVacaciones);
			boolean b_barrioVacaciones = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_barrioVacaciones);
			boolean b_telefonoReferencia = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_telefonoReferencia);
			boolean b_apellido1RefFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellido1RefFamiliar);
			boolean b_apellido2RefFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellido2RefFamiliar);
			boolean b_nombre1RefFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre1RefFamiliar);
			boolean b_nombre2RefFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre2RefFamiliar);
			boolean b_direccionRefFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_direccionRefFamiliar);
			boolean b_barrioRefFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_barrioRefFamiliar);
			boolean b_apellido1RefNoFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellido1RefNoFamiliar);
			boolean b_apellido2RefNoFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellido2RefNoFamiliar);
			boolean b_nombre1RefNoFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre1RefNoFamiliar);
			boolean b_nombre2RefNoFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre2RefNoFamiliar);
			boolean b_direccionRefNoFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_direccionRefNoFamiliar);
			boolean b_barrioRefNoFamiliar = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_barrioRefNoFamiliar);
			boolean b_apellido1Aval = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellido1Aval);
			boolean b_apellido2Aval = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_apellido2Aval);
			boolean b_nombre1Aval = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre1Aval);
			boolean b_nombre2Aval = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_nombre2Aval);
			boolean b_direccionAval = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_direccionAval);
			boolean b_barrioAval = validarTextoCaracteres(validarCaracteres1, cadenaCaracteres, v_barrioAval);

	 		if(b_apellidoPaterno==false) campos1 = campos1 + "," + d_apellidoPaterno;
			if(b_apellidoMaterno==false) campos1 = campos1 + "," + d_apellidoMaterno;
			if(b_apellidoCasada==false) campos1 = campos1 + "," + d_apellidoCasada;
			if(b_nombre1==false) campos1 = campos1 + "," + d_nombre1;
			if(b_nombre2==false) campos1 = campos1 + "," + d_nombre2;
			if(b_numeroPrincipal==false) campos1 = campos1 + "," + d_numeroPrincipal;
			if(b_nombreVia==false) campos1 = campos1 + "," + d_nombreVia;
			if(b_codigoPostal==false) campos1 = campos1 + "," + d_codigoPostal;
			if(b_observacionDireccion==false) campos1 = campos1 + "," + d_observacionDireccion;
			if(b_barrio==false) campos1 = campos1 + "," + d_barrio;
			if(b_numeroPrincipalCT==false) campos1 = campos1 + "," + d_numeroPrincipalCT;
			if(b_nombreViaCT==false) campos1 = campos1 + "," + d_nombreViaCT;
			if(b_codigoPostalCT==false) campos1 = campos1 + "," + d_codigoPostalCT;
			if(b_observacionDireccionCT==false) campos1 = campos1 + "," + d_observacionDireccionCT;
			if(b_barrioCT==false) campos1 = campos1 + "," + d_barrioCT;
			if(b_numeroPrincipalVacaciones==false) campos1 = campos1 + "," + d_numeroPrincipalVacaciones;
			if(b_nombreViaVacaciones==false) campos1 = campos1 + "," + d_nombreViaVacaciones;
			if(b_observacionDireccionVacaciones==false) campos1 = campos1 + "," + d_observacionDireccionVacaciones;
			if(b_barrioVacaciones==false) campos1 = campos1 + "," + d_barrioVacaciones;
			if(b_telefonoReferencia==false) campos1 = campos1 + "," + d_telefonoReferencia;
			if(b_apellido1RefFamiliar==false) campos1 = campos1 + "," + d_apellido1RefFamiliar;
			if(b_apellido2RefFamiliar==false) campos1 = campos1 + "," + d_apellido2RefFamiliar;
			if(b_nombre1RefFamiliar==false) campos1 = campos1 + "," + d_nombre1RefFamiliar;
			if(b_nombre2RefFamiliar==false) campos1 = campos1 + "," + d_nombre2RefFamiliar;
			if(b_direccionRefFamiliar==false) campos1 = campos1 + "," + d_direccionRefFamiliar;										
			if(b_barrioRefFamiliar==false) campos1 = campos1 + "," + d_barrioRefFamiliar;
			if(b_apellido1RefNoFamiliar==false) campos1 = campos1 + "," + d_apellido1RefNoFamiliar;
			if(b_apellido2RefNoFamiliar==false) campos1 = campos1 + "," + d_apellido2RefNoFamiliar;
			if(b_nombre1RefNoFamiliar==false) campos1 = campos1 + "," + d_nombre1RefNoFamiliar;
			if(b_nombre2RefNoFamiliar==false) campos1 = campos1 + "," + d_nombre2RefNoFamiliar;
			if(b_direccionRefNoFamiliar==false) campos1 = campos1 + "," + d_direccionRefNoFamiliar;										
			if(b_barrioRefNoFamiliar==false) campos1 = campos1 + "," + d_barrioRefNoFamiliar;
			if(b_apellido1Aval==false) campos1 = campos1 + "," + d_apellido1Aval;
			if(b_apellido2Aval==false) campos1 = campos1 + "," + d_apellido2Aval;
			if(b_nombre1Aval==false) campos1 = campos1 + "," + d_nombre1Aval;
			if(b_nombre2Aval==false) campos1 = campos1 + "," + d_nombre2Aval;
			if(b_direccionAval==false) campos1 = campos1 + "," + d_direccionAval;										
			if(b_barrioAval==false) campos1 = campos1 + "," + d_barrioAval;
			
			if(campos1.length()>1) campos1 = campos1.substring(1);		
		}	
		
		
		if (campos1.length() > 0) {
			if(validarCaracteres1.equals("CV"))
				campos1 = msgValidarCaracteresInicio + campos1 + msgValidarCaracteresFinal2 + " ";
			else	 
				campos1 = msgValidarCaracteresInicio + campos1 + msgValidarCaracteresFinal1 + " ";
				
			campos1 = campos1 + obtenerListaCaracteres(cadenaCaracteres);
			
			this.mensajeCaracteres1 = campos1;
			return false;
		}
		
		return true;	
	}
	
	boolean validarTextoCaracteres(String tipoValidacion, String cadenaCV, String texto) {
		if(tipoValidacion.equals("CV")) {
			return validarTextoCaracteresValidos(cadenaCV, texto);
		}
		else {
			return validarTextoCaracteresNoValidos(cadenaCV, texto);
		}
	}

	
	private boolean validarTextoCaracteresNoValidos(String cadenaCV, String texto) {
		String cadena = "__" + cadenaCV;
				
		if(texto==null)
			return true;
	
		for(int i=0; i<texto.length(); i++) {
			String aux = "__" + texto.codePointAt(i) + "__";
	
			if(cadena.indexOf(aux)>=0) {
				return false;
			}
		}
		
		return true;	
	}
	
	private boolean validarTextoCaracteresValidos(String cadenaCV, String texto) {
		String cadena = "__" + cadenaCV;
		
		if(texto==null)
			return true;
				
		for(int i=0; i<texto.length(); i++) {
			String aux = "__" + texto.codePointAt(i) + "__";
	
			if (cadena.indexOf(aux) < 0) {
				return false;
			}
		}
		
		return true;	
	}
	
	private String obtenerListaCaracteres(String codigoCaracteres) {
		String cadenaCaracteres ="";
		
		//validarCaracteres = validarCaracteres.substring(0, validarCaracteres.length-2);
		String[] listaCaracteres = codigoCaracteres.split("__");

		for(int i=0; i<listaCaracteres.length; i++) {
		   int aux = Integer.parseInt(listaCaracteres[i]);
		   if(aux > 20)
			   cadenaCaracteres = cadenaCaracteres + " " + String.valueOf((char)aux);
		}
		
		return cadenaCaracteres;		
	}	

	private boolean validarCamposEntradaCaracteres2(MantenimientoMAEModificacionClienteForm f) {
		String validarCaracteres2 = f.getValidarCaracteres2();
		String campos2 = "";
		String cadenaCaracteres ="";
		
		String msgValidarCaracteresInicio = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		String msgValidarCaracteresFinal2 = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal2");

		if (!(f.getValidarCaracteres2().equals("CV") || f.getValidarCaracteres2().equals("CNV"))) {
			return true;
		}

		if (f.getValidarCaracteres2().equals("CV") || f.getValidarCaracteres2().equals("CNV")) {
			if(f.getValidarCaracteres2().equals("CV"))
				cadenaCaracteres = f.getCadenaCaracteresV2(); 
			else	 
				cadenaCaracteres = f.getCadenaCaracteresNV2(); 

			String d_telefonoCasaDireccionEntrega = getResourceMessage("mantenimientoMAEClienteForm.telefonoCasaDireccionEntrega");
			String d_telefonoCelularDireccionEntrega = getResourceMessage("mantenimientoMAEClienteForm.telefonoCelularDireccionEntrega");
			String d_telefonoCasaDireccionVacaciones = getResourceMessage("mantenimientoMAEClienteForm.telefonoCasaDireccionVacaciones");
			String d_telefonoCelularDireccionVacaciones = getResourceMessage("mantenimientoMAEClienteForm.telefonoCelularDireccionVacaciones");
			String d_telefonoCasa = getResourceMessage("mantenimientoMAEClienteForm.telefonoCasa");
			String d_telefonoCelular = getResourceMessage("mantenimientoMAEClienteForm.telefonoCelular");
			String d_telefono = getResourceMessage("mantenimientoMAEClienteForm.telefono");
			String d_telefonoTrabajo = getResourceMessage("mantenimientoMAEModificacionClienteForm.telefonoTrabajo");
			String d_telefonoCasaRefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.telefonoCasaRefFamiliar");
			String d_telefonoCelRefFamiliar = getResourceMessage("mantenimientoMAEClienteForm.telefonoCelRefFamiliar");
			String d_telefonoCasaRefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.telefonoCasaRefNoFamiliar");
			String d_telefonoCelRefNoFamiliar = getResourceMessage("mantenimientoMAEClienteForm.telefonoCelRefNoFamiliar");
			String d_telefonoCasaAval = getResourceMessage("mantenimientoMAEClienteForm.telefonoCasaAval");
			String d_telefonoCelAval = getResourceMessage("mantenimientoMAEClienteForm.telefonoCelAval");

			String v_telefonoCasaDireccionEntrega = f.getTelefonoCasaDireccionEntrega();
			String v_telefonoCelularDireccionEntrega = f.getTelefonoCelularDireccionEntrega();
			String v_telefonoCasaDireccionVacaciones = f.getTelefonoCasaDireccionVacaciones();
			String v_telefonoCelularDireccionVacaciones = f.getTelefonoCelularDireccionVacaciones();
			String v_telefonoCasa = f.getTelefonoCasa();
			String v_telefonoCelular = f.getTelefonoCelular();
			//String v_telefono = f.getTelefono();
			//String v_telefonoTrabajo = TelefonoTrabajo();
			String v_telefonoCasaRefFamiliar = f.getTelefonoCasaRefFamiliar();
			String v_telefonoCelRefFamiliar = f.getTelefonoCelRefFamiliar();
			String v_telefonoCasaRefNoFamiliar = f.getTelefonoCasaRefNoFamiliar();
			String v_telefonoCelRefNoFamiliar = f.getTelefonoCelRefNoFamiliar();
			String v_telefonoCasaAval = f.getTelefonoCasaAval();
			String v_telefonoCelAval = f.getTelefonoCelAval();
			
			boolean b_telefonoCasaDireccionEntrega = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCasaDireccionEntrega);
			boolean b_telefonoCelularDireccionEntrega =  validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCelularDireccionEntrega);
			boolean b_telefonoCasaDireccionVacaciones = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCasaDireccionVacaciones);
			boolean b_telefonoCelularDireccionVacaciones =  validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCelularDireccionVacaciones);
			boolean b_telefonoCasa =  validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCasa);
			boolean b_telefonoCelular =validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCelular);
			//boolean b_telefono = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefono);
			//boolean b_telefonoTrabajo = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoTrabajo);
			boolean b_telefonoCasaRefFamiliar = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCasaRefFamiliar);
			boolean b_telefonoCelRefFamiliar = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCelRefFamiliar);
			boolean b_telefonoCasaRefNoFamiliar = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCasaRefNoFamiliar);
			boolean b_telefonoCelRefNoFamiliar = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCelRefNoFamiliar);
			boolean b_telefonoCasaAval = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCasaAval);
			boolean b_telefonoCelAval = validarTextoCaracteres(validarCaracteres2, cadenaCaracteres, v_telefonoCelAval);		
			

	 		if(b_telefonoCasaDireccionEntrega==false) campos2 = campos2 + "," + d_telefonoCasaDireccionEntrega;
			if(b_telefonoCelularDireccionEntrega==false) campos2 = campos2 + "," + d_telefonoCelularDireccionEntrega;
	 		if(b_telefonoCasaDireccionVacaciones==false) campos2 = campos2 + "," + d_telefonoCasaDireccionVacaciones;
			if(b_telefonoCelularDireccionVacaciones==false) campos2 = campos2 + "," + d_telefonoCelularDireccionVacaciones;
			if(b_telefonoCasa==false) campos2 = campos2+ "," + d_telefonoCasa;
			if(b_telefonoCelular==false) campos2 = campos2 + "," + d_telefonoCelular;
			//if(b_telefono==false) campos2 = campos2 + "," + d_telefono;
			//if(b_telefonoTrabajo==false) campos2 = campos2 + "," + d_telefonoTrabajo;
			if(b_telefonoCasaRefFamiliar==false) campos2 = campos2 + "," + d_telefonoCasaRefFamiliar;
			if(b_telefonoCelRefFamiliar==false) campos2 = campos2 + "," + d_telefonoCelRefFamiliar;
			if(b_telefonoCasaRefNoFamiliar==false) campos2 = campos2 + "," + d_telefonoCasaRefNoFamiliar;
			if(b_telefonoCelRefNoFamiliar==false) campos2 = campos2 + "," + d_telefonoCelRefNoFamiliar;
			if(b_telefonoCasaAval==false) campos2 = campos2 + "," + d_telefonoCasaAval;
			if(b_telefonoCelAval==false) campos2 = campos2 + "," + d_telefonoCelAval;						
		
			if(campos2.length()>1) campos2 = campos2.substring(1);		
		}	
		
		
		if (campos2.length() > 0) {
			if(validarCaracteres2.equals("CV"))
				campos2 = msgValidarCaracteresInicio + campos2 + msgValidarCaracteresFinal2 + " ";
			else	 
				campos2 = msgValidarCaracteresInicio + campos2 + msgValidarCaracteresFinal1 + " ";
			
			campos2 = campos2 + obtenerListaCaracteres(cadenaCaracteres);
			
			this.mensajeCaracteres2 = campos2;
			return false;
		}
		
		return true;	
	}

	public boolean validarCamposEntradaCaracteres3(MantenimientoMAEModificacionClienteForm f) {
		String validarCaracteres3 = f.getValidarCaracteres3();
		String campos3 = "";
		String cadenaCaracteres ="";
		
		String msgValidarCaracteresInicio = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresInicio");
		String msgValidarCaracteresFinal1 = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal1");
		String msgValidarCaracteresFinal2 = getResourceMessage("mantenimientoMAEClienteForm.msg.validarCaracteresFinal2");

		if (!(validarCaracteres3.equals("CV") || validarCaracteres3.equals("CNV"))) {
			return true;
		}

		if (validarCaracteres3.equals("CV") || validarCaracteres3.equals("CNV")) {
			if(validarCaracteres3.equals("CV"))
				cadenaCaracteres = f.getCadenaCaracteresV3();
			else	 
				cadenaCaracteres = f.getCadenaCaracteresNV3();

			String d_mail = getResourceMessage("mantenimientoMAEClienteForm.mail");

			String v_mail = f.getMail();

			boolean b_mail = validarTextoCaracteres(validarCaracteres3, cadenaCaracteres, v_mail);
	 		if(b_mail==false) campos3 = d_mail;
		}	

		if (campos3.length() > 0) {
			if(validarCaracteres3.equals("CV"))
				campos3 = msgValidarCaracteresInicio + campos3 + msgValidarCaracteresFinal2 + " ";
			else	 
				campos3 = msgValidarCaracteresInicio + campos3 + msgValidarCaracteresFinal1 + " ";
			
			campos3 = campos3 + obtenerListaCaracteres(cadenaCaracteres);
			
			this.mensajeCaracteres3 = campos3;
			return false;
		}
		
		return true;	
	}
	
	public void validaCodigoConsultoraLider() {
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		int longitud = Integer.parseInt(f.getLongitudCodigoCliente());
		int longitudActual = f.getCodigoLiderVinculo().length();

		int qCeros = longitud - longitudActual;
		String cantidadCeros = "";
		if (qCeros > 0) {
			for (int i = 0; i < qCeros; i++) {
				cantidadCeros += "0";
			}
		}
		f.setCodigoLiderVinculo(cantidadCeros + f.getCodigoLiderVinculo());
		String regex = "";
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		if (f.getCodigoLiderVinculo().length() > 0) {
			regex = ajaxService.getExisteCodigoCliente(f.getOidPais(),
					f.getCodigoLiderVinculo());
			if (regex.length() > 0) {
				String[] x = regex.split("\\|");

				f.setNombreLiderVinculo(x[1]);
				f.setOidLiderVinculo(x[2]);
			}
		}
	}

	public void validaCodigoConsultoraVinculo() {
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		int longitud = Integer.parseInt(f.getLongitudCodigoCliente());
		int longitudActual = f.getCodigoConsultoraVinculo().length();

		int qCeros = longitud - longitudActual;
		String cantidadCeros = "";
		if (qCeros > 0) {
			for (int i = 0; i < qCeros; i++) {
				cantidadCeros += "0";
			}
		}
		f.setCodigoConsultoraVinculo(cantidadCeros
				+ f.getCodigoConsultoraVinculo());
		String regex = "";
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		if (f.getCodigoConsultoraVinculo().length() > 0) {
			regex = ajaxService.getExisteCodigoCliente(f.getOidPais(),
					f.getCodigoConsultoraVinculo());
			if (regex.length() > 0) {
				String[] x = regex.split("\\|");

				f.setNombreConsultoraVinculo(x[1]);
				f.setOidConsultoraVinculo(x[2]);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}

		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.mostrarPopupConsultora = false;

		if (accion.equals("POPUP_CLIENTE")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
				f.setNombreLiderVinculo(cliente.get("nombre1")
						.toString() + " " + cliente.get("apellido1").toString());
				f.setCodigoLiderVinculo(cliente.get(
						"codigoCliente").toString());
				//
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String regex = ajaxService.getExisteCodigoCliente(
						f.getOidPais(),
						f.getCodigoLiderVinculo());
				if (regex.length() > 0) {
					String[] x = regex.split("\\|");

					f.setNombreLiderVinculo(x[1]);
					f.setOidLiderVinculo(x[2]);
				}
				//

				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
			}
		}
		
		if (accion.equals("POPUP_CONSULTORA")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
				f.setNombreConsultoraVinculo(cliente.get("nombre1").toString()
						+ " " + cliente.get("apellido1").toString());
				f.setCodigoConsultoraVinculo(cliente.get("codigoCliente")
						.toString());
				//
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String regex = ajaxService.getExisteCodigoCliente(
						f.getOidPais(), f.getCodigoConsultoraVinculo());
				if (regex.length() > 0) {
					String[] x = regex.split("\\|");

					f.setNombreConsultoraVinculo(x[1]);
					f.setOidConsultoraVinculo(x[2]);
				}
				//

				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'setAceptarPopup' method");
		}
	}

	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.mostrarPopupConsultora = false;
		this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals("POPUP_CLIENTE")) {
			this.mostrarPopupCliente = true;
		}
		if (accion.equals("POPUP_CONSULTORA")) {
			this.mostrarPopupConsultora = true;
		}
	}

	public void deleteDireccionPrincipal(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDireccionPrincipal' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		
		f.setBorradoDireccionPrincipal(true);
		this.setMostrarBotonEliminarDireccion(false);
		
		if(f.isMostrarTipoVia())
			f.setTipoVia("");

		if(f.isMostrarNumeroPrincipal())
			f.setNumeroPrincipal("");

		f.setNombreVia("");
		f.setObservacionDireccion("");

		/*if(f.isMostrarTipoVia())
			tipoVia.focus();
		else
			nombreVia.focus();	 */ 	
	}
	
	public void deleteTipoDocumentoIdentidad(String tipo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteTipoDocumentoIdentidad' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		
		if(tipo.equals("1")){
			f.setBorradoDocumentoIdentidad1(true);
			this.setMostrarBotonEliminarTipo1(false);			
			
			f.setNumeroDocumentoIdentidad("");
			f.setTipoDocumentoIdentidad("");
		
			//tipoDocumentoIdentidad1.focus();	
		}
		
		if(tipo.equals("2")){
			f.setBorradoDocumentoIdentidad2(true);
			this.setMostrarBotonEliminarTipo2(false);
			
			f.setNumeroDocumentoIdentidad2("");
			f.setTipoDocumentoIdentidad2("");
		
			//tipoDocumentoIdentidad2.focus();	
		}
		
	}

	public void deleteDireccionOpcional(ActionEvent e) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDireccionOpcional' method");
		}

		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;
		
		f.setBorradoDireccionOpcional(true);
		this.setMostrarBotonEliminarDireccionCT(false);
		
		if(f.isMostrarTipoVia())
			f.setTipoViaCT("");

		if(f.isMostrarNumeroPrincipal())
			f.setNumeroPrincipalCT("");

		f.setNombreViaCT("");
		f.setObservacionDireccionCT("");

		//tipoDireccionCT.focus();  	
	}


	/**
	 * @return the busquedaConsultorasAction
	 */
	public BusquedaConsultorasAction getBusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}

	/**
	 * @param busquedaConsultorasAction the busquedaConsultorasAction to set
	 */
	public void setBusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	public LabelValue[] getMaeClienteNivel2AVAList() {
		return maeClienteNivel2AVAList;
	}

	public void setMaeClienteNivel2AVAList(LabelValue[] maeClienteNivel2AVAList) {
		this.maeClienteNivel2AVAList = maeClienteNivel2AVAList;
	}

	public LabelValue[] getMaeClienteNivel3AVAList() {
		return maeClienteNivel3AVAList;
	}

	public void setMaeClienteNivel3AVAList(LabelValue[] maeClienteNivel3AVAList) {
		this.maeClienteNivel3AVAList = maeClienteNivel3AVAList;
	}

	public LabelValue[] getMaeClienteNivel4AVAList() {
		return maeClienteNivel4AVAList;
	}

	public void setMaeClienteNivel4AVAList(LabelValue[] maeClienteNivel4AVAList) {
		this.maeClienteNivel4AVAList = maeClienteNivel4AVAList;
	}

	public LabelValue[] getMaeCiudadAVAList() {
		return maeCiudadAVAList;
	}

	public void setMaeCiudadAVAList(LabelValue[] maeCiudadAVAList) {
		this.maeCiudadAVAList = maeCiudadAVAList;
	}

	/**
	 * @return the mostrarBotonEliminarDireccion
	 */
	public boolean isMostrarBotonEliminarDireccion() {
		return mostrarBotonEliminarDireccion;
	}

	/**
	 * @param mostrarBotonEliminarDireccion the mostrarBotonEliminarDireccion to set
	 */
	public void setMostrarBotonEliminarDireccion(
			boolean mostrarBotonEliminarDireccion) {
		this.mostrarBotonEliminarDireccion = mostrarBotonEliminarDireccion;
	}

	/**
	 * @return the maeCiudadList
	 */
	public LabelValue[] getMaeCiudadList() {
		return maeCiudadList;
	}

	/**
	 * @param maeCiudadList the maeCiudadList to set
	 */
	public void setMaeCiudadList(LabelValue[] maeCiudadList) {
		this.maeCiudadList = maeCiudadList;
	}

	public List getMaeTipoPersonaList() {
		return maeTipoPersonaList;
	}

	public void setMaeTipoPersonaList(List maeTipoPersonaList) {
		this.maeTipoPersonaList = maeTipoPersonaList;
	}

	public List getMaeOrigenIngresoList() {
		return maeOrigenIngresoList;
	}

	public void setMaeOrigenIngresoList(List maeOrigenIngresoList) {
		this.maeOrigenIngresoList = maeOrigenIngresoList;
	}

	/**
	 * @return the copiaDireccionEntrega
	 */
	public String getCopiaDireccionEntrega() {
		return copiaDireccionEntrega;
	}

	/**
	 * @param copiaDireccionEntrega the copiaDireccionEntrega to set
	 */
	public void setCopiaDireccionEntrega(String copiaDireccionEntrega) {
		this.copiaDireccionEntrega = copiaDireccionEntrega;
	}

	/**
	 * @return the siccSubTipoDocumentoList
	 */
	public List getSiccSubTipoDocumentoList() {
		return siccSubTipoDocumentoList;
	}

	/**
	 * @param siccSubTipoDocumentoList the siccSubTipoDocumentoList to set
	 */
	public void setSiccSubTipoDocumentoList(List siccSubTipoDocumentoList) {
		this.siccSubTipoDocumentoList = siccSubTipoDocumentoList;
	}

	/**
	 * @return the siccSubTipoDocumentoList2
	 */
	public List getSiccSubTipoDocumentoList2() {
		return siccSubTipoDocumentoList2;
	}

	/**
	 * @param siccSubTipoDocumentoList2 the siccSubTipoDocumentoList2 to set
	 */
	public void setSiccSubTipoDocumentoList2(List siccSubTipoDocumentoList2) {
		this.siccSubTipoDocumentoList2 = siccSubTipoDocumentoList2;
	}
	

	/**
	 * @return the mostrarBotonEliminarTipo1
	 */
	public boolean isMostrarBotonEliminarTipo1() {
		return mostrarBotonEliminarTipo1;
	}

	/**
	 * @param mostrarBotonEliminarTipo1 the mostrarBotonEliminarTipo1 to set
	 */
	public void setMostrarBotonEliminarTipo1(boolean mostrarBotonEliminarTipo1) {
		this.mostrarBotonEliminarTipo1 = mostrarBotonEliminarTipo1;
	}

	/**
	 * @return the mostrarBotonEliminarTipo2
	 */
	public boolean isMostrarBotonEliminarTipo2() {
		return mostrarBotonEliminarTipo2;
	}

	/**
	 * @param mostrarBotonEliminarTipo2 the mostrarBotonEliminarTipo2 to set
	 */
	public void setMostrarBotonEliminarTipo2(boolean mostrarBotonEliminarTipo2) {
		this.mostrarBotonEliminarTipo2 = mostrarBotonEliminarTipo2;
	}

	/**
	 * @return the mostrarBotonEliminarDireccionCT
	 */
	public boolean isMostrarBotonEliminarDireccionCT() {
		return mostrarBotonEliminarDireccionCT;
	}

	/**
	 * @param mostrarBotonEliminarDireccionCT the mostrarBotonEliminarDireccionCT to set
	 */
	public void setMostrarBotonEliminarDireccionCT(
			boolean mostrarBotonEliminarDireccionCT) {
		this.mostrarBotonEliminarDireccionCT = mostrarBotonEliminarDireccionCT;
	}

	/**
	 * @return the maeBancoList
	 */
	public List getMaeBancoList() {
		return maeBancoList;
	}

	/**
	 * @param maeBancoList the maeBancoList to set
	 */
	public void setMaeBancoList(List maeBancoList) {
		this.maeBancoList = maeBancoList;
	}

	/**
	 * @return the showBancoCuenta
	 */
	public boolean isShowBancoCuenta() {
		return showBancoCuenta;
	}

	/**
	 * @param showBancoCuenta the showBancoCuenta to set
	 */
	public void setShowBancoCuenta(boolean showBancoCuenta) {
		this.showBancoCuenta = showBancoCuenta;
	}

	/**
	 * @return the indicadorImpresionPaqDocBoolean
	 */
	public boolean isIndicadorImpresionPaqDocBoolean() {
		return indicadorImpresionPaqDocBoolean;
	}

	/**
	 * @param indicadorImpresionPaqDocBoolean the indicadorImpresionPaqDocBoolean to set
	 */
	public void setIndicadorImpresionPaqDocBoolean(
			boolean indicadorImpresionPaqDocBoolean) {
		this.indicadorImpresionPaqDocBoolean = indicadorImpresionPaqDocBoolean;
	}

	/**
	 * @return the maeClienteCodigoTerritorialCorrespondeList
	 */
	public List getMaeClienteCodigoTerritorialCorrespondeList() {
		return maeClienteCodigoTerritorialCorrespondeList;
	}

	/**
	 * @param maeClienteCodigoTerritorialCorrespondeList the maeClienteCodigoTerritorialCorrespondeList to set
	 */
	public void setMaeClienteCodigoTerritorialCorrespondeList(List maeClienteCodigoTerritorialCorrespondeList) {
		this.maeClienteCodigoTerritorialCorrespondeList = maeClienteCodigoTerritorialCorrespondeList;
	}

	/**
	 * @return the maeTipoCuentaList
	 */
	public List getMaeTipoCuentaList() {
		return maeTipoCuentaList;
	}

	/**
	 * @param maeTipoCuentaList the maeTipoCuentaList to set
	 */
	public void setMaeTipoCuentaList(List maeTipoCuentaList) {
		this.maeTipoCuentaList = maeTipoCuentaList;
	}

	/**
	 * @return the showTipoCuentaCuentaCorriente
	 */
	public boolean isShowTipoCuentaCuentaCorriente() {
		return showTipoCuentaCuentaCorriente;
	}

	/**
	 * @param showTipoCuentaCuentaCorriente the showTipoCuentaCuentaCorriente to set
	 */
	public void setShowTipoCuentaCuentaCorriente(boolean showTipoCuentaCuentaCorriente) {
		this.showTipoCuentaCuentaCorriente = showTipoCuentaCuentaCorriente;
	}
}
