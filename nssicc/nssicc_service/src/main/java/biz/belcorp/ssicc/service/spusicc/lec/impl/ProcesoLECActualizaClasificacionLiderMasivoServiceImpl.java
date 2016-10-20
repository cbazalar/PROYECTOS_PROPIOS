package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECActualizaClasificacionLiderMasivoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Proceso que realiza la Actualizacion de Clasificacion de Lider Masivo
 * 
 * <p>
 * <a href="ProcesoLECActualizaClasificacionLiderMasivoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
@Service("spusicc.procesoLECActualizaClasificacionLiderMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECActualizaClasificacionLiderMasivoServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLECActualizaClasificacionLiderMasivoDAO")
	private ProcesoLECActualizaClasificacionLiderMasivoDAO procesoLECActualizaClasificacionLiderMasivoDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		procesoLECActualizaClasificacionLiderMasivoDAO.executeProcesoLECActualizaClasificacionLiderMasivo(params);
		
	}
	
}
