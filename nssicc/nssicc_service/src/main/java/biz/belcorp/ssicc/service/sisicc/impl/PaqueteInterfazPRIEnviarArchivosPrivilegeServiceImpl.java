package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.service.scdf.StickerService;
import biz.belcorp.ssicc.service.scdf.UltimasNoticiasService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;


/**
 * The Class PaqueteInterfazPRIEnviarArchivosPrivilegeServiceImpl.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/12/2014
 */
@Service("sisicc.paqueteInterfazPRIEnviarArchivosPrivilegeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazPRIEnviarArchivosPrivilegeServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	@Resource(name="scdf.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;
	
	@Resource(name="paisService")
	private PaisService paisService;
	
	@Resource(name="scdf.stickerService")
	private StickerService stickerService;	
	
	@Resource(name="scdf.ultimasNoticiasService")
	private UltimasNoticiasService ultimasNoticiasService;	
	

	
}
