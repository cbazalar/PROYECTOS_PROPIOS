package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoService;

@Service("sisicc.paqueteInterfazIMPGeneracionDocumentosNumeroInternoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazFACEnviarReprocesoPendientesServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	@Resource(name="spisicc.interfazIMPGeneracionDocumentosNumeroInternoService")
	private InterfazIMPGeneracionDocumentosNumeroInternoService interfazService;
	

}
