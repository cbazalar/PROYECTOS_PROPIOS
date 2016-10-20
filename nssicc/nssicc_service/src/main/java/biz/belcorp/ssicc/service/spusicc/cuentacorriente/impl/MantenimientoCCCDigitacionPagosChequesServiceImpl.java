package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosChequesDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraChequesDigitados;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoChequeDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosChequesService;

/**
 * Service que controla la Consulta de Telecobro
 *  
 * <p>
 * <a href="MantenimientoCCCDigitacionPagosChequesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.mantenimientoCCCDigitacionPagosChequesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCDigitacionPagosChequesServiceImpl extends BaseService implements MantenimientoCCCDigitacionPagosChequesService {
	

	@Resource(name = "spusicc.mantenimientoCCCDigitacionPagosChequesDAO")
	MantenimientoCCCDigitacionPagosChequesDAO mantenimientoCCCDigitacionPagosChequesDAO;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDigitacionPagosChequesService#getBancosCheques(java.util.Map)
	 */
	public List getBancosCheques(Map criteria){
		return mantenimientoCCCDigitacionPagosChequesDAO.getBancosCheques(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDigitacionPagosChequesService#getSucursalesCheques(java.util.Map)
	 */
	public List getSucursalesCheques(Map criteria){
		return mantenimientoCCCDigitacionPagosChequesDAO.getSucursalesCheques(criteria);
	}
	
	public void generarPagoCheque( Map criteria,List detallesPagosChequesList) throws Exception { 
		
		try { 
																		
			for (int i = 0; i < detallesPagosChequesList.size(); i++) {
				
				EstructuraChequesDigitados estructura = new EstructuraChequesDigitados();
												
				PagoChequeDetalle cccPagoChequeDetalle = (PagoChequeDetalle)detallesPagosChequesList.get(i);
				
				estructura.setCodigoBanco(cccPagoChequeDetalle.getBanco());				
				estructura.setCodigoConsultora(cccPagoChequeDetalle.getCodigoConsultora());				
				estructura.setFechaCobro(cccPagoChequeDetalle.getFechaCobro());
				
				double importeCheque = Double.parseDouble(cccPagoChequeDetalle.getImportePago());
				
				estructura.setImporteCheque(importeCheque); 
				
				estructura.setNumeroLote((String)(criteria.get("numeroLote")));												
				estructura.setCodigoUsuario((String)(criteria.get("codigoUsuario")));
																																		
					mantenimientoCCCDigitacionPagosChequesDAO.generarPagoCheque(estructura);				
			}
			
			mantenimientoCCCDigitacionPagosChequesDAO.generarLoteBancarioCheque(criteria);
			
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
		
	}			
}
