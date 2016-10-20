package biz.belcorp.ssicc.service.spusicc.mic.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.mic.ProcesoMICGeneracionAptasDAO;
import biz.belcorp.ssicc.service.framework.quartz.CronTriggerSchedulerService;
import biz.belcorp.ssicc.service.spusicc.mic.ProcesoMICSegurosSchedulerService;


/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMICSegurosSchedulerServiceImpl extends SchedulerFactoryBean implements
ProcesoMICSegurosSchedulerService {
	
	protected final Log log = LogFactory.getLog(getClass());
	public static int DELAY_DEFAULT =60000;
	
	private ProcesoMICGeneracionAptasDAO procesoMICGeneracionAptasDAO;
	
	private CronTriggerSchedulerService procesoMICSegurosCronTrigger;
	private CronTriggerSchedulerService interfazMICSegurosCronTrigger;
	private CronTriggerSchedulerService interfazMICRecepcionPagosCronTrigger;
	private CronTriggerSchedulerService interfazMICEnvioAseguradoraCronTrigger;


	/**
	 * @param procesoMICGeneracionAptasDAO the procesoMICGeneracionAptasDAO to set
	 */
	public void setProcesoMICGeneracionAptasDAO(
			ProcesoMICGeneracionAptasDAO procesoMICGeneracionAptasDAO) {
		this.procesoMICGeneracionAptasDAO = procesoMICGeneracionAptasDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosSchedulerService#setCronExpression(java.lang.String)
	 */
	public void setCronExpression() throws ParseException {
		
			Map micPrams = procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();
			String horaGenera=(String)micPrams.get("horaGenera");
			String horaEnvio =(String)micPrams.get("horaEnvia");//envio IPM
			String horaEnvioAseguradora = (String)micPrams.get("horaEnviaAseguradora");//
			String horaRecepcion = (String)micPrams.get("horaRecepcion");//
		    //seta el nuevo cron expresion				
			//crono generacion aptas
			if(StringUtils.isNotEmpty(horaGenera)){
				//CronTriggerBean procesoMICSegurosCronTrigger=(CronTriggerBean)triggers.get(0);						
				procesoMICSegurosCronTrigger.setCronExpression(getCronExpression(horaGenera));
			}
			//hora envio - crono envio IPM
			if(StringUtils.isNotEmpty(horaEnvio)){			
				//CronTriggerBean interfazMICSegurosCronTrigger=(CronTriggerBean)triggers.get(1);
				interfazMICSegurosCronTrigger.setCronExpression(getCronExpression(horaEnvio));
			}	
			
			//crono recepcion
			if(StringUtils.isNotEmpty(horaRecepcion)){			
				//CronTriggerBean interfazMICRecepcionPagosCronTrigger=(CronTriggerBean)triggers.get(2);
				interfazMICRecepcionPagosCronTrigger.setCronExpression(getCronExpression(horaRecepcion));
			}
			//crono envio aseguradora
			if(StringUtils.isNotEmpty(horaEnvioAseguradora)){			
				//CronTriggerBean interfazMICEnvioAseguradoraCronTrigger=(CronTriggerBean)triggers.get(3);
				//interfazMICEnvioAseguradoraCronTrigger.setCronExpression(getCronExpression(horaEnvioAseguradora));
				interfazMICEnvioAseguradoraCronTrigger.setCronExpression(getCronExpression(horaEnvioAseguradora));
			}			
		
	 	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosSchedulerService#setCronExpression(java.lang.String)
	 */
	public void setCronExpression(Map micPrams) throws ParseException {
		//log.debug("triggers " + triggers.size());
		
			String horaGenera=(String)micPrams.get("horaGenera");
			String horaEnvio =(String)micPrams.get("horaEnvia");
			String horaEnvioAseguradora = (String)micPrams.get("horaEnviaAseguradora");//
			String horaRecepcion = (String)micPrams.get("horaRecepcion");//			
		    //seta el nuevo cron expresion	
			if(StringUtils.isNotEmpty(horaGenera)){
				//CronTriggerBean procesoMICSegurosCronTrigger=(CronTriggerBean)triggers.get(0);						
				procesoMICSegurosCronTrigger.setCronExpression(getCronExpression(horaGenera));
			}
			//hora envio
			if(StringUtils.isNotEmpty(horaEnvio)){			
				//CronTriggerBean interfazMICSegurosCronTrigger=(CronTriggerBean)triggers.get(1);
				interfazMICSegurosCronTrigger.setCronExpression(getCronExpression(horaEnvio));
			}	
			
			//crono recepcion
			if(StringUtils.isNotEmpty(horaRecepcion)){			
				//CronTriggerBean interfazMICRecepcionPagosCronTrigger=(CronTriggerBean)triggers.get(2);
				interfazMICRecepcionPagosCronTrigger.setCronExpression(getCronExpression(horaRecepcion));
			}
			//crono envio aseguradora
			if(StringUtils.isNotEmpty(horaEnvioAseguradora)){			
				//CronTriggerBean interfazMICEnvioAseguradoraCronTrigger=(CronTriggerBean)triggers.get(3);
				//interfazMICEnvioAseguradoraCronTrigger.setCronExpression(getCronExpression(horaEnvioAseguradora));
				interfazMICEnvioAseguradoraCronTrigger.setCronExpression(getCronExpression(horaEnvioAseguradora));
			}
			
	 	
	}
	
	/**
	 * devuelve el cronogram seguun unix
	 * @param hora
	 * @return
	 */
	private String getCronExpression(String hora) {
		String expre ="0 M H * * ?";
		String res =StringUtils.replace(expre,"H",hora.substring(0,2));
		res=StringUtils.replace(res,"M",hora.substring(2,4));
		log.debug("getCronExpression "+res);
		//res="0 0/2 * * * ?";
		return res;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		//log.debug("afterPropertiesSet "+ triggers.size());		
		log.debug("is running "+super.isRunning());
		if(!super.isRunning()){
			setCronExpression();
			super.setAutoStartup(true);
			super.afterPropertiesSet();
			//super.start();
			//super.start();			
			
			final ProcesoMICSegurosSchedulerServiceImpl scheduler= this;			
			Thread schedulerThread = new Thread() {
				     				public void run() {
				     					try {
				     						//se revisa indicador		
				     					 while(true){
				     						//log.debug("hilo schdler");
				     						Map micPrams = procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();
				     						BigDecimal startupDelay =(BigDecimal)micPrams.get("valorDelay") ;
				     						//int delay= Integer.parseInt(StringUtils.isNotEmpty(startupDelay)?startupDelay:"0");
				     						if(startupDelay!=null && startupDelay.intValue()>0){												
					     						String indicador =(String)micPrams.get("indicadorCambio");
					     						if(StringUtils.equals(indicador, Constants.NUMERO_UNO)){
					     							//scheduler.setWaitForJobsToCompleteOnShutdown(false);
					     							log.debug("changing calendar ...");
					     							scheduler.stop();
					     							scheduler.destroy();
					     							
					     							scheduler.setOverwriteExistingJobs(true);
					     							scheduler.setCronExpression(micPrams);		
					     							log.debug("end set crono  ... reload...");
					     							scheduler.afterPropertiesBaseSet();
					     							scheduler.procesoMICGeneracionAptasDAO.updateParametros(micPrams);
					     						}					     					   	
				     						}else {
				     							return;
				     						}
				     						Thread.sleep(startupDelay.intValue() * DELAY_DEFAULT);
				     					 }
				     					}catch (InterruptedException ex1) {
				     						log.debug("error ex1 "+ex1.getMessage());
				     					}
				     					catch (Exception ex) {
				     						log.debug("error ex2 "+ex.getMessage());
				     					}
				  
				     				}
				     			};
		     schedulerThread.start();						
		}
	}
	
	/**
	 * @throws Exception
	 */
	public void afterPropertiesBaseSet() throws Exception {
		super.afterPropertiesSet();
	}

	
	
	/**
	 * @return the interfazMICEnvioAseguradoraCronTrigger
	 */
	public CronTriggerSchedulerService getInterfazMICEnvioAseguradoraCronTrigger() {
		return interfazMICEnvioAseguradoraCronTrigger;
	}

	/**
	 * @param interfazMICEnvioAseguradoraCronTrigger the interfazMICEnvioAseguradoraCronTrigger to set
	 */
	public void setInterfazMICEnvioAseguradoraCronTrigger(
			CronTriggerSchedulerService interfazMICEnvioAseguradoraCronTrigger) {
		this.interfazMICEnvioAseguradoraCronTrigger = interfazMICEnvioAseguradoraCronTrigger;
	}

	/**
	 * @return the procesoMICSegurosCronTrigger
	 */
	public CronTriggerSchedulerService getProcesoMICSegurosCronTrigger() {
		return procesoMICSegurosCronTrigger;
	}

	/**
	 * @param procesoMICSegurosCronTrigger the procesoMICSegurosCronTrigger to set
	 */
	public void setProcesoMICSegurosCronTrigger(
			CronTriggerSchedulerService procesoMICSegurosCronTrigger) {
		this.procesoMICSegurosCronTrigger = procesoMICSegurosCronTrigger;
	}

	/**
	 * @return the interfazMICSegurosCronTrigger
	 */
	public CronTriggerSchedulerService getInterfazMICSegurosCronTrigger() {
		return interfazMICSegurosCronTrigger;
	}

	/**
	 * @param interfazMICSegurosCronTrigger the interfazMICSegurosCronTrigger to set
	 */
	public void setInterfazMICSegurosCronTrigger(
			CronTriggerSchedulerService interfazMICSegurosCronTrigger) {
		this.interfazMICSegurosCronTrigger = interfazMICSegurosCronTrigger;
	}

	/**
	 * @return the interfazMICRecepcionPagosCronTrigger
	 */
	public CronTriggerSchedulerService getInterfazMICRecepcionPagosCronTrigger() {
		return interfazMICRecepcionPagosCronTrigger;
	}

	/**
	 * @param interfazMICRecepcionPagosCronTrigger the interfazMICRecepcionPagosCronTrigger to set
	 */
	public void setInterfazMICRecepcionPagosCronTrigger(
			CronTriggerSchedulerService interfazMICRecepcionPagosCronTrigger) {
		this.interfazMICRecepcionPagosCronTrigger = interfazMICRecepcionPagosCronTrigger;
	}

	/**
	 * @return the procesoMICGeneracionAptasDAO
	 */
	public ProcesoMICGeneracionAptasDAO getProcesoMICGeneracionAptasDAO() {
		return procesoMICGeneracionAptasDAO;
	}
	
	


	
	
}
