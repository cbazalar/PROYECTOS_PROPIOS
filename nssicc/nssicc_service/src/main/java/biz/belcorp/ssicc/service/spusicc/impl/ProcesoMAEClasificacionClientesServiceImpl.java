/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ProcesoMAEClasificacionClientesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoMAEClasificacionClientesService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoMAEClasificacionClientesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoMAEClasificacionClientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEClasificacionClientesServiceImpl extends BaseService implements ProcesoMAEClasificacionClientesService {
	
	@Resource(name="spusicc.procesoMAEClasificacionClientesDAO")
	ProcesoMAEClasificacionClientesDAO procesoMAEClasificacionClientesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoMAEClasificacionClientesService#executeProcesarClasificacion(java.util.Map)
	 */
	public void executeProcesarClasificacion(Map criteria) {
		procesoMAEClasificacionClientesDAO.executeProcesarClasificacion(criteria);
		
	}

	/**
	 * @param criteria
	 */
	public void executeProcesarClasificacionLove(Map criteria) {
		procesoMAEClasificacionClientesDAO.executeProcesarClasificacionLove(criteria);
		
	}

	public void setProcesoMAEClasificacionClientesDAO(
			ProcesoMAEClasificacionClientesDAO procesoMAEClasificacionClientesDAO) {
		this.procesoMAEClasificacionClientesDAO = procesoMAEClasificacionClientesDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoMAEClasificacionClientesService#executeActualizarDatosClientes(java.util.Map)
	 */
	public void executeActualizarDatosClientes(Map criteria) {
		procesoMAEClasificacionClientesDAO.executeActualizarDatosClientes(criteria);
	}
	
}
