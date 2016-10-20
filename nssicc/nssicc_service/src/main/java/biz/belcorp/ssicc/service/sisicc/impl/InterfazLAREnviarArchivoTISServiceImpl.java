package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnviarArchivoTISServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="}">Sergio Apaza</a>
 */
@Service("sisicc.interfazLAREnviarArchivoTISService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLAREnviarArchivoTISServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazLARDAO")
	InterfazLARDAO InterfazLARDAO;
	
	
	/**
	 * @param interfazLARDAO the interfazLARDAO to set
	 */
	public void setInterfazLARDAO(InterfazLARDAO interfazLARDAO) {
		InterfazLARDAO = interfazLARDAO;
	}

	/**
	 * Template Method que define el algoritmo basico para los Procesos de
	 * SiCC: Ejecuta el stored del Proceso.
	 * 
	 * @param parametros
	 *            de la Interfaz
	 * @return numero de registros procesados, que sera 0 por ser un Proceso
	 * @throws Exception 
	 */
	protected int processInterfaz(InterfazParams interfazParams)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'processInterfaz' method");
		
		int registrosProcesados = 0;
		Map criteria = new HashMap();
		criteria.put("codigoPais", interfazParams.getQueryParams().get("codigoPais"));
		criteria.put("codigoSistema", interfazParams.getQueryParams().get("codigoSistema"));
		criteria.put("codigoInterfaz", "LAR-35");
		criteria.put("numeroLote", interfazParams.getQueryParams().get("numeroLote"));
		
		if(InterfazLARDAO.existeHistoricoInterfazError(criteria)) { 
			log.info("Interfaz LAR-35 con Error, se volvera intentar ejecutar");
			registrosProcesados = super.processInterfaz(interfazParams);
			
		} else {
			log.info("Interfaz LAR-35 sin Error");
			
			Integer regProc = (Integer)interfazParams.getQueryParams().get("registrosProcesados");
			if (regProc!= null){
				registrosProcesados = regProc;
			}

			//actualizamos para que no genere LOG
			Interfaz interfaz = interfazParams.getInterfaz();
			interfaz.setFlagLogErrores(Constants.NO);
		}
		
		return registrosProcesados;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		InterfazLARDAO.executeInterfazLAREnviarArchivoTIS2(params);
	}
	
}
