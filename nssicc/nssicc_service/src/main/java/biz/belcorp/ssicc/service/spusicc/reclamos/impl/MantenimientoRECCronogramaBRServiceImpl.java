/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECCronogramaBRDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CronogramaBR;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECCronogramaBRService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="MantenimientoRECCronogramaBRServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */
@Service("spusicc.mantenimientoRECCronogramaBRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECCronogramaBRServiceImpl extends BaseService implements MantenimientoRECCronogramaBRService {
	
	@Resource(name="spusicc.mantenimientoRECCronogramaBRDAO")
	MantenimientoRECCronogramaBRDAO mantenimientoRECCronogramaBRDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCronogramaBRService#deleteCronogramaBR(java.util.Map)
	 */
	public void deleteCronogramaBR(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRServiceImpl - deleteCronogramaBR(java.util.Map)");
		mantenimientoRECCronogramaBRDAO.deleteCronogramaBR(map);
		log.info("Salio a MantenimientoRECCronogramaBRServiceImpl - deleteCronogramaBR(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCronogramaBRService#getCronogramaBRList(java.util.Map)
	 */
	public List getCronogramaBRList(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRServiceImpl - getCronogramaBRList(java.util.Map)");
		List lista  = mantenimientoRECCronogramaBRDAO.getCronogramaBRList(map);
		log.info("Salio a MantenimientoRECCronogramaBRServiceImpl - getCronogramaBRList(java.util.Map) - Resultado:" + lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCronogramaBRService#insertCronogramaBR(biz.belcorp.ssicc.spusicc.reclamos.model.CronogramaBR, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCronogramaBR(CronogramaBR cronograma, Usuario usuario) {
		mantenimientoRECCronogramaBRDAO.insertCronogramaBR(cronograma, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCronogramaBRService#updateCronogramaBR(biz.belcorp.ssicc.spusicc.reclamos.model.CronogramaBR, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCronogramaBR(CronogramaBR cronograma, Usuario usuario) {
		mantenimientoRECCronogramaBRDAO.updateCronogramaBR(cronograma, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCronogramaBRService#getCronogramaBRValidarPeriodoActivo(java.util.Map)
	 */
	public int getCronogramaBRValidarPeriodoActivo(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRServiceImpl - getCronogramaBRValidarPeriodoActivo(java.util.Map)");
		int resultado  = mantenimientoRECCronogramaBRDAO.getCronogramaBRValidarPeriodoActivo(map);
		log.info("Salio a MantenimientoRECCronogramaBRServiceImpl - getCronogramaBRValidarPeriodoActivo(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCronogramaBRService#getCronogramaBRPeriodoActivo(java.util.Map)
	 */
	public int getCronogramaBRPeriodoActivo(Map map){
		int resultado  = mantenimientoRECCronogramaBRDAO.getCronogramaBRPeriodoActivo(map);
		return resultado;
	}
}
