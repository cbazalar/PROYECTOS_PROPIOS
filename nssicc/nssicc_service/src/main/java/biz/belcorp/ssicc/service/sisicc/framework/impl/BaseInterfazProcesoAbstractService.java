package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/* POR REESTRUCTURAR NSSICC */
/**
 * Clase service abstracta para procesos que se requieren que simulen la ejecucion
 * de una interfaz en SiCC. Implementa el template method 'processInterfaz' 
 * para la ejecucion del Stored relacionado al proceso. Generalmente
 * los procesos requeriran solo de implementar el metodo 'readData' 
 * que contiene la lgica de negocio relacionado al proceso.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */

@Service("sisicc.baseInterfazProcesoAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseInterfazProcesoAbstractService extends BaseInterfazAbstractService {

	/**
	 * Template Method que define el algoritmo basico para los Procesos de
	 * SiCC: Ejecuta el stored del Proceso.
	 * 
	 * @param parametros
	 *            de la Interfaz
	 * @return numero de registros procesados, que sera 0 por ser un Proceso
	 */
	protected int processInterfaz(InterfazParams interfazParams)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'processInterfaz' method");
		
		int registrosProcesados = 0;
		
		Integer regProc = (Integer)interfazParams.getQueryParams().get("registrosProcesados");
		if (regProc!= null){
			registrosProcesados = regProc;
		}
		try {
			//actualizamos para que no genere LOG
			Interfaz interfaz = interfazParams.getInterfaz();
			interfaz.setFlagLogErrores(Constants.NO);
			
			executeStoreProcedure(interfazParams.getQueryParams());
			
		}   catch (Exception e) {
			log.error("Error al procesar la interfaz: " + e.getMessage());
			InterfazException interfazException = new InterfazException(e
					.getMessage());
			interfazException.setRegistrosProcesados(registrosProcesados);
			throw interfazException;
		}
		return registrosProcesados;
	}
	
	/**
	 * Sobrescribir este metodo y realizar la llamada al Stored Procedure
	 * correspondiente.
	 * 
	 * @param params
	 *            Map con parametros de la ejecucion del Stored Procedure
	 * @throws Exception 
	 */
	protected abstract void executeStoreProcedure(Map params) throws InterfazException, Exception;
	
}
