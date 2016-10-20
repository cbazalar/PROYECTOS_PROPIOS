package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoRECGestionBoletasRecojoNoExitosasService extends Service {
	
	/**
	 * @param params
	 * @return
	 */
	public List getBoletasRecojoNOExitosasList(Map params);

	/**
	 * @param criteria
	 */
	public void executeProcesoBoletaRecojoNoExitosa(Map params);

}
