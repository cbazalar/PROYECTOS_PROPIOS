package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEGeneracionMapaCentroDistribucionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEGeneracionMapaCentroDistribucionService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEGeneracionMapaCentroDistribucionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEGeneracionMapaCentroDistribucionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPEGeneracionMapaCentroDistribucionServiceImpl extends BaseService implements MantenimientoAPEGeneracionMapaCentroDistribucionService{

	@Resource(name="spusicc.mantenimientoAPEGeneracionMapaCentroDistribucionDAO")
 	private MantenimientoAPEGeneracionMapaCentroDistribucionDAO mantenimientoAPEGeneracionMapaCentroDistribucionDAO;
 	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEGeneracionMapaCentroDistribucionService#getNextCodigoMapaCentroDistribucion()
	 */
	public String getNextCodigoMapaCentroDistribucion(){
		return mantenimientoAPEGeneracionMapaCentroDistribucionDAO.getNextCodigoMapaCentroDistribucion();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEGeneracionMapaCentroDistribucionService#executeGenerarMapaCentroDistribucion(java.util.Map)
	 */
	public void executeGenerarMapaCentroDistribucion(Map criteria){
		mantenimientoAPEGeneracionMapaCentroDistribucionDAO.executeGenerarMapaCentroDistribucion(criteria);
	}

 }