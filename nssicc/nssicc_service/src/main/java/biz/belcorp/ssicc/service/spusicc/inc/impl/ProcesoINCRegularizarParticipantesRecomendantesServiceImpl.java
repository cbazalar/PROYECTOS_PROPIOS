package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCRegularizarParticipantesRecomendantesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que regulariza Participantes y Recomendantes
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCRegularizarParticipantesRecomendantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCRegularizarParticipantesRecomendantesServiceImpl extends
	BaseInterfazProcesoAbstractService {
	           
	
	@Resource(name="spusicc.procesoINCRegularizarParticipantesRecomendantesDAO")
	private ProcesoINCRegularizarParticipantesRecomendantesDAO procesoINCRegularizarParticipantesRecomendantesDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCRegularizarParticipantesRecomendantesDAO.executeRegularizarParticipantesRecomendantes(params);
	}
	
	/**
	 * @param procesoINCRegularizarParticipantesRecomendantesServiceDAO the procesoINCRegularizarParticipantesRecomendantesServiceDAO to set
	 */
	public void setProcesoINCRegularizarParticipantesRecomendantesDAO(
			ProcesoINCRegularizarParticipantesRecomendantesDAO procesoINCRegularizarParticipantesRecomendantesDAO) {
		this.procesoINCRegularizarParticipantesRecomendantesDAO = procesoINCRegularizarParticipantesRecomendantesDAO;
		
	}
	
}
