package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.MaestroInstructora;
import biz.belcorp.ssicc.dao.edu.model.ParametroGenerico;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.ConsultaEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaEDUCursoCapacitacionEjecutivaSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;

/**
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaEDUCursoCapacitacionEjecutivaSearchAction extends
		BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6712020878314851740L;
	private LabelValue[] siccRegionList;
	private LabelValue[] eduParametrosRegionList;
	private List eduParametrosTipoAsistencia;
	private List eduEmpresaComercializadoraList;
	private List eduConsultaListadoClasificacionesList;
	private List eduRegistroClasificacionList;
	private List eduCursoConsultaEjecutivaList;
	private List eduCursoPlanillaProgramadaList;
	private List eduCursoPlanillaDictadoList;
	private List eduCursoDictadoList;
	private List eduCursoSituacionList;
	private LabelValue[] eduCursoList;
	private LabelValue[] eduParametrosZonaList;
	private List siccConsultaList;
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";
	
	public LabelValue[] getEduParametrosRegionList() {
		return eduParametrosRegionList;
	}

	public void setEduParametrosRegionList(LabelValue[] eduParametrosRegionList) {
		this.eduParametrosRegionList = eduParametrosRegionList;
	}

	public List getEduParametrosTipoAsistencia() {
		return eduParametrosTipoAsistencia;
	}

	public void setEduParametrosTipoAsistencia(List eduParametrosTipoAsistencia) {
		this.eduParametrosTipoAsistencia = eduParametrosTipoAsistencia;
	}

	public List getEduCursoSituacionList() {
		return eduCursoSituacionList;
	}

	public void setEduCursoSituacionList(List eduCursoSituacionList) {
		this.eduCursoSituacionList = eduCursoSituacionList;
	}

	public LabelValue[] getEduParametrosZonaList() {
		return eduParametrosZonaList;
	}

	public void setEduParametrosZonaList(LabelValue[] eduParametrosZonaList) {
		this.eduParametrosZonaList = eduParametrosZonaList;
	}

	public LabelValue[] getEduCursoList() {
		return eduCursoList;
	}

	

	
	public void setEduCursoList(LabelValue[] eduCursoList) {
		this.eduCursoList = eduCursoList;
	}

	@ManagedProperty(value = "#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

	
	
	public List getEduCursoConsultaEjecutivaList() {
		return eduCursoConsultaEjecutivaList;
	}

	public void setEduCursoConsultaEjecutivaList(List eduCursoConsultaEjecutivaList) {
		this.eduCursoConsultaEjecutivaList = eduCursoConsultaEjecutivaList;
	}

	public List getEduCursoPlanillaProgramadaList() {
		return eduCursoPlanillaProgramadaList;
	}

	public void setEduCursoPlanillaProgramadaList(
			List eduCursoPlanillaProgramadaList) {
		this.eduCursoPlanillaProgramadaList = eduCursoPlanillaProgramadaList;
	}

	public List getEduCursoPlanillaDictadoList() {
		return eduCursoPlanillaDictadoList;
	}

	public void setEduCursoPlanillaDictadoList(List eduCursoPlanillaDictadoList) {
		this.eduCursoPlanillaDictadoList = eduCursoPlanillaDictadoList;
	}

	public List getEduCursoDictadoList() {
		return eduCursoDictadoList;
	}

	public void setEduCursoDictadoList(List eduCursoDictadoList) {
		this.eduCursoDictadoList = eduCursoDictadoList;
	}

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaHIPClientePOPUPSearchAction
						.getBeanRegistroSeleccionado();
				ConsultaEDUCursoCapacitacionEjecutivaSearchForm f = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
				f.setCodigoConsultora(((String) clienteHipMap
						.get("codigoCliente")));
				String apellido2 = (String) clienteHipMap.get("apellido2");
				String nombre1 = (String) clienteHipMap.get("nombre1");
				String numeroDocumento = (String) clienteHipMap
						.get("numeroDocumento");
				this.busquedaHIPClientePOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
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
		this.mostrarPopUpCliente = false;
		this.busquedaHIPClientePOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
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

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
		}
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	public List getSiccConsultaList() {
		return siccConsultaList;
	}

	public void setSiccConsultaList(List siccConsultaList) {
		this.siccConsultaList = siccConsultaList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaEDUCursoCapacitacionEjecutivaSearchForm form = new ConsultaEDUCursoCapacitacionEjecutivaSearchForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		try {
			Pais pais = mPantallaPrincipalBean.getCurrentCountry();
			
			Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
			ConsultaEDUCursoCapacitacionEjecutivaSearchForm f = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
			f.reset();
			f.setCodigoPais(pais.getCodigo());
			f.setCargaCombo(Constants.SI);
			f.setHiddenTipo("");

			/* Inicializamos la Empresa, siempre despues de LoadCombos */
			/* Empresas */
			EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
			parametroEmpresa.setCodigoPais(pais.getCodigo());
			parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
			MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");

			eduEmpresaComercializadoraList = siccService
					.getEmpresasComercializadorasByPais(parametroEmpresa);

			List listaEmpresa = eduEmpresaComercializadoraList;
			if ((listaEmpresa != null) && (listaEmpresa.size() > 0)) {
				EmpresaComercializadora empresa = new EmpresaComercializadora();
				empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				// session.setAttribute("EMPRESA_DEFAULT",empresa.getCodigoEmpresa());
				f.setCodigoEmpresa(empresa.getCodigoEmpresa());

				/* obtenemos campaña de proceso */
				obtenerCampannaProceso();
				f.setCampanhaFinal(f.getCampanhaInicio());

				/* Listamos los Cursos */
				Map criterios = new HashMap();
				MantenimientoEDUCursoCapacitacionService service = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");
				criterios.put("codigoPais", f.getCodigoPais());
				criterios.put("estadoRegistro", Constants.ESTADO_ACTIVO);
				criterios.put("codigoEmpresa", f.getCodigoEmpresa());
				List resultado = service
						.getCursosCapacitacionByCriteria(criterios);

				LabelValue[] resultCursoDictado = getCursos(resultado);
				eduCursoList=resultCursoDictado;
				
			}

			/* Obteniendo instructora */
			MaestroInstructora instructora = new MaestroInstructora();
			instructora.setCodigoPais(f.getCodigoPais());
			instructora.setCodigoEmpresa(f.getCodigoEmpresa());
			instructora.setCodigoUsuario(usuario.getLogin());
			MantenimientoEDUGenericoService genericoService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
			List listaInstructora = genericoService
					.getMaestroInstructoraByCriteria(instructora);

			if (listaInstructora == null) {
				ActionMessages messages = new ActionMessages();
				messages.add(
						ActionErrors.GLOBAL_MESSAGE,
						new ActionMessage(
								"consultaEDUCursoCapacitacionEjecutivaSearchForm..errors.instructora"));

				return;
			}

			if (listaInstructora != null && listaInstructora.size() > 0) {
				MaestroInstructora bean = (MaestroInstructora) listaInstructora
						.get(0);
				f.setCodigoInstructora(bean.getCodigoInstructora());
				f.setHiddencodigoInstructora(bean.getCodigoInstructora());
				f.setDescripcionInstructora(bean.getPrimerNombre() + " "
						+ bean.getApellidoPaterno() + " "
						+ bean.getApellidoMaterno());
			}

			loadCombos();

			/* Seteando lista de Consulta */
			List lista = new ArrayList();
			eduCursoConsultaEjecutivaList=lista;
			eduCursoPlanillaProgramadaList=lista;
			eduCursoPlanillaDictadoList=lista;
			eduCursoDictadoList=lista;
		

		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"errors.detail", error));
		}
	}
	
	private LabelValue[] getRegiones(RegionCursoCapacitacion regionCursoCapacitacion) {
		LabelValue[] result = null;
			try {
				MantenimientoEDUCursoCapacitacionService service = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");						
				List regiones = service
						.getRegion(regionCursoCapacitacion);
				if (regiones != null && regiones.size() > 0) {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base region = new Base();
							RegionCursoCapacitacion detalle = new RegionCursoCapacitacion();
							detalle = (RegionCursoCapacitacion) regiones.get(i);
							region.setCodigo(detalle.getCodigoRegion());
							region.setDescripcion(detalle.getCodigoRegion() + " - "+detalle.getDescripcionRegion());
							// Construimos la descripcion
							LabelValue lv = new LabelValue(region
									.getDescripcion(), region.getCodigo());
							result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		return result;
	}	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ConsultaEDUCursoCapacitacionEjecutivaSearchForm form = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService"); 
			eduParametrosZonaList=(aSvc.getZonaByRegionSelected(form.getCodigoPais(), form.getCodigoEmpresa(),regiones,"S" ));
		}else{
			eduParametrosZonaList=null;
		}
		
		form.setCodigoZona(null);
	}
	
	
	public LabelValue[] getZonaByRegion(
			final String codigoPais,final String codigoEmpresa,final String[] codigoRegion) {
		
		LabelValue[] result = null;
		
		MantenimientoEDUCursoCapacitacionService serviceCurso = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");
		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoEmpresa", codigoEmpresa);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (codigoRegion.length == 0) {
					return result;
				}

				List listaZona = serviceCurso.getZonaByRegionSelected(criteria);
				if (listaZona != null && listaZona.size() > 0) {
						result = new LabelValue[listaZona.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < listaZona.size(); i++) {
							Base zona = new Base();
							ZonaCursoCapacitacion detalle = new ZonaCursoCapacitacion();
							detalle = (ZonaCursoCapacitacion) listaZona.get(i);
							zona.setCodigo(detalle.getCodigoRegion() + detalle.getCodigoZona());
							zona.setDescripcion(detalle.getCodigoRegion() + " - "+detalle.getDescripcionZona());
							// Construimos la descripcion
							LabelValue lv = new LabelValue(zona
									.getDescripcion(), zona.getCodigo());							
							result[i + 1] = lv;
						}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	
	
	public LabelValue[] getCursos(
			List listaCursos) {
		
		LabelValue[] result = null;
		try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
				
				if (listaCursos != null && listaCursos.size() > 0) {
						result = new LabelValue[listaCursos.size()];
						for (int i = 0; i < listaCursos.size(); i++) {
							Base cursoDictado = new Base();
							CursoCapacitacion cursoCapacitacion= new CursoCapacitacion();
							cursoCapacitacion = (CursoCapacitacion) listaCursos.get(i);
							cursoDictado.setCodigo(cursoCapacitacion.getCodigoCurso());
							cursoDictado.setDescripcion(cursoCapacitacion.getNombreCurso());
							// Construimos la descripcion
							LabelValue lv = new LabelValue(cursoDictado
									.getDescripcion(), cursoDictado.getCodigo());
							result[i] = lv;
						}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		return result;
	}

	protected void obtenerCampannaProceso() throws Exception {

		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		// Obteniendo Campaña de Proceso
		Map criteria = new HashMap();
		ConsultaEDUCursoCapacitacionEjecutivaSearchForm f = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoEmpresa", f.getCodigoEmpresa());
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		f.setCampanhaInicio(codigoPeriodo);

	}

	private void loadCombos() throws Exception {
		ConsultaEDUCursoCapacitacionEjecutivaSearchForm f = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		/*Situación*/
		List resultado = new ArrayList();
		
		ConsultaEDUCursoCapacitacionService service = (ConsultaEDUCursoCapacitacionService) getBean("edu.consultaEDUCursoCapacitacionService");
		Map criteria = new HashMap();
		resultado = service.getSituaciones(criteria);
		
		eduCursoSituacionList=resultado;
		RegionCursoCapacitacion regionCursoCapacitacion = new RegionCursoCapacitacion();
		regionCursoCapacitacion.setCodigoPais(pais.getCodigo());
		regionCursoCapacitacion.setCodigoEmpresa(f.getCodigoEmpresa());
		regionCursoCapacitacion.setCodigoInstructora(f.getCodigoInstructora());
		
		LabelValue[] regiones = getRegiones(regionCursoCapacitacion);
		
		eduParametrosRegionList=regiones;
		eduParametrosZonaList=null;
		

		
		/*Tipo*/
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		ParametroGenerico parametroGenerico = new ParametroGenerico();
		parametroGenerico.setCodEstaNivel("T");
		eduParametrosTipoAsistencia=siccService
				.getTipoAsistencia(parametroGenerico);
		f.setTipo(Constants.EDU_TIPO_TODAS);

	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		ConsultaEDUCursoCapacitacionEjecutivaSearchForm f = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
		ConsultaEDUCursoCapacitacionService service = (ConsultaEDUCursoCapacitacionService) getBean("edu.consultaEDUCursoCapacitacionService");
		Map criterios = new HashMap();
		criterios.put("codigoPais",f.getCodigoPais());
		criterios.put("codigoEmpresa",f.getCodigoEmpresa());
		criterios.put("codigoCurso",f.getCodigoCurso());
		criterios.put("codigoCliente",f.getCodigoConsultora());
		criterios.put("codigoInstructora",f.getCodigoInstructora());		
		criterios.put("codigoPlanillaProgramacion",f.getCodigoPlanilla());
		criterios.put("campanhaInicial",f.getCampanhaInicio());
		criterios.put("campanhaFinal",f.getCampanhaFinal());
		criterios.put("campanhaIngreso",f.getCampanhaIngreso());
		
		if(!f.getTipo().equals(Constants.EDU_PARAMETRO_TIPO_ASITT_CURSO_TODAS))
			criterios.put("tipo",f.getTipo());
		
		if (f.getCodigoRegion() != null && f.getCodigoRegion().length > 0 )  {
			String codigoRegion = f.getCodigoRegion()[0];
			if (!("00".equals(codigoRegion) || StringUtils.isBlank(codigoRegion)))
				criterios.put("codigoRegion",f.getCodigoRegion());
		}	
		
		if (f.getCodigoZona() != null && f.getCodigoZona().length > 0 )  {
			String codigoZona = f.getCodigoZona()[0];
			if (!("00".equals(codigoZona) || StringUtils.isBlank(codigoZona)))
				criterios.put("codigoZona",f.getCodigoZona());
		}
		
		criterios.put("codigoEstadoCapacitacion",f.getSituacion());
		criterios.put("dictadoCurso",f.getCodigoDictado());
		f.setHiddenCodigoDictado(f.getCodigoDictado());
		f.setHiddenCodigoPlanilla(f.getCodigoPlanilla());
		f.setHiddenCodigoPlanillaDictado(f.getCodigoPlanillaDictado());
		
		
		List resultado = new ArrayList();

		if (Constants.EDU_CURSO_SITUACION_APTA.equals(f.getSituacion())){
			//criterios.put("estadoCapacitacion",Constants.ESTADO_CAPACITADA_PENDIENTE);
			resultado = service.getConsultaCursoCapacitacionAptas(criterios);
		}else if (Constants.ESTADO_CAPACITADA_CAPACITADA.equals(f.getSituacion())){
			criterios.put("estadoCapacitacion",Constants.ESTADO_CAPACITADA_CAPACITADA);
			criterios.put("codigoPlanillaProgramacion",f.getCodigoPlanillaDictado());
			resultado = service.getConsultaCursoCapacitacionCapacitadas(criterios);
		}else if (Constants.EDU_CURSO_SITUACION_PROGRAMADA.equals(f.getSituacion())){
			//criterios.put("estadoCapacitacion",Constants.ESTADO_CAPACITADA_PROGRAMADA); 
			//Se realiza de frente ahora con el de programacion con curso
			resultado = service.getConsultaCursoCapacitacionProgramadas(criterios);
		}
		//*************************************************************
		else if (Constants.EDU_CURSO_SITUACION_NO_APTA.equals(f.getSituacion())){		
			resultado = service.getConsultaCursoCapacitacionNoAptas(criterios);
		}
		else if (Constants.ESTADO_PENDIENTE_FACTURACION.equals(f.getSituacion())||
				 Constants.ESTADO_PENDIENTE_PROGRAMADA.equals(f.getSituacion())||
				 Constants.ESTADO_POR_CONFIRMAR.equals(f.getSituacion())){
			criterios.put("estadoCapacitacion",f.getSituacion());
			resultado = service.getConsultaCursoCapacitacionPendientes(criterios);			
		}
		//*************************************************************
		eduCursoConsultaEjecutivaList=resultado.size()>0?resultado:null;
		
			
		syncZona();
		f.setCargaCombo(Constants.NO);
		f.setHiddenTipo(f.getTipo());
		
		/* devolviendo lista */
		return resultado;

	}

	public List getEduConsultaListadoClasificacionesList() {
		return eduConsultaListadoClasificacionesList;
	}

	public void setEduConsultaListadoClasificacionesList(
			List eduConsultaListadoClasificacionesList) {
		this.eduConsultaListadoClasificacionesList = eduConsultaListadoClasificacionesList;
	}

	public List getEduRegistroClasificacionList() {
		return eduRegistroClasificacionList;
	}

	public void setEduRegistroClasificacionList(
			List eduRegistroClasificacionList) {
		this.eduRegistroClasificacionList = eduRegistroClasificacionList;
	}

	private void syncZona()
	{	
		ConsultaEDUCursoCapacitacionEjecutivaSearchForm f = (ConsultaEDUCursoCapacitacionEjecutivaSearchForm) this.formBusqueda;
		String[] codigoRegion = f.getCodigoRegion();
		if (f.getCodigoRegion()!=null){
			LabelValue[] resultZona = getZonaByRegion(f.getCodigoPais(),f.getCodigoEmpresa(),codigoRegion);
			eduParametrosZonaList=resultZona;
		}					
	}

}
