package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.scdf.InterfazPrivilegeService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazPRIRecepcionarArchivosPrivilegeService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PaqueteInterfazPRIRecepcionarArchivosPrivilegeServiceImpl extends
		BaseInterfazPaqueteAbstractServiceImpl {

	@Resource(name = "scdf.interfazPrivilegeService")
	InterfazPrivilegeService service;

	
}
