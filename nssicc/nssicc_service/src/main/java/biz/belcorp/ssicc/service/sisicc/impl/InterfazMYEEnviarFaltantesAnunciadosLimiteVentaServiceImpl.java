package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio Faltantes Anunciados
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 */
@Service("sisicc.interfazMYEEnviarFaltantesAnunciadosLimiteVentaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarFaltantesAnunciadosLimiteVentaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazMYEDAO")
	InterfazMYEDAO interfazMYEDAO;
	
	
	protected void executeStoreProcedure(Map params) {
		interfazMYEDAO
				.executeInterfazMYEEnviarFaltantesAnunciadosLimiteVenta(params);
	}
}