package biz.belcorp.ssicc.service.spusicc.flexipago.impl;

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
import biz.belcorp.ssicc.service.spusicc.flexipago.ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService;

public class ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebSchedulerServiceImpl extends SchedulerFactoryBean {
	
	protected final Log log = LogFactory.getLog(getClass());
	private GenericoDAO genericoDAO;
	
	private ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosSchedulerService#setCronExpression(java.lang.String)
	 */
	public void setCronExpression() throws ParseException, SchedulerException, Exception {
		log.debug("Entrando .... ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebSchedulerServiceImpl");
		
			
			//Recuperamos el codigo Pais
			String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
			
			//Recuperamos el Periodo de Ejecucion del proceso
			String periodoEjecucion = obtenerParametroPais(codigoPais, Constants.FLX_CODIGO_SISTEMA, Constants.FLX_CODIGO_PERIODO_EJECUCION_RECEPCION_WEB);
			
		    //setea el nuevo cron expresion				
			//hora envio - crono envio IPM
			if(StringUtils.isNotEmpty(periodoEjecucion)){			
				//CronTriggerBean interfazFLXCronTrigger=(CronTriggerBean)triggers.get(0);
				
				MethodInvokingJobDetailFactoryBean tarea = new MethodInvokingJobDetailFactoryBean();
                tarea.setTargetObject(this.procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService);
                tarea.setTargetMethod("executeRecepcionArchivosConsultorasRecomendadasRechazadasWeb");
                tarea.setName("TR01_ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadas");
                tarea.setConcurrent(false);
                tarea.afterPropertiesSet();
				
				CronTriggerBean cronTrigger = new CronTriggerBean();
				cronTrigger.setBeanName("CR01_ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadas");
                cronTrigger.setCronExpression(periodoEjecucion);
                cronTrigger.afterPropertiesSet();
                
    			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.scheduleJob((JobDetail) tarea.getObject(), cronTrigger);
				scheduler.start();
			}	
		
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
		
		//Verificamos si es Necesario que se ejecute el Quartz para el Proceso 
		String indicadorEjecucion = obtenerParametroPais(codigoPais, Constants.FLX_CODIGO_SISTEMA, Constants.FLX_CODIGO_INDICADOR_EJECUCION_RECEPCION_WEB);
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
	 * @return the procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService
	 */
	public ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService getProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService() {
		return procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService;
	}

	/**
	 * @param procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService the procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService to set
	 */
	public void setProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService(
			ProcesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService) {
		this.procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService = procesoFLXRecepcionArchivosConsultorasRecomendadasRechazadasWebService;
	}

	
	
}
