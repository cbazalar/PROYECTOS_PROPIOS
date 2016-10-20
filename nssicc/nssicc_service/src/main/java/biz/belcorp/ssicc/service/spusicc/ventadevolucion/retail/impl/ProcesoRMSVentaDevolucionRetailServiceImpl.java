/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.ventadevolucion.retail.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ventadevolucion.retail.ProcesoRMSVentaDevolucionRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ventadevolucion.retail.ProcesoRMSVentaDevolucionRetailService;

/**
 * @author Richar Cruzado
 * @date   30/12/2015
 */
@Service("spusicc.procesoRMSVentaDevolucionRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRMSVentaDevolucionRetailServiceImpl extends BaseService 
    implements ProcesoRMSVentaDevolucionRetailService {
	 
	@Resource(name="spusicc.procesoRMSVentaDevolucionRetailDAO")
	ProcesoRMSVentaDevolucionRetailDAO procesoRMSVentaDevolucionRetailDAO;
	

	@Override
	public void insertaVentaDevolucion(Map criteria) {
		
		procesoRMSVentaDevolucionRetailDAO.insertaVentaDevolucion(criteria);
		
	}
	

}