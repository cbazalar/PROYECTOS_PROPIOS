package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReversionEntregaPremiosPorUltimoNivelDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que realiza la reversion de entrega de premios
 * por sobrepasar lmite de ltimo nivel
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCReversionEntregaPremiosPorUltimoNivelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCReversionEntregaPremiosPorUltimoNivelServiceImpl extends
	BaseInterfazProcesoAbstractService {
	               
	@Resource(name="spusicc.procesoINCReversionEntregaPremiosPorUltimoNivelDAO")
	private ProcesoINCReversionEntregaPremiosPorUltimoNivelDAO procesoINCReversionEntregaPremiosPorUltimoNivelDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCReversionEntregaPremiosPorUltimoNivelDAO.executeReversionEntregaPremiosPorUltimoNivel(params);
	}
	

}
