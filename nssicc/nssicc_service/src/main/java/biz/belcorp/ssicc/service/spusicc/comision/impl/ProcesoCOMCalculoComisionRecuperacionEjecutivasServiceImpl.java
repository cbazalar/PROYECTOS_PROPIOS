package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasService;

@Service("spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOMCalculoComisionRecuperacionEjecutivasServiceImpl extends BaseService
		implements ProcesoCOMCalculoComisionRecuperacionEjecutivasService {
	
	@Resource(name="spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasDAO")
	private ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO procesoCOMCalculoComisionRecuperacionEjecutivasDAO;
	
	public void executeCalculoComisionRecuperacionEjecutivas(Map params) {
		procesoCOMCalculoComisionRecuperacionEjecutivasDAO.executeCalculoComisionRecuperacionEjecutivas(params);
		
	}
	public Integer getCalculoComisionRecuperacionEjecutivasList(Map params) {
		
		return procesoCOMCalculoComisionRecuperacionEjecutivasDAO.getCalculoComisionRecuperacionEjecutivasCount(params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoComisionRecuperacionEjecutivasService#executeComisionRecuperacionPerdidas(java.util.Map)
	 */
	public void executeComisionRecuperacionPerdidas(Map map) {
		procesoCOMCalculoComisionRecuperacionEjecutivasDAO
							.executeComisionRecuperacionPerdidas(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoComisionRecuperacionEjecutivasService#getComisionRecuperacionPerdidas(java.util.Map)
	 */
	public List getComisionRecuperacionPerdidas(Map params) {
		return procesoCOMCalculoComisionRecuperacionEjecutivasDAO
					.getComisionRecuperacionPerdidas(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoComisionRecuperacionEjecutivasService#getComisionRecuperacionPerdidasRegion(java.util.Map)
	 */
	public List getComisionRecuperacionPerdidasRegion(Map params) {
		return procesoCOMCalculoComisionRecuperacionEjecutivasDAO.getComisionRecuperacionPerdidasRegion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoComisionRecuperacionEjecutivasService#executeActualizacionEstatusEjecutivas(java.util.Map)
	 */
	public void executeActualizacionEstatusEjecutivas(Map params) {
		procesoCOMCalculoComisionRecuperacionEjecutivasDAO.executeActualizacionEstatusEjecutivas(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoComisionRecuperacionEjecutivasService#executeEliminarComisionRecuperacion(java.util.Map)
	 */
	public void executeEliminarComisionRecuperacion(Map params) {
		procesoCOMCalculoComisionRecuperacionEjecutivasDAO.executeEliminarComisionRecuperacion(params);
	}
}