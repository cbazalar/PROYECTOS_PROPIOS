package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Proceso que realiza el calculo Objetivo Ingresos Efectivos Lder
 * 
 * <p>
 * <a href="ProcesoLETCalculoObjetivoIngresoEfectivoLiderServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Service("spusicc.procesoLETCalculoObjetivoIngresoEfectivoLiderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCalculoObjetivoIngresoEfectivoLiderServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLETCalculoObjetivoIngresoEfectivoLiderDAO")
	private ProcesoLETCalculoObjetivoIngresoEfectivoLiderDAO procesoLETCalculoObjetivoIngresoEfectivoLiderDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		procesoLETCalculoObjetivoIngresoEfectivoLiderDAO.executeProcesoLETCalculoObjetivoIngresoEfectivoLider(params);		
	}
	
}
