package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACronogramaFase1Service;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACronogramaFase1PopupForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACronogramaFase1SearchForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCBonificacionPeriodoForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCRACronogramaFase1SearchAction extends
		BaseMantenimientoSearchAbstractAction {
	private List craCargaCronoFase1List = new ArrayList();
	private List craCargaCronoFase1FueraList = new ArrayList();
	private String fechaInicio;
	private String numeroColumnas;
	private String periodo;
	private String periodoSeleccionado;

	private String ordenDesplazado;
	private String ordenActividadOrigen;


	/**
	 * @return
	 */
	public String getOrdenDesplazado() {
		return ordenDesplazado;
	}

	/**
	 * @param ordenDesplazado
	 */
	public void setOrdenDesplazado(String ordenDesplazado) {
		this.ordenDesplazado = ordenDesplazado;
	}

	/**
	 * @return
	 */
	public String getOrdenActividadOrigen() {
		return ordenActividadOrigen;
	}

	/**
	 * @param ordenActividadOrigen
	 */
	public void setOrdenActividadOrigen(String ordenActividadOrigen) {
		this.ordenActividadOrigen = ordenActividadOrigen;
	}

	/**
	 * @return
	 */
	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	/**
	 * @param periodoSeleccionado
	 */
	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

	/**
	 * @return
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return
	 */
	public String getNumeroColumnas() {
		return numeroColumnas;
	}

	/**
	 * @param numeroColumnas
	 */
	public void setNumeroColumnas(String numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}

	/**
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public List getCraCargaCronoFase1List() {
		return craCargaCronoFase1List;
	}

	/**
	 * @param craCargaCronoFase1List
	 */
	public void setCraCargaCronoFase1List(List craCargaCronoFase1List) {
		this.craCargaCronoFase1List = craCargaCronoFase1List;
	}

	/**
	 * @return
	 */
	public List getCraCargaCronoFase1FueraList() {
		return craCargaCronoFase1FueraList;
	}

	/**
	 * @param craCargaCronoFase1FueraList
	 */
	public void setCraCargaCronoFase1FueraList(List craCargaCronoFase1FueraList) {
		this.craCargaCronoFase1FueraList = craCargaCronoFase1FueraList;
	}

	/**
	 * @param event
	 */
	public void openActividadPopup(ActionEvent event) {
		log.debug("Executing action : setViewAttributes. ");

		MantenimientoCRACronogramaFase1PopupForm f = (MantenimientoCRACronogramaFase1PopupForm)this.formMantenimiento;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String id = externalContext.getRequestParameterMap().get("DATOS");
		log.debug("----- id ----- " + id);

		String periodo = StringUtils.split(id, "|")[0];
		String fechaInicio = StringUtils.split(id, "|")[1];
		String oidGrupoZona = StringUtils.split(id, "|")[2];
		String nombreActividad = StringUtils.split(id, "|")[3];
		String orden = StringUtils.split(id, "|")[4]; // respecto inicio de
														// periodo

		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
		f.setOidGrupoZona(oidGrupoZona);
		f.setNombreActividad(nombreActividad);
		f.setFechaDesplazada("");
		f.setDiasDesplazamiento("");
		f.setCodigoPeriodo(periodo);

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("oidGrupoZona", oidGrupoZona);
		criteria.put("nombreActividad", nombreActividad);
		criteria.put("periodo", periodo);

		List lista = service.getDatosCronoFase1(criteria);

		if (lista.size() > 0) {
			f.setNombreActividad((String) ((Map) lista.get(0))
					.get("nombreActividad"));
			f.setOidCronogramaFase1(((BigDecimal) ((Map) lista.get(0))
					.get("oid")).toString());
			f.setNombreGrupoZona((String) ((Map) lista.get(0))
					.get("nombreGrupo"));
			f.setDiasDesplazamiento(((BigDecimal) ((Map) lista.get(0))
					.get("desplazamiento")).toString());
			f.setFecha((String) ((Map) lista.get(0)).get("fechaPrevista"));
			f.setIndicadorLaborable(((BigDecimal) ((Map) lista.get(0))
					.get("indicadorLaborable")).toString());

			if ((BigDecimal) ((Map) lista.get(0)).get("oidActividadOrigen") != null) {

				String oidactividadOrigen = ((BigDecimal) ((Map) lista.get(0))
						.get("oidActividadOrigen")).toString();

				criteria.put("oidActividad", oidactividadOrigen);
				criteria.put("nombreActividad", null);
				List listaOrigen = service.getDatosCronoFase1(criteria);

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
				"PF('viewCronogramaFase1Popup').show()");
	}

	private static final long serialVersionUID = 3235563050175383094L;
	private List craPeriodoCorporativoList;

	/**
	 * @return
	 */
	public List getCraPeriodoCorporativoList() {
		return craPeriodoCorporativoList;
	}

	/**
	 * @throws ParseException
	 */
	public void actualizarFecha() throws ParseException {

		MantenimientoCRACronogramaFase1PopupForm f = (MantenimientoCRACronogramaFase1PopupForm) formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
        String fechaDesplazada = ajax.getActuaFechaFase1(pais.getCodigo(), f.getOidCronogramaFase1(), f.getOidGrupoZona(), f.getDiasDesplazamiento());
        this.setOrdenDesplazado(fechaDesplazada);
	}

	/**
	 * @param craPeriodoCorporativoList
	 */
	public void setCraPeriodoCorporativoList(List craPeriodoCorporativoList) {
		this.craPeriodoCorporativoList = craPeriodoCorporativoList;
	}

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
		MantenimientoCRACronogramaFase1SearchForm searchForm = new MantenimientoCRACronogramaFase1SearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		log.debug("find");
		MantenimientoCRACronogramaFase1SearchForm f = (MantenimientoCRACronogramaFase1SearchForm) formBusqueda;
		MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
		// ActionMessages messages = new ActionMessages();

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("cantidadColumnas", "");

		List listaCronoGrupoZona = service.getCargaCronogramaFase1(criteria);

		pintaCronograma(criteria.get("fechaInicio").toString(),
				criteria.get("cantidadColumnas").toString(),
				listaCronoGrupoZona, f.getCodigoPeriodo());

		return this.craPeriodoCorporativoList;
	}

	/**
	 * Reemplaza al search
	 */
	public void search() {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			log.debug("find");
			MantenimientoCRACronogramaFase1SearchForm f = (MantenimientoCRACronogramaFase1SearchForm) this.formBusqueda;
			MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");
			// ActionMessages messages = new ActionMessages();

			Map criteria = new HashMap();

			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("cantidadColumnas", "");

			List listaCronoGrupoZona = service.getCargaCronogramaFase1(criteria);

			pintaCronograma(criteria.get("fechaInicio").toString(),
					criteria.get("cantidadColumnas").toString(),
					listaCronoGrupoZona, f.getCodigoPeriodo());

			this.addInfo("Mensaje",
					getResourceMessage("mantenimientoCRACronogramaFase1Form.msg.generar"));
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param fecha
	 * @param dias
	 * @return
	 */
	public static Date sumaDias(Date fecha, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_YEAR, dias);
		return cal.getTime();
	}

	/**
	 * 
	 */
	public void generar() {
		try {
			log.debug("generar Cliente");

			String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser()
					.getLogin();

			MantenimientoCRACronogramaFase1SearchForm f = (MantenimientoCRACronogramaFase1SearchForm) this.formBusqueda;
			MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");

			Map criteria = new HashMap();

			criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("usuario", codigoUsuario);
			criteria.put("cantidadColumnas", "");

			List listaCronoGrupoZona = service.generarCronogramaFase1(criteria);

			pintaCronograma(criteria.get("fechaInicio").toString(),
					criteria.get("cantidadColumnas").toString(),
					listaCronoGrupoZona, f.getCodigoPeriodo());
			this.addInfo("Mensaje",
					getResourceMessage("mantenimientoCRACronogramaFase1Form.msg.generar"));
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param fechaIniPerio
	 * @param numColumnas
	 * @param listaCronoGrupoZona
	 * @param periodo
	 */
	private void pintaCronograma(String fechaIniPerio, String numColumnas,
			List listaCronoGrupoZona, String periodo) {

		List listaCronoFechaDentro = new ArrayList();
		List listaCronoFechaFuera = new ArrayList();

		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Date fechaIni = null;

		String grupoZonaAnt = "";
		String oidGrupoZonaAnt = "";
		Map cronoGrupoZona = new HashMap();
		Map cronoFechaIni = new HashMap();

		String actividades[] = new String[Integer.parseInt(numColumnas)];
		log.debug("----> " + actividades.length);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoSalida = new SimpleDateFormat("EEEE dd MMMM",
				new Locale("es"));

		try {
			fechaIni = formato.parse(fechaIniPerio);
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));

		}
		log.debug("-- fecha incio -> " + fechaIni.toString());

		// primera fila cabecera fechas
		cronoFechaIni.put("grupoZona", "Grupo Zona");
		for (int i = 0; i < Integer.parseInt(numColumnas); i++) {

			actividades[i] = formatoSalida.format(sumaDias(fechaIni, i));
		}
		cronoFechaIni.put("actividades", actividades);
		listaCronoFechaDentro.add(cronoFechaIni);

		// Resto de filas actividades
		actividades = new String[Integer.parseInt(numColumnas)];

		HashMap params = new HashMap();

		params.put("oidGrupoZona", "xxx");
		params.put("grupoZona", "xxx");

		listaCronoGrupoZona.add(params);

		for (int i = 0; i < listaCronoGrupoZona.size(); i++) {

			cronoGrupoZona = (Map) listaCronoGrupoZona.get(i);

			if ((!oidGrupoZonaAnt.equals(cronoGrupoZona.get("oidGrupoZona")
					.toString())) && (i != 0)) {
				// quiebre
				Map cronoFecha = new HashMap();
				cronoFecha.put("actividades", actividades);
				cronoFecha.put("oidGrupoZona", oidGrupoZonaAnt);
				cronoFecha.put("grupoZona", grupoZonaAnt);
				listaCronoFechaDentro.add(cronoFecha);
				actividades = new String[Integer.parseInt(numColumnas) + 1];

			}
			if (!cronoGrupoZona.get("grupoZona").toString().equals("xxx")) {
				int orden = Integer.parseInt(cronoGrupoZona.get("orden")
						.toString());
				if ((orden >= 0) && (orden < Integer.parseInt(numColumnas))) {
					actividades[orden] = cronoGrupoZona.get("actividad")
							.toString();
				} else {
					String[] actividad = StringUtils.split(
							cronoGrupoZona.get("actividad").toString(), "|");

					for (int j = 0; j < actividad.length; j++) {

						Map cronoFechaFuera = new HashMap();

						cronoFechaFuera.put("oidGrupoZona",
								cronoGrupoZona.get("oidGrupoZona"));
						cronoFechaFuera.put("grupoZona",
								cronoGrupoZona.get("grupoZona"));
						cronoFechaFuera.put("fecha",
								cronoGrupoZona.get("fecha"));
						cronoFechaFuera.put("orden",
								cronoGrupoZona.get("orden"));
						cronoFechaFuera.put("nombreActividad", actividad[j]);

						listaCronoFechaFuera.add(cronoFechaFuera);
					}

				}

				grupoZonaAnt = cronoGrupoZona.get("grupoZona").toString();
				oidGrupoZonaAnt = cronoGrupoZona.get("oidGrupoZona").toString();
			}

		}

		this.craCargaCronoFase1List.clear();
		this.craCargaCronoFase1FueraList.clear();
		this.fechaInicio = null;
		this.numeroColumnas = null;
		periodo = null;

		this.fechaInicio = fechaIniPerio;
		this.periodo = periodo;
		// request.getSession().setAttribute("fechaInicio", fechaIniPerio);
		// request.getSession().setAttribute("periodo", periodo);

		this.craCargaCronoFase1List = listaCronoFechaDentro;
		this.craCargaCronoFase1FueraList = listaCronoFechaFuera;
		for (int i = 0; i < this.craCargaCronoFase1List.size(); i++) {
			// Creo la lista actividad2 para no perjudicar la lógica
			List actividades2 = new ArrayList();
			Map map = (Map) this.craCargaCronoFase1List.get(i);
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
		this.numeroColumnas = Integer.toString(Integer.parseInt(numColumnas)-1);
		
		// request.getSession().setAttribute("numeroColumnas", numColumnas);
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

		return true;
	}

	/**
	 * @param e
	 * @throws IOException
	 */
	public void grabar(ActionEvent e){
		log.debug("Executing action : setSaveAttributes. ");
		try {
			MantenimientoCRACronogramaFase1PopupForm f = (MantenimientoCRACronogramaFase1PopupForm) this.formMantenimiento;
			MantenimientoCRACronogramaFase1Service service = (MantenimientoCRACronogramaFase1Service) getBean("spusicc.cronograma.mantenimientoCRACronogramaFase1Service");

			String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser()
					.getLogin();

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("oidCrono", f.getOidCronogramaFase1());
			criteria.put("oidGrupoZona", f.getOidGrupoZona());
			criteria.put("diasDesplazamiento", f.getDiasDesplazamiento());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("usuario", codigoUsuario);

			service.updateCronogramaFase1(criteria);

			f.setSalirPantalla(Constants.SI);
			this.search();
			this.redireccionarPagina("mantenimientoCRACronogramaFase1SearchForm");
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));		}
	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoCRACronogramaFase1PopupForm f = (MantenimientoCRACronogramaFase1PopupForm) this.formMantenimiento;
		if (accion.equals("INSERTAR_POPUP")) {
			if (StringUtils.isBlank(f.getDiasDesplazamiento()))
				return this
						.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.desplazamiento");
		}
		return null;
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
		log.debug("setAddAttributes method ....");
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		MantenimientoCRACronogramaFase1PopupForm formMant = new MantenimientoCRACronogramaFase1PopupForm();
		formMantenimiento=formMant;
		MantenimientoCRACronogramaFase1SearchForm f = (MantenimientoCRACronogramaFase1SearchForm) this.formBusqueda;

		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setCodigoPeriodo("");

		this.craCargaCronoFase1List.clear();
		this.craCargaCronoFase1FueraList.clear();
		this.fechaInicio = null;
		this.numeroColumnas = null;
		this.periodo = null;

	}

	/**
	 * @param duracion
	 * @param codigoPeriodo
	 */
	public void recalcular(String duracion, String codigoPeriodo) {

		// String name= (String) ((UIOutput) vce.getSource()).getValue();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) this.listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) this.listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		Calendar calendar = Calendar.getInstance();

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date fechaTemp = new Date();
			try {
				fechaTemp = formatoDeFecha
						.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
								.getFechaInicio());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en parsear la fecha" + i);
			}
			calendar.setTime(fechaTemp);
			calendar.add(Calendar.DAY_OF_YEAR, Integer
					.parseInt(((PeriodoCronograma) this.listaBusqueda.get(i))
							.getDuracion()) - 1);
			String fechaFin = DateUtil.getDate(calendar.getTime());
			((PeriodoCronograma) listaBusqueda.get(i)).setFechaFin(fechaFin);
			if (i + 1 < this.listaBusqueda.size()) {

				// tengo que agregar que le sume los dias de duracion del
				// dia siguiente
				try {
					Date fechaIniSig = formatoDeFecha
							.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
									.getFechaFin());
					calendar.setTime(fechaIniSig);
					calendar.add(Calendar.DATE, 1);

					((PeriodoCronograma) this.listaBusqueda.get(i + 1))
							.setFechaInicio(DateUtil.getDate(calendar.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					log.error("Error en parsear la fecha" + i);
				}

			}
		}
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

	}

	/**
	 * 
	 */
	public void enviarFormulario() {
		MantenimientoCRACronogramaFase1SearchForm f = (MantenimientoCRACronogramaFase1SearchForm) this.formBusqueda;
		try {
			this.periodo = f.getCodigoPeriodo();
			if (periodo.equals(""))
				throw new Exception(
						this.getResourceMessage("mantenimientoCRACronogramaFase2Search.msg.codigoPeriodo"));

			else {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String data = aSvc.getCronogramaBRValidarPeriodoExistente(f
						.getCodigoPeriodo());
				if (data.equals("0")) {
					throw new Exception(
							this.getResourceMessage("mantenimientoRECCronogramaBRForm.error.codigoPeriodo"));
				} else {
					String valor = aSvc.getCronogramaFase1Existente(
							f.getCodigoPais(), this.periodo);
					if (valor.equals("0"))
						this.getRequestContext()
								.execute(
										"PF('confirmDialog1_confirmationDialogConfirmar').show()");
					else
						this.getRequestContext()
								.execute(
										"PF('confirmDialog2_confirmationDialogConfirmar').show()");
				}

			}
		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}
	}


	/**
	 * @param fechaInicioSelected
	 * @param fechaFinSelected
	 * @param codigoPeriodo
	 * @throws ParseException
	 */
	public void recalcularFechaInicio(String fechaInicioSelected,
			String fechaFinSelected, String codigoPeriodo)
			throws ParseException {

		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) this.listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) this.listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date dtInicioArray = formatoDeFecha
					.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
							.getFechaInicio());
			Date dtFinArray = formatoDeFecha
					.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
							.getFechaFin());

			long dif = dtFinArray.getTime() - dtInicioArray.getTime();

			Long duracion = dif / (1000L * 60L * 60L * 24L);
			((PeriodoCronograma) this.listaBusqueda.get(i)).setDuracion((duracion
					.toString()));

			if (i + 1 < this.listaBusqueda.size()) {
				// obtengo fecha final actual para sumarle uno y añadirle al
				// siguiente registro
				Calendar c = Calendar.getInstance();
				c.setTime(dtFinArray);
				c.add(Calendar.DATE, 1);
				// le añado uno y seteo la fecha inicio del siguiente registro
				((PeriodoCronograma) this.listaBusqueda.get(i + 1))
						.setFechaInicio(DateUtil.getDate(c.getTime()));
				// sig como utilitario para rapido acceso
				PeriodoCronograma sig = ((PeriodoCronograma) this.listaBusqueda
						.get(i + 1));
				c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
				c.add(Calendar.DATE, Integer.parseInt(sig.getDuracion()));
				((PeriodoCronograma) this.listaBusqueda.get(i + 1))
						.setFechaFin(DateUtil.getDate(c.getTime()));
			}

		}

	}

	/**
	 * @param fechaInicioSelected
	 * @param fechaFinSelected
	 * @param codigoPeriodo
	 * @throws ParseException
	 */
	public void recalcularFechaFin(String fechaInicioSelected,
			String fechaFinSelected, String codigoPeriodo)
			throws ParseException {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) this.listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) this.listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date dtInicioArray = formatoDeFecha
					.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
							.getFechaInicio());
			Date dtFinArray = formatoDeFecha
					.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
							.getFechaFin());

			int duracionPeriodo = Integer
					.parseInt(((PeriodoCronograma) this.listaBusqueda.get(i))
							.getDuracion()) - 1;
			// aumento la fecha actual sumandole la duracion del periodo
			Calendar cr = Calendar.getInstance();
			cr.setTime(dtInicioArray);
			cr.add(Calendar.DATE, duracionPeriodo);
			((PeriodoCronograma) this.listaBusqueda.get(i)).setFechaFin(DateUtil
					.getDate(cr.getTime()));

			if (i + 1 < this.listaBusqueda.size()) {
				// obtengo fecha final actual para sumarle uno y añadirle al
				// siguiente registro
				Calendar c = Calendar.getInstance();
				c.setTime(formatoDeFecha
						.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
								.getFechaFin()));
				c.add(Calendar.DAY_OF_YEAR, 1);
				// le añado uno y seteo la fecha inicio del siguiente registro
				((PeriodoCronograma) this.listaBusqueda.get(i + 1))
						.setFechaInicio(DateUtil.getDate(c.getTime()));
				// sig como utilitario para rapido acceso

				PeriodoCronograma sig = ((PeriodoCronograma) this.listaBusqueda
						.get(i + 1));
				c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
				c.add(Calendar.DATE, Integer.parseInt(sig.getDuracion()));
				((PeriodoCronograma) this.listaBusqueda.get(i + 1))
						.setFechaFin(DateUtil.getDate(c.getTime()));
			}

		}

	}

}
