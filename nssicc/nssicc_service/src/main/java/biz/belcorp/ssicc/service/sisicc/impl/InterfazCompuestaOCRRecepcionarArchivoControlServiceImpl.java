package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.impl.SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl;

/**
 * Service de la recepcion Compuesta OCR.
 * 
 * @author <a href="sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc.interfazCompuestaOCRRecepcionarArchivoControlService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCompuestaOCRRecepcionarArchivoControlServiceImpl extends SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl  {
	
	@Override
	public void beforeExecuteInterfaz(Map params) {
		
		
	}

	@Override
	public void afterExecuteInterfaz(Map params,
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) {		
		//actualizar el archivo de control
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		log.debug("afterExecuteInterfaz OCR ARCHIVO CONTROL");
		/*params.put("estadoPendiente",Constants.NUMERO_UNO);
		params.put("indPendiente",Constants.NUMERO_UNO);
		getInterfazService().updateEstadoArchivoControl(params);
		params.remove("indPendiente");*/
	}

	
	
}