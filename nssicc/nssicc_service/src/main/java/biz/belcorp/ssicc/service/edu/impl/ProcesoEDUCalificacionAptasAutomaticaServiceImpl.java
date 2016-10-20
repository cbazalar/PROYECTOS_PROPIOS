package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCalificacionAptasDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.ProcesoBatchDAO;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.edu.ProcesoEDURecodificacionConsultoraService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Service("edu.procesoEDUCalificacionAptasAutomaticaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUCalificacionAptasAutomaticaServiceImpl extends BaseService  
		implements ProcesoEDUCalificacionAptasAutomaticaService	{
	
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService	procesoEDUComercialService;
	
	@Resource(name="edu.procesoEDURecodificacionConsultoraService")
	ProcesoEDURecodificacionConsultoraService procesoEDURecodificacionConsultoraService;
	
	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;
	
	@Resource(name="scsicc.procesoBatchDAO")
	ProcesoBatchDAO procesoBatchDAO;
	
	@Resource(name="edu.procesoEDUCalificacionAptasDAO")
	ProcesoEDUCalificacionAptasDAO procesoEDUCalificacionAptasDAO;
	
	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasAutomaticaService#getCursosCalificacionAptasAutomaticaByCriteria(java.util.Map)
	 */
	public List getCursosCalificacionAptasAutomaticaByCriteria(Map criteria) {
		return procesoEDUCalificacionAptasDAO.getCursosCalificacionAptasAutomaticaByCriteria(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasAutomaticaService#getCampannaActualProceso(java.util.Map)
	 */
	public String getCampannaActualProceso(Map criteria) {
		return procesoEDUCalificacionAptasDAO.getCampannaActualProceso(criteria);
	}
	
	public String getFechaProcesoFacturacion(Map criteria) {
		return procesoEDUCalificacionAptasDAO.getFechaProcesoFacturacion(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasAutomaticaService#executeCalificacionAptasAutomatica(java.lang.String, java.util.Map)
	 */
	public void executeCalificacionAptasAutomatica(String codigoPais, Map params) throws Exception {
		
		/* Obteniendo parametros del Pais y Empresa */
		String codigoEmpresa = (String) params.get("codigoEmpresa");
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro =	mantenimientoEDUGenericoDAO.getParametroCurso(parametro);
		params.put("indicadorNombreCompleto", parametro.getIndicadorNombreCompleto());
		params.put("indicadorBloqueo", parametro.getIndicadorBloqueo());
		params.put("indicadorRecodificacion", parametro.getIndicadorRecodificacion());
		params.put("indicadorClasificacionEquivalencia", parametro.getIndicadorClasificacionEquivalencia());
		params.put("valorDelimitadorNombreCompleto", parametro.getValorDelimitadorNombreCompleto());
		params.put("indicadorEquivalenciaMensaje", parametro.getIndicadorEquiMensaje());	
		params.put("indicadorDespachoClasificacion", parametro.getIndicadorDespachoClasificacion());	
		
		//implementado para todos
		procesoEDUComercialService.executeProcesoEDUProcesoCargaRecepcionMaestros(codigoPais,params);
		
		/* Verificando que existan regiones y zonas */
		Usuario usuario = (Usuario) params.get("usuario");
		this.verificarRegionesZonas(params, codigoPais, codigoEmpresa, usuario);
		
		/*Inserta regiones que cierran segun cronograma de operaciones (implentado solo para colombia y sicc x ahora) 03/11/2008*/
		this.executeRegionesAcerrar(codigoPais,params);
		/* Invocando procesos previos a la Calificacion de Aptas Automatica */
		// Proceso de Recodificacion de Consultoras
		if (Constants.EDU_INDICADOR_RECODIFICACION_SI.equals(parametro.getIndicadorRecodificacion())) {
			List listaRecodificacion = procesoEDUComercialService.getRecodificacionConsultora(codigoPais, params);
			if (listaRecodificacion != null) {
				for(int i=0; i < listaRecodificacion.size(); i++) {
					Map paramsRecodificacion = (Map) listaRecodificacion.get(i);
					paramsRecodificacion.put("codigoPais", codigoPais);
					paramsRecodificacion.put("codigoEmpresa", codigoEmpresa);
					paramsRecodificacion.put("usuarioLogin", usuario.getLogin());
					procesoEDURecodificacionConsultoraService.executeRecodificacionConsultora(paramsRecodificacion);
				}	
			}
		}
		
		
		/* Carga de Pedidos de Consultora */
		procesoEDUComercialService.deleteHistoricoBloqueoConsultoraTemporal(params); 
		procesoEDUComercialService.deleteHistoricoBloqueoConsultoraTemporalComercial(codigoPais, params);
		
		procesoEDUComercialService.executeProcesoEDUCargarHistoricoPedidos(codigoPais, params);
		procesoEDUComercialService.executeProcesoEDUCargarConsultorasNuevas(codigoPais, params);
		//23/09/2008 carga los pedidos de consultoras ue solicitan un CUV de educacion en un nuevo temporal 
		procesoEDUComercialService.executeProcesoEDUCargarPedidosCUV(codigoPais, params);
					
        /* Invocando a la calificacin Aptas automatica */
		params.put("valorEnvio","N");
		String cursos[] = (String[])params.get("listaCursos");
		if (cursos != null && cursos.length > 0) {
			
			/* Proceso de Calificacion Automatica de Aptas */
			for(int i=0; i < cursos.length; i++ ) {
				cursos[i] = cursos[i].trim();
				params.put("codigoPais", codigoPais);
				params.put("codigoCurso", cursos[i]);
				
				/* Invocando Procedimiento Oracle que filtre calificadoras aptas */
				procesoEDUCalificacionAptasDAO.executeCalificacionAptasAutomatica(params);
			}
		}
		
		/* Proceso de Bloqueo de consultora */
		if (Constants.EDU_INDICADOR_BLOQUEO_SI.equals(parametro.getIndicadorBloqueo())) {
			List listaHistoricoBloqueo = procesoEDUComercialService.getHistoricoBloqueoConsultoraTemporal(params);
			procesoEDUComercialService.insertBloqueoConsultora(codigoPais, listaHistoricoBloqueo,params);
		}	
	}


	
	private void executeRegionesAcerrar(String codigoPais,Map params) throws Exception{
		/*Inserta regiones que cierran segun cronograma de operaciones (implentado solo para colombia x ahora) 03/11/2008*/
		procesoEDUComercialService.executeRegionesAcerrar(codigoPais,params);
	}


	/**
	 * Verifica la existencia de Regiones y Zonas
	 * @param params
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	private void verificarRegionesZonas(Map params, String codigoPais, String codigoEmpresa, Usuario usuario) throws Exception {
		RegionCursoCapacitacion region = new RegionCursoCapacitacion();
		region.setCodigoPais(codigoPais);
		region.setCodigoEmpresa(codigoEmpresa);
		List listaRegion = mantenimientoEDUCursoCapacitacionDAO.getRegion(region);
		
		/* Verificando existencia de Regiones */
		if (listaRegion == null || listaRegion.size() <= 0) {
			String mensajeError =  this.messageSource.
					getMessage("procesoEDUCalificacionEnviarAptasAutomaticaForm.error.noExistenRegiones",
					null, getLocale(usuario));
			throw new Exception(mensajeError);
		}
		
		/* Verificando existencia de Zonas */
		ZonaCursoCapacitacion zona = new ZonaCursoCapacitacion();
		zona.setCodigoPais(codigoPais);
		zona.setCodigoEmpresa(codigoEmpresa);
		List listaZona = mantenimientoEDUCursoCapacitacionDAO.getZona(zona);
		if (listaZona == null || listaZona.size() <= 0) {
			String mensajeError =  this.messageSource.
					getMessage("procesoEDUCalificacionEnviarAptasAutomaticaForm.error.noExistenZonas",
					null, getLocale(usuario));
			throw new Exception(mensajeError);
		}
		return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUCalificacionAptasAutomaticaService#executeCalificacionEnviarAptasAutomatica(java.lang.String, java.util.Map)
	 */
	public void executeCalificacionEnviarAptasAutomatica(String codigoPais, Map params) throws Exception {
		params.put("tipoProceso","");
		String fechaFact = (String) params.get("fechaFacturacion");
		params.put("fechaFact", fechaFact);
		this.executeCalificacionAptasAutomatica(codigoPais, params);
		
		/* Borrando Mensajes de Equivalencia */
		String indicadorEquivalenciaMensaje = (String) params.get("indicadorEquivalenciaMensaje"); 
		if (Constants.EDU_INDICADOR_EQUIVALENCIA_MENSAJE_SI.equals(indicadorEquivalenciaMensaje)) {
			procesoEDUComercialService.executeBorrarEquivalenciaMensaje(codigoPais, params);
		}
		
		/* Envio de Aptas */
		procesoEDUComercialService.executeProcesoEDUEnviarAptas(codigoPais, params);
		procesoEDUComercialService.executeProcesoEDUCargarBeneficiosCapacitadas(codigoPais, params);
		
		/* En caso que este activado el flag de Equivalencia de Clasificacion */
		String indicadorEquiClasificacion = (String) params.get("indicadorClasificacionEquivalencia"); 
		if (Constants.EDU_INDICADOR_EQUIVALENCIA_CLASIFICACION_SI.equals(indicadorEquiClasificacion)) {
			procesoEDUComercialService.executeEnvioEquivalenciaClasificacion(codigoPais, params);
		}
		
		// Llama a la ejecucion de procesos post calificacion y envio de aptas automatica
		//this.ejecutarProcesosPostCalificacionEnvioAptasAutomatica(params);
	}
	
	
	/**
	 * Solicita la ejecucion de un Procedimiento Almacenado el cual verifca 
	 * los indicadores para ejecutar o no 
	 * los procesos post ejecucion Calificacion Automatica 
	 */
//	private void ejecutarProcesosPostCalificacionEnvioAptasAutomatica(Map params){
//		procesoEDUCalificacionAptasDAO.ejecutarProcesosPostCalificacionEnvioAptasAutomatica(params);
//	}
		
}
