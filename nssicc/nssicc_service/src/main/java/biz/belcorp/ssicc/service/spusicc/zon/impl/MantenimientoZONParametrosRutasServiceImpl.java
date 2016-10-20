/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONParametrosRutasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONParametrosRutasService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONParametrosRutasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */
@Service("spusicc.mantenimientoZONParametrosRutasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONParametrosRutasServiceImpl extends BaseService
		implements MantenimientoZONParametrosRutasService {
	
	@Resource(name="spusicc.mantenimientoZONParametrosRutasDAO")
	private MantenimientoZONParametrosRutasDAO mantenimientoZONParametrosRutasDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONParametrosRutasService#getParametrosRutasList(java.util.Map)
	 */
	public List getParametrosRutasList(Map criteria){
		return mantenimientoZONParametrosRutasDAO.getParametrosRutasList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONParametrosRutasService#deleteParametroRuta(java.lang.String[])
	 */
	public void deleteParametroRuta(String[] items, String codigoUsuario){		
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", codigoUsuario);
		for(int i = 0; i < items.length; i++){	
			String id = items[i];
			criteria.put("codigoPais", StringUtils.split(id, "|")[0]);			
			criteria.put("codigoNovedad", StringUtils.split(id, "|")[1]);
			mantenimientoZONParametrosRutasDAO.deleteParametroRuta(criteria);
			
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONParametrosRutasService#updateParametroRutaDirectorio(java.util.Map)
	 */
	public void updateParametroRutaDirectorio(Map params){
		mantenimientoZONParametrosRutasDAO.updateParametroRutaDirectorio(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONParametrosRutasService#insertParametroRutaDirectorio(java.util.Map)
	 */
	public void insertParametroRutaDirectorio(Map params){
		mantenimientoZONParametrosRutasDAO.insertParametroRutaDirectorio(params);
	}
}
