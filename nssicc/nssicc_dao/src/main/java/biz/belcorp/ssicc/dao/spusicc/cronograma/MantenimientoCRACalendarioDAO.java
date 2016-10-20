package biz.belcorp.ssicc.dao.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="MantenimientoCRACalendarioDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia </a>
 */

/**
 * @author PERRAMIREZ
 *
 */
public interface MantenimientoCRACalendarioDAO extends DAO {

	/**
	 * @param criteria
	 */
	public void insertDiaNoLaborable(Map criteria);

	/**
	 * @param criteria
	 */
	public void insertFeriados(Map criteria);

	/**
	 * @param criteria
	 */
	public void insertAnhio(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getCalendarioFeriados(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public Integer existeAnhio(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public Integer existeFecha(Map criteria);

	/**
	 * @param criteria
	 */
	public void deleteFeriado(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void deleteDiaNoLaborable(Map criteria);

	/**
	 * @param criteria
	 */
	public void updateIndCalendario(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public int getFeriadoValidoAnhio(Map criteria);

	/**
	 * @param criteria
	 */
	public void copiarCalendarioPorActividad(Map criteria);

}
