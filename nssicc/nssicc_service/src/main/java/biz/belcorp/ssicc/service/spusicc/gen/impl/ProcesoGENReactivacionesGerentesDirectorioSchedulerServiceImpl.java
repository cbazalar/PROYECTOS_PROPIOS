/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
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
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENReactivacionesGerentesDirectorioService;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

/**
 * @author ghuertas
 *
 */
public class ProcesoGENReactivacionesGerentesDirectorioSchedulerServiceImpl extends SchedulerFactoryBean {

	protected final Log log = LogFactory.getLog(getClass());
	
	private GenericoDAO genericoDAO;
	
	private ProcesoGENReactivacionesGerentesDirectorioService procesoGENReactivacionesGerentesDirectorioService;
	
	/**
	 * @param genericoDAO the genericoDAO to set
	 */
	public void setGenericoDAO(GenericoDAO genericoDAO) {
		this.genericoDAO = genericoDAO;
	}

	
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
		String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
		
		//Verificamos si es Necesario que se ejecute el Quartz para el Proceso de Reactivaciones de Gerentes de Directorio
		String indicadorEjecucion = obtenerParametroPais(codigoPais, Constants.SISTEMA_DIR, "001");
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
	
	public void setCronExpression() throws ParseException, SchedulerException, Exception {
		log.debug("Entrando .... ProcesoGENReactivacionesGerentesDirectorioSchedulerServiceImpl");
		
			//Recuperamos el Periodo de Ejecucion de Reactivaciones de Gerentes de Directorio
		    String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
		    String periodoEjecucion = obtenerParametroPais(codigoPais, Constants.SISTEMA_DIR, "002");
			
		    //setea el nuevo cron expresion				
			//hora envio - crono envio IPM
			log.debug("is running " + periodoEjecucion);
			if(StringUtils.isNotEmpty(periodoEjecucion)){			
				//interfazGENReactivacionesGerentesDirectorioCronTrigger.setCronExpression(periodoEjecucion);
				
				MethodInvokingJobDetailFactoryBean tarea = new MethodInvokingJobDetailFactoryBean();
                tarea.setTargetObject(this.procesoGENReactivacionesGerentesDirectorioService);
                tarea.setTargetMethod("executeInterfazGENReactivacionesGerentesDirectorio");
                tarea.setName("TR01_ProcesoGENReactivacionesGerentesDirectorio");
                tarea.setConcurrent(false);
                tarea.afterPropertiesSet();
				
				CronTriggerBean cronTrigger = new CronTriggerBean();
				cronTrigger.setBeanName("CR01_ProcesoGENReactivacionesGerentesDirectorio");
                cronTrigger.setCronExpression(periodoEjecucion);
                cronTrigger.afterPropertiesSet();
                
    			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.scheduleJob((JobDetail) tarea.getObject(), cronTrigger);
				scheduler.start();
			}	
			
	}

	/**
	 * @throws Exception
	 */
	public void afterPropertiesBaseSet() throws Exception {
		super.afterPropertiesSet();
	}


	/**
	 * @return the procesoGENReactivacionesGerentesDirectorioService
	 */
	public ProcesoGENReactivacionesGerentesDirectorioService getProcesoGENReactivacionesGerentesDirectorioService() {
		return procesoGENReactivacionesGerentesDirectorioService;
	}


	/**
	 * @param procesoGENReactivacionesGerentesDirectorioService the procesoGENReactivacionesGerentesDirectorioService to set
	 */
	public void setProcesoGENReactivacionesGerentesDirectorioService(
			ProcesoGENReactivacionesGerentesDirectorioService procesoGENReactivacionesGerentesDirectorioService) {
		this.procesoGENReactivacionesGerentesDirectorioService = procesoGENReactivacionesGerentesDirectorioService;
	}


	
	
	
	
	
}
