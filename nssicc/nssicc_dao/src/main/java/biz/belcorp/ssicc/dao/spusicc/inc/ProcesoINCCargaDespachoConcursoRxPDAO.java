package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCCargaDespachoConcursoRxPDAO extends DAO {

	/**
	 * Retorna la lista de Concursos Vigentes RxP
	 * 
	 * @return
	 */
	List getListConcursoVigentesRxP();

	/**
	 * Realiza la validacion del concurso RxP y el periodo seleccionado
	 * 
	 * @return
	 */
	String validarDespachoConcursoRxP(Map criteria);
	
	/**
	 * Realiza Validacion del Codigo de Venta para 
	 * 
	 * @param criteria
	 * @return
	 */
	List validarCodigoVentaRxP(Map criteria);
	
	/**
	 * Inserta un registro en la tabla cabecera de Despacho Concurso RxP
	 * 
	 * @param cabecera
	 */
	void insertDespachoConcursoRxP(Map cabecera);

	/**
	 * Actualiza un registro en la tabla cabecera de Despacho Concurso RxP
	 * 
	 * @param cabecera
	 */
	void updateDespachoConcursoRxP(Map cabecera);
	
	/**
	 * Inserta un registro en la tabla detalle de Despacho Concurso RxP
	 * 
	 * @param detalle
	 */
	void insertDespachoConcursoRxPDetalle(Map detalle);
	
	/**
	 * Borrar los registros de la tabla detalle de Despacho Concurso RxP para un determinado concurso
	 * 
	 * @param detalle
	 */
	void deleteDespachoConcursoRxPDetalle(Map detalle);

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

