package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que Genera mensajes gestionado por el usuario
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("spusicc.procesoMENConsultorasStatusService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMENConsultorasStatusServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
	 log.debug("inicio ProcesoMENConsultorasStatusServiceImpl " + params);
	 List listPlantilla = procesoMENGenerarMensajesDAO.getPlantillaProceso(params);
	 Iterator it = listPlantilla.iterator();
		 while(it.hasNext()){
			 String codigoPlantilla = (String)it.next();
			 params.put("codigoPlantilla", codigoPlantilla);
			 procesoMENGenerarMensajesDAO.executaProcesoMensajes(params);
		 }
	}
	
}