/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sms.impl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.spusicc.sms.ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioService;

/**
 * <p>
 * <a href="ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com"> </a>
 */

@Service("spusicc.procesoSMSEnvioConsultorasPagosCCRecaudoBancarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioServiceImpl extends BaseService implements ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";
	
	@Resource(name="sisicc.interfazExecutionService")
	private InterfazExecutionService interfazExecutionService;
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name="sisicc.interfazService")
	private InterfazService interfazService;
	
	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.service.ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioService#executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario()
	 */
	public void executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario() throws Exception {
		
		log.debug("inicio job executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario");
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		
		/* obteniendo Host */
		InetAddress address = InetAddress.getLocalHost();
		String sHostName = address.getHostName();
		log.debug("Nombre del Host: " + sHostName);
		
		boolean servidorOASdedicado = true;
		boolean ejecutarInterfaz = true;
		
		ParametroPais parametro = new ParametroPais();
		parametro.setCodigoSistema(Constants.SISTEMA_BAS);
		parametro.setCodigoParametro(Constants.BAS_SERVER_QUARTZ_DEFAULT);
		
		ParametroPais servidor = new ParametroPais();
		try {
			servidor = (ParametroPais)genericoDAO.getParametrosPais(parametro).get(0);
		}
		catch(Exception e) {
			servidorOASdedicado = false;
		}
		if (servidorOASdedicado) {
			if (!StringUtils.equalsIgnoreCase(sHostName, servidor.getValorParametro()))
				ejecutarInterfaz = false;
		}
		
		if (ejecutarInterfaz) {
			log.debug("Iniciando ejecuci�n de job");
			
			//Recuperamos el codigo Pais
			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoSistema(Constants.SISTEMA_GEN);
			parametroPais.setCodigoParametro(Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);
			
			ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
			String codigoPais = pPais.getCodigoPais();
			String codigoInterfaz = Constants.SMS_INTERFAZ_ENVIO_CONSULTORA_PAGOS_RECAUDO_BANCARIO;
			String codigoProcesoBatch = Constants.SMS_PROCESO_ENVIO_CONSULTORA_PAGOS;
			
			if(isEjecucionProceso(codigoPais, codigoInterfaz, codigoProcesoBatch)){
				
				String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
		        Calendar cal = GregorianCalendar.getInstance();
		        //cal.add(Calendar.DAY_OF_MONTH, -1);
		        String fechaProceso = sdf.format(cal.getTime());

				Usuario usuario = new Usuario();
				usuario.setLogin(USUARIO_QUARTZ);
				
				Map params = new HashMap();
			    params.put("usuario",usuario);
				params.put("codigoUsuario", usuario.getLogin());
				params.put("codigoPais", codigoPais);
				params.put("codigoSistema", codigoSistema);
				params.put("codigoInterfaz", codigoInterfaz);
				params.put("numeroLote", "");
				params.put("fechaProceso", fechaProceso);
				params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
				params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
				params.put("codigoProcesoBatch", codigoProcesoBatch);
				params.put("descripcion", getDescripcionInterfaz(params));
				params.put("flagNovedades", Constants.NUMERO_UNO);
				
				//insertamos el proceso batch
				procesoBatchService.deleteProcesoBatchActu(params, usuario);
				procesoBatchService.insertProcesoBatchActu(params, usuario);
			
				//ejecutamos proceso interfaz		
				params = this.interfazExecutionService.executeInterfazValidacionesPrevias(params);
				executionResult =interfazExecutionService.executeInterfaz(params);
				
				if(!executionResult.ejecucionCompletada())				
					updateInterfazRegistroProcesoBatchError(params, usuario, new Exception(interfazResult.getMensaje()));
				else
					updateInterfazRegistroProcesoBatchOK(params, usuario);
			}
			
			log.debug("fin job executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario");
		}
		
		
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
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
		return (interfazEjecucion!=null?interfazEjecucion.getDescripcion():"");
	}
	
	/**
	 * Se encarga de verificar si ya esta en ejecucion
	 * @param micPrams
	 * @param codigoProcesoBatch 
	 * @return
	 */
	private boolean isEjecucionProceso(String codigoPais, String codigoInterfaz, String codigoProcesoBatch) {
		Map params = new HashMap();
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		
		params.put("codigoPais", codigoPais);
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
	
}
