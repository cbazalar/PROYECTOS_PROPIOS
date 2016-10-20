package biz.belcorp.ssicc.service.sisicc.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteProcesoGENProcesarCierreRegion")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteProcesoGENProcesarCierreRegionServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	@Resource(name="sisicc.interfazDATService")
	private InterfazDATService interfazDATService;



}
