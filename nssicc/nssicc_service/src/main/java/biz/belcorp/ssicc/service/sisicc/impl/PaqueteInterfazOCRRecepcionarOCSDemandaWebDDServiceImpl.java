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

@Service("sisicc.paqueteInterfazOCRRecepcionarOCSDemandaWebDDService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazOCRRecepcionarOCSDemandaWebDDServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl
{
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	private ProcesoSTOExecutionService procesoSTOExecutionService;
	
	
}
