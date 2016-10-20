package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.InterfazBaseCompuestaService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BaseInterfazHiloAbstractService;

/**
 * Service por default de la recepcion Compuesta es generico.
 * 
 * @author <a href="sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazBaseCompuestaServiceImpl implements InterfazBaseCompuestaService {
		
	private Map interfazImplementations;

	/**
	 * @return the interfazImplementations
	 */
	public Map getInterfazImplementations() {
		return interfazImplementations;
	}

	/**
	 * @param interfazImplementations the interfazImplementations to set
	 */
	public void setInterfazImplementations(Map interfazImplementations) {
		this.interfazImplementations = interfazImplementations;
	}	
	
	public SSiCC_Desfasado_BaseInterfazHiloAbstractService getInterfazImplementation(String codigo) {
		return (SSiCC_Desfasado_BaseInterfazHiloAbstractService) interfazImplementations.get(codigo);
	}
}