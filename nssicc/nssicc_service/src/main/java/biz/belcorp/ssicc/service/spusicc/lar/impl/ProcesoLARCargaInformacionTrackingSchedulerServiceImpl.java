package biz.belcorp.ssicc.service.spusicc.lar.impl;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.spusicc.lar.ProcesoLARCargaInformacionTrackingService;

/**
 * @author SigcomtUser
 *
 */
public class ProcesoLARCargaInformacionTrackingSchedulerServiceImpl extends SchedulerFactoryBean {
	
	protected final Log log = LogFactory.getLog(getClass());
	private GenericoDAO genericoDAO;
	
	private ProcesoLARCargaInformacionTrackingService procesoLARCargaInformacionTrackingService; 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosSchedulerService#setCronExpression(java.lang.String)
	 */
	public void setCronExpression() throws ParseException, SchedulerException, Exception {
		log.debug("Entrando .... ProcesoLARCargaInformacionTrackingSchedulerServiceImpl");
		
			
			//Recuperamos el codigo Pais
			String codigoPais = this.obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
			
			//Recuperamos el Periodo de Ejecucion del proceso
			String periodoEjecucion = this.obtenerParametroPais(codigoPais, Constants.LAR_CODIGO_SISTEMA, Constants.LAR_CODIGO_PERIODO_EJECUCION_CARGA_TRACKING);
			
		    //setea el nuevo cron expresion				
			//hora envio - crono envio IPM
			if(StringUtils.isNotEmpty(periodoEjecucion)){			
				//CronTriggerBean interfazFLXCronTrigger=(CronTriggerBean)triggers.get(0);
				
				MethodInvokingJobDetailFactoryBean tarea = new MethodInvokingJobDetailFactoryBean();
                tarea.setTargetObject(this.procesoLARCargaInformacionTrackingService);
                tarea.setTargetMethod("executeCargaInformacionTracking");
                tarea.setName("TR01_ProcesoLARCargaInformacionTrackingService");
                tarea.setConcurrent(false);
                tarea.afterPropertiesSet();
				
				CronTriggerBean cronTrigger = new CronTriggerBean();
				cronTrigger.setBeanName("CR01_ProcesoLARCargaInformacionTrackingService");
                cronTrigger.setCronExpression(periodoEjecucion);
                cronTrigger.afterPropertiesSet();
                
    			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.scheduleJob((JobDetail) tarea.getObject(), cronTrigger);
				scheduler.start();
			}	
		
	}

	/**
	 * @param codigoPais
	 * @param codigoSistema
	 * @param codigoParametro
	 * @return
	 */
	private String obtenerParametroPais(String codigoPais, String codigoSistema, String codigoParametro) {
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(codigoSistema);
		parametroPais.setCodigoParametro(codigoParametro);
		
		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
		String valorParametro = pPais.getValorParametro();
		
		return valorParametro;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		log.debug("is running "+super.isRunning());
		//Recuperamos el codigo Pais
		String codigoPais = this.obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
		
		//Verificamos si es Necesario que se ejecute el Quartz para el Proceso 
		String indicadorEjecucion = this.obtenerParametroPais(codigoPais, Constants.LAR_CODIGO_SISTEMA, Constants.LAR_CODIGO_INDICADOR_EJECUCION_CARGA_TRACKING);
		log.debug("indicadorEjecucion " + indicadorEjecucion);
		
		if(Constants.YES.equals(indicadorEjecucion)) {
			if(!super.isRunning()){
				log.debug("Empezando running ");
				
				setCronExpression();
				super.setAutoStartup(true);
				super.afterPropertiesSet();
			}	
		}
		
	}

	/**
	 * @throws Exception
	 */
	public void afterPropertiesBaseSet() throws Exception {
		super.afterPropertiesSet();
	}

	/**
	 * @return the genericoDAO
	 */
	public GenericoDAO getGenericoDAO() {
		return genericoDAO;
	}

	/**
	 * @param genericoDAO the genericoDAO to set
	 */
	public void setGenericoDAO(GenericoDAO genericoDAO) {
		this.genericoDAO = genericoDAO;
	}

	/**
	 * @return the procesoLARCargaInformacionTrackingService
	 */
	public ProcesoLARCargaInformacionTrackingService getProcesoLARCargaInformacionTrackingService() {
		return procesoLARCargaInformacionTrackingService;
	}

	/**
	 * @param procesoLARCargaInformacionTrackingService the procesoLARCargaInformacionTrackingService to set
	 */
	public void setProcesoLARCargaInformacionTrackingService(
			ProcesoLARCargaInformacionTrackingService procesoLARCargaInformacionTrackingService) {
		this.procesoLARCargaInformacionTrackingService = procesoLARCargaInformacionTrackingService;
	}

	
	
	
}
