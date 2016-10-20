/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionExternaPagosBancariosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioExterno;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionExternaPagosBancariosService;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.mantenimientoCCCDigitacionExternaPagosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCDigitacionExternaPagosBancariosServiceImpl extends BaseService implements MantenimientoCCCDigitacionExternaPagosBancariosService {
	
	@Resource(name = "spusicc.mantenimientoCCCDigitacionExternaPagosBancariosDAO")
	MantenimientoCCCDigitacionExternaPagosBancariosDAO mantenimientoCCCDigitacionExternaPagosBancariosDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDigitacionExternaPagosBancariosService#generarPagoBancarioManual(biz.belcorp.ssicc.spusicc.cuentacorriente.model.PagoBancarioCabecera, java.util.List)
	 */
	public void generarPagoBancarioExterno(Map criteria, List detallesPagosBancariosList) throws Exception { 
		
		//Recorriendo los detalles
		
		if (log.isDebugEnabled()) {				
			log.debug("Parametros " +  criteria.toString());
		}
		
		try { 
									
			for (int i = 0; i < detallesPagosBancariosList.size(); i++) {
				
				PagoBancarioExterno pagoBancarioExternoDetalle = new PagoBancarioExterno();
				 			
				pagoBancarioExternoDetalle = (PagoBancarioExterno)detallesPagosBancariosList.get(i);
		   																									
				mantenimientoCCCDigitacionExternaPagosBancariosDAO.insertarPagoBancarioExterno(pagoBancarioExternoDetalle);				
			}
			
			mantenimientoCCCDigitacionExternaPagosBancariosDAO.generarLoteBancarioExterno(criteria);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
		
	}			
}
