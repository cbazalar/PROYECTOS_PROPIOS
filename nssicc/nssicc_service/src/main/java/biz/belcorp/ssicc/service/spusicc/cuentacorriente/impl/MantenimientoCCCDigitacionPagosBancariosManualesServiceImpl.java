/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosBancariosManualesDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioCabecera;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosBancariosManualesService;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.mantenimientoCCCDigitacionPagosBancariosManualesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCDigitacionPagosBancariosManualesServiceImpl extends BaseService implements MantenimientoCCCDigitacionPagosBancariosManualesService {
	
	@Resource(name = "spusicc.mantenimientoCCCDigitacionPagosBancariosManualesDAO")
	MantenimientoCCCDigitacionPagosBancariosManualesDAO mantenimientoCCCDigitacionPagosBancariosManualesDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDigitacionPagosBancariosManualesService#generarPagoBancarioManual(biz.belcorp.ssicc.spusicc.cuentacorriente.model.PagoBancarioCabecera, java.util.List)
	 */
	public void generarPagoBancarioManual(PagoBancarioCabecera cccPagoBancarioCabecera, List detallesPagosBancariosList) throws Exception { 
		
		//Recorriendo los detalles
		try { 
			
			Map params1 = BeanUtils.describe(cccPagoBancarioCabecera);
			
			for (int i = 0; i < detallesPagosBancariosList.size(); i++) {
				PagoBancarioDetalle cccPagoBancarioDetalle = new PagoBancarioDetalle();
				cccPagoBancarioDetalle = (PagoBancarioDetalle)detallesPagosBancariosList.get(i);
				
				//Ejecutando los Detalles
				//if(!recDigitDetal.getCodigoVentaDevuelve().equals("")){
				cccPagoBancarioDetalle.setNumeroLinea(i+1);
												
					
					Map params2 = BeanUtils.describe(cccPagoBancarioDetalle);					
					
					params1.putAll(params2);									
							
					if (log.isDebugEnabled()) {
						log.debug(params1);
					}
					
					mantenimientoCCCDigitacionPagosBancariosManualesDAO.generarPagoBancarioManual(params1);				
			}
			
			mantenimientoCCCDigitacionPagosBancariosManualesDAO.registrarLoteBancario(params1);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
		
	}			
}
