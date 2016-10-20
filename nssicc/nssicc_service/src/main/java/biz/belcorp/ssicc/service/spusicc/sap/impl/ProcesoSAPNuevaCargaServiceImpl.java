package biz.belcorp.ssicc.service.spusicc.sap.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sap.ProcesoSAPNuevaCargaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sap.ProcesoSAPNuevaCargaService;

/**
* @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
**/
@Service("spusicc.procesoSAPNuevaCargaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSAPNuevaCargaServiceImpl extends BaseService implements ProcesoSAPNuevaCargaService {

	@Resource(name="spusicc.procesoSAPNuevaCargaDAO")
    private ProcesoSAPNuevaCargaDAO procesoSAPNuevaCargaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sap.service.ProcesoSAPNuevaCargaService#getCantidadRegistros(java.util.Map)
	 */
	public Base getCantidadRegistros(Map criteria){
		 return procesoSAPNuevaCargaDAO.getCantidadRegistros(criteria);
	 }
	 
	 
	 /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scdf.service.ProcesoSAPNuevaCargaService#executeUpdateRegistros(java.util.Map)
	 */
	public void executeUpdateRegistros(Map params){
		 procesoSAPNuevaCargaDAO.executeUpdateRegistros(params);
	 }
}