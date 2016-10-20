package biz.belcorp.ssicc.dao.spusicc.sessionexperte;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.PeriodoPrograma;

/**
 * TODO Include class description here.
 *
 * <p>
 * <a href="MantenimientoSSEPeriodoProgramaDAO.java.html"> <i>View Source</i> </a>
 * </p>
 *
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 *
 */
public interface MantenimientoSSEPeriodoProgramaDAO extends DAO {

	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos PeriodoPrograma, poblados.
	 */
    public List getPeriodosProgramaByCriteria(PeriodoPrograma periodo);
	
	/**
	 * Inserta un Periodo de Programa
	 * @param producto ProductoPeriodo, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que inserta.
	 */
	public void insertPeriodoProgramaSSE(PeriodoPrograma periodo, Usuario usuario);

	/**
	 * Cambia el estado a INACTIVO del Periodo de Programa (No lo elimina).
	 * @param producto ProductoPeriodo, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que elimina.
	 */
	public void updateInactivarPeriodoProgramaSSE(PeriodoPrograma periodo, Usuario usuario);

	/**
	 * Obtiene el MAXIMO codigo de periodo existente para un programa
	 * @param periodo Periodo que contiene el codigo de pais y programa
	 * @return Maximo Cdigo de Periodo para un programa 
	 */
	public String getPeriodoDefaultByPrograma(PeriodoPrograma periodo);

	/**
	 * Obtiene el SIGUIENTE codigo de periodo para un programa
	 * @param periodo Periodo que contiene el codigo de pais y programa
	 * @return SIGUIETNE Cdigo de Periodo para un programa 
	 */
	public String getNextPeriodoBySSEProgPerio(PeriodoPrograma periodo);
	
	/**
	 * @param producto Objeto tipo ProductoPeriodo que contiene el codigo del pais y el programa. 
	 * @return El siguiente codigo de programa para un pais dado.
	 */
//	public String getNextCodigoPeriodoByProgramaPais(ProductoPeriodo producto);
	
}
