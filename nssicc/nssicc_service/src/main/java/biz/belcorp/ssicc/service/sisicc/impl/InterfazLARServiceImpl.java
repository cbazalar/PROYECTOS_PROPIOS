/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazLARService;


/**
 * 
 * <p>
 * <a href="InterfazLARServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("sisicc.interfazLARService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARServiceImpl extends BaseService implements InterfazLARService {

	@Resource(name="sisicc.interfazLARDAO")
	private InterfazLARDAO interfazLARDAO;

	
	public void executeGenerarPedidosChequear(Map params){
		interfazLARDAO.executeGenerarPedidosChequear(params);
	}
	
	public void executeActualizarPedidosChequear(Map params){
		interfazLARDAO.executeActualizarPedidosChequear(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazLARService#updateLARDocumentosCabeceraComplemento(java.util.Map)
	 */
	public void updateLARDocumentosCabeceraComplemento(Map params){
		interfazLARDAO.updateLARDocumentosCabeceraComplemento(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazLARService#deleteInterfazLAR8Parametros()
	 */
	public void deleteInterfazLAR8Parametros() {
		interfazLARDAO.deleteInterfazLAR8Parametros();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazLARService#insertInterfazLAR8Parametros(java.util.Map)
	 */
	public void insertInterfazLAR8Parametros(Map params) {
		interfazLARDAO.insertInterfazLAR8Parametros(params);		
	}
	
}