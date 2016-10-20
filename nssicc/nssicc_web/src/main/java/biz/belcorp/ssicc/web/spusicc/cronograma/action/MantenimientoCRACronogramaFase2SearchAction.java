package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACronogramaFase1Service;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAGrupoZonaService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAPeriodoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACronogramaFase2PopupForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACronogramaFase2SearchForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAPeriodoForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoMAEClienteForm;

@SessionScoped
@ManagedBean
public class MantenimientoCRACronogramaFase2SearchAction extends
		BaseMantenimientoSearchAbstractAction {
	private List craCargaCronoFase2List = new ArrayList();
	private List craCargaCronoFase2FueraList = new ArrayList();
	private String fechaInicio;
	private String numeroColumnas;
	private String periodo;
	private String periodoSeleccionado;
	private List craZonaAsignadas;
	
	private LabelValue[] craZonaRegenerarNuevo;
	private LabelValue[] craZonaRegenerar;
	private LabelValue[] siccActividadList;
	private String ordenDesplazado;
	private String codigoPeriodo;
	private List craGrupoZonaList;
	private LabelValue[] craZonaList;
	private MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm formPorZona;

	public String getOrdenDesplazado() {
		return ordenDesplazado;
	}

	public void setOrdenDesplazado(String ordenDesplazado) {
		this.ordenDesplazado = ordenDesplazado;
	}

	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNumeroColumnas() {
		return numeroColumnas;
	}

	public void setNumeroColumnas(String numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List getCraCargaCronoFase2List() {
		return craCargaCronoFase2List;
	}

	public void setCraCargaCronoFase2List(List craCargaCronoFase2List) {
		this.craCargaCronoFase2List = craCargaCronoFase2List;
	}

	public List getCraCargaCronoFase2FueraList() {
		return craCargaCronoFase2FueraList;
	}

	public void setCraCargaCronoFase2FueraList(List craCargaCronoFase2FueraList) {
		this.craCargaCronoFase2FueraList = craCargaCronoFase2FueraList;
	}

	public List getCraGrupoZonaList() {
		return craGrupoZonaList;
	}

	public void setCraGrupoZonaList(List craGrupoZonaList) {
		this.craGrupoZonaList = craGrupoZonaList;
	}

	public LabelValue[] getCraZonaList() {
		return craZonaList;
	}

	public void setCraZonaList(LabelValue[] craZonaList) {
		this.craZonaList = craZonaList;
	}

	/**
	 * 
	 */

	public void openActividadPopup(ActionEvent event) {

		log.debug("Executing action : setViewAttributes. ");

		MantenimientoCRACronogramaFase2PopupForm f = new MantenimientoCRACronogramaFase2PopupForm();
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String id = externalContext.getRequestParameterMap().get("DATOS");
		log.debug("----- id ----- " + id);

		String periodo = codigoPeriodo;
		String oidZona = StringUtils.split(id, "|")[0];
		String nombreActividad = StringUtils.split(id, "|")[1];

		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
		f.setOidZona(oidZona);
		f.setNombreActividad(nombreActividad);
		f.setFechaDesplazada("");
		f.setDiasDesplazamiento("");
		f.setCodigoPeriodo(periodo);

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("oidZona", oidZona);
		criteria.put("nombreActividad", nombreActividad);
		criteria.put("periodo", periodo);

		List lista = service.getDatosCronoFase2(criteria);

		if (lista.size() > 0) {
			f.setNombreActividad((String) ((Map) lista.get(0))
					.get("nombreActividad"));
			f.setFechaInicio((String) ((Map) lista.get(0)).get("fechaInicio"));
			f.setIndicadorLaborable(((BigDecimal) ((Map) lista.get(0))
					.get("indicadorLaborable")).toString());
			f.setOidCronogramaFase2(((BigDecimal) ((Map) lista.get(0))
					.get("oid")).toString());
			f.setNombreZona((String) ((Map) lista.get(0)).get("nombreZona"));
			f.setDiasDesplazamiento(((BigDecimal) ((Map) lista.get(0))
					.get("desplazamiento")).toString());
			f.setFecha((String) ((Map) lista.get(0)).get("fechaPrevista"));
			f.setOidGrupoZona(((BigDecimal) ((Map) lista.get(0))
					.get("oidGrupoZona")).toString());

			if ((BigDecimal) ((Map) lista.get(0)).get("oidActividadOrigen") != null) {

				String oidactividadOrigen = ((BigDecimal) ((Map) lista.get(0))
						.get("oidActividadOrigen")).toString();

				criteria.put("oidActividad", oidactividadOrigen);
				criteria.put("nombreActividad", null);
				List listaOrigen = service.getDatosCronoFase2(criteria);

				f.setNombreActividadOrigen((String) ((Map) listaOrigen.get(0))
						.get("nombreActividad"));
				f.setFechaActividadOrigen((String) ((Map) listaOrigen.get(0))
						.get("fechaPrevista"));

			} else {
				f.setNombreActividadOrigen("");
				f.setFechaActividadOrigen("");
			}
		}
		setOrdenDesplazado(null);
		this.formMantenimiento = f;
		this.getRequestContext().execute(
				"PF('viewCronogramaFase2Popup').show()");

	}

	public void openActividadPopupPorZona(ActionEvent event) {
		log.debug("Executing action : setViewAttributes. ");
		MantenimientoCRACronogramaFase2SearchForm form = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		try {
			if (form.getCodigoPeriodo().equals(""))
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoPeriodo"));
			else if (form.getGrupoZonaList() == null
					|| form.getGrupoZonaList().length == 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoGrupoZonaNoSelecc"));
			else {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String data = aSvc.getCronogramaBRValidarPeriodoExistente(form
						.getCodigoPeriodo());
				if (data.equals("0"))
					throw new Exception(
							this.getResourceMessage("mantenimientoRECCronogramaBRForm.error.codigoPeriodo"));

			}

			MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm f = (MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm) this.formPorZona;
			f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo());

			MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

			f.setCodigoPeriodo(form.getCodigoPeriodo());
			f.setOidGrupoZona(form.getGrupoZonaList()[0]);

			Map criteria = new HashMap();
			criteria.put("oidGrupo", form.getGrupoZonaList()[0]);

			List list = service.getGrupos(criteria);

			f.setNombreGrupoZona(((Map) list.get(0)).get("descripcion")
					.toString());

			String grupoZonaList[] = { f.getOidGrupoZona() };
			criteria.put("grupoZonaList", grupoZonaList);

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			// obteniendo las lista de grupode facturacion y actividad
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			Long oidMarca = clienteService
					.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
			Long oidCanal = clienteService
					.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);

			Map params = new HashMap();
			params.put("codigoPais", f.getCodigoPais());
			params.put(
					"oidPais",
					new Long(reporteService.getOidString(
							"getOidPaisByCodigoPais", params)));
			params.put("oidMarca", oidMarca);
			params.put("oidCanal", oidCanal);

			this.craZonaAsignadas = service.getZonaAsignadasGrupo(criteria); // para
																				// asignar
																				// zonas
																				// disponibles
			List listActividad = reporteService.getActividad(params);
			this.siccActividadList = new LabelValue[listActividad.size()];
			int i = 0;
			for (Object object : listActividad) {
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base) object).getDescripcion());
				labelValue.setValue(((Base) object).getCodigo());
				this.getSiccActividadList()[i] = labelValue;
				i++;
			}
			f.setZonaReferencia(null);
			f.setZonaRegenerar(null);
			f.setActividades(null);
			
			this.craZonaRegenerar = this.craZonaRegenerarNuevo;
			this.getRequestContext().execute("PF('viewCronogramaPorZonaFase2Popup').show()");
		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm f = (MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm) this.formPorZona;
		MantenimientoCRACronogramaFase2PopupForm form = (MantenimientoCRACronogramaFase2PopupForm) this.formMantenimiento;
		if (accion.equals("INSERTAR_POPUP")) {
			// ############### VALIDAR CAMPOS REQUERIDOS #######################
			if (StringUtils.isBlank(f.getZonaReferencia()))
				return this
						.getResourceMessage("mantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm.msg.zonaReferenciaNoSelecc");
			if (f.getZonaRegenerar() == null
					|| f.getZonaRegenerar().length == 0)
				return this
						.getResourceMessage("mantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm.msg.zonaRegenerarNoSelecc");
			if (f.getActividades() == null || f.getActividades().length == 0)
				return this
						.getResourceMessage("mantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm.msg.actividadesNoSelecc");

		} else if (accion.equals("INSERTAR")) {
			if (StringUtils.isBlank(form.getDiasDesplazamiento()))
				return this
						.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.desplazamiento");
		}
		return null;
	}

	public void grabarCopia(ActionEvent event) {
		log.debug("Executing action : setSaveAttributes. ");

		MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm f = (MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm) this.formPorZona;
		MantenimientoCRACronogramaFase2SearchForm form = (MantenimientoCRACronogramaFase2SearchForm) this.formBusqueda;
		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {
			String codigoUsuario = usuario.getLogin();

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("zonaReferencia", f.getZonaReferencia());
			criteria.put("zonaRegenerarList",
					Arrays.asList(f.getZonaRegenerar()));
			criteria.put("actividades", Arrays.asList(f.getActividades()));
			criteria.put("usuario", codigoUsuario);

			service.copiaCronogramaPorZonaFase2(criteria);
			buscar(form);
			addInfo("Mensaje",
					this.getResourceMessage("mantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm.msj.registrar"));

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	private static final long serialVersionUID = 3235563050175383094L;
	private List craPeriodoCorporativoList;

	public List getCraPeriodoCorporativoList() {
		return craPeriodoCorporativoList;
	}

	public void actualizarFecha() throws ParseException {
		MantenimientoCRACronogramaFase2PopupForm f = (MantenimientoCRACronogramaFase2PopupForm) formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String fechaDesplazada = ajax.getActuaFechaFase2(pais.getCodigo(),
				f.getOidCronogramaFase2(), f.getOidGrupoZona(),
				f.getDiasDesplazamiento());
		this.setOrdenDesplazado(fechaDesplazada);
	}

	public void setCraPeriodoCorporativoList(List craPeriodoCorporativoList) {
		this.craPeriodoCorporativoList = craPeriodoCorporativoList;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCRACronogramaFase2SearchForm searchForm = new MantenimientoCRACronogramaFase2SearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	public void search(ActionEvent event) throws Exception {

		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		try {

			if (StringUtils.isBlank(f.getCodigoPeriodo()))
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoPeriodo"));
			else if (f.getZonaList() == null || f.getZonaList().length == 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoZona"));

			buscar(f);

		} catch (Exception e) {
			// TODO: handle exception
			addError("Mensaje", obtieneMensajeErrorException(e));
		}
	}

	public void buscar(MantenimientoCRACronogramaFase2SearchForm f) {
		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");

		Map criteria = new HashMap();
		try {
			this.codigoPeriodo = f.getCodigoPeriodo();
			criteria.put("codigoPais", mPantallaPrincipalBean
					.getCurrentCountry().getCodigo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("cantidadColumnas", "");
			
			criteria.put("zonaList", new ArrayList());
			if (f.getZonaList() != null) {
			   try {
				   String zona = f.getZonaList()[0];
				   if (StringUtils.isNotBlank(zona)) {
					   criteria.put("zonaList",  Arrays.asList(f.getZonaList()));
			        }
			   }
			   catch(Exception ex) {
			   }
			   
			}
			
			criteria.put("oidZona", new ArrayList());
			if (f.getGrupoZonaList() != null) {
				try {
				   String zona = f.getGrupoZonaList()[0];
				   if (StringUtils.isNotBlank(zona) && !StringUtils.equals("T", zona)) {
					   criteria.put("oidZona",  Arrays.asList(f.getGrupoZonaList()));
				   }
				}
				catch(Exception ex) {
			   }
			}
			

			List listaCronoZona = service.getCargaCronogramaFase2(criteria);

			if ((listaCronoZona == null) || (listaCronoZona.size() == 0))
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Form.msg.noExiste"));
			else
				pintaCronograma(criteria.get("fechaInicio").toString(),
						criteria.get("cantidadColumnas").toString(),
						listaCronoZona, f.getCodigoPeriodo());
		} catch (Exception e) {
			// TODO: handle exception
			addError("Mensaje", obtieneMensajeErrorException(e));
		}

	}

	public void busqueda() {

		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String data = aSvc.getCronogramaBRValidarPeriodoExistente(f
				.getCodigoPeriodo());
		try {

			if (StringUtils.isBlank(f.getCodigoPeriodo()))
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoPeriodo"));
			else if (f.getZonaList() == null || f.getZonaList().length == 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoZona"));

			else if (data.equals("0"))
				throw new Exception(
						this.getResourceMessage("mantenimientoRECCronogramaBRForm.error.codigoPeriodo"));

			buscar(f);
		} catch (Exception e) {
			// TODO: handle exception
			addError("Mensaje", obtieneMensajeErrorException(e));
		}
	}

	public void buscarZonasPorGrupo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		String[] zonaList = {};
		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		f.setZonaList(zonaList);
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			String valor[] = (String[]) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			craZonaList = ajax.getZonasGrupo(valor);

		} else {
			craZonaList = null;
		}
	}

	public static Date sumaDias(Date fecha, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_YEAR, dias);
		return cal.getTime();
	}

	public void generar(ActionEvent event) {

		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {
			String codigoUsuario = usuario.getLogin();
			Map criteria = new HashMap();

			criteria.put("codigoPais", mPantallaPrincipalBean
					.getCurrentCountry().getCodigo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put(
					"zonaList",
					(f.getZonaList() == null) ? new ArrayList()
							: ((String) (f.getZonaList()[0]).toString())
									.equals("T") ? new ArrayList() : Arrays
									.asList(f.getZonaList()));
			criteria.put("usuario", codigoUsuario);
			criteria.put(
					"oidZona",
					(f.getGrupoZonaList() == null) ? new ArrayList()
							: ((String) (f.getGrupoZonaList()[0]).toString())
									.equals("T") ? new ArrayList() : Arrays
									.asList(f.getGrupoZonaList()));
			criteria.put("cantidadColumnas", "");

			List listaCronoZona = service.generarCronogramaFase2(criteria);

			pintaCronograma(criteria.get("fechaInicio").toString(), criteria
					.get("cantidadColumnas").toString(), listaCronoZona,
					f.getCodigoPeriodo());

			addInfo("Mensaje",
					getResourceMessage("mantenimientoCRACronogramaFase2Form.msg.generar"));
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	private void pintaCronograma(String fechaIniPerio, String numColumnas,
			List listaCronoZona, String periodo) {

		List listaCronoFechaDentro = new ArrayList();
		List listaCronoFechaFuera = new ArrayList();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Date fechaIni = null;

		String zonaAnt = "";
		String oidZonaAnt = "";
		Map cronoZona = new HashMap();
		Map cronoFechaIni = new HashMap();

		String actividades[] = new String[Integer.parseInt(numColumnas)];
		log.debug("----> " + actividades.length);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoSalida = new SimpleDateFormat("EEEE dd MMMM",
				new Locale("es"));

		try {
			fechaIni = formato.parse(fechaIniPerio);
		} catch (Exception ex) {

		}

		// primera fila cabecera fechas
		cronoFechaIni.put("zona", "Zona");
		for (int i = 0; i < Integer.parseInt(numColumnas); i++) {

			actividades[i] = formatoSalida.format(sumaDias(fechaIni, i));
		}
		cronoFechaIni.put("actividades", actividades);
		listaCronoFechaDentro.add(cronoFechaIni);

		// Resto de filas actividades
		actividades = new String[Integer.parseInt(numColumnas)];

		HashMap params = new HashMap();

		params.put("zona", "xxx");
		params.put("oidZona", "xxx");

		listaCronoZona.add(params);

		for (int i = 0; i < listaCronoZona.size(); i++) {

			cronoZona = (Map) listaCronoZona.get(i);

			if ((!oidZonaAnt.equals(cronoZona.get("oidZona").toString()))
					&& (i != 0)) {
				// quiebre

				Map cronoFecha = new HashMap();
				cronoFecha.put("actividades", actividades);
				cronoFecha.put("oidZona", oidZonaAnt);
				cronoFecha.put("zona", zonaAnt);
				listaCronoFechaDentro.add(cronoFecha);
				actividades = new String[Integer.parseInt(numColumnas) + 1];

			}
			if (!cronoZona.get("zona").toString().equals("xxx")) {
				int orden = Integer.parseInt(cronoZona.get("orden").toString());
				if ((orden >= 0) && (orden < Integer.parseInt(numColumnas))) {
					actividades[orden] = cronoZona.get("actividad").toString();
				} else {
					String[] actividad = StringUtils.split(
							cronoZona.get("actividad").toString(), "|");

					for (int j = 0; j < actividad.length; j++) {

						Map cronoFechaFuera = new HashMap();

						cronoFechaFuera
								.put("oidZona", cronoZona.get("oidZona"));
						cronoFechaFuera.put("zona", cronoZona.get("zona"));
						cronoFechaFuera.put("fecha", cronoZona.get("fecha"));
						cronoFechaFuera.put("orden", cronoZona.get("orden"));
						cronoFechaFuera.put("nombreActividad", actividad[j]);

						listaCronoFechaFuera.add(cronoFechaFuera);
					}

				}

				zonaAnt = cronoZona.get("zona").toString();
				oidZonaAnt = cronoZona.get("oidZona").toString();
			}

		}
		craCargaCronoFase2FueraList = null;
		craCargaCronoFase2List = null;

		this.fechaInicio = null;
		this.numeroColumnas = null;
		this.periodo = null;

		this.fechaInicio = fechaIniPerio;
		this.periodo = periodo;
		this.craCargaCronoFase2List = listaCronoFechaDentro;
		this.craCargaCronoFase2FueraList = listaCronoFechaFuera;
		this.numeroColumnas = numColumnas;

		for (int i = 0; i < craCargaCronoFase2List.size(); i++) {
			// Creo la lista actividad2 para no perjudicar la lógica
			List actividades2 = new ArrayList();
			Map map = (Map) craCargaCronoFase2List.get(i);
			String[] act = (String[]) map.get("actividades");
			for (int j = 0; j < act.length; j++) {
				List lista = new ArrayList();
				Map map2 = new HashMap();
				List listaActividades = new ArrayList();
				Map map3 = new HashMap();
				String actividad = act[j];
				if (actividad == null)
					actividad = "";
				StringTokenizer st = new StringTokenizer(actividad, "|");
				while (st.hasMoreTokens())
					listaActividades.add(st.nextToken());

				map3.put("listaB", listaActividades);
				map3.put("index", "" + j + "");
				lista.add(map3);

				map2.put("lista", lista);
				actividades2.add(map2);
			}

			map.put("actividades2", actividades2);
		}
		this.numeroColumnas = Integer
				.toString(Integer.parseInt(numColumnas) - 1);

	}

	public void eliminar(ActionEvent event) {
		String codigoUsuario = mPantallaPrincipalBean.getCurrentUser()
				.getLogin();

		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
		try {
			Map criteria = new HashMap();

			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put(
					"zonaList",
					(f.getZonaList() == null) ? new ArrayList()
							: ((String) (f.getZonaList()[0]).toString())
									.equals("T") ? new ArrayList() : Arrays
									.asList(f.getZonaList()));

			service.deleteCronogramaFase2(criteria);

			craCargaCronoFase2List = null;
			craCargaCronoFase2FueraList = null;
			f.setZonaList(null);
			f.setGrupoZonaList(null);
			addInfo("Mensaje",
					getResourceMessage("mantenimientoCRACronogramaFase2Form.msg.delete"));
		} catch (Exception e) {
			// TODO: handle exception
			addError("Mensaje", obtieneMensajeErrorException(e));
		}

	}

	public void cargarZonas(ValueChangeEvent val) {
		MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm f = (MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm) this.formPorZona;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		this.craZonaRegenerar = ajax.getZonasGrupoRegenerar(
				f.getOidGrupoZona(), valor);
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		return true;
	}

	public void grabar(ActionEvent e) throws IOException {

		log.debug("Executing action : setSaveAttributes. ");

		MantenimientoCRACronogramaFase2PopupForm f = (MantenimientoCRACronogramaFase2PopupForm) formMantenimiento;
		MantenimientoCRACronogramaFase2SearchForm form = (MantenimientoCRACronogramaFase2SearchForm) this.formBusqueda;
		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");

		String codigoUsuario = mPantallaPrincipalBean.getCurrentUser()
				.getCodigo();

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("oidCrono", f.getOidCronogramaFase2());
		criteria.put("oidGrupoZona", f.getOidGrupoZona());
		criteria.put("diasDesplazamiento", f.getDiasDesplazamiento());
		criteria.put("usuario", codigoUsuario);

		service.updateCronogramaFase2(criteria);
		addInfo("Mensaje",
				getResourceMessage("mantenimientoCRAPeriodoForm.msj.registrar"));
		buscar(form);
		this.redireccionarPagina("mantenimientoCRACronogramaFase2SearchForm");

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("setAddAttributes method ....");
		mostrarBotonConsultar = false;
		mostrarBotonEliminar = false;
		mostrarBotonModificar = false;
		mostrarBotonNuevo = false;
		mostrarBotonBuscar = false;
		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) formBusqueda;
		log.debug("setAddAttributes method ....");

		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());

		Map criteria = new HashMap();
		MantenimientoCRAGrupoZonaService serviceGrupo = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

		criteria.put("oidGrupo", "");
		criteria.put("estado", "1");

		craGrupoZonaList = serviceGrupo.getGrupos(criteria);

		f.setCodigoPeriodo("");
		craCargaCronoFase2List = null;

		craCargaCronoFase2FueraList = null;
		fechaInicio = null;
		numeroColumnas = null;
		periodo = null;

		MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm fzona = new MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm();
		formPorZona = fzona;
		MantenimientoCRACronogramaFase2PopupForm formMant = new MantenimientoCRACronogramaFase2PopupForm();
		formMantenimiento = formMant;

	}

	public void recalcular(String duracion, String codigoPeriodo) {

		// String name= (String) ((UIOutput) vce.getSource()).getValue();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		Calendar calendar = Calendar.getInstance();

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date fechaTemp = new Date();
			try {
				fechaTemp = formatoDeFecha
						.parse(((PeriodoCronograma) listaBusqueda.get(i))
								.getFechaInicio());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en parsear la fecha" + i);
			}
			calendar.setTime(fechaTemp);
			calendar.add(Calendar.DAY_OF_YEAR, Integer
					.parseInt(((PeriodoCronograma) listaBusqueda.get(i))
							.getDuracion()) - 1);
			String fechaFin = DateUtil.getDate(calendar.getTime());
			((PeriodoCronograma) listaBusqueda.get(i)).setFechaFin(fechaFin);
			if (i + 1 < listaBusqueda.size()) {

				// tengo que agregar que le sume los dias de duracion del
				// dia siguiente
				try {
					Date fechaIniSig = formatoDeFecha
							.parse(((PeriodoCronograma) listaBusqueda.get(i))
									.getFechaFin());
					calendar.setTime(fechaIniSig);
					calendar.add(Calendar.DATE, 1);

					((PeriodoCronograma) listaBusqueda.get(i + 1))
							.setFechaInicio(DateUtil.getDate(calendar.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en parsear la fecha" + i);
				}

			}
		}
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

	}

	/**
	 * 
	 */
	public void enviarFormulario(ActionEvent event) {
		MantenimientoCRACronogramaFase2SearchForm f = (MantenimientoCRACronogramaFase2SearchForm) this.formBusqueda;
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String accion = externalContext.getRequestParameterMap().get(
				"parametroAccion");
		try {
			this.periodo = f.getCodigoPeriodo();
			if (periodo.equals(""))
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoPeriodo"));
			else if (f.getZonaList() == null || f.getZonaList().length == 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoZona"));
			else {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String data = aSvc.getCronogramaBRValidarPeriodoExistente(f
						.getCodigoPeriodo());
				if (data.equals("0"))
					throw new Exception(
							this.getResourceMessage("mantenimientoRECCronogramaBRForm.error.codigoPeriodo"));
				else {
					if (accion.equals("EXECUTE"))
						this.getRequestContext()
								.execute(
										"PF('confirmDialog_confirmationDialogConfirmar').show()");
					else if (accion.equals("ELIMINAR"))
						this.getRequestContext()
								.execute(
										"PF('confirmDialogEliminar_confirmationDialogConfirmar').show()");
				}
			}
		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}
	}

	public void recalcularFechaInicio(String fechaInicioSelected,
			String fechaFinSelected, String codigoPeriodo)
			throws ParseException {

		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date dtInicioArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaInicio());
			Date dtFinArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaFin());

			long dif = dtFinArray.getTime() - dtInicioArray.getTime();

			Long duracion = dif / (1000L * 60L * 60L * 24L);
			((PeriodoCronograma) listaBusqueda.get(i)).setDuracion((duracion
					.toString()));

			if (i + 1 < this.listaBusqueda.size()) {
				// obtengo fecha final actual para sumarle uno y añadirle al
				// siguiente registro
				Calendar c = Calendar.getInstance();
				c.setTime(dtFinArray);
				c.add(Calendar.DATE, 1);
				// le añado uno y seteo la fecha inicio del siguiente registro
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaInicio(DateUtil.getDate(c.getTime()));
				// sig como utilitario para rapido acceso
				PeriodoCronograma sig = ((PeriodoCronograma) listaBusqueda
						.get(i + 1));
				c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
				c.add(Calendar.DATE, Integer.parseInt(sig.getDuracion()));
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaFin(DateUtil.getDate(c.getTime()));
			}

		}

	}

	public void recalcularFechaFin(String fechaInicioSelected,
			String fechaFinSelected, String codigoPeriodo)
			throws ParseException {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date dtInicioArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaInicio());
			Date dtFinArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaFin());

			int duracionPeriodo = Integer
					.parseInt(((PeriodoCronograma) listaBusqueda.get(i))
							.getDuracion()) - 1;
			// aumento la fecha actual sumandole la duracion del periodo
			Calendar cr = Calendar.getInstance();
			cr.setTime(dtInicioArray);
			cr.add(Calendar.DATE, duracionPeriodo);
			((PeriodoCronograma) listaBusqueda.get(i)).setFechaFin(DateUtil
					.getDate(cr.getTime()));

			if (i + 1 < this.listaBusqueda.size()) {
				// obtengo fecha final actual para sumarle uno y añadirle al
				// siguiente registro
				Calendar c = Calendar.getInstance();
				c.setTime(formatoDeFecha
						.parse(((PeriodoCronograma) listaBusqueda.get(i))
								.getFechaFin()));
				c.add(Calendar.DAY_OF_YEAR, 1);
				// le añado uno y seteo la fecha inicio del siguiente registro
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaInicio(DateUtil.getDate(c.getTime()));
				// sig como utilitario para rapido acceso

				PeriodoCronograma sig = ((PeriodoCronograma) listaBusqueda
						.get(i + 1));
				c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
				c.add(Calendar.DATE, Integer.parseInt(sig.getDuracion()));
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaFin(DateUtil.getDate(c.getTime()));
			}

		}

	}

	public List getCraZonaAsignadas() {
		return craZonaAsignadas;
	}

	public void setCraZonaAsignadas(List craZonaAsignadas) {
		this.craZonaAsignadas = craZonaAsignadas;
	}

	public LabelValue[] getCraZonaRegenerar() {
		return craZonaRegenerar;
	}

	public void setCraZonaRegenerar(LabelValue[] craZonaRegenerar) {
		this.craZonaRegenerar = craZonaRegenerar;
	}

	public LabelValue[] getSiccActividadList() {
		return siccActividadList;
	}

	public void setSiccActividadList(LabelValue[] siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	public MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm getFormPorZona() {
		return formPorZona;
	}

	public void setFormPorZona(
			MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm formPorZona) {
		this.formPorZona = formPorZona;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}
