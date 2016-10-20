package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarCodigoVentaPremioCanastaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * The Class ProcesoLECGenerarCodigoVentaPremioCanastaServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/07/2014
 */
@Service("spusicc.procesoLECGenerarCodigoVentaPremioCanastaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECGenerarCodigoVentaPremioCanastaServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoLECGenerarCodigoVentaPremioCanastaDAO")
    private ProcesoLECGenerarCodigoVentaPremioCanastaDAO procesoLECGenerarCodigoVentaPremioCanastaDAO;
	
	

	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,Exception {
		procesoLECGenerarCodigoVentaPremioCanastaDAO.executeProcesoLECGenerarCodigoVentaPremioCanasta(params);
		
	}  

}
