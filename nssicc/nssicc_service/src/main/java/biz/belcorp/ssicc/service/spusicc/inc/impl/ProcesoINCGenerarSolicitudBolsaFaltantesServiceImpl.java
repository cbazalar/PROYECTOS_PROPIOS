package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarSolicitudBolsaFaltantesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service que va a generar Solicitudes de Servicio de bolsa faltantes
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCGenerarSolicitudBolsaFaltantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCGenerarSolicitudBolsaFaltantesServiceImpl extends
	BaseInterfazProcesoAbstractService {
	        
	@Resource(name="spusicc.procesoINCGenerarSolicitudBolsaFaltantesDAO")
	private ProcesoINCGenerarSolicitudBolsaFaltantesDAO procesoINCGenerarSolicitudBolsaFaltantesDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCGenerarSolicitudBolsaFaltantesDAO.executeGenerarSolicitudBolsaFaltantes(params);
	}
	

}
