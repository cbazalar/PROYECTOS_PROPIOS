package biz.belcorp.ssicc.service.spusicc.sto.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.dao.spncd.model.SecuenciaValidacion;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOSecuenciaValidacionDAO;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOSecuenciaValidacionService;


/**
 * Service con metodos para las consultas invocados por la pantalla de Secuencia Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOSecuenciaValidacionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
@Service("sto.mantenimientoSTOSecuenciaValidacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOSecuenciaValidacionServiceImpl extends BaseService implements MantenimientoSTOSecuenciaValidacionService {
	
	@Resource(name="sto.mantenimientoSTOSecuenciaValidacionDAO")
	MantenimientoSTOSecuenciaValidacionDAO mantenimientoSTOSecuenciaValidacionDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOSecuenciaValidacionService#getValidacionesByCriteria(java.util.Map)
	 */
	public List getValidacionesByCriteria(Map params) {
		return mantenimientoSTOSecuenciaValidacionDAO.getValidacionesByCriteria(params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOSecuenciaValidacionService#getTipoDocumentoList(java.util.Map)
	 */
	public List getTipoDocumentoList(Map params) {
		return mantenimientoSTOSecuenciaValidacionDAO.getTipoDocumentoList(params);
	}




	public boolean insertPametroSecuenciaValidacionSTO(SecuenciaValidacion bean, Usuario usuario) {
		return mantenimientoSTOSecuenciaValidacionDAO.insertPametroSecuenciaValidacionSTO(bean,usuario);
	}
	
	public boolean updatePametroSecuenciaValidacionSTO(Map criteria,
			Usuario usuario) {
		return mantenimientoSTOSecuenciaValidacionDAO.updatePametroSecuenciaValidacionSTO(criteria,usuario);
		
	}


	public void deletePametroSecuenciaValidacionSTO(SecuenciaValidacion bean) {
		 mantenimientoSTOSecuenciaValidacionDAO.deletePametroSecuenciaValidacionSTO(bean);
		
	}
}