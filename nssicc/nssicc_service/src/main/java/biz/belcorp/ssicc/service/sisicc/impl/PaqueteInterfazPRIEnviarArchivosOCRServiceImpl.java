package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.service.scdf.PremioService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazPRIEnviarArchivosOCRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazPRIEnviarArchivosOCRServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl
{
	
	@Resource(name="scdf.interfazSiCCService")
	private InterfazSiCCService siccService;
	
	@Resource(name="paisService")
	private PaisService paisService;
	
	@Resource(name="scdf.premioService")
	private PremioService premioService;
	
}
