package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACalendarioService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACalendarioForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGrupoZonaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCRACalendarioAction extends
		BaseMantenimientoSearchAbstractAction {
	private static final long serialVersionUID = 8584703116370067386L;

	private boolean mostrarPopUpCliente = false;
	private static final String POPCALENDARIO = "CALENDARIO";

	private Object feriadosSeleccionados;
	private List craListaFechaNoLaborables01 = new ArrayList();
	private List craListaFechaNoLaborables02 = new ArrayList();
	private List craListaFechaNoLaborables03 = new ArrayList();
	private List craListaFechaNoLaborables04 = new ArrayList();
	private List craListaFechaNoLaborables05 = new ArrayList();
	private List craListaFechaNoLaborables06 = new ArrayList();
	private List craListaFechaNoLaborables07 = new ArrayList();
	private List craListaFechaNoLaborables08 = new ArrayList();
	private List craListaFechaNoLaborables09 = new ArrayList();
	private List craListaFechaNoLaborables10 = new ArrayList();
	private List craListaFechaNoLaborables11 = new ArrayList();
	private List craListaFechaNoLaborables12 = new ArrayList();
	private List craListaFechaNoLaborables13 = new ArrayList();
	private List craListaFechaNoLaborables14 = new ArrayList();
	private List craListaFechaNoLaborables15 = new ArrayList();
	private List craListaFechaNoLaborables16 = new ArrayList();
	private List craListaFechaNoLaborables17 = new ArrayList();
	private List craListaFechaNoLaborables18 = new ArrayList();
	private DataTableModel listaEstructuraArchivoModel;
	private List craListaFechaFeriado = new ArrayList();
	private List craListaFechaNoLaborables = new ArrayList();
	private List siccActividadList = new ArrayList();

	private Object[] columnasSeleccionadas;

	private String seleccionoRegistros = Constants.NUMERO_CERO;

	private boolean valorTrue = true;
	private boolean valorFalse = false;

	@ManagedProperty(value = "#{mantenimientoCRACalendarioCopiaPopupAction}")
	private MantenimientoCRACalendarioCopiaPopupAction mantenimientoCRACalendarioCopiaPopupAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;
		if (accion.equals(this.POPCALENDARIO)) {
			this.mostrarPopUpCliente = true;
			this.mantenimientoCRACalendarioCopiaPopupAction.setAnhio(f
					.getAnhio());
			this.mantenimientoCRACalendarioCopiaPopupAction.setOidActividad(f
					.getCodigoActividad());
			for (int i = 0; i < this.siccActividadList.size(); i++) {
				Base base = (Base) this.siccActividadList.get(i);
				if (StringUtils
						.equals(f.getCodigoActividad(), base.getCodigo())) {
					this.mantenimientoCRACalendarioCopiaPopupAction
							.setNombreActividad(base.getDescripcion());
					break;
				}
			}
			this.mantenimientoCRACalendarioCopiaPopupAction.obtenerValores();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCRACalendarioForm searchForm = new MantenimientoCRACalendarioForm();
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
			log.debug("Entering 'find' method");
		}

		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());

		this.craListaFechaFeriado.clear();
		this.craListaFechaNoLaborables.clear();

		criteria.put("indicadorFestivo", "1");
		criteria.put("indicadorLaborable", "0");
		this.craListaFechaFeriado = service.getCalendarioFeriados(criteria);

		criteria.put("indicadorFestivo", "0");
		criteria.put("indicadorLaborable", "");

		this.asignarValores(criteria);

		return this.craListaFechaFeriado;
	}

	/**
	 * Asigna valores a cada tabla
	 * 
	 * @param m
	 */
	private void asignarValores(Map m) {

		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;
		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		StringBuilder b = new StringBuilder();
		b.append(f.getAnhio());
		// periodo 1

		b.append("01");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables01 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables01);

		b.replace(4, 6, "02");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables02 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables02);

		b.replace(4, 6, "03");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables03 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables03);

		b.replace(4, 6, "04");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables04 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables04);

		b.replace(4, 6, "05");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables05 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables05);

		b.replace(4, 6, "06");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables06 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables06);

		b.replace(4, 6, "07");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables07 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables07);

		b.replace(4, 6, "08");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables08 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables08);

		b.replace(4, 6, "09");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables09 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables09);

		b.replace(4, 6, "10");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables10 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables10);

		b.replace(4, 6, "11");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables11 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables11);

		b.replace(4, 6, "12");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables12 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables12);

		b.replace(4, 6, "13");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables13 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables13);

		b.replace(4, 6, "14");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables14 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables14);

		b.replace(4, 6, "15");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables15 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables15);

		b.replace(4, 6, "16");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables16 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables16);

		b.replace(4, 6, "17");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables17 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables17);

		b.replace(4, 6, "18");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables18 = service.getCalendarioFeriados(m);
		setearValorBooleano(craListaFechaNoLaborables18);

	}

	public void setearValorBooleano(List lista) {
		for (int i = 0; i < lista.size(); i++) {
			Map data = (Map) lista.get(i);
			if (data.get("indNoLabo").toString().equals("1"))
				data.put("valorBooleano", "true");
			else
				data.put("valorBooleano", "false");
			
			if (data.get("indTrap").toString().equals("1"))
				data.put("valorBooleano2", "true");
			else
				data.put("valorBooleano2", "false");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
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

		/* *********** METODO ADD ******** */
		MantenimientoCRAGrupoZonaForm f = new MantenimientoCRAGrupoZonaForm();

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
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonSave = false;
		this.mostrarListaBusqueda = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		if (f.getAnhio() == null)
			f.setAnhio(anhio);

		f.setCodigoActividad("");
		f.setCodigoPais(pais.getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// obteniendo las lista de grupode facturacion y actividad
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);

		Map params = new HashMap();
		params.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		params.put(
				"oidPais",
				new Long(reporteService.getOidString("getOidPaisByCodigoPais",
						params)));
		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);

		this.siccActividadList = reporteService.getActividad(params);
		if (this.siccActividadList != null && this.siccActividadList.size() > 0) {
			Base base = (Base) this.siccActividadList.get(0);
			f.setCodigoActividad(base.getCodigo());
		}

	}

	/**
	 * @param
	 * @throws Exception
	 */
	public void generarDiasLaborables(String method) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'generarDiasLaborables' method");
		}

		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());

		String id = method;
		if (id.equals("generarSabados")) {
			criteria.put("fecha", Constants.CRA_DIA_SABADO);
		}
		if (id.equals("generarDomingos")) {
			criteria.put("fecha", Constants.CRA_DIA_DOMINGO);
		}

		service.insertCalendarioDiaNoLaborable(criteria);
		criteria.put("indicadorFestivo", "0");
		criteria.put("indicadorLaborable", "");
		asignarValores(criteria);
		addInfo("Mensaje",
				getResourceMessage("mantenimientoCRACalendarioForm.msj.registrar"));

	}

	/**
	 * @param method
	 */
	public void deleteDiasLaborables(String method) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'delete' method");
		}

		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());

		String id = method;
		if (id.equals("deleteSabados")) {
			criteria.put("fecha", Constants.CRA_DIA_SABADO);
		}
		if (id.equals("deleteDomingos")) {
			criteria.put("fecha", Constants.CRA_DIA_DOMINGO);
		}

		service.deleteDiaNoLaborable(criteria);
		criteria.put("indicadorFestivo", "0");
		criteria.put("indicadorLaborable", "");
		asignarValores(criteria);
		this.addInfo(
				"Mensaje",
				getResourceMessage("mantenimientoCRACalendarioForm.msj.deleteDiaNoLaborable"));
	}

	/**
	 * 
	 */
	public void grabarFeriados() {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'add' method");
		}
		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) this.formBusqueda;
		f.setFecha(DateUtil.getDate(f.getFechaD()));
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("fecha", f.getFecha());
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());
		int result = service.insertFeriados(criteria);
		criteria.put("indicadorFestivo", "1");
		criteria.put("indicadorLaborable", "0");
		this.craListaFechaFeriado = service.getCalendarioFeriados(criteria);
		if (result != 0) {
			this.addInfo(
					"Mensaje",
					getResourceMessage("mantenimientoCRACalendarioForm.msj.existeFecha"));
		} else {
			this.addInfo(
					"Mensaje",
					getResourceMessage("mantenimientoCRACalendarioForm.msj.registrar"));
		}
	}

	/**
	 * 
	 */
	public void eliminarFeriados() {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'remove' method");
		}
		log.debug("setDeleteAttributes");

		Map feriados = (Map) this.feriadosSeleccionados;
		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		Map criteria = new HashMap();

		String id = feriados.get("fecha").toString();

		if (id != null) {

			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}

			try {

				criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser()
						.getLogin());
				criteria.put("fecha", id);
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("anhio", f.getAnhio());
				criteria.put("indicadorFestivo", "1");
				criteria.put("indicadorLaborable", "0");
				criteria.put("codigoActividad", f.getCodigoActividad());
				service.deleteFeriado(criteria);
				this.feriadosSeleccionados = null;
				this.craListaFechaFeriado = service
						.getCalendarioFeriados(criteria);
				this.addInfo("Mensaje", "Feriado eliminado correctamente");
			} catch (Exception e) {
				this.addError("Mensaje", obtieneMensajeErrorException(e));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCRAGrupoZonaForm cobradorForm = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCRAGrupoZonaForm.add.success";
		} else {
			return "mantenimientoCRAGrupoZonaForm.update.success";
		}
	}

	public void definirUA(ActionEvent event) throws IOException {
		String ventana = "PF('dialogGrabarCalendar').show()";
		this.getRequestContext().execute(ventana);
	}

	public void updateIndNoLaborable() {
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String data = externalContext.getRequestParameterMap().get("data");
		String campania = data.split(";")[0];
		String posicion = data.split(";")[1];
		String valorBooleano = data.split(";")[2];
		String anio = f.getAnhio();
		String codActividad = f.getCodigoActividad();
		String codigoPais = f.getCodigoPais();
		String usuario = mPantallaPrincipalBean.getCurrentUser().getLogin();
		String indNoLaborable = "";
		String fecha = "";
		if (valorBooleano.equals("true"))
			indNoLaborable = "1";
		else
			indNoLaborable = "0";
		String indTransporte = "";
		List listaTemp = getLista(campania);
		for (int i = 0; i < listaTemp.size(); i++) {
			Map map = (Map) listaTemp.get(i);
			if (i == Integer.parseInt(posicion)) {
				fecha = (String) map.get("fecha");
				break;
			}

		}
		ajax.updateIndCalendario(anio, codActividad, fecha, indNoLaborable,
				indTransporte, codigoPais, usuario);
	}

	public void updateIndTransportista() {
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String data = externalContext.getRequestParameterMap().get("data");
		String campania = data.split(";")[0];
		String posicion = data.split(";")[1];
		String valorBooleano = data.split(";")[2];
		String anio = f.getAnhio();
		String codActividad = f.getCodigoActividad();
		String codigoPais = f.getCodigoPais();
		String usuario = mPantallaPrincipalBean.getCurrentUser().getLogin();
		String indTransporte = "";
		String fecha = "";
		if (valorBooleano.equals("true"))
			indTransporte = "1";
		else
			indTransporte = "0";
		String indNoLaborable = "";
		List listaTemp = getLista(campania);
		for (int i = 0; i < listaTemp.size(); i++) {
			Map map = (Map) listaTemp.get(i);
			if (i == Integer.parseInt(posicion)) {
				fecha = (String) map.get("fecha");
				break;
			}

		}
		ajax.updateIndCalendario(anio, codActividad, fecha, indNoLaborable,
				indTransporte, codigoPais, usuario);
	}

	public List getLista(String campania) {
		List listaTemp = new ArrayList();
		if (campania.equals("01"))
			listaTemp = this.craListaFechaNoLaborables01;
		if (campania.equals("02"))
			listaTemp = this.craListaFechaNoLaborables02;
		if (campania.equals("03"))
			listaTemp = this.craListaFechaNoLaborables03;
		if (campania.equals("04"))
			listaTemp = this.craListaFechaNoLaborables04;
		if (campania.equals("05"))
			listaTemp = this.craListaFechaNoLaborables05;
		if (campania.equals("06"))
			listaTemp = this.craListaFechaNoLaborables06;
		if (campania.equals("07"))
			listaTemp = this.craListaFechaNoLaborables07;
		if (campania.equals("08"))
			listaTemp = this.craListaFechaNoLaborables08;
		if (campania.equals("09"))
			listaTemp = this.craListaFechaNoLaborables09;
		if (campania.equals("10"))
			listaTemp = this.craListaFechaNoLaborables10;
		if (campania.equals("11"))
			listaTemp = this.craListaFechaNoLaborables11;
		if (campania.equals("12"))
			listaTemp = this.craListaFechaNoLaborables12;
		if (campania.equals("13"))
			listaTemp = this.craListaFechaNoLaborables13;
		if (campania.equals("14"))
			listaTemp = this.craListaFechaNoLaborables14;
		if (campania.equals("15"))
			listaTemp = this.craListaFechaNoLaborables15;
		if (campania.equals("16"))
			listaTemp = this.craListaFechaNoLaborables16;
		if (campania.equals("17"))
			listaTemp = this.craListaFechaNoLaborables17;
		if (campania.equals("18"))
			listaTemp = this.craListaFechaNoLaborables18;
		return listaTemp;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables01() {
		return craListaFechaNoLaborables01;
	}

	/**
	 * @param craListaFechaNoLaborables01
	 */
	public void setCraListaFechaNoLaborables01(List craListaFechaNoLaborables01) {
		this.craListaFechaNoLaborables01 = craListaFechaNoLaborables01;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables02() {
		return craListaFechaNoLaborables02;
	}

	/**
	 * @param craListaFechaNoLaborables02
	 */
	public void setCraListaFechaNoLaborables02(List craListaFechaNoLaborables02) {
		this.craListaFechaNoLaborables02 = craListaFechaNoLaborables02;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables03() {
		return craListaFechaNoLaborables03;
	}

	/**
	 * @param craListaFechaNoLaborables03
	 */
	public void setCraListaFechaNoLaborables03(List craListaFechaNoLaborables03) {
		this.craListaFechaNoLaborables03 = craListaFechaNoLaborables03;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables04() {
		return craListaFechaNoLaborables04;
	}

	/**
	 * @param craListaFechaNoLaborables04
	 */
	public void setCraListaFechaNoLaborables04(List craListaFechaNoLaborables04) {
		this.craListaFechaNoLaborables04 = craListaFechaNoLaborables04;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables05() {
		return craListaFechaNoLaborables05;
	}

	/**
	 * @param craListaFechaNoLaborables05
	 */
	public void setCraListaFechaNoLaborables05(List craListaFechaNoLaborables05) {
		this.craListaFechaNoLaborables05 = craListaFechaNoLaborables05;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables06() {
		return craListaFechaNoLaborables06;
	}

	/**
	 * @param craListaFechaNoLaborables06
	 */
	public void setCraListaFechaNoLaborables06(List craListaFechaNoLaborables06) {
		this.craListaFechaNoLaborables06 = craListaFechaNoLaborables06;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables07() {
		return craListaFechaNoLaborables07;
	}

	/**
	 * @param craListaFechaNoLaborables07
	 */
	public void setCraListaFechaNoLaborables07(List craListaFechaNoLaborables07) {
		this.craListaFechaNoLaborables07 = craListaFechaNoLaborables07;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables08() {
		return craListaFechaNoLaborables08;
	}

	/**
	 * @param craListaFechaNoLaborables08
	 */
	public void setCraListaFechaNoLaborables08(List craListaFechaNoLaborables08) {
		this.craListaFechaNoLaborables08 = craListaFechaNoLaborables08;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables09() {
		return craListaFechaNoLaborables09;
	}

	/**
	 * @param craListaFechaNoLaborables09
	 */
	public void setCraListaFechaNoLaborables09(List craListaFechaNoLaborables09) {
		this.craListaFechaNoLaborables09 = craListaFechaNoLaborables09;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables10() {
		return craListaFechaNoLaborables10;
	}

	/**
	 * @param craListaFechaNoLaborables10
	 */
	public void setCraListaFechaNoLaborables10(List craListaFechaNoLaborables10) {
		this.craListaFechaNoLaborables10 = craListaFechaNoLaborables10;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables11() {
		return craListaFechaNoLaborables11;
	}

	/**
	 * @param craListaFechaNoLaborables11
	 */
	public void setCraListaFechaNoLaborables11(List craListaFechaNoLaborables11) {
		this.craListaFechaNoLaborables11 = craListaFechaNoLaborables11;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables12() {
		return craListaFechaNoLaborables12;
	}

	/**
	 * @param craListaFechaNoLaborables12
	 */
	public void setCraListaFechaNoLaborables12(List craListaFechaNoLaborables12) {
		this.craListaFechaNoLaborables12 = craListaFechaNoLaborables12;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables13() {
		return craListaFechaNoLaborables13;
	}

	/**
	 * @param craListaFechaNoLaborables13
	 */
	public void setCraListaFechaNoLaborables13(List craListaFechaNoLaborables13) {
		this.craListaFechaNoLaborables13 = craListaFechaNoLaborables13;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables14() {
		return craListaFechaNoLaborables14;
	}

	/**
	 * @param craListaFechaNoLaborables14
	 */
	public void setCraListaFechaNoLaborables14(List craListaFechaNoLaborables14) {
		this.craListaFechaNoLaborables14 = craListaFechaNoLaborables14;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables15() {
		return craListaFechaNoLaborables15;
	}

	/**
	 * @param craListaFechaNoLaborables15
	 */
	public void setCraListaFechaNoLaborables15(List craListaFechaNoLaborables15) {
		this.craListaFechaNoLaborables15 = craListaFechaNoLaborables15;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables16() {
		return craListaFechaNoLaborables16;
	}

	/**
	 * @param craListaFechaNoLaborables16
	 */
	public void setCraListaFechaNoLaborables16(List craListaFechaNoLaborables16) {
		this.craListaFechaNoLaborables16 = craListaFechaNoLaborables16;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables17() {
		return craListaFechaNoLaborables17;
	}

	/**
	 * @param craListaFechaNoLaborables17
	 */
	public void setCraListaFechaNoLaborables17(List craListaFechaNoLaborables17) {
		this.craListaFechaNoLaborables17 = craListaFechaNoLaborables17;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables18() {
		return craListaFechaNoLaborables18;
	}

	/**
	 * @param craListaFechaNoLaborables18
	 */
	public void setCraListaFechaNoLaborables18(List craListaFechaNoLaborables18) {
		this.craListaFechaNoLaborables18 = craListaFechaNoLaborables18;
	}

	/**
	 * @return
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return
	 */
	public String getSeleccionoRegistros() {
		return seleccionoRegistros;
	}

	/**
	 * @param seleccionoRegistros
	 */
	public void setSeleccionoRegistros(String seleccionoRegistros) {
		this.seleccionoRegistros = seleccionoRegistros;
	}

	/**
	 * @return
	 */
	public Object getFeriadosSeleccionados() {
		return feriadosSeleccionados;
	}

	/**
	 * @param feriadosSeleccionados
	 */
	public void setFeriadosSeleccionados(Object feriadosSeleccionados) {
		this.feriadosSeleccionados = feriadosSeleccionados;
	}

	/**
	 * @return
	 */
	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	/**
	 * @param mostrarPopUpCliente
	 */
	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	/**
	 * @return
	 */
	public MantenimientoCRACalendarioCopiaPopupAction getMantenimientoCRACalendarioCopiaPopupAction() {
		return mantenimientoCRACalendarioCopiaPopupAction;
	}

	/**
	 * @param mantenimientoCRACalendarioCopiaPopupAction
	 */
	public void setMantenimientoCRACalendarioCopiaPopupAction(
			MantenimientoCRACalendarioCopiaPopupAction mantenimientoCRACalendarioCopiaPopupAction) {
		this.mantenimientoCRACalendarioCopiaPopupAction = mantenimientoCRACalendarioCopiaPopupAction;
	}

	/**
	 * @return
	 */
	public List getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @param siccActividadList
	 */
	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaFeriado() {
		return craListaFechaFeriado;
	}

	/**
	 * @param craListaFechaFeriado
	 */
	public void setCraListaFechaFeriado(List craListaFechaFeriado) {
		this.craListaFechaFeriado = craListaFechaFeriado;
	}

	/**
	 * @return
	 */
	public List getCraListaFechaNoLaborables() {
		return craListaFechaNoLaborables;
	}

	/**
	 * @param craListaFechaNoLaborables
	 */
	public void setCraListaFechaNoLaborables(List craListaFechaNoLaborables) {
		this.craListaFechaNoLaborables = craListaFechaNoLaborables;
	}

	/**
	 * @return
	 */
	public static String getPopcalendario() {
		return POPCALENDARIO;
	}

	/**
	 * @return
	 */
	public boolean isValorTrue() {
		return valorTrue;
	}

	/**
	 * @param valorTrue
	 */
	public void setValorTrue(boolean valorTrue) {
		this.valorTrue = valorTrue;
	}

	/**
	 * @return
	 */
	public boolean isValorFalse() {
		return valorFalse;
	}

	/**
	 * @param valorFalse
	 */
	public void setValorFalse(boolean valorFalse) {
		this.valorFalse = valorFalse;
	}

	/**
	 * @return
	 */
	public DataTableModel getListaEstructuraArchivoModel() {
		return listaEstructuraArchivoModel;
	}

	/**
	 * @param listaEstructuraArchivoModel
	 */
	public void setListaEstructuraArchivoModel(
			DataTableModel listaEstructuraArchivoModel) {
		this.listaEstructuraArchivoModel = listaEstructuraArchivoModel;
	}
}
