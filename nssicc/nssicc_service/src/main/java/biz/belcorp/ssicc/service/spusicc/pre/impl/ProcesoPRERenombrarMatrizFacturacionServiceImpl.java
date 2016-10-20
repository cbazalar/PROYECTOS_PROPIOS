package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionService;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("spusicc.procesoPRERenombrarMatrizFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPRERenombrarMatrizFacturacionServiceImpl extends BaseService implements ProcesoPRERenombrarMatrizFacturacionService {
		
	@Resource(name="spusicc.procesoPRERenombrarMatrizFacturacionDAO")
	private ProcesoPRERenombrarMatrizFacturacionDAO procesoPRERenombrarMatrizFacturacionDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionService#getOidCabeceraCampanaDestino(java.lang.String)
	 */
	@Override
	public String getOidCabeceraCampanaDestino(String codigoPeriodo) {
		return procesoPRERenombrarMatrizFacturacionDAO.getOidCabeceraCampanaDestino(codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionService#getExisteOfertaEnMatrizFacturacionDestino(java.lang.String)
	 */
	@Override
	public int getExisteOfertaEnMatrizFacturacionDestino(String oidCabecera) {
		return procesoPRERenombrarMatrizFacturacionDAO.getExisteOfertaEnMatrizFacturacionDestino(oidCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionService#executeRenombrarMatrizFacturacion(java.util.Map)
	 */
	@Override
	public void executeRenombrarMatrizFacturacion(Map params) {
		procesoPRERenombrarMatrizFacturacionDAO.executeRenombrarMatrizFacturacion(params);
	}
}