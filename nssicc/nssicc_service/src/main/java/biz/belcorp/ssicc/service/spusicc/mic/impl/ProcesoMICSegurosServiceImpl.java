package biz.belcorp.ssicc.service.spusicc.mic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.spusicc.mic.ProcesoMICGeneracionAptasDAO;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.spusicc.mic.ProcesoMICSegurosService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */

@Service("procesoMICSegurosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMICSegurosServiceImpl extends BaseService implements
	ProcesoMICSegurosService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";
	private static final String TIPO_OPERACION_IPM = "01";
	private static final String TIPO_OPERACION_ASEG = "02";
	
	
	@Resource(name="sisicc.interfazExecutionService")
	private InterfazExecutionService interfazExecutionService ;
	
	@Resource(name="spusicc.procesoMICGeneracionAptasDAO")
	private ProcesoMICGeneracionAptasDAO procesoMICGeneracionAptasDAO;
	
	@Resource(name="sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name="sisicc.interfazService")
	private InterfazService interfazService;
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICGeneracionAptasService#executeGeneracionAptasMicroSeguros(java.util.Map)
	 */
	public void executeGeneracionAptasMicroSeguros()
			throws Exception {
		log.debug("inico job executeGeneracionAptasMicroSeguros ");
		Map micPrams = procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();
		String horaGene = (String)micPrams.get("horaGenera");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String hora = sdf.format(new Date(System.currentTimeMillis()));
		//log.debug("comparando hora de genracion con la hora schudler " + hora + " horaGenr " + horaGene);
		if(hora.equals(horaGene)){
		//relaizamos el proceso
			log.debug("execute before executeGeneracionAptasMicroSeguros");
			if(isEjecucionProceso(micPrams,Constants.MIC_PROCESO_BATCH_GENERACION)){
				//preparamos el param
				Map params = prepareParamsBeforeExecute(micPrams);
				//sobreescribimos 
				params.put("codigoProcesoBatch", Constants.MIC_PROCESO_BATCH_GENERACION);
				//insertamos el proceos batch
				Usuario usuario = (Usuario) params.get("usuario");
				procesoBatchService.deleteProcesoBatchActu(params, usuario);
				procesoBatchService.insertProcesoBatchActu(params, usuario);
				//executamos proceso
			 try{	
				 SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");				 
				 String fecha = sdf1.format(new Date(System.currentTimeMillis()));
				 params.put("fechaProceso", fecha); 
				procesoMICGeneracionAptasDAO.executeGeneracionAptasMicroSeguros(params);
			 }catch(Exception e){
				 updateInterfazRegistroProcesoBatchError(params, usuario, e);
				 log.debug("fin job executeGeneracionAptasMicroSeguros ");
				 return;
			 }
			   updateInterfazRegistroProcesoBatchOK(params,usuario);
			}
		}	
		log.debug("fin job executeGeneracionAptasMicroSeguros ");				
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosService#executeEnvioPagoMicroSeguros()
	 */
	public void executeEnvioPagoMicroSeguros() throws Exception {
		log.debug("inicio job executeEnvioPagoMicroSeguros ");	
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		//procesoMICGeneracionAptasDAO.executeGeneracionAptasMicroSeguros(criteria);		
		
		Map micPrams = procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();
		String horaEnvia = (String)micPrams.get("horaEnvia");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String hora = sdf.format(new Date(System.currentTimeMillis()));
		//log.debug("comparando hora de genracion con la hora schudler " + hora + " horaGenr " + horaEnvia);
		if(hora.equals(horaEnvia)){		
			Integer result= procesoMICGeneracionAptasDAO.validaEnvioMicroseguros(TIPO_OPERACION_IPM);
			log.debug("reult validacion "+result);
			if(result==1){
				log.debug("execute before executeEnvioPagoMicroSeguros");
			  if(isEjecucionProceso(micPrams,Constants.MIC_PROCESO_BATCH_ENVIO_IPM)){	
					Map params = prepareParamsBeforeExecute(micPrams);
					params.put("codigoInterfaz", "MIC-1");//el unitario
					params.put("codigoProcesoBatch", Constants.MIC_PROCESO_BATCH_ENVIO_IPM);
					params.put("descripcion", getDescripcionInterfaz(params));
					//insertamos el proceos batch
					Usuario usuario = (Usuario) params.get("usuario");
					procesoBatchService.deleteProcesoBatchActu(params, usuario);
					procesoBatchService.insertProcesoBatchActu(params, usuario);
		            //ejecutamos proceso interfaz			
					executionResult =interfazExecutionService.executeInterfaz(params);
					List list = executionResult.getInterfazResultsInterfaz();
					interfazResult = (InterfazResult)list.get(0);
					if(!interfazResult.isCompletado())				
						updateInterfazRegistroProcesoBatchError(params, usuario,new Exception(interfazResult.getMensaje()));
					else
						updateInterfazRegistroProcesoBatchOK(params,usuario);
			  }	
			}
		}
		log.debug("fin job executeEnvioPagoMicroSeguros ");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosService#executeEnvioAseguradoraMicroSeguros()
	 */
	public void executeEnvioAseguradoraMicroSeguros() throws Exception {
		log.debug("inicio job executeEnvioAseguradoraMicroSeguros ");	
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		//procesoMICGeneracionAptasDAO.executeGeneracionAptasMicroSeguros(criteria);
		Map micPrams = procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();		
		String horaEnviaAseguradora = (String)micPrams.get("horaEnviaAseguradora");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String hora = sdf.format(new Date(System.currentTimeMillis()));
		//log.debug("comparando hora de genracion con la hora schudler " + hora + " horaGenr " + horaEnviaAseguradora);
		if(hora.equals(horaEnviaAseguradora)){			
			Integer result= procesoMICGeneracionAptasDAO.validaEnvioMicroseguros(TIPO_OPERACION_ASEG);
			log.debug("reult validacion "+result);
			if(result==1){
				log.debug("execute before executeEnvioAseguradoraMicroSeguros");
			  if(isEjecucionProceso(micPrams,Constants.MIC_PROCESO_BATCH_ENVIO_ASEG)){	
					Map params = prepareParamsBeforeExecute(micPrams);
					params.put("codigoInterfaz", "MIC-3");//el unitario
					params.put("codigoProcesoBatch", Constants.MIC_PROCESO_BATCH_ENVIO_ASEG);
					params.put("descripcion", getDescripcionInterfaz(params));
					//insertamos el proceos batch
					Usuario usuario = (Usuario) params.get("usuario");
					procesoBatchService.deleteProcesoBatchActu(params, usuario);
					procesoBatchService.insertProcesoBatchActu(params, usuario);
		            //ejecutamos proceso interfaz			
					executionResult =interfazExecutionService.executeInterfaz(params);
					List list = executionResult.getInterfazResultsInterfaz();
					interfazResult = (InterfazResult)list.get(0);
					if(!interfazResult.isCompletado())				
						updateInterfazRegistroProcesoBatchError(params, usuario,new Exception(interfazResult.getMensaje()));
					else
						updateInterfazRegistroProcesoBatchOK(params,usuario);
			  }	
			}
		}
		log.debug("fin job executeEnvioAseguradoraMicroSeguros ");	
		
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosService#executeRecepcionPagoMicroSeguros()
	 */
	public void executeRecepcionPagoMicroSeguros() throws Exception {
		log.debug("inicio job executeRecepcionPagoMicroSeguros ");	
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		//procesoMICGeneracionAptasDAO.executeGeneracionAptasMicroSeguros(criteria);
		Map micPrams = procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();	
		String horaRecepcion = (String)micPrams.get("horaRecepcion");
		String numeroEnvios = (String)micPrams.get("numeroArchivos");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String hora = sdf.format(new Date(System.currentTimeMillis()));
		//log.debug("comparando hora de genracion con la hora schudler " + hora + " horaGenr " + horaGene);
		if(hora.equals(horaRecepcion)){			
		  if(isEjecucionProceso(micPrams,Constants.MIC_PROCESO_BATCH_ENVIO_ASEG)){	
				Map params = prepareParamsBeforeExecute(micPrams);
				params.put("codigoInterfaz", "MIC-2");//el unitario
				params.put("codigoProcesoBatch", Constants.MIC_PROCESO_BATCH_RECEP);
				params.put("descripcion", getDescripcionInterfaz(params));
				//insertamos el proceos batch
				Usuario usuario = (Usuario) params.get("usuario");
				procesoBatchService.deleteProcesoBatchActu(params, usuario);
				procesoBatchService.insertProcesoBatchActu(params, usuario);
	            //ejecutamos proceso interfaz
				params.put("numeroEjecucionEnvios", numeroEnvios);
				executionResult =interfazExecutionService.executeInterfaz(params);
				
				List list = executionResult.getInterfazResultsInterfaz();
				log.debug("list " + list.size());
				boolean isCompleto = true;
				for(int i=0;i<list.size();i++){
					interfazResult = (InterfazResult)list.get(i);
					isCompleto = isCompleto && interfazResult.isCompletado();
					if(!isCompleto) break;
				}  
				
				if(!isCompleto)				
					updateInterfazRegistroProcesoBatchError(params, usuario,new Exception(interfazResult.getMensaje()));
				else
					updateInterfazRegistroProcesoBatchOK(params,usuario);
		  }	
		}
		log.debug("fin job executeRecepcionPagoMicroSeguros ");	
		
	}

	   /**
     * Retorna la descripcion de la intefaz
     * @param params
     * @return
     */
    private String getDescripcionInterfaz(Map params) {
    	
  	  	String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService
				.getInterfaz(interfazEjecucionPK);
		return (interfazEjecucion!=null?interfazEjecucion.getDescripcion():"");
	}

	/**
	 * @param micPrams
	 * @return
	 */
	protected Map prepareParamsBeforeExecute(Map micPrams) {
		String codigoPais= (String)micPrams.get("codigoPais");
		String codigoPeriodo =interfazSiCCService.getPeriodoDefaultByPaisCanal(codigoPais,Constants.CODIGO_CANAL_DEFAULT);
		String codigoInterfaz = Constants.MIC_PAQUETE_INTERFAZ_SALIDA;
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
		
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaFacturacion",fechaFacturacion);
		params.put("numeroLote", "");
		params.put("codigoSistema", codigoSistema);
		params.put("codigoInterfaz", codigoInterfaz);	
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		//el usuario QUARTZ		
		Usuario usuario = new Usuario();
		usuario.setLogin(USUARIO_QUARTZ);
	    params.put("usuario",usuario);
		params.put("codigoUsuario", usuario.getLogin());
		return params;
	}

	/**
	 * @param params
	 * @param usuario
	 */
	private void updateInterfazRegistroProcesoBatchOK(Map params,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
			
		procesoBatchService.updateProcesoBatchActu(params, usuario);		
	}


	/**
	 * @param params
	 * @param usuario
	 * @param e
	 */
	private void updateInterfazRegistroProcesoBatchError(Map params,
			Usuario usuario, Exception e) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR); 
		
		try{
			String descripcionLog = (e!=null?e.getMessage():"null");
		
			if (descripcionLog.length() >= 1000) {
				descripcionLog = descripcionLog.substring(1,999);
			}
		
			params.put("descripcionLog", descripcionLog);
			procesoBatchService.updateProcesoBatchActu(params, usuario);
		}catch(Exception ex){
			params.put("descripcionLog", ex.getMessage());
			procesoBatchService.updateProcesoBatchActu(params, usuario);
		}
		
	}


	/**
	 * Se encarga de verificar si ya esta en ejecucion
	 * @param micPrams
	 * @param codigoProcesoBatch 
	 * @return
	 */
	private boolean isEjecucionProceso(Map micPrams, String codigoProcesoBatch) {
		Map params = new HashMap();
		String codPais = (String)micPrams.get("codigoPais");
		String codigoInterfaz = Constants.MIC_PAQUETE_INTERFAZ_SALIDA;
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim(); 
		//String codigoProcesoBatch = Constants.MIC_PROCESO_BATCH_GENERACION;
		params.put("codigoPais", codPais);
		params.put("codigoSistema", codigoSistema);
		params.put("codigoProcesoBatch", codigoProcesoBatch);
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		
		/* Verificando que no se encuentre en Ejecucion */
		List listaProcesoBatch = procesoBatchService.getProcesoBatchActuByCriteria(params);
		if (listaProcesoBatch.size() > 0) {
			ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
				return false;
			}
		}
		return true;
	}
}
