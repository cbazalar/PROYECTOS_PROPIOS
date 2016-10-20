package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazDATEnviarArchivosEducacionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PaqueteInterfazDATEnviarArchivosEducacionServiceImpl extends
		BaseInterfazPaqueteAbstractServiceImpl {

	@Resource(name = "edu.mantenimientoEDUGenericoService")
	private MantenimientoEDUGenericoService service;

	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {
		super.afterExecuteInterfaz(params);
		if (log.isDebugEnabled()) {
			log.debug("Entering 'afterExecuteInterfaz' method");
			log.debug(params);
		}
		if (interfazExecutionResult.ejecucionCompletada()) {
			String tipoEnvio = (String) params.get("tipoEnvio");
			if (Constants.EDU_TIPO_ENVIO_NORMAL.equals(tipoEnvio)) {
				service.updateNroLoteInterfazDatamart(params);
			}
		}
		log.debug("Fin 'afterExecuteInterfaz' method");

	}
}
