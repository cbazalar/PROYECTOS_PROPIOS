package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.InterfazIVRService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazIVREnviarNovedadIVRCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazIVREnviarNovedadIVRCorporativoServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl{
	
	@Resource(name="sisicc.interfazIVRService")
	private InterfazIVRService service;
	
	

}
