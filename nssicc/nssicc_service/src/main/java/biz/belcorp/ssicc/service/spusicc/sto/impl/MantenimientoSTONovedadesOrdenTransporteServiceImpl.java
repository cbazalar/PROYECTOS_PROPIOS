package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTONovedadesOrdenTransporteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTONovedadesOrdenTransporteService;

/**
 * @author peextdoliva
 */
@Service("spusicc.mantenimientoSTONovedadesOrdenTransporteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTONovedadesOrdenTransporteServiceImpl extends BaseService implements MantenimientoSTONovedadesOrdenTransporteService{
	
	@Resource(name="spusicc.mantenimientoSTONovedadesOrdenTransporteDAO")
	private MantenimientoSTONovedadesOrdenTransporteDAO mantenimientoSTONovedadesOrdenTransporteDAO;	
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTONovedadesOrdenTransporteService#getNovedadesOrdenTransporte(java.util.Map)
	 */
	public List getNovedadesOrdenTransporte(Map criteria){
		return mantenimientoSTONovedadesOrdenTransporteDAO.getNovedadesOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTONovedadesOrdenTransporteService#deleteNovedadesOrdenTransporte(java.lang.String[])
	 */
	public void deleteNovedadesOrdenTransporte(String[] items){		
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			String id = items[i];			
			criteria.put("codigoNovedad", id);			
			mantenimientoSTONovedadesOrdenTransporteDAO.deleteNovedadesOrdenTransporte(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTONovedadesOrdenTransporteService#insertNovedadesOrdenTransporte(java.util.Map)
	 */
	public void insertNovedadesOrdenTransporte(Map criteria){
		mantenimientoSTONovedadesOrdenTransporteDAO.insertNovedadesOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTONovedadesOrdenTransporteService#updateNovedadesOrdenTransporte(java.util.Map)
	 */
	public void updateNovedadesOrdenTransporte(Map criteria){
		mantenimientoSTONovedadesOrdenTransporteDAO.updateNovedadesOrdenTransporte(criteria);
	}
}