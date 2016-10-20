package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;


/**
 * The Class PaqueteInterfazIVRRecepcionarPedidosServiceImpl.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/12/2014
 */
@Service("sisicc.paqueteInterfazIVRRecepcionarPedidosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazIVRRecepcionarPedidosServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	private ProcesoSTOExecutionService procesoSTOExecutionService;

	

}
