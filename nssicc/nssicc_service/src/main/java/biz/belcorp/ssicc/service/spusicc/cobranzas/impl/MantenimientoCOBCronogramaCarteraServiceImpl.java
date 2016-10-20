package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBCronogramaCarteraDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CronogramaCartera;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBCronogramaCarteraService;



/**
 * Service que controla el Mantenimiento del Cronograma De Cartera
 *  
 * <p>
 * <a href="MantenimientoCOBCronogramaCarteraServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz"></a>
 * 
 */
@Service("spusicc.mantenimientoCOBCronogramaCarteraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOBCronogramaCarteraServiceImpl extends BaseService implements MantenimientoCOBCronogramaCarteraService {

	@Resource(name="spusicc.mantenimientoCOBCronogramaCarteraDAO")
	private MantenimientoCOBCronogramaCarteraDAO mantenimientoCOBCronogramaCarteraDAO;



	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBCronogramaCarteraService#getCronogramaCarteraList(java.util.Map)
	 */
	public List  getCronogramaCarteraList(Map criteria) {
		return mantenimientoCOBCronogramaCarteraDAO.getCronogramaCarteraList(criteria);
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBCronogramaCarteraService#getCronogramaCarteraById(biz.belcorp.ssicc.spusicc.cobranzas.model.CronogramaCartera)
     */
    public CronogramaCartera getCronogramaCarteraById(CronogramaCartera cronogramaCartera){
		return mantenimientoCOBCronogramaCarteraDAO.getCronogramaCarteraById(cronogramaCartera);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBCronogramaCarteraService#updateCronogramaCartera(java.util.Map)
	 */
	public void updateCronogramaCartera(Map criteria){
		this.mantenimientoCOBCronogramaCarteraDAO.updateCronogramaCartera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBCronogramaCarteraService#existeCarteraAsignada(java.util.Map)
	 */
	public int existeCarteraAsignada(Map criteria) {
		return mantenimientoCOBCronogramaCarteraDAO.existeCarteraAsignada(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBCronogramaCarteraService#cierreCartera(java.util.Map)
	 */
	public void cierreCartera(Map criteria) {
		mantenimientoCOBCronogramaCarteraDAO.cierreCartera(criteria);
	}

	
}
