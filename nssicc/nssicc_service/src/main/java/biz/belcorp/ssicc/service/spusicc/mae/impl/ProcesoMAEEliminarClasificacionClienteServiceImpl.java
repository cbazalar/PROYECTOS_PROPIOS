package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEEliminarClasificacionClienteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAEEliminarClasificacionClienteService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAEEliminarClasificacionClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEEliminarClasificacionClienteServiceImpl extends BaseService implements
ProcesoMAEEliminarClasificacionClienteService {
		
	@Resource(name="spusicc.procesoMAEEliminarClasificacionClienteDAO")
	private ProcesoMAEEliminarClasificacionClienteDAO procesoMAEEliminarClasificacionClienteDAO;

	
	/**
	 * Elimina la clasificacion del cliente
	 * 
	 * @param params
	 */
	public void eliminarClasificacionCliente(Map params){
		procesoMAEEliminarClasificacionClienteDAO.insertClasificacionClienteHistorico(params);
		procesoMAEEliminarClasificacionClienteDAO.eliminarClasificacionCliente(params);
	}

	
	/**
	 * Retorna numero de consultoras clasificacion
	 * @return
	 */
	public String getNroConsultorasClasificacion(Map params){
		return procesoMAEEliminarClasificacionClienteDAO.getNroConsultorasClasificacion(params);
	}

}

