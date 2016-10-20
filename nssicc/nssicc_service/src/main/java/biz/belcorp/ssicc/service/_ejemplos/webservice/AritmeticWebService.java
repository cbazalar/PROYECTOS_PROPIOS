/**
 * 
 */
package biz.belcorp.ssicc.service._ejemplos.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Danny Amaro
 *  
 */
@WebService
public interface AritmeticWebService {

	@WebMethod
	int sumaTest(int a, int b);
	
	@WebMethod
	String getCampannaActualProceso();
	
}
