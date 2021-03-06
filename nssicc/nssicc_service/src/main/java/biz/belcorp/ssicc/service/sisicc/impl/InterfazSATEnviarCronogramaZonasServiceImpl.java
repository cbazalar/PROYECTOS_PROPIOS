/*
 * Created on 03/04/2008 02:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSATEnviarVolumenesValorizacionPorSeccionDiaImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSATEnviarCronogramaZonasImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Facturacion Macias </a>
 */
@Service("sisicc.interfazSATEnviarCronogramaZonasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSATEnviarCronogramaZonasServiceImpl extends
		BaseInterfazSalidaAbstractService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			System.out.println("Dentro del service "+queryParams.get("codigoPais"));
			list = this.interfazSiCCDAO.getInterfazSATEnviarCronogramaZonas(queryParams);
			System.out.println("Lista de parametros "+list.size());
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return list;
	}
}
