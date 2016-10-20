/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.descuento.retail.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.descuento.retail.ProcesoRMSEscalaDescuentosRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.descuento.retail.ProcesoRMSEscalaDescuentosRetailService;

/**
 * @author Richar Cruzado
 *
 */
@Service("spusicc.procesoRMSEscalaDescuentosRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRMSEscalaDescuentosRetailServiceImpl extends BaseService 
    implements ProcesoRMSEscalaDescuentosRetailService {
	
	@Resource(name="spusicc.procesoRMSEscalaDescuentosRetailDAO")
	ProcesoRMSEscalaDescuentosRetailDAO procesoRMSEscalaDescuentosRetailDAO;

	
	@Override
	public List getEscalaDescuentoRetail() {
		
		return procesoRMSEscalaDescuentosRetailDAO.getEscalaDescuentoRetail();
	}
	

}