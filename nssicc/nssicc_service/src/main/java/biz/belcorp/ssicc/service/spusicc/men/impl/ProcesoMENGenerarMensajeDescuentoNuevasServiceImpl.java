package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author <a href="mailto: sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMENGenerarMensajeDescuentoNuevasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMENGenerarMensajeDescuentoNuevasServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException {
		log.debug("inicio ProcesoMENGenerarMensajeDescuentoNuevasServiceImpl " + params);
		List listPlantilla = procesoMENGenerarMensajesDAO.getPlantillaProceso(params);
		Iterator it = listPlantilla.iterator();
		while(it.hasNext()){
		  String codigoPlantilla = (String)it.next();
		  params.put("codigoPlantilla", codigoPlantilla);
		  procesoMENGenerarMensajesDAO.executaProcesoMensajes(params);
		}
	}
}
