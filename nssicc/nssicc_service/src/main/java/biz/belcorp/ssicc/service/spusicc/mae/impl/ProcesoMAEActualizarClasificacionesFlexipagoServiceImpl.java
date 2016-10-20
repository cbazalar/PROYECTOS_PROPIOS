package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarClasificacionesFlexipagoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para Actualizar Clasificaciones Flexipago
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 */
@Service("spusicc.procesoMAEActualizarClasificacionesFlexipagoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEActualizarClasificacionesFlexipagoServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoMAEActualizarClasificacionesFlexipagoDAO")
	private ProcesoMAEActualizarClasificacionesFlexipagoDAO procesoMAEActualizarClasificacionesFlexipagoDAO;

	protected void executeStoreProcedure(Map params) {
		procesoMAEActualizarClasificacionesFlexipagoDAO.executeActualizarClasificacionesFlexipago(params);
	}
	

}
