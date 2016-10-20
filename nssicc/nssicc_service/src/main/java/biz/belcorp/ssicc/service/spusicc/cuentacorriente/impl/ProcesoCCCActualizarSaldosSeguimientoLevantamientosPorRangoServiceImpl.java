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

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService;

/**
 * @author Diego Torres
 *
 */
@Service("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceImpl extends BaseService implements ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService {
	
	@Resource(name = "spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO")
	ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService#executeProcesarSaldosSeguimientoLevantamientosPorRango(java.util.Map)
	 */
	public void executeProcesarSaldosSeguimientoLevantamientosPorRango(Map datos){		
		procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO.executeProcesarSaldosSeguimientoLevantamientosPorRango(datos);
	}


	public List getRangoPeriodos(Map criteria) {
		return procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO.getRangoPeriodos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoService#executeProcesarSaldosActual(java.util.Map)
	 */
	public void executeProcesarSaldosActual(Map criteria) {
		procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO.executeProcesarSaldosActual(criteria);
	}
}
