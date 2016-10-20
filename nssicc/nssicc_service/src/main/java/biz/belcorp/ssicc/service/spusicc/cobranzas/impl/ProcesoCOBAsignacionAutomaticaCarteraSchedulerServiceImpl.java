package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;


public class ProcesoCOBAsignacionAutomaticaCarteraSchedulerServiceImpl extends SchedulerFactoryBean {
	
	protected final Log log = LogFactory.getLog(getClass());
	private GenericoDAO genericoDAO;
	private List triggers; 
	
	/**
	 * @param genericoDAO the genericoDAO to set
	 */
	public void setGenericoDAO(GenericoDAO genericoDAO) {
		this.genericoDAO = genericoDAO;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#setTriggers(org.quartz.Trigger[])
	 */
	public void setTriggers(Trigger[] triggers) {
		 this.triggers = Arrays.asList(triggers);
		 super.setTriggers(triggers);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.ProcesoMICSegurosSchedulerService#setCronExpression(java.lang.String)
	 */
	public void setCronExpression() throws ParseException {
		log.debug("Entrando .... ProcesoCOBAsignacionAutomaticaCarteraSchedulerServiceImpl");
		if(triggers.size()>0){
			
			//Recuperamos el codigo Pais
			String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
			
			//Recuperamos el Periodo de Ejecucion para la Interfaz de Recuperacion de Cobranza
			String periodoEjecucion = obtenerParametroPais(codigoPais, Constants.CODIGO_SISTEMA_COB, "005");
			
		    //setea el nuevo cron expresion				
			//hora envio - crono envio IPM
			if(StringUtils.isNotEmpty(periodoEjecucion)){			
				CronTriggerBean interfazMICSegurosCronTrigger=(CronTriggerBean)triggers.get(0);
				interfazMICSegurosCronTrigger.setCronExpression(periodoEjecucion);
			}	
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
		log.debug("afterPropertiesSet "+ triggers.size());		
		log.debug("is running "+super.isRunning());
		//Recuperamos el codigo Pais
		String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);	
		
		//Verificamos si es Necesario que se ejecute el Quartz para el Proceso de Recuperacion de Cobranzas
		String indicadorEjecucion = obtenerParametroPais(codigoPais, Constants.CODIGO_SISTEMA_COB, "004");
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
	
}
