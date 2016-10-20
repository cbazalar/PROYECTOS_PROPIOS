package biz.belcorp.ssicc.service.spusicc.mic;

import java.text.ParseException;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoMICSegurosSchedulerService {



	/**
	 * Reseta el crono segun expresion de Base de datos
	 * 
	 * @throws ParseException
	 */
	public void setCronExpression() throws ParseException; 

}
