/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosService;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Service("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceImpl extends BaseService implements ProcesoCCCActualizarSaldosSeguimientoLevantamientosService {
	
	@Resource(name = "spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO")
	ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO procesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO;
	
	@Resource(name = "scsicc.reporteService")
	ReporteService reporteService;	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCActualizarSaldosSeguimientoLevantamientosService#executeProcesarSaldosSeguimientoLevantamientos(java.util.Map)
	 */
	public void executeProcesarSaldosSeguimientoLevantamientos(Map datos){		
		procesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO.executeProcesarSaldosSeguimientoLevantamientos(datos);
	}


}
