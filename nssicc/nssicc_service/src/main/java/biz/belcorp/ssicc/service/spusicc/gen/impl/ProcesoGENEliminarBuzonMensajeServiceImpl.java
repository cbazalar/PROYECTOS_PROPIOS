package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que elimina el buzon de mensajes.
 * 
 * @author <a href="mailto:frrodriguez@belcorp.biz">Francesco Rodriguez</a>
 */
@Service("spusicc.procesoGENEliminarBuzonMensajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENEliminarBuzonMensajeServiceImpl extends
		BaseInterfazProcesoAbstractService {

	@Resource(name = "spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoMENGenerarMensajesDAO.executeProcesarEliminarBuzonMensaje(params);
	}

	

}
