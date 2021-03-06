package biz.belcorp.ssicc.service.sisicc.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazDATRecepcionarClienteRepartoCatalogoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazDATRecepcionarClienteRepartoCatalogoServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl
{
	

}
