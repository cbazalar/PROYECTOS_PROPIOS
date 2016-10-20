package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActivarEntregaDireccionVacacionesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que permite activar entrega en Direccin de Vacaciones
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoMAEActivarEntregaDireccionVacacionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEActivarEntregaDireccionVacacionesServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoMAEActivarEntregaDireccionVacacionesDAO")
	private ProcesoMAEActivarEntregaDireccionVacacionesDAO procesoMAEActivarEntregaDireccionVacacionesDAO;

	protected void executeStoreProcedure(Map params) {
		procesoMAEActivarEntregaDireccionVacacionesDAO.executeActivarEntregaDireccionVacaciones(params);
	}
	
	
}
