/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSICConcursoDuplaCyzone;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSICConcursoDuplaCyzoneDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius Vasquez</a>
 * 
 */
public interface MantenimientoSICConcursoDuplaCyzoneDAO extends DAO {

	public List getConcursosMantenenimientoDuplaCyzoneList(Map criteria);
	
	public InterfazSICConcursoDuplaCyzone getConcursoDuplaCyzone(String codigoConcurso);

	public void updateConcursoDuplaCyzone(InterfazSICConcursoDuplaCyzone concursoDuplaCyzone);	
	
	public void insertConcursoDuplaCyzone(InterfazSICConcursoDuplaCyzone concursoDuplaCyzone);	

	public String getNextConcursoDuplaCyzone();	
	
}