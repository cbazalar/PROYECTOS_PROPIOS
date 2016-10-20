package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.ProcesoBatchDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BasePaqueteInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazMultiHiloParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_BaseMultiHiloInterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * Implementacion de InterfazExecutionService que utiliza un Map con las
 * implementaciones especificas de las Interfaces SiCC inyectados mediante
 * Spring. Nuevas interfaces requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service.xml'.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * DESFASADO
 */
public class SSiCC_Desfasado_InterfazExecutionServiceImpl  implements SSiCC_Desfasado_InterfazExecutionService  {
	protected final Log log = LogFactory.getLog(getClass());
	
	private InterfazService interfazService;

	private HistoricoService historicoService;
	
	private ProcesoBatchDAO procesoBatchDAO;
	
	private MantenimientoSTOBloqueoControlDAO mantenimientoSTOBloqueoControlDAO;
	
	private BaseInterfazService baseinterfazServicePaquete;
	
	private ProcesoSTOService stoService;

	private InterfazExecutionService interfazExecutionServiceNSSiCC;
	
	
	/* INI SA PER-SiCC-2012-0840 */
	protected BaseMailService mailService;
	/* FIN SA PER-SiCC-2012-0840 */
	
	/**
	 * Map que contiene las implementaciones especificas de las Interfaces SiCC.
	 */
	private Map interfazImplementations;

	/**
	 * Ejecuta la Interfaz SiSiCC, contiene la logica para delegar la ejecucion
	 * de las interfaces unitarias y de paquete.
	 * 
	 * @param params
	 *            parametros de la interfaz
	 * @return List de InterfazResult con los resultados de la ejecucion.
	 */
	public SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(Map params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'executeInterfaz' method");		
		/* INI NUEVO MULTILOTE */
		boolean ejecutarSto=false; //validar si por lo menos 1 lote se cargo correctamente, este debe invocar a las validaciones.
		/* INI CAMBIO CB */
		List listaMultiLote = new ArrayList();		
		int numeroMultiLote = 1;
		String indicadorMultiLote = Constants.NO;
		String numeroEjecucionEnviosMultiLote ;
		try {
			listaMultiLote = (List) params.get("listaMultiLote");
		}
		catch(Exception e) {
			listaMultiLote = new ArrayList();
		}
		try {
			numeroMultiLote = (Integer)params.get("numeroMultiLote");
		}
		catch(Exception e) {
			numeroMultiLote = 1;
		}
		try {
			indicadorMultiLote = (String)params.get("indicadorMultiLote"); 
		}
		catch(Exception e) {
			indicadorMultiLote = Constants.NO;
		}
		try {
			numeroEjecucionEnviosMultiLote = (String) params.get("numeroEjecucionEnvios"); 
		}
		catch(Exception e) {
			numeroEjecucionEnviosMultiLote = null;
		}
		/* FIN CAMBIO CB */
		
		/*ini valid control*/
		List listaMultiLoteSize = (List) params.get("listaMultiLoteSize");
		/*fin valid control*/
		
		SSiCC_Desfasado_InterfazExecutionResult result = new SSiCC_Desfasado_InterfazExecutionResult();
		String codigoPaisMultiLote = (String) params.get("codigoPais");
		String codigoSistemaMultiLote = (String) params.get("codigoSistema");
		String codigoInterfazMultiLote = (String) params.get("codigoInterfaz");
		
		params = this.interfazExecutionServiceNSSiCC.executeInterfazValidacionesPreviasBase(params);
		params.put("ES_SSICC_DESFASADO", Constants.SI);
		
		//ini valid control
		String codigoInterfazArchivoControl = (String)params.get("codigoInterfazArchivoControl");
		//fin valid control
		
		log.debug("numeroMultiLote "+numeroMultiLote + " codigoInterfaz "+codigoInterfazMultiLote);
		for (int x=0; x < numeroMultiLote; x++) {
		/* FIN NUEVO MULTILOTE */
			
			// Obtengo el usuario de los parametros y lo remuevo del map, este
			// usuario sera pasado como atributo en InterfazParams
			Usuario usuario = (Usuario) params.get("usuario");
	
			// Obtengo la Interfaz a ejecutar partir de los parametros del Map
			/* INI NUEVO MULTILOTE */
			String codigoPais = codigoPaisMultiLote;
			String codigoSistema = codigoSistemaMultiLote;
			String codigoInterfaz = codigoInterfazMultiLote;
			String numeroEjecucionEnvios = numeroEjecucionEnviosMultiLote; 
			/* FIN NUEVO MULTILOTE */
			
			log.debug("numeroEjecucionEnvios " + numeroEjecucionEnvios);			
			InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,	codigoSistema, codigoInterfaz);
			String[] listaInterfaces = (String[])params.get("listaInterfaces");
			String[] listaInterfacesSeleccionadas = (String[])params.get("listaInterfacesSeleccionadas");
			interfazEjecucionPK.setListaInterfaces(listaInterfaces);
			interfazEjecucionPK.setListaInterfacesSeleccionadas(listaInterfacesSeleccionadas);
			
			Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
	
			//ini valid control x lote
			/*inicio multilote unitarias inicio*/
			String[] listaNombresArchivos;
			if(listaMultiLote == null || listaMultiLote.size()==0)
				listaNombresArchivos = (String[])params.get("listaNombresArchivos");
			else	
			   listaNombresArchivos = (String[])listaMultiLote.get(x);
			
			params.put("numLoteArchivo","");
			if(codigoInterfazArchivoControl != null && listaNombresArchivos!=null && listaNombresArchivos.length>0 && listaNombresArchivos[0]!= null){
				int index= listaNombresArchivos[0].indexOf(".");
				String numLoteArchivo ="";
				if(index != -1){//hay archivo
				  String [] split =  listaNombresArchivos[0].split("_");	
				  //ini lote archivo control
				  if(codigoInterfazArchivoControl.equals(interfazEjecucion.getCodigo())){
					 //OCR-1_PPCCAAAAMMDDNNNN
					 //PPCCAAAAMMDDNNNN 
					  //numLoteArchivo = split[1].substring(0, index-split[0].length()-1);
					  //AAAAMMDDNNNN
					  //numLoteArchivo = numLoteArchivo.substring(4);
					  numLoteArchivo = split[1].substring(0, index-split[0].length()-1);
				  }
				  else{
					  numLoteArchivo = split[1].substring(0, index-split[0].length()-1);
				  }
				  //fin lote archivo control
				}
				params.put("numLoteArchivo",numLoteArchivo);
			}																							
			/*fin multilote unitarias inicio*/
	
			/*validamos las interfaces en tabla de comntrol si hay archivo de control inicio*/
			String codigoErrorControl="";
			if(codigoInterfazArchivoControl != null && !codigoInterfazArchivoControl.equals(interfazEjecucion.getCodigo())){
			 //validamos con la tabla temporal y numero de registros que viene x interfaz 
			  long [] listaArchivosSize = (long[])listaMultiLoteSize.get(x);	
			  codigoErrorControl= validarInterfazByControl(params,listaNombresArchivos,listaArchivosSize);
			  log.debug("codigoErrorControl "+codigoErrorControl);
			  //if(codigoErrorControl.equals(Constants.ARCHIVO_NO_EXISTE_EN_CONTROL)) continue;	
			}					
			/*fin de validacion con el archivo de control*/
	
			Historico historicoEjecucion = initializeHistorico(interfazEjecucion,params);
	
			// Se sincroniza este bloque para evitar que las ejecuciones obtengan el
			// mismo numero de lote
			synchronized (this) {
				// Se incluyo esta modificacion para permitir obtener el numero de
				// lote desde la capa Action, en caso que no se haya seteado se
				// obtiene de la manera normal mediante el InterfazService
				String numeroLote = (String) params.get("numeroLote");
				if (StringUtils.isEmpty(numeroLote) || Constants.SI.equals(indicadorMultiLote)) { /* NUEVO MULTILOTE */
					numeroLote = interfazService.getNumeroLote(interfazEjecucionPK);
					params.put("numeroLote", numeroLote);
				}
				historicoEjecucion.setNumeroLote(numeroLote);
				
				/******************/
				 //Se agrega el nombre del archivo que se va a ejecutar para insertalo en la tabla de BAS_HISTO_LOTES
				 //para el caso de las interfaces unitarias
				if (interfazEjecucion.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)) {
					InterfazParams interfazParams2 = new InterfazParams();
					interfazParams2.setInterfaz(interfazEjecucion);
					String nombArchivoEjecutar = interfazParams2.getArchivoEntradaFileName();
					historicoEjecucion.setNombreArchivo(nombArchivoEjecutar);
				}
				/*************************/
				
				log.debug("Insertando historico=" + historicoEjecucion);
				historicoService.insertHistorico(historicoEjecucion, usuario); 
				//procesoBatchDAO.updateProcesoBatchActuLote(params, usuario);	
				
				numeroLote = historicoEjecucion.getNumeroLote();
				params.put("numeroLote", numeroLote);
				
			}
			log.info("Se obtuvo y se inserto en el historico el numeroLote="+ historicoEjecucion.getNumeroLote());
			result = new SSiCC_Desfasado_InterfazExecutionResult(); /* NUEVO MULTILOTE */
			result.setNumeroLote(historicoEjecucion.getNumeroLote());
			result.setInterfaz(interfazEjecucion);
			
			// Ejecucion de Interfaz Unitaria
			if (interfazEjecucion.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)) {
				log.info("Iniciando ejecucion de Interfaz Unitaria: "
						+ interfazEjecucion.getCodigo());
				BaseInterfazService interfazImpl = getInterfazImplementation(interfazEjecucion.getCodigo());
	
				if (interfazImpl != null) {
					InterfazParams interfazParams = new InterfazParams();
					interfazParams.setInterfaz(interfazEjecucion);
					interfazParams.setHistorico(historicoEjecucion);
					interfazParams.setUsuario(usuario);
					interfazParams.setQueryParams(params);
					interfazParams.updateInterfazQueryParams();
									
					if(StringUtils.isEmpty(codigoErrorControl)){//no hay error
     					//solo es usado en este momento para el gestor d einterfaces de micro seguros
					    int numeroEjecucion = StringUtils.isNotEmpty(numeroEjecucionEnvios)?Integer.parseInt(numeroEjecucionEnvios):-1;
						if(numeroEjecucion>0){
							for(int i=0; i<numeroEjecucion;i++){
								InterfazResult interfazResult = interfazImpl.executeInterfaz(interfazParams);
								result.getInterfazResults().add(interfazResult);
							}
						}else{
							InterfazResult interfazResult = interfazImpl.executeInterfaz(interfazParams);
							//ini validacion carga de interfaz
							if(codigoInterfazArchivoControl != null 
										&& !codigoInterfazArchivoControl.equals(interfazEjecucion.getCodigo())){
							  if(interfazResult.isCompletado()){	
								params.put("estadoPendiente",Constants.NUMERO_UNO);
								params.put("codigoInterfazEjecucion", interfazEjecucion.getCodigo());
								interfazService.updateEstadoArchivoControl(params);	
								params.remove("codigoInterfazEjecucion");
							  }else{
								//verificvar si el erro viene de sto
								  String mensaje= interfazResult.getMensaje();
								  String errorSto =Constants.OCR_CARGA_ERROR_VALIDACION;
								  int index = StringUtils.isNotEmpty(mensaje)? mensaje.indexOf(errorSto):-1;
								  if(index != -1){
									  //hay error , pero marcamos la carga
										params.put("estadoPendiente",Constants.NUMERO_UNO);
										params.put("codigoInterfazEjecucion", interfazEjecucion.getCodigo());
										interfazService.updateEstadoArchivoControl(params);	
										params.remove("codigoInterfazEjecucion");
								  }
							  }								
							}
							//fin validacion carga de interfaz
							result.getInterfazResults().add(interfazResult);
						}
					}else{
						//lo marco como error
						//ini sb cambio en error de validacion
						InterfazResult interfazResult = new InterfazResult(historicoEjecucion.getNumeroLote(),interfazEjecucion);
						if("-1".equals(codigoErrorControl)){
							interfazResult.setMensaje("numero de registros distintos al archivo de control ");
						}else{
							//o archivo no se encuentra en la red. revisar por favor si se cargo el archivo de control
							interfazResult.setMensaje("Archivo no existe en el FTP ");
						}
						//fin sb cambio en error de validacion
						//HISTORICO
						historicoEjecucion.setFlagError(Constants.SI);							
						historicoEjecucion.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
						historicoEjecucion.setDescripcionError(interfazResult.getMensaje());
						historicoEjecucion.setFechaFinProceso(new Timestamp(System
								.currentTimeMillis()));
						historicoEjecucion.setHistoricoFileName(interfazParams.getHistoricoFileName());
						//log.debug(historico);
						this.historicoService.updateHistorico(historicoEjecucion, interfazParams.getUsuario());
						//
						//SAVE INTERFAZ
						Long correlativo = interfazEjecucion.getNumeroEjecucion();
						if (correlativo == null)
							interfazEjecucion.setNumeroEjecucion(new Long(1));
						else
							interfazEjecucion.setNumeroEjecucion(new Long(correlativo.longValue() + 1));
						
						interfazService.updateNumeroEjecucionInterfaz(interfazEjecucion,usuario);
						//						
						result.getInterfazResults().add(interfazResult);
					}
					//fin valid control
				} else {
					log.error("No se encontro la implementacion de la Interfaz "
							+ interfazEjecucion.getCodigo());
					InterfazResult interfazResult = new InterfazResult(
							historicoEjecucion.getNumeroLote(), interfazEjecucion);
					result.getInterfazResults().add(interfazResult);
				}
			}
	
			// Ejecucion de Interfaz Paquete
			else if (interfazEjecucion.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)) {
				log.info("Iniciando ejecucion de Interfaz Paquete: "
						+ interfazEjecucion.getCodigo());
				
				/* INI SA PER-SiCC-2012-1120 */
				String indicadorSeleccionInterfaz = (String)params.get(Constants.INDICADOR_SELECCION_INTERFAZ);
				if(indicadorSeleccionInterfaz != null) {
					interfazEjecucion.setIndicadorSeleccion(indicadorSeleccionInterfaz);
				}
				/* FIN SA PER-SiCC-2012-1120 */
	
				// Obtengo un List con los codigos de las interfaces empaquetadas
				List listInterfacesEmpaquetadas =new ArrayList();
				boolean indicadorSeleccionActivo = false;
				
				if (Constants.SI.equals(interfazEjecucion.getIndicadorSeleccion())) {
					if (listaInterfaces != null && listaInterfacesSeleccionadas != null)
						if (listaInterfacesSeleccionadas.length < listaInterfaces.length)
							indicadorSeleccionActivo = true;
						if (listaInterfacesSeleccionadas.length == 0) 
							indicadorSeleccionActivo = false;
				}
				
				if (indicadorSeleccionActivo)
					listInterfacesEmpaquetadas = interfazService.getComponentesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
				else
					listInterfacesEmpaquetadas = interfazService.getComponentesInterfazPaquete(interfazEjecucionPK);			
				log.info("Se obtuvieron las interfaces unitarias empaquetadas: "+ listInterfacesEmpaquetadas);

				/*INI PAQUETE ZIPEADO */	
				if(interfazEjecucion.comprimir()) {
					String directorioTemporalPaquete = FileUtil.formatDirectory(interfazEjecucion.getDirectorioTemporal())
												+ interfazEjecucion.getCodigo() + "_" + historicoEjecucion.getNumeroLote();
					
					interfazEjecucion.setDirectorioTemporalPaquete(directorioTemporalPaquete);
					File fileDirectorio = new File(directorioTemporalPaquete);
					boolean creacionDirectorio = fileDirectorio.mkdir();
					if(!creacionDirectorio) {
						log.error("No se pudo Crear el Directiorio temporal para el paquete: " + directorioTemporalPaquete);
					}
				}
				/*FIN PAQUETE ZIPEADO */
				
				
				String nombreArchivo = "";
				int ind=0;
				//si hay error en control basta con entrar ejecutar en secuencia debido a que no se eejcutara la interfaz
				// solo se marcara el error de numero de registros no valido
				if (Constants.NO.equals(interfazEjecucion.getEsPaqueteMultiHilo()) || StringUtils.isNotEmpty(codigoErrorControl)) {
				//fin valid control	
					// Itero sobre las interfaces empaquetadas y las ejecuto
					Iterator iterator = listInterfacesEmpaquetadas.iterator();
					while (iterator.hasNext()) {
						Interfaz interfazEmpaquetada = (Interfaz) iterator.next();
						log.info("Iniciando ejecucion de Interfaz Unitaria empaquetada: "
										+ interfazEmpaquetada.getCodigo()
										+ " "
										+ interfazEmpaquetada.getDescripcion());
		
						BaseInterfazService interfazEmpaquetadaImpl = getInterfazImplementation(interfazEmpaquetada.getCodigo());
		
						//Obteniendo el nombre del archivo que se esta proecesando para insertarlo en la tabla de BAS_HISTO_LOTES
						nombreArchivo = (listaNombresArchivos!=null && listaNombresArchivos.length>0)?listaNombresArchivos[ind]:"";
						ind++;
						// Creo el historico para la interfaz empaquetada
						Historico historicoEmpaquetada = initializeHistorico(interfazEmpaquetada, params);
						historicoEmpaquetada.setNumeroLote(historicoEjecucion.getNumeroLote());
						historicoEmpaquetada.setCodigoInterfaz(interfazEmpaquetada.getCodigo());
						historicoEmpaquetada.setCodigoInterfazEmpaquetada(codigoInterfaz);
						historicoEmpaquetada.setDescripcionLote(interfazEmpaquetada.getDescripcion());
						historicoEmpaquetada.setOrdenHilo(new Long(0));
						
						//Se guarda el nombre del archivo
						historicoEmpaquetada.setNombreArchivo(nombreArchivo);
						historicoService.insertHistorico(historicoEmpaquetada, usuario);
						String numeroLote = historicoEjecucion.getNumeroLote();
						params.put("numeroLote", numeroLote);
						
						/*INI PAQUETE ZIPEADO */
						if(interfazEjecucion.comprimir()) {
							interfazEmpaquetada.setDirectorioTemporalPaquete(interfazEjecucion.getDirectorioTemporalPaquete());
						}
						/*FIN PAQUETE ZIPEADO */
						
						// Creo los parametros para la interfaz empaquetada
						InterfazParams interfazParams = new InterfazParams();
						interfazParams.setInterfaz(interfazEmpaquetada);
						interfazParams.setHistorico(historicoEmpaquetada);
						interfazParams.setUsuario(usuario);
						interfazParams.setQueryParams(params);
						interfazParams.updateInterfazQueryParams();
						
						//ini valid control
						if(StringUtils.isEmpty(codigoErrorControl)){//no hay error
							//solo es usado en este momento para el gestor de interfaces de micro seguros
							int numeroEjecucion = StringUtils.isNotEmpty(numeroEjecucionEnvios)?Integer.parseInt(numeroEjecucionEnvios):-1;
							if(numeroEjecucion > 0){
								for(int i=0; i<numeroEjecucion;i++){
									InterfazResult interfazResult = interfazEmpaquetadaImpl.executeInterfaz(interfazParams);
									result.getInterfazResults().add(interfazResult);
								}
							}else{
								InterfazResult interfazResult = interfazEmpaquetadaImpl.executeInterfaz(interfazParams);
													
								//ini validacion carga de interfaz
								if(codigoInterfazArchivoControl != null 
											&& !codigoInterfazArchivoControl.equals(interfazEmpaquetada.getCodigo())){
								  if(interfazResult.isCompletado()){	
									params.put("estadoPendiente",Constants.NUMERO_UNO);
									params.put("codigoInterfazEjecucion", interfazEmpaquetada.getCodigo());
									interfazService.updateEstadoArchivoControl(params);	
									params.remove("codigoInterfazEjecucion");
								  }else{
									//verificvar si el erro viene de sto
									  String mensaje= interfazResult.getMensaje();
									  String errorSto =Constants.OCR_CARGA_ERROR_VALIDACION;
									  int index = StringUtils.isNotEmpty(mensaje)? mensaje.indexOf(errorSto):-1;
									  if(index != -1){
										  //hay error , pero marcamos la carga
											params.put("estadoPendiente",Constants.NUMERO_UNO);
											params.put("codigoInterfazEjecucion", interfazEmpaquetada.getCodigo());
											interfazService.updateEstadoArchivoControl(params);	
											params.remove("codigoInterfazEjecucion");
									  }
								  }								
								}
								//fin validacion carga de interfaz
								
								
								result.getInterfazResults().add(interfazResult);
							}
						}else{
							//lo marco como error
							InterfazResult interfazResult = new InterfazResult(historicoEmpaquetada.getNumeroLote(),interfazEmpaquetada);
							//interfazResult.setCompletado(false);							
							//interfazResult.setMensaje("numero de registros distintos al archivo de control o archivo no se encuntra en la red. revisar por favor si se cargo el archivo de control");							
							interfazResult.setCompletado(false);
							if("-1".equals(codigoErrorControl)){
								interfazResult.setMensaje("numero de registros distintos al archivo de control ");
							}else{
								//o archivo no se encuentra en la red. revisar por favor si se cargo el archivo de control
								interfazResult.setMensaje("Archivo no existe en el FTP ");
							}
							//HISTORICO
							historicoEmpaquetada.setFlagError(Constants.SI);							
							historicoEmpaquetada.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
							historicoEmpaquetada.setDescripcionError(interfazResult.getMensaje());
							historicoEmpaquetada.setFechaFinProceso(new Timestamp(System
									.currentTimeMillis()));
							historicoEmpaquetada.setHistoricoFileName(interfazParams.getHistoricoFileName());
							//log.debug(historico);
							this.historicoService.updateHistorico(historicoEmpaquetada, interfazParams.getUsuario());
							//
							//SAVE INTERFAZ
							Long correlativo = interfazEmpaquetada.getNumeroEjecucion();
							if (correlativo == null)
								interfazEmpaquetada.setNumeroEjecucion(new Long(1));
							else
								interfazEmpaquetada.setNumeroEjecucion(new Long(correlativo.longValue() + 1));
							
							interfazService.updateNumeroEjecucionInterfaz(interfazEmpaquetada,usuario);
							//
							result.getInterfazResults().add(interfazResult);
							
						}
						//fin valid control
					}
				}	
				else {
					/*SB inicio niveles del hilo*/
					/* COLOCAR TEMA PARA INTERFAZ MULTIHILO */										
					//Integer nroHilosEjecutar = new Integer(1);
					ExecutorService poolMultiHilo = null;
					Integer nroNivelesEjecutar = new Integer(1);
					Integer nroHilosPoolConexion = new Integer(5);
					long timeWait = 1000;//POR DEFAULT 1Sg
					/*if (indicadorSeleccionActivo)
						nroHilosEjecutar = interfazService.getNroHilosInterfazPaqueteSeleccionadas(interfazEjecucionPK);
					else
						nroHilosEjecutar = interfazService.getNroHilosInterfazPaquete(interfazEjecucionPK);*/
					
					if (indicadorSeleccionActivo)
						nroNivelesEjecutar = interfazService.getNroNivelesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
					else
						nroNivelesEjecutar = interfazService.getNroNivelesInterfazPaquete(interfazEjecucionPK);
					
					List<Future<SSiCC_Desfasado_InterfazExecutionResult>> resultHilo = new ArrayList<Future<SSiCC_Desfasado_InterfazExecutionResult>>();//Future[nroHilosEjecutar] ;
					
					/*obteniendo maximo hilos*/
					Map criteriaParamPais = new HashMap();
					criteriaParamPais.put("codigoPais", codigoPais);
					criteriaParamPais.put("codigoSistema", "GEN");
					criteriaParamPais.put("nombreParametro", Constants.IND_MAX_HILO_INTERFAZ);
					String nroHilos = mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteriaParamPais);
					
					if (StringUtils.isNotBlank(nroHilos))
						nroHilosPoolConexion = new Integer(nroHilos);
					
					/*obteniendo tiempo de espera x nivel */
					criteriaParamPais = new HashMap();
					criteriaParamPais.put("codigoPais", codigoPais);
					criteriaParamPais.put("codigoSistema", "GEN");
					criteriaParamPais.put("nombreParametro", Constants.TIME_WAIT_HILO_NIVEL);
					String strTimeWait = mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteriaParamPais);
					
					if (StringUtils.isNotBlank(strTimeWait))
						timeWait = new Long(strTimeWait);
					
					/*fin de obtner el timewait*/
					
					int posIni=0;
					for(int n=0;n<nroNivelesEjecutar;n++){
						log.debug("inicio nivel " + n);
						poolMultiHilo = Executors.newFixedThreadPool(nroHilosPoolConexion);						
						List listInterfacesEmpaquetadasNivel = getListEmpaquetadaNivel(listInterfacesEmpaquetadas,posIni);
						posIni+=listInterfacesEmpaquetadasNivel.size();
						log.debug("lista nivel " + listInterfacesEmpaquetadasNivel.size() + "  inicio de ste nivel en : "+posIni);
						//se encarga de forma el numero de hilos y por cada hilo las interfaces a ejecutar						
						executeNivelMultHilo(params, usuario, codigoInterfaz,
							numeroEjecucionEnvios, interfazEjecucion,
							historicoEjecucion, listInterfacesEmpaquetadasNivel,
							listaNombresArchivos, poolMultiHilo, resultHilo);
						
						// This will make the executor accept no new threads
						// and finish all existing threads in the queue
						poolMultiHilo.shutdown();
						
						/* VERIFICANDO LA FINALIZACION DE EJECUCION DE LOS HILOS */
						while (!poolMultiHilo.isTerminated()) {
							//Thread.sleep(timeWait);
							poolMultiHilo.awaitTermination(timeWait, TimeUnit.MILLISECONDS);	
						}
						
						log.debug("fin nivel " + n);	
					}	
					
					// COLOCANDO EL RESULTADO DE TODOS LOS HILOS EN EL RESULT PRINCIPAL result
					try {
						for(Future<SSiCC_Desfasado_InterfazExecutionResult> res :resultHilo) {
							SSiCC_Desfasado_InterfazExecutionResult resultadoHilo = res.get();//(InterfazExecutionResult)resultHilo.get(i);
							Iterator iteratorResult = resultadoHilo.getInterfazResults().iterator();
							while (iteratorResult.hasNext()) {
								  InterfazResult interfazResult = (InterfazResult) iteratorResult.next();
								  result.getInterfazResults().add(interfazResult);
							}
						}
					}
					catch(Exception e) {
						log.debug(e.getCause());
					}
				}
				//SB FIN NIVEL HILO
				// REGRESO AL HILO PADRE DE LA INTERFAZ
				historicoEjecucion.setFechaFinProceso(new Timestamp(System.currentTimeMillis()));
				
				/*INI PAQUETE ZIPEADO */
				if(interfazEjecucion.comprimir()) {
					// Creo el historico para la interfaz empaquetada
					Historico historicoEmpaquetada = initializeHistorico(interfazEjecucion, params);
					historicoEmpaquetada.setNumeroLote(historicoEjecucion.getNumeroLote());
					historicoEmpaquetada.setCodigoInterfaz(interfazEjecucion.getCodigo());
					historicoEmpaquetada.setCodigoInterfazEmpaquetada(codigoInterfaz);
					historicoEmpaquetada.setDescripcionLote(interfazEjecucion.getDescripcion());
					historicoEmpaquetada.setNombreArchivo(interfazEjecucion.getNombreArchivoEntradaSalida());
					Historico historicoEmpaquetadaClone = new Historico();
					historicoEmpaquetadaClone = (Historico)historicoEmpaquetada.clone();
					
					// Creo los parametros para la interfaz empaquetada
					InterfazParams interfazParams = new InterfazParams();
					interfazParams.setInterfaz(interfazEjecucion);
					interfazParams.setHistorico(historicoEmpaquetadaClone);
					interfazParams.setUsuario(usuario);
					interfazParams.setQueryParams(params);
					interfazParams.updateInterfazQueryParams();
					InterfazParams interfazParamsClone = new InterfazParams();
					interfazParamsClone = (InterfazParams)interfazParams.clone();
					interfazParamsClone.getQueryParams().putAll(params);
					
					baseinterfazServicePaquete.executeInterfaz(interfazParams);
				}		
				
				/* INI SA PER-SiCC-2012-0840 */
				if(Constants.SI.equals(interfazEjecucion.getIndicadorEnviarCorreo())) {
					// Creo el historico para la interfaz empaquetada
					Historico historicoEmpaquetada = initializeHistorico(interfazEjecucion, params);
					historicoEmpaquetada.setNumeroLote(historicoEjecucion.getNumeroLote());
					historicoEmpaquetada.setCodigoInterfaz(interfazEjecucion.getCodigo());
					historicoEmpaquetada.setCodigoInterfazEmpaquetada(codigoInterfaz);
					historicoEmpaquetada.setDescripcionLote(interfazEjecucion.getDescripcion());
					historicoEmpaquetada.setNombreArchivo(interfazEjecucion.getNombreArchivoEntradaSalida());
					Historico historicoEmpaquetadaClone = new Historico();
					historicoEmpaquetadaClone = (Historico)historicoEmpaquetada.clone();
					
					InterfazParams interfazParams = new InterfazParams();
					interfazParams.setInterfaz(interfazEjecucion);
					interfazParams.setHistorico(historicoEmpaquetadaClone);
					interfazParams.setUsuario(usuario);
					interfazParams.setQueryParams(params);
					
					this.sendMail(interfazParams);
				}
				/* FIN SA PER-SiCC-2012-0840 */
				
				/*FIN PAQUETE ZIPEADO */
				
				/* INI NUEVO MULTILOTE */
				boolean ejecucionExitosa = result.ejecucionCompletada();  
				if (!ejecucionExitosa) {
					historicoEjecucion.setFlagError(Constants.SI);
					historicoEjecucion.setDescripcionError(Constants.INTERFAZSICC_ERROR_EJECUCION_PAQUETE);
					historicoEjecucion.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO);
				}
				ejecutarSto= ejecutarSto || ejecucionExitosa;
				/* FIN NUEVO MULTILOTE */
				historicoService.updateHistorico(historicoEjecucion, usuario);
				//se debe permitir continuar con la carga del siguiente lote si el anterior dio error.
				//if (!ejecucionExitosa) break; 
				
				/* INI CAMBIOS MULTILOTE */
				result.setHistoricoEjecucion(historicoEjecucion);
				/* FIN CAMBIOS MULTILOTE */
			}
		
			
			//ini valid control
			/* se recupera el numero de lote STO siempre y cuando para la interaz este configurado tipo documento y venga numero de lote sto*/
			/* el numero de lote sto se recupera en el Basepaquete y luego de terminar un lote recuperamos el siguiene numero de lote sto */
			String tipoDocumento= (String)params.get("paramTipoDocumento");
			String numeroLoteSTO = (String)params.get("numLoteSTO");
			if(StringUtils.isNotEmpty(tipoDocumento) && StringUtils.isNotEmpty(numeroLoteSTO)){
				String numLoteSTO = stoService.updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais,tipoDocumento));
				log.info("numLoteSTO : "+ numLoteSTO);		
				params.put("numLoteSTO",numLoteSTO);
			}			
			//fin valid control
									
	    } /* NUEVO MULTILOTE */
		result.setEjecutarSTO(ejecutarSto);//aunque el result es del ultimo lote el boolean posee resultados de los anteriores lotes
		log.info("Resultado de la ejecucion, result=" + result);
		return result;
	}

	/**
	 * verifica  los archivos de la interfaz contra la tabla de control y los deja en un estado pendiente(2)
	 * al terminar la ejecucion del apquete actualiza ha enviado
	 * @param params 
	 * @param listaNombresArchivos
	 * @param listaArchivosSize
	 * @return
	 */
	private String validarInterfazByControl(Map params, String[] listaNombresArchivos,
			long[] listaArchivosSize) {
		String codigo="";
		Map criteria = new HashMap();
		Long oidControl=null;
		//List<Long> listOids= new ArrayList();
		//ojo la validacion es x lote si viniera cabecera y detalle y basta que uno falle en num lineas
		// no carga nada de ese lote
		for(int i=0;i<listaNombresArchivos.length;i++){
			oidControl=null;
			String nombreArchivoOriginal = listaNombresArchivos[i];
			String numLoteArchivo ="";
			String preNomArchivo="";
			criteria.put("codigoPais", params.get("codigoPais"));
			criteria.put("codigoSistema", params.get("codigoSistema"));			
			//si el nombre del archvio es vacio ya no es valido 
			if(StringUtils.isEmpty(nombreArchivoOriginal)) {
				//se valida si existe en control el paquete de interfaz
				return "-1";				
			}
			
			int index= nombreArchivoOriginal.indexOf(".");
			int index2 = nombreArchivoOriginal.indexOf("_");
			
			if(index != -1 && index2!=-1){//hay archivo
			  String [] split =  nombreArchivoOriginal.split("_");	
			  numLoteArchivo = split[1].substring(0, index - split[0].length()-1);
			  preNomArchivo = split[0]+"_";//ex OCR5C_									
			  criteria.put("preNomArchivo", preNomArchivo);
			  criteria.put("numLoteArchivo", numLoteArchivo);
			  //lo colocamos al params para hacer el update a cargo si queda con pendiente
			  params.put("numLoteArchivo", numLoteArchivo);
			  params.put("preNomArchivo", preNomArchivo);
			  //INICIALMENTE SE HARA UNA VALDIACION DE QUE EXISTA EN EL ARCHIVOD E CONTROL 
			  //INDEPENDIENTEMENTE DEL NUMERO D ELINEAS
			   oidControl = interfazService.getOidArchivoControl(criteria);//si existe, oidcontrol es not null
			}

			if(oidControl != null){
				//validamos numero de linea
				log.debug("lineas "+ listaArchivosSize[i]);
				criteria.put("numLineas", listaArchivosSize[i]);
				//criteria.put("idProcesoBatch", params.get("idProcesoBatch"));//siemopre es unico en una carga
				oidControl = interfazService.getOidArchivoControl(criteria);
				criteria.remove("numLineas");
				log.debug("oidcontrol encontrado  "+ oidControl);
				if(oidControl == null) return "-1";//existe pero no concuerda numero de linea
			}else{
				return "-2";
			}
			//
		}					
		/*if(StringUtils.isEmpty(codigo)){
		  //dejamos en estado pendiente el indicador de carga
		  //for(Long oid: listOids){  }			
			criteria.put("idProcesoBatch",params.get("idProcesoBatch"));
			criteria.put("codigoUsuario", params.get("codigoUsuario"));
			criteria.put("estadoPendiente",Constants.NUMERO_DOS);
			interfazService.updateEstadoArchivoControl(criteria);	
		}*/
		
		return codigo;
	}

	/**
	 * retorna una lista obteniendo un segmento del nilve iniciando una posicin referencial
	 * y termina cuando haya cambio de nivel, esta posicion indica de donde inicia el nuevo nivel 
	 * pre condicion lista no vacia y lista ordenada x nivel
	 * @param n
	 * @param listInterfacesEmpaquetadas
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	protected List getListEmpaquetadaNivel(List listInterfacesEmpaquetadas,int posIni) {
		List result = new ArrayList();		
		Long nivelInicio= ((Interfaz)listInterfacesEmpaquetadas.get(posIni)).getNivelHilo();
		for(int n=posIni; n<listInterfacesEmpaquetadas.size();n++) {
			Interfaz interfazEmpaquetada = (Interfaz) listInterfacesEmpaquetadas.get(n);
			Long nivelHilo =interfazEmpaquetada.getNivelHilo();					
			
			if(nivelHilo.longValue() != nivelInicio.longValue()) break;
			
			result.add(interfazEmpaquetada);
						
		}
		
		return result;
	}

	/**
	 * @param params
	 * @param usuario
	 * @param codigoInterfaz
	 * @param numeroEjecucionEnvios
	 * @param interfazEjecucion
	 * @param historicoEjecucion
	 * @param listInterfacesEmpaquetadas
	 * @param listaNombresArchivos
	 * @param poolMultiHilo
	 * @param resultHilo
	 * @throws CloneNotSupportedException
	 */
	private void executeNivelMultHilo(Map params, Usuario usuario,
			String codigoInterfaz, String numeroEjecucionEnvios,
			Interfaz interfazEjecucion, Historico historicoEjecucion,
			List listInterfacesEmpaquetadas, String[] listaNombresArchivos,
			ExecutorService poolMultiHilo,
			List<Future<SSiCC_Desfasado_InterfazExecutionResult>> resultHilo)
			throws CloneNotSupportedException {
		String nombreArchivo;
		int ind;
		List<BaseInterfazService> listaInterfazEmpaquetadaImpl = new ArrayList<BaseInterfazService>();
		List<InterfazParams> listaInterfazParams = new ArrayList<InterfazParams>();
		List<Integer> listaNumeroEjecucion = new ArrayList<Integer>();
		
		List<InterfazMultiHiloParams> listaInterfazMultiHiloParams = new ArrayList<InterfazMultiHiloParams>();
		InterfazMultiHiloParams interfazMultiHiloParams = new InterfazMultiHiloParams();
		
		// Itero sobre la lista de interfaces del nivel i
		Iterator iterator = listInterfacesEmpaquetadas.iterator();
		ind=0;
		Long hiloActual = new Long(-1);
		int indiceHilo=-1;
		
		while (iterator.hasNext()) {
			Interfaz interfazEmpaquetada = (Interfaz) iterator.next();
			log.info("Iniciando ejecucion de Interfaz Unitaria empaquetada: "
					+ interfazEmpaquetada.getCodigo()
					+ " "
					+ interfazEmpaquetada.getDescripcion());
			BaseInterfazService interfazEmpaquetadaImpl = getInterfazImplementation(interfazEmpaquetada.getCodigo());

			//Obteniendo el nombre del archivo que se esta procesando para insertarlo en la tabla de BAS_HISTO_LOTES
			nombreArchivo = (listaNombresArchivos!=null && listaNombresArchivos.length>0)?listaNombresArchivos[ind]:"";
			ind++;
						
			// Creo el historico para la interfaz empaquetada
			Historico historicoEmpaquetada = initializeHistorico(interfazEmpaquetada, params);
			historicoEmpaquetada.setNumeroLote(historicoEjecucion.getNumeroLote());
			historicoEmpaquetada.setCodigoInterfaz(interfazEmpaquetada.getCodigo());
			historicoEmpaquetada.setCodigoInterfazEmpaquetada(codigoInterfaz);
			historicoEmpaquetada.setDescripcionLote(interfazEmpaquetada.getDescripcion());
			historicoEmpaquetada.setNombreArchivo(nombreArchivo);
			Historico historicoEmpaquetadaClone = new Historico();
			historicoEmpaquetadaClone = (Historico)historicoEmpaquetada.clone();
			
			/*INI PAQUETE ZIPEADO */
			if(interfazEjecucion.comprimir()) {
				interfazEmpaquetada.setDirectorioTemporalPaquete(interfazEjecucion.getDirectorioTemporalPaquete());
			}
			/*FIN PAQUETE ZIPEADO */
			
			// Creo los parametros para la interfaz empaquetada
			InterfazParams interfazParams = new InterfazParams();
			interfazParams.setInterfaz(interfazEmpaquetada);
			interfazParams.setHistorico(historicoEmpaquetadaClone);
			interfazParams.setUsuario(usuario);
			interfazParams.setQueryParams(params);
			interfazParams.updateInterfazQueryParams();
			InterfazParams interfazParamsClone = new InterfazParams();
			interfazParamsClone = (InterfazParams)interfazParams.clone();
			interfazParamsClone.getQueryParams().putAll(params);
			
			//solo es usado en este momento para el gestor de interfaces de micro seguros
			int numeroEjecucion = StringUtils.isNotEmpty(numeroEjecucionEnvios)?Integer.parseInt(numeroEjecucionEnvios):-1;
			
			//Adicionando a la listas
			Long hiloInterfaz = interfazEmpaquetada.getOrdenHilo();
			if (hiloActual.longValue() != hiloInterfaz.longValue()) {
				hiloActual = hiloInterfaz;
				indiceHilo++;
				
				listaInterfazEmpaquetadaImpl = new ArrayList<BaseInterfazService>();
				listaInterfazParams = new ArrayList<InterfazParams>();
				listaNumeroEjecucion = new ArrayList<Integer>();
				listaInterfazEmpaquetadaImpl.add(interfazEmpaquetadaImpl);
				listaInterfazParams.add(interfazParamsClone);
				listaNumeroEjecucion.add(numeroEjecucion);
				
				interfazMultiHiloParams = new InterfazMultiHiloParams();
				interfazMultiHiloParams.setListaInterfazEmpaquetadaImpl(listaInterfazEmpaquetadaImpl);
				interfazMultiHiloParams.setListaInterfazParams(listaInterfazParams);
				interfazMultiHiloParams.setListaNumeroEjecucion(listaNumeroEjecucion);
				
				listaInterfazMultiHiloParams.add(interfazMultiHiloParams);
			}
			else {
				listaInterfazEmpaquetadaImpl.add(interfazEmpaquetadaImpl);
				listaInterfazParams.add(interfazParamsClone);
				listaNumeroEjecucion.add(numeroEjecucion);
				
				interfazMultiHiloParams.setListaInterfazEmpaquetadaImpl(listaInterfazEmpaquetadaImpl);
				interfazMultiHiloParams.setListaInterfazParams(listaInterfazParams);
				interfazMultiHiloParams.setListaNumeroEjecucion(listaNumeroEjecucion);
				
				listaInterfazMultiHiloParams.set(indiceHilo, interfazMultiHiloParams);
			}
		}
		
		/* COMPROBADOR DE DATOS */
		Iterator iteratorHilo = listaInterfazMultiHiloParams.iterator();
		ind=0;
		while (iteratorHilo.hasNext()) {
			InterfazMultiHiloParams comprobadorInterfazMultiHiloParams = (InterfazMultiHiloParams) iteratorHilo.next();
			List listaService = comprobadorInterfazMultiHiloParams.getListaInterfazEmpaquetadaImpl();
			List listaInterfaz = comprobadorInterfazMultiHiloParams.getListaInterfazParams();
			log.info("HILO: "+ ind);
			for(int i=0;i < listaService.size(); i++) {
				BaseInterfazService interfazEmpaquetadaImpl = (BaseInterfazService)listaService.get(i);
				InterfazParams interfazParams = (InterfazParams)listaInterfaz.get(i);
				log.info("Interfaz: " + interfazParams.getInterfaz().getCodigo());
				log.info("BaseInterfazService: " + interfazEmpaquetadaImpl.toString());
			}
			/*INVOCADOR AL EJECUTOR DE INTERFAZ POR HILO */
			InterfazMultiHiloParams interfazMultiHiloParamsHijo = (InterfazMultiHiloParams)comprobadorInterfazMultiHiloParams.clone();
			//resultHilo[ind]
			Future<SSiCC_Desfasado_InterfazExecutionResult> submit= poolMultiHilo.submit(new SSiCC_Desfasado_BaseMultiHiloInterfazResult(this, interfazMultiHiloParamsHijo));
			resultHilo.add(submit);
			ind++;
			log.info(" ");
				
		}
			
	}
	
	/**
	 * Ejecucion de la parte Multihilo para las interfaz que esten comprendidos dentro de un paquete y que devuelve un resultado
	 * Solo para INTERFACES DE TIPO PAQUETE
	 * @param interfazMultiHiloParams
	 * @return
	 */
	public SSiCC_Desfasado_InterfazExecutionResult executeMultiHiloResult(InterfazMultiHiloParams interfazMultiHiloParams) throws Exception {
		SSiCC_Desfasado_InterfazExecutionResult result = new SSiCC_Desfasado_InterfazExecutionResult();
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeMultiHiloResult' method");
		}
		List<BaseInterfazService> listaService = interfazMultiHiloParams.getListaInterfazEmpaquetadaImpl();
		List<InterfazParams> listaInterfaz = interfazMultiHiloParams.getListaInterfazParams();
		List<Integer> listaNumeroEjecucion = interfazMultiHiloParams.getListaNumeroEjecucion();
		
		/* Invocando a la Ejecucion de Interfaces */
		for(int i=0;i < listaService.size(); i++) {
			BaseInterfazService interfazEmpaquetadaImpl = (BaseInterfazService)listaService.get(i);
			InterfazParams interfazParams = (InterfazParams)listaInterfaz.get(i);
			Integer numeroEjecucion = (Integer)listaNumeroEjecucion.get(i);
			Historico historicoEmpaquetada = interfazParams.getHistorico();
			Usuario usuario = (Usuario)interfazParams.getUsuario();
			historicoEmpaquetada.setFechaInicioProceso(new Timestamp(System.currentTimeMillis()));
			Map params = interfazParams.getQueryParams();
			historicoService.insertHistorico(historicoEmpaquetada, usuario);

			//Solo es usado en este momento para el gestor de interfaces de MicroSeguros
			if (numeroEjecucion > 0) {
				for (int x=0; x < numeroEjecucion; x++) {
					InterfazResult interfazResult = interfazEmpaquetadaImpl.executeInterfaz(interfazParams);
					result.getInterfazResults().add(interfazResult);
				}
			}
			else {
				InterfazResult interfazResult = interfazEmpaquetadaImpl.executeInterfaz(interfazParams);
				//ini validacion carga de interfaz
				
				String codigoInterfazArchivoControl = (String)params.get("codigoInterfazArchivoControl");				
				if(codigoInterfazArchivoControl != null 
							&& !codigoInterfazArchivoControl.equals(interfazParams.getInterfaz().getCodigo())){
				  if(interfazResult.isCompletado()){	
					params.put("estadoPendiente",Constants.NUMERO_UNO);
					params.put("codigoInterfazEjecucion", interfazParams.getInterfaz().getCodigo());
					interfazService.updateEstadoArchivoControl(params);	
					params.remove("codigoInterfazEjecucion");
				  }else{
					//verificvar si el erro viene de sto
					  String mensaje= interfazResult.getMensaje();
					  String errorSto =Constants.OCR_CARGA_ERROR_VALIDACION;
					  int index = StringUtils.isNotEmpty(mensaje)? mensaje.indexOf(errorSto):-1;
					  if(index != -1){
						  //hay error , pero marcamos la carga
							params.put("estadoPendiente",Constants.NUMERO_UNO);
							params.put("codigoInterfazEjecucion", interfazParams.getInterfaz().getCodigo());
							interfazService.updateEstadoArchivoControl(params);	
							params.remove("codigoInterfazEjecucion");
					  }
				  }								
				}
				//fin validacion carga de interfaz												
				result.getInterfazResults().add(interfazResult);
			}
			
			String numeroLote = historicoEmpaquetada.getNumeroLote();
			params.put("numeroLote", numeroLote);
			interfazParams.setQueryParams(params);
		}
		if (log.isDebugEnabled()) {
			log.debug("Fin 'executeMultiHiloResult' method");
		}
		return result;
	}
	
	/**
	 * Crea e inicializa el Historico.
	 * 
	 * @param params
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Historico inicializado
	 */
	private Historico initializeHistorico(Interfaz interfaz, Map params) {
		if (log.isDebugEnabled())
			log.debug("Entering 'initializeHistorico' method");
		Historico historico = new Historico();
		historico.setFechaInicioProceso(new Timestamp(System
				.currentTimeMillis()));
		historico.setCodigoPais(interfaz.getCodigoPais());
		historico.setCodigoSistema(interfaz.getCodigoSistema());
		historico.setCodigoInterfaz(interfaz.getCodigo());
		historico.setFlagError(Constants.NO);
		historico.setRegistrosProcesados(new Long(0));
		historico.setRegistrosErroneos(new Long(0));
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
		historico.setDescripcionLote((String) params.get("descripcion"));
		historico.setObservaciones((String) params.get("observaciones"));
		historico.setIdProcesoBatch((Long)params.get("idProcesoBatch"));
		historico.setOrdenHilo(params.get("ordenHilo")==null?interfaz.getOrdenHilo():(Long)params.get("ordenHilo"));
		historico.setOrderEjecucionHisto(params.get("ordenEjecucionHisto")==null?interfaz.getOrdenEjecucion():(Long)params.get("ordenEjecucionHisto"));
		//sb inicio niveles del hilo
		historico.setNivelHilo(params.get("nivelHilo")==null?interfaz.getNivelHilo():(Long)params.get("nivelHilo"));
		//fin nivel del hilo
		
		//se borra limpia el paramas en niveles, orden y ejecucion
		params.remove("ordenHilo");
		params.remove("ordenEjecucionHisto");
		params.remove("nivelHilo");
		
		if (log.isDebugEnabled())
			log.debug("historico=" + historico);
		return historico;
	}
	
	/**
	 * Obtiene la implementacion especifica de la Interfaz del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 *            utilizado como key del Map
	 * @return Implementacion especifica de la Interfaz SiCC
	 */
	protected BaseInterfazService getInterfazImplementation(String codigo) {
		return (BaseInterfazService) interfazImplementations.get(codigo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService#getPaqueteInterfazImplementation(java.lang.String)
	 */
	public SSiCC_Desfasado_BasePaqueteInterfazService getPaqueteInterfazImplementation(String codigo) {
		return (SSiCC_Desfasado_BasePaqueteInterfazService) interfazImplementations.get(codigo);
	}

	public void setInterfazImplementations(Map interfazImplementations) {
		this.interfazImplementations = interfazImplementations;
	}

	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}

	public void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}
	
	/**
	 * @param procesoBatchDAO the procesoBatchDAO to set
	 */
	public void setProcesoBatchDAO(ProcesoBatchDAO procesoBatchDAO) {
		this.procesoBatchDAO = procesoBatchDAO;
	}
	
	
	/**
	 * @param mantenimientoSTOBloqueoControlDAO the mantenimientoSTOBloqueoControlDAO to set
	 */
	public void setMantenimientoSTOBloqueoControlDAO(
			MantenimientoSTOBloqueoControlDAO mantenimientoSTOBloqueoControlDAO) {
		this.mantenimientoSTOBloqueoControlDAO = mantenimientoSTOBloqueoControlDAO;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService#getInterfazImplementations()
	 */
	public Map getInterfazImplementations() {
		return interfazImplementations;
	}

	/**
	 * @param baseinterfazServicePaquete the baseinterfazServicePaquete to set
	 */
	public void setBaseinterfazServicePaquete(
			BaseInterfazService baseinterfazServicePaquete) {
		this.baseinterfazServicePaquete = baseinterfazServicePaquete;
	}

	/**
	 * @return the interfazService
	 */
	public InterfazService getInterfazService() {
		return interfazService;
	}

	/**
	 * @return the historicoService
	 */
	public HistoricoService getHistoricoService() {
		return historicoService;
	}

	/**
	 * @return the procesoBatchDAO
	 */
	public ProcesoBatchDAO getProcesoBatchDAO() {
		return procesoBatchDAO;
	}

	/**
	 * @return the mantenimientoSTOBloqueoControlDAO
	 */
	public MantenimientoSTOBloqueoControlDAO getMantenimientoSTOBloqueoControlDAO() {
		return mantenimientoSTOBloqueoControlDAO;
	}

	/**
	 * @return the baseinterfazServicePaquete
	 */
	public BaseInterfazService getBaseinterfazServicePaquete() {
		return baseinterfazServicePaquete;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService#getStoService()
	 */
	public ProcesoSTOService getStoService() {
		return stoService;
	}

	/**
	 * @param stoService the stoService to set
	 */
	public void setStoService(ProcesoSTOService stoService) {
		this.stoService = stoService;
	}

	/* INI SA PER-SiCC-2012-0840 */
	/**
	 * @return the mailService
	 */
	public BaseMailService getMailService() {
		return mailService;
	}

	/**
	 * @param mailService the mailService to set
	 */
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}
	
	private void sendMail(InterfazParams interfazParams) {
		if (log.isDebugEnabled())
			log.debug("Entering 'sendMail' method");
		
		try {
			Interfaz interfaz = interfazParams.getInterfaz();
			log.debug("Entering 'sendMail' Indicador EnviarCorreo = " + interfaz.getIndicadorEnviarCorreo());
			
			Map parameterMap = new HashMap();
			parameterMap.put("interfaz", interfaz);
			parameterMap.put("historico", interfazParams.getHistorico());
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", interfaz.getCodigoPais());
			criteria.put("codigoSistema", interfaz.getCodigoSistema());
			criteria.put("codigoInterfaz", interfaz.getCodigo());
			criteria.put("numeroLote", interfazParams.getHistorico().getNumeroLote());
			List listaInterfaces = this.historicoService.getHistoricosByCriteria(criteria);
			parameterMap.put("listaInterfaces", listaInterfaces);
			
			Map queryParams = new HashMap();
			queryParams.put("parameterMap", parameterMap);
		
			MailParams mailParams = new MailParams();
			mailParams.setQueryParams(queryParams);
			mailParams.setUsuario(interfazParams.getUsuario());
			mailParams.setPais(interfaz.getPais());
		
			mailService.enviarMail(mailParams);
		
			log.info("Se envio un mail del paquete interfaz Ejecutada :" + interfaz.getCodigo());

		} catch (Exception e) {
			log.error("Error al grabar el Historico.");
			log.error(e.getMessage());
		}
	}
	/* FIN SA PER-SiCC-2012-0840 */

	/**
	 * @return the interfazExecutionServiceNSSiCC
	 */
	public InterfazExecutionService getInterfazExecutionServiceNSSiCC() {
		return interfazExecutionServiceNSSiCC;
	}

	/**
	 * @param interfazExecutionServiceNSSiCC the interfazExecutionServiceNSSiCC to set
	 */
	public void setInterfazExecutionServiceNSSiCC(
			InterfazExecutionService interfazExecutionServiceNSSiCC) {
		this.interfazExecutionServiceNSSiCC = interfazExecutionServiceNSSiCC;
	}

	
	
	
}