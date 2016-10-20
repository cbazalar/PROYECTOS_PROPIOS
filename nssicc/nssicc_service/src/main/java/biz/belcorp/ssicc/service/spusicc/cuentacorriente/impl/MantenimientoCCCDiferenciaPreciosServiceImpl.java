/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDiferenciaPreciosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargosyAbonos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDiferenciaPreciosService;

/**
 * @author Cristhian Roman
 *
 */

public class MantenimientoCCCDiferenciaPreciosServiceImpl extends BaseService implements MantenimientoCCCDiferenciaPreciosService {
	

	MantenimientoCCCDiferenciaPreciosDAO mantenimientoCCCDiferenciaPreciosDAO;

	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDiferenciaPreciosService#getCargosAbonosList(java.util.Map)
	 */
	public List getCargosAbonosList(Map criteria){
	   return mantenimientoCCCDiferenciaPreciosDAO.getCargosAbonosList(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDiferenciaPreciosService#executeProcesoCargosAbonos(java.util.List, java.util.Map)
	 */
	public void executeProcesoCargosAbonos(List lista, Map criteria){
		String ejecutoProceso = Constants.NO;
		if (lista != null) {
            Iterator i = lista.iterator();
            while (i.hasNext()) {
            	CargosyAbonos cargoAbono = (CargosyAbonos) i.next();
            	Integer totalError = cargoAbono.getTotalError();
            	if (totalError.intValue() == 0) {
            		ejecutoProceso = Constants.SI;
            		criteria.put("codigoCliente", cargoAbono.getCodigoCliente());
            		mantenimientoCCCDiferenciaPreciosDAO.executeProcesoCargosAbonos(criteria);
             	}         	
            }
        }
		criteria.put("ejecutoProceso", ejecutoProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCDiferenciaPreciosService#executeGenerarDataCargosAbonos(java.util.Map)
	 */
	public void executeGenerarDataCargosAbonos(Map criteria) {
		mantenimientoCCCDiferenciaPreciosDAO.executeGenerarDataCargosAbonos(criteria);
	}
}