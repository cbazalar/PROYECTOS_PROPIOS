package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAlarmasValoresCubicajeDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEAlarmasValoresCubicajeService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEAlarmasValoresCubicajeServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 * 
 */
@Service("spusicc.mantenimientoAPEAlarmasValoresCubicajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEAlarmasValoresCubicajeServiceImpl extends BaseService implements MantenimientoAPEAlarmasValoresCubicajeService{
	
	@Resource(name="spusicc.mantenimientoAPEAlarmasValoresCubicajeDAO")
	private MantenimientoAPEAlarmasValoresCubicajeDAO mantenimientoAPEAlarmasValoresCubicajeDAO;

	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAlarmasValoresCubicajeService#getAlarmasValoresCubicajeList(java.util.Map)
	 */
	public List getAlarmasValoresCubicajeList(Map criteria) {
		return mantenimientoAPEAlarmasValoresCubicajeDAO.getAlarmasValoresCubicajeList(criteria);
	}
	


}