package biz.belcorp.ssicc.service.spusicc.car.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.car.MantenimientoCARNivelRiesgoSeccionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.car.MantenimientoCARNivelRiesgoSeccionService;

/**
 * Service que executa las metodos de Mantenimiento de Nivel de Riesgo x Seccion
 *  
 * <p>
 * <a href="MantenimientoCARNivelRiesgoSeccionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */ 
@Service("spusicc.mantenimientoCARNivelRiesgoSeccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCARNivelRiesgoSeccionServiceImpl extends BaseService implements 
		MantenimientoCARNivelRiesgoSeccionService {
	
	@Resource(name="spusicc.mantenimientoCARNivelRiesgoSeccionDAO")
	MantenimientoCARNivelRiesgoSeccionDAO mantenimientoCARNivelRiesgoSeccionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.service.MantenimientoCARNivelRiesgoSeccionService#getNivelRiesgoSeccionList(java.util.Map)
	 */
	public List getNivelRiesgoSeccionList(Map criteria) {
		return mantenimientoCARNivelRiesgoSeccionDAO.getNivelRiesgoSeccionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.service.MantenimientoCARNivelRiesgoSeccionService#getNivelesRiesgos(java.util.Map)
	 */
	public List getNivelesRiesgos(Map criteria) {
		return mantenimientoCARNivelRiesgoSeccionDAO.getNivelesRiesgos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.service.MantenimientoCARNivelRiesgoSeccionService#updateNivelRiesgoSeccion(java.util.Map)
	 */
	public void updateNivelRiesgoSeccion(Map params) {
		boolean encontrado = mantenimientoCARNivelRiesgoSeccionDAO.existeNivelRiesgoSeccion(params);
		
		if(encontrado)
			mantenimientoCARNivelRiesgoSeccionDAO.updateNivelRiesgoSeccion(params);
		else
			mantenimientoCARNivelRiesgoSeccionDAO.insertNivelRiesgoSeccion(params);
	}	
	
	

}
