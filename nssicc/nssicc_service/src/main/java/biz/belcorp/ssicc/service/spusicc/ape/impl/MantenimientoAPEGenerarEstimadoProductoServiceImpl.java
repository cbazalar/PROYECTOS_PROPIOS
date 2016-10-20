package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEGenerarEstimadoProductoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEGenerarEstimadoProductoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEGenerarEstimadoProductoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEGenerarEstimadoProductoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPEGenerarEstimadoProductoServiceImpl extends BaseService implements MantenimientoAPEGenerarEstimadoProductoService{

	@Resource(name="spusicc.mantenimientoAPEGenerarEstimadoProductoDAO")
 	private MantenimientoAPEGenerarEstimadoProductoDAO mantenimientoAPEGenerarEstimadoProductoDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEGenerarEstimadoProductoService#executeGenerarEstimadoProducto(java.util.Map)
	 */
	public void executeGenerarEstimadoProducto(Map criteria){
		mantenimientoAPEGenerarEstimadoProductoDAO.executeGenerarEstimadoProducto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEGenerarEstimadoProductoService#executeGenerarOlas(java.util.Map)
	 */
	public void executeGenerarOlas(Map criteria){
		mantenimientoAPEGenerarEstimadoProductoDAO.executeGenerarOlas(criteria);
	}
 }