package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.ProcesarINCConfiguracionConcursoCuponElectronico;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoINCHabilitacionConcursoCargaPuntajeService  extends Service{

	/**
	 * Retorna la lista de concursos Activos
	 * @return
	 */
	List getListParametrosConcursosActivos();

	/**
	 *  Habilita concursos 
	 * @param criteria
	 */
	void insertConcursoHabilitado(Map criteria);

	/**
	 * Retorna la lista de Concursos habilitados
	 * @return
	 */
	List getListConcursoHabilitado();
	
	/**
	 * Retorna la lista de Motivos
	 * @return
	 */
	List  getListMotivoConcursos();

	
	/**
	 * Borra todos los registros de concursos habilitados
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
	 * Retorna la lista de Concursos para cyzone
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
	 * Registra la información de un bloqueoLider.
	 *  
	 * @param procesarINCConfiguracionConcursoCuponElectronico
	 * Configuración Concurso Cupón Electrónico a ser insertado.
	 * @return
	 */
	public String insertMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico);

	/**
	 * Actualiza la información de un bloqueoLider.
	 *  
	 * @param procesarINCConfiguracionConcursoCuponElectronico
	 * Configuración Concurso Cupón Electrónico a ser actualizado.
	 * 
	 */
	public void updateMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico);
	
}
