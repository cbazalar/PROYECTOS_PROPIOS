package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.ProcesarINCConfiguracionConcursoCuponElectronico;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntaje;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoINCHabilitacionConcursoCargaPuntajeDAO extends DAO {

	/**
	 * Retorna la lista de concursos Activos
	 * @return
	 */
	List getListParametrosConcursosActivos();

	/**
	 * Inserta Concurso Habilitados
	 * @param criteria
	 */
	void insertConcursoHabilitado(Map criteria);
	

	/**
	 * Retorna la lista de Concursos habilitados
	 * @return
	 */
	List getListConcursoHabilitado();

	/**
	 * se encaga de hacer el rgsitro de habilitacion de los consursos
	 * @param list
	 * @param login
	 */
	void insertCargaPuntaje(List listConcursoSeleccionados, String login);

	/**
	 * elimina la carga de puntaje
	 * @param oidConcurso
	 */
	void deleteCargaPuntaje(String oidConcurso);

	/**
	 * Inserta la carga de Puntaje
	 * @param cargaPuntaje
	 */
	void insertCargaPuntaje(CargaPuntaje cargaPuntaje);

	/**
	 * Retorna la lista de Motivos
	 * @return
	 */
	List getListMotivoConcursos();

	/**
	 * Elimina los registros de concursos habilitados
	 * @return
	 */
	void deleteConcursoHabilitado();

	/**
	 * Retorna los concursos Activos filtrados por una observacion
	 * @param valObservacion
	 * @return
	 */
	List getListConcursosActivosObs(String valObservacion);
	
	/**
	 * Retorna la lista de Concurso vigentes y cerrados
	 * @return
	 */
	List getListConcursosVigentesCerrados();
	
	/**
	 * Retorna la lista de Concurso vigentes y cerrados para Reporte Ganadoras
	 * @return
	 */
	List getListConcursosVigentesCerradosGanadoras();
	
	/**
	 * Retorna la lista de Concursos Cyzone
	 * @return
	 */
	List getListConcursoCyzo();
	
	/**
	 * Retorna la lista de Concursos para programas de reconocimiento
	 * @return
	 */
	List getListConcursoReconocido();

	/**
	 * Retorna el indicador de producto exigido por codigo de concurso
	 * @param oidConcurso
	 * @return
	 */
	int getIndicadorProdExig(Integer oidConcurso);
	
	/**
	 *  retorna la lista de Concurso de Cupones Electrónicos
	 *  
	 * @param criteria
	 * @return
	 */
	public List getListaConcursosCEActivosByConcursoPeriodo(Map criteria);
	
	/**
	 * Obtiene datos relacionados a un tipo de Concurso de cupones electrónicos como: código, descripción, periodo, estado
	 * 
	 * @param params
	 * @return
	 */
	public Map getDatosConcursoCuponElectronico(Map params);
	
	/**
	 * Metodo que devuelve CERO o UNO, para indicar si existe el Concurso
	 * @param criteria
	 * @return
	 */
	public String getValidarExisteConcursoCuponElectronico(Map criteria);
	
	/**
	 * Registra la información de un bloqueoLider.
	 *  
	 * @param procesarINCConfiguracionConcursoCuponElectronico
	 * Configuración Concurso Cupón Electrónico a ser insertado.
	 * 
	 */
	public void insertMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico);

	/**
	 * Actualiza la información de un bloqueoLider.
	 *  
	 * @param procesarINCConfiguracionConcursoCuponElectronico
	 * Configuración Concurso Cupón Electrónico a ser actualizado.
	 * 
	 */
	public void updateMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico);
	
}
