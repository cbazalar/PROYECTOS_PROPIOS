/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.precioproducto.retail.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.precioproducto.retail.ProcesoRMSPrecioProductoRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.precioproducto.retail.ProcesoRMSPrecioProductoRetailService;

/**
 * @author Richar Cruzado
 * @date   28/12/2015
 */
@Service("spusicc.procesoRMSPrecioProductoRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRMSPrecioProductoRetailServiceImpl extends BaseService 
	implements ProcesoRMSPrecioProductoRetailService {
	
	@Resource(name="spusicc.procesoRMSPrecioProductoRetailDAO")
	ProcesoRMSPrecioProductoRetailDAO procesoRMSPrecioProductoRetailDAO;

	@Override
	public List getPrecioProductoRetail(Map map) {
						
		return procesoRMSPrecioProductoRetailDAO.getPrecioProductoRetail(map);
		
	}
	

}
