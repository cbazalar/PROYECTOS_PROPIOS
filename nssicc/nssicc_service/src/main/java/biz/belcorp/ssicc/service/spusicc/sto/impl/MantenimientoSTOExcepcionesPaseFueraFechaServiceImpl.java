package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOExcepcionesPaseFueraFechaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOExcepcionesPaseFueraFechaService;

/**
 * @author peextdoliva
 */
@Service("spusicc.mantenimientoSTOExcepcionesPaseFueraFechaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOExcepcionesPaseFueraFechaServiceImpl extends BaseService implements MantenimientoSTOExcepcionesPaseFueraFechaService{
	
	@Resource(name="spusicc.mantenimientoSTOExcepcionesPaseFueraFechaDAO")
	private MantenimientoSTOExcepcionesPaseFueraFechaDAO mantenimientoSTOExcepcionesPaseFueraFechaDAO;	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionesPaseFueraFechaService#getExcepcionesPaseFueraFecha(java.util.Map)
	 */
	public List getExcepcionesPaseFueraFecha(Map criteria){
		return mantenimientoSTOExcepcionesPaseFueraFechaDAO.getExcepcionesPaseFueraFecha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionesPaseFueraFechaService#deleteExcepciones(java.lang.String[])
	 */
	public void deleteExcepciones(String[] items){		
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			String id = items[i];			
			criteria.put("codigoPeriodo", StringUtils.split(id, "|")[0]);
			criteria.put("codigoCliente", StringUtils.split(id, "|")[1]);
			mantenimientoSTOExcepcionesPaseFueraFechaDAO.deleteExcepciones(criteria);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionesPaseFueraFechaService#insertExcepciones(java.util.Map)
	 */
	public void insertExcepciones(Map criteria){
		mantenimientoSTOExcepcionesPaseFueraFechaDAO.insertExcepciones(criteria);
	}
	
	
	

	
}