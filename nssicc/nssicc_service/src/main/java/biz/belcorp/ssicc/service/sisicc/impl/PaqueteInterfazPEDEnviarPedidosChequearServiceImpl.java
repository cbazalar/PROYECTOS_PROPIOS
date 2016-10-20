package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.InterfazLARService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;


/**
 * The Class PaqueteInterfazPEDEnviarPedidosChequearServiceImpl.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/12/2014
 */
@Service("sisicc.paqueteInterfazPEDEnviarPedidosChequearService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazPEDEnviarPedidosChequearServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	@Resource(name="sisicc.interfazLARService")
	private InterfazLARService interfazLARService;
	

	

}
