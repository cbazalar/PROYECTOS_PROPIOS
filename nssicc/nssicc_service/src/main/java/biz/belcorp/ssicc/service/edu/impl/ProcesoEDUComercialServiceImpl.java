package biz.belcorp.ssicc.service.edu.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUComercialDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUInicioProcesosDiariosDAO;
import biz.belcorp.ssicc.dao.edu.gen.model.MatrizProducto;
import biz.belcorp.ssicc.dao.edu.gen.model.ProductoDetalle;
import biz.belcorp.ssicc.dao.edu.model.ConexionExterna;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.edu.gen.GenericoEDUFacadeService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUComercialServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 */
@Service("edu.procesoEDUComercialService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDUComercialServiceImpl extends BaseService implements
		ProcesoEDUComercialService {

	@Resource(name="edu.genericoEDUFacadeService")
	GenericoEDUFacadeService genericoEDUFacadeService;

	@Resource(name="edu.procesoEDUComercialDAO")
	ProcesoEDUComercialDAO procesoEDUComercialDAO;
	
	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;

	@Resource(name="edu.procesoEDUInicioProcesosDiariosDAO")
	ProcesoEDUInicioProcesosDiariosDAO procesoEDUInicioProcesosDiariosDAO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDURecepcionarPedidos(java.lang.String,
	 *      java.util.Map)
	 */
	public void executeProcesoEDURecepcionarPedidos(String codigoPais,
			Map params) throws Exception {
		List list = (List) genericoEDUFacadeService.getPedidosComercial(
				codigoPais, params);
		
		/* Verificar si existen pedidos */
		if (list == null || list.size()<= 0) {
			Usuario usuario = (Usuario) params.get("usuario");
			String mensajeError =  this.messageSource.
					getMessage("procesoEDUCalificacionEnviarAptasAutomaticaForm.error.noExistenPedidos",
					null, getLocale(usuario));
			throw new Exception(mensajeError);
		}	
		procesoEDUComercialDAO.deleteTemporalPedidos(params);
		String indicadorNombreCompleto = (String) params.get("indicadorNombreCompleto");
		if (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicadorNombreCompleto))
			procesoEDUComercialDAO.insertTemporalPedidosNombreCompleto(list);
		else
			procesoEDUComercialDAO.insertTemporalPedidos(list);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDURecepcionarPedidosFacturados(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDURecepcionarPedidosFacturados(
			String codigoPais, Map params) throws Exception {
		//Validacion que verifica que se haya  ejecutado la calificacion 
//		boolean isEjecutado = validaExecuteProcesoCalificacion(codigoPais, params);
//		if(!isEjecutado){
//			String mensajeError = getMensajeError(params,"procesoEDUCierreFacturacionDiarioForm.error.executeNoProcesoCalificacion"); 
//			throw new Exception(mensajeError);
//		}
		boolean hayConsFact=this.executeProcesoEDURecepcionarPedidosConsultorasFacturados(codigoPais, params);
		boolean hayCursFact=this.executeProcesoEDURecepcionarCursosFacturados(codigoPais, params);
		if(hayConsFact || hayCursFact ){
//			El envio se hace ahora bien se haga el registro del cronograma, Mantenimiento Cronograa Dictado			
			executaProcesoEDURecepcionarCursosNoFacturadosMixto(codigoPais,params);//los cursos mixtos 
			//que se encuentarn en PO los pasa a PC en su misma campana
		    //se ejecuta el proceso de pedidos rezagados , movido de la cali a cieere facturacion
			executeProcesoConsultoraRezagadas(params);
			return;
			}
		else{
			 Usuario usuario = (Usuario) params.get("usuario");
			 String mensajeError =  this.messageSource.
						getMessage("procesoEDUCierreFacturacionDiarioForm.error.noExistenPedidos",
						null, getLocale(usuario));
			throw new Exception(mensajeError);
			
		}
		
	}

	private void executeProcesoConsultoraRezagadas(Map params) {
		
		try {
			procesoEDUInicioProcesosDiariosDAO.executeProcesoConsultoraRezagadas(params);
		} catch (Exception e) {
		   log.debug("error en executeProcesoConsultoraRezagadas " + e.getMessage());
		}		
	}

	private boolean validaExecuteProcesoCalificacion(String codigoPais, Map params) throws Exception{
		//verifica que exista consultoras en estado PF o FC o PO para la campaa de proceso
		//Esto al menos asegura que haya siod ejecutado la calificacion antes
		Integer cont = (Integer) procesoEDUComercialDAO.validaExecuteProcesoCalificacion(codigoPais, params);		
		return (cont != null && cont.intValue()>0 ? true: false);//true:han ejecutado false: no han ejecutado
	}

	private String getMensajeError(Map params, String key) {
		Usuario usuario = (Usuario) params.get("usuario");
		String mensajeError =  this.messageSource.getMessage(key,null, getLocale(usuario));
		return mensajeError;
	}

	private void executaProcesoEDURecepcionarCursosNoFacturadosMixto(String codigoPais, Map params) {
		log.debug("executaProcesoEDURecepcionarCursosNoFacturadosMixto");
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		List list =(List) procesoEDUComercialDAO
				.getPedidosCursosNoFacturadosMixto(codigoPais, params);
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidosCursos();
			procesoEDUComercialDAO.insertTemporalPedidosCursosFacturados(list, codigoPais, codigoEmpresa);
			procesoEDUComercialDAO.updatePedidosCursosNoFacturados(codigoPais, params);
		}
	}

	/**
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private boolean executeProcesoEDURecepcionarPedidosConsultorasFacturados(
			String codigoPais, Map params) throws Exception {

		List list = (List) genericoEDUFacadeService
				.getPedidosComercialFacturados(codigoPais, params);
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidos(params);
			procesoEDUComercialDAO.insertTemporalPedidosFacturados(list);
			procesoEDUComercialDAO.updateHistoricoPedidosFacturados(codigoPais, params);
			return true;
		}
		return false;
	}
	
	/**
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private boolean executeProcesoEDURecepcionarCursosFacturados(
			String codigoPais, Map params) throws Exception {
		
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		List list = (List) genericoEDUFacadeService
				.getPedidosCursosFacturados(codigoPais, params);
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidosCursos();
			procesoEDUComercialDAO.insertTemporalPedidosCursosFacturados(list, codigoPais, codigoEmpresa);
			procesoEDUComercialDAO.updatePedidosCursosFacturados(codigoPais, params);
			return true;
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDURecepcionarCursosNoFacturados(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDURecepcionarCursosNoFacturados(
			String codigoPais, Map params) throws Exception {
		
		String valor=genericoEDUFacadeService.executeFakeProcesoCursoNoFacturados(codigoPais,params);
		if(StringUtils.isNotEmpty(valor)){ // solo FOX Y ORACLE
			executaProcesoEDURecepcionarCursosNoFacturadosMixtoYCosto(codigoPais,params);
			return ;
		}
		//AS400
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		List list = (List) genericoEDUFacadeService
				.getPedidosCursosNoFacturados(codigoPais, params);
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidosCursos();
			procesoEDUComercialDAO.insertTemporalPedidosCursosFacturados(list, codigoPais, codigoEmpresa);
			procesoEDUComercialDAO.updatePedidosCursosNoFacturados(codigoPais, params);
		}

	}
	
	private void executaProcesoEDURecepcionarCursosNoFacturadosMixtoYCosto(String codigoPais, Map params)throws Exception  {
		log.debug("executaProcesoEDURecepcionarCursosNoFacturadosMixtoYCosto");
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		List list = (List) procesoEDUComercialDAO
				.getPedidosCursosNoFacturadosMixtoYCosto(codigoPais, params);
		if (list != null && list.size() > 0){
			log.debug("list "+ list.size() );
			procesoEDUComercialDAO.deleteTemporalPedidosCursos();
			procesoEDUComercialDAO.insertTemporalPedidosCursosFacturados(list, codigoPais, codigoEmpresa);
			procesoEDUComercialDAO.updatePedidosCursosNoFacturados(codigoPais, params);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUCargarHistoricoPedidos(java.lang.String,
	 *      java.util.Map)
	 */
	public void executeProcesoEDUCargarHistoricoPedidos(String codigoPais,
			Map params) throws Exception {

		this.executeProcesoEDURecepcionarPedidos(codigoPais, params);
		procesoEDUComercialDAO.updateHistoricoPedidos(codigoPais, params);
		procesoEDUComercialDAO.updateMaestroClientes(codigoPais, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUCargarConsultorasNuevas(java.lang.String,
	 *      java.util.Map)
	 */
	public void executeProcesoEDUCargarConsultorasNuevas(String codigoPais,
			Map params) {
		params.put("indicadorPedido", "1");
		/*
		 * List list =
		 * (List)genericoEDUFacadeService.getConsultorasNuevas(codigoPais,
		 * params); Usuario usuario = (Usuario)params.get("usuario");
		 * params.put("codusuario",usuario.getLogin());
		 * procesoEDUComercialDAO.insertMaestroClientes(list, params);
		 */
	}

	public ProductoDetalle getDetalleProducto(String codigoPais, Map params)
			throws Exception {

		ProductoDetalle productoDetalle = null;
		List list = (List) genericoEDUFacadeService.getDetalleProducto(
				codigoPais, params);
		if (list != null && list.size() > 0) {
			productoDetalle = (ProductoDetalle) list.get(0);
		}
		return productoDetalle;
	}

	public MatrizProducto getMatrizProducto(String codigoPais, Map params)
			throws Exception {

		MatrizProducto matrizProducto = null;
		List list = (List) genericoEDUFacadeService.getMatrizProducto(
				codigoPais, params);
		if (list != null && list.size() > 0) {
			matrizProducto = (MatrizProducto) list.get(0);
		}

		return matrizProducto;
	}

	public void executeProcesoEDUProcesoCargaRecepcionMaestros(
			String codigoPais, Map params) throws Exception {
		
		this.executeProcesoEDUCargarControlFacturacion(codigoPais, params);
		this.executeProcesoEDUCargarRegiones(codigoPais, params);
		this.executeProcesoEDUCargarZonas(codigoPais, params);

	}

	public void executeProcesoEDUCargarRegiones(String codigoPais, Map params)
			throws Exception {

		List list = (List) genericoEDUFacadeService.getRegionesComercial(
				codigoPais, params);
		procesoEDUComercialDAO.deleteTemporalRegiones();
		procesoEDUComercialDAO.insertTemporalRegiones(list, params);
		procesoEDUComercialDAO.updateMaestroRegiones(codigoPais, params);
	}

	public void executeProcesoEDUCargarZonas(String codigoPais, Map params)
			throws Exception {

		List list = (List) genericoEDUFacadeService.getZonasComercial(
				codigoPais, params);
		procesoEDUComercialDAO.deleteTemporalZonas();
		procesoEDUComercialDAO.insertTemporalZonas(list, params);
		procesoEDUComercialDAO.updateMaestroZonas(codigoPais, params);
	}

	public void executeProcesoEDUCargarControlFacturacion(String codigoPais,
			Map params) throws Exception {
		log.debug("executeProcesoEDUCargarControlFacturacion ");
		String isProcesoCalificacion=(String)params.get("isProcesoCalificacion");
		List list = (List) genericoEDUFacadeService
				.getControlFacturacionComercial(codigoPais, params);
		log.debug("list "+list.size());
		procesoEDUComercialDAO.deleteTemporalControlFacturacion();
		procesoEDUComercialDAO.insertTemporalControlFacturacion(list, params);
		if(Constants.SI.equals(isProcesoCalificacion)){
		  //validacion de la campanha que exista ya en Control de facturacion de Educacion
		  // con esto se asegura que se haya ejecutado proceso de recpcion maestros
			Integer numCampanha = procesoEDUComercialDAO.getValidaCampanha(params);
			if(numCampanha.intValue()==1){
		      //realizamos solamnete un update al control de facturacion		
			   procesoEDUComercialDAO.updateToControlFacturacion(codigoPais, params);
			}else{
				//envio de mensaje de error
				Usuario usuario = (Usuario) params.get("usuario");
				Map map = (Map)list.get(0);
				String [] periodo = new String[1];
				periodo[0] = String.valueOf(map.get("codigoPeriodo"));
				 String mensajeError =  this.messageSource.
							getMessage("procesoEDUCalificacionEnviarAptasAutomaticaForm.error.noExistenRegiones",
									 periodo, getLocale(usuario));
				throw new Exception(mensajeError);
			}
		}else{
		procesoEDUComercialDAO.updateControlFacturacion(codigoPais, params);
	}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarAptas(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarAptas(String codigoPais, Map params)
			throws Exception {
		log.debug("executeProcesoEDUEnviarAptas");
		this.executeProcesoEDUEnviarAptasMixtoBloqueo(codigoPais, params);
		this.executeProcesoEDUEnviarAptasSinCosto(codigoPais, params);
		this.executeProcesoEDUEnviarAptasCosto(codigoPais, params);
		String indicadorEquiClasificacion = (String) params.get("indicadorClasificacionEquivalencia"); 
		log.debug(" indicadorEquiClasificacion " + indicadorEquiClasificacion);
		if (Constants.EDU_INDICADOR_EQUIVALENCIA_CLASIFICACION_SI.equals(indicadorEquiClasificacion)) {
			this.executeProcesoEDUCompraCursoCosto(codigoPais, params);
		}else{
			this.executeProcesoEDUEnviarConfirmacionCursoCosto(codigoPais, params);
		}
	}

	private void executeProcesoEDUEnviarConfirmacionCursoCosto(String codigoPais, Map params) throws Exception {
		//se obtien lista de consultoras que estan para comprar el curso
		List list = (List) procesoEDUComercialDAO.getConsultorasConfirmCursoCosto(params);		
		String numeroLote ="";
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("codusuario", usuario.getLogin());

		if (list != null && list.size() > 0) {
			
			/* Verificando que exista Clasificaciones Tipo Invitacin en la lista de Aptas */
			this.verificarClasificacionEnvioAptas(usuario, list);
			
			//SE MANDA LISTA DE CURSOS CON COSTO
			params.put("tipoCostoCurso",Constants.EDU_TIPO_CURSO_CON_COSTO);
			String [] listCursos = procesoEDUComercialDAO.getListaCursosByTipoCurso(params);
			params.put("listCursos",listCursos);
			//SE BORRA LOS CURSOS 
			genericoEDUFacadeService.deleteConsultorasConfirmanCursoCosto(codigoPais, params);
			//se inserta en el EDCURCST con estado 'C'
			genericoEDUFacadeService.insertConsultorasConfirmanCursoCosto(codigoPais, list,
					numeroLote,params);
	
	}

		
	}
		
	private void executeProcesoEDUCompraCursoCosto(String codigoPais, Map params) throws Exception{
		procesoEDUComercialDAO.executeProcesoConsultorasCompraCursoCosto(params);
	}

	/**
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	private void executeProcesoEDUEnviarAptasSinCosto(String codigoPais, Map params)
			throws Exception {
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("codusuario", usuario.getLogin());
		log.debug("executeProcesoEDUEnviarAptasSinCosto ");
		List list = (List) procesoEDUComercialDAO.getConsultorasAptas(params);
		
		params.put("tipoLote", Constants.EDU_NUMERO_LOTE_DIARIO);
		params.put("codigoPais", codigoPais);
		
		if (list != null && list.size() > 0) {
			
			/* Verificando que exista Clasificaciones Tipo Invitacin en la lista de Aptas */
			this.verificarClasificacionEnvioAptas(usuario, list);	
			
			/* En caso este activado el indicador de Equivalencia de Clasificacion */
			String indicadorEquiClasificacion = (String) params.get("indicadorClasificacionEquivalencia"); 
			if (Constants.EDU_INDICADOR_EQUIVALENCIA_CLASIFICACION_SI.equals(indicadorEquiClasificacion)) {
				procesoEDUComercialDAO.executeInsertaEquivalenciaClasificacion(list);
			}
			
			procesoEDUComercialDAO.updateNumeroLoteSgte(params);
			String numeroLote = procesoEDUComercialDAO.getNumeroLote(params);
			params.put("numeroLote", numeroLote);
			genericoEDUFacadeService.deleteConsultorasAptas(codigoPais, params);
			genericoEDUFacadeService.insertConsultorasAptas(codigoPais, list,
					numeroLote,params);
			procesoEDUComercialDAO.updateEnvioHistoricoAptas(params);
		}
	}

	/**
	 * Verificando que exista Clasificaciones Tipo Invitacin en la lista de Aptas 
	 * @param usuario
	 * @param list
	 * @throws Exception
	 */
	private void verificarClasificacionEnvioAptas(Usuario usuario, List list) throws Exception {
		
		Iterator listIterator = list.iterator();
		while (listIterator.hasNext()) {
			Map data = (Map) listIterator.next();
			String codigoClasificacion = (String) data.get("codigoClasificacion");
			if (StringUtils.isBlank(codigoClasificacion)) {
				String mensajeError =  this.messageSource.
						getMessage("procesoEDUCalificacionEnviarAptasAutomaticaForm.error.noExisteClasificacionInvitacion",
						null, getLocale(usuario));
				throw new Exception(mensajeError);
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarAptasCosto(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarAptasCosto(String codigoPais, Map params)
			throws Exception {
		List list = (List) procesoEDUComercialDAO.getConsultorasAptasCosto(params);		
				
		params.put("tipoProceso","");
		params.put("tipoLote", Constants.EDU_NUMERO_LOTE_DIARIO);
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("codusuario", usuario.getLogin());

		if (list != null && list.size() > 0) {
			
			/* Verificando que exista Clasificaciones Tipo Invitacin en la lista de Aptas */
			this.verificarClasificacionEnvioAptas(usuario, list);
			
			/* En caso este activado el indicador de Equivalencia de Clasificacion */
			String indicadorEquiClasificacion = (String) params.get("indicadorClasificacionEquivalencia"); 
			if (Constants.EDU_INDICADOR_EQUIVALENCIA_CLASIFICACION_SI.equals(indicadorEquiClasificacion)) {
				procesoEDUComercialDAO.executeInsertaEquivalenciaClasificacion(list);
			}
			
			String numeroLote = procesoEDUComercialDAO.getNumeroLote(params);
			params.put("numeroLote", numeroLote);
			
			//SE MANDA LISTA DE CURSOS CON COSTO
			params.put("tipoCostoCurso",Constants.EDU_TIPO_CURSO_CON_COSTO);
			String [] listCursos = procesoEDUComercialDAO.getListaCursosByTipoCurso(params);
			params.put("listCursos",listCursos);
			//SE BORRA LOS CURSOS 
			genericoEDUFacadeService.deleteConsultorasAptasCosto(codigoPais, params);
			genericoEDUFacadeService.insertConsultorasAptasCosto(codigoPais, list,
					numeroLote,params);
			procesoEDUComercialDAO.updateEnvioHistoricoAptasCosto(params);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarAptasMixtoBloqueo(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarAptasMixtoBloqueo(String codigoPais, Map params)
			throws Exception {
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("codusuario", usuario.getLogin());
		params.put("tipoLote", Constants.EDU_NUMERO_LOTE_DIARIO);
		params.put("codigoPais", codigoPais);
		
		String numeroLote = procesoEDUComercialDAO.getNumeroLote(params);
		params.put("numeroLote", numeroLote);
		//PROCESO SE ENCARGA DE PONER LOS CURSOS MIXTOS EN PO EN APTAS
		procesoEDUComercialDAO.executeProcesoConsultorasAptasMixtoBloqueo(params);
		List list = (List) procesoEDUComercialDAO.getConsultorasAptasMixtoBloqueo(params);		
		
		/* Verificando que exista Clasificaciones Tipo Invitacin en la lista de Aptas */
		if (list != null && list.size() > 0) {
		this.verificarClasificacionEnvioAptas(usuario, list);
			
			
			//SE MANDA LISTA DE CURSOS MIXTO
			params.put("tipoCostoCurso",Constants.EDU_TIPO_CURSO_MIXTO);
			String [] listCursos = procesoEDUComercialDAO.getListaCursosByTipoCurso(params);
			log.debug("listCursos "+listCursos);
			params.put("listCursos",listCursos);
			log.debug("listCursos "+ params);
			//SE BORRA LOS CURSOS MIXTOS , EL BORRADO LO HACE DE LA MISMA TABLA DE CURSO CON COSTO
			genericoEDUFacadeService.deleteConsultorasAptasCosto(codigoPais, params);
			
			genericoEDUFacadeService.insertConsultorasAptasMixtoBloqueo(codigoPais, list,
					numeroLote,params);			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarAptasPorProgramar(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarAptasPorProgramar(String codigoPais,
			Map params) throws Exception {
		List list = (List) procesoEDUComercialDAO
				.getConsultorasAptasporProgramar(params);
		Usuario usuario = (Usuario) params.get("usuario");
		String periodo = (String) params.get("codigoPeriodo");
		params.put("codusuario", usuario.getLogin());

		if (list != null && list.size() > 0) {
			procesoEDUComercialDAO.updateNumeroLoteSgte(params);
			params.put("tipoLote", Constants.EDU_NUMERO_LOTE_REGION);
			String numeroLote = procesoEDUComercialDAO.getNumeroLote(params);
			params.put("numeroLote", numeroLote);
			genericoEDUFacadeService.deleteConsultorasAptasProgramar(
					codigoPais, params);
			genericoEDUFacadeService.insertConsultorasAptasporProgramar(
					codigoPais, list, numeroLote, periodo,params);
			procesoEDUComercialDAO.insertMaestroProgramacionCursos(params);
			procesoEDUComercialDAO.updateEnvioHistoricoAptasProgramadas(params);

		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getExisteCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public Integer getExisteCursoCapacitacion(String codigoPais,
			Map params) throws Exception {
		Integer contador = genericoEDUFacadeService.getExisteCursoCapacitacion(codigoPais,params);
    	return contador;

	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeInsertParametrosCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public void executeInsertParametrosCursoCapacitacion(String codigoPais,
			Map params) throws Exception {
		genericoEDUFacadeService.insertParametrosCursoCapacitacion(codigoPais,
				params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeUpdateParametrosCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public void executeUpdateParametrosCursoCapacitacion(String codigoPais,
			Map params)throws Exception {
		genericoEDUFacadeService.updateParametrosCursoCapacitacion(codigoPais,
				params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUCargarAptasPorProgramar(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUCargarAptasPorProgramar(String codigoPais,
			Map params) throws Exception {
		List list = (List) genericoEDUFacadeService
				.getConsultorasAptasporProgramar(codigoPais, params);
		procesoEDUComercialDAO.deleteTemporalConsultorasAptasporProgramar();
		procesoEDUComercialDAO.insertTemporalConsultorasAptasporProgramar(list,
				params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDURenEnviarAptas(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDURenEnviarAptas(String codigoPais, Map params)
			throws Exception {
		params.put("tipoLote", Constants.EDU_NUMERO_LOTE_DIARIO);
		String numeroLote = procesoEDUComercialDAO.getNumeroLote(params);
		params.put("numeroLote", numeroLote);
		params.put("tipoProceso", "R");
		List list = (List) procesoEDUComercialDAO.getConsultorasAptas(params);
		genericoEDUFacadeService.deleteConsultorasAptas(codigoPais, params);
		genericoEDUFacadeService.insertConsultorasAptas(codigoPais, list,
				numeroLote,params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDURenEnviarAptasPorProgramar(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDURenEnviarAptasPorProgramar(String codigoPais,
			Map params) throws Exception {
		params.put("tipoLote", Constants.EDU_NUMERO_LOTE_REGION);
		String numeroLote = procesoEDUComercialDAO.getNumeroLote(params);
		String periodo = (String) params.get("codigoPeriodo");
		params.put("tipoProceso", "R");
		params.put("indicadorCierreCampana", "S");
		params.put("numeroLote", numeroLote);
		List list = (List) procesoEDUComercialDAO
				.getConsultorasAptasporProgramar(params);
		genericoEDUFacadeService.deleteConsultorasAptasProgramar(codigoPais,
				params);
		genericoEDUFacadeService.insertConsultorasAptasporProgramar(codigoPais,
				list, numeroLote, periodo,params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarHistoricoCapacitadas(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarHistoricoCapacitadas(String codigoPais, Map params) throws Exception{
		List list = (List) procesoEDUComercialDAO.getAptasHistoricasFacturacion(params);
		genericoEDUFacadeService.deleteAptasHistoricas(codigoPais,params);
		genericoEDUFacadeService.insertAptasHistoricas(codigoPais,list,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarBeneficiosCapacitadas(String codigoPais, Map params) throws Exception{
		params.put("codigoPais",(String)params.get("codigoPais"));
		params.put("campanaProceso",(String)params.get("codigoPeriodo"));
	
		List list = (List) procesoEDUComercialDAO.getResultBeneficiosCapacitadas(params);
		log.debug("list enviar beneficios  "+list.size());
		if (list != null && list.size() > 0) {
//			genericoEDUFacadeService.deleteBeneficiosCapacitadas(codigoPais, params);
			genericoEDUFacadeService.insertBeneficiosCapacitadas(codigoPais,list,params);
			procesoEDUComercialDAO.updateEnvioBeneficiosCapacitadas(params);			
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUCargarBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUCargarBeneficiosCapacitadas(String codigoPais,
			Map params) throws Exception {
		
		procesoEDUComercialDAO.updateCargarBeneficiosCapacitadas(codigoPais, params);
		this.executeProcesoEDUEnviarBeneficiosCapacitadas(codigoPais, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeInsertParametrosClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeInsertParametrosClasificacion(String codigoPais,Map params) throws Exception{
		genericoEDUFacadeService.insertParametrosCursoCapacitacion(codigoPais,
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertMantenimientoCodVenta(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoCodVenta(String codigoPais,Map params) throws Exception{
		genericoEDUFacadeService.insertMantenimientoCodVenta(codigoPais,
				params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeUpdateParametrosClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeUpdateParametrosClasificacion(String codigoPais,Map params) throws Exception{
		genericoEDUFacadeService.updateParametrosCursoCapacitacion(codigoPais,
				params);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeInsertMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeInsertMantenimientoClasificacion(String codigoPais,Map params) throws Exception{
		String tipoClas = (String)params.get("tipoClasificacion");
		
		if (tipoClas.equals("B")){
			genericoEDUFacadeService.deleteMantenimientoClasificacion(codigoPais,params);
			genericoEDUFacadeService.insertMantenimientoClasificacion(codigoPais,params);
		}else if (tipoClas.equals("I")) {
			genericoEDUFacadeService.deleteMantenimientoClasificacionInvi(codigoPais,params);
			genericoEDUFacadeService.insertMantenimientoClasificacionInvi(codigoPais,params);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeInsertUpdateMantenimientoClasificacionValida(java.lang.String, java.util.Map)
	 */
	public void executeInsertUpdateMantenimientoClasificacionValida(String codigoPais,Map params) throws Exception{
		String tipoClas = (String)params.get("tipoClasificacion");
		List listClasificacion=null;
		if (tipoClas.equals("B")){
			listClasificacion=genericoEDUFacadeService.getMantenimientoClasificacion(codigoPais, params);
			if (listClasificacion!=null){ 
				 if( listClasificacion.size() <= 0) {
				genericoEDUFacadeService.insertMantenimientoClasificacion(
						codigoPais, params);
			} else {
				genericoEDUFacadeService.updateMantenimientoClasificacion(
						codigoPais, params);
			}
			} 
		}else if (tipoClas.equals("I")) {
			listClasificacion=genericoEDUFacadeService.getMantenimientoClasificacionInvi(codigoPais, params);
		  	if(listClasificacion!=null){
				if (listClasificacion.size() <= 0) {
				genericoEDUFacadeService.insertMantenimientoClasificacionInvi(
						codigoPais, params);
			} else {
				genericoEDUFacadeService.updateMantenimientoClasificacionInvi(
						codigoPais, params);
			}
		}
	}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeUpdateMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeUpdateMantenimientoClasificacion(String codigoPais,Map params) throws Exception{
		genericoEDUFacadeService.updateMantenimientoClasificacion(codigoPais,
				params);
	}

	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getExisteMensajeEducacion(java.lang.String, java.util.Map)
     */
    public Integer getExisteMensajeEducacion(String dataSource, Map params) throws Exception {
    	Integer contador = genericoEDUFacadeService.getExisteMensajeEducacion(dataSource, params);
    	return contador;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void insertMensajeEducacion(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.insertMensajeEducacion(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#updateMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void updateMensajeEducacion(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.updateMensajeEducacion(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#deleteMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void deleteMensajeEducacion(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.deleteMensajeEducacion(dataSource, params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public Integer getExisteEmpresaComercializadora(String dataSource, Map params) throws Exception {
    	Integer contador = genericoEDUFacadeService.getExisteEmpresaComercializadora(dataSource, params);
    	return contador;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void insertEmpresaComercializadora(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.insertEmpresaComercializadora(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#updateEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void updateEmpresaComercializadora(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.updateEmpresaComercializadora(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#deleteEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void deleteEmpresaComercializadora(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.deleteEmpresaComercializadora(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertBloqueoConsultora(java.lang.String, java.util.List)
	 */
	public void insertBloqueoConsultora(String dataSource, final List list,Map params) throws Exception {
		genericoEDUFacadeService.insertBloqueoConsultora(dataSource, list,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#deleteHistoricoBloqueoConsultoraTemporal(java.util.Map)
	 */
	public void deleteHistoricoBloqueoConsultoraTemporal(Map params) throws Exception {
		procesoEDUComercialDAO.deleteHistoricoBloqueoConsultoraTemporal(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#deleteHistoricoBloqueoConsultoraTemporalComercial(java.lang.String, java.util.Map)
	 */
	public void deleteHistoricoBloqueoConsultoraTemporalComercial(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.deleteBloqueoConsultora(dataSource, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getHistoricoBloqueoConsultoraTemporal(java.util.Map)
	 */
	public List getHistoricoBloqueoConsultoraTemporal(Map params) throws Exception {
		List listHistoricosBloqueadas=null;
		//se obtiene conexion : ORA,FOX,AS4
		ConexionExterna conexion = mantenimientoEDUGenericoDAO.getConexionExternaByCriteria(params);
		log.debug("conexion.getTipoBaseDatosExterna() " + conexion.getTipoBaseDatosExterna());
		if(Constants.EDU_CONEXION_EXTERNA_AS400.equals(conexion.getTipoBaseDatosExterna())){
			listHistoricosBloqueadas= procesoEDUComercialDAO.getHistoricoBloqueoConsultoraTemporal(params);
		}else//otra conexion
			listHistoricosBloqueadas= procesoEDUComercialDAO.getHistoricoBloqueoConsultora(params);
		return listHistoricosBloqueadas;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getRecodificacionConsultora(java.lang.String, java.util.Map)
	 */
	public List getRecodificacionConsultora(String dataSource, Map params) throws Exception {
		return genericoEDUFacadeService.getRecodificacionConsultora(dataSource, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getExisteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public Integer getExisteCronogramaDictado(String dataSource, Map params) throws Exception {
    	Integer contador = genericoEDUFacadeService.getExisteCronogramaDictado(dataSource, params);
    	return contador;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void insertCronogramaDictado(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.insertCronogramaDictado(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#updateCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void updateCronogramaDictado(String dataSource, Map params) throws Exception {
		genericoEDUFacadeService.updateCronogramaDictado(dataSource, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDUEnviarGenerarPlanillaProgramacion(java.lang.String, java.util.Map)
	 */
	public void executeProcesoEDUEnviarGenerarPlanillaProgramacion(String codigoPais, Map params) throws Exception {
		List lista = (List) procesoEDUComercialDAO.getGenerarPlanillaProgramacion(params);		
		if (lista != null && lista.size() > 0) {
			genericoEDUFacadeService.insertGenerarPlanillaProgramacion(codigoPais, params,  lista);
		}
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#deleteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteCronogramaDictado(String codigoPais, Map params) throws Exception {
		genericoEDUFacadeService.deleteCronogramaDictado(codigoPais, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertEnvioCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioCronogramaDictado(String codigoPais, Map params, List lista) throws Exception {
		genericoEDUFacadeService.insertEnvioCronogramaDictado(codigoPais, params,  lista);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#limpiarCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void limpiarCronogramaDictado(String codigoPais, Map params) throws Exception {
		genericoEDUFacadeService.limpiarCronogramaDictado(codigoPais, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertEnvioStatusConsultora(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioStatusConsultora(String codigoPais, Map params, List lista) throws Exception {
		genericoEDUFacadeService.insertEnvioStatusConsultora(codigoPais, params, lista);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeEnvioEquivalenciaClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeEnvioEquivalenciaClasificacion(String codigoPais, Map params) throws Exception{
		genericoEDUFacadeService.executeEnvioEquivalenciaClasificacion(codigoPais,
				params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getTipoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getTipoClasificacionEquivalencia(String codigoPais, Map params) throws Exception {
		return genericoEDUFacadeService.getTipoClasificacionEquivalencia(codigoPais,
				params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getCodigoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getCodigoClasificacionEquivalencia(String codigoPais, Map params) throws Exception{
		return genericoEDUFacadeService.getCodigoClasificacionEquivalencia(codigoPais,
				params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeBorrarEquivalenciaMensaje(java.lang.String, java.util.Map)
	 */
	public void executeBorrarEquivalenciaMensaje(String codigoPais, Map params) throws Exception {
		genericoEDUFacadeService.executeBorrarEquivalenciaMensaje(codigoPais,
				params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#getMigracionComercialConsultora(java.lang.String, java.util.Map)
	 */
	public List getMigracionComercialConsultora(String codigoPais, Map params) throws Exception {
		List lista = genericoEDUFacadeService.getMigracionComercialConsultora(codigoPais, params);
		return lista;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#insertMigracionComercialConsultoraTemporal(java.lang.String, java.util.List)
	 */
	public void insertMigracionComercialConsultoraTemporal(String codigoPais, List lista) throws Exception {
		
	}


	public List getCodigoMensajeEquivalencia(String codigoPais,Map params) throws Exception {
		params.put("codigoPais",codigoPais);
		return genericoEDUFacadeService.getCodigoMensajeEquivalencia(codigoPais,params);
		
	}

	public void executeProcesoEDUCargarPedidosCUV(String codigoPais, Map params) throws Exception {
		this.executeProcesoEDURecepcionarPedidosCUV(codigoPais, params);
		log.debug("ok executeProcesoEDUCargarPedidosCUV");
	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUComercialService#executeProcesoEDURecepcionarPedidos(java.lang.String,
	 *      java.util.Map)
	 */
	public void executeProcesoEDURecepcionarPedidosCUV(String codigoPais,
			Map params) throws Exception {
		List list = (List) genericoEDUFacadeService.getPedidosComercialCUV(
				codigoPais, params);
	
		/* Verifica si existen pedidos */
		if ( list!=null && list.size()> 0) {		
			procesoEDUComercialDAO.deleteTemporalPedidosCUV(params);
			String indicadorNombreCompleto = (String) params.get("indicadorNombreCompleto");
			log.debug("indicadorNombreCompleto " + indicadorNombreCompleto);
			if (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicadorNombreCompleto))
				procesoEDUComercialDAO.insertTemporalPedidosNombreCompletoCUV(list);
			else
				procesoEDUComercialDAO.insertTemporalPedidosCUV(list);
		}
	}

	public void executeRegionesAcerrar(String codigoPais, Map params) throws Exception {
		List list = genericoEDUFacadeService.getListRegionesACerrar(codigoPais,params);
		if(list!=null && list.size()> 0){
		 procesoEDUComercialDAO.deleteRegionesACerrar(codigoPais,params);
		 procesoEDUComercialDAO.insertarRegionesACerrar(list);
		}	
	}

	/**
	 * @return Returns the procesoEDUInicioProcesosDiariosDAO.
	 */
	public ProcesoEDUInicioProcesosDiariosDAO getProcesoEDUInicioProcesosDiariosDAO() {
		return procesoEDUInicioProcesosDiariosDAO;
	}

	/**
	 * @param procesoEDUInicioProcesosDiariosDAO The procesoEDUInicioProcesosDiariosDAO to set.
	 */
	public void setProcesoEDUInicioProcesosDiariosDAO(
			ProcesoEDUInicioProcesosDiariosDAO procesoEDUInicioProcesosDiariosDAO) {
		this.procesoEDUInicioProcesosDiariosDAO = procesoEDUInicioProcesosDiariosDAO;
	}

	public List getListCampanhasActivas(Map criteria) throws Exception  {
		String codigoPais= (String)criteria.get("codigoPais");
		List list = (List) genericoEDUFacadeService.getControlFacturacionComercial(codigoPais, criteria);
		return list;
	}


}
