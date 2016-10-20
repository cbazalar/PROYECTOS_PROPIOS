package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEControlBalanceoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEControlBalanceoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEControlBalanceoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEControlBalanceoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEControlBalanceoServiceImpl extends BaseService implements MantenimientoAPEControlBalanceoService{
	
	@Resource(name="spusicc.mantenimientoAPEControlBalanceoDAO")
	private MantenimientoAPEControlBalanceoDAO mantenimientoAPEControlBalanceoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlBalanceoService#getControlBalanceoList(java.util.Map)
	 */
	public List getControlBalanceoList(Map criteria) {
		return mantenimientoAPEControlBalanceoDAO.getControlBalanceoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlBalanceoService#getOidAsignacionProductoAnaquel(java.util.Map)
	 */
	public String getOidAsignacionProductoAnaquel(Map criteria){
		return mantenimientoAPEControlBalanceoDAO.getOidAsignacionProductoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlBalanceoService#getDescMapaZonaCabec(java.util.Map)
	 */
	public String getDescMapaZonaCabec(Map criteria){
		return mantenimientoAPEControlBalanceoDAO.getDescMapaZonaCabec(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlBalanceoService#getDescMapaCentroCabec(java.util.Map)
	 */
	public String getDescMapaCentroCabec(Map criteria){
		return mantenimientoAPEControlBalanceoDAO.getDescMapaCentroCabec(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlBalanceoService#executeBalanceoLinea(java.util.Map)
	 */
	public void executeBalanceoLinea(Map criteria){
		mantenimientoAPEControlBalanceoDAO.executeBalanceoLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEControlBalanceoService#validaBalanceoLinea(java.util.Map)
	 */
	public int validaBalanceoLinea(Map criteria){
		return mantenimientoAPEControlBalanceoDAO.validaBalanceoLinea(criteria);
	}
}