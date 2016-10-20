package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCCargaDespachoConcursoRxPService extends Service {

	/**
	 * Retorna la lista de Concursos Vigentes RxP
	 * 
	 * @return
	 */
	List getListConcursoVigentesRxP();

	/**
	 * Realiza la validacion de Periodo y Concurso RxP
	 * 
	 * @param cabecera
	 */
	String validarDespachoConcursoRxP(Map criteria);
	
	/**
	 * Realiza la carga de los datos en las tablas de Despacho Concurso RxP
	 * 
	 * @param cabecera
	 */
	void executeCargaDespachoConcursoRxP(Map params);

	/**
	 * Recupera los codigos de Ventas relacionados a un concurso RxP 
	 * 
	 * @param criteria
	 * @return
	 */
	List getDespachoConcursoRxPDetalle(Map criteria);

	/**
	 * Recupera datos del Concurso RxP 
	 * 
	 * @param criteria
	 * @return
	 */
	List getDespachoConcursoRxP(Map criteria);

}
