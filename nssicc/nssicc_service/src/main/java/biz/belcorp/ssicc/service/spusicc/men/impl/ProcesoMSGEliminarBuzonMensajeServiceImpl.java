/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ProcesoMAEClasificacionClientesDAO;
import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMSGEliminarBuzonMensajeService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoMSGEliminarBuzonMensajeServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */	
@Service("spusicc.procesoMSGEliminarBuzonMensajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMSGEliminarBuzonMensajeServiceImpl extends BaseService implements ProcesoMSGEliminarBuzonMensajeService {
	
	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;
	
	@Resource(name="spusicc.procesoMAEClasificacionClientesDAO")
	private ProcesoMAEClasificacionClientesDAO procesoMAEClasificacionClientesDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.ProcesoMSGEliminarBuzonMensajeService#executeProcesarEliminarBuzonMensaje(java.util.Map)
	 */
	public void executeProcesarEliminarBuzonMensaje(Map criteria) {
		procesoMENGenerarMensajesDAO.executeProcesarEliminarBuzonMensaje(criteria);
		if(Constants.NRO_UNO.equals((String)criteria.get("indicadorValMae"))){
			this.procesoMAEClasificacionClientesDAO.executeValidacionClientes(criteria);
		}
	}

}