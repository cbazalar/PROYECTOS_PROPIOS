package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptasTipoCalificacion;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDUCalificacionEnviarAptasAutomaticaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;

public class ProcesoEDUCalificacionEnviarAptasAutomaticaAction extends
	BaseProcesoAbstractAction {

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//
//	/**
//	 * Hook method, invocado antes de realizar el forward del metodo 'view'.
//	 * que es invocado desde el Menu Principal.
//	 * Este metodo no contiene implementacion, su intencion es de ser
//	 * sobreescrito para setear atributos en el request del view o en el form.
//	 * Luego de la llamada a este metodo inmediatamente se realiza el forward al
//	 * 'getViewForward'.
//	 * @param request
//	 *            Request para setear los objetos en sesion
//	 * @param form
//	 *            ActionForm para setear valores
//	 */
//	protected void setViewAttributes(HttpServletRequest request, ActionForm form) {
//		
//	}
//
//
//	/**
//	 * Obtiene descripcion del proceso del archivo de recursos
//	 * @param resultado
//	 * @param messageResources
//	 * @param codigo
//	 * @param keyMensaje
//	 */
//	private void adicionarProceso(ArrayList resultado, MessageResources messageResources, String codigo, String keyMensaje) {
//		Base bean = new Base();
//		String proceso = messageResources.getMessage(keyMensaje);
//		bean.setCodigo(codigo);
//		bean.setDescripcion(proceso);			
//		resultado.add(bean);
//	}
//
//
//	/**
//	 * Seteando Combos
//	 * @param request
//	 * @throws Exception
//	 */
//	private void loadCombos(HttpServletRequest request, ProcesoEDUCalificacionEnviarAptasAutomaticaForm f)
//			throws Exception {
//		HttpSession session = request.getSession(true);
//		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
//		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
//		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
//		parametroEmpresa.setCodigoPais(pais.getCodigo());
//		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
//		
//		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
//		List listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
//		session.setAttribute(Constants.EDU_EMPRESA_COMERCIALIZADORA_LIST, listaEmpresa);
//		
//		if (listaEmpresa != null && listaEmpresa.size() > 0) {
//			EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa.get(0);
//			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
//			
//			/* Cargando archivo de control de Facturacion */
//			Map criteria = new HashMap();
//			criteria.put("codigoPais", f.getCodigoPais());
//			criteria.put("codigoEmpresa", f.getCodigoEmpresa());
//			criteria.put("usuario", usuario);
//			criteria.put("copiarSoloControlFacturacion", Constants.SI);
//			criteria.put("isProcesoCalificacion",Constants.NO);//SE PIDIO Q YA NO VALIDE SI QUE ACTUALIZE EL EDU_CTROL_FACT
//			ProcesoEDUComercialService	procesoEDUComercialService = (ProcesoEDUComercialService) 
//						getBean("edu.procesoEDUComercialService");
//			procesoEDUComercialService.executeProcesoEDUCargarControlFacturacion(f.getCodigoPais(), criteria);
//			
//			/* obteniendo lista de cursos para la calificacion automatica */
//			this.obtenerListaCursos(request, f);
//			
//			/* Obteniendo parametros del Pais y Empresa */
//			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
//			parametro.setCodigoPais(f.getCodigoPais());
//			parametro.setCodigoEmpresa(f.getCodigoEmpresa());
//			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
//			parametro =	siccService.getParametroCurso(parametro);
//			f.setIndicadorRecodificacion(parametro.getIndicadorRecodificacion());
//			f.setIndicadorConsultoraRezagada(parametro.getIndicadorConsultoraRezagada());
//			f.setIndicadorBloqueo(parametro.getIndicadorBloqueo());
//			f.setIndicadorRegistroPlanillasNoProcesadas(parametro.getIndicadorRegistroPlanillasNoProcesadas());
//			
//		}
//		else {
//			session.setAttribute("errorProceso", Constants.SI);
//		}
//	}
//
//	
//	/**
//	 * Obtiene Lista de Cursos luego de encontrar la Campa�a de Proceso
//	 * @param request
//	 * @param f
//	 * @return
//	 * @throws Exception
//	 */
//	protected List obtenerListaCursos(HttpServletRequest request, ProcesoEDUCalificacionEnviarAptasAutomaticaForm f) throws Exception {
//		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
//		HttpSession session = request.getSession(true);
//		ProcesoEDUComercialService	procesoEDUComercialService = (ProcesoEDUComercialService) getBean("edu.procesoEDUComercialService");
//
//		String codigoPeriodo="";
//		String fechaFacturacion = "";
//	
//		// Obteniendo Campa�a de Proceso 
//		Map criteria = new HashMap();
//		criteria.put("codigoPais", f.getCodigoPais());
//		criteria.put("codigoEmpresa", f.getCodigoEmpresa());
//		criteria.put("noCopiarArchivos",Constants.SI);
//		 List listCruceCampanhas = procesoEDUComercialService.getListCampanhasActivas(criteria);
//		 if(listCruceCampanhas.size()==1){//una sola campanha activa
//			 codigoPeriodo = service.getCampannaActualProceso(criteria);
//			 fechaFacturacion = service.getFechaProcesoFacturacion(criteria);
//			 session.removeAttribute(Constants.LISTA_CAMPANHAS);
//		 }
//		 if(listCruceCampanhas.size()>1){
//			//Cruce de Campanha (2 campa�as activas)
//			session.setAttribute(Constants.LISTA_CAMPANHAS,listCruceCampanhas);
//			Map map = (Map)listCruceCampanhas.get(0);
//			codigoPeriodo =(String)map.get("codigoPeriodo");
//			fechaFacturacion =(String)map.get("fechafacturacion");
//			
//		 }
//	
//	
//	 	 
//		if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isBlank(fechaFacturacion)) {
//			session.setAttribute("errorProceso", Constants.SI);
//			return null;
//		}
//		
//		f.setCodigoPeriodo(codigoPeriodo);
//		f.setFechaFacturacion(fechaFacturacion);
//		
//        // Obteniendo Lista de Cursos
//		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
//		criteria.put("fechaFacturacion",f.getFechaFacturacion());
//		criteria.put("estadoRegistro", Constants.ESTADO_ACTIVO);
//		criteria.put("tipoCalificacion", Constants.EDU_TIPO_CALIFICACION_AUTOMATICA);
//		
//		List resultado = service.getCursosCalificacionAptasAutomaticaByCriteria(criteria);
//		session.setAttribute(Constants.EDU_MANTENIMIENTO_PROCESO_APTA_AUTOMATICA_LIST,
//				resultado);
//		
//		/* Obteniendo los codigos de cursos */
//		String[] cursos = new String[resultado.size()];
//		for(int i=0; i < resultado.size(); i++) {
//			CalificacionAptasTipoCalificacion bean = (CalificacionAptasTipoCalificacion) resultado.get(i);
//			cursos[i] = bean.getCodigoCurso();
//		}
//		f.setSelectedItems(cursos);
//		
//		return resultado;
//	}
//	
//	
//	
//	/* (non-Javadoc)
//	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
//	 */
//	protected Map prepareParamsBeforeExecute(ActionForm form, HttpServletRequest request) throws Exception {
//		if (log.isDebugEnabled()) {
//			log.debug("Entering 'prepareParamsBeforeExecute' method");
//		}
////		Map params = super.prepareParamsBeforeExecute(form, request);
//		ProcesoEDUCalificacionEnviarAptasAutomaticaForm f = (ProcesoEDUCalificacionEnviarAptasAutomaticaForm) this.formProceso;
//		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService)getBean("edu.mantenimientoEDUGenericoService");	
//		
//		HttpSession session = request.getSession(true);
//		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
//		String codigoPais = pais.getCodigo();
//		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
//		params.put("codigoPais", codigoPais);
//		params.put("codigoEmpresa", f.getCodigoEmpresa());
//		params.put("codigoPeriodo", f.getCodigoPeriodo());
//		params.put("usuario", usuario);
//		params.put("listaCursos", f.getSelectedItems());
//		
//		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
//		parametro.setCodigoPais(codigoPais);
//		parametro.setCodigoEmpresa(f.getCodigoEmpresa());
//		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
//		parametro =	service.getParametroCurso(parametro);
//		params.put("indicadorEquivalenciaMensaje", parametro.getIndicadorEquiMensaje());	
//		
//		return params;
//	}	
//	
//	
//	/* (non-Javadoc)
//	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#executeProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
//	 */
//	protected Map executeProcess(ActionForm form, HttpServletRequest request, Map params) throws Exception {
//		//super.executeProcess(form, request, params);
//		log.debug("Los parametros del Reporte en el executeProcess son: "
//				+ params.toString());
//		
//		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
//		String codigoPais = (String) params.get("codigoPais");
//					
//        // Invocando a la calificaci�n y Envio de Aptas automatica
//		service.executeCalificacionEnviarAptasAutomatica(codigoPais, params);
//		return params;
//	}
//
//
//	/* (non-Javadoc)
//	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#getCodigoErrorBatch(java.util.Map)
//	 */
//	protected String getCodigoErrorBatch(Map params) {
//		String codigoError = (String) params.get("codigoError");
//		return codigoError;
//	}
//	
//	/**
//	 * @author sbuchelli
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * Se encarga de cargar los Cursos A calificar correspondiente a la campanha de proceso
//	 * */
//	
//	public ActionForward cargarCursos(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) {
//			log.debug("Entering 'cargarCursos' method");
//		}
//		ProcesoEDUCalificacionEnviarAptasAutomaticaForm f = (ProcesoEDUCalificacionEnviarAptasAutomaticaForm) this.formProceso;
//		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
//		HttpSession session = request.getSession(true);
//		
//
//		
//		// Obteniendo Campa�a de Proceso 
//		Map criteria = new HashMap();
//		criteria.put("codigoPais", f.getCodigoPais());
//		criteria.put("codigoEmpresa", f.getCodigoEmpresa());
//		  // Obteniendo Lista de Cursos
//		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
//		criteria.put("fechaFacturacion",f.getFechaFacturacion());
//		criteria.put("estadoRegistro", Constants.ESTADO_ACTIVO);
//		criteria.put("tipoCalificacion", Constants.EDU_TIPO_CALIFICACION_AUTOMATICA);
//		
//		List resultado = service.getCursosCalificacionAptasAutomaticaByCriteria(criteria);
//		session.setAttribute(Constants.EDU_MANTENIMIENTO_PROCESO_APTA_AUTOMATICA_LIST,
//				resultado);
//		
//		/* Obteniendo los codigos de cursos */
//		String[] cursos = new String[resultado.size()];
//		for(int i=0; i < resultado.size(); i++) {
//			CalificacionAptasTipoCalificacion bean = (CalificacionAptasTipoCalificacion) resultado.get(i);
//			cursos[i] = bean.getCodigoCurso();
//		}
//		f.setSelectedItems(cursos);
//		
//		
//		return mapping.findForward(getViewForward());
//	}


}
