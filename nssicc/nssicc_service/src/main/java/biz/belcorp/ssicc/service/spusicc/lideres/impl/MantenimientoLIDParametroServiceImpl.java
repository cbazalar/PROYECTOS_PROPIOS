package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDParametroDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDParametroService;

/**
 * Service que ejecutara que ejecutara los metodos del mantenimiento de parametro de Lideres
 *  
 * <p>
 * <a href="MantenimientoLIDParametroServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.mantenimientoLIDParametroService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLIDParametroServiceImpl extends BaseService implements
	MantenimientoLIDParametroService {
	
	@Resource(name="spusicc.mantenimientoLIDParametroDAO")
	private MantenimientoLIDParametroDAO mantenimientoLIDParametroDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDParametroService#getParametro(java.lang.String)
	 */
	public Map getParametro(String codigoPais) {
		return mantenimientoLIDParametroDAO.getParametro(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDParametroService#updateParametro(java.util.Map)
	 */
	public void updateParametro(Map params) {
		Map mapParametro = mantenimientoLIDParametroDAO.getParametro((String)params.get("codigoPais"));
		
		if(mapParametro == null) 
			mantenimientoLIDParametroDAO.insertParametro(params);
		else
			mantenimientoLIDParametroDAO.updateParametro(params);
	}


}
