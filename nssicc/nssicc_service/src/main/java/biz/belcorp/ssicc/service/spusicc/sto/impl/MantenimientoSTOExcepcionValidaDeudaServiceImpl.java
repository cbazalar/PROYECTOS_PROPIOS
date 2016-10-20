package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOExcepcionValidaDeudaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOExcepcionValidaDeudaService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.mantenimientoSTOExcepcionValidaDeudaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOExcepcionValidaDeudaServiceImpl extends BaseService implements MantenimientoSTOExcepcionValidaDeudaService{
	
	@Resource(name="spusicc.mantenimientoSTOExcepcionValidaDeudaDAO")
	private MantenimientoSTOExcepcionValidaDeudaDAO mantenimientoSTOExcepcionValidaDeudaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionValidaDeudaService#getExcepcionValidaDeudaList(java.util.Map)
	 */
	public List getExcepcionValidaDeudaList(Map criteria) {
		return mantenimientoSTOExcepcionValidaDeudaDAO.getExcepcionValidaDeudaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionValidaDeudaService#insertExcepValidDeuda(java.util.Map)
	 */
	public void insertExcepValidDeuda(Map criteria) {
		mantenimientoSTOExcepcionValidaDeudaDAO.insertExcepValidDeuda(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionValidaDeudaService#deleteExcepcionValidaDeuda(java.util.Map)
	 */
	public void deleteExcepcionValidaDeuda(Map parametros) {
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		
		Map criteria = new HashMap();
		
		for (int i = 0; i < selectedItems.length; i++) {
			
			criteria.put("oidClienExcepValiDeud", Integer.valueOf(selectedItems[i]));
			mantenimientoSTOExcepcionValidaDeudaDAO.deleteExcepcionValidaDeuda(criteria);
		}
	}
}