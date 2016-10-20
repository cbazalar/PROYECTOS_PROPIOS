package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEmitirAlarmaProductoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEEmitirAlarmaProductoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEEmitirAlarmaProductoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEEmitirAlarmaProductoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEEmitirAlarmaProductoServiceImpl extends BaseService implements MantenimientoAPEEmitirAlarmaProductoService{
	
	@Resource(name="spusicc.mantenimientoAPEEmitirAlarmaProductoDAO")
	private MantenimientoAPEEmitirAlarmaProductoDAO mantenimientoAPEEmitirAlarmaProductoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaProductoService#getEmitirAlarmaProductoList(java.util.Map)
	 */
	public List getEmitirAlarmaProductoList(Map criteria) {
		return mantenimientoAPEEmitirAlarmaProductoDAO.getEmitirAlarmaProductoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaProductoService#getMailAlarmaProductoList(java.util.Map)
	 */
	public List getMailAlarmaProductoList(Map criteria) {
		return mantenimientoAPEEmitirAlarmaProductoDAO.getMailAlarmaProductoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaProductoService#getOidPeriodoSiguiente(java.util.Map)
	 */
	public String getOidPeriodoSiguiente(Map criteria){
		return mantenimientoAPEEmitirAlarmaProductoDAO.getOidPeriodoSiguiente(criteria);
	}
}