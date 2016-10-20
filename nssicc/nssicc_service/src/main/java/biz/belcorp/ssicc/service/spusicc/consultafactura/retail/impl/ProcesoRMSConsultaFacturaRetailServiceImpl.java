/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.consultafactura.retail.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.consultafactura.retail.ProcesoRMSConsultaFacturaRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.consultafactura.retail.ProcesoRMSConsultaFacturaRetailService;

/**
 * @author Richar Cruzado
 * @date   29/12/2015
 */
@Service("spusicc.procesoRMSConsultaFacturaRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRMSConsultaFacturaRetailServiceImpl extends BaseService 
	implements ProcesoRMSConsultaFacturaRetailService {


	@Resource(name = "spusicc.procesoRMSConsultaFacturaRetailDAO")
	ProcesoRMSConsultaFacturaRetailDAO procesoRMSConsultaFacturaRetailDAO;
	
	@Override
	public List getConsultaFacturaRetail(Map map) {
		
		return procesoRMSConsultaFacturaRetailDAO.getConsultaFacturaRetail(map);
		
	}
	
}
