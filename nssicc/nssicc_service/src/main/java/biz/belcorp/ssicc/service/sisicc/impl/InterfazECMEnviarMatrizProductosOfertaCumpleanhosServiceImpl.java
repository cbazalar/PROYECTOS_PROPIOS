package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazECMDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio Faltantes Anunciados
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Service("sisicc.interfazECMEnviarMatrizProductosOfertaCumpleanhosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazECMEnviarMatrizProductosOfertaCumpleanhosServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazECMDAO")
	InterfazECMDAO interfazECMDAO;
	
	
	protected void executeStoreProcedure(Map params) {
		interfazECMDAO
				.executeInterfazECMEnviarMatrizProductosOfertaCumpleanhos(params);
	}
}