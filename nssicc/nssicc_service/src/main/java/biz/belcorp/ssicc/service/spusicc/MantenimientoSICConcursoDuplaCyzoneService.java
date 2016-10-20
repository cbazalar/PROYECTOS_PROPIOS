/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.InterfazSICConcursoDuplaCyzone;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSICConcursoDuplaCyzoneService.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius Vasquez</a>
 * 
 */
public interface MantenimientoSICConcursoDuplaCyzoneService extends Service {

	
	public List getConcursosMantenenimientoDuplaCyzoneList(Map criteria);
	
	
	public InterfazSICConcursoDuplaCyzone getConcursoDuplaCyzone(
			String codigoConcurso);

	public void updateConcursoDuplaCyzone(
			InterfazSICConcursoDuplaCyzone concursoDupla);

	public void insertConcursoDuplaCyzone(
			InterfazSICConcursoDuplaCyzone concursoDupla);

	/**
	 * 
	 * @return el siguiente numero para la entidad de concurso-dupla
	 */
	public String getNextConcursoDuplaCyzone();
	
	
}