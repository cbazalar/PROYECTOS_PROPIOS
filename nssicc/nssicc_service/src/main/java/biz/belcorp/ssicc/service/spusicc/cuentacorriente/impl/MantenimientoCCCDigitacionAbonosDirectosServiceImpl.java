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

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionAbonosDirectosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargoAbonoDirectoCabecera;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargoAbonoDirectoDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionAbonosDirectosService;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.mantenimientoCCCDigitacionAbonosDirectosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCDigitacionAbonosDirectosServiceImpl extends BaseService implements MantenimientoCCCDigitacionAbonosDirectosService {
	
	@Resource(name = "spusicc.mantenimientoCCCDigitacionAbonosDirectosDAO")
	MantenimientoCCCDigitacionAbonosDirectosDAO mantenimientoCCCDigitacionAbonosDirectosDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getOperacionesReclamos(java.util.Map)
	 */
	public List getTiposAbonosDirectosDigitables(){
		return mantenimientoCCCDigitacionAbonosDirectosDAO.getTiposAbonosDirectosDigitables();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#insertReclamoDigitado(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera, java.util.List)
	 */
	public void insertCargoAbonoDirectoDigitado(CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec, List detallesCargoAbonoDirecList) throws Exception  { 
		
		//Recorriendo los detalles
		try { 
		
			for (int i = 0; i < detallesCargoAbonoDirecList.size(); i++) {
				CargoAbonoDirectoDetalle cadDigitDetal = new CargoAbonoDirectoDetalle();
				cadDigitDetal = (CargoAbonoDirectoDetalle)detallesCargoAbonoDirecList.get(i);
				
				//Ejecutando los Detalles
				//if(!recDigitDetal.getCodigoVentaDevuelve().equals("")){
					cadDigitDetal.setNumeroLinea(i+1);
												
					Map params1 = BeanUtils.describe(cccCargoAbonoDirecCabec);
					Map params2 = BeanUtils.describe(cadDigitDetal);					
					
					params1.putAll(params2);									
							
					if (log.isDebugEnabled()) {
						log.debug(params1);
					}
					
					mantenimientoCCCDigitacionAbonosDirectosDAO.insertCargoAbonoDirectoDigitado(params1);				
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
		
	}			
}
