package biz.belcorp.ssicc.service.edu;

import java.util.Map;

/**
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 *
 */
public interface ProcesoEDUGenerarPlanillaProgramacionService {
	
	/**
	 * Realiza el proceso de Actualizacin de Planilla Programacin
	 * @param codigoPais
	 * @param params
	 * @throws Exception
	 */
	public void executeGenerarPlanillaProgramacion(Map params) throws Exception;
	
	public Integer getCantidadPlanillasRegion(Map criteria);	
	
}
