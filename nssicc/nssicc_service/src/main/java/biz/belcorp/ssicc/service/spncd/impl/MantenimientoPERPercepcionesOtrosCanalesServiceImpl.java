package biz.belcorp.ssicc.service.spncd.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPERPercepcionesOtrosCanalesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spncd.MantenimientoPERPercepcionesOtrosCanalesService;

@Service("spncd.mantenimientoPERPercepcionesOtrosCanalesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPERPercepcionesOtrosCanalesServiceImpl extends BaseService implements MantenimientoPERPercepcionesOtrosCanalesService {

	@Resource(name="spusicc.mantenimientoPERPercepcionesOtrosCanalesDAO")
	MantenimientoPERPercepcionesOtrosCanalesDAO mantenimientoDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	InterfazSiCCDAO interfazSiCCDAO;

	public List getConsolidadoPercepcionesAcumulado(Map criteria){
		return mantenimientoDAO.getConsolidadoPercepcionesAcumulado(criteria);
	}
	
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERPercepcionesOtrosCanalesService#getPercepcionesOtrosCanales(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado)
	 */
	public List getPercepcionesOtrosCanales(Map criteria) {
		return mantenimientoDAO.getPercepcionesOtrosCanales(criteria);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERPercepcionesOtrosCanalesService#getConsolidadoPercepcion(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado)
	 */
	public List getConsolidadoPercepcion(InterfazPERActualizarPercepcionesConsolidado consolidado) {
		return mantenimientoDAO.getConsolidadoPercepcion(consolidado);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERPercepcionesOtrosCanalesService#updatePercepcionesOtrosCanales(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePercepcionesOtrosCanales(InterfazPERActualizarPercepcionesConsolidado consolidado, Usuario usuario) {
		mantenimientoDAO.updatePercepcionesOtrosCanales(consolidado, usuario);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERPercepcionesOtrosCanalesService#removePercepcionesOtrosCanales(biz.belcorp.ssicc.sisicc.model.InterfazPERActualizarPercepcionesConsolidado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removePercepcionesOtrosCanales(InterfazPERActualizarPercepcionesConsolidado consolidado, Usuario usuario) {
		mantenimientoDAO.removePercepcionesOtrosCanales(consolidado, usuario);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERPercepcionesOtrosCanalesService#getNextCorrelativo(java.lang.String)
	 */
	public String getNextCorrelativo(String codigoPais) {
		return mantenimientoDAO.getNextCorrelativo(codigoPais);
	}

	public void insertInterfazPERActualizarPercepcionesConsolidado(InterfazPERActualizarPercepcionesConsolidado consolidado, Usuario usuario) {
		interfazSiCCDAO.insertInterfazPERActualizarPercepcionesConsolidado(consolidado, usuario);
	}
}
