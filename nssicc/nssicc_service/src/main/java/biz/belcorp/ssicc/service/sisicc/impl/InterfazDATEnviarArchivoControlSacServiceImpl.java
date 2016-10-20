package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarArchivoControlSacServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author Sergio Apaza
 */
@Service("sisicc.interfazDATEnviarArchivoControlSacService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarArchivoControlSacServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) {
		interfazDATDAO.executeInterfazDATEnviarArchivoControl(params);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#prepareFileBeforeSend(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File)
	 */
	protected File prepareFileBeforeSend(InterfazParams interfazParams,
			File tempFile) {
		Integer registrosProcesados = (Integer)interfazParams.getQueryParams().get("QP_registrosProcesados");
		if(registrosProcesados == 0) {
			Interfaz interfaz = interfazParams.getInterfaz();
			interfaz.setFlagEnvioArchivo("0"); //Que no envie el archivo al directorio de salida
		}	
		
		return super.prepareFileBeforeSend(interfazParams, tempFile);
	}
	
}

