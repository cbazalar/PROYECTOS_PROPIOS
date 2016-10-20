package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMEnviarCargaMasivaEjecutivaNuevaServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a>efernandezo</a>
 */
@Service("sisicc.interfazCOMEnviarCargaMasivaEjecutivaNuevaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOMEnviarCargaMasivaEjecutivaNuevaServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {

	/**
	 * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
	 * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
	 * la interfaz
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazCOMEnviarCargaMasivaEjecutivaNueva(params);
	}

}
