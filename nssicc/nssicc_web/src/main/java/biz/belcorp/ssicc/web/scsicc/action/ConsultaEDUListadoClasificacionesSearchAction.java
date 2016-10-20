package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroClasificacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaEDUListadoClasificacionesSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;

/**
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaEDUListadoClasificacionesSearchAction extends
		BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6712020878314851740L;
	private LabelValue[] siccRegionList;

	private List eduEmpresaComercializadoraList;
	private List eduConsultaListadoClasificacionesList;
	private List eduRegistroClasificacionList;
	private List eduCursoList;
	
	private List siccConsultaList;
	
	
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";

		
	
	@ManagedProperty(value = "#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

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
				ConsultaEDUListadoClasificacionesSearchForm f = (ConsultaEDUListadoClasificacionesSearchForm) this.formBusqueda;
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
		ConsultaEDUListadoClasificacionesSearchForm form = new ConsultaEDUListadoClasificacionesSearchForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		log.debug("Inicio view , ");
		try {
			Pais pais = mPantallaPrincipalBean.getCurrentCountry();
			ConsultaEDUListadoClasificacionesSearchForm f = (ConsultaEDUListadoClasificacionesSearchForm) this.formBusqueda;
			loadCombos();
			f.setCodigoPais(pais.getCodigo());
			f.setIndicadorEnvioComercial("1");

			/* Inicializamos la Empresa, siempre despues de LoadCombos */
			List listaEmpresa =eduEmpresaComercializadoraList;
			if ((listaEmpresa != null) && (listaEmpresa.size() > 0)) {
				EmpresaComercializadora empresa = new EmpresaComercializadora();
				empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				f.setCodigoEmpresa(empresa.getCodigoEmpresa());
//				session.setAttribute("EMPRESA_DEFAULT",
//						empresa.getCodigoEmpresa());
				f.setCodigoEmpresa(empresa.getCodigoEmpresa());
			}
			// limpiamos lista de session
			eduConsultaListadoClasificacionesList=null;
			//session.removeAttribute(Constants.EDU_CONSULTA_LISTADO_CLASIFICACIONES_LIST);
			// cargamos combos inicales CLASIFICCIONES
			MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
			ParametroClasificacion parametroClasificacion = new ParametroClasificacion();
			parametroClasificacion.setCodigoPais(f.getCodigoPais());
			parametroClasificacion.setCodigoEmpresa(f.getCodigoEmpresa());
			parametroClasificacion
					.setTipoClasificacion(Constants.EDU_TIPO_BENEFICIO);
			eduRegistroClasificacionList=service.getParametroClasificacionByCriteria(parametroClasificacion);
		
			// combo Curso
			MantenimientoEDUCursoCapacitacionService serviceCurso = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoEmpresa", f.getCodigoEmpresa());
			List listCurso = serviceCurso
					.getCursosCapacitacionByCriteria(criteria);
			eduCursoList=listCurso;

		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"errors.detail", error));
			
		}
	}

	private void loadCombos() throws Exception {

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);

		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		eduEmpresaComercializadoraList=siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);


	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		log.debug("inicio setFindAttributes");
	    ConsultaEDUListadoClasificacionesSearchForm f = (ConsultaEDUListadoClasificacionesSearchForm) this.formBusqueda;
	    MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
	    Map map = new HashMap();
	    map.put("codigoPais",f.getCodigoPais());
	    map.put("codigoEmpresa",f.getCodigoEmpresa());
	    map.put("codigoPeriodo",f.getCodigoPeriodo());
	    map.put("codigoClasificacion",f.getCodigoClasificacion());
	    map.put("codigoCurso",f.getCodigoCurso());
	    map.put("codigoCliente",f.getCodigoConsultora());
	    map.put("indicadorEnvioComercial",f.getIndicadorEnvioComercial());
	    List listClasificaciones = service.getListClasificaciones(map);
	     eduConsultaListadoClasificacionesList=listClasificaciones;
	    
		return listClasificaciones;


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

	public void setEduRegistroClasificacionList(List eduRegistroClasificacionList) {
		this.eduRegistroClasificacionList = eduRegistroClasificacionList;
	}

	public List getEduCursoList() {
		return eduCursoList;
	}

	public void setEduCursoList(List eduCursoList) {
		this.eduCursoList = eduCursoList;
	}
	



}
