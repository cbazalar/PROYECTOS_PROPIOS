/*
 * Created on 03/04/2008 02:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSATEnviarVolumenesValorizacionPorSeccionDiaImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSATEnviarVolumenesValorizacionPorSeccionDiaImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Facturacion Macias </a>
 */
@Service("sisicc.interfazSATEnviarAFPEstibadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSATEnviarAFPEstibadoServiceImpl extends
BaseInterfazSalidaStoredProcedureAbstractService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.getInterfazSATEnviarAFPEstibados(params);
	}


}
