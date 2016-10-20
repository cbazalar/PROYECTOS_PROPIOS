package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;

import com.hxtt.sql.cr;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAPeriodoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAPeriodoForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCRAPeriodoAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 3235563050175383094L;
	private List craPeriodoCorporativoList;
	private String cra_duracion_periodo_valor = Constants.CRA_DURACION_PERIODO_VALOR;
	private List tamanioTemp;

	/**
	 * @return
	 */
	public List getCraPeriodoCorporativoList() {
		return craPeriodoCorporativoList;
	}

	/**
	 * @param craPeriodoCorporativoList
	 */
	public void setCraPeriodoCorporativoList(List craPeriodoCorporativoList) {
		this.craPeriodoCorporativoList = craPeriodoCorporativoList;
	}

	public String getCra_duracion_periodo_valor() {
		return cra_duracion_periodo_valor;
	}

	public void setCra_duracion_periodo_valor(String cra_duracion_periodo_valor) {
		this.cra_duracion_periodo_valor = cra_duracion_periodo_valor;
	}

	public List getTamanioTemp() {
		return tamanioTemp;
	}

	public void setTamanioTemp(List tamanioTemp) {
		this.tamanioTemp = tamanioTemp;
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
		MantenimientoCRAPeriodoForm searchForm = new MantenimientoCRAPeriodoForm();
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
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");
		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) this.formBusqueda;

		this.craPeriodoCorporativoList = new ArrayList();
		// Seteamos año actual
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		if (f.getAnhio() == null)
			f.setAnhio(anhio);

		List periodoList = service.getPeriodoCorporativoList(f.getAnhio());
		List periodoNuevoList = new ArrayList();

		List periodoCronogramaList = new ArrayList();
		periodoCronogramaList = service.getPeriodoCronogramaList(f.getAnhio());

		if (periodoCronogramaList.size() == 0) {

			// si no existen periodos corporativos (seg_perio_corpo), se crean
			if (periodoList.size() == 0) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("anhio", f.getAnhio());
				periodoNuevoList = service.insertPeriodoCorporativo(criteria);
				this.craPeriodoCorporativoList = periodoNuevoList;
			} else
				this.craPeriodoCorporativoList = periodoList;
		} else {
			this.craPeriodoCorporativoList = periodoCronogramaList;
		}
		return this.craPeriodoCorporativoList;
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
			log.debug("JFA : Entering 'save' method");
		}
		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) this.formBusqueda;
		int tamanio = this.listaBusqueda.size();
		String[] listaCodigoPeriodo = new String[tamanio + 1];

		String[] listaDuracion = new String[tamanio + 1];

		String[] listaFechaFin = new String[tamanio + 1];

		String[] listaFechaInicio = new String[tamanio + 1];

		String[] listaIndicadorPeriodoCorto = new String[tamanio + 1];

		String[] listaIndicadorPeriodoCruce = new String[tamanio + 1];

		String[] listaOidPeriodo = new String[tamanio + 1];
		listaCodigoPeriodo[0] = "";
		listaDuracion[0] = "";
		listaFechaFin[0] = "";
		listaFechaInicio[0] = "";
		listaIndicadorPeriodoCorto[0] = "";
		listaIndicadorPeriodoCruce[0] = "";
		listaOidPeriodo[0] = "";
		for (int i = 1; i < this.listaBusqueda.size() + 1; i++) {

			listaCodigoPeriodo[i] = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getCodigoPeriodo();
			listaDuracion[i] = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getDuracion();
			listaFechaFin[i] = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getFechaFin();
			listaFechaInicio[i] = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getFechaInicio();

			String indCorto = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getIndicadorPeriodoCorto();
			if (indCorto.equals("1"))
				listaIndicadorPeriodoCorto[i] = "SI";
			else
				listaIndicadorPeriodoCorto[i] = "NO";

			String indCruce = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getIndicadorPeriodoCruce();
			if (indCruce.equals("1"))
				listaIndicadorPeriodoCruce[i] = "SI";
			else
				listaIndicadorPeriodoCruce[i] = "NO";
			listaOidPeriodo[i] = ((PeriodoCronograma) this.listaBusqueda
					.get(i - 1)).getOid();
		}
		f.setListaCodigoPeriodo(listaCodigoPeriodo);
		f.setListaOidPeriodo(listaOidPeriodo);
		f.setListaDuracion(listaDuracion);
		f.setListaIndicadorPeriodoCruce(listaIndicadorPeriodoCruce);
		f.setListaIndicadorPeriodoCorto(listaIndicadorPeriodoCorto);
		f.setListaFechaFin(listaFechaFin);
		f.setListaFechaInicio(listaFechaInicio);

		// Extraemos el usuario de la sesión
		String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser()
				.getCodigo();

		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");

		Map criteria = new HashMap();
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("pais", f.getCodigoPais());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
		criteria.put("anhio", f.getAnhio());

		criteria.put("listaOidPeriodo", (String[]) f.getListaOidPeriodo());
		criteria.put("listaCodigoPeriodo", (String[]) f.getListaCodigoPeriodo());
		criteria.put("listaFechaInicio", (String[]) f.getListaFechaInicio());
		criteria.put("listaFechaFin", (String[]) f.getListaFechaFin());
		criteria.put("listaIndicadorPeriodoCorto",
				(String[]) f.getListaIndicadorPeriodoCorto());
		criteria.put("listaIndicadorPeriodoCruce",
				(String[]) f.getListaIndicadorPeriodoCruce());
		criteria.put("usuario", codigoUsuario);
		criteria.put("newRecord", f.isNewRecord());
		service.insertPeriodoCronograma(criteria);

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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonNuevo = false;
		this.mostrarBotonBuscar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");
		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) this.formBusqueda;

		this.craPeriodoCorporativoList = new ArrayList();
		// Seteamos año actual
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		if (f.getAnhio() == null)
			f.setAnhio(anhio);

		List periodoList = service.getPeriodoCorporativoList(f.getAnhio());
		List periodoNuevoList = new ArrayList();

		List periodoCronogramaList = new ArrayList();
		periodoCronogramaList = service.getPeriodoCronogramaList(f.getAnhio());

		if (periodoCronogramaList.size() == 0) {

			// si no existen periodos corporativos (seg_perio_corpo), se crean
			if (periodoList.size() == 0) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", this.mPantallaPrincipalBean
						.getCurrentCountry().getCodigo());
				criteria.put("anhio", f.getAnhio());
				periodoNuevoList = service.insertPeriodoCorporativo(criteria);
				this.craPeriodoCorporativoList = periodoNuevoList;
			} else
				this.craPeriodoCorporativoList = periodoList;
			f.setNewRecord(true);
		} else {
			this.craPeriodoCorporativoList = periodoCronogramaList;
			f.setNewRecord(false);
		}
		this.listaBusqueda = this.craPeriodoCorporativoList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		mostrarListaBusqueda = false;
	}

	public void cargarAnio(AjaxBehaviorEvent e) {

		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");
		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) this.formBusqueda;

		this.craPeriodoCorporativoList = new ArrayList();
		// Seteamos año actual
		try {

			if (StringUtils.isBlank(f.getAnhio()))
				throw new Exception("El campo Año es obligatorio");

			List periodoList = service.getPeriodoCorporativoList(f.getAnhio());
			List periodoNuevoList = new ArrayList();

			List periodoCronogramaList = new ArrayList();
			periodoCronogramaList = service.getPeriodoCronogramaList(f
					.getAnhio());

			if (periodoCronogramaList.size() == 0) {

				// si no existen periodos corporativos (seg_perio_corpo), se
				// crean
				if (periodoList.size() == 0) {
					Map criteria = new HashMap();
					criteria.put("codigoPais", this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo());
					criteria.put("anhio", f.getAnhio());
					periodoNuevoList = service
							.insertPeriodoCorporativo(criteria);
					this.craPeriodoCorporativoList = periodoNuevoList;
				} else
					this.craPeriodoCorporativoList = periodoList;
				f.setNewRecord(true);
			} else {
				this.craPeriodoCorporativoList = periodoCronogramaList;
				f.setNewRecord(false);
			}

			this.listaBusqueda = this.craPeriodoCorporativoList;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		} catch (Exception ex) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(ex));
		}

	}

	/**
	 * @param duracion
	 * @param codigoPeriodo
	 */
	public void recalcular() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String data = externalContext.getRequestParameterMap().get("data");
		String duracion = data.split(";")[0];
		String codigoPeriodo = data.split(";")[1];
		this.tamanioTemp = this.listaBusqueda;
		try {
			if (StringUtils.isBlank(duracion))
				throw new Exception("Ingresar duración");

			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			String valor = null;
			int pos = -1;
			for (int i = 0; i < this.listaBusqueda.size(); i++) {
				if (((PeriodoCronograma) this.listaBusqueda.get(i))
						.getCodigoPeriodo().equals(codigoPeriodo)) {
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
							.parse(((PeriodoCronograma) this.listaBusqueda
									.get(i)).getFechaInicio());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					log.error("Error en parsear la fecha" + i);
				}
				calendar.setTime(fechaTemp);
				calendar.add(Calendar.DAY_OF_YEAR, Integer
						.parseInt(((PeriodoCronograma) this.listaBusqueda
								.get(i)).getDuracion()) - 1);
				String fechaFin = DateUtil.getDate(calendar.getTime());
				((PeriodoCronograma) this.listaBusqueda.get(i))
						.setFechaFin(fechaFin);
				if (i + 1 < this.listaBusqueda.size()) {

					// tengo que agregar que le sume los dias de duracion del
					// dia siguiente
					try {
						Date fechaIniSig = formatoDeFecha
								.parse(((PeriodoCronograma) this.listaBusqueda
										.get(i)).getFechaFin());
						calendar.setTime(fechaIniSig);
						calendar.add(Calendar.DATE, 1);

						((PeriodoCronograma) this.listaBusqueda.get(i + 1))
								.setFechaInicio(DateUtil.getDate(calendar
										.getTime()));
					} catch (ParseException e) {
						log.error("Error en parsear la fecha" + i);
					}
				}
				actualizacionPeriodos(tamanioTemp);

			}
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void actualizacionPeriodos(List listaTemp) {
		List fechaInicioList = new ArrayList();
		List fechaFinList = new ArrayList();
		this.listaBusqueda = listaTemp;

		for (int i = 0; i < listaTemp.size(); i++) {
			PeriodoCronograma data = (PeriodoCronograma) this.listaBusqueda
					.get(i);
			if (Integer.parseInt(data.getDuracion()) < Integer
					.parseInt(cra_duracion_periodo_valor))
				data.setIndicadorPeriodoCorto("1");
			else
				data.setIndicadorPeriodoCorto("0");
		}

		for (int i = 1; i < listaTemp.size(); i++) {
			fechaInicioList.add(((PeriodoCronograma) this.listaBusqueda.get(i))
					.getFechaInicio());
		}

		for (int i = 0; i < listaTemp.size() - 1; i++) {
			fechaFinList.add(((PeriodoCronograma) this.listaBusqueda.get(i))
					.getFechaFin());
		}

		for (int i = 0; i < listaTemp.size() - 1; i++) {
			PeriodoCronograma data = (PeriodoCronograma) this.listaBusqueda
					.get(i);
			String fechaIni = fechaInicioList.get(i).toString();
			String fechaFin = fechaFinList.get(i).toString();
			int resultado = DateUtil.compararFechas(fechaFin, fechaIni);
			if (resultado == 1)
				((PeriodoCronograma) this.listaBusqueda.get(i))
						.setIndicadorPeriodoCruce("1");
			else
				((PeriodoCronograma) this.listaBusqueda.get(i))
						.setIndicadorPeriodoCruce("0");
		}
	}

	/**
	 * @param fechaInicioSelected
	 * @param fechaFinSelected
	 * @param codigoPeriodo
	 * @throws ParseException
	 */
	public void recalcularFechaInicio() throws ParseException {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String data = externalContext.getRequestParameterMap().get("data");
		String fechaInicioSelected = data.split(";")[0];
		String fechaFinSelected = data.split(";")[1];
		String codigoPeriodo = data.split(";")[2];
		String booleano = data.split(";")[3];
		String index = data.split(";")[4];
		this.tamanioTemp = this.listaBusqueda;
		try {
			if (booleano.equals("0")){
				PeriodoCronograma data2 = (PeriodoCronograma)listaBusqueda.get(Integer.parseInt(index));
				data2.setFechaFin("");
				throw new Exception("Fecha inválida");
			}
			if (StringUtils.isBlank(fechaInicioSelected))
				throw new Exception("Ingresar fecha inicio");
			if (StringUtils.isBlank(fechaFinSelected))
				throw new Exception("Ingresar fecha final");

			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			String valor = null;
			int pos = -1;
			for (int i = 0; i < this.listaBusqueda.size(); i++) {
				if (((PeriodoCronograma) this.listaBusqueda.get(i))
						.getCodigoPeriodo().equals(codigoPeriodo)) {
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

				Long duracion = dif / (1000L * 60L * 60L * 24L) + 1;
				((PeriodoCronograma) this.listaBusqueda.get(i))
						.setDuracion((duracion.toString()));

				if (i + 1 < this.listaBusqueda.size()) {
					// obtengo fecha final actual para sumarle uno y añadirle al
					// siguiente registro
					Calendar c = Calendar.getInstance();
					c.setTime(dtFinArray);
					c.add(Calendar.DATE, 1);
					// le añado uno y seteo la fecha inicio del siguiente
					// registro
					((PeriodoCronograma) this.listaBusqueda.get(i + 1))
							.setFechaInicio(DateUtil.getDate(c.getTime()));
					// sig como utilitario para rapido acceso
					PeriodoCronograma sig = ((PeriodoCronograma) this.listaBusqueda
							.get(i + 1));
					c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
					c.add(Calendar.DATE,
							Integer.parseInt(sig.getDuracion()) - 1);
					((PeriodoCronograma) this.listaBusqueda.get(i + 1))
							.setFechaFin(DateUtil.getDate(c.getTime()));
				}
			}
			actualizacionPeriodos(tamanioTemp);
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param fechaInicioSelected
	 * @param fechaFinSelected
	 * @param codigoPeriodo
	 * @throws ParseException
	 */
	public void recalcularFechaFin() throws ParseException {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String data = externalContext.getRequestParameterMap().get("data");
		String fechaInicioSelected = data.split(";")[0];
		String fechaFinSelected = data.split(";")[1];
		String codigoPeriodo = data.split(";")[2];
		String booleano = data.split(";")[3];
		String index = data.split(";")[4];
		this.tamanioTemp = this.listaBusqueda;
		
		try {
			if (booleano.equals("0")){
				PeriodoCronograma data2 = (PeriodoCronograma)listaBusqueda.get(Integer.parseInt(index));
				data2.setFechaInicio("");
				throw new Exception("Fecha inválida");
			}
			if (StringUtils.isBlank(fechaInicioSelected))
				throw new Exception("Ingresar fecha inicio");
			if (StringUtils.isBlank(fechaFinSelected))
				throw new Exception("Ingresar fecha final");
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			String valor = null;
			int pos = -1;
			for (int i = 0; i < this.listaBusqueda.size(); i++) {
				if (((PeriodoCronograma) this.listaBusqueda.get(i))
						.getCodigoPeriodo().equals(codigoPeriodo)) {
					valor = ((PeriodoCronograma) this.listaBusqueda.get(i))
							.getCodigoPeriodo();
					pos = i;
				}
			}

			for (int i = pos; i < this.listaBusqueda.size(); i++) {
				Date dtInicioArray = formatoDeFecha
						.parse(((PeriodoCronograma) this.listaBusqueda.get(i))
								.getFechaInicio());
				// Date dtFinArray = formatoDeFecha
				// .parse(((PeriodoCronograma) this.listaBusqueda.get(i))
				// .getFechaFin());

				int duracionPeriodo = Integer
						.parseInt(((PeriodoCronograma) this.listaBusqueda
								.get(i)).getDuracion()) - 1;
				// aumento la fecha actual sumandole la duracion del periodo
				Calendar cr = Calendar.getInstance();
				cr.setTime(dtInicioArray);
				cr.add(Calendar.DATE, duracionPeriodo);
				((PeriodoCronograma) this.listaBusqueda.get(i))
						.setFechaFin(DateUtil.getDate(cr.getTime()));

				if (i + 1 < this.listaBusqueda.size()) {
					// obtengo fecha final actual para sumarle uno y añadirle al
					// siguiente registro
					Calendar c = Calendar.getInstance();
					c.setTime(formatoDeFecha
							.parse(((PeriodoCronograma) listaBusqueda.get(i))
									.getFechaFin()));
					c.add(Calendar.DAY_OF_YEAR, 1);
					// le añado uno y seteo la fecha inicio del siguiente
					// registro
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

				if (Integer.parseInt(((PeriodoCronograma) this.listaBusqueda
						.get(i)).getDuracion()) < Integer
						.parseInt(cra_duracion_periodo_valor))
					((PeriodoCronograma) this.listaBusqueda.get(i))
							.setIndicadorPeriodoCorto("1");
				else
					((PeriodoCronograma) this.listaBusqueda.get(i))
							.setIndicadorPeriodoCorto("0");
			}
			
			actualizacionPeriodos(this.tamanioTemp);


		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
	}
}