package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCRegularizarParticipantesRecomendantesDAO
		extends DAO {

	/**
	 * Proceso que regulariza Participantes y Recomendantes
	 * 
	 * @param params
	 */
	public void executeRegularizarParticipantesRecomendantes(Map params);

}
