package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.InterfazCOBEnviarInformacionProveedoresCobranzaService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

/**
 * The Class PaqueteInterfazCOBEnviarInformacionProveedoresCobranzaServiceImpl.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/11/2014
 */
@Service("sisicc.paqueteInterfazCOBEnviarInformacionProveedoresCobranzaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazCOBEnviarInformacionProveedoresCobranzaServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	@Resource(name="sisicc.interfazCOBEnviarInformacionProveedoresCobranzaService")
	private InterfazCOBEnviarInformacionProveedoresCobranzaService interfazCOBEnviarInformacionProveedoresCobranzaService;

	

}
