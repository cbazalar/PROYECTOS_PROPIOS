package biz.belcorp.ssicc.service.spusicc.love.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVEliminarPuntajeDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso Elimina Puntaje por incumplir con el numero maximo de campaas
 * sin pasar pedido del Programa LOVE
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLOVEliminarPuntajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLOVEliminarPuntajeServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLOVEliminarPuntajeDAO")
	private ProcesoLOVEliminarPuntajeDAO procesoLOVEliminarPuntajeDAO;

	protected void executeStoreProcedure(Map params) {
		procesoLOVEliminarPuntajeDAO.executeEliminarPuntaje(params);
	}
	

	
}

