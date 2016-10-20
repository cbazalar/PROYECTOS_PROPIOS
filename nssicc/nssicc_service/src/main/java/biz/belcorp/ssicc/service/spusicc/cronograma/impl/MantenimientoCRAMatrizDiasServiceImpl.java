package biz.belcorp.ssicc.service.spusicc.cronograma.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAMatrizDiasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAMatrizDiasService;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("spusicc.cronograma.mantenimientoCRAMatrizDiasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCRAMatrizDiasServiceImpl extends BaseService implements MantenimientoCRAMatrizDiasService {
	
	@Resource(name="spusicc.cronograma.mantenimientoCRAMatrizDiasDAO")
	MantenimientoCRAMatrizDiasDAO mantenimientoCRAMatrizDiasDAO;

		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#getCargaMatrizDias(java.util.Map)
	 */
	public List getCargaMatrizDias(Map criteria){
		return mantenimientoCRAMatrizDiasDAO.getCargaMatrizDias(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#getMatrizDias(java.util.Map)
	 */
	public List getMatrizDias(Map criteria){
		return mantenimientoCRAMatrizDiasDAO.getMatrizDias(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#updateMatrizDias(java.util.Map)
	 */
	public void updateMatrizDias(Map criteria){
		mantenimientoCRAMatrizDiasDAO.updateMatrizDias(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#getMatrizDiasFueraPeriodo(java.util.Map)
	 */
	public List getMatrizDiasFueraPeriodo(Map criteria){
		return mantenimientoCRAMatrizDiasDAO.getMatrizDiasFueraPeriodo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#insertActividadMatrizDias(java.util.Map)
	 */
	public void insertActividadMatrizDias(List lista){
		for (int i = 0; i < lista.size(); i++) {
			Map criteria = (HashMap)lista.get(i);
			try {
				mantenimientoCRAMatrizDiasDAO.insertActividadMatrizDias(criteria);
			} catch (Exception e) {
				log.debug("ya existia "+criteria.toString());
			}
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#updateMatrizUpdateActividad(java.util.Map)
	 */
	public void updateMatrizUpdateActividad(Map criteria){
		mantenimientoCRAMatrizDiasDAO.updateMatrizUpdateActividad(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#updateMatrizDeleteActividad(java.util.Map)
	 */
	public void updateMatrizDeleteActividad(Map criteria){
		mantenimientoCRAMatrizDiasDAO.updateMatrizDeleteActividad(criteria);
	}
}