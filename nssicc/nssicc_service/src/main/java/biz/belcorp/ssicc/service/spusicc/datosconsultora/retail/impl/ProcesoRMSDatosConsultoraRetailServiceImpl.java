/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.datosconsultora.retail.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.datosconsultora.retail.ProcesoRMSDatosConsultoraRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.datosconsultora.retail.ProcesoRMSDatosConsultoraRetailService;

/**
 * @author Richar Cruzado
 * @date   23/12/2015
 */
@Service("spusicc.procesoRMSDatosConsultoraRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRMSDatosConsultoraRetailServiceImpl extends BaseService 
    implements ProcesoRMSDatosConsultoraRetailService {
	
	@Resource(name="spusicc.procesoRMSDatosConsultoraRetailDAO")
	ProcesoRMSDatosConsultoraRetailDAO procesoRMSDatosConsultoraRetailDAO;


	@Override
	public List getDatosConsultoraRetail(Map map) {

		return procesoRMSDatosConsultoraRetailDAO.getDatosConsultoraRetail(map);
		
	}


	@Override
	public void executeDatosConsultoraRetail(Map criteria) {
		
		procesoRMSDatosConsultoraRetailDAO.executeDatosConsultoraRetail(criteria);
		
	}
	

}