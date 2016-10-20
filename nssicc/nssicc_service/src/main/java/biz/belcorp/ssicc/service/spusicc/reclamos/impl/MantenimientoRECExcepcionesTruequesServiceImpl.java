package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECExcepcionesTruequesDAO;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECExcepcionesTruequesService;

/**
 * <p>
 * <a href="MantenimientoRECExcepcionesTruequesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 * 
 */
@Service("spusicc.mantenimientoRECExcepcionesTruequesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECExcepcionesTruequesServiceImpl extends BaseService implements MantenimientoRECExcepcionesTruequesService {

	@Resource(name="spusicc.mantenimientoRECExcepcionesTruequesDAO")
	MantenimientoRECExcepcionesTruequesDAO mantenimientoRECExcepcionesTruequesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECExcepcionesTruequesService#deleteExcepcionTrueque(java.util.Map)
	 */
	public void deleteExcepcionTrueque(Map criteria) {
		mantenimientoRECExcepcionesTruequesDAO.deleteExcepcionTrueque(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECExcepcionesTruequesService#getExcepcionesTrueques(java.util.Map)
	 */
	public List getExcepcionesTrueques(Map criteria) {
		return mantenimientoRECExcepcionesTruequesDAO.getExcepcionesTrueques(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECExcepcionesTruequesService#insertExcepcionTrueque(java.util.Map)
	 */
	public void insertExcepcionTrueque(Map criteria) {
		
		List lista = mantenimientoRECExcepcionesTruequesDAO.getExcepcionesTrueques(criteria);
		
		if(lista.size() > 0)
			throw new InvalidIdentifierException(String.class, criteria.get("codigoSapDevuelve"));
		
		mantenimientoRECExcepcionesTruequesDAO.insertExcepcionTrueque(criteria);
	}
}
