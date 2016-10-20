package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service para el envio de Tipos de Bloqueo de la Interfaz AVI.
 * 
 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 */
@Service("sisicc.InterfazAVIEnviarTipoBloqueoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAVIEnviarTipoBloqueoServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazAVIDAO")
	private InterfazAVIDAO interfazAVIDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.info("Entro a InterfazAVIEnviarTipoBloqueoServiceImpl - executeStoreProcedure(Map)");
		interfazAVIDAO.executeInterfazAVITiposBloqueo(params);
		log.info("Salio a InterfazAVIEnviarTipoBloqueoServiceImpl - executeStoreProcedure(Map)");
	}

	
}


