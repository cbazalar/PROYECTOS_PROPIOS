/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.consultacatalogos.retail.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.consultacatalogos.retail.ProcesoRMSConsultaCatalogosRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.consultacatalogos.retail.ProcesoRMSConsultaCatalogosRetailService;

/**
 * @author Richar Cruzado
 * @date   21/01/2016
 */
@Service("spusicc.procesoRMSConsultaCatalogosRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRMSConsultaCatalogosRetailServiceImpl extends BaseService 
	implements ProcesoRMSConsultaCatalogosRetailService {

	
	@Resource(name = "spusicc.procesoRMSConsultaCatalogosRetailDAO")
	ProcesoRMSConsultaCatalogosRetailDAO procesoRMSConsultaCatalogosRetailDAO;
	
	@Override
	public List getConsultaCatalogosRetail() {
		 
		return procesoRMSConsultaCatalogosRetailDAO.getConsultaCatalogosRetail();
	}

	
	
}
