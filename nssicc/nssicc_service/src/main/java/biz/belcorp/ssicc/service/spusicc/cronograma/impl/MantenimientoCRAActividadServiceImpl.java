package biz.belcorp.ssicc.service.spusicc.cronograma.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAActividadDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAActividadService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRAActividadServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Service("spusicc.mantenimientoCRAActividadService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCRAActividadServiceImpl extends BaseService implements MantenimientoCRAActividadService{

	@Resource(name="spusicc.mantenimientoCRAActividadDAO")
	private MantenimientoCRAActividadDAO mantenimientoCRAActividadDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAActividadService#getActividades(java.util.Map)
	 */
	public List getActividades(Map criteria){
		return mantenimientoCRAActividadDAO.getActividades(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAActividadService#deleteActividad(java.util.Map)
	 */
	public void deleteActividad(Map criteria){
		mantenimientoCRAActividadDAO.deleteActividad(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAActividadService#getClaseActividades(java.util.Map)
	 */
	public List getClaseActividades(Map criteria){
		return mantenimientoCRAActividadDAO.getClaseActividades(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAActividadService#saveActividad(java.util.Map)
	 */
	public void insertActividad(Map criteria){
		mantenimientoCRAActividadDAO.insertActividad(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAActividadService#updateActividad(java.util.Map)
	 */
	public void updateActividad(Map criteria){
		mantenimientoCRAActividadDAO.updateActividad(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAActividadService#getActividadesExcepto(java.util.Map)
	 */
	public List getActividadesExcepto(Map criteria){
		return mantenimientoCRAActividadDAO.getActividadesExcepto(criteria);
	}
}
