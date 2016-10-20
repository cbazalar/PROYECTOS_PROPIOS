package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Implementacion de InterfazExecutionService que utiliza un Map con las
 * implementaciones especificas de las Interfaces SiCC inyectados mediante
 * Spring. Nuevas interfaces requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service.xml'.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazExecutionServiceImpl extends BaseService implements InterfazExecutionService  {
	protected final Log log = LogFactory.getLog(getClass());
	protected final String CODIGO_PAQUETE_ESTANDAR = "GEN-PESTANDAR";
	
	private HistoricoService historicoService;
	 
	private InterfazService interfazService;
	
	private ProcesoBatchService procesoBatchService;
	
	/**
	 * Map que contiene las implementaciones especificas de las Interfaces SiCC. 
	 */
	private Map interfazImplementations;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#getInterfazImplementation(java.lang.String)
	 */
	public BaseInterfazService getInterfazImplementation(String codigo) {
		return (BaseInterfazService) interfazImplementations.get(codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#executeInterfazValidacionesPrevias(java.util.Map)
	 */
	public Map<String, Object> executeInterfazValidacionesPrevias(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeInterfazValidacionesPrevias' method");
		
		params = this.executeInterfazValidacionesPreviasBase(params);
		params = this.executeInterfazValidacionesPreviasPaquete(params);
		
		/* Veerificando los Numeros de Lotes en caso de Interfaz de Entrada o Paquete de Interfaces de Entrada */
		String verifica = this.verificaNumeroLote(params); 
		if(verifica.equals(Constants.NUMERO_UNO)){
			String keyMensaje = "interfaz.numero.lote";
			String mensaje = this.getKeyMessage(keyMensaje);
			throw new Exception(mensaje);
		}
		else{
			if (verifica.equals("-1")){
				boolean validar = true;
				String validarListaArchivosEntrada = (String) params.get("validarListaArchivosEntrada");
				if (StringUtils.isNotBlank(validarListaArchivosEntrada) && Constants.NO.equals(validarListaArchivosEntrada)) {
					validar = false;
				}
				if (validar) {
					String keyMensaje = "interfaz.numero.archivos.incompletos";
					String mensaje = this.getKeyMessage(keyMensaje);
					throw new Exception(mensaje);
				}
			}
		}
		
		if (log.isDebugEnabled())
			log.debug("Fin 'executeInterfazValidacionesPrevias' method");
		return params;
	}
	
	/**
	 * Ejecuta las validaciones BASE antes de la ejecucion de la Interfaz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> executeInterfazValidacionesPreviasBase(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeInterfazValidacionesPreviasBase' method");
		
		Usuario usuario = (Usuario) params.get("usuario");
		
	    /* Validaciones INICIALES */
		String codigoPais = (String) params.get("codigoPais");
		if (StringUtils.isBlank(codigoPais)) {
		    String error = this.getKeyMessage("interfaz.error.codigoPais", usuario);
			throw new Exception(error);
		}
		String codigoSistema = (String) params.get("codigoSistema");
		if (StringUtils.isBlank(codigoSistema)) {
		    String error = this.getKeyMessage("interfaz.error.codigoSistema", usuario);
			throw new Exception(error);
		}
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		if (StringUtils.isBlank(codigoInterfaz)) {
			String error = this.getKeyMessage("interfaz.error.codigoInterfaz", usuario);
			throw new Exception(error);
		}
		
		/* Obteniendo interfaz */
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,	codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
		params.put("interfazEjecucionPK", interfazEjecucionPK);
		params.put("interfazEjecucion", interfazEjecucion);
		
		String tipoInterfaz = interfazEjecucion.getTipoGeneracion();
		params.put("tipoInterfazExecution", tipoInterfaz);
		
		String codigoProcesoBatch = new String();
		if (tipoInterfaz.equals(Constants.TIPO_GENERACION_PAQUETE)) {
			codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
			/*
			if (StringUtils.isBlank(codigoProcesoBatch)) {
			   String error = this.getKeyMessage("interfaz.error.codigoProcesoBatch", usuario);
			   throw new Exception(error);
			}*/
		}	
				
		/* Colocando codigo de Interfaz Empaquetada */
		params.put("codigoInterfazEmpaquetada", interfazEjecucion.getCodigo());
			
		/* Colocando Indicador MultiLote */
		String indicadorMultiLote = interfazEjecucion.getIndicadorMultiLote();
		if (StringUtils.isBlank(indicadorMultiLote)) 
			indicadorMultiLote = Constants.NO;
		params.put("indicadorMultiLote", indicadorMultiLote);
	
		
		/* Obteniendo Service de Implementacion */
//		BaseInterfazService interfazImpl = null;
//		try {
//			interfazImpl = this.getInterfazImplementation(interfazEjecucion.getCodigo());
//		}
//		catch (Exception e) {
//			String error = this.getKeyMessage("interfaz.error.invocacionService", usuario);
//			throw new Exception(error);
//		}
		
		if (log.isDebugEnabled())
			log.debug("Fin 'executeInterfazValidacionesPreviasBase' method");
		return params;
	}
	
	
	/**
	 * Ejecuta las validaciones BASE antes de la ejecucion de la Interfaz para los Paquetes de Interfaz 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> executeInterfazValidacionesPreviasPaquete(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeInterfazValidacionesPreviasPaquete' method");
		
		InterfazPK interfazEjecucionPK = (InterfazPK)params.get("interfazEjecucionPK");
		Interfaz interfazEjecucion = (Interfaz)params.get("interfazEjecucion");
		String tipoInterfazExecution = (String)params.get("tipoInterfazExecution");
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		
		
		/* Validando validaciones propias de Paquete de Interfaz */
		if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_PAQUETE)) {
			
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSistema", codigoSistema);
			
			if (StringUtils.isNotBlank(codigoProcesoBatch)) {
				criteria.put("codigoProcesoBatch", codigoProcesoBatch);
				
				/* Verificando que no se este ejecutando el Proceso Batch */
				String ejecucion = this.verificarProcesoBatchEnEjecucion(criteria);
				if (StringUtils.isNotBlank(ejecucion)) 
					throw new Exception(ejecucion);
				
				/* Verificando que no se este ejecutando Procesos Batch Dependientes */
				ejecucion =  this.verificarProcesoBatchEnEjecucionDependientes(criteria);
				if (StringUtils.isNotBlank(ejecucion)) 
					throw new Exception(ejecucion);
		    }
				
				
			/* VERIFICANDO LISTA DE INTERFACES SELECCIONADAS */
			boolean validarLista = true;
			String validarListaInterfaceSalida = (String) params.get("validarListaInterfaceSalida");
			if (StringUtils.isNotBlank(validarListaInterfaceSalida) && Constants.NO.equals(validarListaInterfaceSalida)) {
				validarLista = false;
			}
			if (!validarLista) {
				return params;
			}
			
			String[] listaInterfacesSeleccionadas = (String[])params.get("listaInterfacesSeleccionadas");
			Boolean indicadorSeleccionActivo = false;
			if (Constants.SI.equals(interfazEjecucion.getIndicadorSeleccion())) {
				if (listaInterfacesSeleccionadas != null)
					indicadorSeleccionActivo = true;
			}
			List listaInterfacesEmpaquetadas = new ArrayList();
			String ejecutarSoloProcesoAdicional = Constants.NO;
			if (indicadorSeleccionActivo) {
				if (listaInterfacesSeleccionadas == null || listaInterfacesSeleccionadas.length <= 0) {
					
					try {
						List<Interfaz> listaProcesosAdicionalesSeleccionadas = (ArrayList<Interfaz>) params.get("listaProcesosAdicionalesSeleccionadas");
						if (listaProcesosAdicionalesSeleccionadas != null && listaProcesosAdicionalesSeleccionadas.size() > 0) 
							ejecutarSoloProcesoAdicional = Constants.SI;
					}
					catch (Exception e) {
						ejecutarSoloProcesoAdicional =  Constants.NO;
					}
					if (ejecutarSoloProcesoAdicional.equals(Constants.NO)) {
						String keyMensaje = "interfaz.sinSeleccionInterfazPaquete";
						String proceso = this.getKeyMessage(keyMensaje);
						throw new Exception(proceso);
					}
				}
				interfazEjecucionPK.setListaInterfacesSeleccionadas(listaInterfacesSeleccionadas);
				listaInterfacesEmpaquetadas = interfazService.getComponentesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
			}	
			else {
				listaInterfacesEmpaquetadas = interfazService.getComponentesInterfazPaquete(interfazEjecucionPK);
			}
				
			/* Verificando que la lista de Interfaces no este vacía */
			if (listaInterfacesEmpaquetadas == null || listaInterfacesEmpaquetadas.size() == 0) {
				
				try {
					List<Interfaz> listaProcesosAdicionalesSeleccionadas = (ArrayList<Interfaz>) params.get("listaProcesosAdicionalesSeleccionadas");
					if (listaProcesosAdicionalesSeleccionadas != null && listaProcesosAdicionalesSeleccionadas.size() > 0) 
						ejecutarSoloProcesoAdicional = Constants.SI;
				}
				catch (Exception e) {
					ejecutarSoloProcesoAdicional =  Constants.NO;
				}
				if (ejecutarSoloProcesoAdicional.equals(Constants.NO)) {
					String keyMensaje = "interfaz.sinSeleccionInterfazPaquete";
					String proceso = this.getKeyMessage(keyMensaje);
					throw new Exception(proceso);
				}
				
			}
		
			
			/* Obteniendo nro de Niveles */
			Integer nroNivelesEjecutar = new Integer(1);
			if (indicadorSeleccionActivo)
				nroNivelesEjecutar = interfazService.getNroNivelesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
			else
				nroNivelesEjecutar = interfazService.getNroNivelesInterfazPaquete(interfazEjecucionPK);
			
			/* seteando params */
			params.put("ejecutarSoloProcesoAdicional", ejecutarSoloProcesoAdicional);
			params.put("nroNivelesEjecutar", nroNivelesEjecutar);
			params.put("indicadorSeleccionActivo", indicadorSeleccionActivo);
			params.put("listaInterfacesEmpaquetadas", listaInterfacesEmpaquetadas);
			
			log.info("Se obtuvieron las interfaces unitarias empaquetadas: "+ listaInterfacesEmpaquetadas);
			
		}
		
		if (log.isDebugEnabled())
			log.debug("Fin 'executeInterfazValidacionesPreviasPaquete' method");
		
		return params;
	}
	
	

	/**
	 * Inserta Registro en la tabla de Procesos BATCH Actuales
	 * @param interfazParams
	 * @throws Exception
	 */
	public Map<String, Object> insertProceBatch(Map<String, Object> params) throws Exception {
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return params;
		
		
		/* INSERTANDO EN BAS_PROCE_BATCH_ACTUA */
		Usuario usuario = (Usuario) params.get("usuario");
		Pais pais = (Pais) params.get("pais");
		Long idProcesoBatch;
		try {
			idProcesoBatch = (Long) params.get("idProcesoBatch");
			if (idProcesoBatch == null || idProcesoBatch == 0) {
				idProcesoBatch = this.procesoBatchService.getSecuenciaSiguienteProcesoBatchActu();
			}	
		}
		catch (Exception e) {
			idProcesoBatch = this.procesoBatchService.getSecuenciaSiguienteProcesoBatchActu();
		}
		
		params.put("idProcesoBatch", idProcesoBatch);
		params.put("mostrarPaginaConsultaBatch", Constants.SI);
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		params.put("recomendacionError", "    ");
		
		try {
			procesoBatchService.deleteProcesoBatchActu(params, usuario);
			procesoBatchService.insertProcesoBatchActu(params, usuario);
		}	
		catch (Exception e) {
			String error = this.getKeyMessage("interfaz.error.insertarProcesoBatchActu", usuario);
			throw new Exception(error);
		}
		return params;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#obtenerListaInterfaces(java.util.Map)
	 */
	public Map<String, Object> obtenerListaInterfaces(Map<String, Object> params) throws Exception  {		
		if (log.isDebugEnabled())
			log.debug("Fin 'obtenerListaInterfaces' method");
		
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		InterfazPK interfazPK = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
		Interfaz interfaz = this.interfazService.getInterfaz(interfazPK);
		
		params.put("mensajeError", "");
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA)){
			
			// Seteo las interfaces del paquete en sesion en caso la interfaz sea de paquete
			if (interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)) {
				List<Interfaz> listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
				log.info("Interfaces empaquetadas =" + listPaquete);
				if (listPaquete == null) {
					// Interfaces Deshabilitadas
					String keyMessage = "interfazSiCC.error.interfaz.desabilitada";
					String mensajeError = this.getKeyMessage(keyMessage);
					params.put("mensajeError", mensajeError);
					return params;
				} else {
					//Obtiene los archivos asociados a cada interfaz del paquete
					String[] listaInterfaces = new String[listPaquete.size()];
					String[] listaInterfacesSeleccionadas = new String[listPaquete.size()];
					
					for (int i = 0; i < listPaquete.size(); i++) {
						Interfaz interf = (Interfaz)listPaquete.get(i);
						interf.setArchivos(this.getListArchivos(interf));
						listaInterfaces[i] = interf.getCodigo();
						listaInterfacesSeleccionadas[i] = interf.getCodigo();
					}
					params.put("listaInterfaces", listaInterfaces);
					params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
					params.put("listaInterfazArchivos", listPaquete);
				}
			}
			else{
				/* INTERFACE UNITARIA */
				List<Interfaz> listPaquete = interfazService.getComponentesInterfazUnitaria(interfazPK);
				String[] listaInterfaces = new String[1];
				String[] listaInterfacesSeleccionadas = new String[1];
				interfaz.setArchivos(this.getListArchivos(interfaz));
				listaInterfaces[0] = interfaz.getCodigo();
				listaInterfacesSeleccionadas[0] = interfaz.getCodigo();
				listPaquete.set(0, interfaz);
				params.put("listaInterfaces", listaInterfaces);
				params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
				params.put("listaInterfazArchivos", listPaquete);		
			}
		}
		else{
			if (interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)) {
				List<Interfaz> listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
				log.info("Interfaces empaquetadas =" + listPaquete);
				if (listPaquete == null) {
					// Interfaces Deshabilitadas
					String keyMessage = "interfazSiCC.error.interfaz.desabilitada";
					String mensajeError = this.getKeyMessage(keyMessage);
					params.put("mensajeError", mensajeError);
					return params;
				} else {
					
					String[] listaInterfaces = new String[listPaquete.size()];
					String[] listaInterfacesSeleccionadas = new String[listPaquete.size()];
					for (int i = 0; i < listPaquete.size(); i++) {
						Interfaz interf = (Interfaz)listPaquete.get(i);
						listaInterfaces[i] = interf.getCodigo();
						listaInterfacesSeleccionadas[i] = interf.getCodigo();
					}
					params.put("listaInterfaces", listaInterfaces);
					params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
					params.put("listaInterfazArchivos", listPaquete);
					
				}	
			}
			else {
				List<Interfaz> listPaquete = new ArrayList<Interfaz>();
				listPaquete.add(interfaz);
				String[] listaInterfaces = new String[1];
				String[] listaInterfacesSeleccionadas = new String[1];
				listaInterfaces[0] = interfaz.getCodigo();
				listaInterfacesSeleccionadas[0] = interfaz.getCodigo();
				params.put("listaInterfaces", listaInterfaces);
				params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
				params.put("listaInterfazArchivos", listPaquete);	
				
			}
		}
		
		if (log.isDebugEnabled())
			log.debug("Fin 'obtenerListaInterfaces' method");
		return params;
	}	
	
	
	/* 
	 * valida lo archvios que van a procesar
	 * retorna -1 si archivos incompletos
	 *          0 exito
	 *          1 numero de archvio de lote o lotes no son correctos 
	 *          -2 archvio de control obligatorio
	 *          -3 Número de Archivos Incompletos o el número de Lote de los archivos no son iguales.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazHiloAbstractService#verificaNumeroLote(java.util.Map)
	 */
	public String verificaNumeroLote(Map<String, Object> params) throws Exception {
		String valida = Constants.NUMERO_CERO;;
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		InterfazPK interfazPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		
		/* INI NUEVO MULTILOTE */
		int numeroMultiLote = 1;
		//int numeroMultiLoteActual = numeroMultiLote;
		params.put("numeroMultiLote", numeroMultiLote);
		List listaMultiLoteCompuesto = new ArrayList();
		List listaMultiLoteCompuestoSize = new ArrayList();
		params.put("listaMultiLoteCompuesto", listaMultiLoteCompuesto);
		params.put("listaMultiLoteCompuestoSize", listaMultiLoteCompuestoSize); //contiene el numero de registros del archivo , su corrsondiente
		//se encuentra en la lista anterior
		/* FIN NUEVO MULTILOTE */
		
		params.put("listaNombresArchivos", null);
		
		if (StringUtils.isBlank(codigoSistema) || StringUtils.isBlank(codigoInterfaz))
			return valida;
		
		/* Validando la existencia de Archivos */
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA)) {
			List listPaquete = new ArrayList();
	
			if (interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE))
			    listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
			else
				listPaquete = interfazService.getComponentesInterfazUnitaria(interfazPK);
			
			List archivos = null;
			for (int i = 0; i < listPaquete.size(); i++) {
				Interfaz inter = (Interfaz)listPaquete.get(i);			
				archivos = this.getListArchivos(inter);
				if (archivos == null || archivos.size() < 0 ) {
					valida = "-1";
					return valida;
				}
				LabelArchivos labelArchivo = (LabelArchivos) archivos.get(0);
				if (labelArchivo.isError()) {
					valida = "-1";
					return valida;
				}
			}
	
		}
		
		/* Validaciones de Lote */
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA) && 
		   interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)){
		   valida = this.validaEntradaPaquete(params);
		}
		
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA) && 
				   interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_COMPUESTA)){
					
			// POR IMPLEMENTAR INTERFAZ COMPUESTA NSSICC
			//valida = validaEntradaPaqueteCompuesta(params,interfazPK,	numeroMultiLote, listaMultiLoteCompuesto,listaMultiLoteCompuestoSize); 
		}
		
		return valida;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#validaEntradaPaquete(java.util.Map)
	 */
	public String validaEntradaPaquete(Map<String, Object> params) throws Exception {
		String valida=Constants.NUMERO_CERO;
		
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		InterfazPK interfazPK = (InterfazPK) params.get("interfazEjecucionPK");
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		
		if(interfaz.getIndicadorValidarLoteEntrada().equals(Constants.SI)){
			
			/* INI NUEVO MULTILOTE */
			int numeroMultiLote = 1;
			int numeroMultiLoteActual = numeroMultiLote;
			params.put("numeroMultiLote", numeroMultiLote);
			List listaMultiLote = new ArrayList();
			params.put("listaMultiLote", listaMultiLote);
			/* FIN NUEVO MULTILOTE */
			
			params.put("listaNombresArchivos", null);
			if (StringUtils.isBlank(codigoSistema) || StringUtils.isBlank(codigoInterfaz))
				return valida;
			
			if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA) && 
			   interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)){
				
				// Si son de Tipo Paquete y de Entrada se debe validar que los archivos tengan el mismo numero de Lote

				//Obteniendo la lista de interfaces del paquete
				List listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
				boolean isVacio=true;
				if (listPaquete != null) {
					String[] listaInterfaces = new String[listPaquete.size()];
					String[] listaInterfacesPrefijo = new String[listPaquete.size()];
					String[] listaNombreArchivo = new String[listPaquete.size()];
					String[] listaNombresArchivos = new String[listPaquete.size()];
					long[] listaArchivosSize = new long[listPaquete.size()];
					List archivos = null;
					
					/* INI NUEVO MULTILOTE */
					String indicadorMultiLote = (String)params.get("indicadorMultiLote");
					if (Constants.SI.equals(indicadorMultiLote)) {
						Interfaz interInicial = (Interfaz)listPaquete.get(0);
						archivos = this.getListArchivos(interInicial); 
						numeroMultiLote = archivos.size();
						numeroMultiLoteActual = numeroMultiLote;
						params.put("numeroMultiLote", numeroMultiLote); 
					}	
					
					for (int x=0; x < numeroMultiLote; x++) {
						listaNombresArchivos = new String[listPaquete.size()];
						listaArchivosSize = new long[listPaquete.size()];
					/* FIN NUEVO MULTILOTE */
						
						for (int i = 0; i < listPaquete.size(); i++) {
							Interfaz inter = (Interfaz)listPaquete.get(i);
							
							listaInterfaces[i] = inter.getCodigo();					
							if (inter.getNombreArchivoEntradaSalida() != null){
								listaInterfacesPrefijo[i] = inter.getNombreArchivoEntradaSalida();
							}
							else{
								listaInterfacesPrefijo[i] = inter.getCodigo() + '_';
							}					
							archivos = this.getListArchivos(inter);
							
							/* INI NUEVO MULTILOTE */
							//LabelArchivos labelArchivos = (LabelArchivos)archivos.get(0);
							numeroMultiLoteActual = archivos.size();
							if (Constants.SI.equals(indicadorMultiLote)) {
								if (numeroMultiLoteActual != numeroMultiLote) {
									valida = "-1";
									break;
								}
							}	
							LabelArchivos labelArchivos = (LabelArchivos)archivos.get(x);
							/* FIN NUEVO MULTILOTE */
							
							if (labelArchivos.getPesoArchivo() != null ){
								listaNombreArchivo[i] = labelArchivos.getNombreArchivo().substring(listaInterfacesPrefijo[i].length(), labelArchivos.getNombreArchivo().length()-4);
								listaNombresArchivos[i] = labelArchivos.getNombreArchivo();
								listaArchivosSize[i] = labelArchivos.getNumeroRegistro();
								isVacio=false;
							}
							else{
								isVacio= isVacio && true;
								//cuando el numero de archvios es vacio x unitarias debe retornar true						
								valida = "-1";//para no validar los nombres de archivo
								//break;
							}
						}
						
						/* INI NUEVO MULTILOTE */
						if (valida.equals(Constants.NUMERO_CERO)){
							String loteAnterior = "";
							//Se compara los numeros de lotes de los archivos de las interfaces
							for (int i = 0; i < listaNombreArchivo.length; i++) {
								if (i!= 0 ){
									if (!listaNombreArchivo[i].equals(loteAnterior)){
										valida = "1";
										break;	
									}	
								}
								loteAnterior = listaNombreArchivo[i];
							}
						}
						if (x==0) {
							params.put("listaNombresArchivos", listaNombresArchivos);
						}
						listaMultiLote.add(listaNombresArchivos);
						params.put("listaMultiLote", listaMultiLote);
						//listaMultiLoteSize.add(listaArchivosSize);
						//params.put("listaMultiLoteSize", listaMultiLoteSize);
						/* FIN NUEVO MULTILOTE */
						
					}
	
				}
				if(isVacio) {
					  valida = "-2";	
				}
			}
		}
		
		return valida;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#executeInterfaz(java.util.Map)
	 */
	public InterfazExecutionResult executeInterfaz(Map<String, Object> params) throws Exception {
		
		if (log.isDebugEnabled())
			log.debug("Entering 'executeInterfaz' method");
			
		/* Obteniendo interfaz */
		Interfaz interfazEjecucion = (Interfaz)params.get("interfazEjecucion");
		String tipoInterfazExecution = (String)params.get("tipoInterfazExecution");
		Usuario usuario = (Usuario) params.get("usuario");
		
		/* Obteniendo Service de Implementacion */
		BaseInterfazService interfazImpl = null;
		try {
			interfazImpl = this.getInterfazImplementation(interfazEjecucion.getCodigo());	
			if (interfazImpl == null) {
				if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_PAQUETE)) {
					interfazImpl = this.getInterfazImplementation(this.CODIGO_PAQUETE_ESTANDAR);	
				}
				else {
					throw new Exception("Debe declarar el Service en el archivo applicationContext-service-sisicc-framework.xml");
				}
			}
		}
		catch (Exception e) {
			if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_PAQUETE)) {
				interfazImpl = this.getInterfazImplementation(this.CODIGO_PAQUETE_ESTANDAR);	
			}
			else {
				throw new Exception("Debe declarar el Service en el archivo applicationContext-service-sisicc-framework.xml");
			}
		}
		
		/* EJECUCION DE LA INTERFAZ */
		InterfazExecutionResult result = new InterfazExecutionResult();
		
		if (interfazImpl != null) {			
			InterfazParams interfazParams = new InterfazParams();
			interfazParams.setInterfaz(interfazEjecucion);
			interfazParams.setQueryParams(params);
			interfazParams.updateInterfazQueryParams();
			
			/* Invocando a la ejecucion de la interfaz */
			if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_UNITARIA)) {
				InterfazResult interfazResult = interfazImpl.executeInterfaz(interfazParams);
				result.getInterfazResultsInterfaz().add(interfazResult);
			}
			else if (tipoInterfazExecution.equals(Constants.TIPO_GENERACION_PAQUETE)) {
				interfazParams.setQueryParams(params);
				result = interfazImpl.executePaquete(interfazParams);
			}
			params.put("interfazParams", interfazParams);
		}
		else {
			String mensajeError = this.getKeyMessage("procesoBatch.error.registrarService") + " : " + interfazEjecucion.getCodigo();
			params.put("interfazParams", null);
			throw new Exception(mensajeError);
		}
		
		log.info("Resultado de la ejecucion, result=" + result);
		if (log.isDebugEnabled())
			log.debug("Fin 'executeInterfaz' method");
		return result;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#verificarProcesoBatchEnEjecucion(java.util.Map)
	 */
	public String verificarProcesoBatchEnEjecucion(Map<String, Object> params) {
		String codigpProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigpProcesoBatch)) return null;
		
		/* Verificando que no se encuentre en Ejecucion */
		String mensajeWarnig02 = this.getKeyMessage("procesoBatch.error.procesoEnEjecucion");
		try {
			List listaProcesoBatch = procesoBatchService.getProcesoBatchActuByCriteria(params);
			if (listaProcesoBatch.size() > 0) {
				ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
				if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
					return mensajeWarnig02;
				}
			}
		}
		catch (Exception e) {
			return this.obtieneMensajeErrorException(e);
		}
		return null;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#verificarProcesoBatchEnEjecucionDependientes(java.util.Map)
	 */
	public String verificarProcesoBatchEnEjecucionDependientes(Map<String, Object> params) {
		String codigpProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigpProcesoBatch)) return null;
		
		try {
			List listaProcesoBatchDependientes = procesoBatchService.getProcesoBatchActuDependientesByCriteria(params);
			if (listaProcesoBatchDependientes.size() > 0) {
				
				String mensaje = this.getKeyMessage("procesoBatch.error.procesoDependienteEnEjecucion");
				for (int i = 0; i < listaProcesoBatchDependientes.size(); i++) {
					ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatchDependientes.get(i);
					mensaje  +=  "\n" + procesoBatchActu.getCodigoSistema() + "-" + procesoBatchActu.getProcesoBatch().getCodigoProcesoBatch();
					mensaje  +=  " " + procesoBatchActu.getProcesoBatch().getDescripcionProcesoBatch();
					mensaje += "; ";
				}
				mensaje +=  "\n" + this.getKeyMessage("procesoBatch.error.espereProcesoDependienteEnEjecucion");
				return mensaje;			
			}
		}
		catch (Exception e) {
			return this.obtieneMensajeErrorException(e);
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#getListArchivos(biz.belcorp.ssicc.dao.sisicc.model.Interfaz)
	 */
	public List getListArchivos(Interfaz interfaz) throws Exception {
		String mensaje = this.getKeyMessage("mensaje.error.noExisteArchivo");
		
		List archivosList = new ArrayList();			
		InterfazParams interfazParams = new InterfazParams();
		interfazParams.setInterfaz(interfaz);
		archivosList = interfazParams.getListArchivosEntrada();		
		if(archivosList.size() == 0){
			LabelArchivos labelArchivos = new LabelArchivos();
			labelArchivos.setNombreArchivo(mensaje);
			labelArchivos.setError(true);
			archivosList.add(labelArchivos);
			
		}	
		else {
			if (interfaz.getFlagValidarCargaPrevia().equals(Constants.SI)){
				Map criteria = new HashMap();
				
	          	criteria.put("codigoPais",interfaz.getCodigoPais());  
	          	criteria.put("codigoSistema",interfaz.getCodigoSistema());
				criteria.put("codigoInterfaz",interfaz.getCodigo());
				criteria.put("ejecucionSatisfactoria",Constants.SI);
				
				for (int i = 0; i < archivosList.size(); i++) {
					LabelArchivos labelArchivos = (LabelArchivos)archivosList.get(i);
					criteria.put("nombreArchivoOriginal",labelArchivos.getNombreArchivo());
					int size = this.historicoService.getHistoricosByCriteria(criteria).size();
					labelArchivos.setObservacion("");
					
					
					if (size>0)	{
						String mensajeArchivosCargadosAntes = this.getKeyMessage("mensaje.error.archivoCargadoAntes");
						labelArchivos.setObservacion(mensajeArchivosCargadosAntes);
						labelArchivos.setError(true);
					}
				}
			}
			
		}
		return archivosList;
	}	   	
	
  /* (non-Javadoc)
 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#updateInterfazRegistroProcesoBatch(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams, java.lang.Exception)
 */
  @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
  public void updateInterfazRegistroProcesoBatch(Map<String, Object> params, Exception exception) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR);
		
		log.error(exception);
		String descripcionLog = exception.getMessage();
		
		if (descripcionLog!= null && descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		
		params.put("descripcionLog", descripcionLog);
		this.procesoBatchService.updateProcesoBatchActu02(params, usuario);
		this.procesoBatchService.executeProcesoBatchActuRecomendacionError(params);
	}
  
    /**
	 * Metodo que es invocado para finalizar la ejecucion del proceso BATCH
	 * @param params
	 * @param request
	 * @param interfazExecutionResult
	 * @param usuario
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
   public void updateInterfazRegistroProcesoBatch(Map<String, Object> params) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		String codigoProcesoBatch = (String) params.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		Usuario usuario = (Usuario) params.get("usuario");
		String numeroLote = "";
		params.put("numeroLote", numeroLote);
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("usuario", usuario);
		
		boolean ejecucionCompletada = true;
		if (ejecucionCompletada) {
			params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		}
		else {
			params.put("descripcionLog", Constants.PROCESO_BATCH_ENVIADO_GENERICO_ERRORES);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
		}	
		this.procesoBatchService.updateProcesoBatchActu02(params, usuario);
		this.procesoBatchService.executeProcesoBatchActuRecomendacionError(params);
		
	}
	
   /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService#getInterfazImplementations()
	 */
	public Map getInterfazImplementations() {
		return interfazImplementations;
	}
   
	/* GET - SET */
	/**
	 * @return the interfazService
	 */
	public InterfazService getInterfazService() {
		return interfazService;
	}

	/**
	 * @param interfazService the interfazService to set
	 */
	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}
	
	
	/**
	 * @return the historicoService
	 */
	public HistoricoService getHistoricoService() {
		return historicoService;
	}


	/**
	 * @param historicoService the historicoService to set
	 */
	public void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}


	/**
	 * @param interfazImplementations
	 */
	public void setInterfazImplementations(Map interfazImplementations) {
		this.interfazImplementations = interfazImplementations;
	}


	/**
	 * @return the procesoBatchService
	 */
	public ProcesoBatchService getProcesoBatchService() {
		return procesoBatchService;
	}


	/**
	 * @param procesoBatchService the procesoBatchService to set
	 */
	public void setProcesoBatchService(ProcesoBatchService procesoBatchService) {
		this.procesoBatchService = procesoBatchService;
	}

	
	
	
	
	
	
}
