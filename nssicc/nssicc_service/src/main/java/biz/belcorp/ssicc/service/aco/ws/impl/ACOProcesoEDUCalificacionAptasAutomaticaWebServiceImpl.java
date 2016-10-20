/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptasTipoCalificacion;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoEDUCalificacionAptasAutomaticaWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoEDUCalificacionAptasAutomaticaWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoEDUCalificacionAptasAutomaticaWebService {

	
    MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	ProcesoEDUCalificacionAptasAutomaticaService procesoEDUCalificacionAptasAutomaticaService;
	
	private static final String CODIGO_SISTEMA = "EDU";
	private static final String CODIGO_PROCESO_BATCH = "07";
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.usuarioService = (UsuarioService) getWebApplicationContext().getBean("usuarioService");
		this.mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService) getWebApplicationContext().getBean("edu.mantenimientoEDUGenericoService");
		this.procesoEDUCalificacionAptasAutomaticaService = (ProcesoEDUCalificacionAptasAutomaticaService) getWebApplicationContext().getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#executeProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	protected Map executeProcess(Map params) throws Exception {
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		
		String codigoPais = (String) params.get("codigoPais");
					
        // Invocando a la calificaci�n Aptas automatica
		this.procesoEDUCalificacionAptasAutomaticaService.executeCalificacionAptasAutomatica(codigoPais, params);
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#
	 * ejecutarProcesoEDUCalificacionEnviarAptasAutomatica(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoEDUCalificacionEnviarAptasAutomatica(
			String codigoPais, 	String codigoEmpresa, String codigoUsuario) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";
		String codigoSistema = CODIGO_SISTEMA;
		String codigoProcesoBatch = CODIGO_PROCESO_BATCH;
		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			String codigoIsoIdioma = pais.getCodigoIdiomaIso();
			if (StringUtils.isBlank(codigoEmpresa)) {
				EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
				parametroEmpresa.setCodigoPais(pais.getCodigo());
				parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
				List listaEmpresa = this.mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(parametroEmpresa);
				
				if (listaEmpresa != null && listaEmpresa.size() > 0) {
					EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa.get(0);
					codigoEmpresa = empresa.getCodigoEmpresa();
				}
			}
			if (StringUtils.isBlank(codigoEmpresa)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoEmpresa", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			Usuario usuario = this.usuarioService.getUsuarioByUsername(codigoUsuario);
					
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", codigoSistema);
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("codigoEmpresa", codigoEmpresa);
			params.put("codigoUsuario", codigoUsuario);
			params.put("usuario", usuario);
			params.put("codigoIsoIdioma", codigoIsoIdioma);
			params.put("pais", pais);
			
			/* Cargando archivo de control de Facturacion */
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("usuario", usuario);
			
			/* obteniendo lista de cursos para la calificacion automatica */
	        params = this.obtenerListaCursos(params);
			
			/* Obteniendo parametros del Pais y Empresa */
			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(codigoPais);
			parametro.setCodigoEmpresa(codigoEmpresa);
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro =	this.mantenimientoEDUGenericoService.getParametroCurso(parametro);
			
			
			
			parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(codigoPais);
			parametro.setCodigoEmpresa(codigoEmpresa);
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro =	this.mantenimientoEDUGenericoService.getParametroCurso(parametro);
			
			// Invocando a la calificaci�n y Envio de Aptas automatica
			this.executeProceso(params);
			mensajeError = MapUtils.getString(criteria, "mensajeError");
    		
			if(StringUtils.isNotBlank(mensajeError))
				throw new Exception(mensajeError);
			
			estado = true;
			objetoRespuesta.setMensaje("Se ejecuto el servicio con exito.");
			objetoRespuesta.setEjecucionExitosa(estado);

		} catch (Exception e) {
			mensajeError = e.getMessage();
			objetoRespuesta.setEjecucionExitosa(false);
			objetoRespuesta.setMensaje(mensajeError);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	
		return objetoRespuesta;
	}

	
    
	/**
	 * Obtiene Lista de Cursos luego de encontrar la Campa�a de Proceso
	 * @param request
	 * @param f
	 * @return
	 * @throws Exception
	 */
	protected Map obtenerListaCursos(Map params) throws Exception {
		
		String codigoPeriodo="";
		String fechaFacturacion = "";
	
		
		// Obteniendo Campa�a de Proceso
		codigoPeriodo = this.procesoEDUCalificacionAptasAutomaticaService.getCampannaActualProceso(params);
		fechaFacturacion = this.procesoEDUCalificacionAptasAutomaticaService.getFechaProcesoFacturacion(params);
			
        // Obteniendo Lista de Cursos
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaFacturacion",fechaFacturacion);
		params.put("estadoRegistro", Constants.ESTADO_ACTIVO);
		params.put("tipoCalificacion", Constants.EDU_TIPO_CALIFICACION_AUTOMATICA);
		
		List resultado = this.procesoEDUCalificacionAptasAutomaticaService.getCursosCalificacionAptasAutomaticaByCriteria(params);
		
		/* Obteniendo los codigos de cursos */
		String[] cursos = new String[resultado.size()];
		for(int i=0; i < resultado.size(); i++) {
			CalificacionAptasTipoCalificacion bean = (CalificacionAptasTipoCalificacion) resultado.get(i);
			cursos[i] = bean.getCodigoCurso();
		}
		params.put("listaCursos", cursos);
		return params;
	}
	
	

}
