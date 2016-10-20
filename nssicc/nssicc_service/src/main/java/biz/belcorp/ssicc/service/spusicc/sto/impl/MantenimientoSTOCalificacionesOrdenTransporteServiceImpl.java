package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOCalificacionesOrdenTransporteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOCalificacionesOrdenTransporteService;

/**
 * @author peextdoliva
 */

@Service("spusicc.mantenimientoSTOCalificacionesOrdenTransporteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOCalificacionesOrdenTransporteServiceImpl extends BaseService implements MantenimientoSTOCalificacionesOrdenTransporteService{
	
	@Resource(name="spusicc.mantenimientoSTOCalificacionesOrdenTransporteDAO")
	private MantenimientoSTOCalificacionesOrdenTransporteDAO mantenimientoSTOCalificacionesOrdenTransporteDAO;	
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOCalificacionesOrdenTransporteService#getCalificacionesOrdenTransporte(java.util.Map)
	 */
	public List getCalificacionesOrdenTransporte(Map criteria){
		return mantenimientoSTOCalificacionesOrdenTransporteDAO.getCalificacionesOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOCalificacionesOrdenTransporteService#deleteCalificacionesOrdenTransporte(java.lang.String[])
	 */
	public void deleteCalificacionesOrdenTransporte(String[] items){		
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			String id = items[i];			
			criteria.put("codigoCalificacion", id);			
			mantenimientoSTOCalificacionesOrdenTransporteDAO.deleteCalificacionesOrdenTransporte(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOCalificacionesOrdenTransporteService#insertCalificacionesOrdenTransporte(java.util.Map)
	 */
	public void insertCalificacionesOrdenTransporte(Map criteria){
		mantenimientoSTOCalificacionesOrdenTransporteDAO.insertCalificacionesOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOCalificacionesOrdenTransporteService#updateCalificacionesOrdenTransporte(java.util.Map)
	 */
	public void updateCalificacionesOrdenTransporte(Map criteria){
		mantenimientoSTOCalificacionesOrdenTransporteDAO.updateCalificacionesOrdenTransporte(criteria);
	}
}