/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCargaVentaDirectaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERCargaVentaDirectaService;

/**
 * @author peextrdelosreyes
 *
 */
@Service("spusicc.procesoPERCargaVentaDirectaService")
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

	public String bloqueoControlAnual(Map criteria) {
		//verificamos si esta bloqueado la entidad de control anual
		String estadoBloqueo = procesoPERCargaVentaDirectaDAO.getVerificaBloqueoControlAnual(criteria);
		
		//si no esta bloqueado, bloqueamos la tabla de entidades de control anual
		if("1".equals(estadoBloqueo)) {
			criteria.put("estadoControlAnual", "3");
			procesoPERCargaVentaDirectaDAO.updateBloqueoControlAnual(criteria);
		}	
		
		return estadoBloqueo;
	}
	
	public void desbloqueoControlAnual(Map criteria) {
		//desbloqueamos la tabla de entidades de control anual
		criteria.put("estadoControlAnual", "1");
		procesoPERCargaVentaDirectaDAO.updateBloqueoControlAnual(criteria);
	}
	
}
