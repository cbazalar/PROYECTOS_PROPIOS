/**
 * 
 */
package biz.belcorp.ssicc.service.spncd.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCargaVentaDirectaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spncd.ProcesoPERCargaVentaDirectaService;

/**
 * @author peextrdelosreyes
 *
 */

@Service("spncd.procesoPERCargaVentaDirectaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERCargaVentaDirectaServiceImpl extends BaseService	implements ProcesoPERCargaVentaDirectaService {
	
	@Resource(name="spusicc.procesoPERCargaVentaDirectaDAO")
	ProcesoPERCargaVentaDirectaDAO procesoPERCargaVentaDirectaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPERCargaVentaDirectaService#executeCargaVentaDirecta(java.util.Map)
	 */
	public String executeCargaVentaDirecta(Map criteria) {
		return procesoPERCargaVentaDirectaDAO.executeCargaVentaDirecta(criteria);
	}
	/**
	 * @param procesoPERCargaVentaDirectaDAO The procesoPERCargaVentaDirectaDAO to set.
	 */
	public void setProcesoPERCargaVentaDirectaDAO(
			ProcesoPERCargaVentaDirectaDAO procesoPERCargaVentaDirectaDAO) {
		this.procesoPERCargaVentaDirectaDAO = procesoPERCargaVentaDirectaDAO;
	}

}
