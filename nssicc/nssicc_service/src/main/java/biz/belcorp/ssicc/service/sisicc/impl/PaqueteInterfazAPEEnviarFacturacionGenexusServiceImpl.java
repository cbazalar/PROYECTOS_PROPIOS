package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPEImportarOrdenAnaquelesService;

@Service("sisicc.paqueteInterfazAPEEnviarFacturacionGenexusService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazAPEEnviarFacturacionGenexusServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl{

	@Resource(name="spusicc.procesoAPEImportarOrdenAnaquelesService")
	private ProcesoAPEImportarOrdenAnaquelesService  procesoAPEImportarOrdenAnaquelesService;
	
	@Override
	protected void afterExecuteInterfaz (Map params) throws Exception{
		
		super.afterExecuteInterfaz(params);
		if (log.isDebugEnabled()) {
			log.debug("Entering 'afterExecuteInterfaz' method");
			log.debug(params);
		}
		
		if (interfazExecutionResult.ejecucionCompletada()) {
			procesoAPEImportarOrdenAnaquelesService.actualizarIndicador(params);
		}
		log.debug("Fin 'afterExecuteInterfaz' method");
		
	}
}


