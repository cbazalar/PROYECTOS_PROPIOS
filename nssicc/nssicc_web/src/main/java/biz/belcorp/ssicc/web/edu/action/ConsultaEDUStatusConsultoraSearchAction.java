package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictado;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.EstadoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.MaestroCliente;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.service.edu.ConsultaEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDURegistroAsistenciaService;
import biz.belcorp.ssicc.web.edu.form.ConsultaEDUStatusConsultoraSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;

@ManagedBean
@SessionScoped
public class ConsultaEDUStatusConsultoraSearchAction extends
BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7012713482326161395L;
	private List eduEmpresaComercializadoraList;
	private List eduConsultaNivelesCapacList;
	private List eduConsultaHistoConsuAptaList;
	private List eduConsultaHistoConsuProgrList;
	private List eduConsultaHistoConsuCapacList;
	private List eduConsultaHistoConsuBenefList;
	private List eduConsultaCronogramaRegionesList;
	private List eduConsultaCronogramaZonaList;
	private List historicoAptasList;

	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente= (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado(); 
				ConsultaEDUStatusConsultoraSearchForm f = (ConsultaEDUStatusConsultoraSearchForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
	}

	
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)){ 
			this.mostrarPopupConsultora = true;
		}
	}
	

	

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		log.debug("inicio setFindAttributes");

		List listCronogramaRegiones = new ArrayList();
		// inicializamos atributos
		HistoricoCapacitadasDetalle historicoCapacitadasDetalle = new HistoricoCapacitadasDetalle();
		HistoricoAptas historicoAptas = new HistoricoAptas();

		ConsultaEDUStatusConsultoraSearchForm f = (ConsultaEDUStatusConsultoraSearchForm) this.formBusqueda;
		ProcesoEDURegistroAsistenciaService service = (ProcesoEDURegistroAsistenciaService) getBean("edu.procesoEDURegistroAsistenciaService");

		historicoAptas = setStatusConsultora(f, historicoAptas,
				historicoCapacitadasDetalle, service);

		listCronogramaRegiones = setCronograma(historicoAptas, service);
		log.debug("listCronogramaRegiones " + listCronogramaRegiones.size());
		// if (listCronogramaRegiones.size() > 0)
		// request.setAttribute("cronograma", Constants.ACTIVO);

		setStatusHistorico(historicoAptas);
		List listhisto = new ArrayList();
		listhisto.add(historicoAptas);

		this.historicoAptasList=listhisto;
		return historicoAptasList;

	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarListaBusqueda = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaEDUStatusConsultoraSearchForm f = (ConsultaEDUStatusConsultoraSearchForm) this.formBusqueda;
		loadCombos();
		f.setCodigoPais(pais.getCodigo());

		/* Inicializamos la Empresa, siempre despues de LoadCombos */
		List listaEmpresa = this.eduEmpresaComercializadoraList;
		if ((listaEmpresa != null) && (listaEmpresa.size() > 0)) {
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}
		long longitudConsultora = pais.getLongitudCodigoCliente();
		f.setLongitudConsultora(new Integer(new Long(longitudConsultora)
				.intValue()));

	}

	private void loadCombos() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		this.eduEmpresaComercializadoraList = siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);

	}

	private HistoricoAptas setStatusConsultora(
			ConsultaEDUStatusConsultoraSearchForm f,
			HistoricoAptas historicoAptas,
			HistoricoCapacitadasDetalle historicoCapacitadasDetalle,
			ProcesoEDURegistroAsistenciaService service) throws Exception {

		completarVO(historicoAptas, f.getCodigoPais(), f.getCodigoEmpresa(),
				f.getCodigoConsultora());
		completarVO(historicoCapacitadasDetalle, f.getCodigoPais(),
				f.getCodigoEmpresa(), f.getCodigoConsultora());

		// List
		// listHistoricoCapacDeta=service.getHistoricoCapacitadasDetalle(historicoCapacitadasDetalle);
		historicoAptas = getSituacionActual(historicoAptas, service);

		if (historicoAptas == null) {

			String mensajeWarnig = getResourceMessage("consultaEDUStatusConsultora.msg.validarHistoricoAptas");

			throw new Exception(mensajeWarnig);
		}

		// se completa el bean resultado
		// f.setNivelCapacitacion(historicoCapacitadasDetalle!=null?historicoCapacitadasDetalle.getCodigoCurso():"");

		f.setCodigoRegion(historicoAptas.getCodigoRegion());
		f.setCodigoZona(historicoAptas.getCodigoZona());
		f.setCodigoPlanillaProgramacion(historicoAptas
				.getCodigoPlanillaProgramacion());
		f.setCampanhaIngreso(historicoAptas.getCampanhaIngreso());
		f.setStatus(historicoAptas.getStatus());
		// session.setAttribute(Constants.EDU_CONSULTA_NIVELES_CAPAC_LIST,listHistoricoCapacDeta);
		// se registar los niveles por alcanzar en el histoAptas
		setNivelesxAlcanzar();
		return historicoAptas;
		// return listHistoricoAptas;
	}

	private void completarVO(Object object, String codigoPais,
			String codigoEmpresa, String codigoCliente) {
		if (object instanceof HistoricoAptas) {
			HistoricoAptas o = (HistoricoAptas) object;
			o.setCodigoCliente(codigoCliente);
			o.setCodigoEmpresa(codigoEmpresa);
			o.setCodigoPais(codigoPais);
		}

		if (object instanceof HistoricoCapacitadasDetalle) {
			HistoricoCapacitadasDetalle o = (HistoricoCapacitadasDetalle) object;
			o.setCodigoCliente(codigoCliente);
			o.setCodigoEmpresa(codigoEmpresa);
			o.setCodigoPais(codigoPais);
		}
	}

	private HistoricoAptas getSituacionActual(HistoricoAptas historicoAptas,
			ProcesoEDURegistroAsistenciaService service) {
		List listHistoricoAptas = service
				.getSituacionActualHistoricoAptas(historicoAptas);
		if (listHistoricoAptas.size() > 0) {
			this.eduConsultaNivelesCapacList = listHistoricoAptas;
			historicoAptas = (HistoricoAptas) listHistoricoAptas.get(0);
			return historicoAptas;
		}
		return null;
	}

	private void setStatusHistorico(HistoricoAptas historicoAptas) {
		ConsultaEDUCursoCapacitacionService service = (ConsultaEDUCursoCapacitacionService) getBean("edu.consultaEDUCursoCapacitacionService");
		// armando el Map de consulta
		Map map = new HashMap();
		map.put("codigoPais", historicoAptas.getCodigoPais());
		map.put("codigoEmpresa", historicoAptas.getCodigoEmpresa());
		map.put("codigoCliente", historicoAptas.getCodigoCliente());

		log.debug("map que se envia  " + map);
		// obteniendo historco de calificds d ela consultora
		List listStatusAptas = service.getListStatusAptaConsultora(map);
		// obteniendo las programadas
		List listStatusProg = service.getListStatusProgrConsultora(map);
		// obteniendo las capacitadas de la consultora
		List listStatusCapac = service.getListStatusCapacConsultora(map);
		// obteniendo los beneficion de la consultora
		List listStatusBenef = service.getListStatusBenefConsultora(map);
		this.eduConsultaHistoConsuAptaList = listStatusAptas;
		this.eduConsultaHistoConsuProgrList = listStatusProg;
		this.eduConsultaHistoConsuCapacList = listStatusCapac;
		this.eduConsultaHistoConsuBenefList = listStatusBenef;

	}

	private void setNivelesxAlcanzar() {
		ConsultaEDUCursoCapacitacionService service = (ConsultaEDUCursoCapacitacionService) getBean("edu.consultaEDUCursoCapacitacionService");
		List list = this.eduConsultaNivelesCapacList;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			HistoricoAptas historicoAptas = (HistoricoAptas) it.next();

			Map map = new HashMap();
			map.put("codigoPais", historicoAptas.getCodigoPais());
			map.put("codigoEmpresa", historicoAptas.getCodigoEmpresa());
			map.put("codigoCurso", historicoAptas.getCodigoCurso());

			String[] nivelesCurso = service.getNivelesxAlcanzar(map);
			historicoAptas.setNivelesAlcanzar(nivelesCurso);
		}

	}

	private List setCronograma(HistoricoAptas historicoAptas,
			ProcesoEDURegistroAsistenciaService service) throws Exception {
		List listCronogramaConsultora = new ArrayList();
		List listCronogramaRegiones = new ArrayList();
		// historicoAptas =getSituacionActual(historicoAptas,service);
		log.debug("estado Capacitacion "
				+ historicoAptas.getEstadoCapacitacion());
		if (!historicoAptas.getEstadoCapacitacion().equals(
				Constants.EDU_CURSO_SITUACION_PROGRAMADA)) {
			String mensajeWarnig = getResourceMessage("consultaEDUStatusConsultora.msg.validarSituacionProgramada");
			mensajeWarnig = StringUtils.replaceOnce(mensajeWarnig, "{0}",
					getDescripcionSituacion(historicoAptas
							.getEstadoCapacitacion()));
			// removmos las listas
			// throw new Exception(mensajeWarnig);
			return listCronogramaRegiones;
		}
		// filtros pais, empresa ,campanha ,curso
		// cronograma para todas las regiones
		boolean tipoRegion = true;
		listCronogramaRegiones = getCronograma(historicoAptas, tipoRegion);
		listCronogramaConsultora = getCronograma(historicoAptas, !tipoRegion);

		this.eduConsultaCronogramaRegionesList = listCronogramaRegiones;
		this.eduConsultaCronogramaZonaList = listCronogramaConsultora;

		log.debug("size cronograma consultora "
				+ listCronogramaConsultora.size());
		// if (listCronogramaConsultora.size() > 0)
		// request.setAttribute("cronconsultora", Constants.ACTIVO);
		return listCronogramaRegiones;
	}

	private List getCronograma(HistoricoAptas historicoAptas, boolean tipoRegion) {
		List listCronogramaDictado = null;

		CronogramaDictado cronogramaDictado = new CronogramaDictado();
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		// completa todos los filtros qnesecita el cronograma
		completarCronograma(cronogramaDictado, historicoAptas, service);
		if (tipoRegion)
			listCronogramaDictado = service
					.getCronogramaDictado(cronogramaDictado);
		else
			listCronogramaDictado = service
					.getCronogramaDictadoProgramadoByZona(cronogramaDictado);

		return listCronogramaDictado;
	}

	private void completarCronograma(CronogramaDictado cronograma,
			HistoricoAptas historicoAptas,
			MantenimientoEDUGenericoService service) {
		cronograma.setCodigoPais(historicoAptas.getCodigoPais());
		cronograma.setCodigoEmpresa(historicoAptas.getCodigoEmpresa());
		// cronograma.setCodigoCurso(historicoAptas.getCodigoCurso());
		cronograma.setEstadoActividad(Constants.ESTADO_ENTIDAD_ACTIVO);
		MaestroCliente cliente = getCliente(historicoAptas, service);
		cronograma.setCodigoRegion(cliente.getCodigoRegion());
		Map criteria = new HashMap();
		criteria.put("campannaProceso",
				historicoAptas.getCampanhaUltimaCalificacionApta());
		criteria.put("numeroCampanna", new Integer("1"));
		String campanna = service.getDevuelveCampanna(criteria);
		log.debug("campanna " + campanna);
		cronograma.setCampannaCronograma(campanna);
		// le agrego la zona correspondiente, es usada solo para
		// obtener el cronograma de la consultora en su zona
		String[] zona = new String[1];// la consultora tiene una zona
		zona[0] = cliente.getCodigoZona();
		cronograma.setListaZonas(zona);
		// ******************
		cronograma.setCodigoCliente(historicoAptas.getCodigoCliente());
		// *****************
	}

	private MaestroCliente getCliente(HistoricoAptas historicoAptas,
			MantenimientoEDUGenericoService service) {
		MaestroCliente maestroCliente = new MaestroCliente();
		maestroCliente.setCodigoPais(historicoAptas.getCodigoPais());
		maestroCliente.setCodigoEmpresa(historicoAptas.getCodigoEmpresa());
		maestroCliente.setCodigoCliente(historicoAptas.getCodigoCliente());
		List listClientes = service.getMaestroClientes(maestroCliente);
		return (MaestroCliente) listClientes.get(0);
	}

	private String getDescripcionSituacion(String estado) {
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		EstadoCapacitacion estadoCapac = new EstadoCapacitacion();
		estadoCapac.setCodigo(estado);
		estadoCapac = service.getEstadoCapacitacion(estadoCapac);
		return (estadoCapac == null ? "" : estadoCapac.getDescripcion());
	}



	/**
	 * @return the eduEmpresaComercializadoraList
	 */
	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	/**
	 * @param eduEmpresaComercializadoraList
	 *            the eduEmpresaComercializadoraList to set
	 */
	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	/**
	 * @return the eduConsultaNivelesCapacList
	 */
	public List getEduConsultaNivelesCapacList() {
		return eduConsultaNivelesCapacList;
	}

	/**
	 * @param eduConsultaNivelesCapacList
	 *            the eduConsultaNivelesCapacList to set
	 */
	public void setEduConsultaNivelesCapacList(List eduConsultaNivelesCapacList) {
		this.eduConsultaNivelesCapacList = eduConsultaNivelesCapacList;
	}

	/**
	 * @return the eduConsultaHistoConsuAptaList
	 */
	public List getEduConsultaHistoConsuAptaList() {
		return eduConsultaHistoConsuAptaList;
	}

	/**
	 * @param eduConsultaHistoConsuAptaList
	 *            the eduConsultaHistoConsuAptaList to set
	 */
	public void setEduConsultaHistoConsuAptaList(
			List eduConsultaHistoConsuAptaList) {
		this.eduConsultaHistoConsuAptaList = eduConsultaHistoConsuAptaList;
	}

	/**
	 * @return the eduConsultaHistoConsuProgrList
	 */
	public List getEduConsultaHistoConsuProgrList() {
		return eduConsultaHistoConsuProgrList;
	}

	/**
	 * @param eduConsultaHistoConsuProgrList
	 *            the eduConsultaHistoConsuProgrList to set
	 */
	public void setEduConsultaHistoConsuProgrList(
			List eduConsultaHistoConsuProgrList) {
		this.eduConsultaHistoConsuProgrList = eduConsultaHistoConsuProgrList;
	}

	/**
	 * @return the eduConsultaHistoConsuCapacList
	 */
	public List getEduConsultaHistoConsuCapacList() {
		return eduConsultaHistoConsuCapacList;
	}

	/**
	 * @param eduConsultaHistoConsuCapacList
	 *            the eduConsultaHistoConsuCapacList to set
	 */
	public void setEduConsultaHistoConsuCapacList(
			List eduConsultaHistoConsuCapacList) {
		this.eduConsultaHistoConsuCapacList = eduConsultaHistoConsuCapacList;
	}

	/**
	 * @return the eduConsultaHistoConsuBenefList
	 */
	public List getEduConsultaHistoConsuBenefList() {
		return eduConsultaHistoConsuBenefList;
	}

	/**
	 * @param eduConsultaHistoConsuBenefList
	 *            the eduConsultaHistoConsuBenefList to set
	 */
	public void setEduConsultaHistoConsuBenefList(
			List eduConsultaHistoConsuBenefList) {
		this.eduConsultaHistoConsuBenefList = eduConsultaHistoConsuBenefList;
	}

	/**
	 * @return the eduConsultaCronogramaRegionesList
	 */
	public List getEduConsultaCronogramaRegionesList() {
		return eduConsultaCronogramaRegionesList;
	}

	/**
	 * @param eduConsultaCronogramaRegionesList
	 *            the eduConsultaCronogramaRegionesList to set
	 */
	public void setEduConsultaCronogramaRegionesList(
			List eduConsultaCronogramaRegionesList) {
		this.eduConsultaCronogramaRegionesList = eduConsultaCronogramaRegionesList;
	}

	/**
	 * @return the eduConsultaCronogramaZonaList
	 */
	public List getEduConsultaCronogramaZonaList() {
		return eduConsultaCronogramaZonaList;
	}

	/**
	 * @param eduConsultaCronogramaZonaList
	 *            the eduConsultaCronogramaZonaList to set
	 */
	public void setEduConsultaCronogramaZonaList(
			List eduConsultaCronogramaZonaList) {
		this.eduConsultaCronogramaZonaList = eduConsultaCronogramaZonaList;
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


	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}


	/**
	 * @param busquedaConsultoraPOPUPSearchAction the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}


	/**
	 * @return the popupConsultora
	 */
	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}


	/**
	 * @return the historicoAptasList
	 */
	public List getHistoricoAptasList() {
		return historicoAptasList;
	}


	/**
	 * @param historicoAptasList the historicoAptasList to set
	 */
	public void setHistoricoAptasList(List historicoAptasList) {
		this.historicoAptasList = historicoAptasList;
	}


	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaEDUStatusConsultoraSearchForm f = new ConsultaEDUStatusConsultoraSearchForm();
		return f;
	}
	
	
	
	

}
