package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCampanha;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaCliente;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaRegion;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptaDemandaZona;
import biz.belcorp.ssicc.dao.edu.model.CalificacionAptasDemandaCurso;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUCalificacionAptasDemandaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 */


/**
 * @author peextcbazalar
 *
 */
public interface ProcesoEDUCalificacionAptasDemandaDAO extends DAO {

	/**
	 * Devuelve la lista de registros de Calificacin de Aptas por Demanda
	 * @param calificacionAptasCurso
	 * 			objeto CalificacionAptasDemandaCurso poblado.
	 */
	public List getCalificacionAptasDemandaCurso(CalificacionAptasDemandaCurso calificacionAptasCurso);

	/**
	 * Devuelve la lista de registros de Zonas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaZona
	 * 			objeto CalificacionAptaDemandaZona poblado.
	 */
	public List getCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona);

	/**
	 * Devuelve la lista de registros de regiones para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaRegion
	 * 			objeto CalificacionAptaDemandaRegion poblado.
	 */
	public List getCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion);

	
	/**
	 * Devuelve la lista de registros de clientes para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCliente
	 * 			objeto CalificacionAptaDemandaCliente poblado.
	 */
	public List getCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente);

	/**
	 * Devuelve la lista de registros de campaas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCampanha
	 * 			objeto CalificacionAptaDemandaCampanha poblado.
	 */
	public List getCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha);
	

	/**
	 * Inserta un registro de Zonas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaZona
	 * 			objeto CalificacionAptaDemandaZona poblado.
	 */
	public void insertCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona,Usuario usuario);

	/**
	 * Inserta un registro de region para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaRegion
	 * 			objeto CalificacionAptaDemandaRegion poblado.
	 */
	public void insertCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion,Usuario usuario);

	/**
	 * Inserta un registro de cliente para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCliente
	 * 			objeto CalificacionAptaDemandaCliente poblado.
	 */
	public void insertCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente,Usuario usuario);

	/**
	 * inserta un registro de campaa para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCampanha
	 * 			objeto CalificacionAptaDemandaCampanha poblado.
	 */
	public void insertCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha,Usuario usuario);
	

	/**
	 * Elimina un registro de Zonas para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaZona
	 * 			objeto CalificacionAptaDemandaZona poblado.
	 */
	public void removeCalificacionAptaDemandaZona(CalificacionAptaDemandaZona calificacionAptaDemandaZona);

	/**
	 * Elimina un registro de region para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaRegion
	 * 			objeto CalificacionAptaDemandaRegion poblado.
	 */
	public void removeCalificacionAptaDemandaRegion(CalificacionAptaDemandaRegion calificacionAptaDemandaRegion);

	/**
	 * Elimina un registro de cliente para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCliente
	 * 			objeto CalificacionAptaDemandaCliente poblado.
	 */
	public void removeCalificacionAptaDemandaCliente(CalificacionAptaDemandaCliente calificacionAptaDemandaCliente);

	/**
	 * Elimina un registro de campaa para la calificacin de Aptas por Demanda
	 * @param calificacionAptaDemandaCampanha
	 * 			objeto CalificacionAptaDemandaCampanha poblado.
	 */
	public void removeCalificacionAptaDemandaCampanha(CalificacionAptaDemandaCampanha calificacionAptaDemandaCampanha);
	
	
	/**
	 * Efectua el proceso de Calificacion de Aptas a Demanda 
	 * @param criteria
	 */
	public void executeCalificacionAptasDemanda(Map criteria);
	
	
	/**
	 * Efectua el proceso Previo de Calificacion de Aptas a Demanda 
	 * @param criteria
	 */
	public void executePrevioCalificacionAptasDemanda(Map criteria);

	/**
	 * Devuelve lista de consultoras obtenidas previamente por el proceso Previo de Calificacion a Demanda 
	 * @param criteria
	 * @return
	 */
	public List getPrevioCalificacionAptasDemanda(Map criteria);
}
