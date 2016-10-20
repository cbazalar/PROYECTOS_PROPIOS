package biz.belcorp.ssicc.service.framework.quartz;

import java.text.ParseException;
/**
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 *
 */
public interface CronTriggerSchedulerService {



	/**
	 * Reseta el crono segun expresion de Base de datos
	 * 
	 * @throws ParseException
	 */
	public void setCronExpression(String expression) throws ParseException; 

}
