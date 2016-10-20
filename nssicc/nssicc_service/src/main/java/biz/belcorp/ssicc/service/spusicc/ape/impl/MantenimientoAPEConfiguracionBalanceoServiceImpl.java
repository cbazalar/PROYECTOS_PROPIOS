package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionBalanceoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionBalanceo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionBalanceoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEConfiguracionBalanceoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicolas Lopez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEConfiguracionBalanceoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEConfiguracionBalanceoServiceImpl extends BaseService implements MantenimientoAPEConfiguracionBalanceoService {

	@Resource(name="spusicc.mantenimientoAPEConfiguracionBalanceoDAO")
	private MantenimientoAPEConfiguracionBalanceoDAO mantenimientoAPEConfiguracionBalanceoDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#getConfiguracionBalanceoList(java.util.Map)
	 */
	public List getConfiguracionBalanceoList(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoDAO.getConfiguracionBalanceoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#getFuncionDistribucionList(java.util.Map)
	 */
	public List getFuncionDistribucionList(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoDAO.getFuncionDistribucionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#getOidFuncionDist(java.util.Map)
	 */
	public String getOidFuncionDist(Map criteria) {
		return (String)this.mantenimientoAPEConfiguracionBalanceoDAO.getOidFuncionDist(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#insertarConfiguracionBalanceo(java.util.Map)
	 */
	public void insertarConfiguracionBalanceo(Map criteria) {
		this.mantenimientoAPEConfiguracionBalanceoDAO.insertarConfiguracionBalanceo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#getValidaExisteConfiguracionBalanceo(java.util.Map)
	 */
	public String getValidaExisteConfiguracionBalanceo(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoDAO.getValidaExisteConfiguracionBalanceo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#getConfiguracionBalanceoObject(java.util.Map)
	 */
	public ConfiguracionBalanceo getConfiguracionBalanceoObject(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoDAO.getConfiguracionBalanceoObject(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#eliminarConfiguracionBalanceo(java.util.Map, java.lang.String[])
	 */
	public void eliminarConfiguracionBalanceo(Map criteria, String[] items) {
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			criteria.put("oidLinArmado", id);
			mantenimientoAPEConfiguracionBalanceoDAO.eliminarConfiguracionBalanceo(criteria);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionBalanceoService#getOidLineaArmadoxOidCD(java.util.Map)
	 */
	public String getOidLineaArmadoxOidCD(Map criteria) {
		return this.mantenimientoAPEConfiguracionBalanceoDAO.getOidLineaArmadoxOidCD(criteria);
	}  

}
