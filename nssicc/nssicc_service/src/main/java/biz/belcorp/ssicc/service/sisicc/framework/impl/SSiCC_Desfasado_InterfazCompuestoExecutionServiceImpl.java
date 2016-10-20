package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.ParametroInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BasePaqueteInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazCompuestoExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;


/**
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc_desfasado.interfazCompuestoExecutionService")
public class SSiCC_Desfasado_InterfazCompuestoExecutionServiceImpl extends SSiCC_Desfasado_InterfazExecutionServiceImpl implements SSiCC_Desfasado_InterfazCompuestoExecutionService {

	private static final String TIPO_DOCUMENTO_PARAM = "codigoTipoDocumento";
	
	@Resource(name="sisicc_desfasado.interfazExecutionService")
	private SSiCC_Desfasado_InterfazExecutionService interfazExecutionService;
	
	@Resource(name="sisicc.parametroInterfazService")
	private ParametroInterfazService parametroInterfazService ;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.impl.SSiCC_Desfasado_InterfazExecutionServiceImpl#executeInterfaz(java.util.Map)
	 */
	public SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(HashMap params) throws Exception {
		boolean succes=true;
		SSiCC_Desfasado_InterfazExecutionResult result = new SSiCC_Desfasado_InterfazExecutionResult();
		List interfazResults = new ArrayList();
		Usuario usuario = (Usuario)params.get("usuario"); 
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		//para el archivo de control si es que hubiera
		//String hayArchivoControl = (String)params.get("hayArchivoControl");
		String codigoInterfazArchivoControl = (String)params.get("codigoInterfazArchivoControl");
		//
		
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,	codigoSistema, codigoInterfaz);
		String[] listaInterfaces = (String[])params.get("listaInterfaces");
		String[] listaInterfacesSeleccionadas = (String[])params.get("listaInterfacesSeleccionadas");
		interfazEjecucionPK.setListaInterfaces(listaInterfaces);
		interfazEjecucionPK.setListaInterfacesSeleccionadas(listaInterfacesSeleccionadas);
		
		Interfaz interfazEjecucion = this.interfazExecutionService.
				getInterfazService().getInterfaz(interfazEjecucionPK);
		
		Historico historicoEjecucion = initializeHistorico(interfazEjecucion,params);
		params.put("codigoInterfazCompuesta", codigoInterfaz);
		String numeroLote="";
		synchronized (this) {
			numeroLote = (String) params.get("numeroLote");
			if (StringUtils.isEmpty(numeroLote)) { 
				numeroLote = this.interfazExecutionService.
									getInterfazService().getNumeroLote(interfazEjecucionPK);				
			}
			params.put("numeroLote",numeroLote);
			//params.remove("indicadorMultiLote");
			historicoEjecucion.setNumeroLote(numeroLote);
							
			log.debug("Insertando historico de compuesto=" + historicoEjecucion);
			this.interfazExecutionService.
					getHistoricoService().insertHistorico(historicoEjecucion, usuario);
			this.interfazExecutionService.
					getProcesoBatchDAO().updateProcesoBatchActuLote(params, usuario);
		}
		
		
		boolean indicadorSeleccionActivo = false;
		
		if (Constants.SI.equals(interfazEjecucion.getIndicadorSeleccion())) {
			if (listaInterfaces != null && listaInterfacesSeleccionadas != null)
				if (listaInterfacesSeleccionadas.length < listaInterfaces.length)
					indicadorSeleccionActivo = true;
				if (listaInterfacesSeleccionadas.length == 0) 
					indicadorSeleccionActivo = false;
		}
		/*para utilizar esta opcion la vista debe ser cambiada a una seleciion de Unitarias y compuesta*/
		/*ahorita se muestra las compuestas como una lista de interfaces unitarias*/
		List listInterfacesEmpaquetadas = null;
		if (indicadorSeleccionActivo)
			  listInterfacesEmpaquetadas = this.interfazExecutionService.
				getInterfazService().getComponentesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
		else
			  listInterfacesEmpaquetadas = this.interfazExecutionService.
				getInterfazService().getComponentesInterfazPaquete(interfazEjecucionPK);
		
						
		
		Iterator iterator = listInterfacesEmpaquetadas.iterator();
		int index=0;
		List listaMultiLoteCompuesto = (List) params.get("listaMultiLoteCompuesto");
		List listaMultiLoteCompuestoSize = (List) params.get("listaMultiLoteCompuestoSize");
		
		if (Constants.NO.equals(interfazEjecucion.getEsPaqueteMultiHilo())) {
			//no es multihilo			
			while(iterator.hasNext()){
				//boolean hayControl=false;
				Interfaz interfazEmpaquetada = (Interfaz) iterator.next();
				log.info("Iniciando ejecucion de Interfaz Unitaria/Paquete empaquetada: "
								+ interfazEmpaquetada.getCodigo()
								+ " "
								+ interfazEmpaquetada.getDescripcion());
				//String [] listArchivos =(String []) listaMultiLote.get(index);
				List listMultiLote = (List)listaMultiLoteCompuesto.get(index);
				List listMultiLoteSize = (List)listaMultiLoteCompuestoSize.get(index);
				//
				log.debug("numero de archivos por interfaz "+ listMultiLote.size() + " interfazEmpaquetada.getCodigo() "+interfazEmpaquetada.getCodigo());
				HashMap paramsHilo = (HashMap)params.clone();
				paramsHilo.put("listaMultiLote", listMultiLote);
				paramsHilo.put("listaMultiLoteSize", listMultiLoteSize);
				paramsHilo.put("codigoInterfaz", interfazEmpaquetada.getCodigo());
				paramsHilo.put("numeroMultiLote", listMultiLote.size());
				//paramsHilo.put("numeroLote",null);
				//paramsHilo.put("indicadorMultiLote", interfazEmpaquetada.getIndicadorMultiLote());
				
				if(interfazEmpaquetada.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)){
					//if es control se ejecuta tod los lotes /caso contraio s evalida si interfaz se encuntra en tabla
					//de control 
					if(codigoInterfazArchivoControl.equals(interfazEmpaquetada.getCodigo())){
						
						if(!hayArchivoControl(listMultiLoteSize)){
							index++;
							continue;
						}
						
						result = this.interfazExecutionService.executeInterfaz(paramsHilo);
					}else{
						/*validamos las interfaces en tabla de comntrol si hay archivo de control inicio*/
						String codigoErrorControl="";
						 //validamos que el paqute de interfz pertenzca ala tabla de control como no cargada 	
						  codigoErrorControl= validarInterfazByControl(params,interfazEmpaquetada.getCodigo(),
								  						Constants.TIPO_GENERACION_UNITARIA, codigoInterfazArchivoControl);	
						  if(codigoErrorControl.equals(Constants.ARCHIVO_NO_EXISTE_EN_CONTROL)){
							  index++;
							  continue;							  
						  }
						 //hay inteerfaces unitarias que obtinen numero de lote STO
						  paramsHilo.remove("numLoteSTO");
						  paramsHilo.remove("paramTipoDocumento");
						  //
						  setNumLoteSto(paramsHilo,interfazEmpaquetada);
						  //						  						  
						/*fin de validacion con el archivo de control*/
						 result = this.interfazExecutionService.executeInterfaz(paramsHilo);
					}
					 
				}else{
					/*validamos las interfaces en tabla de comntrol si hay archivo de control inicio*/
					String codigoErrorControl="";
					 //validamos que el paqute de interfz pertenzca ala tabla de control como no cargada 	
					  codigoErrorControl= validarInterfazByControl(params,interfazEmpaquetada.getCodigo(),Constants.TIPO_GENERACION_PAQUETE,
							  										codigoInterfazArchivoControl);	
					  if(codigoErrorControl.equals(Constants.ARCHIVO_NO_EXISTE_EN_CONTROL)){
						  index++;
						  continue;					
					  }
					/*fin de validacion con el archivo de control*/
					SSiCC_Desfasado_BasePaqueteInterfazService paqueteService = (SSiCC_Desfasado_BasePaqueteInterfazService) this.interfazExecutionService.getPaqueteInterfazImplementation(interfazEmpaquetada.getCodigo());
					//paqueteService.setSSiCC_Desfasado_InterfazExecutionService(SSiCC_Desfasado_InterfazExecutionService);
					result = paqueteService.executeInterfaz(paramsHilo);
				}				  								
				succes= succes && result.ejecucionCompletada();
				log.debug("succes no multhilo "+succes);
				index++;
			}
			
			InterfazResult interfazResult = new InterfazResult(numeroLote,interfazEjecucion);
			interfazResult.setCompletado(succes);
			interfazResults.add(interfazResult);
			result.setNumeroLote(numeroLote);
			result.setInterfazResults(interfazResults);
			result.setInterfaz(interfazEjecucion);
			
		}else{
			//multihilo multi nivel
			ExecutorService poolMultiHilo = null;
			Integer nroNivelesEjecutar = new Integer(1);
			Integer nroHilosPoolConexion = new Integer(5);
			long timeWait = 1000;//POR DEFAULT 1Sg

			if (indicadorSeleccionActivo)
				nroNivelesEjecutar = this.interfazExecutionService.
											getInterfazService().getNroNivelesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
			else
				nroNivelesEjecutar = this.interfazExecutionService.
											 getInterfazService().getNroNivelesInterfazPaquete(interfazEjecucionPK);
			
			List<Future<SSiCC_Desfasado_InterfazExecutionResult>> resultHilo = new ArrayList<Future<SSiCC_Desfasado_InterfazExecutionResult>>();//Future[nroHilosEjecutar] ;
			
			/*obteniendo maximo hilos*/
			Map criteriaParamPais = new HashMap();
			criteriaParamPais.put("codigoPais", codigoPais);
			criteriaParamPais.put("codigoSistema", "GEN");
			criteriaParamPais.put("nombreParametro", Constants.IND_MAX_HILO_INTERFAZ);
			String nroHilos = this.interfazExecutionService.
					   					getMantenimientoSTOBloqueoControlDAO().getParametroGenericoSistema(criteriaParamPais);
			
			if (StringUtils.isNotBlank(nroHilos))
				nroHilosPoolConexion = new Integer(nroHilos);
			
			/*obteniendo tiempo de espera x nivel */
			criteriaParamPais = new HashMap();
			criteriaParamPais.put("codigoPais", codigoPais);
			criteriaParamPais.put("codigoSistema", "GEN");
			criteriaParamPais.put("nombreParametro", Constants.TIME_WAIT_HILO_NIVEL);
			String strTimeWait = this.interfazExecutionService.
										getMantenimientoSTOBloqueoControlDAO().getParametroGenericoSistema(criteriaParamPais);
			
			if (StringUtils.isNotBlank(strTimeWait))
				timeWait = new Long(strTimeWait);
			
			/*fin de obtner el timewait*/
			
			int posIni=0;
			int indexHilo=0;
			for(int n=0;n<nroNivelesEjecutar;n++){
				log.debug("inicio nivel " + n);
				poolMultiHilo = Executors.newFixedThreadPool(nroHilosPoolConexion);						
				List listInterfacesEmpaquetadasNivel = getListEmpaquetadaNivel(listInterfacesEmpaquetadas,posIni);
				indexHilo=posIni;
				posIni+=listInterfacesEmpaquetadasNivel.size();
				log.debug("lista nivel "+n+" size: " + listInterfacesEmpaquetadasNivel.size() + "  inicio del sgte nivel en : "+posIni);
				//se encarga de forma el numero de hilos y por cada hilo las interfaces a ejecutar
				
				executeNivelMultHilo(interfazExecutionService, poolMultiHilo, (HashMap)params,
									 listInterfacesEmpaquetadasNivel,codigoInterfazArchivoControl,resultHilo,indexHilo);				
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
			
			for(Future<SSiCC_Desfasado_InterfazExecutionResult> res :resultHilo) {
				SSiCC_Desfasado_InterfazExecutionResult resultadoHilo = res.get();//(SSiCC_Desfasado_InterfazExecutionResult)resultHilo.get(i);
				Iterator iteratorResult = resultadoHilo.getInterfazResults().iterator();
				while (iteratorResult.hasNext()) {
					  InterfazResult interfazResult = (InterfazResult) iteratorResult.next();
					  result.getInterfazResults().add(interfazResult);
				}
			}
			
			InterfazResult interfazResult = new InterfazResult(numeroLote,interfazEjecucion);
			interfazResult.setCompletado(result.ejecucionCompletada());
			interfazResults.add(interfazResult);
			result.setInterfazResults(interfazResults);
			result.setInterfaz(interfazEjecucion);
			result.setNumeroLote(numeroLote);
			log.debug("is multihilo termina con lote "+numeroLote);						
		}
		
		historicoEjecucion.setFechaFinProceso(new Timestamp(System.currentTimeMillis()));
		boolean ejecucionExitosa = result.ejecucionCompletada();  
		if (!ejecucionExitosa) {
			historicoEjecucion.setFlagError(Constants.SI);
			historicoEjecucion.setDescripcionError(Constants.INTERFAZSICC_ERROR_EJECUCION_PAQUETE);
			historicoEjecucion.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO);
		}		

		this.interfazExecutionService.
				getHistoricoService().updateHistorico(historicoEjecucion, usuario);
		
		return result;
	}
	
	/**
	 * Obtiene el num lote STO si la interfaz esta relacionada aun tipod e documento
	 * @param paramsHilo
	 * @param interfazEmpaquetada
	 */
	private void setNumLoteSto(HashMap paramsHilo, Interfaz interfaz) {
	  String numLoteSTO="";
		  
	  String codigoPais= interfaz.getCodigoPais();
	  String codigoSistema= interfaz.getCodigoSistema();
	  String codigo= interfaz.getCodigo();
	  InterfazPK interfazPK = new InterfazPK(codigoPais, codigoSistema, codigo);
	  List<ParametroInterfaz> list = parametroInterfazService.getParametrosByPKInterfaz(interfazPK);
			for(ParametroInterfaz p : list){
				paramsHilo.put(p.getNombre(), p.getValor());			
				//
				if(TIPO_DOCUMENTO_PARAM.equals(p.getNombre())){
					log.debug("tipo documento  "+p.getValor());
					paramsHilo.put("paramTipoDocumento", p.getValor());		
					numLoteSTO = this.interfazExecutionService.
													getStoService().
														updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais,p.getValor()));
					log.info("numLoteSTO Unitaria: "+ numLoteSTO);		
					
				}
							
			}
		  
		  paramsHilo.put("numLoteSTO", numLoteSTO);
		  //paramsHilo.put("tipoDocumento", tipoDocumento);		
	}

	/**
	 * verifica si hay archvio de control para procesar
	 * @param listMultiLoteSize
	 * @return
	 */
	private boolean hayArchivoControl(List listMultiLoteSize) {
		long sum=0;
		for(int x=0;x<listMultiLoteSize.size();x++){
			 long [] listaArchivosSize = (long[])listMultiLoteSize.get(x);
			 for(int i=0; i< listaArchivosSize.length;i++){
				 sum+=listaArchivosSize[i];
			 }
		}
		log.debug("hayArchivoControl " + (sum>0?true:false));
		return sum>0?true:false;
	}

	/**
	 * Valida el paque de interfz conta la tabla de control, es una validacion referencial contras las no cargdas
	 * @param params
	 * @param tipoGeneracionPaquete 
	 * @param codigo
	 * @return
	 */
	private String validarInterfazByControl(HashMap params, String codigo, String tipoGeneracionPaquete, String codigoInterfazArchivoControl) {
		Map criteria = new HashMap();
		Long oidControl=null;
		criteria.put("codigoPais", params.get("codigoPais"));
		criteria.put("codigoSistema", params.get("codigoSistema"));		
		if(Constants.TIPO_GENERACION_UNITARIA.equals(tipoGeneracionPaquete))
			criteria.put("codigoInterfaz", codigo);
		else
			criteria.put("codigoPaquete", codigo);
		try{
		  log.debug("validando en compuesta");	
		  oidControl = this.interfazExecutionService.getInterfazService().getOidArchivoControl(criteria);//si existe oidcontrol es not null
		}catch(Exception e){
		  //hay mas de un lote que no se ha cargado ponemos a O para pasar la validacion de continuar
			oidControl =new Long(0);
		}
		log.debug("oidControl "+oidControl);
		criteria.remove("codigoPaquete");
		criteria.remove("codigoInterfaz");
		if(oidControl != null) {
			String idProcesoBatch = params.get("idProcesoBatch").toString();
			String datosCierre = obtenerDatosArchivoControl(idProcesoBatch, (String)params.get("codigoSistema"), 
															codigoInterfazArchivoControl, true);
			
			//si hay datos de interfaz de control de cierre, verificamos si hay interfaz de control de lote
			if(datosCierre!=null) {	
				String datosCierreAux = obtenerDatosArchivoControl(idProcesoBatch, (String)params.get("codigoSistema"), 
															codigoInterfazArchivoControl, false);
				
				if(datosCierreAux!=null) 
					return "-1";
				else
					return "-2";
			} else
				return "-1";
		}
		return "-2";
		
	}

	/**
	 * Obtine la lista de hilos para ejecutar
	 * @param SSiCC_Desfasado_InterfazExecutionService 
	 * @param poolMultiHilo 
	 * @param params
	 * @param listInterfacesEmpaquetadasNivel
	 * @param codigoInterfazArchivoControl 
	 * @param resultHilo
	 * @param indexHilo 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	private void executeNivelMultHilo(final SSiCC_Desfasado_InterfazExecutionService interfazExecutionService, 
									ExecutorService poolMultiHilo, HashMap params,
									List listInterfacesEmpaquetadasNivel,
									final String codigoInterfazArchivoControl, List<Future<SSiCC_Desfasado_InterfazExecutionResult>> resultHilo, int indexHilo) {
		
		
		List listaInterfazMultiHiloParams = new ArrayList();
		List listaInterfazMultiHilo = null;
		// Itero sobre la lista de interfaces del nivel i
		Iterator iterator = listInterfacesEmpaquetadasNivel.iterator();
		int ind=0;
		Long hiloActual = new Long(-1);
		int indiceHilo=-1;		
		//int index=0;
		while (iterator.hasNext()) {
			Interfaz interfazEmpaquetada = (Interfaz) iterator.next();					
			//Adicionando a la listas
			Long hiloInterfaz = interfazEmpaquetada.getOrdenHilo();
			if (hiloActual.longValue() != hiloInterfaz.longValue()) {
				hiloActual = hiloInterfaz;
				indiceHilo++;
				//anhadimos en la lista de interfaces y params
				listaInterfazMultiHilo = new ArrayList();
				Map map = new HashMap();
				map.put("hiloInterfaz", interfazEmpaquetada);
				map.put("index", indexHilo);
				listaInterfazMultiHilo.add(map);//
				listaInterfazMultiHiloParams.add(listaInterfazMultiHilo);//parametro la lista de interfaz
			}
			else {
				//actualizamos el params
				Map map = new HashMap();
				map.put("hiloInterfaz", interfazEmpaquetada);
				map.put("index", indexHilo);
				listaInterfazMultiHilo.add(map);				
				listaInterfazMultiHiloParams.set(indiceHilo, listaInterfazMultiHilo);
			}
			indexHilo++;
		}
		
		Iterator iteratorMapHilo = listaInterfazMultiHiloParams.iterator();
		ind=0;		
		//se tine la lista de los nombres de archivos de todas las interfaces
		final List listaMultiLoteCompuesto = (List) params.get("listaMultiLoteCompuesto");
		//se tine la lista de los num registros de archivos de todas las interfaces
		final List listaMultiLoteCompuestoSize = (List) params.get("listaMultiLoteCompuestoSize");
		while (iteratorMapHilo.hasNext()) {
			List<Map> listHiloMapInterfaz = (List) iteratorMapHilo.next();	
			/*parametros para las task*/
			final List listHiloMapInterfazClon = new ArrayList();
			final HashMap paramsHilo = (HashMap)params.clone();
			
			
			log.info("HILO Compuesto: "+ ind);
			for(int i=0;i < listHiloMapInterfaz.size(); i++) {
				Map interfazMapEmpaquetada = (Map)listHiloMapInterfaz.get(i);
				listHiloMapInterfazClon.add(interfazMapEmpaquetada);
				log.info("Interfaz Map: " + interfazMapEmpaquetada + "paramsHilo "+paramsHilo);
			}
									 
			Callable<SSiCC_Desfasado_InterfazExecutionResult> task =
					new Callable<SSiCC_Desfasado_InterfazExecutionResult>() {
				        boolean succes=false;				        
				        boolean ejecutoAlgunaInterfaz=false;
				        SSiCC_Desfasado_InterfazExecutionResult res=new SSiCC_Desfasado_InterfazExecutionResult() ;
				        List interfazResults = new ArrayList();
				        List<Map> hiloMapCallInterfaz = listHiloMapInterfazClon;
				        //int i=0;//varible que sirve para ver el log
						public SSiCC_Desfasado_InterfazExecutionResult call() {		
							log.debug("Entering 'call()' method  task "+Thread.currentThread() );
								for (Map mapInterfaz : hiloMapCallInterfaz){
							        Interfaz interfazMultiHiloHijo = (Interfaz)mapInterfaz.get("hiloInterfaz");
							        Integer pos = (Integer)mapInterfaz.get("index");
							        paramsHilo.put("codigoInterfaz", interfazMultiHiloHijo.getCodigo());									
									try {
										List listMultiLote = (List)listaMultiLoteCompuesto.get(pos);
										List listMultiLoteSize = (List)listaMultiLoteCompuestoSize.get(pos);
										//String [] listPaquete =(String []) listaMultiLote.get(pos);
										paramsHilo.put("listaMultiLote",listMultiLote );
										paramsHilo.put("listaMultiLoteSize",listMultiLoteSize );
										paramsHilo.put("ordenHilo",interfazMultiHiloHijo.getOrdenHilo());
										paramsHilo.put("ordenEjecucionHisto",interfazMultiHiloHijo.getOrdenEjecucion());
										paramsHilo.put("nivelHilo",interfazMultiHiloHijo.getNivelHilo());
										paramsHilo.put("numeroMultiLote", listMultiLote.size()); 
										//paramsHilo.put("numeroLote",null);										
										//paramsHilo.put("indicadorMultiLote",interfazMultiHiloHijo.getIndicadorMultiLote()); 
										
										//validacion de ejecutar	
										if(!codigoInterfazArchivoControl.equals(interfazMultiHiloHijo.getCodigo())){				
											/*validamos las interfaces en tabla de comntrol si hay archivo de control inicio*/
											String codigoErrorControl="";
											 //validamos que el paqute de interfz pertenzca ala tabla de control como no cargada 	
											  codigoErrorControl= validarInterfazByControl(paramsHilo,interfazMultiHiloHijo.getCodigo(),
													  				interfazMultiHiloHijo.getTipoGeneracion(), codigoInterfazArchivoControl);	
											  if(codigoErrorControl.equals(Constants.ARCHIVO_NO_EXISTE_EN_CONTROL)) continue;											
											/*fin de validacion con el archivo de control*/
										}
										else{
											if(!hayArchivoControl(listMultiLoteSize)) continue;
										}
										
										
										ejecutoAlgunaInterfaz = true;
										
										if(interfazMultiHiloHijo.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)){
											
											 //hay inteerfaces unitarias que obtinen numero de lote STO
											  paramsHilo.remove("numLoteSTO");
											  paramsHilo.remove("paramTipoDocumento");
											  //
											 setNumLoteSto(paramsHilo, interfazMultiHiloHijo);
											 res = interfazExecutionService.executeInterfaz(paramsHilo);
										}else{
											//BaseInterfazService paqueteService = (BaseInterfazService)
											SSiCC_Desfasado_BasePaqueteInterfazService paqueteService = (SSiCC_Desfasado_BasePaqueteInterfazService)
													interfazExecutionService.getPaqueteInterfazImplementation(interfazMultiHiloHijo.getCodigo());
											paqueteService.setInterfazExecutionService(interfazExecutionService);
											res = paqueteService.executeInterfaz(paramsHilo);
										}
										
										
										//res=this.interfazExecutionService.executeInterfaz(paramsHilo);
										succes = succes || res.ejecucionCompletada();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}							 	
								}
								
								if(!ejecutoAlgunaInterfaz) succes = true;
								log.debug("succes listHiloMapInterfaz : "+succes);
								
								InterfazResult interfazResult = new InterfazResult();
								interfazResult.setCompletado(succes);
								interfazResults.add(interfazResult);
								res.setInterfazResults(interfazResults);
								 log.debug("Finished 'call()' method task " +Thread.currentThread());		
								return res;
						}
					};			
			//resultHilo[ind]						
			Future<SSiCC_Desfasado_InterfazExecutionResult> submit=poolMultiHilo.submit(task);
			resultHilo.add(submit);
			ind++;
			log.info(" ");
				
		}
		
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
		historico.setOrdenHilo(interfaz.getOrdenHilo());
		historico.setOrderEjecucionHisto(interfaz.getOrdenEjecucion());
		//sb inicio niveles del hilo
		historico.setNivelHilo(interfaz.getNivelHilo());
		//fin nivel del hilo
		if (log.isDebugEnabled())
			log.debug("historico=" + historico);
		return historico;
	}
	
	private String obtenerDatosArchivoControl(String idProcesoBatch, String codigoSistema, String codigoInterfaz, boolean esTipoLoteCierre) {
		Map criteria = new HashMap();
		String datos = null;
		
		criteria.put("codigoSistema", codigoSistema);
		criteria.put("codigoInterfaz", codigoInterfaz);
		criteria.put("idProcesoBatch", idProcesoBatch);
		
		if(esTipoLoteCierre)
			criteria.put("tipoLoteCierre", "S");
		
		datos = this.interfazExecutionService.getInterfazService().getDescripcionArchivoControl(criteria);
			
		return datos;
	}
	
	

	

	/**
	 * @return the interfazExecutionService
	 */
	public SSiCC_Desfasado_InterfazExecutionService getInterfazExecutionService() {
		return interfazExecutionService;
	}

	/**
	 * @param interfazExecutionService the interfazExecutionService to set
	 */
	public void setInterfazExecutionService(
			SSiCC_Desfasado_InterfazExecutionService interfazExecutionService) {
		this.interfazExecutionService = interfazExecutionService;
	}

	/**
	 * @return the parametroInterfazService
	 */
	public ParametroInterfazService getParametroInterfazService() {
		return parametroInterfazService;
	}

	/**
	 * @param parametroInterfazService the parametroInterfazService to set
	 */
	public void setParametroInterfazService(
			ParametroInterfazService parametroInterfazService) {
		this.parametroInterfazService = parametroInterfazService;
	}	
}
