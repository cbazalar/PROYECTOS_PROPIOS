/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPERCargoAbonoDirectoIncobrableForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public interface ProcesoPERCargoAbonoDirectoIncobrableService extends Service {

	public void executeCargoAbonoDirecto(Map criteria)  ;
	
}