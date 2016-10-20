/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENReactivacionesGerentesDirectorioService;

/**
 * <p>
 * <a href="ProcesoGENReactivacionesGerentesDirectorioServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com"> </a>
 */
@Service("spusicc.procesoGENReactivacionesGerentesDirectorioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENReactivacionesGerentesDirectorioServiceImpl extends BaseService implements ProcesoGENReactivacionesGerentesDirectorioService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";
	
	@Resource(name = "sisicc.interfazExecutionService")
	private InterfazExecutionService interfazExecutionService;
	
	@Resource(name = "scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name = "sisicc.interfazService")
	private InterfazService interfazService;
	
	@Resource(name = "genericoDAO")
	private GenericoDAO genericoDAO;
	
	@Resource(name = "scsicc.reporteDAO")
	private ReporteDAO reporteDAO;
	
	@Resource(name = "sisicc.mailProcesoGENReactivacionesGerentesDirectorioService")
	private BaseMailService mailService;
	
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENReactivacionesGerentesDirectorioService#executeInterfazGENReactivacionesGerentesDirectorio()
	 */
	public void executeInterfazGENReactivacionesGerentesDirectorio() throws Exception {
		log.debug("inicio job executeInterfazGENReactivacionesGerentesDirectorio");
		InterfazResult interfazResult = null;
		InterfazExecutionResult executionResult = null;
		
		log.debug("execute before executeInterfazGENReactivacionesGerentesDirectorio");
		
		//Recuperamos el codigo Pais
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoSistema(Constants.SISTEMA_GEN);
		parametroPais.setCodigoParametro(Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);
		//primero ejecutamos la primera interfaz GEN-43
		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
		String codigoPais = pPais.getCodigoPais();
		String codigoInterfaz = Constants.GEN_INTERFAZ_REACTIVACION_GERENTES_DIRECTORIO_LICENCIAS;
		String codigoProcesoBatch = Constants.GEN_PROCESO_REACTIVACION_GERENTES_DIRECTORIO;
		String codigoSistema = codigoInterfaz.substring(0, codigoInterfaz.indexOf('-')).trim();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String fechaProceso = sdf.format(cal.getTime());
        
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String fechaActual = sdf.format(cal.getTime());
		
		InterfazPK pk = new InterfazPK(codigoPais,codigoSistema, Constants.GEN_INTERFAZ_REACTIVACION_GERENTES_DIRECTORIO_LICENCIAS);
		InterfazPK pk1 = new InterfazPK(codigoPais,codigoSistema, Constants.GEN_INTERFAZ_REACTIVACION_GERENTES_DIRECTORIO_FUTURAS);
		
		//boolean isEjecutadoAnterior = StringUtils.equalsIgnoreCase(fechaProceso, sdf.format(interfazService.getFechaUltimoProceso(pk)));
		boolean isEjecutadoActual = StringUtils.equalsIgnoreCase(fechaActual, sdf.format(interfazService.getFechaUltimoProceso(pk)));
		//boolean isEjecutadoAnterior1 = StringUtils.equalsIgnoreCase(fechaProceso, sdf.format(interfazService.getFechaUltimoProceso(pk1)));
		boolean isEjecutadoActual1 = StringUtils.equalsIgnoreCase(fechaActual, sdf.format(interfazService.getFechaUltimoProceso(pk1)));
		
		
		if(!isEjecutadoActual && !isEjecutadoActual1)
		{
			if(isEjecucionProceso(codigoPais, codigoInterfaz, codigoProcesoBatch)){
				
				/* obteniendo Host */
				InetAddress address = InetAddress.getLocalHost();
				String sHostName;
				sHostName = address.getHostName();
				if (log.isDebugEnabled()) {
					log.debug("Nombre del Host: " + sHostName);
				}
				
				ParametroPais parametro = new ParametroPais();
				parametro.setCodigoPais(codigoPais);
				parametro.setCodigoSistema(Constants.SISTEMA_DIR);
				parametro.setCodigoParametro(Constants.COB_PARAM_SERVIDOR_ENVIO_RECUPERACION_COBRANZA);
				boolean servidorOASdedicado = true;
				boolean ejecutarInterfaz = true;
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
					
					//insertamos el proceso batch
					procesoBatchService.deleteProcesoBatchActu(params, usuario);
					procesoBatchService.insertProcesoBatchActu(params, usuario);
				
					//ejecutamos proceso interfaz			
					params = this.interfazExecutionService.executeInterfazValidacionesPrevias(params);
					executionResult =interfazExecutionService.executeInterfaz(params);
					
					boolean interfazGENLicencias = executionResult.ejecucionCompletada();
					
					codigoInterfaz = Constants.GEN_INTERFAZ_REACTIVACION_GERENTES_DIRECTORIO_FUTURAS;
					params.put("codigoInterfaz", codigoInterfaz);
					params.put("descripcion", getDescripcionInterfaz(params));
					
					//ejecutamos proceso interfaz			
					executionResult =interfazExecutionService.executeInterfaz(params);
					
					boolean interfazGENFuturas = executionResult.ejecucionCompletada();
				
					if(!interfazGENLicencias || !interfazGENFuturas){				
						updateInterfazRegistroProcesoBatchError(params, usuario, new Exception(interfazResult.getMensaje()));
						}
					else{
						updateInterfazRegistroProcesoBatchOK(params, usuario);
						//enviar correo
						Map criteria=new HashMap();
						Map paramCorreo=null;
						criteria.put("codigoPais",codigoPais);
						criteria.put("nombreReporte",Constants.GEN_NOMBRE_PARAM_CORREO);
						paramCorreo =  reporteDAO.getParametrosReporte(criteria);
						
						params.put("correosDestinos", MapUtils.getString(paramCorreo, "correoDefault"));
						params.put("correoOrigen", MapUtils.getString(paramCorreo, "correoOrigen")); 
						params.put("subject", MapUtils.getString(paramCorreo, "subject"));
						params.put("nombreArchivoReporte", null);
						params.put("fileAttachment", null);
						params.put("parameterMap",params);
						
						Pais pais = new Pais();
						pais.setCodigo(codigoPais);
						params.put("pais", pais);
						
						MailParams mailParams = new MailParams();
						mailParams.setUsuario((Usuario)MapUtils.getObject(params, "usuario"));
						mailParams.setPais((Pais)MapUtils.getObject(params, "pais"));
						mailParams.setQueryParams(params);
						
						mailService.enviarMail(mailParams);
						
					}
				}
			}
		}
		log.debug("fin job executeInterfazGENReactivacionesGerentesDirectorio");
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
