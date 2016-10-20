package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONRegionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONRegionService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.mantenimientoZONRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONRegionServiceImpl extends BaseService implements MantenimientoZONRegionService{
	
	@Resource(name="spusicc.mantenimientoZONRegionDAO")
	private MantenimientoZONRegionDAO mantenimientoZONRegionDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#getRegionesList(java.util.Map)
	 */
	public List getRegionesList(Map criteria) {
		return mantenimientoZONRegionDAO.getRegionesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#deleteRegiones(java.lang.String[], java.lang.String, java.lang.String)
	 */
	public void deleteRegiones(String oidRegion, String codigoUsuario,String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", codigoUsuario);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("oidRegion",oidRegion);
		
		mantenimientoZONRegionDAO.deleteRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#getZonasIndActivasByOidRegion(java.lang.Integer)
	 */
	public List getZonasIndActivasByOidRegion(Integer oidRegion) {
		return mantenimientoZONRegionDAO.getZonasIndActivasByOidRegion(oidRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#getRegion(java.lang.Integer)
	 */
	public Map getRegion(Integer oidRegion) {
		return mantenimientoZONRegionDAO.getRegion(oidRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#getEncuentraRegionByCodigoRegion(java.lang.String)
	 */
	public Integer getEncuentraRegionByCodigoRegion(String codigoRegionRegistro) {
		return mantenimientoZONRegionDAO.getEncuentraRegionByCodigoRegion(codigoRegionRegistro);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#insertRegion(java.util.Map)
	 */
	public void insertRegion(Map params) {
		mantenimientoZONRegionDAO.insertRegion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#updateRegion(java.util.Map)
	 */
	public void updateRegion(Map params) {
		mantenimientoZONRegionDAO.updateRegion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONRegionService#deleteRegionFisicamente(java.lang.Integer)
	 */
	public void deleteRegionFisicamente(Integer oidRegion) {
		mantenimientoZONRegionDAO.deleteRegionFisicamente(oidRegion);
	}
}